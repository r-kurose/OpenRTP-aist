package jp.go.aist.rtm.rtcbuilder.manager;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;
import jp.go.aist.rtm.rtcbuilder.ui.editors.LanguageEditorSection;

public abstract class GenerateManager {

	//生成対象言語
	public abstract String getManagerKey();
	//スケルトンコードの生成
	public abstract List<GeneratedResult> generateTemplateCode(RtcParam rtcParam);
	//生成対象言語用開発プラグイン情報の取得
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		return null;
	}
	//生成対象言語用入力ページの取得
	public LanguageEditorSection getLanguageEditorSection() {
		return null;
	}

}
