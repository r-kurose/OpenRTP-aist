package jp.go.aist.rtm.systemeditor.ui.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.extension.LoadProfileExtension;
import jp.go.aist.rtm.systemeditor.extension.SaveProfileExtension;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.profiles.util.JSONUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.swt.events.SelectionAdapter;
import org.openrtp.namespaces.rts.version02.Property;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ダイアグラムに対する表示制御などの設定値を格納します。<br>
 * ダイアグラム別にリソースを区別し、また、 EMFの通知機構を利用する/しないを制御可能にします。
 */
public class SystemDiagramStore {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemDiagramStore.class);

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
		Map<String, Target> map = getTargetMap(type);
		Target ret = map.get(id);
		if (ret == null) {
			ret = new Target();
			map.put(id, ret);
		}
		return ret;
	}

	/**
	 * リソース/EMF通知機構の種別に対するターゲットのマップを取得します。
	 * 
	 * @param type
	 * @return
	 */
	public Map<String, Target> getTargetMap(String type) {
		Map<String, Target> ret = this.targetMap.get(type);
		if (ret == null) {
			ret = new HashMap<>();
			this.targetMap.put(type, ret);
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

	/** [プロファイル] ECタブの表示切替 */
	public static String PROP_ECTAB_SHOW = "rtse.ectab.show";
	/** [プロファイル] EC関連の表示切替 */
	public static String PROP_ECCONN_SHOW = "rtse.ecconn.show";
	/** [プロファイル] EC関連のベンドポイント設定 */
	public static String PROP_ECCONN_BENDPOINTS = "rtse.ecconn.bendpoints";

	/**
	 * ダイアグラム別リソースのプロファイル保存のための拡張
	 */
	public static class SaveProfile extends SaveProfileExtension {

		@Override
		public String getName() {
			return null;
		}

		@Override
		public SelectionAdapter getListener(SystemDiagram sd) {
			return null;
		}

		@Override
		public ErrorInfo validate(SystemDiagram sd) {
			return null;
		}

		@Override
		public ErrorInfo prepareSave(SystemDiagram sd, String vendor, String name, String version, String path) {
			return null;
		}

		@Override
		public ErrorInfo preSave(SystemDiagram sd, RtsProfileExt profile) {
			LOGGER.info("preSave sd={} profile={}", sd, profile);
			SystemDiagramStore store = SystemDiagramStore.instance(sd);
			{
				// ECタブの表示切替
				String value = store.getTarget().get(SystemDiagramStore.ID_DISPLAY_EC_TAB);
				addProfileProperty(profile.getProperties(), PROP_ECTAB_SHOW, value);
			}
			{
				// EC関連の表示切替
				String value = store.getTarget().get(SystemDiagramStore.ID_DISPLAY_EC_CONN);
				addProfileProperty(profile.getProperties(), PROP_ECCONN_SHOW, value);
			}
			{
				// EC関連のベンドポイントの保存
				Map<String, SystemDiagramStore.Target> map = store.getTargetMap("ECConnection");
				Map<String, Map<String, String>> bendpoints = new HashMap<>();
				for (Map.Entry<String, SystemDiagramStore.Target> entry : map.entrySet()) {
					String ecconnId = entry.getKey();
					SystemDiagramStore.Target target = entry.getValue();
					{
						@SuppressWarnings("unchecked")
						Map<Integer, Point> r = (Map<Integer, Point>) target
								.getResource(SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP);
						Map<String, String> bp = new HashMap<>();
						for (Map.Entry<Integer, Point> re : r.entrySet()) {
							Integer idx = re.getKey();
							Point point = re.getValue();
							bp.put(idx.toString(), String.format("%d:%d", point.x, point.y));
						}
						bendpoints.put(ecconnId, bp);
					}
				}
				String val = JSONUtil.toJSON(bendpoints);
				val = val.replaceAll("\r\n", "");
				//
				addProfileProperty(profile.getProperties(), PROP_ECCONN_BENDPOINTS, val);
			}
			return null;
		}

		@Override
		public ErrorInfo postSave(SystemDiagram sd, IFile file) {
			LOGGER.info("postSave sd={} file={}", sd, file);
			return null;
		}

	}

	/**
	 * ダイアグラム別リソースのプロファイル読込のための拡張
	 */
	public static class LoadProfile extends LoadProfileExtension {

		@Override
		public ErrorInfo preLoad(RtsProfileExt profile, String path) {
			LOGGER.info("preLoad profile={} path={}", profile, path);
			return null;
		}

		@Override
		public ErrorInfo postLoad(SystemDiagram sd, RtsProfileExt profile, SystemDiagram oldSd) {
			LOGGER.info("postLoad sd={} profile={}", sd, profile);
			SystemDiagramStore store = SystemDiagramStore.instance(sd);
			//
			boolean dispEC = false;
			boolean dispECConn = false;
			//
			for (Property prop : profile.getProperties()) {
				if (PROP_ECTAB_SHOW.equals(prop.getName())) {
					// ECタブの表示切替
					dispEC = "true".equals(prop.getValue());
				} else if (PROP_ECCONN_SHOW.equals(prop.getName())) {
					// EC関連の表示切替
					dispECConn = "true".equals(prop.getValue());
				} else if (PROP_ECCONN_BENDPOINTS.equals(prop.getName())) {
					// EC関連のベンドポイントの読込
					@SuppressWarnings("unchecked")
					Map<String, Map<String, String>> bendpoints = JSONUtil.parse(Map.class, prop.getValue());
					for (Map.Entry<String, Map<String, String>> val : bendpoints.entrySet()) {
						String ecconnId = val.getKey();
						Map<String, String> bpMap = val.getValue();
						Map<Integer, Point> bp = new HashMap<>();
						for (Map.Entry<String, String> bpEnt : bpMap.entrySet()) {
							int idx = Integer.parseInt(bpEnt.getKey());
							Point p = null;
							String v = bpEnt.getValue();
							if (v != null && !v.isEmpty()) {
								String[] vv = v.split(":");
								if (vv.length >= 2) {
									p = new Point(Integer.parseInt(vv[0]), Integer.parseInt(vv[1]));
								}
							}
							if (p != null) {
								bp.put(new Integer(idx), p);
							}
						}
						store.getTarget("ECConnection", ecconnId).putResource(KEY_EC_CONN_BENDPOINT_MAP, bp);
					}
				}
			}
			return null;
		}

	}

	/** プロファイルのプロパティを設定します。 */
	static void addProfileProperty(List<Property> props, String name, String value) {
		for (Property prop : props) {
			if (name != null && name.equals(prop.getName())) {
				prop.setValue(value);
				return;
			}
		}
		Property prop = new Property();
		prop.setName(name);
		prop.setValue(value);
		props.add(prop);
	}

}
