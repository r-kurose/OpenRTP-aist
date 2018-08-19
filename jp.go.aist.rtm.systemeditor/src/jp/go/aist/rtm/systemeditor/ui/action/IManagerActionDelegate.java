package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentActionDelegate;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Managerに対するアクション
 */
public class IManagerActionDelegate implements IObjectActionDelegate {
	/**
	 * Shutdownに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String SHUTDOWN_ACTION_ID = IManagerActionDelegate.class.getName() + ".Shutdown"; //$NON-NLS-1$
	
	/**
	 * Shutdownに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String RESTART_ACTION_ID = IManagerActionDelegate.class.getName() + ".Restart"; //$NON-NLS-1$

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_SHUTDOWN = Messages.getString("IManagerActionDelegate.shutdown");
	static final String MSG_CONFIRM_RESTART = Messages.getString("IManagerActionDelegate.restart");
	static final String ERROR_UNKNOWN_COMMAND = Messages.getString("IComponentActionDelegate.14");

	private ISelection selection;
	private IWorkbenchPart targetPart;
	ComponentActionDelegate actionDelegate;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
		actionDelegate = new ComponentActionDelegate();
		actionDelegate.setActivePart(null, this.targetPart);
	}
	
	static abstract class ManagerCommand extends ComponentActionDelegate.Command {
		protected RTCManager target;

		public ManagerCommand(RTCManager manager) {
			this.target = manager;
		}
	}
	
	@Override
	public void run(IAction action) {
		for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {

			final RTCManager manager = (RTCManager) iter.next();

			ManagerCommand command = null;
			if (SHUTDOWN_ACTION_ID.equals(action.getId())) {
				command = new ManagerCommand(manager) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_SHUTDOWN;
					}

					@Override
					public int run() {
						return manager.shutdownR();
					}

					@Override
					public void done() {
					}
				};
			} else if (RESTART_ACTION_ID.equals(action.getId())) {
				command = new ManagerCommand(manager) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_RESTART;
					}

					@Override
					public int run() {
						return manager.restartR();
					}

					@Override
					public void done() {
					}
				};
			} else {
				throw new RuntimeException(ERROR_UNKNOWN_COMMAND);
			}

			if (SystemEditorPreferenceManager.getInstance().isConfirmComponentAction()) {
				// アクションの実行確認が有効な場合
				boolean isOK = MessageDialog.openConfirm(targetPart.getSite().getShell(), TITLE_CONFIRM_DIALOG,
						command.getConfirmMessage());
				if (!isOK) {
					return;
				}
			}

			actionDelegate.run(command);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
