package jp.go.aist.rtm.systemeditor.ui.util;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

/**
 * コマンドの実行を代理するクラス
 */
public class ComponentActionDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentActionDelegate.class);

	static final String MSG_BEGIN_TASK = Messages.getString("ComponentActionDelegate.1");
	static final String MSG_SUB_TASK1 = Messages.getString("ComponentActionDelegate.2");
	static final String MSG_SUB_TASK2 = Messages.getString("ComponentActionDelegate.3");

	static final String ERROR_TITLE = Messages.getString("Common.dialog.error_title");

	static final String ERROR_DEFAULT = Messages.getString("ComponentActionDelegate.5");
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

		public abstract int run() throws Exception;

		public abstract void done();

		public static Command of(String confirm, Callable<Integer> run, Callable<Integer> done) {
			return new Command() {
				@Override
				public int run() throws Exception {
					return run.call();
				}

				@Override
				public void done() {
					try {
						done.call();
					} catch (Exception e) {
						LOGGER.error("Fail to done command.", e);
					}
				}
			};
		}

	};

	public void run(final Command command) {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());

		final Integer[] returnCode = new Integer[1];

		IRunnableWithProgress runable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

				monitor.beginTask(MSG_BEGIN_TASK, 100);
				monitor.worked(20);
				monitor.subTask(MSG_SUB_TASK1);

				TimeoutWrapper wrapper = TimeoutWrapper.asDefault();
				returnCode[0] = wrapper.start(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return command.run();
					}
				});

				monitor.subTask(MSG_SUB_TASK2);
				monitor.done();
			}
		};

		try {
			dialog.run(false, false, runable);
		} catch (Exception e) {
			LOGGER.error("Fail in dialog", e);
		}

		if (returnCode[0] == null) {
			LOGGER.error("Fail to run command.");
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
		MessageDialog.openError(targetPart.getSite().getShell(), ERROR_TITLE, message);
	}

}
