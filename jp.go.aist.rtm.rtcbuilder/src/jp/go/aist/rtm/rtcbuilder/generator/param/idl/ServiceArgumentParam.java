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
	private String module;
	private String direction;
	private boolean isUnbounded;
	private boolean isArray;
	private boolean isInnerArray;
	private boolean isStruct;
	private boolean isEnum;
	private boolean isChildDouble;
	private boolean isInterface;
	private boolean isSequence;
	private boolean isAlias;
	private int arrayDim;

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
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
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
	
	public boolean isInnerArray() {
		return isInnerArray;
	}
	public void setInnerArray(boolean isInnerArray) {
		this.isInnerArray = isInnerArray;
	}
	
	public boolean isChildDouble() {
		return isChildDouble;
	}
	public void setChildDouble(boolean isChildDouble) {
		this.isChildDouble = isChildDouble;
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
/**
 * Append by Hara
 */
	public boolean isString() {
	  if(type.equals("string")){
		  return true;
	  }
	  if(this.isAlias && this.originalType.equals("string")){
		  return true;
	  }
	  return false;
	}
	public boolean isSequence() {
		return isSequence;
	}
	public void setSequence(boolean isSequence) {
		this.isSequence = isSequence;
	}
	public boolean isInterface() {
		return isInterface;
	}
	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}
	public boolean isAlias() {
		return isAlias;
	}
	public void setAlias(boolean isAlias) {
		this.isAlias = isAlias;
	}
	public int getArrayDim() {
		return arrayDim;
	}
	public void setArrayDim(int dim) {
		this.arrayDim = dim;
	}
}
