package jp.go.aist.rtm.repositoryView.ui.action;

import java.util.List;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.ui.dialog.RTRepositoryConnectDialog;
import jp.go.aist.rtm.repositoryView.ui.views.RepositoryView;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * RTレポジトリに接続するアクション
 *
 */
public class ConnectRTRepositoryAction implements IViewActionDelegate {

	private RepositoryView view;

	public void init(IViewPart view) {
		this.view = (RepositoryView) view;
	}

	@SuppressWarnings("unchecked")
	public void run(IAction action) {
		TreeViewer viewer = this.view.getViewer();
		RTRepositoryConnectDialog dialog = new RTRepositoryConnectDialog(view
				.getSite().getShell());
		if( dialog.open()==0 ) {
			((List<RepositoryViewItem>)viewer.getInput()).add(dialog.getResultItem());
			viewer.refresh();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

}
