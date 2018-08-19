package jp.go.aist.rtm.systemeditor.ui.handler;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.systemeditor.ui.editor.command.DeleteECConnectionCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECEditPart;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ECコネクションの表示/非表示切替コマンドを実行するハンドラ
 */
public class ECConnectionCommandHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECConnectionCommandHandler.class);

	/** [コマンドID] ECコネクション表示 */
	public static final String SHOW_ECCONN_ID = "rtse.command.ecconn.Show";
	/** [コマンドID] ECコネクション非表示 */
	public static final String HIDE_ECCONN_ID = "rtse.command.ecconn.Hide";
	/** [コマンドID] ECコネクション切断 */
	public static final String DISCONNECT_ECCONN_ID = "rtse.command.ecconn.Disconnect";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();

		if (SHOW_ECCONN_ID.equals(command.getId()) || HIDE_ECCONN_ID.equals(command.getId())) {
			return executeDisplay(event, command.getId());
		} else if (DISCONNECT_ECCONN_ID.equals(command.getId())) {
			return executeDisconnect(event, command.getId());
		}

		return null;
	}

	private Object executeDisplay(ExecutionEvent event, String commandId) {
		IEditorPart part = HandlerUtil.getActiveEditor(event);
		SystemDiagramEditor editor = null;
		if (part instanceof SystemDiagramEditor) {
			editor = (SystemDiagramEditor) part;
		}
		if (editor == null) {
			return null;
		}
		LOGGER.debug("ECConnectionCommandHandler: command=<{}> editor=<{}>", commandId, editor);

		String dispEc = "true";
		if (SHOW_ECCONN_ID.equals(commandId)) {
			dispEc = "true";
		} else if (HIDE_ECCONN_ID.equals(commandId)) {
			dispEc = "false";
		}
		SystemDiagram diagram = editor.getSystemDiagram();
		SystemDiagramStore store = SystemDiagramStore.instance(diagram);
		store.getTarget().set(SystemDiagramStore.ID_DISPLAY_EC_CONN, dispEc);

		return null;
	}

	private Object executeDisconnect(ExecutionEvent event, String commandId) {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		ECEditPart.PartECEditPart ec = null;
		if (selection instanceof StructuredSelection) {
			StructuredSelection ss = (StructuredSelection) selection;
			ec = (ECEditPart.PartECEditPart) ss.getFirstElement();
		}
		if (ec == null) {
			return null;
		}
		LOGGER.debug("ECConnectionCommandHandler: command=<{}> ec=<{}>", commandId, ec);

		DeleteECConnectionCommand cmd = new DeleteECConnectionCommand();
		cmd.setPartEC(ec);
		cmd.execute();

		return null;
	}

}
