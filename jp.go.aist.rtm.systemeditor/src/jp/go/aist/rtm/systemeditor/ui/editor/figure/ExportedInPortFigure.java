package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.InPort;

/**
 * 公開されたInportのフィギュア（子RTCウィンドウで使用される）
 */
public class ExportedInPortFigure extends InPortFigure {

	public ExportedInPortFigure(InPort port) {
		super(port);

		setTemplate(PortFigure.P_INPORT_EXPORTED);
	}

}
