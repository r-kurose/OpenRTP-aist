package jp.go.aist.rtm.nameserviceview.ui.action;

import java.io.File;
import java.io.IOException;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
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
	
	private static String SCRIPT_WINDOWS = System.getenv("RTM_ROOT") + "bin" + Path.SEPARATOR + "rtm-naming.bat";
	private static String SCRIPT_LINUX = "/usr/bin/rtm-naming";
	
	public void init(IViewPart view) {
		this.view = (NameServiceView) view;
	}

	public void run(IAction action) {
		String targetOS = System.getProperty("os.name").toLowerCase();
		boolean isWindows = false; 
		if(targetOS.toLowerCase().startsWith("windows")) {
			isWindows = true;
		}
		
		ProcessBuilder pb = null;
		if(isWindows) {
			pb = new ProcessBuilder(SCRIPT_WINDOWS);
			
		} else {
			String passWord = "";
			PasswordDialog  passwdDialog = new PasswordDialog(view.getSite().getShell());
			if(passwdDialog.open()!=Dialog.OK) return;
			
			passWord = passwdDialog.getPassWord();
			pb = new ProcessBuilder(SCRIPT_LINUX, "-f", "-w " + passWord);
		}
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/////
		//ネームサーバーの登録を複数回試行
		for(int index=0; index<10; index++) {
			NameServerContext server = NameServerManager.eInstance.addNameServer("localhost");
			if(server!=null) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		NameServerManager.eInstance.refreshAll();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		String target = "";
		String targetOS = System.getProperty("os.name").toLowerCase();
		if(targetOS.toLowerCase().startsWith("windows")) {
			target = SCRIPT_WINDOWS; 
		} else {
			target = SCRIPT_LINUX;
		}
		File targetFile = new File(target);
		if(targetFile.exists()==false) {
			action.setEnabled(false);
		}
	}
}
