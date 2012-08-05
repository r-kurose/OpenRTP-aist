package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.DeploymentSettingDialog;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

public class DeploySettingActionDelegate implements IEditorActionDelegate {

	static final String DIALOG_TITLE_ERROR = Messages.getString("Common.dialog.error_title");

	private ISelection selection;
	private AbstractSystemDiagramEditor targetEditor;
	private List<Component> selectedComponents;

	/**
	* アクションのメインの実行メソッド
	* 
	*/
	public void run(final IAction action) {
		if (selectedComponents.size() != 1) {
			return;
		}
		Shell shell = targetEditor.getSite().getShell();
		
		DeploymentSettingDialog dialog = new DeploymentSettingDialog(shell, selectedComponents.get(0));
		int open = dialog.open();
		if (open != IDialogConstants.OK_ID) {
			return;
		}
	}
	
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (AbstractSystemDiagramEditor) targetEditor;
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		action.setEnabled(isEnable());
	}
	
	@SuppressWarnings("unchecked")
	private boolean isEnable() {
		selectedComponents = new ArrayList<Component>();
		if (selection instanceof IStructuredSelection) {
			for (Iterator iterator = ((IStructuredSelection) selection)
					.iterator(); iterator.hasNext();) {
				Object part = iterator.next();
				if (part instanceof ComponentEditPart) {
					selectedComponents.add(((ComponentEditPart) part)
							.getModel());
				}
			}
		}
		if (selectedComponents.isEmpty()) {
			return false;
		}
		return true;
	}
}
