package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ExportedServicePortFigure;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ServicePortFigure;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * ServicePortのEditPartクラス
 */
public class ServicePortEditPart extends PortEditPart {

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public ServicePortEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	public ServicePort getModel() {
		return (ServicePort) super.getModel();
	}

	@Override
	public void notifyChanged(Notification notification) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (isActive()) {
					refresh();
					refreshTargetConnections();
				}
			}
		});
	}

	@Override
	protected IFigure createFigure() {
		super.createFigure();

		IFigure result = null;
		if (isExported()) {
			result = new ExportedServicePortFigure(getModel()) {
				@Override
				public void setBounds(Rectangle rect) {
					super.setBounds(rect);
					setLabelBounds(getBaseBounds(), rect, getDirection());
				}
			};
		} else {
			result = new ServicePortFigure(getModel()) {
				@Override
				public void setBounds(Rectangle rect) {
					super.setBounds(rect);
					setLabelBounds(getBaseBounds(), rect, getDirection());
				}
			};
		}
		result.setLocation(new Point(0, 0));

		OutPortEditPart.supportAutoCreateConnectorToolMode(getViewer(), result);

		return result;
	}

	@Override
	protected void refreshVisuals() {
		Color color = SystemEditorPreferenceManager.getInstance().getColor(
				SystemEditorPreferenceManager.COLOR_SERVICEPORT_NO_CONNECT);
		if (isConnected()) {
			color = SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_SERVICEPORT_CONNECTED);
		}

		getFigure().setBackgroundColor(color);

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), getFigure().getBounds());
	}

}
