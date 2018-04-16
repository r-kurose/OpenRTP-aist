package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.to_cid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ECSelectionEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ECFigure;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

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
 * @param <M>
 * @param <F>
 */
public abstract class ECEditPart<M extends ECEditPart.AbstractEC, F extends IFigure> extends AbstractEditPart implements
		NodeEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECEditPart.class);

	public ECEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
		LOGGER.trace("new: actionRegistry=<{}>", to_cid(actionRegistry));
	}

	@SuppressWarnings("unchecked")
	@Override
	public M getModel() {
		return (M) super.getModel();
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
		LOGGER.trace("refreshVisuals: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));
		getFigure().setBackgroundColor(ColorHelper.getECBodyColor(getModel().getModel()));
		getFigure().setForegroundColor(ColorHelper.getECBorderColor(getModel().getModel()));
		getFigure().setToolTip(ToolTipHelper.getECToolTip(getModel().getModel()));
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
		getModel().getModel().eAdapters().add(this);
		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(preferenceChangeListener);
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate");
		super.deactivate();
		getModel().getModel().eAdapters().remove(this);
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

	/**
	 * 関連ECをダイアグラムから検索します。
	 */
	public static class ECFinder {

		private OwnEC ownEc;
		private OwnECEditPart ownPart;
		private PartEC partEc;
		private PartECEditPart partPart;

		ECFinder(OwnEC ec, OwnECEditPart part) {
			this.ownEc = ec;
			this.ownPart = part;
		}

		ECFinder(PartEC ec, PartECEditPart part) {
			this.partEc = ec;
			this.partPart = part;
		}

		public OwnEC getOwnedEC() {
			if (this.partEc == null || this.partPart == null) {
				return null;
			}
			return null;
		}

		public List<PartEC> getPartECList() {
			List<PartEC> ret = new ArrayList<PartEC>();
			if (this.ownEc == null || this.ownPart == null) {
				return ret;
			}
			return ret;
		}

		boolean eqlByRemote(Component c1, Component c2) {
			if (c1 == null || !(c1 instanceof CorbaComponent)) {
				return false;
			}
			if (c2 == null || !(c2 instanceof CorbaComponent)) {
				return false;
			}
			CorbaComponent cc1 = (CorbaComponent) c1;
			CorbaComponent cc2 = (CorbaComponent) c2;
			return (cc1.getCorbaObjectInterface() != null && cc1.getCorbaObjectInterface().equals(cc2.getCorbaObjectInterface()));
		}
	}

	@Override
	protected List<?> getModelTargetConnections() {
		List<ECConnectionEditPart.ECConnection> ret = new ArrayList<>();
		if (!(getModel() instanceof PartEC)) {
			return ret;
		}
		LOGGER.trace("getModelTargetConnections: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));
		ECFinder finder = new ECFinder((PartEC) getModel(), (PartECEditPart) this);
		OwnEC oe = finder.getOwnedEC();
		if (oe != null) {
			LOGGER.trace("getModelTargetConnections:  source=<{}>", to_cid(oe));
			ret.add(new ECConnectionEditPart.ECConnection(oe, (PartEC) getModel()));
		}
		return ret;
	}

	@Override
	protected List<?> getModelSourceConnections() {
		List<ECConnectionEditPart.ECConnection> ret = new ArrayList<>();
		if (!(getModel() instanceof OwnEC)) {
			return ret;
		}
		LOGGER.trace("getModelSourceConnections: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));
		ECFinder finder = new ECFinder((OwnEC) getModel(), (OwnECEditPart) this);
		for (PartEC pe : finder.getPartECList()) {
			LOGGER.trace("getModelSourceConnections:  target=<{}>", to_cid(pe));
			ret.add(new ECConnectionEditPart.ECConnection((OwnEC) getModel(), pe));
		}
		return ret;
	}

	@Override
	protected void refreshSourceConnections() {
		LOGGER.trace("refreshSourceConnections: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));

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
		LOGGER.trace("refreshTargetConnections: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));

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

	/** 選択中の ECの場合は true */
	public boolean isPrimary() {
		CorbaComponent comp = (CorbaComponent) getModel().getModel().eContainer();
		if (comp == null) {
			return false;
		}
		CorbaExecutionContext cec = (CorbaExecutionContext) getModel().getModel();
		return cec == comp.getPrimaryExecutionContext();
	}

	/** ECタブの表示が有効の場合は true */
	public boolean showECTab() {
		SystemDiagramEditPart diagram = (SystemDiagramEditPart) this.getParent().getParent();
		SystemDiagramStore store = SystemDiagramStore.instance(diagram.getModel());
		String value = store.get(SystemDiagramStore.ID_DISPLAY_EC_TAB);
		return "true".equals(value);
	}

	/**
	 * Owned EC の EditPart
	 */
	public static class OwnECEditPart extends ECEditPart<OwnEC, ECFigure.OwnECFigure> {

		public OwnECEditPart(ActionRegistry actionRegistry) {
			super(actionRegistry);
		}

		@Override
		public OwnEC getModel() {
			return (OwnEC) super.getModel();
		}

		@Override
		protected IFigure createFigure() {
			LOGGER.trace("createFigure");
			IFigure result = new ECFigure.OwnECFigure(getModel());
			if (!showECTab()) {
				result = new ECFigure.HiddenOwnECFigure(getModel());
			} else if (isPrimary()) {
				result = new ECFigure.SelectedOwnECFigure(getModel());
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

	/**
	 * Participant ECの EditPartを表します。
	 */
	public static class PartECEditPart extends ECEditPart<PartEC, ECFigure.PartECFigure> {

		public PartECEditPart(ActionRegistry actionRegistry) {
			super(actionRegistry);
		}

		@Override
		public PartEC getModel() {
			return (PartEC) super.getModel();
		}

		@Override
		protected IFigure createFigure() {
			LOGGER.trace("createFigure");
			IFigure result = new ECFigure.PartECFigure(getModel());
			if (!showECTab()) {
				result = new ECFigure.HiddenPartECFigure(getModel());
			} else if (isPrimary()) {
				result = new ECFigure.SelectedPartECFigure(getModel());
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
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					if (isActive()) {
						refresh();
						refreshVisuals();
						refreshTargetConnections();
					}
				}
			});
		}

	}

	public static class AbstractEC {

		protected ExecutionContext ec;

		public ExecutionContext getModel() {
			return this.ec;
		}

	}

	/**
	 * Owned ECのモデルのラッパを表します。<br>
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
	 */
	public static class OwnEC extends AbstractEC {

		public OwnEC(ExecutionContext ec) {
			this.ec = ec;
		}

	}

	/**
	 * Participant ECのモデルのラッパを表します。<br>
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
	 */
	public static class PartEC extends AbstractEC {

		public PartEC(ExecutionContext ec) {
			this.ec = ec;
		}

	}

}
