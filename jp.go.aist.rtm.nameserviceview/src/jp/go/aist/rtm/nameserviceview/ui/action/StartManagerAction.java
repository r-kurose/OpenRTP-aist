package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class StartManagerAction implements IViewActionDelegate {
	private boolean isWindows = false; 
	private NameServiceView view;
	
	public void init(IViewPart view) {
		this.view = (NameServiceView) view;
	}

	public void run(IAction action) {
		view.getViewer().expandAll();
		List<TreeItem> items = new ArrayList<TreeItem>();
		getAllItems(view.getViewer().getTree(), items);
		RTCManager targetManager = null;
		for(TreeItem item : items) {
			Object adapter = AdapterUtil.getAdapter(item.getData(),
					RTCManager.class);
			if (adapter != null) {
				targetManager = (RTCManager) adapter;
				break;
			}
		}
		if(targetManager!=null) {
			targetManager.shutdownR();
		}
		/////
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}
		
		String target = "";
		if(isWindows) {
			target = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + System.getenv("RTM_VC_VERSION") + Path.SEPARATOR + "rtcd.exe"; 
		} else {
			target = "/usr/bin/rtcd"; 
		}
		try {
			ProcessBuilder pb = new ProcessBuilder(target, "-d");
			Process process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
	
	private void getAllItems(Tree tree, List<TreeItem> allItems) {
	    for(TreeItem item : tree.getItems()) {
	    	item.setExpanded(true);
	        getAllItems(item, allItems);
	    }
	}

	private void getAllItems(TreeItem currentItem, List<TreeItem> allItems)	{
	    TreeItem[] children = currentItem.getItems();

	    for(int index = 0; index < children.length; index++) {
	        allItems.add(children[index]);
	        getAllItems(children[index], allItems);
	    }
	}	
}
