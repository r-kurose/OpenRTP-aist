package jp.go.aist.rtm.systemeditor.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 800));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowFastViewBars(false);
        configurer.setShowMenuBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setTitle("RT System Editor RCP");
	}

	@Override
	public void postWindowCreate() {
//		this.getWindowConfigurer().getActionBarConfigurer().getCoolBarManager().removeAll();
//		super.postWindowCreate();
		IWorkbenchPage page =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		MenuManager mbManager=((ApplicationWindow)page.getWorkbenchWindow()).getMenuBarManager(); 

		for (IContributionItem item : mbManager.getItems()){
	       if (item.getId().equals("file")){
	    	   for (IContributionItem child : ((MenuManager)item).getItems() ) {
    			   String id = child.getId();
			       if (id!=null && 
			    		   (id.equals("converstLineDelimitersTo") || id.equals("save.ext")
			    				   || id.equals("org.eclipse.ui.edit.text.openExternalFile") || id.equals("new.ext")) ){
			    	   child.setVisible(false);
			    	   child.dispose();
			       }
	    	   }
//		       } else if (item.getId().equals("help")){
//		    	   for (IContributionItem child : ((MenuManager)item).getItems() ) {
//	    			   String id = child.getId();
//				       if (id!=null &&
//				    		   (id.equals("org.eclipse.ui.actions.showKeyAssistHandler") || id.equals("group.assist"))){
//				    	   child.dispose();
//				       }
//		    	   }
	       }
	   }
	}

	@Override
	public void postWindowOpen() {
//		super.postWindowOpen();
		IWorkbenchPage page =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		page.hideActionSet("org.eclipse.ui.WorkingSetActionSet");
		page.hideActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
		page.hideActionSet("org.eclipse.ui.edit.text.actionSet.navigation");
//		page.showActionSet("jp.go.aist.rtm.systemeditor.ui.actionSet");
	}
}
