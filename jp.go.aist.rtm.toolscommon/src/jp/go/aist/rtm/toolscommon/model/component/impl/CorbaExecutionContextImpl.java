/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ComponentState;
import static jp.go.aist.rtm.toolscommon.util.RTMixin.eql;

import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaPropertyMap;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import RTC.ExecutionContextProfile;
import _SDOPackage.NameValue;

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
		this.properties = new CorbaPropertyMap() {
			@Override
			public NameValue[] getNameValues() {
				if (getRtcExecutionContextProfile() == null
						|| getRtcExecutionContextProfile().properties == null) {
					return new NameValue[0];
				}
				return getRtcExecutionContextProfile().properties;
			}

			@Override
			public void setNameValues(NameValue[] nvs) {
				getRtcExecutionContextProfile().properties = nvs;
			}
		};
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
	@Override
	public org.omg.CORBA.Object getCorbaObject() {
		return corbaObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public ExecutionContextProfile getRtcExecutionContextProfile() {
		return rtcExecutionContextProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setRtcExecutionContextProfile(ExecutionContextProfile newRtcExecutionContextProfile) {
		ExecutionContextProfile oldRtcExecutionContextProfile = rtcExecutionContextProfile;
		rtcExecutionContextProfile = newRtcExecutionContextProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_EXECUTION_CONTEXT__RTC_EXECUTION_CONTEXT_PROFILE, oldRtcExecutionContextProfile, rtcExecutionContextProfile));
		if (rtcExecutionContextProfile == null) {
			setRateL(RATE_L_EDEFAULT);
			setKindL(KIND_L_EDEFAULT);
		} else {
			setRateL(rtcExecutionContextProfile.rate);
			setKindL(rtcExecutionContextProfile.kind.value());
		}
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
	@Override
	public int startR() {
		RTC.ExecutionContext ec = getCorbaObjectInterface();
		RTC.ReturnCode_t ret = ec.start();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int stopR() {
		RTC.ExecutionContext ec = getCorbaObjectInterface();
		RTC.ReturnCode_t ret = ec.stop();
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int activateR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.ReturnCode_t ret = getCorbaObjectInterface().activate_component(ro);
		//
		synchronizeRemote_EC_ComponentState(ro, getCorbaObjectInterface());
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int deactivateR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.ReturnCode_t ret = getCorbaObjectInterface().deactivate_component(ro);
		//
		synchronizeRemote_EC_ComponentState(ro, getCorbaObjectInterface());
		return ret.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int resetR(Component comp) {
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		RTC.ReturnCode_t ret = getCorbaObjectInterface().reset_component(ro);
		//
		synchronizeRemote_EC_ComponentState(ro, getCorbaObjectInterface());
		return ret.value();
	}

	private Map<RTC.RTObject, Integer> componentStateMap = new HashMap<>();

	// コンポーネント状態の設定(仮)
	void setComponentState(Component comp, Integer state) {
		if (comp == null || !(comp instanceof CorbaComponent)) {
			return;
		}
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		Integer oldState = this.componentStateMap.get(ro);
		if (!eql(oldState, state)) {
			this.componentStateMap.put(ro, state);
			if (eNotificationRequired()) {
				eNotify(new ENotificationImpl(this, Notification.SET,
						ComponentPackage.EXECUTION_CONTEXT__STATE_L, oldState,
						state));
			}
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int getComponentState(Component comp) {
		if (comp == null || !(comp instanceof CorbaComponent)) {
			return ExecutionContext.RTC_UNKNOWN;
		}
		RTC.RTObject ro = ((CorbaComponent) comp).getCorbaObjectInterface();
		Integer state = this.componentStateMap.get(ro);
		if (state == null) {
			return ExecutionContext.RTC_UNKNOWN;
		}
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getComponentStateName(Component comp) {
		return RTC_STATUS_LABEL(getComponentState(comp));
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
		if (participants.size() != rtcExecutionContextProfile.participants.length) {
			participants.clear();
			for (RTC.RTObject ro : rtcExecutionContextProfile.participants) {
				CorbaComponent c = toCorbaComponent(ro);
				participants.add(c);
			}
		}
		return participants;
	}

	CorbaComponent toCorbaComponent(RTC.RTObject ro) {
		CorbaComponent c = ComponentFactory.eINSTANCE.createCorbaComponent();
		c.setCorbaObject(ro);
		// CorbaComponentを作成し、RTC.ComponentProfileのキャッシュを設定
		// キャッシュが存在しない場合は同期させる
		RTC.ComponentProfile profile = CorbaObjectStore.eINSTANCE
				.findRTCProfile(ro);
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
		try {
			RTC.ReturnCode_t ret = ec.add_component(cc
					.getCorbaObjectInterface());
			if (ret != RTC.ReturnCode_t.RTC_OK) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
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
		try {
			RTC.ReturnCode_t ret = ec.remove_component(cc
					.getCorbaObjectInterface());
			if (ret != RTC.ReturnCode_t.RTC_OK) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
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

	@Override
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
						public void syncronizeLocal(LocalObject localObject) {
							CorbaExecutionContext cec = (CorbaExecutionContext) localObject;
							RTC.ExecutionContext ec = cec
									.getCorbaObjectInterface();
							RTC.ExecutionContextProfile prof = CorbaObjectStore.eINSTANCE
									.findECProfile(ec);
							if (!eql(cec.getRtcExecutionContextProfile(), prof)) {
								cec.setRtcExecutionContextProfile(prof);
							}
						}
					},
					new AttributeMapping(ComponentPackage.eINSTANCE
							.getExecutionContext_StateL()) {
						@Override
						public void syncronizeLocal(LocalObject localObject) {
							CorbaExecutionContext cec = (CorbaExecutionContext) localObject;
							RTC.ExecutionContext ec = cec
									.getCorbaObjectInterface();
							Integer state = CorbaObjectStore.eINSTANCE
									.findECState(ec);
							if (state != null && cec.getStateL() != state) {
								cec.setStateL(state);
							}
							//
							Component owner = cec.getOwner();
							if (owner != null
									&& owner instanceof CorbaComponent) {
								RTC.RTObject ro = ((CorbaComponent) owner)
										.getCorbaObjectInterface();
								Integer compState = CorbaObjectStore.eINSTANCE
										.findComponentState(ec, ro);
								((CorbaExecutionContextImpl) cec)
										.setComponentState(owner, compState);
							}
						}
					}, }, new ReferenceMapping[] {});

} // CorbaExecutionContextImpl
