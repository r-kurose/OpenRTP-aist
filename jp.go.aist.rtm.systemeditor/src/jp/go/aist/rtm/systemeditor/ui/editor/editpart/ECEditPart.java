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
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ECAnchor;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ECFigure;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.graphics.Cursor;
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
	public ECFigure getFigure() {
		if (this.invalid) {
			setInvalid(false);
			IFigure newFig = createFigure();
			if (newFig != this.figure) {
				newFig.setParent(this.figure.getParent());
			}
			setFigure(newFig);
			return (ECFigure) this.figure;
		}
		return (ECFigure) super.getFigure();
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
		//
		private ComponentEditPart compPart;
		private SystemDiagramEditPart diagramPart;
		private Map<String, ECConnectionEditPart.ECConnection> connMap = null;

		// OwnEC を起点とする場合
		ECFinder(OwnEC ec, OwnECEditPart part) {
			this.ownEc = ec;
			this.ownPart = part;
			//
			this.compPart = (ComponentEditPart) this.ownPart.getParent();
			initConnMap();
		}

		// PartEC を起点とする場合
		ECFinder(PartEC ec, PartECEditPart part) {
			this.partEc = ec;
			this.partPart = part;
			//
			this.compPart = (ComponentEditPart) this.partPart.getParent();
			initConnMap();
		}

		@SuppressWarnings("unchecked")
		void initConnMap() {
			this.diagramPart = (SystemDiagramEditPart) this.compPart.getParent();
			SystemDiagram diagram = this.diagramPart.getModel();
			SystemDiagramStore store = SystemDiagramStore.instance(diagram);
			this.connMap = (Map<String, ECConnectionEditPart.ECConnection>) store.getTarget().getResource(
					SystemDiagramStore.KEY_EC_CONN_MAP);
			if (this.connMap == null) {
				this.connMap = new HashMap<>();
				store.getTarget().putResource(SystemDiagramStore.KEY_EC_CONN_MAP, this.connMap);
			}
		}

		/**
		 * ダイアグラムから PartEC に対する OwnEC を検索します。
		 * 
		 * @return
		 */
		public OwnEC findOwnedEC() {
			if (this.partEc == null || this.partPart == null) {
				return null;
			}
			if (this.diagramPart == null) {
				return null;
			}
			for (Object o1 : this.diagramPart.getChildren()) {
				if (o1 instanceof ComponentEditPart) {
					ComponentEditPart c = (ComponentEditPart) o1;
					for (Object o2 : c.getChildren()) {
						if (o2 instanceof ECEditPart.OwnECEditPart) {
							ECEditPart.OwnECEditPart pp = (ECEditPart.OwnECEditPart) o2;
							ECEditPart.OwnEC oec = pp.getModel();
							if (eqlByRemote(oec.getModel(), this.partEc.getModel())) {
								return oec;
							}
						}
					}
				}
			}
			return null;
		}

		/**
		 * ダイアグラムから OwnEC に対する PartEC のリストを検索します。
		 * 
		 * @return
		 */
		public List<PartEC> findPartECList() {
			List<PartEC> ret = new ArrayList<PartEC>();
			if (this.ownEc == null || this.ownPart == null) {
				return ret;
			}
			if (this.diagramPart == null) {
				return ret;
			}
			for (Object o1 : this.diagramPart.getChildren()) {
				if (o1 instanceof ComponentEditPart) {
					ComponentEditPart c = (ComponentEditPart) o1;
					for (Object o2 : c.getChildren()) {
						if (o2 instanceof ECEditPart.PartECEditPart) {
							ECEditPart.PartECEditPart pp = (ECEditPart.PartECEditPart) o2;
							ECEditPart.PartEC pec = pp.getModel();
							if (eqlByRemote(pec.getModel(), this.ownEc.getModel())) {
								ret.add(pec);
							}
						}
					}
				}
			}
			return ret;
		}

		/**
		 * ダイアグラム内から OwnEC、PartECによる EC関連を検索します。既存で EC関連を検索できなかった場合は EC関連オブジェクトを生成します。
		 * 
		 * @param oe
		 * @param pe
		 * @return
		 */
		public ECConnectionEditPart.ECConnection findOrCreateConn(OwnEC oe, PartEC pe) {
			String connId = ECConnectionEditPart.ECConnection.buildId(oe, pe);
			LOGGER.trace("findOrCreateConn: oe=<{}> pe=<{}> id=<{}>", to_cid(oe), to_cid(pe), connId);
			ECConnectionEditPart.ECConnection conn = this.connMap.get(connId);
			if (conn == null) {
				conn = new ECConnectionEditPart.ECConnection(oe, pe);
				this.connMap.put(connId, conn);
			}
			return conn;
		}

		// CORBAオブジェクトで CorbaComponent の等価チェック
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

		// CORBAオブジェクトで CorbaExecutionComponent の等価チェック
		boolean eqlByRemote(ExecutionContext ec1, ExecutionContext ec2) {
			if (ec1 == null || !(ec1 instanceof CorbaExecutionContext)) {
				return false;
			}
			if (ec2 == null || !(ec2 instanceof CorbaExecutionContext)) {
				return false;
			}
			CorbaExecutionContext cec1 = (CorbaExecutionContext) ec1;
			CorbaExecutionContext cec2 = (CorbaExecutionContext) ec2;
			return (cec1.getCorbaObjectInterface() != null && cec1.getCorbaObjectInterface().equals(
					cec2.getCorbaObjectInterface()));
		}
	}

	@Override
	protected List<?> getModelTargetConnections() {
		List<ECConnectionEditPart.ECConnection> ret = new ArrayList<>();
		if (!showECTab() || !showECConn()) {
			// ECタブ表示、EC関連表示ともに有効な場合に EC関連モデルを返します
			return ret;
		}
		if (!(getModel() instanceof PartEC)) {
			return ret;
		}
		LOGGER.trace("getModelTargetConnections: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));
		LOGGER.trace("getModelTargetConnections:  owner=<{}>", to_cid(getModel().getModel().getOwner()));
		for (Component c : getModel().getModel().getParticipants()) {
			LOGGER.trace("getModelTargetConnections:  parts=<{}>", to_cid(c));
		}
		ECFinder finder = new ECFinder((PartEC) getModel(), (PartECEditPart) this);
		OwnEC oe = finder.findOwnedEC();
		if (oe != null) {
			LOGGER.trace("getModelTargetConnections:  source=<{}>", to_cid(oe));
			ECConnectionEditPart.ECConnection conn = finder.findOrCreateConn(oe, (PartEC) getModel());
			ret.add(conn);
		}
		for (ECConnectionEditPart.ECConnection conn : ret) {
			LOGGER.trace("getModelTargetConnections:  conn=<{}> <{}>", to_cid(conn), conn);
		}
		return ret;
	}

	@Override
	protected List<?> getModelSourceConnections() {
		List<ECConnectionEditPart.ECConnection> ret = new ArrayList<>();
		if (!showECTab() || !showECConn()) {
			// ECタブ表示、EC関連表示ともに有効な場合に EC関連モデルを返します
			return ret;
		}
		if (!(getModel() instanceof OwnEC)) {
			return ret;
		}
		LOGGER.trace("getModelSourceConnections: this=<{}> model=<{}>", to_cid(this), to_cid(getModel()));
		LOGGER.trace("getModelSourceConnections:  owner=<{}>", to_cid(getModel().getModel().getOwner()));
		for (Component c : getModel().getModel().getParticipants()) {
			LOGGER.trace("getModelSourceConnections:  parts=<{}>", to_cid(c));
		}
		ECFinder finder = new ECFinder((OwnEC) getModel(), (OwnECEditPart) this);
		for (PartEC pe : finder.findPartECList()) {
			LOGGER.trace("getModelSourceConnections:  target=<{}>", to_cid(pe));
			ECConnectionEditPart.ECConnection conn = finder.findOrCreateConn((OwnEC) getModel(), pe);
			ret.add(conn);
		}
		for (ECConnectionEditPart.ECConnection conn : ret) {
			LOGGER.trace("getModelSourceConnections:  conn=<{}> <{}>", to_cid(conn), conn);
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

	@Override
	protected void addSourceConnection(ConnectionEditPart connection, int index) {
		LOGGER.trace("addSourceConnection: this=<{}> model=<{}> conn=<{}> index=<{}>", to_cid(this), to_cid(getModel()),
				to_cid(connection), index);
		// ターゲット側の設定も行う
		ECConnectionEditPart.ECConnection connectionModel = (ECConnectionEditPart.ECConnection) connection.getModel();
		PartECEditPart targetPart = (PartECEditPart) getViewer().getEditPartRegistry().get(connectionModel.getTarget());
		if (targetPart == null) {
			return;
		}
		targetPart.primAddTargetConnection(connection, index);
		GraphicalEditPart target = (GraphicalEditPart) connection.getTarget();
		if (target != null) {
			target.getTargetConnections().remove(connection);
		}
		GraphicalEditPart source = (GraphicalEditPart) connection.getSource();
		if (source != null) {
			source.getSourceConnections().remove(connection);
		}
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
		LOGGER.trace("addTargetConnection: this=<{}> model=<{}> conn=<{}> index=<{}>", to_cid(this), to_cid(getModel()),
				to_cid(connection), index);
		// ソース側の設定も行う
		ECConnectionEditPart.ECConnection connectionModel = (ECConnectionEditPart.ECConnection) connection.getModel();
		OwnECEditPart sourcePart = (OwnECEditPart) getViewer().getEditPartRegistry().get(connectionModel.getSource());
		if (sourcePart == null) {
			return;
		}
		sourcePart.primAddSourceConnection(connection, index);
		GraphicalEditPart source = (GraphicalEditPart) connection.getSource();
		if (source != null) {
			source.getSourceConnections().remove(connection);
		}
		GraphicalEditPart target = (GraphicalEditPart) connection.getTarget();
		if (target != null) {
			target.getTargetConnections().remove(connection);
		}
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
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		LOGGER.trace("getSourceConnectionAnchor: this=<{}> connection=<{}>", to_cid(getModel()), to_cid(connection));
		return new ECAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		LOGGER.trace("getTargetConnectionAnchor: this=<{}> connection=<{}>", to_cid(getModel()), to_cid(connection));
		return new ECAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		LOGGER.trace("getSourceConnectionAnchor: this=<{}> request=<{}>", to_cid(getModel()), request);
		return new ECAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		LOGGER.trace("getTargetConnectionAnchor: this=<{}> request=<{}>", to_cid(getModel()), request);
		return new ECAnchor(getFigure());
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
		String value = store.getTarget().get(SystemDiagramStore.ID_DISPLAY_EC_TAB);
		return "true".equals(value);
	}

	/** ECコネクションの表示が有効の場合は true */
	public boolean showECConn() {
		SystemDiagramEditPart diagram = (SystemDiagramEditPart) this.getParent().getParent();
		SystemDiagramStore store = SystemDiagramStore.instance(diagram.getModel());
		String value = store.getTarget().get(SystemDiagramStore.ID_DISPLAY_EC_CONN);
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

		@Override
		public DragTracker getDragTracker(Request request) {
			LOGGER.debug("getDragTracker: request=<{}>", request);
			if (request instanceof SelectionRequest) {
				return new DragEditPartsTracker(this) {

					private AttachECCommand command = new AttachECCommand();

					@Override
					protected boolean handleDragStarted() {
						this.command.setOwnECTarget((OwnECEditPart) this.getSourceEditPart());
						return super.handleDragStarted();
					}

					@Override
					protected boolean handleDragInProgress() {
						this.command.setTargetPart(this.getTargetEditPart());
						this.command.setLocation(this.getLocation());
						return super.handleDragInProgress();
					}

					@Override
					protected void performDrag() {
						if (this.command.canExecute()) {
							this.command.execute();
							this.command.setOwnECTarget(null);
						}
						super.performDrag();
					}

					@Override
					protected Cursor calculateCursor() {
						Cursor ret = null;
						if (this.command.canExecute()) {
							ret = Cursors.UPARROW;
						} else {
							ret = Cursors.CROSS;
						}
						return ret;
					}
				};
			}
			return super.getDragTracker(request);
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

	/**
	 * ECモデルの抽象的なラッパを表します。
	 */
	public static abstract class AbstractEC {

		protected ExecutionContext ec;
		protected String id;
		protected Component comp;

		/**
		 * ECモデルを取得します。
		 * 
		 * @return
		 */
		public ExecutionContext getModel() {
			return this.ec;
		}

		/**
		 * ECの種別を表します。(own|part)
		 * 
		 * @return
		 */
		abstract public String getType();

		/**
		 * RTC上の EC関連IDを取得します。
		 * 
		 * @return
		 */
		public String getId() {
			return this.id;
		}

		/**
		 * RTCモデルを取得します。
		 * 
		 * @return
		 */
		public Component getComponent() {
			return this.comp;
		}

		/**
		 * ECを特定するIDを取得します。({RTC名}.{種別}.{EC関連ID} 形式)
		 * 
		 * @return
		 */
		public String getEcId() {
			String compName = (this.comp != null) ? this.comp.getInstanceNameL() : "";
			return String.format("%s.%s.%s", compName, getType(), getId());
		}

	}

	/**
	 * Owned ECのモデルのラッパを表します。<br>
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
	 */
	public static class OwnEC extends AbstractEC {

		public OwnEC(ExecutionContext ec, String id, Component comp) {
			this.ec = ec;
			this.id = id;
			this.comp = comp;
		}

		@Override
		public String getType() {
			return "own";
		}

	}

	/**
	 * Participant ECのモデルのラッパを表します。<br>
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
	 */
	public static class PartEC extends AbstractEC {

		public PartEC(ExecutionContext ec, String id, Component comp) {
			this.ec = ec;
			this.id = id;
			this.comp = comp;
		}

		@Override
		public String getType() {
			return "part";
		}

	}

	/**
	 * ECへ RTCをアタッチするコマンド (DnD用)
	 */
	public static class AttachECCommand extends Command {

		private OwnECEditPart ownECPart;
		private EditPart targetPart;
		private Point location;

		public void setOwnECTarget(OwnECEditPart ownECPart) {
			this.ownECPart = ownECPart;
		}

		public void setTargetPart(EditPart editPart) {
			this.targetPart = editPart;
		}

		public void setLocation(Point location) {
			this.location = location;
		}

		@Override
		public boolean canExecute() {
			if (this.ownECPart == null || this.targetPart == null || this.location == null) {
				return false;
			}
			if (!(this.targetPart instanceof ComponentEditPart)) {
				return false;
			}
			ComponentEditPart compPart = (ComponentEditPart) this.targetPart;
			Rectangle bound = compPart.getBodyBounds();
			if (bound != null && bound.contains(this.location)) {
				return true;
			}
			return false;
		}

		@Override
		public void execute() {
			LOGGER.debug("AttachECCommand.execute");
			ComponentEditPart compPart = (ComponentEditPart) this.targetPart;
			Component comp = compPart.getModel();
			ExecutionContext ec = this.ownECPart.getModel().getModel();
			ec.addComponentR(comp);

		}

	}

}
