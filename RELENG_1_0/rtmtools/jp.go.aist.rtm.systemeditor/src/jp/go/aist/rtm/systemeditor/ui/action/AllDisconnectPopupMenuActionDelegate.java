package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortHelper;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * ポートの接続をすべて削除するアクションデリゲート
 *
 */
public class AllDisconnectPopupMenuActionDelegate implements
		IObjectActionDelegate {
	private Port port;
	private IWorkbenchPart targetPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	// @Override
	public void run(IAction action) {
		if (port == null) return;
		boolean isOk = MessageDialog.openConfirm(targetPart.getSite()
				.getShell(), Messages.getString("AllDisconnectPopupMenuActionDelegate.0"), Messages.getString("AllDisconnectPopupMenuActionDelegate.1")); //$NON-NLS-1$ //$NON-NLS-2$
		if (!isOk) return;
		AllDisconnectAction allDisconnectAction = new AllDisconnectAction();
		allDisconnectAction.setTarget(port);
		setParent(allDisconnectAction);
		allDisconnectAction.run();
	}

	private void setParent(AllDisconnectAction allDisconnectAction) {
		if (targetPart instanceof AbstractSystemDiagramEditor) {
			SystemDiagram diagram = ((AbstractSystemDiagramEditor)targetPart).getSystemDiagram();
			allDisconnectAction.setParent(diagram.getRootDiagram());
		}
	}

	// @Override
	public void selectionChanged(IAction action, ISelection selection) {
		port = PortHelper.getPort(selection);
		action.setEnabled(PortHelper.isConnected(port));
	}

}
