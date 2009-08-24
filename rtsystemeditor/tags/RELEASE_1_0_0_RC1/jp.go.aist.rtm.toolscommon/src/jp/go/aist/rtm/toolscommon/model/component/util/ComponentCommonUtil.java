package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
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
		if (ports.get(0).getSynchronizer() instanceof CorbaPortSynchronizer) {
			// オンライン			
			for (Port port : ports) {
				for (Object o : port.getConnectorProfiles()) {
					if (!(o instanceof CorbaConnectorProfile)) {
						continue;
					}
					CorbaConnectorProfile prof = (CorbaConnectorProfile) o;
					if (prof.getRtcConnectorProfile().ports.length < 2) {
						continue;
					}
					String s = port.getSynchronizer().getOriginalPortString();
					RTC.PortService sps = prof.getRtcConnectorProfile().ports[0];
					RTC.PortService tps = prof.getRtcConnectorProfile().ports[1];
					if (s.equals(sps.toString())) {
						if (!origs.contains(tps.toString())) {
							result.add(port);
							break;
						}
					}
					if (s.equals(tps.toString())) {
						if (!origs.contains(sps.toString())) {
							result.add(port);
							break;
						}
					}
				}
			}
		} else {
			// オフライン
		}
		return result;
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
