package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import jp.go.aist.rtm.systemeditor.factory.ComponentCommandCreator;
import jp.go.aist.rtm.systemeditor.ui.editor.command.ChangeConstraintCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;

/**
 * システムダイアグラムに関するEditPolicyクラス
 */
public class SystemXYLayoutEditPolicy extends XYLayoutEditPolicy {

	CreateRequest request;

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		ChangeConstraintCommand command = new ChangeConstraintCommand();
		command.setModel((ModelElement) child.getModel());
		command.setConstraint((Rectangle) constraint);

		return command;
	}

	/**
	 * オンラインの場合には、既に存在するオブジェクトは登録できない。
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		ComponentCommandCreator creator = new ComponentCommandCreator();
		ComponentCommandCreator.MultiCreateCommand result = creator
				.getCreateCommand(request, getHost().getModel());

		if (result.getCommandPairs().isEmpty()) {
			MessageDialog.openInformation(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "Information", creator
					.getMessage());
			return null;
		}

		if (request.getLocation() != null) {
			int count = 0;
			for (ComponentCommandCreator.CommandPair pair : result
					.getCommandPairs()) {
				Rectangle rect = (Rectangle) getConstraintFor(request);
				rect.x += count * 20;
				rect.y += count * 20;
				pair.component.setConstraint(Draw2dUtil
						.toRtcLinkRectangle(rect));
				count++;
			}
		}
		return result;
	}

	@Override
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	@Override
	public SystemDiagramEditPart getHost() {
		return (SystemDiagramEditPart) super.getHost();
	}

}
