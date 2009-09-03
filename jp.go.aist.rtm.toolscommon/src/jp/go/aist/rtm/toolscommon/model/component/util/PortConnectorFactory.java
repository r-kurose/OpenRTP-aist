package jp.go.aist.rtm.toolscommon.model.component.util;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortConnectorImpl;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorSpecificationImpl;

/**
 * PortConnectorのファクトリクラス
 *
 */
public class PortConnectorFactory {

	/**
	 * @return	オフライン用のPortConnector
	 */
	public static PortConnector createPortConnectorSpecification() {
		return new PortConnectorSpecificationImpl();
	}

	private static PortConnector createCorbaPortConnector() {
		return new CorbaPortConnectorImpl();
	}

	/**
	 * @param profile
	 * @return	コネクタプロファイルに即したPortConnector
	 */
	public static PortConnector createPortConnector(ConnectorProfile profile) {
		if (profile instanceof CorbaConnectorProfile) {
			return createCorbaPortConnector();
		} else {
			return createPortConnectorSpecification();
		}
	}

	/**
	 * @param corbaObjectInterface
	 * @return オンラインかどうかに即したPortConnector
	 * 現状はオンラインの場合はCORBA固定
	 */
	public static PortConnector createPortConnector(boolean online) {
		if (online) {
			return createCorbaPortConnector();
		} else {
			return createPortConnectorSpecification();
		}
	}

	/**
	 * @param port	コネクタの接続元または接続先となるポート
	 * @return　　　　　ポートに即したコネクタ（現状はCORBAオブジェクトを持つかどうかだけで判断）
	 */
	public static PortConnector createPortConnector(Port port) {
		return createPortConnector(getCorbaObjectInterface(port) != null);
	}

	private static Object getCorbaObjectInterface(Port port) {
		PortSynchronizer synchronizer = port.getSynchronizer();
		if (!(synchronizer instanceof CorbaPortSynchronizer)) return null;
		return ((CorbaPortSynchronizer)synchronizer).getCorbaObjectInterface();
	}

}
