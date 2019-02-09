package jp.go.aist.rtm.rtcbuilder.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.*;

public class CMakeGenerateManager extends GenerateManager {

	protected static final String TEMPLATE_PATH = "jp/go/aist/rtm/rtcbuilder/template";

	protected static final String MSG_ERROR_GENERATE_FILE = IRTCBMessageConstants.ERROR_CODE_GENERATION;

	public CMakeGenerateManager() {
	}

	@Override
	public String getManagerKey() {
		return LANG_CPP;
	}

	@Override
	public String getLangArgList() {
		return LANG_CPP_ARG;
	}

	@Override
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		if (!validateRtcParam(rtcParam)) {
			return result;
		}

		Map<String, Object> contextMap = createContextMap(rtcParam);
		contextMap.put("tmpltHelper", new TemplateHelper());

		resetIDLServiceClass(rtcParam);

		return generateTemplateCode10(contextMap);
	}

	public boolean validateRtcParam(RtcParam rtcParam) {
		if (!rtcParam.isLanguageExist(getManagerKey())) {
			return false;
		}
		if (rtcParam.getName() == null) {
			return false;
		}
		return true;
	}

	public Map<String, Object> createContextMap(RtcParam rtcParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", TEMPLATE_PATH);
		map.put("rtcParam", rtcParam);
		map.put("helper", new TemplateHelper());
		return map;
	}

	// RTM 1.0系
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		GeneratedResult gr;
		//Root
		gr = generateCOPYING(contextMap);
		result.add(gr);
		gr = generateCOPYING_LESSER(contextMap);
		result.add(gr);
		gr = generateCMakeLists(contextMap);
		result.add(gr);

		//cmake
		gr = generateCmakeCMakeLists(contextMap);
		result.add(gr);
		gr = generateCmakeCPackOption(contextMap);
		result.add(gr);
		gr = generateResourceLicenseRTF(contextMap);
		result.add(gr);
		gr = generateCmakeConfigVersion(contextMap);
		result.add(gr);
		gr = generateCmakeConfig(contextMap);
		result.add(gr);
		gr = generateCmakePcIn(contextMap);
		result.add(gr);
		gr = generateModulesUninstall(contextMap);
		result.add(gr);
		gr = generateUtilIn(contextMap);
		result.add(gr);

		//doc
		gr = generateDocCMakeLists(contextMap);
		result.add(gr);
		gr = generateDocConfPy(contextMap);
		result.add(gr);
		gr = generateDoxyfile(contextMap);
		result.add(gr);

		//doc/content
		gr = generateDocIndex(contextMap);
		result.add(gr);
		gr = generateDocIndexJ(contextMap);
		result.add(gr);

		//idl
		gr = generateIdlCMakeLists(contextMap);
		result.add(gr);

		//include
		gr = generateIncludeCMakeLists(contextMap);
		result.add(gr);

		//include/Module
		gr = generateIncModuleCMakeLists(contextMap);
		result.add(gr);

		//src
		gr = generateSrcCMakeLists(contextMap);
		result.add(gr);

		return result;
	}

	// 1.0系 (CMake)
	public GeneratedResult generateCOPYING(Map<String, Object> contextMap) {
		String outfile = "COPYING";
		String infile = "cmake/COPYING.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateCOPYING_LESSER(Map<String, Object> contextMap) {
		String outfile = "COPYING.LESSER";
		String infile = "cmake/COPYING.LESSER.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateCMakeLists(Map<String, Object> contextMap) {
		String outfile = "CMakeLists.txt";
		String infile = "cmake/CMakeLists.txt.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	// 1.0系 (CMake/cmake)
	public GeneratedResult generateCmakeCMakeLists(Map<String, Object> contextMap) {
		String outfile = "cmake/CMakeLists.txt";
		String infile = "cmake/cmake/CMakeCMakeLists.txt.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateCmakeCPackOption(Map<String, Object> contextMap) {
		String outfile = "cmake/cpack_options.cmake.in";
		String infile = "cmake/cmake/cpack_options_cmake.in.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateResourceLicenseRTF(
			Map<String, Object> contextMap) {
		String outfile = "cmake/License.rtf";
		String infile = "cmake/cmake/License.rtf.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateCmakeConfigVersion(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "cmake/" + rtcParam.getName().toLowerCase() + "-config-version.cmake.in";
		String infile = "cmake/cmake/config_version.cmake.in.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateCmakeConfig(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "cmake/" + rtcParam.getName().toLowerCase() + "-config.cmake.in";
		String infile = "cmake/cmake/config.cmake.in.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateCmakePcIn(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "cmake/" + rtcParam.getName().toLowerCase() + ".pc.in";
		String infile = "cmake/cmake/pc.in.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateModulesUninstall(
			Map<String, Object> contextMap) {
		String outfile = "cmake/uninstall_target.cmake.in";
		String infile = "cmake/cmake/cmake_uninstall.cmake.in.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateUtilIn(
			Map<String, Object> contextMap) {
		String outfile = "cmake/utils.cmake";
		String infile = "cmake/cmake/utils.in.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	// 1.0系 (CMake/doc)
	public GeneratedResult generateDocCMakeLists(Map<String, Object> contextMap) {
		String outfile = "doc/CMakeLists.txt";
		String infile = "cmake/doc/DocCMakeLists.txt.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	public GeneratedResult generateDocConfPy(Map<String, Object> contextMap) {
		String outfile = "doc/conf.py.in";
		String infile = "cmake/doc/conf.py.in.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateDoxyfile(Map<String, Object> contextMap) {
		String outfile = "doc/doxyfile.in";
		String infile = "cmake/doc/Doxyfile.in.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/doc/content)
	public GeneratedResult generateDocIndex(Map<String, Object> contextMap) {
		String outfile = "doc/content/index.txt";
		String infile = "cmake/doc/index.txt.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateDocIndexJ(Map<String, Object> contextMap) {
		String outfile = "doc/content/index_j.txt";
		String infile = "cmake/doc/index_j.txt.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/idl)
	public GeneratedResult generateIdlCMakeLists(Map<String, Object> contextMap) {
		String outfile = "idl/CMakeLists.txt";
		String infile = "cmake/idl/IdlCMakeLists.txt.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	// 1.0系 (CMake/include)
	public GeneratedResult generateIncludeCMakeLists(Map<String, Object> contextMap) {
		String outfile = "include/CMakeLists.txt";
		String infile = "cmake/include/IncludeCMakeLists.txt.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	// 1.0系 (CMake/include/module)
	public GeneratedResult generateIncModuleCMakeLists(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "include/" + rtcParam.getName() + "/CMakeLists.txt";
		String infile = "cmake/include/IncModuleCMakeLists.txt.vsl";
		GeneratedResult result = generate(infile, outfile, contextMap);
		result.setNotBom(true);
		return result;
	}

	// 1.0系 (CMake/src)
	public GeneratedResult generateSrcCMakeLists(Map<String, Object> contextMap) {
		String outfile = "src/CMakeLists.txt";
		String infile = "cmake/src/SrcCMakeLists.txt.vsl";
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
					new String[] { "CMake", outfile }), e);
		}
	}

	public String getUUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}

}
