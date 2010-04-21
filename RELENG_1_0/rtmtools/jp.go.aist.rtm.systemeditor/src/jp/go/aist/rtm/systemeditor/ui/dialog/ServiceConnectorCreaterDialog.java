package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * サービスポート間の接続のコネクタプロファイルの選択ダイアログ
 * <P>
 * ポート名を入力する 接続しようとしているServicePort間でマッチングを行い、必要に応じて警告を表示する。
 * ここでいうマッチングは、「PortInterfaceProfile.type」が同じで、「PortInterfaceProfile.polarity」がPROVIDEDとREQUIREDで対応することをいう。
 * ・完全一致した場合 → 警告なし ・一部一致した場合 → 警告 「Port interfaces do not match completely.」
 * ・完全不一致した場合 → 警告 「No corresponding port interface.」
 * 
 */
public class ServiceConnectorCreaterDialog extends TitleAreaDialog {

	static final int EXEC_BUTTON_WIDTH = 70;

	static final String LABEL_PROPERTY_CONSUMER = Messages.getString("ServiceConnectorCreaterDialog.7");
	static final String LABEL_PROPERTY_PROVIDER = Messages.getString("ServiceConnectorCreaterDialog.8");

	static final String LABEL_BUTTON_ADD = Messages.getString("ServiceConnectorCreaterDialog.9");
	static final String LABEL_BUTTON_DELETE = Messages.getString("ServiceConnectorCreaterDialog.10");

	static final String LABEL_DETAIL = Messages.getString("ServiceConnectorCreaterDialog.11");

	static final String LABEL_UNKNOWN = Messages.getString("ServiceConnectorCreaterDialog.12");

	static final String PROPERTY_CONSUMER = "PROPERTY_CONSUMER";
	static final String PROPERTY_PROVIDER = "PROPERTY_PROVIDER";

	private Text nameText;

	Composite detailComposite;

	TableViewer interfaceTableViewer;
	Table interfaceTable;

	Button addButton;
	Button deleteButton;

	private ConnectorProfile connectorProfile;
	private ConnectorProfile dialogResult;

	private ServicePort first;

	private ServicePort second;

	List<InterfaceEntry> interfaceList;
	InterfaceEntry selectedEntry;

	Map<String, ConnectorProfile.InterfaceId> consumerMap;
	List<String> consumerLabels;

	Map<String, ConnectorProfile.InterfaceId> providerMap;
	List<String> providerLabels;

	public ServiceConnectorCreaterDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * ConnectorProfileCreaterインタフェースの実装メソッド
	 * <p>
	 * ConnectorProfileとなる候補が複数ある場合には、ダイアログを表示し、ConnectorProfileを作成する。
	 */
	public ConnectorProfile getConnectorProfile(ServicePort first,
			ServicePort second) {
		this.first = first;
		this.second = second;

		consumerMap = new HashMap<String, ConnectorProfile.InterfaceId>();
		consumerLabels = new ArrayList<String>();
		providerMap = new HashMap<String, ConnectorProfile.InterfaceId>();
		providerLabels = new ArrayList<String>();
		registInterfaceMap(first);
		registInterfaceMap(second);

		this.connectorProfile = ComponentFactory.eINSTANCE
				.createConnectorProfile();
		this.connectorProfile.setName(first.getNameL() + "_" //$NON-NLS-1$
				+ second.getNameL());

		open();

		return dialogResult;
	}

	/** Consumer/Providerのインターフェース一覧を登録 */
	void registInterfaceMap(ServicePort port) {
		if (port == null) {
			return;
		}
		String compName = LABEL_UNKNOWN;
		String portName = LABEL_UNKNOWN;
		//
		String name = port.getNameL();
		if (name != null) {
			String[] names = name.split("\\.");
			if (names.length < 2) {
				if (port.eContainer() instanceof Component) {
					compName = ((Component) port.eContainer())
							.getInstanceNameL();
				}
				portName = names[0];
			} else {
				compName = names[0];
				portName = names[1];
			}
		}
		for (PortInterfaceProfile ip : port.getInterfaces()) {
			ConnectorProfile.InterfaceId id = new ConnectorProfile.InterfaceId();
			id.rtc_name = compName;
			id.port_name = portName;
			id.setPolarityBy(ip);
			id.if_tname = ip.getTypeName();
			id.if_iname = ip.getInstanceName();

			String label = InterfaceEntry.toLabelString(id);
			if (ip.isRequiredPolarity()) {
				consumerMap.put(label, id);
				consumerLabels.add(label);
			} else {
				providerMap.put(label, id);
				providerLabels.add(label);
			}
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		gl = new GridLayout();

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.getString("ServiceConnectorCreaterDialog.1")); //$NON-NLS-1$
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		String message = Messages.getString("ServiceConnectorCreaterDialog.2"); //$NON-NLS-1$
		try {
			List<PortInterfaceProfile> interfaces1 = first.getInterfaces();
			List<PortInterfaceProfile> interfaces2 = second.getInterfaces();

			int countMatch = countMatch(interfaces1, interfaces2);
			if (countMatch > 0 && countMatch == countTotal(interfaces1, interfaces2)) {
				message = null;
			} else {
				if (countMatch == 0) {
					message = Messages.getString("ServiceConnectorCreaterDialog.3"); //$NON-NLS-1$
				} else {
					message = Messages.getString("ServiceConnectorCreaterDialog.4"); //$NON-NLS-1$
				}
			}

		} catch (Exception e) {
		}
		if (message != null) {
			setMessage(message, IMessageProvider.WARNING);
		}

		return mainComposite;
	}

	/**
	 * メインとなる表示部を作成する
	 */
	private void createConnectorProfileComposite(final Composite mainComposite) {
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


		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText(Messages.getString("ServiceConnectorCreaterDialog.5")); //$NON-NLS-1$
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gd);
		nameText.setTextLimit(255);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setName(nameText.getText());
				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		final Button detailCheck = new Button(portProfileEditComposite,
				SWT.CHECK);
		detailCheck.setText(LABEL_DETAIL);
		detailCheck.setSelection(false);
		createLabel(portProfileEditComposite, "");
		createLabel(portProfileEditComposite, "");

		detailCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selection = detailCheck.getSelection();
				if (selection && detailComposite == null) {
					createDetailComposite(mainComposite);
				}
				detailComposite.setVisible(selection);
			}
		});

		loadData();
	}

	/**
	 * 詳細設定の表示部を作成する
	 */
	Composite createDetailComposite(Composite parent) {
		GridLayout gl;
		GridData gd;

		detailComposite = new Composite(parent, SWT.NONE);
		gl = new GridLayout(2, false);
		gd = new GridData(GridData.FILL_BOTH);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setVisible(false);

		interfaceTableViewer = new TableViewer(detailComposite,
				SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		interfaceTableViewer.setContentProvider(new ArrayContentProvider());
		interfaceTableViewer.setColumnProperties(new String[] {
				PROPERTY_CONSUMER, PROPERTY_PROVIDER });
		interfaceTableViewer
				.setLabelProvider(new InterfaceEntryLabelProvider());
		interfaceTableViewer.setCellModifier(new InterfaceTableCellModifier(
				interfaceTableViewer));
		CellEditor[] editors = new CellEditor[2];
		editors[0] = new ComboBoxCellEditor(interfaceTableViewer.getTable(),
				consumerLabels.toArray(new String[0]), SWT.READ_ONLY);
		editors[1] = new ComboBoxCellEditor(interfaceTableViewer.getTable(),
				providerLabels.toArray(new String[0]), SWT.READ_ONLY);
		interfaceTableViewer.setCellEditors(editors);
		interfaceTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) event
								.getSelection();
						selectedEntry = (InterfaceEntry) selection
								.getFirstElement();
						deleteButton.setEnabled(true);
					}
				});

		interfaceTable = interfaceTableViewer.getTable();
		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.heightHint = 120;
		interfaceTable.setLayout(gl);
		interfaceTable.setLayoutData(gd);
		interfaceTable.setLinesVisible(true);
		interfaceTable.setHeaderVisible(true);

		TableColumn col = new TableColumn(interfaceTable, SWT.NONE);
		col.setText(LABEL_PROPERTY_CONSUMER);
		col.setWidth(300);

		col = new TableColumn(interfaceTable, SWT.NONE);
		col.setText(LABEL_PROPERTY_PROVIDER);
		col.setWidth(300);

		Composite buttonComposite = new Composite(detailComposite, SWT.NONE);
		gl = new GridLayout();
		gd = new GridData(GridData.FILL_BOTH);
		buttonComposite.setLayout(gl);
		buttonComposite.setLayoutData(gd);

		addButton = new Button(buttonComposite, SWT.TOP);
		addButton.setText(LABEL_BUTTON_ADD);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		addButton.setLayoutData(gd);
		addButton.setEnabled(true);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InterfaceEntry entry = newEntry();
				interfaceList.add(entry);
				interfaceTableViewer.setInput(interfaceList);
			}
		});

		deleteButton = new Button(buttonComposite, SWT.TOP);
		deleteButton.setText(LABEL_BUTTON_DELETE);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		deleteButton.setLayoutData(gd);
		deleteButton.setEnabled(false);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEntry == null) {
					return;
				}
				interfaceList.remove(selectedEntry);
				interfaceTableViewer.setInput(interfaceList);
				deleteButton.setEnabled(false);
			}
		});

		loadDetailData();

		getShell().setSize(getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));

		return detailComposite;
	}

	Label createLabel(Composite parent, String label) {
		Label l = new Label(parent, SWT.NONE);
		l.setText(label);
		return l;
	}

	/**
	 * モデル情報にアクセスし、表示に設定する
	 */
	void loadData() {
		nameText.setText(connectorProfile.getName());

		loadDetailData();
	}

	void loadDetailData() {
		if (interfaceTableViewer == null) {
			return;
		}
		interfaceTableViewer.setInput(Collections.EMPTY_LIST);
		if (interfaceList == null) {
			interfaceList = new ArrayList<InterfaceEntry>();
		}
		interfaceList.clear();
		interfaceTableViewer.setInput(interfaceList);
	}

	/** Consumer/Providerの新規エントリを作成 */
	InterfaceEntry newEntry() {
		InterfaceEntry entry = new InterfaceEntry();
		//
		String label = consumerLabels.get(0);
		ConnectorProfile.InterfaceId id = consumerMap.get(label);
		entry.consumer = id.clone();
		//
		label = providerLabels.get(0);
		id = providerMap.get(label);
		entry.provider = id.clone();
		return entry;
	}

	/** Consumer/Providerのエントリをプロファイルへ反映 */
	void applyEntry() {
		if (interfaceList == null) {
			return;
		}
		List<String> keys = new ArrayList<String>();
		// Consumer/Providerのプロパティをクリア
		for (String key : connectorProfile.getPropertyKeys()) {
			if (ConnectorProfile.InterfaceId.isValid(key)) {
				keys.add(key);
			}
		}
		for (String key : keys) {
			connectorProfile.removeProperty(key);
		}
		// 新しいConsumer/Providerを設定
		for (InterfaceEntry e : interfaceList) {
			String consumer = e.consumer.toString();
			String provider = e.provider.toString();
			connectorProfile.setProperty(consumer, provider);
		}
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("ServiceConnectorCreaterDialog.6")); //$NON-NLS-1$
	}

	@Override
	protected void okPressed() {
		applyEntry();
		dialogResult = connectorProfile;
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		connectorProfile = null;
		super.cancelPressed();
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * メッセージを設定する。
	 */
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
	}

	/**
	 * PortInterfaceProfileのマッチ数を数える
	 *    requiredだけが対象
	 * 
	 * @param interfaces1
	 * @param interfaces2
	 * @return
	 */
	private int countMatch(List<PortInterfaceProfile> interfaces1,
			List<PortInterfaceProfile> interfaces2) {

		int result = 0;
		for (PortInterfaceProfile profile : interfaces1) {
			if (profile.isRequiredPolarity()) result += hasMatch(profile, interfaces2);
		}
		for (PortInterfaceProfile profile : interfaces2) {
			if (profile.isRequiredPolarity()) result += hasMatch(profile, interfaces1);
		}

		return result;
	}

	//　requiring interface の数を返す
	private int countTotal(List<PortInterfaceProfile> interfaces1,
			List<PortInterfaceProfile> interfaces2) {
		int result = 0;
		for (PortInterfaceProfile profile : interfaces1) {
			if (profile.isRequiredPolarity()) result++;
		}
		for (PortInterfaceProfile profile : interfaces2) {
			if (profile.isRequiredPolarity()) result++;
		}
		return result;
	}

	private int hasMatch(PortInterfaceProfile profile,
			List<PortInterfaceProfile> profiles2) {
		for (PortInterfaceProfile profile2 : profiles2) {
			if (isMatch(profile, profile2)) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * PortInterfaceProfileがマッチするかどうか
	 * 
	 * @param profile
	 * @param profile2
	 * @return マッチするかどうか
	 */
	private boolean isMatch(PortInterfaceProfile profile1,
			PortInterfaceProfile profile2) {
		
		if (!profile1.getTypeName().equals(profile2.getTypeName())) return false;
		if (profile1.isProvidedPolarity()) return profile2.isRequiredPolarity();
		if (profile1.isRequiredPolarity()) return profile2.isProvidedPolarity();
		
		return false;
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

	/** インターフェース一覧のエントリ */
	public static class InterfaceEntry {
		ConnectorProfile.InterfaceId consumer;
		ConnectorProfile.InterfaceId provider;

		public String getConsumerLabel() {
			return toLabelString(consumer);
		}

		public String getProviderLabel() {
			return toLabelString(provider);
		}

		public static String toLabelString(ConnectorProfile.InterfaceId id) {
			return id.rtc_name + ":" + id.if_tname + ":" + id.if_iname;
		}
	}

	/** ポート一覧表示のLabelProvider */
	public class InterfaceEntryLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			InterfaceEntry entry = (InterfaceEntry) element;
			if (columnIndex == 0) {
				return entry.getConsumerLabel();
			} else if (columnIndex == 1) {
				return entry.getProviderLabel();
			}
			return null;
		}
	}

	/** インターフェース一覧のCellModifier */
	public class InterfaceTableCellModifier implements ICellModifier {
		private TableViewer viewer;

		public InterfaceTableCellModifier(TableViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public boolean canModify(Object element, String property) {
			return true;
		}

		@Override
		public Object getValue(Object element, String property) {
			Object result = null;
			InterfaceEntry entry = (InterfaceEntry) element;
			if (PROPERTY_CONSUMER.equals(property)) {
				String label = entry.getConsumerLabel();
				int index = consumerLabels.indexOf(label);
				result = new Integer(index);
			} else if (PROPERTY_PROVIDER.equals(property)) {
				String label = entry.getProviderLabel();
				int index = providerLabels.indexOf(label);
				result = new Integer(index);
			}
			return result;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}
			InterfaceEntry entry = (InterfaceEntry) element;
			if (PROPERTY_CONSUMER.equals(property)) {
				Integer index = (Integer) value;
				if (index >= 0 && index < consumerLabels.size()) {
					ConnectorProfile.InterfaceId id = consumerMap
							.get(consumerLabels.get(index));
					entry.consumer = id.clone();
				}
			} else if (PROPERTY_PROVIDER.equals(property)) {
				Integer index = (Integer) value;
				if (index >= 0 && index < providerLabels.size()) {
					ConnectorProfile.InterfaceId id = providerMap
							.get(providerLabels.get(index));
					entry.provider = id.clone();
				}
			}
			viewer.update(element, null);
		}
	}

}
