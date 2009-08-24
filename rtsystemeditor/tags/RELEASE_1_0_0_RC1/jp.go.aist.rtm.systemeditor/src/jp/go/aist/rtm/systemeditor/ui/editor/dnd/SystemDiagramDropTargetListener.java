package jp.go.aist.rtm.systemeditor.ui.editor.dnd;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

/**
 * システムダイアグラムのDropTargetListener
 */
public class SystemDiagramDropTargetListener extends
		AbstractTransferDropTargetListener {

	/**
	 * コンストラクタ
	 * 
	 * @param viewer
	 *            EditPartViewer
	 */
	public SystemDiagramDropTargetListener(EditPartViewer viewer) {
		super(viewer, LocalSelectionTransfer.getInstance());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void updateTargetRequest() {
		((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Request createTargetRequest() {
		ComponentFactory factory = new ComponentFactory();
		Component component = getComponent();
		setComponent(factory, component);

		CreateRequest result = new CreateRequest(); // nullObjectとして返す。
		result.setFactory(factory);
		return result;
	}

	private void setComponent(ComponentFactory factory, Component component) {
		if (component == null) return;
		factory.setComponent(component);
	}

	private Component getComponent() {
		if (getCurrentEvent().data instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) getCurrentEvent().data;
			Object firstElement = selection.getFirstElement();

			return (Component) AdapterUtil.getAdapter(firstElement,
					Component.class);
		}
		return null;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void handleDrop() {
		IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer
				.getInstance().getSelection();
		getCurrentEvent().data = selection;
		super.handleDrop();
	}

}
