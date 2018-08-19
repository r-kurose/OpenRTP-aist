package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.systemeditor.ui.editor.command.DeleteECConnectionCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECConnectionEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECEditPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EC関連の ConnectionEditPolicy、EC関連線の削除コマンドを与えます。
 */
public class ECConnectionEditPolicy extends ConnectionEditPolicy {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECConnectionEditPolicy.class);

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		LOGGER.debug("getDeleteCommand: req=<{}> host=<{}>", request, getHost());
		if (!(getHost() instanceof ECConnectionEditPart)) {
			return null;
		}
		ECConnectionEditPart part = (ECConnectionEditPart) getHost();
		if (!(part.getTarget() instanceof ECEditPart.PartECEditPart)) {
			return null;
		}
		DeleteECConnectionCommand command = new DeleteECConnectionCommand();
		command.setPartEC((ECEditPart.PartECEditPart) part.getTarget());
		return command;
	}

}
