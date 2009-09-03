package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ジェネレータの引数となるクラス
 */
public class GeneratorParam implements Serializable {

	List<RtcParam> rtcParams = new ArrayList<RtcParam>();

	public List<RtcParam> getRtcParams() {
		return rtcParams;
	}

	HashMap<String , Object> extensionDatas = new HashMap<String, Object>();

	public HashMap<String, Object> getExtensionDatas() {
		return extensionDatas;
	}
}
