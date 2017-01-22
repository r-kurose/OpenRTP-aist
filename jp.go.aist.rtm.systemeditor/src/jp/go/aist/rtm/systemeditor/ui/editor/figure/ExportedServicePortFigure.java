package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

/**
 * 公開されたServiceportのフィギュア（子RTCウィンドウで使用される）
 */
public class ExportedServicePortFigure extends ServicePortFigure {

	public ExportedServicePortFigure(ServicePort port) {
		super(port);

		setTemplate(PortFigure.P_SVCPORT_EXPORTED);
	}

}
