/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorbaWrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import RTC.PortProfile;
import _SDOPackage.NameValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Port Synchronizer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl#getOriginalPortString <em>Original Port String</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl#getRTCPortProfile <em>RTC Port Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorbaPortSynchronizerImpl extends CorbaWrapperObjectImpl implements CorbaPortSynchronizer {

	private static final String NAME_VALUE_KEY_PORT_PORT_TYPE_DATA_OUTPORT_VALUE = "DataOutPort";

	private static final String NAME_VALUE_KEY_PORT_PORT_TYPE_DATA_INPORT_VALUE = "DataInPort";

	private static final String NAME_VALUE_KEY_PORT_PORT_TYPE_SERVICE_PORT_VALUE = "CorbaPort";

	private static final String NAME_VALUE_KEY_PORT_PORT_TYPE = "port.port_type";

	/**
	 * The default value of the '{@link #getOriginalPortString() <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalPortString()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_PORT_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginalPortString() <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalPortString()
	 * @generated
	 * @ordered
	 */
	protected String originalPortString = ORIGINAL_PORT_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getRTCPortProfile() <em>RTC Port Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCPortProfile()
	 * @generated
	 * @ordered
	 */
	protected static final PortProfile RTC_PORT_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRTCPortProfile() <em>RTC Port Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCPortProfile()
	 * @generated
	 * @ordered
	 */
	protected PortProfile rTCPortProfile = RTC_PORT_PROFILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaPortSynchronizerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_PORT_SYNCHRONIZER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getOriginalPortString() {
		if (getCorbaObjectInterface() != null) return getCorbaObjectInterface().toString();
		return originalPortString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalPortString(String newOriginalPortString) {
		String oldOriginalPortString = originalPortString;
		originalPortString = newOriginalPortString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING, oldOriginalPortString, originalPortString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortProfile getRTCPortProfile() {
		return rTCPortProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRTCPortProfile(PortProfile newRTCPortProfile) {
		PortProfile oldRTCPortProfile = rTCPortProfile;
		rTCPortProfile = newRTCPortProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE, oldRTCPortProfile, rTCPortProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				return getOriginalPortString();
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE:
				return getRTCPortProfile();
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
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				setOriginalPortString((String)newValue);
				return;
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE:
				setRTCPortProfile((PortProfile)newValue);
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
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				setOriginalPortString(ORIGINAL_PORT_STRING_EDEFAULT);
				return;
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE:
				setRTCPortProfile(RTC_PORT_PROFILE_EDEFAULT);
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
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING:
				return ORIGINAL_PORT_STRING_EDEFAULT == null ? originalPortString != null : !ORIGINAL_PORT_STRING_EDEFAULT.equals(originalPortString);
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER__RTC_PORT_PROFILE:
				return RTC_PORT_PROFILE_EDEFAULT == null ? rTCPortProfile != null : !RTC_PORT_PROFILE_EDEFAULT.equals(rTCPortProfile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == PortSynchronizer.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING: return ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == PortSynchronizer.class) {
			switch (baseFeatureID) {
				case ComponentPackage.PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING: return ComponentPackage.CORBA_PORT_SYNCHRONIZER__ORIGINAL_PORT_STRING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (originalPortString: ");
		result.append(originalPortString);
		result.append(", rTCPortProfile: ");
		result.append(rTCPortProfile);
		result.append(')');
		return result.toString();
	}

//	@Override
	public RTC.PortService getCorbaObjectInterface() {
		return getRTCPortProfile().port_ref;
	}
// Mapping Rule

	public static final MappingRule MAPPING_RULE = new MappingRule(null,
			new ClassMapping(
					Port.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							ComponentPackage.eINSTANCE
								.getCorbaPortSynchronizer_RTCPortProfile()) }) {
						@Override
						public LocalObject createLocalObject(
								LocalObject parent,
								Object[] remoteObjects, Object link) {
							RTC.PortProfile profile = (PortProfile) remoteObjects[0];
							String portType = SDOUtil.getStringValue(profile.properties
									, NAME_VALUE_KEY_PORT_PORT_TYPE);
							Port port;
							if (portType.equals(NAME_VALUE_KEY_PORT_PORT_TYPE_DATA_INPORT_VALUE)){
								port = ComponentFactory.eINSTANCE.createInPort();
							} else if (portType.equals(NAME_VALUE_KEY_PORT_PORT_TYPE_DATA_OUTPORT_VALUE)) {
								port = ComponentFactory.eINSTANCE.createOutPort();
							} else if (portType.equals(NAME_VALUE_KEY_PORT_PORT_TYPE_SERVICE_PORT_VALUE)) {
								port = ComponentFactory.eINSTANCE.createServicePort();
							} else {
								throw new IllegalStateException("unknown port type:" + portType);
							}
							CorbaPortSynchronizer synchronizer = ComponentFactory.eINSTANCE.createCorbaPortSynchronizer();
							port.setSynchronizer(synchronizer);
							synchronizer.setRTCPortProfile(profile);
							return port;
						}
			},
		new AttributeMapping[] {
			new AttributeMapping(ComponentPackage.eINSTANCE
					.getPort_NameL(), true) {
				@Override
				public Object getRemoteAttributeValue(
						LocalObject localObject, Object[] remoteObjects) {
					try {
						PortProfile portProfile = getPortProfile(localObject);
						return portProfile != null ? portProfile.name : null;
					} catch (Exception e) {
						return null;
					}
				}
			},
			new AttributeMapping(ComponentPackage.eINSTANCE
					.getPort_ConnectorProfiles(), false) {
				@SuppressWarnings("unchecked")
				@Override
				public Object getRemoteAttributeValue(
						LocalObject localObject, Object[] remoteObjects) {
					List result = new ArrayList();
					try {
						PortProfile portProfile = getPortProfile(localObject);
						if (portProfile == null) return result;
						for (RTC.ConnectorProfile profile : portProfile.connector_profiles) {
							result.add(CorbaWrapperFactory
									.getInstance().createWrapperObject(
											profile));
						}
					} catch (Exception e) {
						// void
					}

					return result;
				}
			} ,
			new AttributeMapping(ComponentPackage.eINSTANCE
					.getPort_Interfaces(), true) {
				@SuppressWarnings("unchecked")
				@Override
				public Object getRemoteAttributeValue(
						LocalObject localObject, Object[] remoteObjects) {
					List result = new ArrayList();
					try {
						PortProfile portProfile = getPortProfile(localObject);
						if (portProfile == null) return result;
						for (RTC.PortInterfaceProfile profile : portProfile.interfaces) {
							result.add(new CorbaPortInterfaceProfile(profile));
						}
					} catch (Exception e) {
						// void
					}

					return result;
				}
			}
}, new ReferenceMapping[] {}){

	@Override
	public boolean isTarget(LocalObject localObject) {
		Port port = (Port) localObject;
		return port.getSynchronizer() instanceof CorbaPortSynchronizer;
	}};

	protected static PortProfile getPortProfile(LocalObject localObject) {
		CorbaPortSynchronizer synchronizer = getCorbaSynchronizer(localObject);
		if (synchronizer == null) return null;
		return synchronizer.getRTCPortProfile();
	}

	private static CorbaPortSynchronizer getCorbaSynchronizer(
			LocalObject localObject) {
		if (!(localObject instanceof Port)) return null;
		Port port = (Port) localObject;
		if (!(port.getSynchronizer() instanceof CorbaPortSynchronizer)) return null;
		CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer)port.getSynchronizer();
		return synchronizer;
	}

	public static RTC.PortService getRemoteObjectsForSync(LocalObject localObject) {
		CorbaPortSynchronizer synchronizer = getCorbaSynchronizer(localObject);
		if (synchronizer == null) return null;
		return synchronizer.getCorbaObjectInterface();
	}

//	@Override
	public void disconnectAll() {
		getCorbaObjectInterface().disconnect_all();
	}

//	@Override
	public String getDataflowType() {
		return CorbaConnectorProfileImpl.getDataflowTypes(getRtcProperties());
	}

//	@Override
	public String getDataType() {
		return CorbaConnectorProfileImpl.getDataTypes(getRtcProperties());
	}

//	@Override
	public String getInterfaceType() {
		return CorbaConnectorProfileImpl.getInterfaceTypes(getRtcProperties());
	}

//	@Override
	public String getSubscriptionType() {
		return CorbaConnectorProfileImpl.getSubscriptionTypes(getRtcProperties());
	}

	private NameValue[] getRtcProperties() {
		if (getRTCPortProfile() == null) return null;
		return getRTCPortProfile().properties;
	}

	public List<jp.go.aist.rtm.toolscommon.model.component.NameValue> getProperties() {
		return CorbaConnectorProfileImpl.getProperties(getRtcProperties());
	}

	public String getProperty(String name) {
		return CorbaConnectorProfileImpl.getPropertyValueAsStringValue(getRtcProperties(), name);
	}

} //CorbaPortSynchronizerImpl
