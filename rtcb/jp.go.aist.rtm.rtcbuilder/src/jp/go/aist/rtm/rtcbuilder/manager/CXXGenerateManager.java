package jp.go.aist.rtm.rtcbuilder.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.template.cpp.CXXConverter;

/**
 * CXXファイルの出力を制御するマネージャ
 */
public class CXXGenerateManager extends GenerateManager {

	@Override
	public String getManagerKey() {
		return IRtcBuilderConstants.LANG_CPP;
	}
	@Override
	public String getLangArgList() {
		return IRtcBuilderConstants.LANG_CPP_ARG;
	}

	/**
	 * ファイルを出力する
	 * 
	 * @param generatorParam	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {

		InputStream ins = null;
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		if( !rtcParam.isLanguageExist(IRtcBuilderConstants.LANG_CPP) ) return result;
		if( rtcParam.getName() == null) return result;

		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("rtcParam", rtcParam);
		if( rtcParam.getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			contextMap.put("cXXConv", new CXXConverter());
		} else {
			contextMap.put("cXXConv", new jp.go.aist.rtm.rtcbuilder.template._042.cpp.CXXConverter());
		}
		contextMap.put("tmpltHelper", new TemplateHelper());

		result = generateCompSource(contextMap, result);

		result = generateMakeFile(contextMap, result);
		result = generateRTCHeader(contextMap, result);
		result = generateRTCSource(contextMap, result);
		result = generateRTCExtend(contextMap, result);

		//IDLファイル内に記述されているServiceClassParamを設定する
		for( IdlFileParam idlFileParam : rtcParam.getProviderIdlPathes() ) {
			for (ServiceClassParam serviceClassParam : rtcParam.getServiceClassParams()) {
				if( idlFileParam.getIdlPath().equals(serviceClassParam.getIdlPath()) )
					idlFileParam.addServiceClassParams(serviceClassParam);
			}
		}

		//Providerに参照されているServiceClassParamを作成する
		Set<ServiceClassParam> providerRefenencedServiceClassParam = new HashSet<ServiceClassParam>();
		for( ServicePortParam servicePort : rtcParam.getServicePorts() ) {
			for( ServicePortInterfaceParam serviceInterface : servicePort.getServicePortInterfaces() ) {
				if( serviceInterface.getDirection().equals(ServicePortInterfaceParam.INTERFACE_DIRECTION_PROVIDED) ) {
					ServiceClassParam find = null;
					for (ServiceClassParam serviceClassParam : rtcParam	.getServiceClassParams()) {
						if (serviceInterface.getInterfaceType().equals(
								serviceClassParam.getName())) {
							find = serviceClassParam;
							break;
						}
					}
					if (find != null) {
						providerRefenencedServiceClassParam.add(find);
					}
				}
			}
		}
	
		for (IdlFileParam idlFileParm : rtcParam.getProviderIdlPathes()) {
			contextMap = new HashMap<String, Object>();
			contextMap.put("rtcParam", rtcParam);
			contextMap.put("idlFileParam", idlFileParm);
			contextMap.put("cXXConv", new CXXConverter());
			contextMap.put("tmpltHelper", new TemplateHelper());
			
			result = generateSVCHeader(contextMap, result);
			result = generateSVCSource(contextMap, result);
			result = generateSVCExtend(contextMap, result);
		}
	
		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * Standalone componentを生成する
	 * 
	 * @param rtcParam	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateCompSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			ins = CXXGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/_100/cpp/CXX_Comp_src.template");
		} else {
			ins = CXXGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_Comp_src.template");
		}
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + "Comp.cpp"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * Makefileを生成する
	 * 
	 * @param contextMap	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateMakeFile(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_Makefile_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Makefile_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "Makefile." + 
				((RtcParam)contextMap.get("rtcParam")).getName()));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * RTCのヘッダ・ファイルを生成する
	 * 
	 * @param contextMap	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateRTCHeader(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_100/cpp/CXX_RTC_Header_src.template";
		} else if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_042) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_RTC_Header_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_RTC_Header_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".h"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * RTCのソース・ファイルを生成する
	 * 
	 * @param rtcParam	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateRTCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_100/cpp/CXX_RTC_Source_src.template";
		} else if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_042) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_RTC_Source_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_RTC_Source_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".cpp"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	protected List<GeneratedResult> generateRTCExtend(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		if( !((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			String tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Sln_vc8.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc8.sln"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_VcProj_vc8.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc8.vcproj"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Comp_VcProj_vc8.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "Comp_vc8.vcproj"));
			//
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Sln_vc9.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc9.sln"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_VcProj_vc9.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc9.vcproj"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Comp_VcProj_vc9.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "Comp_vc9.vcproj"));
			//
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Copyprops_Bat.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "copyprops.bat"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_User_Config_Vsprops.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "user_config.vsprops"));

			try {
				if( ins != null) ins.close();
			} catch (Exception e) {
				throw new RuntimeException(e); // system error
			}
		}
		return result;		
	}
	/**
	 * Service implementation headerを生成する
	 * 
	 * @param contextMap	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateSVCHeader(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CXXGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_SVC_Header_src.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
								TemplateHelper.getBasename(
										((IdlFileParam)contextMap.get("idlFileParam")).getIdlFileNoExt())
									+ TemplateHelper.getServiceImplSuffix() + ".h"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	/**
	 * Service implementation codeを生成する
	 * 
	 * @param contextMap	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateSVCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_SVC_Source_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_SVC_Source_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
								TemplateHelper.getBasename(
										((IdlFileParam)contextMap.get("idlFileParam")).getIdlFileNoExt())
									+ TemplateHelper.getServiceImplSuffix() + ".cpp"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	protected List<GeneratedResult> generateSVCExtend(Map<String, Object> contextMap, List<GeneratedResult> result) {
		return result;		
	}
}
