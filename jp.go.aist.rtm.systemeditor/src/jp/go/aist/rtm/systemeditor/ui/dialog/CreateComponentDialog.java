package jp.go.aist.rtm.systemeditor.ui.dialog;

import jp.go.aist.rtm.systemeditor.nl.Messages;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * マネージャビューからコンポーネントを作成するダイアログ
 *
 */
public class CreateComponentDialog extends TitleAreaDialog {

	public static final String CONFIG_NAME = "CONFIG_NAME"; //$NON-NLS-1$

	public static final String CONFIG_VALUE = "CONFIG_VALUE"; //$NON-NLS-1$

	public static final int BUTTON_WIDTH = 70;

	private String parameter;

	private Text paramText;

	private Combo typeCombo;

	@SuppressWarnings("unchecked")
	private EList typeList;
	
	public CreateComponentDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	@SuppressWarnings("unchecked")
	public void setTypeList(EList typeList) {
		this.typeList = typeList;
	}
	public String getParameter() {
		return this.parameter;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainComposite = new Composite((Composite) super.createDialogArea(parent), SWT.NONE);

		GridLayout gl;
		gl = new GridLayout(2, false);
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label type = new Label(mainComposite, SWT.NONE);
		type.setText(Messages.getString("CreateComponentDialog.2")); //$NON-NLS-1$
		typeCombo = new Combo(mainComposite, SWT.NONE);
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		typeCombo.setLayoutData(gd);
		for (Object typeObject : typeList) {
			typeCombo.add(typeObject.toString());
		}
		typeCombo.select(0);
		parameter = typeCombo.getText();
		typeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				notifyModified();
			}
		});
		
		Label param = new Label(mainComposite, SWT.NONE);
		param.setText(Messages.getString("CreateComponentDialog.3")); //$NON-NLS-1$
		paramText = new Text(mainComposite, SWT.MULTI | SWT.BORDER
						| SWT.V_SCROLL | SWT.WRAP);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.heightHint = 240;
		paramText.setLayoutData(gd);
		paramText.setText(""); //$NON-NLS-1$
		paramText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				notifyModified();
			}
		});

		return mainComposite;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		notifyModified();
		return composite;
	}

	/**
	 * 変更を通知します。
	 */
	private void notifyModified() {
		parameter = typeCombo.getText() + "?" + paramText.getText(); //$NON-NLS-1$
		if (!validateInput()) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		} else {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	private boolean validateInput() {
		if (this.parameter == null || this.parameter.length() == 0) {
			return false;
		}
		String errmsg = Messages.getString("CreateComponentDialog.6"); //$NON-NLS-1$
		String p[] = this.parameter.split("\\?"); //$NON-NLS-1$
		if (p.length > 0 && p[0].indexOf("&") != -1) { //$NON-NLS-1$
			// component_nameに&が入っている
			this.setMessage(errmsg, IMessageProvider.WARNING);
			return false;
		}
		if (p.length == 1) {
			// コンポーネント名のみ指定
			return true;
		}
		return true;
	}
}
