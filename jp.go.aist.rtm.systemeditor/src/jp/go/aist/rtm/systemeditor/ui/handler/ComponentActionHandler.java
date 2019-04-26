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
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

public class ComponentActionHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentActionHandler.class);

	/** [コマンドID] コンポーネント活性 */
	public static final String ACTIVATE_ID = "rtse.command.component.Activate";
	/** [コマンドID] コンポーネント非活性 */
	public static final String DEACTIVATE_ID = "rtse.command.component.Deactivate";
	/** [コマンドID] コンポーネントリセット */
	public static final String RESET_ID = "rtse.command.component.Reset";
	/** [コマンドID] コンポーネント終了 */
	public static final String EXIT_ID = "rtse.command.component.Exit";

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_ACTIVATE = Messages.getString("ComponentActionHandler.confirm.activate");
	static final String MSG_CONFIRM_DEACTIVATE = Messages.getString("ComponentActionHandler.confirm.deactivate");
	static final String MSG_CONFIRM_RESET = Messages.getString("ComponentActionHandler.confirm.reset");
	static final String MSG_CONFIRM_EXIT = Messages.getString("ComponentActionHandler.confirm.exit");
	static final String MSG_CONFIRM_UNKNOWN = Messages.getString("ComponentActionHandler.confirm.unknown");

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
		LOGGER.trace("ComponentActionHandler: command=<{}> components=<{}>", command.getId(), components);
		if (components.isEmpty()) {
			return null;
		}

		this.actionDelegate = new ComponentActionDelegate();
		this.actionDelegate.setActivePart(null, part);

		String confirmMessage = MSG_CONFIRM_UNKNOWN;
		List<ComponentActionDelegate.Command> commands = new ArrayList<>();

		if (ACTIVATE_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_ACTIVATE;
			commands = ComponentActionDelegate.commandOf_ACTIVATE(LOGGER, components);

		} else if (DEACTIVATE_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_DEACTIVATE;
			commands = ComponentActionDelegate.commandOf_DEACTIVATE(LOGGER, components);

		} else if (RESET_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_RESET;
			commands = ComponentActionDelegate.commandOf_RESET(LOGGER, components);

		} else if (EXIT_ID.equals(command.getId())) {
			confirmMessage = MSG_CONFIRM_EXIT;
			commands = ComponentActionDelegate.commandOf_EXIT(LOGGER, components);

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

	/** 指定RTCを起点にアクション実行の対象となるRTCリストを取得します */
	public static List<CorbaComponent> getTargetComponentList(Component comp) {
		List<CorbaComponent> ret = new ArrayList<>();
		if (comp instanceof CorbaComponent) {
			// 子RTCは親への操作時にミドルウェア側で処理されるよう変更されたので
			// ダイアグラム上のRTCのみ対象とする
			ret.add((CorbaComponent) comp);
		} else if (comp.isCompositeComponent() && comp.isGroupingCompositeComponent()) {
			// Grouping複合RTCの場合は子RTCを対象とする
			for (Component c : comp.getComponents()) {
				List<CorbaComponent> children = getTargetComponentList(c);
				ret.addAll(children);
			}
		}
		return ret;
	}

}
