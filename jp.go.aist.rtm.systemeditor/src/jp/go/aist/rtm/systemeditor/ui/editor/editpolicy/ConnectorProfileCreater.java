package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class ConnectorProfileCreater {
	
	protected ConnectorProfile connectorProfile;
	protected String messageTitle;
	protected String message;
	
	public enum ResultCode {
		SUCCESS,
		FAILURE,
		THROUGH
	}

	abstract public ResultCode getConnectorProfile(Port first,Port second,Shell shell) throws Exception;

	public ConnectorProfile getConnectorProfile() {
		return connectorProfile;
	}
	public String getMessage() {
		return message;
	}

	public void showErrorMessage(Shell shell) {
		if(message != null && !message.equals("")) {
			MessageBox msgBox = new MessageBox(shell,SWT.OK);
			msgBox.setText(messageTitle);
			msgBox.setMessage(message);
			msgBox.open();
		}
	}
	
}
