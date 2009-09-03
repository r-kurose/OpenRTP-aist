package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * データポート間の接続のコネクタプロファイルの選択ダイアログ
 * <P>
 * コネクタプロファイルの選択可能なタイプを、OutPortおよびInPortから判断しプルダウンとして表示する。（「Any」自体は表示されない）(文字のケースは無視してマッチングし、マッチした際に表示されるのはOutPortの文字列とする)<br>
 * 選択可能であるのは、データポートだけであり、サービスポートは含まれない。<br>
 * OutPortもしくはInPortに「Any」が含まれている場合には、相手のすべて型を受け入れるものとする。<br>
 * OutPortおよびInportにAnyが含まれている場合には、コンボボックスに任意の文字列を入力可能とし、「*任意入力可」を表示する。<br>
 * サブスクリプションタイプは、データフロータイプが「Push」の時のみ表示される。<br>
 * PushRateは、サブスクリプションタイプが「Periodic」であり、かつデータフロータイプが「Push」の時のみ表示される<br>
 */
public class DataConnectorCreaterDialog extends TitleAreaDialog {

	private static final int COMBO_MIN = 72;

	private Text nameText;

	private Combo dataTypeCombo;

	private Combo interfaceTypeCombo;

	private Combo dataflowTypeCombo;

	private Combo subscriptionTypeCombo;

	private ConnectorProfile connectorProfile;

	private ConnectorProfile dialogResult;

	private Text pushRateText;

	private OutPort outport;

	private InPort inport;

	public DataConnectorCreaterDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * ConnectorProfileCreaterインタフェースの実装メソッド
	 * <p>
	 * ConnectorProfileとなる候補が複数ある場合には、ダイアログを表示し、ConnectorProfileを作成する。
	 */
	public ConnectorProfile getConnectorProfile(OutPort outport, InPort inport) {
		this.outport = outport;
		this.inport = inport;

		connectorProfile = ComponentFactory.eINSTANCE
				.createConnectorProfile();
		connectorProfile.setName(outport.getNameL() + "_" //$NON-NLS-1$
				+ inport.getNameL());

		setShellStyle(this.getShellStyle() | SWT.RESIZE  );
		open();

		return dialogResult;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		gl = new GridLayout();

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.getString("DataConnectorCreaterDialog.1")); //$NON-NLS-1$
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		return mainComposite;
	}

	/**
	 * メインとなる表示部を作成する
	 */
	private void createConnectorProfileComposite(Composite mainComposite) {
		GridLayout gl;
		GridData gd;
		Composite portProfileEditComposite = new Composite(mainComposite,
				SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginBottom = 0;
		gl.marginHeight = 0;
		gl.marginLeft = 0;
		gl.marginRight = 0;
		gl.marginTop = 0;
		gl.marginWidth = 0;
		portProfileEditComposite.setLayout(gl);
		portProfileEditComposite
				.setLayoutData(new GridData(GridData.FILL_BOTH));

		int style;

		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText(Messages.getString("DataConnectorCreaterDialog.2")); //$NON-NLS-1$
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 140;
		gd.horizontalSpan = 2;
		nameText.setLayoutData(gd);
		nameText.setTextLimit(255);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setName(nameText.getText());
				notifyModified();
			}
		});

		Label dataTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.3")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		dataTypeCombo.setLayoutData(gd);
		dataTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataType(dataTypeCombo.getText());
				notifyModified();
			}
		});
		Label dataTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataTypeFooterLabel.setText(ConnectorUtil.isAllowAnyDataType(
				outport, inport) ? Messages.getString("DataConnectorCreaterDialog.4") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label interfaceTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		interfaceTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.6")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyInterfaceType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		interfaceTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		interfaceTypeCombo.setLayoutData(gd);
		interfaceTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setInterfaceType(interfaceTypeCombo.getText());
				notifyModified();
			}
		});
		Label interfaceTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		interfaceTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnyInterfaceType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.7") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label dataflowTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataflowTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.9")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataflowType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataflowTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		dataflowTypeCombo.setLayoutData(gd);
		dataflowTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataflowType(dataflowTypeCombo.getText());
				notifyModified();
			}
		});
		Label dataflowTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataflowTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnyDataflowType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.10") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label subscriptionTypeLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.12")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnySubscriptionType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		subscriptionTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		subscriptionTypeCombo.setLayoutData(gd);
		subscriptionTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setSubscriptionType(subscriptionTypeCombo
						.getText());
				notifyModified();
			}
		});
		Label subscriptionTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnySubscriptionType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.13") : ""); //$NON-NLS-1$ //$NON-NLS-2$
		subscriptionTypeCombo.setEnabled(false);

		Label pushRate = new Label(portProfileEditComposite, SWT.NONE);
		pushRate.setText(Messages.getString("DataConnectorCreaterDialog.15")); //$NON-NLS-1$
		pushRateText = new Text(portProfileEditComposite, SWT.SINGLE
				| SWT.BORDER);
		pushRateText.setEnabled(false);
		gd = new GridData();
		gd.widthHint = COMBO_MIN + 21;
		pushRateText.setLayoutData(gd);
		pushRateText.setTextLimit(9);
		pushRateText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = pushRateText.getText();

				boolean isDouble = false;
				try {
					Double.parseDouble(text);
					isDouble = true;
				} catch (Exception ex) {
					// void
				}

				if (isDouble) {
					connectorProfile.setPushRate(Double.parseDouble(text));
				}

				notifyModified();
			}
		});

		loadData();
	}

	/**
	 * モデル情報にアクセスし、表示に設定する
	 */
	@SuppressWarnings("unchecked")
	private void loadData() {

		nameText.setText(connectorProfile.getName());
		dataTypeCombo.setItems((String[]) ConnectorUtil.getAllowDataTypes(
				outport, inport).toArray(
				new String[0]));
		connectorProfile.setDataType(getDefaultValue(ConnectorUtil
				.getAllowDataTypes(outport, inport), ConnectorUtil
				.isAllowAnyDataType(outport, inport)));
		dataTypeCombo.select(ConnectorUtil
				.getAllowDataTypes(outport, inport).indexOf(
						connectorProfile.getDataType()));

		if( inport.eContainer() instanceof ComponentSpecification ) {
			interfaceTypeCombo.setItems(SystemEditorPreferenceManager.getInstance().getInterfaceTypes());
			interfaceTypeCombo.select(0);
			//
			dataflowTypeCombo.setItems(SystemEditorPreferenceManager.getInstance().getDataFlowTypes());
			dataflowTypeCombo.select(0);
			//
			subscriptionTypeCombo.setItems(SystemEditorPreferenceManager.getInstance().getSubscriptionTypes());
			subscriptionTypeCombo.select(0);
		} else {
			interfaceTypeCombo.setItems((String[]) ConnectorUtil
					.getAllowInterfaceTypes(outport, inport).toArray(
							new String[0]));
			connectorProfile.setInterfaceType(getDefaultValue(ConnectorUtil
					.getAllowInterfaceTypes(outport, inport), ConnectorUtil
					.isAllowAnyInterfaceType(outport, inport)));
			interfaceTypeCombo.select(ConnectorUtil.getAllowInterfaceTypes(
					outport, inport).indexOf(connectorProfile.getInterfaceType()));

			dataflowTypeCombo.setItems((String[]) ConnectorUtil
					.getAllowDataflowTypes(outport, inport).toArray(
							new String[0]));
			connectorProfile.setDataflowType(getDefaultValue(ConnectorUtil
					.getAllowDataflowTypes(outport, inport), ConnectorUtil
					.isAllowAnyDataflowType(outport, inport)));
			dataflowTypeCombo.select(ConnectorUtil.getAllowDataflowTypes(
					outport, inport).indexOf(connectorProfile.getDataflowType()));
	
			subscriptionTypeCombo.setItems((String[]) ConnectorUtil
					.getAllowSubscriptionTypes(outport, inport).toArray(
							new String[0]));
			connectorProfile.setSubscriptionType(getDefaultValue(ConnectorUtil
					.getAllowSubscriptionTypes(outport, inport), ConnectorUtil
					.isAllowAnySubscriptionType(outport, inport)));
			subscriptionTypeCombo.select(ConnectorUtil
					.getAllowSubscriptionTypes(outport, inport).indexOf(
							connectorProfile.getSubscriptionType()));
		}
	}

	/**
	 * コンボにおいて、「表示候補のリスト」と、「どのような文字列でも設定可能であるかどうか」を引数に取り、初期表示の文字列を決定する
	 * 
	 * @param candidateList
	 *            表示候補リスト
	 * @param isAllowAny
	 *            どのような文字列でも設定可能であるかどうか
	 * @return 初期表示の文字列
	 */
	@SuppressWarnings("unchecked")
	private String getDefaultValue(List candidateList, boolean isAllowAny) {
		String result = null;

		if (candidateList.size() > 0) {
			result = (String) candidateList.get(0);
		}

		if (result == null) {
			if (isAllowAny) {
				result = ConnectorProfile.ANY;
			}
		}

		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("DataConnectorCreaterDialog.16")); //$NON-NLS-1$
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void okPressed() {
		dialogResult = connectorProfile;
		super.okPressed();
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * メッセージを設定する。 メッセージとしてはエラーメッセージを想定しており、
	 * エラーメッセージが存在するか空文字かどうかにより、OKボタンのEnableの制御も行うように、オーバーライドした。
	 */
	public void setMessage(String newMessage, int newType) {
		super.setMessage(newMessage, newType);

		boolean isOkEnable = false;
		if (newMessage.length() == 0) {
			isOkEnable = true;
		}

		getButton(IDialogConstants.OK_ID).setEnabled(isOkEnable);
	}

	/**
	 * 設定に変更があった場合に呼び出されることを想定したメソッド。
	 * SubscriptionTypeコンボとPushInterbalのEnableを管理する。
	 * <p>
	 * 注意：設定値の変更がある場合には、必ずこのメソッドを呼び出すこと<br>
	 * 現在は、表示側で設定を変更した後に、このメソッドを必ず呼び出すように実装しているが、
	 * 項目数が増えるようならば、モデルの変更通知機能を使用して実装する方が良い。
	 */
	public void notifyModified() {
		if (getButton(IDialogConstants.OK_ID) != null) {
			setMessage("", IMessageProvider.NONE); //$NON-NLS-1$
		}

		if (connectorProfile.isPushIntervalAvailable()) {
			boolean isDouble = false;
			try {
				double value = Double.parseDouble(pushRateText.getText());
				if (value > 0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}

			String message = ""; //$NON-NLS-1$
			if (isDouble == false) {
				message = Messages.getString("DataConnectorCreaterDialog.19"); //$NON-NLS-1$
			}

			if (message.length() != 0) {
				setMessage(message, IMessageProvider.ERROR);
			}
		}

		subscriptionTypeCombo.setEnabled(connectorProfile
				.isSubscriptionTypeAvailable());

		pushRateText.setEnabled(connectorProfile.isPushIntervalAvailable());
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
