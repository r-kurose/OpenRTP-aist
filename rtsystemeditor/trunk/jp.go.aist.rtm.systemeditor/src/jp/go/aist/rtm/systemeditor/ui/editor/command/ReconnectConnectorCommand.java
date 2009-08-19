package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.GraphicalConnectorCreateManager;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.gef.commands.Command;

/**
 * コネクタを接続しなおすコマンド
 * <p>
 * 内部では、昔の接続を破棄して新しく接続を行う。
 */
public class ReconnectConnectorCommand extends Command {
	private Port newSource;

	private Port newTarget;

	private ClearLineConstraintCommand clearLineConstraintCommand = new ClearLineConstraintCommand();

	private PortConnector connector;

	private GraphicalConnectorCreateManager manager;

	/**
	 * コンストラクタ
	 * 
	 * @param connector
	 *            コネクタ
	 * @param manager
	 *            ConnectorCreateManager
	 */
	public ReconnectConnectorCommand(PortConnector connector,
			GraphicalConnectorCreateManager profileCreater) {
		this.connector = connector;
		this.manager = profileCreater;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean canExecute() {
		if (newSource != null) {
			manager.setFirst(newSource);
			manager.setSecond(connector.getTarget());
			if (manager.validate() == false) {
				return false;
			}
		}

		if (newTarget != null) {
			manager.setFirst((Port) connector.getSource());
			manager.setSecond((Port) newTarget);
			if (manager.validate() == false) {
				return false;
			}
		}

		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (newSource != null) {
			manager.setFirst((Port) newSource);
			manager.setSecond((Port) connector.getTarget());

			ConnectorProfile profile = manager.getConnectorProfile();
			if (profile != null) {
				connector.deleteConnectorR();
				manager.connectR(profile);
			}
		}

		if (newTarget != null) {
			manager.setFirst((Port) connector.getSource());
			manager.setSecond((Port) newTarget);
			
			ConnectorProfile profile = manager.getConnectorProfile();
			if (profile != null) {
				connector.deleteConnectorR();
				manager.connectR(profile);
			}
		}

		clearLineConstraintCommand.setModel(connector);
		clearLineConstraintCommand.execute();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
	}

	/**
	 * 新しい接続元を設定する
	 * 
	 * @param source
	 *            新しい接続元
	 */
	public void setNewSource(Port source) {
		this.newSource = source;
	}

	/**
	 * 新しい接続先を設定する
	 * 
	 * @param source
	 *            新しい接続先
	 */
	public void setNewTarget(Port target) {
		this.newTarget = target;
	}

}
