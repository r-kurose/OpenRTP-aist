package jp.go.aist.rtm.toolscommon.component;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

import org.junit.Test;

public class ComponentTest {
	@Test
	public void getPathCorba() throws Exception {
		Component component = ComponentFactory.eINSTANCE.createCorbaComponent();
		component.setPathId("192.168.1.164/client164.local.tech-arts.co.jp.host_cxt/ConfigSample0.rtc");
		assertEquals("192.168.1.164", component.getPath());
	}
	@Test
	public void getPathSpecification() throws Exception {
		Component component = ComponentFactory.eINSTANCE.createComponentSpecification();
		component.setPathId("file://localhost/C:\\RTSystemEditor\\rsmtj\\RTC_Sample Vender.example.ImageProcess_1.0.0.xml:1");
		assertEquals("file://localhost/C:\\RTSystemEditor\\rsmtj", component.getPath());
	}
	@Test
	public void getExportedPorts() throws Exception {
		Component component = ComponentFactory.eINSTANCE.createComponentSpecification();
		component.setActiveConfigurationSet(createConfigutationSet());
		List<String> exportedPorts = component.getExportedPorts();
		assertEquals(2, exportedPorts.size());
		assertEquals("ConsoleIn0.out", exportedPorts.get(0));
		assertEquals("MyServiceConsumer0.service", exportedPorts.get(1));
	}
	@SuppressWarnings("unchecked")
	private ConfigurationSet createConfigutationSet() {
		ConfigurationSet result = ComponentFactory.eINSTANCE.createConfigurationSet();
		result.getConfigurationData().add(createNameValue());
		return result;
	}
	private NameValue createNameValue() {
		NameValue result = ComponentFactory.eINSTANCE.createNameValue();
		result.setName("exported_ports");
		result.setValue("ConsoleIn0.out, MyServiceConsumer0.service");
		return result;
	}
}
