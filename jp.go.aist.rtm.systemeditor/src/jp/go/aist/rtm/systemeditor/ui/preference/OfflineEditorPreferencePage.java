package jp.go.aist.rtm.systemeditor.ui.preference;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * オフラインエディタの設定ページ
 *
 */
public class OfflineEditorPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private TableViewer interfaceTypeViewer;
	private TableViewer dataFlowTypeViewer;
	private TableViewer subscriptionTypeViewer;
	private TableViewer pushPolicyViewer;
	private TableViewer bufferFullPolicyViewer;
	private TableViewer bufferEmptyPolicyViewer;
	//
    private List<SingleLabelItem> selectedInterfaceType = new ArrayList<SingleLabelItem>();
    private List<SingleLabelItem> selectedDataFlowType = new ArrayList<SingleLabelItem>();
    private List<SingleLabelItem> selectedSubscriptionType = new ArrayList<SingleLabelItem>();
    private List<SingleLabelItem> selectedPushPolicy = new ArrayList<SingleLabelItem>();
    private List<SingleLabelItem> selectedBufferFullPolicy = new ArrayList<SingleLabelItem>();
    private List<SingleLabelItem> selectedBufferEmptyPolicy = new ArrayList<SingleLabelItem>();

	public OfflineEditorPreferencePage() {
	}

	public OfflineEditorPreferencePage(String title) {
		super(title);
	}

	public OfflineEditorPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridData gd;

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);

		//Interface Type
		Group interfaceTypeGroup = createGroup(composite, Messages.getString("OfflineEditorPreferencePage.8")); //$NON-NLS-1$
		interfaceTypeViewer = new TableViewer(interfaceTypeGroup, SWT.BORDER | SWT.FULL_SELECTION);
		interfaceTypeViewer = createTableViewer(interfaceTypeGroup, interfaceTypeViewer);
		//
        interfaceTypeViewer.setInput(selectedInterfaceType);
		convertStrings2SingleItems(SystemEditorPreferenceManager.getInstance().getInterfaceTypes(), selectedInterfaceType);
		interfaceTypeViewer.refresh();

		//DataFlow Type
		Group dataFlowTypeGroup = createGroup(composite, Messages.getString("OfflineEditorPreferencePage.9")); //$NON-NLS-1$
		dataFlowTypeViewer = new TableViewer(dataFlowTypeGroup, SWT.BORDER | SWT.FULL_SELECTION);
		dataFlowTypeViewer = createTableViewer(dataFlowTypeGroup, dataFlowTypeViewer);
		//
		dataFlowTypeViewer.setInput(selectedDataFlowType);
		convertStrings2SingleItems(SystemEditorPreferenceManager.getInstance().getDataFlowTypes(), selectedDataFlowType);
		dataFlowTypeViewer.refresh();
		
		//Subscription Type
		Group subscriptionTypeGroup = createGroup(composite, Messages.getString("OfflineEditorPreferencePage.10")); //$NON-NLS-1$
		subscriptionTypeViewer = new TableViewer(subscriptionTypeGroup, SWT.BORDER | SWT.FULL_SELECTION);
		subscriptionTypeViewer = createTableViewer(subscriptionTypeGroup, subscriptionTypeViewer);
		//
		subscriptionTypeViewer.setInput(selectedSubscriptionType);
		convertStrings2SingleItems(SystemEditorPreferenceManager.getInstance().getSubscriptionTypes(), selectedSubscriptionType);
		subscriptionTypeViewer.refresh();
		
		//Push Policy
		Group pushPolicyGroup = createGroup(composite, Messages.getString("OfflineEditorPreferencePage.11")); //$NON-NLS-1$
		pushPolicyViewer = new TableViewer(pushPolicyGroup, SWT.BORDER | SWT.FULL_SELECTION);
		pushPolicyViewer = createTableViewer(pushPolicyGroup, pushPolicyViewer);
		//
		pushPolicyViewer.setInput(selectedPushPolicy);
		convertStrings2SingleItems(SystemEditorPreferenceManager.getInstance().getPushPolicies(), selectedPushPolicy);
		pushPolicyViewer.refresh();
		
		//Buffer Full Policy
		Group bufferFullPolicyGroup = createGroup(composite, Messages.getString("OfflineEditorPreferencePage.12")); //$NON-NLS-1$
		bufferFullPolicyViewer = new TableViewer(bufferFullPolicyGroup, SWT.BORDER | SWT.FULL_SELECTION);
		bufferFullPolicyViewer = createTableViewer(bufferFullPolicyGroup, bufferFullPolicyViewer);
		//
		bufferFullPolicyViewer.setInput(selectedBufferFullPolicy);
		convertStrings2SingleItems(SystemEditorPreferenceManager.getInstance().getBufferFullPolicies(), selectedBufferFullPolicy);
		bufferFullPolicyViewer.refresh();
		
		//Buffer Empty Policy
		Group bufferEmptyPolicyGroup = createGroup(composite, Messages.getString("OfflineEditorPreferencePage.13")); //$NON-NLS-1$
		bufferEmptyPolicyViewer = new TableViewer(bufferEmptyPolicyGroup, SWT.BORDER | SWT.FULL_SELECTION);
		bufferEmptyPolicyViewer = createTableViewer(bufferEmptyPolicyGroup, bufferEmptyPolicyViewer);
		//
		bufferEmptyPolicyViewer.setInput(selectedBufferEmptyPolicy);
		convertStrings2SingleItems(SystemEditorPreferenceManager.getInstance().getBufferEmptyPolicies(), selectedBufferEmptyPolicy);
		bufferEmptyPolicyViewer.refresh();

		return composite;
	}
	
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void performDefaults() {
		setDefault();
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		List<String> strArray = new ArrayList<String>();
		convertSingleItems2Strings(selectedInterfaceType, strArray);
		SystemEditorPreferenceManager.getInstance().setInterfaceTypes(strArray);

		convertSingleItems2Strings(selectedDataFlowType, strArray);
		SystemEditorPreferenceManager.getInstance().setDataFlowTypes(strArray);

		convertSingleItems2Strings(selectedSubscriptionType, strArray);
		SystemEditorPreferenceManager.getInstance().setSubscriptionTypes(strArray);

		convertSingleItems2Strings(selectedPushPolicy, strArray);
		SystemEditorPreferenceManager.getInstance().setPushPolicies(strArray);

		convertSingleItems2Strings(selectedBufferFullPolicy, strArray);
		SystemEditorPreferenceManager.getInstance().setBufferFullPolicies(strArray);

		convertSingleItems2Strings(selectedBufferEmptyPolicy, strArray);
		SystemEditorPreferenceManager.getInstance().setBufferEmptyPolicies(strArray);

		return super.performOk();
	}
		
	private void setDefault() {
		String[] prefInterfaceType = SystemEditorPreferenceManager.defaultConnectInterfaceType;
		convertStrings2SingleItems(prefInterfaceType, selectedInterfaceType);
		interfaceTypeViewer.refresh();
		
		String[] prefDataFlowType = SystemEditorPreferenceManager.defaultConnectDataFlowType;
		convertStrings2SingleItems(prefDataFlowType, selectedDataFlowType);
		dataFlowTypeViewer.refresh();

		String[] prefSubscriptionType = SystemEditorPreferenceManager.defaultConnectSubscriptionType;
		convertStrings2SingleItems(prefSubscriptionType, selectedSubscriptionType);
		subscriptionTypeViewer.refresh();

		String[] prefPushPolicies = SystemEditorPreferenceManager.defaultConnectPushPolicy;
		convertStrings2SingleItems(prefPushPolicies, selectedPushPolicy);
		pushPolicyViewer.refresh();
		
		String[] prefBufferFullPolicies = SystemEditorPreferenceManager.defaultConnectBufferFullPolicy;
		convertStrings2SingleItems(prefBufferFullPolicies, selectedBufferFullPolicy);
		bufferFullPolicyViewer.refresh();
		
		String[] prefBufferEmptyPolicies = SystemEditorPreferenceManager.defaultConnectBufferEmptyPolicy;
		convertStrings2SingleItems(prefBufferEmptyPolicies, selectedBufferEmptyPolicy);
		bufferEmptyPolicyViewer.refresh();
	}
		
	private TableViewer createTableViewer(Composite parent, final TableViewer targetViewer) {

		Table table = targetViewer.getTable();
        table.setLinesVisible(false);
        table.setHeaderVisible(false);
        
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		gd.heightHint = 50;
		table.setLayoutData(gd);
        
        targetViewer.setContentProvider(new ArrayContentProvider());
        targetViewer.setLabelProvider(new SingleLabelProvider());
		TableColumn nameColumn = new TableColumn(targetViewer.getTable(), SWT.NONE);
		nameColumn.setText(""); //$NON-NLS-1$
		nameColumn.setWidth(150);
		targetViewer.setColumnProperties(new String[] { "newItem" }); //$NON-NLS-1$
		CellEditor[] editors = new CellEditor[] {
				new TextCellEditor(targetViewer.getTable()),
		};
		targetViewer.setCellEditors(editors);
		targetViewer.setCellModifier(new SingleLabelCellModifier(targetViewer));

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		gd = new GridData();
		composite.setLayoutData(gd);

		//////////
		Button addButton = new Button(composite, SWT.PUSH);
		addButton.setText(Messages.getString("OfflineEditorPreferencePage.5")); //$NON-NLS-1$
		addButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				((java.util.List) targetViewer.getInput()).add(new SingleLabelItem("newItem")); //$NON-NLS-1$
				targetViewer.refresh();
			}
		});
		gd = new GridData();
		addButton.setLayoutData(gd);
		/////
		Button deleteButton = new Button(composite, SWT.PUSH);
		//
		deleteButton.setText(Messages.getString("OfflineEditorPreferencePage.7")); //$NON-NLS-1$
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = targetViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((java.util.List) targetViewer.getInput()).size() >= selectionIndex + 1) {
					((java.util.List) targetViewer.getInput())
							.remove(selectionIndex);
					targetViewer.refresh();
				}
			}
		});
		gd = new GridData();
		deleteButton.setLayoutData(gd);
		
		return targetViewer;
	}

	private Group createGroup(Composite parent, String title) {
		Group targetGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		targetGroup.setLayout(gridLayout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		targetGroup.setLayoutData(gd);
		targetGroup.setText(title);
		
		return targetGroup;
	}
		
	private void convertSingleItems2Strings(List<SingleLabelItem> sources, List<String> targets) {
		targets.clear();
		for( SingleLabelItem target : sources) {
			targets.add(new String(target.getLabeltext()));
		}
	}

	private void convertStrings2SingleItems(String[] sources, List<SingleLabelItem> targets) {
		targets.clear();
		for( String source : sources) {
			targets.add(new SingleLabelItem(source));
		}
	}

	private class SingleLabelItem {
		private String labeltext = ""; //$NON-NLS-1$
	
		public SingleLabelItem(String name) {
			this.labeltext = name;
		}
		public String getLabeltext() {
			return this.labeltext;
		}
	
		public void setLabeltext(String contents) {
			this.labeltext = contents;
		}
	}

	private class SingleLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			SingleLabelItem item = (SingleLabelItem)element;
			return item.getLabeltext();
		}
	}

	private class SingleLabelCellModifier  implements ICellModifier {

		private StructuredViewer viewer;

		public SingleLabelCellModifier(StructuredViewer viewer) {
			this.viewer = viewer;
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		public Object getValue(Object element, String property) {
			if (element instanceof SingleLabelItem == false) {
				return null;
			}

			SingleLabelItem selectedItem = (SingleLabelItem) element;

			String result = selectedItem.getLabeltext();

			return result;
		}

		/**
		 * {@inheritDoc}
		 */
		public void modify(Object element, String property, Object value) {
			if (element instanceof TableItem == false) {
				return;
			}

			SingleLabelItem selectedItem = (SingleLabelItem) ((TableItem) element)
					.getData();
			
			selectedItem.setLabeltext((String) value);

			viewer.refresh();
		}
	}
}
