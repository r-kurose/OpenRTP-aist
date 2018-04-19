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
public class SystemDiagramStore extends WrapperObjectImpl implements Notifier {

	/** ECタブの表示切替ID */
	public static final int ID_DISPLAY_EC_TAB = 1;
	public static EAttribute F_DISPLAY_EC_TAB;

	/** ECコネクションの表示切替ID */
	public static final int ID_DISPLAY_EC_CONN = 2;
	public static EAttribute F_DISPLAY_EC_CONN;

	/** ECコネクションのプールへのキー */
	public static final String KEY_EC_CONN_MAP = "EC_CONN_MAP";

	private static Map<SystemDiagram, SystemDiagramStore> instances;
	private static Map<Integer, EAttribute> features;

	private Map<Integer, String> prop = new HashMap<>();
	private Map<String, Object> resourceMap = new HashMap<>();

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
		EcoreFactoryImpl factory = new EcoreFactoryImpl();
		{
			EAttributeImpl a = (EAttributeImpl) factory.createEAttribute();
			a.setFeatureID(ID_DISPLAY_EC_TAB);
			F_DISPLAY_EC_TAB = a;
			features.put(ID_DISPLAY_EC_TAB, F_DISPLAY_EC_TAB);
		}
		{
			EAttributeImpl a = (EAttributeImpl) factory.createEAttribute();
			a.setFeatureID(ID_DISPLAY_EC_CONN);
			F_DISPLAY_EC_CONN = a;
			features.put(ID_DISPLAY_EC_CONN, F_DISPLAY_EC_CONN);
		}
	}

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
		EAttribute a = features.get(ID_DISPLAY_EC_TAB);
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
