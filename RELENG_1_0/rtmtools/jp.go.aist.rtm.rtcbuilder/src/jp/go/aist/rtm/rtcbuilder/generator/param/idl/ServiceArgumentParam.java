package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;

/**
 * サービスのメソッド引数をあらわすクラス
 */
public class ServiceArgumentParam implements Serializable {
	private static final long serialVersionUID = 5610882091160166120L;

	private String type;
	private String originalType;
	private String name;
	private String direction;
	private boolean isUnbounded;
	private boolean isArray;
	private boolean isStruct;
	private boolean isEnum;

	public String getName() {
		return name;
	}
	public void setName(String argName) {
		this.name = argName;
	}

	public String getType() {
		return type;
	}
	public void setType(String argType) {
		this.type = argType;
	}

	public String getOriginalType() {
		return originalType;
	}
	public void setOriginalType(String argType) {
		this.originalType = argType;
	}

	public String getDirection() {
		return direction;
	}
	public void setDirection(String argDirection) {
		this.direction = argDirection;
	}
	
	public boolean isUnbounded() {
		return isUnbounded;
	}
	public void setUnbounded(boolean isUnbounded) {
		this.isUnbounded = isUnbounded;
	}

	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	
	public boolean isStruct() {
		return isStruct;
	}
	public void setStruct(boolean isStruct) {
		this.isStruct = isStruct;
	}
	
	public boolean isEnum() {
		return isEnum;
	}
	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}
}
