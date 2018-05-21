package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.to_cid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.systemeditor.ui.editor.command.ECMoveLineCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECConnectionEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.LineMoveHandle;
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
 * EC関連のベンドポイント編集のEditPolicy
 */
public class ECConnectionBendpointEditPolicy extends SelectionHandlesEditPolicy implements PropertyChangeListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECConnectionBendpointEditPolicy.class);

	private static final Map<Integer, Point> NULL_CONSTRAINT = Collections.emptyMap();
	private Map<Integer, Point> originalConstraint;

	private boolean isDeleting = false;

	private SystemDiagram diagram;

	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public void setHost(EditPart host) {
		super.setHost(host);
		ECConnectionEditPart.ECConnection conn = (ECConnectionEditPart.ECConnection) getHost().getModel();
		//
		SystemDiagramStore store = SystemDiagramStore.instance(this.diagram);
		SystemDiagramStore.Target target = store.getTarget("ECConnection", conn.getId());
		@SuppressWarnings("unchecked")
		Map<Integer, Point> points = (Map<Integer, Point>) target.getResource(SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP);
		if (points == null) {
			points = new TreeMap<>();
			target.putResource(SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP, points);
		}
		//
		conn.getRoutingConstraint().clear();
		conn.getRoutingConstraint().putAll(points);
	}

	@Override
	public void activate() {
		LOGGER.trace("activate: this=<{}>", to_cid(this));
		super.activate();
		getConnectionFigure().addPropertyChangeListener(Connection.PROPERTY_POINTS, this);
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate: this=<{}>", to_cid(this));
		getConnectionFigure().removePropertyChangeListener(Connection.PROPERTY_POINTS, this);
		super.deactivate();
	}

	@Override
	public void eraseSourceFeedback(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType()) || REQ_CREATE_BENDPOINT.equals(request.getType())) {
			eraseConnectionFeedback((BendpointRequest) request);
		}
	}

	@Override
	public Command getCommand(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
			if (this.isDeleting) {
				return getDeleteBendpointCommand((BendpointRequest) request);
			}
			return getMoveBendpointCommand((BendpointRequest) request);
		}
		if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			return getCreateBendpointCommand((BendpointRequest) request);
		}
		return null;
	}

	@Override
	public void showSourceFeedback(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
			showMoveBendpointFeedback((BendpointRequest) request);
		} else if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
			showCreateBendpointFeedback((BendpointRequest) request);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (getHost().isActive() && getHost().getSelected() != EditPart.SELECTED_NONE) {
			addSelectionHandles();
		}
	}

	@Override
	protected List<LineMoveHandle> createSelectionHandles() {
		List<LineMoveHandle> result = new ArrayList<>();
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
		return (Map<Integer, Point>) getConnectionFigure().getRoutingConstraint();
	}

	protected void setRoutingConstraint(Map<Integer, Point> constraint) {
		getConnectionFigure().setRoutingConstraint(constraint);
	}

	protected void eraseConnectionFeedback(BendpointRequest request) {
		LOGGER.trace("eraseConnectionFeedback: this=<{}> index=<{}> point=<{}>", to_cid(this), request.getIndex(),
				request.getLocation());
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
		this.originalConstraint = getRoutingConstraint();
		if (this.originalConstraint == null) {
			this.originalConstraint = NULL_CONSTRAINT;
		}
		setRoutingConstraint(new TreeMap<Integer, Point>(this.originalConstraint));
	}

	private void showCreateBendpointFeedback(BendpointRequest request) {
		// LOGGER.trace("showCreateBendpointFeedback: this=<{}> index=<{}> point=<{}>", to_cid(this), request.getIndex(),
		// request.getLocation());
		Point p = new Point(request.getLocation());
		getConnectionFigure().translateToRelative(p);
		if (this.originalConstraint == null) {
			saveOriginalConstraint();
		}
		getRoutingConstraint().put(request.getIndex(), p);
	}

	protected void showMoveBendpointFeedback(BendpointRequest request) {
		// LOGGER.trace("showMoveBendpointFeedback: this=<{}> index=<{}> point=<{}>", to_cid(this), request.getIndex(),
		// request.getLocation());
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
		// LOGGER.trace("getCreateBendpointCommand: this=<{}> index=<{}> point=<{}>", to_cid(this), request.getIndex(),
		// request.getLocation());
		return null;
	}

	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		// LOGGER.trace("getDeleteBendpointCommand: this=<{}> index=<{}> point=<{}>", to_cid(this), request.getIndex(),
		// request.getLocation());
		return null;
	}

	protected Command getMoveBendpointCommand(BendpointRequest request) {
		// LOGGER.trace("getMoveBendpointCommand: this=<{}> index=<{}> point=<{}>", to_cid(this), request.getIndex(),
		// request.getLocation());
		ECMoveLineCommand result = new ECMoveLineCommand();
		result.setModel((ECConnectionEditPart.ECConnection) getHost().getModel());
		result.setDiagram(this.diagram);
		Point location = request.getLocation();
		getConnectionFigure().translateToRelative(location);
		result.setLocation(location);
		result.setIndex(request.getIndex());
		return result;
	}

}
