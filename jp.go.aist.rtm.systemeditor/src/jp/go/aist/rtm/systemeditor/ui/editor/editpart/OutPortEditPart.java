package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ExportedOutPortFigure;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.OutPortFigure;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * OutPortのEditPartクラス
 */
public class OutPortEditPart extends PortEditPart {

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
	/**
	 * {@inheritDoc}
	 */
	public OutPort getModel() {
		return (OutPort) super.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyChanged(Notification notification) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (isActive()) {
					refresh();
				}
			}
		});
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		IFigure result = isExported() ? new ExportedOutPortFigure(getModel())
				: new OutPortFigure(getModel());
		result.setLocation(new Point(0, 0));

		supportAutoCreateConnectorToolMode(getViewer(), result);

		return result;
	}

	/**
	 * ポート上で、自動的にコネクタを作成するモードに変更する機能を付加するメソッド
	 */
	public static void supportAutoCreateConnectorToolMode(
			final EditPartViewer viewer, IFigure figure) {
		final EditDomain domain = viewer.getEditDomain();
		final AutoConnectorCreationTool connectionCreationTool = new AutoConnectorCreationTool();

		figure.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
				connectionCreationTool.setFactory(new SimpleFactory(
						PortConnectorImpl.class));
				domain.setActiveTool(connectionCreationTool);
			}

			public void mouseExited(MouseEvent me) {
				if (domain.getActiveTool() == connectionCreationTool
						&& connectionCreationTool.isStartedState() == false) {
					domain.setActiveTool(domain.getDefaultTool());
				}
			}

			public void mouseHover(MouseEvent me) {
			}

			public void mouseMoved(MouseEvent me) {
			}
		});
		figure.addMouseListener(new MouseListener.Stub(){

			@Override
			public void mousePressed(MouseEvent me) {
				// right click
				if (me.button == 3) {
					domain.setActiveTool(domain.getDefaultTool());
				}
			}
			
		});
	}

	/**
	 * コネクタを作成するツール
	 */
	public static class AutoConnectorCreationTool extends
			ConnectionDragCreationTool {
		@Override
		protected void handleFinished() {
			getDomain().setActiveTool(getDomain().getDefaultTool());
			super.handleFinished();
		}

		public boolean isStartedState() {
			return super.isInState(STATE_CONNECTION_STARTED);
		}
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		Color color = SystemEditorPreferenceManager.getInstance().getColor(
				SystemEditorPreferenceManager.COLOR_DATAPORT_NO_CONNECT);

		if (isConnected()) {
			color = SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_DATAPORT_CONNECTED);
		}

		getFigure().setBackgroundColor(color);

		getFigure().setToolTip(
				InPortEditPart.getDataPortToolTip(getModel()));

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), getFigure().getBounds());
	}

}
