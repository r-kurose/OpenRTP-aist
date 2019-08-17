package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.ui.editors.IMessageConstants;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CodeGeneratePreferencePage extends AbstarctFieldEditorPreferencePage implements
							IWorkbenchPreferencePage {
	//
	static private String[][] componentTypeItems = {
						{"STATIC", "STATIC"},
						{"UNIQUE", "UNIQUE"},
						{"COMMUTATIVE","COMMUTATIVE"}};
	static private String[][] activityTypeItems = {
						{"PERIODIC", "PERIODIC"},
						{"SPORADIC", "SPORADIC"},
						{"EVENTDRIVEN","EVENTDRIVEN"}};
	static private String[][] componentKindItems = {
						{"DataFlowComponent", "DataFlowComponent"}, 
						{"FiniteStateMachineComponent","FiniteStateMachineComponent"},
						{"DataFlowFiniteStateMachineComponent","DataFlowFiniteStateMachineComponent"},
						{"FiniteStateMachineMultiModeComponent","FiniteStateMachineMultiModeComponent"},
						{"DataFlowMultiModeComponent","DataFlowMultiModeComponent"},
						{"DataFlowFiniteStateMachineMultiModeComponent", "DataFlowFiniteStateMachineMultiModeComponent"}};
	//
	static private String[][] executionContextTypeItems = {
						{"PeriodicExecutionContext", "PeriodicExecutionContext"},
						{"ExtTrigExecutionContext","ExtTrigExecutionContext"}};
	
	public CodeGeneratePreferencePage(){
		setPreferenceStore(RtcBuilderPlugin.getDefault().getPreferenceStore());
	}
	
	@Override
	public void init(IWorkbench workbench) {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		storeComponentInitialSetting(store);
		storeConfigurationSetInitialSetting(store);
		storeBackupInitialSetting(store);
	}

	@Override
	protected void createFieldEditors() {
		Composite composite = new Composite(getFieldEditorParent(), SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);
		createComponentPart(composite);
		createConfigurationSetParts(composite);
		createBackupParts(composite);
	}

	private void createBackupParts(Composite composite) {
		Composite backupGroup = createGroup(composite, IPreferenceMessageConstants.CODE_GEN_TITLE_BACKUP);
		IntegerFieldEditor moduleMaxInstanceTextEditor = new IntegerFieldEditor(ComponentPreferenceManager.Generate_Backup_Num,
				IMessageConstants.BACKUP_FILE_NUM, backupGroup);
		addField(moduleMaxInstanceTextEditor);
	}
	
	private void createConfigurationSetParts(Composite composite) {
		Composite configGroup = createGroup(composite, IPreferenceMessageConstants.CODE_GEN_TITLE_CONFIG);
		DigitAlphabetStringFieldEditor configurationNameEditor = 
			new DigitAlphabetStringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Name,
					Messages.getString("IMC.CONFIGURATION_TBLLBL_NAME"), configGroup);
		addField(configurationNameEditor);
		StringFieldEditor configurationTypeEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Type,
					Messages.getString("IMC.CONFIGURATION_TBLLBL_TYPE"), configGroup);
		addField(configurationTypeEditor);
		StringFieldEditor configurationVarNameEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_VarName,
					Messages.getString("IMC.CONFIGURATION_LBL_VARNAME"), configGroup);
		addField(configurationVarNameEditor);
		DigitAlphabetStringFieldEditor configurationDefaultEditor = 
			new DigitAlphabetStringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Default,
					Messages.getString("IMC.CONFIGURATION_TBLLBL_DEFAULTVAL"), configGroup);
		addField(configurationDefaultEditor);
		StringFieldEditor configurationConstraintEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Constraint,
					Messages.getString("IMC.CONFIGURATION_LBL_CONSTRAINT"), configGroup);
		addField(configurationConstraintEditor);
		StringFieldEditor configurationUnitEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Unit,
					Messages.getString("IMC.CONFIGURATION_LBL_UNIT"), configGroup);
		addField(configurationUnitEditor);
		//
		StringFieldEditor configurationPrefixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Prefix,
					IPreferenceMessageConstants.PORT_LBL_PREFIX, configGroup);
		addField(configurationPrefixEditor);
		StringFieldEditor configurationSuffixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Configuration_Suffix,
					IPreferenceMessageConstants.PORT_LBL_SUFFIX, configGroup);
		addField(configurationSuffixEditor);
	}

	private void createComponentPart(Composite composite) {
		//Basic Page
		Composite basicGroup = createGroup(composite, IPreferenceMessageConstants.CODE_GEN_TITLE_BASIC);
		DigitAlphabetStringFieldEditor moduleNameEditor = 
			new DigitAlphabetStringFieldEditor(ComponentPreferenceManager.Generate_Basic_Name,
					Messages.getString("IMC.BASIC_LBL_MODULENAME"), basicGroup);
		addField(moduleNameEditor);
		StringFieldEditor moduleDescriptionEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Description,
					Messages.getString("IMC.BASIC_LBL_DESCRIPTION"), basicGroup);
		addField(moduleDescriptionEditor);
		StringFieldEditor moduleVersionEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Version,
					Messages.getString("IMC.BASIC_LBL_VERSION"), basicGroup);
		addField(moduleVersionEditor);
		StringFieldEditor moduleVendorEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Vendor_Name,
					Messages.getString("IMC.BASIC_LBL_VENDOR"), basicGroup);
		addField(moduleVendorEditor);
		StringFieldEditor moduleCategoryEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Category,
					Messages.getString("IMC.BASIC_LBL_CATEGORY"), basicGroup);
		addField(moduleCategoryEditor);
		//
		ComboFieldEditor componentTypeComboEditor = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ComponentType,
				Messages.getString("IMC.BASIC_LBL_COMPONENT_TYPE"), componentTypeItems, basicGroup);
		addField(componentTypeComboEditor);
		ComboFieldEditor activityTypeComboEditor = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ActivityType,
				Messages.getString("IMC.BASIC_LBL_ACTIVITY_TYPE"), activityTypeItems, basicGroup);
		addField(activityTypeComboEditor);
		//
		ComboFieldEditor componentKindCombo = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ComponentKind,
				Messages.getString("IMC.BASIC_LBL_COMPONENT_KIND"), componentKindItems, basicGroup);
		addField(componentKindCombo);
		//
		IntegerFieldEditor moduleMaxInstanceTextEditor = new IntegerFieldEditor(ComponentPreferenceManager.Generate_Basic_Max_Instance,
				Messages.getString("IMC.BASIC_LBL_MAX_INSTANCES"), basicGroup);
		addField(moduleMaxInstanceTextEditor);
		//
		ComboFieldEditor executionTypeCombo = new ComboFieldEditor(ComponentPreferenceManager.Generate_Basic_ExecutionType,
				Messages.getString("IMC.BASIC_LBL_EXECUTION_TYPE"), executionContextTypeItems, basicGroup);
		addField(executionTypeCombo);
		//
		DoubleStringFieldEditor moduleExecutionRateTextEditor = 
			new DoubleStringFieldEditor(ComponentPreferenceManager.Generate_Basic_Execution_Rate,
					Messages.getString("IMC.BASIC_LBL_EXECUTION_RATE"), basicGroup);
		addField(moduleExecutionRateTextEditor);
		//
		StringFieldEditor commonPrefixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Prefix,
					IPreferenceMessageConstants.PORT_LBL_PREFIX, basicGroup);
		addField(commonPrefixEditor);
		StringFieldEditor commonSuffixEditor = 
			new StringFieldEditor(ComponentPreferenceManager.Generate_Basic_Suffix,
					IPreferenceMessageConstants.PORT_LBL_SUFFIX, basicGroup);
		addField(commonSuffixEditor);
	}
	
	private void storeBackupInitialSetting(IPreferenceStore store) {
		store.setDefault(ComponentPreferenceManager.Generate_Backup_Num, ComponentPreferenceManager.DEFAULT_BACKUP_NUM);
	}

	private void storeConfigurationSetInitialSetting(IPreferenceStore store) {
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Name, ComponentPreferenceManager.DEFAULT_CONFIGURATION_NAME);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Type, ComponentPreferenceManager.DEFAULT_CONFIGURATION_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_VarName, ComponentPreferenceManager.DEFAULT_CONFIGURATION_VARNAME);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Default, ComponentPreferenceManager.DEFAULT_CONFIGURATION_DEFAULT);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Constraint, ComponentPreferenceManager.DEFAULT_CONFIGURATION_CONSTRAINT);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Unit, ComponentPreferenceManager.DEFAULT_CONFIGURATION_UNIT);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Prefix, ComponentPreferenceManager.DEFAULT_CONFIGURATION_PREFIX);
		store.setDefault(ComponentPreferenceManager.Generate_Configuration_Suffix, ComponentPreferenceManager.DEFAULT_CONFIGURATION_SUFFIX);
	}

	private void storeComponentInitialSetting(IPreferenceStore store) {
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Name, ComponentPreferenceManager.DEFAULT_COMPONENT_NAME);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Description, ComponentPreferenceManager.DEFAULT_DESCRIPTION);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Version, ComponentPreferenceManager.DEFAULT_VERSION);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Vendor_Name, ComponentPreferenceManager.DEFAULT_VENDER);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Category, ComponentPreferenceManager.DEFAULT_CATEGORY);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ComponentType, ComponentPreferenceManager.DEFAULT_COMPONENT_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ActivityType, ComponentPreferenceManager.DEFAULT_ACTIVITY_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ComponentKind, ComponentPreferenceManager.DEFAULT_COMPONENT_KIND);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_ExecutionType, ComponentPreferenceManager.DEFAULT_EXECUTION_TYPE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Max_Instance, ComponentPreferenceManager.DEFAULT_MAXINST);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Execution_Rate, ComponentPreferenceManager.DEFAULT_EXECUTION_RATE);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Prefix, ComponentPreferenceManager.DEFAULT_PREFIX);
		store.setDefault(ComponentPreferenceManager.Generate_Basic_Suffix, ComponentPreferenceManager.DEFAULT_SUFFIX);
	}
}
