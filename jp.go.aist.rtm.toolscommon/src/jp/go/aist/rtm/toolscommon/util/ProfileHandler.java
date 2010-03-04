package jp.go.aist.rtm.toolscommon.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version02.BasicInfo;
import org.openrtp.namespaces.rtc.version02.DataportExt;
import org.openrtp.namespaces.rtc.version02.RtcProfile;
import org.openrtp.namespaces.rtc.version02.ServiceinterfaceDoc;
import org.openrtp.namespaces.rtc.version02.ServiceportExt;

//
/**
 * RTCプロファイルを読み込むクラス
 *
 */
public class ProfileHandler {
	private static final String PORTTYPE_IN = "DataInPort";
	private static final String SPEC_SUFFIX = "RTC";
	private static final String SPEC_MAJOR_SEPARATOR = ":";
	private static final String SPEC_MINOR_SEPARATOR = ".";
	
	private static final String INTERFACE_DIRECTION_PROVIDED = "Provided";
	private static final String INTERFACE_DIRECTION_REQUIRED = "Required";

	private final String CATEGORY_COMPOSITE =  "composite.";

	public static boolean validateXml(String targetString) throws Exception {
		XmlHandler handler = new XmlHandler();
		try {
			handler.restoreFromXmlRtc(targetString);
		} catch (IOException e) {
			throw new Exception("XML Validation Error", e);
		}
		return true;
	}

	public ComponentSpecification createComponentFromXML(String targetXML) throws Exception {
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(targetXML);
		
		ComponentSpecification component = profile2ComponentEMF(profile, null);
		return component;
	}

	public ComponentSpecification createComponent(String targetFile) throws Exception  {
		
		//対象ファイルの読み込み
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8"));
		String tmp_str = null;
		StringBuffer tmp_sb = new StringBuffer();
	    while((tmp_str = br.readLine()) != null){
	    	tmp_sb.append(tmp_str + "\r\n");
	    }
	    br.close();

	    ComponentSpecification component = createComponentFromXML(tmp_sb.toString());
		return component;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private ComponentSpecification profile2ComponentEMF(RtcProfile module,
			ComponentSpecification specification) throws Exception {
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
		specification.getPorts().addAll(profile2PortEMF(module));

		// BasicInfoからデフォルトのExecutionContextを作成
		jp.go.aist.rtm.toolscommon.model.component.ExecutionContext ec = ComponentFactory.eINSTANCE
				.createExecutionContext();
		String type = bi.getActivityType();
		if ("PERIODIC".equals(type)) {
			ec.setKindL(ec.KIND_PERIODIC);
		} else if ("SPORADIC".equals(type)) {
			ec.setKindL(ec.KIND_OTHER);
		} else if ("EVENTDRIVEN".equals(type)) {
			ec.setKindL(ec.KIND_EVENT_DRIVEN);
		} else {
			ec.setKindL(ec.KIND_UNKNOWN);
		}
		ec.setRateL(bi.getExecutionRate());
		specification.setExecutionContext("default", ec);

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

		return specification;
	}
	
	private boolean checkProfile(RtcProfile module) {
		if( !module.getBasicInfo().getCategory().startsWith(CATEGORY_COMPOSITE) ) return true;
		if( module.getDataPorts().size()>0 ) return false;
		if( module.getServicePorts().size()>0 ) return false;
			
		return true;
	}

	@SuppressWarnings("unchecked")
	private List<Port> profile2PortEMF(RtcProfile module) {
		List<Port> list = new ArrayList<Port>();
		Port port = null;
		for( Iterator iterator = module.getDataPorts().iterator(); iterator.hasNext(); ) {
			DataportExt dataPort = (DataportExt)iterator.next();
			if( dataPort.getPortType().equals(PORTTYPE_IN)) {
				port = ComponentFactory.eINSTANCE.createInPort();
			} else {
				port = ComponentFactory.eINSTANCE.createOutPort();
			}
			port.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
			port.setNameL(dataPort.getName());
			port.setDataType(dataPort.getType());
			
			list.add(port);
		}
		for (Iterator iterator = module.getServicePorts().iterator(); iterator.hasNext();) {
			ServiceportExt servicePortExt = (ServiceportExt) iterator.next();
	
			port = ComponentFactory.eINSTANCE.createServicePort();
			port.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
			port.setNameL(servicePortExt.getName());
			
			for( int intIdx=0; intIdx<servicePortExt.getServiceInterface().size(); intIdx++) {
				ServiceinterfaceDoc serviceIF = (ServiceinterfaceDoc)servicePortExt.getServiceInterface().get(intIdx);
				PortInterfaceProfile portIF = new PortInterfaceProfile();
				portIF.setInstanceName(serviceIF.getName());
				portIF.setTypeName(serviceIF.getType());
				if( serviceIF.getDirection().equals(INTERFACE_DIRECTION_PROVIDED) ) {
					portIF.setProvidedPolarity();
				} else if( serviceIF.getDirection().equals(INTERFACE_DIRECTION_REQUIRED) ) {
					portIF.setRequiredPolarity();
				}
				port.getInterfaces().add(portIF);
			}

			list.add(port);
		}
		return list;
	}
}
