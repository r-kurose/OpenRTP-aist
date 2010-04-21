package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentActionDelegate;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * RtcLinkの個々のコンポーネントそれぞれに対するアクション
 */
public class IComponentActionDelegate implements IObjectActionDelegate {
	/**
	 * Startに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String START_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".executioncontext.Start"; //$NON-NLS-1$

	/**
	 * Stopに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String STOP_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".executioncontext.Stop"; //$NON-NLS-1$

	/**
	 * Activateに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String ACTIVATE_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Activate"; //$NON-NLS-1$

	/**
	 * Deactivateに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String DEACTIVATE_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Deactivate"; //$NON-NLS-1$

	/**
	 * Resetに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String RESET_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Reset"; //$NON-NLS-1$

	/**
	 * Exitに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String EXIT_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Exit"; //$NON-NLS-1$

	static final String TITLE_CONFIRM_DIALOG = Messages
			.getString("IComponentActionDelegate.15");

	static final String MSG_CONFIRM_START = Messages.getString("IComponentActionDelegate.7");
	static final String MSG_CONFIRM_STOP = Messages.getString("IComponentActionDelegate.8");
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

	/** コンポーネントアクションのコマンド */
	static abstract class ComponentCommand extends
			ComponentActionDelegate.Command {
		protected CorbaComponent comp;

		public ComponentCommand(CorbaComponent comp) {
			this.comp = comp;
		}
	}

	@SuppressWarnings("unchecked")
	public void run(final IAction action) {

		for (Iterator iter = ((IStructuredSelection) selection).iterator(); iter
				.hasNext();) {

			final CorbaComponent component = (CorbaComponent) iter.next();

			ComponentCommand command = null;
			if ((START_ACTION_ID).equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_START;
					}

					@Override
					public int run() {
						return comp.startR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (STOP_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_STOP;
					}

					@Override
					public int run() {
						return component.stopR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (ACTIVATE_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_ACTIVATE;
					}

					@Override
					public int run() {
						return component.activateR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (DEACTIVATE_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_DEACTIVATE;
					}

					@Override
					public int run() {
						return component.deactivateR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (RESET_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_RESET;
					}

					@Override
					public int run() {
						return component.resetR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (EXIT_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_EXIT;
					}

					@Override
					public int run() {
						return component.exitR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else {
				throw new RuntimeException(ERROR_UNKNOWN_COMMAND);
			}

			if (SystemEditorPreferenceManager.getInstance()
					.isConfirmComponentAction()) {
				// アクションの実行確認が有効な場合
				boolean isOK = MessageDialog.openConfirm(targetPart.getSite()
						.getShell(), TITLE_CONFIRM_DIALOG, command
						.getConfirmMessage());
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
