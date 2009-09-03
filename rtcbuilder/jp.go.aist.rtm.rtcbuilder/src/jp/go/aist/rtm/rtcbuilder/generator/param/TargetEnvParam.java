package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 実行環境を表すクラス
 */
public class TargetEnvParam implements Serializable {
	
	private String langVersion;
	private String os;
	private String other;
	private String cpuOther;
	private List<String> osVersions = new ArrayList<String>();
	private List<String> cpus = new ArrayList<String>();
	private List<LibraryParam> libraries = new ArrayList<LibraryParam>();
	//

	public TargetEnvParam() {
		this.langVersion = "";
		this.os = "";
		this.other = "";
		this.cpuOther = "";
		//
		this.osVersions.clear();
		this.cpus.clear();
		this.libraries.clear();
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
		this.langVersion = langVersion;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public void setCpuOther(String cpuOther) {
		this.cpuOther = cpuOther;
	}
	public void setOsVersions(List<String> osVersions) {
		this.osVersions = osVersions;
	}
	public void setCpus(List<String> cpus) {
		this.cpus = cpus;
	}
	public void setLibraries(List<LibraryParam> libraries) {
		this.libraries = libraries;
	}
}
