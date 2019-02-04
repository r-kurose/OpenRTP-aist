package jp.go.aist.rtm.systemeditor.ui.action;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.LOG_R;

import java.util.Iterator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentActionDelegate;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

/**
 * RtcLinkの個々のコンポーネントそれぞれに対するアクション
 */
public class IComponentActionDelegate implements IObjectActionDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(IComponentActionDelegate.class);

	/**
	 * Activateに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String ACTIVATE_ACTION_ID = IComponentActionDelegate.class.getName() + ".Activate"; //$NON-NLS-1$

	/**
	 * Deactivateに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String DEACTIVATE_ACTION_ID = IComponentActionDelegate.class.getName() + ".Deactivate"; //$NON-NLS-1$

	/**
	 * Resetに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String RESET_ACTION_ID = IComponentActionDelegate.class.getName() + ".Reset"; //$NON-NLS-1$

	/**
	 * Exitに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String EXIT_ACTION_ID = IComponentActionDelegate.class.getName() + ".Exit"; //$NON-NLS-1$

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_ACTIVATE = Messages.getString("IComponentActionDelegate.9");
	static final String MSG_CONFIRM_DEACTIVATE = Messages.getString("IComponentActionDelegate.10");
	static final String MSG_CONFIRM_RESET = Messages.getString("IComponentActionDelegate.11");
	static final String MSG_CONFIRM_EXIT = Messages.getString("IComponentActionDelegate.12");

	static final String ERROR_UNKNOWN_COMMAND = Messages.getString("IComponentActionDelegate.14");

	private ISelection selection;

	private IWorkbenchPart targetPart;

	ComponentActionDelegate actionDelegate;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
		actionDelegate = new ComponentActionDelegate();
		actionDelegate.setActivePart(null, this.targetPart);
	}

	public void run(final IAction action) {
		for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {

			CorbaComponent component = (CorbaComponent) iter.next();
			ComponentActionDelegate.Command command = null;

			if (ACTIVATE_ACTION_ID.equals(action.getId())) {
				command = ComponentActionDelegate.Command.of(MSG_CONFIRM_ACTIVATE, //
						() -> {
							return LOG_R(LOGGER, "activate()", component, () -> {
								return component.activateR();
							});
						}, //
						() -> {
							component.synchronizeManually();
							return 1;
						});

			} else if (DEACTIVATE_ACTION_ID.equals(action.getId())) {
				command = ComponentActionDelegate.Command.of(MSG_CONFIRM_DEACTIVATE, //
						() -> {
							return LOG_R(LOGGER, "deactivate()", component, () -> {
								return component.deactivateR();
							});
						}, //
						() -> {
							component.synchronizeManually();
							return 1;
						});

			} else if (RESET_ACTION_ID.equals(action.getId())) {
				command = ComponentActionDelegate.Command.of(MSG_CONFIRM_RESET, //
						() -> {
							return LOG_R(LOGGER, "reset()", component, () -> {
								return component.resetR();
							});
						}, //
						() -> {
							component.synchronizeManually();
							return 1;
						});

			} else if (EXIT_ACTION_ID.equals(action.getId())) {
				command = ComponentActionDelegate.Command.of(MSG_CONFIRM_EXIT, //
						() -> {
							return LOG_R(LOGGER, "exit()", component, () -> {
								return component.exitR();
							});
						}, //
						() -> {
							component.synchronizeManually();
							return 1;
						});

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

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
