/**
 * Copyright (c) 2004-2020 Carnegie Mellon University and others. (see Contributors file).
 * All Rights Reserved.
 *
 * NO WARRANTY. ALL MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE
 * OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT
 * MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 *
 * Created, in part, with funding and support from the United States Government. (see Acknowledgments file).
 *
 * This program includes and/or can make use of certain third party source code, object code, documentation and other
 * files ("Third Party Software"). The Third Party Software that is used by this program is dependent upon your system
 * configuration. By using this program, You agree to comply with any and all relevant Third Party Software terms and
 * conditions contained in any such Third Party Software or separate license file distributed with such Third Party
 * Software. The parties who own the Third Party Software ("Third Party Licensors") are intended third party benefici-
 * aries to this license with respect to the terms applicable to their Third Party Software. Third Party Software li-
 * censes only apply to the Third Party Software and not any other portion of this program or this program as a whole.
 */
package org.osate.xtext.aadl2.errormodel.instantiation;

import static org.osate.xtext.aadl2.errormodel.util.EMV2TypeSetUtil.isNoError;
import static org.osate.xtext.aadl2.errormodel.util.EMV2TypeSetUtil.mapTokenThroughConstraint;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.Feature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.AnnexInstance;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.annexsupport.AnnexInstantiator;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.BehaviorInstance;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.CompositeStateInstance;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.ConstrainedInstanceObject;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.Constraint;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.ConstraintElement;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.EMV2AnnexInstance;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.EMV2InstanceFactory;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.EMV2InstanceObject;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.EOperation;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.EventInstance;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.StateInstance;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.StateMachineInstance;
import org.osate.xtext.aadl2.errormodel.EMV2Instance.StateTransitionInstance;
import org.osate.xtext.aadl2.errormodel.errorModel.AllExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.AndExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.CompositeState;
import org.osate.xtext.aadl2.errormodel.errorModel.ConditionElement;
import org.osate.xtext.aadl2.errormodel.errorModel.ConditionExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.EMV2Path;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorEvent;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorStateMachine;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorEvent;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPropagation;
import org.osate.xtext.aadl2.errormodel.errorModel.FeatureorPPReference;
import org.osate.xtext.aadl2.errormodel.errorModel.OrExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.OrmoreExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.OutgoingPropagationCondition;
import org.osate.xtext.aadl2.errormodel.errorModel.QualifiedErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.SConditionElement;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EMV2TypeSetUtil;
import org.osate.xtext.aadl2.errormodel.util.EMV2Util;

public class EMV2AnnexInstantiator implements AnnexInstantiator {
	@Override
	public void instantiateAnnex(ComponentInstance instance, String annexName) {
		EMV2AnnexInstance emv2AI = EMV2InstanceFactory.eINSTANCE.createEMV2AnnexInstance();
		instance.getAnnexInstances().add(emv2AI);

		Collection<ErrorBehaviorEvent> events = EMV2Util.getAllErrorBehaviorEvents(instance);
		for (ErrorBehaviorEvent ev : events) {
			instantiateEvent(ev, emv2AI);
		}

		ErrorBehaviorStateMachine ebsm = EMV2Util.getAllErrorBehaviorStateMachine(instance);
		instantiateStateMachine(ebsm, emv2AI);

		Collection<ErrorBehaviorTransition> transitions = EMV2Util.getAllErrorBehaviorTransitions(instance);
		for (ErrorBehaviorTransition tr : transitions) {
			instantiateStateTransition(tr, emv2AI);
		}

		Collection<CompositeState> compstates = EMV2Util.getAllCompositeStates(instance);
		for (CompositeState cs : compstates) {
			instantiateCompositeState(cs, emv2AI);
		}

		Collection<OutgoingPropagationCondition> OPCs = EMV2Util.getAllOutgoingPropagationConditions(instance);
		for (OutgoingPropagationCondition opc : OPCs) {
			instantiateOutgoingPropagationCondition(opc, emv2AI);
		}
	}


	public void instantiateEvent(ErrorBehaviorEvent g, EMV2AnnexInstance context) {
		EventInstance gi = createEventInstance(g);
		context.getEvents().add(gi);
		if (g instanceof ErrorEvent) {
			 TypeSet ts = ((ErrorEvent)g).getTypeSet();
			if (ts != null) {
				for (TypeToken tt : ts.getTypeTokens()) {
					gi.getGeneratedTypedEvents().add(createConstrainedInstanceObject(gi, tt));
				}
			}
		}
	}

	public EventInstance createEventInstance(ErrorBehaviorEvent g) {
		EventInstance gi = EMV2InstanceFactory.eINSTANCE.createEventInstance();
		gi.setName(g.getName());
		gi.setEvent(g);
		return gi;
	}

	public ConstrainedInstanceObject createConstrainedInstanceObject(EventInstance context, TypeToken token) {
		ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
		cio.setInstanceObject(context);
		if (!token.getType().isEmpty()) {
			cio.setName(context.getName() + ":" + token.toString());
			cio.getConstraint().add(EcoreUtil.copy(token));
		} else {
			cio.setName(context.getName());
		}
		return cio;
	}

	public ConstrainedInstanceObject createConstrainedInstanceObject(InstanceObject io, boolean outgoing) {
		ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
		cio.setInstanceObject(io);
		cio.setName(cio.getInstanceObject().toString());
		cio.setOutgoing(outgoing);
		return cio;
	}

	public CompositeStateInstance createCompositeStateInstance(CompositeState st) {
		CompositeStateInstance sti = EMV2InstanceFactory.eINSTANCE.createCompositeStateInstance();
		sti.setName(st.getName());
		sti.setCompositeState(st);
		return sti;
	}

	public void instantiateStateMachine(ErrorBehaviorStateMachine ebsm, EMV2AnnexInstance context){
		StateMachineInstance svi = EMV2InstanceFactory.eINSTANCE.createStateMachineInstance();
		context.setStateMachine(svi);
		StateInstance initState = null;
		for (ErrorBehaviorState st : ebsm.getStates()) {
			StateInstance istate = createStateInstance(st);
			if (st.isIntial()) {
				initState = istate;
			}
			svi.getStates().add(istate);
		}
		svi.setCurrentState(initState);
	}

	public StateInstance createStateInstance(ErrorBehaviorState ss) {
		StateInstance si = EMV2InstanceFactory.eINSTANCE.createStateInstance();
		si.setName(ss.getName());
		return si;
	}

	public void instantiateStateTransition(ErrorBehaviorTransition st, EMV2AnnexInstance context) {
		StateMachineInstance smi = context.getStateMachine();
		if (smi == null) {
			return ;
		}
		StateTransitionInstance sti = EMV2InstanceFactory.eINSTANCE.createStateTransitionInstance();
		sti.setName(st.getName());
		sti.setStateTransition(st);
		context.getTransitions().add(sti);
		ConditionExpression behaviorCondition = st.getCondition();
		ConstraintElement cio = instantiateCondition(context, behaviorCondition, false);
		sti.setCondition(cio);
		if (st.isSteadyState()) {
			if (st.isAllStates()) {
				context.getTransitions().remove(sti);
				for (StateInstance si : smi.getStates()) {
					StateTransitionInstance nsti = EcoreUtil.copy(sti);
					nsti.getInStates().add(si);
					nsti.setTargetState(si);
				}
			} else {
				StateInstance ssti = findStateInstance(context, st.getSource());
				sti.getInStates().add(ssti);
				sti.setTargetState(ssti);
			}
		} else {
			// explicit target state
			sti.setTargetState(findStateInstance(context, st.getTarget()));
			if (st.isAllStates()) {
				for (StateInstance si : smi.getStates()) {
					sti.getInStates().add(si);
				}
			} else {
				sti.getInStates().add(findStateInstance(context, st.getSource()));
			}
		}
	}

	public void instantiateCompositeState(CompositeState st, EMV2AnnexInstance context) {
		CompositeStateInstance sti = EMV2InstanceFactory.eINSTANCE.createCompositeStateInstance();
		sti.setName(st.getName());
		sti.setCompositeState(st);
		context.getComposites().add(sti);
		ConditionExpression behaviorCondition = st.getCondition();
		ConstraintElement cio = instantiateCondition(context, behaviorCondition, false);
		sti.setCondition(cio);
		// explicit target state
		sti.setTargetState(findStateInstance(context, st.getState()));
	}

	public void instantiateOutgoingPropagationCondition(OutgoingPropagationCondition opc, EMV2AnnexInstance context) {
		BehaviorInstance bi = EMV2InstanceFactory.eINSTANCE.createBehaviorInstance();
		bi.setName(opc.getName());
		bi.setEmv2Element(opc);
		context.getBehaviors().add(bi);
		ConditionExpression behaviorCondition = opc.getCondition();
		ConstraintElement cio = instantiateCondition(context, behaviorCondition, false);
		bi.setCondition(cio);
		// explicit target state
		if (opc.isAllStates()) {
			StateMachineInstance smi = context.getStateMachine();
			if (smi != null) {
				for (StateInstance si : smi.getStates()) {
					bi.getInStates().add(si);
				}
			}
		} else {
			bi.getInStates().add(findStateInstance(context, opc.getState()));
		}
		// action. We keep shared action instances such that there is only one per type
		if (opc.isAllPropagations()) {
		} else {
			ErrorPropagation outep = opc.getOutgoing();
			FeatureorPPReference fppref = outep.getFeatureorPPRef();
			if (fppref != null) {
				NamedElement fpp = fppref.getFeatureorPP();
				ComponentInstance component = (ComponentInstance) context.eContainer();
				if (fpp instanceof Feature) {
					FeatureInstance fi = component.findFeatureInstance((Feature) fpp);
					TypeToken outtoken = opc.getTypeToken() != null
							? opc.getTypeToken().getTypeTokens().get(0)
							: null;
					ConstrainedInstanceObject foundcio = findMatchingActionCIO(context,fi,outtoken);
					if (foundcio != null){
						context.getActions().add(foundcio);
					} else {
						ConstrainedInstanceObject tcio = createConstrainedInstanceObject(fi,true);
						if (outtoken != null) {
							tcio.getConstraint().add(EcoreUtil.copy(outtoken));
						}
						context.getActions().add(tcio);
						bi.getActions().add(tcio);
					}
				}
			}
		}
	}

	private ConstrainedInstanceObject findMatchingActionCIO(EMV2AnnexInstance context, InstanceObject io,
			TypeToken tt) {
		for (ConstrainedInstanceObject action : context.getActions()) {
			if (action.getInstanceObject() == io) {
				if (action.getConstraint().contains(tt)) {
					return action;
				}
			}
		}
		return null;
	}

	private StateInstance findStateInstance(EMV2AnnexInstance context, ErrorBehaviorState state) {
		StateMachineInstance svi = context.getStateMachine();
		if (svi != null) {
			return findStateInstance(svi,state);
		}
		return null;
	}

	private StateInstance findStateInstance(StateMachineInstance svi, ErrorBehaviorState state) {
		for (StateInstance si : svi.getStates()) {
			if (si.getName().equals(state.getName())) {
				return si;
			}
		}
		return null;
	}

	public ConstraintElement instantiateCondition(EMV2AnnexInstance annex, ConditionExpression condition,
			boolean stateOnly) {

		// Mapping of AND expression
		if (condition instanceof AndExpression) {
			AndExpression expression = (AndExpression) condition;
			Constraint andExpr = EMV2InstanceFactory.eINSTANCE.createConstraint();
			andExpr.setOperator(EOperation.ALL);
			for (ConditionExpression ce : expression.getOperands()) {
				ConstraintElement res = instantiateCondition(annex, ce, stateOnly);
				if (res != null) {
					andExpr.getConstraintElements().add(res);
				}
			}

			return andExpr;
		}

		// Mapping of All expression
		if (condition instanceof AllExpression) {
			AllExpression allCondition = (AllExpression) condition;
			if (allCondition.getCount() == 0) {
				Constraint allExpr = EMV2InstanceFactory.eINSTANCE.createConstraint();
				allExpr.setOperator(EOperation.ALL);
				for (ConditionExpression ce : allCondition.getOperands()) {
					ConstraintElement res = instantiateCondition(annex, ce, stateOnly);
					if (res != null) {
						allExpr.getConstraintElements().add(res);
					}
				}
				return allExpr;
			}
			return null;
		}

		// Mapping of OR expression
		if (condition instanceof OrExpression) {
			OrExpression orExpression = (OrExpression) condition;
			Constraint allExpr = EMV2InstanceFactory.eINSTANCE.createConstraint();
			allExpr.setOperator(EOperation.ONEOF);
			for (ConditionExpression ce : orExpression.getOperands()) {
				ConstraintElement res = instantiateCondition(annex, ce, stateOnly);
				if (res != null) {
					allExpr.getConstraintElements().add(res);
				}
			}
			return allExpr;
		}

		// Mapping of ORMORE expression
		if (condition instanceof OrmoreExpression) {
			OrmoreExpression omCondition = (OrmoreExpression) condition;

			if (omCondition.getCount() == 1) {
				/* 1 ormore is mapped to a OR gate */
				Constraint allExpr = EMV2InstanceFactory.eINSTANCE.createConstraint();
				allExpr.setOperator(EOperation.ANY);
				for (ConditionExpression ce : omCondition.getOperands()) {
					ConstraintElement res = instantiateCondition(annex, ce, stateOnly);
					if (res != null) {
						allExpr.getConstraintElements().add(res);
					}
				}

			} else {
				/* x ormore with x > 1 is mapped to a ORMORE gate */
				Constraint omExpr = EMV2InstanceFactory.eINSTANCE.createConstraint();
				omExpr.setOperator(EOperation.KORMORE);
				omExpr.setK(omCondition.getCount());
				for (ConditionExpression ce : omCondition.getOperands()) {
					ConstraintElement res = instantiateCondition(annex, ce, stateOnly);
					if (res != null) {
						omExpr.getConstraintElements().add(res);
					}
				}
			}
		}

		// Mapping of single condition element
		if (condition instanceof ConditionElement) {
			ConditionElement conditionElement = (ConditionElement) condition;

			if (condition instanceof SConditionElement) {
				SConditionElement sconditionElement = (SConditionElement) condition;
				if (sconditionElement.getQualifiedState() != null) {
					/**
					 * In the following, it seems that we reference another
					 * component. This is typically the case when the condition is
					 * within an composite error behavior.
					 *
					 * So, we find the referenced component in the component
					 * hierarchy and add all its contributors to the returned
					 * events.
					 */
					QualifiedErrorBehaviorState qs = sconditionElement.getQualifiedState();
					ComponentInstance component = (ComponentInstance) annex.eContainer();
					ComponentInstance referencedComponent = EMV2Util.getLastComponentInstance(qs, component);
					ErrorBehaviorState state = EMV2Util.getState(sconditionElement);
					// either original type or mapped to constraint in condition or type set on state declaration
					TypeSet referencedErrorType = (sconditionElement.getConstraint() != null)
							? sconditionElement.getConstraint()
							: state.getTypeSet();
					EMV2AnnexInstance eai = findEMV2AnnexInstance(referencedComponent);
					StateInstance si = findStateInstance(eai, state);
					// state only
					ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
					cio.setInstanceObject(si);
					cio.setName(si.getName());
					if (referencedErrorType != null) {
						// handle type set on states
						// get incoming type from propagation
						EList<TypeToken> leaftypes = EMV2TypeSetUtil.flattenTypesetElements(referencedErrorType);
						cio.getConstraint().addAll(leaftypes);
					}
					return cio;
				} else if (sconditionElement.getQualifiedErrorPropagationReference() != null) {
					EMV2Path path = sconditionElement.getQualifiedErrorPropagationReference();
					ComponentInstance component = (ComponentInstance) annex.eContainer();
					ComponentInstance referencedComponent = EMV2Util.getLastComponentInstance(path, component);
					ErrorPropagation ep = EMV2Util.getErrorPropagation(path);
					// either original type or mapped to constraint in condition or type set on state declaration
					TypeSet referencedErrorType = (sconditionElement.getConstraint() != null)
							? sconditionElement.getConstraint()
							: ep.getTypeSet();
					FeatureorPPReference fppref = ep.getFeatureorPPRef();
					if (fppref != null) {
						NamedElement fpp = fppref.getFeatureorPP();
						if (fpp instanceof Feature) {
							FeatureInstance fi = referencedComponent.findFeatureInstance((Feature) fpp);
							ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE
									.createConstrainedInstanceObject();
							cio.setInstanceObject(fi);
							cio.setName(fi.getName());
							// get incoming type from propagation
							EList<TypeToken> leaftypes = EMV2TypeSetUtil.flattenTypesetElements(referencedErrorType);
							cio.getConstraint().addAll(leaftypes);
						}
					} else {
						return null;
					}
				}
			} // end SConditionElement

			if (conditionElement.getConstraint() != null) {
				if (isNoError(conditionElement.getConstraint())) {
					// this is a recovery transition since an incoming propagation constraint is NoError
					return null;
				}
			}
			if (conditionElement.getQualifiedErrorPropagationReference() != null) {
				ConstrainedInstanceObject cio = EMV2InstanceFactory.eINSTANCE.createConstrainedInstanceObject();
				EMV2Path path = conditionElement.getQualifiedErrorPropagationReference();

				ComponentInstance relatedComponent = (ComponentInstance) annex.eContainer();
				NamedElement errorModelElement = EMV2Util.getErrorModelElement(path);

				/**
				 * Here, we have an error event. Likely, this is something we
				 * can get when we are analyzing error component behavior.
				 */
				if (errorModelElement instanceof ErrorEvent) {
					EventInstance evi = findEventInstance(annex, (ErrorEvent) errorModelElement);
					cio.setInstanceObject(evi);
					cio.setName(evi.getName());
					Collection<TypeToken> referencedErrorTypes = conditionElement.getConstraint() != null
							? mapTokenThroughConstraint(conditionElement.getConstraint(), null)
							: mapTokenThroughConstraint(((ErrorEvent) errorModelElement).getTypeSet(), null);
					cio.getConstraint().addAll(referencedErrorTypes);
					return cio;
				}

				/**
				 * Here, we have an error propagation. This is notified with the
				 * in propagation within a composite error model.
				 */
				if (errorModelElement instanceof ErrorPropagation) {
					ErrorPropagation errorPropagation = (ErrorPropagation) errorModelElement;
					FeatureorPPReference fppref = errorPropagation.getFeatureorPPRef();
					if (fppref != null) {
						NamedElement fpp = fppref.getFeatureorPP();
						if (fpp instanceof Feature) {
							FeatureInstance fi = relatedComponent.findFeatureInstance((Feature) errorModelElement);
							cio.setInstanceObject(fi);
							cio.setName(fi.getName());
						}
					}
					Collection<TypeToken> referencedErrorTypes = conditionElement.getConstraint() != null
							? EMV2TypeSetUtil.flattenTypesetElements(conditionElement.getConstraint())
							: EMV2TypeSetUtil.flattenTypesetElements(errorPropagation.getTypeSet());
					cio.getConstraint().addAll(referencedErrorTypes);
					return cio;
				}

			}
		}
		return null;
	}


	private EventInstance findEventInstance(EMV2AnnexInstance eai, ErrorBehaviorEvent ev) {
		for (EMV2InstanceObject ei : eai.getEvents()) {
			if (ei instanceof EventInstance && ei.getName().equals(ev.getName())) {
				return (EventInstance) ei;
			}
		}
		return null;
	}

	private EMV2AnnexInstance findEMV2AnnexInstance(ComponentInstance ci) {
		for (AnnexInstance ai : ci.getAnnexInstances()) {
			if (ai instanceof EMV2AnnexInstance) {
				return (EMV2AnnexInstance) ai;
			}
		}
		return null;
	}

}
