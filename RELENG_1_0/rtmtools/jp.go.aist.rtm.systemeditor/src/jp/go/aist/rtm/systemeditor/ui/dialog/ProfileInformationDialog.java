package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * RTSプロファイルの保存時に表示するダイアログ
 *
 */
public class ProfileInformationDialog extends Dialog {

	private Text txtVendor;
	private Text txtSystemName;
	private Text txtVersion;
	private Text txtUpdateLog;
	private Text txtPath;
	//
	private String inputVendor;
	private String inputSystemName;
	private String inputVersion;
//	private String inputUpdateLog;
	private String inputPath;
	//
	private boolean isOverWrite = false;
	
//	 必須コンポーネント設定の対象 2008.12.11
	private CheckboxTableViewer viewer;
	private List<Component> components;

	/**
	 * コンストラクタ
	 * 
	 * @param shell
	 */
	public ProfileInformationDialog(Shell shell) {
		super(shell);
		this.setShellStyle(this.getShellStyle() | SWT.RESIZE  );
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout(3, false);

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		KeyListener listener = new KeyListener() {
			public void keyReleased(KeyEvent e) {
				doValidate();
			}
			public void keyPressed(KeyEvent e) {
			}
		};

		txtVendor = createLabelAndText(mainComposite, Messages.getString("ProfileInformationDialog.0")); //$NON-NLS-1$
		if(inputVendor!=null) txtVendor.setText(inputVendor);
		txtVendor.addKeyListener(listener);
		txtSystemName = createLabelAndText(mainComposite, Messages.getString("ProfileInformationDialog.1")); //$NON-NLS-1$
		if(inputSystemName!=null) txtSystemName.setText(inputSystemName);
		txtSystemName.addKeyListener(listener);
		txtVersion = createLabelAndText(mainComposite, Messages.getString("ProfileInformationDialog.2")); //$NON-NLS-1$
		if(inputVersion!=null) txtVersion.setText(inputVersion);
		txtVersion.addKeyListener(listener);
		//
		GridData gd;
		Label label = new Label(mainComposite, SWT.NULL);
		label.setText(Messages.getString("ProfileInformationDialog.3")); //$NON-NLS-1$
		final Text txtPathLocal = new Text(mainComposite, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		txtPath = txtPathLocal;
		if(inputPath!=null) txtPath.setText(inputPath);
		txtPath.addKeyListener(listener);
		txtPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				doValidate();
			}
		});
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		txtPath.setLayoutData(gd);
		if( isOverWrite ) txtPath.setEnabled(false);
		//
		Button checkButton = new Button(mainComposite, SWT.PUSH);
		checkButton.setText(Messages.getString("ProfileInformationDialog.4") ); //$NON-NLS-1$
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalAlignment = SWT.FILL;
		checkButton.setLayoutData(gd);
		checkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell());
				dialog.setFilterExtensions(new String[] { "*.xml" }); //$NON-NLS-1$
				if (txtPath.getText().length() > 0)
					dialog.setFileName(txtPath.getText());
				String newPath = dialog.open();
				if (newPath != null) {
					if( !newPath.endsWith(".xml") ) newPath += ".xml"; //$NON-NLS-1$ //$NON-NLS-2$
					txtPath.setText(newPath);
				}
			}
		});
		if( isOverWrite ) checkButton.setEnabled(false);
		//
		label = new Label(mainComposite, SWT.LEFT);
		label.setText(Messages.getString("ProfileInformationDialog.8")); //$NON-NLS-1$
		txtUpdateLog = new Text(mainComposite, SWT.MULTI | SWT.BORDER | SWT.LEFT);
		txtUpdateLog.addKeyListener(listener);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.heightHint = 70;
		txtUpdateLog.setLayoutData(gd);
		//
		// 必須コンポーネント選択エリアを追加  2008.12.11
		createRequiedComponentsArea(mainComposite);
		
		return mainComposite;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("ProfileInformationDialog.9")); //$NON-NLS-1$
		int x = 550;
		int y = 400;

		shell.setBounds(shell.getDisplay().getBounds().width / 2 - x / 2, shell
				.getDisplay().getBounds().height
				/ 2 - y / 2, x, y);
	}

	@Override
	protected void okPressed() {
		inputVendor = txtVendor.getText();
		inputSystemName = txtSystemName.getText();
		inputVersion = txtVersion.getText();
		inputPath = txtPath.getText();
//		inputUpdateLog = txtUpdateLog.getText();
		// OKボタンが押されたときに必須コンポーネントであるかの設定を更新する
		syncRequiredComponents();
		super.okPressed();
	}
	
    public String getInputPath() {
		return inputPath;
	}

	public String getInputSystemName() {
		return inputSystemName;
	}

//	public String getInputUpdateLog() {
//		return inputUpdateLog;
//	}

	/**
	 * @return 入力されたベンダー
	 */
	public String getInputVendor() {
		return inputVendor;
	}

	public String getInputVersion() {
		return inputVersion;
	}

	/**
	 * システムIDからベンダー、バージョン、システム名をセットする
	 * @param id	システムID
	 */
	public void setSystemId(String id) {
		if (id == null) return;
		String[] strSplit = id.split(":"); //$NON-NLS-1$
		if(strSplit.length==3) {
			this.inputVersion = strSplit[strSplit.length-1];
			String strId = strSplit[strSplit.length-2];
			int index = strId.lastIndexOf("."); //$NON-NLS-1$
			this.inputSystemName = strId.substring(index+1);
			this.inputVendor = strId.substring(0,index);
		} else if(strSplit.length==4) {
			this.inputVersion = strSplit[strSplit.length-1];
			this.inputSystemName = strSplit[2];
			this.inputVendor = strSplit[1];
		}
	}

	/**
	 * @param owflag	上書き保存であれば、true:新規保存であれば、false
	 */
	public void setOverWrite(boolean owflag) {
		this.isOverWrite = owflag;
	}

	private void doValidate() {
    	if( txtVendor.getText() != null && !txtVendor.getText().equals("") && //$NON-NLS-1$
			txtSystemName.getText() != null && !txtSystemName.getText().equals("") &&    			 //$NON-NLS-1$
			txtVersion.getText() != null && !txtVersion.getText().equals("") &&    			 //$NON-NLS-1$
			( isOverWrite || (!isOverWrite && txtPath.getText() != null && !txtPath.getText().equals(""))) ) { //$NON-NLS-1$
    			this.getButton(IDialogConstants.OK_ID).setEnabled(true);
    	} else {
			this.getButton(IDialogConstants.OK_ID).setEnabled(false);
    	}
    }
	
	private Text createLabelAndText(Composite baseComposite, String labelString) {
		GridData gd;
		Label label = new Label(baseComposite, SWT.NULL);
		label.setText(labelString);
		Text targetText = new Text(baseComposite, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		targetText.setLayoutData(gd);
		
		return targetText;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control result = super.createButtonBar(parent);
		doValidate();
		return result;
	}

	// 必須コンポーネント設定の対象 2008.12.11
	public void setComponets(List<Component> components) {
		this.components = components;
	}

	// 必須コンポーネント選択エリア 2008.12.11
	private void createRequiedComponentsArea(Composite mainComposite) {
		Label pathLabel = new Label(mainComposite, SWT.LEFT);
		pathLabel.setText(Messages.getString("ProfileInformationDialog.16")); //$NON-NLS-1$
		viewer = CheckboxTableViewer.newCheckList(mainComposite, SWT.BORDER);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ITableLabelProvider(){
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}
			public String getColumnText(Object element, int columnIndex) {
				Component item = (Component)element;
				return item.getInstanceNameL();
			}
			public void addListener(ILabelProviderListener listener) {
			}
			public void dispose() {
			}
			public boolean isLabelProperty(Object element, String property) {
				return true;
			}
			public void removeListener(ILabelProviderListener listener) {
			}});
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.minimumWidth = 320;
		viewer.getTable().setLayoutData(gd);
		setViewerInput();
		
		Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(1, false);
		buttonComposite.setLayout(gridLayout);
		buttonComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Button selectButton = new Button(buttonComposite, SWT.PUSH);
		selectButton.setText(Messages.getString("ProfileInformationDialog.17") ); //$NON-NLS-1$
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalAlignment = SWT.FILL;
		selectButton.setLayoutData(gd);
		selectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.setAllChecked(true);
			}
		});

		Button deselectButton = new Button(buttonComposite, SWT.PUSH);
		deselectButton.setText(Messages.getString("ProfileInformationDialog.18") ); //$NON-NLS-1$
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalAlignment = SWT.FILL;
		deselectButton.setLayoutData(gd);
		deselectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.setAllChecked(false);
			}
		});

	}

	private void setViewerInput() {
		viewer.setInput(components);
		TableItem[] children = viewer.getTable().getItems();
        for (int i = 0; i < children.length; i++) {
            TableItem item = children[i];
            Component component = (Component) item.getData();
            item.setChecked(component.isRequired());
        } 
	}

	private void syncRequiredComponents() {
		TableItem[] children = viewer.getTable().getItems();
        for (int i = 0; i < children.length; i++) {
            TableItem item = children[i];
            Component component = (Component) item.getData();
            component.setRequired(item.getChecked());
        } 
		
	}

}
