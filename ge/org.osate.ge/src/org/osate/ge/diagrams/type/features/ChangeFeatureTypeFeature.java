/*******************************************************************************
 * Copyright (C) 2013 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0073.
 *******************************************************************************/
package org.osate.ge.diagrams.type.features;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.osate.aadl2.AbstractFeature;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.Feature;
import org.osate.ge.diagrams.common.patterns.FeaturePattern;
import org.osate.ge.services.AadlModificationService;
import org.osate.ge.services.BusinessObjectResolutionService;
import org.osate.ge.services.AadlModificationService.AbstractModifier;
import org.osate.ge.util.StringUtil;

public class ChangeFeatureTypeFeature extends AbstractCustomFeature {
	private final AadlModificationService aadlModService;
	private final BusinessObjectResolutionService bor;
	private final EClass featureType;	
	
	@Inject
	public ChangeFeatureTypeFeature(final AadlModificationService aadlModService, final BusinessObjectResolutionService bor, 
			final IFeatureProvider fp, final @Named("Feature Type") EClass featureType) {
		super(fp);
		this.aadlModService = aadlModService;
		this.bor = bor;
		this.featureType = featureType;
	}

	@Override
    public String getName() {
        return "Convert to " + StringUtil.camelCaseToUser(featureType.getName());
    }
	
    @Override
	public boolean isAvailable(final IContext context) {
		final ICustomContext customCtx = (ICustomContext)context;
		final PictogramElement[] pes = customCtx.getPictogramElements();		
		if(customCtx.getPictogramElements().length < 1 || !(customCtx.getPictogramElements()[0] instanceof Shape)) {
			return false;
		}
		
		// Check that the shape represents a feature and that the feature is owned by the classifier represented by the shape's container, and that the classifier can
		// contain features of the type this feature changes features into.
		final PictogramElement pe = pes[0];		
		final Object bo = bor.getBusinessObjectForPictogramElement(pe);
		final Object containerBo = bor.getBusinessObjectForPictogramElement(((Shape)pe).getContainer());
		
		if(!(bo instanceof Feature && containerBo instanceof Classifier)) {
			return false;
		}
		
		final Feature feature = (Feature)bo;	
		return feature.getContainingClassifier() == containerBo && 
				FeaturePattern.canContainFeatureType(feature.getContainingClassifier(), featureType) &&
				(feature.getRefined() == null || feature.getRefined() instanceof AbstractFeature);
	}   	
    
	@Override
	public boolean canUndo(final IContext context) {
		return false;
	}
    
    @Override
    public boolean canExecute(final ICustomContext context) {
    	// Only allow when the feature is owned by the container
    	final PictogramElement pe = context.getPictogramElements()[0];
    	final Feature feature = (Feature)bor.getBusinessObjectForPictogramElement(((Shape)pe));
		
		// Check that the feature is not already of the target type
    	return feature.eClass() != featureType;
    }
    
	@Override
	public void execute(final ICustomContext context) {
		final PictogramElement pe = context.getPictogramElements()[0];		
		final Feature feature = (Feature)bor.getBusinessObjectForPictogramElement(pe);
		aadlModService.modify(feature, new AbstractModifier<Feature, Object>() {
			@Override
			public Object modify(final Resource resource, final Feature featurec) {
				final Classifier featureOwner = feature.getContainingClassifier();
				final Feature replacementFeature = FeaturePattern.createFeature(featureOwner, featureType);
				
				// Copy structural feature values to the replacement object.
				transferStructuralFeatureValues(feature, replacementFeature);
				
				// Remove the old object
				EcoreUtil.remove(feature);
				
				// TODO: Does it keep refineness?

				return null;
			}			
		});
	}
	
	/**
	 * Copies structural feature values from original to replacement. If replacement does not contain a matching structural feature, the value is ignored. If a feature is not set,
	 * its value is not copied over to the replacement.
	 * @param original
	 * @param replacement
	 */
	private void transferStructuralFeatureValues(final EObject original, final EObject replacement) {
		for(final EStructuralFeature feature : original.eClass().getEAllStructuralFeatures())	{
			if(feature.isChangeable() && !feature.isDerived()) {
				final Object originalValue = original.eGet(feature, true);						

				// Only copy values that are set
				if(original.eIsSet(feature)) {
					if(replacement.eClass().getEAllStructuralFeatures().contains(feature)) {
						if(feature.isMany()) {
							final @SuppressWarnings("unchecked") List<Object> originalList = (List<Object>)originalValue;
							final Object replacementValue = replacement.eGet(feature);
							final @SuppressWarnings("unchecked") List<Object> replacementList = (List<Object>)replacementValue;
							replacementList.addAll(originalList);					
						} else {
							replacement.eSet(feature, originalValue);
						}
					}
				}
			}
		}
	}	
}
