package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.extension.PortConnectorFactoryExtension;
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

	static final String EXTENTION_POINT_NAME = "portconnectorfactory";
	static List<PortConnectorFactoryExtension> portConnectorFactories;

	/**
	 * @return オフライン用のPortConnector
	 */
	public static PortConnector createPortConnectorSpecification() {
		return new PortConnectorSpecificationImpl();
	}

	private static PortConnector createCorbaPortConnector() {
		return new CorbaPortConnectorImpl();
	}

	/**
	 * @param source
	 *            コネクタの接続元となるポート
	 * @param target
	 *            コネクタの接続先となるポート
	 * @return ポートに即したコネクタ（拡張可。デフォルトはCORBAオブジェクトを持つかどうかだけで判断）
	 */
	public static PortConnector createPortConnector(Port source, Port target) {
		if (portConnectorFactories == null) {
			buildPortConnectorFactory();
		}
		PortConnectorFactoryExtension factory = null;
		for (PortConnectorFactoryExtension ext : portConnectorFactories) {
			ext.setSource(source);
			ext.setTarget(target);
			if (!ext.canCreate()) {
				continue;
			}
			factory = ext;
			break;
		}
		return factory.createPortConnector();
	}

	static void buildPortConnectorFactory() {
		portConnectorFactories = new ArrayList<PortConnectorFactoryExtension>();
		//
		String ns = ToolsCommonPlugin.class.getPackage().getName();
		if (Platform.getExtensionRegistry() != null) {
			IExtension[] extensions = Platform.getExtensionRegistry()
					.getExtensionPoint(ns, EXTENTION_POINT_NAME)
					.getExtensions();
			for (IExtension ex : extensions) {
				for (IConfigurationElement ce : ex.getConfigurationElements()) {
					Object obj;
					try {
						obj = ce.createExecutableExtension("extensionclass");
						if (obj instanceof PortConnectorFactoryExtension) {
							portConnectorFactories
									.add((PortConnectorFactoryExtension) obj);
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		// デフォルトのファクトリ
		PortConnectorFactoryExtension ext = createDefaultExtension();
		portConnectorFactories.add(ext);
	}

	public static List<PortConnectorFactoryExtension> getExtensions() {
		return portConnectorFactories;
	}

	public static PortConnectorFactoryExtension createDefaultExtension() {
		return new PortConnectorFactoryExtension() {
			@Override
			public boolean canCreate() {
				return true;
			}

			@Override
			public PortConnector createPortConnector() {
				PortConnector result = null;
				if (getCorbaObjectInterface(source) == null) {
					result = createPortConnectorSpecification();
				} else {
					result = createCorbaPortConnector();
				}
				if (result == null) {
					return null;
				}
				result.setSource(source);
				result.setTarget(target);
				return result;
			}

			Object getCorbaObjectInterface(Port port) {
				PortSynchronizer sync = port.getSynchronizer();
				if (!(sync instanceof CorbaPortSynchronizer)) {
					return null;
				}
				return ((CorbaPortSynchronizer) sync).getCorbaObjectInterface();
			}
		};
	}

}
