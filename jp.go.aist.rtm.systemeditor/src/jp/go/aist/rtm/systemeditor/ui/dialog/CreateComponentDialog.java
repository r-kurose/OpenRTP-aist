package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;

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

	private static final String LABEL_TYPE_TITLE = Messages
			.getString("CreateComponentDialog.2");
	private static final String LABEL_PROCESS_GROUP_TITLE = Messages
			.getString("CreateComponentDialog.process_group.title");
	private static final String LABEL_PARAMETER_TITLE = Messages
			.getString("CreateComponentDialog.3");
	private static final String ERR_INVALID_PARAM = Messages
			.getString("CreateComponentDialog.6");

	public static final String CONFIG_NAME = "CONFIG_NAME";
	public static final String CONFIG_VALUE = "CONFIG_VALUE";

	public static final int BUTTON_WIDTH = 70;

	private String parameter;
	private Text paramText;
	private Combo typeCombo;
	private Combo processGroupCombo;
	private List<String> typeList;
	private List<String> processGroupList;

	public CreateComponentDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public void setProcessGroupList(List<String> processGroupList) {
		this.processGroupList = processGroupList;
	}

	public String getParameter() {
		return this.parameter;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainComposite = new Composite(
				(Composite) super.createDialogArea(parent), SWT.NONE);

		GridLayout gl;
		gl = new GridLayout(2, false);
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label typeLabel = new Label(mainComposite, SWT.NONE);
		typeLabel.setText(LABEL_TYPE_TITLE);
		this.typeCombo = new Combo(mainComposite, SWT.NONE);
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.typeCombo.setLayoutData(gd);
		for (String type : this.typeList) {
			this.typeCombo.add(type);
		}
		this.typeCombo.select(0);
		this.parameter = this.typeCombo.getText();
		this.typeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				notifyModified();
			}
		});

		Label pgLabel = new Label(mainComposite, SWT.NONE);
		pgLabel.setText(LABEL_PROCESS_GROUP_TITLE);
		this.processGroupCombo = new Combo(mainComposite, SWT.NONE);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.processGroupCombo.setLayoutData(gd);
		for (String pg : this.processGroupList) {
			this.processGroupCombo.add(pg);
		}
		this.processGroupCombo.select(0);
		this.processGroupCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				notifyModified();
			}
		});

		Label paramLabel = new Label(mainComposite, SWT.NONE);
		paramLabel.setText(LABEL_PARAMETER_TITLE);
		this.paramText = new Text(mainComposite, SWT.MULTI | SWT.BORDER
				| SWT.V_SCROLL | SWT.WRAP);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.heightHint = 240;
		this.paramText.setLayoutData(gd);
		this.paramText.setText("");
		this.paramText.addModifyListener(new ModifyListener() {
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
		this.parameter = this.typeCombo.getText();
		StringBuffer sb = new StringBuffer();
		String pg = this.processGroupCombo.getText();
		if (pg != null && !pg.isEmpty()) {
			sb.append("process_group=" + pg);
		}
		String pm = this.paramText.getText();
		if (pm != null && !pm.isEmpty()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(pm);
		}
		if (sb.length() > 0) {
			this.parameter += "?" + sb.toString();
		}
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
		String errmsg = ERR_INVALID_PARAM;
		String p[] = this.parameter.split("\\?");
		if (p.length > 0 && p[0].indexOf("&") != -1) {
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
