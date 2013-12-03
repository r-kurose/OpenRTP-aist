package jp.go.aist.rtm.rtcbuilder.safety.ui.Perspective;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

public class SafetyProperty extends LanguageProperty {
	private String PerspectiveId = "org.eclipse.cdt.ui.CPerspective";
	//private String PerspectiveName = "Safety";
	private String PerspectiveName = "C/C++";
	private String PluginId = "org.eclipse.cdt.ui.CPerspective";

	public String getPerspectiveId() {
		System.out.println("getPerspectiveId entry");
		return PerspectiveId;
	}

	public String getPerspectiveName() {
		System.out.println("getPerspectiveName entry");
		return PerspectiveName;
	}

	public String getPluginId() {
		System.out.println("getPluginId entry");
		return PluginId;
	}

	@Override
	public List<String> getNatures() {
		System.out.println("getNatures entry");
		List<String> natures = new ArrayList<String>();
		natures.add("org.eclipse.cdt.core.cnature");
		natures.add("org.eclipse.cdt.make.core.makeNature");
		natures.add("org.eclipse.cdt.make.core.ScannerConfigNature");
		natures.add("org.eclipse.cdt.core.ccnature");
		//natures.add("org.eclipse.jdt.core.javanature");
		return natures;
	}
}
