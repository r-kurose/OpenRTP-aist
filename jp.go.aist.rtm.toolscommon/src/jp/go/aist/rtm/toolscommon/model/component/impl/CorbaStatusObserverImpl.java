/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_HB_ENABLE;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_HB_INTERVAL;
import static jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager.KEY_STATUS_OBSERVER_HB_TRYCOUNT;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_ActiveConfigurationSet;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_ConfigurationSets;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ComponentState;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ECProfile;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_EC_ECState;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCComponentProfile;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCExecutionContexts;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCPortProfile;
import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.synchronizeRemote_RTCRTObjects;
import static jp.go.aist.rtm.toolscommon.util.RTMixin.eql;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverStore;

import org.eclipse.emf.ecore.EClass;
import org.omg.PortableServer.Servant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Status Observer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class CorbaStatusObserverImpl extends CorbaObserverImpl implements CorbaStatusObserver {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CorbaStatusObserverImpl.class);

	public static final String[] TYPE_NAMES = new String[] {
			"COMPONENT_PROFILE", //
			"RTC_STATUS", //
			"EC_STATUS", //
			"PORT_PROFILE", //
			"CONFIGURATION", //
			"HEART_BEAT", //
	};

	protected ComponentObserverPOAImpl servant;

	RTC.RTObject rtc;

	PropertyChangeListener listener;

	static Map<RTC.RTObject, HeartBeat> hbMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected CorbaStatusObserverImpl() {
		super();
		if (hbMap == null) {
			hbMap = new HashMap<RTC.RTObject, HeartBeat>();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_STATUS_OBSERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isTimeOut() {
		if (rtc == null) {
			return false;
		}
		HeartBeat hb = hbMap.get(rtc);
		if (hb == null) {
			return false;
		}
		if (!hb.isEnable()) {
			return false;
		}
		return hb.isTimeOut();
	}

	@Override
	public Servant getServant() {
		if (servant == null) {
			servant = new ComponentObserverPOAImpl(this);
		}
		return servant;
	}

	@Override
	public boolean attachComponent(CorbaComponent component) {
		RTC.RTObject ro = component.getCorbaObjectInterface();
		if (rtc == null) {
			rtc = ro;
		}
		if (!eql(rtc, ro)) {
			return false;
		}
		CorbaStatusObserver obs = CorbaObserverStore.eINSTANCE
				.findStatusObserver(ro);
		if (obs != null) {
			return true;
		} else {
			HeartBeat hb = new HeartBeat();
			hbMap.put(rtc, hb);
			//
			serviceProfile = new _SDOPackage.ServiceProfile();
			serviceProfile.interface_type = OpenRTM.ComponentObserverHelper
					.id();
			//
//			setProperty("observed_status", "ALL");
//			setProperty("heartbeat.enable", hb.getPropEnable());
//			setProperty("heartbeat.interval", hb.getPropInterval());
			//
			listener = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					propertyChanged(evt.getPropertyName());
				}
			};
			ToolsCommonPreferenceManager.getInstance()
					.addPropertyChangeListener(listener);
			//
			activate();
			try {
				boolean result = addServiceProfile(rtc.get_configuration());
				if (!result) {
					deactivate();
					return false;
				}
			} catch (Exception e) {
				deactivate();
				return false;
			}
			CorbaObserverStore.eINSTANCE.registStatusObserver(ro, this);
		}
		return true;
	}

	@Override
	public boolean detachComponent() {
		if (rtc == null) {
			return true;
		}
		if (!CorbaObserverStore.eINSTANCE.isEmptyComponentReference(rtc)) {
			return true;
		}
		//
		return finish();
	}

	@Override
	public boolean finish() {
		if (rtc == null) {
			return true;
		}
		//
		boolean result = false;
		try {
			result = removeServiceProfile(rtc.get_configuration());
		} catch (Exception e) {
		}
		deactivate();
		//
		CorbaObserverStore.eINSTANCE.removeStatusObserver(rtc);
		hbMap.remove(rtc);
		ToolsCommonPreferenceManager.getInstance()
				.removePropertyChangeListener(listener);
		//
		return result;
	}

	public void notifyStatus(OpenRTM.StatusKind status_kind, String hint) {
		if (OpenRTM.StatusKind.HEARTBEAT.equals(status_kind)) {
			// H.B受信
			HeartBeat hb = hbMap.get(rtc);
			if (hb != null) {
				hb.recv();
				hb.setForceTimeOut(false);
			}
			return;
		}

		String profId = (serviceProfile == null) ? "" : serviceProfile.id;
		LOGGER.info("update_status({}, {}): id={}",
				TYPE_NAMES[status_kind.value()], hint, profId);

		if (CorbaObserverStore.eINSTANCE.isEmptyComponentReference(rtc)) {
			return;
		}
		//
		if (OpenRTM.StatusKind.COMPONENT_PROFILE.equals(status_kind)) {
			// RTC.ComponentProfileの変更通知
			synchronizeRemote_RTCComponentProfile(rtc);
		}
		if (OpenRTM.StatusKind.RTC_STATUS.equals(status_kind)) {
			// RTC状態の変更通知
			if (hint == null) {
				return;
			}
			String[] ss = hint.split(":");
			if (ss.length != 2) {
				return;
			}
			String state = ss[0];
			String id = ss[1];
			//
			int stateValue = ExecutionContext.RTC_UNKNOWN;
			if ("ACTIVE".equals(state)) {
				stateValue = RTC.LifeCycleState._ACTIVE_STATE;
			} else if ("INACTIVE".equals(state)) {
				stateValue = RTC.LifeCycleState._INACTIVE_STATE;
			} else if ("ERROR".equals(state)) {
				stateValue = RTC.LifeCycleState._ERROR_STATE;
			} else if ("FINALIZE".equals(state)) {
				// H.Bタイムアウトを設定
				HeartBeat hb = hbMap.get(rtc);
				hb.setForceTimeOut(true);
				return;
			}
			//
			RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE.findContext(
					rtc, id);
			CorbaObjectStore.eINSTANCE
					.registComponentState(ec, rtc, stateValue);
		}
		if (OpenRTM.StatusKind.EC_STATUS.equals(status_kind)) {
			// EC状態の変更通知
			if (hint == null) {
				return;
			}
			String[] ss = hint.split(":");
			if (ss.length != 2) {
				return;
			}
			String action = ss[0];
			String id = ss[1];
			//
			if ("ATTACHED".equals(action) || "DETACHED".equals(action)) {
				RTC.ExecutionContext oldEc = CorbaObjectStore.eINSTANCE
						.findContext(rtc, id);
				//
				synchronizeRemote_RTCExecutionContexts(rtc);
				//
				RTC.ExecutionContext newEc = CorbaObjectStore.eINSTANCE
						.findContext(rtc, id);
				//
				RTC.ExecutionContext ec = null;
				if ("ATTACHED".equals(action)) {
					ec = newEc;
				} else if ("DETACHED".equals(action)) {
					ec = oldEc;
				}
				if (ec != null) {
					synchronizeRemote_EC_ECProfile(ec);
					synchronizeRemote_EC_ComponentState(rtc, ec);
					// 複合RTCの子情報の変更通知がないため、ECのアタッチ/デタッチ時にECオーナーを更新
					RTC.ExecutionContextProfile ecprof = CorbaObjectStore.eINSTANCE
							.findECProfile(ec);
					if (ecprof != null && ecprof.owner != null) {
						synchronizeRemote_RTCRTObjects(ecprof.owner);
					}
				}
			} else if ("RATE_CHANGED".equals(action)) {
				RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE
						.findContext(rtc, id);
				if (ec != null) {
					synchronizeRemote_EC_ECProfile(ec);
				}
			} else if ("STARTUP".equals(action) || "SHUTDOWN".equals(action)) {
				RTC.ExecutionContext ec = CorbaObjectStore.eINSTANCE
						.findContext(rtc, id);
				if (ec != null) {
					synchronizeRemote_EC_ECState(ec);
				}
			}
		}
		if (OpenRTM.StatusKind.PORT_PROFILE.equals(status_kind)) {
			// RTC.PortProfileの変更通知
			if (hint == null) {
				return;
			}
			String[] ss = hint.split(":");
			if (ss.length != 2) {
				return;
			}
			String action = ss[0];
			String port_name = ss[1];
			//
			if ("CONNECT".equals(action) || "DISCONNECT".equals(action)) {
				synchronizeRemote_RTCPortProfile(rtc, port_name);
			} else if ("ADD".equals(action) || "REMOVE".equals(action)) {
				synchronizeRemote_RTCComponentProfile(rtc);
			}
		}
		if (OpenRTM.StatusKind.CONFIGURATION.equals(status_kind)) {
			// ConfigurationSetの変更通知
			if (hint == null) {
				return;
			}
			if ("ACTIVATE_CONFIG_SET".equals(hint)) {
				synchronizeRemote_ActiveConfigurationSet(rtc);
			} else {
				synchronizeRemote_ConfigurationSets(rtc);
				// 複合RTCの公開ポート変更の通知がないので、ConfigurationSetの通知時にプロファイルを更新
				RTC.ComponentProfile prof = CorbaObjectStore.eINSTANCE
						.findRTCProfile(rtc);
				if (prof != null && prof.category != null
						&& prof.category.startsWith("composite.")) {
					synchronizeRemote_RTCComponentProfile(rtc);
				}
			}
		}
	}

	public void propertyChanged(String name) {
		if (name == null) {
			return;
		}
		String p = name.substring(name.lastIndexOf(".") + 1);

		LOGGER.info("property changed: {}", p);

		if (!KEY_STATUS_OBSERVER_HB_ENABLE.equals(name)
				&& !KEY_STATUS_OBSERVER_HB_INTERVAL.equals(name)
				&& !KEY_STATUS_OBSERVER_HB_TRYCOUNT.equals(name)) {
			return;
		}
		//
		if (rtc == null) {
			return;
		}
		HeartBeat hb = hbMap.get(rtc);
		if (hb == null || serviceProfile == null) {
			return;
		}
		//
		hb.updatePreference();
		setProperty("heartbeat.enable", hb.getPropEnable());
		setProperty("heartbeat.interval", hb.getPropInterval());
		//
		try {
			removeServiceProfile(rtc.get_configuration());
			addServiceProfile(rtc.get_configuration());
		} catch (Exception e) {
		}
	}

	static class ComponentObserverPOAImpl extends OpenRTM.ComponentObserverPOA {
		CorbaStatusObserverImpl parent;

		public ComponentObserverPOAImpl(CorbaStatusObserverImpl parent) {
			this.parent = parent;
		}

		@Override
		public void update_status(OpenRTM.StatusKind status_kind, String hint) {
			parent.notifyStatus(status_kind, hint);
		}
	}

	public static class HeartBeat {
		Boolean enable;
		Double interval;
		Integer tryCount;
		Integer count;
		Long nextTime;
		boolean forceTimeOut;

		ToolsCommonPreferenceManager pref;

		HeartBeat() {
			this.pref = ToolsCommonPreferenceManager.getInstance();
			//
			this.enable = pref.isSTATUS_OBSERVER_HB_ENABLE();
			this.interval = pref.getSTATUS_OBSERVER_HB_INTERVAL();
			this.tryCount = pref.getSTATUS_OBSERVER_HB_TRYCOUNT();
			this.count = 0;
			this.nextTime = new Date().getTime();
			this.forceTimeOut = false;
		}

		void updatePreference() {
			this.enable = pref.isSTATUS_OBSERVER_HB_ENABLE();
			this.interval = pref.getSTATUS_OBSERVER_HB_INTERVAL();
			this.tryCount = pref.getSTATUS_OBSERVER_HB_TRYCOUNT();
		}

		String getPropEnable() {
			return isEnable() ? "YES" : "NO";
		}

		String getPropInterval() {
			return Double.toString(interval);
		}

		boolean isEnable() {
			return this.enable;
		}

		Double getInterval() {
			return interval;
		}

		void setInterval(Double interval) {
			this.interval = interval;
		}

		Integer getTryCount() {
			return tryCount;
		}

		void setTryCount(Integer tryCount) {
			this.tryCount = tryCount;
		}

		void recv() {
			nextTime = getNextTime();
			count = 0;
		}

		boolean isTimeOut() {
			if (forceTimeOut) {
				return true;
			}
			Date now = new Date();
			if (now.getTime() > nextTime) {
				nextTime = getNextTime();
				count++;
			}
			if (count > tryCount) {
				return true;
			}
			return false;
		}

		long getNextTime() {
			Date now = new Date();
			return now.getTime() + new Double(interval * 1000.0).longValue();
		}

		void setForceTimeOut(boolean b) {
			this.forceTimeOut = b;
		}
	}

} //CorbaStatusObserverImpl
