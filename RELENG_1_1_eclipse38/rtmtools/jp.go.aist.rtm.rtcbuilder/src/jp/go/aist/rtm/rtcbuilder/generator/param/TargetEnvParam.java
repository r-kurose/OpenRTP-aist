package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.List;

/**
 * 実行環境を表すクラス
 */
public class TargetEnvParam extends AbstractRecordedParam implements
		Serializable {

	private static final long serialVersionUID = 3332439570605262920L;

	private String langVersion;
	private String os;
	private String other;
	private String cpuOther;
	private RecordedList<String> osVersions = new RecordedList<String>();
	private RecordedList<String> cpus = new RecordedList<String>();
	private RecordedList<LibraryParam> libraries = new RecordedList<LibraryParam>();
	//

	public TargetEnvParam() {
		this("", "", "", "");
	}
	
	public TargetEnvParam(String langVersion, String os, String other, String cpuOther) {
		this.langVersion = langVersion;
		this.os = os;
		this.other = other;
		this.cpuOther = cpuOther;
		//
		this.osVersions.clear();
		this.cpus.clear();
		this.libraries.clear();
	}
	
	public String getLangVersion() {
		return this.langVersion;
	}
	public String getOs() {
		return this.os;
	}
	public String getOther() {
		return this.other;
	}
	public String getCpuOther() {
		return this.cpuOther;
	}
	public List<String> getOsVersions() {
		return this.osVersions;
	}
	public List<String> getCpus() {
		return this.cpus;
	}
	public List<LibraryParam> getLibraries() {
		return this.libraries;
	}
	//
	public void setLangVersion(String langVersion) {
		checkUpdated(this.langVersion, langVersion);
		this.langVersion = langVersion;
	}
	public void setOs(String os) {
		checkUpdated(this.os, os);
		this.os = os;
	}
	public void setOther(String other) {
		checkUpdated(this.other, other);
		this.other = other;
	}
	public void setCpuOther(String cpuOther) {
		checkUpdated(this.cpuOther, cpuOther);
		this.cpuOther = cpuOther;
	}

	@Override
	public boolean isUpdated() {
		if (super.isUpdated()) {
			return true;
		}
		if (this.osVersions.isUpdated() || this.cpus.isUpdated()) {
			return true;
		}
		if (this.libraries.isUpdated()) {
			return true;
		}
		return false;
	}

	@Override
	public void resetUpdated() {
		super.resetUpdated();
		//
		this.osVersions.resetUpdated();
		this.cpus.resetUpdated();
		this.libraries.resetUpdated();
	}

}
