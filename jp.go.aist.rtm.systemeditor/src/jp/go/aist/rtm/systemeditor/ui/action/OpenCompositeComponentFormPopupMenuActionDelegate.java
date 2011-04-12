package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 複合コンポーネントを開くアクションのデリゲート
 *
 */
public class OpenCompositeComponentFormPopupMenuActionDelegate implements IObjectActionDelegate {

	private ISelection selection;
	
	private ComponentEditPart componentPart;
	
	private IWorkbenchPart parentSystemDiagramEditor;
	
	private Component compositeComponent;
	
	/**
	 * {@inheritDoc}
	 */
	public void run(final IAction action) {
		if (selection instanceof StructuredSelection) {
			if (((StructuredSelection) selection).getFirstElement() instanceof ComponentEditPart) {
				componentPart = (ComponentEditPart) ((StructuredSelection) selection)
						.getFirstElement();
				if (componentPart.getModel().isCompositeComponent()) {
					compositeComponent = componentPart.getModel();
					OpenCompositeComponentAction openAction = new OpenCompositeComponentAction(
							this.parentSystemDiagramEditor);
					openAction.setCompositeComponent(compositeComponent);
					openAction.run();
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		action.setEnabled(isEnable());
	}
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.parentSystemDiagramEditor = targetPart;
		
	}
	private boolean isEnable(){
		if (selection instanceof IStructuredSelection) {
			Object part = ((IStructuredSelection) selection).getFirstElement();
			if (part instanceof ComponentEditPart) {
				return ((ComponentEditPart) part).getModel().isCompositeComponent();
			}
		}
		return false;
	}
}
