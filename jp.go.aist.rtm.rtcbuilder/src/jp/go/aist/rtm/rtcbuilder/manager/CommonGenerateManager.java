package jp.go.aist.rtm.rtcbuilder.manager;

import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.form;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;

/**
 * 一般ファイルの出力を制御するマネージャ
 */
public class CommonGenerateManager extends GenerateManager {

	static final String TEMPLATE_PATH = "jp/go/aist/rtm/rtcbuilder/template";

	static final String MSG_ERROR_GENERATE_FILE = "Common generation error. [{0}]";

	@Override
	public String getManagerKey() {
		return "Common";
	}

	@Override
	public String getLangArgList() {
		return null;
	}

	/**
	 * ファイルを出力する
	 *
	 * @param generatorParam
	 * @return 出力結果のリスト
	 */
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("template", TEMPLATE_PATH);
		contextMap.put("rtcParam", rtcParam);
		contextMap.put("tmpltHelper", new TemplateHelper());

		return generateTemplateCode10(contextMap);
	}

	// RTM 1.0系
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		GeneratedResult gr;

		gr = generateREADME(contextMap);
		result.add(gr);

		gr = generateRTCConf10(contextMap);
		result.add(gr);

		gr = generateComponentConf(contextMap);
		result.add(gr);

		gr = generateDockerfile(contextMap);
		result.add(gr);

		return result;
	}

	// RTM 1.0

	public GeneratedResult generateREADME(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "README." + rtcParam.getName();
		String infile = "";
		if(rtcParam.isChoreonoid()) {
			infile = "common/README_Choreonoid.vsl";
		} else {
			infile = "common/README.vsl";
		}
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCConf10(Map<String, Object> contextMap) {
		String outfile = "rtc.conf";
		String infile = "common/rtc.conf.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateComponentConf(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".conf";
		String infile = "common/Component.conf.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateDockerfile(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "Dockerfile." + rtcParam.getName();
		String infile = "dockerfile/Dockerfile.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generate(String infile, String outfile,
			Map<String, Object> contextMap) {
		try {
			String template = TEMPLATE_PATH + "/" + infile;
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			GeneratedResult gr = null;
			try( InputStream ins = cl.getResourceAsStream(template) ) {
				gr = TemplateUtil.createGeneratedResult(ins, contextMap, outfile);
			}
			return gr;
		} catch (Exception e) {
			throw new RuntimeException(form(MSG_ERROR_GENERATE_FILE,
					new String[] { outfile }), e);
		}
	}

}
