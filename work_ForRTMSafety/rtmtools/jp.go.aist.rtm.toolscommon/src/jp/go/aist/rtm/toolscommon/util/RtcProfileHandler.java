package jp.go.aist.rtm.toolscommon.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version02.BasicInfo;
import org.openrtp.namespaces.rtc.version02.BasicInfoExt;
import org.openrtp.namespaces.rtc.version02.Dataport;
import org.openrtp.namespaces.rtc.version02.DataportExt;
import org.openrtp.namespaces.rtc.version02.Property;
import org.openrtp.namespaces.rtc.version02.RtcProfile;
import org.openrtp.namespaces.rtc.version02.Serviceinterface;
import org.openrtp.namespaces.rtc.version02.ServiceinterfaceDoc;
import org.openrtp.namespaces.rtc.version02.Serviceport;
import org.openrtp.namespaces.rtc.version02.ServiceportExt;

//
/**
 * RTCプロファイルを読み込むクラス
 *
 */
public class RtcProfileHandler {
	private static final String PORTTYPE_IN = "DataInPort";
	private static final String SPEC_SUFFIX = "RTC";
	private static final String SPEC_MAJOR_SEPARATOR = ":";
	// private static final String SPEC_MINOR_SEPARATOR = ".";
	
	private static final String INTERFACE_DIRECTION_PROVIDED = "Provided";
	private static final String INTERFACE_DIRECTION_REQUIRED = "Required";

	private final String CATEGORY_COMPOSITE =  "composite.";

	public static boolean validateXml(String targetString) throws Exception {
		System.out.println("validateXml entry");
		XmlHandler handler = new XmlHandler();
		try {
			System.out.print("validateXml 010 "+targetString);
			handler.restoreFromXmlRtc(targetString);
		} catch (IOException e) {
			throw new Exception("XML Validation Error", e);
		}
		System.out.println("validateXml return");
		return true;
	}

	public ComponentSpecification createComponentFromXML(String targetXML) throws Exception {
		System.out.println("createComponentFromXML entry");
		XmlHandler handler = new XmlHandler();
		System.out.println("createComponentFromXML 010 "+targetXML);
		RtcProfile profile = handler.restoreFromXmlRtc(targetXML);
		System.out.println("createComponentFromXML 011 ");
		System.out.println("createComponentFromXML 012 Language="+profile.getLanguage().getKind());
		
		ComponentSpecification component = profile2ComponentEMF(profile, null);
		System.out.println("createComponentFromXML 015 Language="+profile.getLanguage().getKind());
		component.setLanguage(profile.getLanguage().getKind());
		System.out.println("createComponentFromXML 016 Language="+component.getLanguage());
		System.out.println("createComponentFromXML 020 "+component.getRtcType());
		System.out.println("createComponentFromXML return");
		return component;
	}

	public ComponentSpecification createComponent(String targetFile) throws Exception  {
		
		System.out.println("createComponent entry");
		System.out.println("createComponent 010 "+targetFile);
		//対象ファイルの読み込み
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8"));
		String tmp_str = null;
		StringBuffer tmp_sb = new StringBuffer();
	    while((tmp_str = br.readLine()) != null){
	    	tmp_sb.append(tmp_str + "\r\n");
	    }
	    br.close();

	    ComponentSpecification component = createComponentFromXML(tmp_sb.toString());
		System.out.println("createComponent return");
		return component;
	}

	@SuppressWarnings("static-access")
	private ComponentSpecification profile2ComponentEMF(RtcProfile module,
			ComponentSpecification specification) throws Exception {
		System.out.println("profile2ComponentEMF entry");
		if( !checkProfile(module) ) throw new Exception("Incorrect Profile.");
		if (specification == null) {
			specification = ComponentFactory.eINSTANCE.createComponentSpecification();
		}
		BasicInfo bi = module.getBasicInfo();
		specification.setInstanceNameL(bi.getName());
		specification.setTypeNameL(bi.getName());
		specification.setCategoryL(bi.getCategory());
		specification.setVenderL(bi.getVendor());
		specification.setVersionL(bi.getVersion());
		specification.setDescriptionL(bi.getDescription());
		specification.setRtcType(bi.getRtcType());
		specification.getPorts().addAll(profile2PortEMF(module));
		//<+zxc
		//zxc+>

		if (bi instanceof BasicInfoExt) {
			BasicInfoExt biExt = (BasicInfoExt) bi;
			for (Property p : biExt.getProperties()) {
				specification.setProperty(p.getName(), p.getValue());
			}
		}

		// BasicInfoからデフォルトのExecutionContextを作成
		jp.go.aist.rtm.toolscommon.model.component.ExecutionContext ec = ComponentFactory.eINSTANCE
				.createExecutionContext();
		String type = bi.getExecutionType();
		if ("PeriodicExecutionContext".equals(type)) {
			ec.setKindL(ec.KIND_PERIODIC);
		} else if ("ExtTrigExecutionContext".equals(type)) {
			ec.setKindL(ec.KIND_EVENT_DRIVEN);
		} else {
			ec.setKindL(ec.KIND_OTHER);
		}
		ec.setRateL(bi.getExecutionRate());
		ec.setOwner(specification);
		specification.getExecutionContexts().add(ec);
		specification.getExecutionContextHandler().sync();

		// Constraint設定とWidget設定に対応 2008.12.25
		RTCConfigurationParser parser = new RTCConfigurationParser();
		List<ConfigurationSet> configurationSets = parser.parse(module);
		if (configurationSets != null) {
			specification.getConfigurationSets().addAll(configurationSets);
			specification.setActiveConfigurationSet(parser
					.getActiveConfigurationSet());
		}

		String moduleId = SPEC_SUFFIX + SPEC_MAJOR_SEPARATOR
				+ specification.getVenderL() + SPEC_MAJOR_SEPARATOR
				+ specification.getCategoryL() + SPEC_MAJOR_SEPARATOR
				+ specification.getTypeNameL() + SPEC_MAJOR_SEPARATOR
				+ specification.getVersionL();
		specification.setComponentId(moduleId);

		System.out.println("profile2ComponentEMF return");
		return specification;
	}
	
	private boolean checkProfile(RtcProfile module) {
		if( !module.getBasicInfo().getCategory().startsWith(CATEGORY_COMPOSITE) ) return true;
		if( module.getDataPorts().size()>0 ) return false;
		if( module.getServicePorts().size()>0 ) return false;
			
		return true;
	}

	private List<Port> profile2PortEMF(RtcProfile module) {
		List<Port> list = new ArrayList<Port>();
		Port port = null;
		for (Dataport dp : module.getDataPorts()) {
			DataportExt dataPortExt = (DataportExt) dp;
			if (dataPortExt.getPortType().equals(PORTTYPE_IN)) {
				port = ComponentFactory.eINSTANCE.createInPort();
			} else {
				port = ComponentFactory.eINSTANCE.createOutPort();
			}
			port.setSynchronizer(ComponentFactory.eINSTANCE
					.createPortSynchronizer());
			port.setNameL(dataPortExt.getName());
			port.setDataType(dataPortExt.getType());
			for (Property p : dataPortExt.getProperties()) {
				port.getSynchronizer().setProperty(p.getName(), p.getValue());
			}
			list.add(port);
		}

		for (Serviceport sp : module.getServicePorts()) {
			ServiceportExt servicePortExt = (ServiceportExt) sp;
			port = ComponentFactory.eINSTANCE.createServicePort();
			port.setSynchronizer(ComponentFactory.eINSTANCE
					.createPortSynchronizer());
			port.setNameL(servicePortExt.getName());
			for (Property p : servicePortExt.getProperties()) {
				port.getSynchronizer().setProperty(p.getName(), p.getValue());
			}

			for (Serviceinterface si : servicePortExt.getServiceInterface()) {
				ServiceinterfaceDoc serviceIF = (ServiceinterfaceDoc) si;
				PortInterfaceProfile portIF = new PortInterfaceProfile();
				portIF.setInstanceName(serviceIF.getName());
				portIF.setTypeName(serviceIF.getType());
				if (serviceIF.getDirection().equals(
						INTERFACE_DIRECTION_PROVIDED)) {
					portIF.setProvidedPolarity();
				} else if (serviceIF.getDirection().equals(
						INTERFACE_DIRECTION_REQUIRED)) {
					portIF.setRequiredPolarity();
				}
				port.getInterfaces().add(portIF);
			}
			list.add(port);
		}
		return list;
	}

}
