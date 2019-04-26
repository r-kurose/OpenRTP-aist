package jp.go.aist.rtm.systemeditor.ui.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.EditPolicyConstraint;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RTCの表示方向切替コマンドを実行するハンドラ
 */
public class ChangeDirectionCommandHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChangeDirectionCommandHandler.class);

	/** [コマンドID] 水平方向表示 */
	public static final String HORIZONTAL_DIRECTION_ID = "rtse.command.direction.Horizontal";
	/** [コマンドID] 巣直方向表示 */
	public static final String VERTICAL_DIRECTION_ID = "rtse.command.direction.Vertical";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		List<ComponentEditPart> comps = new ArrayList<>();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Iterator<?> iterator = structuredSelection.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (o instanceof ComponentEditPart) {
					comps.add((ComponentEditPart) o);
				}
			}
		}
		if (command == null || comps.isEmpty()) {
			return null;
		}
		return executeInternal(command.getId(), comps.toArray(new ComponentEditPart[0]));
	}

	// コマンド実行本体
	Object executeInternal(String id, ComponentEditPart... editParts) {
		LOGGER.debug("ChangeDirectionCommandHandler: command=<{}> comps=<{}>", id, editParts);

		org.eclipse.gef.requests.ChangeBoundsRequest request = new org.eclipse.gef.requests.ChangeBoundsRequest();
		request.setType(EditPolicyConstraint.REQ_CHANGE_DIRECTION);

		int direction = Component.CHANGE_HORIZON_DIRECTION;
		if (HORIZONTAL_DIRECTION_ID.equals(id)) {
			direction = Component.CHANGE_HORIZON_DIRECTION;
		} else if (VERTICAL_DIRECTION_ID.equals(id)) {
			direction = Component.CHANGE_VERTICAL_DIRECTION;
		}

		HashMap<String, Integer> data = new HashMap<>();
		data.put(EditPolicyConstraint.DIRECTION_KEY, direction);

		request.setExtendedData(data);

		org.eclipse.gef.commands.CompoundCommand compoundCmd = new org.eclipse.gef.commands.CompoundCommand();
		for (ComponentEditPart editPart : editParts) {
			if (editPart.understandsRequest(request)) {
				org.eclipse.gef.commands.Command cmd = editPart.getCommand(request);
				if (cmd != null) {
					compoundCmd.add(cmd);
				}
			}
		}
		compoundCmd.execute();

		return null;
	}

	/**
	 * RTCの表示方向切替コマンドのハンドラをキックします。<br>
	 * plugin.xml にマウス操作定義がないため、マウス操作によるコマンド実行を代理します。
	 */
	public static class Delegate {

		private String id;
		private List<ComponentEditPart> comps = new ArrayList<>();

		public void setId(String id) {
			this.id = id;
		}

		public void setTargetEditPart(ComponentEditPart... parts) {
			for (ComponentEditPart part : parts) {
				this.comps.add(part);
			}
		}

		public void run() throws Exception {
			ChangeDirectionCommandHandler handler = new ChangeDirectionCommandHandler();
			handler.executeInternal(this.id, this.comps.toArray(new ComponentEditPart[0]));
		}

	}

}
