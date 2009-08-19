package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

/**
 * 選択されたノードに適したビューに切り替えるためのクラス
 *
 */
public class OpenView {
	private static final String EXTENTION_POINT_NAME = "openViews";

	@SuppressWarnings("unchecked")
//	private static Map<Class, String> viewMap;
	private static Map<Class, Map<String, String>> viewMap;

	@SuppressWarnings("unchecked")
	public static String getViewId(Object obj) {
		return getViewId(obj, null);
	}

	/**
	 * @param obj	選択されたオブジェクト
	 * @param kind	選択されたオブジェクト用のビューが複数ある場合の識別子
	 * @return		選択されたオブジェクトの情報を表示させるビューのID
	 */
	@SuppressWarnings("unchecked")
	public static String getViewId(Object obj, String kind) {
		if (viewMap == null) {
			buildViewMap();
		}
//		for (Class cl : viewMap.keySet()) {
//			Object adp = AdapterUtil.getAdapter(obj, cl);
//			if (adp != null) {
//				return viewMap.get(cl);
//			}
//		}
		for (Class cl : viewMap.keySet()) {
			Object adp = AdapterUtil.getAdapter(obj, cl);
			if (adp == null) {
				continue;
			}
			Map<String, String> m = viewMap.get(cl);
			if (m == null) {
				return null;
			}
			String viewId = null;
			if (kind != null) {
				viewId = m.get(kind);
			}
			if (viewId == null) {
				viewId = m.get("default");
			}
			return viewId;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static void buildViewMap() {		
//		// 拡張ポイントがNULLとなったときは、「-clean」オプションで起動，
//		もしくはworkspaceを切り替えて起動を行うことで対処する　2009.01.24

		viewMap = new HashMap<Class, Map<String, String>>();
		String ns = ToolsCommonPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				String key = ce.getAttribute("class");
				String viewId = ce.getAttribute("view_id");
				String kind = ce.getAttribute("kind");
				Class keyClass;
				try {
					keyClass = Platform.getBundle(
							ex.getNamespaceIdentifier()).loadClass(key);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				Map<String, String> m = viewMap.get(keyClass);
				if (m == null) {
					m = new HashMap<String, String>();
				}
				if (kind != null) {
					m.put(kind, viewId);
				} else {
					m.put("default", viewId);
				}
				viewMap.put(keyClass, m);
			}
		}
	}
}
