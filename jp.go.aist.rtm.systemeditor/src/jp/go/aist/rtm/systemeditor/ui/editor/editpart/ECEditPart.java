package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ECSelectionEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ECFigure;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ECの EditPartを表します。
 * 
 * @param <F>
 */
public abstract class ECEditPart<F extends IFigure> extends AbstractEditPart implements NodeEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECEditPart.class);

	public ECEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
		LOGGER.trace("new: actionRegistry=<{}>", actionRegistry);
	}

	@Override
	public abstract ExecutionContext getModel();

	protected Object getWrappedModel() {
		return super.getModel();
	}

	@Override
	protected void createEditPolicies() {
		LOGGER.trace("createEditPolicies");
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ECSelectionEditPolicy());
	}

	@Override
	public IFigure getFigure() {
		if (this.invalid) {
			setInvalid(false);
			IFigure newFig = createFigure();
			if (newFig != this.figure) {
				newFig.setParent(this.figure.getParent());
			}
			setFigure(newFig);
			return this.figure;
		}
		return super.getFigure();
	}

	@Override
	protected void refreshVisuals() {
		getFigure().setBackgroundColor(ColorHelper.getECBodyColor(getModel()));
		getFigure().setForegroundColor(ColorHelper.getECBorderColor(getModel()));
		getFigure().setToolTip(ToolTipHelper.getECToolTip(getModel()));
	}

	private boolean invalid = false;

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
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

	@Override
	public void activate() {
		LOGGER.trace("activate");
		super.activate();
		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(preferenceChangeListener);
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate");
		super.deactivate();
		SystemEditorPreferenceManager.getInstance().removePropertyChangeListener(preferenceChangeListener);
	}

	private class Adapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			LOGGER.trace("notifyChanged: msg=<{}>", msg);
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

	public static class ECFinder {

		private ExecutionContext ec;
		private Component comp;
		private SystemDiagram diagram;

		public ECFinder(ExecutionContext ec) {
			this.ec = ec;
			getDiagram();
		}

		public Component getComponent() {
			if (this.comp != null) {
				return this.comp;
			}
			if (this.ec == null || !(this.ec.eContainer() instanceof Component)) {
				return null;
			}
			this.comp = (Component) this.ec.eContainer();
			return this.comp;
		}

		public SystemDiagram getDiagram() {
			if (this.diagram != null) {
				return this.diagram;
			}
			Component comp = getComponent();
			if (comp == null) {
				return null;
			}
			if (comp == null || !(comp.eContainer() instanceof SystemDiagram)) {
				return null;
			}
			this.diagram = (SystemDiagram) comp.eContainer();
			return this.diagram;
		}

		public boolean isOwnedEC() {
			Component comp = getComponent();
			if (comp == null) {
				return false;
			}
			return comp.getExecutionContextHandler().getId(this.ec) != null;
		}

		public boolean isPartEC() {
			Component comp = getComponent();
			if (comp == null) {
				return false;
			}
			return comp.getParticipationContextHandler().getId(this.ec) != null;
		}

		public List<ExecutionContext> getPartECList() {
			List<ExecutionContext> ret = new ArrayList<>();
			if (!isOwnedEC()) {
				return ret;
			}
			for (Component c : this.ec.getParticipants()) {
				if (!this.diagram.getComponents().contains(c)) {
					continue;
				}
				for (ExecutionContext pe : c.getParticipationContexts()) {
					if (this.comp.equals(pe.getOwner())) {
						ret.add(pe);
					}
				}
			}
			return ret;
		}

		public ExecutionContext getOwnedEC() {
			if (!isPartEC()) {
				return null;
			}
			Component oc = this.ec.getOwner();
			if (!this.diagram.getComponents().contains(oc)) {
				return null;
			}
			for (ExecutionContext oe : oc.getExecutionContexts()) {
				if (oe.getParticipants().contains(this.comp)) {
					return oe;
				}
			}
			return null;
		}

	}

	@Override
	protected List<?> getModelTargetConnections() {
		List<ECConnectionEditPart.ECConnection> ret = new ArrayList<>();

		ECFinder finder = new ECFinder(getModel());
		ExecutionContext oe = finder.getOwnedEC();
		if (oe != null) {
			ret.add(new ECConnectionEditPart.ECConnection(oe, getModel()));
		}

		return ret;
	}

	@Override
	protected List<?> getModelSourceConnections() {
		List<ECConnectionEditPart.ECConnection> ret = new ArrayList<>();

		ECFinder finder = new ECFinder(getModel());
		for (ExecutionContext pe : finder.getPartECList()) {
			ret.add(new ECConnectionEditPart.ECConnection(getModel(), pe));
		}

		return ret;
	}

	@Override
	protected void refreshSourceConnections() {
		LOGGER.trace("refreshSourceConnections: this=<{}>", getModel());

		Map<Object, ConnectionEditPart> modelToEditPart = new HashMap<>();
		List<?> editParts = getSourceConnections();
		List<?> modelObjects = getModelSourceConnections();
		if (modelObjects == null) {
			modelObjects = new ArrayList<>();
		}
		List<ConnectionEditPart> trash = new ArrayList<>();

		int i = 0;
		for (i = 0; i < editParts.size(); i++) {
			ConnectionEditPart editPart = (ConnectionEditPart) editParts.get(i);
			Object model = editPart.getModel();
			if (modelObjects.contains(model)) {
				modelToEditPart.put(model, editPart);
			} else {
				trash.add(editPart);
			}
		}
		// Add new EditParts
		for (i = 0; i < modelObjects.size(); i++) {
			Object model = modelObjects.get(i);
			if (!modelToEditPart.containsKey(model)) {
				ConnectionEditPart editPart = createOrFindConnection(model);
				addSourceConnection(editPart, 0);
			}
		}
		// Remove the remaining EditParts
		for (i = 0; i < trash.size(); i++) {
			removeSourceConnection((ConnectionEditPart) trash.get(i));
		}
	}

	@Override
	protected void refreshTargetConnections() {
		LOGGER.trace("refreshTargetConnections: this=<{}>", getModel());

		Map<Object, ConnectionEditPart> mapModelToEditPart = new HashMap<>();
		List<?> connections = getTargetConnections();
		List<?> modelObjects = getModelTargetConnections();
		if (modelObjects == null)
			modelObjects = new ArrayList<>();
		List<ConnectionEditPart> trash = new ArrayList<>();

		int i = 0;
		for (i = 0; i < connections.size(); i++) {
			ConnectionEditPart editPart = (ConnectionEditPart) connections.get(i);
			Object model = editPart.getModel();
			if (modelObjects.contains(model)) {
				mapModelToEditPart.put(model, editPart);
			} else {
				trash.add(editPart);
			}
		}
		// Add new EditParts
		for (i = 0; i < modelObjects.size(); i++) {
			Object model = modelObjects.get(i);
			if (!mapModelToEditPart.containsKey(model)) {
				ConnectionEditPart editPart = createOrFindConnection(model);
				addTargetConnection(editPart, 0);
			}
		}
		// Remove the remaining Connection EditParts
		for (i = 0; i < trash.size(); i++) {
			removeTargetConnection((ConnectionEditPart) trash.get(i));
		}
	}

	/**
	 * Owned EC の EditPart
	 */
	public static class OwnECEditPart extends ECEditPart<ECFigure.OwnECFigure> {

		public OwnECEditPart(ActionRegistry actionRegistry) {
			super(actionRegistry);
		}

		@Override
		public ExecutionContext getModel() {
			return (ExecutionContext) ((ECEditPart.OwnEC) super.getWrappedModel()).getModel();
		}

		@Override
		protected IFigure createFigure() {
			LOGGER.trace("createFigure");

			IFigure result = new ECFigure.OwnECFigure(getModel());

			// 代表EC の場合は選択中として描画
			CorbaComponent comp = (CorbaComponent) getModel().eContainer();
			if (comp != null) {
				CorbaExecutionContext cec = (CorbaExecutionContext) getModel();
				if (cec == comp.getPrimaryExecutionContext()) {
					result = new ECFigure.SelectedOwnECFigure(getModel());
				}
			}

			result.setLocation(new Point(0, 0));

			return result;
		}

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(Request request) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(Request request) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public void notifyChanged(Notification notification) {
			if (ComponentPackage.eINSTANCE.getExecutionContext_StateL().equals(notification.getFeature())) {
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (isActive()) {
							refreshVisuals();
						}
					}
				});
			}
		}

	}

	/**
	 * Participant ECの EditPartを表します。
	 */
	public static class PartECEditPart extends ECEditPart<ECFigure.PartECFigure> {

		public PartECEditPart(ActionRegistry actionRegistry) {
			super(actionRegistry);
		}

		@Override
		public ExecutionContext getModel() {
			return (ExecutionContext) ((ECEditPart.PartEC) super.getWrappedModel()).getModel();
		}

		@Override
		protected IFigure createFigure() {
			LOGGER.trace("createFigure");

			IFigure result = new ECFigure.PartECFigure(getModel());

			// 代表EC の場合は選択中として描画
			CorbaComponent comp = (CorbaComponent) getModel().eContainer();
			if (comp != null) {
				CorbaExecutionContext cec = (CorbaExecutionContext) getModel();
				if (cec == comp.getPrimaryExecutionContext()) {
					result = new ECFigure.SelectedPartECFigure(getModel());
				}
			}

			result.setLocation(new Point(0, 0));
			return result;
		}

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(Request request) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(Request request) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public void notifyChanged(Notification notification) {
			if (ComponentPackage.eINSTANCE.getExecutionContext_StateL().equals(notification.getFeature())) {
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (isActive()) {
							refreshVisuals();
						}
					}
				});
			}
		}

	}

	/**
	 * Owned ECのモデルのラッパを表します。<br>
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
	 */
	public static class OwnEC {

		private ExecutionContext ec;

		public OwnEC(ExecutionContext ec) {
			this.ec = ec;
		}

		public ExecutionContext getModel() {
			return this.ec;
		}

	}

	/**
	 * Participant ECのモデルのラッパを表します。<br>
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
	 */
	public static class PartEC {

		private ExecutionContext ec;

		public PartEC(ExecutionContext ec) {
			this.ec = ec;
		}

		public ExecutionContext getModel() {
			return this.ec;
		}

	}

}
