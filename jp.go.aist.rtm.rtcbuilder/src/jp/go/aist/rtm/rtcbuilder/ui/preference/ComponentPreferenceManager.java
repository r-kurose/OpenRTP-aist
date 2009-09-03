package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class ComponentPreferenceManager {
	private static ComponentPreferenceManager __instance = new ComponentPreferenceManager();
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static ComponentPreferenceManager getInstance() {
		return __instance;
	}

	//コード生成
	/**
	 * Module Nameのキー
	 */
	private static final String Generate_Basic_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_MODULE_NAME";
	/**
	 * Descriptionのキー
	 */
	private static final String Generate_Basic_Description = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_DESCRIPTION";
	/**
	 * Versionのキー
	 */
	private static final String Generate_Basic_Version = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_VERSION";
	/**
	 * Vendor Nameのキー
	 */
	private static final String Generate_Basic_Vendor_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_VENDOR_NAME";
	/**
	 * Categoryのキー
	 */
	private static final String Generate_Basic_Category = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_CATEGORY";
	/**
	 * Component Typeのキー
	 */
	private static final String Generate_Basic_ComponentType = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_COMPONENT_TYPE";
	/**
	 * Activity Typeのキー
	 */
	private static final String Generate_Basic_ActivityType = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_ACTIVITY_TYPE";
	/**
	 * Component Kindのキー
	 */
	private static final String Generate_Basic_ComponentKind = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_COMPONENT_KIND";
	/**
	 * Max Instanceのキー
	 */
	private static final String Generate_Basic_Max_Instance = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_MAX_INSTANCES";
	/**
	 * Execution Typeのキー
	 */
	private static final String Generate_Basic_ExecutionType = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_EXECUTION_TYPE";
	/**
	 * Execution Rateのキー
	 */
	private static final String Generate_Basic_Execution_Rate = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_EXECUTION_RATE";
	/**
	 * 共通接頭語のキー
	 */
	private static final String Generate_Basic_Prefix = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_PREFIX";
	/**
	 * 共通接尾語のキー
	 */
	private static final String Generate_Basic_Suffix = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_SUFFIX";
	////
	/**
	 * Configuration Nameのキー
	 */
	private static final String Generate_Configuration_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_NAME";
	/**
	 * Configuration Typeのキー
	 */
	private static final String Generate_Configuration_Type = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_TYPE";
	/**
	 * Configuration Variable Nameのキー
	 */
	private static final String Generate_Configuration_VarName = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_VARNAME";
	/**
	 * Configuration Default Valueのキー
	 */
	private static final String Generate_Configuration_Default = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_DEFAULT";
	/**
	 * Configuration Constraintのキー
	 */
	private static final String Generate_Configuration_Constraint = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_CONSTRAINT";
	/**
	 * Configuration Unitのキー
	 */
	private static final String Generate_Configuration_Unit = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_UNIT";
	/**
	 * Configuration 接頭語のキー
	 */
	private static final String Generate_Configuration_Prefix = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_PREFIX";
	/**
	 * Configuration 接頭尾のキー
	 */
	private static final String Generate_Configuration_Suffix = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_SUFFIX";
	///
	/**
	 * 設定画面で一度でもOKが押されたか否かを示すフィールドのキー
	 */
	private static final String Basic_Preference_Status = ComponentPreferenceManager.class.getName()
			+ "BASIC_PREFERENCE_STATUS";
	/**
	 * 設定画面で一度でもOKが押された場合上記フィールドに格納する定数値
	 */
	private static final String Basic_Preference_Status_Dirty = "DIRTY";

	public static final String DEFAULT_COMPONENT_NAME = "ModuleName";
	public static final String DEFAULT_DESCRIPTION = "ModuleDescription";
	public static final String DEFAULT_CATEGORY = "Category";
	public static final String DEFAULT_VERSION = "1.0.0";
	public static final String DEFAULT_VENDER = "VenderName";
	public static final String DEFAULT_COMPONENT_TYPE = IRtcBuilderConstants.COMPONENT_TYPE_ITEMS[0];
	public static final String DEFAULT_ACTIVITY_TYPE = IRtcBuilderConstants.ACTIVITY_TYPE_ITEMS[0];
	public static final String DEFAULT_COMPONENT_KIND = IRtcBuilderConstants.COMPONENT_KIND_ITEMS[0];
	public static final int DEFAULT_MAXINST = 1;
	public static final String DEFAULT_EXECUTION_TYPE = IRtcBuilderConstants.EXECUTIONCONTEXT_TYPE_ITEMS[0];
	public static final double DEFAULT_EXECUTION_RATE = 1.0;
	public static final String DEFAULT_PREFIX = "m_";
	public static final String DEFAULT_SUFFIX = "";
	//
	public static final String DEFAULT_CONFIGURATION_NAME = "conf_name";
	public static final String DEFAULT_CONFIGURATION_TYPE = "conf_type";
	public static final String DEFAULT_CONFIGURATION_VARNAME = "conf_varname";
	public static final String DEFAULT_CONFIGURATION_DEFAULT = "conf_default";
	public static final String DEFAULT_CONFIGURATION_CONSTRAINT = "conf_constraint";
	public static final String DEFAULT_CONFIGURATION_UNIT = "";
	public static final String DEFAULT_CONFIGURATION_PREFIX = "";
	public static final String DEFAULT_CONFIGURATION_SUFFIX = "";
	//
	

	/**
	 * コード生成時の ModuleName デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Name デフォルト値
	 */
	public String getBasic_ComponentName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Name);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_COMPONENT_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ModuleName デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultModuleName	 ModuleName デフォルト値
	 */
	public void setBasic_ComponentName(String defaultModuleName) {
		String oldModuleName = getBasic_ComponentName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Name, defaultModuleName);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Name, oldModuleName, defaultModuleName);
	}
	
	/**
	 * コード生成時の ModuleDescription デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Description デフォルト値
	 */
	public String getBasic_Description() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Description, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Description);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_DESCRIPTION;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ModuleDescription デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDescription	 Module Description デフォルト値
	 */
	public void setBasic_Description(String defaultDescription) {
		String oldDescription = getBasic_Description();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Description, defaultDescription);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Description, oldDescription, defaultDescription);
	}

	/**
	 * コード生成時の Module Category デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Category デフォルト値
	 */
	public String getBasic_Category() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Category, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Category);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CATEGORY;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Category デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultCategory	 Module Category デフォルト値
	 */
	public void setBasic_Category(String defaultCategory) {
		String oldCategory = getBasic_Category();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Category, defaultCategory);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Category, oldCategory, defaultCategory);
	}

	/**
	 * コード生成時の Module Version デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Version デフォルト値
	 */
	public String getBasic_Version() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Version, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Version);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_VERSION;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Version デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultVersion	 Module Version デフォルト値
	 */
	public void setBasic_Version(String defaultVersion) {
		String oldVersion = getBasic_Version();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Version, defaultVersion);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Version, oldVersion, defaultVersion);
	}

	/**
	 * コード生成時の Module Component Type デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Component Type デフォルト値
	 */
	public String getBasic_ComponentType() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ComponentType, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ComponentType);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_COMPONENT_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Component Type デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultCompType	 Module Component Type デフォルト値
	 */
	public void setBasic_ComponentType(String defaultCompType) {
		String oldCompType = getBasic_ComponentType();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ComponentType, defaultCompType);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ComponentType, oldCompType, defaultCompType);
	}

	/**
	 * コード生成時の Module Activity Type デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Activity Type デフォルト値
	 */
	public String getBasic_ActivityType() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ActivityType, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ActivityType);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_ACTIVITY_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Activity Type デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultActivityType	 Module Activity Type デフォルト値
	 */
	public void setBasic_ActivityType(String defaultActivityType) {
		String oldActivityType = getBasic_ActivityType();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ActivityType, defaultActivityType);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ActivityType, oldActivityType, defaultActivityType);
	}

	/**
	 * コード生成時の Module Component Kind デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Component Kind デフォルト値
	 */
	public String getBasic_ComponentKind() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ComponentKind, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ComponentKind);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_COMPONENT_KIND;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Component Kind デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultCompKind	 Module Component Kind デフォルト値
	 */
	public void setBasic_ComponentKind(String defaultCompKind) {
		String oldCompKind = getBasic_ComponentKind();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ComponentKind, defaultCompKind);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ComponentKind, oldCompKind, defaultCompKind);
	}

	/**
	 * コード生成時の Module Vendor Name デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Vendor Name デフォルト値
	 */
	public String getBasic_VendorName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Vendor_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Vendor_Name);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_VENDER;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Vendor Name デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultVendor	 Module Vendor Name デフォルト値
	 */
	public void setBasic_VendorName(String defaultVendor) {
		String oldVendor = getBasic_VendorName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Vendor_Name, defaultVendor);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Vendor_Name, oldVendor, defaultVendor);
	}

	/**
	 * コード生成時の Module Max Instances デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Max Instances デフォルト値
	 */
	public int getBasic_MaxInstances() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Max_Instance, -1);

		int resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getInt(Generate_Basic_Max_Instance);
		int result;
		if (resultTemp == -1) { // defaultvalue
			result = DEFAULT_MAXINST;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Max Instances デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultMaxInst	 Module Max Instances デフォルト値
	 */
	public void setBasic_MaxInstances(int defaultMaxInst) {
		int oldMaxInst = getBasic_MaxInstances();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Max_Instance, defaultMaxInst);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Max_Instance, oldMaxInst, defaultMaxInst);
	}

	/**
	 * コード生成時の Module Execution Type デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Module Execution Type デフォルト値
	 */
	public String getBasic_ExecutionType() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ExecutionType, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ExecutionType);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_EXECUTION_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Module Execution Type デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultExecutionType	 Module Execution Type デフォルト値
	 */
	public void setBasic_ExecutionType(String defaultExecutionType) {
		String oldExecutionType = getBasic_ExecutionType();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ExecutionType, defaultExecutionType);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ExecutionType, oldExecutionType, defaultExecutionType);
	}

	/**
	 * コード生成時の Execution Rate デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Execution Rate デフォルト値
	 */
	public double getBasic_ExecutionRate() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Execution_Rate, -1);

		double resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getDouble(Generate_Basic_Execution_Rate);
		double result;
		if (resultTemp == -1) { // defaultvalue
			result = DEFAULT_EXECUTION_RATE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Execution Rate デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultExecutionRate	 Execution Rate デフォルト値
	 */
	public void setBasic_ExecutionRate(double defaultExecutionRate) {
		double oldExecutionRate = getBasic_ExecutionRate();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Execution_Rate, defaultExecutionRate);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Execution_Rate, Double.valueOf(oldExecutionRate), Double.valueOf(defaultExecutionRate));
	}
	
	/**
	 * コード生成時の 接頭語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return 接頭語 デフォルト値
	 */
	public String getBasic_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Prefix);
		String result;
		if (resultTemp.equals("") && !Basic_Preference_Status_Dirty.equals(getPreferenceStatus())) { // defaultvalue
			result = DEFAULT_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の 接頭語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultPrefix	 接頭語 デフォルト値
	 */
	public void setBasic_Prefix(String defaultPrefix) {
		String oldPrefix = getBasic_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Prefix, defaultPrefix);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Prefix, oldPrefix, defaultPrefix);
	}
	
	/**
	 * コード生成時の 接尾語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return 接尾語 デフォルト値
	 */
	public String getBasic_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Suffix);
		String result;
		if (resultTemp.equals("") && !Basic_Preference_Status_Dirty.equals(getPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の 接尾語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultSuffix	 接尾語 デフォルト値
	 */
	public void setBasic_Suffix(String defaultSuffix) {
		String oldSuffix = getBasic_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Suffix, defaultSuffix);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Suffix, oldSuffix, defaultSuffix);
	}
	////
	/**
	 * コード生成時の Configuration Name デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Configuration Name デフォルト値
	 */
	public String getConfiguration_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Name);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration Name デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultConfigurationName	 Configuration Name デフォルト値
	 */
	public void setConfiguration_Name(String defaultConfigurationName) {
		String oldConfigName = getConfiguration_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Name, defaultConfigurationName);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Name, oldConfigName, defaultConfigurationName);
	}

	/**
	 * コード生成時の Configuration Type デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Configuration Type デフォルト値
	 */
	public String getConfiguration_Type() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Type, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Type);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration Type デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultConfigurationType	 Configuration Type デフォルト値
	 */
	public void setConfiguration_Type(String defaultConfigurationType) {
		String oldConfigType = getConfiguration_Type();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Type, defaultConfigurationType);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Type, oldConfigType, defaultConfigurationType);
	}

	/**
	 * コード生成時の Configuration 変数名 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Configuration 変数名 デフォルト値
	 */
	public String getConfiguration_VarName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_VarName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_VarName);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_VARNAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration 変数名 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultConfigurationVarName	 Configuration 変数名 デフォルト値
	 */
	public void setConfiguration_VarName(String defaultConfigurationVarName) {
		String oldConfigVarName = getConfiguration_VarName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_VarName, defaultConfigurationVarName);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_VarName, oldConfigVarName, defaultConfigurationVarName);
	}

	/**
	 * コード生成時の Configuration デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Configuration デフォルト値
	 */
	public String getConfiguration_Default() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Default, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Default);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_DEFAULT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultConfigurationDefault	 Configuration デフォルト値
	 */
	public void setConfiguration_Default(String defaultConfigurationDefault) {
		String oldConfigDefault = getConfiguration_Default();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Default, defaultConfigurationDefault);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Default, oldConfigDefault, defaultConfigurationDefault);
	}

	/**
	 * コード生成時の Configuration 制約を取得する
	 * 
	 * @param key キー
	 * @return Configuration 制約
	 */
	public String getConfiguration_Constraint() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Constraint, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Constraint);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_CONSTRAINT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration 制約を設定する
	 * 
	 * @param key キー
	 * @param defaultConfigurationConstraint	 Configuration 制約
	 */
	public void setConfiguration_Constraint(String defaultConfigurationConstraint) {
		String oldConfigConstraint = getConfiguration_Constraint();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Constraint, defaultConfigurationConstraint);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Constraint, oldConfigConstraint, defaultConfigurationConstraint);
	}

	/**
	 * コード生成時の Configuration Unitを取得する
	 * 
	 * @param key キー
	 * @return Configuration 制約
	 */
	public String getConfiguration_Unit() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Unit, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Unit);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_UNIT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration Unitを設定する
	 * 
	 * @param key キー
	 * @param defaultConfigurationUnit	 Configuration Unit
	 */
	public void setConfiguration_Unit(String defaultConfigurationUnit) {
		String oldConfigUnit = getConfiguration_Unit();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Unit, defaultConfigurationUnit);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Unit, oldConfigUnit, defaultConfigurationUnit);
	}
	
	/**
	 * コード生成時の Configuration 接頭語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return 接頭語 デフォルト値
	 */
	public String getConfiguration_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Prefix);
		String result;
		if (resultTemp.equals("") && !Basic_Preference_Status_Dirty.equals(getPreferenceStatus())) { // defaultvalue
			result = DEFAULT_CONFIGURATION_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration 接頭語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultConfigPrefix	 接頭語 デフォルト値
	 */
	public void setConfiguration_Prefix(String defaultConfigPrefix) {
		String oldConfigPrefix = getConfiguration_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Prefix, defaultConfigPrefix);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Prefix, oldConfigPrefix, defaultConfigPrefix);
	}
	
	/**
	 * コード生成時の Configuration 接尾語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return Configuration 接尾語 デフォルト値
	 */
	public String getConfiguration_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Suffix);
		String result;
		if (resultTemp.equals("") && !Basic_Preference_Status_Dirty.equals(getPreferenceStatus())) { // defaultvalue
			result = DEFAULT_CONFIGURATION_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の Configuration 接尾語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultSuffix	 Configuration 接尾語 デフォルト値
	 */
	public void setConfiguration_Suffix(String defaultConfigSuffix) {
		String oldConfigSuffix = getConfiguration_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Suffix, defaultConfigSuffix);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Suffix, oldConfigSuffix, defaultConfigSuffix);
	}

	/**
	 * 設定画面が設定済みか否かを示す項目の値を取得する
	 * 
	 * @return
	 */
	public String getPreferenceStatus() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Basic_Preference_Status, "");
		return RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Basic_Preference_Status);
	}
	/**
	 * 設定画面が設定済みか否かを示す項目の値を設定する
	 */
	public void setDirtyToPreferenceStatus() {
		String oldStatus = getPreferenceStatus();
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Basic_Preference_Status, Basic_Preference_Status_Dirty);
		propertyChangeSupport.firePropertyChange(Basic_Preference_Status, oldStatus, Basic_Preference_Status_Dirty);
	}
	
	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
