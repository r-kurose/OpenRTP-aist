package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigParameterParam;

public class ConfigPreferenceManager {
	private static ConfigPreferenceManager __instance = new ConfigPreferenceManager();
	private static final String Separator = "::";
	private static final String EscSeparator = "&colon;";
	
	public static ConfigPreferenceManager getInstance() {
		getDefaultConfigValue();
		return __instance;
	}

	/**
	 * Configuration Nameのキー
	 */
	private static final String Config_Name = ConfigPreferenceManager.class.getName()
									+ "CONFIG_NAME";
	/**
	 * Default Valueのキー
	 */
	private static final String Default_Value = ConfigPreferenceManager.class.getName()
									+ "DEFAULT_VALUE";
	private static final String REASON_OF_NO_ITEM = ConfigPreferenceManager.class.getName() + "REASON_OF_NO_ITEM";
	private static final String IS_DELETED = "IS_DELETED";

	public static ArrayList<ConfigParameterParam> defaultConfigValue = new ArrayList<ConfigParameterParam>();

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(	this);

	
	private static final String[] CONFIG_ITEM = new String[] { 
		"exec_cxt.evdriven.type",
		"exec_cxt.periodic.type"
	};
	private static final String[] DEFAULT_ITEM = new String[] { 
		"EventDrivenExecutionContext",	//"exec_cxt.evdriven.type",
		"PeriodicExecutionContext"		//"exec_cxt.periodic.type",
	};

	public static ArrayList<ConfigParameterParam> getDefaultConfigValue() {
		defaultConfigValue = new ArrayList<ConfigParameterParam>();
		String resultConf = null;
		String resultValue = null;
		
		for (int intIdx=0; intIdx < CONFIG_ITEM.length; intIdx++) {
			resultConf = CONFIG_ITEM[intIdx];
			resultValue = DEFAULT_ITEM[intIdx];
			ConfigParameterParam configParam = new ConfigParameterParam(resultConf, resultValue);
			defaultConfigValue.add(configParam);
		}
		
		return defaultConfigValue;
	}

	
	public static String[] getConfigName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Config_Name, "");
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(REASON_OF_NO_ITEM, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Config_Name);
		String reasonOfNoItem = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(REASON_OF_NO_ITEM);
		String[] result;
		if (resultTemp.equals("") && !IS_DELETED.equals(reasonOfNoItem)) { // defaultvalue
			result = CONFIG_ITEM;
		} else {
			result = resultTemp.split(Separator);
			for(int index=0;index<result.length;index++ ) {
				result[index] = result[index].replaceAll(EscSeparator, Separator);
			}
		}
		return result;
	}

	public static String[] getDefaultValue() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Default_Value, "");
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(REASON_OF_NO_ITEM, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Default_Value);
		String reasonOfNoItem = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(REASON_OF_NO_ITEM);
		String[] result;
		if (resultTemp.equals("") && !IS_DELETED.equals(reasonOfNoItem)) { // defaultvalue
			result = DEFAULT_ITEM;
		} else {
			result = resultTemp.split(Separator);
			for(int index=0;index<result.length;index++ ) {
				result[index] = result[index].replaceAll(EscSeparator, Separator);
			}
		}
		return result;
	}
	public static List<ConfigParameterParam> getConfigNameValue() {
		String[] configName = getConfigName();
		String[] defaultValue = getDefaultValue();

		ArrayList<ConfigParameterParam> result = new ArrayList<ConfigParameterParam>();
		if( configName.length==1 && "".equals(configName[0]) )
			return result;	// 要素がないときは空のListを返す
		
		for(int intIdx=0; intIdx < configName.length; intIdx++) {
			ConfigParameterParam configParam;
			if(intIdx < defaultValue.length ) {
				configParam = new ConfigParameterParam(configName[intIdx],defaultValue[intIdx]);
			} else {
				 configParam = new ConfigParameterParam(configName[intIdx],"");
			}
			result.add(configParam);
		}
		return result;
	}

	public static String getReasonOfNoItemValue(){
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(REASON_OF_NO_ITEM, "");
		String reasonOfNoItem = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(REASON_OF_NO_ITEM);
		return reasonOfNoItem;
	}
	
	public void setConfigValue(List<ConfigParameterParam> arrayConfig) {
		String oldConfigName = convArray2String(getConfigName());
		String oldDefaultValue = convArray2String(getDefaultValue());
		String oldReasonOfNoItemValue = getReasonOfNoItemValue();
		String newConfigName = convConfName2String(arrayConfig);
		String newDefaultValue = convDefVal2String(arrayConfig);
		String newReasonOfNoItemValue;
		if( newConfigName.length()==0 && newDefaultValue.length()==0 ){
			newReasonOfNoItemValue = IS_DELETED;
		}else{
			newReasonOfNoItemValue = "";
		}
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Config_Name, newConfigName);
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Default_Value, newDefaultValue);
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(REASON_OF_NO_ITEM, newReasonOfNoItemValue);
		propertyChangeSupport.firePropertyChange(Config_Name,oldConfigName,arrayConfig);
		propertyChangeSupport.firePropertyChange(Default_Value,oldDefaultValue,arrayConfig);
		propertyChangeSupport.firePropertyChange(REASON_OF_NO_ITEM,oldReasonOfNoItemValue,newReasonOfNoItemValue);
	}

	public static String convConfName2String(List<ConfigParameterParam> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for (int intIdx=0; intIdx<source.size(); intIdx++) {
			String target = source.get(intIdx).getDefaultVal();
			target = target.replaceAll(Separator, EscSeparator);
			source.get(intIdx).setDefaultVal(target);
			resultTemp.append(source.get(intIdx).getConfigName()+Separator);
		}

		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-Separator.length());
	}
	public static String convDefVal2String(List<ConfigParameterParam> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for (int intIdx=0; intIdx<source.size(); intIdx++) {
			String target = source.get(intIdx).getDefaultVal();
			target = target.replaceAll(Separator, EscSeparator);
			source.get(intIdx).setDefaultVal(target);
			resultTemp.append(source.get(intIdx).getDefaultVal()+Separator);
		}

		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-Separator.length());
	}
	public static String convArray2String(String[] source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for (int intIdx=0; intIdx<source.length; intIdx++) {
			source[intIdx] = source[intIdx].replaceAll(Separator, EscSeparator);
			resultTemp.append(source[intIdx]+Separator);
		}

		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-Separator.length());
	}
}
