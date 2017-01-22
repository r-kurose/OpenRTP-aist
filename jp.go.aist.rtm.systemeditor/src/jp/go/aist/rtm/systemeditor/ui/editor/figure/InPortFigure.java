package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.InPort;

/**
 * InPortのFigure
 */
public class InPortFigure extends PortFigure {

	/**
	 * コンストラクタ
	 * 
	 * @param inport
	 *            モデル
	 */
	public InPortFigure(InPort inport) {
		init();

		setTemplate(PortFigure.P_INPORT);

		setBackgroundColor(PortFigure.S_INPORT.bg);
		setForegroundColor(PortFigure.S_INPORT.fg);
	}

}
