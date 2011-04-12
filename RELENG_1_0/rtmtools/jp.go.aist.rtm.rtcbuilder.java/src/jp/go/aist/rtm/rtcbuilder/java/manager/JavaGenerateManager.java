package jp.go.aist.rtm.rtcbuilder.java.manager;

import java.io.File;
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
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java.template.JavaConverter;
import jp.go.aist.rtm.rtcbuilder.java.template.TemplateHelperJava;
import jp.go.aist.rtm.rtcbuilder.java.ui.Perspective.JavaProperty;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

/**
 * Javaファイルの出力を制御するマネージャ
 */
public class JavaGenerateManager extends GenerateManager {

	@Override
	public String getTargetVersion() {
		return IRtcBuilderConstants.RTM_VERSION_100;
	}
	@Override
	public String getManagerKey() {
		return IRtcBuilderConstantsJava.LANG_JAVA;
	}
	@Override
	public String getLangArgList() {
		return IRtcBuilderConstantsJava.LANG_JAVA_ARG;
	}

	@Override
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		LanguageProperty langProp = null;
		if(rtcParam.isLanguageExist(IRtcBuilderConstantsJava.LANG_JAVA) ) {
			langProp = new JavaProperty();
		}
		return langProp;
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

		if (rtcParam.isLanguageExist(IRtcBuilderConstantsJava.LANG_JAVA) && rtcParam.getName() != null) {
			Map<String, Object> contextMap = new HashMap<String, Object>();
			contextMap.put("rtcParam", rtcParam);
			contextMap.put("tmpltHelper", new TemplateHelper());
			contextMap.put("tmpltHelperJava", new TemplateHelperJava());
			contextMap.put("javaConv", new JavaConverter());
			//
			String defaultPath = System.getenv("RTM_ROOT");
			if( defaultPath!=null ) { 
				contextMap.put("javaRoot", defaultPath);
			}
			//

			result = generateCompSource(contextMap, result);

			result = generateBuildFile(contextMap, result);
			result = generateRTCSource(contextMap, result);
			result = generateRTCImplSource(contextMap, result);
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
				contextMap.put("tmpltHelperJava", new TemplateHelperJava());
				contextMap.put("javaConv", new JavaConverter());
				
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
	 * Standalone componentを生成する
	 * 
	 * @param rtcParam	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateCompSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = JavaGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/Java_Comp_src.template");
		
		String outDir = ((RtcParam)contextMap.get("rtcParam")).getOutputProject();
		File targetDirectory = new File(outDir + File.separator + "src");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		targetDirectory = new File(outDir + File.separator + "bin");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		
		String outFile; 
		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			outFile = File.separator + "src" + File.separator + ((RtcParam)contextMap.get("rtcParam")).getName() + "Comp.java";
		} else {
			outFile = ((RtcParam)contextMap.get("rtcParam")).getName() + "Comp.java";
		}
		
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, outFile));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * build.xmlを生成する
	 * 
	 * @param contextMap	生成用パラメータ
	 * @param result	生成結果格納先
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateBuildFile(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			ins = JavaGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/_100/Java_Build_src.template");
		} else {
			ins = JavaGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/Java_Build_src.template");
		}
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				"build_" + ((RtcParam)contextMap.get("rtcParam")).getName() +".xml"));

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
	 * @param result	生成用パラメータ
	 * @return 出力結果のリスト
	 */
	protected List<GeneratedResult> generateRTCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String outFile;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			ins = JavaGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/_100/Java_RTC_Source_src.template");
			outFile = File.separator + "src" + File.separator + ((RtcParam)contextMap.get("rtcParam")).getName() + ".java";
		} else {
			ins = JavaGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/Java_RTC_Source_src.template");
			outFile = ((RtcParam)contextMap.get("rtcParam")).getName() + ".java";
		}
		
		String outDir = ((RtcParam)contextMap.get("rtcParam")).getOutputProject();
		File targetDirectory = new File(outDir + File.separator + "src");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		targetDirectory = new File(outDir + File.separator + "bin");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, outFile));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	protected List<GeneratedResult> generateRTCExtend(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = JavaGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/Java_ClassPath_src.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ".classpath"));

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
	protected List<GeneratedResult> generateRTCImplSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String outFile;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			ins = JavaGenerateManager.class.getClassLoader()
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/_100/Java_RTC_Impl_Source_src.template");
			outFile = File.separator + "src" + File.separator + ((RtcParam)contextMap.get("rtcParam")).getName() + "Impl.java";
		} else {
			ins = JavaGenerateManager.class.getClassLoader()
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/Java_RTC_Impl_Source_src.template");
			outFile = ((RtcParam)contextMap.get("rtcParam")).getName() + "Impl.java";
		}
		
		String outDir = ((RtcParam)contextMap.get("rtcParam")).getOutputProject();
		File targetDirectory = new File(outDir + File.separator + "src");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		targetDirectory = new File(outDir + File.separator + "bin");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, outFile));

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
		String outFile;

		String outDir = ((RtcParam)contextMap.get("rtcParam")).getOutputProject();
		File targetDirectory = new File(outDir + File.separator + "src");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		targetDirectory = new File(outDir + File.separator + "bin");
		if( !targetDirectory.isDirectory() ) {
			targetDirectory.mkdir();
        }
		
		for(ServiceClassParam serviceClass : ((IdlFileParam)contextMap.get("idlFileParam")).getServiceClassParams()) {
			ins = JavaGenerateManager.class.getClassLoader()	
					.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/java/template/Java_SVC_Source_src.template");
			contextMap.put("serviceClassParam", serviceClass);
			
			if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
				outFile = File.separator + "src" + File.separator + TemplateHelper.getBasename(serviceClass.getName())
							+ TemplateHelper.getServiceImplSuffix() + ".java";
			} else {
				outFile = TemplateHelper.getBasename(serviceClass.getName()) + TemplateHelper.getServiceImplSuffix() + ".java";
			}
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, outFile));
	
			try {
				if( ins != null) ins.close();
			} catch (Exception e) {
				throw new RuntimeException(e); // system error
			}
		}

		return result;
	}
	@SuppressWarnings("unchecked")
	protected List<GeneratedResult> generateSVCExtend(Map contextMap, List<GeneratedResult> result) {
		return result;		
	}
}
