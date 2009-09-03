package jp.go.aist.rtm.rtcbuilder.vbdotnet.ui.Perspective;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

public class VbDotNetProperty extends LanguageProperty {
	private String PerspectiveId = "xxx.xxx.xxx";
	private String PerspectiveName = "VB.NET";
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
//		natures.add("org.eclipse.jdt.core.javanature");
		return natures;
	}
}
