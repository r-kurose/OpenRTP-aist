package jp.go.aist.rtm.systemeditor.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class PostSaveExtension {

	protected String messageTitle;
	protected String message;
	
	abstract public ResultCode execute(AbstractSystemDiagramEditor editor,IFile file,IProgressMonitor progressMonitor);
	
	public enum ResultCode {
		SUCCESS,
		FAILURE,
		THROUGH
	}
	public void showErrorMessage(Shell shell) {
		if(message != null && !message.equals("")) {
			MessageBox msgBox = new MessageBox(shell,SWT.OK);
			msgBox.setText(messageTitle);
			msgBox.setMessage(message);
			msgBox.open();
		}
	}
	
	public String getMessage() {
		return message;
	}

}
