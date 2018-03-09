package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ECSelectionEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ECFigure;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
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
public abstract class ECEditPart<F extends IFigure> extends AbstractEditPart
		implements NodeEditPart {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ECEditPart.class);

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
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ECSelectionEditPolicy());
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
		getFigure()
				.setForegroundColor(ColorHelper.getECBorderColor(getModel()));
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
		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceChangeListener);
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate");
		super.deactivate();
		SystemEditorPreferenceManager.getInstance()
				.removePropertyChangeListener(preferenceChangeListener);
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
			return (ExecutionContext) ((ECEditPart.OwnEC) super
					.getWrappedModel()).getModel();
		}

		@Override
		protected IFigure createFigure() {
			LOGGER.trace("createFigure");

			IFigure result = new ECFigure.OwnECFigure(getModel());

			// 代表の ECの場合は選択中として描画 (仮)
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
		public ConnectionAnchor getSourceConnectionAnchor(
				ConnectionEditPart connection) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(
				ConnectionEditPart connection) {
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
			if (ComponentPackage.eINSTANCE.getExecutionContext_StateL().equals(
					notification.getFeature())) {
				PlatformUI.getWorkbench().getDisplay()
						.asyncExec(new Runnable() {
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
	public static class PartECEditPart extends
			ECEditPart<ECFigure.PartECFigure> {

		public PartECEditPart(ActionRegistry actionRegistry) {
			super(actionRegistry);
		}

		@Override
		public ExecutionContext getModel() {
			return (ExecutionContext) ((ECEditPart.PartEC) super
					.getWrappedModel()).getModel();
		}

		@Override
		protected IFigure createFigure() {
			LOGGER.trace("createFigure");

			IFigure result = new ECFigure.PartECFigure(getModel());

			result.setLocation(new Point(0, 0));
			return result;
		}

		@Override
		public ConnectionAnchor getSourceConnectionAnchor(
				ConnectionEditPart connection) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ConnectionAnchor getTargetConnectionAnchor(
				ConnectionEditPart connection) {
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
			if (ComponentPackage.eINSTANCE.getExecutionContext_StateL().equals(
					notification.getFeature())) {
				PlatformUI.getWorkbench().getDisplay()
						.asyncExec(new Runnable() {
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
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと
	 * EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
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
	 * ※モデル上は Owned/Participantの ECに違いはなく、Componentの関連によって決まるが、 モデルと
	 * EditPartの対応付けで別クラスにする必要があり、ここでラップクラスを設けます。
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
