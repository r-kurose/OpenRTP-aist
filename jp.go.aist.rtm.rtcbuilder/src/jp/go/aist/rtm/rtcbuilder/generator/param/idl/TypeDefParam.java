package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 変数定義をあらわすクラス
 */
public class TypeDefParam implements Serializable {
	private static final long serialVersionUID = -5166319784409856148L;
	
	private String originalDef;
	private String moduleName;
	private String targetDef;
	private String scopedName;
	private boolean isSequence;
	private boolean isString;
	private boolean isArray;
	private boolean isInnerArray;
	private boolean isStruct;
	private boolean isEnum;
	private boolean isChildString;
	private boolean isChildDouble;
	private List<String> childType;

	public TypeDefParam() {
		originalDef = "";
		moduleName = "";
		targetDef = "";
		scopedName = "";
		isSequence = false;
		isString = false;
		isArray = false;
		isInnerArray = false;
		isStruct = false;
		isEnum = false;
		isChildString = false;
		isChildDouble = false;
		childType = new ArrayList<String>();
	}

	public String getOriginalDef() {
		return originalDef;
	}
	public void setOriginalDef(String originalDef) {
		this.originalDef = originalDef;
	}

	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getTargetDef() {
		return this.targetDef;
	}
	public void setTargetDef(String targetDef) {
		this.targetDef = targetDef;
	}

	public String getScopedName() {
		return this.scopedName;
	}
	public void setScopedName(String scname) {
		this.scopedName = scname;
	}

	public boolean isSequence() {
		return isSequence;
	}
	public void setSequence(boolean isSequence) {
		this.isSequence = isSequence;
	}

	public boolean isString() {
		return isString;
	}
	public void setString(boolean isString) {
		this.isString = isString;
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

	public boolean isChildString() {
		return isChildString;
	}
	public void setChildString(boolean isChildString) {
		this.isChildString = isChildString;
	}

	public boolean isChildDouble() {
		return isChildDouble;
	}
	public void setChildDouble(boolean isChildDouble) {
		this.isChildDouble = isChildDouble;
	}

	public List<String> getChildType() {
		return childType;
	}
}
