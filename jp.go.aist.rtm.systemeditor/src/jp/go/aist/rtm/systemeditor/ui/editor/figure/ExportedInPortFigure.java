package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.InPort;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.PointList;

/**
 * 公開されたInportのフィギュア（子RTCウィンドウで使用される）
 *
 */
public class ExportedInPortFigure extends InPortFigure {
	public ExportedInPortFigure(InPort port) {
		super(port);
		setScale(1.0, 1.0);
		setFill(true);
		setTemplate(createPointList());
		setSize(20 + 1, 20 + 1);

		setBackgroundColor(ColorConstants.darkBlue);
		setForegroundColor(ColorConstants.red);

	}

	private PointList createPointList() {
		PointList result = new PointList(13);
		result.addPoint(0, 0);
		result.addPoint(5, -5);
		result.addPoint(-5, -5);
		result.addPoint(-5, 5);
		result.addPoint(5, 5);
		result.addPoint(0, 0);
		result.addPoint(10, 0);
		result.addPoint(10, 5);
		result.addPoint(20, 5);
		result.addPoint(15, 0);
		result.addPoint(20, -5);
		result.addPoint(10, -5);
		result.addPoint(10, 0);

		return result;
	}
}
