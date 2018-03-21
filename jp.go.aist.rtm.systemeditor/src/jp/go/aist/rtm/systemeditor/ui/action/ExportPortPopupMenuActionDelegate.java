package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortHelper;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.util.ComponentCommonUtil;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * ポートの公開・非公開を切り替えるアクションのデリゲート
 *
 */
public class ExportPortPopupMenuActionDelegate implements IObjectActionDelegate {
	private static final String ACTION_UNEXPORT = Messages.getString("ExportPortPopupMenuActionDelegate.action.unexport"); // Unexport

	private static final String ACTION_EXPORT = Messages.getString("ExportPortPopupMenuActionDelegate.action.export"); // Export

	private Component compositeComponent;

	private List<String> requiredExportedPorts;

	private Port port;

	private PortEditPart portPart;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		compositeComponent = null;
		if (targetPart instanceof AbstractSystemDiagramEditor) {
			SystemDiagram diagram = ((AbstractSystemDiagramEditor) targetPart)
					.getSystemDiagram();
			Component component = diagram.getCompositeComponent();
			if (component != null) {
				compositeComponent = component;
			}
		}
		if (compositeComponent == null) {
			action.setEnabled(false);
			return;
		}

		this.requiredExportedPorts = ComponentCommonUtil
				.getRequiredExportedPorts(compositeComponent.getComponents());
	}

	public void run(IAction action) {
		if (port == null || compositeComponent == null) return;
		ExportPortAction exportAction = new ExportPortAction();
		exportAction.setTarget(port);
		exportAction.setParent(compositeComponent);
		exportAction.run();
		if (portPart.getParent() instanceof ComponentEditPart) {
			ComponentEditPart parent = (ComponentEditPart) portPart.getParent();
			parent.refreshPortEditPart(portPart);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		port = PortHelper.getPort(selection);
		portPart = PortHelper.getPortPart(selection);
		action.setText(getActionText());
		action.setEnabled(true);
		if (port == null || compositeComponent == null) {
			action.setEnabled(false);
			return;
		}
		String name = port.getNameL();
		// String name = "";
		// if (port.eContainer() != null) {
		// 		name = ((Component) port.eContainer()).getInstanceNameL();
		// }
		// name += "." + port.getNameL();
		if (this.requiredExportedPorts.contains(name)) {
			action.setEnabled(false);
			return;
		}
//		System.out.println((port.getPortProfile().getNameL()));
	}

	private String getActionText() {
		if (PortHelper.isExported(port)) {
			return ACTION_UNEXPORT;
		} else {
			return ACTION_EXPORT;
		}
	}
}
