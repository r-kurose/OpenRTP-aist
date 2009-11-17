package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;

/**
 * コンフィギュレーションプロファイルを表すクラス
 */
public class ConfigParameterParam extends AbstractRecordedParam implements
		Serializable {

	private static final long serialVersionUID = -6870474016201077762L;

	private String configName;
	private String defaultValue;
	protected int selection = 0;

	public ConfigParameterParam() {
		this("", "");
	}

	public ConfigParameterParam(String configName, String defaultVal) {
		this.configName = configName;
		this.defaultValue = defaultVal;
		//
		setUpdated(false);
	}

	public String getConfigName() {
		return configName;
	}

	public String getDefaultVal() {
		return defaultValue;
	}

	public int getIndex() {
		return selection;
	}

	public void setConfigName(String configName) {
		checkUpdated(this.configName, configName);
		this.configName = configName;
	}

	public void setDefaultVal(String defaultVal) {
		checkUpdated(this.defaultValue, defaultVal);
		this.defaultValue = defaultVal;
	}

}
