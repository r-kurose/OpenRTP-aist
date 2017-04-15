package jp.go.aist.rtm.systemeditor.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.PreferenceManager;
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
        //
        PreferenceManager prefer = (PreferenceManager)PlatformUI.getWorkbench().getPreferenceManager();
        prefer.remove("org.eclipse.ui.preferencePages.Workbench");
	}

	@Override
	public void postWindowCreate() {
		IMenuManager mbManager = getWindowConfigurer().getActionBarConfigurer()
				.getMenuManager();
		for (IContributionItem item : mbManager.getItems()) {
			if (item.getId().equals("file")) {
				for (IContributionItem child : ((IMenuManager) item).getItems()) {
					String id = child.getId();
					if (id != null
							&& (id.equals("converstLineDelimitersTo")
									|| id.equals("save.ext")
									|| id.equals("org.eclipse.ui.edit.text.openExternalFile") || id
										.equals("new.ext"))) {
						child.setVisible(false);
						child.dispose();
					}
				}
			}
		}
	}

	@Override
	public void postWindowOpen() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		page.hideActionSet("org.eclipse.ui.WorkingSetActionSet");
		page.hideActionSet("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
		page.hideActionSet("org.eclipse.ui.edit.text.actionSet.navigation");
	}
}
