package org.osate.ge.errormodel.pictogramHandlers;

import javax.inject.Named;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.osate.aadl2.AadlPackage;
import org.osate.ge.errormodel.ErrorModelCategories;
import org.osate.ge.errormodel.util.ErrorModelBusinessObjectHelper;
import org.osate.ge.errormodel.util.ErrorModelNamingHelper;
import org.osate.ge.ext.ExtensionPaletteEntry;
import org.osate.ge.ext.ExtensionPaletteEntry.Type;
import org.osate.ge.ext.Names;
import org.osate.ge.ext.SimplePaletteEntry;
import org.osate.ge.ext.annotations.CanCreate;
import org.osate.ge.ext.annotations.CreateBusinessObject;
import org.osate.ge.ext.annotations.GetCreateOwningBusinessObject;
import org.osate.ge.ext.annotations.GetPaletteEntries;
import org.osate.ge.ext.services.PictogramElementService;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelLibrary;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelPackage;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorType;

public class ErrorTypePictogramHandler {
	@GetPaletteEntries
	public ExtensionPaletteEntry[] getPaletteEntries(final PictogramElementService pes, final IDiagramTypeProvider dtp) {
		if(!(pes.getBusinessObject(dtp.getDiagram()) instanceof AadlPackage)) {
			return null;
		}
		
		return new ExtensionPaletteEntry[] { 
			new SimplePaletteEntry(ErrorModelCategories.ERROR_MODEL, Type.CREATE, "Error Type", null, null)
		};
	}
	
	@CanCreate
	public boolean canCreateShape(final @Named(Names.CONTAINER) ContainerShape container, final PictogramElementService pes) {
		return pes.getBusinessObject(container) instanceof AadlPackage && container instanceof Diagram;
	}

	@GetCreateOwningBusinessObject
	public Object getOwnerBusinessObject(final @Named(Names.CONTAINER) ContainerShape container, final PictogramElementService pes) {
		return ErrorModelBusinessObjectHelper.getOwnerBusinessObjectForErrorModelLibraryElement(container, pes);
	}
	
	@CreateBusinessObject
	public Object createBusinessObject(@Named(Names.OWNER_BO) Object ownerBo) {		
		final ErrorModelLibrary errorModelLibrary = ErrorModelBusinessObjectHelper.getOrCreateErrorModelLibrary(ownerBo);
		
		// Create the ErrorType
		final ErrorType newErrorType = (ErrorType)EcoreUtil.create(ErrorModelPackage.eINSTANCE.getErrorType());
		final String newErrorTypeName = ErrorModelNamingHelper.buildUniqueIdentifier(errorModelLibrary, "NewErrorType");
		newErrorType.setName(newErrorTypeName);
		
		// Add the new type to the error model library
		errorModelLibrary.getTypes().add(newErrorType);
		
		return newErrorType;
	}
}
