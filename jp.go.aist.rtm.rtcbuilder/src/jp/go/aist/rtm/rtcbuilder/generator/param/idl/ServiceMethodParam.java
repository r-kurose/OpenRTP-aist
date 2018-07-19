package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * サービスのメソッドを表すクラス
 */
public class ServiceMethodParam implements Serializable {
	private static final long serialVersionUID = -880298040480678410L;
	
	private String type;
	private String name;
	private String module;

	private String originalType;

	private boolean isSequence;
	private boolean isArray;
	private boolean isStruct;
	private boolean isString;
	private boolean isChildString;

	private boolean isUnbounded;
	private boolean isInterface;
	private boolean isAlias;
	private int arrayDim;
	private boolean isDefault;

	private List<ServiceArgumentParam> arguments = new ArrayList<ServiceArgumentParam>();

	public boolean getIsVoid() {
		return type.equals("void");
	}
	public boolean getIsBoolean() {
		return type.equals("boolean");
	}
	public boolean getIsResult() {
		return isStruct && !isSequence && !isString && !isArray;
	}

	public String getName() {
		return name;
	}

	public void setName(String methodName) {
		this.name = methodName;
	}

	public String getType() {
		return type;
	}

	public void setType(String methodType) {
		this.type = methodType;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
	public List<ServiceArgumentParam> getArguments() {
		return arguments;
	}
	
	public boolean isSequence() {
		return isSequence;
	}
	public void setSequence(boolean isSequence) {
		this.isSequence = isSequence;
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
	
	public boolean isString() {
		return isString;
	}
	public void setString(boolean isString) {
		this.isString = isString;
	}
	
	public boolean isChildString() {
		return isChildString;
	}
	public void setChildString(boolean isChildString) {
		this.isChildString = isChildString;
	}
/**
 * Append by Hara 2015/06
 */

	public boolean isUnbounded() {
		return this.isUnbounded;
	}
	public void setUnbounded(boolean b) {
		this.isUnbounded = b;
	}
	public boolean isInterface() {
		return this.isInterface;
	}
	public void setInterface(boolean b) {
		this.isInterface = b;
	}
	public boolean isAlias() {
		return this.isAlias;
	}
	public void setAlias(boolean b) {
		this.isAlias = b;
	}

	public String getOriginalType() {
		return this.originalType;
	}
	public void setOriginalType(String typ) {
		this.originalType = typ;
	}

	public int getArrayDim() {
		return this.arrayDim;
	}
	public void setArrayDim(int dim) {
		this.arrayDim = dim;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
