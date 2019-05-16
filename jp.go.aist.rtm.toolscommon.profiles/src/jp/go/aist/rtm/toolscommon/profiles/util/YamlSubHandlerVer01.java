package jp.go.aist.rtm.toolscommon.profiles.util;

import static jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler.createXMLGregorianCalendar;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.openrtp.namespaces.rtc.version02.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version02.Actions;
import org.openrtp.namespaces.rtc.version02.BasicInfoExt;
import org.openrtp.namespaces.rtc.version02.ConfigurationExt;
import org.openrtp.namespaces.rtc.version02.ConfigurationSet;
import org.openrtp.namespaces.rtc.version02.DataportExt;
import org.openrtp.namespaces.rtc.version02.DocAction;
import org.openrtp.namespaces.rtc.version02.DocBasic;
import org.openrtp.namespaces.rtc.version02.DocConfiguration;
import org.openrtp.namespaces.rtc.version02.DocDataport;
import org.openrtp.namespaces.rtc.version02.DocServiceinterface;
import org.openrtp.namespaces.rtc.version02.DocServiceport;
import org.openrtp.namespaces.rtc.version02.LanguageExt;
import org.openrtp.namespaces.rtc.version02.Library;
import org.openrtp.namespaces.rtc.version02.ObjectFactory;
import org.openrtp.namespaces.rtc.version02.Parameter;
import org.openrtp.namespaces.rtc.version02.Position;
import org.openrtp.namespaces.rtc.version02.Property;
import org.openrtp.namespaces.rtc.version02.RtcProfile;
import org.openrtp.namespaces.rtc.version02.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version02.ServiceportExt;
import org.openrtp.namespaces.rtc.version02.TargetEnvironment;

public class YamlSubHandlerVer01 {

	@SuppressWarnings("unchecked")
	public RtcProfile mapToRtc(Map profileYOrg) {
		boolean isActionAdded = false;
		RtcProfile profile = null;
		ObjectFactory factory = new ObjectFactory();
		if( profileYOrg != null ) {
			Map profileY  = (Map)profileYOrg.get("rtcProfile");
			if( profileY != null ) {
				profile = factory.createRtcProfile();
				profile.setId((String)profileY.get("id"));
				profile.setVersion((String)profileY.get("version"));
				//基本
				Map basicY = (Map)profileY.get("basicInfo");
				if( basicY != null ) {
					BasicInfoExt basic = factory.createBasicInfoExt();
					basic.setAbstract((String)basicY.get("abstract"));
					basic.setActivityType((String)basicY.get("activityType"));
					basic.setCategory((String)basicY.get("category"));
					basic.setComponentKind((String)basicY.get("componentKind"));
					basic.setComponentType((String)basicY.get("componentType"));
					basic.setCreationDate(createXMLGregorianCalendar((Map<String, Integer>)basicY.get("creationDate")));
					basic.setDescription((String)basicY.get("description"));
					basic.setExecutionRate((Double)basicY.get("executionRate"));
					basic.setExecutionType((String)basicY.get("executionType"));
					if(basicY.get("maxInstances")!=null) basic.setMaxInstances(BigInteger.valueOf((Integer)basicY.get("maxInstances")));
					basic.setName((String)basicY.get("name"));
					basic.setUpdateDate(createXMLGregorianCalendar((Map<String, Integer>)basicY.get("updateDate")));
					basic.setVendor((String)basicY.get("vendor"));
					basic.setVersion((String)basicY.get("version"));
					//Basic Doc
					Map docbasicY = (Map)basicY.get("rtcDoc::doc");
					if( docbasicY != null ) {
						DocBasic docbasic = factory.createDocBasic();
						docbasic.setDescription((String)docbasicY.get("description"));
						docbasic.setInout((String)docbasicY.get("inout"));
						docbasic.setAlgorithm((String)docbasicY.get("algorithm"));
						docbasic.setCreator((String)docbasicY.get("creator"));
						docbasic.setLicense((String)docbasicY.get("license"));
						docbasic.setReference((String)docbasicY.get("reference"));
						basic.setDoc(docbasic);
					}
					//Basic ext
					if( basicY.get("rtcExt::versionUpLog")!=null )basic.getVersionUpLogs().addAll((List)basicY.get("rtcExt::versionUpLog"));
					//Properties
					List basicPropertiesListY = (List)basicY.get("rtcExt::properties");
					if( basicPropertiesListY != null ) {
						for(int intIdx=0;intIdx<basicPropertiesListY.size();intIdx++ ) {
							Map basicPropInfoY = (Map)basicPropertiesListY.get(intIdx);
							if( basicPropInfoY != null ) {
								Property prop = factory.createProperty();
								prop.setName((String)basicPropInfoY.get("name"));
								prop.setValue((String)basicPropInfoY.get("value"));
								basic.getProperties().add(prop);
							}
						}
					}
					profile.setBasicInfo(basic);
				}
				//Actions
				Map actionsY = (Map)profileY.get("actions");
				if( actionsY != null ) {
					Actions actions = factory.createActions();
					Map eachActionY = (Map)actionsY.get("onInitialize");
					if( eachActionY != null ) {
						actions.setOnInitialize(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onFinalize");
					if( eachActionY != null ) {
						actions.setOnFinalize(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onStartup");
					if( eachActionY != null ) {
						actions.setOnStartup(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onShutdown");
					if( eachActionY != null ) {
						actions.setOnShutdown(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onActivated");
					if( eachActionY != null ) {
						actions.setOnActivated(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onDeactivated");
					if( eachActionY != null ) {
						actions.setOnDeactivated(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onExecute");
					if( eachActionY != null ) {
						actions.setOnExecute(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onAborting");
					if( eachActionY != null ) {
						actions.setOnAborting(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onError");
					if( eachActionY != null ) {
						actions.setOnError(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onReset");
					if( eachActionY != null ) {
						actions.setOnReset(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onStateUpdate");
					if( eachActionY != null ) {
						actions.setOnStateUpdate(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onRateChanged");
					if( eachActionY != null ) {
						actions.setOnRateChanged(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					if( isActionAdded ) profile.setActions(actions);
				}

				//Data Ports
				List dataPortListY = (List)profileY.get("dataPorts");
				if( dataPortListY != null ) {
					for(int intIdx=0;intIdx<dataPortListY.size();intIdx++ ) {
						Map dataPortInfoY = (Map)dataPortListY.get(intIdx);
						if( dataPortInfoY != null ) {
							DataportExt dataport = createDataPortFromYaml(dataPortInfoY);
							profile.getDataPorts().add(dataport);
						}
					}
				}
				//Service Ports
				List servicePortListY = (List)profileY.get("servicePorts");
				if( servicePortListY != null ) {
					for(int intIdx=0;intIdx<servicePortListY.size();intIdx++ ) {
						Map servicePortY = (Map)servicePortListY.get(intIdx);
						if( servicePortY != null ) {
							ServiceportExt serviceport = createServicePortFromYaml(servicePortY);
							profile.getServicePorts().add(serviceport);
						}
					}
				}
				//Configuration
				Map configSetInfoY = (Map)profileY.get("configurationSet");
				if( configSetInfoY != null ) {
					List configsInfoY = (List)configSetInfoY.get("configuration");
					if( configsInfoY != null ) {
						ConfigurationSet configset = factory.createConfigurationSet();
						for(int intIdx=0;intIdx<configsInfoY.size();intIdx++ ) {
							Map configInfoY = (Map)configsInfoY.get(intIdx);
							if( configInfoY != null ) {
								ConfigurationExt config = factory.createConfigurationExt();
								config.setDefaultValue((String)configInfoY.get("defaultValue"));
								config.setName((String)configInfoY.get("name"));
								config.setType((String)configInfoY.get("type"));
								config.setVariableName((String)configInfoY.get("varname"));
								configset.getConfiguration().add(config);
								//
								Map configDocY = (Map)configInfoY.get("rtcDoc::doc");
								if( configDocY != null ) {
									DocConfiguration docconfig = factory.createDocConfiguration();
									docconfig.setConstraint((String)configDocY.get("constraint"));
									docconfig.setDataname((String)configDocY.get("dataname"));
									docconfig.setDefaultValue((String)configDocY.get("defaultValue"));
									docconfig.setDescription((String)configDocY.get("description"));
									docconfig.setRange((String)configDocY.get("range"));
									docconfig.setUnit((String)configDocY.get("unit"));

									config.setDoc(docconfig);
								}
								//Properties
								List configPropertiesListY = (List)configInfoY.get("rtcExt::properties");
								if( configPropertiesListY != null ) {
									for(int intIdxif=0;intIdxif<configPropertiesListY.size();intIdxif++ ) {
										Map ifPropInfoY = (Map)configPropertiesListY.get(intIdxif);
										if( ifPropInfoY != null ) {
											Property prop = factory.createProperty();
											prop.setName((String)ifPropInfoY.get("name"));
											prop.setValue((String)ifPropInfoY.get("value"));
											config.getProperties().add(prop);
										}
									}
								}
							}
						}
						profile.setConfigurationSet(configset);
					}
				}

				//Parameter
				List paramsInfoY = (List)profileY.get("parameters");
				if( paramsInfoY != null ) {
					for(int intIdx=0;intIdx<paramsInfoY.size();intIdx++ ) {
						Map paramInfoY = (Map)paramsInfoY.get(intIdx);
						if( paramInfoY != null ) {
							Parameter param = factory.createParameter();
							param.setName((String)paramInfoY.get("name"));
							param.setDefaultValue((String)paramInfoY.get("defaultValue"));
							profile.getParameters().add(param);
						}
					}
				}
				//Language
				Map langInfoY = (Map)profileY.get("language");
				if( langInfoY != null ) {
					LanguageExt language = factory.createLanguageExt();
					Map cxxInfoY = (Map)langInfoY.get("cxx");
					if( cxxInfoY != null ) {
						language.setKind("cxx");
						createTargetEnv(factory, language, cxxInfoY);
					}
					Map javaInfoY = (Map)langInfoY.get("java");
					if( javaInfoY != null ) {
						language.setKind("java");
						createTargetEnv(factory, language, javaInfoY);
					}
					if( langInfoY.get("python") != null ) {
						language.setKind("python");
					}
					if( langInfoY.get("Csharp") != null ) {
						language.setKind("Csharp");
					}
					if( langInfoY.get("Ruby") != null ) {
						language.setKind("Ruby");
					}
					profile.setLanguage(language);
				}
			}
		}
		return profile;
	}

	private void createTargetEnv(ObjectFactory factory, LanguageExt language, Map langInfoY) {
		TargetEnvironment env = factory.createTargetEnvironment();
		env.setOs((String)langInfoY.get("os"));
		env.setLangVersion((String)langInfoY.get("arch"));
		List libsInfoY = (List)langInfoY.get("library");
		if( libsInfoY != null ) {
			for( Object library : libsInfoY ) {
				Library lib = factory.createLibrary();
				lib.setName((String)library);
				env.getLibraries().add(lib);
			}
		}
		language.getTargets().add(env);
	}

	private ActionStatusDoc createActionFromYaml(Map actionY) {
		ObjectFactory factory = new ObjectFactory();
		ActionStatusDoc actionStatus = factory.createActionStatusDoc();
		Boolean impl = (Boolean)actionY.get("implemented");
		if( impl!=null) {
			actionStatus.setImplementedbln(impl.booleanValue());
		} else {
			actionStatus.setImplementedbln(false);
		}

		Map docActionY = (Map)actionY.get("rtcDoc::doc");
		if( docActionY != null ) {
			DocAction docAction = factory.createDocAction();
			docAction.setDescription((String)docActionY.get("description"));
			docAction.setPreCondition((String)docActionY.get("preCondition"));
			docAction.setPostCondition((String)docActionY.get("postCondition"));
			actionStatus.setDoc(docAction);
		}
		return actionStatus;
	}

	private DataportExt createDataPortFromYaml(Map yamlMap) {
		ObjectFactory factory = new ObjectFactory();
		DataportExt dataport = factory.createDataportExt();
		dataport.setDataflowType((String)yamlMap.get("dataflowType"));
		dataport.setIdlFile((String)yamlMap.get("idlFile"));
		dataport.setInterfaceType((String)yamlMap.get("interfaceType"));
		dataport.setName((String)yamlMap.get("name"));
		dataport.setPortType((String)yamlMap.get("portType"));
		if( yamlMap.get("rtcExt::position")!=null )
			dataport.setPosition(Position.fromValue(((String)yamlMap.get("rtcExt::position")).toUpperCase()));
		dataport.setSubscriptionType((String)yamlMap.get("subscriprionType"));
		dataport.setType((String)yamlMap.get("type"));
		dataport.setVariableName((String)yamlMap.get("rtcExt::varname"));
		//
		Map portdocY = (Map)yamlMap.get("rtcDoc::doc");
		if( portdocY != null ) {
			DocDataport docdataport = factory.createDocDataport();
			docdataport.setDescription((String)portdocY.get("description"));
			docdataport.setNumber((String)portdocY.get("number"));
			docdataport.setOccerrence((String)portdocY.get("occerrence"));
			docdataport.setOperation((String)portdocY.get("operation"));
			docdataport.setSemantics((String)portdocY.get("semantics"));
			docdataport.setType((String)portdocY.get("type"));
			docdataport.setUnit((String)portdocY.get("unit"));
			dataport.setDoc(docdataport);
		}
		//Properties
		List dpPropertiesListY = (List)yamlMap.get("rtcExt::properties");
		if( dpPropertiesListY != null ) {
			for(int intIdx=0;intIdx<dpPropertiesListY.size();intIdx++ ) {
				Map dpPropInfoY = (Map)dpPropertiesListY.get(intIdx);
				if( dpPropInfoY != null ) {
					Property prop = factory.createProperty();
					prop.setName((String)dpPropInfoY.get("name"));
					prop.setValue((String)dpPropInfoY.get("value"));
					dataport.getProperties().add(prop);
				}
			}
		}
		//
		return dataport;
	}

	private ServiceportExt createServicePortFromYaml(Map yamlMap) {
		ObjectFactory factory = new ObjectFactory();
		ServiceportExt serviceport = factory.createServiceportExt();
		serviceport.setName((String)yamlMap.get("name"));
		if( yamlMap.get("rtcExt::position")!=null )
			serviceport.setPosition(Position.fromValue(((String)yamlMap.get("rtcExt::position")).toUpperCase()));
		//
		Map portdocY = (Map)yamlMap.get("rtcDoc::doc");
		if( portdocY != null ) {
			DocServiceport docserviceport = factory.createDocServiceport();
			docserviceport.setDescription((String)portdocY.get("description"));
			docserviceport.setIfdescription((String)portdocY.get("ifdescription"));
			serviceport.setDoc(docserviceport);
		}

		//Properties
		List srvPropertiesListY = (List)yamlMap.get("rtcExt::properties");
		if( srvPropertiesListY != null ) {
			for(int intIdx=0;intIdx<srvPropertiesListY.size();intIdx++ ) {
				Map srvPropInfoY = (Map)srvPropertiesListY.get(intIdx);
				if( srvPropInfoY != null ) {
					Property prop = factory.createProperty();
					prop.setName((String)srvPropInfoY.get("name"));
					prop.setValue((String)srvPropInfoY.get("value"));
					serviceport.getProperties().add(prop);
				}
			}
		}

		//Service Interface
		List interfacesY = (List)yamlMap.get("serviceInterface");
		if( interfacesY != null ) {
			for(int intIdx=0;intIdx<interfacesY.size();intIdx++ ) {
				Map interfaceY = (Map)interfacesY.get(intIdx);
				ServiceinterfaceExt serviceIF = factory.createServiceinterfaceExt();
				serviceIF.setDirection((String)interfaceY.get("direction"));
				serviceIF.setIdlFile((String)interfaceY.get("idlFile"));
				serviceIF.setInstanceName((String)interfaceY.get("instanceName"));
				serviceIF.setName((String)interfaceY.get("name"));
				serviceIF.setPath((String)interfaceY.get("path"));
				serviceIF.setType((String)interfaceY.get("type"));
				serviceIF.setVariableName((String)interfaceY.get("varname"));
				//
				Map ifDocY = (Map)interfaceY.get("rtcDoc::doc");
				if( ifDocY != null ) {
					DocServiceinterface docserviceIF = factory.createDocServiceinterface();
					docserviceIF.setDescription((String)ifDocY.get("description"));
					docserviceIF.setDocArgument((String)ifDocY.get("docArgument"));
					docserviceIF.setDocException((String)ifDocY.get("docException"));
					docserviceIF.setDocPostCondition((String)ifDocY.get("docPostCondition"));
					docserviceIF.setDocPreCondition((String)ifDocY.get("docPreCondition"));
					docserviceIF.setDocReturn((String)ifDocY.get("docReturn"));
					serviceIF.setDoc(docserviceIF);
				}
				//Properties
				List ifPropertiesListY = (List)yamlMap.get("rtcExt::properties");
				if( ifPropertiesListY != null ) {
					for(int intIdxif=0;intIdxif<ifPropertiesListY.size();intIdxif++ ) {
						Map ifPropInfoY = (Map)ifPropertiesListY.get(intIdxif);
						if( ifPropInfoY != null ) {
							Property prop = factory.createProperty();
							prop.setName((String)ifPropInfoY.get("name"));
							prop.setValue((String)ifPropInfoY.get("value"));
							serviceIF.getProperties().add(prop);
						}
					}
				}
				serviceport.getServiceInterface().add(serviceIF);
			}
		}
		return serviceport;
	}
}
