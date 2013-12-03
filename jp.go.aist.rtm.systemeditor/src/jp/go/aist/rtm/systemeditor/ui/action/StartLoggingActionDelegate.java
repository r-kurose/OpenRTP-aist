package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class StartLoggingActionDelegate implements IObjectActionDelegate {

	static final String ACTION_START_LOGGING = Messages
			.getString("StartLoggingActionDelegate.start");
	static final String ACTION_STOP_LOGGING = Messages
			.getString("StartLoggingActionDelegate.stop");

	SystemDiagram diagram;
	CorbaComponent component;

	@Override
	public void run(IAction action) {
		if (component == null) {
			return;
		}
		//
		if (component.getLogObserver() != null) {
			component.getLogObserver().finish();
		} else {
			CorbaLogObserver obs = ComponentFactory.eINSTANCE
					.createCorbaLogObserver();
			obs.attachComponent(component);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		component = null;
		if (selection instanceof IStructuredSelection) {
			Object part = ((IStructuredSelection) selection).getFirstElement();
			if (part instanceof ComponentEditPart) {
				Component model = ((ComponentEditPart) part).getModel();
				if (model instanceof CorbaComponent) {
					component = (CorbaComponent) model;
				}
			}
		}
		//
		if (component == null || !component.supportedCorbaObserver()) {
			action.setText(ACTION_START_LOGGING);
			action.setEnabled(false);
			return;
		}
		//
		String actionName = ACTION_START_LOGGING;
		if (component.getLogObserver() != null) {
			actionName = ACTION_STOP_LOGGING;
		}
		action.setText(actionName);
		action.setEnabled(canExecution());
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		diagram = null;
		if (targetPart instanceof AbstractSystemDiagramEditor) {
			AbstractSystemDiagramEditor editor = (AbstractSystemDiagramEditor) targetPart;
			if (editor.isOnline()) {
				diagram = editor.getSystemDiagram();
			}
		}
		action.setEnabled(canExecution());
	}

	boolean canExecution() {
		return (diagram != null && component != null);
	}

}
