package jp.go.aist.rtm.systemeditor.ui.util;

import java.lang.reflect.InvocationTargetException;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPart;

/**
 * コマンドの実行を代理するクラス
 */
public class ComponentActionDelegate {

	static final String MSG_BEGIN_TASK = Messages.getString("ComponentActionDelegate.1");
	static final String MSG_SUB_TASK1 = Messages.getString("ComponentActionDelegate.2");
	static final String MSG_SUB_TASK2 = Messages.getString("ComponentActionDelegate.3");

	static final String ERROR_TITLE = Messages.getString("ComponentActionDelegate.4");

	static final String ERROR_DEFAULT =Messages.getString("ComponentActionDelegate.5"); 
	static final String ERROR_INVALID_PARAM = Messages.getString("ComponentActionDelegate.6");
	static final String ERROR_UNSUPPORTED = Messages.getString("ComponentActionDelegate.7");
	static final String ERROR_OUT_OF_RESOURCE = Messages.getString("ComponentActionDelegate.8");
	static final String ERROR_INVALID_PRECONDITION = Messages.getString("ComponentActionDelegate.9");

	IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * コマンド実行の基本クラス
	 */
	public static abstract class Command {
		public String getConfirmMessage() {
			return null;
		}

		public boolean hasConfirmMessage() {
			return getConfirmMessage() != null;
		}

		public abstract int run();

		public abstract void done();
	};

	public void run(final Command command) {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart
				.getSite().getShell());

		final int defaultTimeout = ToolsCommonPreferenceManager.getInstance()
				.getDefaultTimeout(
						ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);

		final Integer[] returnCode = new Integer[1];

		IRunnableWithProgress runable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {

				monitor.beginTask(MSG_BEGIN_TASK, 100);
				monitor.worked(20);
				monitor.subTask(MSG_SUB_TASK1);

				TimeoutWrapper wrapper = new TimeoutWrapper(defaultTimeout);
				wrapper.setJob(new TimeoutWrappedJob() {
					@Override
					protected Object executeCommand() {
						return command.run();
					}
				});
				returnCode[0] = (Integer) wrapper.start();

				monitor.subTask(MSG_SUB_TASK2);
				monitor.done();
			}
		};

		try {
			dialog.run(false, false, runable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (returnCode[0] == null) {
			return;
		}

		if (CorbaComponent.RETURNCODE_OK == returnCode[0]) {
			command.done();
		} else if (CorbaComponent.RETURNCODE_ERROR == returnCode[0]) {
			openError(ERROR_DEFAULT);
		} else if (CorbaComponent.RETURNCODE_BAD_PARAMETER == returnCode[0]) {
			openError(ERROR_INVALID_PARAM);
		} else if (CorbaComponent.RETURNCODE_UNSUPPORTED == returnCode[0]) {
			openError(ERROR_UNSUPPORTED);
		} else if (CorbaComponent.RETURNCODE_OUT_OF_RESOURCES == returnCode[0]) {
			openError(ERROR_OUT_OF_RESOURCE);
		} else if (CorbaComponent.RETURNCODE_PRECONDITION_NOT_MET == returnCode[0]) {
			openError(ERROR_INVALID_PRECONDITION);
		}
	}

	void openError(String message) {
		MessageDialog.openError(targetPart.getSite().getShell(), ERROR_TITLE,
				message);
	}

}
