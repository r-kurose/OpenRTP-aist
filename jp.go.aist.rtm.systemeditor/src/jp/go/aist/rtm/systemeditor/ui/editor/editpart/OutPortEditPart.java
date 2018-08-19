package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ExportedOutPortFigure;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.OutPortFigure;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OutPortのEditPartクラス
 */
public class OutPortEditPart extends PortEditPart {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OutPortEditPart.class);

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public OutPortEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	public OutPort getModel() {
		return (OutPort) super.getModel();
	}

	@Override
	public void notifyChanged(Notification notification) {
		LOGGER.trace("notifyChanged: this=<{}> notification=<{}>", getModel()
				.getNameL(), notification);
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (isActive()) {
					refresh();
				}
			}
		});
	}

	@Override
	protected IFigure createFigure() {
		super.createFigure();

		IFigure result = null;
		if (isExported()) {
			result = new ExportedOutPortFigure(getModel()) {
				@Override
				public void setBounds(Rectangle rect) {
					super.setBounds(rect);
					setLabelBounds(getBaseBounds(), rect, getDirection());
				}
			};
		} else {
			result = new OutPortFigure(getModel()) {
				@Override
				public void setBounds(Rectangle rect) {
					super.setBounds(rect);
					setLabelBounds(getBaseBounds(), rect, getDirection());
				}
			};
		}
		result.setLocation(new Point(0, 0));

		AutoConnectorCreationTool.fetchTool(this, result);

		return result;
	}

	@Override
	protected void refreshVisuals() {
		Color color = SystemEditorPreferenceManager.getInstance()
				.getColor(SystemEditorPreferenceManager.COLOR_DATAPORT_NO_CONNECT);
		if (isConnected()) {
			color = SystemEditorPreferenceManager.getInstance()
					.getColor(SystemEditorPreferenceManager.COLOR_DATAPORT_CONNECTED);
		}
		getFigure().setBackgroundColor(color);
		getFigure().setToolTip(InPortEditPart.getDataPortToolTip(getModel()));
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), getFigure().getBounds());
	}

}
