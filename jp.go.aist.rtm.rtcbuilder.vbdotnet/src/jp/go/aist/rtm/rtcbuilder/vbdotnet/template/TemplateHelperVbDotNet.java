package jp.go.aist.rtm.rtcbuilder.vbdotnet.template;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import jp.go.aist.rtm.rtcbuilder.vbdotnet.IRtcBuilderConstantsVbDotNet;

public class TemplateHelperVbDotNet {
	//
	public String convertDescDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_DESC_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_DESC_OFFSET_VBDOTNET);
	}
	public String convertPreDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_DESC_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_PRE_OFFSET_VBDOTNET);
	}
	public String convertPostDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_DESC_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_POST_OFFSET_VBDOTNET);
	}
	public String convertUnitDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_UNIT_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_UNIT_OFFSET_VBDOTNET);
	}
	public String convertRangeDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_RANGE_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_RANGE_OFFSET_VBDOTNET);
	}
	public String convertConstraintDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_CONSTRAINT_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_CONSTRAINT_OFFSET_VBDOTNET);
	}
	public String convertTypeDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_UNIT_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_UNIT_OFFSET_VBDOTNET);
	}
	public String convertNumberDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_NUMBER_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_NUMBER_OFFSET_VBDOTNET);
	}
	public String convertSemanticsDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_SEMANTICS_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_SEMANTICS_OFFSET_VBDOTNET);
	}
	public String convertFrequencyDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_SEMANTICS_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_SEMANTICS_OFFSET_VBDOTNET);
	}
	public String convertCycleDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_CYCLE_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_CYCLE_OFFSET_VBDOTNET);
	}
	public String convertInterfaceDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_INTERFACE_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_INTERFACE_OFFSET_VBDOTNET);
	}
	public String convertInterfaceDetailDocVbDotNet(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstantsVbDotNet.DOC_INTERFACE_DETAIL_PREFIX_VBDOTNET, IRtcBuilderConstantsVbDotNet.DOC_INTERFACE_DETAIL_OFFSET_VBDOTNET);
	}
	//

}
