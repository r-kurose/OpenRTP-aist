package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.systemeditor.factory.CompositeComponentCreator;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.util.ComponentCommonUtil;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 複合コンポーネントを新規に作成するダイアログ
 * 
 */
public class NewCompositeComponentDialog extends TitleAreaDialog {

	// The Grouping composite component cannot be included in the composite
	// components other than Grouping.
	public static final String MESSAGE_CONTAIN_GROUPING_FAIL = Messages
			.getString("NewCompositeComponentDialog.msg.contain_grouping_fail");

	// The component name already exists.
	public static final String MESSAGE_ALREADY_EXIST_NAME = Messages
			.getString("NewCompositeComponentDialog.msg.already_exist_name");

	private static final String rtcExtension = ".rtc";
	private static final String xmlExtension = ".xml";

	private Combo mgrCombo;
	private Text nameText;
	private Combo typeCombo;
	private Combo pathCombo;

	private List<Component> selectedComponents;
	private List<Component> baseComponents;

	private List<String> allPorts;
	private List<String> selectedPorts;
	private List<String> requiredExportedPorts;

	private List<RTCManager> mgrList;

	CompositeComponentCreator creator;

	public NewCompositeComponentDialog(Shell parentShell,
			CompositeComponentCreator creator, List<Component> list,
			List<Component> baseList) {
		super(parentShell);
		setHelpAvailable(false);
		if (list == null || list.size() == 0) {
			return;
		}
		this.selectedComponents = list;
		this.baseComponents = baseList;

		this.allPorts = NewCompositeComponentDialogData
				.getPorts(this.selectedComponents);
		this.selectedPorts = new ArrayList<String>();
		this.requiredExportedPorts = ComponentCommonUtil
				.getRequiredExportedPorts(this.selectedComponents);

		this.creator = creator;
		this.creator.setComponents(this.selectedComponents);

		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl = new GridLayout();
		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createConnectorProfileComposite(mainComposite);
		return mainComposite;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		pathCombo.setText(pathCombo.getItem(0));
		return composite;
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

		portProfileEditComposite.setLayout(gl);
		portProfileEditComposite
				.setLayoutData(new GridData(GridData.FILL_BOTH));

		if (creator.isOnline()) {
			// オンラインの場合はコンポーネントを生成するマネージャを選択
			Label mgr = new Label(portProfileEditComposite, SWT.NONE);
			mgr.setText("Manager :"); //$NON-NLS-1$
			mgrList = NameServerManager.eInstance.getRTCManagerList();
			mgrCombo = new Combo(portProfileEditComposite, SWT.NONE
					| SWT.READ_ONLY);
			gd = new GridData(GridData.GRAB_HORIZONTAL);
			gd.minimumWidth = 180;
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			mgrCombo.setLayoutData(gd);
			for (RTCManager e : mgrList) {
				mgrCombo.add(e.getPathId());
			}
			if (mgrList.size() > 0) {
				mgrCombo.select(0);
				this.creator.setManager(mgrList.get(0));
			}
			mgrCombo.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					Combo c = (Combo) e.widget;
					creator.setManager(mgrList.get(c.getSelectionIndex()));
					notifyModified();
				}
			});
		}

		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText("Name :"); //$NON-NLS-1$
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gd);

		Label type = new Label(portProfileEditComposite, SWT.NONE);
		type.setText("Type :"); //$NON-NLS-1$
		typeCombo = new Combo(portProfileEditComposite, SWT.NONE
				| SWT.READ_ONLY);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		typeCombo.setLayoutData(gd);
		for (String t : creator.getCompositeTypes()) {
			typeCombo.add(t);
		}
		typeCombo.select(0);
		this.creator.setCompositeType(typeCombo.getText());
		typeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Combo c = (Combo) e.widget;
				creator.setCompositeType(c.getText());
				notifyModified();
			}
		});

		Label pathLabel = new Label(portProfileEditComposite, SWT.NONE);
		pathLabel.setText("Path :"); //$NON-NLS-1$
		pathCombo = new Combo(portProfileEditComposite, SWT.NONE);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pathCombo.setLayoutData(gd);
		pathCombo.setItems(NewCompositeComponentDialogData
				.getPathComboItems(selectedComponents));
		pathCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPath();
				notifyModified();
			}
		});
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				creator.setInstanceName(nameText.getText());
				setPath();
				notifyModified();
			}
		});
		// ポート選択エリアを追加 2008.11.26
		createPortArea(portProfileEditComposite);
	}

	/**
	 * ポート選択エリア
	 */
	private void createPortArea(Composite portProfileEditComposite) {
		Label pathLabel = new Label(portProfileEditComposite, SWT.NONE);
		pathLabel.setText("Port :"); //$NON-NLS-1$
		final CheckboxTableViewer viewer = CheckboxTableViewer.newCheckList(
				portProfileEditComposite, SWT.BORDER);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new PortLabelProvider());
		viewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				refreshSelectedPorts(viewer);
				requireCheck(viewer);
			}
		});
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 0;
		gd.verticalSpan = 0;
		gd.minimumWidth = 240;
		viewer.getTable().setLayoutData(gd);
		viewer.setInput(this.allPorts);
		// 必須公開ポートをあらかじめチェック
		requireCheck(viewer);

		Composite buttonComposite = new Composite(portProfileEditComposite,
				SWT.NONE);
		GridLayout gridLayout = new GridLayout(1, false);
		buttonComposite.setLayout(gridLayout);
		buttonComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button selectButton = new Button(buttonComposite, SWT.PUSH);
		selectButton.setText(Messages
				.getString("NewCompositeComponentDialog.11")); //$NON-NLS-1$
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalAlignment = SWT.FILL;
		selectButton.setLayoutData(gd);
		selectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.setAllChecked(true);
				refreshSelectedPorts(viewer);
			}

		});

		Button deselectButton = new Button(buttonComposite, SWT.PUSH);
		deselectButton.setText(Messages
				.getString("NewCompositeComponentDialog.12")); //$NON-NLS-1$
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		deselectButton.setLayoutData(gd);
		deselectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				viewer.setAllChecked(false);
				selectedPorts.clear();
				requireCheck(viewer);
			}
		});
	}

	private void requireCheck(final CheckboxTableViewer viewer) {
		for (String port : this.allPorts) {
			if (this.requiredExportedPorts.contains(port)) {
				viewer.setChecked(port, true);
			}
		}
	}

	private void refreshSelectedPorts(final CheckboxTableViewer viewer) {
		selectedPorts.clear();
		for (Object o : viewer.getCheckedElements()) {
			selectedPorts.add((String) o);
		}
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("New Composite Component"); //$NON-NLS-1$
	}

	@Override
	protected void okPressed() {
		creator.setExportedPorts(getExportedPortString());
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	/**
	 * メッセージを設定する。
	 */
	@Override
	public void setMessage(String newMessage, int newType) {
		super.setMessage(newMessage, newType);
	}

	/**
	 * 設定に変更があった場合に呼び出されることを想定したメソッド。
	 * <p>
	 * 注意：設定値の変更がある場合には、必ずこのメソッドを呼び出すこと<br>
	 * 現在は、表示側で設定を変更した後に、このメソッドを必ず呼び出すように実装しているが、
	 * 項目数が増えるようならば、モデルの変更通知機能を使用して実装する方が良い。
	 */
	public void notifyModified() {
		if (nameText.getText().equals("") || pathCombo.getText().equals("")
				|| !validateInput()) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		} else {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	private boolean validateInput() {
		if (!creator.isValid()) {
			setMessage(creator.getMessage(), IMessageProvider.ERROR);
			return false;
		}
		for (Component target : baseComponents) {
			if (nameText.getText().trim().equals(target.getInstanceNameL())) {
				setMessage(MESSAGE_ALREADY_EXIST_NAME, IMessageProvider.WARNING);
				return false;
			}
		}
		this.setMessage(""); //$NON-NLS-1$
		return true;
	}

	@Override
	protected Point getInitialSize() {
		Point computeSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT,
				true);
		computeSize.x += 20;
		return computeSize;
	}

	private void setPath() {
		String path = pathCombo.getText();
		if (creator.isOnline()) {
			creator.setPathId(path + "/" + nameText.getText() + rtcExtension);
		} else {
			creator.setPathId(path.substring(0, path.lastIndexOf("/")) + "/"
					+ nameText.getText() + xmlExtension);
		}
	}

	String getExportedPortString() {
		StringBuffer buffer = new StringBuffer();
		String comma = "";
		for (String s : this.requiredExportedPorts) {
			buffer.append(comma);
			comma = ",";
			buffer.append(s);
		}
		for (String port : this.selectedPorts) {
			if (!this.requiredExportedPorts.contains(port)) {
				buffer.append(comma);
				comma = ",";
				buffer.append(port);
			}
		}
		return buffer.toString();
	}

	public class PortLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		private ColorRegistry colorRegistry;
		private static final String COLOR_REQUIRED = "COLOR_REQUIRED";

		PortLabelProvider() {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(COLOR_REQUIRED, new RGB(192, 192, 192));
		}

		public String getColumnText(Object element, int columnIndex) {
			return element.toString();
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public Color getBackground(Object element, int columnIndex) {
			if (requiredExportedPorts.contains(element)) {
				return colorRegistry.get(COLOR_REQUIRED);
			}
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}
	}

}
