package jp.go.aist.rtm.rtcbuilder.generator.param;

/**
 * Propertyî•ñ‚ğ•Û‚·‚éƒNƒ‰ƒX
 */
public class PropertyParam {
	private String name;
	private String value;
	
	public PropertyParam() {
		super();
	}
	public PropertyParam(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
