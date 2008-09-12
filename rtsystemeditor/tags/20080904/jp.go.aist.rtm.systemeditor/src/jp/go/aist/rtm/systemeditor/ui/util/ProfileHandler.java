package jp.go.aist.rtm.systemeditor.ui.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.OfflineSystemDiagramEditor;
import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorTarget;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnectorSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.openrtp.namespaces.rts.ComponentExt;
import org.openrtp.namespaces.rts.ConfigurationData;
import org.openrtp.namespaces.rts.Dataport;
import org.openrtp.namespaces.rts.DataportConnector;
import org.openrtp.namespaces.rts.Location;
import org.openrtp.namespaces.rts.ObjectFactory;
import org.openrtp.namespaces.rts.Participant;
import org.openrtp.namespaces.rts.RtsProfileExt;
import org.openrtp.namespaces.rts.Serviceport;
import org.openrtp.namespaces.rts.ServiceportConnector;
import org.openrtp.namespaces.rts.TargetComponent;
import org.openrtp.namespaces.rts.TargetPort;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

public class ProfileHandler {
	
	private final String COMPOSITETYPE_NONE = "NONE";
	
	public SystemDiagram convertToSystemEditor(String targetFile, ArrayList baseInfo,
			AbstractSystemDiagramEditor targetEditor) throws Exception {
		RtsProfileExt profile = null;
		XmlHandler handler = new XmlHandler();
		profile = handler.loadXmlRts(targetFile);
		//
		SystemDiagram diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		diagram.setEditorId(OfflineSystemDiagramEditor.OFFLINE_SYSTEM_DIAGRAM_EDITOR_ID);
		diagram.setSystemId(profile.getId());
		diagram.setCreationDate(profile.getCreationDate().toString());
		diagram.setUpdateDate(profile.getUpdateDate().toString());
		//
		List<AbstractComponent> targetComposites = new ArrayList<AbstractComponent>();
		//
		for( Iterator iter = profile.getComponent().iterator(); iter.hasNext(); ) {
			ComponentExt component = (ComponentExt)iter.next();
			AbstractComponent targetComponent = null;
			if( component.getCompositeType().toUpperCase().equals(COMPOSITETYPE_NONE)) { 
				//Simple RTCの存在チェック，ロード
				ComponentSpecification spec = checkModels(component.getId(), baseInfo);
				if( spec==null ) {
					throw new Exception("Target Component does not exist in RepositoryView.");
				}
				targetComponent = (AbstractComponent) SystemEditorWrapperFactory.getInstance().copy(spec);
//				targetComponent.setOpenCompositeComponentAction(spec.getOpenCompositeComponentAction());
//			}
			} else {
				targetComponent = ComponentFactory.eINSTANCE.createComponentSpecification();
			}
			restoreBasicInfo(diagram, component, targetComponent);
			
			if( component.getCompositeType().toUpperCase().equals(COMPOSITETYPE_NONE)) { 
				//ConfigurationSet
				restoreConfigurationSet(component, targetComponent);
			} else {
				targetComponent.setCompsiteType(Component.COMPOSITE_COMPONENT);
				targetComposites.add(targetComponent);
			}
			diagram.getComponents().add(targetComponent);
		}
//		restorePortConnection(profile, diagram);
		//Composite Component
		for( AbstractComponent target : targetComposites) {
			for( Iterator iter = profile.getComponent().iterator(); iter.hasNext(); ) {
				ComponentExt component = (ComponentExt)iter.next();
				
				if( target.getPathId().equals(component.getPathUri()) &&
						target.getInstanceNameL().equals(component.getInstanceName()) ) {
					List<AbstractComponent> selectedComponents = getChildren(component, diagram.getComponents());
					target.getComponents().addAll(selectedComponents);
				}
			}
		}
		restorePortConnection(profile, diagram);
		//
		return diagram;
	}

	private void restoreBasicInfo(SystemDiagram diagram, ComponentExt component, AbstractComponent targetComponent) {
		if( component.getCompositeType().toUpperCase().equals(COMPOSITETYPE_NONE)) { 
			String pathUri = component.getPathUri();
			int compCount =	ComponentUtil.getComponentNumberByPathId(
					(ComponentSpecification) targetComponent, diagram);
			targetComponent.setPathId(pathUri + ":" + Integer.valueOf(compCount+1).toString() );
		} else {
			targetComponent.setPathId(component.getPathUri());
			((ComponentSpecification)targetComponent).setComponentId(component.getPathUri());
		}
		targetComponent.setInstanceNameL(component.getInstanceName());
		Rectangle constraint = new Rectangle();
		constraint.setX(component.getLocation().getX().intValue());
		constraint.setY(component.getLocation().getY().intValue());
		constraint.setWidth(component.getLocation().getWidth().intValue());
		constraint.setHeight(component.getLocation().getHeight().intValue());
		targetComponent.setConstraint(constraint);
		targetComponent.setOutportDirection(component.getLocation().getDirection());
	}

	private void restorePortConnection(RtsProfileExt profile, SystemDiagram diagram) {
		for( Iterator iter = profile.getComponent().iterator(); iter.hasNext(); ) {
			ComponentExt component = (ComponentExt)iter.next();
			//DataPort接続
			List<Dataport> dataPortListBase = component.getDataPorts();
			for( Dataport dataPortBase : dataPortListBase) {
				List<DataportConnector> connListBase = dataPortBase.getDataPortConnectors();
				if( connListBase!=null ) {
					for( DataportConnector connBase : connListBase) {
						ConnectorProfile conn = ComponentFactory.eINSTANCE.createConnectorProfile();
						conn.setConnectorId(connBase.getConnectorId());
						conn.setName(connBase.getName());
						conn.setInterfaceType(connBase.getInterfaceType());
						conn.setDataType(connBase.getDataType());
						conn.setDataflowType(connBase.getDataflowType());
						if(connBase.getSubscriptionType()!=null) conn.setSubscriptionType(connBase.getSubscriptionType());
						if(connBase.getPushInterval()!=null) conn.setPushRate(connBase.getPushInterval());

						connectPorts(component, conn, diagram.getComponents(),
								connBase.getTargetDataPort(), dataPortBase.getName());
					}
				}
			}
			//ServicePort接続
			List<Serviceport> servicePortListBase = component.getServicePorts();
			for( Serviceport servicePortBase : servicePortListBase) {
				List<ServiceportConnector> connListBase = servicePortBase.getServicePortConnectors();
				if( connListBase!=null ) {
					for( ServiceportConnector connBase : connListBase) {
						ConnectorProfile conn = ComponentFactory.eINSTANCE.createConnectorProfile();
						conn.setConnectorId(connBase.getConnectorId());
						conn.setName(connBase.getName());

						connectPorts(component, conn, diagram.getComponents(), 
								connBase.getTargetServicePort(), servicePortBase.getName());
					}
				}
			}
		}
	}
	private List<AbstractComponent> getChildren(ComponentExt component, List components) {
		List<AbstractComponent> result = new ArrayList<AbstractComponent>();
		for(TargetComponent target : component.getParticipants().getParticipant() ) {
			for( Object trgComp : components ) {
				if( target.getComponentId().equals(((ComponentSpecification)trgComp).getComponentId()) &&
						target.getInstanceName().equals(((ComponentSpecification)trgComp).getInstanceNameL())) {
					result.add((ComponentSpecification)trgComp);
					break;
				}
			}
		}
		return result;
	}

	private void connectPorts(ComponentExt component, ConnectorProfile conn, List components, TargetPort target, String sourcePortName) {
		PortConnectorSpecification connector = ComponentFactory.eINSTANCE.createPortConnectorSpecification();
		connector.setSource(getTargetPortforRestore(
				component.getId(), component.getInstanceName(),	sourcePortName,	components));
		connector.setTarget(getTargetPortforRestore(
				target.getComponentId(), target.getInstanceName(), target.getPortName(), components));
		connector.setConnectorProfile(conn);
		connector.createConnectorR();
	}

	private void restoreConfigurationSet(ComponentExt component, AbstractComponent copy) {
		String activeId = component.getActiveConfigurationSet();
		List<org.openrtp.namespaces.rts.ConfigurationSet> configSetsBase = component.getConfigurationSets();
		copy.getConfigurationSets().clear();
		for( org.openrtp.namespaces.rts.ConfigurationSet configBase : configSetsBase ) {
			jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet configSet = ComponentFactory.eINSTANCE.createConfigurationSet();
			configSet.setId(configBase.getId());
			List<ConfigurationData> confdataBase = configBase.getConfigurationData();
			if(activeId!=null && activeId.equals(configBase.getId()) ) {
				copy.setActiveConfigurationSet(configSet);
			}
			for( ConfigurationData dataBase : confdataBase ) {
				NameValue value = ComponentFactory.eINSTANCE.createNameValue();
				value.setName(dataBase.getName());
				value.setValueAsString(dataBase.getData());
				configSet.getConfigurationData().add(value);
			}
			copy.getConfigurationSets().add(configSet);
		}
	}

	private ComponentSpecification checkModels(String strId, List models) {
		ComponentSpecification result = null;
		
		for(Iterator iter=models.iterator(); iter.hasNext();) {
			RepositoryViewItem item = (RepositoryViewItem)iter.next();
			if( item instanceof RepositoryViewLeafItem ) {
				ComponentSpecification target = ((RepositoryViewLeafItem)item).getComponent();
				if( target == null) return null;
				if(target.getComponentId().equals(strId)) return target;
			} else {
				if( item.getChildren() != null ) {
					result = checkModels(strId, item.getChildren());
					if( result!=null ) return result;
				}
			}
		}
		return result;
	}
	
	private Port getTargetPortforRestore(String componentId, String instanceName, String portName, List components) {
		Port result = null;
		
		for( Object trgComp : components ) {
			if( componentId.equals(((ComponentSpecification)trgComp).getComponentId()) &&
					instanceName.equals(((ComponentSpecification)trgComp).getInstanceNameL())) {
				for(Object trgPort : ((ComponentSpecification)trgComp).getPorts() ) {
					if(portName.equals(((Port)trgPort).getPortProfile().getNameL()) ) {
						return (Port)trgPort;
					}
				}
						
			}
		}
		
		return result;
	}
	
	public RtsProfileExt convertToProfile(EObject graphPart) {
		RtsProfileExt profile = null;
		ObjectFactory factory = new ObjectFactory();
		EObject systemRoot = EcoreUtil.getRootContainer(graphPart);
		if( systemRoot != null ) {
			profile = factory.createRtsProfileExt();
			profile.setId(((SystemDiagram)systemRoot).getSystemId());
			DatatypeFactory dateFactory = new DatatypeFactoryImpl();
			profile.setCreationDate(dateFactory.newXMLGregorianCalendar(((SystemDiagram)systemRoot).getCreationDate()));
			profile.setUpdateDate(dateFactory.newXMLGregorianCalendar(((SystemDiagram)systemRoot).getUpdateDate()));
			profile.setVersion("1.0");
			//変更履歴は未設定
			List<String> savedConnectors = new ArrayList<String>();
			for( Iterator iter = systemRoot.eAllContents(); iter.hasNext(); ) {
				Object obj = iter.next();
				if( obj instanceof ComponentSpecification ) {
					ComponentSpecification targetComp = (ComponentSpecification)obj;
					ComponentExt component = saveBasicInfo(targetComp, factory);
					component.setLocation(saveLocation(targetComp, factory));
					//ConfigurationSets
					saveConfigurationSets(factory, targetComp, component);

					if( targetComp.getCompsiteTypeStr().toUpperCase().equals(COMPOSITETYPE_NONE)) {
						//DataInPorts
						saveDataPorts(factory, targetComp, component);
						//ServicePorts
						saveServicePorts(factory, targetComp, component, savedConnectors);
					}
					profile.getComponent().add(component);
				}
			}
		}
		return profile;
	}

	private ComponentExt saveBasicInfo(ComponentSpecification targetComp, ObjectFactory factory) {
		ComponentExt component = factory.createComponentExt();
		component.setId(targetComp.getComponentId());
		if(targetComp.getCompsiteTypeStr().toUpperCase().equals(COMPOSITETYPE_NONE)) {
			component.setPathUri(targetComp.getPathURI());
		} else {
			component.setPathUri(targetComp.getPathId());
			component.setId(targetComp.getPathId());
		}
		component.setInstanceName(targetComp.getInstanceNameL());
		if( targetComp.getActiveConfigurationSet() != null ) {
			component.setActiveConfigurationSet(
					targetComp.getActiveConfigurationSet().getId());
		}
		component.setCompositeType(targetComp.getCompsiteTypeStr());
		if(targetComp.getComponents().size()>0) {
			Participant participant = factory.createParticipant();
			for(Object childBase : targetComp.getComponents() ) {
				TargetComponent child = factory.createTargetComponent();
				if( ((ComponentSpecification)childBase).getCompsiteTypeStr().toUpperCase().equals(COMPOSITETYPE_NONE)) {
					child.setComponentId(((ComponentSpecification)childBase).getComponentId());
				} else {
					child.setComponentId(((ComponentSpecification)childBase).getPathId());
				}
				child.setInstanceName(((ComponentSpecification)childBase).getInstanceNameL());
				participant.getParticipant().add(child);
			}
			component.setParticipants(participant);
		}
		if( targetComp.getActiveConfigurationSet()!=null ) {
			component.setActiveConfigurationSet(targetComp.getActiveConfigurationSet().getId());
		}
		component.setIsRequired(true);
		return component;
	}

	private void saveConfigurationSets(ObjectFactory factory, ComponentSpecification targetComp, ComponentExt component) {
		List<ConfigurationSet> configs = targetComp.getConfigurationSets();
		if( configs != null && configs.size()>0 ) {
			for( Iterator iterconf = configs.iterator(); iterconf.hasNext(); ) {
				ConfigurationSet baseConfig = (ConfigurationSet)iterconf.next();
				component.getConfigurationSets().add(saveConfigurationSet(baseConfig, factory));
			}
		}
		if( targetComp.getActiveConfigurationSet()!=null ) {
			component.setActiveConfigurationSet(
					targetComp.getActiveConfigurationSet().getId());
		}
	}

	private org.openrtp.namespaces.rts.ConfigurationSet saveConfigurationSet(ConfigurationSet baseConfig, ObjectFactory factory) {
		
		org.openrtp.namespaces.rts.ConfigurationSet config = factory.createConfigurationSet();
		config.setId(baseConfig.getId());
		List<NameValue> nvlistBase = baseConfig.getConfigurationData();
		if( nvlistBase!=null ) {
			for( Iterator iternv = nvlistBase.iterator(); iternv.hasNext(); ) {
				NameValue nvBase = (NameValue)iternv.next();
				ConfigurationData nv = factory.createConfigurationData();
				nv.setName(nvBase.getName());
				nv.setData(nvBase.getValueAsString());
				config.getConfigurationData().add(nv);
			}
		}
		return config;
	}

	private boolean isConnected(ComponentExt component, Port portBase) {
		return component.getCompositeType().toUpperCase().equals(COMPOSITETYPE_NONE) &&
				(portBase.getSourceConnectors().size() > 0 ||
						portBase.getTargetConnectors().size() > 0);
	}

	private void saveDataPorts(ObjectFactory factory, ComponentSpecification targetComp, ComponentExt component) {
		List<Port> outports = targetComp.getAllOutPorts();
		if( outports != null && outports.size()>0 ) {
			for( Iterator iterport = outports.iterator(); iterport.hasNext(); ) {
				OutPort portBase = (OutPort)iterport.next();
				if(isConnected(component, portBase) ) {
					Dataport port = factory.createDataport();
					port.setName(portBase.getPortProfile().getNameL());
					component.getDataPorts().add(port);
					for(Object source: portBase.getSourceConnectors() ) {
						ConnectorProfile profile = ((PortConnectorSpecification)source).getConnectorProfile();
						port.getDataPortConnectors().add(saveDataPortConnector(factory, portBase, profile));
					}
					for(Object target: portBase.getTargetConnectors() ) {
						ConnectorProfile profile = ((PortConnectorSpecification)target).getConnectorProfile();
						port.getDataPortConnectors().add(saveDataPortConnector(factory, portBase, profile));
					}
				}
			}
		}
	}

	private DataportConnector saveDataPortConnector(ObjectFactory factory, OutPort portBase, ConnectorProfile profile) {
		DataportConnector connector = factory.createDataportConnector();
		connector.setConnectorId(profile.getConnectorId());
		connector.setName(profile.getName());
		connector.setInterfaceType(profile.getInterfaceType());
		connector.setDataType(profile.getDataType());
		connector.setDataflowType(profile.getDataflowType());
		if(profile.getSubscriptionType()!=null) connector.setSubscriptionType(profile.getSubscriptionType());
		if(profile.getPushRate()!=null) connector.setPushInterval(profile.getPushRate());
		connector.setTargetDataPort(getTargetPortforSave(profile, factory, portBase));
		return connector;
	}

	private void saveServicePorts(ObjectFactory factory, ComponentSpecification targetComp, ComponentExt component, List<String> savedConnectors) {
		List<Port> serviceports = targetComp.getAllServiceports();
		boolean isTarget =false;
		if( serviceports != null && serviceports.size()>0 ) {
			for( Iterator iterport = serviceports.iterator(); iterport.hasNext(); ) {
				isTarget =false;
				ServicePort portBase = (ServicePort)iterport.next();
				if(isConnected(component, portBase) ) {
					org.openrtp.namespaces.rts.Serviceport port = factory.createServiceport();
					port.setName(portBase.getPortProfile().getNameL());
					for(Object source: portBase.getSourceConnectors() ) {
						if( !savedConnectors.contains(((PortConnectorSpecification)source).getConnectorProfile().getConnectorId()) ) { 
							ConnectorProfile profile = ((PortConnectorSpecification)source).getConnectorProfile();
							port.getServicePortConnectors().add(saveServicePortConnector(factory, portBase, profile));
							savedConnectors.add(((PortConnectorSpecification)source).getConnectorProfile().getConnectorId());
							isTarget = true;
						}
					}
					for(Object target: portBase.getTargetConnectors() ) {
						if( !savedConnectors.contains(((PortConnectorSpecification)target).getConnectorProfile().getConnectorId()) ) { 
							ConnectorProfile profile = ((PortConnectorSpecification)target).getConnectorProfile();
							port.getServicePortConnectors().add(saveServicePortConnector(factory, portBase, profile));
							savedConnectors.add(((PortConnectorSpecification)target).getConnectorProfile().getConnectorId());
							isTarget = true;
						}
					}
					if( isTarget ) component.getServicePorts().add(port);
				}
			}
		}
	}

	private ServiceportConnector saveServicePortConnector(ObjectFactory factory, Port portBase, ConnectorProfile source) {
		ServiceportConnector connector = factory.createServiceportConnector();
		connector.setConnectorId(source.getConnectorId());
		connector.setName(source.getName());
		connector.setTargetServicePort(getTargetPortforSave(source,factory,portBase));
		return connector;
	}

	private Location saveLocation(ComponentSpecification targetComp, ObjectFactory factory) {
		Location location = factory.createLocation();
		location.setX(BigInteger.valueOf(targetComp.getConstraint().getX()));
		location.setY(BigInteger.valueOf(targetComp.getConstraint().getY()));
		location.setWidth(BigInteger.valueOf(targetComp.getConstraint().getWidth()));
		location.setHeight(BigInteger.valueOf(targetComp.getConstraint().getHeight()));
		location.setDirection(targetComp.getOutportDirectionStr());
		return location;
	}

	private TargetPort getTargetPortforSave(ConnectorProfile source, ObjectFactory factory, Port basePort) {
		ConnectorTarget target = ((PortConnectorSpecification)source.eContainer()).getTarget();
		if(target==null || target==basePort) {
			target = (ConnectorTarget)((PortConnectorSpecification)source.eContainer()).eContainer();
		}
		TargetPort port = factory.createTargetPort();
		port.setComponentId(((ComponentSpecification)target.eContainer()).getComponentId());
		port.setInstanceName(((ComponentSpecification)target.eContainer()).getInstanceNameL());
		port.setPortName(((Port)target).getPortProfile().getNameL());
		
		return port;
	}
}
