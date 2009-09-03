/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExecutionContextImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;

import jp.go.aist.rtm.toolscommon.ui.propertysource.ExecutionContextPropertySource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Execution Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getKindL <em>Kind L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getRateL <em>Rate L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ExecutionContextImpl#getStateL <em>State L</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionContextImpl extends WrapperObjectImpl implements
		ExecutionContext {
	/**
	 * The default value of the '{@link #getKindL() <em>Kind L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getKindL()
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_L_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getKindL() <em>Kind L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getKindL()
	 * @generated
	 * @ordered
	 */
	protected int kindL = KIND_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getRateL() <em>Rate L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRateL()
	 * @generated
	 * @ordered
	 */
	protected static final Double RATE_L_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRateL() <em>Rate L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRateL()
	 * @generated
	 * @ordered
	 */
	protected Double rateL = RATE_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getStateL() <em>State L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStateL()
	 * @generated
	 * @ordered
	 */
	protected static final int STATE_L_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStateL() <em>State L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getStateL()
	 * @generated
	 * @ordered
	 */
	protected int stateL = STATE_L_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ExecutionContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.EXECUTION_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getKindL() {
		return kindL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setKindL(int newKindL) {
		int oldKindL = kindL;
		kindL = newKindL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__KIND_L, oldKindL, kindL));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Double getRateL() {
		return rateL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRateL(Double newRateL) {
		Double oldRateL = rateL;
		rateL = newRateL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__RATE_L, oldRateL, rateL));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getStateL() {
		return stateL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStateL(int newStateL) {
		int oldStateL = stateL;
		stateL = newStateL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.EXECUTION_CONTEXT__STATE_L, oldStateL, stateL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getKindName() {
		if (kindL == KIND_PERIODIC) {
			return "PERIODIC";
		} else if (kindL == KIND_EVENT_DRIVEN) {
			return "EVENT_DRIVEN";
		} else if (kindL == KIND_OTHER) {
			return "OTHER";
		} else {
			return "UNKNOWN";
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getStateName() {
		if (stateL == STATE_STOPPED) {
			return "STOPPED";
		} else if (stateL == STATE_RUNNING) {
			return "RUNNING";
		} else {
			return "UNKNOWN";
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				return new Integer(getKindL());
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				return getRateL();
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				return new Integer(getStateL());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				setKindL(((Integer)newValue).intValue());
				return;
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				setRateL((Double)newValue);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				setStateL(((Integer)newValue).intValue());
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				setKindL(KIND_L_EDEFAULT);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				setRateL(RATE_L_EDEFAULT);
				return;
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				setStateL(STATE_L_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.EXECUTION_CONTEXT__KIND_L:
				return kindL != KIND_L_EDEFAULT;
			case ComponentPackage.EXECUTION_CONTEXT__RATE_L:
				return RATE_L_EDEFAULT == null ? rateL != null : !RATE_L_EDEFAULT.equals(rateL);
			case ComponentPackage.EXECUTION_CONTEXT__STATE_L:
				return stateL != STATE_L_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (kindL: ");
		result.append(kindL);
		result.append(", rateL: ");
		result.append(rateL);
		result.append(", stateL: ");
		result.append(stateL);
		result.append(')');
		return result.toString();
	}

	@Override
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ExecutionContextPropertySource(this);
		}
		if (result == null) {
			result = super.getAdapter(adapter);
		}
		return result;
	}

} // ExecutionContextImpl
