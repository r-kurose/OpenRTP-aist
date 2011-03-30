package jp.go.aist.rtm.rtcbuilder.extension;

import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.ui.editors.AbstractCustomFormPage;
import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;

public abstract class AddFormPageExtension {
	//対象言語
	public abstract String getManagerKey();

	public abstract Map<Integer,AbstractCustomFormPage> getCustomPages(RtcBuilderEditor editor);
}
