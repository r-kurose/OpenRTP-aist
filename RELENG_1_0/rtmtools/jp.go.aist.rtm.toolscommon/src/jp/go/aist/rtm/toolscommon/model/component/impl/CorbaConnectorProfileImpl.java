/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import RTC.ConnectorProfile;
import _SDOPackage.NameValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Connector Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl#getRtcConnectorProfile <em>Rtc Connector Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorbaConnectorProfileImpl extends ConnectorProfileImpl implements CorbaConnectorProfile {

	private static final String NAME_VALUE_KEY_DATAPORT_DATA_TYPE = "dataport.data_type";

	private static final String NAME_VALUE_KEY_DATAPORT_INTERFACE_TYPE = "dataport.interface_type";

	private static final String NAME_VALUE_KEY_DATAPORT_DATAFLOW_TYPE = "dataport.dataflow_type";

	private static final String NAME_VALUE_KEY_DATAPORT_SUBSCRIPTION_TYPE = "dataport.subscription_type";

	private static final String NAME_VALUE_KEY_PORT_PUSH_RATE = "dataport.push_rate";
	
	/**
	 * The default value of the '{@link #getRtcConnectorProfile() <em>Rtc Connector Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcConnectorProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ConnectorProfile RTC_CONNECTOR_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRtcConnectorProfile() <em>Rtc Connector Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcConnectorProfile()
	 * @generated
	 * @ordered
	 */
	protected ConnectorProfile rtcConnectorProfile = RTC_CONNECTOR_PROFILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaConnectorProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_CONNECTOR_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorProfile getRtcConnectorProfile() {
		return rtcConnectorProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRtcConnectorProfile(ConnectorProfile newRtcConnectorProfile) {
		ConnectorProfile oldRtcConnectorProfile = rtcConnectorProfile;
		rtcConnectorProfile = newRtcConnectorProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE, oldRtcConnectorProfile, rtcConnectorProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				return getRtcConnectorProfile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				setRtcConnectorProfile((ConnectorProfile)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				setRtcConnectorProfile(RTC_CONNECTOR_PROFILE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.CORBA_CONNECTOR_PROFILE__RTC_CONNECTOR_PROFILE:
				return RTC_CONNECTOR_PROFILE_EDEFAULT == null ? rtcConnectorProfile != null : !RTC_CONNECTOR_PROFILE_EDEFAULT.equals(rtcConnectorProfile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (rtcConnectorProfile: ");
		result.append(rtcConnectorProfile);
		result.append(')');
		return result.toString();
	}

	@Override
	public String getConnectorId() {
		return getRtcConnectorProfile() == null ? null
				: getRtcConnectorProfile().connector_id;
	}

	@Override
	public String getName() {
		return getRtcConnectorProfile() == null ? null
				: getRtcConnectorProfile().name;
	}

	@Override
	public void setConnectorId(String newConnectorId) {
		if (getRtcConnectorProfile() == null) setRtcConnectorProfile(new ConnectorProfile());
		getRtcConnectorProfile().connector_id = newConnectorId;
	}

	@Override
	public void setName(String newName) {
		if (getRtcConnectorProfile() == null) setRtcConnectorProfile(new ConnectorProfile());
		getRtcConnectorProfile().name = newName;
	}

	@Override
	public String getSourceString() {
		if (getRtcConnectorProfile() == null) return null;
		if (getRtcConnectorProfile().ports == null) return null;
		if (getRtcConnectorProfile().ports.length < 1) return null;
		if (getRtcConnectorProfile().ports[0] == null) return null;
		return getRtcConnectorProfile().ports[0].toString();
	}

	@Override
	public String getTargetString() {
		if (getRtcConnectorProfile() == null) return null;
		if (getRtcConnectorProfile().ports == null) return null;
		if (getRtcConnectorProfile().ports.length < 2) return null;
		if (getRtcConnectorProfile().ports[1] == null) return null;
		return getRtcConnectorProfile().ports[1].toString();
	}

	static String getDataflowTypes(NameValue[] properties) {
		return getPropertyValueAsStringValue(properties, NAME_VALUE_KEY_DATAPORT_DATAFLOW_TYPE);
	}
	static String getDataTypes(NameValue[] properties) {
		return getPropertyValueAsStringValue(properties, NAME_VALUE_KEY_DATAPORT_DATA_TYPE);
	}
	static String getInterfaceTypes(NameValue[] properties) {
		return getPropertyValueAsStringValue(properties, NAME_VALUE_KEY_DATAPORT_INTERFACE_TYPE);
	}
	static String getSubscriptionTypes(NameValue[] properties) {
		return getPropertyValueAsStringValue(properties, NAME_VALUE_KEY_DATAPORT_SUBSCRIPTION_TYPE);
	}
	
	static List<jp.go.aist.rtm.toolscommon.model.component.NameValue> getProperties(
			NameValue[] rtcProperties) {
		if (rtcProperties == null) Collections.emptyList();
		List<jp.go.aist.rtm.toolscommon.model.component.NameValue> result = 
			new ArrayList<jp.go.aist.rtm.toolscommon.model.component.NameValue>();
		for (NameValue property : rtcProperties) {
			String name = property.name;
			if (name.equals(NAME_VALUE_KEY_DATAPORT_DATAFLOW_TYPE)) continue;
			if (name.equals(NAME_VALUE_KEY_DATAPORT_DATA_TYPE)) continue;
			if (name.equals(NAME_VALUE_KEY_DATAPORT_INTERFACE_TYPE)) continue;
			if (name.equals(NAME_VALUE_KEY_DATAPORT_SUBSCRIPTION_TYPE)) continue;
			jp.go.aist.rtm.toolscommon.model.component.NameValue entry 
				= ComponentFactory.eINSTANCE.createNameValue();
			entry.setName(name);
			entry.setValue(SDOUtil.toAnyString(property.value));
			result.add(entry);
		}
		return result;
	}

	static String getPropertyValueAsStringValue(NameValue[] properties,
			String name) {
		if (properties == null) return null;
		for (NameValue nv : properties) {
			if (nv.name.equals(name)) return SDOUtil.toAnyString(nv.value);
		}
		return null;
	}

	private void setPropertyValueAsStringValue(NameValue[] properties,
			String name, String value) {
		if (properties == null) return;
		for (NameValue nv : properties) {
			if (nv.name.equals(name)){
				nv.value = SDOUtil.newAny(value);
				return;
			}
		}
		addRrcConnectotProfileProperty(name, value);
	}

	private void addRrcConnectotProfileProperty(String name, String value) {
		if (getRtcConnectorProfile() == null) return;
		
		NameValue[] properties = getNewProperties(getRtcConnectorProfile().properties);
		
		properties[properties.length-1] = SDOUtil.newNV(name, value);
		
		getRtcConnectorProfile().properties = properties;
	}
	
	public static NameValue[] createProperties(
			jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile connectorProfile) {
		List<NameValue> result = new ArrayList<NameValue>();
		
		addProperty(result, connectorProfile.getDataflowType(), NAME_VALUE_KEY_DATAPORT_DATAFLOW_TYPE);
		addProperty(result, connectorProfile.getSubscriptionType(), NAME_VALUE_KEY_DATAPORT_SUBSCRIPTION_TYPE);
		addProperty(result, connectorProfile.getDataType(), NAME_VALUE_KEY_DATAPORT_DATA_TYPE);
		addProperty(result, connectorProfile.getInterfaceType(), NAME_VALUE_KEY_DATAPORT_INTERFACE_TYPE);
		addProperty(result, connectorProfile.getPushRate(), NAME_VALUE_KEY_PORT_PUSH_RATE);
		
		return result.toArray(new NameValue[0]);
	}
	
	private static void addProperty(List<NameValue> result, Double value, String name) {
		if (value == null) return;
		addProperty(result, value.toString(), name);
	}

	private static void addProperty(List<NameValue> result, String value, String name) {
		if (StringUtils.isBlank(value)) return;
		result.add(SDOUtil.newNV(name, value));
	}

	private NameValue[] getNewProperties(NameValue[] properties) {
		if (properties == null) return new NameValue[1];
		NameValue[] result = new NameValue[properties.length + 1];
		System.arraycopy(properties, 0, result, 0, properties.length);
		return result;
	}

	private NameValue[] getProperties() {
		if (getRtcConnectorProfile() == null) return null;
		return getRtcConnectorProfile().properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getDataflowType() {
		return getPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_DATAFLOW_TYPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setDataflowType(String newDataflowType) {
		setPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_DATAFLOW_TYPE,
				newDataflowType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getSubscriptionType() {
		return getPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_SUBSCRIPTION_TYPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setSubscriptionType(String newSubscriptionType) {
		setPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_SUBSCRIPTION_TYPE,
				newSubscriptionType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getDataType() {
		return getPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_DATA_TYPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setDataType(String newDataType) {
		setPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_DATA_TYPE, newDataType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getInterfaceType() {
		return getPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_INTERFACE_TYPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setInterfaceType(String newInterfaceType) {
		setPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_DATAPORT_INTERFACE_TYPE,
				newInterfaceType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Double getPushRate() {
		String value = getPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_PORT_PUSH_RATE);

		try {
			return Double.parseDouble(value);
		} catch (RuntimeException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setPushRate(Double newPushRate) {
		setPropertyValueAsStringValue(getProperties(),
				NAME_VALUE_KEY_PORT_PUSH_RATE, newPushRate.toString());
	}

	// Mapping Rule
	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					CorbaConnectorProfileImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							ComponentPackage.eINSTANCE
									.getCorbaConnectorProfile_RtcConnectorProfile()) }) {
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});



} //CorbaConnectorProfileImpl
