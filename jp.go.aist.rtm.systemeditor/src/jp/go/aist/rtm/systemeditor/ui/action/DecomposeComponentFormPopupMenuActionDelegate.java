package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;


/**
 * 複合コンポーネントを解除するアクションのデリゲート
 *
 */
public class DecomposeComponentFormPopupMenuActionDelegate implements IObjectActionDelegate{
	private Component compositeComponent;
	private SystemDiagram diagram;
	private AbstractSystemDiagramEditor targetEditor;


	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof AbstractSystemDiagramEditor) {
			targetEditor = (AbstractSystemDiagramEditor)targetPart;
			diagram = targetEditor.getSystemDiagram();
		} else {
			diagram = null;
		}
	}

	public void run(IAction action) {
		if (compositeComponent == null) return;
		DecomposeComponentAction decomposeAction = new DecomposeComponentAction();
		decomposeAction.setTarget(compositeComponent);
		decomposeAction.setParent(diagram);
		decomposeAction.run();
		targetEditor.refresh();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		compositeComponent = getCompositeComponent(selection);
		action.setEnabled(compositeComponent != null);
	}

	private Component getCompositeComponent(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		Object part = ((IStructuredSelection) selection).getFirstElement();
		if (!(part instanceof ComponentEditPart)) return null;
		Component model = ((ComponentEditPart) part).getModel();
		if (model.isCompositeComponent()) return model;
		return null;
	}

}
