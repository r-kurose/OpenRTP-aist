package jp.go.aist.rtm.systemeditor.rcp;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IAction saveAction, saveAsAction, quitAction, aboutAction, deleteAction;
	private IWorkbenchAction preferenceAction;
//	private IContributionItem viewAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(final IWorkbenchWindow window) {
    	saveAction = ActionFactory.SAVE.create(window);
        register(saveAction);
        //
    	saveAsAction = ActionFactory.SAVE_AS.create(window);
        register(saveAsAction);
        //
		quitAction = ActionFactory.QUIT.create(window);
        register(quitAction);
        //
		aboutAction = ActionFactory.ABOUT.create(window);
        register(aboutAction);
        //
        deleteAction =  ActionFactory.DELETE.create(window);
        register(deleteAction);
		//
//        viewAction = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
        
        //
		preferenceAction = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction);
		//
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		
		MenuManager fileMenu = new MenuManager(IDEWorkbenchMessages.Workbench_file, IWorkbenchActionConstants.M_FILE);
//		MenuManager fileMenu = new MenuManager("&File", "SystemEditor_File");
		menuBar.add(fileMenu);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
		fileMenu.add(new Separator());
//        fileMenu.add(loadAction);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
//		fileMenu.add(new Separator());
        fileMenu.add(quitAction);
        //
		IMenuManager toolsMenu = new MenuManager(IDEWorkbenchMessages.Workbench_window, "SystemEditor_Window");
		menuBar.add(toolsMenu);
//		toolsMenu.add(viewAction);
//		toolsMenu.add(new Separator());
		toolsMenu.add(preferenceAction);
        //
		MenuManager helpMenu = new MenuManager(IDEWorkbenchMessages.Workbench_help, IWorkbenchActionConstants.M_HELP);
//		MenuManager helpMenu = new MenuManager("&Help", "SystemEditor_Help");
		menuBar.add(helpMenu);
        helpMenu.add(aboutAction);
        helpMenu.add(new Separator());
        helpMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
	}
}
