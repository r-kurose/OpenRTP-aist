package jp.go.aist.rtm.systemeditor.ui.handler;

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
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

public class ManagerActionHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerActionHandler.class);

	/** [コマンドID] マネージャ停止 */
	public static final String SHUTDOWN_ID = "rtse.command.manager.Shutdown";
	/** [コマンドID] マネージャ再起動 */
	public static final String RESTART_ID = "rtse.command.manager.Restart";

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_SHUTDOWN = Messages.getString("ManagerActionHandler.confirm.shutdown");
	static final String MSG_CONFIRM_RESTART = Messages.getString("ManagerActionHandler.confirm.restart");
	static final String MSG_CONFIRM_UNKNOWN = Messages.getString("ManagerActionHandler.confirm.unknown");

	ComponentActionDelegate actionDelegate;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		IWorkbenchPart part = HandlerUtil.getActivePartChecked(event);
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);

		List<RTCManager> managers = new ArrayList<>();
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {
				Object o = AdapterUtil.getAdapter(iter.next(), RTCManager.class);
				if (!(o instanceof RTCManager)) {
					continue;
				}
				managers.add((RTCManager) o);
			}
		}
		LOGGER.trace("ManagerActionHandler: command=<{}> managers=<{}>", command.getId(), managers);
		if (managers.isEmpty()) {
			return null;
		}

		this.actionDelegate = new ComponentActionDelegate();
		this.actionDelegate.setActivePart(null, part);

		String confirmMessage = MSG_CONFIRM_UNKNOWN;
		List<ComponentActionDelegate.Command> commands = new ArrayList<>();

		if (SHUTDOWN_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_SHUTDOWN;
			commands = ComponentActionDelegate.commandOf_Manager_SHUTDOWN(LOGGER, managers);

		} else if (RESTART_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_RESTART;
			commands = ComponentActionDelegate.commandOf_Manager_RESTART(LOGGER, managers);

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

}
