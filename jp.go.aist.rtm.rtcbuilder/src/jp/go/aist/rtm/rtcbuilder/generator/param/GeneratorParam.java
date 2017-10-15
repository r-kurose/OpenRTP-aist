package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ジェネレータの引数となるクラス
 */
public class GeneratorParam implements Serializable {

	private static final long serialVersionUID = -935608504783590375L;
	
	RtcParam rtcParam;
	List<DataTypeParam> dataTypeParams = new ArrayList<DataTypeParam>();

	public RtcParam getRtcParam() {
		return rtcParam;
	}

	public void setRtcParam(RtcParam rtcParam) {
		this.rtcParam = rtcParam;
	}

	public List<DataTypeParam> getDataTypeParams() {
		return dataTypeParams;
	}

	HashMap<String , Object> extensionDatas = new HashMap<String, Object>();

	public HashMap<String, Object> getExtensionDatas() {
		return extensionDatas;
	}
}
