package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * コンポーネントの子要素の Figure
 */
public abstract class ComponentChildFigure extends PolygonDecoration {

	private String direction;

	protected void init() {
		setScale(1.0, 1.0);
		setFill(true);
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
	 * 方向を設定する
	 * 
	 * @param direction
	 *            方向
	 */
	public void setDirection(String direction) {
		this.direction = direction;
		double rotation = getRotation(direction);
		setRotation(rotation);
	}

	/**
	 * 回転角度を算出します
	 * 
	 * @param direction
	 * @return
	 */
	protected double getRotation(String direction) {
		double ret = 0;
		if (direction.equals(Component.OUTPORT_DIRECTION_RIGHT_LITERAL)) {
			ret = 0;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_DOWN_LITERAL)) {
			ret = Math.PI / 2;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_LEFT_LITERAL)) {
			ret = Math.PI;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_UP_LITERAL)) {
			ret = Math.PI * 3 / 2;
		}
		return ret;
	}

	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
		fireFigureMoved();
	}

	public Rectangle getBaseBounds() {
		return (getParent() != null) ? getParent().getBounds()
				: new Rectangle();
	}

	/**
	 * 子要素のスタイル定義を表します。
	 */
	public static class FigureStyle {

		public final Color bg;
		public final Color fg;

		FigureStyle(Color bg, Color fg) {
			this.bg = bg;
			this.fg = fg;
		}

	}

}
