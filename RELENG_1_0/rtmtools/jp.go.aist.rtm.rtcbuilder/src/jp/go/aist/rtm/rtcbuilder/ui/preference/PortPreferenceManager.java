package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class PortPreferenceManager {
	private static PortPreferenceManager __instance = new PortPreferenceManager();
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static PortPreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * DataPort Nameのキー
	 */
	private static final String Generate_DataPort_Name = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_NAME";
	/**
	 * DataPort Typeのキー
	 */
	private static final String Generate_DataPort_Type = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_TYPE";
	/**
	 * DataPort VarNameのキー
	 */
	private static final String Generate_DataPort_VarName = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_VARNAME";
	/**
	 * DataPort Constraintのキー
	 */
	private static final String Generate_DataPort_Constraint = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_CONSTRAINT";
	/**
	 * DataPort Unitのキー
	 */
	private static final String Generate_DataPort_Unit = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_UNIT";
	/**
	 * DataPort 接頭語のキー
	 */
	private static final String Generate_DataPort_Prefix = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_PREFIX";
	/**
	 * DataPort 接尾語のキー
	 */
	private static final String Generate_DataPort_Suffix = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_SUFFIX";
	////
	/**
	 * ServicePort Nameのキー
	 */
	private static final String Generate_ServicePort_Name = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_NAME";
	/**
	 * ServicePort 接頭語のキー
	 */
	private static final String Generate_ServicePort_Prefix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_PREFIX";
	/**
	 * ServicePort 接尾語のキー
	 */
	private static final String Generate_ServicePort_Suffix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_SUFFIX";
	/**
	 * ServiceInterfacet Nameのキー
	 */
	private static final String Generate_ServiceIF_Name = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_NAME";
	/**
	 * ServiceInterfacet Instance Nameのキー
	 */
	private static final String Generate_ServiceIF_InstanceName = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_INSTANCENAME";
	/**
	 * ServiceInterfacet Varriable Nameのキー
	 */
	private static final String Generate_ServiceIF_VarName = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_VARNAME";
	/**
	 * ServiceInterfacet Instance 接頭語のキー
	 */
	private static final String Generate_ServiceIF_Prefix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_PREFIX";
	/**
	 * ServiceInterfacet Instance 接尾語のキー
	 */
	private static final String Generate_ServiceIF_Suffix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_SUFFIX";
	///
	/**
	 * Port設定画面で一度でもOKが押されたか否かを示すフィールドのキー
	 */
	private static final String Port_Preference_Status = PortPreferenceManager.class.getName()
			+ "PORT_PREFERENCE_STATUS";
	/**
	 * Port設定画面で一度でもOKが押された場合上記フィールドに格納する定数値
	 */
	private static final String Port_Preference_Status_Dirty = "DIRTY";

	public static final String DEFAULT_DATAPORT_NAME = "dp_name";
	public static final String DEFAULT_DATAPORT_TYPE = "dp_type";
	public static final String DEFAULT_DATAPORT_VARNAME = "dp_vname";
	public static final String DEFAULT_DATAPORT_CONSTRAINT = "dp_constraint";
	public static final String DEFAULT_DATAPORT_UNIT = "";
	public static final String DEFAULT_DATAPORT_PREFIX = "";
	public static final String DEFAULT_DATAPORT_SUFFIX = "";
	//
	public static final String DEFAULT_SERVICEPORT_NAME = "sv_name";
	public static final String DEFAULT_SERVICEPORT_PREFIX = "";
	public static final String DEFAULT_SERVICEPORT_SUFFIX = "";
	//
	public static final String DEFAULT_SERVICEIF_NAME = "if_name";
	public static final String DEFAULT_SERVICEIF_INSTANCENAME = "if_instance";
	public static final String DEFAULT_SERVICEIF_VARNAME = "if_varname";
	public static final String DEFAULT_SERVICEIF_PREFIX = "";
	public static final String DEFAULT_SERVICEIF_SUFFIX = "";

	/**
	 * コード生成時の DataPort Name デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort Name デフォルト値
	 */
	public String getDataPort_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Name);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort Name デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortName	 DataPort Name デフォルト値
	 */
	public void setDataPort_Name(String defaultDataPortName) {
		String oldDataPortName = getDataPort_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Name, defaultDataPortName);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Name, oldDataPortName, defaultDataPortName);
	}

	/**
	 * コード生成時の DataPort Type デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort Type デフォルト値
	 */
	public String getDataPort_Type() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Type, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Type);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort Type デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortType	 DataPort Type デフォルト値
	 */
	public void setDataPort_Type(String defaultDataPortType) {
		String oldDataPortType = getDataPort_Type();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Type, defaultDataPortType);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Type, oldDataPortType, defaultDataPortType);
	}

	/**
	 * コード生成時の DataPort 変数名 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort 変数名 デフォルト値
	 */
	public String getDataPort_VarName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_VarName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_VarName);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_VARNAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort 変数名 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortVarName	 DataPort 変数名 デフォルト値
	 */
	public void setDataPort_VarName(String defaultDataPortVarName) {
		String oldDataPortVarName = getDataPort_VarName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_VarName, defaultDataPortVarName);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_VarName, oldDataPortVarName, defaultDataPortVarName);
	}

	/**
	 * コード生成時の DataPort 制約 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort 制約 デフォルト値
	 */
	public String getDataPort_Constraint() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Constraint, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Constraint);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_CONSTRAINT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort 制約 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortVarName	 DataPort 制約 デフォルト値
	 */
	public void setDataPort_Constraint(String defaultDataPortConstraint) {
		String oldDataPortConstraint = getDataPort_Constraint();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Constraint, defaultDataPortConstraint);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Constraint, oldDataPortConstraint, defaultDataPortConstraint);
	}
	
	/**
	 * コード生成時の DataPort Unit デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort Unit デフォルト値
	 */
	public String getDataPort_Unit() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Unit, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Unit);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_UNIT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort Unit デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortVarName	 DataPort Unit デフォルト値
	 */
	public void setDataPort_Unit(String defaultDataPortUnit) {
		String oldDataPortUnit = getDataPort_Unit();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Unit, defaultDataPortUnit);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Unit, oldDataPortUnit, defaultDataPortUnit);
	}
	
	/**
	 * コード生成時の DataPort 接頭語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort 接頭語 デフォルト値
	 */
	public String getDataPort_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Prefix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort 接頭語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortPrefix	 DataPort 接頭語 デフォルト値
	 */
	public void setDataPort_Prefix(String defaultDataPortPrefix) {
		String oldDataPortPrefix = getDataPort_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Prefix, defaultDataPortPrefix);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Prefix, oldDataPortPrefix, defaultDataPortPrefix);
	}
	
	/**
	 * コード生成時の DataPort 接尾語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return DataPort 接尾語 デフォルト値
	 */
	public String getDataPort_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Suffix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の DataPort 接尾語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultDataPortSuffix	 DataPort 接尾語 デフォルト値
	 */
	public void setDataPort_Suffix(String defaultDataPortSuffix) {
		String oldDataPortSuffix = getDataPort_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Suffix, defaultDataPortSuffix);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Suffix, oldDataPortSuffix, defaultDataPortSuffix);
	}

	//
	//
	/**
	 * コード生成時の ServicePort 名 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return ServicePort 変数名 デフォルト値
	 */
	public String getServicePort_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServicePort_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServicePort_Name);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEPORT_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServicePort 名 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServicePortName	 ServicePort 名 デフォルト値
	 */
	public void setServicePort_Name(String defaultServicePortName) {
		String oldServicePortName = getServicePort_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServicePort_Name, defaultServicePortName);

		propertyChangeSupport.firePropertyChange(Generate_ServicePort_Name, oldServicePortName, defaultServicePortName);
	}

	/**
	 * コード生成時の ServicePort 接頭語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return ServicePort 接頭語 デフォルト値
	 */
	public String getServicePort_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServicePort_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServicePort_Prefix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEPORT_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServicePort 接頭語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServicePortPrefix	 ServicePort 接頭語 デフォルト値
	 */
	public void setServicePort_Prefix(String defaultServicePortPrefix) {
		String oldServicePortPrefix = getServicePort_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServicePort_Prefix, defaultServicePortPrefix);

		propertyChangeSupport.firePropertyChange(Generate_ServicePort_Prefix, oldServicePortPrefix, defaultServicePortPrefix);
	}

	/**
	 * コード生成時の ServicePort 接尾語 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return ServicePort 接尾語 デフォルト値
	 */
	public String getServicePort_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServicePort_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServicePort_Suffix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEPORT_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServicePort 接尾語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServicePortSuffix	 ServicePort 接尾語 デフォルト値
	 */
	public void setServicePort_Suffix(String defaultServicePortSuffix) {
		String oldServicePortSuffix = getServicePort_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServicePort_Suffix, defaultServicePortSuffix);

		propertyChangeSupport.firePropertyChange(Generate_ServicePort_Suffix, oldServicePortSuffix, defaultServicePortSuffix);
	}

	/**
	 * コード生成時の ServiceIF 名 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return ServiceIF名 デフォルト値
	 */
	public String getServiceIF_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_Name);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServiceIF 名 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServiceIFName	 ServiceIF 名 デフォルト値
	 */
	public void setServiceIF_Name(String defaultServiceIFName) {
		String oldServiceIFName = getServiceIF_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_Name, defaultServiceIFName);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_Name, oldServiceIFName, defaultServiceIFName);
	}

	/**
	 * コード生成時の ServiceIF インスタンス名 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return ServiceIF インスタンス名 デフォルト値
	 */
	public String getServiceIF_InstanceName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_InstanceName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_InstanceName);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_INSTANCENAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServiceIF インスタンス名 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServiceIFInstanceName	 ServiceIF インスタンス名 デフォルト値
	 */
	public void setServiceIF_InstanceName(String defaultServiceIFInstanceName) {
		String oldServiceIFInstanceName = getServiceIF_InstanceName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_InstanceName, defaultServiceIFInstanceName);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_InstanceName, oldServiceIFInstanceName, defaultServiceIFInstanceName);
	}

	/**
	 * コード生成時の ServiceIF 変数名 デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return ServiceIF 変数名 デフォルト値
	 */
	public String getServiceIF_VarName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_VarName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_VarName);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_VARNAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServiceIF 変数名 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServiceIFVarName	 ServiceIF 変数名 デフォルト値
	 */
	public void setServiceIF_VarName(String defaultServiceIFVarName) {
		String oldServiceIFVarName = getServiceIF_VarName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_VarName, defaultServiceIFVarName);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_VarName, oldServiceIFVarName, defaultServiceIFVarName);
	}

	/**
	 * コード生成時の ServiceIF 接頭語を取得する
	 * 
	 * @param key キー
	 * @return ServiceIF 接頭語 デフォルト値
	 */
	public String getServiceIF_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_Prefix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServiceIF 接頭語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServiceIFPrefix	 ServiceIF 接頭語 デフォルト値
	 */
	public void setServiceIF_Prefix(String defaultServiceIFPrefix) {
		String oldServiceIFPrefix = getServiceIF_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_Prefix, defaultServiceIFPrefix);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_Prefix, oldServiceIFPrefix, defaultServiceIFPrefix);
	}

	/**
	 * コード生成時の ServiceIF 接尾語を取得する
	 * 
	 * @param key キー
	 * @return ServiceIF 接尾語 デフォルト値
	 */
	public String getServiceIF_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_Suffix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * コード生成時の ServiceIF 接尾語 デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param defaultServiceIFSuffix	 ServiceIF 接尾語 デフォルト値
	 */
	public void setServiceIF_Suffix(String defaultServiceIFSuffix) {
		String oldServiceIFSuffix = getServiceIF_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_Suffix, defaultServiceIFSuffix);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_Suffix, oldServiceIFSuffix, defaultServiceIFSuffix);
	}

	/**
	 * Port設定画面が設定済みか否かを示す項目の値を取得する
	 * 
	 * @return
	 */
	public String getPortPreferenceStatus() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Port_Preference_Status, "");
		return RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Port_Preference_Status);
	}
	/**
	 * Port設定画面が設定済みか否かを示す項目の値を設定する
	 */
	public void setDirtyToPortPreferenceStatus() {
		String oldStatus = getPortPreferenceStatus();
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Port_Preference_Status, Port_Preference_Status_Dirty);
		propertyChangeSupport.firePropertyChange(Port_Preference_Status, oldStatus, Port_Preference_Status_Dirty);
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
