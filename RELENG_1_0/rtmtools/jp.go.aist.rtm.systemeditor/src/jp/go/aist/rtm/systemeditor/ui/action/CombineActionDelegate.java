package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialogData;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.command.CombineCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.omg.CORBA.TIMEOUT;

/**
 * 複合コンポーネントを作成するアクション
 */
public class CombineActionDelegate implements IEditorActionDelegate {

	private ISelection selection;

	private AbstractSystemDiagramEditor targetEditor;

	private List<Component> selectedComponents;

	/**
	 * アクションのメインの実行メソッド
	 * 
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void run(final IAction action) {
		if (selectedComponents.size() == 0) {
			return;
		}
		Shell shell = targetEditor.getSite().getShell();
		NewCompositeComponentDialog dialog = new NewCompositeComponentDialog(
				shell, targetEditor.isOnline(), selectedComponents,
				targetEditor.getSystemDiagram().getComponents());
		int open = dialog.open();
		if (open != IDialogConstants.OK_ID) {
			return;
		}

		int defaultTimeout = ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
				ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
		TimeoutWrapper wrapper = new TimeoutWrapper(defaultTimeout);

		CreateCompositeComponentJob1 job1 = new CreateCompositeComponentJob1();
		job1.setDialog(dialog);
		job1.setTargetEditor(targetEditor);
		wrapper.setJob(job1);
		Component compositeComponent = (Component) wrapper.start();
		if (compositeComponent == null) return;

		CreateCompositeComponentJob2 job2 = new CreateCompositeComponentJob2();
		job2.setCompositeComponent(compositeComponent);
		job2.setSelectedComponents(selectedComponents);
		wrapper.setJob(job2);
		if (wrapper.start() == null && compositeComponent instanceof CorbaComponent) {
			final CorbaComponent comp = (CorbaComponent) compositeComponent;
			wrapper.setJob(new TimeoutWrappedJob(){
				@Override
				protected Object executeCommand() {
					return comp.exitR();
				}});
			wrapper.start();
			return;
		}

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
		return (selectedComponents.size() > 0);
	}
}
