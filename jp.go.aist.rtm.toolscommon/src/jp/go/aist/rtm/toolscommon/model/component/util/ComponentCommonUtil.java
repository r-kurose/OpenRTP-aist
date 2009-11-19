package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;

public class ComponentCommonUtil {

	/** ports以外のポートとの接続を持つポートを抽出 */
	static List<Port> getConnectedPorts(List<Port> ports) {
		List<Port> result = new ArrayList<Port>();
		if (ports.isEmpty())
			return result;
		List<String> origs = new ArrayList<String>();
		for (Port port : ports) {
			origs.add(port.getOriginalPortString());
		}
		for (Port port : ports) {
			for (Object o : port.getConnectorProfiles()) {
				if (!(o instanceof ConnectorProfile)) {
					continue;
				}
				if (isConnectedOuterPort(origs, (ConnectorProfile)o)){
					result.add(port);
					break;
				}
			}
		}
		return result;
	}

	private static boolean isConnectedOuterPort(List<String> origs, ConnectorProfile prof) {
		if (!origs.contains(prof.getSourceString())) return true;
		if (!origs.contains(prof.getTargetString())) return true;
		return false;
	}

	/** RTC一覧から、複合RTCの公開必須のポート設定を取得 */
	public static List<String> getRequiredExportedPorts(
			List<Component> components) {
		List<String> result = new ArrayList<String>();
		List<Port> allPorts = new ArrayList<Port>();
		for (Component c : components) {
			for (Object o : c.getPorts()) {
				allPorts.add((Port) o);
			}
		}
		List<Port> ports = getConnectedPorts(allPorts);
		for (Component c : components) {
			for (Object o : c.getPorts()) {
				Port port = (Port) o;
				if (ports.contains(port)) {
					String s = c.getInstanceNameL() + "."
							+ port.getNameL();
					result.add(s);
				}
			}
		}
		return result;
	}

}
