package jp.go.aist.rtm.systemeditor.ui.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentComparator;
import jp.go.aist.rtm.systemeditor.ui.views.actionorderview.ActionOrderView.ActionName;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * エディタ上の全コンポーネントコマンドを実行するハンドラ
 */
public class AllComponentCommandHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AllComponentCommandHandler.class);

	/** [コマンドID] 全コンポーネント終了 */
	public static final String ALL_EXIT_ID = "rtse.command.allcomponent.AllExit";
	/** [コマンドID] 全コンポーネント開始 */
	public static final String ALL_START_ID = "rtse.command.allcomponent.AllSart";
	/** [コマンドID] 全コンポーネント停止 */
	public static final String ALL_STOP_ID = "rtse.command.allcomponent.AllStop";
	/** [コマンドID] 全コンポーネント活性 */
	public static final String ALL_ACTIVATE_ID = "rtse.command.allcomponent.AllActivate";
	/** [コマンドID] 全コンポーネント非活性 */
	public static final String ALL_DEACTIVATE_ID = "rtse.command.allcomponent.AllDeactivate";

	static final String MSG_CONFIRM_START = Messages.getString("AllComponentCommandHandler.5");
	static final String MSG_CONFIRM_STOP = Messages.getString("AllComponentCommandHandler.6");
	static final String MSG_CONFIRM_ACTIVATE = Messages.getString("AllComponentCommandHandler.7");
	static final String MSG_CONFIRM_DEACTIVATE = Messages.getString("AllComponentCommandHandler.8");
	static final String MSG_CONFIRM_EXIT = Messages.getString("AllComponentCommandHandler.9");
	static final String MSG_CONFIRM_UNKNOWN = Messages.getString("AllComponentCommandHandler.4");

	static final String MSG_MONITOR_BEGIN = Messages.getString("AllComponentCommandHandler.10");
	static final String MSG_MONITOR_SUB1 = Messages.getString("AllComponentCommandHandler.11");
	static final String MSG_MONITOR_SUB2 = Messages.getString("AllComponentCommandHandler.10");

	static final String MSG_ERROR = Messages.getString("AllComponentCommandHandler.14");

	static final String TITLE_CONFIRM = Messages.getString("Common.dialog.confirm_title");
	static final String TITLE_ERROR = Messages.getString("Common.dialog.error_title");

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final Command command = event.getCommand();
		IEditorPart part = HandlerUtil.getActiveEditor(event);
		SystemDiagramEditor editor = null;
		if (part instanceof SystemDiagramEditor) {
			editor = (SystemDiagramEditor) part;
		}
		LOGGER.debug("AllComponentCommandHandler: command=<{}> editor=<{}>", command.getId(), editor);
		if (editor == null) {
			return null;
		}

		String comfirmMessage = MSG_CONFIRM_UNKNOWN;
		if (ALL_START_ID.equals(command.getId())) {
			comfirmMessage = MSG_CONFIRM_START;
		} else if (ALL_STOP_ID.equals(command.getId())) {
			comfirmMessage = MSG_CONFIRM_STOP;
		} else if (ALL_ACTIVATE_ID.equals(command.getId())) {
			comfirmMessage = MSG_CONFIRM_ACTIVATE;
		} else if (ALL_DEACTIVATE_ID.equals(command.getId())) {
			comfirmMessage = MSG_CONFIRM_DEACTIVATE;
		} else if (ALL_EXIT_ID.equals(command.getId())) {
			comfirmMessage = MSG_CONFIRM_EXIT;
		}

		boolean isOk = true;
		if (SystemEditorPreferenceManager.getInstance().isConfirmComponentAction() || ALL_EXIT_ID.equals(command.getId())) {
			isOk = MessageDialog.openConfirm(editor.getSite().getShell(), TITLE_CONFIRM, comfirmMessage);
		}
		if (isOk) {
			final SystemDiagram systemDiagram = editor.getSystemDiagram();
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(editor.getSite().getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

					monitor.beginTask(MSG_MONITOR_BEGIN, 100);

					monitor.subTask(MSG_MONITOR_SUB1);

					try {
						if (ALL_START_ID.equals(command.getId())) {
							doAllStart(systemDiagram);
						} else if (ALL_STOP_ID.equals(command.getId())) {
							doAllStop(systemDiagram);
						} else if (ALL_ACTIVATE_ID.equals(command.getId())) {
							doAllActivate(systemDiagram);
						} else if (ALL_DEACTIVATE_ID.equals(command.getId())) {
							doAllDectivate(systemDiagram);
						} else if (ALL_EXIT_ID.equals(command.getId())) {
							doAllExit(systemDiagram);
						}
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}

					monitor.subTask(MSG_MONITOR_SUB2);
					monitor.done();
				}

			};

			try {
				dialog.run(false, false, runable);
			} catch (InvocationTargetException e) {
				MessageDialog.openError(editor.getSite().getShell(), TITLE_ERROR, MSG_ERROR + e.getMessage());
			} catch (InterruptedException e) {
				LOGGER.error("Fail in dialog (interrupted)", e);
			}
		}

		return null;
	}

	private void doAllStop(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_SHUT_DOWN));
		for (int index = 0; index < targetComps.size(); index++) {
			((CorbaComponent) targetComps.get(index)).stopAll();
		}
	}

	private void doAllStart(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_START_UP));
		for (int index = 0; index < targetComps.size(); index++) {
			((CorbaComponent) targetComps.get(index)).startAll();
		}
	}

	private void doAllActivate(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_ACTIVATION));
		for (int index = 0; index < targetComps.size(); index++) {
			((CorbaComponent) targetComps.get(index)).activateAll();
		}
	}

	private void doAllDectivate(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_DEACTIVATION));
		for (int index = 0; index < targetComps.size(); index++) {
			((CorbaComponent) targetComps.get(index)).deactivateAll();
		}
	}

	private void doAllExit(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_FINALIZE));
		for (int index = 0; index < targetComps.size(); index++) {
			((CorbaComponent) targetComps.get(index)).exitR();
		}
	}

	private List<Component> getTargetComps(SystemDiagram systemDiagram) {
		List<Component> targetComps = new ArrayList<Component>();
		for (Component comp : systemDiagram.getRegisteredComponents()) {
			if (!(comp instanceof CorbaComponent)) {
				continue;
			}
			targetComps.add(comp);
		}
		return targetComps;
	}

}
