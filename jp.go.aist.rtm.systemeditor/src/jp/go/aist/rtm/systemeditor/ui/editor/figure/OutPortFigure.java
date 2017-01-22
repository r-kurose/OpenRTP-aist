package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.OutPort;

/**
 * OutPortのFigure
 */
public class OutPortFigure extends PortFigure {

	/**
	 * コンストラクタ
	 * 
	 * @param outport
	 *            モデル
	 */
	public OutPortFigure(OutPort outport) {
		init();

		setTemplate(PortFigure.P_OUTPORT);

		setBackgroundColor(PortFigure.S_OUTPORT.bg);
		setForegroundColor(PortFigure.S_OUTPORT.fg);
	}

}
