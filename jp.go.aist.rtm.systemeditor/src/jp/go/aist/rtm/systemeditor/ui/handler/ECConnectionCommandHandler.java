package jp.go.aist.rtm.systemeditor.ui.handler;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		IEditorPart part = HandlerUtil.getActiveEditor(event);
		SystemDiagramEditor editor = null;
		if (part instanceof SystemDiagramEditor) {
			editor = (SystemDiagramEditor) part;
		}
		LOGGER.debug("ECConnectionCommandHandler: command=<{}> editor=<{}>", command.getId(), editor);
		if (editor == null) {
			return null;
		}

		String dispEc = "true";
		if (SHOW_ECCONN_ID.equals(command.getId())) {
			dispEc = "true";
		} else if (HIDE_ECCONN_ID.equals(command.getId())) {
			dispEc = "false";
		}
		SystemDiagram diagram = editor.getSystemDiagram();
		SystemDiagramStore store = SystemDiagramStore.instance(diagram);
		store.set(SystemDiagramStore.ID_DISPLAY_EC_CONN, dispEc);

		return null;
	}

}
