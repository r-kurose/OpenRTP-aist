package jp.go.aist.rtm.rtcbuilder.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class RtcBuilderApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public RtcBuilderApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new RtcBuilderApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1100, 800));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowFastViewBars(false);
        configurer.setShowMenuBar(true);
        configurer.setShowPerspectiveBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setTitle("RtcBuilder RCP");
        IPreferenceStore apiStore = PlatformUI.getPreferenceStore();
		apiStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, "TOP_RIGHT");
	}

	@Override
	public void postWindowCreate() {
//		this.getWindowConfigurer().getActionBarConfigurer().getCoolBarManager().removeAll();
//		super.postWindowCreate();
		IWorkbenchPage page =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		MenuManager mbManager=((ApplicationWindow)page.getWorkbenchWindow()).getMenuBarManager(); 

//		for (IContributionItem item : mbManager.getItems()){
//	       if (item.getId().equals("file")){
//	    	   for (IContributionItem child : ((MenuManager)item).getItems() ) {
//    			   String id = child.getId();
//			       if (id!=null && 
////			    		   (id.equals("converstLineDelimitersTo") || id.equals("save.ext")
////			    				   || id.equals("org.eclipse.ui.edit.text.openExternalFile") || id.equals("new.ext")) ){
//			    		   (id.equals("converstLineDelimitersTo") || id.equals("save.ext")
//			    				   || id.equals("org.eclipse.ui.edit.text.openExternalFile") ) ){
//			    	   child.setVisible(false);
//			    	   child.dispose();
//			       }
//	    	   }
//	       }
//	   }
	}

	@Override
	public void postWindowOpen() {
//		super.postWindowOpen();
//		IWorkbenchPage page =
//			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		page.hideActionSet("org.eclipse.ui.WorkingSetActionSet");
//		page.hideActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
//		page.hideActionSet("org.eclipse.ui.edit.text.actionSet.navigation");
////		page.showActionSet("jp.go.aist.rtm.systemeditor.ui.actionSet");
	}
}
