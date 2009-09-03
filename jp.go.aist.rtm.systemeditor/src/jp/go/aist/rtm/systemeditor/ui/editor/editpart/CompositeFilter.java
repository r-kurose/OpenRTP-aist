package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;

/**
 * 複合コンポーネント関係でEditPartに表示するオブジェクトを制御する
 *
 */
public class CompositeFilter {

	/**
	 * 指定したポート宛のコネクタのリストを返す
	 * @param port	描画対象のポート
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PortConnector> getModelTargetConnections(Port port) {
		return getConnections(null, port);
	}

	@SuppressWarnings("unchecked")
	private static Port findPort(List components, String originalString) {
		for (Object obj: components) {
			for(Object element : ((Component)obj).getPorts()) {
				Port port = (Port) element;
				if (port.getOriginalPortString().equals(originalString)) {
					return port;
				}
			}
		}
		return null;	// Component がExitしたときなど
	}

	private static SystemDiagram getDiagram(Port port) {
		if (port == null) return null;
		if (port.eContainer() == null) return null;
		return (SystemDiagram) port.eContainer().eContainer();
	}

	/**
	 * 指定したポート発のコネクタのリストを返す
	 * @param port	描画対象のポート
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<PortConnector> getModelSourceConnections(Port port) {
		return getConnections(port, null);
	}

	@SuppressWarnings("unchecked")
	private static List<PortConnector> getConnections(Port first, Port second) {
		Port port = first != null ? first : second;

		List<PortConnector> result = new ArrayList<PortConnector>();

		SystemDiagram diagram = getDiagram(port);
		if (diagram == null) return result;
		
		List components = diagram.getComponents();
		if (!(components.contains(port.eContainer())))return result;

		String originalPortString = port.getOriginalPortString();
		for(Object obj : port.getConnectorProfiles()) {
			ConnectorProfile profile = (ConnectorProfile) obj;
			String tmpString = (first != null ? profile.getSourceString() : profile.getTargetString());
			if (!(tmpString.equals(originalPortString))) continue;
			
			String anotherString = (first != null ? profile.getTargetString() : profile.getSourceString());
			Port anotherPort = findPort(components, anotherString);
			if (anotherPort == null) continue;
			
			PortConnector connector = findOrCreateConnector(port, profile);
			if (connector == null) continue;
			
			connector.setSource(first != null ? port : anotherPort);
			connector.setTarget(first != null ? anotherPort : port);
			
			result.add(connector);
//			return result;
		}
		return result;
	}

	private static PortConnector findOrCreateConnector(Port port,
			ConnectorProfile profile) {
		SystemDiagram diagram = getDiagram(port);
		if (diagram == null) return null;
		
//		Map<String, PortConnector> connectorMap = diagram.getRootDiagram().getConnectorMap();
		// 複数のダイアグラムで同じコネクタIDのコネクタが存在する場合があるので、ダイアグラムごとに管理 2009.05.25
		Map<String, PortConnector> connectorMap = diagram.getConnectorMap();
		PortConnector connector = connectorMap.get(profile.getConnectorId());
		if (connector != null) return connector;
		
		connector = PortConnectorFactory.createPortConnector(profile);
		connector.setConnectorProfile(profile);
		connectorMap.put(profile.getConnectorId(), connector);

		// 子ダイアグラムのコネクタのベンドポイントが保存されるように、ルートダイアグラムにも極力設定
		Map<String, PortConnector> rootConnectorMap = diagram.getRootDiagram().getConnectorMap();
		if (!rootConnectorMap.containsKey(profile.getConnectorId())) {
			rootConnectorMap.put(profile.getConnectorId(), connector);
		}
		return connector;
	}
}
