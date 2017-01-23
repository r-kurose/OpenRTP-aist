package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.EditableManhattanConnectorRouter;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortConnectorBendpointEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortConnectorEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortConnectorEndpointEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コネクタのEditPartクラス
 */
public class PortConnectorEditPart extends AbstractConnectionEditPart {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PortConnectorEditPart.class);

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	private SystemDiagram diagram;
	private String connectorId;

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 */
	public PortConnectorEditPart(ActionRegistry actionRegistry) {
		super();
		LOGGER.trace("new: actionRegistry=<{}>", actionRegistry);
	}

	/**
	 * モデルへのリスナ
	 */
	protected Adapter modelListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (getParent() == null || getViewer() == null
					|| getViewer().getControl() == null) {
				return;
			}
			if (!(ComponentPackage.eINSTANCE
					.getPortConnector_RoutingConstraint().equals(msg
					.getFeature()))) {
				return;
			}
			LOGGER.trace("notifyChanged: this=<{}> msg=<{}>", connectorId, msg);
			getViewer().getControl().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (isActive()) {
						refreshBendPoint();
					}
				}
			});
		}
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createEditPolicies() {
		LOGGER.trace("createEditPolicies");
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new PortConnectorEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new PortConnectorEndpointEditPolicy());
		//
		this.diagram = ((SystemDiagramEditPart) getRoot().getContents())
				.getModel();
		this.connectorId = getModel().getConnectorProfile().getConnectorId();
		LOGGER.trace("createEditPolicies: connectorId=<{}> diagram=<{}>",
				this.connectorId, this.diagram);
		PortConnectorBendpointEditPolicy bendpointEditPolicy = new PortConnectorBendpointEditPolicy();
		bendpointEditPolicy.setDiagram(this.diagram);
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				bendpointEditPolicy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IFigure createFigure() {
		LOGGER.trace("createFigure");
		PolylineConnection result = new PolylineConnection();
		result.setLineWidth(1);
		result.setConnectionRouter(new EditableManhattanConnectorRouter());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activate() {
		LOGGER.trace("activate: this=<{}>", this.connectorId);
		super.activate();
		((ModelElement) getModel()).eAdapters().add(this.modelListener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivate() {
		LOGGER.trace("deactivate: this=<{}>", this.connectorId);
		super.deactivate();
		((ModelElement) getModel()).eAdapters().remove(this.modelListener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortConnector getModel() {
		return (PortConnector) super.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshBendPoint();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * モデルのオブジェクトに委譲している
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		Object result = AdapterUtil.getAdapter(getModel(), key);
		if (result == null) {
			result = super.getAdapter(key);
		}
		return result;
	}

	/**
	 * ベンドポイントを再設定する
	 */
	protected void refreshBendPoint() {
		Map<Integer, Point> routingConstraint = Draw2dUtil
				.toDraw2dPointMap(this.diagram
						.getPortConnectorRoutingConstraint(this.connectorId));
		LOGGER.trace("refreshBendPoint: this=<{}> constraint=<{}>",
				this.connectorId, routingConstraint);
		if (routingConstraint == null) {
			routingConstraint = Collections.emptyMap();
		}
		getConnectionFigure().setRoutingConstraint(routingConstraint);
	}

	/**
	 * コンポーネントFigureの変更の通知を行うリスナを登録する
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * コンポーネントFigureの変更の通知を行うリスナを削除する
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

}
