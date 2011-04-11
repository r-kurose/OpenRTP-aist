package jp.go.aist.rtm.rtcbuilder.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.*;

/**
 * CXXファイルの出力を制御するマネージャ
 */
public class CXXGenerateManager extends GenerateManager {

	static final String TEMPLATE_PATH = "jp/go/aist/rtm/rtcbuilder/template";

	static final String MSG_ERROR_GENERATE_FILE = IRTCBMessageConstants.ERROR_CODE_GENERATION;

	@Override
	public String getManagerKey() {
		return LANG_CPP;
	}

	@Override
	public String getLangArgList() {
		return LANG_CPP_ARG;
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
		if (!rtcParam.isLanguageExist(LANG_CPP)) {
			return result;
		}
		if (rtcParam.getName() == null) {
			return result;
		}

		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("template", TEMPLATE_PATH);
		contextMap.put("rtcParam", rtcParam);
		contextMap.put("cXXConv",
				new jp.go.aist.rtm.rtcbuilder.manager.CXXConverter04());
		contextMap.put("tmpltHelper", new TemplateHelper());

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
		gr = generateRTCHeader(contextMap);
		result.add(gr);
		gr = generateRTCSource(contextMap);
		result.add(gr);

		if (rtcParam.enableOldBuildEnv()) {
			// 旧バージョンのビルド環境の生成
			gr = generateMakefile(contextMap);
			result.add(gr);

			gr = generateVC9Sln(contextMap);
			result.add(gr);
			gr = generateVC8Sln(contextMap);
			result.add(gr);
			gr = generateVC9CompProj(contextMap);
			result.add(gr);
			gr = generateVC8CompProj(contextMap);
			result.add(gr);
			gr = generateVC9RTCProj(contextMap);
			result.add(gr);
			gr = generateVC8RTCProj(contextMap);
			result.add(gr);
			gr = generateVCCopyProps(contextMap);
			result.add(gr);
			gr = generateVCUserConfig(contextMap);
			result.add(gr);
		}

		for (IdlFileParam idl : rtcParam.getProviderIdlPathes()) {
			contextMap.put("idlFileParam", idl);
			gr = generateSVCHeader(contextMap);
			result.add(gr);
			gr = generateSVCSource(contextMap);
			result.add(gr);
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
		gr = generateRTCHeader_04(contextMap);
		result.add(gr);
		gr = generateRTCSource_04(contextMap);
		result.add(gr);

		gr = generateMakefile(contextMap);
		result.add(gr);

		gr = generateVC9Sln(contextMap);
		result.add(gr);
		gr = generateVC8Sln(contextMap);
		result.add(gr);
		gr = generateVC9CompProj_04(contextMap);
		result.add(gr);
		gr = generateVC8CompProj_04(contextMap);
		result.add(gr);
		gr = generateVC9RTCProj_04(contextMap);
		result.add(gr);
		gr = generateVC8RTCProj_04(contextMap);
		result.add(gr);
		gr = generateVCCopyProps(contextMap);
		result.add(gr);
		gr = generateVCUserConfig(contextMap);
		result.add(gr);

		for (IdlFileParam idl : rtcParam.getProviderIdlPathes()) {
			contextMap.put("idlFileParam", idl);
			gr = generateSVCHeader_04(contextMap);
			result.add(gr);
			gr = generateSVCSource_04(contextMap);
			result.add(gr);
		}

		return result;
	}

	// 1.0系 (C++)

	public GeneratedResult generateCompSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp.cpp";
		String infile = "cpp/CXX_Comp.cpp.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCHeader(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".h";
		String infile = "cpp/CXX_RTC.h.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".cpp";
		String infile = "cpp/CXX_RTC.cpp.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCHeader(Map<String, Object> contextMap) {
		IdlFileParam idlParam = (IdlFileParam) contextMap.get("idlFileParam");
		String outfile = TemplateHelper.getBasename(idlParam.getIdlFileNoExt())
				+ TemplateHelper.getServiceImplSuffix() + ".h";
		String infile = "cpp/CXX_SVC.h.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCSource(Map<String, Object> contextMap) {
		IdlFileParam idlParam = (IdlFileParam) contextMap.get("idlFileParam");
		String outfile = TemplateHelper.getBasename(idlParam.getIdlFileNoExt())
				+ TemplateHelper.getServiceImplSuffix() + ".cpp";
		String infile = "cpp/CXX_SVC.cpp.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 0.4系 (C++)

	public GeneratedResult generateCompSource_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp.cpp";
		String infile = "cpp_04/CXX_Comp.cpp.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCHeader_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".h";
		String infile = "cpp_04/CXX_RTC.h.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateRTCSource_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".cpp";
		String infile = "cpp_04/CXX_RTC.cpp.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCHeader_04(Map<String, Object> contextMap) {
		IdlFileParam idlParam = (IdlFileParam) contextMap.get("idlFileParam");
		String outfile = TemplateHelper.getBasename(idlParam.getIdlFileNoExt())
				+ TemplateHelper.getServiceImplSuffix() + ".h";
		String infile = "cpp_04/CXX_SVC.h.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCSource_04(Map<String, Object> contextMap) {
		IdlFileParam idlParam = (IdlFileParam) contextMap.get("idlFileParam");
		String outfile = TemplateHelper.getBasename(idlParam.getIdlFileNoExt())
				+ TemplateHelper.getServiceImplSuffix() + ".cpp";
		String infile = "cpp_04/CXX_SVC.cpp.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (ビルド環境)

	public GeneratedResult generateMakefile(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = "Makefile." + rtcParam.getName();
		String infile = "cpp/Makefile.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC9Sln(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "_vc9.sln";
		String infile = "cpp/CXX_vc9.sln.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC8Sln(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "_vc8.sln";
		String infile = "cpp/CXX_vc8.sln.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC8CompProj(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp_vc8.vcproj";
		String infile = "cpp/CXX_Comp_vc8.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC9CompProj(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp_vc9.vcproj";
		String infile = "cpp/CXX_Comp_vc9.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC8RTCProj(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "_vc8.vcproj";
		String infile = "cpp/CXX_vc8.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC9RTCProj(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "_vc9.vcproj";
		String infile = "cpp/CXX_vc9.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVCCopyProps(Map<String, Object> contextMap) {
		String outfile = "copyprops.bat";
		String infile = "cpp/copyprops.bat.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVCUserConfig(Map<String, Object> contextMap) {
		String outfile = "user_config.vsprops";
		String infile = "cpp/user_config.vsprops.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 0.4系 (ビルド環境)

	public GeneratedResult generateVC8CompProj_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp_vc8.vcproj";
		String infile = "cpp_04/CXX_Comp_vc8.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC9CompProj_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "Comp_vc9.vcproj";
		String infile = "cpp_04/CXX_Comp_vc9.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC8RTCProj_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "_vc8.vcproj";
		String infile = "cpp_04/CXX_vc8.vcproj.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateVC9RTCProj_04(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + "_vc9.vcproj";
		String infile = "cpp_04/CXX_vc9.vcproj.vsl";
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
					new String[] { "C++", outfile }), e);
		}
	}

}
