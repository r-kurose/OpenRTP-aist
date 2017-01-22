package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

/**
 * ServicePortのFigure
 */
public class ServicePortFigure extends PortFigure {

	/**
	 * コンストラクタ
	 * 
	 * @param servicePort
	 *            モデル
	 */
	public ServicePortFigure(ServicePort servicePort) {
		init();

		setTemplate(PortFigure.P_SVCPORT);

		setBackgroundColor(PortFigure.S_SVCPORT.bg);
		setForegroundColor(PortFigure.S_SVCPORT.fg);

		setToolTip(getServicePortToolTip(servicePort));
	}

}
