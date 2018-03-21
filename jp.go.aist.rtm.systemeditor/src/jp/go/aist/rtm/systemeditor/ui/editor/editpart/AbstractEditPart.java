package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * EditPartの抽象クラス。アダプタ機能とドメインモデルからの変更通知の機能を追加している
 */
public abstract class AbstractEditPart extends AbstractGraphicalEditPart
		implements Adapter {
	private ActionRegistry actionRegistry;

	Adapter defaultAdapterDelegate = new AdapterImpl();

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public AbstractEditPart(ActionRegistry actionRegistry) {
		this.actionRegistry = actionRegistry;
	}
	
	@Override
	public Notifier getTarget() {
		return defaultAdapterDelegate.getTarget();
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return defaultAdapterDelegate.isAdapterForType(type);
	}

	@Override
	public void setTarget(Notifier newTarget) {
		defaultAdapterDelegate.setTarget(newTarget);
	}

	/**
	 * ActionRegistryを取得する
	 * 
	 * @return ActionRegistry
	 */
	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	@Override
	public void activate() {
		super.activate();
		((ModelElement) getModel()).eAdapters().add(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((ModelElement) getModel()).eAdapters().remove(this);
	}

	/**
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
	 * 図に付与されるラベル
	 */
	public static class FloatingLabel extends Label {

		public FloatingLabel(IFigure parentFigure) {
			setParent(parentFigure);
			parentFigure.add(this);
		}

		/**
		 * 削除する場合に呼び出されることを意図する
		 */
		public void deactivate() {
			getParent().remove(this);
		}

		@Override
		public boolean isFocusTraversable() {
			return false;
		}

		@Override
		public boolean isRequestFocusEnabled() {
			return false;
		}

		@Override
		protected boolean isMouseEventTarget() {
			return false;
		}
	}
}
