package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import junit.framework.TestCase;

public class ExportActionOfflineTest extends TestCase {
	private SystemDiagram diagram1;
	private SystemDiagram diagram2;

	private ComponentSpecification component1;
	private ComponentSpecification component2;

	private Port port1;
	private Port port2;
	private Port port3;

	private ExportPortAction action;

	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		diagram1 = createSystemDiagram();
		diagram2 = createSystemDiagram();
		
		component1 = ComponentFactory.eINSTANCE.createComponentSpecification();
		component1.setInstanceNameL("child");
		
		component2 = ComponentFactory.eINSTANCE.createComponentSpecification();
		component2.setActiveConfigurationSet(ExportPortActionTest.setupConfigurationSet());
		component2.getConfigurationSets().add(component2.getActiveConfigurationSet());
		component2.getComponents().add(component1);

		diagram1.getComponents().add(component1);
		diagram1.setCompositeComponent(component2);
		diagram2.getComponents().add(component2);
		

		port1 = createPort("1", "port1");
		port2 = createPort("2", "port2");
		port3 = createPort("2", "child.port2");

		component1.getPorts().add(port1);
		component1.getPorts().add(port2);
		component2.getPorts().add(port3);

		action = new ExportPortAction();
		action.setParent(component2);
	}
	
	private SystemDiagram createSystemDiagram() {
		SystemDiagram diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setKind(SystemDiagramKind.OFFLINE_LITERAL);
		return diagram;
	}

	private Port createPort(String originalPortString, String portName) {
		Port result = ComponentFactory.eINSTANCE.createPort();
		result.setNameL(portName);
		result.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		result.setOriginalPortString(originalPortString);
		return result;
	}
	@SuppressWarnings("unchecked")
	private ConfigurationSet setupConfigurationSet2() {
		ConfigurationSet result = ComponentFactory.eINSTANCE.createConfigurationSet();
		result.setId("default");
		result.getConfigurationData().add(ExportPortActionTest.createNameValue("exported_ports", ""));
		return result;
	}

	public void testOfflineExport() throws Exception {
		action.setTarget(port1);
		action.run();
		assertEquals(2, component2.getPorts().size());
		assertEquals("child.port2,child.port1", ExportPortActionTest.getExportedPorts(component2));
		action.setTarget(port2);
		action.run();
		assertEquals(1, component2.getPorts().size());
		assertEquals("child.port1", ExportPortActionTest.getExportedPorts(component2));
		action.setTarget(port1);
		action.run();
		assertEquals(0, component2.getPorts().size());
		assertEquals("", ExportPortActionTest.getExportedPorts(component2));
	}

	public void testOfflineUnexport() throws Exception {
		action.setTarget(port2);
		action.run();
		assertEquals(0, component2.getPorts().size());
		assertEquals("", ExportPortActionTest.getExportedPorts(component2));
	}
	
	public void testCameraComponent() throws Exception {
		component1.setInstanceNameL("CameraComponent_1");
		port1.setNameL("ImageData");
		port2.setNameL("CapPORT");
		component2.getPorts().clear();
		component2.setActiveConfigurationSet(setupConfigurationSet2());
		
		action.setTarget(port1);
		action.run();
		assertEquals(1, component2.getPorts().size());
		assertEquals("CameraComponent_1.ImageData", ExportPortActionTest.getExportedPorts(component2));
		action.setTarget(port2);
		action.run();
		assertEquals(2, component2.getPorts().size());
		assertEquals("CameraComponent_1.ImageData,CameraComponent_1.CapPORT", ExportPortActionTest.getExportedPorts(component2));
		action.setTarget(port1);
		action.run();
		assertEquals(1, component2.getPorts().size());
		assertEquals("CameraComponent_1.CapPORT", ExportPortActionTest.getExportedPorts(component2));
	}

	public void testSeq() throws Exception {
		component1.setInstanceNameL("SeqIn");
		port1.setNameL("Short");
		port2.setNameL("ShortSeq");
		component2.getPorts().clear();
		component2.setActiveConfigurationSet(setupConfigurationSet2());
		
		action.setTarget(port2);
		action.run();
		assertEquals(1, component2.getPorts().size());
		assertEquals("SeqIn.ShortSeq", ExportPortActionTest.getExportedPorts(component2));
		action.setTarget(port1);
		action.run();
		assertEquals(2, component2.getPorts().size());
		assertEquals("SeqIn.ShortSeq,SeqIn.Short", ExportPortActionTest.getExportedPorts(component2));
		action.setTarget(port1);
		action.run();
		assertEquals(1, component2.getPorts().size());
		assertEquals("SeqIn.ShortSeq", ExportPortActionTest.getExportedPorts(component2));	
	}
	
}
