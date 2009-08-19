package jp.go.aist.rtm.repositoryView.ui.action;

import java.io.File;
import java.util.List;

import jp.go.aist.rtm.repositoryView.model.LocalRVRootItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewFactory;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.repositoryView.nl.Messages;
import jp.go.aist.rtm.repositoryView.ui.views.RepositoryView;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.util.ProfileHandler;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * XMLファイルからRTCをロードするアクションデリゲート
 * ViewActionLoadDirecrotyと共通する処理は別クラスに抽出したい
 *
 */
public class ViewActionLoadFile implements IViewActionDelegate {

	private RepositoryView view;
	
	public void init(IViewPart view) {
		this.view = (RepositoryView)view;
	}

	@SuppressWarnings("unchecked")
	public void run(IAction action) {

		//対象ファイルの選択
		FileDialog fileDialog = new FileDialog(view.getSite().getShell(), SWT.OPEN);
		fileDialog.setFilterNames(new String[]{Messages.getString("ViewActionLoadFile.0")}); //$NON-NLS-1$
		fileDialog.setFilterExtensions(new String[]{"*.xml"}); //$NON-NLS-1$
		String targetFileName = fileDialog.open();
		if( targetFileName==null ) return;
		
		ComponentSpecification module = null;
    	ProfileHandler handler = new ProfileHandler();
		try {
			module = handler.createComponent(targetFileName);
		} catch (Exception e) {
			MessageDialog.openError(view.getSite().getShell(), Messages.getString("ViewActionLoadFile.2"),	 //$NON-NLS-1$
					Messages.getString("ViewActionLoadFile.3")); //$NON-NLS-1$
			return;
		}
//		module.setPathId(targetFileName.substring(0, targetFileName.lastIndexOf(File.separator)));
		String fileName = targetFileName.substring(targetFileName.lastIndexOf(File.separator) + 1);
		module.setAliasName(module.getInstanceNameL() + "(" + fileName + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		module.setPathId("file://localhost/" + targetFileName + ":1"); //$NON-NLS-1$
		
    	TreeViewer viewer = this.view.getViewer();
    	RepositoryViewItem rootItem = new RepositoryViewItem("root", 0); //$NON-NLS-1$
    	rootItem.setChildren((List<RepositoryViewItem>)viewer.getInput());

 		RepositoryViewItem itemFirst = rootItem.getChild(module.getPathId());
 		if( itemFirst==null ) {
    		itemFirst = new LocalRVRootItem(module.getPathId());
			rootItem.addChild(itemFirst);
 		}
		RepositoryViewFactory.buildTree(itemFirst, module, RepositoryViewLeafItem.RTC_LEAF);
		viewer.refresh();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		
	}
}
