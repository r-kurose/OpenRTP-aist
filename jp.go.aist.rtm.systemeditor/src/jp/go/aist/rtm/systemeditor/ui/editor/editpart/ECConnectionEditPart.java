package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.to_cid;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.EditableManhattanConnectorRouter;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
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
			if (!(ComponentPackage.eINSTANCE.getPortConnector_RoutingConstraint().equals(msg.getFeature()))) {
				return;
			}
			LOGGER.trace("notifyChanged: this=<{}> msg=<{}>", to_cid(getModel()), msg);
			getViewer().getControl().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (isActive()) {
						refreshVisuals();
					}
				}
			});
		}
	};

	@Override
	protected void createEditPolicies() {
		LOGGER.trace("createEditPolicies");
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
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
		// refreshBendPoint();
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
	 * 
	 */
	public static class ECConnection {

		private List<Adapter> adapters = new ArrayList<>();

		private String id;
		private ECEditPart.OwnEC source;
		private ECEditPart.PartEC target;

		public static String buildId(ECEditPart.OwnEC own, ECEditPart.PartEC part) {
			Object source = null;
			Object target = null;
			if (own != null) {
				source = own.getModel();
				if (source instanceof CorbaExecutionContext) {
					source = ((CorbaExecutionContext) source).getCorbaObjectInterface();
				}
			}
			if (part != null) {
				target = part.getModel();
				if (part.getModel() instanceof CorbaExecutionContext) {
					target = ((CorbaExecutionContext) target).getCorbaObjectInterface();
				}
			}
			return String.format("%s-%s", source, target);
		}

		public ECConnection(ECEditPart.OwnEC source, ECEditPart.PartEC target) {
			this.source = source;
			this.target = target;
			this.id = buildId(this.source, this.target);
		}

		public String getId() {
			return this.id;
		}

		public ECEditPart.OwnEC getSource() {
			return this.source;
		}

		public void setSource(ECEditPart.OwnEC source) {
			this.source = source;
			this.id = buildId(this.source, this.target);
		}

		public ECEditPart.PartEC getTarget() {
			return this.target;
		}

		public void setTarget(ECEditPart.PartEC target) {
			this.target = target;
			this.id = buildId(this.source, this.target);
		}

		public List<Adapter> eAdapters() {
			return this.adapters;
		}

		// public void eNotify(Notification notification) {
		// // TODO 自動生成されたメソッド・スタブ
		//
		// }

		@Override
		public String toString() {
			return String.format("%s<{%s} -> {%s}>", getClass().getSimpleName(), to_cid(this.source), to_cid(this.target));
		}
	}

}
