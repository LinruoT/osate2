/**
 */
package org.osate.xtext.aadl2.errormodel.errorModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.impl.NamedElementImpl;

import org.osate.xtext.aadl2.errormodel.errorModel.ConditionExpression;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelPackage;
import org.osate.xtext.aadl2.errormodel.errorModel.TransitionBranch;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Error Behavior Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#getTypeTokenConstraint <em>Type Token Constraint</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#isAll <em>All</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#getTargetToken <em>Target Token</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#isMask <em>Mask</em>}</li>
 *   <li>{@link org.osate.xtext.aadl2.errormodel.errorModel.impl.ErrorBehaviorTransitionImpl#getDestinationBranches <em>Destination Branches</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ErrorBehaviorTransitionImpl extends NamedElementImpl implements ErrorBehaviorTransition
{
  /**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
  protected ErrorBehaviorState source;

  /**
	 * The cached value of the '{@link #getTypeTokenConstraint() <em>Type Token Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getTypeTokenConstraint()
	 * @generated
	 * @ordered
	 */
  protected TypeSet typeTokenConstraint;

  /**
	 * The default value of the '{@link #isAll() <em>All</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isAll()
	 * @generated
	 * @ordered
	 */
  protected static final boolean ALL_EDEFAULT = false;

  /**
	 * The cached value of the '{@link #isAll() <em>All</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isAll()
	 * @generated
	 * @ordered
	 */
  protected boolean all = ALL_EDEFAULT;

  /**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
  protected ConditionExpression condition;

  /**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
  protected ErrorBehaviorState target;

  /**
	 * The cached value of the '{@link #getTargetToken() <em>Target Token</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getTargetToken()
	 * @generated
	 * @ordered
	 */
  protected TypeToken targetToken;

  /**
	 * The default value of the '{@link #isMask() <em>Mask</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isMask()
	 * @generated
	 * @ordered
	 */
  protected static final boolean MASK_EDEFAULT = false;

  /**
	 * The cached value of the '{@link #isMask() <em>Mask</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isMask()
	 * @generated
	 * @ordered
	 */
  protected boolean mask = MASK_EDEFAULT;

  /**
	 * The cached value of the '{@link #getDestinationBranches() <em>Destination Branches</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getDestinationBranches()
	 * @generated
	 * @ordered
	 */
  protected EList<TransitionBranch> destinationBranches;

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ErrorBehaviorTransitionImpl()
  {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass()
  {
		return ErrorModelPackage.Literals.ERROR_BEHAVIOR_TRANSITION;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ErrorBehaviorState getSource()
  {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (ErrorBehaviorState)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ErrorBehaviorState basicGetSource()
  {
		return source;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setSource(ErrorBehaviorState newSource)
  {
		ErrorBehaviorState oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__SOURCE, oldSource, source));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public TypeSet getTypeTokenConstraint()
  {
		return typeTokenConstraint;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetTypeTokenConstraint(TypeSet newTypeTokenConstraint, NotificationChain msgs)
  {
		TypeSet oldTypeTokenConstraint = typeTokenConstraint;
		typeTokenConstraint = newTypeTokenConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT, oldTypeTokenConstraint, newTypeTokenConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTypeTokenConstraint(TypeSet newTypeTokenConstraint)
  {
		if (newTypeTokenConstraint != typeTokenConstraint) {
			NotificationChain msgs = null;
			if (typeTokenConstraint != null)
				msgs = ((InternalEObject)typeTokenConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT, null, msgs);
			if (newTypeTokenConstraint != null)
				msgs = ((InternalEObject)newTypeTokenConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT, null, msgs);
			msgs = basicSetTypeTokenConstraint(newTypeTokenConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT, newTypeTokenConstraint, newTypeTokenConstraint));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isAll()
  {
		return all;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setAll(boolean newAll)
  {
		boolean oldAll = all;
		all = newAll;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__ALL, oldAll, all));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ConditionExpression getCondition()
  {
		return condition;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetCondition(ConditionExpression newCondition, NotificationChain msgs)
  {
		ConditionExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setCondition(ConditionExpression newCondition)
  {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION, newCondition, newCondition));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ErrorBehaviorState getTarget()
  {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (ErrorBehaviorState)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ErrorBehaviorState basicGetTarget()
  {
		return target;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTarget(ErrorBehaviorState newTarget)
  {
		ErrorBehaviorState oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET, oldTarget, target));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public TypeToken getTargetToken()
  {
		return targetToken;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetTargetToken(TypeToken newTargetToken, NotificationChain msgs)
  {
		TypeToken oldTargetToken = targetToken;
		targetToken = newTargetToken;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN, oldTargetToken, newTargetToken);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTargetToken(TypeToken newTargetToken)
  {
		if (newTargetToken != targetToken) {
			NotificationChain msgs = null;
			if (targetToken != null)
				msgs = ((InternalEObject)targetToken).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN, null, msgs);
			if (newTargetToken != null)
				msgs = ((InternalEObject)newTargetToken).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN, null, msgs);
			msgs = basicSetTargetToken(newTargetToken, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN, newTargetToken, newTargetToken));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isMask()
  {
		return mask;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setMask(boolean newMask)
  {
		boolean oldMask = mask;
		mask = newMask;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__MASK, oldMask, mask));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList<TransitionBranch> getDestinationBranches()
  {
		if (destinationBranches == null) {
			destinationBranches = new EObjectContainmentEList<TransitionBranch>(TransitionBranch.class, this, ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__DESTINATION_BRANCHES);
		}
		return destinationBranches;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
		switch (featureID) {
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT:
				return basicSetTypeTokenConstraint(null, msgs);
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION:
				return basicSetCondition(null, msgs);
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN:
				return basicSetTargetToken(null, msgs);
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__DESTINATION_BRANCHES:
				return ((InternalEList<?>)getDestinationBranches()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
		switch (featureID) {
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT:
				return getTypeTokenConstraint();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__ALL:
				return isAll();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION:
				return getCondition();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN:
				return getTargetToken();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__MASK:
				return isMask();
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__DESTINATION_BRANCHES:
				return getDestinationBranches();
		}
		return super.eGet(featureID, resolve, coreType);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
		switch (featureID) {
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__SOURCE:
				setSource((ErrorBehaviorState)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT:
				setTypeTokenConstraint((TypeSet)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__ALL:
				setAll((Boolean)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION:
				setCondition((ConditionExpression)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET:
				setTarget((ErrorBehaviorState)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN:
				setTargetToken((TypeToken)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__MASK:
				setMask((Boolean)newValue);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__DESTINATION_BRANCHES:
				getDestinationBranches().clear();
				getDestinationBranches().addAll((Collection<? extends TransitionBranch>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void eUnset(int featureID)
  {
		switch (featureID) {
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__SOURCE:
				setSource((ErrorBehaviorState)null);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT:
				setTypeTokenConstraint((TypeSet)null);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__ALL:
				setAll(ALL_EDEFAULT);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION:
				setCondition((ConditionExpression)null);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET:
				setTarget((ErrorBehaviorState)null);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN:
				setTargetToken((TypeToken)null);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__MASK:
				setMask(MASK_EDEFAULT);
				return;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__DESTINATION_BRANCHES:
				getDestinationBranches().clear();
				return;
		}
		super.eUnset(featureID);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public boolean eIsSet(int featureID)
  {
		switch (featureID) {
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__SOURCE:
				return source != null;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TYPE_TOKEN_CONSTRAINT:
				return typeTokenConstraint != null;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__ALL:
				return all != ALL_EDEFAULT;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__CONDITION:
				return condition != null;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET:
				return target != null;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__TARGET_TOKEN:
				return targetToken != null;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__MASK:
				return mask != MASK_EDEFAULT;
			case ErrorModelPackage.ERROR_BEHAVIOR_TRANSITION__DESTINATION_BRANCHES:
				return destinationBranches != null && !destinationBranches.isEmpty();
		}
		return super.eIsSet(featureID);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String toString()
  {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (all: ");
		result.append(all);
		result.append(", mask: ");
		result.append(mask);
		result.append(')');
		return result.toString();
	}

} //ErrorBehaviorTransitionImpl
