package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.systemeditor.ui.editor.command.ChangeDirectionCommand;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

/**
 * ï˚å¸ïœçXÇÃEditPolicyÉNÉâÉX
 */
public class ChangeDirectionEditPolicy extends LayoutEditPolicy {

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command getCommand(Request request) {
		if (request instanceof ChangeBoundsRequest == false
				|| EditPolicyConstraint.REQ_CHANGE_DIRECTION.equals(request
						.getType()) == false) {
			return null;
		}

		return getChangeDirectionCommand(getHost(),
				(ChangeBoundsRequest) request);
	}

	private Command getChangeDirectionCommand(EditPart editPart,
			ChangeBoundsRequest request) {
		Component model = (Component) editPart.getModel();

		ChangeDirectionCommand command = new ChangeDirectionCommand();

		command.setModel(model);

		int req = (Integer) request.getExtendedData().get(
				EditPolicyConstraint.DIRECTION_KEY);

		command.setDirection(getDirection(req, model.getOutportDirection()));

		return command;
	}

	private String getDirection(int req, String outportDirection) {
		if (Component.CHANGE_HORIZON_DIRECTION == req) {
			if (Component.OUTPORT_DIRECTION_RIGHT_LITERAL.equals(outportDirection)) {
				return Component.OUTPORT_DIRECTION_LEFT_LITERAL;
			} else  {
				return Component.OUTPORT_DIRECTION_RIGHT_LITERAL;
			}
		} else if (Component.CHANGE_VERTICAL_DIRECTION == req) {
			if (Component.OUTPORT_DIRECTION_UP_LITERAL.equals(outportDirection)) {
				return Component.OUTPORT_DIRECTION_DOWN_LITERAL;
			} else  {
				return Component.OUTPORT_DIRECTION_UP_LITERAL;
			}
		}
		return Component.OUTPORT_DIRECTION_RIGHT_LITERAL;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return null;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command getMoveChildrenCommand(Request request) {
		return null;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean understandsRequest(Request req) {
		if (req instanceof ChangeBoundsRequest == false) {
			return false;
		}

		boolean result = false;
		if (EditPolicyConstraint.REQ_CHANGE_DIRECTION.equals(req.getType())) {
			result = true;
		}

		return result;
	}

}
