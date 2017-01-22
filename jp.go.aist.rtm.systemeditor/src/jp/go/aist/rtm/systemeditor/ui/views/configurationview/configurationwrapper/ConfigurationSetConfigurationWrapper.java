package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;

import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;

/**
 * 変更前と変更後のConfigurationSetを保持するラッパー
 */
public class ConfigurationSetConfigurationWrapper
		implements Comparable<ConfigurationSetConfigurationWrapper>, Secretable {

	private List<NamedValueConfigurationWrapper> namedValueList = new ArrayList<NamedValueConfigurationWrapper>();
	private String id;
	private boolean isNameModified = false;
	private ConfigurationSet configurationSet;

	public ConfigurationSet getConfigurationSet() {
		return configurationSet;
	}

	public ConfigurationSetConfigurationWrapper(ConfigurationSet configurationSet, String name) {
		this.id = name;
		this.configurationSet = configurationSet;
	}

	public List<NamedValueConfigurationWrapper> getNamedValueList() {
		return namedValueList;
	}

	public void addNamedValue(NamedValueConfigurationWrapper namedValue) {
		namedValueList.add(namedValue);
	}

	public void removeNamedValue(NamedValueConfigurationWrapper namedValue) {
		namedValueList.remove(namedValue);
	}

	public String getId() {
		return id;
	}

	public void setId(String name) {
		if (name.equals(this.id)) {
			return;
		}

		this.id = name;
		isNameModified = true;
	}

	public boolean isNameModified() {
		return isNameModified;
	}

	public boolean isActivateModified() {
		return false;
	}

	/**
	 * 隠し設定値(ID名が「__」で始まる)を判定します
	 * 
	 * @return 隠し設定値の場合はtrue
	 */
	@Override
	public boolean isSecret() {
		return (this.id != null && this.id.startsWith("__"));
	}

	@Override
	public ConfigurationSetConfigurationWrapper clone() {
		ConfigurationSetConfigurationWrapper result = new ConfigurationSetConfigurationWrapper(this.configurationSet,
				this.id);
		for (NamedValueConfigurationWrapper nv : this.namedValueList) {
			result.namedValueList.add(nv.clone());
		}
		return result;
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(ConfigurationSetConfigurationWrapper object) {
		return new CompareToBuilder().append(this.id, object.id).toComparison();
	}

}
