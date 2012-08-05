package jp.go.aist.rtm.rtcbuilder.factory;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.extension.ExportExtension;
import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

public class ExportCreator {

	private static List<ExportExtension> extensionList;

	@SuppressWarnings("unchecked")
	public ExportCreator() {
		if (extensionList == null) {
			extensionList = RtcBuilderPlugin.getDefault().getExportExtensionLoader().getList();
		}
	}

	public void preExport(RtcBuilderEditor editor) throws Exception {
		for(ExportExtension extension : extensionList) {
			if(StringUtil.matchKey(editor.getRtcParam().getLanguage(), extension.getManagerKey())) {
				extension.preExport(editor);	
			}
		}
	}

	public void postExport(String filePath, RtcBuilderEditor editor) throws Exception {
		for(ExportExtension extension : extensionList) {
			if(StringUtil.matchKey(editor.getRtcParam().getLanguage(), extension.getManagerKey())) {
				extension.postExport(filePath, editor);	
			}
		}
	}

	public boolean canCreateProfileName(RtcBuilderEditor editor) {
		if(getCreateProfileNameExtension(editor)!=null) {
			return true;	
		} else {
			return false;			
		}
	}

	public String createProfileName(RtcBuilderEditor editor) {
		ExportExtension extension = getCreateProfileNameExtension(editor);
		if(extension!=null) {
			return extension.createProfileName();
		}
		return null;
	}
	
	private ExportExtension getCreateProfileNameExtension(RtcBuilderEditor editor) {
		for(ExportExtension extension : extensionList) {
			if(StringUtil.matchKey(editor.getRtcParam().getLanguage(), extension.getManagerKey())) {
				if(extension.canCreateProfileName()) {
					return extension;
				}
			}
		}
		return null;
	}

}
