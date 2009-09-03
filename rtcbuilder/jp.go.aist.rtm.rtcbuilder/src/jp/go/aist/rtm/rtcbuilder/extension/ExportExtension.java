package jp.go.aist.rtm.rtcbuilder.extension;

import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;

public abstract class ExportExtension {
	//‘ÎÛŒ¾Œê
	public abstract String getManagerKey();

	public abstract String[] getFileDialogFilterNames();
	public abstract String[] getFileDialogFilterExtensions();

	public abstract void export(String filePath,RtcBuilderEditor editor) throws Exception;	
}
