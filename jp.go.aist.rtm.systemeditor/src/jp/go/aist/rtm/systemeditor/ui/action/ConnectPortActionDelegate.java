package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.dialog.DisconnectDialog;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.GraphicalConnectorCreateManager;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import static jp.go.aist.rtm.systemeditor.nl.Messages.*;
import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.*;

public class ConnectPortActionDelegate implements IObjectActionDelegate {

	static final String ACTION_CONNECT_ID = ConnectPortActionDelegate.class
			.getName()
			+ ".Connect";
	static final String ACTION_DISCONNECT_ID = ConnectPortActionDelegate.class
			.getName()
			+ ".Disconnect";

	static final String LABEL_CONNECT_PORT = getString("ConnectPortActionDelegate.connect");
	static final String LABEL_DISCONNECT_PORT = getString("ConnectPortActionDelegate.disconnect");

	static final String ERROR_CANNOT_DISCONNECT = getString("ConnectPortActionDelegate.error.1");
	static final String ERROR_DISCONNECT_FAILURE = getString("ConnectPortActionDelegate.error.2");

	AbstractSystemDiagramEditor editor;
	Port port;

	@Override
	public void run(IAction action) {
		if (isConnectAction(action)) {
			runConnect();
		} else {
			runDisconnect();
		}
	}

	public void runConnect() {
		GraphicalConnectorCreateManager manager = new GraphicalConnectorCreateManager(
				editor.getSite().getShell());
		manager.setFirst(port);
		if (!manager.validateSingle()) {
			return;
		}
		manager.createProfileAndConnector();
	}

	public void runDisconnect() {
		DisconnectDialog dialog = new DisconnectDialog(editor.getSite()
				.getShell());
		dialog.setPort(port);
		int open = dialog.open();
		if (open != IDialogConstants.OK_ID) {
			return;
		}
		List<ConnectorProfile> deleteProfiles = dialog
				.getDeleteConnectorProfiles();
		if (port.getSynchronizer() == null) {
			openError(ERROR_CANNOT_DISCONNECT);
		}
		String failIds = "";
		for (ConnectorProfile prof : deleteProfiles) {
			boolean ret = port.getSynchronizer().disconnect(
					prof.getConnectorId());
			if (!ret) {
				if (!failIds.isEmpty()) {
					failIds += ",";
				}
				failIds += prof.getConnectorId();
			}
		}
		if (!failIds.isEmpty()) {
			openError(form(ERROR_DISCONNECT_FAILURE, failIds));
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		port = null;
		if (selection instanceof IStructuredSelection) {
			Object part = ((IStructuredSelection) selection).getFirstElement();
			if (part instanceof PortEditPart) {
				Port model = ((PortEditPart) part).getModel();
				if (model instanceof Port) {
					port = (Port) model;
				}
			}
		}
		action.setText((isConnectAction(action)) ? LABEL_CONNECT_PORT
				: LABEL_DISCONNECT_PORT);
		action.setEnabled(canExecution(action));
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof AbstractSystemDiagramEditor) {
			editor = (AbstractSystemDiagramEditor) targetPart;
		}
		action.setEnabled(canExecution(action));
	}

	boolean canExecution(IAction action) {
		if (editor == null || port == null) {
			return false;
		}
		if (isConnectAction(action)) {
			return true;
		} else {
			return !port.getConnectorProfiles().isEmpty();
		}
	}

	boolean isConnectAction(IAction action) {
		return ACTION_CONNECT_ID.equals(action.getId());
	}

	void openError(String message) {
		MessageDialog.openError(editor.getSite().getShell(), "Error", message);
	}

}
