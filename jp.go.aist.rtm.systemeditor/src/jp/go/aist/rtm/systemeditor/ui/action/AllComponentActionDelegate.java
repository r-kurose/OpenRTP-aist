package jp.go.aist.rtm.systemeditor.ui.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentComparator;
import jp.go.aist.rtm.systemeditor.ui.views.actionorderview.ActionOrderView.ActionName;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AllStart,AllStopを実行するアクション
 */
@Deprecated
public class AllComponentActionDelegate implements IEditorActionDelegate {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AllComponentActionDelegate.class);

	/**
	 * AllStartに使用されるID。この値が、Plugin.XMLに指定されなければならない。
	 */
	public static final String ALL_START_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllStart"; //$NON-NLS-1$

	/**
	 * AllStopに使用されるID。この値が、Plugin.XMLに指定されなければならない。
	 */
	public static final String ALL_STOP_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllStop"; //$NON-NLS-1$

	/**
	 * AllActivateに使用されるID。この値が、Plugin.XMLに指定されなければならない。
	 */
	public static final String ALL_ACTIVATE_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllActivate"; //$NON-NLS-1$

	/**
	 * AllDeactivateに使用されるID。この値が、Plugin.XMLに指定されなければならない。
	 */
	public static final String ALL_DEACTIVATE_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllDeactivate"; //$NON-NLS-1$

	/**
	 * AllExitに使用されるID。この値が、Plugin.XMLに指定されなければならない。
	 */
	public static final String ALL_EXIT_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllExit"; //$NON-NLS-1$
	
	private SystemDiagramEditor targetEditor;

	/**
	 * アクションのメインの実行メソッド
	 * <p>
	 * 実行可否の確認を求め、実行を行う。
	 */
	public void run(final IAction action) {
		final SystemDiagram systemDiagram = targetEditor.getSystemDiagram();

		String comfirmMessage = Messages.getString("AllComponentActionDelegate.4"); //$NON-NLS-1$
		if (ALL_START_ACTION_ID.equals(action.getId())) {
			comfirmMessage = Messages.getString("AllComponentActionDelegate.5"); //$NON-NLS-1$
		} else if (ALL_STOP_ACTION_ID.equals(action.getId())) {
			comfirmMessage = Messages.getString("AllComponentActionDelegate.6"); //$NON-NLS-1$
		} else if (ALL_ACTIVATE_ACTION_ID.equals(action.getId())) {
			comfirmMessage = Messages.getString("AllComponentActionDelegate.7"); //$NON-NLS-1$
		} else if (ALL_DEACTIVATE_ACTION_ID.equals(action.getId())) {
			comfirmMessage = Messages.getString("AllComponentActionDelegate.8"); //$NON-NLS-1$
		} else if (ALL_EXIT_ACTION_ID.equals(action.getId())) {
			comfirmMessage = Messages.getString("AllComponentActionDelegate.9"); //$NON-NLS-1$
		}

		boolean isOk = true;
		if (SystemEditorPreferenceManager.getInstance().isConfirmComponentAction() || ALL_EXIT_ACTION_ID.equals(action.getId()) ) {
			isOk = MessageDialog.openConfirm(targetEditor.getSite()
				.getShell(), Messages.getString("Common.dialog.confirm_title"), comfirmMessage); //$NON-NLS-1$
		}

		if (isOk) {

			ProgressMonitorDialog dialog = new ProgressMonitorDialog(
					targetEditor.getSite().getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask(Messages.getString("AllComponentActionDelegate.10"), 100); //$NON-NLS-1$

					monitor.subTask(Messages.getString("AllComponentActionDelegate.11")); //$NON-NLS-1$

					try {
						if (ALL_START_ACTION_ID.equals(action.getId())) {
							doAllStart(systemDiagram);
						} else if (ALL_STOP_ACTION_ID.equals(action.getId())) {
							doAllStop(systemDiagram);
						} else if (ALL_ACTIVATE_ACTION_ID
								.equals(action.getId())) {
							doAllActivate(systemDiagram);
						} else if (ALL_DEACTIVATE_ACTION_ID.equals(action
								.getId())) {
							doAllDectivate(systemDiagram);
						} else if (ALL_EXIT_ACTION_ID.equals(action
								.getId())) {
							doAllExit(systemDiagram);
						}
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}

					monitor.subTask(Messages.getString("AllComponentActionDelegate.12")); //$NON-NLS-1$
					monitor.done();
				}

			};

			try {
				dialog.run(false, false, runable);
			} catch (InvocationTargetException e) {
				MessageDialog.openError(targetEditor.getSite().getShell(),
						Messages.getString("Common.dialog.error_title"), Messages.getString("AllComponentActionDelegate.14") + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (InterruptedException e) {
				LOGGER.error("Fail in dialog (interrupted)", e);
			}
		}

	}

	private void doAllStop(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_SHUT_DOWN));
		for(int index=0; index<targetComps.size(); index++) {
			((CorbaComponent)targetComps.get(index)).stopAll();
		}
	}

	private void doAllStart(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_START_UP));
		for(int index=0; index<targetComps.size(); index++) {
			((CorbaComponent)targetComps.get(index)).startAll();
		}
	}

	private void doAllActivate(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_ACTIVATION));
		for(int index=0; index<targetComps.size(); index++) {
			((CorbaComponent)targetComps.get(index)).activateAll();
		}
	}

	private void doAllDectivate(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_DEACTIVATION));
		for(int index=0; index<targetComps.size(); index++) {
			((CorbaComponent)targetComps.get(index)).deactivateAll();
		}
	}

	private void doAllExit(SystemDiagram systemDiagram) {
		List<Component> targetComps = getTargetComps(systemDiagram);
		Collections.sort(targetComps, new ComponentComparator(ActionName.ACTION_FINALIZE));
		for(int index=0; index<targetComps.size(); index++) {
			((CorbaComponent)targetComps.get(index)).exitR();
		}
	}
	
	private List<Component> getTargetComps(SystemDiagram systemDiagram) {
		List<Component> targetComps = new ArrayList<Component>(); 
		for (Component comp : systemDiagram.getRegisteredComponents()) {
			if (!(comp instanceof CorbaComponent)) {
				continue;
			}
			targetComps.add(comp);
		}
		return targetComps;
	}
	
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (SystemDiagramEditor) targetEditor;
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
