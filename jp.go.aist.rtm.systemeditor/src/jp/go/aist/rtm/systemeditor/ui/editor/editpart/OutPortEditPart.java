package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ExportedOutPortFigure;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.OutPortFigure;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorImpl;

/**
 * OutPortのEditPartクラス
 */
public class OutPortEditPart extends PortEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(OutPortEditPart.class);

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

		supportAutoCreateConnectorToolMode(getViewer(), result);

		return result;
	}

	/**
	 * ポート上で、自動的にコネクタを作成するモードに変更する機能を付加するメソッド
	 */
	public static void supportAutoCreateConnectorToolMode(final EditPartViewer viewer, IFigure figure) {

		final EditDomain domain = viewer.getEditDomain();
		final AutoConnectorCreationTool connectionCreationTool = new AutoConnectorCreationTool();

		figure.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent me) {
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				connectionCreationTool.setFactory(new SimpleFactory(PortConnectorImpl.class));
				domain.setActiveTool(connectionCreationTool);
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if (domain.getActiveTool() == connectionCreationTool
						&& connectionCreationTool.isStartedState() == false) {
					domain.setActiveTool(domain.getDefaultTool());
				}
			}

			@Override
			public void mouseHover(MouseEvent me) {
			}

			@Override
			public void mouseMoved(MouseEvent me) {
			}
		});

		figure.addMouseListener(new MouseListener.Stub() {
			@Override
			public void mousePressed(MouseEvent me) {
				// right click
				if (me.button == 3) {
					domain.setActiveTool(domain.getDefaultTool());
				}
			}
		});
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
