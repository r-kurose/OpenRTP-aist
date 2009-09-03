package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.ComponentFactoryImpl;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;
import junit.framework.TestCase;

public class CompositeFilterTest extends TestCase {
	
//	private SystemDiagram diagram;
	private Port port1;
	private Port port2;
	private Port port3;


	@Override
	protected void setUp() throws Exception {
		setupDiagram();
	}
//
//	public void testGetSystemDiagramChildren() {
//		List<Object> result = CompositeFilter.getSystemDiagramChildren(diagram);
//		assertEquals(2, result.size());
//	}
	
	public void testGetModelTargetConnections() {
		List<PortConnector> result = CompositeFilter.getModelTargetConnections(setupPort1());
		assertEquals(0, result.size());

//		result = CompositeFilter.getModelSourceConnections(setupPort2());
//		assertEquals(0, result.size());

		result = CompositeFilter.getModelTargetConnections(setupPort3());
		assertEquals(1, result.size());
	}

	public void testGetModelSourceConnections() {
		assertEquals(1, setupPort1().getConnectorProfiles().size());
		List<PortConnector> result = CompositeFilter.getModelSourceConnections(setupPort1());
		assertEquals(1, result.size());
		assertEquals(setupPort3(), result.get(0).getTarget());
		assertEquals(setupPort1(), result.get(0).getSource());
		
//		assertEquals(1, setupPort2().getConnectorProfiles().size());
//		result = CompositeFilter.getModelSourceConnections(setupPort2());
//		assertEquals(0, result.size());
		
		assertEquals(1, setupPort3().getConnectorProfiles().size());
		result = CompositeFilter.getModelSourceConnections(setupPort3());
		assertEquals(0, result.size());
	}

	@SuppressWarnings("unchecked")
	private SystemDiagram setupDiagram() {
		SystemDiagram diagram = new ComponentFactoryImpl().createSystemDiagram();
		diagram.getComponents().add(setupComponent1());
//		diagram.getComponents().add(setupComponent2());
//		diagram.getComponents().add(setupComponent3());
		diagram.getComponents().add(setupComponent4());
		setupConnector(setupPort2(), setupPort3());
		return diagram;
	}

	@SuppressWarnings("unchecked")
	private Object setupComponent1() {
		Component component = createComponent("1");
		component.getComponents().add(setupComponent2());
		component.getComponents().add(setupComponent3());
		component.setCategoryL("composite.PeriodicECShared");
		component.getPorts().add(setupPort1());
		return component;
	}

	@SuppressWarnings("unchecked")
	private Object setupComponent2() {
		Component component = createComponent("2");
		component.getPorts().add(setupPort2());
		return component;
	}

	private Object setupComponent3() {
		Component component = createComponent("3");
		return component;
	}

	@SuppressWarnings("unchecked")
	private Object setupComponent4() {
		Component component = createComponent("4");
		component.getPorts().add(setupPort3());
		return component;
	}

	private Component createComponent(String pathId) {
		Component component = ComponentFactory.eINSTANCE.createComponentSpecification();
		component.setPathId(pathId);
		component.setComponentId(pathId);
		component.setInstanceNameL(pathId);
		return component;
	}

	private Port setupPort1() {
		if (port1 != null) return port1;
//		port1 = ComponentFactory.eINSTANCE.createOutPort();
//		port1.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
//		port1.setOriginalPortString(getOriginalPortString("2", "2", "2"));
//		return port1;
		port1 = setupPort2().proxy();
		return port1;
	}

	private Port setupPort2() {
		if (port2 != null) return port2;
		port2 =  ComponentFactory.eINSTANCE.createOutPort();
		port2.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port2.setOriginalPortString(getOriginalPortString("2", "2", "2"));
		return port2;
	}

	private Port setupPort3() {
		if (port3 != null) return port3;
		port3 = ComponentFactory.eINSTANCE.createInPort();
		port3.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port3.setOriginalPortString(getOriginalPortString("4", "4", "3"));
		return port3;
	}

	private void setupConnector(Port source, Port target) {
		PortConnector connector = PortConnectorFactory.createPortConnectorSpecification();
		ConnectorProfile profile = ComponentFactory.eINSTANCE.createConnectorProfile();
		connector.setSource(source);
		connector.setTarget(target);
		connector.setConnectorProfile(profile);
		connector.createConnectorR();
	}


	private String getOriginalPortString(String componentId,
			String instanceName, String portName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{componentId:").append(componentId).append(",instanceName:").append(instanceName);
		buffer.append(",portName:").append(portName).append("}");
		return buffer.toString();
	}

}
