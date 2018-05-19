package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * ポートモニタを開始/終了するアクションを表します。
 */
public class MonitorPortActionDelegate implements IObjectActionDelegate {

	/** ポートモニタを開始するアクションID */
	static final String ACTION_START_ID = MonitorPortActionDelegate.class.getName() + ".Start";
	/** ポートモニタを停止するアクションID */
	static final String ACTION_STOP_ID = MonitorPortActionDelegate.class.getName() + ".Stop";

	private ComponentEditPart compPart;
	private AbstractSystemDiagramEditor editor;

	@Override
	public void run(IAction action) {
		if (ACTION_START_ID.equals(action.getId())) {
			this.compPart.setActivePortMonitor(true);
		} else if (ACTION_STOP_ID.equals(action.getId())) {
			this.compPart.setActivePortMonitor(false);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.compPart = null;
		if (selection instanceof IStructuredSelection) {
			Object part = ((IStructuredSelection) selection).getFirstElement();
			if (part instanceof ComponentEditPart) {
				this.compPart = (ComponentEditPart) part;
			}
		}
		action.setEnabled(canExecution(action));
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if (targetPart instanceof AbstractSystemDiagramEditor) {
			this.editor = (AbstractSystemDiagramEditor) targetPart;
		}
	}

	boolean canExecution(IAction action) {
		if (this.editor == null || this.compPart == null) {
			return false;
		}
		if (this.compPart.isEnablePortMonitor()) {
			if (ACTION_START_ID.equals(action.getId()) && !this.compPart.isActivePortMonitor()) {
				return true;
			} else if (ACTION_STOP_ID.equals(action.getId()) && this.compPart.isActivePortMonitor()) {
				return true;
			}
		}
		return false;
	}

}
