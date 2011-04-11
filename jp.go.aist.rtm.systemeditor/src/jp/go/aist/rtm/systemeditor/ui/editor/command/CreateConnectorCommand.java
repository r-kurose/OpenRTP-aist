package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.GraphicalConnectorCreateManager;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;

/**
 * コネクタを作成するコマンド
 */
public class CreateConnectorCommand extends Command {
	private GraphicalConnectorCreateManager manager;
	private EditPartViewer viewer;
	private boolean result;

	/**
	 * コンストラクタ
	 * 
	 * @param connector
	 *            コネクタ
	 * @param manager
	 *            manager
	 */
	public CreateConnectorCommand(GraphicalConnectorCreateManager profileCreater) {
		this.manager = profileCreater;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean canExecute() {
		if (manager.getFirst() == null) return false;
		if (manager.getSecond() == null) return false;
		return manager.validate();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		viewer.deselectAll();
		result = manager.createProfileAndConnector(); //成功か失敗かは返さないが、将来必要なら返すように修正すること
	}

	public boolean getResult() {
		return result;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		if (true) {
			// コマンド実行時にprofileCreaterにより、
			// 線を引こうとしてキャンセルすると、execute（）時に実行を取りやめることになるが、
			// こうして取りやめた場合にも、コマンドが実行されたものとしてUndoが有効になってしまう。
			// これをうまく扱えるようになるまでは、UNDOは難しい。。。

			throw new RuntimeException();
		}
	}

	public GraphicalConnectorCreateManager getManager() {
		return manager;
	}
	public void setViewer(EditPartViewer viewer) {
		this.viewer = viewer;
	}

}
