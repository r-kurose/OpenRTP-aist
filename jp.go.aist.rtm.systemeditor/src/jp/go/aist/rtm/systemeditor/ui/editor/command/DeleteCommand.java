package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.action.CompositeComponentHelper;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.gef.commands.Command;

/**
 * Rtcを削除する削除コマンド
 */
public class DeleteCommand extends Command {
	private SystemDiagram parent;

	private Component target;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (!CompositeComponentHelper.openConfirm(target, Messages.getString("DeleteCommand.1"))) //$NON-NLS-1$
			return;
		
		disconnectAll();

		// 開いたエディタを閉じる
		ComponentUtil.closeCompositeComponent(target);
		
		// 削除実行
		if (parent.getCompositeComponent()!= null) {
			// 自身が複合コンポーネントの子の場合は複合コンポーネントから外す
			parent.getCompositeComponent().removeComponentR(target);
			// 親エディタに自身を追加する
			SystemDiagram parentSystemDiagram = parent.getParentSystemDiagram();
			parentSystemDiagram.addComponent(target);
			// ネストしている場合はメンバーの再設定が必要
			if (parentSystemDiagram.getCompositeComponent() != null) {
				parentSystemDiagram.getCompositeComponent().setComponentsR(parentSystemDiagram.getComponents());
			}
			// 画面更新
			ComponentUtil.findEditor(parentSystemDiagram).refresh();
		} else {		
			// ダイアグラムからコンポーネントを削除する
			parent.removeComponent(target);
		}
		
		// 画面更新
		ComponentUtil.findEditor(parent).refresh();
	}

	/**
	 * オフラインエディタの場合にだけ、接続をすべて解除する
	 */
	private void disconnectAll() {
		if (target.inOnlineSystemDiagram()) return;
		for (Object obj: target.getPorts()) {
			Port port = (Port) obj;
			port.disconnectAll();
		}
	}

	/**
	 * 親のシステムダイアグラムを設定する
	 * 
	 * @param parent
	 *            親のシステムダイアグラム
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * 変更対象のコンポーネント
	 * 
	 * @param target
	 *            コンポーネント
	 */
	public void setTarget(Component target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDocs}
	 */
	public void undo() {
	}
}
