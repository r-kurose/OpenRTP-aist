package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.systemeditor.ui.editor.command.DeleteECConnectionCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECEditPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ECの ComponentEditPolicy、EC関連線の削除コマンドを与えます。
 */
public class ECComponentEditPolicy extends ComponentEditPolicy {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECComponentEditPolicy.class);

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		LOGGER.debug("getDeleteCommand: req=<{}> host=<{}>", request, getHost());
		if (!(getHost() instanceof ECEditPart.PartECEditPart)) {
			return null;
		}
		DeleteECConnectionCommand command = new DeleteECConnectionCommand();
		command.setPartEC((ECEditPart.PartECEditPart) getHost());
		return command;
	}

}
