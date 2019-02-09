package jp.go.aist.rtm.toolscommon.component;

import static org.junit.Assert.assertEquals;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;

import org.junit.Test;

public class OfflineCompositeTest {
	private SystemDiagram diagram;

	@Test
	public void createComposite() throws Exception {
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		diagram.getComponents().add(createComponent("ImageProcess"));
		diagram.getComponents().add(createComponent("CameraComponent"));

		Component s1 = createCompositeComponentSpecification();
		s1.addComponentsR(diagram.getComponents());

		assertEquals(2, s1.getComponents().size());
		assertEquals(2, s1.getExportedPorts().size());
		assertEquals("ImageProcess.CapPORT", s1.getExportedPorts().get(0));
		assertEquals("CameraComponent.CapPORT", s1.getExportedPorts().get(1));
		assertEquals(2, s1.getPorts().size());

		assertEquals(2, diagram.getComponents().size());

		diagram.getComponents().add(s1);
		diagram.getComponents().add(createComponent("ImageViewer"));

		assertEquals(4, diagram.getComponents().size());

		assertEquals(getPort(0,0).getOriginalPortString()
				, getPort(2, 0).getOriginalPortString());

		PortConnector connector = createConnector();
		connector.createConnectorR();

		assertEquals(getPort(0,0).getOriginalPortString()
				, connector.getConnectorProfile().getSourceString());

		verifyConnectCount(1, getPort(0,0));
		verifyConnectCount(0, getPort(1,0));
		verifyConnectCount(1, getPort(2,0));
		verifyConnectCount(0, getPort(2,1));
		verifyConnectCount(1, getPort(3,0));
	}

	private Port getPort(int componentIndex, int portIndex) {
		Component component = diagram.getComponents().get(componentIndex);
		return component.getPorts().get(portIndex);
	}
	private void verifyConnectCount(int count, Port port) {
		assertEquals(count, port.getConnectorProfiles().size());
	}

	private Component createComponent(String instanceName) {
		Component component = ComponentFactory.eINSTANCE.createComponentSpecification();
		component.setInstanceNameL(instanceName);
		component.getPorts().add(createPort());
		return component;
	}

	private Port createPort() {
		Port port = ComponentFactory.eINSTANCE.createPort();
		port.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port.setNameL("CapPORT");
		return port;
	}

	private Component createCompositeComponentSpecification() {
		Component component = ComponentFactory.eINSTANCE.createComponentSpecification();
		component.setInstanceNameL("s1");
		ConfigurationSet configSet = createConfigurationSet();
		component.getConfigurationSets().add(configSet);
		component.setActiveConfigurationSet(configSet);
		return component;
	}

	private ConfigurationSet createConfigurationSet() {
		ConfigurationSet configSet = ComponentFactory.eINSTANCE.createConfigurationSet();
		configSet.setId("default");
		configSet.getConfigurationData().add(createNameValur());
		return configSet;
	}

	private NameValue createNameValur() {
		NameValue result = ComponentFactory.eINSTANCE.createNameValue();
		result.setName("exported_ports");
		result.setValue("ImageProcess.CapPORT,CameraComponent.CapPORT");
		return result;
	}


	private PortConnector createConnector() {
		PortConnector connector = PortConnectorFactory.createPortConnectorSpecification();
		connector.setSource((diagram.getComponents().get(2)).getPorts().get(0));
		connector.setTarget((diagram.getComponents().get(3)).getPorts().get(0));
		connector.setConnectorProfile(createConnectorProfile());
		return connector;
	}

	private ConnectorProfile createConnectorProfile() {
		ConnectorProfile profile = ComponentFactory.eINSTANCE.createConnectorProfile();
		profile.setName("CapPORT_CapPORT");
		return profile;
	}

}
