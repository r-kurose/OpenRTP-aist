package jp.go.aist.rtm.nameserviceview.ui.action;

import java.util.Iterator;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.nameserviceview.nl.Messages;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * ネームサービスをビューから削除するアクション または ネームサービスからコンテキストを削除するアクション
 *
 */
public class DeleteNodeActionDelegate implements IObjectActionDelegate {
	private IWorkbenchPart targetPart;
	private IStructuredSelection selection;

	private static final String DELETE_FROM_VIEW = Messages
			.getString("Delete_from_View");
	private static final String DELETE_FROM_SERVICE = Messages
			.getString("Delete_from_Name_Service");

	private enum SelectedNode {
		SERVER, CONTEXT
	}

	private SelectedNode currentState;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	public void run(IAction action) {
		if (currentState == null)
			return;
		if (currentState == SelectedNode.SERVER) {
			runServer();
		} else if (currentState == SelectedNode.CONTEXT) {
			runContext();
		}
	}

	private void runServer() {
		for (Iterator iter = ((IStructuredSelection) selection).iterator(); iter
				.hasNext();) {
			NameServerContext context = (NameServerContext) iter.next();
			NameServerManager.eInstance.removeNode(context);
		}
	}

	private void runContext() {
		boolean confirm = MessageDialog.openConfirm(targetPart.getSite()
				.getShell(), Messages.getString("DeleteINamingContextActionDelegate.0"), Messages.getString("DeleteINamingContextActionDelegate.1")); //$NON-NLS-1$ //$NON-NLS-2$
		if (!confirm) return;

		try {
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				CorbaNode binding = (CorbaNode) iter.next();
				binding.deleteR();
			}
		} catch (Exception e) {
			MessageDialog.openInformation(targetPart.getSite()
					.getShell(), Messages.getString("DeleteINamingContextActionDelegate.2"), Messages.getString("DeleteINamingContextActionDelegate.3")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;
		updateState(action);
	}

	private void updateState(IAction action) {
		currentState = getCurrentState();
		updateActionText(action);
		action.setEnabled(currentState != null);
	}

	private void updateActionText(IAction action) {
		if (currentState == null)
			return;
		if (currentState == SelectedNode.SERVER) {
			action.setText(DELETE_FROM_VIEW);
		} else if (currentState == SelectedNode.CONTEXT) {
			action.setText(DELETE_FROM_SERVICE);
		}
	}

	private SelectedNode getCurrentState() {
		boolean hasServer = false;
		boolean hasContext = false;
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof NamingObjectNode) {
				hasContext = true;
			} else if (obj instanceof NamingContextNode) {
				NamingContextNode context = (NamingContextNode) obj;
				if (context.getKind().equals(NamingContextNode.KIND_SERVER)) {
					hasServer = true;
				} else {
					hasContext = true;
				}
			}
		}
		if (hasServer == hasContext)
			return null;
		if (hasServer)
			return SelectedNode.SERVER;
		if (hasContext)
			return SelectedNode.CONTEXT;
		return null;
	}
}
