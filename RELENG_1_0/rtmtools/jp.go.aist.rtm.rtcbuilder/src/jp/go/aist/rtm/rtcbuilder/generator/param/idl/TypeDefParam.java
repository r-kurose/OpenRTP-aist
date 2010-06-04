package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ïœêîíËã`ÇÇ†ÇÁÇÌÇ∑ÉNÉâÉX
 */
public class TypeDefParam implements Serializable {
	private static final long serialVersionUID = -5166319784409856148L;
	
	private String originalDef;
	private String targetDef;
	private String scopedName;
	private boolean isSequence;
	private boolean isString;
	private boolean isArray;
	private boolean isStruct;
	private boolean isEnum;
	private List<String> childType;

	public TypeDefParam() {
		originalDef = "";
		targetDef = "";
		scopedName = "";
		isSequence = false;
		isString = false;
		isArray = false;
		isStruct = false;
		isEnum = false;
		childType = new ArrayList<String>();
	}

	public String getOriginalDef() {
		return originalDef;
	}
	public void setOriginalDef(String originalDef) {
		this.originalDef = originalDef;
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

	public List<String> getChildType() {
		return childType;
	}
}
