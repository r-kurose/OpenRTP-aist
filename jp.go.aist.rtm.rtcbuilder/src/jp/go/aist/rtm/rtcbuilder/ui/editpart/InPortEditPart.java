package jp.go.aist.rtm.rtcbuilder.ui.editpart;

import jp.go.aist.rtm.rtcbuilder.model.component.DataInPort;
import jp.go.aist.rtm.rtcbuilder.ui.figure.ComponentFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.InPortFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.PortFigureBase;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * InPortのEditPartクラス
 */
public class InPortEditPart extends PortEditPartBase {

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		ComponentFigure parentFigure = (ComponentFigure)((ComponentEditPart)this.getParent()).getFigure();
		int direction = this.getModel().getDirection().getValue();
		PortFigureBase result = new InPortFigure(getModel(), direction,
				new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_DATAPORT)));

		int index = this.getModel().getIndex();

		return modifyPosition(parentFigure, direction, index, result);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public DataInPort getModel() {
		return (DataInPort) super.getModel();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		InPortFigure inport = (InPortFigure)getFigure();
		originalChildren = inport.getParent().getChildren();
		super.refreshVisuals();
	}
}
