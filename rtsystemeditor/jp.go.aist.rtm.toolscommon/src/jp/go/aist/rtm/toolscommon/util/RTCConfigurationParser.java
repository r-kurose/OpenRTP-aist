package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

import org.openrtp.namespaces.rtc.version02.And;
import org.openrtp.namespaces.rtc.version02.Configuration;
import org.openrtp.namespaces.rtc.version02.ConfigurationExt;
import org.openrtp.namespaces.rtc.version02.ConstraintHashType;
import org.openrtp.namespaces.rtc.version02.ConstraintListType;
import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version02.ConstraintUnitType;
import org.openrtp.namespaces.rtc.version02.Or;
import org.openrtp.namespaces.rtc.version02.Property;
import org.openrtp.namespaces.rtc.version02.PropertyIsBetween;
import org.openrtp.namespaces.rtc.version02.PropertyIsEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.RtcProfile;

/**
 * RTCプロファイルのコンフィグ部分を解析するクラス
 *
 */
public class RTCConfigurationParser {
	private ConfigurationSet activeSet = createConfigurationSet("default");
	private ConfigurationSet constraints = createConfigurationSet("_default");
	private ConfigurationSet widgets = createConfigurationSet("_widget_");
	private String name;	// 現在設定中のコンフィグカラム名

	@SuppressWarnings("unchecked")
	public List<ConfigurationSet> parse(RtcProfile profile) {
		if (profile == null) return null;
		if( profile.getConfigurationSet().getConfiguration().isEmpty() ) return null;
		
		for( Iterator iterator = profile.getConfigurationSet().getConfiguration().iterator(); iterator.hasNext(); ) {
			Configuration config = (Configuration)iterator.next();
			name = config.getName();
			
			parseConstraint(config.getConstraint());
			parseWidget(config);
			
			activeSet.getConfigurationData().add(createNameValue(config.getDefaultValue()));
		}
		
		List<ConfigurationSet> result = new ArrayList<ConfigurationSet>();
		result.add(activeSet);
		result.add(constraints);
		result.add(widgets);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void parseWidget(Configuration config) {
		if (!(config instanceof ConfigurationExt)) return;
		ConfigurationExt configExt = (ConfigurationExt)config;
		for (Property property : configExt.getProperties()) {
			if (property.getName().equals("_widget_")) {
				widgets.getConfigurationData().add(createNameValue(property.getValue()));
				return;
			}
		}
	}

	private NameValue createNameValue(String value) {
		NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
		nv.setName(name);
		nv.setValue(value);
		return nv;
	}

	private void parseConstraint(ConstraintType constraint) {
		if (constraint == null) return;
		String value = parseConstraintUnit(constraint.getConstraintUnitType());
		if (addConstraint(value))return;
		value = parseConstraintList(constraint.getConstraintListType());
		if (addConstraint(value))return;
		value = parseConstraintHash(constraint.getConstraintHashType());
		if (addConstraint(value))return;
	}

	@SuppressWarnings("unchecked")
	private boolean addConstraint(String value) {
		if (value == null) return false;
		constraints.getConfigurationData().add(createNameValue(value));
		return true;
	}

	private String parseConstraintUnit(ConstraintUnitType constraintUnitType) {
		if (constraintUnitType == null) return null;
		String value = parseAnd(constraintUnitType.getAnd());
		if (value != null) return value;
		value = parseOr(constraintUnitType.getOr());
		if (value != null) return value;
		value = parsePropertyIsBetween(constraintUnitType.getPropertyIsBetween());
		if (value != null) return value;
		value = parsePropertyIsEqualTo(constraintUnitType.getPropertyIsEqualTo());
		if (value != null) return value;
		value = parsePropertyIsGreaterThan(constraintUnitType.getPropertyIsGreaterThan());
		if (value != null) return value;
		value = parsePropertyIsGreaterThanOrEqualTo(constraintUnitType.getPropertyIsGreaterThanOrEqualTo());
		if (value != null) return value;
		value = parsePropertyIsLessThan(constraintUnitType.getPropertyIsLessThan());
		if (value != null) return value;
		value = parsePropertyIsLessThanOrEqualTo(constraintUnitType.getPropertyIsLessThanOrEqualTo());
		if (value != null) return value;
		return null;
	}

	private String parseAnd(And and) {
		if (and == null) return null;
		AndConstraintParser parser = new AndConstraintParser();
		return parser.parse(and);		
	}
	
	private String parseOr(Or or) {
		if (or == null) return null;
		StringBuffer buffer = new StringBuffer("(");
		for (ConstraintType constraint : or.getConstraint()) {
			parseOrElement(buffer, constraint);
		}
		buffer.append(")");
		return buffer.toString();
	}

	private String parsePropertyIsBetween(PropertyIsBetween propertyIsBetween) {
		if (propertyIsBetween == null) return null;
		AndConstraintParser parser = new AndConstraintParser();
		return parser.parse(propertyIsBetween);		
	}

	private String parsePropertyIsEqualTo(PropertyIsEqualTo propertyIsEqualTo) {
		if (propertyIsEqualTo == null) return null;
		return propertyIsEqualTo.getLiteral();
	}

	private String parsePropertyIsGreaterThan(PropertyIsGreaterThan propertyIsGreaterThan) {
		if (propertyIsGreaterThan == null) return null;
		return "x>" + propertyIsGreaterThan.getLiteral();
	}

	private String parsePropertyIsGreaterThanOrEqualTo(PropertyIsGreaterThanOrEqualTo propertyIsGreaterThanOrEqualTo) {
		if (propertyIsGreaterThanOrEqualTo == null) return null;
		return "x>=" + propertyIsGreaterThanOrEqualTo.getLiteral();
	}

	private String parsePropertyIsLessThan(PropertyIsLessThan propertyIsLessThan) {
		if (propertyIsLessThan == null) return null;
		return "x<" + propertyIsLessThan.getLiteral();
	}

	private String parsePropertyIsLessThanOrEqualTo(PropertyIsLessThanOrEqualTo propertyIsLessThanOrEqualTo) {
		if (propertyIsLessThanOrEqualTo == null) return null;
		return "x<=" + propertyIsLessThanOrEqualTo.getLiteral();
	}

	private String parseConstraintList(ConstraintListType constraintListType) {
		if (constraintListType == null) return null;
		StringBuffer buffer = new StringBuffer();
		for (ConstraintType constraint : constraintListType.getConstraint()) {
			String value = parseConstraintUnit(constraint.getConstraintUnitType());
			if (value == null) continue;
			if (buffer.length() > 0) buffer.append(", ");
			buffer.append(value);
		}
		return buffer.toString();
	}	
	
	private String parseConstraintHash(ConstraintHashType constraintHashType) {
		if (constraintHashType == null) return null;
		StringBuffer buffer = new StringBuffer("{");
		for (ConstraintType constraint : constraintHashType.getConstraint()) {
			ConstraintUnitType constraintUnitType = constraint.getConstraintUnitType();
			String value = parseConstraintUnit(constraintUnitType);
			if (value == null) continue;
			String key = constraintUnitType.getKey();
			if (key == null) continue;
			if (buffer.length() > 1) buffer.append(", ");
			buffer.append(key).append(":");
			buffer.append(value);
		}
		buffer.append("}");
		return buffer.toString();
	}

	private void parseOrElement(StringBuffer buffer, ConstraintType constraint) {
		ConstraintUnitType constraintUnitType = constraint.getConstraintUnitType();
		if (constraintUnitType == null) return;
		PropertyIsEqualTo propertyIsEqualTo = constraintUnitType.getPropertyIsEqualTo();
		if (propertyIsEqualTo == null) return;
		if (buffer.length() > 1) buffer.append(",");
		buffer.append(propertyIsEqualTo.getLiteral());
	}


	private ConfigurationSet createConfigurationSet(String value) {
		ConfigurationSet configSet = ComponentFactory.eINSTANCE.createConfigurationSet();
		configSet.setId(value);
		return configSet;
	}

	public ConfigurationSet getActiveConfigurationSet() {
		return activeSet;
	}

}
