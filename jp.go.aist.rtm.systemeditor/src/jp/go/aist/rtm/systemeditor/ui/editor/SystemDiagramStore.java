package jp.go.aist.rtm.systemeditor.ui.editor;

import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;

/**
 * ダイアグラムに対する表示制御などの設定値を格納します。<br>
 * ダイアグラム別にリソースを区別し、また、 EMFの通知機構を利用する/しないを制御可能にします。
 */
public class SystemDiagramStore {

	/** ECタブの表示切替ID */
	public static final int ID_DISPLAY_EC_TAB = 1;
	public static EAttribute F_DISPLAY_EC_TAB;

	/** ECコネクションの表示切替ID */
	public static final int ID_DISPLAY_EC_CONN = 2;
	public static EAttribute F_DISPLAY_EC_CONN;

	/** ECコネクションのベンドポイント更新ID */
	public static final int ID_BENDPOINT_EC_CONN = 3;
	public static EAttribute F_BENDPOINT_EC_CONN;

	/** ECコネクションのプールへのキー */
	public static final String KEY_EC_CONN_MAP = "EC_CONN_MAP";

	/** ECコネクションのベンドポイントリストへのキー */
	public static final String KEY_EC_CONN_BENDPOINT_MAP = "EC_CONN_BENDPOINT_MAP";

	private static Map<SystemDiagram, SystemDiagramStore> instances;
	private static Map<Integer, EAttribute> features;

	private Map<String, Map<String, Target>> targetMap = new HashMap<>();

	/**
	 * ダイアグラム別のリソース/EMF通知機構のストアを取得します。
	 * 
	 * @param diagram
	 * @return
	 */
	public static SystemDiagramStore instance(SystemDiagram diagram) {
		if (instances == null) {
			init();
		}
		SystemDiagramStore store = instances.get(diagram);
		if (store == null) {
			store = new SystemDiagramStore();
			instances.put(diagram, store);
		}
		return store;
	}

	static void init() {
		instances = new HashMap<>();
		features = new HashMap<>();
		//
		F_DISPLAY_EC_TAB = registEAttribute(ID_DISPLAY_EC_TAB);
		F_DISPLAY_EC_CONN = registEAttribute(ID_DISPLAY_EC_CONN);
		F_BENDPOINT_EC_CONN = registEAttribute(ID_BENDPOINT_EC_CONN);
	}

	static EAttribute registEAttribute(int id) {
		EcoreFactoryImpl factory = new EcoreFactoryImpl();
		EAttributeImpl ret = (EAttributeImpl) factory.createEAttribute();
		ret.setFeatureID(id);
		features.put(id, ret);
		return ret;
	}

	/**
	 * ダイアグラムのデフォルトのリソース/EMF通知機構のターゲットを取得します。
	 * 
	 * @return
	 */
	public Target getTarget() {
		return getTarget("diagram", "default");
	}

	/**
	 * リソース/EMF通知機構の種別、および IDを指定してターゲットを取得します。
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	public Target getTarget(String type, String id) {
		Map<String, Target> map = this.targetMap.get(type);
		if (map == null) {
			map = new HashMap<>();
			this.targetMap.put(type, map);
		}
		Target ret = map.get(id);
		if (ret == null) {
			ret = new Target();
			map.put(id, ret);
		}
		return ret;
	}

	/**
	 * リソース/EMF通知機構の種別、および IDを指定してターゲットを削除します。
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	public Target removeTarget(String type, String id) {
		Map<String, Target> map = this.targetMap.get(type);
		if (map == null) {
			return null;
		}
		return map.remove(id);
	}

	/**
	 * リソース/EMF通知機構のターゲットを表します。
	 */
	public static class Target extends WrapperObjectImpl implements Notifier {

		private Map<Integer, String> prop = new HashMap<>();
		private Map<String, Object> resourceMap = new HashMap<>();

		/**
		 * ダイアグラムに対する設定値を取得します。
		 * 
		 * @param key
		 * @return
		 */
		public String get(int key) {
			return this.prop.get(key);
		}

		/**
		 * ダイアグラムに対する設定値を格納します。<br>
		 * EMF通知対象のリソースの場合は変更通知を発行します。
		 * 
		 * @param key
		 * @param value
		 */
		public void set(int key, String value) {
			String oldValue = this.prop.get(key);
			this.prop.put(key, value);
			//
			EAttribute a = features.get(key);
			if (a != null) {
				Notification notification = new ENotificationImpl(this, Notification.SET, a, oldValue, value);
				eNotify(notification);
			}
		}

		/**
		 * ダイアグラムに対する任意のリソース(オブジェクト)を取得します。
		 * 
		 * @param key
		 * @return
		 */
		public Object getResource(String key) {
			return this.resourceMap.get(key);
		}

		/**
		 * ダイアグラムに対する任意のリソース(オブジェクト)を格納します。
		 * 
		 * @param key
		 * @param resource
		 */
		public void putResource(String key, Object resource) {
			this.resourceMap.put(key, resource);
		}

		// 以下、EMF通知機構

		private boolean doDeliver = true;
		private EList<Adapter> adapters = new BasicEList<>();

		/**
		 * EMF通知機構にアダプタを追加/削除する際に利用します。
		 */
		@Override
		public EList<Adapter> eAdapters() {
			return this.adapters;
		}

		@Override
		public boolean eDeliver() {
			return this.doDeliver;
		}

		@Override
		public void eSetDeliver(boolean deliver) {
			this.doDeliver = deliver;
		}

		@Override
		public void eNotify(Notification notification) {
			if (!eDeliver()) {
				return;
			}
			for (Adapter adapter : this.adapters) {
				adapter.notifyChanged(notification);
			}
		}

	}

}
