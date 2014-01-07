package jp.go.aist.rtm.rtcbuilder.safety.manager;

import java.io.File;
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
import jp.go.aist.rtm.rtcbuilder.safety.ui.Perspective.SafetyProperty;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.form;

import static jp.go.aist.rtm.rtcbuilder.safety.IRtcBuilderConstantsSafety.LANG_SAFETY;
import static jp.go.aist.rtm.rtcbuilder.safety.IRtcBuilderConstantsSafety.LANG_SAFETY_ARG;

/**
 * Safetyファイルの出力を制御するマネージャ
 */
public class SafetyGenerateManager extends GenerateManager {

	static final String TEMPLATE_PATH = "jp/go/aist/rtm/rtcbuilder/safety/template";

	static final String MSG_ERROR_GENERATE_FILE = IRTCBMessageConstants.ERROR_CODE_GENERATION;
	
	private final String DEFAULT_VERSION = "1.1.0"; 

	@Override
	public String getTargetVersion() {
		return RTM_VERSION_100;
	}

	@Override
	public String getManagerKey() {
		return LANG_SAFETY;
	}

	@Override
	public String getLangArgList() {
		return LANG_SAFETY_ARG;
	}

	@Override
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		LanguageProperty langProp = null;
		if (rtcParam.isLanguageExist(LANG_SAFETY)) {
			langProp = new SafetyProperty();
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

		if (!rtcParam.isLanguageExist(LANG_SAFETY) || rtcParam.getName() == null) {
			return result;
		}
		try {
			String rootPath = System.getenv("RTM_SAFETY_ROOT") + File.separator + "jar";
			File targetDir = new File(rootPath);
			File[] targetFiles = targetDir.listFiles();
			long lastDate = 0;
			File targetJar = null;
			if( targetFiles==null) {
				//rtcParam.setRtmSafetyVersion(DEFAULT_VERSION);
			} else {
				for(File target : targetFiles) {
					if( target.getName().startsWith("OpenRTM-aist") ) {
						if( lastDate<target.lastModified() ) {
							targetJar = target;
						}
					}
				}
				//
				if( targetJar!=null ) {
					String SafetyVersion = targetJar.getName().substring(13,18);
					//rtcParam.setRtmSafetyVersion(safetyVersion);
				} else {
					//rtcParam.setRtmSafetyVersion(DEFAULT_VERSION);
				}
			}
		} catch (NullPointerException ex) {
			//rtcParam.setRtmSafetyVersion(DEFAULT_VERSION);
		}
		
		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("template", TEMPLATE_PATH);
		contextMap.put("rtcParam", rtcParam);
		contextMap.put("tmpltHelper", new TemplateHelper());
		contextMap.put("tmpltHelperSafety", new TemplateHelperSafety());
		contextMap.put("safetyConv", new SafetyConverter());
		//
		String defaultPath = System.getenv("RTM_SAFETY_ROOT");
		if (defaultPath != null) {
			defaultPath = defaultPath.replaceAll("\\\\", "/");
			contextMap.put("safetyRoot", defaultPath);
		}

		resetIDLServiceClass(rtcParam);

		return generateTemplateCode10(contextMap);
	}

	// RTM 1.0系
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");

		GeneratedResult gr;
		//gr = generateCompSource(contextMap);
		//result.add(gr);
		gr = generateRTCHeader(contextMap);
		result.add(gr);
		gr = generateRTCSource(contextMap);
		result.add(gr);
		//gr = generateRTCImplSource(contextMap);
		//result.add(gr);

		//gr = generateClassPath(contextMap);
		//result.add(gr);
		//gr = generateRunBat(contextMap);
		//result.add(gr);
		//gr = generateRunSh(contextMap);
		//result.add(gr);

		//gr = generateBuildXML(contextMap);
		//result.add(gr);

		//for (IdlFileParam idl : rtcParam.getProviderIdlPathes()) {
		//	contextMap.put("idlFileParam", idl);
		//	for (ServiceClassParam svc : idl.getServiceClassParams()) {
		//		contextMap.put("serviceClassParam", svc);
		//		gr = generateSVCSource(contextMap);
		//		result.add(gr);
		//	}
		//}

		gr = generateDoxygen(contextMap);
		result.add(gr);

		return result;
	}

	// 1.0系 (Safety)

	public GeneratedResult generateCompSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + "Comp.c";
		String infile = "safety/Safety_Comp.c.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		//String outfile = "src/" + rtcParam.getName() + ".c";
		String outfile = rtcParam.getName() + ".c";
		String infile = "safety/Safety_RTC.c.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCHeader(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		//String outfile = "include/" + rtcParam.getName() + "/" + rtcParam.getName() + ".h";
		String outfile = rtcParam.getName() + ".h";
		String infile = "safety/Safety_RTC.h.vsl";
		return generate(infile, outfile, contextMap);
	}



	public GeneratedResult generateRTCImplSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + "Impl.c";
		String infile = "safety/Safety_RTC_Impl.c.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCSource(Map<String, Object> contextMap) {
		ServiceClassParam svc = (ServiceClassParam) contextMap
				.get("serviceClassParam");
		String outfile = "src/" + TemplateHelper.getBasename(svc.getName())
				+ TemplateHelper.getServiceImplSuffix() + ".c";
		String infile = "safety/Safety_SVC.c.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateClassPath(Map<String, Object> contextMap) {
		String outfile = ".classpath";
		String infile = "safety/classpath.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRunBat(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".bat";
		String infile = "safety/run.bat.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRunSh(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".sh";
		String infile = "safety/run.sh.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (ビルド環境)

	public GeneratedResult generateBuildXML(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "build_" + rtcParam.getName() + ".xml";
		String infile = "safety/build.xml.vsl";
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
					new String[] { "RTMSafety", outfile }), e);
		}
	}
	public GeneratedResult generateDoxygen(Map<String, Object> contextMap) {
		String outfile = "Doxygen.conf";
		String infile = "safety/Doxygen.conf.vsl";
		return generate(infile, outfile, contextMap);
	}


}
