package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.DocumentPreferenceManager;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version02.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version02.Actions;
import org.openrtp.namespaces.rtc.version02.BasicInfoExt;
import org.openrtp.namespaces.rtc.version02.ConfigurationExt;
import org.openrtp.namespaces.rtc.version02.DataportExt;
import org.openrtp.namespaces.rtc.version02.DocAction;
import org.openrtp.namespaces.rtc.version02.DocBasic;
import org.openrtp.namespaces.rtc.version02.DocConfiguration;
import org.openrtp.namespaces.rtc.version02.DocDataport;
import org.openrtp.namespaces.rtc.version02.DocServiceinterface;
import org.openrtp.namespaces.rtc.version02.DocServiceport;
import org.openrtp.namespaces.rtc.version02.Language;
import org.openrtp.namespaces.rtc.version02.LanguageExt;
import org.openrtp.namespaces.rtc.version02.Library;
import org.openrtp.namespaces.rtc.version02.ObjectFactory;
import org.openrtp.namespaces.rtc.version02.Parameter;
import org.openrtp.namespaces.rtc.version02.Position;
import org.openrtp.namespaces.rtc.version02.Property;
import org.openrtp.namespaces.rtc.version02.RtcProfile;
import org.openrtp.namespaces.rtc.version02.ServiceinterfaceDoc;
import org.openrtp.namespaces.rtc.version02.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version02.ServiceportExt;
import org.openrtp.namespaces.rtc.version02.TargetEnvironment;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class ParamUtil {
	
	public static RtcProfile initialXml(String creationDate) {
		ObjectFactory factory = new ObjectFactory();
		RtcProfile profileType = factory.createRtcProfile();
		String moduleId = IRtcBuilderConstants.SPEC_SUFFIX + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
				ComponentPreferenceManager.getInstance().getBasic_VendorName() + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
				ComponentPreferenceManager.getInstance().getBasic_Category() + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
				ComponentPreferenceManager.getInstance().getBasic_ComponentName() + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
				ComponentPreferenceManager.getInstance().getBasic_Version();
		profileType.setId(moduleId);
		profileType.setVersion(IRtcBuilderConstants.SCHEMA_VERSION);
		
		BasicInfoExt basic = initBasicInfo(creationDate, factory);
		profileType.setBasicInfo(basic);
		//
		Actions actionType = initActions(factory);
		profileType.setActions(actionType);
		
		return profileType;
	}

	private static Actions initActions(ObjectFactory factory) {

		ArrayList<String> docs = DocumentPreferenceManager.getDocumentValue();
		Actions actionType = factory.createActions();
		//
		ActionStatusDoc actionStatus = null;
		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_INITIALIZE)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_INITIALIZE)).booleanValue());
			actionType.setOnInitialize(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_FINALIZE)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_FINALIZE)).booleanValue());
			actionType.setOnFinalize(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_STARTUP)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_STARTUP)).booleanValue());
			actionType.setOnStartup(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_SHUTDOWN)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_SHUTDOWN)).booleanValue());
			actionType.setOnShutdown(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ACTIVATED)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ACTIVATED)).booleanValue());
			actionType.setOnActivated(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_DEACTIVATED)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_DEACTIVATED)).booleanValue());
			actionType.setOnDeactivated(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_EXECUTE)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_EXECUTE)).booleanValue());
			actionType.setOnExecute(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ABORTING)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ABORTING)).booleanValue());
			actionType.setOnAborting(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ERROR)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ERROR)).booleanValue());
			actionType.setOnError(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_RESET)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_RESET)).booleanValue());
			actionType.setOnReset(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE)).booleanValue());
			actionType.setOnStateUpdate(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED)).booleanValue());
			actionType.setOnRateChanged(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ACTION)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_ACTION)).booleanValue());
			actionType.setOnAction(actionStatus);
		}

		if( Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_MODE_CHANGED)).booleanValue() ) {
			actionStatus = factory.createActionStatusDoc();
			actionStatus.setImplementedbln(Boolean.valueOf(docs.get(IRtcBuilderConstants.ACTIVITY_MODE_CHANGED)).booleanValue());
			actionType.setOnModeChanged(actionStatus);
		}
		return actionType;
	}

	private static BasicInfoExt initBasicInfo(String creationDate, ObjectFactory factory) {

		BasicInfoExt basic = factory.createBasicInfoExt();
		basic.setName(ComponentPreferenceManager.getInstance().getBasic_ComponentName());
		basic.setDescription(ComponentPreferenceManager.getInstance().getBasic_Description());
		basic.setVersion(ComponentPreferenceManager.getInstance().getBasic_Version());
		basic.setVendor(ComponentPreferenceManager.getInstance().getBasic_VendorName());
		basic.setCategory(ComponentPreferenceManager.getInstance().getBasic_Category());
		basic.setComponentType(ComponentPreferenceManager.getInstance().getBasic_ComponentType());
		basic.setActivityType(ComponentPreferenceManager.getInstance().getBasic_ActivityType());
		basic.setComponentKind(ComponentPreferenceManager.getInstance().getBasic_ComponentKind());
		basic.setMaxInstances(BigInteger.valueOf(ComponentPreferenceManager.getInstance().getBasic_MaxInstances()));
		basic.setExecutionType(ComponentPreferenceManager.getInstance().getBasic_ExecutionType());
		basic.setExecutionRate(Double.valueOf(ComponentPreferenceManager.getInstance().getBasic_ExecutionRate()));
		//
		DatatypeFactory dateFactory = new DatatypeFactoryImpl();
		basic.setCreationDate(dateFactory.newXMLGregorianCalendar(creationDate));
		basic.setUpdateDate(dateFactory.newXMLGregorianCalendar(creationDate));
		//
		DocBasic docBasic = factory.createDocBasic();
		docBasic.setCreator(DocumentPreferenceManager.getCreatorValue());
		docBasic.setLicense(DocumentPreferenceManager.getLicenseValue());
		basic.setDoc(docBasic);

		return basic;
	}

	protected  static boolean checkNotNull(String target) {
		if( target==null) return false;
		if( target.equals("") ) return false;
		return true;
	}

	public RtcParam convertFromModule(RtcProfile profile, GeneratorParam generatorParam,
			List<GenerateManager> managerList) throws Exception {
		return convertFromModule(profile, generatorParam, managerList, false);
		
	}
	
	public RtcParam convertFromModule(RtcProfile profile, GeneratorParam generatorParam,
										List<GenerateManager> managerList, boolean isDirect) throws Exception {
		RtcParam rtcParam = new RtcParam(generatorParam, isDirect);
		
		rtcParam.setSchemaVersion(profile.getVersion());

		convertFromModuleBasic(profile, rtcParam);
		if( profile.getDataPorts() != null ) {
			createDataPortParam(profile.getDataPorts(), rtcParam);
		}
		if( profile.getServicePorts() != null ) {
			createServicePortParam(profile.getServicePorts(), rtcParam.getServicePorts());
		}
		if( profile.getConfigurationSet() != null ) {
			createConfigParam(profile.getConfigurationSet().getConfiguration(), rtcParam);
		}
		convertFromModuleParameter(profile, rtcParam);
		convertFromModuleLanguage(profile, managerList, rtcParam);
		convertFromModuleLanguage(profile, rtcParam);
		//
		return rtcParam;
	}

	private void convertFromModuleLanguage(RtcProfile profile, RtcParam rtcParam) {
		Actions actions = profile.getActions();
		if( actions != null ) {
			if( actions.getOnInitialize() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_INITIALIZE, (ActionStatusDoc)actions.getOnInitialize());
			if( actions.getOnFinalize() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_FINALIZE, (ActionStatusDoc)actions.getOnFinalize());
			if( actions.getOnStartup() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_STARTUP, (ActionStatusDoc)actions.getOnStartup());
			if( actions.getOnShutdown() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_SHUTDOWN, (ActionStatusDoc)actions.getOnShutdown());
			if( actions.getOnActivated() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_ACTIVATED, (ActionStatusDoc)actions.getOnActivated());
			if( actions.getOnDeactivated() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_DEACTIVATED, (ActionStatusDoc)actions.getOnDeactivated());
			if( actions.getOnExecute() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_EXECUTE, (ActionStatusDoc)actions.getOnExecute());
			if( actions.getOnAborting() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_ABORTING, (ActionStatusDoc)actions.getOnAborting());
			if( actions.getOnError() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_ERROR, (ActionStatusDoc)actions.getOnError());
			if( actions.getOnReset() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_RESET, (ActionStatusDoc)actions.getOnReset());
			if( actions.getOnStateUpdate() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_STATE_UPDATE, (ActionStatusDoc)actions.getOnStateUpdate());
			if( actions.getOnRateChanged() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_RATE_CHANGED, (ActionStatusDoc)actions.getOnRateChanged());
			if( actions.getOnAction() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_ACTION, (ActionStatusDoc)actions.getOnAction());
			if( actions.getOnModeChanged() != null )
				setActions( rtcParam, IRtcBuilderConstants.ACTIVITY_MODE_CHANGED, (ActionStatusDoc)actions.getOnModeChanged());
		}
	}

	private void convertFromModuleLanguage(RtcProfile profile, List<GenerateManager> managerList, RtcParam rtcParam) {
		Language language = profile.getLanguage();
		if (language != null) {
			String langKind = language.getKind();
			if (isCxx(langKind)) {
				rtcParam.getLangList().clear();
				rtcParam.getLangList().add(IRtcBuilderConstants.LANG_CPP);
				rtcParam.getLangArgList().clear();
				rtcParam.getLangArgList().add(IRtcBuilderConstants.LANG_CPP_ARG);
				rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
			} else {
				if (managerList != null) {
					for (GenerateManager manager : managerList) {
						manager.convertProfile(profile);
						language = profile.getLanguage();
						langKind = language.getKind();
						if (langKind.trim().equals(manager.getManagerKey())) {
							rtcParam.getLangList().clear();
							rtcParam.getLangList().add(manager.getManagerKey());
							rtcParam.getLangArgList().clear();
							rtcParam.getLangArgList().add(manager.getLangArgList());
							rtcParam.setRtmVersion(manager.getTargetVersion());
							break;
						}
					}
				}
			}
			if( language instanceof LanguageExt ) {
				LanguageExt langExt = (LanguageExt)language;
				for( TargetEnvironment target : langExt.getTargets()) {
					TargetEnvParam env = new TargetEnvParam();
					env.setLangVersion(target.getLangVersion());
					env.setOs(target.getOs());
					env.setOther(target.getOther());
					env.setCpuOther(target.getCpuOther());
					//
					for( String osVersion : target.getOsVersions()) {
						env.getOsVersions().add(osVersion);
					}
					//
					for( String cpus : target.getCpus()) {
						env.getCpus().add(cpus);
					}
					//
					for( Library lib : target.getLibraries()) {
						LibraryParam libParam = new LibraryParam();
						libParam.setName(lib.getName());
						libParam.setVersion(lib.getVersion());
						libParam.setOther(lib.getOther());
						env.getLibraries().add(libParam);
					}
					rtcParam.getTargetEnvs().add(env);
				}
			}
		}
	}

	private void convertFromModuleParameter(RtcProfile profile, RtcParam rtcParam) {
		for( Object paramObj : profile.getParameters() ) {
			Parameter param = (Parameter)paramObj;
			ConfigParameterParam paramp = new ConfigParameterParam();
			paramp.setConfigName(param.getName());
			paramp.setDefaultVal(param.getDefaultValue());
			rtcParam.getConfigParameterParams().add(paramp);
		}
	}

	private void convertFromModuleBasic(RtcProfile profile, RtcParam rtcParam) {
		BasicInfoExt basic = (BasicInfoExt)profile.getBasicInfo();
		//��{
		rtcParam.setName(basic.getName());
		rtcParam.setComponentType(basic.getComponentType());
		rtcParam.setActivityType(basic.getActivityType());
		rtcParam.setComponentKind(basic.getComponentKind());

		rtcParam.setDescription(basic.getDescription());
		rtcParam.setVersion(basic.getVersion());
		rtcParam.setVender(basic.getVendor());
		rtcParam.setCategory(basic.getCategory());
		if( basic.getMaxInstances() != null )
			rtcParam.setMaxInstance(basic.getMaxInstances().intValue());
		rtcParam.setExecutionType(basic.getExecutionType());
		if( basic.getExecutionRate()!=null )
			rtcParam.setExecutionRate(basic.getExecutionRate().doubleValue());
		rtcParam.setAbstract(basic.getAbstract());
		rtcParam.setRtcType(basic.getRtcType());
		XMLGregorianCalendar crDate = basic.getCreationDate();
		crDate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		rtcParam.setCreationDate(crDate.toString());
		XMLGregorianCalendar updateDate = basic.getUpdateDate();
		updateDate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		rtcParam.setUpdateDate(updateDate.toString());
		
		rtcParam.getVersionUpLog().clear();
		rtcParam.getVersionUpLog().addAll(basic.getVersionUpLogs());
		// rtcParam.setVersionUpLog(basic.getVersionUpLogs());
		//Doc Basic
		DocBasic docbasic = basic.getDoc();
		if( docbasic != null ) {
			rtcParam.setDocDescription(docbasic.getDescription());
			rtcParam.setDocInOut(docbasic.getInout());
			rtcParam.setDocAlgorithm(docbasic.getAlgorithm());
			rtcParam.setDocCreator(docbasic.getCreator());
			rtcParam.setDocLicense(docbasic.getLicense());
			rtcParam.setDocReference(docbasic.getReference());
		}
		//Ext Basic
		rtcParam.setOutputProject(basic.getSaveProject());
		//Basic Properties
		for( Property prop : basic.getProperties() ) {
			PropertyParam propParam = new PropertyParam();
			propParam.setName(prop.getName());
			propParam.setValue(prop.getValue());
			rtcParam.getProperties().add(propParam);
		}
	}
	
	private boolean isCxx(String target) {
		if( target.toUpperCase().equals(IRtcBuilderConstants.LANG_CPPWIN) ||
				target.equals(IRtcBuilderConstants.LANG_CPP) )
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	private void createConfigParam(List configs, RtcParam rtcParam)  throws Exception{
		for( Object config : configs ) {
			ConfigurationExt configDoc = (ConfigurationExt)config;
			ConfigSetParam configp = new ConfigSetParam(
					configDoc.getName(), configDoc.getType(), configDoc.getDefaultValue());
			configp.setUnit(configDoc.getUnit());
			if( configDoc.getConstraint()!=null )
				configp.setConstraint(XmlHandler.restoreConstraint(configDoc.getConstraint()));
			DocConfiguration docConfig = configDoc.getDoc();
			if( docConfig!=null ) {
				configp.setDocDataName(docConfig.getDataname());
				configp.setDocDescription(docConfig.getDescription());
				configp.setDocDefaultVal(docConfig.getDefaultValue());
				configp.setDocUnit(docConfig.getUnit());
				configp.setDocRange(docConfig.getRange());
				configp.setDocConstraint(docConfig.getConstraint());
			}
			//Properties
			if( config instanceof ConfigurationExt ) {
				ConfigurationExt configExt = (ConfigurationExt)config;
				configp.setVarName(configExt.getVariableName());
				for( Property prop : configExt.getProperties() ) {
					PropertyParam propParam = new PropertyParam();
					propParam.setName(prop.getName());
					propParam.setValue(prop.getValue());
					configp.getProperties().add(propParam);
				}
			}
			rtcParam.getConfigParams().add(configp);
		}
	}
	
	private void setActions(RtcParam rtcParam, int actionId, ActionStatusDoc actionStatus) {
		rtcParam.setActionImplemented( actionId, actionStatus.getImplemented());
		if( actionStatus.getDoc() != null ) {
			rtcParam.setDocActionOverView( actionId, actionStatus.getDoc().getDescription()); 
			rtcParam.setDocActionPreCondition( actionId, actionStatus.getDoc().getPreCondition()); 
			rtcParam.setDocActionPostCondition( actionId, actionStatus.getDoc().getPostCondition()); 
		}
	}

	@SuppressWarnings("unchecked")
	private void createServicePortParam(List servicePorts, List<ServicePortParam> targetPort) {
		for( Object serviceport : servicePorts ) {
			ServiceportExt servicePortExt = (ServiceportExt)serviceport;
			ServicePortParam serviceportp = new ServicePortParam();
			serviceportp.setName(servicePortExt.getName());
			serviceportp.setPosition(servicePortExt.getPosition().toString());
			DocServiceport doc = servicePortExt.getDoc();
			if( doc != null ) {
				serviceportp.setDocDescription(doc.getDescription());
				serviceportp.setDocIfDescription(doc.getIfdescription());
			}
			//Properties
			for( Property prop : servicePortExt.getProperties() ) {
				PropertyParam propParam = new PropertyParam();
				propParam.setName(prop.getName());
				propParam.setValue(prop.getValue());
				serviceportp.getProperties().add(propParam);
			}

			//Service Interface
			for( Object serviceinterface : servicePortExt.getServiceInterface() ) {
				ServiceinterfaceDoc serviceIfDoc = (ServiceinterfaceDoc)serviceinterface;
				DocServiceinterface docSrv = serviceIfDoc.getDoc();
				ServicePortInterfaceParam serviceIF = new ServicePortInterfaceParam(serviceportp);
				serviceIF.setName(serviceIfDoc.getName());
				serviceIF.setDirection(serviceIfDoc.getDirection());
				serviceIF.setInstanceName(serviceIfDoc.getInstanceName());
				serviceIF.setIdlFile(serviceIfDoc.getIdlFile());
				serviceIF.setInterfaceType(serviceIfDoc.getType());
				serviceIF.setIdlSearchPath(serviceIfDoc.getPath());
				if( docSrv!=null ) {
					serviceIF.setDocDescription(docSrv.getDescription());
					serviceIF.setDocArgument(docSrv.getDocArgument());
					serviceIF.setDocReturn(docSrv.getDocReturn());
					serviceIF.setDocException(docSrv.getDocException());
					serviceIF.setDocPreCondition(docSrv.getDocPreCondition());
					serviceIF.setDocPostCondition(docSrv.getDocPostCondition());
				}
				//Properties
				if( serviceinterface instanceof ServiceinterfaceExt ) {
					ServiceinterfaceExt serviceIfExt = (ServiceinterfaceExt)serviceinterface;
					serviceIF.setVarName(serviceIfExt.getVariableName());
					for( Property prop : serviceIfExt.getProperties() ) {
						PropertyParam propParam = new PropertyParam();
						propParam.setName(prop.getName());
						propParam.setValue(prop.getValue());
						serviceIF.getProperties().add(propParam);
					}
				}

				serviceportp.getServicePortInterfaces().add(serviceIF);
			}
			targetPort.add(serviceportp);
		}
	}

	@SuppressWarnings("unchecked")
	private void createDataPortParam(List dataPorts, RtcParam rtcParam) throws Exception {
		List<DataPortParam> InPortList = new ArrayList<DataPortParam>();
		List<DataPortParam> OutPortList = new ArrayList<DataPortParam>();
		for( Object dataport : dataPorts ) {
			DataportExt dataPortExt = (DataportExt)dataport;
			DataPortParam dataportp = new DataPortParam();
			dataportp.setName(dataPortExt.getName());
			dataportp.setType(dataPortExt.getType());
			dataportp.setVarName(dataPortExt.getVariableName());
			dataportp.setPosition(dataPortExt.getPosition().toString());
			dataportp.setDataFlowType(dataPortExt.getDataflowType());
			dataportp.setInterfaceType(dataPortExt.getInterfaceType());
			dataportp.setSubscriptionType(dataPortExt.getSubscriptionType());
			dataportp.setIdlFile(dataPortExt.getIdlFile());
			dataportp.setUnit(dataPortExt.getUnit());
			if( dataPortExt.getConstraint()!=null )
				dataportp.setConstraint(XmlHandler.restoreConstraint(dataPortExt.getConstraint()));
			
			DocDataport docPort = dataPortExt.getDoc();
			if( docPort!=null ) {
				dataportp.setDocDescription(docPort.getDescription());
				dataportp.setDocType(docPort.getType());
				dataportp.setDocNum(docPort.getNumber());
				dataportp.setDocSemantics(docPort.getSemantics());
				dataportp.setDocUnit(docPort.getUnit());
				dataportp.setDocOccurrence(docPort.getOccerrence());
				dataportp.setDocOperation(docPort.getOperation());
			}
			//Properties
			for( Property prop : dataPortExt.getProperties() ) {
				PropertyParam propParam = new PropertyParam();
				propParam.setName(prop.getName());
				propParam.setValue(prop.getValue());
				dataportp.getProperties().add(propParam);
			}
			//
			if(dataPortExt.getPortType().equals(IRtcBuilderConstants.SPEC_DATA_INPORT_KIND) )
				InPortList.add(dataportp);
			else
				OutPortList.add(dataportp);
		}
		rtcParam.getInports().clear();
		rtcParam.getInports().addAll(InPortList);
		rtcParam.getOutports().clear();
		rtcParam.getOutports().addAll(OutPortList);
		// rtcParam.setInports(InPortList);
		// rtcParam.setOutports(OutPortList);
	}

	public RtcProfile convertToModule(GeneratorParam generatorParam,
										List<GenerateManager> managerList) throws Exception {
		RtcParam rtcParam = generatorParam.getRtcParams().get(0);
		return convertToModule(rtcParam, managerList);
	}

	public RtcProfile convertToModule(RtcParam target, List<GenerateManager> managerList) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		RtcProfile profile = factory.createRtcProfile();
		String moduleId = IRtcBuilderConstants.SPEC_SUFFIX + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
		target.getVender() + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
		target.getCategory() + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
		target.getName() + IRtcBuilderConstants.SPEC_MAJOR_SEPARATOR +
		target.getVersion();
		profile.setId(moduleId);
		profile.setVersion(target.getSchemaVersion());
		convertToModuleBasic(target, factory, profile);
		convertToModuleActions(target, factory, profile);

		for( DataPortParam dataportp : target.getInports() ) {
			profile.getDataPorts().add(createDataPort(dataportp, IRtcBuilderConstants.SPEC_DATA_INPORT_KIND));
		}
		for( DataPortParam dataportp : target.getOutports() ) {
			profile.getDataPorts().add(createDataPort(dataportp, IRtcBuilderConstants.SPEC_DATA_OUTPORT_KIND));
		}
		for( ServicePortParam serviceportp : target.getServicePorts() ) {
			ServiceportExt serviceport = createServicePort(serviceportp);
			profile.getServicePorts().add(serviceport);
		}
		convertToModuleConfiguration(target, factory, profile);
		convertToModuleParameter(target, factory, profile);
		convertToModuleLanguage(managerList, target, factory, profile);
		
		deleteInapplicableItem(profile, managerList);
		
		return profile;
	}

	private void convertToModuleLanguage(List<GenerateManager> managerList, RtcParam rtcParam, ObjectFactory factory, RtcProfile profile) {
		for( String languagep : rtcParam.getLangList() ) {
			LanguageExt language = factory.createLanguageExt();
			if(languagep.equals(IRtcBuilderConstants.LANG_CPP)) {
				language.setKind(IRtcBuilderConstants.LANG_CPP);
			} else {
				if( managerList != null ) {
					for( Iterator<GenerateManager> iter = managerList.iterator(); iter.hasNext(); ) {
						GenerateManager manager = iter.next();
						if( languagep.trim().equals(manager.getManagerKey())) {
							language.setKind(manager.getManagerKey());
							break;
						}
					}
				}
			}
			//
			for(TargetEnvParam target : rtcParam.getTargetEnvs() ) {
				TargetEnvironment env = factory.createTargetEnvironment();
				env.setLangVersion(target.getLangVersion());
				env.setOs(target.getOs());
				env.setOther(target.getOther());
				env.setCpuOther(target.getCpuOther());
				//
				for( String osVersion : target.getOsVersions() ) {
					env.getOsVersions().add(osVersion);
				}
				//
				for( String cpus : target.getCpus() ) {
					env.getCpus().add(cpus);
				}
				//
				for( LibraryParam library : target.getLibraries() ) {
					Library lib = factory.createLibrary();
					lib.setName(library.getName());
					lib.setVersion(library.getVersion());
					lib.setOther(library.getOther());
					env.getLibraries().add(lib);
				}
				language.getTargets().add(env);
			}
			profile.setLanguage(language);
		}
	}

	private void convertToModuleParameter(RtcParam rtcParam, ObjectFactory factory, RtcProfile profile) {
		for( ConfigParameterParam configp : rtcParam.getConfigParameterParams() ) {
			Parameter param = factory.createParameter();
			param.setName(configp.getConfigName());
			param.setDefaultValue(configp.getDefaultVal());
			profile.getParameters().add(param);
		}
	}

	private void convertToModuleConfiguration(RtcParam rtcParam, ObjectFactory factory, RtcProfile profile) throws Exception {
		for( ConfigSetParam configp : rtcParam.getConfigParams() ) {
			ConfigurationExt config = factory.createConfigurationExt();
			config.setName(configp.getName());
			config.setType(configp.getType());
			config.setVariableName(configp.getVarName());
			config.setDefaultValue(configp.getDefaultVal());
			config.setUnit(configp.getUnit());
			if( configp.getConstraint()!=null && !"".equals(configp.getConstraint()) )
				config.setConstraint(XmlHandler.convertToXmlConstraint(configp.getConstraint()));
			profile.getConfigurationSet().getConfiguration().add(config);
			//
			for(PropertyParam propp : configp.getProperties() ) {
				if( propp.getValue()!=null && propp.getValue().length()>0 ) {
					Property prop = factory.createProperty();
					prop.setName(propp.getName());
					prop.setValue(propp.getValue());
					config.getProperties().add(prop);
				}
			}
			//
			DocConfiguration docconfig = factory.createDocConfiguration();
			docconfig.setDataname(configp.getDocDataName());
			docconfig.setDefaultValue(configp.getDocDefaultVal());
			docconfig.setDescription(configp.getDocDescription());
			docconfig.setUnit(configp.getDocUnit());
			docconfig.setRange(configp.getDocRange());
			docconfig.setConstraint(configp.getDocConstraint());
			if( checkNotNull(configp.getDocDataName()) ||
				 checkNotNull(configp.getDocDefaultVal()) ||
				 checkNotNull(configp.getDocDescription()) ||
				 checkNotNull(configp.getDocUnit()) ||
				 checkNotNull(configp.getDocRange()) ||
				 checkNotNull(configp.getDocConstraint()) ) {
					config.setDoc(docconfig);
			}

		}
	}

	private void convertToModuleActions(RtcParam rtcParam, ObjectFactory factory, RtcProfile profile) {
		Actions actions = factory.createActions();
		actions.setOnInitialize(createActions(IRtcBuilderConstants.ACTIVITY_INITIALIZE, rtcParam));
		actions.setOnFinalize(createActions(IRtcBuilderConstants.ACTIVITY_FINALIZE, rtcParam));
		actions.setOnStartup(createActions(IRtcBuilderConstants.ACTIVITY_STARTUP, rtcParam));
		actions.setOnShutdown(createActions(IRtcBuilderConstants.ACTIVITY_SHUTDOWN, rtcParam));
		actions.setOnActivated(createActions(IRtcBuilderConstants.ACTIVITY_ACTIVATED, rtcParam));
		actions.setOnDeactivated(createActions(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, rtcParam));
		actions.setOnExecute(createActions(IRtcBuilderConstants.ACTIVITY_EXECUTE, rtcParam));
		actions.setOnAborting(createActions(IRtcBuilderConstants.ACTIVITY_ABORTING, rtcParam));
		actions.setOnError(createActions(IRtcBuilderConstants.ACTIVITY_ERROR, rtcParam));
		actions.setOnReset(createActions(IRtcBuilderConstants.ACTIVITY_RESET, rtcParam));
		actions.setOnStateUpdate(createActions(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE, rtcParam));
		actions.setOnRateChanged(createActions(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED, rtcParam));
		actions.setOnAction(createActions(IRtcBuilderConstants.ACTIVITY_ACTION, rtcParam));
		actions.setOnModeChanged(createActions(IRtcBuilderConstants.ACTIVITY_MODE_CHANGED, rtcParam));
		profile.setActions(actions);
	}

	private void convertToModuleBasic(RtcParam rtcParam, ObjectFactory factory, RtcProfile profile) {
		BasicInfoExt basic = factory.createBasicInfoExt();
		basic.setName(rtcParam.getName());
		basic.setDescription(rtcParam.getDescription());
		basic.setVersion(rtcParam.getVersion());
		basic.setVendor(rtcParam.getVender());
		basic.setCategory(rtcParam.getCategory());
		basic.setComponentType(rtcParam.getComponentType());
		basic.setActivityType(rtcParam.getActivityType());
		basic.setComponentKind(rtcParam.getComponentKind());
		basic.setMaxInstances(BigInteger.valueOf(rtcParam.getMaxInstance()));
		basic.setExecutionType(rtcParam.getExecutionType());
		basic.setExecutionRate(Double.valueOf(rtcParam.getExecutionRate()));
		//
		basic.setAbstract(rtcParam.getAbstract());
		basic.setRtcType(rtcParam.getRtcType());
		if(rtcParam.getCreationDate()!=null) basic.setCreationDate(XMLGregorianCalendarImpl.parse(rtcParam.getCreationDate()));
		if(rtcParam.getUpdateDate()!=null) basic.setUpdateDate(XMLGregorianCalendarImpl.parse(rtcParam.getUpdateDate()));
		if(rtcParam.getVersionUpLog()!=null)basic.getVersionUpLogs().addAll(rtcParam.getVersionUpLog());
		if(rtcParam.getCurrentVersionUpLog()!=null)basic.getVersionUpLogs().add(rtcParam.getCurrentVersionUpLog());
		//Doc Basic
		DocBasic docbasic = factory.createDocBasic();
		if( rtcParam.isDocExist() ) {
			docbasic.setDescription(rtcParam.getDocDescription());
			docbasic.setInout(rtcParam.getDocInOut());
			docbasic.setAlgorithm(rtcParam.getDocAlgorithm());
			docbasic.setCreator(rtcParam.getDocCreator());
			docbasic.setLicense(rtcParam.getDocLicense());
			docbasic.setReference(rtcParam.getDocReference());
			basic.setDoc(docbasic);
		}
		//Ext Basic
		basic.setSaveProject(rtcParam.getOutputProject());
		//Properties
		for( PropertyParam prop : rtcParam.getProperties() ) {
			Property basicProp = factory.createProperty();
			basicProp.setName(prop.getName());
			basicProp.setValue(prop.getValue());
			basic.getProperties().add(basicProp);
		}
		profile.setBasicInfo(basic);
	}

	private DataportExt createDataPort(DataPortParam dataportp, String portType) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		DataportExt dataport = factory.createDataportExt();
		dataport.setPortType(portType);
		dataport.setName(dataportp.getName());
		dataport.setType(dataportp.getType());
		dataport.setVariableName(dataportp.getVarName());
		dataport.setPosition(Position.fromValue(dataportp.getPosition().toUpperCase()));
		dataport.setIdlFile(dataportp.getIdlFile());
		dataport.setDataflowType(dataportp.getDataFlowType());
		dataport.setInterfaceType(dataportp.getInterfaceType());
		dataport.setSubscriptionType(dataportp.getSubscriptionType());
		dataport.setUnit(dataportp.getUnit());
		if( dataportp.getConstraint()!=null && !"".equals(dataportp.getConstraint()) )
			dataport.setConstraint(XmlHandler.convertToXmlConstraint(dataportp.getConstraint()));
		//
		DocDataport docdataport = factory.createDocDataport();
		docdataport.setDescription(dataportp.getDocDescription());
		docdataport.setType(dataportp.getDocType());
		docdataport.setNumber(dataportp.getDocNum());
		docdataport.setSemantics(dataportp.getDocSemantics());
		docdataport.setUnit(dataportp.getDocUnit());
		docdataport.setOccerrence(dataportp.getDocOccurrence());
		docdataport.setOperation(dataportp.getDocOperation());
		if( checkNotNull(dataportp.getDocDescription()) ||
			 checkNotNull(dataportp.getDocType()) ||
			 checkNotNull(dataportp.getDocNum()) ||
			 checkNotNull(dataportp.getDocSemantics()) ||
			 checkNotNull(dataportp.getDocUnit()) ||
			 checkNotNull(dataportp.getDocOccurrence()) ||
			 checkNotNull(dataportp.getDocOperation()) ) {
				dataport.setDoc(docdataport);
		}
		//
		//Properties
		for( PropertyParam prop : dataportp.getProperties() ) {
			Property dpProp = factory.createProperty();
			dpProp.setName(prop.getName());
			dpProp.setValue(prop.getValue());
			dataport.getProperties().add(dpProp);
		}
		//
		return dataport;
	}

	private ServiceportExt createServicePort(ServicePortParam serviceportp) {
		ObjectFactory factory = new ObjectFactory();
		ServiceportExt serviceport = factory.createServiceportExt();
		serviceport.setName(serviceportp.getName());
		serviceport.setPosition(Position.fromValue(serviceportp.getPosition().toUpperCase()));
		//
		DocServiceport docserviceport = factory.createDocServiceport();
		docserviceport.setDescription(serviceportp.getDocDescription());
		docserviceport.setIfdescription(serviceportp.getDocIfDescription());
		if( checkNotNull(serviceportp.getDocDescription()) ||
			 checkNotNull(serviceportp.getDocIfDescription()) ) {
				 serviceport.setDoc(docserviceport);
		}
		//Properties
		for( PropertyParam prop : serviceportp.getProperties() ) {
			Property srvProp = factory.createProperty();
			srvProp.setName(prop.getName());
			srvProp.setValue(prop.getValue());
			serviceport.getProperties().add(srvProp);
		}
		//Service Interface
		for( ServicePortInterfaceParam serviceinterfacep : serviceportp.getServicePortInterfaces() ) {
			ServiceinterfaceExt serviceIF = factory.createServiceinterfaceExt();
			serviceIF.setName(serviceinterfacep.getName());
			serviceIF.setDirection(serviceinterfacep.getDirection());
			serviceIF.setInstanceName(serviceinterfacep.getInstanceName());
			serviceIF.setVariableName(serviceinterfacep.getVarName());
			serviceIF.setIdlFile(serviceinterfacep.getIdlFile());
			serviceIF.setType(serviceinterfacep.getInterfaceType());
			serviceIF.setPath(serviceinterfacep.getIdlSearchPath());
			//
			DocServiceinterface docserviceIF = factory.createDocServiceinterface();
			docserviceIF.setDescription(serviceinterfacep.getDocDescription());
			docserviceIF.setDocArgument(serviceinterfacep.getDocArgument());
			docserviceIF.setDocReturn(serviceinterfacep.getDocReturn());
			docserviceIF.setDocException(serviceinterfacep.getDocException());
			docserviceIF.setDocPreCondition(serviceinterfacep.getDocPreCondition());
			docserviceIF.setDocPostCondition(serviceinterfacep.getDocPostCondition());
			if( checkNotNull(serviceinterfacep.getDocDescription()) ||
				 checkNotNull(serviceinterfacep.getDocArgument()) ||
				 checkNotNull(serviceinterfacep.getDocReturn()) ||
				 checkNotNull(serviceinterfacep.getDocException()) ||
				 checkNotNull(serviceinterfacep.getDocPreCondition()) ||
				 checkNotNull(serviceinterfacep.getDocPostCondition()) ) {
					serviceIF.setDoc(docserviceIF);
			}
			//Properties
			for( PropertyParam prop : serviceinterfacep.getProperties() ) {
				Property ifProp = factory.createProperty();
				ifProp.setName(prop.getName());
				ifProp.setValue(prop.getValue());
				serviceIF.getProperties().add(ifProp);
			}
			
			serviceport.getServiceInterface().add(serviceIF);
		}
		return serviceport;
	}

	private ActionStatusDoc createActions(int actionId, RtcParam rtcParam) {
		ObjectFactory factory = new ObjectFactory();
		ActionStatusDoc status = factory.createActionStatusDoc();
		DocAction docAction = factory.createDocAction();
		//
		docAction.setDescription(rtcParam.getDocActionOverView(actionId));
		docAction.setPreCondition(rtcParam.getDocActionPreCondition(actionId));
		docAction.setPostCondition(rtcParam.getDocActionPostCondition(actionId));
		if( checkNotNull(docAction.getDescription()) || 
			 checkNotNull(docAction.getPreCondition()) ||
			 checkNotNull(docAction.getPostCondition()) ) {
				status.setDoc(docAction);
		}
		//
		status.setImplementedbln(rtcParam.getActionImplemented(actionId));
		//
		return status;
	}
	
	private void deleteInapplicableItem(RtcProfile profile, List<GenerateManager> managerList){
		if( profile.getLanguage()==null ) return;
		String langName = profile.getLanguage().getKind();
		for( GenerateManager manager : managerList ){
			if( manager.getManagerKey().endsWith(langName) ){
				List<String> infos = manager.getInapplicables();
				if( infos!=null ){
					if( infos.contains(GenerateManager.RTC_PROFILE_PARAMETERS_INAPPLICABLE) )
						profile.getParameters().clear();
					if( infos.contains(GenerateManager.RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE) )
						profile.getServicePorts().clear();
				}
				break;
			}
		}
	}
}
