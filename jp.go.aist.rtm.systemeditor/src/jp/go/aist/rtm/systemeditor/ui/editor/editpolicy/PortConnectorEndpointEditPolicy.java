package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.AutoConnectorCreationTool;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortConnectorEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.ReconnectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ポートのEditPolicyクラス
 */
public class PortConnectorEndpointEditPolicy extends ConnectionEndpointEditPolicy {

	private static final Logger LOGGER = LoggerFactory.getLogger(PortConnectorEndpointEditPolicy.class);

	private static final Map<Integer, Point> NULL_CONSTRAINT = Collections.emptyMap();

	private Map<Integer, Point> originalConstraint;

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void showConnectionMoveFeedback(ReconnectRequest request) {
		LOGGER.trace("showConnectionMoveFeedback request=<{}>", request);

		if (originalConstraint == null) {
			saveOriginalConstraint();
		}

		TreeMap<Integer, Point> newConstraint = new TreeMap<Integer, Point>();
		getConnection().setRoutingConstraint(newConstraint);

		// ポート再接続モード開始
		PortConnectorEditPart conn = (PortConnectorEditPart) getHost();
		SystemDiagramEditPart sd = AutoConnectorCreationTool.findSystemDiagramEditPart(conn);
		PortEditPart port = null;
		if (REQ_RECONNECT_SOURCE.equals(request.getType())) {
			port = (PortEditPart) conn.getTarget();
		} else {
			port = (PortEditPart) conn.getSource();
		}
		AutoConnectorCreationTool.showAllConnectablePort(sd, port);

		super.showConnectionMoveFeedback(request);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void eraseConnectionMoveFeedback(ReconnectRequest request) {
		LOGGER.trace("eraseConnectionMoveFeedback request=<{}>", request);

		restoreOriginalConstraint();

		// ポート再接続モード終了
		PortConnectorEditPart conn = (PortConnectorEditPart) getHost();
		SystemDiagramEditPart sd = AutoConnectorCreationTool.findSystemDiagramEditPart(conn);
		AutoConnectorCreationTool.hideAllConnectablePort(sd);

		super.eraseConnectionMoveFeedback(request);
	}

	protected void restoreOriginalConstraint() {
		if (originalConstraint != null) {
			if (originalConstraint == NULL_CONSTRAINT)
				getConnection().setRoutingConstraint(null);
			else
				getConnection().setRoutingConstraint(originalConstraint);
		}
	}

	@SuppressWarnings("unchecked")
	protected void saveOriginalConstraint() {
		originalConstraint = (Map<Integer, Point>) getConnection().getRoutingConstraint();
		if (originalConstraint == null)
			originalConstraint = NULL_CONSTRAINT;
		getConnection().setRoutingConstraint(new TreeMap<Integer, Point>(originalConstraint));
	}

}
