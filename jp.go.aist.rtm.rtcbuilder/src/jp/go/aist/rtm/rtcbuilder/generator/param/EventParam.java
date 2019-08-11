package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;

/**
 * FSMのイベント情報を表すクラス
 */
public class EventParam  extends AbstractRecordedParam implements Serializable {

	private static final long serialVersionUID = 4150725663790259830L;

	private String dataType;
	//
	private String doc_description;
	private String doc_type;
	private String doc_num;
	private String doc_semantics;
	private String doc_unit;
	private String doc_occurrence;
	private String doc_operation;
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/////
	public String getDoc_description() {
		return doc_description;
	}
	public void setDoc_description(String doc_description) {
		this.doc_description = doc_description;
	}
	
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	
	public String getDoc_num() {
		return doc_num;
	}
	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}
	
	public String getDoc_semantics() {
		return doc_semantics;
	}
	public void setDoc_semantics(String doc_semantics) {
		this.doc_semantics = doc_semantics;
	}
	
	public String getDoc_unit() {
		return doc_unit;
	}
	public void setDoc_unit(String doc_unit) {
		this.doc_unit = doc_unit;
	}
	
	public String getDoc_occurrence() {
		return doc_occurrence;
	}
	public void setDoc_occurrence(String doc_occurrence) {
		this.doc_occurrence = doc_occurrence;
	}
	
	public String getDoc_operation() {
		return doc_operation;
	}
	public void setDoc_operation(String doc_operation) {
		this.doc_operation = doc_operation;
	}
}
