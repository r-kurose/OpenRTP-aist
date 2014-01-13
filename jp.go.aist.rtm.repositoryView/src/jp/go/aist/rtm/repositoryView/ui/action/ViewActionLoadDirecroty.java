package jp.go.aist.rtm.repositoryView.ui.action;

import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import jp.go.aist.rtm.repositoryView.model.LocalRVRootItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewFactory;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.repositoryView.nl.Messages;
import jp.go.aist.rtm.repositoryView.ui.views.RepositoryView;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.util.RtcProfileHandler;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * ディレクトリからRTCをロードするアクションデリゲート
 *
 */
public class ViewActionLoadDirecroty implements IViewActionDelegate  {

	private RepositoryView view;
	
	public void init(IViewPart view) {
		this.view = (RepositoryView) view;
	}

	@SuppressWarnings("unchecked")
	public void run(IAction action) {
		System.out.println("ViewActionLoadDirecroty.run() entry");

		DirectoryDialog directoryDialog = new DirectoryDialog(view.getSite().getShell(),SWT.NULL);
		directoryDialog.setText(Messages.getString("ViewActionLoadDirecroty.0")); //$NON-NLS-1$
		directoryDialog.setMessage(Messages.getString("ViewActionLoadDirecroty.1")); //$NON-NLS-1$
		String filePath = directoryDialog.open();

		if (filePath == null) return;

		File dir = new File(filePath);
    	File[] files = dir.listFiles(new Filter());
		
    	TreeViewer viewer = this.view.getViewer();
    	RepositoryViewItem rootItem = new RepositoryViewItem("root", 0); //$NON-NLS-1$
    	rootItem.setChildren((List<RepositoryViewItem>)viewer.getInput());
    	
    	RepositoryViewItem itemFirst = rootItem.getChild(filePath);
    	if( itemFirst == null ) {
    		itemFirst = new LocalRVRootItem(filePath);
    		((List)viewer.getInput()).add(itemFirst);
    	}
    	ComponentSpecification module = null;
    	RtcProfileHandler handler = new RtcProfileHandler();
		for (int intIdx = 0; intIdx < files.length; intIdx++){
    		try {
				module = handler.createComponent(files[intIdx].toString());
			} catch (Exception e) {
				MessageDialog.openError(view.getSite().getShell(), Messages.getString("ViewActionLoadDirecroty.3"),	 //$NON-NLS-1$
				Messages.getString("ViewActionLoadDirecroty.4") + "\r\n\r\n[ " + e.getMessage() + " ]"); //$NON-NLS-1$
				return;
			}
			module.setAliasName(module.getInstanceNameL() + "(" + files[intIdx].getName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$

			/////
			//TODO 09.09.30 pathURI対応
//			module.setPathId("file://localhost/" + files[intIdx].getPath() + ":1"); //$NON-NLS-1$
			//
			File convFile = new File(files[intIdx].getPath());
			try {
				URI pathUri = new URI("file", "localhost",convFile.toURI().getPath() , null );
				module.setPathId(pathUri.toString()); //$NON-NLS-1$
			} catch (URISyntaxException e) {
				MessageDialog.openError(view.getSite().getShell(), Messages.getString("ViewActionLoadDirecroty.3"),	 //$NON-NLS-1$
				Messages.getString("ViewActionLoadDirecroty.4") + "\r\n\r\n[ " + e.getMessage() + " ]"); //$NON-NLS-1$
				return;
			}
			/////
			RepositoryViewFactory.buildTree(itemFirst, module, RepositoryViewLeafItem.RTC_LEAF);
		}
    	viewer.refresh();
		System.out.println("ViewActionLoadDirecroty.run() return");
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	class Filter implements FileFilter {
		public boolean accept(File file) {
			if (!file.isFile()) {
				return false;
			}
			String s = file.getName();
			int x = s.lastIndexOf('.');
			if ( x < 0) {
				return false;
			}
			String extention = s.substring(x+1).toLowerCase();
			if (extention.endsWith("xml")) { //$NON-NLS-1$
				return true;
			}
			return false;
		}
	}

	
}
