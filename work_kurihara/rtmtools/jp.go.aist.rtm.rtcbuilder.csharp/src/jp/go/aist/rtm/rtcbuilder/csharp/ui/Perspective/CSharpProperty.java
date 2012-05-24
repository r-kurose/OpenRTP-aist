package jp.go.aist.rtm.rtcbuilder.csharp.ui.Perspective;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

public class CSharpProperty extends LanguageProperty {
	private String PerspectiveId = "xxx.xxx.xxx";
	private String PerspectiveName = "C#";
	private String PluginId = "xxx.xxx.xxx";

	public String getPerspectiveId() {
		return PerspectiveId;
	}

	public String getPerspectiveName() {
		return PerspectiveName;
	}

	public String getPluginId() {
		return PluginId;
	}

	@Override
	public List<String> getNatures() {
		List<String> natures = new ArrayList<String>();
//		natures.add("xxx.python.pydev.pythonNature");
		return natures;
	}
}
