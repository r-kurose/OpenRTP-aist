package jp.go.aist.rtm.nameserviceview.ui.dialog;

import jp.go.aist.rtm.nameserviceview.nl.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * RT Name Service ViewのAboutダイアログ
 * <p>
 * HTMLにより作成する
 */
public class AboutDialog extends Dialog {
	private static final String html = "<html>" //$NON-NLS-1$
			+ "<body bgcolor=\"#eeeeee\">" //$NON-NLS-1$
			+ "<center>" //$NON-NLS-1$
			+ "<table bgcolor=\"#ffffff\" width=\"100%\" cellspacing=\"0\"cellpadding=\"0\" border=\"1\">" //$NON-NLS-1$
			+ "<tr>" //$NON-NLS-1$
			+ "<td align=\"center\">" //$NON-NLS-1$
			+ Messages.getString("AboutDialog.6") //$NON-NLS-1$
			+ "</td>" //$NON-NLS-1$
			+ "</tr>" //$NON-NLS-1$
			+ "</table>" //$NON-NLS-1$
			+ Messages.getString("AboutDialog.10") //$NON-NLS-1$
			+ Messages.getString("AboutDialog.11") //$NON-NLS-1$
			+ Messages.getString("AboutDialog.12") //$NON-NLS-1$
			+ Messages.getString("AboutDialog.13") //$NON-NLS-1$
			+ Messages.getString("AboutDialog.14") //$NON-NLS-1$
			+ Messages.getString("AboutDialog.15") //$NON-NLS-1$
			+ "<p>" //$NON-NLS-1$
			+ Messages.getString("AboutDialog.17") //$NON-NLS-1$
			+ "</p>" + "</center>" + "</body>" + "</html>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	/**
	 * コンストラクタ
	 * 
	 * @param shell
	 */
	public AboutDialog(Shell shell) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
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

		Browser browser = new Browser(mainComposite, SWT.NONE);
		browser.setText(html);
		browser.setLayoutData(new GridData(GridData.FILL_BOTH));
		browser.setSize(500, 390);

		return mainComposite;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("AboutDialog.22")); //$NON-NLS-1$
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
