package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jp.go.aist.rtm.toolscommon.util.RTMixin.*;

public class CorbaObjectStore {

	public static CorbaObjectStore eINSTANCE = new CorbaObjectStore();

	// RTC.RTObj => RTC.ComponentProfile
	Map<RTC.RTObject, RTC.ComponentProfile> profileCache;
	// RTC.RTObj => { ID => RTC.EC }
	Map<RTC.RTObject, Map<String, RTC.ExecutionContext>> eContextIdMap;
	// RTC.RTObj => RTC.EC(owned)[]
	Map<RTC.RTObject, RTC.ExecutionContext[]> eOwnedContextCache;
	// RTC.RTObj => RTC.EC(participating)[]
	Map<RTC.RTObject, RTC.ExecutionContext[]> eParticipatingContextCache;
	// RTC.RTObj => SDO.ConfigurationSet[]
	Map<RTC.RTObject, _SDOPackage.ConfigurationSet[]> configSetCache;
	// RTC.RTObj => SDO.ConfigurationSet(active)
	Map<RTC.RTObject, _SDOPackage.ConfigurationSet> activeConfigSetCache;
	// RTC.RTObj => [ RTC.RTObj, ... ]
	Map<RTC.RTObject, List<RTC.RTObject>> compositeMemberList;

	// RTC.EC => RTC.ExecutionContextProfile
	Map<RTC.ExecutionContext, RTC.ExecutionContextProfile> eContextProfileCache;
	// RTC.EC => EC_STATE
	Map<RTC.ExecutionContext, Integer> eContextStateCache;
	// RTC.EC => { RTC.RTObj => State }
	Map<RTC.ExecutionContext, Map<RTC.RTObject, Integer>> eContextCompStateMap;

	public CorbaObjectStore() {
		//
		this.profileCache = new HashMap<RTC.RTObject, RTC.ComponentProfile>();
		this.eContextIdMap = new HashMap<RTC.RTObject, Map<String, RTC.ExecutionContext>>();
		this.eOwnedContextCache = new HashMap<RTC.RTObject, RTC.ExecutionContext[]>();
		this.eParticipatingContextCache = new HashMap<RTC.RTObject, RTC.ExecutionContext[]>();
		this.configSetCache = new HashMap<RTC.RTObject, _SDOPackage.ConfigurationSet[]>();
		this.activeConfigSetCache = new HashMap<RTC.RTObject, _SDOPackage.ConfigurationSet>();
		this.compositeMemberList = new HashMap<RTC.RTObject, List<RTC.RTObject>>();
		//
		this.eContextProfileCache = new HashMap<RTC.ExecutionContext, RTC.ExecutionContextProfile>();
		this.eContextStateCache = new HashMap<RTC.ExecutionContext, Integer>();
		this.eContextCompStateMap = new HashMap<RTC.ExecutionContext, Map<RTC.RTObject, Integer>>();
	}

	/**
	 * RTC.RTObjectをキーに RTC.ComponentProfileを検索します。
	 */
	public RTC.ComponentProfile findRTCProfile(RTC.RTObject ro) {
		return profileCache.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ComponentProfileを保存します。
	 */
	public synchronized RTC.ComponentProfile registRTCProfile(RTC.RTObject ro,
			RTC.ComponentProfile profile) {
		return profileCache.put(ro, profile);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ComponentProfileを削除します。
	 */
	public synchronized RTC.ComponentProfile removeRTCProfile(RTC.RTObject ro) {
		return profileCache.remove(ro);
	}

	/**
	 * RTC.RTObjectとポート名をキーに RTC.PortProfileを検索します。
	 */
	public RTC.PortProfile findRTCPortProfile(RTC.RTObject ro, String name) {
		RTC.ComponentProfile prof = profileCache.get(ro);
		if (prof == null) {
			return null;
		}
		for (RTC.PortProfile pprof : prof.port_profiles) {
			if (eql(name, pprof.name)) {
				return pprof;
			}
		}
		return null;
	}

	/**
	 * RTC.RTObjectとポート名をキーに RTC.PortProfileを保存します。
	 */
	public synchronized RTC.PortProfile registRTCPortProfile(RTC.RTObject ro,
			String name, RTC.PortProfile profile) {
		RTC.ComponentProfile prof = profileCache.get(ro);
		if (prof == null) {
			return null;
		}
		int index = -1;
		RTC.PortProfile old = null;
		for (int i = 0; i < prof.port_profiles.length; i++) {
			old = prof.port_profiles[i];
			if (eql(name, old.name)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			prof.port_profiles[index] = profile;
		}
		return old;
	}

	Map<String, RTC.ExecutionContext> getContextIdMap(RTC.RTObject ro) {
		Map<String, RTC.ExecutionContext> result = eContextIdMap.get(ro);
		if (result == null) {
			result = new HashMap<String, RTC.ExecutionContext>();
			eContextIdMap.put(ro, result);
		}
		return result;
	}

	/**
	 * RTC.RTObjectとコンテキストIDをキーに RTC.ExecutionContextを検索します。
	 */
	public RTC.ExecutionContext findContext(RTC.RTObject ro, String id) {
		Map<String, RTC.ExecutionContext> result = getContextIdMap(ro);
		return result.get(id);
	}

	/**
	 * RTC.RTObjectと RTC.ExecutionContextをキーにコンテキストIDを検索(逆引き)します。
	 */
	public String findContextId(RTC.RTObject ro, RTC.ExecutionContext ec) {
		Map<String, RTC.ExecutionContext> result = getContextIdMap(ro);
		for (String key : result.keySet()) {
			RTC.ExecutionContext e = result.get(key);
			if (eql(ec, e)) {
				return key;
			}
		}
		return null;
	}

	/**
	 * RTC.RTObjectとコンテキストIDをキーに RTC.ExecutionContextを保存します。
	 */
	public synchronized RTC.ExecutionContext registContext(RTC.RTObject ro,
			String id, RTC.ExecutionContext ec) {
		Map<String, RTC.ExecutionContext> result = getContextIdMap(ro);
		return result.put(id, ec);
	}

	/**
	 * RTC.RTObjectに関連する RTC.ExecutionContextをクリアします。
	 */
	public synchronized void clearContext(RTC.RTObject ro) {
		Map<String, RTC.ExecutionContext> result = getContextIdMap(ro);
		result.clear();
	}

	/**
	 * RTC.RTObjectをキーに RTC.ExecutionContextの一覧(owned)を検索します。
	 */
	public RTC.ExecutionContext[] findOwnedContexts(RTC.RTObject ro) {
		return eOwnedContextCache.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ExecutionContextの一覧(owned)を保存します。
	 */
	public synchronized RTC.ExecutionContext[] registOwnedContexts(
			RTC.RTObject ro, RTC.ExecutionContext[] ec) {
		return eOwnedContextCache.put(ro, ec);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ExecutionContextの一覧(owned)を削除します。
	 */
	public synchronized RTC.ExecutionContext[] removeOwnedContexts(
			RTC.RTObject ro) {
		return eOwnedContextCache.remove(ro);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ExecutionContextの一覧(participating)を検索します。
	 */
	public RTC.ExecutionContext[] findParticipatingContexts(RTC.RTObject ro) {
		return eParticipatingContextCache.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ExecutionContextの一覧(participating)を保存します。
	 */
	public synchronized RTC.ExecutionContext[] registParticipatingContexts(
			RTC.RTObject ro, RTC.ExecutionContext[] ec) {
		return eParticipatingContextCache.put(ro, ec);
	}

	/**
	 * RTC.RTObjectをキーに RTC.ExecutionContextの一覧(participating)を削除します。
	 */
	public synchronized RTC.ExecutionContext[] removeParticipatingContexts(
			RTC.RTObject ro) {
		return eParticipatingContextCache.remove(ro);
	}

	/**
	 * RTC.RTObjectをキーに SDO.ConfigurationSetの一覧を検索します。
	 */
	public _SDOPackage.ConfigurationSet[] findConfigSet(RTC.RTObject ro) {
		return configSetCache.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに SDO.ConfigurationSetの一覧を保存します。
	 */
	public synchronized _SDOPackage.ConfigurationSet[] registConfigSet(
			RTC.RTObject ro, _SDOPackage.ConfigurationSet[] cs) {
		return configSetCache.put(ro, cs);
	}

	/**
	 * RTC.RTObjectをキーに SDO.ConfigurationSetの一覧を削除します。
	 */
	public synchronized _SDOPackage.ConfigurationSet[] removeConfigSet(
			RTC.RTObject ro) {
		return configSetCache.remove(ro);
	}

	/**
	 * RTC.RTObjectをキーに SDO.ConfigurationSet(active)を検索します。
	 */
	public _SDOPackage.ConfigurationSet findActiveConfigSet(RTC.RTObject ro) {
		return activeConfigSetCache.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに SDO.ConfigurationSet(active)を保存します。
	 */
	public synchronized _SDOPackage.ConfigurationSet registActiveConfigSet(
			RTC.RTObject ro, _SDOPackage.ConfigurationSet cs) {
		return activeConfigSetCache.put(ro, cs);
	}

	/**
	 * RTC.RTObjectをキーに SDO.ConfigurationSet(active)を削除します。
	 */
	public synchronized _SDOPackage.ConfigurationSet removeActiveConfigSet(
			RTC.RTObject ro) {
		return activeConfigSetCache.remove(ro);
	}

	/**
	 * RTC.RTObjectをキーに複合RTCのメンバ一覧(RTC.RTObject)を検索します。
	 */
	public List<RTC.RTObject> getCompositeMemberList(RTC.RTObject ro) {
		List<RTC.RTObject> result = compositeMemberList.get(ro);
		if (result == null) {
			result = new ArrayList<RTC.RTObject>();
			compositeMemberList.put(ro, result);
		}
		return result;
	}

	/**
	 * RTC.RTObjectをキーに複合RTCのメンバ一覧(RTC.RTObject)を削除します。
	 */
	public synchronized List<RTC.RTObject> removeCompositeMemberList(
			RTC.RTObject ro) {
		return compositeMemberList.remove(ro);
	}

	/**
	 * RTC.ExecutionContextをキーに RTC.ExecutionContextProfileを検索します。
	 */
	public RTC.ExecutionContextProfile findECProfile(RTC.ExecutionContext ec) {
		return eContextProfileCache.get(ec);
	}

	/**
	 * RTC.ExecutionContextをキーに RTC.ExecutionContextProfileを保存します。
	 */
	public synchronized RTC.ExecutionContextProfile registECProfile(
			RTC.ExecutionContext ec, RTC.ExecutionContextProfile profile) {
		return eContextProfileCache.put(ec, profile);
	}

	/**
	 * RTC.ExecutionContextをキーに RTC.ExecutionContextProfileを削除します。
	 */
	public synchronized RTC.ExecutionContextProfile removeECProfile(
			RTC.ExecutionContext ec) {
		return eContextProfileCache.remove(ec);
	}

	/**
	 * RTC.ExecutionContextをキーにコンテキストの状態を検索します。
	 */
	public Integer findECState(RTC.ExecutionContext ec) {
		return eContextStateCache.get(ec);
	}

	/**
	 * RTC.ExecutionContextをキーにコンテキストの状態を保存します。
	 */
	public synchronized Integer registECState(RTC.ExecutionContext ec,
			Integer state) {
		return eContextStateCache.put(ec, state);
	}

	/**
	 * RTC.ExecutionContextをキーにコンテキストの状態を削除します。
	 */
	public synchronized Integer removeECState(RTC.ExecutionContext ec) {
		return eContextStateCache.remove(ec);
	}

	Map<RTC.RTObject, Integer> getComponentStateMap(RTC.ExecutionContext ec) {
		Map<RTC.RTObject, Integer> result = eContextCompStateMap.get(ec);
		if (result == null) {
			result = new HashMap<RTC.RTObject, Integer>();
			eContextCompStateMap.put(ec, result);
		}
		return result;
	}

	/**
	 * RTC.ExecutionContextと RTC.RTObjectをキーにRTCの状態を検索します。
	 */
	public Integer findComponentState(RTC.ExecutionContext ec, RTC.RTObject ro) {
		Map<RTC.RTObject, Integer> result = getComponentStateMap(ec);
		return result.get(ro);
	}

	/**
	 * RTC.ExecutionContextと RTC.RTObjectをキーにRTCの状態を保存します。
	 */
	public synchronized Integer registComponentState(RTC.ExecutionContext ec,
			RTC.RTObject ro, Integer state) {
		Map<RTC.RTObject, Integer> result = getComponentStateMap(ec);
		return result.put(ro, state);
	}

	/**
	 * RTC.ExecutionContextをキーにRTCの状態一覧を削除します。
	 */
	public synchronized void removeComponentStateMap(RTC.ExecutionContext ec) {
		eContextCompStateMap.remove(ec);
	}

}
