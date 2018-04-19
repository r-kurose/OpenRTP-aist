package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * ECコネクションのアンカーを表します。
 */
public class ECAnchor extends ChopboxAnchor implements DirectionableConnectionAnchor {

	public ECAnchor(ECFigure figure) {
		super(figure);
	}

	@Override
	public Point getLocation(Point reference) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBox());
		r.translate(-1, -1);
		r.resize(1, 1);

		getOwner().translateToAbsolute(r);
		float centerX = r.x + 0.5f * r.width;
		float centerY = r.y + 0.5f * r.height;

		return new PrecisionPoint(centerX, centerY);
	}

	@Override
	public ECFigure getOwner() {
		return (ECFigure) super.getOwner();
	}

	@Override
	public String getDirection() {
		return getOwner().getDirection();
	}

}
