package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.util.CompositeComponentHelper;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.gef.commands.Command;

/**
 * コネクタを削除するコマンド
 */
public class DeleteConnectorCommand extends Command {
	private PortConnector connector;

	public DeleteConnectorCommand() {
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		boolean result = connector.deleteConnectorR();
		if (result == false) {
			return;
		}

		CompositeComponentHelper.synchronizeManually(connector.getSource());
	}

	/**
	 * コネクタを設定する
	 * 
	 * @param connector
	 *            コネクタ
	 */
	public void setConnector(PortConnector connector) {
		this.connector = connector;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		boolean result = connector.createConnectorR();
		if (result == false) {
			return;
		}
	}
}
