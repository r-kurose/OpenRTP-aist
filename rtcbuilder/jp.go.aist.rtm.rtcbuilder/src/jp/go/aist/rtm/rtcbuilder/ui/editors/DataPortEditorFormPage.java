package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import jp.go.aist.rtm.rtcbuilder.ui.preference.PortPreferenceManager;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * DataPortページ
 */
public class DataPortEditorFormPage extends AbstractEditorFormPage {

	private static final String DATAPORTPARAM_PROPERTY_NAME = "DATAPORTPARAM_PROPERTY_NAME";
	private static final String DATAPORTPARAM_PROPERTY_TYPE = "DATAPORTPARAM_PROPERTY_TYPE";
	private static final String DATAPORTPARAM_PROPERTY_VAR_NAME = "DATAPORTPARAM_PROPERTY_VAR_NAME";
	private static final String DATAPORTPARAM_PROPERTY_POSITION = "DATAPORTPARAM_PROPERTY_POSITION";
	private static final String DATAPORTPARAM_PROPERTY_CONSTRAINT = "DATAPORTPARAM_PROPERTY_CONSTRAINT";
	private static final String DATAPORTPARAM_PROPERTY_UNIT = "DATAPORTPARAM_PROPERTY_UNIT";

	private TableViewer inportTableViewer;
	private TableViewer outportTableViewer;
	//
	private Text portNameText;
	private Text descriptionText;
	private Text typeText;
	private Text numberText;
	private Text semanticsText;
	private Text unitText;
	private Text occurrenceText;
	private Text operationText;
	//
	private DataPortParam preSelection;
	private DataPortParam selectParam;
	//
	private String defaultPortName;
	private String defaultPortType;
	private String defaultPortVarName;
	private String defaultPortConstraint;
	private String defaultPortUnit;
	private String[] defaultTypeList;
	
	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public DataPortEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.DATAPORT_SECTION);
		//
		preSelection = null;
		updateDefaultValue();
	}

	private void updateDefaultValue() {
		defaultPortName = PortPreferenceManager.getInstance().getDataPort_Name();
		defaultPortType = PortPreferenceManager.getInstance().getDataPort_Type();
		defaultPortVarName = PortPreferenceManager.getInstance().getDataPort_VarName();
		defaultPortConstraint = PortPreferenceManager.getInstance().getDataPort_Constraint();
		defaultPortUnit = PortPreferenceManager.getInstance().getDataPort_Unit();
		//
		defaultTypeList = super.extractDataTypes();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm);
		FormToolkit toolkit = managedForm.getToolkit();

		Label label = toolkit.createLabel(form.getBody(), IMessageConstants.DATAPORT_SECTION);
		if( titleFont==null ) {
			titleFont = new Font(form.getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		label.setFont(titleFont);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		//
		inportTableViewer = createPortSection(toolkit, form,
				IMessageConstants.DATAPORT_INPORT_TITLE, IMessageConstants.DATAPORT_INPORT_EXPL, 0);
		createHintSection(toolkit, form);
		outportTableViewer = createPortSection(toolkit, form,
				IMessageConstants.DATAPORT_OUTPORT_TITLE, IMessageConstants.DATAPORT_OUTPORT_EXPL, 1);

		createDocumentSection(toolkit, form);
		//
		// 言語・環境ページより先にこのページが表示された場合、ここで言語を判断する
		editor.setEnabledInfoByLangFromRtcParam();

		load();
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 3);
		//
		createHintLabel(IMessageConstants.DATAPORT_HINT_DATAPORT_TITLE, IMessageConstants.DATAPORT_HINT_DATAPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_INPORT_TITLE, IMessageConstants.DATAPORT_HINT_INPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_OUTPORT_TITLE, IMessageConstants.DATAPORT_HINT_OUTPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_PORTNAME_TITLE, IMessageConstants.DATAPORT_HINT_PORTNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_DATATYPE_TITLE, IMessageConstants.DATAPORT_HINT_DATATYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_VARNAME_TITLE, IMessageConstants.DATAPORT_HINT_VARNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_POSITION_TITLE, IMessageConstants.DATAPORT_HINT_POSITION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_CONSTRAINT_TITLE, IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_UNIT_TITLE, IMessageConstants.DATAPORT_HINT_UNIT_DESC, toolkit, composite);
		//
		createHintLabel(IMessageConstants.DATAPORT_HINT_DOCUMENT_TITLE, IMessageConstants.DATAPORT_HINT_DOCUMENT_DESC, toolkit, composite);
	}
	
	private void createDocumentSection(FormToolkit toolkit, ScrolledForm form) {

		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				"Documentation", IMessageConstants.DATAPORT_DOCUMENT_EXPL, 2);
		//
		portNameText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_PORTNAME, SWT.BORDER);
		portNameText.setEditable(false);
		portNameText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		descriptionText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		descriptionText.setLayoutData(gridData);
		typeText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_PORTTYPE);
		numberText = createLabelAndText(toolkit, composite,	
				IMessageConstants.DATAPORT_LBL_DATANUM);
		semanticsText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_SEMANTICS, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		semanticsText.setLayoutData(gridData);
		unitText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_UNIT);
		occurrenceText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_OCCUR, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		occurrenceText.setLayoutData(gridData);
		operationText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_OPERAT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		operationText.setLayoutData(gridData);
	}

	private TableViewer createPortSection(FormToolkit toolkit, ScrolledForm form,
			String sectionLabel, String sectionExpl, final int initSel) {

		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				sectionLabel, sectionExpl, 2);
		//
		final TableViewer portParamTableViewer = new TableViewer(toolkit.createTable(composite,
						SWT.SINGLE | SWT.FULL_SELECTION));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		portParamTableViewer.getTable().setLayoutData(gd);

		portParamTableViewer.getTable().setHeaderVisible(true);
		portParamTableViewer.getTable().setLinesVisible(true);

		portParamTableViewer.setContentProvider(new ArrayContentProvider());

		portParamTableViewer.setLabelProvider(new DataPortParamLabelProvider());

		TableColumn nameColumn = new TableColumn(portParamTableViewer.getTable(), SWT.NONE);
		nameColumn.setText(IMessageConstants.DATAPORT_TBLLBL_PORTNAME);
		nameColumn.setWidth(65);
		TableColumn typeColumn = new TableColumn(portParamTableViewer.getTable(), SWT.NONE);
		typeColumn.setText(IMessageConstants.DATAPORT_TBLLBL_DATATYPE);
		typeColumn.setWidth(65);
		TableColumn varnameColumn = new TableColumn(portParamTableViewer.getTable(), SWT.NONE);
		varnameColumn.setText(IMessageConstants.DATAPORT_TBLLBL_VARNAME);
		varnameColumn.setWidth(65);
		TableColumn posColumn = new TableColumn(portParamTableViewer.getTable(), SWT.NONE);
		posColumn.setText(IMessageConstants.DATAPORT_TBLLBL_POSITION);
		posColumn.setWidth(65);
		TableColumn constColumn = new TableColumn(portParamTableViewer.getTable(), SWT.NONE);
		constColumn.setText(IMessageConstants.DATAPORT_TBLLBL_CONSTRAINT);
		constColumn.setWidth(65);
		TableColumn unitColumn = new TableColumn(portParamTableViewer.getTable(), SWT.NONE);
		unitColumn.setText(IMessageConstants.DATAPORT_TBLLBL_UNIT);
		unitColumn.setWidth(65);

		portParamTableViewer.setColumnProperties(new String[] {
				DATAPORTPARAM_PROPERTY_NAME, DATAPORTPARAM_PROPERTY_TYPE, 
				DATAPORTPARAM_PROPERTY_VAR_NAME, DATAPORTPARAM_PROPERTY_POSITION,
				DATAPORTPARAM_PROPERTY_CONSTRAINT, DATAPORTPARAM_PROPERTY_UNIT});

		CellEditor[] editors = new CellEditor[] {
				new TextCellEditor(portParamTableViewer.getTable()),
				new ComboBoxCellEditor(portParamTableViewer.getTable(), defaultTypeList, SWT.DROP_DOWN | SWT.READ_ONLY),
				new TextCellEditor(portParamTableViewer.getTable()),
				new ComboBoxCellEditor(portParamTableViewer.getTable(), DataPortParam.COMBO_ITEM, SWT.DROP_DOWN | SWT.READ_ONLY),
				new TextCellEditor(portParamTableViewer.getTable()),
				new TextCellEditor(portParamTableViewer.getTable())
			};

		portParamTableViewer.setCellEditors(editors);
		portParamTableViewer.setCellModifier(new DataPortParamCellModifier(	portParamTableViewer));

		Composite buttonComposite = toolkit.createComposite(composite, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginWidth = 1;
		buttonComposite.setLayout(gl);
		gd = new GridData();
		gd.verticalAlignment = SWT.BEGINNING;
		gd.widthHint = 70;
		buttonComposite.setLayoutData(gd);

		Button addButton = toolkit.createButton(buttonComposite, "Add", SWT.PUSH);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateDefaultValue();
				DataPortParam selectParam = new DataPortParam(defaultPortName, defaultPortType, defaultPortVarName, defaultPortConstraint, defaultPortUnit, initSel);
				((List) portParamTableViewer.getInput()).add(selectParam);
				portParamTableViewer.refresh();
				update();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		addButton.setLayoutData(gd);
		//
		Button deleteButton = toolkit.createButton(buttonComposite, "Delete", SWT.PUSH);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = portParamTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) portParamTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) portParamTableViewer.getInput())
							.remove(selectionIndex);
					portParamTableViewer.refresh();
					preSelection = null;
					clearText();
					update();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		//
		portParamTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				setDocumentContents();
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				selectParam = (DataPortParam)selection.getFirstElement();
				if( selectParam != null ) {
					StringBuffer portName = new StringBuffer(selectParam.getName());
					if(event.getSource().equals(inportTableViewer)) {
						portName.append(" (InPort)");
					} else {
						portName.append(" (OutPort)");
					}
					portNameText.setText(portName.toString());
					descriptionText.setText(getDisplayDocText(selectParam.getDocDescription()));
					typeText.setText(getDisplayDocText(selectParam.getDocType()));
					numberText.setText(getDisplayDocText(selectParam.getDocNum()));
					semanticsText.setText(getDisplayDocText(selectParam.getDocSemantics()));
					unitText.setText(getDisplayDocText(selectParam.getDocUnit()));
					occurrenceText.setText(getDisplayDocText(selectParam.getDocOccurrence()));
					operationText.setText(getDisplayDocText(selectParam.getDocOperation()));
					preSelection = selectParam;
				}
			}
		});

		return portParamTableViewer;
	}

	public void update() {
		if( selectParam != null ) {
			selectParam.setDocDescription(getDocText(descriptionText.getText()));
			selectParam.setDocType(getDocText(typeText.getText()));
			selectParam.setDocNum(getDocText(numberText.getText()));
			selectParam.setDocSemantics(getDocText(semanticsText.getText()));
			selectParam.setDocUnit(getDocText(unitText.getText()));
			selectParam.setDocOccurrence(getDocText(occurrenceText.getText()));
			selectParam.setDocOperation(getDocText(operationText.getText()));
		}
		//
		editor.updateEMFDataInPorts(editor.getRtcParam().getInports());
		editor.updateEMFDataOutPorts(editor.getRtcParam().getOutports());
		editor.updateDirty();
	}

	public void updateForOutput() {
		update();
		setDocumentContents();
	}

	private void setDocumentContents() {
		if( preSelection != null ) {
			preSelection.setDocDescription(getDocText(descriptionText.getText()));
			preSelection.setDocType(getDocText(typeText.getText()));
			preSelection.setDocNum(getDocText(numberText.getText()));
			preSelection.setDocSemantics(getDocText(semanticsText.getText()));
			preSelection.setDocUnit(getDocText(unitText.getText()));
			preSelection.setDocOccurrence(getDocText(occurrenceText.getText()));
			preSelection.setDocOperation(getDocText(operationText.getText()));
		}
	}
	
	private void clearText() {
		portNameText.setText("");
		descriptionText.setText("");
		typeText.setText("");
		numberText.setText("");
		semanticsText.setText("");
		unitText.setText("");
		occurrenceText.setText("");
		operationText.setText("");
	}

	/**
	 * データをロードする
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();
		if( outportTableViewer != null)
			outportTableViewer.setInput(rtcParam.getOutports());
		if( inportTableViewer != null)
			inportTableViewer.setInput(rtcParam.getInports());
	}

	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		Set<String> checkSet = new HashSet<String>(); 
		
		for(DataPortParam dataport : rtcParam.getInports()) {
			result = checkDataPort(dataport, checkSet);
			if( result != null) return result;
		}
		//
		for(DataPortParam dataport : rtcParam.getOutports()) {
			result = checkDataPort(dataport, checkSet);
			if( result != null) return result;
		}
		
		return null;
	}
	
	private String checkDataPort(DataPortParam dataport, Set checkSet) {
		String result = null;
		//DataPort Name
		if( dataport.getName()==null || dataport.getName().length()==0 ) {
			result = IMessageConstants.DATAPORT_VALIDATE_PORTNAME1;
			return result;
		}
		if( !StringUtil.checkDigitAlphabet(dataport.getName()) ) {
			result = IMessageConstants.DATAPORT_VALIDATE_PORTNAME2;
			return result;
		}
		//DataPort type
		if( dataport.getType()==null || dataport.getType().length()==0 ) {
			result = IMessageConstants.DATAPORT_VALIDATE_PORTTYPE;
			return result;
		}
		//重複
		if( checkSet.contains(dataport.getName()) ) {
			result = IMessageConstants.DATAPORT_VALIDATE_DUPLICATE;
			return result;
		}

		checkSet.add(dataport.getName());
		return null;
	}

	private class DataPortParamLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof DataPortParam == false) {
				return null;
			}

			DataPortParam dataPortParam = (DataPortParam) element;

			String result = null;
			if (columnIndex == 0) {
				result = dataPortParam.getName();
			} else if (columnIndex == 1) {
				result = dataPortParam.getType();
			} else if (columnIndex == 2) {
				result = dataPortParam.getVarName();
			} else if (columnIndex == 3) {
				result = DataPortParam.COMBO_ITEM[dataPortParam.getPositionByIndex()];
			} else if (columnIndex == 4) {
				result = dataPortParam.getConstraint();
			} else if (columnIndex == 5) {
				result = dataPortParam.getUnit();
			}

			return result;
		}
	}

	private class DataPortParamCellModifier implements ICellModifier {

		private StructuredViewer viewer;

		public DataPortParamCellModifier(StructuredViewer viewer) {
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
			if (element instanceof DataPortParam == false) {
				return null;
			}

			DataPortParam dataPortParam = (DataPortParam) element;

			String result = null;
			if (DATAPORTPARAM_PROPERTY_NAME.equals(property)) {
				result = dataPortParam.getName();
			} else if (DATAPORTPARAM_PROPERTY_TYPE.equals(property)) {
				return new Integer(searchIndex(defaultTypeList, dataPortParam.getType()));
			} else if (DATAPORTPARAM_PROPERTY_VAR_NAME.equals(property)) {
				result = dataPortParam.getVarName();
			} else if (DATAPORTPARAM_PROPERTY_POSITION.equals(property)) {
				return new Integer(dataPortParam.getPositionByIndex());
			} else if (DATAPORTPARAM_PROPERTY_CONSTRAINT.equals(property)) {
				result = dataPortParam.getConstraint();
			} else if (DATAPORTPARAM_PROPERTY_UNIT.equals(property)) {
				result = dataPortParam.getUnit();
			}

			return result;
		}

		/**
		 * {@inheritDoc}
		 */
		public void modify(Object element, String property, Object value) {
			if (element instanceof TableItem == false) {
				return;
			}

			DataPortParam dataPortParam = (DataPortParam) ((TableItem) element)
					.getData();

			if (DATAPORTPARAM_PROPERTY_NAME.equals(property)) {
				dataPortParam.setName((String) value);
				StringBuffer portName = new StringBuffer(dataPortParam.getName());
				if( ((TableItem)element).getParent().equals(inportTableViewer.getTable())) {
					portName.append(" (InPort)");
				} else {
					portName.append(" (OutPort)");
				}
				portNameText.setText(portName.toString());
				
			} else if (DATAPORTPARAM_PROPERTY_TYPE.equals(property)) {
				if( ((Integer)value).intValue()>=0 ) {
					dataPortParam.setType( defaultTypeList[((Integer)value).intValue()] );
				}
			} else if (DATAPORTPARAM_PROPERTY_VAR_NAME.equals(property)) {
				dataPortParam.setVarName((String) value);
			} else if (DATAPORTPARAM_PROPERTY_POSITION.equals(property)) {
				dataPortParam.setPositionByIndex(((Integer) value).intValue());
			} else if (DATAPORTPARAM_PROPERTY_CONSTRAINT.equals(property)) {
				dataPortParam.setConstraint((String) value);
			} else if (DATAPORTPARAM_PROPERTY_UNIT.equals(property)) {
				dataPortParam.setUnit((String) value);
			}

			viewer.refresh();
			update();
		}
	}

	public void setDataPortFormPageEnabled(boolean value) {
		
		setTableViewerEnabled(inportTableViewer, value);
		setTableViewerEnabled(outportTableViewer, value);
	}

	private void setTableViewerEnabled(TableViewer viewer,boolean value) {
		if (viewer != null) {
			Control ctrl = viewer.getControl();
			ctrl.getParent().setEnabled(value);			
			int color = value ? SWT.COLOR_LIST_BACKGROUND : SWT.COLOR_WIDGET_LIGHT_SHADOW;
			ctrl.setBackground(getSite().getShell().getDisplay().getSystemColor(color));
		}
	}
}
