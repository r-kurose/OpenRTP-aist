package jp.go.aist.rtm.rtcbuilder.csharp.template;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.csharp.IRtcBuilderConstantsCSharp;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー Python用
 */
public class TemplateHelperCs {
	//
	public String convertDescDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_DESC_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_DESC_OFFSET_CSHARP);
	}
	public String convertPreDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_DESC_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_PRE_OFFSET_CSHARP);
	}
	public String convertPostDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_DESC_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_POST_OFFSET_CSHARP);
	}
	public String convertUnitDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_UNIT_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_UNIT_OFFSET_CSHARP);
	}
	public String convertRangeDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_RANGE_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_RANGE_OFFSET_CSHARP);
	}
	public String convertConstraintDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_CONSTRAINT_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_CONSTRAINT_OFFSET_CSHARP);
	}
	public String convertTypeDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_UNIT_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_UNIT_OFFSET_CSHARP);
	}
	public String convertNumberDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_NUMBER_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_NUMBER_OFFSET_CSHARP);
	}
	public String convertSemanticsDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_SEMANTICS_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_SEMANTICS_OFFSET_CSHARP);
	}
	public String convertFrequencyDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_SEMANTICS_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_SEMANTICS_OFFSET_CSHARP);
	}
	public String convertCycleDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_CYCLE_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_CYCLE_OFFSET_CSHARP);
	}
	public String convertInterfaceDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_INTERFACE_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_INTERFACE_OFFSET_CSHARP);
	}
	public String convertInterfaceDetailDocJava(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsCSharp.DOC_INTERFACE_DETAIL_PREFIX_CSHARP, IRtcBuilderConstantsCSharp.DOC_INTERFACE_DETAIL_OFFSET_CSHARP);
	}
	//
}
