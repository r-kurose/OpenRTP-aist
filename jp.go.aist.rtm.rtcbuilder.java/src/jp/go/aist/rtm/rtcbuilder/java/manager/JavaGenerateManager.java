package jp.go.aist.rtm.rtcbuilder.java.manager;

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
	
	private final String DEFAULT_VERSION = "1.1.0"; 

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
		try {
			String rootPath = System.getenv("RTM_JAVA_ROOT") + File.separator + "jar";
			File targetDir = new File(rootPath);
			File[] targetFiles = targetDir.listFiles();
			long lastDate = 0;
			File targetJar = null;
			if( targetFiles==null) {
				rtcParam.setRtmJavaVersion(DEFAULT_VERSION);
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
					String javaVersion = targetJar.getName().substring(13,18);
					rtcParam.setRtmJavaVersion(javaVersion);
				} else {
					rtcParam.setRtmJavaVersion(DEFAULT_VERSION);
				}
			}
		} catch (NullPointerException ex) {
			rtcParam.setRtmJavaVersion(DEFAULT_VERSION);
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
		gr = generateRunXML(contextMap);
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
		/////
		gr = generateTestCompSource(contextMap);
		result.add(gr);
		gr = generateTestRTCSource(contextMap);
		result.add(gr);
		gr = generateTestRTCImplSource(contextMap);
		result.add(gr);
		for (IdlFileParam idl : rtcParam.getConsumerIdlPathes()) {
			if(idl.isDataPort()) continue;
			contextMap.put("idlFileParam", idl);
			for (ServiceClassParam svc : idl.getTestServiceClassParams()) {
				contextMap.put("serviceClassParam", svc);
				gr = generateTestSVCSource(contextMap);
				result.add(gr);
			}
		}


		return result;
	}

	// ソースコード

	public GeneratedResult generateCompSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + "Comp.java";
		String infile = "java/Java_Comp.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateRTCSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + ".java";
		String infile = "java/Java_RTC.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateRTCImplSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "src/" + rtcParam.getName() + "Impl.java";
		String infile = "java/Java_RTC_Impl.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateSVCSource(Map<String, Object> contextMap) {
		ServiceClassParam svc = (ServiceClassParam) contextMap
				.get("serviceClassParam");
		String outfile = "src/" + TemplateHelper.getBasename(svc.getName())
				+ TemplateHelper.getServiceImplSuffix() + ".java";
		String infile = "java/Java_SVC.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
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
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateRunSh(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".sh";
		String infile = "java/run.sh.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateRunXML(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "run_" + rtcParam.getName() + ".xml";
		String infile = "java/runRTC.xml.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}
	
	// 1.0系 (ビルド環境)

	public GeneratedResult generateBuildXML(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "build_" + rtcParam.getName() + ".xml";
		String infile = "java/build.xml.vsl";
		return generate(infile, outfile, contextMap);
	}
	
	//////////
	public GeneratedResult generateTestCompSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "test/src/" + rtcParam.getName() + "TestComp.java";
		String infile = "java/test/Java_Test_Comp.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateTestRTCSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "test/src/" + rtcParam.getName() + "Test.java";
		String infile = "java/test/Java_Test_RTC.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}
	
	public GeneratedResult generateTestRTCImplSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "test/src/" + rtcParam.getName() + "TestImpl.java";
		String infile = "java/test/Java_Test_RTC_Impl.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}
	
	public GeneratedResult generateTestSVCSource(Map<String, Object> contextMap) {
		ServiceClassParam svc = (ServiceClassParam) contextMap
				.get("serviceClassParam");
		String outfile = "test/src/" + TemplateHelper.getBasename(svc.getName())
				+ TemplateHelper.getServiceImplSuffix() + ".java";
		String infile = "java/Java_SVC.java.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
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
