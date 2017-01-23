package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortGraphicalNodeEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.PortAnchor;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.PortFigure;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * ポートのEditPartクラス
 */
public abstract class PortEditPart extends AbstractEditPart implements NodeEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(PortEditPart.class);

	FloatingLabel portLabel;

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public PortEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
		LOGGER.trace("new: actionRegistry=<{}>", actionRegistry);
	}

	@Override
	protected IFigure createFigure() {
		LOGGER.trace("createFigure");
		portLabel = new FloatingLabel(((AbstractGraphicalEditPart) getParent().getParent()).getFigure());
		portLabel.setText(getPortBaseName());
		portLabel.setSize(30, 10);
		return null;
	}

	String getPortBaseName() {
		String name = getModel().getNameL();
		if (name == null) {
			return "";
		}
		int index = name.lastIndexOf(".");
		if (index != -1) {
			name = name.substring(index + 1);
		}
		return name;
	}

	public void setLabelBounds(Rectangle baseRect, Rectangle rect, String direction) {
		if (portLabel == null) {
			return;
		}
		Rectangle labelRect = portLabel.getTextBounds().getCopy();
		if (Component.OUTPORT_DIRECTION_RIGHT_LITERAL.equals(direction)) {
			Point p = rect.getTopRight();
			labelRect.x = baseRect.x + p.x + 1;
			labelRect.y = baseRect.y + p.y - labelRect.height + (rect.height / 2) - 1;
		} else if (Component.OUTPORT_DIRECTION_LEFT_LITERAL.equals(direction)) {
			Point p = rect.getTopLeft();
			labelRect.x = baseRect.x + p.x - labelRect.width - 1;
			labelRect.y = baseRect.y + p.y - labelRect.height + (rect.height / 2) - 1;
		} else if (Component.OUTPORT_DIRECTION_UP_LITERAL.equals(direction)) {
			Point p = rect.getTop();
			labelRect.x = baseRect.x + p.x - labelRect.width / 2;
			labelRect.y = baseRect.y + p.y - labelRect.height;
		} else if (Component.OUTPORT_DIRECTION_DOWN_LITERAL.equals(direction)) {
			Point p = rect.getBottom();
			labelRect.x = baseRect.x + p.x - labelRect.width / 2;
			labelRect.y = baseRect.y + p.y;
		}
		portLabel.setBounds(labelRect);
	}

	@Override
	protected void createEditPolicies() {
		LOGGER.trace("createEditPolicies");
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new PortGraphicalNodeEditPolicy());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		LOGGER.trace("getSourceConnectionAnchor: this=<{}> editpart=<{}>",
				getModel().getNameL(), connection);
		return new PortAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		LOGGER.trace("getTargetConnectionAnchor: this=<{}> editpart=<{}>",
				getModel().getNameL(), connection);
		return new PortAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		LOGGER.trace("getSourceConnectionAnchor: this=<{}> request=<{}>",
				getModel().getNameL(), request);
		return new PortAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		LOGGER.trace("getTargetConnectionAnchor: this=<{}> request=<{}>",
				getModel().getNameL(), request);
		return new PortAnchor(getFigure());
	}

	@Override
	public PortFigure getFigure() {
		if (invalid) {
			setInvalid(false);
			IFigure newFig = createFigure();
			if (newFig != this.figure) {
				newFig.setParent(this.figure.getParent());
			}
			setFigure(newFig);
			return (PortFigure) figure;
		}
		return (PortFigure) super.getFigure();
	}

	@Override
	protected List<?> getModelTargetConnections() {
		return CompositeFilter.getModelTargetConnections(getModel());
	}

	@Override
	protected List<?> getModelSourceConnections() {
		return CompositeFilter.getModelSourceConnections(getModel());
	}

	@Override
	public Port getModel() {
		return (Port) super.getModel();
	}

	/**
	 * 設定マネージャを監視するリスナ
	 */
	PropertyChangeListener preferenceChangeListener = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			refreshVisuals();
		}
	};

	private boolean invalid = false;

	@Override
	public void activate() {
		LOGGER.trace("activate: this=<{}>", getModel().getNameL());
		super.activate();
		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(preferenceChangeListener);
		if (getModel().eContainer() instanceof ComponentSpecification) {
			getModel().eAdapters().add(new Adapter());
		}
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate: this=<{}>", getModel().getNameL());
		portLabel.deactivate();
		super.deactivate();
		SystemEditorPreferenceManager.getInstance().removePropertyChangeListener(preferenceChangeListener);
	}

	// ポートが公開されているかを返す
	protected boolean isExported() {
		return PortHelper.isExported(getModel());
	}

	protected boolean isConnected() {
		return PortHelper.isConnected(getModel());
	}

	private class Adapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			LOGGER.trace("notifyChanged: this=<{}> msg=<{}>", getModel()
					.getNameL(), msg);
			if (ComponentPackage.eINSTANCE.getPort_ConnectorProfiles().equals(msg.getFeature())
					&& (msg.getEventType() == Notification.ADD || msg.getEventType() == Notification.REMOVE)) {
				if (getModel().eContainer().eContainer() instanceof SystemDiagram) {
					SystemDiagram systemDiagram = (SystemDiagram) getModel().eContainer().eContainer();
					SystemDiagram rootDiagram = systemDiagram.getRootDiagram();
					if (rootDiagram.isConnectorProcessing()) {
						// void
					} else {
						rootDiagram.setConnectorProcessing(true);
						AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(systemDiagram);
						if (editor != null)
							editor.refresh();
					}
					rootDiagram.setConnectorProcessing(false);
				}
			}
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					if (isActive()) {
						refresh();
					}
				}
			});
		}
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	// ターゲットのポートのEditPartが存在しない時に走るときがある。
	@Override
	protected void addSourceConnection(ConnectionEditPart connection, int index) {
		LOGGER.trace(
				"addSourceConnection: this=<{}> connEditpart=<{}> index=<{}>",
				getModel().getNameL(), connection, index);
		// ターゲット側の設定も行う
		PortConnector connectionModel = (PortConnector) connection.getModel();
		PortEditPart targetPart = (PortEditPart) getViewer().getEditPartRegistry().get(connectionModel.getTarget());
		if (targetPart == null)
			return;

		targetPart.primAddTargetConnection(connection, index);
		GraphicalEditPart target = (GraphicalEditPart) connection.getTarget();
		if (target != null)
			target.getTargetConnections().remove(connection);

		GraphicalEditPart source = (GraphicalEditPart) connection.getSource();
		if (source != null)
			source.getSourceConnections().remove(connection);

		connection.setSource(null);
		connection.setTarget(targetPart);
		targetPart.fireTargetConnectionAdded(connection, index);

		// 元々のソース側の設定を行う
		primAddSourceConnection(connection, index);

		connection.setSource(this);
		fireSourceConnectionAdded(connection, index);

		connection.activate();
	}

	@Override
	protected void addTargetConnection(ConnectionEditPart connection, int index) {
		LOGGER.trace(
				"addTargetConnection: this=<{}> connEditpart=<{}> index=<{}>",
				getModel().getNameL(), connection, index);
		// ソース側の設定も行う
		PortConnector connectionModel = (PortConnector) connection.getModel();
		PortEditPart sourcePart = (PortEditPart) getViewer().getEditPartRegistry().get(connectionModel.getSource());
		if (sourcePart == null)
			return;

		sourcePart.primAddSourceConnection(connection, index);
		GraphicalEditPart source = (GraphicalEditPart) connection.getSource();
		if (source != null)
			source.getSourceConnections().remove(connection);

		GraphicalEditPart target = (GraphicalEditPart) connection.getTarget();
		if (target != null)
			target.getTargetConnections().remove(connection);

		connection.setTarget(null);
		connection.setSource(sourcePart);
		sourcePart.fireSourceConnectionAdded(connection, index);

		// 元々のターゲット側の設定を行う
		primAddTargetConnection(connection, index);

		connection.setTarget(this);
		fireTargetConnectionAdded(connection, index);

		connection.activate();
	}

	@Override
	protected void refreshSourceConnections() {
		// LOGGER.trace("refreshSourceConnections: this=<{}>",
		// getModel().getNameL());
		int i;
		ConnectionEditPart editPart;
		Object model;

		Map<Object, ConnectionEditPart> modelToEditPart = new HashMap<>();
		List<?> editParts = getSourceConnections();

		List<?> modelObjects = getModelSourceConnections();
		if (modelObjects == null)
			modelObjects = new ArrayList<>();

		List<ConnectionEditPart> trash = new ArrayList<>();

		for (i = 0; i < editParts.size(); i++) {
			editPart = (ConnectionEditPart) editParts.get(i);
			model = editPart.getModel();
			if (modelObjects.contains(model)) {
				modelToEditPart.put(model, editPart);
			} else {
				trash.add(editPart);
			}
		}
		// Add new EditParts
		for (i = 0; i < modelObjects.size(); i++) {
			model = modelObjects.get(i);
			if (!modelToEditPart.containsKey(model)) {
				editPart = createOrFindConnection(model);
				addSourceConnection(editPart, 0);
			}
		}
		// Remove the remaining EditParts
		for (i = 0; i < trash.size(); i++)
			removeSourceConnection((ConnectionEditPart) trash.get(i));
	}

	@Override
	protected void refreshTargetConnections() {
		// LOGGER.trace("refreshTargetConnections: this=<{}>",
		// getModel().getNameL());
		int i;
		ConnectionEditPart editPart;
		Object model;

		Map<Object, ConnectionEditPart> mapModelToEditPart = new HashMap<>();
		List<?> connections = getTargetConnections();

		List<?> modelObjects = getModelTargetConnections();
		if (modelObjects == null)
			modelObjects = new ArrayList<>();

		List<ConnectionEditPart> trash = new ArrayList<>();

		for (i = 0; i < connections.size(); i++) {
			editPart = (ConnectionEditPart) connections.get(i);
			model = editPart.getModel();
			if (modelObjects.contains(model)) {
				mapModelToEditPart.put(model, editPart);
			} else {
				trash.add(editPart);
			}
		}
		// Add new EditParts
		for (i = 0; i < modelObjects.size(); i++) {
			model = modelObjects.get(i);
			if (!mapModelToEditPart.containsKey(model)) {
				editPart = createOrFindConnection(model);
				addTargetConnection(editPart, 0);
			}
		}
		// Remove the remaining Connection EditParts
		for (i = 0; i < trash.size(); i++)
			removeTargetConnection((ConnectionEditPart) trash.get(i));
	}

}
