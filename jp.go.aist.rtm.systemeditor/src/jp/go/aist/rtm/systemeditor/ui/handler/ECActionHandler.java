package jp.go.aist.rtm.systemeditor.ui.handler;

import static jp.go.aist.rtm.systemeditor.ui.handler.ComponentActionHandler.getTargetComponentList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentActionDelegate;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

public class ECActionHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECActionHandler.class);

	/** [コマンドID] EC開始 */
	public static final String START_ID = "rtse.command.ec.Start";
	/** [コマンドID] EC停止 */
	public static final String STOP_ID = "rtse.command.ec.Stop";
	/** [コマンドID] EC参加コンポーネント活性 */
	public static final String ACTIVATE_RTCS_ID = "rtse.command.ec.ActivateRTCs";
	/** [コマンドID] EC参加コンポーネント非活性 */
	public static final String DEACTIVATE_RTCS_ID = "rtse.command.ec.DeactivateRTCs";

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_START = Messages.getString("ECActionHandler.confirm.start");
	static final String MSG_CONFIRM_STOP = Messages.getString("ECActionHandler.confirm.stop");
	static final String MSG_CONFIRM_ACTIVATE_RTCS = Messages.getString("ECActionHandler.confirm.activate_rtcs");
	static final String MSG_CONFIRM_DEACTIVATE_RTCS = Messages.getString("ECActionHandler.confirm.deactivate_rtcs");
	static final String MSG_CONFIRM_UNKNOWN = Messages.getString("ECActionHandler.confirm.unknown");

	ComponentActionDelegate actionDelegate;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		IWorkbenchPart part = HandlerUtil.getActivePartChecked(event);
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);

		List<CorbaComponent> components = new ArrayList<>();
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {
				Object o = AdapterUtil.getAdapter(iter.next(), Component.class);
				if (o instanceof Component) {
					components.addAll(getTargetComponentList((Component) o));
				}
			}
		}
		LOGGER.trace("ECActionHandler: command=<{}> components=<{}>", command.getId(), components);
		if (components.isEmpty()) {
			return null;
		}

		this.actionDelegate = new ComponentActionDelegate();
		this.actionDelegate.setActivePart(null, part);

		String confirmMessage = MSG_CONFIRM_UNKNOWN;
		List<ComponentActionDelegate.Command> commands = new ArrayList<>();

		if ((START_ID).equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_START;
			commands = ComponentActionDelegate.commandOf_START(LOGGER, components);

		} else if (STOP_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_STOP;
			commands = ComponentActionDelegate.commandOf_STOP(LOGGER, components);

		} else if (ACTIVATE_RTCS_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_ACTIVATE_RTCS;
			for (CorbaComponent cc : components) {
				CorbaExecutionContext pec = (CorbaExecutionContext) cc.getPrimaryExecutionContext();
				if (pec == null) {
					LOGGER.warn("Primary EC is null or is not CorbaExecutionContext. comp={}", cc);
					continue;
				}
				List<CorbaComponent> comps = findComponents(pec);
				List<ComponentActionDelegate.Command> cmds = ComponentActionDelegate.commandOf_ACTIVATE(LOGGER, pec,
						comps);
				commands.addAll(cmds);
			}

		} else if (DEACTIVATE_RTCS_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_DEACTIVATE_RTCS;
			for (CorbaComponent cc : components) {
				CorbaExecutionContext pec = (CorbaExecutionContext) cc.getPrimaryExecutionContext();
				if (pec == null) {
					LOGGER.warn("Primary EC is null or is not CorbaExecutionContext. comp={}", cc);
					continue;
				}
				List<CorbaComponent> comps = findComponents(pec);
				List<ComponentActionDelegate.Command> cmds = ComponentActionDelegate.commandOf_DEACTIVATE(LOGGER, pec,
						comps);
				commands.addAll(cmds);
			}

		}

		if (SystemEditorPreferenceManager.getInstance().isConfirmComponentAction()) {
			// アクションの実行確認が有効な場合
			boolean isOK = MessageDialog.openConfirm(part.getSite().getShell(), TITLE_CONFIRM_DIALOG, confirmMessage);
			if (!isOK) {
				return null;
			}
		}

		actionDelegate.run(commands);

		return null;
	}

	// EC に紐づくコンポーネントのリストを取得します(重複除外)
	private List<CorbaComponent> findComponents(CorbaExecutionContext ec) {
		List<CorbaComponent> ret = new ArrayList<>();
		List<Object> remotes = new ArrayList<>();
		//
		if (ec.getOwner() instanceof CorbaComponent) {
			ret.add((CorbaComponent) ec.getOwner());
			remotes.add(((CorbaComponent) ec.getOwner()).getCorbaObjectInterface());
		}
		for (Component c : ec.getParticipants()) {
			if (!(c instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent cc = (CorbaComponent) c;
			if (remotes.contains(cc.getCorbaObjectInterface())) {
				continue;
			}
			ret.add(cc);
			remotes.add(cc.getCorbaObjectInterface());
		}
		LOGGER.trace("findComponents: comps={}", ret);
		return ret;
	}

}
