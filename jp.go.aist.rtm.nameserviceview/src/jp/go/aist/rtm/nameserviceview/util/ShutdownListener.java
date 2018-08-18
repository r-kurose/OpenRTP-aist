package jp.go.aist.rtm.nameserviceview.util;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.Node;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

public class ShutdownListener implements IWorkbenchListener {
	@Override
	public void postShutdown(IWorkbench arg0) {
	}

	@Override
	public boolean preShutdown(IWorkbench arg0, boolean arg1) {
		StringBuilder builder = new StringBuilder();
		EList<Node> nodeList = NameServerManager.eInstance.getNodes();
		for(Node target : nodeList) {
			NameServerContext node = (NameServerContext)target;
			if(0<builder.length()) {
				builder.append(",");
			}
			builder.append(node.getNameServerName());
		}
		NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
				NameServiceViewPlugin.COMBO_ITEMS_KEY, builder.toString());
		
		return true;
	}
}
