package jp.go.aist.rtm.rtcbuilder.template;

import java.io.File;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー
 */
public class TemplateHelper {

	/**
	 * ベース名を取得する
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getBasename(String fullName) {
		String[] split = fullName.split("::");
		return split[split.length - 1];
	}

	/**
	 * ファイル名を取得する
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getFileName(String fullPath) {
		if(fullPath==null) return "";
		File file = new File(fullPath);
		return file.getName();
	}
	/**
	 * 拡張子無しファイル名を取得する
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getFilenameNoExt(String fullPath) {
		String fileName = getFileName(fullPath);

		if(fileName == null) return "";
		
		int index = fileName.lastIndexOf('.');
		if(index>0 && index<fileName.length()-1) {
			return fileName.substring(0,index);
		}
		return "";
	}
	public static String getServiceImplSuffix() {
		return IRtcBuilderConstants.DEFAULT_SVC_IMPL_SUFFIX;
	}

	public static String getServiceSkelSuffix() {
		return IRtcBuilderConstants.DEFAULT_SVC_SKEL_SUFFIX;
	}

	public static String getServiceStubSuffix() {
		return IRtcBuilderConstants.DEFAULT_SVC_STUB_SUFFIX;
	}

	public String convertDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_DEFAULT_PREFIX, IRtcBuilderConstants.DOC_DEFAULT_OFFSET);
	}
	public String convertDescDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_DESC_PREFIX, IRtcBuilderConstants.DOC_DESC_OFFSET);
	}
	public String convertPreDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_DESC_PREFIX, IRtcBuilderConstants.DOC_PRE_OFFSET);
	}
	public String convertPostDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_DESC_PREFIX, IRtcBuilderConstants.DOC_POST_OFFSET);
	}
	public String convertUnitDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_UNIT_PREFIX, IRtcBuilderConstants.DOC_UNIT_OFFSET);
	}
	public String convertRangeDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_RANGE_PREFIX, IRtcBuilderConstants.DOC_RANGE_OFFSET);
	}
	public String convertConstraintDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_CONSTRAINT_PREFIX, IRtcBuilderConstants.DOC_CONSTRAINT_OFFSET);
	}
	public String convertTypeDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_UNIT_PREFIX, IRtcBuilderConstants.DOC_UNIT_OFFSET);
	}
	public String convertNumberDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_NUMBER_PREFIX, IRtcBuilderConstants.DOC_NUMBER_OFFSET);
	}
	public String convertSemanticsDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_SEMANTICS_PREFIX, IRtcBuilderConstants.DOC_SEMANTICS_OFFSET);
	}
	public String convertFrequencyDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_SEMANTICS_PREFIX, IRtcBuilderConstants.DOC_SEMANTICS_OFFSET);
	}
	public String convertCycleDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_CYCLE_PREFIX, IRtcBuilderConstants.DOC_CYCLE_OFFSET);
	}
	public String convertInterfaceDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_INTERFACE_PREFIX, IRtcBuilderConstants.DOC_INTERFACE_OFFSET);
	}
	public String convertInterfaceDetailDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_INTERFACE_DETAIL_PREFIX, IRtcBuilderConstants.DOC_INTERFACE_DETAIL_OFFSET);
	}
	//
	public String convertReadMePortDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_PORT_PREFIX, IRtcBuilderConstants.DOC_README_PORT_OFFSET);
	}
	public String convertReadMePortDetailDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_PORT_DETAIL_PREFIX, IRtcBuilderConstants.DOC_README_PORT_DETAIL_OFFSET);
	}
	public String convertReadMeInterfaceDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_INTERFACE_PREFIX, IRtcBuilderConstants.DOC_README_INTERFACE_OFFSET);
	}
	public String convertReadMeConfigDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_PORT_DETAIL_PREFIX, IRtcBuilderConstants.DOC_README_PORT_DETAIL_OFFSET);
	}
	public String convertReadMeModuleDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_MODULE_PREFIX, IRtcBuilderConstants.DOC_README_MODULE_OFFSET);
	}
	public String convertReadMeActivityDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_ACTIVITY_PREFIX, IRtcBuilderConstants.DOC_README_ACTIVITY_OFFSET);
	}
	public String convertReadMeAuthorDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_PREFIX, IRtcBuilderConstants.DOC_AUTHOR_OFFSET);
	}
	public String convertReadMeCopyRightDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_README_COPYRIGHT_PREFIX, IRtcBuilderConstants.DOC_DEFAULT_OFFSET);
	}
	public String convertAuthorDoc(String source) {
		return StringUtil.splitString(source, IRtcBuilderConstants.DOC_DEFAULT_WIDTH, 
				IRtcBuilderConstants.DOC_DEFAULT_PREFIX, IRtcBuilderConstants.DOC_AUTHOR_OFFSET);
	}
	//
}
