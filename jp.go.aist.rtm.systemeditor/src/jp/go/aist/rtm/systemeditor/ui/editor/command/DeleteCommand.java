package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.concurrent.Callable;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.CompositeComponentHelper;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * Rtcを削除する削除コマンド
 */
public class DeleteCommand extends Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCommand.class);

	private SystemDiagram parent;

	private Component target;

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (!CompositeComponentHelper.openConfirm(target, Messages.getString("DeleteCommand.1"))) {
			return;
		}

		disconnectAll();

		// 開いたエディタを閉じる
		ComponentUtil.closeCompositeComponent(target);

		// 削除実行
		if (parent.getCompositeComponent() != null) {
			renestComposite();
		} else {
			// ダイアグラムからコンポーネントを削除する
			parent.removeComponent(target);
		}

		// 画面更新
		AbstractSystemDiagramEditor ae = ComponentUtil.findEditor(parent);
		if (ae != null) {
			ae.refresh();
		}
	}

	private void renestComposite() {
		TimeoutWrapper wrapper = TimeoutWrapper.asDefault();

		// 自身が複合RTCの子の場合は複合RTCから外す
		{
			Boolean result = wrapper.start(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return parent.getCompositeComponent().removeComponentR(target);
				}
			});
			if (result == null || !result) {
				LOGGER.error("Fail to remove from parent-composite. composite=<{}>", parent.getCompositeComponent());
				return;
			}
		}
		// 親エディタに自身を追加する
		SystemDiagram parentSystemDiagram = parent.getParentSystemDiagram();
		{
			Boolean result = wrapper.start(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					parentSystemDiagram.addComponent(target);
					return Boolean.TRUE;
				}
			});
			if (result == null || !result) {
				LOGGER.error("Fail to add to parent-diagram. diagram=<{}>", parentSystemDiagram);
				return;
			}
		}
		// ネストしている場合はメンバーの再設定が必要
		Component parentComposite = parentSystemDiagram.getCompositeComponent();
		if (parentComposite != null) {
			Boolean result = wrapper.start(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return parentComposite.setComponentsR(parentSystemDiagram.getComponents());
				}
			});
			if (result == null || !result) {
				LOGGER.error("Fail to re-set components to parent-composite. composite=<{}>", parentComposite);
				return;
			}
		}
		// 画面更新
		ComponentUtil.findEditor(parentSystemDiagram).refresh();
	}

	/**
	 * オフラインエディタの場合にだけ、接続をすべて解除する
	 */
	private void disconnectAll() {
		if (target.inOnlineSystemDiagram()) {
			return;
		}
		TimeoutWrapper wrapper = TimeoutWrapper.asDefault();
		for (Port port : target.getPorts()) {
			Boolean result = wrapper.start(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return port.getSynchronizer().disconnectAll();
				}
			});
			if (result == null || !result) {
				LOGGER.error("Fail to disconnect-all. port=<{}>", port);
				return;
			}
			port.disconnectAll();
		}
	}

	/**
	 * 親のシステムダイアグラムを設定する
	 * 
	 * @param parent 親のシステムダイアグラム
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * 変更対象のコンポーネント
	 * 
	 * @param target コンポーネント
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
