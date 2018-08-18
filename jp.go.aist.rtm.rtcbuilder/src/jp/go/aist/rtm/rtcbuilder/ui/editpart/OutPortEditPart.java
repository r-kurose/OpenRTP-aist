package jp.go.aist.rtm.rtcbuilder.ui.editpart;

import jp.go.aist.rtm.rtcbuilder.model.component.DataOutPort;
import jp.go.aist.rtm.rtcbuilder.ui.figure.ComponentFigure;
import jp.go.aist.rtm.rtcbuilder.ui.figure.OutPortFigure;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * OutPortのEditPartクラス
 */
public class OutPortEditPart extends PortEditPartBase {

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		ComponentFigure parentFigure = (ComponentFigure)((ComponentEditPart)this.getParent()).getFigure();
		int direction = this.getModel().getDirection().getValue();
		OutPortFigure result = new OutPortFigure(getModel(), direction,
				new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_DATAPORT)));
		int index = this.getModel().getIndex();

		return modifyPosition(parentFigure, direction, index, result);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public DataOutPort getModel() {
		return (DataOutPort) super.getModel();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		OutPortFigure outport = (OutPortFigure)getFigure();
		originalChildren = outport.getParent().getChildren();
		super.refreshVisuals();
	}

}
