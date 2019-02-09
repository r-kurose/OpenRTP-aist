package jp.go.aist.rtm.systemeditor.ui.util;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;
import junit.framework.TestCase;

import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.ObjectFactory;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.TargetPort;

public class RestoreConnectionTest extends TestCase {
	private Port port1;
	private Port port2;
	private Port port3;

	private Component component1;
	private Component component2;
	private Component component3;
	private Component component4;

	private SystemDiagram diagram;
	private RtsProfileExt profile;
	private ObjectFactory factory;

	public void testRestoreConnection() throws Exception {
		RtsProfileHandler handler = new RtsProfileHandler();
		handler.restoreConnection(diagram);
		verifyConnectorCount(port1, 1);
		verifyConnectorCount(port2, 0);
		verifyConnectorCount(port3, 1);
		ConnectorProfile connector1 = port1.getConnectorProfiles().get(0);
//		assertEquals(port3, connector1.getTarget());
		assertEquals(port3.getOriginalPortString(), connector1.getTargetString());
		ConnectorProfile connector2 = port3.getConnectorProfiles().get(0);
//		assertEquals(port1, connector2.getSource());
		assertEquals(port1.getOriginalPortString(), connector2.getSourceString());
	}

	private void verifyConnectorCount(Port port, int profileCount) {
		assertEquals(profileCount, port.getConnectorProfiles().size());
	}

	@Override
	protected void setUp() throws Exception {
		setupPort1();
		setupPort2();
		setupPort3();

		setupComponent1();
		setupComponent2();
		setupComponent3();
		setupComponent4();

		setupProfile();
		setupDiagram();
	}

	private void setupPort1() {
		port1 = ComponentFactory.eINSTANCE.createOutPort();
		port1.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port1.setNameL("CameraComponent_1.ImageData");
		port1.setOriginalPortString(getOriginalPortString("RTC:Sample Vender.example.CameraComponent:1.0.0", "", "CameraComponent_1", "CameraComponent_1.ImageData"));
	}
	private void setupPort2() {
		port2 = ComponentFactory.eINSTANCE.createInPort();
		port2.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port2.setNameL("ImageViewer_1.ImageDataIn");
		port2.setOriginalPortString(getOriginalPortString("RTC:Sample Vender.example.ImageViewer:1.0.0", "", "ImageViewer_1", "ImageViewer_1.ImageDataIn"));
	}
	private void setupPort3() {
		port3 = ComponentFactory.eINSTANCE.createInPort();
		port3.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port3.setNameL("ImageViewer_1.ImageDataIn");
		port3.setOriginalPortString(getOriginalPortString("RTC:Sample Vender.example.ImageViewer:1.0.0", "", "ImageViewer_1", "ImageViewer_1.ImageDataIn"));
	}

	private void setupProfile() {
		factory = new ObjectFactory();
		profile = factory.createRtsProfileExt();
		profile.getDataPortConnectors().add(setupDataportConnector());
	}

	private DataportConnector setupDataportConnector() {
		DataportConnector result = factory.createDataportConnector();
		result.setConnectorId("cfc3257f-ba1e-4953-8c87-f1e7718e265f");
		result.setSourceDataPort(setupSourceDataport());
		result.setTargetDataPort(setupTargetdataport());
		return result;
	}

	private TargetPort setupSourceDataport() {
		TargetPort result = factory.createTargetPort();
		result.setComponentId("RTC:Sample Vender.example.CameraComponent:1.0.0");
		result.setInstanceName("CameraComponent_1");
		result.setPortName("CameraComponent_1.ImageData");
		return result;
	}

	private TargetPort setupTargetdataport() {
		TargetPort result = factory.createTargetPort();
		result.setComponentId("RTC:.composite.PeriodicECShared.:");
		result.setInstanceName("gg");
		result.setPortName("ImageViewer_1.ImageDataIn");
		return result;
	}

	private void setupComponent1() {
		component1 = createComponent("RTC:Sample Vender.example.CameraComponent:1.0.0", "CameraComponent_1");
		component1.getPorts().add(port1);
	}
	private void setupComponent2() {
		component2 = createComponent("RTC:Sample Vender.example.ImageProcess:1.0.0", "ImageProcess_1");
	}
	private void setupComponent3() {
		component3 = createComponent("RTC:Sample Vender.example.ImageViewer:1.0.0", "ImageViewer_1");
		component3.getPorts().add(port2);
	}
	private void setupComponent4() {
		component4 = createComponent("RTC:.composite.PeriodicECShared.:", "gg");
		component4.getPorts().add(port3);
//		component4.getComponents().add(component2);
//		component4.getComponents().add(component3);
	}

	private Component createComponent(String componentId, String instanceName) {
		Component result = ComponentFactory.eINSTANCE.createComponentSpecification();
		result.setComponentId(componentId);
		result.setPathId("");
		result.setInstanceNameL(instanceName);
		return result;
	}

	private void setupDiagram() {
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		diagram.getComponents().add(component1);
		diagram.getComponents().add(component2);
		diagram.getComponents().add(component3);
		diagram.getComponents().add(component4);
		diagram.setProfile(profile);
	}


	private String getOriginalPortString(String componentId, String pathId,
			String instanceName, String portName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{componentId:").append(componentId);
		buffer.append(",pathId:").append(pathId);
		buffer.append(",instanceName:").append(instanceName);
		buffer.append(",portName:").append(portName).append("}");
		return buffer.toString();
	}

}
