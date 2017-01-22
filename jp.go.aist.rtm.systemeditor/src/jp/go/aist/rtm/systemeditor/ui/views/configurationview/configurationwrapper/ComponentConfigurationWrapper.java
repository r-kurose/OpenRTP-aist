package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

/**
 * コンフィグセットの編集用データ
 *
 */
public class ComponentConfigurationWrapper {

	public static ComponentConfigurationWrapper create(Component target) {
		ComponentConfigurationWrapper result = new ComponentConfigurationWrapper();
		List<ConfigurationSetConfigurationWrapper> configSetList = result.getConfigurationSetList();
		List<ConfigurationSetConfigurationWrapper> secretConfigSetList = new ArrayList<>();

		// パラメータ名−widget種別
		Map<String, String> widgets = new HashMap<>();
		// configurationSet名−制約条件マップ(パラメータ名−制約)
		Map<String, Map<String, String>> conditions = new HashMap<>();

		for (ConfigurationSet cs : target.getConfigurationSets()) {
			if (cs.getId().equals("__widget__")) {
				for (NameValue nv : cs.getConfigurationData()) {
					widgets.put(nv.getName(), nv.getValueAsString());
				}
			} else if (cs.getId().startsWith("__")) {
				String key = cs.getId().substring(2);
				Map<String, String> kc = new HashMap<>();
				for (NameValue nv : cs.getConfigurationData()) {
					kc.put(nv.getName(), nv.getValueAsString());
				}
				conditions.put(key, kc);
			}
		}

		result.widgetSetting = widgets;
		result.conditionSetting = conditions;

		for (ConfigurationSet cs : target.getConfigurationSets()) {
			ConfigurationSetConfigurationWrapper configSetWrapper = new ConfigurationSetConfigurationWrapper(cs,
					cs.getId());
			List<NamedValueConfigurationWrapper> namedValueList = configSetWrapper.getNamedValueList();
			List<NamedValueConfigurationWrapper> secretNamedValueList = new ArrayList<>();

			// configurationSetに対応する制約条件(なければデフォルトを使用)
			Map<String, String> conds = null;
			if (!cs.getId().startsWith("__")) {
				conds = conditions.get(cs.getId());
				if (conds == null) {
					conds = conditions.get("constraints__");
				}
			}
			for (NameValue nv : cs.getConfigurationData()) {
				NamedValueConfigurationWrapper namedValueWrapper = new NamedValueConfigurationWrapper(nv.getName(),
						nv.getValue(), nv.getTypeName());
				if (conds != null) {
					String type = widgets.get(nv.getName());
					String cond = conds.get(nv.getName());
					namedValueWrapper.setWidgetAndCondition(type, cond);
				}
				if (namedValueWrapper.isSecret()) {
					secretNamedValueList.add(namedValueWrapper);
				} else {
					namedValueList.add(namedValueWrapper);
				}
			}
			// 隠しNamedValueは後方へ整列
			Collections.sort(namedValueList);
			namedValueList.addAll(secretNamedValueList);

			if (target.getActiveConfigurationSet() != null
					&& target.getActiveConfigurationSet().getId().equals(cs.getId())) {
				result.setActiveConfigSet(configSetWrapper);
			}

			if (configSetWrapper.isSecret()) {
				secretConfigSetList.add(configSetWrapper);
			} else {
				configSetList.add(configSetWrapper);
			}
		}
		// 隠しConfiguratoinSetは後方へ整列
		Collections.sort(configSetList);
		configSetList.addAll(secretConfigSetList);

		return result;
	}

	private List<ConfigurationSetConfigurationWrapper> configurationSetList = new ArrayList<ConfigurationSetConfigurationWrapper>();
	private ConfigurationSetConfigurationWrapper activeConfigurationSet;
	private Map<String, String> widgetSetting;
	private Map<String, Map<String, String>> conditionSetting;

	public List<ConfigurationSetConfigurationWrapper> getConfigurationSetList() {
		return configurationSetList;
	}

	public void setActiveConfigSet(ConfigurationSetConfigurationWrapper configurationSet) {
		this.activeConfigurationSet = configurationSet;
	}

	public ConfigurationSetConfigurationWrapper getActiveConfigSet() {
		return activeConfigurationSet;
	}

	public void addConfigurationSet(ConfigurationSetConfigurationWrapper configurationSet) {
		configurationSetList.add(configurationSet);
	}

	public void removeConfigurationSet(ConfigurationSetConfigurationWrapper configurationSet) {
		configurationSetList.remove(configurationSet);
	}

	/**
	 * パラメータ名とwidget設定文字列のマップを返します。
	 */
	public Map<String, String> getWidgetSetting() {
		if (this.widgetSetting == null) {
			return new HashMap<String, String>();
		}
		return this.widgetSetting;
	}

	/**
	 * デフォルトのパラメータ名と制約条件設定文字列のマップを返します。
	 */
	public Map<String, String> getDefaultConditionSetting() {
		return this.getConditionSetting("default");
	}

	/**
	 * configSet名を指定して、パラメータ名と制約条件設定文字列のマップを返します。
	 */
	public Map<String, String> getConditionSetting(String key) {
		Map<String, String> result = this.conditionSetting.get(key);
		if (result == null) {
			return new HashMap<String, String>();
		}
		return result;
	}

	/**
	 * デフォルトのNameValueの名前リストを取得します。
	 */
	public Set<String> getDefaultNameSet() {
		Set<String> result = new HashSet<String>();
		if (this.widgetSetting != null) {
			result.addAll(this.widgetSetting.keySet());
		}
		if (this.conditionSetting != null) {
			Map<String, String> dc = this.conditionSetting.get("default");
			if (dc != null) {
				result.addAll(dc.keySet());
			}
		}
		return result;
	}

	@Override
	public ComponentConfigurationWrapper clone() {
		ComponentConfigurationWrapper result = new ComponentConfigurationWrapper();
		List<ConfigurationSetConfigurationWrapper> configurationSetList = result.getConfigurationSetList();
		for (ConfigurationSetConfigurationWrapper cs : this.configurationSetList) {
			configurationSetList.add(cs.clone());
		}
		if (this.widgetSetting != null) {
			result.widgetSetting = new HashMap<String, String>();
			for (String name : this.widgetSetting.keySet()) {
				result.widgetSetting.put(name, this.widgetSetting.get(name));
			}
		}
		if (this.conditionSetting != null) {
			result.conditionSetting = new HashMap<String, Map<String, String>>();
			for (String key : this.conditionSetting.keySet()) {
				Map<String, String> c1 = this.conditionSetting.get(key);
				Map<String, String> c2 = new HashMap<String, String>();
				for (String name : c1.keySet()) {
					c2.put(name, c1.get(name));
				}
				result.conditionSetting.put(key, c2);
			}
		}
		return result;
	}
}
