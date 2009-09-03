package jp.go.aist.rtm.rtcbuilder.manager;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

public abstract class GenerateManager {
	public static String RTC_PROFILE_PARAMETERS_INAPPLICABLE = "RTC_PROFILE_PARAMETERS_INAPPLICABLE";
	public static String RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE = "RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE";

	//生成対象言語
	public abstract String getManagerKey();
	//生成対象言語名称(引数用)
	public abstract String getLangArgList();
	//スケルトンコードの生成
	public abstract List<GeneratedResult> generateTemplateCode(RtcParam rtcParam);
	//生成対象言語用開発プラグイン情報の取得
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		return null;
	}
	//生成対象言語名称(引数用)
	public String getTargetVersion() {
		return IRtcBuilderConstants.RTM_VERSION_042;
	}
	// 特定のタグを適用対象外とするか否か
	public List<String> getInapplicables(){
		// 適用対象外とする場合は、オーバーライドして上で
		// 定義されている定数をListとして返す
		return null;
	}
}
