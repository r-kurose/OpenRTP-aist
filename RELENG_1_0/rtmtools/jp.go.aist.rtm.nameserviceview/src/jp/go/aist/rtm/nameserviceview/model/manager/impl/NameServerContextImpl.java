/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.manager.impl;

import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Server Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.manager.impl.NameServerContextImpl#getNameServerName <em>Name Server Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NameServerContextImpl extends NodeImpl implements NameServerContext {
	/**
	 * The default value of the '{@link #getNameServerName() <em>Name Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameServerName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_SERVER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNameServerName() <em>Name Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameServerName()
	 * @generated
	 * @ordered
	 */
	protected String nameServerName = NAME_SERVER_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NameServerContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ManagerPackage.Literals.NAME_SERVER_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNameServerName() {
		return nameServerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNameServerName(String newNameServerName) {
		String oldNameServerName = nameServerName;
		nameServerName = newNameServerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ManagerPackage.NAME_SERVER_CONTEXT__NAME_SERVER_NAME, oldNameServerName, nameServerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void synchronizeLocal() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void finalizeLocal() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ManagerPackage.NAME_SERVER_CONTEXT__NAME_SERVER_NAME:
				return getNameServerName();
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
			case ManagerPackage.NAME_SERVER_CONTEXT__NAME_SERVER_NAME:
				setNameServerName((String)newValue);
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
			case ManagerPackage.NAME_SERVER_CONTEXT__NAME_SERVER_NAME:
				setNameServerName(NAME_SERVER_NAME_EDEFAULT);
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
			case ManagerPackage.NAME_SERVER_CONTEXT__NAME_SERVER_NAME:
				return NAME_SERVER_NAME_EDEFAULT == null ? nameServerName != null : !NAME_SERVER_NAME_EDEFAULT.equals(nameServerName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (nameServerName: ");
		result.append(nameServerName);
		result.append(')');
		return result.toString();
	}

	@Override
	public List<RTCManager> getRTCManagerList() {
		return Collections.emptyList();
	}

} //NameServerContextImpl
