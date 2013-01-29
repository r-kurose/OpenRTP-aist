package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;

public class CorbaObserverStore {

	public static CorbaObserverStore eINSTANCE = new CorbaObserverStore();

	// RTC.RTObj => ComponentList
	Map<RTC.RTObject, ComponentList> compReferenceMap;
	// RTC.RTObj => CorbaStatusObserver
	Map<RTC.RTObject, CorbaStatusObserver> statusObserverMap;
	// RTC.RTObj => CorbaLogObserver
	Map<RTC.RTObject, CorbaLogObserver> logObserverMap;

	public CorbaObserverStore() {
		this.compReferenceMap = new HashMap<RTC.RTObject, ComponentList>();
		this.statusObserverMap = new HashMap<RTC.RTObject, CorbaStatusObserver>();
		this.logObserverMap = new HashMap<RTC.RTObject, CorbaLogObserver>();
	}

	/**
	 * RTC.RTObjectを参照する CorbaComponentを追加します。
	 */
	public void addComponentReference(RTC.RTObject ro, CorbaComponent comp) {
		ComponentList list = compReferenceMap.get(ro);
		if (list == null) {
			list = new ComponentList();
			compReferenceMap.put(ro, list);
		}
		list.add(comp);
	}

	public void addComponentReference(CorbaComponent comp) {
		addComponentReference(comp.getCorbaObjectInterface(), comp);
	}

	/**
	 * RTC.RTObjectを参照する CorbaComponentを削除します。
	 */
	public void removeComponentReference(RTC.RTObject ro, CorbaComponent comp) {
		ComponentList list = compReferenceMap.get(ro);
		if (list == null) {
			return;
		}
		list.remove(comp);
	}

	public void removeComponentReference(CorbaComponent comp) {
		removeComponentReference(comp.getCorbaObjectInterface(), comp);
	}

	/**
	 * RTC.RTObjectを参照する CorbaComponentの一覧を取得します。
	 */
	public ComponentList findComponentReferenceList(RTC.RTObject ro) {
		ComponentList result = compReferenceMap.get(ro);
		if (result == null) {
			result = new ComponentList();
		}
		return result;
	}

	/**
	 * RTC.RTObjectを参照する CorbaComponentが存在しない場合はtrue
	 */
	public boolean isEmptyComponentReference(RTC.RTObject ro) {
		ComponentList list = compReferenceMap.get(ro);
		if (list == null) {
			return true;
		}
		return list.isEmpty();
	}

	/**
	 * RTC.RTObjectをキーに CorbaStatusObserverを検索します。
	 */
	public CorbaStatusObserver findStatusObserver(RTC.RTObject ro) {
		return statusObserverMap.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに CorbaStatusObserverを保存します。
	 */
	public synchronized CorbaStatusObserver registStatusObserver(
			RTC.RTObject ro, CorbaStatusObserver observer) {
		return statusObserverMap.put(ro, observer);
	}

	/**
	 * RTC.RTObjectをキーに CorbaStatusObserverを削除します。
	 */
	public synchronized CorbaStatusObserver removeStatusObserver(RTC.RTObject ro) {
		return statusObserverMap.remove(ro);
	}

	/**
	 * RTC.RTObjectをキーに CorbaLogObserverを検索します。
	 */
	public CorbaLogObserver findLogObserver(RTC.RTObject ro) {
		return logObserverMap.get(ro);
	}

	/**
	 * RTC.RTObjectをキーに CorbaLogObserverを保存します。
	 */
	public synchronized CorbaLogObserver registLogObserver(RTC.RTObject ro,
			CorbaLogObserver observer) {
		return logObserverMap.put(ro, observer);
	}

	/**
	 * RTC.RTObjectをキーに CorbaLogObserverを削除します。
	 */
	public synchronized CorbaLogObserver removeLogObserver(RTC.RTObject ro) {
		return logObserverMap.remove(ro);
	}

	public static class ComponentList {
		List<CorbaComponent> components;

		ComponentList() {
			this.components = new ArrayList<CorbaComponent>();
		}

		public CorbaComponent get(int index) {
			return components.get(index);
		}

		public boolean add(CorbaComponent component) {
			if (contain(component)) {
				return true;
			}
			return components.add(component);
		}

		public boolean contain(CorbaComponent component) {
			for (CorbaComponent comp : components) {
				if (comp == component) {
					return true;
				}
			}
			return false;
		}

		public boolean isEmpty() {
			return components.isEmpty();
		}

		public CorbaComponent remove(CorbaComponent component) {
			int index = -1;
			for (int i = 0; i < components.size(); i++) {
				if (components.get(i) == component) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				return components.remove(index);
			}
			return null;
		}
	}

}
