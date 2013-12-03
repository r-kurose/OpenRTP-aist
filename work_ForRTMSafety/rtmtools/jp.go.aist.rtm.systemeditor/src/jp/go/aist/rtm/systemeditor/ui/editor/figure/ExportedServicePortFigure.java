package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.PointList;

/**
 * 公開されたServiceportのフィギュア（子RTCウィンドウで使用される）
 *
 */
public class ExportedServicePortFigure extends ServicePortFigure {
	public ExportedServicePortFigure(ServicePort port) {
		super(port);
		setScale(1.0, 1.0);
		setFill(true);
		setTemplate(createPointList());
		setSize(20 + 1, 20 + 1);

		setBackgroundColor(ColorConstants.lightBlue);
		setForegroundColor(ColorConstants.red);
	}

	private PointList createPointList() {
		PointList result = new PointList(13);
		result.addPoint(5, 0);
		result.addPoint(5, -5);
		result.addPoint(-5, -5);
		result.addPoint(-5, 5);
		result.addPoint(5, 5);
		result.addPoint(5, 0);

		result.addPoint(10, 0);
		result.addPoint(10, -5);
		result.addPoint(20, -5);
		result.addPoint(20, 5);
		result.addPoint(10, 5);
		result.addPoint(10, 0);

		return result;
	}

}
