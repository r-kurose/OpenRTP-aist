package jp.go.aist.rtm.rtcbuilder.java.template;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー
 */
public class TemplateHelperJava {

	//
	public String convertDescDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_DESC_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_DESC_OFFSET_JAVA);
	}
	public String convertPreDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_DESC_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_PRE_OFFSET_JAVA);
	}
	public String convertPostDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_DESC_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_POST_OFFSET_JAVA);
	}
	public String convertUnitDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_UNIT_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_UNIT_OFFSET_JAVA);
	}
	public String convertRangeDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_RANGE_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_RANGE_OFFSET_JAVA);
	}
	public String convertConstraintDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_CONSTRAINT_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_CONSTRAINT_OFFSET_JAVA);
	}
	public String convertTypeDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_UNIT_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_UNIT_OFFSET_JAVA);
	}
	public String convertNumberDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_NUMBER_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_NUMBER_OFFSET_JAVA);
	}
	public String convertSemanticsDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_SEMANTICS_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_SEMANTICS_OFFSET_JAVA);
	}
	public String convertFrequencyDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_SEMANTICS_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_SEMANTICS_OFFSET_JAVA);
	}
	public String convertCycleDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_CYCLE_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_CYCLE_OFFSET_JAVA);
	}
	public String convertInterfaceDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_INTERFACE_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_INTERFACE_OFFSET_JAVA);
	}
	public String convertInterfaceDetailDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsJava.DOC_INTERFACE_DETAIL_PREFIX_JAVA, IRtcBuilderConstantsJava.DOC_INTERFACE_DETAIL_OFFSET_JAVA);
	}
	//
}
