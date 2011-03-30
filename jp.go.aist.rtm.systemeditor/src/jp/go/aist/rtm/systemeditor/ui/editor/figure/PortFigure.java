package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;

/**
 * PortのFigure
 */
public class PortFigure extends PolygonDecoration {

	private String direction;

	/**
	 * 方向を設定する
	 * 
	 * @param direction
	 *            方向
	 */
	public void setDirection(String direction) {
		this.direction = direction;
		setRotation(getRotation(direction));
	}

	private double getRotation(String direction) {
		if (direction.equals(Component.OUTPORT_DIRECTION_RIGHT_LITERAL)) {
			return 0;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_LEFT_LITERAL)) {
			return Math.PI;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_UP_LITERAL)) {
			return Math.PI * 3 / 2;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_DOWN_LITERAL)) {
			return Math.PI / 2;
		}
		return 0;
	}

	/**
	 * 方向を取得する
	 * 
	 * @return 方向
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * データポートのツールチップを取得する
	 * 
	 * @param profile
	 *            モデル
	 * @return ツールチップ
	 */
	public static Panel getServicePortToolTip(Port port) {
		Panel tooltip = new Panel();
		tooltip.setLayoutManager(new StackLayout());

		String labelString = "";
		try {
			labelString = labelString
					+ (port.getNameL() == null ? "<unknown>" : port
							.getNameL()) + ""; // \r\nは最後はいらない
		} catch (RuntimeException e) {
			// void
		}

		Label label1 = new Label(labelString);
		tooltip.add(label1);
		return tooltip;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setLocation(Point p) {
		super.setLocation(p);
		fireFigureMoved();
	}

}
