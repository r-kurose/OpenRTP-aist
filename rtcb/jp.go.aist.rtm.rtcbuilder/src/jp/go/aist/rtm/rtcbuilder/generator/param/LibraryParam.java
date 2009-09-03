package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;

/**
 * ライブラリ情報を表すクラス
 */
public class LibraryParam implements Serializable {
	
	private String name;
	private String version;
	private String other;

	public LibraryParam() {
		this.name = "";
		this.version = "";
		this.other = "";
	}
	
	public LibraryParam(String name, String version, String other) {
		this.name = name;
		this.version = version;
		this.other = other;
	}
	
	public String getName() {
		return this.name;
	}
	public String getVersion() {
		return this.version;
	}
	public String getOther() {
		return this.other;
	}
	//
	public void setName(String name) {
		this.name = name;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
