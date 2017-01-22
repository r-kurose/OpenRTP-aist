package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.OutPort;

/**
 * 公開されたOutportのフィギュア（子RTCウィンドウで使用される）
 */
public class ExportedOutPortFigure extends OutPortFigure {

	public ExportedOutPortFigure(OutPort port) {
		super(port);

		setTemplate(PortFigure.P_OUTPORT_EXPORTED);
	}

}
