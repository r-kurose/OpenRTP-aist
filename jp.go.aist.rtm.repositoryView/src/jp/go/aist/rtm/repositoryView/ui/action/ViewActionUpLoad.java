package jp.go.aist.rtm.repositoryView.ui.action;

import java.io.IOException;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewFactory;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.repositoryView.model.ServerRVRootItem;
import jp.go.aist.rtm.repositoryView.repository.RTRepositoryAccesser;
import jp.go.aist.rtm.repositoryView.ui.views.RepositoryView;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.repositoryView.nl.Messages;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * RTレポジトリに選択したノードをアップロードするアクションデリゲート
 *
 */
public class ViewActionUpLoad implements IObjectActionDelegate {

	private RepositoryView view;
	private ISelection selection;

	public void run(IAction action) {
		//対象ファイル選択画面の表示
		FileDialog fileDialog = new FileDialog(view.getSite().getShell(), SWT.OPEN);
		fileDialog.setFilterNames(new String[]{Messages.getString("ViewActionUpLoad.0"),Messages.getString("ViewActionUpLoad.1"),Messages.getString("ViewActionUpLoad.2"),Messages.getString("ViewActionUpLoad.3")}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		fileDialog.setFilterExtensions(new String[]{"*.zip","*.tar","*.gz","*.*"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String targetFileName = fileDialog.open();
		if( targetFileName==null ) return;
		
		IStructuredSelection selected = (IStructuredSelection)this.selection;
		ServerRVRootItem targetServer = (ServerRVRootItem)selected.getFirstElement();
		ComponentSpecification module = null;
		try {
			module = RTRepositoryAccesser.getInstance().uploadProfile(targetFileName, targetServer.getName());
		} catch (IOException e) {
			MessageDialog.openError(view.getSite().getShell(), Messages.getString("ViewActionUpLoad.8"),	 //$NON-NLS-1$
			Messages.getString("ViewActionUpLoad.9")); //$NON-NLS-1$
			return;
		} catch (Exception e) {
			MessageDialog.openError(view.getSite().getShell(), Messages.getString("ViewActionUpLoad.10"),	 //$NON-NLS-1$
			Messages.getString("ViewActionUpLoad.11")); //$NON-NLS-1$
			return;
		}
		//情報追加
    	TreeViewer viewer = this.view.getViewer();
    	module.setAliasName(module.getInstanceNameL());
		RepositoryViewFactory.buildTree(targetServer, module, RepositoryViewLeafItem.RTC_LEAF, true);
		viewer.refresh();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.view = (RepositoryView)targetPart;
	}

}
