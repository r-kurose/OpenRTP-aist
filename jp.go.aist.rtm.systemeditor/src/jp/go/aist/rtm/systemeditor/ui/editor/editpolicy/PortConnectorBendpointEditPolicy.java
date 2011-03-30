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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

/**
 * コネクタのベンドポイント編集のEditPolicyクラス
 */
public class PortConnectorBendpointEditPolicy extends
		SelectionHandlesEditPolicy implements PropertyChangeListener {

	private static final Map<Integer, Point> NULL_CONSTRAINT = Collections.emptyMap();

	private Map<Integer, Point> originalConstraint;

	private boolean isDeleting = false;

	/**
	 * {@inheritDoc}
	 */
	public void activate() {
		super.activate();
		getConnection().addPropertyChangeListener(Connection.PROPERTY_POINTS,
				this);
	}

	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		getConnection().removePropertyChangeListener(
				Connection.PROPERTY_POINTS, this);
		super.deactivate();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void eraseConnectionFeedback(BendpointRequest request) {
		restoreOriginalConstraint();
		originalConstraint = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void eraseSourceFeedback(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())
				|| REQ_CREATE_BENDPOINT.equals(request.getType()))
			eraseConnectionFeedback((BendpointRequest) request);
	}

	/**
	 * {@inheritDoc}
	 */
	public Command getCommand(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
			if (isDeleting)
				return getDeleteBendpointCommand((BendpointRequest) request);
			return getMoveBendpointCommand((BendpointRequest) request);
		}
		if (REQ_CREATE_BENDPOINT.equals(request.getType()))
			return getCreateBendpointCommand((BendpointRequest) request);
		return null;
	}

	protected Connection getConnection() {
		return (Connection) ((ConnectionEditPart) getHost()).getFigure();
	}

	/**
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (getHost().getSelected() != EditPart.SELECTED_NONE)
			addSelectionHandles();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void restoreOriginalConstraint() {
		if (originalConstraint != null) {
			if (originalConstraint == NULL_CONSTRAINT)
				getConnection().setRoutingConstraint(null);
			else
				getConnection().setRoutingConstraint(originalConstraint);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	protected void saveOriginalConstraint() {
		originalConstraint = (Map<Integer, Point>) getConnection().getRoutingConstraint();
		if (originalConstraint == null)
			originalConstraint = NULL_CONSTRAINT;
		getConnection().setRoutingConstraint(
				new TreeMap<Integer, Point>(originalConstraint));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	private void showCreateBendpointFeedback(BendpointRequest request) {
		Point p = new Point(request.getLocation());
		getConnection().translateToRelative(p);
		if (originalConstraint == null)	saveOriginalConstraint();
		((Map<Integer, Point>) getConnection().getRoutingConstraint())
			.put(request.getIndex(), p);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	protected void showMoveBendpointFeedback(BendpointRequest request) {
		Point p = new Point(request.getLocation());
		if (isDeleting) {
			isDeleting = false;
			eraseSourceFeedback(request);
		}
		if (originalConstraint == null)
			saveOriginalConstraint();
		Map<Integer, Point> constraint = (Map<Integer, Point>) getConnection().getRoutingConstraint();
		getConnection().translateToRelative(p);

		constraint.put(request.getIndex(), p);
		getConnection().setRoutingConstraint(constraint);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showSourceFeedback(Request request) {
		if (REQ_MOVE_BENDPOINT.equals(request.getType()))
			showMoveBendpointFeedback((BendpointRequest) request);
		else if (REQ_CREATE_BENDPOINT.equals(request.getType()))
			showCreateBendpointFeedback((BendpointRequest) request);
	}

	protected Command getCreateBendpointCommand(BendpointRequest request) {
		return null;
	}

	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		return null;
	}

	protected Command getMoveBendpointCommand(BendpointRequest request) {
		MoveLineCommand result = new MoveLineCommand();
		result
				.setModel((PortConnector) getHost()
						.getModel());
		Point location = request.getLocation();
		getConnection().translateToRelative(location);
		
		result.setLocation(location);
		result.setIndex(request.getIndex());

		return result;
	}

	protected List<LineMoveHandle> createSelectionHandles() {
		List<LineMoveHandle> result = new ArrayList<LineMoveHandle>();
		ConnectionEditPart connEP = (ConnectionEditPart) getHost();
		PointList points = getConnection().getPoints();

		if (points.size() > 2) {
			for (int i = 1; i < points.size() - 2; i++) {
				result.add(new LineMoveHandle(connEP, i));
			}
		}

		return result;
	}

}
