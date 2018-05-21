package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.to_cid;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.EditableManhattanConnectorRouter;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ECConnectionBendpointEditPolicy;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ECConnectionEditPart extends AbstractConnectionEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECConnectionEditPart.class);

	private SystemDiagram diagram;

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public ECConnectionEditPart(ActionRegistry actionRegistry) {
		super();
		LOGGER.trace("new: actionRegistry=<{}>", to_cid(actionRegistry));
	}

	protected Adapter modelListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (getParent() == null || getViewer() == null || getViewer().getControl() == null) {
				return;
			}
			if (!(msg.getNotifier() instanceof SystemDiagramStore.Target) //
					|| !SystemDiagramStore.F_BENDPOINT_EC_CONN.equals(msg.getFeature())) {
				return;
			}
			LOGGER.trace("notifyChanged: this=<{}> msg=<{}>", to_cid(getModel()), msg);
			getViewer().getControl().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (isActive()) {
						refreshBendPoint();
					}
				}
			});
		}
	};

	@Override
	protected void createEditPolicies() {
		LOGGER.trace("createEditPolicies(endpoint)");
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		//
		this.diagram = ((SystemDiagramEditPart) getRoot().getContents()).getModel();
		LOGGER.trace("createEditPolicies(bendpoint): diagram=<{}>", to_cid(this.diagram));
		ECConnectionBendpointEditPolicy bendpointEditPolicy = new ECConnectionBendpointEditPolicy();
		bendpointEditPolicy.setDiagram(this.diagram);
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, bendpointEditPolicy);
	}

	@Override
	protected IFigure createFigure() {
		LOGGER.trace("createFigure");
		PolylineConnection result = new PolylineConnection();
		result.setLineWidth(1);
		result.setAlpha(50);
		result.setConnectionRouter(new EditableManhattanConnectorRouter());
		return result;
	}

	@Override
	public void activate() {
		LOGGER.trace("activate: this=<{}>", to_cid(getModel()));
		super.activate();
		getModel().eAdapters().add(this.modelListener);
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate: this=<{}>", to_cid(getModel()));
		super.deactivate();
		getModel().eAdapters().remove(this.modelListener);
	}

	@Override
	public ECConnection getModel() {
		return (ECConnection) super.getModel();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshBendPoint();
	}

	/**
	 * ベンドポイントを再設定
	 */
	protected void refreshBendPoint() {
		LOGGER.trace("refreshBendPoint: this=<{}>", to_cid(this));
		//
		SystemDiagramStore store = SystemDiagramStore.instance(this.diagram);
		@SuppressWarnings("unchecked")
		Map<Integer, Point> routingConstraint = (Map<Integer, Point>) store.getTarget("ECConnection", getModel().getId())
				.getResource(SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP);
		if (routingConstraint == null) {
			routingConstraint = Collections.emptyMap();
		}
		getConnectionFigure().setRoutingConstraint(routingConstraint);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		Object result = AdapterUtil.getAdapter(getModel(), key);
		if (result == null) {
			result = super.getAdapter(key);
		}
		return result;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * EC関連を表す
	 */
	public static class ECConnection {

		private List<Adapter> adapters = new ArrayList<>();

		private String id;
		private ECEditPart.OwnEC source;
		private ECEditPart.PartEC target;
		private Map<Integer, Point> routingConstraint = new HashMap<>();

		public static String buildId(ECEditPart.OwnEC own, ECEditPart.PartEC part) {
			return String.format("%s-%s", own.getEcId(), part.getEcId());
		}

		public ECConnection(ECEditPart.OwnEC source, ECEditPart.PartEC target) {
			this.source = source;
			this.target = target;
			this.id = buildId(this.source, this.target);
		}

		/**
		 * EC関連を特定するIDを取得します。
		 * 
		 * @return
		 */
		public String getId() {
			return this.id;
		}

		/**
		 * OwnECを取得します。(EC関連コネクションのソース)
		 * 
		 * @return
		 */
		public ECEditPart.OwnEC getSource() {
			return this.source;
		}

		/**
		 * PartECを取得します。(EC関連コネクションのソース)
		 * 
		 * @return
		 */
		public ECEditPart.PartEC getTarget() {
			return this.target;
		}

		/**
		 * ベンドポイントの一覧を取得します。
		 * 
		 * @return
		 */
		public Map<Integer, Point> getRoutingConstraint() {
			return this.routingConstraint;
		}

		public List<Adapter> eAdapters() {
			return this.adapters;
		}

		// public void eNotify(Notification notification) {
		// // TODO 自動生成されたメソッド・スタブ
		// }

		@Override
		public String toString() {
			return String.format("%s<{%s}>", getClass().getSimpleName(), getId());
		}

	}

}
