package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.toolscommon.corba.ConfigurationMock;
import jp.go.aist.rtm.toolscommon.corba.CorbaObjectMock;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import junit.framework.TestCase;

import org.omg.CORBA.Any;

import RTC.PortProfile;


public class ExportPortActionTest extends TestCase {
	private Port port1;
	private Port port2;
	private Port port3;
	private StringBuffer buffer;

	private ExportPortAction action;
	
	private SystemDiagram diagram1;
	private SystemDiagram diagram2;
	private CorbaComponent component1;
	private CorbaComponent component2;

	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		port1 = createPort("1", "port1");
		port2 = createPort("2", "port2");
		port3 = createPort("2", "child.port2");
		buffer = new StringBuffer();
		diagram1 = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram2 = ComponentFactory.eINSTANCE.createSystemDiagram();
		component1 = ComponentFactory.eINSTANCE.createCorbaComponent();
		component2 = ComponentFactory.eINSTANCE.createCorbaComponent();
		diagram1.getComponents().add(component1);
		diagram1.getComponents().add(component2);
		diagram2.getComponents().add(component1);
		diagram2.setParentSystemDiagram(diagram1);
		diagram2.setCompositeComponent(component2);
		component2.setChildSystemDiagram(diagram2);
		component1.getPorts().add(port1);
		component1.getPorts().add(port2);
		component1.setInstanceNameL("child");
		component2.getComponents().add(component1);
		component2.getPorts().add(port3);
		component2.setActiveConfigurationSet(setupConfigurationSet());
		
		action = new ExportPortAction();
		action.setParent(component2);
	}
	
	@SuppressWarnings("unchecked")
	static ConfigurationSet setupConfigurationSet() {
		ConfigurationSet result = ComponentFactory.eINSTANCE.createConfigurationSet();
		result.setId("default");
		result.getConfigurationData().add(createNameValue("exported_ports", "child.port2"));
		return result;
	}

	static Object createNameValue(String name, String value) {
		Any any = CorbaUtil.getOrb().create_any();
		any.insert_string(value);

		NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
		nv.setName(name);
		nv.setValue(value);
		return nv;
	}

	private Port createPort(String originalPortString, String portName) {
		Port result = ComponentFactory.eINSTANCE.createPort();
		result.setNameL(portName);
		result.setSynchronizer(ComponentFactory.eINSTANCE.createCorbaPortSynchronizer());
		result.setOriginalPortString(originalPortString);
		return result;
	}
	
	private void setupOnline(){
		SystemEditorPreferenceManager.setInstance(new SystemEditorPreferenceManager(){
			@Override
			public int getInterval(String key) {
				return 1000;
			}});
		component1.setCorbaObject(new CorbaObjectMock(new StringBuffer()));
		component1.setSynchronizationSupport(new SynchronizationSupport(component1,CorbaComponentImpl.MAPPING_RULE,null));
		component2.setCorbaObject(new CorbaObjectMock(buffer));
		component2.setSDOConfiguration(new ConfigurationMock(buffer));
		component2.setSynchronizationSupport(new SynchronizationSupport(component2,CorbaComponentImpl.MAPPING_RULE,null));
		port3.setSynchronizationSupport(new SynchronizationSupport(port3,CorbaPortSynchronizerImpl.MAPPING_RULE,null));
		setCorbaObject(port3, new CorbaObjectMock(new StringBuffer(), "2"));
		port2.setSynchronizationSupport(new SynchronizationSupport(port2,CorbaPortSynchronizerImpl.MAPPING_RULE,null));
		setCorbaObject(port2, new CorbaObjectMock(new StringBuffer(), "2"));
		setCorbaObject(port1, new CorbaObjectMock(new StringBuffer(), "1"));
		port1.setSynchronizationSupport(new SynchronizationSupport(port1,CorbaPortSynchronizerImpl.MAPPING_RULE,null));
	}
	
	private void setCorbaObject(Port port, CorbaObjectMock corbaObjectMock) {
		CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port.getSynchronizer();
		RTC.PortProfile profile = new PortProfile();
		profile.port_ref = corbaObjectMock;
		synchronizer.setRTCPortProfile(profile);
		synchronizer.setCorbaObject(corbaObjectMock);
	}

	public void testOnlineExport() throws Exception {
		setupOnline();
		action.setTarget(port1);
		action.run();
		assertEquals("add_configuration_set id:default exported_ports:child.port2,child.port1 get_active_configuration_set "
				, buffer.toString());
	}

	public void testOnlineUnexport() throws Exception {
		setupOnline();
		action.setTarget(port2);
		action.run();
		assertEquals("add_configuration_set id:default exported_ports: get_active_configuration_set "
				, buffer.toString());
	}

	static String getExportedPorts(Component component) {
		jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet activeConfigurationSet = component.getActiveConfigurationSet();
		for (Object  element : activeConfigurationSet.getConfigurationData()) {
			NameValue value = (NameValue) element;
			if (value.getName().equals("exported_ports")) {
				return value.getValueAsString();
			}
		}
		return null;
	}
}
