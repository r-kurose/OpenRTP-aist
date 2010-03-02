package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * コンフィギュレーションパラメータ定義を表すクラス
 */
public class ConfigSetParam extends AbstractRecordedParam implements
		Serializable {

	private static final long serialVersionUID = -7557513141707055621L;

	private String name;
	private String type;
	private String varname;
	private String defaultValue;
	private String constraint;
	private String unit;
	//
	private String doc_dataname;
	private String doc_default;
	private String doc_description;
	private String doc_unit;
	private String doc_range;
	private String doc_constraint;
	//Properties
	private List<PropertyParam> properties = new ArrayList<PropertyParam>();

	public ConfigSetParam() {
		this("", "", "", "", "");
	}

	public ConfigSetParam(String name, String type, String defaultVal) {
		this(name, type, "", defaultVal, "");
	}

	public ConfigSetParam(String name, String type, String varname,
			String defaultVal) {
		this(name, type, varname, defaultVal, "");
	}

	public ConfigSetParam(String name, String type, String varname,
			String defaultVal, String constraint) {
		this(name, type, varname, defaultVal, constraint, "");
	}
	
	public ConfigSetParam(String name, String type, String varname,
			String defaultVal, String constraint, String unit) {
		this(name, type, varname, defaultVal, "", "", "", "", "", "");
		//
		this.constraint = constraint;
		this.unit = unit;
		setUpdated(false);
	}

	public ConfigSetParam(String name, String type, String varname,
			String defaultVal, String doc_dataname, String doc_default,
			String doc_description, String doc_unit, String doc_range,
			String doc_constraint) {
		this.name = name;
		this.type = type;
		this.varname = varname;
		this.defaultValue = defaultVal;
		//
		this.doc_dataname = doc_dataname;
		this.doc_default = doc_default;
		this.doc_description = doc_description;
		this.doc_unit = doc_unit;
		this.doc_range = doc_range;
		this.doc_constraint = doc_constraint;
		//
		setUpdated(false);
	}

	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getVarName() {
		return varname;
	}
	public String getDefaultVal() {
		return defaultValue;
	}
	public String getConstraint() {
		return constraint;
	}
	public String getUnit() {
		return unit;
	}
	//
	public String getWidget() {
		for( PropertyParam param : properties ) {
			if( param.getName().equals("__widget__") )
				//
				if(param.getValue().contains(".")) {
					String[] widget = param.getValue().split("\\.");
					return widget[0];
				}
				//
				return param.getValue();
		}
		return "";
	}
	public String getSliderStep() {
		for( PropertyParam param : properties ) {
			if( param.getName().equals("__widget__") )
//			if( param.getName().equals("_slider_step_") )
				//
				if(param.getValue().contains(".")) {
					String[] widget = param.getValue().split("\\.");
					if(widget.length>1) return widget[1];
					return null;
				}
				//
//				return param.getValue();
		}
		return "";
	}
	//
	//
	public String getTmplVarName() {
		if( varname==null || varname.equals(""))
			return this.name;
		return this.varname;
	}

	public void setName(String name) {
		checkUpdated(this.name, name);
		this.name = name;
	}
	public void setType(String type) {
		checkUpdated(this.type, type);
		this.type = type;
	}
	public void setVarName(String varname) {
		checkUpdated(this.varname, varname);
		this.varname = varname;
	}
	public void setDefaultVal(String defaultVal) {
		checkUpdated(this.defaultValue, defaultVal);
		this.defaultValue = defaultVal;
	}
	public void setConstraint(String constraint) {
		checkUpdated(this.constraint, constraint);
		this.constraint = constraint;
	}
	public void setUnit(String unit) {
		checkUpdated(this.unit, unit);
		this.unit = unit;
	}
	//
	public void setWidget(String widget) {
		for (PropertyParam param : properties) {
			if (param.getName().equals("__widget__")) {
				checkUpdated(param.getValue(), widget);
				param.setValue(widget);
				return;
			}
		}
		properties.add(new PropertyParam("__widget__", widget));
		setUpdated(true);
	}
	public void setSliderStep(String sliderStep) {
		for (PropertyParam param : properties) {
			if (param.getName().equals("__widget__")) {
				String value = param.getValue();
				if (value.equals("slider")) {
					param.setValue("slider." + sliderStep);
					setUpdated(true);
				} else if (value.startsWith("slider.")) {
					String oldStep = value.substring("slider.".length());
					checkUpdated(oldStep, sliderStep);
					param.setValue("slider." + sliderStep);
				}
				return;
			}
		}
	}
	//
	//
	public String getDocDataName() {
		return doc_dataname;
	}
	public String getDocDefaultVal() {
		return doc_default;
	}
	public String getDocDescription() {
		return doc_description;
	}
	public String getDocUnit() {
		return doc_unit;
	}
	public String getDocRange() {
		return doc_range;
	}
	public String getDocConstraint() {
		return doc_constraint;
	}
	//
	public void setDocDataName(String dataName) {
		checkUpdated(this.doc_dataname, dataName);
		this.doc_dataname = dataName;
	}
	public void setDocDefaultVal(String defval) {
		checkUpdated(this.doc_default, defval);
		this.doc_default = defval;
	}
	public void setDocDescription(String description) {
		checkUpdated(this.doc_description, description);
		this.doc_description = description;
	}
	public void setDocUnit(String unit) {
		checkUpdated(this.doc_unit, unit);
		this.doc_unit = unit;
	}
	public void setDocRange(String range) {
		checkUpdated(this.doc_range, range);
		this.doc_range = range;
	}
	public void setDocConstraint(String constraint) {
		checkUpdated(this.doc_constraint, constraint);
		this.doc_constraint = constraint;
	}
	//
	public List<PropertyParam> getProperties() {
		return properties;
	}

}
