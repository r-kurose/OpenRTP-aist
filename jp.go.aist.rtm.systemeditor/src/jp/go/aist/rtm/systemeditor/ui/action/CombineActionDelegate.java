package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.factory.CompositeComponentCreator;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.command.CombineCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

/**
 * 複合コンポーネントを作成するアクション
 */
public class CombineActionDelegate implements IEditorActionDelegate {

	static final String DIALOG_TITLE_ERROR = Messages
			.getString("Common.dialog.error_title");

	private ISelection selection;
	private AbstractSystemDiagramEditor targetEditor;
	private List<Component> selectedComponents;

	/**
	 * アクションのメインの実行メソッド
	 * 
	 */
	public void run(final IAction action) {
		if (selectedComponents.size() == 0) {
			return;
		}
		Shell shell = targetEditor.getSite().getShell();

		final CompositeComponentCreator creator = new CompositeComponentCreator();
		creator.setTargetEditor(targetEditor);
		creator.setComponents(selectedComponents);
		if (!creator.canCreate()) {
			MessageDialog.openError(shell, DIALOG_TITLE_ERROR, creator
					.getMessage());
			return;
		}

		NewCompositeComponentDialog dialog = new NewCompositeComponentDialog(
				shell, creator, selectedComponents, targetEditor
						.getSystemDiagram().getComponents());
		int open = dialog.open();
		if (open != IDialogConstants.OK_ID) {
			return;
		}
		Component compositeComponent = creator.create();

		// ダイアグラムへの登録はCombineCommandで行う
		CombineCommand command = new CombineCommand();
		command.setParent(this.targetEditor.getSystemDiagram());
		command.setTarget(compositeComponent);
		targetEditor.deselectAll();
		if (targetEditor.getAdapter(CommandStack.class) != null) {
			((CommandStack) targetEditor.getAdapter(CommandStack.class))
					.execute(command);
		} else {
			throw new RuntimeException();
		}
		targetEditor.refresh();
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (AbstractSystemDiagramEditor) targetEditor;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		action.setEnabled(isEnable());
	}

	private boolean isEnable() {
		selectedComponents = new ArrayList<Component>();
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> iterator = ((IStructuredSelection) selection)
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
		//
		CompositeComponentCreator creator = new CompositeComponentCreator();
		creator.setTargetEditor(targetEditor);
		creator.setComponents(selectedComponents);
		return creator.isActionEnabled();
	}

}
