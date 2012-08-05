package jp.go.aist.rtm.rtcbuilder.python.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.ui.Perspective.PythonProperty;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.*;
import static jp.go.aist.rtm.rtcbuilder.util.RTCUtil.form;

import static jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython.LANG_PYTHON;
import static jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython.LANG_PYTHON_ARG;

/**
 * Pythonファイルの出力を制御するマネージャ
 */
public class PythonGenerateManager extends GenerateManager {

	static final String TEMPLATE_PATH = "jp/go/aist/rtm/rtcbuilder/python/template";

	static final String MSG_ERROR_GENERATE_FILE = "Python generation error. [{0}]";

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
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		LanguageProperty langProp = null;
		if (rtcParam.isLanguageExist(LANG_PYTHON)) {
			langProp = new PythonProperty();
		}
		return langProp;
	}

	/**
	 * ファイルを出力する
	 * 
	 * @param generatorParam
	 * @return 出力結果のリスト
	 */
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		if (!rtcParam.isLanguageExist(LANG_PYTHON)) {
			return result;
		}

		List<IdlFileParam> allIdlFileParams = new ArrayList<IdlFileParam>();
		allIdlFileParams = new ArrayList<IdlFileParam>();
		allIdlFileParams.addAll(rtcParam.getProviderIdlPathes());
		allIdlFileParams.addAll(rtcParam.getConsumerIdlPathes());

		// IDLファイル内に記述されているServiceClassParamを設定する
		for (IdlFileParam idlFileParam : allIdlFileParams) {
			for (ServiceClassParam serviceClassParam : rtcParam
					.getServiceClassParams()) {
				if (idlFileParam.getIdlPath().equals(
						serviceClassParam.getIdlPath()))
					idlFileParam.addServiceClassParams(serviceClassParam);
			}
		}

		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("template", TEMPLATE_PATH);
		contextMap.put("rtcParam", rtcParam);
		contextMap.put("tmpltHelper", new TemplateHelper());
		contextMap.put("tmpltHelperPy", new TemplateHelperPy());
		contextMap.put("pyConv", new PythonConverter());
		contextMap.put("allIdlFileParam", allIdlFileParams);

		return generateTemplateCode10(contextMap);
	}

	// RTM 1.0系
	@SuppressWarnings("unchecked")
	public List<GeneratedResult> generateTemplateCode10(
			Map<String, Object> contextMap) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		List<IdlFileParam> allIdlFileParams = (List<IdlFileParam>) contextMap
				.get("allIdlFileParam");

		GeneratedResult gr;
		gr = generatePythonSource(contextMap);
		result.add(gr);

		if (allIdlFileParams.size() > 0) {
			gr = generateIDLCompileBat(contextMap);
			result.add(gr);
			gr = generateIDLCompileSh(contextMap);
			result.add(gr);
		}

		for (IdlFileParam idlFileParam : rtcParam.getProviderIdlPathes()) {
			contextMap.put("idlFileParam", idlFileParam);
			gr = generateSVCIDLExampleSource(contextMap);
			result.add(gr);
		}

		return result;
	}

	// 1.0系 (Python)

	public GeneratedResult generatePythonSource(Map<String, Object> contextMap) {
		RtcParam rtcParam = (RtcParam) contextMap.get("rtcParam");
		String outfile = rtcParam.getName() + ".py";
		String infile = "python/Py_RTC.py.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCIDLSource(Map<String, Object> contextMap) {
		IdlFileParam idlParam = (IdlFileParam) contextMap.get("idlFileParam");
		String outfile = TemplateHelper.getFilenameNoExt(idlParam.getIdlPath())
				+ "_idl.py";
		String infile = "python/Py_SVC_idl.py.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateSVCIDLExampleSource(
			Map<String, Object> contextMap) {
		IdlFileParam idlParam = (IdlFileParam) contextMap.get("idlFileParam");
		String outfile = idlParam.getIdlFileNoExt() + "_idl_example.py";
		String infile = "python/Py_SVC_idl_example.py.vsl";
		return generate(infile, outfile, contextMap);
	}

	// 1.0系 (ビルド環境)

	public GeneratedResult generateIDLCompileBat(Map<String, Object> contextMap) {
		String outfile = "idlcompile.bat";
		String infile = "python/idlcompile.bat.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generateIDLCompileSh(Map<String, Object> contextMap) {
		String outfile = "idlcompile.sh";
		String infile = "python/idlcompile.sh.vsl";
		return generate(infile, outfile, contextMap);
	}

	public GeneratedResult generate(String infile, String outfile,
			Map<String, Object> contextMap) {
		try {
			String template = TEMPLATE_PATH + "/" + infile;
			InputStream ins = getClass().getClassLoader().getResourceAsStream(
					template);
			GeneratedResult gr = TemplateUtil.createGeneratedResult(ins,
					contextMap, outfile);
			if (ins != null) {
				ins.close();
			}
			return gr;
		} catch (Exception e) {
			throw new RuntimeException(form(MSG_ERROR_GENERATE_FILE,
					new String[] { outfile }), e);
		}
	}

}
