package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;

/**
 * •Ï”’è‹`‚ğ‚ ‚ç‚í‚·ƒNƒ‰ƒX
 */
public class TypeDefParam implements Serializable {
	private String originalDef;
	private String targetDef;
	private String scopedName;
	private boolean isSequence;

	public TypeDefParam() {
		originalDef = "";
		targetDef = "";
		scopedName = "";
		isSequence = false;
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
}
