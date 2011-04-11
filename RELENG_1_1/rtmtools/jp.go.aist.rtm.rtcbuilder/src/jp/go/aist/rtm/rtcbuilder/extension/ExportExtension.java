package jp.go.aist.rtm.rtcbuilder.extension;

import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;

public abstract class ExportExtension {
	//対象言語
	public abstract String getManagerKey();
	
	public abstract boolean canCreateProfileName();
	public abstract String createProfileName();

	public abstract void preExport(RtcBuilderEditor editor) throws Exception;
	public abstract void postExport(String filePath,RtcBuilderEditor editor) throws Exception;
	
	public static class ExportException extends java.lang.Exception {
		private static final long serialVersionUID = -1436220103076727992L;

		public ExportException(String message) {
			super(message);
		}
	}
}
