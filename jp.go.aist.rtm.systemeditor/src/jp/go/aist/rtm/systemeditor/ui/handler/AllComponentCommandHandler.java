package jp.go.aist.rtm.systemeditor.ui.handler;

import static jp.go.aist.rtm.systemeditor.ui.handler.ComponentActionHandler.getTargetComponentList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentActionDelegate;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentComparator;
import jp.go.aist.rtm.systemeditor.ui.views.actionorderview.ActionOrderView.ActionName;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * エディタ上の全コンポーネントコマンドを実行するハンドラ
 */
public class AllComponentCommandHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AllComponentCommandHandler.class);

	/** [コマンドID] 全コンポーネント終了 */
	public static final String ALL_EXIT_ID = "rtse.command.allcomponent.AllExit";
	/** [コマンドID] 全コンポーネント開始 */
	public static final String ALL_START_ID = "rtse.command.allcomponent.AllStart";
	/** [コマンドID] 全コンポーネント停止 */
	public static final String ALL_STOP_ID = "rtse.command.allcomponent.AllStop";
	/** [コマンドID] 全コンポーネント活性 */
	public static final String ALL_ACTIVATE_ID = "rtse.command.allcomponent.AllActivate";
	/** [コマンドID] 全コンポーネント非活性 */
	public static final String ALL_DEACTIVATE_ID = "rtse.command.allcomponent.AllDeactivate";

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_START = Messages.getString("AllComponentCommandHandler.confirm.start");
	static final String MSG_CONFIRM_STOP = Messages.getString("AllComponentCommandHandler.confirm.stop");
	static final String MSG_CONFIRM_ACTIVATE = Messages.getString("AllComponentCommandHandler.confirm.activate");
	static final String MSG_CONFIRM_DEACTIVATE = Messages.getString("AllComponentCommandHandler.confirm.deactivate");
	static final String MSG_CONFIRM_EXIT = Messages.getString("AllComponentCommandHandler.confirm.exit");
	static final String MSG_CONFIRM_UNKNOWN = Messages.getString("AllComponentCommandHandler.confirm.unknown");

	ComponentActionDelegate actionDelegate;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		IEditorPart part = HandlerUtil.getActiveEditor(event);
		SystemDiagramEditor editor = null;
		if (part instanceof SystemDiagramEditor) {
			editor = (SystemDiagramEditor) part;
		}
		LOGGER.debug("AllComponentCommandHandler: command=<{}> editor=<{}>", command.getId(), editor);
		if (editor == null) {
			return null;
		}

		this.actionDelegate = new ComponentActionDelegate();
		this.actionDelegate.setActivePart(null, editor);

		SystemDiagram systemDiagram = editor.getSystemDiagram();

		String confirmMessage = MSG_CONFIRM_UNKNOWN;
		List<ComponentActionDelegate.Command> commands = new ArrayList<>();

		if (ALL_START_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_START;
			List<CorbaComponent> comps = getTargetComps(systemDiagram,
					new ComponentComparator(ActionName.ACTION_START_UP));
			commands = ComponentActionDelegate.commandOf_START(LOGGER, comps);

		} else if (ALL_STOP_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_STOP;
			List<CorbaComponent> comps = getTargetComps(systemDiagram,
					new ComponentComparator(ActionName.ACTION_SHUT_DOWN));
			commands = ComponentActionDelegate.commandOf_STOP(LOGGER, comps);

		} else if (ALL_ACTIVATE_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_ACTIVATE;
			List<CorbaComponent> comps = getTargetComps(systemDiagram,
					new ComponentComparator(ActionName.ACTION_ACTIVATION));
			commands = ComponentActionDelegate.commandOf_ACTIVATE(LOGGER, comps);

		} else if (ALL_DEACTIVATE_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_DEACTIVATE;
			List<CorbaComponent> comps = getTargetComps(systemDiagram,
					new ComponentComparator(ActionName.ACTION_DEACTIVATION));
			commands = ComponentActionDelegate.commandOf_DEACTIVATE(LOGGER, comps);

		} else if (ALL_EXIT_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_EXIT;
			List<CorbaComponent> comps = getTargetComps(systemDiagram,
					new ComponentComparator(ActionName.ACTION_FINALIZE));
			commands = ComponentActionDelegate.commandOf_EXIT(LOGGER, comps);

		}

		if (SystemEditorPreferenceManager.getInstance().isConfirmComponentAction()
				|| ALL_EXIT_ID.equals(command.getId())) {
			// アクションの実行確認が有効な場合
			boolean isOk = MessageDialog.openConfirm(editor.getSite().getShell(), TITLE_CONFIRM_DIALOG, confirmMessage);
			if (!isOk) {
				return null;
			}
		}

		this.actionDelegate.run(commands);

		return null;
	}

	private List<CorbaComponent> getTargetComps(SystemDiagram systemDiagram, ComponentComparator comparator) {
		List<CorbaComponent> targetComps = new ArrayList<>();
		for (Component comp : systemDiagram.getComponents()) {
			targetComps.addAll(getTargetComponentList(comp));
		}
		Collections.sort(targetComps, comparator);
		return targetComps;
	}

}
