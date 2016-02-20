package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.DataConnectorCreaterDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.ServiceConnectorCreaterDialog;
import jp.go.aist.rtm.systemeditor.ui.util.CompositeComponentHelper;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コネクタプロファイルを作成する責務を持ったマネージャ
 */
public class GraphicalConnectorCreateManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GraphicalConnectorCreateManager.class);

	static final String ERROR_TITLE = Messages
			.getString("GraphicalConnectorCreateManager.0");

	static final String ERROR_CONNECT_FAILED = Messages
			.getString("GraphicalConnectorCreateManager.1");

	static final String EXTENTION_POINT_NAME = "createconnectorprofile";
	private static List<ConnectorProfileCreater> connectorProfileCreators;

	private Shell shell;

	private Port first;

	private Port second;

	public GraphicalConnectorCreateManager(Shell shell) {
		this.shell = shell;
	}

	public Port getFirst() {
		return first;
	}

	public void setFirst(Port first) {
		this.first = first;
	}

	public Port getSecond() {
		return second;
	}

	public void setSecond(Port second) {
		this.second = second;
	}

	/**
	 * 接続元を取得する
	 */
	public Port getSource() {
		Port result = first;
		if (second instanceof OutPort) {
			result = second;
		}
		return result;
	}

	/**
	 * 接続先を取得する
	 */
	public Port getTarget() {
		Port result = first;
		if (first == getSource()) {
			result = second;
		}
		return result;
	}

	public boolean validate() {
		return getSource().validateTargetConnector(getTarget())
				&& getTarget().validateSourceConnector(getSource());
	}

	public boolean validateSingle() {
		return (getSource() != null && getTarget() == null)
				|| (getSource() == null && getTarget() != null);
	}

	/**
	 * ConnectorProfileを生成し、ポート接続を行います。
	 * 
	 * @return ポート接続成功の場合はtrue
	 */
	public boolean createProfileAndConnector() {
		ConnectorProfile connectorProfile = getConnectorProfile();
		if (connectorProfile == null) {
			return false;
		}
		return connectR(connectorProfile);
	}

	/**
	 * ConnectorProfile(接続情報)を生成します。(拡張可)
	 * 
	 * @return 接続情報
	 */
	public ConnectorProfile getConnectorProfile() {
		if (connectorProfileCreators == null) {
			buildConnectorProfileCreator();
		}
		for (ConnectorProfileCreater creator : connectorProfileCreators) {
			try {
				ConnectorProfileCreater.ResultCode result = creator
						.getConnectorProfile(getSource(), getTarget(), shell);
				if (result == ConnectorProfileCreater.ResultCode.SUCCESS) {
					return creator.getConnectorProfile();
				} else if (result == ConnectorProfileCreater.ResultCode.FAILURE) {
					creator.showErrorMessage(shell);
					return null;
				} else {
					continue;
				}
			} catch (Exception e) {
				LOGGER.error("Fail to get connection profile", e);
				return null;
			}
		}
		if (getSource() instanceof OutPort && getTarget() instanceof InPort) {
			return new DataConnectorCreaterDialog(shell).getConnectorProfile(
					(OutPort) getSource(), (InPort) getTarget());
		} else if (getSource() instanceof ServicePort
				&& getTarget() instanceof ServicePort) {
			return new ServiceConnectorCreaterDialog(shell)
					.getConnectorProfile((ServicePort) getSource(),
							(ServicePort) getTarget());
		} else if (getSource() instanceof OutPort && getTarget() == null) {
			// OutPortのみ
			return new DataConnectorCreaterDialog(shell).getConnectorProfile(
					(OutPort) getSource(), null);
		} else if (getSource() instanceof InPort && getTarget() == null) {
			// InPortのみ
			return new DataConnectorCreaterDialog(shell).getConnectorProfile(
					null, (InPort) getSource());
		} else if (getSource() instanceof ServicePort && getTarget() == null) {
			// ServicePortのみ
			return new ServiceConnectorCreaterDialog(shell)
					.getConnectorProfile((ServicePort) getSource(), null);
		} else {
			return null;
		}
	}

	private static void buildConnectorProfileCreator() {
		connectorProfileCreators = new ArrayList<ConnectorProfileCreater>();
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof ConnectorProfileCreater) {
						connectorProfileCreators
								.add((ConnectorProfileCreater) obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * ConnectorProfileを元に、ポート接続を行います。
	 * 
	 * @param connectorProfile
	 *            接続情報
	 * @return ポート接続成功の場合はtrue
	 */
	public boolean connectR(ConnectorProfile connectorProfile) {
		PortConnector connector = PortConnectorFactory.createPortConnector(
				getSource(), getTarget());
		connector.setConnectorProfile(connectorProfile);

		boolean result = connector.createConnectorR();
		if (!result) {
			MessageDialog.openError(shell, ERROR_TITLE, ERROR_CONNECT_FAILED);
			return false;
		}
		CompositeComponentHelper.synchronizeManually(connector.getSource());
		return result;
	}

}
