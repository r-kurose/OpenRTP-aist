package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.IOException;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.ui.dialog.PasswordDialog;
import jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class StartNameServiceAction implements IViewActionDelegate {
	private NameServiceView view;
	private boolean isWindows = false; 
	
//	@Override
	public void init(IViewPart view) {
		this.view = (NameServiceView) view;
	}

	public void run(IAction action) {
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}
		
		if(isWindows) {
			String target = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtm-naming.bat"; 
			try {
				ProcessBuilder pb = new ProcessBuilder(target);
				Process process = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			String passWord = "";
			PasswordDialog  passwdDialog = new PasswordDialog(view.getSite().getShell());
			if(passwdDialog.open()!=Dialog.OK) return;
			
			passWord = passwdDialog.getPassWord();
			String target = "rtm-naming"; 
			try {
				ProcessBuilder pb = new ProcessBuilder(target, "-f", "-w " + passWord);
				Process process = pb.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/////
		NameServerManager.eInstance.addNameServer("localhost");
		NameServerManager.eInstance.refreshAll();
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
