package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.gef.commands.Command;

/**
 * 方向を変更するコマンド
 */
public class ChangeDirectionCommand extends Command {

	private Component model;

	private String direction;

	private String oldDirection;

	private ClearLineConstraintCommand clearLineConstraintCommand = new ClearLineConstraintCommand();

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		model.setOutportDirection(direction);

		clearLineConstraintCommand.setModel(model);
		clearLineConstraintCommand.execute();
	}

	/**
	 * 方向を設定する
	 * 
	 * @param direction
	 *            方向
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * 変更対象のモデルを設定する
	 * 
	 * @param model
	 *            変更対象のモデル
	 */
	public void setModel(Component model) {
		this.model = model;
		this.oldDirection = model.getOutportDirection();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		clearLineConstraintCommand.undo();
		model.setOutportDirection(oldDirection);
	}
}
