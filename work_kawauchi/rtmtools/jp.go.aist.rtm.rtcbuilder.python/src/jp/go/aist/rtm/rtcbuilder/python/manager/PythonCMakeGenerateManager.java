package jp.go.aist.rtm.rtcbuilder.python.manager;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.CMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.form;

import static jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython.LANG_PYTHON;
import static jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython.LANG_PYTHON_ARG;

public class PythonCMakeGenerateManager extends CMakeGenerateManager {

	static final String TEMPLATE_PATH_PYTHON = "jp/go/aist/rtm/rtcbuilder/python/template";

	public PythonCMakeGenerateManager() {
		DOXYGEN_FILE_PATTERNS = "*.py *.idl";
	}

	@Override
	public String getTargetVersion() {
		return RTM_VERSION_100;
	}

	@Override
	public String getManagerKey() {
		return LANG_PYTHON;
	}

	@Override
	public String getLangArgList() {
		return LANG_PYTHON_ARG;
	}

	@Override
	public Map<String, Object> createContextMap(RtcParam rtcParam) {
		Map<String, Object> map = super.createContextMap(rtcParam);
		map.put("templatePython", TEMPLATE_PATH_PYTHON);
		return map;
	}

	// RTM 1.0系
	@Override
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = super.generateTemplateCode10(contextMap);
		return result;
	}

	// 1.0系 (CMake)

	@Override
	public GeneratedResult generateCMakeLists(Map<String, Object> contextMap) {
		String outfile = "CMakeLists.txt";
		String infile = "cmake/CMakeLists.txt.vsl";
		return generatePython(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/cmake_modules)

	@Override
	public GeneratedResult generateModulesFindOpenRTM(
			Map<String, Object> contextMap) {
		String outfile = "cmake_modules/FindOpenRTMPython.cmake";
		String infile = "cmake/FindOpenRTMPython.cmake.vsl";
		return generatePython(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/cpack_resources)

	@Override
	public GeneratedResult generateResourceWixXSL(Map<String, Object> contextMap) {
		String outfile = "cpack_resources/wix.xsl.in";
		String infile = "cmake/wix.xsl.in.vsl";
		return generatePython(infile, outfile, contextMap);
	}

	@Override
	public GeneratedResult generateResourceDescriptionTXT(Map<String, Object> contextMap) {
		String outfile = "cpack_resources/Description.txt";
		String infile = "cmake/Description.txt.vsl";
		return generatePython(infile, outfile, contextMap);
	}

	@Override
	public GeneratedResult generateResourceDescriptionTXT(Map<String, Object> contextMap) {
		String outfile = "cpack_resources/License.txt";
		String infile = "cmake/License.txt.vsl";
		return generatePython(infile, outfile, contextMap);
	}

	public GeneratedResult generatePython(String infile, String outfile,
			Map<String, Object> contextMap) {
		try {
			String template = TEMPLATE_PATH_PYTHON + "/" + infile;
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
