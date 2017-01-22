package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.ConnectionDragCreationTool;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コネクタを作成するツール
 */
public class AutoConnectorCreationTool extends ConnectionDragCreationTool {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoConnectorCreationTool.class);

	@Override
	protected void handleFinished() {
		getDomain().setActiveTool(getDomain().getDefaultTool());
		super.handleFinished();
	}

	@Override
	protected boolean handleDragStarted() {
		LOGGER.trace("handleDragStarted: currViewer={} currCommand={} target={}", getCurrentViewer(),
				getCurrentCommand(), getTargetEditPart());
		// ポート接続モード開始
		final SystemDiagramEditPart sd = getSystemDiagramEditPart();
		final EditPart target = getTargetEditPart();
		LOGGER.trace("handleDragStarted: diagram=<{}> target=<{}>", sd, target);
		if (sd != null) {
			sd.setConnectingPortEditPart((PortEditPart) target);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					sd.refreshViewAllConnectablePort();
				}
			});
		}
		return super.handleDragStarted();
	}

	@Override
	protected boolean handleCreateConnection() {
		LOGGER.trace("handleCreateConnection: currViewer={} currCommand={} target={}", getCurrentViewer(),
				getCurrentCommand(), getTargetEditPart());
		// ポート接続モード終了
		final SystemDiagramEditPart sd = getSystemDiagramEditPart();
		LOGGER.trace("handleCreateConnection: diagram=<{}>", sd);
		if (sd != null) {
			sd.setConnectingPortEditPart(null);
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					sd.refreshViewAllConnectablePort();
				}
			});
		}
		return super.handleCreateConnection();
	}

	SystemDiagramEditPart getSystemDiagramEditPart() {
		EditPartViewer viewer = getCurrentViewer();
		List<?> list = viewer.getRootEditPart().getChildren();
		if (!list.isEmpty() && list.get(0) instanceof SystemDiagramEditPart) {
			SystemDiagramEditPart sd = (SystemDiagramEditPart) list.get(0);
			return sd;
		}
		return null;
	}

	@Override
	protected boolean handleDrag() {
		return super.handleDrag();
	}

	@Override
	protected boolean handleMove() {
		return super.handleMove();
	}

	@Override
	protected boolean handleDragInProgress() {
		return super.handleDragInProgress();
	}

	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		return super.handleKeyDown(e);
	}

	@Override
	protected boolean handleKeyUp(KeyEvent e) {
		return super.handleKeyUp(e);
	}

	@Override
	protected void handleKeyTraversed(TraverseEvent event) {
		super.handleKeyTraversed(event);
	}

	@Override
	protected boolean handleEnteredEditPart() {
		return super.handleEnteredEditPart();
	}

	@Override
	protected boolean handleExitingEditPart() {
		return super.handleExitingEditPart();
	}

	@Override
	protected boolean handleFocusGained() {
		return super.handleFocusGained();
	}

	@Override
	protected boolean handleFocusLost() {
		return super.handleFocusLost();
	}

	@Override
	protected boolean handleHover() {
		return super.handleHover();
	}

	@Override
	protected boolean handleHoverStop() {
		return super.handleHoverStop();
	}

	@Override
	protected boolean handleViewerEntered() {
		return super.handleViewerEntered();
	}

	@Override
	protected boolean handleViewerExited() {
		return super.handleViewerExited();
	}

	@Override
	protected void handleSourceDeactivated() {
		super.handleSourceDeactivated();
	}

	public boolean isStartedState() {
		return super.isInState(STATE_CONNECTION_STARTED);
	}

}
