package jp.go.aist.rtm.nameserviceview.ui.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.nameserviceview.ui.dialog.ShowIORDialog;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

/**
 * IOR情報を表示するアクション
 */
public class ShowIORActionDelegate implements IObjectActionDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShowIORActionDelegate.class);

	private IWorkbenchPart targetPart;
	private IStructuredSelection selection;

	@Override
	public void run(IAction action) {
		Object first = this.selection.getFirstElement();
		if (!(first instanceof CorbaWrapperObject)) {
			LOGGER.error("Unexpected the selected object: obj=<{}>", first);
			return;
		}

		CorbaUtil.IORInfo info = CorbaUtil.getIORInfo(((CorbaWrapperObject) first).getCorbaObject());
		LOGGER.debug("IORInfo: <{}>", info);

		ShowIORDialog dialog = new ShowIORDialog(this.targetPart.getSite().getShell(), info);
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		LOGGER.trace("selectionChanged: action=<{}> selection=<{}>", action, selection);
		this.selection = (IStructuredSelection) selection;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		LOGGER.trace("setActivePart: action=<{}> target=<{}>", action, targetPart);
		this.targetPart = targetPart;
	}

}
