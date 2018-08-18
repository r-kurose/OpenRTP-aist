package jp.go.aist.rtm.nameserviceview.ui.dialog;

import jp.go.aist.rtm.nameserviceview.nl.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PasswordDialog extends Dialog {
	private Text txtPasswd;
	private String passWord = "";
	
	public String getPassWord() {
		return passWord;
	}

	public PasswordDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Input Password");
	}
	
	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout();

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.getString("PasswordDialog.1")); //$NON-NLS-1$
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;
		
		txtPasswd = new Text(mainComposite, SWT.PASSWORD|SWT.SINGLE|SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		txtPasswd.setLayoutData(gd);

		return mainComposite;
	}
	
	@Override
	protected void okPressed() {
		passWord = txtPasswd.getText();
		super.okPressed();
	}

}
