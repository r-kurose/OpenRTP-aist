package jp.go.aist.rtm.nameserviceview.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * ゾンビを一括して削除するアクション
 *
 */
public class KillZombiesAction implements IViewActionDelegate {

//	@Override
	public void init(IViewPart view) {
	}

	public void run(IAction action) {
		Job job = new Job("ゾンビ削除処理") {
			@SuppressWarnings("unchecked")
			protected IStatus run(IProgressMonitor monitor) {

				monitor.beginTask("すべてのゾンビエントリを削除します", 100);

				monitor.internalWorked(30);

				monitor.subTask("ゾンビのエントリを削除しています...");

				List<NamingObjectNode> deleteList = new ArrayList<NamingObjectNode>();
				for (Iterator iter = NameServerManager.eInstance
						.eAllContents(); iter.hasNext();) {
					Object obj = iter.next();
					if (obj instanceof NamingObjectNode) {
						NamingObjectNode namingObjectNode = (NamingObjectNode) obj;
						if (namingObjectNode.isZombie()) {
							deleteList.add(namingObjectNode);
						}
					}
				}

				for (NamingObjectNode namingObjectNode : deleteList) {
					try {
						namingObjectNode.deleteR();
					} catch (Exception e) {
						// void
					}
				}

				monitor.subTask("ゾンビのエントリの削除を終了しました。");
				monitor.done();

				return org.eclipse.core.runtime.Status.OK_STATUS;
			}

		};
		job.schedule();

	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

}
