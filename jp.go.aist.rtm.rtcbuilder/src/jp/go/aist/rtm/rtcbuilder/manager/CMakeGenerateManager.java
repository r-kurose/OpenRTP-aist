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

	protected static Map<RtcParam, String> WIX_PRODUCT_ID_MAP = null;
	protected static Map<RtcParam, String> WIX_UPGRADECODE_MAP = null;

	protected String WIX_PRODUCT_ID = null;
	protected String WIX_UPGRADECODE = null;

	protected String DOXYGEN_FILE_PATTERNS;

	public CMakeGenerateManager() {
		DOXYGEN_FILE_PATTERNS = "*.cpp *.h *.idl";
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

		resetWIXUUID(rtcParam);

		Map<String, Object> contextMap = createContextMap(rtcParam);

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
		if (rtcParam.getRtmVersion().equals(RTM_VERSION_042)) {
			return false;
		}
		return true;
	}

	public void resetWIXUUID(RtcParam rtcParam) {
		if (WIX_PRODUCT_ID_MAP == null) {
			WIX_PRODUCT_ID_MAP = new HashMap<RtcParam, String>();
		}
		if (WIX_UPGRADECODE_MAP == null) {
			WIX_UPGRADECODE_MAP = new HashMap<RtcParam, String>();
		}
		if (WIX_PRODUCT_ID_MAP.get(rtcParam) == null
				|| WIX_UPGRADECODE_MAP.get(rtcParam) == null) {
			if (!rtcParam.getIsTest()) {
				WIX_PRODUCT_ID_MAP.put(rtcParam, getUUID());
				WIX_UPGRADECODE_MAP.put(rtcParam, getUUID());
			} else {
				WIX_PRODUCT_ID_MAP.put(rtcParam,
						"D839647B-9EDA-4344-857D-FA5A102E5DE5");
				WIX_UPGRADECODE_MAP.put(rtcParam,
						"8BC9CEB8-8B4A-11D0-8D11-00A0C91BC942");
			}
		}
		WIX_PRODUCT_ID = WIX_PRODUCT_ID_MAP.get(rtcParam);
		WIX_UPGRADECODE = WIX_UPGRADECODE_MAP.get(rtcParam);
	}

	public Map<String, Object> createContextMap(RtcParam rtcParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("template", TEMPLATE_PATH);
		map.put("rtcParam", rtcParam);
		map.put("helper", new TemplateHelper());
		map.put("DOXYGEN_FILE_PATTERNS", DOXYGEN_FILE_PATTERNS);
		map.put("WIX_PRODUCT_ID", WIX_PRODUCT_ID);
		map.put("WIX_UPGRADECODE", WIX_UPGRADECODE);
		return map;
	}

	// RTM 1.0系
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		GeneratedResult gr;
		gr = generateCMakeLists(contextMap);
		result.add(gr);
		gr = generateDoxyfile(contextMap);
		result.add(gr);

		gr = generateModulesUninstall(contextMap);
		result.add(gr);
		gr = generateModulesCPackWIX(contextMap);
		result.add(gr);
		gr = generateModulesFindOpenRTM(contextMap);
		result.add(gr);

		gr = generateResourceDescriptionTXT(contextMap);
		result.add(gr);
		gr = generateResourceLicenseTXT(contextMap);
		result.add(gr);
		gr = generateResourceLicenseRTF(contextMap);
		result.add(gr);
		gr = generateResourceWixXSL(contextMap);
		result.add(gr);

		return result;
	}

	// 1.0系 (CMake)

	public GeneratedResult generateCMakeLists(Map<String, Object> contextMap) {
		String outfile = "CMakeLists.txt";
		String infile = "cmake/CMakeLists.txt.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateDoxyfile(Map<String, Object> contextMap) {
		String outfile = "Doxyfile.in";
		String infile = "cmake/Doxyfile.in.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/cmake_modules)

	public GeneratedResult generateModulesUninstall(
			Map<String, Object> contextMap) {
		String outfile = "cmake_modules/cmake_uninstall.cmake.in";
		String infile = "cmake/cmake_uninstall.cmake.in.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateModulesCPackWIX(
			Map<String, Object> contextMap) {
		String outfile = "cmake_modules/CPackWIX.cmake";
		String infile = "cmake/CPackWIX.cmake.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateModulesFindOpenRTM(
			Map<String, Object> contextMap) {
		String outfile = "cmake_modules/FindOpenRTM.cmake";
		String infile = "cmake/FindOpenRTM.cmake.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (CMake/cpack_resources)

	public GeneratedResult generateResourceDescriptionTXT(
			Map<String, Object> contextMap) {
		String outfile = "cpack_resources/Description.txt";
		String infile = "cmake/Description.txt.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateResourceLicenseTXT(
			Map<String, Object> contextMap) {
		String outfile = "cpack_resources/License.txt";
		String infile = "cmake/License.txt.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateResourceLicenseRTF(
			Map<String, Object> contextMap) {
		String outfile = "cpack_resources/License.rtf";
		String infile = "cmake/License.rtf.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateResourceWixXSL(Map<String, Object> contextMap) {
		String outfile = "cpack_resources/wix.xsl.in";
		String infile = "cmake/wix.xsl.in.vsl";
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
					new String[] { "CMake", outfile }), e);
		}
	}

	public String getUUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}

}
