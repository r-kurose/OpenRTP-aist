package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EC関連線を削除するコマンド(PartECをデタッチ)
 */
public class DeleteECConnectionCommand extends Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteECConnectionCommand.class);

	private ExecutionContext ec;
	private Component comp;

	@Override
	public void execute() {
		LOGGER.debug("execute: ec=<{}> comp=<{}>", this.ec, this.comp);
		this.ec.removeComponentR(this.comp);
	}

	@Override
	public void undo() {
	}

	public void setPartEC(ECEditPart.PartECEditPart pec) {
		this.ec = pec.getModel().getModel();
		this.comp = pec.getModel().getComponent();
	}

}
