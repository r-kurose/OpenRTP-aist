package jp.go.aist.rtm.rtcbuilder.rcp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.Preferences;

import jp.go.aist.rtm.rtcbuilder.rcp.action.SwitchWorkspaceAction;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * This class controls all aspects of the application's execution
 */
public class RtcBuilderApplication implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		/////
		Location instanceLoc = Platform.getInstanceLocation();
		
		String commandStr = System.getProperty(SwitchWorkspaceAction.PROP_COMMANDS);
		String storedString = Preferences.userNodeForPackage(RtcBuilderActivator.class).get("workspace", "");
		String workspace = null;
		if(storedString!=null && storedString.contains(SwitchWorkspaceAction.CMD_DATA)) {
			workspace = getDataValue(instanceLoc, storedString); 
			instanceLoc.set(new URL("file", null, workspace), false);
		} else if(commandStr!=null && commandStr.contains(SwitchWorkspaceAction.CMD_DATA)) {
							workspace = getDataValue(instanceLoc, commandStr); 
			instanceLoc.set(new URL("file", null, workspace), false);
		}
		
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new RtcBuilderApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	private String getDataValue(Location instanceLoc, String storedString)
			throws IOException, MalformedURLException {
		final String[] commands = storedString.split("\\n"); 
		for (int index=0; index<commands.length; index++) { 
			if (SwitchWorkspaceAction.CMD_DATA.equals(commands[index])) {
				return commands[index+1];
			} 
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
