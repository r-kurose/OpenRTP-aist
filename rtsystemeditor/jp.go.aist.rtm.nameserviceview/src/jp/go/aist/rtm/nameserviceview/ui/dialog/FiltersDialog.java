package jp.go.aist.rtm.nameserviceview.ui.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.nl.Messages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * ネームサービスビューに適用するフィルタを設定するダイアログ
 *
 */
public class FiltersDialog extends Dialog {

	private CheckboxTableViewer tableViewer = null;
	private Composite composite = null;
	
	private static final String SEPARATOR= ","; //$NON-NLS-1$
	
	private boolean patternEnable = false;
	
	private Button radioForward;
	private Button radioContain;
	
	private Text patternText;

	public static final String COMPONENT = Messages.getString("FiltersDialog.1"); //$NON-NLS-1$
	public static final String COMPOSITE_COMPONENT = Messages.getString("FiltersDialog.2"); //$NON-NLS-1$
	public static final String MANAGER = Messages.getString("FiltersDialog.10"); //$NON-NLS-1$
	public static final String OBJECT = Messages.getString("FiltersDialog.3"); //$NON-NLS-1$
	public static final String HOST_NAMING_CONTEXT = Messages.getString("FiltersDialog.4"); //$NON-NLS-1$
	public static final String MANAGER_NAMING_CONTEXT = Messages.getString("FiltersDialog.5"); //$NON-NLS-1$
	public static final String CATEGORY_NAMING_CONTEXT = Messages.getString("FiltersDialog.6"); //$NON-NLS-1$
	public static final String MODULE_NAMING_CONTEXT = Messages.getString("FiltersDialog.7"); //$NON-NLS-1$
	public static final String FOLDER = Messages.getString("FiltersDialog.8"); //$NON-NLS-1$
	public static final String ZOMBIE_OBJECT = Messages.getString("FiltersDialog.9"); //$NON-NLS-1$

	private static final String TABLEVIEWER_ITEMS_KEY = FiltersDialog.class.getName()
			+ "TABLEVIEWER"; //$NON-NLS-1$

	private static final String PATTERNTEXT_ITEMS_KEY = FiltersDialog.class.getName()
			+ "PATTERNTEXT"; //$NON-NLS-1$
	
	private static final String RADIOBUTTON_KEY = FiltersDialog.class.getName()
			+ "RADIOBUTTON"; //$NON-NLS-1$
	
	private static final String PATTERNENABLE_KEY = FiltersDialog.class.getName()
			+ "PATTERNENABLE"; //$NON-NLS-1$

	public FiltersDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("FiltersDialog.34")); //$NON-NLS-1$
	}

	protected void createButtonForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, true);
	}

	@Override
	public Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.composite = (Composite) super.createDialogArea(parent);
		this.createComposite(this.composite);
		return composite;

	}

	/**
	 * This method initializes composite
	 * 
	 */
	private void createComposite(Composite parent) {
		Composite group = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.minimumWidth = 300;
		group.setLayoutData(gd);

		createObjectSelectionPart(group);

		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 300;
		Label lblSelection = new Label(parent, SWT.NONE);
		lblSelection.setLayoutData(gd);
		lblSelection.setText(Messages.getString("FiltersDialog.15")); //$NON-NLS-1$
		tableViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER
				| SWT.CHECK);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}
			public String getColumnText(Object element, int columnIndex) {
				return element.toString();
			}
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}
			public void addListener(ILabelProviderListener listener) {
			}
			public void dispose() {
			}
			public void removeListener(ILabelProviderListener listener) {
			}
		});
		gd = new GridData();
		gd.heightHint = 150;
		gd.minimumWidth = 300;
		gd.grabExcessHorizontalSpace = true;
		Table table = tableViewer.getTable();
		table.setLayoutData(gd);
		TableColumn clmSelection = new TableColumn(table, SWT.NONE);
		clmSelection.setWidth(300);
		table.setHeaderVisible(false);
		table.setLinesVisible(false);
		tableViewer.setInput(getNameList());
		tableViewer.setCheckedElements(loadDefaultFilters().toArray());

		Label lblDesc = new Label(parent, SWT.NONE);
		lblDesc.setText(Messages.getString("FiltersDialog.16")); //$NON-NLS-1$
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		lblDesc.setLayoutData(gd);
		final Text txtObjectName = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP
				| SWT.V_SCROLL);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.heightHint = 50;
		txtObjectName.setBackground(parent.getBackground());
		txtObjectName.setLayoutData(gd);
		txtObjectName.setEditable(false);

		createAllButtonPart(parent);

		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						if (event.getSelection() instanceof StructuredSelection) {
							txtObjectName
									.setText(getFilterDescription(((StructuredSelection) event
											.getSelection()).getFirstElement()));
						}
					}
				});
	}

	private void createAllButtonPart(Composite parent) {
		GridData gd;
		Composite buttonCompsite = new Composite(parent, SWT.BOTTOM);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		buttonCompsite.setLayout(gridLayout);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		buttonCompsite.setLayoutData(gd);

		Button selectAllButton = new Button(buttonCompsite, SWT.NONE);
		selectAllButton.setText(Messages.getString("FiltersDialog.35")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = SWT.LEFT;
		selectAllButton.setLayoutData(gd);
		selectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setAllChecked(true);
			}
		});

		Button deselectAllButton = new Button(buttonCompsite, SWT.NONE);
		deselectAllButton.setText(Messages.getString("FiltersDialog.36")); //$NON-NLS-1$
		gd = new GridData();
		gd.horizontalAlignment = SWT.LEFT;
		gd.widthHint = 100;
		deselectAllButton.setLayoutData(gd);
		deselectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setAllChecked(false);
			}
		});
	}

	private void createObjectSelectionPart(Composite group) {
		final Button chkObjectName = new Button(group, SWT.CHECK);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		chkObjectName.setLayoutData(gd);
		chkObjectName.setText(Messages.getString("FiltersDialog.19"));  //$NON-NLS-1$

		patternText = new Text(group, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		gd.horizontalSpan = 2;
		patternText.setLayoutData(gd);
		patternText.setText(loadDefaultPattern());

		radioForward = new Button(group, SWT.RADIO);
		radioForward.setText(Messages.getString("FiltersDialog.20")); //$NON-NLS-1$

		radioContain = new Button(group, SWT.RADIO);
		radioContain.setText(Messages.getString("FiltersDialog.21")); //$NON-NLS-1$
		this.patternEnable = loadPatternEnable();
		chkObjectName.setSelection(patternEnable);
		patternText.setEnabled(patternEnable);
		radioForward.setEnabled(patternEnable);
		radioContain.setEnabled(patternEnable);
		radioContain.setSelection(!isStartsWith());
		radioForward.setSelection(!radioContain.getSelection());
		chkObjectName.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				patternEnable = chkObjectName.getSelection();
				patternText.setEnabled(patternEnable);
				radioForward.setEnabled(patternEnable);
				radioContain.setEnabled(patternEnable);
				if (patternEnable)
					patternText.setFocus();
			}
		});
	}

	private static String loadRadioSelection() {
		String name = NameServiceViewPlugin.getDefault().getPreferenceStore()
			.getString(RADIOBUTTON_KEY);
		return name;
	}

	@Override
	protected void okPressed() {
		saveDefaultFilters(Arrays.asList(tableViewer.getCheckedElements()));
		super.okPressed();
	}

	private String getFilterDescription(Object object) {
		String result = ""; //$NON-NLS-1$
		if (COMPONENT.equals(object)) {
			result = Messages.getString("FiltersDialog.23"); //$NON-NLS-1$
		} else if (COMPOSITE_COMPONENT.equals(object)) {
			result = Messages.getString("FiltersDialog.24"); //$NON-NLS-1$
		} else if (MANAGER.equals(object)) {
			result = Messages.getString("FiltersDialog.32"); //$NON-NLS-1$
		} else if (OBJECT.equals(object)) {
			result = Messages.getString("FiltersDialog.25"); //$NON-NLS-1$
		} else if (HOST_NAMING_CONTEXT.equals(object)) {
			result = Messages.getString("FiltersDialog.26"); //$NON-NLS-1$
		} else if (MANAGER_NAMING_CONTEXT.equals(object)) {
			result = Messages.getString("FiltersDialog.27"); //$NON-NLS-1$
		} else if (CATEGORY_NAMING_CONTEXT.equals(object)) {
			result = Messages.getString("FiltersDialog.28"); //$NON-NLS-1$
		} else if (MODULE_NAMING_CONTEXT.equals(object)) {
			result = Messages.getString("FiltersDialog.29"); //$NON-NLS-1$
		} else if (FOLDER.equals(object)) {
			result = Messages.getString("FiltersDialog.30"); //$NON-NLS-1$
		} else if (ZOMBIE_OBJECT.equals(object)) {
			result = Messages.getString("FiltersDialog.31"); //$NON-NLS-1$
		}
		return result;
	}

	private void saveDefaultFilters(List<?> values) {
		StringBuffer newString = new StringBuffer();
		for (Object value : values) {
			if (!newString.toString().equals("")) { //$NON-NLS-1$
				newString.append(SEPARATOR);
			}
			newString.append(value);
		}
		NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
				TABLEVIEWER_ITEMS_KEY, newString.toString());
		NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
				PATTERNTEXT_ITEMS_KEY, patternText.getText());
		if (patternEnable) {
			NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
					PATTERNENABLE_KEY, true);
		} else {
			NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
					PATTERNENABLE_KEY, false);
		}
		
		if (radioForward.getSelection()) {
			NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
					RADIOBUTTON_KEY, radioForward.getText());
		} else {
			NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
					RADIOBUTTON_KEY, radioContain.getText());
		}
	}
	
	public static List<String> loadDefaultFilters() {
		return loadPreferenceStore(TABLEVIEWER_ITEMS_KEY);
	}
	
	public static boolean loadPatternEnable() {
		String enable = NameServiceViewPlugin.getDefault().getPreferenceStore()
			.getString(PATTERNENABLE_KEY);
		return Boolean.parseBoolean(enable);
	}
	
	private static List<String> loadPreferenceStore(String key) {
		String defaultString = NameServiceViewPlugin.getDefault().getPreferenceStore()
				.getString(key);
		StringTokenizer tokenize = new StringTokenizer(defaultString, SEPARATOR);
		List<String> values = new ArrayList<String>();
		while (tokenize.hasMoreTokens()) {
			values.add(tokenize.nextToken());
		}
		return values;
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

	private List<String> getNameList(){
		List<String>list = new ArrayList<String>();
		list.add(COMPONENT);
		list.add(COMPOSITE_COMPONENT);
		list.add(MANAGER);
		list.add(OBJECT);
		list.add(HOST_NAMING_CONTEXT);
		list.add(MANAGER_NAMING_CONTEXT);
		list.add(CATEGORY_NAMING_CONTEXT);
		list.add(MODULE_NAMING_CONTEXT);
		list.add(FOLDER);
		list.add(ZOMBIE_OBJECT);
		return list;
	}
	
	public static String loadDefaultPattern() {
		String pattern = NameServiceViewPlugin.getDefault().getPreferenceStore()
			.getString(PATTERNTEXT_ITEMS_KEY);
		return pattern;
	}

	public static boolean isStartsWith() {
		String name = loadRadioSelection();
		
		return Messages.getString("FiltersDialog.33").equals(name); //$NON-NLS-1$
	}
}
