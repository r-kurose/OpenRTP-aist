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

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IAction saveAction, saveAsAction, quitAction, aboutAction, deleteAction;
	private IWorkbenchAction preferenceAction;

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
		preferenceAction = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction);
		//
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		
		MenuManager fileMenu = new MenuManager(IDEWorkbenchMessages.Workbench_file, IWorkbenchActionConstants.M_FILE);
		menuBar.add(fileMenu);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
		fileMenu.add(new Separator());
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        fileMenu.add(quitAction);
        //
		IMenuManager toolsMenu = new MenuManager(IDEWorkbenchMessages.Workbench_window, "SystemEditor_Window");
		menuBar.add(toolsMenu);
		toolsMenu.add(preferenceAction);
	}
}
