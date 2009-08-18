package jp.go.aist.rtm.rtcbuilder.extension;

import java.util.List;

public abstract class EditorExtension {
	
	public static String RTC_PROFILE_PARAMETERS_INAPPLICABLE = "RTC_PROFILE_PARAMETERS_INAPPLICABLE";

	public static String RTC_PROFILE_DATA_PORTS_INAPPLICABLE = "RTC_PROFILE_DATA_PORTS_INAPPLICABLE";
	public static String RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE = "RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE";

	public static String GENERATE_BUTTON_SECTION_INAPPLICABLE = "GENERATE_BUTTON_SECTION_INAPPLICABLE";

	//生成対象言語
	public abstract String getManagerKey();

	// 特定のタグを適用対象外とするか否か
	public List<String> getInapplicables(){
		// 適用対象外とする場合は、オーバーライドして上で
		// 定義されている定数をListとして返す
		return null;
	}

}
