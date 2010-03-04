package jp.go.aist.rtm.rtcbuilder.rcp.action;


import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

public class RCPActionFactory {
	
    public static final ActionFactory SWITCH_WORKSPACE = new ActionFactory(
            "openWorkspace") { //$NON-NLS-1$
        public IWorkbenchAction create(IWorkbenchWindow window) {
            if (window == null) {
                throw new IllegalArgumentException();
            }
            IWorkbenchAction action = new SwitchWorkspaceAction(window);
            action.setId(getId());
            return action;
        }
    };
}
