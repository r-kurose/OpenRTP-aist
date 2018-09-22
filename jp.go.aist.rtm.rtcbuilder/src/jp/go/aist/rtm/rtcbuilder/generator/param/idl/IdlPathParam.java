package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;

public class IdlPathParam implements Serializable {

	private static final long serialVersionUID = 2531043621817335163L;
	
	private String path;
	private boolean isDefault;
	
	public IdlPathParam() {
	}

	public IdlPathParam(String path, boolean isDefault) {
		this.path = path;
		this.isDefault = isDefault;
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
}
