package jp.go.aist.rtm.rtcbuilder.csharp.manager;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.csharp.IRtcBuilderConstantsCSharp;
import jp.go.aist.rtm.rtcbuilder.csharp.template.CSharpConverter;
import jp.go.aist.rtm.rtcbuilder.csharp.template.TemplateHelperCs;
import jp.go.aist.rtm.rtcbuilder.csharp.ui.Perspective.CSharpProperty;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

/**
 * C#ファイルの出力を制御するマネージャ
 */
public class CSharpGenerateManager extends GenerateManager {

	@Override
	public String getManagerKey() {
		return IRtcBuilderConstantsCSharp.LANG_CSHARP;
	}
	@Override
	public String getLangArgList() {
		return IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG;
	}

	@Override
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		LanguageProperty langProp = null;
		if(rtcParam.isLanguageExist(IRtcBuilderConstantsCSharp.LANG_CSHARP) ) {
			langProp = new CSharpProperty();
		}
		return langProp;
	}

	/**
	 * ファイルを出力する
	 * 
	 * @param generatorParam	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	@Override
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {

		InputStream ins = null;
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		if (rtcParam.isLanguageExist(IRtcBuilderConstantsCSharp.LANG_CSHARP) && rtcParam.getName() != null) {
			Map<String, Object> contextMap = new HashMap<String, Object>();
			contextMap.put("rtcParam", rtcParam);
			contextMap.put("tmpltHelper", new TemplateHelper());
			contextMap.put("tmpltHelperCs", new TemplateHelperCs());
			contextMap.put("csConv", new CSharpConverter());

			result = generateRTCSource(contextMap, result);
			result = generateAppConfig(contextMap, result);
			result = generateProject(contextMap, result);
			result = generateUserProject(contextMap, result);
			result = generateGenFile(contextMap, result);
			result = generateProgramFile(contextMap, result);
			result = generateAssemblyInfoFile(contextMap, result, rtcParam.getOutputProject());

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
						for (ServiceClassParam serviceClassParam : rtcParam.getServiceClassParams()) {
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
				contextMap.put("tmpltHelper", new TemplateHelper());
				contextMap.put("tmpltHelperCs", new TemplateHelperCs());
				contextMap.put("csConv", new CSharpConverter());
				
				result = generateSVCSource(contextMap, result);
				result = generateSVCExtend(contextMap, result);
			}
	
			try {
				if( ins != null) ins.close();
			} catch (Exception e) {
				throw new RuntimeException(e); // system error
			}
		}

		return result;
	}
	
	/**
	 * RTCのソース・ファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateRTCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/CSharp_RTC_Source.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".cs"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * App.configファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateAppConfig(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/App_Config.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "App.config"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * プロジェクトファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateProject(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/CSharp_Project.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".csproj"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * Userプロジェクトファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateUserProject(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/CSharp_Project_User.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".csproj.user"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * Genファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateGenFile(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/CSharp_Gen.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "gen.xml"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	/**
	 * Programファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateProgramFile(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/CSharp_Program.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "Program.cs"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	/**
	 * AssemblyInfoファイルを生成する
	 * 
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateAssemblyInfoFile(Map<String, Object> contextMap, List<GeneratedResult> result, String outDir) {
		InputStream ins = null;

		ins = CSharpGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/CSharp_Assemble_Info.template");
		File targetDirectory = new File(outDir + File.separator + "Properties");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, File.separator + "Properties" + File.separator + "AssemblyInfo.cs"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	/**
	 * RTCImplのソース・ファイルを生成する
	 * 
	 * @param rtcParam	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateSVCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		for(ServiceClassParam serviceClass : ((IdlFileParam)contextMap.get("idlFileParam")).getServiceClassParams()) {
			ins = CSharpGenerateManager.class.getClassLoader()	
					.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/csharp/template/Csharp_SVC_Source.template");
			contextMap.put("serviceClassParam", serviceClass);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
									TemplateHelper.getBasename(serviceClass.getName()) + "Impl.cs"));
	
			try {
				if( ins != null) ins.close();
			} catch (Exception e) {
				throw new RuntimeException(e); // system error
			}
		}

		return result;
	}
	protected List<GeneratedResult> generateSVCExtend(Map contextMap, List<GeneratedResult> result) {
		return result;		
	}
}
