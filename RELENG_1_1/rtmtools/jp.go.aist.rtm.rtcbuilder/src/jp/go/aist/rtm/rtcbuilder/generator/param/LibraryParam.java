package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;

/**
 * ライブラリ情報を表すクラス
 */
public class LibraryParam extends AbstractRecordedParam implements Serializable {

	private static final long serialVersionUID = -2156457756959722869L;

	private String name;
	private String version;
	private String other;

	public LibraryParam() {
		this("", "", "");
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
		checkUpdated(this.name, name);
		this.name = name;
	}
	public void setVersion(String version) {
		checkUpdated(this.version, version);
		this.version = version;
	}
	public void setOther(String other) {
		checkUpdated(this.other, other);
		this.other = other;
	}

}
