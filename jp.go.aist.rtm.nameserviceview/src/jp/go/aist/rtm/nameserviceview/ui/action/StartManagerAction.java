package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.IOException;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class StartManagerAction implements IViewActionDelegate {
	private boolean isWindows = false; 
	
	public void init(IViewPart view) {
	}

	public void run(IAction action) {
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}
		
		if(isWindows) {
			String target = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + System.getenv("RTM_VC_VERSION") + Path.SEPARATOR + "rtcd.exe"; 
			try {
				ProcessBuilder pb = new ProcessBuilder(target);
				Process process = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			String target = "/usr/bin/rtcd"; 
			try {
				ProcessBuilder pb = new ProcessBuilder(target);
				Process process = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
