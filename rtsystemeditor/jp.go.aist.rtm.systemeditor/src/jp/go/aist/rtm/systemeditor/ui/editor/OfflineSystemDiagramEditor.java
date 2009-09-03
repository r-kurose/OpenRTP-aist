package jp.go.aist.rtm.systemeditor.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import jp.go.aist.rtm.repositoryView.RepositoryViewPlugin;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.ui.views.RepositoryView;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.action.RestoreOption;
import jp.go.aist.rtm.systemeditor.ui.util.RtsProfileHandler;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

/**
 * SystemDiagramEditorクラス
 */
public class OfflineSystemDiagramEditor extends AbstractSystemDiagramEditor {

	/**
	 * システムダイアグラムエディタのID
	 */
	public static final String OFFLINE_SYSTEM_DIAGRAM_EDITOR_ID = "jp.go.aist.rtm.systemeditor.ui.editor.OfflineSystemDiagramEditor"; //$NON-NLS-1$

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		IEditorInput newInput = new NullEditorInput();
		super.init(site, newInput);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getSystemDiagram().setKind(SystemDiagramKind.OFFLINE_LITERAL);
	}

	protected IEditorInput load(IEditorInput input, final IEditorSite site,
			final RestoreOption restore) throws PartInitException {
		
		IEditorInput targetInput = getTargetInput(input
				, Messages.getString("OfflineSystemDiagramEditor.7"));

		if (targetInput instanceof FileEditorInput)	 {
			// RTSプロファイルをファイルからロードする
			doLoad(site, (FileEditorInput)targetInput);
		}

		postLoad();

		return targetInput;
	}

	private void doLoad(final IEditorSite site, FileEditorInput editorInput)
			throws PartInitException {
		try {
			final String strPath = editorInput.getPath().toOSString();

			ProgressMonitorDialog dialog = new ProgressMonitorDialog(site
					.getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				@SuppressWarnings("unchecked")
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException,
						InterruptedException {

					monitor.beginTask(Messages.getString("OfflineSystemDiagramEditor.2"), 100); //$NON-NLS-1$
					monitor.subTask(Messages.getString("OfflineSystemDiagramEditor.3")); //$NON-NLS-1$
					monitor.internalWorked(20);

					try {
						RepositoryView repositoryViewerPart = 
							  (RepositoryView)getSite().getWorkbenchWindow().getActivePage().
							  findView(RepositoryViewPlugin.PLUGIN_ID +  ".view"); //$NON-NLS-1$
						
						RtsProfileHandler handler = new RtsProfileHandler();
						handler.setRepositoryModel((List<RepositoryViewItem>) repositoryViewerPart.getModel());
						SystemDiagram diagram = handler.load(strPath, SystemDiagramKind.OFFLINE_LITERAL);
						handler.restoreConfigSet(diagram);
						handler.restoreCompositeComponentPort(diagram);
						handler.restoreConnection(diagram);
						setSystemDiagram(diagram);
					} catch (Exception e) {
						monitor.done();
						throw new InvocationTargetException(e,
								Messages.getString("OfflineSystemDiagramEditor.5") + e.getMessage()); //$NON-NLS-1$
					}
					monitor.done();
				}
			};

			dialog.run(false, false, runable);
		} catch (Exception e) {
			throw new PartInitException(Messages.getString("OfflineSystemDiagramEditor.6"), e); //$NON-NLS-1$
		}
	}

	@Override
	public String getEditorId() {
		return OFFLINE_SYSTEM_DIAGRAM_EDITOR_ID;
	}

	@Override
	public boolean isOnline() {
		return false;
	}
}
