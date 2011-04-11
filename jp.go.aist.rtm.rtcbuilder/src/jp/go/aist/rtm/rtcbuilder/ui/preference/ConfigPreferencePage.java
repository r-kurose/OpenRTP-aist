package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigParameterParam;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class ConfigPreferencePage extends AbstractPreferencePage implements 
		IWorkbenchPreferencePage {
	private TableViewer configSetTable;
	
	private static final int CONFIGPROFILE_PROPERTY_CONFIGURATION = 0;
	private static final int CONFIGPROFILE_PROPERTY_DEFAULT = 1;
	private List<ConfigParameterParam> paramArray = new ArrayList<ConfigParameterParam>();


	public ConfigPreferencePage() {
	}

	public ConfigPreferencePage(String title) {
		super(title);
	}

	public ConfigPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridData gd;
		
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(1,true));
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = GridData.BEGINNING;
		composite.setLayoutData(gd);
		
		Group group = createGroup(composite, "");
		
		configSetTable = new TableViewer(group,SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		configSetTable = createTableViewer(group, configSetTable);
		
		return composite;
	}

	private TableViewer createTableViewer(Group group, final TableViewer targetViewer) {
		GridData gd;
		
		GridLayout layout = new GridLayout(2,false);
		layout.horizontalSpacing = 2;
	    layout.marginWidth = 3;
	    layout.marginHeight = 3;
	    group.setLayout(layout);

		final Table table = targetViewer.getTable();
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.heightHint = 300;
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		createConfigProfileColumn(targetViewer, IPreferenceMessageConstants.CONFIG_CLMN_CONFIGURATION, 200, CONFIGPROFILE_PROPERTY_CONFIGURATION);
		createConfigProfileColumn(targetViewer, IPreferenceMessageConstants.CONFIG_CLMN_DEFAUT_VALUE, 200, CONFIGPROFILE_PROPERTY_DEFAULT);
			
		targetViewer.setContentProvider(new ArrayContentProvider());
        targetViewer.setLabelProvider(new ConfigParamLabelProvider());
		
		// ボタン生成
		gd = new GridData();
		Button addButton = new Button(group, SWT.PUSH);
		addButton.setText(IPreferenceMessageConstants.CONFIG_BTN_ADD);
		gd.widthHint = 70;
		addButton.setLayoutData(gd);
		
		addButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				((java.util.List) targetViewer.getInput()).add(new ConfigParameterParam("configuration",""));
				int selectionIndex = targetViewer.getTable().getItemCount();
				targetViewer.refresh();
				targetViewer.getTable().setSelection(selectionIndex);
			}
		});
		gd = new GridData();
		Button delButton = new Button(group, SWT.PUSH);
		delButton.setText(IPreferenceMessageConstants.CONFIG_BTN_DELETE);
		gd.widthHint = 70;
		delButton.setLayoutData(gd);
		delButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = targetViewer.getTable().getSelectionIndex();
				if (selectionIndex >= 0
						&& ((java.util.List) targetViewer.getInput()).size() >= selectionIndex + 1) {
					((java.util.List) targetViewer.getInput()).remove(selectionIndex);
					
					targetViewer.refresh();
				}
			}
		});
		
		paramArray = new ArrayList<ConfigParameterParam>();
		paramArray = ConfigPreferenceManager.getConfigNameValue();
		configSetTable.setInput(paramArray);
		ConfigPreferenceManager.getInstance().setConfigValue(paramArray);

		return targetViewer;
	}


	private void createConfigProfileColumn(TableViewer tv, String title, int width, int no){
		TableViewerColumn col = super.createColumn(tv, title, width);
		col.setEditingSupport(new ConfigProfileCellModifier(tv, no));
	}
	
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void performDefaults() {
		ArrayList<ConfigParameterParam> tempArray = new ArrayList<ConfigParameterParam>();
		tempArray = ConfigPreferenceManager.getDefaultConfigValue();
		configSetTable.setInput(tempArray);
		paramArray = tempArray;

		super.performDefaults();
	}
	
	private class ConfigParamLabelProvider extends LabelProvider implements	ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof ConfigParameterParam == false) {
				return null;
			}
			ConfigParameterParam configProfileParam = (ConfigParameterParam) element;
		
			String result = null;
			if (columnIndex == 0) {
				result = configProfileParam.getConfigName();
			} else if (columnIndex == 1) {
				result = configProfileParam.getDefaultVal();
			}
		
			return result;
		}
	}

	private class ConfigProfileCellModifier extends EditingSupport {
		
		private CellEditor editor;  
		private int column;

		public ConfigProfileCellModifier(ColumnViewer viewer, int column) { 
			super(viewer);
			editor = new TextCellEditor(((TableViewer) viewer).getTable());  
			this.column = column;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}
		
		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override  
		protected Object getValue(Object element) {
			if (element instanceof ConfigParameterParam == false) return null;
			
			ConfigParameterParam configProfileParam = (ConfigParameterParam) element;

			switch (this.column) {  
			case CONFIGPROFILE_PROPERTY_CONFIGURATION:
				return configProfileParam.getConfigName();  
			case CONFIGPROFILE_PROPERTY_DEFAULT:  
				return configProfileParam.getDefaultVal();  
			default:
				break;
			}
			return null;
		}

		@Override
		protected void setValue(Object element, Object value) {
			if (element instanceof ConfigParameterParam == false) return;
			ConfigParameterParam configProfileParam = (ConfigParameterParam)element;
		
			switch (this.column) {
			case CONFIGPROFILE_PROPERTY_CONFIGURATION:
				configProfileParam.setConfigName((String) value);
				break;
			case CONFIGPROFILE_PROPERTY_DEFAULT:  
				configProfileParam.setDefaultVal((String) value);
				break;
			default:
				break;
			}

			getViewer().update(element, null);
		}
	}

	@Override
	public boolean performOk() {
		if (validate() == true ) {
			ConfigPreferenceManager.getInstance().setConfigValue(paramArray);
			return super.performOk();
		}
		return false;
	}
	
	public boolean validate() {
		
		int intRow = configSetTable.getTable().getItemCount();
		String result1 = null;
		String result2 = null;
		for (int intIdx1=0; intIdx1 <= intRow-1; intIdx1++) {
			result1 = configSetTable.getTable().getItem(intIdx1).getText();
			if (result1 == "") {
				MessageDialog.openError(getShell(),"Error","Configurationの値は必須入力です");
				configSetTable.getTable().setSelection(intIdx1);
				return false;
			}
			for (int intIdx2=intIdx1; intIdx2 <= intRow-1; intIdx2++) {
				if (intIdx1 != intIdx2) {
					result2 = configSetTable.getTable().getItem(intIdx2).getText();
					if (result1.equals(result2)) {
						MessageDialog.openError(getShell(),"Error","Configurationの値が重複しています");
						configSetTable.getTable().setSelection(intIdx2);
						return false;
					}
				}
			}
		}
		return true;
	}
}

