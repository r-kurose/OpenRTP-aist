package jp.go.aist.rtm.systemeditor.ui.editor.dnd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.repositoryView.model.RTCRVLeafItem;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

/**
 * システムダイアグラムのDropTargetListener
 */
public class SystemDiagramDropTargetListener extends
		AbstractTransferDropTargetListener {

	Boolean online = null;

	/**
	 * コンストラクタ
	 * 
	 * @param viewer
	 *            EditPartViewer
	 */
	public SystemDiagramDropTargetListener(EditPartViewer viewer) {
		super(viewer, LocalSelectionTransfer.getInstance());
	}

	public boolean isOnline() {
		if (online == null) {
			online = false;
			if (getViewer().getRootEditPart().getContents().getModel() instanceof SystemDiagram) {
				SystemDiagram sd = (SystemDiagram) getViewer().getRootEditPart()
						.getContents().getModel();
				if (SystemDiagramKind.ONLINE_LITERAL.equals(sd.getKind())) {
					online = true;
				}
			}
		}
		return online;
	}

	@Override
	protected void updateTargetRequest() {
		((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
	}

	@Override
	protected Request createTargetRequest() {
		ComponentFactory factory = new ComponentFactory();
		List<Component> components = getComponents();
		setComponents(factory, components);

		CreateRequest result = new CreateRequest(); // nullObjectとして返す。
		result.setFactory(factory);
		return result;
	}

	void setComponents(ComponentFactory factory, List<Component> components) {
		if (components == null || components.isEmpty()) {
			return;
		}
		for (Component c : components) {
			factory.addComponent(c);
		}
	}

	List<Component> getComponents() {
		List<Component> result = new ArrayList<Component>();
		if (getCurrentEvent().data instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) getCurrentEvent().data;
			Iterator<?> iter = selection.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				Component comp = (Component) AdapterUtil.getAdapter(obj,
						Component.class);
				if (comp == null) {
					continue;
				}
				if (isOnline() && obj instanceof NamingObjectNode) {
					result.add(comp);
				} else if (!isOnline() && obj instanceof RTCRVLeafItem) {
					result.add(comp);
				}
			}
		}
		return result;
	}

	@Override
	protected void handleDrop() {
		IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer
				.getInstance().getSelection();
		getCurrentEvent().data = selection;
		super.handleDrop();
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		if (!super.isEnabled(event)) {
			return false;
		}
		// オンラインエディタへは NameServiceViewから DnD可能
		// オフラインエディタへは RepositoryViewから DnD可能
		IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer
				.getInstance().getSelection();
		Iterator<?> iter = selection.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (isOnline() && obj instanceof NamingObjectNode) {
				return true;
			} else if (!isOnline() && obj instanceof RTCRVLeafItem) {
				return true;
			}
		}
		return false;
	}

}
