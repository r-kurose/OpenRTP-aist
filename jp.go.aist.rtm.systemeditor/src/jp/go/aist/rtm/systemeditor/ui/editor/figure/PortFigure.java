package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;

/**
 * PortのFigure
 */
public class PortFigure extends PolygonDecoration {

	/** 出力ポートの描画テンプレート */
	public static final PointList P_OUTPORT;
	/** 入力ポートの描画テンプレート */
	public static final PointList P_INPORT;
	/** サービスポートの描画テンプレート */
	public static final PointList P_SVCPORT;

	/** 公開出力ポートの描画テンプレート */
	public static final PointList P_OUTPORT_EXPORTED;
	/** 公開入力ポートの描画テンプレート */
	public static final PointList P_INPORT_EXPORTED;
	/** 公開サービスポートの描画テンプレート */
	public static final PointList P_SVCPORT_EXPORTED;

	/** 出力ポートのスタイル */
	public static final PortFigureStyle S_OUTPORT;
	/** 入力ポートのスタイル */
	public static final PortFigureStyle S_INPORT;
	/** サービスポートのスタイル */
	public static final PortFigureStyle S_SVCPORT;

	static {
		PointList p = new PointList(5);
		p.addPoint(-6, -6);
		p.addPoint(0, -6);
		p.addPoint(6, 0);
		p.addPoint(0, 6);
		p.addPoint(-6, 6);
		P_OUTPORT = p;

		p = new PointList(5);
		p.addPoint(-6, -6);
		p.addPoint(-6, 6);
		p.addPoint(6, 6);
		p.addPoint(0, 0);
		p.addPoint(6, -6);
		P_INPORT = p;

		p = new PointList(5);
		p.addPoint(-6, -6);
		p.addPoint(-6, 6);
		p.addPoint(6, 6);
		p.addPoint(6, -6);
		P_SVCPORT = p;

		p = new PointList(13);
		p.addPoint(6, 0);
		p.addPoint(0, -6);
		p.addPoint(-6, -6);
		p.addPoint(-6, 6);
		p.addPoint(0, 6);
		p.addPoint(6, 0);
		p.addPoint(12, 0);
		p.addPoint(12, 6);
		p.addPoint(18, 6);
		p.addPoint(24, 0);
		p.addPoint(18, -6);
		p.addPoint(12, -6);
		p.addPoint(12, 0);
		P_OUTPORT_EXPORTED = p;

		p = new PointList(13);
		p.addPoint(0, 0);
		p.addPoint(6, -6);
		p.addPoint(-6, -6);
		p.addPoint(-6, 6);
		p.addPoint(6, 6);
		p.addPoint(0, 0);
		p.addPoint(12, 0);
		p.addPoint(12, 6);
		p.addPoint(24, 6);
		p.addPoint(18, 0);
		p.addPoint(24, -6);
		p.addPoint(12, -6);
		p.addPoint(12, 0);
		P_INPORT_EXPORTED = p;

		p = new PointList(13);
		p.addPoint(6, 0);
		p.addPoint(6, -6);
		p.addPoint(-6, -6);
		p.addPoint(-6, 6);
		p.addPoint(6, 6);
		p.addPoint(6, 0);
		p.addPoint(12, 0);
		p.addPoint(12, -6);
		p.addPoint(24, -6);
		p.addPoint(24, 6);
		p.addPoint(12, 6);
		p.addPoint(12, 0);
		P_SVCPORT_EXPORTED = p;

		S_OUTPORT = new PortFigureStyle(ColorConstants.darkGreen, ColorConstants.red);
		S_INPORT = new PortFigureStyle(ColorConstants.darkBlue, ColorConstants.red);
		S_SVCPORT = new PortFigureStyle(ColorConstants.lightBlue, ColorConstants.red);
	}

	private String direction;

	protected void init() {
		setScale(1.0, 1.0);
		setFill(true);
	}

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
			labelString = labelString + (port.getNameL() == null ? "<unknown>" : port.getNameL()) + ""; // \r\nは最後はいらない
		} catch (RuntimeException e) {
			// void
		}

		Label label1 = new Label(labelString);
		tooltip.add(label1);
		return tooltip;
	}

	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
		fireFigureMoved();
	}

	public Rectangle getBaseBounds() {
		return (getParent() != null) ? getParent().getBounds() : new Rectangle();
	}

	/**
	 * ポートのスタイル定義を表します。
	 */
	public static class PortFigureStyle {
		public final Color bg;
		public final Color fg;

		PortFigureStyle(Color bg, Color fg) {
			this.bg = bg;
			this.fg = fg;
		}
	}

}
