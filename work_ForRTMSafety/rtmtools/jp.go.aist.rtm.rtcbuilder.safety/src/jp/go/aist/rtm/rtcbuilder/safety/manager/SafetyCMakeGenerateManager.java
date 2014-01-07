package jp.go.aist.rtm.rtcbuilder.safety.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.CMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.form;

import static jp.go.aist.rtm.rtcbuilder.safety.IRtcBuilderConstantsSafety.LANG_SAFETY;
import static jp.go.aist.rtm.rtcbuilder.safety.IRtcBuilderConstantsSafety.LANG_SAFETY_ARG;

public class SafetyCMakeGenerateManager extends CMakeGenerateManager {

	static final String TEMPLATE_PATH_SAFETY = "jp/go/aist/rtm/rtcbuilder/safety/template";

	public SafetyCMakeGenerateManager() {
		DOXYGEN_FILE_PATTERNS = "*.c *.h *.idl";
	}

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
	public Map<String, Object> createContextMap(RtcParam rtcParam) {
		Map<String, Object> map = super.createContextMap(rtcParam);
		map.put("templateSafety", TEMPLATE_PATH_SAFETY);
		return map;
	}

	// RTM 1.0系
	@Override
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		//List<GeneratedResult> result = super.generateTemplateCode10(contextMap);
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		//GeneratedResult gr;
		//gr = generateModulesSafetyCompile(contextMap);
		//result.add(gr);

		return result;
	}

	// 1.0系 (CMake)

	@Override
	public GeneratedResult generateCMakeLists(Map<String, Object> contextMap) {
		String outfile = "CMakeLists.txt";
		String infile = "cmake/CMakeLists.txt.vsl";
		return generateSafety(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/cmake_modules)

	@Override
	public GeneratedResult generateModulesFindOpenRTM(
			Map<String, Object> contextMap) {
		String outfile = "cmake_modules/FindOpenRTMSafety.cmake";
		String infile = "cmake/FindOpenRTMSafety.cmake.vsl";
		return generateSafety(infile, outfile, contextMap);
	}

	public GeneratedResult generateModulesSafetyCompile(
			Map<String, Object> contextMap) {
		String outfile = "cmake_modules/cmake_safetycompile.cmake.in";
		String infile = "cmake/cmake_safetycompile.cmake.in.vsl";
		return generateSafety(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/cpack_resources)

	@Override
	public GeneratedResult generateResourceWixXSL(Map<String, Object> contextMap) {
		String outfile = "cpack_resources/wix.xsl.in";
		String infile = "cmake/wix.xsl.in.vsl";
		return generateSafety(infile, outfile, contextMap);
	}

	public GeneratedResult generateSafety(String infile, String outfile,
			Map<String, Object> contextMap) {
		try {
			String template = TEMPLATE_PATH_SAFETY + "/" + infile;
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
					new String[] { "CMake", outfile }), e);
		}
	}

}
