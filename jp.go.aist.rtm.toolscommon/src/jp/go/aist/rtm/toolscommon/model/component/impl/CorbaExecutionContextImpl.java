/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import RTC.ExecutionContextProfile;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Execution Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl#getCorbaObject <em>Corba Object</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl#getRtcExecutionContextProfile <em>Rtc Execution Context Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorbaExecutionContextImpl extends ExecutionContextImpl implements CorbaExecutionContext {
	/**
	 * The default value of the '{@link #getCorbaObject() <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorbaObject()
	 * @generated
	 * @ordered
	 */
	protected static final org.omg.CORBA.Object CORBA_OBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCorbaObject() <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorbaObject()
	 * @generated
	 * @ordered
	 */
	protected org.omg.CORBA.Object corbaObject = CORBA_OBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRtcExecutionContextProfile() <em>Rtc Execution Context Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcExecutionContextProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionContextProfile RTC_EXECUTION_CONTEXT_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRtcExecutionContextProfile() <em>Rtc Execution Context Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRtcExecutionContextProfile()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContextProfile rtcExecutionContextProfile = RTC_EXECUTION_CONTEXT_PROFILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaExecutionContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_EXECUTION_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.omg.CORBA.Object getCorbaObject() {
		return corbaObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorbaObject(org.omg.CORBA.Object newCorbaObject) {
		org.omg.CORBA.Object oldCorbaObject = corbaObject;
		corbaObject = newCorbaObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT, oldCorbaObject, corbaObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContextProfile getRtcExecutionContextProfile() {
		return rtcExecutionContextProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setRtcExecutionContextProfile(ExecutionContextProfile newRtcExecutionContextProfile) {
		ExecutionContextProfile oldRtcExecutionContextProfile = rtcExecutionContextProfile;
		rtcExecutionContextProfile = newRtcExecutionContextProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE, oldRtcExecutionContextProfile, rtcExecutionContextProfile));
		setRateL(rtcExecutionContextProfile.rate);
		setKindL(rtcExecutionContextProfile.kind.value());
	}

	@Override
	public EList<NameValue> getProperties() {
		EList<NameValue> result = new BasicEList<NameValue>();
		if (getRtcExecutionContextProfile() == null) {
			return result;
		}
		for (NameValue nv : SDOUtil
				.createNameValueList(getRtcExecutionContextProfile().properties)) {
			result.add(nv);
		}
		return result;
	}

	@Override
	public boolean setRateR(Double rate) {
		RTC.ReturnCode_t ret = getCorbaObjectInterface().set_rate(rate);
		if (ret != RTC.ReturnCode_t.RTC_OK) {
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int startR() {
		RTC.ReturnCode_t ret = getCorbaObjectInterface().start();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int stopR() {
		RTC.ReturnCode_t ret = getCorbaObjectInterface().stop();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int activateR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.ReturnCode_t ret = getCorbaObjectInterface().activate_component(ro);
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int deactivateR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.ReturnCode_t ret = getCorbaObjectInterface().deactivate_component(ro);
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int resetR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.ReturnCode_t ret = getCorbaObjectInterface().reset_component(ro);
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getComponentStateR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.LifeCycleState state = getCorbaObjectInterface()
				.get_component_state(ro);
		if (state == RTC.LifeCycleState.ACTIVE_STATE
				|| state == RTC.LifeCycleState.ERROR_STATE
				|| state == RTC.LifeCycleState.INACTIVE_STATE) {
			return state.value();
		}
		return ExecutionContext.RTC_UNKNOWN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getComponentStateName(Component comp) {
		int state = getComponentStateR(comp);
		if (state == RTC.LifeCycleState.ACTIVE_STATE.value()) {
			return "ACTIVATE";
		} else if (state == RTC.LifeCycleState.INACTIVE_STATE.value()) {
			return "INACTIVATE";
		} else if (state == RTC.LifeCycleState.ERROR_STATE.value()) {
			return "ERROR";
		}
		return "UNKNOWN";
	}

	@Override
	public Component getOwner() {
		if (owner != null) {
			return owner;
		}
		if (rtcExecutionContextProfile == null
				|| rtcExecutionContextProfile.owner == null) {
			return null;
		}
		CorbaComponent c = toCorbaComponent(rtcExecutionContextProfile.owner);
		return c;
	}

	@Override
	public EList<Component> getParticipants() {
		if (participants == null) {
			super.getParticipants();
		}
		if (rtcExecutionContextProfile == null
				|| rtcExecutionContextProfile.participants == null) {
			return participants;
		}
		participants.clear();
		for (RTC.RTObject ro : rtcExecutionContextProfile.participants) {
			CorbaComponent c = toCorbaComponent(ro);
			participants.add(c);
		}
		return participants;
	}

	CorbaComponent toCorbaComponent(RTC.RTObject ro) {
		CorbaComponent c = ComponentFactory.eINSTANCE.createCorbaComponent();
		c.setCorbaObject(ro);
		// CorbaComponentを作成し、RTC.ComponentProfileのキャッシュを設定
		// キャッシュが存在しない場合は同期させる
		RTC.ComponentProfile profile = CorbaComponentImpl.getProfile(ro);
		if (profile != null) {
			c.setRTCComponentProfile(profile);
		} else {
			getSynchronizationSupport().getSynchronizationManager()
					.assignSynchonizationSupport(c);
			c.synchronizeManually();
		}
		return c;
	}

	@Override
	public boolean addComponentR(Component comp) {
		if (!(comp instanceof CorbaComponent)) {
			return false;
		}
		CorbaComponent cc = (CorbaComponent) comp;
		// 同一RTCのアタッチを許容
		RTC.ExecutionContext ec = getCorbaObjectInterface();
		RTC.ReturnCode_t ret = ec.add_component(cc.getCorbaObjectInterface());
		if (ret != RTC.ReturnCode_t.RTC_OK) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeComponentR(Component comp) {
		if (!(comp instanceof CorbaComponent)) {
			return false;
		}
		CorbaComponent cc = (CorbaComponent) comp;
		RTC.ExecutionContext ec = getCorbaObjectInterface();
		if (!cc.getRTCParticipationContexts().contains(ec)) {
			return true;
		}
		RTC.ReturnCode_t ret = ec
				.remove_component(cc.getCorbaObjectInterface());
		if (ret != RTC.ReturnCode_t.RTC_OK) {
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT:
				return getCorbaObject();
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE:
				return getRtcExecutionContextProfile();
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
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT:
				setCorbaObject((org.omg.CORBA.Object)newValue);
				return;
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE:
				setRtcExecutionContextProfile((ExecutionContextProfile)newValue);
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
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT:
				setCorbaObject(CORBA_OBJECT_EDEFAULT);
				return;
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE:
				setRtcExecutionContextProfile(RTC_EXECUTION_CONTEXT_PROFILE_EDEFAULT);
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
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT:
				return CORBA_OBJECT_EDEFAULT == null ? corbaObject != null : !CORBA_OBJECT_EDEFAULT.equals(corbaObject);
			case ComponentPackage.CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE:
				return RTC_EXECUTION_CONTEXT_PROFILE_EDEFAULT == null ? rtcExecutionContextProfile != null : !RTC_EXECUTION_CONTEXT_PROFILE_EDEFAULT.equals(rtcExecutionContextProfile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == CorbaWrapperObject.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT: return CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT;
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
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == CorbaWrapperObject.class) {
			switch (baseFeatureID) {
				case CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT: return ComponentPackage.CORBA_EXECUTION_CONTEXT__CORBA_OBJECT;
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
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (corbaObject: ");
		result.append(corbaObject);
		result.append(", rtcExecutionContextProfile: ");
		result.append(rtcExecutionContextProfile);
		result.append(')');
		return result.toString();
	}

	public RTC.ExecutionContext getCorbaObjectInterface() {
		return RTC.ExecutionContextHelper.narrow(getCorbaObject());
	}

	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					CorbaExecutionContextImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) }) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					boolean result = false;
					if (((org.omg.CORBA.Object) remoteObjects[0])
							._is_a(RTC.ExecutionContextHelper.id())) {
						result = true;
					}
					return result;
				}

				@Override
				public boolean needsPing() {
					return true;
				}

				@Override
				public Object[] narrow(Object[] remoteObjects) {
					return new Object[] { RTC.ExecutionContextHelper
							.narrow((org.omg.CORBA.Object) remoteObjects[0]) };
				}
			},
			new AttributeMapping[] {
					new AttributeMapping(
							ComponentPackage.eINSTANCE
									.getCorbaExecutionContext_RtcExecutionContextProfile()) {
						@Override
						public Object getRemoteAttributeValue(
								LocalObject localObject, Object[] remoteObjects) {
							Object result = null;
							try {
								org.omg.CORBA.Object co = (org.omg.CORBA.Object) remoteObjects[0];
								if (co._is_a(RTC.ExecutionContextServiceHelper
										.id())) {
									RTC.ExecutionContextService ec = RTC.ExecutionContextServiceHelper
											.narrow(co);
									RTC.ExecutionContextProfile prof = ec
											.get_profile();
									result = prof;
								} else {
									RTC.ExecutionContext ec = RTC.ExecutionContextHelper
											.narrow(co);
									RTC.ExecutionContextProfile prof = new RTC.ExecutionContextProfile();
									prof.rate = ec.get_rate();
									prof.kind = ec.get_kind();
									result = prof;
								}
							} catch (Exception e) {
								// void
							}
							return result;
						}

						@Override
						public boolean isEquals(Object value1, Object value2) {
							if (value1 == null) {
								return value2 == null;
							}
							if (!(value1 instanceof RTC.ExecutionContextProfile)) {
								return false;
							}
							if (!(value2 instanceof RTC.ExecutionContextProfile)) {
								return false;
							}
							RTC.ExecutionContextProfile p1 = (RTC.ExecutionContextProfile) value1;
							RTC.ExecutionContextProfile p2 = (RTC.ExecutionContextProfile) value2;
							if ((p1.kind == p2.kind) && (p1.rate == p2.rate)
									&& eq(p1.owner, p2.owner)
									&& eq(p1.participants, p2.participants)
									&& eq(p1.properties, p2.properties)) {
								return true;
							}
							return false;
						}

						boolean eq(RTC.RTObject o1, RTC.RTObject o2) {
							if (o1 == null) {
								return (o2 == null);
							}
							return o1.equals(o2);
						}

						boolean eq(RTC.RTObject[] o1, RTC.RTObject[] o2) {
							if (o1.length != o2.length) {
								return false;
							}
							for (int i = 0; i < o1.length; i++) {
								if (!eq(o1[i], o2[i])) {
									return false;
								}
							}
							return true;
						}

						boolean eq(_SDOPackage.NameValue[] o1,
								_SDOPackage.NameValue[] o2) {
							if (o1.length != o2.length) {
								return false;
							}
							for (int i = 0; i < o1.length; i++) {
								if (o1[i].name != o2[i].name
										|| o1[i].value != o2[i].value) {
									return false;
								}
							}
							return true;
						}
					},
					new AttributeMapping(ComponentPackage.eINSTANCE
							.getExecutionContext_StateL()) {
						@Override
						public Object getRemoteAttributeValue(
								LocalObject localObject, Object[] remoteObjects) {
							try {
								RTC.ExecutionContext ec = RTC.ExecutionContextHelper
										.narrow((org.omg.CORBA.Object) remoteObjects[0]);
								if (ec.is_running()) {
									return STATE_RUNNING;
								} else {
									return STATE_STOPPED;
								}
							} catch (Exception e) {
								return STATE_UNKNOWN;
							}
						}

						@Override
						public boolean isEquals(Object value1, Object value2) {
							if (value1 == null) {
								return value2 == null;
							}
							if (value1 instanceof Integer
									&& value2 instanceof Integer) {
								return ((Integer) value1)
										.equals((Integer) value2);
							}
							return false;
						}
					}, }, new ReferenceMapping[] {});

} // CorbaExecutionContextImpl
