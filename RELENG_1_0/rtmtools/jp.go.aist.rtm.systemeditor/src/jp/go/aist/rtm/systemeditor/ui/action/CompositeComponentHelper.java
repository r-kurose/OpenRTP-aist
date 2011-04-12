package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;

/**
 * 複合コンポーネントの処理に関するヘルパークラス
 *
 */
public class CompositeComponentHelper {

	private static final String CONFIRM_TITLE = Messages.getString("CompositeComponentHelper.confirm.title"); // Confirm

	/**
	 * 子ウィンドウが開いているときに削除または解除を行うかを確認する
	 * @param target
	 * @param msg
	 * @return
	 */
	public static boolean openConfirm(Component target, String msg) {
		if (!target.isCompositeComponent()) return true;
		SystemDiagram childDiagram = target.getChildSystemDiagram();
		if (childDiagram == null) return true;
		IEditorPart editor = ComponentUtil.findEditor(childDiagram);
		if (editor == null) return true;
		
		return MessageDialog.openConfirm(editor.getSite()
						.getShell(), CONFIRM_TITLE, msg);
	}

	/**
	 * 同期スレッドが停止しているときに手動で同期を実行する
	 * @param parent	ルートのシステムダイアグラム
	 */
	public static void synchronizeAll(SystemDiagram parent) {
		if (parent == null) return;
		parent.synchronizeManually();
	}

	/**
	 * 同期スレッドが停止しているときにルートのシステムダイアグラムから手動で同期を実行する
	 * @param parent	変更のあったコンポーネント
	 */
	public static void synchronizeAll(Component parent) {
		SystemDiagram childDiagram = parent.getChildSystemDiagram();
		synchronizeAll(childDiagram.getRootDiagram());
	}
	

	/**
	 * 同期スレッドが停止しているときに手動で同期を実行する
	 * @param source 同期対象のポート
	 */
	public static void synchronizeManually(Port source) {
		if (source.eContainer() == null) return;
		if (!(source.eContainer().eContainer() instanceof SystemDiagram)) return;
		SystemDiagram diagram = (SystemDiagram) source.eContainer().eContainer();
		diagram.synchronizeManually();
	}

}
