package jp.go.aist.rtm.rtcbuilder.extension;

import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;

public abstract class ImportExtension {
	//‘ÎÛŒ¾Œê
	public abstract String getManagerKey();

	public abstract String[] getFileDialogFilterNames();

	public abstract String[] getFileDialogFilterExtensions();

	public abstract void setImportData(String filePath,RtcBuilderEditor editor) throws Exception;
}
