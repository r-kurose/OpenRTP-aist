package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.go.aist.rtm.systemeditor.ui.editor.command.MoveLineCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.LineMoveHandle;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コネクタのベンドポイント編集のEditPolicyクラス
 */
public class PortConnectorBendpointEditPolicy extends
		SelectionHandlesEditPolicy implements PropertyChangeListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PortConnectorBendpointEditPolicy.class);

	private static final Map<Integer, Point> NULL_CONSTRAINT = Collections
			.emptyMap();

	private Map<Integer, Point> originalConstraint;

	private boolean isDeleting = false;

	private SystemDiagram diagram;
	private String connectorId;

	/**
	 * 編集対象のダイアグラムを設定します。
	 * 
	 * @param diagram
	 *            編集対象のダイアグラム
	 */
	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public void setHost(EditPart host) {
		LOGGER.trace("setHost: host=<{}>", host);
		super.setHost(host);
		// コネクタの描画開始時にベンドポイントを初期設定
		// (プロファイル読込によるベンドポイント初期値はダイアグラムに格納)
		PortConnector conn = (PortConnector) getHost().getModel();
		this.connectorId = conn.getConnectorProfile().getConnectorId();
		conn.getRoutingConstraint().clear();
		conn.getRoutingConstraint().putAll(
				this.diagram
						.getPortConnectorRoutingConstraint(this.connectorId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activate() {
		LOGGER.trace("activate: this=<{}>", this.connectorId);
		super.activate();
		getConnectionFigure().addPropertyChangeListener(
				Connection.PROPERTY_POINTS, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivate() {
		LOGGER.trace("deactivate: this=<{}>", this.connectorId);
		getConnectionFigure().removePropertyChangeListener(
				Connection.PROPERTY_POINTS, this);
		super.deactivate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eraseSourceFeedback(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())
				|| REQ_CREATE_BENDPOINT.equals(request.getType())) {
			eraseConnectionFeedback((BendpointRequest) request);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command getCommand(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
			if (isDeleting) {
				return getDeleteBendpointCommand((BendpointRequest) request);
			}
			return getMoveBendpointCommand((BendpointRequest) request);
		}
		if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			return getCreateBendpointCommand((BendpointRequest) request);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showSourceFeedback(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
			showMoveBendpointFeedback((BendpointRequest) request);
		} else if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			showCreateBendpointFeedback((BendpointRequest) request);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// LOGGER.trace("propertyChange: this=<{}> host.selected=<{}> host.active=<{}> event=<{}>",this.connectorId,
		// getHost().getSelected(),getHost().isActive(), evt);
		if (getHost().isActive()
				&& getHost().getSelected() != EditPart.SELECTED_NONE) {
			addSelectionHandles();
		}
	}

	@Override
	protected void addSelectionHandles() {
		// LOGGER.trace("addSelectionHandles: this=<{}> host.selected=<{}> host.active=<{}>",this.connectorId,
		// getHost().getSelected(), getHost().isActive());
		super.addSelectionHandles();
	}

	@Override
	protected void removeSelectionHandles() {
		// LOGGER.trace("removeSelectionHandles: this=<{}> host.selected=<{}> host.active=<{}>",this.connectorId,
		// getHost().getSelected(), getHost().isActive());
		super.removeSelectionHandles();
	}

	@Override
	protected List<LineMoveHandle> createSelectionHandles() {
		List<LineMoveHandle> result = new ArrayList<LineMoveHandle>();
		ConnectionEditPart connEP = (ConnectionEditPart) getHost();
		PointList points = getConnectionFigure().getPoints();
		if (points.size() > 2) {
			for (int i = 1; i < points.size() - 2; i++) {
				result.add(new LineMoveHandle(connEP, i));
			}
		}
		return result;
	}

	protected Connection getConnectionFigure() {
		return (Connection) ((ConnectionEditPart) getHost()).getFigure();
	}

	@SuppressWarnings("unchecked")
	protected Map<Integer, Point> getRoutingConstraint() {
		return (Map<Integer, Point>) getConnectionFigure()
				.getRoutingConstraint();
	}

	protected void setRoutingConstraint(Map<Integer, Point> constraint) {
		getConnectionFigure().setRoutingConstraint(constraint);
	}

	protected void eraseConnectionFeedback(BendpointRequest request) {
		LOGGER.trace(
				"eraseConnectionFeedback: this=<{}> index=<{}> point=<{}>",
				this.connectorId, request.getIndex(), request.getLocation());
		if (this.originalConstraint != null) {
			if (this.originalConstraint == NULL_CONSTRAINT) {
				setRoutingConstraint(null);
			} else {
				setRoutingConstraint(this.originalConstraint);
			}
		}
		this.originalConstraint = null;
	}

	protected void saveOriginalConstraint() {
		originalConstraint = getRoutingConstraint();
		if (originalConstraint == null) {
			originalConstraint = NULL_CONSTRAINT;
		}
		setRoutingConstraint(new TreeMap<Integer, Point>(originalConstraint));
	}

	private void showCreateBendpointFeedback(BendpointRequest request) {
		LOGGER.trace(
				"showCreateBendpointFeedback: this=<{}> index=<{}> point=<{}>",
				this.connectorId, request.getIndex(), request.getLocation());
		Point p = new Point(request.getLocation());
		getConnectionFigure().translateToRelative(p);
		if (this.originalConstraint == null) {
			saveOriginalConstraint();
		}
		getRoutingConstraint().put(request.getIndex(), p);
	}

	protected void showMoveBendpointFeedback(BendpointRequest request) {
		LOGGER.trace(
				"showMoveBendpointFeedback: this=<{}> index=<{}> point=<{}>",
				this.connectorId, request.getIndex(), request.getLocation());
		Point p = new Point(request.getLocation());
		if (this.isDeleting) {
			this.isDeleting = false;
			eraseSourceFeedback(request);
		}
		if (this.originalConstraint == null) {
			saveOriginalConstraint();
		}
		getConnectionFigure().translateToRelative(p);
		Map<Integer, Point> constraint = getRoutingConstraint();
		constraint.put(request.getIndex(), p);
		setRoutingConstraint(constraint);
	}

	protected Command getCreateBendpointCommand(BendpointRequest request) {
		LOGGER.trace(
				"getCreateBendpointCommand: this=<{}> index=<{}> point=<{}>",
				this.connectorId, request.getIndex(), request.getLocation());
		return null;
	}

	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		LOGGER.trace(
				"getDeleteBendpointCommand: this=<{}> index=<{}> point=<{}>",
				this.connectorId, request.getIndex(), request.getLocation());
		return null;
	}

	protected Command getMoveBendpointCommand(BendpointRequest request) {
		LOGGER.trace(
				"getMoveBendpointCommand: this=<{}> index=<{}> point=<{}>",
				this.connectorId, request.getIndex(), request.getLocation());
		MoveLineCommand result = new MoveLineCommand();
		result.setModel((PortConnector) getHost().getModel());
		result.setDiagram(this.diagram);
		Point location = request.getLocation();
		getConnectionFigure().translateToRelative(location);
		result.setLocation(location);
		result.setIndex(request.getIndex());
		return result;
	}

}
