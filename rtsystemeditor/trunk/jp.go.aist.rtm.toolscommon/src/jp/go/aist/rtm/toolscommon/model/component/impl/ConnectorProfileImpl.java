/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConnectorProfileImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Connector Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getDataflowType <em>Dataflow Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getSubscriptionType <em>Subscription Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isSubscriptionTypeAvailable <em>Subscription Type Available</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#isPushIntervalAvailable <em>Push Interval Available</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getName <em>Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getConnectorId <em>Connector Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getInterfaceType <em>Interface Type</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getPushRate <em>Push Rate</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getSourceString <em>Source String</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl#getTargetString <em>Target String</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorProfileImpl extends WrapperObjectImpl implements
		ConnectorProfile {
	/**
	 * The default value of the '{@link #getDataflowType() <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDataflowType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATAFLOW_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataflowType() <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataflowType()
	 * @generated
	 * @ordered
	 */
	protected String dataflowType = DATAFLOW_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubscriptionType() <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSubscriptionType()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBSCRIPTION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubscriptionType() <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubscriptionType()
	 * @generated
	 * @ordered
	 */
	protected String subscriptionType = SUBSCRIPTION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSubscriptionTypeAvailable() <em>Subscription Type Available</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSubscriptionTypeAvailable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUBSCRIPTION_TYPE_AVAILABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isPushIntervalAvailable() <em>Push Interval Available</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isPushIntervalAvailable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUSH_INTERVAL_AVAILABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorId() <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConnectorId()
	 * @generated
	 * @ordered
	 */
	protected String connectorId = CONNECTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected String dataType = DATA_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInterfaceType() <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInterfaceType()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERFACE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterfaceType() <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceType()
	 * @generated
	 * @ordered
	 */
	protected String interfaceType = INTERFACE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPushRate() <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPushRate()
	 * @generated
	 * @ordered
	 */
	protected static final Double PUSH_RATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPushRate() <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPushRate()
	 * @generated
	 * @ordered
	 */
	protected Double pushRate = PUSH_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceString() <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceString()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceString() <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceString()
	 * @generated
	 * @ordered
	 */
	protected String sourceString = SOURCE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetString() <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetString()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetString() <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetString()
	 * @generated
	 * @ordered
	 */
	protected String targetString = TARGET_STRING_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ConnectorProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CONNECTOR_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataflowType() {
		return dataflowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataflowType(String newDataflowType) {
		String oldDataflowType = dataflowType;
		dataflowType = newDataflowType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE, oldDataflowType, dataflowType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubscriptionType() {
		return subscriptionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubscriptionType(String newSubscriptionType) {
		String oldSubscriptionType = subscriptionType;
		subscriptionType = newSubscriptionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE, oldSubscriptionType, subscriptionType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isSubscriptionTypeAvailable() {
		return ConnectorProfile.PUSH.equalsIgnoreCase(getDataflowType());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isPushIntervalAvailable() {
		return isSubscriptionTypeAvailable() && ConnectorProfile.PERIODIC
				.equalsIgnoreCase(getSubscriptionType());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectorId() {
		return connectorId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorId(String newConnectorId) {
		String oldConnectorId = connectorId;
		connectorId = newConnectorId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID, oldConnectorId, connectorId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		String oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInterfaceType() {
		return interfaceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterfaceType(String newInterfaceType) {
		String oldInterfaceType = interfaceType;
		interfaceType = newInterfaceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE, oldInterfaceType, interfaceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getPushRate() {
		return pushRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPushRate(Double newPushRate) {
		Double oldPushRate = pushRate;
		pushRate = newPushRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE, oldPushRate, pushRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceString() {
		return sourceString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceString(String newSourceString) {
		String oldSourceString = sourceString;
		sourceString = newSourceString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING, oldSourceString, sourceString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetString() {
		return targetString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetString(String newTargetString) {
		String oldTargetString = targetString;
		targetString = newTargetString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING, oldTargetString, targetString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				return getDataflowType();
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				return getSubscriptionType();
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE:
				return isSubscriptionTypeAvailable() ? Boolean.TRUE : Boolean.FALSE;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE:
				return isPushIntervalAvailable() ? Boolean.TRUE : Boolean.FALSE;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				return getName();
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				return getConnectorId();
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				return getDataType();
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				return getInterfaceType();
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				return getPushRate();
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				return getSourceString();
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				return getTargetString();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				setDataflowType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				setSubscriptionType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				setName((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				setConnectorId((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				setDataType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				setInterfaceType((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				setPushRate((Double)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				setSourceString((String)newValue);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				setTargetString((String)newValue);
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
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				setDataflowType(DATAFLOW_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				setSubscriptionType(SUBSCRIPTION_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				setConnectorId(CONNECTOR_ID_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				setInterfaceType(INTERFACE_TYPE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				setPushRate(PUSH_RATE_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				setSourceString(SOURCE_STRING_EDEFAULT);
				return;
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				setTargetString(TARGET_STRING_EDEFAULT);
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
			case ComponentPackage.CONNECTOR_PROFILE__DATAFLOW_TYPE:
				return DATAFLOW_TYPE_EDEFAULT == null ? dataflowType != null : !DATAFLOW_TYPE_EDEFAULT.equals(dataflowType);
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE:
				return SUBSCRIPTION_TYPE_EDEFAULT == null ? subscriptionType != null : !SUBSCRIPTION_TYPE_EDEFAULT.equals(subscriptionType);
			case ComponentPackage.CONNECTOR_PROFILE__SUBSCRIPTION_TYPE_AVAILABLE:
				return isSubscriptionTypeAvailable() != SUBSCRIPTION_TYPE_AVAILABLE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_INTERVAL_AVAILABLE:
				return isPushIntervalAvailable() != PUSH_INTERVAL_AVAILABLE_EDEFAULT;
			case ComponentPackage.CONNECTOR_PROFILE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ComponentPackage.CONNECTOR_PROFILE__CONNECTOR_ID:
				return CONNECTOR_ID_EDEFAULT == null ? connectorId != null : !CONNECTOR_ID_EDEFAULT.equals(connectorId);
			case ComponentPackage.CONNECTOR_PROFILE__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
			case ComponentPackage.CONNECTOR_PROFILE__INTERFACE_TYPE:
				return INTERFACE_TYPE_EDEFAULT == null ? interfaceType != null : !INTERFACE_TYPE_EDEFAULT.equals(interfaceType);
			case ComponentPackage.CONNECTOR_PROFILE__PUSH_RATE:
				return PUSH_RATE_EDEFAULT == null ? pushRate != null : !PUSH_RATE_EDEFAULT.equals(pushRate);
			case ComponentPackage.CONNECTOR_PROFILE__SOURCE_STRING:
				return SOURCE_STRING_EDEFAULT == null ? sourceString != null : !SOURCE_STRING_EDEFAULT.equals(sourceString);
			case ComponentPackage.CONNECTOR_PROFILE__TARGET_STRING:
				return TARGET_STRING_EDEFAULT == null ? targetString != null : !TARGET_STRING_EDEFAULT.equals(targetString);
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
		result.append(" (dataflowType: ");
		result.append(dataflowType);
		result.append(", subscriptionType: ");
		result.append(subscriptionType);
		result.append(", name: ");
		result.append(name);
		result.append(", connectorId: ");
		result.append(connectorId);
		result.append(", dataType: ");
		result.append(dataType);
		result.append(", interfaceType: ");
		result.append(interfaceType);
		result.append(", pushRate: ");
		result.append(pushRate);
		result.append(", sourceString: ");
		result.append(sourceString);
		result.append(", targetString: ");
		result.append(targetString);
		result.append(')');
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConnectorProfile == false) {
			return false;
		}

		ConnectorProfile p = (ConnectorProfile) obj;

		return new EqualsBuilder().append(getConnectorId(), p.getConnectorId()).isEquals();
	}

} // ConnectorProfileImpl
