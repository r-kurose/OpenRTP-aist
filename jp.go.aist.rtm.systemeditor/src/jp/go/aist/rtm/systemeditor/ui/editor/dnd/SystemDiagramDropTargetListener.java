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
		System.out.println("updateTargetRequest() entry");
		((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
		System.out.println("updateTargetRequest() return");
	}

	@Override
	protected Request createTargetRequest() {
		System.out.println("createTargetRequest() entry");
		ComponentFactory factory = new ComponentFactory();
		List<Component> components = getComponents();
		setComponents(factory, components);

		CreateRequest result = new CreateRequest(); // nullObjectとして返す。
		result.setFactory(factory);
		System.out.println("createTargetRequest() return");
		return result;
	}

	void setComponents(ComponentFactory factory, List<Component> components) {
		System.out.println("setComponents() entry");
		if (components == null || components.isEmpty()) {
			System.out.println("setComponents() return 0");
			return;
		}
		for (Component c : components) {
			System.out.println("setComponents() c.getLanguage()="+c.getLanguage());
			factory.addComponent(c);
		}
		System.out.println("setComponents() return 1");
	}

	List<Component> getComponents() {
		System.out.println("getComponents() entry");
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
					System.out.println("getComponents() offline add");
					result.add(comp);
				}
			}
		}
		System.out.println("getComponents() return");
		return result;
	}

	@Override
	protected void handleDrop() {
		System.out.println("handleDrop() entry");
		IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer
				.getInstance().getSelection();
		getCurrentEvent().data = selection;
		super.handleDrop();
		System.out.println("handleDrop() return");
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		System.out.println("isEnabled() entry");
		if (!super.isEnabled(event)) {
			System.out.println("isEnabled() return false 0");
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
				System.out.println("isEnabled() return true 0");
				return true;
			} else if (!isOnline() && obj instanceof RTCRVLeafItem) {
				System.out.println("isEnabled() return true 1");
				return true;
			}
		}
		System.out.println("isEnabled() return false 1");
		return false;
	}

}
