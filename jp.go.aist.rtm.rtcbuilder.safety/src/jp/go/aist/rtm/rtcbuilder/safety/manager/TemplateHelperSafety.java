package jp.go.aist.rtm.rtcbuilder.safety.manager;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.safety.IRtcBuilderConstantsSafety;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー
 */
public class TemplateHelperSafety {

	//
	public String convertDescDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_DESC_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_DESC_OFFSET_SAFETY);
	}
	public String convertPreDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_DESC_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_PRE_OFFSET_SAFETY);
	}
	public String convertPostDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_DESC_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_POST_OFFSET_SAFETY);
	}
	public String convertUnitDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_UNIT_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_UNIT_OFFSET_SAFETY);
	}
	public String convertRangeDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_RANGE_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_RANGE_OFFSET_SAFETY);
	}
	public String convertConstraintDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_CONSTRAINT_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_CONSTRAINT_OFFSET_SAFETY);
	}
	public String convertTypeDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_UNIT_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_UNIT_OFFSET_SAFETY);
	}
	public String convertNumberDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_NUMBER_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_NUMBER_OFFSET_SAFETY);
	}
	public String convertSemanticsDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_SEMANTICS_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_SEMANTICS_OFFSET_SAFETY);
	}
	public String convertFrequencyDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_SEMANTICS_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_SEMANTICS_OFFSET_SAFETY);
	}
	public String convertCycleDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_CYCLE_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_CYCLE_OFFSET_SAFETY);
	}
	public String convertInterfaceDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_INTERFACE_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_INTERFACE_OFFSET_SAFETY);
	}
	public String convertInterfaceDetailDocSafety(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsSafety.DOC_INTERFACE_DETAIL_PREFIX_SAFETY, IRtcBuilderConstantsSafety.DOC_INTERFACE_DETAIL_OFFSET_SAFETY);
	}
	//
	public boolean notNullRTMRoot() {
		String defaultPath = System.getenv("RTM_ROOT");
		if( defaultPath==null ) return false;
		return true;
	}
}
