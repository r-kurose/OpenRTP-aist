package jp.go.aist.rtm.systemeditor.ui.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
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
	 * Finalizeに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String FINALIZE_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Finalize"; //$NON-NLS-1$

	/**
	 * Exitに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String EXIT_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Exit"; //$NON-NLS-1$

	static final String TITLE_CONFIRM_DIALOG = Messages
			.getString("IComponentActionDelegate.15");

	private ISelection selection;

	private IWorkbenchPart targetPart;

	/**
	 * {@inheritDoc}
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * アクション内部で使用される、メッセージとコマンドをまとめたインタフェース
	 */
	public interface MessageAndCommand {
		public String getConfirmMessage();

		public int run();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void run(final IAction action) {

		for (Iterator iter = ((IStructuredSelection) selection).iterator(); iter
				.hasNext();) {

			final CorbaComponent component = (CorbaComponent) iter.next();

			MessageAndCommand command = null;
			if ((START_ACTION_ID).equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.7"); //$NON-NLS-1$
					}

					public int run() {
						return component.startR();
					}
				};
			} else if (STOP_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.8"); //$NON-NLS-1$
					}

					public int run() {
						return component.stopR();
					}
				};
			} else if (ACTIVATE_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.9"); //$NON-NLS-1$
					}

					public int run() {
						return component.activateR();
					}
				};
			} else if (DEACTIVATE_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.10"); //$NON-NLS-1$
					}

					public int run() {
						return component.deactivateR();
					}
				};
			} else if (RESET_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.11"); //$NON-NLS-1$
					}

					public int run() {
						return component.resetR();
					}
				};
			} else if (EXIT_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.12"); //$NON-NLS-1$
					}

					public int run() {
						return component.exitR();
					}
				};
			} else if (FINALIZE_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return Messages.getString("IComponentActionDelegate.13"); //$NON-NLS-1$
					}

					public int run() {
						return component.finalizeR();
					}
				};
			} else {
				throw new RuntimeException(Messages.getString("IComponentActionDelegate.14")); //$NON-NLS-1$
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

			final MessageAndCommand finalCommand = command;

			final Integer[] returnCode = new Integer[1]; // final配列化することで、クロージャ内で返り値を設定することができるようにする。
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart
					.getSite().getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask(Messages.getString("IComponentActionDelegate.16"), 100); //$NON-NLS-1$

					monitor.worked(20);
					monitor.subTask(Messages.getString("IComponentActionDelegate.17")); //$NON-NLS-1$

//					returnCode[0] = finalCommand.run();
					int defaultTimeout = ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
							ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
					TimeoutWrapper wrapper = new TimeoutWrapper(defaultTimeout);
					wrapper.setJob(new TimeoutWrappedJob(){
						@Override
						protected Object executeCommand() {
							return finalCommand.run();
						}});
					returnCode[0] = (Integer) wrapper.start();
					
					monitor.subTask(Messages.getString("IComponentActionDelegate.18")); //$NON-NLS-1$
					monitor.done();
				}
			};

			try {
				dialog.run(false, false, runable);
			} catch (Exception e) {
				e.printStackTrace(); // system error
			}

			if (returnCode[0] == null) return;
			
			if (CorbaComponent.RETURNCODE_OK == returnCode[0]) {
				component.synchronizeManually();
			} else if (CorbaComponent.RETURNCODE_ERROR == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), Messages.getString("IComponentActionDelegate.19"), //$NON-NLS-1$
						Messages.getString("IComponentActionDelegate.20")); //$NON-NLS-1$
			} else if (CorbaComponent.RETURNCODE_BAD_PARAMETER == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), Messages.getString("IComponentActionDelegate.21"), //$NON-NLS-1$
						Messages.getString("IComponentActionDelegate.22")); //$NON-NLS-1$
			} else if (CorbaComponent.RETURNCODE_UNSUPPORTED == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), Messages.getString("IComponentActionDelegate.23"), //$NON-NLS-1$
						Messages.getString("IComponentActionDelegate.24")); //$NON-NLS-1$
			} else if (CorbaComponent.RETURNCODE_OUT_OF_RESOURCES == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), Messages.getString("IComponentActionDelegate.25"), //$NON-NLS-1$
						Messages.getString("IComponentActionDelegate.26")); //$NON-NLS-1$
			} else if (CorbaComponent.RETURNCODE_PRECONDITION_NOT_MET == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), Messages.getString("IComponentActionDelegate.27"), //$NON-NLS-1$
						Messages.getString("IComponentActionDelegate.28")); //$NON-NLS-1$
			}

		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
