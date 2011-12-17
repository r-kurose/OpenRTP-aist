package jp.go.aist.rtm.rtcbuilder.java.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.java.ui.Perspective.JavaProperty;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.form;

import static jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava.LANG_JAVA;
import static jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava.LANG_JAVA_ARG;

/**
 * Javaファイルの出力を制御するマネージャ
 */
public class JavaGenerateManager extends GenerateManager {

	static final String TEMPLATE_PATH = "jp/go/aist/rtm/rtcbuilder/java/template";

	static final String MSG_ERROR_GENERATE_FILE = IRTCBMessageConstants.ERROR_CODE_GENERATION;

	@Override
	public String getTargetVersion() {
		return RTM_VERSION_100;
	}

	@Override
	public String getManagerKey() {
		return LANG_JAVA;
	}

	@Override
	public String getLangArgList() {
		return LANG_JAVA_ARG;
	}

	@Override
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		LanguageProperty langProp = null;
		if (rtcParam.isLanguageExist(LANG_JAVA)) {
			langProp = new JavaProperty();
		}
		return langProp;
	}

	/**
	 * ファイルを出力する
	 * 
	 * @param generatorParam
	 *            生成用パラメータ
	 * @return 出力結果のリスト
	 */
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		if (!rtcParam.isLanguageExist(LANG_JAVA) || rtcParam.getName() == null) {
			return result;
		}
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("template", TEMPLATE_PATH);
		contextMap.put("rtcParam", rtcParam);
		contextMap.put("tmpltHelper", new TemplateHelper());
		contextMap.put("tmpltHelperJava", new TemplateHelperJava());
		contextMap.put("javaConv", new JavaConverter());
		//
		String defaultPath = System.getenv("RTM_JAVA_ROOT");
		if (defaultPath != null) {
			defaultPath = defaultPath.replaceAll("\\\\", "/");
			contextMap.put("javaRoot", defaultPath);
		}

		resetIDLServiceClass(rtcParam);

		if (rtcParam.getRtmVersion().equals(RTM_VERSION_042)) {
			return generateTemplateCode04(contextMap);
		}
		return generateTemplateCode10(contextMap);
	}

	// RTM 1.0系
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");

		GeneratedResult gr;
		gr = generateCompSource(contextMap);
		result.add(gr);
		gr = generateRTCSource(contextMap);
		result.add(gr);
		gr = generateRTCImplSource(contextMap);
		result.add(gr);

		gr = generateClassPath(contextMap);
		result.add(gr);
		gr = generateRunBat(contextMap);
		result.add(gr);
		gr = generateRunSh(contextMap);
		result.add(gr);

		gr = generateBuildXML(contextMap);
		result.add(gr);

		for (IdlFileParam idl : rtcParam.getProviderIdlPathes()) {
			contextMap.put("idlFileParam", idl);
			for (ServiceClassParam svc : idl.getServiceClassParams()) {
				contextMap.put("serviceClassParam", svc);
				gr = generateSVCSource(contextMap);
				result.add(gr);
			}
		}

		return result;
	}

	// RTM 0.4系
	public List<GeneratedResult> generateTemplateCode04(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");

		GeneratedResult gr;
		gr = generateCompSource_04(contextMap);
		result.add(gr);
		gr = generateRTCSource_04(contextMap);
		result.add(gr);
		gr = generateRTCImplSource_04(contextMap);
		result.add(gr);

		gr = generateClassPath(contextMap);
		result.add(gr);

		gr = generateBuildXML_04(contextMap);
		result.add(gr);

		for (IdlFileParam idl : rtcParam.getProviderIdlPathes()) {
			contextMap.put("idlFileParam", idl);
			for (ServiceClassParam svc : idl.getServiceClassParams()) {
				contextMap.put("serviceClassParam", svc);
				gr = generateSVCSource_04(contextMap);
				result.add(gr);
			}
		}

		return result;
	}

	// 1.0系 (Java)

	public GeneratedResult generateCompSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + "Comp.java";
		String infile = "java/Java_Comp.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + ".java";
		String infile = "java/Java_RTC.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCImplSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + "Impl.java";
		String infile = "java/Java_RTC_Impl.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCSource(Map<String, Object> contextMap) {
		ServiceClassParam svc = (ServiceClassParam) contextMap
				.get("serviceClassParam");
		String outfile = "src/" + TemplateHelper.getBasename(svc.getName())
				+ TemplateHelper.getServiceImplSuffix() + ".java";
		String infile = "java/Java_SVC.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateClassPath(Map<String, Object> contextMap) {
		String outfile = ".classpath";
		String infile = "java/classpath.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRunBat(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".bat";
		String infile = "java/run.bat.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRunSh(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".sh";
		String infile = "java/run.sh.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 0.4系 (Java)

	public GeneratedResult generateCompSource_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp.java";
		String infile = "java/Java_Comp.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCSource_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".java";
		String infile = "java_04/Java_RTC.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCImplSource_04(
			Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Impl.java";
		String infile = "java_04/Java_RTC_Impl.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCSource_04(Map<String, Object> contextMap) {
		ServiceClassParam svc = (ServiceClassParam) contextMap
				.get("serviceClassParam");
		String outfile = TemplateHelper.getBasename(svc.getName())
				+ TemplateHelper.getServiceImplSuffix() + ".java";
		String infile = "java/Java_SVC.java.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (ビルド環境)

	public GeneratedResult generateBuildXML(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "build_" + rtcParam.getName() + ".xml";
		String infile = "java/build.xml.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 0.4系 (ビルド環境)

	public GeneratedResult generateBuildXML_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "build_" + rtcParam.getName() + ".xml";
		String infile = "java_04/build.xml.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generate(String infile, String outfile,
			Map<String, Object> contextMap) {
		try {
			String template = TEMPLATE_PATH + "/" + infile;
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream ins = cl.getResourceAsStream(template);
			GeneratedResult gr = TemplateUtil.createGeneratedResult(ins,
					contextMap, outfile);
			if (ins != null) {
				ins.close();
			}
			return gr;
		} catch (Exception e) {
			throw new RuntimeException(form(MSG_ERROR_GENERATE_FILE,
					new String[] { "Java", outfile }), e);
		}
	}

}