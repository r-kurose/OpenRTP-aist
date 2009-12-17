package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigParameterParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ConfigPreferenceManager;

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
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * Configページ
 */
public class ConfigurationEditorFormPage extends AbstractEditorFormPage {

	private static final String CONFIGSET_PROPERTY_NAME = "CONFIGRATIONSET_PROPERTY_NAME";
	private static final String CONFIGSET_PROPERTY_TYPE = "CONFIGRATIONSET_PROPERTY_TYPE";
	private static final String CONFIGSET_PROPERTY_DEFAULT = "CONFIGRATIONSET_PROPERTY_DEFAULT";
	//
	private static final String CONFIGPROFILE_PROPERTY_CONFIGURATION = "CONFIGRATION_PROFILE_CONFIGURATION";
	private static final String CONFIGPROFILE_PROPERTY_DEFAULT = "CONFIGRATION_PROFILE_DEFAULT";

	private TableViewer configurationSetTableViewer;
	private Button configurationSetAddButton;
	private Button configurationSetDeleteButton;
	//
	private TableViewer configurationProfileTableViewer;
	private Button configurationProfileAddButton;
	private Button configurationProfileDeleteButton;
	//
	private Text parametertNameDetailText;
	private Text variableNameText;
	private Text unitConfigText;
	private Text constraintConfigText;
	private Combo widgetCombo;
	private Text sliderStepText;
	//
	private Text parametertNameText;
	private Text datanameText;
	private Text defaultValText;
	private Text descriptionText;
	private Text unitText;
	private Text rangeText;
	private Text constraintText;
	//
	private ConfigSetParam preSelection;
	private ConfigSetParam selectParam;
	//
	private String defaultConfigName;
	private String defaultConfigType;
	private String defaultConfigVarName;
	private String defaultConfigDefault;
	private String defaultConfigConstraint;
	private String defaultConfigUnit;
	private String[] defaultTypeList;
	private String[] defaultParamNameList;
	private String[] defaultParamDefaultList;
	
	private Composite configurationParameterSectionComposite;
	
	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public ConfigurationEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.CONFIGURATION_SECTION);
		//
		preSelection = null;
		updateDefaultValue();
	}

	private void updateDefaultValue() {
		defaultConfigName = ComponentPreferenceManager.getInstance().getConfiguration_Name();
		defaultConfigType = ComponentPreferenceManager.getInstance().getConfiguration_Type();
		defaultConfigVarName = ComponentPreferenceManager.getInstance().getConfiguration_VarName();
		defaultConfigDefault = ComponentPreferenceManager.getInstance().getConfiguration_Default();
		defaultConfigConstraint = ComponentPreferenceManager.getInstance().getConfiguration_Constraint();
		defaultConfigUnit = ComponentPreferenceManager.getInstance().getConfiguration_Unit();
		//
		defaultTypeList = super.extractDataTypes();
		//
		defaultParamNameList = ConfigPreferenceManager.getInstance().getConfigName();
		defaultParamDefaultList = ConfigPreferenceManager.getInstance().getDefaultValue();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm);
		FormToolkit toolkit = managedForm.getToolkit();
		//
		Label label = toolkit.createLabel(form.getBody(), IMessageConstants.CONFIGURATION_SECTION_TITLE);
		if( titleFont==null ) {
			titleFont = new Font(form.getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		label.setFont(titleFont);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		//
		configurationSetTableViewer = createConfigurationSetSection(toolkit, form);
		createHintSection(toolkit, form);
		createDetailSection(toolkit, form);
		createDocumentSection(toolkit, form);
		configurationProfileTableViewer = createConfigurationParameterSection(toolkit, form);
		
		// 言語・環境ページより先にこのページが表示された場合、ここで言語を判断する
		editor.setEnabledInfoByLang();

		load();
	}

	private void createDetailSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				"Detail", IMessageConstants.CONFIGURATION_DETAIL_EXPL, 2);
		
		parametertNameDetailText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_PARAMNAME, SWT.BORDER);
		parametertNameDetailText.setEditable(false);
		parametertNameDetailText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		variableNameText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_VARNAME);
		unitConfigText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_UNIT);
		constraintConfigText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_CONSTRAINT);
		String[] widgetItems = {"text", "slider", "spin", "radio" };
		widgetCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.CONFIGURATION_LBL_WIDGET,
				widgetItems);
		sliderStepText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_STEP);
		
		widgetCombo.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if( ((Combo)e.widget).getText().trim().equals("slider") ) {
					sliderStepText.setEnabled(true);
				} else {
					sliderStepText.setEnabled(false);
				}
			}
		});

	}

	private void createDocumentSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				"Documentation", IMessageConstants.CONFIGURATION_DOCUMENT_EXPL, 2);
		
		parametertNameText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_PARAMNAME, SWT.BORDER);
		parametertNameText.setEditable(false);
		parametertNameText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		datanameText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_DATANAME);
		defaultValText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_DEFAULT);
		descriptionText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		descriptionText.setLayoutData(gridData);
		unitText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_UNIT);
		rangeText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_RANGE);
		constraintText = createLabelAndText(toolkit, composite,
				IMessageConstants.CONFIGURATION_LBL_CONSTRAINT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		constraintText.setLayoutData(gridData);
	}

	private TableViewer createConfigurationSetSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.CONFIGURATION_SET_TITLE, IMessageConstants.CONFIGURATION_SET_EXPL, 3);
		//
		final TableViewer configSetTableViewer = new TableViewer(toolkit
				.createTable(composite,
						SWT.SINGLE | SWT.FULL_SELECTION));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		configSetTableViewer.getTable().setLayoutData(gd);

		configSetTableViewer.getTable().setHeaderVisible(true);
		configSetTableViewer.getTable().setLinesVisible(true);

		configSetTableViewer.setContentProvider(new ArrayContentProvider());

		configSetTableViewer.setLabelProvider(new ConfigSetLabelProvider());

		createColumnToTableViewer(configSetTableViewer, IMessageConstants.CONFIGURATION_TBLLBL_NAME, 120);
		createColumnToTableViewer(configSetTableViewer, IMessageConstants.CONFIGURATION_TBLLBL_TYPE, 120);
		createColumnToTableViewer(configSetTableViewer, IMessageConstants.CONFIGURATION_TBLLBL_DEFAULTVAL, 120);

		configSetTableViewer.setColumnProperties(new String[] {
				CONFIGSET_PROPERTY_NAME, CONFIGSET_PROPERTY_TYPE, 
				CONFIGSET_PROPERTY_DEFAULT,
				});

		CellEditor[] editors = new CellEditor[] {
				new TextCellEditor(configSetTableViewer.getTable()),
				new LocalComboBoxCellEditor(configSetTableViewer.getTable(), defaultTypeList, SWT.DROP_DOWN),
				new TextCellEditor(configSetTableViewer.getTable()) };

		configSetTableViewer.setCellEditors(editors);
		configSetTableViewer.setCellModifier(new ConfigSetCellModifier(
				configSetTableViewer));

		Composite buttonComposite = toolkit.createComposite(composite, SWT.NONE);
		GridLayout gl = new GridLayout();
		buttonComposite.setLayout(gl);
		gl.marginWidth = 1;
		gd = new GridData();
		gd.verticalAlignment = SWT.BEGINNING;
		gd.widthHint = 50;
		buttonComposite.setLayoutData(gd);

		configurationSetAddButton = createConfigAddButton(toolkit,
				configSetTableViewer, buttonComposite);
		configurationSetDeleteButton = createConfigDeleteButton(toolkit,
				configSetTableViewer, buttonComposite);
		//
		configSetTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				setDocumentContents();
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				selectParam = (ConfigSetParam)selection.getFirstElement();
				if( selectParam != null ) {
					parametertNameDetailText.setText(selectParam.getName());
					variableNameText.setText(getDisplayDocText(selectParam.getVarName()));
					unitConfigText.setText(getDisplayDocText(selectParam.getUnit()));
					constraintConfigText.setText(getDisplayDocText(selectParam.getConstraint()));
					widgetCombo.setText(getDisplayDocText(selectParam.getWidget()));
					sliderStepText.setText(getDisplayDocText(selectParam.getSliderStep()));
					//
					parametertNameText.setText(selectParam.getName());
					datanameText.setText(getDisplayDocText(selectParam.getDocDataName()));
					defaultValText.setText(getDisplayDocText(selectParam.getDocDefaultVal()));
					descriptionText.setText(getDisplayDocText(selectParam.getDocDescription()));
					unitText.setText(getDisplayDocText(selectParam.getDocUnit()));
					rangeText.setText(getDisplayDocText(selectParam.getDocRange()));
					constraintText.setText(getDisplayDocText(selectParam.getDocConstraint()));
					preSelection = selectParam;
				}
			}
		});
		return configSetTableViewer;
	}

	private Button createConfigDeleteButton(FormToolkit toolkit, final TableViewer configSetTableViewer, Composite buttonComposite) {
		Button deleteButton = toolkit.createButton(buttonComposite, "Delete", SWT.PUSH);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = configSetTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) configSetTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) configSetTableViewer.getInput())
							.remove(selectionIndex);
					configSetTableViewer.refresh();
					preSelection = null;
					clearText();
					update();
				}
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		return deleteButton;
	}

	private Button createConfigAddButton(FormToolkit toolkit, final TableViewer configSetTableViewer, Composite buttonComposite) {
		Button addButton = toolkit.createButton(buttonComposite, "Add", SWT.PUSH);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateDefaultValue();
				String confNum = Integer.valueOf(((List) configSetTableViewer.getInput()).size()).toString();
				ConfigSetParam selectParam = new ConfigSetParam(defaultConfigName + confNum, defaultConfigType, defaultConfigVarName, defaultConfigDefault, defaultConfigConstraint, defaultConfigUnit);
				((List) configSetTableViewer.getInput()).add(selectParam);
				configSetTableViewer.refresh();
				update();
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		addButton.setLayoutData(gd);
		return addButton;
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 4);
		//
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_TITLE, IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_PARAMNAME_TITLE, IMessageConstants.CONFIGURATION_HINT_PARAMNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_PARAMTYPE_TITLE, IMessageConstants.CONFIGURATION_HINT_PARAMTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_VARNAME_TITLE, IMessageConstants.CONFIGURATION_HINT_VARNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_DEFAULT_TITLE, IMessageConstants.CONFIGURATION_HINT_DEFAULT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_TITLE, IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_UNIT_TITLE, IMessageConstants.CONFIGURATION_HINT_UNIT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_WIDGET_TITLE, IMessageConstants.CONFIGURATION_HINT_WIDGET_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.CONFIGURATION_HINT_STEP_TITLE, IMessageConstants.CONFIGURATION_HINT_STEP_DESC, toolkit, composite);
	}
	
	private TableViewer createConfigurationParameterSection(FormToolkit toolkit, ScrolledForm form) {
		configurationParameterSectionComposite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.CONFIGURATION_PARAMETER_TITLE, IMessageConstants.CONFIGURATION_PARAMETER_EXPL, 3);
		//
		final TableViewer configParameterTableViewer = new TableViewer(toolkit.
					createTable(configurationParameterSectionComposite,
						SWT.SINGLE | SWT.FULL_SELECTION));
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		configParameterTableViewer.getTable().setLayoutData(gd);

		configParameterTableViewer.getTable().setHeaderVisible(true);
		configParameterTableViewer.getTable().setLinesVisible(true);

		configParameterTableViewer.setContentProvider(new ArrayContentProvider());

		configParameterTableViewer.setLabelProvider(new ConfigParamLabelProvider());

		createColumnToTableViewer(configParameterTableViewer, IMessageConstants.CONFIGURATION_TBLLBL_CONFIGURATION, 200);
		createColumnToTableViewer(configParameterTableViewer, IMessageConstants.CONFIGURATION_TBLLBL_DEFAULTVAL, 200);

		configParameterTableViewer.setColumnProperties(new String[] {
				CONFIGPROFILE_PROPERTY_CONFIGURATION, CONFIGPROFILE_PROPERTY_DEFAULT });

		CellEditor[] editors = new CellEditor[] {
				new LocalComboBoxCellEditor(configParameterTableViewer.getTable(), defaultParamNameList, SWT.DROP_DOWN),
				new TextCellEditor(configParameterTableViewer.getTable()) };

		configParameterTableViewer.setCellEditors(editors);
		configParameterTableViewer.setCellModifier(new ConfigProfileCellModifier(
				configParameterTableViewer));

		Composite buttonComposite = toolkit.createComposite(configurationParameterSectionComposite, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginWidth = 1;
		buttonComposite.setLayout(gl);
		gd = new GridData();
		gd.verticalAlignment = SWT.BEGINNING;
		gd.widthHint = 50;
		buttonComposite.setLayoutData(gd);

		configurationProfileAddButton = createParamAddButton(toolkit,
				configParameterTableViewer, buttonComposite, gl);
		configurationProfileDeleteButton = createParamDelButton(toolkit,
				configParameterTableViewer, buttonComposite);

		configParameterTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				clearText();
				// modifier.getValue()が呼ばれないことがあるため
			}
		});
		return configParameterTableViewer;
	}

	private Button createParamDelButton(FormToolkit toolkit, final TableViewer configParameterTableViewer, Composite buttonComposite) {
		Button deleteButton = toolkit.createButton(
				buttonComposite, "Delete", SWT.PUSH);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = configParameterTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) configParameterTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) configParameterTableViewer.getInput())
							.remove(selectionIndex);
					preSelection = null;
					clearText();
					configParameterTableViewer.refresh();
					editor.updateDirty();
//					update();
				}
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		return deleteButton;
	}

	private Button createParamAddButton(FormToolkit toolkit, final TableViewer configParameterTableViewer, Composite buttonComposite, GridLayout gl) {
		Button addButton = toolkit.createButton(
				buttonComposite, "Add", SWT.PUSH);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gl.marginRight = 0;
		addButton.setLayoutData(gd);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConfigParameterParam param = null;
				if( defaultParamNameList.length > 0 && defaultParamDefaultList.length > 0 ) {
					param = new ConfigParameterParam(defaultParamNameList[0], defaultParamDefaultList[0]);
				} else {
					param = new ConfigParameterParam("configuration", "");
				}
				((List) configParameterTableViewer.getInput()).add(param);
				setDocumentContents();
				preSelection = null;
				clearText();
				configParameterTableViewer.refresh();
				editor.updateDirty();
//				update();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		addButton.setLayoutData(gd);
		return addButton;
	}

	public void updateForOutput() {
		setDocumentContents();
	}

	private void setDocumentContents() {
		if( preSelection != null ) {
			preSelection.setVarName(getDocText(variableNameText.getText()));
			preSelection.setUnit(getDocText(unitConfigText.getText()));
			preSelection.setConstraint(getDocText(constraintConfigText.getText()));
			preSelection.setWidget(getDocText(widgetCombo.getText()));
			preSelection.setSliderStep(getDocText(sliderStepText.getText()));
			//
			preSelection.setDocDataName(getDocText(datanameText.getText()));
			preSelection.setDocDefaultVal(getDocText(defaultValText.getText()));
			preSelection.setDocDescription(getDocText(descriptionText.getText()));
			preSelection.setDocUnit(getDocText(unitText.getText()));
			preSelection.setDocRange(getDocText(rangeText.getText()));
			preSelection.setDocConstraint(getDocText(constraintText.getText()));
		}
	}
	
	public void update() {
		if (selectParam != null) {
			selectParam.setVarName(getDocText(variableNameText.getText()));
			selectParam.setUnit(getDocText(unitConfigText.getText()));
			selectParam.setConstraint(getDocText(constraintConfigText.getText()));
			selectParam.setWidget(getDocText(widgetCombo.getText()));
			selectParam.setSliderStep(getDocText(sliderStepText.getText()));
			//
			selectParam.setDocDataName(getDocText(datanameText.getText()));
			selectParam.setDocDefaultVal(getDocText(defaultValText.getText()));
			selectParam.setDocDescription(getDocText(descriptionText.getText()));
			selectParam.setDocUnit(getDocText(unitText.getText()));
			selectParam.setDocRange(getDocText(rangeText.getText()));
			selectParam.setDocConstraint(getDocText(constraintText.getText()));
		}
		editor.updateDirty();
	}

	private void clearText() {
		//
		parametertNameDetailText.setText("");
		variableNameText.setText("");
		unitConfigText.setText("");
		constraintConfigText.setText("");
		widgetCombo.select(0);
		sliderStepText.setText("");
		//
		parametertNameText.setText("");
		datanameText.setText("");
		defaultValText.setText("");
		descriptionText.setText("");
		unitText.setText("");
		rangeText.setText("");
		constraintText.setText("");
	}

	/**
	 * データをロードする
	 */
	public void load() {
		if (configurationSetTableViewer == null) {
			return;
		}
		//
		RtcParam rtcParam = editor.getRtcParam();
		configurationSetTableViewer.setInput(rtcParam.getConfigParams());
		configurationProfileTableViewer.setInput(rtcParam
				.getConfigParameterParams());
		//
		StructuredSelection selection = (StructuredSelection) configurationSetTableViewer
				.getSelection();
		ConfigSetParam param = (ConfigSetParam) selection.getFirstElement();
		if (param == null) {
			clearText();
		}
	}

	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		Set<String> checkSet = new HashSet<String>(); 
		
		for(ConfigSetParam config : rtcParam.getConfigParams()) {
			//Configuration name
			if( config.getName()==null || config.getName().length()==0 ) {
				result = IMessageConstants.CONFIGURATION_VALIDATE_NAME1;
				return result;
			}
			if( !StringUtil.checkDigitAlphabet(config.getName()) ) {
				result = IMessageConstants.CONFIGURATION_VALIDATE_NAME2;
				return result;
			}
			//Configuration type
			if( config.getType()==null || config.getType().length()==0 ) {
				result = IMessageConstants.CONFIGURATION_VALIDATE_TYPE;
				return result;
			}
			//Configuration default value
			if( config.getDefaultVal()==null || config.getDefaultVal().length()==0 ) {
				result = IMessageConstants.CONFIGURATION_VALIDATE_DEFVALUE;
				return result;
			}
			//
			if( config.getVarName() != null && config.getVarName().length() > 0) {
				if( !StringUtil.checkDigitAlphabet(config.getVarName()) ) {
					result = IMessageConstants.CONFIGURATION_VALIDATE_VARIABLE;
					return result;
				}
			}
			//重複
			if( checkSet.contains(config.getName()) ) {
				result = IMessageConstants.CONFIGURATION_VALIDATE_DUPLICATE;
				return result;
			}
			checkSet.add(config.getName());
			//制約
			//radioは列挙型のみ
			if( config.getWidget().equals("radio") ) {
				if(!(config.getConstraint().trim().startsWith("(") 
						&& config.getConstraint().trim().endsWith(")")) ) {
					result = IMessageConstants.CONFIGURATION_VALIDATE_RADIO;
					return result;
				}
			}
			//spinはint型のみ
//			if( config.getWidget().equals("spin") ) {
//				if(!( config.getType().trim().equals("int") || config.getType().trim().equals("Integer") ) ) {
//					result = IMessageConstants.CONFIGURATION_VALIDATE_SPIN;
//					return result;
//				}
//			}
//			//spin,sliderには最大値，最小値が必要
//			if( config.getWidget().equals("spin") || config.getWidget().equals("slider")) {
//			}
		}
		return null;
	}

	private class ConfigSetLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof ConfigSetParam == false) {
				return null;
			}
		
			ConfigSetParam configSetParam = (ConfigSetParam) element;
		
			String result = null;
			if (columnIndex == 0) {
				result = configSetParam.getName();
			} else if (columnIndex == 1) {
				result = configSetParam.getType();
			} else if (columnIndex == 2) {
				result = configSetParam.getDefaultVal();
			}
		
			return result;
		}
	}

	private class ConfigSetCellModifier implements ICellModifier {

		private StructuredViewer viewer;

		public ConfigSetCellModifier(StructuredViewer viewer) {
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
			if (element instanceof ConfigSetParam == false) {
				return null;
			}
		
			ConfigSetParam configSetParam = (ConfigSetParam) element;
		
			String result = null;
			if (CONFIGSET_PROPERTY_NAME.equals(property)) {
				result = configSetParam.getName();
			} else if (CONFIGSET_PROPERTY_TYPE.equals(property)) {
				// 表示すべき値が選択肢に含まれていない可能性があるため、
				// 選択肢に含まれていない場合は選択肢に追加した上でインデックスを返す。
				int index = updateDefaultTypeList(configSetParam.getType());
				return new Integer(index);
			} else if (CONFIGSET_PROPERTY_DEFAULT.equals(property)) {
				result = configSetParam.getDefaultVal();
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
		
			ConfigSetParam configSetParam = (ConfigSetParam) ((TableItem) element)
					.getData();
		
			if (CONFIGSET_PROPERTY_NAME.equals(property)) {
				configSetParam.setName((String) value);
				parametertNameText.setText(configSetParam.getName());
				parametertNameDetailText.setText(configSetParam.getName());
			} else if (CONFIGSET_PROPERTY_TYPE.equals(property)) {
				if( value instanceof Integer ) {
					configSetParam.setType( defaultTypeList[((Integer)value).intValue()] );
				}else{
					// 手入力された場合
					updateDefaultTypeList((String)value);
					configSetParam.setType((String)value);
				}
			} else if (CONFIGSET_PROPERTY_DEFAULT.equals(property)) {
				configSetParam.setDefaultVal((String) value);
			}
		
			viewer.refresh();
			update();
		}
		
		private int updateDefaultTypeList(String newValue){
			int index = searchIndex(defaultTypeList, newValue);
			if( index == defaultTypeList.length ){
				// その値がプルダウン選択肢にない場合、選択肢にそれを追加する
				String[] newDefaultTypeList = new String[defaultTypeList.length+1];
				for( int i=0; i<defaultTypeList.length; i++ ){
					newDefaultTypeList[i] = defaultTypeList[i];
				}
				newDefaultTypeList[defaultTypeList.length] = newValue;
				
				defaultTypeList = newDefaultTypeList;
				
				CellEditor[] editors = ((TableViewer)viewer).getCellEditors();
				((LocalComboBoxCellEditor)editors[1]).setItems(defaultTypeList);
			}
			return index;
		}
		
	}
	
	@Override
	protected int searchIndex(String[] sources, String target) {
		for(int intIdx=0;intIdx<sources.length;intIdx++) {
			if( target.equals(sources[intIdx]) )
				return intIdx;
		}
		return sources.length;
	}

	
	private class ConfigParamLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof ConfigParameterParam == false) {
				return null;
			}
			preSelection = null;
			clearText();

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

	private class ConfigProfileCellModifier implements ICellModifier {
	
		private StructuredViewer viewer;

		public ConfigProfileCellModifier(StructuredViewer viewer) {
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
			if (element instanceof ConfigParameterParam == false) {
				return null;
			}
		
			preSelection = null;

			ConfigParameterParam configProfileParam = (ConfigParameterParam) element;
		
			String result = null;
			if (CONFIGPROFILE_PROPERTY_CONFIGURATION.equals(property)) {
				int index = updateDefaultConfigNameList(configProfileParam.getConfigName());
				return new Integer(index);
			} else if (CONFIGPROFILE_PROPERTY_DEFAULT.equals(property)) {
				result = configProfileParam.getDefaultVal();
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
		
			ConfigParameterParam configProfileParam = (ConfigParameterParam) ((TableItem) element)
					.getData();
		
			if (CONFIGPROFILE_PROPERTY_CONFIGURATION.equals(property)) {
				if( value instanceof Integer ) {
					configProfileParam.setConfigName(defaultParamNameList[((Integer) value).intValue()]);
					if(defaultParamDefaultList.length > ((Integer) value).intValue()) {
						configProfileParam.setDefaultVal(
								defaultParamDefaultList[((Integer) value).intValue()]);
					}
				}else{
					// 手入力された場合
					updateDefaultConfigNameList((String)value);
					configProfileParam.setConfigName((String)value);
				}
			} else if (CONFIGPROFILE_PROPERTY_DEFAULT.equals(property)) {
				configProfileParam.setDefaultVal((String) value);
			}
		
			viewer.refresh();
			editor.updateDirty();
		}
		
		private int updateDefaultConfigNameList(String newValue){
			int index = searchIndex(defaultParamNameList, newValue);
			if( index == defaultParamNameList.length ){
				// その値がプルダウン選択肢にない場合、選択肢にそれを追加する
				String[] newDefaultTypeList = new String[defaultParamNameList.length+1];
				for( int i=0; i<defaultParamNameList.length; i++ ){
					newDefaultTypeList[i] = defaultParamNameList[i];
				}
				newDefaultTypeList[defaultParamNameList.length] = newValue;
				
				defaultParamNameList = newDefaultTypeList;
				
				CellEditor[] editors = ((TableViewer)viewer).getCellEditors();
				((LocalComboBoxCellEditor)editors[0]).setItems(defaultParamNameList);
			}
			return index;
		}
	}

	/**
	 * Configurationフォーム内の要素の有効/無効を設定します。
	 * <ul>
	 * <li>config.configSet.table : ConfigurationSetセクションのテーブル</li>
	 * <li>config.configSet.addButton : ConfigurationSetセクションの Addボタン</li>
	 * <li>config.configSet.deleteButton : ConfigurationSetセクションの Deleteボタン</li>
	 * <li>config.configParam.table : ConfigurationParameterセクションのテーブル</li>
	 * <li>config.configParam.addButton : ConfigurationParameterセクションの Addボタン</li>
	 * <li>config.configParam.deleteButton : ConfigurationParameterセクションの
	 * Deleteボタン</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("configSet")) {
			if (configurationSetTableViewer != null) {
				if (widgetInfo.matchWidget("table")) {
					setViewerEnabled(configurationSetTableViewer, enabled);
				}
				if (widgetInfo.matchWidget("addButton")) {
					setButtonEnabled(configurationSetAddButton, enabled);
				}
				if (widgetInfo.matchWidget("deleteButton")) {
					setButtonEnabled(configurationSetDeleteButton, enabled);
				}
			}
		}
		if (widgetInfo.matchSection("configParam")) {
			if (configurationProfileTableViewer != null) {
				if (widgetInfo.matchWidget("table")) {
					setViewerEnabled(configurationProfileTableViewer, enabled);
				}
				if (widgetInfo.matchWidget("addButton")) {
					setButtonEnabled(configurationProfileAddButton, enabled);
				}
				if (widgetInfo.matchWidget("deleteButton")) {
					setButtonEnabled(configurationProfileDeleteButton, enabled);
				}
			}
		}
	}

	// 選択肢以外の値が入力されている場合に対応するためのComboBoxCellEditor
	private class LocalComboBoxCellEditor extends ComboBoxCellEditor {
		private CCombo comboBox;

		public LocalComboBoxCellEditor(Composite parent, String[] items, int style) {
			super(parent, items, style);
		}

		@Override
		protected Control createControl(Composite parent) {
			comboBox = (CCombo) super.createControl(parent);
			return comboBox;
		}

		@Override
		protected Object doGetValue() {
			Object value = super.doGetValue();
			if (value.equals(Integer.valueOf(-1))) { // 選択肢以外が入力された場合
				return comboBox.getText();
			}
			return value;
		}
	}
	
}
