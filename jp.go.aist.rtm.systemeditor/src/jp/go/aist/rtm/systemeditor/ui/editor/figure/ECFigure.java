package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECEditPart;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.PointList;

/**
 * ECの Figureを表します。
 */
public abstract class ECFigure extends ComponentChildFigure {

	/** OwnECの描画テンプレート */
	public static final PointList P_OWN_EC;

	/** 選択中 OwnECの描画テンプレート */
	public static final PointList P_OWN_EC_SELECTED;

	/** PartECの描画テンプレート */
	public static final PointList P_PART_EC;

	/** 選択中 PartECの描画テンプレート */
	public static final PointList P_PART_EC_SELECTED;

	/** 非表示 ECの描画テンプレート */
	public static final PointList P_EC_HIDDEN;

	/** 自ECのスタイル */
	public static final FigureStyle S_OWN;
	/** 部分ECのスタイル */
	public static final FigureStyle S_PART;

	static {
		{
			PointList p = new PointList(8);
			p.addPoint(1, -6);
			p.addPoint(12, -6);
			p.addPoint(10, -4);
			p.addPoint(9, -1);
			//
			p.addPoint(9, 1);
			p.addPoint(10, 4);
			p.addPoint(12, 6);
			p.addPoint(1, 6);
			P_OWN_EC = p;
		}
		{
			PointList p = new PointList(8);
			p.addPoint(0, -6);
			p.addPoint(12, -6);
			p.addPoint(10, -4);
			p.addPoint(9, -1);
			//
			p.addPoint(9, 1);
			p.addPoint(10, 4);
			p.addPoint(12, 6);
			p.addPoint(0, 6);
			P_OWN_EC_SELECTED = p;
		}
		{
			PointList p = new PointList(8);
			p.addPoint(1, -6);
			p.addPoint(9, -6);
			p.addPoint(11, -4);
			p.addPoint(12, -1);
			//
			p.addPoint(12, 1);
			p.addPoint(11, 4);
			p.addPoint(9, 6);
			p.addPoint(1, 6);
			P_PART_EC = p;
		}
		{
			PointList p = new PointList(8);
			p.addPoint(0, -6);
			p.addPoint(11, -6);
			p.addPoint(11, -4);
			p.addPoint(12, -1);
			//
			p.addPoint(12, 1);
			p.addPoint(11, 4);
			p.addPoint(9, 6);
			p.addPoint(0, 6);
			P_PART_EC_SELECTED = p;
		}
		{
			PointList p = new PointList(2);
			p.addPoint(0, -6);
			p.addPoint(0, 6);
			P_EC_HIDDEN = p;
		}
		S_OWN = new FigureStyle(ColorConstants.darkBlue, ColorConstants.red);
		S_PART = new FigureStyle(ColorConstants.darkBlue, ColorConstants.red);
	}

	/**
	 * 選択中 ECとして表示します。
	 */
	public abstract void drawAsSelected();

	/**
	 * 非選択中 ECとして表示します。
	 */
	public abstract void drawAsDeselected();

	/**
	 * ECを非表示にします。
	 */
	public abstract void drawAsHidden();

	/**
	 * 選択中の場合は true
	 */
	public abstract boolean isSelected();

	/**
	 * 非表示中の場合は true
	 */
	public abstract boolean isHidden();

	/**
	 * Owned ECの Figureを表します。
	 */
	public static class OwnECFigure extends ECFigure {

		private PointList currentTemplate;

		public OwnECFigure(ECEditPart.OwnEC ec) {
			init();

			setBackgroundColor(ECFigure.S_OWN.bg);
			setForegroundColor(ECFigure.S_OWN.fg);
			setAntialias(1);

			drawAsDeselected();
		}

		@Override
		public void drawAsSelected() {
			this.currentTemplate = P_OWN_EC_SELECTED;
			setTemplate(this.currentTemplate);
			setAlpha(null);
		}

		@Override
		public void drawAsDeselected() {
			this.currentTemplate = P_OWN_EC;
			setTemplate(this.currentTemplate);
			setAlpha(null);
		}

		@Override
		public void drawAsHidden() {
			this.currentTemplate = P_EC_HIDDEN;
			setTemplate(this.currentTemplate);
			setAlpha(0);
		}

		@Override
		public boolean isSelected() {
			return this.currentTemplate == P_OWN_EC_SELECTED;
		}

		@Override
		public boolean isHidden() {
			return this.currentTemplate == P_EC_HIDDEN;
		}

	}

	/**
	 * Participant ECの Figureを表します。
	 */
	public static class PartECFigure extends ECFigure {

		private PointList currentTemplate;

		public PartECFigure(ECEditPart.PartEC ec) {
			init();

			setBackgroundColor(ECFigure.S_PART.bg);
			setForegroundColor(ECFigure.S_PART.fg);
			setAntialias(1);

			drawAsDeselected();
		}

		@Override
		public void drawAsSelected() {
			this.currentTemplate = P_PART_EC_SELECTED;
			setTemplate(this.currentTemplate);
			setAlpha(null);
		}

		@Override
		public void drawAsDeselected() {
			this.currentTemplate = P_PART_EC;
			setTemplate(this.currentTemplate);
			setAlpha(null);
		}

		@Override
		public void drawAsHidden() {
			this.currentTemplate = P_EC_HIDDEN;
			setTemplate(this.currentTemplate);
			setAlpha(0);
		}

		@Override
		public boolean isSelected() {
			return this.currentTemplate == P_PART_EC_SELECTED;
		}

		@Override
		public boolean isHidden() {
			return this.currentTemplate == P_EC_HIDDEN;
		}

	}

}
