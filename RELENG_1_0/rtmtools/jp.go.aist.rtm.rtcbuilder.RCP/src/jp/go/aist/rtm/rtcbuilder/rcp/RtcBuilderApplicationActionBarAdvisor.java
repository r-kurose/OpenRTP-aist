package jp.go.aist.rtm.rtcbuilder.rcp;

import jp.go.aist.rtm.rtcbuilder.rcp.action.RCPActionFactory;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.NewWizardMenu;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.handlers.IActionCommandMappingService;
import org.eclipse.ui.internal.ide.AboutInfo;
import org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.actions.BuildSetMenu;
import org.eclipse.ui.internal.ide.actions.QuickMenuAction;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class RtcBuilderApplicationActionBarAdvisor extends ActionBarAdvisor {

    private NewWizardMenu newWizardMenu;
    @SuppressWarnings("restriction")
	private QuickMenuAction showInQuickMenu;
    //
    private IWorkbenchAction closeAction;
    private IWorkbenchAction closeAllAction;
    private IWorkbenchAction saveAction;
    private IWorkbenchAction saveAsAction;
    private IWorkbenchAction saveAllAction;
    private IWorkbenchAction quitAction;
    //
    private IWorkbenchAction openWorkspaceAction;
    private IWorkbenchAction switchWorkspaceAction;
    private IWorkbenchAction importResourcesAction;
    private IWorkbenchAction exportResourcesAction;
    //
    private IWorkbenchAction undoAction;
    private IWorkbenchAction redoAction;
    //
    private IWorkbenchAction goIntoAction;
    private IWorkbenchAction backAction;
    private IWorkbenchAction forwardAction;
    private IWorkbenchAction upAction;
    private IWorkbenchAction nextAction;
    private IWorkbenchAction previousAction;
    private IWorkbenchAction backwardHistoryAction;
    private IWorkbenchAction forwardHistoryAction;
    //
    IWorkbenchAction buildAllAction; // Incremental workspace build
    // IDE-specific retarget actions
    IWorkbenchAction buildProjectAction;
    MenuManager buildWorkingSetMenu;
    private IWorkbenchAction cleanAction;
    private IWorkbenchAction toggleAutoBuildAction;
    private IWorkbenchAction projectPropertyDialogAction;
    //
    private IWorkbenchAction newWindowAction;
    private IWorkbenchAction newEditorAction;
    //
    private IWorkbenchAction editActionSetAction;
    private IWorkbenchAction savePerspectiveAction;
    private IWorkbenchAction resetPerspectiveAction;
    private IWorkbenchAction closePerspAction;
    private IWorkbenchAction closeAllPerspsAction;
    //
    private IWorkbenchAction showPartPaneMenuAction;
    private IWorkbenchAction showViewMenuAction;
    private IWorkbenchAction quickAccessAction;
    private IWorkbenchAction maximizePartAction;
    private IWorkbenchAction minimizePartAction;
    private IWorkbenchAction nextPerspectiveAction;
    private IWorkbenchAction prevPerspectiveAction;
    private IWorkbenchAction activateEditorAction;
    private IWorkbenchAction nextPartAction;
    private IWorkbenchAction prevPartAction;
    private IWorkbenchAction nextEditorAction;
    private IWorkbenchAction prevEditorAction;
    private IWorkbenchAction switchToEditorAction;
    private IWorkbenchAction openPreferencesAction;
    //
    private IWorkbenchAction introAction;
    private IWorkbenchAction quickStartAction;
    private IWorkbenchAction tipsAndTricksAction;
    private IWorkbenchAction helpContentsAction;
    private IWorkbenchAction helpSearchAction;
    private IWorkbenchAction dynamicHelpAction;
    private IWorkbenchAction aboutAction;
    
	public RtcBuilderApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@SuppressWarnings("restriction")
	protected void makeActions(final IWorkbenchWindow window) {
		//
        closeAction = ActionFactory.CLOSE.create(window);
        register(closeAction);
        closeAllAction = ActionFactory.CLOSE_ALL.create(window);
        register(closeAllAction);
        saveAction = ActionFactory.SAVE.create(window);
        register(saveAction);
        saveAsAction = ActionFactory.SAVE_AS.create(window);
        register(saveAsAction);
        saveAllAction = ActionFactory.SAVE_ALL.create(window);
        register(saveAllAction);
        quitAction = ActionFactory.QUIT.create(window);
        register(quitAction);
        //
        
        openWorkspaceAction = RCPActionFactory.SWITCH_WORKSPACE.create(window);
    	register(openWorkspaceAction);
    	
        importResourcesAction = ActionFactory.IMPORT.create(window);
        register(importResourcesAction);
        exportResourcesAction = ActionFactory.EXPORT.create(window);
        register(exportResourcesAction);
        ////
        undoAction = ActionFactory.UNDO.create(window);
        register(undoAction);
        redoAction = ActionFactory.REDO.create(window);
        register(redoAction);
        //
        goIntoAction = ActionFactory.GO_INTO.create(window);
        register(goIntoAction);
        backAction = ActionFactory.BACK.create(window);
        register(backAction);
        forwardAction = ActionFactory.FORWARD.create(window);
        register(forwardAction);
        upAction = ActionFactory.UP.create(window);
        register(upAction);
        nextAction = ActionFactory.NEXT.create(window);
        nextAction.setImageDescriptor(IDEInternalWorkbenchImages
                        .getImageDescriptor(IDEInternalWorkbenchImages.IMG_ETOOL_NEXT_NAV));
        register(nextAction);
        previousAction = ActionFactory.PREVIOUS.create(window);
        previousAction.setImageDescriptor(IDEInternalWorkbenchImages
                        .getImageDescriptor(IDEInternalWorkbenchImages.IMG_ETOOL_PREVIOUS_NAV));
        register(previousAction);
        forwardHistoryAction = ActionFactory.FORWARD_HISTORY.create(window);
        register(forwardHistoryAction);
        backwardHistoryAction = ActionFactory.BACKWARD_HISTORY.create(window);
        register(backwardHistoryAction);
        //
        buildAllAction = IDEActionFactory.BUILD.create(window);
        register(buildAllAction);
        buildProjectAction = IDEActionFactory.BUILD_PROJECT.create(window);
        register(buildProjectAction);
        //
        String showInQuickMenuId = "org.eclipse.ui.navigate.showInQuickMenu"; //$NON-NLS-1$
        showInQuickMenu = new QuickMenuAction(showInQuickMenuId) {
            protected void fillMenu(IMenuManager menu) {
                menu.add(ContributionItemFactory.VIEWS_SHOW_IN.create(window));
            }
        };
        register(showInQuickMenu);
        //
        cleanAction = IDEActionFactory.BUILD_CLEAN.create(window);
        register(cleanAction);
        toggleAutoBuildAction = IDEActionFactory.BUILD_AUTOMATICALLY.create(window);
        register(toggleAutoBuildAction);
        projectPropertyDialogAction = IDEActionFactory.OPEN_PROJECT_PROPERTIES.create(window);
    	register(projectPropertyDialogAction);
    	//
        newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(getWindow());
        newWindowAction.setText(IDEWorkbenchMessages.Workbench_openNewWindow);
        register(newWindowAction);
		newEditorAction = ActionFactory.NEW_EDITOR.create(window);
		register(newEditorAction);
		//
        editActionSetAction = ActionFactory.EDIT_ACTION_SETS.create(window);
        register(editActionSetAction);
        savePerspectiveAction = ActionFactory.SAVE_PERSPECTIVE.create(window);
        register(savePerspectiveAction);
        resetPerspectiveAction = ActionFactory.RESET_PERSPECTIVE.create(window);
        register(resetPerspectiveAction);
        closePerspAction = ActionFactory.CLOSE_PERSPECTIVE.create(window);
        register(closePerspAction);
        closeAllPerspsAction = ActionFactory.CLOSE_ALL_PERSPECTIVES.create(window);
        register(closeAllPerspsAction);
        //
        showPartPaneMenuAction = ActionFactory.SHOW_PART_PANE_MENU.create(window);
        register(showPartPaneMenuAction);
        showViewMenuAction = ActionFactory.SHOW_VIEW_MENU.create(window);
        register(showViewMenuAction);
        quickAccessAction = ActionFactory.SHOW_QUICK_ACCESS.create(window);
        maximizePartAction = ActionFactory.MAXIMIZE.create(window);
        register(maximizePartAction);
		minimizePartAction = ActionFactory.MINIMIZE.create(window);
		register(minimizePartAction);
        nextPerspectiveAction = ActionFactory.NEXT_PERSPECTIVE.create(window);
        register(nextPerspectiveAction);
        prevPerspectiveAction = ActionFactory.PREVIOUS_PERSPECTIVE.create(window);
        register(prevPerspectiveAction);
        ActionFactory.linkCycleActionPair(nextPerspectiveAction, prevPerspectiveAction);
        activateEditorAction = ActionFactory.ACTIVATE_EDITOR.create(window);
        register(activateEditorAction);
        nextEditorAction = ActionFactory.NEXT_EDITOR.create(window);
        register(nextEditorAction);
        prevEditorAction = ActionFactory.PREVIOUS_EDITOR.create(window);
        register(prevEditorAction);
        ActionFactory.linkCycleActionPair(nextEditorAction, prevEditorAction);
        nextPartAction = ActionFactory.NEXT_PART.create(window);
        register(nextPartAction);
        prevPartAction = ActionFactory.PREVIOUS_PART.create(window);
        register(prevPartAction);
        ActionFactory.linkCycleActionPair(nextPartAction, prevPartAction);
        switchToEditorAction = ActionFactory.SHOW_OPEN_EDITORS.create(window);
        register(switchToEditorAction);
        openPreferencesAction = ActionFactory.PREFERENCES.create(window);
        register(openPreferencesAction);
        //
        if (window.getWorkbench().getIntroManager().hasIntro()) {
            introAction = ActionFactory.INTRO.create(window);
            register(introAction);
        }
        makeFeatureDependentActions(window);
        helpContentsAction = ActionFactory.HELP_CONTENTS.create(window);
        register(helpContentsAction);
        helpSearchAction = ActionFactory.HELP_SEARCH.create(window);
        register(helpSearchAction);
        dynamicHelpAction = ActionFactory.DYNAMIC_HELP.create(window);
        register(dynamicHelpAction);
        aboutAction = ActionFactory.ABOUT.create(window);
        aboutAction.setImageDescriptor(IDEInternalWorkbenchImages
                        .getImageDescriptor(IDEInternalWorkbenchImages.IMG_OBJS_DEFAULT_PROD));
        register(aboutAction);
        
        System.setProperty("eclipse.vm","");
        System.setProperty("eclipse.commands","");
	}

	@SuppressWarnings("restriction")
	@Override
	public void dispose() {
        showInQuickMenu.dispose();
        //
	    newWizardMenu = null;
	    closeAction = null;
	    closeAllAction = null;
	    saveAction = null;
	    saveAsAction = null;
	    saveAllAction = null;
	    quitAction = null;
	    //
	    openWorkspaceAction = null;
	    importResourcesAction = null;
	    exportResourcesAction = null;
	    //
	    undoAction = null;
	    redoAction = null;
	    //
	    goIntoAction = null;
	    backAction = null;
	    forwardAction = null;
	    upAction = null;
	    nextAction = null;
	    previousAction = null;
	    backwardHistoryAction = null;
	    forwardHistoryAction = null;
        showInQuickMenu = null;
        //
        buildAllAction = null;
        buildProjectAction = null;
        buildWorkingSetMenu = null;
        cleanAction = null;
        toggleAutoBuildAction = null;
        projectPropertyDialogAction = null;
        //
        newWindowAction = null;
		newEditorAction = null;
		//
		editActionSetAction = null;
		savePerspectiveAction = null;
		resetPerspectiveAction = null;
		closePerspAction = null;
		closeAllPerspsAction = null;
		//
		showPartPaneMenuAction = null;
		showViewMenuAction = null;
        quickAccessAction.dispose();
        quickAccessAction = null;
        maximizePartAction = null;
        minimizePartAction = null;
        nextPerspectiveAction = null;
        prevPerspectiveAction = null;
        activateEditorAction = null;
        nextPartAction = null;
        prevPartAction = null;
        nextEditorAction = null;
        prevEditorAction = null;
        switchToEditorAction = null;
        openPreferencesAction = null;
        //
        introAction = null;
        quickStartAction = null;
        tipsAndTricksAction = null;
        helpContentsAction = null;
        helpSearchAction = null;
		dynamicHelpAction = null;
		aboutAction = null;
		
		super.dispose();
	}

	protected void fillMenuBar(IMenuManager menuBar) {
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createNavigateMenu());
        menuBar.add(createProjectMenu());
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(createWindowMenu());
        menuBar.add(createHelpMenu());
	}
	
    @SuppressWarnings("restriction")
	private MenuManager createHelpMenu() {
		MenuManager menu = new MenuManager(IDEWorkbenchMessages.Workbench_help, IWorkbenchActionConstants.M_HELP);
		addSeparatorOrGroupMarker(menu, "group.intro"); //$NON-NLS-1$
		// See if a welcome or intro page is specified
		if (introAction != null) {
			menu.add(introAction);
		} else if (quickStartAction != null) {
			menu.add(quickStartAction);
		}
		menu.add(new GroupMarker("group.intro.ext")); //$NON-NLS-1$
		addSeparatorOrGroupMarker(menu, "group.main"); //$NON-NLS-1$
		menu.add(helpContentsAction);
        menu.add(helpSearchAction);
		menu.add(dynamicHelpAction);
		addSeparatorOrGroupMarker(menu, "group.assist"); //$NON-NLS-1$
		// See if a tips and tricks page is specified
		if (tipsAndTricksAction != null) {
			menu.add(tipsAndTricksAction);
		}
		// HELP_START should really be the first item, but it was after
		// quickStartAction and tipsAndTricksAction in 2.1.
		menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
		menu.add(new GroupMarker("group.main.ext")); //$NON-NLS-1$
		addSeparatorOrGroupMarker(menu, "group.tutorials"); //$NON-NLS-1$
		addSeparatorOrGroupMarker(menu, "group.tools"); //$NON-NLS-1$
		addSeparatorOrGroupMarker(menu, "group.updates"); //$NON-NLS-1$
		menu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
		addSeparatorOrGroupMarker(menu, IWorkbenchActionConstants.MB_ADDITIONS);
		// about should always be at the bottom
		menu.add(new Separator("group.about")); //$NON-NLS-1$
		
		ActionContributionItem aboutItem = new ActionContributionItem(aboutAction);
		aboutItem.setVisible(!"carbon".equals(SWT.getPlatform())); //$NON-NLS-1$
        menu.add(aboutItem);
		menu.add(new GroupMarker("group.about.ext")); //$NON-NLS-1$
        return menu;
    }
    @SuppressWarnings("restriction")
    private MenuManager createWindowMenu() {
        MenuManager menu = new MenuManager(
                IDEWorkbenchMessages.Workbench_window, IWorkbenchActionConstants.M_WINDOW);

        menu.add(newWindowAction);
		menu.add(newEditorAction);
		
        menu.add(new Separator());
        addPerspectiveActions(menu);
        menu.add(new Separator());
        addKeyboardShortcuts(menu);
        Separator sep = new Separator(IWorkbenchActionConstants.MB_ADDITIONS);
		sep.setVisible(!"carbon".equals(SWT.getPlatform())); //$NON-NLS-1$
		menu.add(sep);
        
        // See the comment for quit in createFileMenu
        ActionContributionItem openPreferencesItem = new ActionContributionItem(openPreferencesAction);
        openPreferencesItem.setVisible(!"carbon".equals(SWT.getPlatform())); //$NON-NLS-1$
        menu.add(openPreferencesItem);

        menu.add(ContributionItemFactory.OPEN_WINDOWS.create(getWindow()));
        return menu;
    }
    @SuppressWarnings("restriction")
    private MenuManager createProjectMenu() {
        MenuManager menu = new MenuManager(
                IDEWorkbenchMessages.Workbench_project, IWorkbenchActionConstants.M_PROJECT);
        menu.add(new Separator(IWorkbenchActionConstants.PROJ_START));

        menu.add(getOpenProjectItem());
        menu.add(getCloseProjectItem());
        menu.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
        menu.add(new Separator());
        menu.add(buildAllAction);
        menu.add(buildProjectAction);
        addWorkingSetBuildActions(menu);
        menu.add(cleanAction);
        menu.add(toggleAutoBuildAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.BUILD_EXT));
        menu.add(new Separator());

        menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new GroupMarker(IWorkbenchActionConstants.PROJ_END));
        menu.add(new Separator());
        menu.add(projectPropertyDialogAction);
        return menu;
    }
    
    @SuppressWarnings("restriction")
    private MenuManager createNavigateMenu() {
        MenuManager menu = new MenuManager(
                IDEWorkbenchMessages.Workbench_navigate, IWorkbenchActionConstants.M_NAVIGATE);
        menu.add(new GroupMarker(IWorkbenchActionConstants.NAV_START));
        menu.add(goIntoAction);

        MenuManager goToSubMenu = new MenuManager(IDEWorkbenchMessages.Workbench_goTo, IWorkbenchActionConstants.GO_TO);
        menu.add(goToSubMenu);
        goToSubMenu.add(backAction);
        goToSubMenu.add(forwardAction);
        goToSubMenu.add(upAction);
        goToSubMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

        menu.add(new Separator(IWorkbenchActionConstants.OPEN_EXT));
        for (int i = 2; i < 5; ++i) {
            menu.add(new Separator(IWorkbenchActionConstants.OPEN_EXT + i));
        }
        menu.add(new Separator(IWorkbenchActionConstants.SHOW_EXT));
        {
			MenuManager showInSubMenu = new MenuManager(
					IDEWorkbenchMessages.Workbench_showIn, "showIn"); //$NON-NLS-1$
			showInSubMenu.setActionDefinitionId(showInQuickMenu
					.getActionDefinitionId());
			showInSubMenu.add(ContributionItemFactory.VIEWS_SHOW_IN
					.create(getWindow()));
			menu.add(showInSubMenu);
		}
        for (int i = 2; i < 5; ++i) {
            menu.add(new Separator(IWorkbenchActionConstants.SHOW_EXT + i));
        }
        menu.add(new Separator());
        menu.add(nextAction);
        menu.add(previousAction);
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        menu.add(new GroupMarker(IWorkbenchActionConstants.NAV_END));

        //TBD: Location of this actions
        menu.add(new Separator());
        menu.add(backwardHistoryAction);
        menu.add(forwardHistoryAction);
        return menu;
    }
    
    @SuppressWarnings("restriction")
    private MenuManager createEditMenu() {
        MenuManager menu = new MenuManager(IDEWorkbenchMessages.Workbench_edit, IWorkbenchActionConstants.M_EDIT);
        menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));

        menu.add(undoAction);
        menu.add(redoAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
        menu.add(new Separator());

        menu.add(getCutItem());
        menu.add(getCopyItem());
        menu.add(getPasteItem());
        menu.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));
        menu.add(new Separator());

        menu.add(getDeleteItem());
        menu.add(getSelectAllItem());
        menu.add(new Separator());

        menu.add(getFindItem());
        menu.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));
        menu.add(new Separator());

        menu.add(getBookmarkItem());
        menu.add(getTaskItem());
        menu.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));

        menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        return menu;
    }
    
    @SuppressWarnings("restriction")
    private MenuManager createFileMenu() {
        MenuManager menu = new MenuManager(IDEWorkbenchMessages.Workbench_file, IWorkbenchActionConstants.M_FILE);
        menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
        {
            // create the New submenu, using the same id for it as the New action
            String newText = IDEWorkbenchMessages.Workbench_new;
            String newId = ActionFactory.NEW.getId();
            MenuManager newMenu = new MenuManager(newText, newId);
            newMenu.setActionDefinitionId("org.eclipse.ui.file.newQuickMenu"); //$NON-NLS-1$
            newMenu.add(new Separator(newId));
            this.newWizardMenu = new NewWizardMenu(getWindow());
            newMenu.add(this.newWizardMenu);
            newMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
            menu.add(newMenu);
        }
		
        menu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
        menu.add(new Separator());

        menu.add(closeAction);
        menu.add(closeAllAction);
        //		menu.add(closeAllSavedAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
        menu.add(new Separator());
        menu.add(saveAction);
        menu.add(saveAsAction);
        menu.add(saveAllAction);
        menu.add(getRevertItem());
        menu.add(new Separator());
        menu.add(getMoveItem());
        menu.add(getRenameItem());
        menu.add(getRefreshItem());

        menu.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
        menu.add(new Separator());
        menu.add(getPrintItem());
        menu.add(new GroupMarker(IWorkbenchActionConstants.PRINT_EXT));
        menu.add(new Separator());
        menu.add(openWorkspaceAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
        menu.add(new Separator());
        menu.add(importResourcesAction);
        menu.add(exportResourcesAction);
        menu.add(new GroupMarker(IWorkbenchActionConstants.IMPORT_EXT));
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

        menu.add(new Separator());
        menu.add(getPropertiesItem());

        menu.add(ContributionItemFactory.REOPEN_EDITORS.create(getWindow()));
        menu.add(new GroupMarker(IWorkbenchActionConstants.MRU));
        menu.add(new Separator());
        
        // If we're on OS X we shouldn't show this command in the File menu. It
		// should be invisible to the user. However, we should not remove it -
		// the carbon UI code will do a search through our menu structure
		// looking for it when Cmd-Q is invoked (or Quit is chosen from the
		// application menu.
		ActionContributionItem quitItem = new ActionContributionItem(quitAction);
		quitItem.setVisible(!"carbon".equals(SWT.getPlatform())); //$NON-NLS-1$
		menu.add(quitItem);
		menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		return menu;
    }
    
    @SuppressWarnings("restriction")
	private void addSeparatorOrGroupMarker(MenuManager menu, String groupId) {
		String prefId = "useSeparator." + menu.getId() + "." + groupId; //$NON-NLS-1$ //$NON-NLS-2$
		boolean addExtraSeparators = IDEWorkbenchPlugin.getDefault()
				.getPreferenceStore().getBoolean(prefId);
		if (addExtraSeparators) {
			menu.add(new Separator(groupId));
		} else {
			menu.add(new GroupMarker(groupId));
		}
	}
    @SuppressWarnings({ "restriction", "deprecation" })
    private void makeFeatureDependentActions(IWorkbenchWindow window) {
        AboutInfo[] infos = null;
        
        IPreferenceStore prefs = IDEWorkbenchPlugin.getDefault().getPreferenceStore();

        // Optimization: avoid obtaining the about infos if the platform state is
        // unchanged from last time.  See bug 75130 for details.
        String stateKey = "platformState"; //$NON-NLS-1$
        String prevState = prefs.getString(stateKey);
        String currentState = String.valueOf(Platform.getStateStamp());
        boolean sameState = currentState.equals(prevState);
        if (!sameState) {
        	prefs.putValue(stateKey, currentState);
        }
        
        // See if a welcome page is specified.
        // Optimization: if welcome pages were found on a previous run, then just add the action.
        String quickStartKey = IDEActionFactory.QUICK_START.getId(); 
        String showQuickStart = prefs.getString(quickStartKey);
        if (sameState && "true".equals(showQuickStart)) { //$NON-NLS-1$
            quickStartAction = IDEActionFactory.QUICK_START.create(window);
			register(quickStartAction);
        }
        else if (sameState && "false".equals(showQuickStart)) { //$NON-NLS-1$
        	// do nothing
        }
        else {
        	// do the work
    		infos = IDEWorkbenchPlugin.getDefault().getFeatureInfos();
        	boolean found = hasWelcomePage(infos);
            prefs.setValue(quickStartKey, found);
            if (found) {
                quickStartAction = IDEActionFactory.QUICK_START.create(window);
                register(quickStartAction);
	        }
        }
        
        // See if a tips and tricks page is specified.
        // Optimization: if tips and tricks were found on a previous run, then just add the action.
        String tipsAndTricksKey = IDEActionFactory.TIPS_AND_TRICKS.getId();
        String showTipsAndTricks = prefs.getString(tipsAndTricksKey);
        if (sameState && "true".equals(showTipsAndTricks)) { //$NON-NLS-1$
            tipsAndTricksAction = IDEActionFactory.TIPS_AND_TRICKS
					.create(window);
			register(tipsAndTricksAction);
        }
        else if (sameState && "false".equals(showTipsAndTricks)) { //$NON-NLS-1$
        	// do nothing
        }
        else {
        	// do the work
	    	if (infos == null) {
	    		infos = IDEWorkbenchPlugin.getDefault().getFeatureInfos();
	    	}
	    	boolean found = hasTipsAndTricks(infos);
	    	prefs.setValue(tipsAndTricksKey, found);
	    	if (found) {
	            tipsAndTricksAction = IDEActionFactory.TIPS_AND_TRICKS
						.create(window);
				register(tipsAndTricksAction);
		    }
        }
    }
    @SuppressWarnings("restriction")
    private boolean hasWelcomePage(AboutInfo[] infos) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getWelcomePageURL() != null) {
            	return true;
            }
        }
        return false;
	}
    @SuppressWarnings("restriction")
    private boolean hasTipsAndTricks(AboutInfo[] infos) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getTipsAndTricksHref() != null) {
            	return true;
            }
        }
        return false;
	}
    @SuppressWarnings("restriction")
    private void addKeyboardShortcuts(MenuManager menu) {
        MenuManager subMenu = new MenuManager(IDEWorkbenchMessages.Workbench_shortcuts, "shortcuts"); //$NON-NLS-1$
        menu.add(subMenu);
        subMenu.add(showPartPaneMenuAction);
        subMenu.add(showViewMenuAction);
        subMenu.add(quickAccessAction);
        subMenu.add(new Separator());
        subMenu.add(maximizePartAction);
        subMenu.add(minimizePartAction);
        subMenu.add(new Separator());
        subMenu.add(activateEditorAction);
        subMenu.add(nextEditorAction);
        subMenu.add(prevEditorAction);
        subMenu.add(switchToEditorAction);
        subMenu.add(new Separator());
        subMenu.add(nextPartAction);
        subMenu.add(prevPartAction);
        subMenu.add(new Separator());
        subMenu.add(nextPerspectiveAction);
        subMenu.add(prevPerspectiveAction);
    }
    @SuppressWarnings("restriction")
    private void addPerspectiveActions(MenuManager menu) {
        {
            String openText = IDEWorkbenchMessages.Workbench_openPerspective;
            MenuManager changePerspMenuMgr = new MenuManager(openText,
                    "openPerspective"); //$NON-NLS-1$
            IContributionItem changePerspMenuItem = ContributionItemFactory.PERSPECTIVES_SHORTLIST
                    .create(getWindow());
            changePerspMenuMgr.add(changePerspMenuItem);
            menu.add(changePerspMenuMgr);
        }
        {
            MenuManager showViewMenuMgr = new MenuManager(IDEWorkbenchMessages.Workbench_showView, "showView"); //$NON-NLS-1$
            IContributionItem showViewMenu = ContributionItemFactory.VIEWS_SHORTLIST
                    .create(getWindow());
            showViewMenuMgr.add(showViewMenu);
            menu.add(showViewMenuMgr);
        }
        menu.add(new Separator());
        menu.add(editActionSetAction);
        menu.add(savePerspectiveAction);
        menu.add(resetPerspectiveAction);
        menu.add(closePerspAction);
        menu.add(closeAllPerspsAction);
    }
    
    @SuppressWarnings("restriction")
    private void addWorkingSetBuildActions(MenuManager menu) {
        buildWorkingSetMenu = new MenuManager(IDEWorkbenchMessages.Workbench_buildSet);
        IContributionItem workingSetBuilds = new BuildSetMenu(getWindow(),
                getActionBarConfigurer());
        buildWorkingSetMenu.add(workingSetBuilds);
        menu.add(buildWorkingSetMenu);
    }
    
    @SuppressWarnings("restriction")
    private IContributionItem getRevertItem() {
		return getItem(
				ActionFactory.REVERT.getId(),
				"org.eclipse.ui.file.revert", //$NON-NLS-1$
				null, null, WorkbenchMessages.Workbench_revert,
				WorkbenchMessages.Workbench_revertToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getMoveItem() {
		return getItem(ActionFactory.MOVE.getId(), "org.eclipse.ui.edit.move", //$NON-NLS-1$
				null, null, WorkbenchMessages.Workbench_move,
				WorkbenchMessages.Workbench_moveToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getRenameItem() {
		return getItem(ActionFactory.RENAME.getId(),
				"org.eclipse.ui.edit.rename", null, null, //$NON-NLS-1$
				WorkbenchMessages.Workbench_rename,
				WorkbenchMessages.Workbench_renameToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getRefreshItem() {
		return getItem(ActionFactory.REFRESH.getId(),
				"org.eclipse.ui.file.refresh", null, null, //$NON-NLS-1$
				WorkbenchMessages.Workbench_refresh,
				WorkbenchMessages.Workbench_refreshToolTip, null);
	}
    @SuppressWarnings("restriction")
	private IContributionItem getPrintItem() {
		return getItem(
				ActionFactory.PRINT.getId(),
				"org.eclipse.ui.file.print", ISharedImages.IMG_ETOOL_PRINT_EDIT, //$NON-NLS-1$
				ISharedImages.IMG_ETOOL_PRINT_EDIT_DISABLED,
				WorkbenchMessages.Workbench_print,
				WorkbenchMessages.Workbench_printToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getPropertiesItem() {
		return getItem(ActionFactory.PROPERTIES.getId(),
				"org.eclipse.ui.file.properties", null, null, //$NON-NLS-1$
				WorkbenchMessages.Workbench_properties,
				WorkbenchMessages.Workbench_propertiesToolTip, null);
	}
    ////
    @SuppressWarnings("restriction")
    private IContributionItem getCutItem() {
		return getItem(
				ActionFactory.CUT.getId(),
				"org.eclipse.ui.edit.cut", //$NON-NLS-1$
				ISharedImages.IMG_TOOL_CUT,
				ISharedImages.IMG_TOOL_CUT_DISABLED,
				WorkbenchMessages.Workbench_cut,
				WorkbenchMessages.Workbench_cutToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getCopyItem() {
		return getItem(
				ActionFactory.COPY.getId(),
				"org.eclipse.ui.edit.copy", //$NON-NLS-1$
				ISharedImages.IMG_TOOL_COPY,
				ISharedImages.IMG_TOOL_COPY_DISABLED,
				WorkbenchMessages.Workbench_copy,
				WorkbenchMessages.Workbench_copyToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getPasteItem() {
		return getItem(
				ActionFactory.PASTE.getId(),
				"org.eclipse.ui.edit.paste", ISharedImages.IMG_TOOL_PASTE, //$NON-NLS-1$
				ISharedImages.IMG_TOOL_PASTE_DISABLED,
				WorkbenchMessages.Workbench_paste,
				WorkbenchMessages.Workbench_pasteToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getDeleteItem() {
        return getItem(ActionFactory.DELETE.getId(),
        		"org.eclipse.ui.edit.delete", //$NON-NLS-1$
        		ISharedImages.IMG_TOOL_DELETE,
        		ISharedImages.IMG_TOOL_DELETE_DISABLED,
        		WorkbenchMessages.Workbench_delete,
        		WorkbenchMessages.Workbench_deleteToolTip, 
        		IWorkbenchHelpContextIds.DELETE_RETARGET_ACTION);
    }
    @SuppressWarnings("restriction")
    private IContributionItem getSelectAllItem() {
		return getItem(
				ActionFactory.SELECT_ALL.getId(),
				"org.eclipse.ui.edit.selectAll", //$NON-NLS-1$
				null, null, WorkbenchMessages.Workbench_selectAll,
				WorkbenchMessages.Workbench_selectAllToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getFindItem() {
		return getItem(
				ActionFactory.FIND.getId(),
				"org.eclipse.ui.edit.findReplace", //$NON-NLS-1$
				null, null, WorkbenchMessages.Workbench_findReplace,
				WorkbenchMessages.Workbench_findReplaceToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getBookmarkItem() {
		return getItem(
				IDEActionFactory.BOOKMARK.getId(),
				"org.eclipse.ui.edit.addBookmark", //$NON-NLS-1$
				null, null, IDEWorkbenchMessages.Workbench_addBookmark,
				IDEWorkbenchMessages.Workbench_addBookmarkToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getTaskItem() {
		return getItem(
				IDEActionFactory.ADD_TASK.getId(),
				"org.eclipse.ui.edit.addTask", //$NON-NLS-1$
				null, null, IDEWorkbenchMessages.Workbench_addTask,
				IDEWorkbenchMessages.Workbench_addTaskToolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getOpenProjectItem() {
		return getItem(IDEActionFactory.OPEN_PROJECT.getId(),
				"org.eclipse.ui.project.openProject", null, null, //$NON-NLS-1$
				IDEWorkbenchMessages.OpenResourceAction_text,
				IDEWorkbenchMessages.OpenResourceAction_toolTip, null);
	}
    @SuppressWarnings("restriction")
    private IContributionItem getCloseProjectItem() {
		return getItem(
				IDEActionFactory.CLOSE_PROJECT.getId(),
				"org.eclipse.ui.project.closeProject", //$NON-NLS-1$
				null, null, IDEWorkbenchMessages.CloseResourceAction_text,
				IDEWorkbenchMessages.CloseResourceAction_text, null);
	}

    
    @SuppressWarnings("restriction")
    private IContributionItem getItem(String actionId, String commandId,
    		String image, String disabledImage, String label, String tooltip, String helpContextId) {
		ISharedImages sharedImages = getWindow().getWorkbench()
				.getSharedImages();

		IActionCommandMappingService acms = (IActionCommandMappingService) getWindow()
				.getService(IActionCommandMappingService.class);
		acms.map(actionId, commandId);

		CommandContributionItemParameter commandParm = new CommandContributionItemParameter(
				getWindow(), actionId, commandId, null, sharedImages
						.getImageDescriptor(image), sharedImages
						.getImageDescriptor(disabledImage), null, label, null,
				tooltip, CommandContributionItem.STYLE_PUSH, null, false);
		return new CommandContributionItem(commandParm);
	}
    private IWorkbenchWindow getWindow() {
        return getActionBarConfigurer().getWindowConfigurer().getWindow();
    }
}
