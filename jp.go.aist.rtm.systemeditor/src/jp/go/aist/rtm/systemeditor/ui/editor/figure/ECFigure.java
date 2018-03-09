package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.PointList;

/**
 * ECの Figureを表します。
 */
public class ECFigure extends ComponentChildFigure {

	/** ECの描画テンプレート */
	public static final PointList P_EC;

	/** 選択中 ECの描画テンプレート */
	public static final PointList P_EC_SELECTED;

	/** 自ECのスタイル */
	public static final FigureStyle S_OWN;
	/** 部分ECのスタイル */
	public static final FigureStyle S_PART;

	static {
		PointList p = new PointList(4);
		p.addPoint(4, -6);
		p.addPoint(14, -6);
		p.addPoint(14, 6);
		p.addPoint(4, 6);
		P_EC = p;

		p = new PointList(4);
		p.addPoint(0, -6);
		p.addPoint(14, -6);
		p.addPoint(14, 6);
		p.addPoint(0, 6);
		P_EC_SELECTED = p;

		S_OWN = new FigureStyle(ColorConstants.darkBlue, ColorConstants.red);
		S_PART = new FigureStyle(ColorConstants.darkBlue, ColorConstants.red);
	}

	public ECFigure(ExecutionContext ec) {
		init();

		// TODO
		setBackgroundColor(ECFigure.S_OWN.bg);
		setForegroundColor(ECFigure.S_OWN.fg);
	}

	/**
	 * Owned ECの Figureを表します。
	 */
	public static class OwnECFigure extends ECFigure {

		public OwnECFigure(ExecutionContext ec) {
			super(ec);
			setTemplate(P_EC);
		}

	}

	/**
	 * Participant ECの Figureを表します。
	 */
	public static class PartECFigure extends ECFigure {

		public PartECFigure(ExecutionContext ec) {
			super(ec);
			setTemplate(P_EC);
		}

	}

	/**
	 * 選択中の Owned ECの Figureを表します。
	 */
	public static class SelectedOwnECFigure extends OwnECFigure {

		public SelectedOwnECFigure(ExecutionContext ec) {
			super(ec);
			setTemplate(P_EC_SELECTED);
		}

	}

	/**
	 * 選択中の Participant ECの Figureを表します。
	 */
	public static class SelectedPartECFigure extends PartECFigure {

		public SelectedPartECFigure(ExecutionContext ec) {
			super(ec);
			setTemplate(P_EC_SELECTED);
		}

	}

}
