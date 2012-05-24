package jp.go.aist.rtm.toolscommon.util;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.Port;

public class ComponentUtil {

	/**
	 * ポート名を正規化("RTC名"."ポート名" 形式)します。(オフライン)
	 */
	public static String createPortName(String insName, String portName) {
		String result = (portName == null) ? "" : portName;
		if (!result.startsWith(insName)) {
			String[] ns = result.split("\\.");
			result = insName + "." + ns[ns.length - 1];
		}
		return result;
	}

	/**
	 * コンポーネントの各ポート名を整えます。
	 */
	public static Component trimPortNames(Component comp) {
		for (Port port : comp.getPorts()) {
			String name = createPortName(comp.getInstanceNameL(), port
					.getNameL());
			port.setNameL(name);
		}
		return comp;
	}

	/**
	 * コンポーネントを検索します。
	 * 
	 * @param comp
	 *            検索対象のコンポーネント
	 * @param comps
	 *            コンポーネントリスト
	 * @return オンラインの場合はCORBAオブジェクトが一致、オフラインの場合はインスタンス名が一致するもの
	 */
	public static Component find(Component comp, List<Component> comps) {
		if (comp instanceof CorbaComponent) {
			return find((CorbaComponent) comp, comps);
		}
		return find(comp.getInstanceNameL(), comps);
	}

	/**
	 * コンポーネントを検索します。
	 * 
	 * @param comp
	 *            検索対象のコンポーネント (オンライン)
	 * @param comps
	 *            コンポーネントリスト
	 * @return CORBAオブジェクトが一致するコンポーネント
	 */
	public static CorbaComponent find(CorbaComponent comp, List<Component> comps) {
		if (comp == null || comps == null) {
			return null;
		}
		for (Component c : comps) {
			if (!(c instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent cc = (CorbaComponent) c;
			if (cc.getCorbaObject() != null
					&& cc.getCorbaObject().equals(comp.getCorbaObject())) {
				return cc;
			}
		}
		return null;
	}

	/**
	 * コンポーネントを検索します。
	 * 
	 * @param name
	 *            検索対象のコンポーネント名
	 * @param comps
	 *            コンポーネントリスト
	 * @return インスタンス名が一致するもの
	 */
	public static Component find(String name, List<Component> comps) {
		if (name == null || comps == null) {
			return null;
		}
		for (Component c : comps) {
			if (name.equals(c.getInstanceNameL())) {
				return c;
			}
		}
		return null;
	}

}
