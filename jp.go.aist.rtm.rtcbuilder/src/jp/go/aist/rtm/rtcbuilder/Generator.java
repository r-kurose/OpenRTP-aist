package jp.go.aist.rtm.rtcbuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.ParseException;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;
import jp.go.aist.rtm.rtcbuilder.generator.parser.MergeBlockParser;
import jp.go.aist.rtm.rtcbuilder.manager.CMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.CXXGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.CommonGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.editors.IMessageConstants;
import jp.go.aist.rtm.rtcbuilder.ui.preference.DataTypePreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;
import jp.go.aist.rtm.rtcbuilder.util.ValidationUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.IDialogConstants;

/**
 * ジェネレータクラス
 */
public class Generator {
	
	Map<String, GenerateManager> generateManagerList = new HashMap<String, GenerateManager>();

	public Generator() {
		this.addGenerateManager(new CommonGenerateManager());
		this.addGenerateManager(new CXXGenerateManager());
		this.addGenerateManager(new CMakeGenerateManager());
	}

	/**
	 * ジェネレート・マネージャを追加する
	 * 
	 * @param genManager
	 *            　生成対象のジェネレート・マネージャ
	 */
	public void addGenerateManager(GenerateManager genManager) {
		String key = genManager.getClass().getName();
		generateManagerList.put(key, genManager);
	}

	/**
	 * ジェネレート・マネージャをクリアする
	 */
	public void clearGenerateManager() {
		generateManagerList.clear();
	}

	public List<GeneratedResult> generateTemplateCode(
			GeneratorParam generatorParam) throws Exception {
		return generateTemplateCode(generatorParam, null, true);
	}

	/**
	 * ジェネレートする
	 * 
	 * @param generatorParam
	 *            パラメータ
	 * @return GeneratedResultのリスト
	 * @throws ParseException
	 *             IDLのパースに失敗した場合など
	 */
	public List<GeneratedResult> generateTemplateCode(
			GeneratorParam generatorParam, List<String> idlDir, boolean validateFlag)
			throws Exception {

		if( validateFlag ) {
			for( RtcParam rtcParam : generatorParam.getRtcParams() ) {
				validate(rtcParam);
			}
		}

		List<ServiceClassParam> rtcServiceClasses = new ArrayList<ServiceClassParam>();
		//IDL重複チェック用
		List<String> IDLPathes = new ArrayList<String>();
		//IDL読み込み用
		List<ServiceClassParam> IDLPathParams = new ArrayList<ServiceClassParam>();
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		for( RtcParam rtcParam : generatorParam.getRtcParams() ) {
			//onImplementedフラグの修正
			for(int index=IRtcBuilderConstants.ACTIVITY_INITIALIZE;index<IRtcBuilderConstants.ACTIVITY_DUMMY;index++) {
				if(rtcParam.getDetailContent(index)!=null && 0<rtcParam.getDetailContent(index).length()) {
					rtcParam.setActionImplemented(index, true);
				}
			}
			rtcParam.checkAndSetParameter();
			rtcParam.getIdlPathes().clear();
			for( ServicePortParam serviceport : rtcParam.getServicePorts() ) {
				for( ServicePortInterfaceParam serviceInterfaces : serviceport.getServicePortInterfaces() ) {
					if( !IDLPathes.contains(serviceInterfaces.getIdlFullPath()) ) {
						IDLPathes.add(serviceInterfaces.getIdlFullPath());
						IDLPathParams.add(new ServiceClassParam(serviceInterfaces.getIdlFullPath(),
																 serviceInterfaces.getIdlSearchPath()));
					}
					if( 0<serviceInterfaces.getIdlSearchPath().length() &&
							rtcParam.getIdlPathes().contains(serviceInterfaces.getIdlSearchPath())==false) {
						rtcParam.getIdlPathes().add(serviceInterfaces.getIdlSearchPath());
					}
				}
			}
			rtcParam.getIdlPathes().addAll(DataTypePreferenceManager.getInstance().getIdlFileDirectories());
			if(idlDir!=null) {
				rtcParam.getIdlPathes().addAll(idlDir);
			}
			
			rtcServiceClasses.addAll(getRtcServiceClass(rtcParam, IDLPathParams));
			checkReferencedServiceParam(rtcServiceClasses, rtcParam);
			List<ServiceClassParam> serviceClassParamList = new ArrayList<ServiceClassParam>();
			List<String> serviceClassNameList = new ArrayList<String>();
			for( ServiceClassParam serviceClassParam : rtcServiceClasses ) {
				if( !serviceClassNameList.contains(serviceClassParam.getName()) ) {
					serviceClassNameList.add(serviceClassParam.getName());
					serviceClassParamList.add(serviceClassParam);
				}
			}
			rtcParam.getServiceClassParams().clear();
	
			for( ServiceClassParam param : serviceClassParamList ) {
				param.setParent(rtcParam);
				rtcParam.getServiceClassParams().add(param);
			}
			List<GeneratedResult> resultEach = new ArrayList<GeneratedResult>();
			for (String key : generateManagerList.keySet()) {
				GenerateManager manager = generateManagerList.get(key);
				if (!"Common".equals(manager.getManagerKey())
						&& !rtcParam.getLangList().contains(
								manager.getManagerKey())) {
					continue;
				}
				resultEach.addAll(manager.generateTemplateCode(rtcParam));
			}
			result.addAll(resultEach);
		}

		return result;
	}

	/**
	 * バリデートを行う
	 * 
	 * @param generatorParam
	 */
	private void validate(RtcParam rtcParam) {

		if( rtcParam.getOutputProject() == null ) {
			throw new RuntimeException(IRTCBMessageConstants.VALIDATE_ERROR_OUTPUTPROJECT);
		}
		/////Module
		//Name
		if( rtcParam.getName() == null || rtcParam.getName().length()==0 ) {
			throw new RuntimeException(IRTCBMessageConstants.VALIDATE_ERROR_COMPONENTNAME);
		}
		if( !StringUtil.checkDigitAlphabet(rtcParam.getName()) ) {
			throw new RuntimeException(IMessageConstants.BASIC_VALIDATE_NAME2);
		}
		//Category
		if( rtcParam.getCategory()==null || rtcParam.getCategory().length() == 0) {
			throw new RuntimeException(IMessageConstants.BASIC_VALIDATE_CATEGORY);
		}
		/////DataPort
		List<String> portNames = new ArrayList<String>();
		for( DataPortParam inport : rtcParam.getInports() ) {
			String result = ValidationUtil.validateDataPort(inport);
			if( result!=null ) 	throw new RuntimeException(result + " : " + rtcParam.getName());
			if (portNames.contains(inport.getName()))
				throw new RuntimeException(IRTCBMessageConstants.VALIDATE_ERROR_PORTSAMENAME + rtcParam.getName());
			portNames.add(inport.getName());
		}
		for (DataPortParam outport : rtcParam.getOutports()) {
			String result = ValidationUtil.validateDataPort(outport);
			if( result!=null ) 	throw new RuntimeException(result + " : " + rtcParam.getName());
			if( portNames.contains(outport.getName()) )
				throw new RuntimeException(IRTCBMessageConstants.VALIDATE_ERROR_PORTSAMENAME + rtcParam.getName());
			portNames.add(outport.getName());
		}
		/////Service Port
		List<String> servicePortNames = new ArrayList<String>();
		for( ServicePortParam servicePort : rtcParam.getServicePorts() ) {
			String result = ValidationUtil.validateServicePort(servicePort);
			if( result!=null ) 	throw new RuntimeException(result + " : " + rtcParam.getName());
			if( servicePortNames.contains(servicePort.getName()) )
				throw new RuntimeException(IRTCBMessageConstants.VALIDATE_ERROR_INTERFACESAMENAME + rtcParam.getName());
			servicePortNames.add(servicePort.getName());
		}
		/////Service Interface
		List<String> serviceInterfaceNames = new ArrayList<String>();
		for( ServicePortParam servicePort : rtcParam.getServicePorts() ) {
			for( ServicePortInterfaceParam serviceInterface : servicePort.getServicePortInterfaces() ) {
				String result = ValidationUtil.validateServiceInterface(serviceInterface);
				if( result!=null ) 	throw new RuntimeException(result + " : " + rtcParam.getName());
				if (serviceInterfaceNames.contains(serviceInterface.getTmplVarName()))
					throw new RuntimeException(IRTCBMessageConstants.VALIDATE_ERROR_INTERFACESAMENAME + rtcParam.getName());
				serviceInterfaceNames.add(serviceInterface.getTmplVarName());
			}
		}
		/////ConfigurationSet
		List<String> configNames = new ArrayList<String>();
		for( ConfigSetParam config : rtcParam.getConfigParams() ) {
			String result = ValidationUtil.validateConfigurationSet(config);
			if( result!=null ) 	throw new RuntimeException(result + " : " + rtcParam.getName());
			if (configNames.contains(config.getName()))
				throw new RuntimeException(IMessageConstants.CONFIGURATION_VALIDATE_DUPLICATE + rtcParam.getName());
			configNames.add(config.getName());
		}
	}

	/**
	 * 参照されているServiceが存在するか確認する
	 * 
	 * @param rtcServiceClasses
	 * @param generatorParam
	 * @return
	 */
	private void checkReferencedServiceParam(List<ServiceClassParam> rtcServiceClasses, RtcParam param) {

		List<String> serviceTypes = new ArrayList<String>();
		for( ServicePortParam serviceport : param.getServicePorts() ) {
			for( ServicePortInterfaceParam serviceInterfaces : serviceport.getServicePortInterfaces() ) {
				if( !serviceTypes.contains(serviceInterfaces.getInterfaceType()) )
					serviceTypes.add(serviceInterfaces.getInterfaceType());
			}
		}

		for( String serviceType : serviceTypes ) {
			ServiceClassParam find = null;
			for( ServiceClassParam serviceClassParam : rtcServiceClasses ) {
				if( serviceType.equals(serviceClassParam.getName()) ) {
					find = serviceClassParam;
					break;
				}
			}
			if( find == null )
				throw new RuntimeException("'" + serviceType + "' is not found in IDL");
		}
	}

	/**
	 * サービスクラス,型定義を取得する
	 * 
	 * @param generatorParam
	 * @param IDLPathes
	 * @return
	 * @throws ParseException
	 */
	private List<ServiceClassParam> getRtcServiceClass(RtcParam rtcParam,
			List<ServiceClassParam> IDLPathes) throws ParseException {
		List<ServiceClassParam> result = new ArrayList<ServiceClassParam>();
		List<String> includeFiles = new ArrayList<String>();

		for (int intIdx = 0; intIdx < IDLPathes.size(); intIdx++) {
			ServiceClassParam sv = IDLPathes.get(intIdx);
			if (sv == null) continue;
			List<String> incs = new ArrayList<String>();
			String idl = null;
			try {
				String fileName = sv.getName();
				File file = new File(fileName);
				if(file.exists()==false) {
					for(String path : rtcParam.getIdlPathes()) {
						String fullName = path + File.separator + fileName; 
						file = new File(fullName);
						if(file.exists()) {
							for( IdlFileParam idlFile : rtcParam.getProviderIdlPathes() ) {
								if( idlFile.getIdlFile().equals(fileName) ) {
									idlFile.setIdlPath(fullName);
								}
							}
							for( IdlFileParam idlFile : rtcParam.getConsumerIdlPathes() ) {
								if( idlFile.getIdlFile().equals(fileName) ) {
									idlFile.setIdlPath(fullName);
								}
							}
							fileName = fullName;
							break;
						}
					}
				}
				String idlContent = FileUtil.readFile(fileName);
				if (idlContent == null) continue;
				idl = PreProcessor.parse(idlContent, getIncludeIDLDic(sv.getIdlPath()), incs);
			} catch (IOException e) {
				continue;
			}
			IDLParser parser = new IDLParser(new StringReader(idl));

			specification spec = parser.specification();

			List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(spec, sv.getName());
			List<TypeDefParam> typedefParams = IDLParamConverter.convert_typedef(spec, sv.getName());
			if (typedefParams.size() > 0) {
				serviceClassParams = convertType(serviceClassParams, typedefParams);
			}
			for (ServiceClassParam scp : serviceClassParams) {
				scp.setTypeDef(typedefParams);
			}
			result.addAll(serviceClassParams);
			//
			for (IdlFileParam p : rtcParam.getProviderIdlPathes()) {
				if (sv.getName().trim().equals(p.getIdlPath().trim())) {
					for (String s : incs) {
						if (!p.getIncludeIdlPathes().contains(s)) {
							p.getIncludeIdlPathes().add(s);
						}
					}
				}
			}
			for (IdlFileParam p : rtcParam.getConsumerIdlPathes()) {
				if (sv.getName().trim().equals(p.getIdlPath().trim())) {
					for (String s : incs) {
						if (!p.getIncludeIdlPathes().contains(s)) {
							p.getIncludeIdlPathes().add(s);
						}
					}
				}
			}
			//
			for (String s : incs) {
				if (!includeFiles.contains(s)) {
					includeFiles.add(s);
				}
			}
		}
		//
		for(String target : includeFiles) {
			if( !rtcParam.getIncludedIdls().contains(target) ) {
				rtcParam.getIncludedIdls().add(target);
			}
		}

		return result;
	}
	
	private List<ServiceClassParam> convertType(List<ServiceClassParam> source, List<TypeDefParam> types) {
		
		for(int idxParent=0;idxParent<types.size();idxParent++) {
			TypeDefParam cur = types.get(idxParent);
			String target = types.get(idxParent).getOriginalDef();
			//// for struct...
			if (cur.isStruct()){
			   for(String chld : cur.getChildType()){
				    TypeDefParam tdp2 = findTypeDefParam(chld, types);
				    if(tdp2 != null){

							if(!cur.isSequence()){ cur.setSequence(tdp2.isSequence()); }
							if(!cur.isUnbounded()) { 
						 	 	cur.setUnbounded( isUnboundedTypeDef(chld, types));

							}
					  }
				 }
			}
			/// for typedef
			if (cur.isAlias()){
				TypeDefParam tdp = findTypeDefParam(target , types) ;
				if(tdp != null){
					////  Copy Attributs
					if(!cur.isSequence()){ cur.setSequence(tdp.isSequence()); }
					if(!cur.isUnbounded()) { 
						 cur.setUnbounded( isUnboundedTypeDef(cur.getTargetDef(), types));
					}
					cur.setStruct(tdp.isStruct());
					cur.setInterface(tdp.isInterface());
					cur.setOriginalDef(tdp.getTargetDef());
				}

			}
		}
		
		for( ServiceClassParam target : source) {
			for( ServiceMethodParam method : target.getMethods() ) {
				checkMethodType(method, types);
				for( ServiceArgumentParam param : method.getArguments() ) {
					checkArgumentType(param, types);
				}
			}
		}
		return source;
	}

	private TypeDefParam findTypeDefParam(String name, List<TypeDefParam> types) {
		for(int index=0;index<types.size();index++) {
			TypeDefParam tdp = types.get(index);
			if( name.equals(tdp.getTargetDef()) ) {
					return tdp;
			}
		}
		return null;
	}

	private boolean isUnboundedTypeDef(String name, List<TypeDefParam> types) {
		  TypeDefParam cur = findTypeDefParam(name, types);

		  if(cur != null){
				if(cur.isUnbounded() || cur.isSequence()) { 
					return true;

				}else{
		  			if (cur.isAlias()){
						return  isUnboundedTypeDef(cur.getOriginalDef(), types);

					}
		  			if (cur.isStruct()){
			   			for(String chld : cur.getChildType()){
							if( isUnboundedTypeDef(chld, types) ){ return true; }
						}
				 	}
				}
			}
			return false;
	}

	private void checkMethodType(ServiceMethodParam target, List<TypeDefParam> types) {
		String targetFull = target.getModule() + target.getType();
		//
		for(TypeDefParam tdparam : types) {
			String defFull = "";
			if( 0<tdparam.getModuleName().length() ) {
				defFull = tdparam.getModuleName() + "::" + tdparam.getTargetDef();
			} else {
				defFull = tdparam.getTargetDef();
			}
			if(targetFull.equals(defFull)) {
				target.setSequence(tdparam.isSequence());
				target.setArray(tdparam.isArray());
				target.setArrayDim(tdparam.getArrayDim());
				target.setStruct(tdparam.isStruct());
				target.setOriginalType(tdparam.getOriginalDef());
				target.setUnbounded(tdparam.isUnbounded());
				target.setAlias(tdparam.isAlias());
				target.setInterface(tdparam.isInterface());
				break;
			}
		}
		target.setType(checkType(target.getType(), types));
	}
	private void checkArgumentType(ServiceArgumentParam target, List<TypeDefParam> types) {
		String targetFull = target.getModule() + target.getType();
		for(TypeDefParam tdparam : types) {
			String defFull = "";
			if( 0<tdparam.getModuleName().length() ) {
				defFull = tdparam.getModuleName() + "::" + tdparam.getTargetDef();
			} else {
				defFull = tdparam.getTargetDef();
			}
			if(targetFull.equals(defFull)) {
				target.setOriginalType(tdparam.getOriginalDef());
				target.setUnbounded(tdparam.isSequence() || tdparam.isUnbounded());
				target.setArray(tdparam.isArray());
				target.setArrayDim(tdparam.getArrayDim());
				target.setStruct(tdparam.isStruct());
				target.setInterface(tdparam.isInterface());
				target.setAlias(tdparam.isAlias());
				target.setSequence(tdparam.isSequence());
				target.setType(checkType(target.getType(), types));
				return;
			}
		}
	}
	private String checkType(String target, List<TypeDefParam> types) {
		for(TypeDefParam tdparam : types) {
			if(target.equals(tdparam.getTargetDef())) {
				if(!tdparam.getScopedName().equals("")) {
					if(tdparam.isString()) return tdparam.getScopedName() + "::" + tdparam.getOriginalDef();
					return tdparam.getScopedName() + "::" + target;
				}
				if( tdparam.isString() && !tdparam.isStruct() ) return tdparam.getOriginalDef();
			}
		}
		return target;
	}

	private File getIncludeIDLDic(String targetDir) {
		File result = null;
		if( targetDir!=null && targetDir.length()>0 ) {
			File file = new File(targetDir);
			if (file.exists()) {
				result = file;
			} else {
				throw new RuntimeException(IRTCBMessageConstants.ERROR_IDL_DIRECTORY_NOT_FOUND);
			}
		}
		return result;
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private void writeFile(List<GeneratedResult> generatedResultList,
			RtcParam rtcParam, MergeHandler handler) throws IOException, CoreException {

		IWorkspaceRoot workspaceHandle = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workspaceHandle.getProject(rtcParam.getOutputProject());
		if(!project.exists()) {
			return;
		}
		
		for (GeneratedResult generatedResult : generatedResultList) {
			if (generatedResult.getName().equals("") == false) {
				writeFile(generatedResult, project, handler);
			}
		}
		for( IdlFileParam idlFile : rtcParam.getProviderIdlPathes() ) {
			IFile idlTarget;
			if(rtcParam.getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) && rtcParam.enableOldBuildEnv()==false) {
				idlTarget = project.getFile("idl" + File.separator + idlFile.getIdlFile());
			} else {
				idlTarget = project.getFile(idlFile.getIdlFile());
			}
			if( !idlTarget.getLocation().toOSString().equals(idlFile.getIdlPath()) )  {
				idlTarget.delete(true, null);
				idlTarget.create(new FileInputStream(idlFile.getIdlPath()), true, null);
			}
		}
		for( IdlFileParam idlFile : rtcParam.getConsumerIdlPathes() ) {
			IFile idlTarget;
			if(rtcParam.getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) && rtcParam.enableOldBuildEnv()==false) {
				idlTarget = project.getFile("idl" + File.separator + idlFile.getIdlFile());
			} else {
				idlTarget = project.getFile(idlFile.getIdlFile());
			}
			if( !idlTarget.getLocation().toOSString().equals(idlFile.getIdlPath()) )  {
				idlTarget.delete(true, null);
				idlTarget.create(new FileInputStream(idlFile.getIdlPath()), true, null);
			}
		}
		//
		for( String includedIdlFile : rtcParam.getIncludedIdls() ) {
			File target = new File(includedIdlFile);
			IFile idlTarget;
			if(rtcParam.getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) && rtcParam.enableOldBuildEnv()==false) {
				idlTarget = project.getFile("idl" + File.separator + target.getName());
			} else {
				idlTarget = project.getFile(target.getName());
			}
			if( !idlTarget.getLocation().toOSString().equals(includedIdlFile) )  {
				idlTarget.delete(true, null);
				idlTarget.create(new FileInputStream(includedIdlFile), true, null);
			}
		}
	}

	private void writeFile(GeneratedResult generatedResult, IProject outputProject,
			MergeHandler handler) throws IOException {
		
		File targetFile = new File(outputProject.getLocation().toOSString(), generatedResult.getName());

		boolean isOutput = false;
		if (targetFile.exists()) {
			String originalFileContents = FileUtil.readFile(targetFile.getAbsolutePath());
			if (originalFileContents.equals(generatedResult.getCode()) == false) {
				int selectedProcess = handler.getSelectedProcess(generatedResult, originalFileContents);
				if (selectedProcess != MergeHandler.PROCESS_ORIGINAL_ID
						&& selectedProcess != IDialogConstants.CANCEL_ID) {
					IResource originalFile = outputProject.findMember(generatedResult.getName());
					IFile renameFile = outputProject.getFile(generatedResult.getName() + DATE_FORMAT.format(new GregorianCalendar().getTime()) );
					try {
						originalFile.move(renameFile.getFullPath(), true, null);

						if (selectedProcess == MergeHandler.PROCESS_MERGE_ID) {
							generatedResult.setCode(MergeBlockParser.merge(
									originalFileContents, MergeBlockParser
											.parse(generatedResult.getCode())));
						}
	
						isOutput = true;
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (CoreException e) {
						e.printStackTrace();
					}
					//バックアップファイルの整理
					FileUtil.removeBackupFiles(outputProject, generatedResult.getName());
				}
			}
		} else {
			isOutput = true;
		}

		if (isOutput) {
			IFile outputFile = outputProject.getFile(generatedResult.getName());
			IPath relPath = outputFile.getProjectRelativePath();
			if( relPath.segmentCount() > 1 ) {
				String[] segs = relPath.segments();
				StringBuilder builder = new StringBuilder();
				for(int index=0;index<relPath.segmentCount()-1;index++) {
					builder.append(segs[index] + File.separator);
					IFolder folder = outputProject.getFolder(builder.toString());
					if(!folder.exists()) {
						try {
							folder.create(false, true, null);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}
			}
			try {
				outputFile.create(new ByteArrayInputStream(generatedResult.getCode().getBytes("UTF-8")), false, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ジェネレートし、ファイル出力を行う
	 * 
	 * @param generatorParam
	 *            パラメータ
	 * @param handler
	 *            MergeHandler
	 * @throws ParseException
	 * @throws IOException
	 */
	public void doGenerateWrite(GeneratorParam generatorParam,
			MergeHandler handler) throws Exception {
		
		for( RtcParam rtcParam : generatorParam.getRtcParams() ) {
			List<GeneratedResult> generatedResult = generateTemplateCode(generatorParam);
			writeFile(generatedResult, rtcParam, handler);
		}
	}

	/**
	 * マージハンドラ
	 */
	public interface MergeHandler {
		/**
		 * プロセス：オリジナルを残す
		 */
		public static final int PROCESS_ORIGINAL_ID = 10;

		/**
		 * プロセス：新しく生成したものを利用する
		 */
		public static final int PROCESS_GENERATE_ID = 20;

		/**
		 * プロセス：マージを行う
		 */
		public static final int PROCESS_MERGE_ID = 30;

		/**
		 * プロセスを選択する
		 * 
		 * @param generatedResult
		 *            生成結果
		 * @param originalFileContents
		 *            既存ファイルの内容
		 * @return
		 */
		public int getSelectedProcess(GeneratedResult generatedResult,
				String originalFileContents);
	}
}
