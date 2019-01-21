package jp.go.aist.rtm.nameserviceview.corba;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.junit.Test;
import org.omg.CORBA.Any;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import RTC.ComponentProfile;
import RTC.ConnectorProfileHolder;
import RTC.ExecutionContext;
import RTC.PortProfile;
import RTC.RTObject;
import RTC.RTObjectHelper;
import RTC.ReturnCode_t;
import RTM.ManagerHelper;
import RTM.ModuleProfile;
import _SDOPackage.Configuration;
import _SDOPackage.ConfigurationSet;
import _SDOPackage.InternalError;
import _SDOPackage.NameValue;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.SDO;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;


public class NameServerAccesserTest {
	@SuppressWarnings("unchecked")
//	@Test
	public void getFactoryProfiles() throws Exception {
		NamingContextNode context = getNamingContext();
		TreeIterator allContents = context.eAllContents();
		while (allContents.hasNext()) {
			Object obj = allContents.next();
			if (obj instanceof NamingObjectNode) {
				Object entry = ((NamingObjectNode) obj).getEntry();
				if (entry instanceof RTCManager) {
					RTCManagerImpl manager = (RTCManagerImpl)entry;
					ModuleProfile[] profiles = manager.getCorbaObjectInterface().get_factory_profiles();
					for (int i = 0; i < profiles.length; i++) {
						for(int j = 0; j < profiles[i].properties.length; j++) {
							NameValue nameValue = profiles[i].properties[j];
							System.out.println(nameValue.name + ":" + nameValue.value.extract_string());
						}
					}
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
//	@Test
	public void getComponentState() throws Exception {
		NamingContextNode context = getNamingContext();
		TreeIterator allContents = context.eAllContents();
		while (allContents.hasNext()) {
			Object obj = allContents.next();
			if (obj instanceof NamingObjectNode) {
				Object entry = ((NamingObjectNode) obj).getEntry();
				if (entry instanceof CorbaComponent) {
					CorbaComponent component = (CorbaComponent)entry;
//					System.out.print(component.getInstanceNameR());
					RTObject corbaObjectInterface = component.getCorbaObjectInterface();
					ExecutionContext[] contexts = corbaObjectInterface.get_owned_contexts();
					System.out.print(":isAlive:" + corbaObjectInterface.is_alive(contexts[0]));
					System.out.print(":State:" + contexts[0].get_component_state(corbaObjectInterface).value());
					ExecutionContext[] participatingContext = corbaObjectInterface.get_participating_contexts();
					if (participatingContext.length > 0)
						System.out.print(":PerState:" + participatingContext[0].get_component_state(corbaObjectInterface).value());
					System.out.println();
				}
			}
		}
	}
//	@Test
	public void getConnectCompositeComponents() throws Exception {
		CorbaComponentImpl consoleIn = findComponent("ConsoleIn0");
		RTC.PortService[] outPorts = consoleIn.getCorbaObjectInterface().get_ports();
		CorbaComponentImpl consoleOut = findComponent("ConsoleOut0");
		RTC.PortService[] inPorts = consoleOut.getCorbaObjectInterface().get_ports();
		RTCManagerImpl manager = findManager();
		assertNotNull(manager);

		String comp1Name = "PeriodicECSharedComposite?instance_name=comp1&exported_ports=ConsoleIn0.out";
		RTC.RTObject comp1 = manager.getCorbaObjectInterface().create_component(comp1Name);
		Organization[] orgs = comp1.get_owned_organizations();
		orgs[0].set_members(new SDO[]{consoleIn.getCorbaObjectInterface()});
		RTC.PortService[] ports = comp1.get_ports();
		assertEquals(outPorts[0], ports[0]);

		String comp2Name = "PeriodicECSharedComposite?instance_name=comp2&exported_ports=ConsoleOut0.in";
		RTC.RTObject comp2 = manager.getCorbaObjectInterface().create_component(comp2Name);
		Organization[] orgs2 = comp2.get_owned_organizations();
		orgs2[0].set_members(new SDO[]{consoleOut.getCorbaObjectInterface()});
		RTC.PortService[] ports2 = comp2.get_ports();
		assertEquals(inPorts[0], ports2[0]);

		RTC.ConnectorProfile profile = new RTC.ConnectorProfile();
		profile.connector_id = "";
		profile.name = "out_in";
		profile.ports = new RTC.PortService[] { ports[0], ports2[0]};
		profile.properties = setupConnectorProperties();
		ConnectorProfileHolder connectorProfileHolder = new ConnectorProfileHolder(
				profile);
		RTC.ConnectorProfile[]  connectorProfiles = ports[0].get_connector_profiles();
		ports[0].disconnect_all();
//		for(int i=0; i<connectorProfiles.length; i++) {
//			ports[0].disconnect(connectorProfiles[i].connector_id);
//		}
		ports[0].connect(connectorProfileHolder);

		connectorProfiles = ports[0].get_connector_profiles();
		assertEquals(1, connectorProfiles.length);
		assertEquals(ports[0], connectorProfiles[0].ports[0]);
		assertEquals(ports2[0], connectorProfiles[0].ports[1]);

		RTC.ConnectorProfile[] connectorProfiles2 = ports2[0].get_connector_profiles();
		assertEquals(1, connectorProfiles2.length);
		assertEquals(ports[0], connectorProfiles2[0].ports[0]);
		assertEquals(ports2[0], connectorProfiles2[0].ports[1]);

	}

	private NameValue[] setupConnectorProperties() {
		List<NameValue> result = new ArrayList<NameValue>();
		result.add(new NameValue("dataport.dataflow_type"
				, createAny("Push")));
		result.add(new NameValue("dataport.subscription_type"
				, createAny("Flush")));
		result.add(new NameValue("dataport.data_type"
				, createAny("TimedLong")));
		result.add(new NameValue("dataport.interface_type"
				, createAny("CORBA_Any")));
		result.add(new NameValue("dataport.push_rate"
				, createAny("10.0")));
		return result.toArray(new _SDOPackage.NameValue[result.size()]);
	}
	private Any createAny(String value) {
		Any any = CorbaUtil.getOrb().create_any();
		any.insert_string(value);
		return any;
	}
	@SuppressWarnings("unchecked")
	private RTCManagerImpl findManager() throws Exception {
		NamingContextNode context = getNamingContext();
		TreeIterator allContents = context.eAllContents();
		while (allContents.hasNext()) {
			Object obj = allContents.next();
			if (obj instanceof NamingObjectNode) {
				Object entry = ((NamingObjectNode) obj).getEntry();
				if (entry instanceof RTCManager) {
					return (RTCManagerImpl)entry;
				}
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private CorbaComponentImpl findComponent(String name) throws Exception{
		NamingContextNode context = getNamingContext();
		TreeIterator allContents = context.eAllContents();
		while (allContents.hasNext()) {
			Object obj = allContents.next();
			if (obj instanceof NamingObjectNode) {
				Object entry = ((NamingObjectNode) obj).getEntry();
				if (entry instanceof CorbaComponentImpl) {
					CorbaComponentImpl component = (CorbaComponentImpl)entry;
					component.setRTCComponentProfile(component.getCorbaObjectInterface().get_component_profile());
					if (component.getInstanceNameL().equals(name)) return component;
				}
			}
		}
		return null;
	}

	//	@Test
	public void connect() throws Exception {
		NamingContextNode result = getNamingContext();
		assertNotNull(result);

	}

//	@Test
	public void nonExistent() throws Exception {
		String address = "192.168.1.164:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		System.out.println("namingContext has " + bindingList.size() + " nodes");

		Binding binding = bindingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);

		bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		long start = System.currentTimeMillis();
		for (Binding b : bindingList) {
			org.omg.CORBA.Object resolve = hostContext.resolve(b.binding_name);
			try {
				resolve._non_existent();
				fail("should cause error");
			} catch (Exception e) {
				System.out.println(System.currentTimeMillis() - start);
			}
		}
	}

//	@Test
	public void comp1() throws Exception {
		String address = "192.168.1.164:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		System.out.println("namingContext has " + bindingList.size() + " nodes");

		Binding binding = bindingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);

		bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		for (Binding b : bindingList) {
			org.omg.CORBA.Object resolve = hostContext.resolve(b.binding_name);
			if (!resolve._is_a(RTObjectHelper.id())) continue;
			RTObject narrow = RTObjectHelper.narrow(resolve);
			ComponentProfile profile = narrow.get_component_profile();
			if (!profile.instance_name.equals("comp1")) continue;
			for (PortProfile port : profile.port_profiles) {
				System.out.println(port.port_ref);
			}
		}

	}

//	@Test
	public void getManagerFromPathId() throws Exception {
		String pathId = "192.168.1.212/localhost.localdomain.host_cxt";
		assertEquals("192.168.1.212", pathId.split("/")[0]);
		assertEquals("192.168.1.212", "192.168.1.212".split("/")[0]);
		pathId = "192.168.1.212";

		NameServerAccesser instance = NameServerAccesser.getInstance();
		NamingContext context = (NamingContext) instance.getObjectFromPathId(pathId);
		context = instance.getNameServerRootContext(pathId);
		assertNotNull(instance.findManager(context));
	}
	// @Test
	public void compare() throws Exception {
		String address = "192.168.1.153:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		System.out.println("namingContext has " + bindingList.size() + " nodes");

		Binding binding = bindingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);

		bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		for (Binding b : bindingList) {
			for (NameComponent nc : b.binding_name) {
				System.out.print(nc.id);
				System.out.print("=");
				System.out.print(nc.kind);
				System.out.print(":");
			}
			System.out.println();
		}
	}
//	@Test
	public void activate() throws Exception {
		String address = "192.168.1.164:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		assertNotNull(namingContext);
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		assertFalse(bindingList.isEmpty());
		System.out.println("namingContext has " + bindingList.size() + " nodes");
		Binding binding = bindingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);
		assertFalse(BindingType.nobject == binding.binding_type);
		assertFalse(hostContext._is_a(RTObjectHelper.id()));
		assertEquals("host_cxt", binding.binding_name[0].kind);

		bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		binding = bindingList.get(0);
		assertTrue(BindingType.nobject == binding.binding_type);
		org.omg.CORBA.Object resolve = hostContext.resolve(binding.binding_name);
		assertFalse(resolve._is_a(RTObjectHelper.id()));
		assertTrue(resolve._is_a(ManagerHelper.id()));

		binding = bindingList.get(1);
		assertTrue(BindingType.nobject == binding.binding_type);
		resolve = hostContext.resolve(binding.binding_name);
		assertTrue(resolve._is_a(RTObjectHelper.id()));
		assertFalse(resolve._is_a(ManagerHelper.id()));
		RTObject narrow = RTObjectHelper.narrow(resolve);
		RTC.ExecutionContext[] executionContexts = narrow.get_owned_contexts();
		assertEquals(ReturnCode_t.RTC_OK.value(), executionContexts[0].activate_component(narrow).value());
		assertEquals(RTC.LifeCycleState.INACTIVE_STATE.value(), executionContexts[0].get_component_state(narrow).value());
		RTC.ExecutionContext[] ec = narrow.get_participating_contexts();
		assertNotNull(ec);
		assertEquals(2, ec.length);
		assertEquals(RTC.LifeCycleState.INACTIVE_STATE.value(), ec[0].get_component_state(narrow).value());
		assertEquals(RTC.LifeCycleState.INACTIVE_STATE.value(), ec[1].get_component_state(narrow).value());
	}

//	@Test
	public void exportPort() throws Exception {
		String address = "192.168.1.164:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		List<Binding> namingList = CorbaUtil.getBindingList(namingContext);
		System.out.println("namingContext has " + namingList.size() + " nodes");
		Binding binding = namingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);
		assertFalse(BindingType.nobject == binding.binding_type);
		assertFalse(hostContext._is_a(RTObjectHelper.id()));
		assertEquals("host_cxt", binding.binding_name[0].kind);

		List<Binding> bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		// 1つめはManager
		binding = bindingList.get(0);
		assertTrue(BindingType.nobject == binding.binding_type);
		org.omg.CORBA.Object resolve = hostContext.resolve(binding.binding_name);
		assertFalse(resolve._is_a(RTObjectHelper.id()));
		RTM.Manager manager = ManagerHelper.narrow(resolve);

		// 2つめはComponent
		binding = bindingList.get(1);
		assertTrue(BindingType.nobject == binding.binding_type);
		resolve = hostContext.resolve(binding.binding_name);
		assertTrue(resolve._is_a(RTObjectHelper.id()));
		RTObject child = RTObjectHelper.narrow(resolve);
		ComponentProfile childProfile = child.get_component_profile();
		assertEquals("ConsoleIn0", childProfile.instance_name);
		assertEquals("out", childProfile.port_profiles[0].name);

		// 複合RTCを作る
		RTC.RTObject composite = manager.create_component("PeriodicECSharedComposite?instance_name=comp1&exported_ports=");
		Organization org = composite.get_owned_organizations()[0];
		Configuration configuration = composite.get_configuration();
		org.set_members(new SDO[]{child});
		ConfigurationSet activeConfigurationSet = configuration.get_active_configuration_set();
		assertEquals("default", activeConfigurationSet.id);
		assertEquals(2, activeConfigurationSet.configuration_data.length);
		assertEquals("members", activeConfigurationSet.configuration_data[0].name);
		assertEquals("", activeConfigurationSet.configuration_data[0].value.extract_string());
		assertEquals("exported_ports", activeConfigurationSet.configuration_data[1].name);
		assertEquals("", activeConfigurationSet.configuration_data[1].value.extract_string());

		// ポートはすべて非公開
		ComponentProfile compositeProfile = composite.get_component_profile();
		assertEquals(0, compositeProfile.port_profiles.length);

		// ポートを公開する
		configuration.set_configuration_set_values(createSdoConfigurationSet("ConsoleIn0.out"));
		configuration.activate_configuration_set("default");

		// 公開後のコンフィグセット
		activeConfigurationSet = configuration.get_active_configuration_set();
		assertEquals("default", activeConfigurationSet.id);
		assertEquals(2, activeConfigurationSet.configuration_data.length);
		assertEquals("members", activeConfigurationSet.configuration_data[0].name);
		assertEquals("", activeConfigurationSet.configuration_data[0].value.extract_string());
		assertEquals("exported_ports", activeConfigurationSet.configuration_data[1].name);
		assertEquals("ConsoleIn0.out", activeConfigurationSet.configuration_data[1].value.extract_string());

		// 公開後のポート
		compositeProfile = composite.get_component_profile();
		assertEquals(1, compositeProfile.port_profiles.length);

		// 複合RTCを消しておく
		composite.exit();
	}

//	@Test
	public void addMembers() throws Exception {
		String address = "192.168.1.164:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		List<Binding> namingList = CorbaUtil.getBindingList(namingContext);
		System.out.println("namingContext has " + namingList.size() + " nodes");
		Binding binding = namingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);
		assertFalse(BindingType.nobject == binding.binding_type);
		assertFalse(hostContext._is_a(RTObjectHelper.id()));
		assertEquals("host_cxt", binding.binding_name[0].kind);

		List<Binding> bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		// 1つめはManager
		binding = bindingList.get(0);
		assertTrue(BindingType.nobject == binding.binding_type);
		org.omg.CORBA.Object resolve = hostContext.resolve(binding.binding_name);
		assertFalse(resolve._is_a(RTObjectHelper.id()));
		RTM.Manager manager = ManagerHelper.narrow(resolve);

		// 2つめはComponent
		binding = bindingList.get(1);
		assertTrue(BindingType.nobject == binding.binding_type);
		resolve = hostContext.resolve(binding.binding_name);
		assertTrue(resolve._is_a(RTObjectHelper.id()));
		RTObject child = RTObjectHelper.narrow(resolve);
		ComponentProfile childProfile = child.get_component_profile();
		assertEquals("ConsoleIn0", childProfile.instance_name);
		assertEquals("out", childProfile.port_profiles[0].name);

		// 3つめもComponent
		binding = bindingList.get(2);
		assertTrue(BindingType.nobject == binding.binding_type);
		resolve = hostContext.resolve(binding.binding_name);
		assertTrue(resolve._is_a(RTObjectHelper.id()));
		RTObject anotherChild = RTObjectHelper.narrow(resolve);
		childProfile = anotherChild.get_component_profile();
		assertEquals("ConsoleOut0", childProfile.instance_name);
		assertEquals("in", childProfile.port_profiles[0].name);

		// 複合RTCを作る
		RTC.RTObject composite = manager.create_component("PeriodicECSharedComposite?instance_name=comp1&exported_ports=ConsoleIn0.out");
		Organization org = composite.get_owned_organizations()[0];
		Configuration configuration = composite.get_configuration();
		org.set_members(new SDO[]{child});
		verifyExportedConfig(configuration, "ConsoleIn0.out");

		// ConsoleIn0.outを公開
		ComponentProfile compositeProfile = composite.get_component_profile();
		assertEquals(1, compositeProfile.port_profiles.length);

		// ConsoleOut0を追加する
		org.add_members(new SDO[]{anotherChild});

		verifyExportedConfig(configuration, "ConsoleIn0.out");
		compositeProfile = composite.get_component_profile();
		assertEquals(1, compositeProfile.port_profiles.length);

		// ConsoleOut0.inを公開
		configuration.set_configuration_set_values(createSdoConfigurationSet("ConsoleIn0.out,ConsoleOut0.in"));
		configuration.activate_configuration_set("default");

		verifyExportedConfig(configuration, "ConsoleIn0.out,ConsoleOut0.in");
		compositeProfile = composite.get_component_profile();
		assertEquals(2, compositeProfile.port_profiles.length);

		// 複合RTCを消しておく
		composite.exit();
	}
	@Test
	public void dumpComponentState() throws Exception {
		String address = "192.168.1.212:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		List<Binding> namingList = CorbaUtil.getBindingList(namingContext);
		System.out.println("namingContext has " + namingList.size() + " nodes");
		Binding binding = namingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);
		List<Binding> bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");
		for (Binding b : bindingList) {
			org.omg.CORBA.Object resolve = hostContext.resolve(b.binding_name);
			if (!resolve._is_a(RTObjectHelper.id())) continue;
			RTObject ro = RTObjectHelper.narrow(resolve);
			ComponentProfile profile = ro.get_component_profile();
			System.out.println(profile.instance_name);
			RTC.ExecutionContext[] ec = ro.get_participating_contexts();
			System.out.println("participating_contexts : " + dumpComponentState(ec, ro));
			ec = ro.get_owned_contexts();
			System.out.println("owned_contexts : " + dumpComponentState(ec, ro));
		}

	}
	private String dumpComponentState(ExecutionContext[] ec, RTObject ro) {
		if (ec == null) return "";
		if (ec.length == 0) return "";
		return String.valueOf(ec[0].get_component_state(ro).value());

	}
	private void verifyExportedConfig(Configuration configuration, String expected)
			throws NotAvailable, InternalError {
		ConfigurationSet activeConfigurationSet = configuration.get_active_configuration_set();
		assertEquals("default", activeConfigurationSet.id);
		assertEquals(2, activeConfigurationSet.configuration_data.length);
		assertEquals("members", activeConfigurationSet.configuration_data[0].name);
		assertEquals("", activeConfigurationSet.configuration_data[0].value.extract_string());
		assertEquals("exported_ports", activeConfigurationSet.configuration_data[1].name);
		assertEquals(expected, activeConfigurationSet.configuration_data[1].value.extract_string());
	}

	private ConfigurationSet createSdoConfigurationSet(String exportedPorts) {
		ConfigurationSet result = new ConfigurationSet();
		result.id = "default";
		result.description = "";
		result.configuration_data = createConfigurationData(exportedPorts);
		return result;
	}

	private NameValue[] createConfigurationData(String exportedPorts) {
		NameValue[] result = new NameValue[2];
		result[0] = new NameValue();
		result[0].name = "members";
		result[0].value = CorbaUtil.getOrb().create_any();
		result[0].value.insert_string("");
		result[1] = new NameValue();
		result[1].name = "exported_ports";
		result[1].value = CorbaUtil.getOrb().create_any();
		result[1].value.insert_string(exportedPorts);
		return result;
	}
//	@Test
	public void examineCommnicationOrbd() throws Exception {
		String address = "192.168.1.164:1050";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.0@" + address + "/NameService"));
		assertNotNull(namingContext);
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		assertFalse(bindingList.isEmpty());
		System.out.println("namingContext has " + bindingList.size() + " nodes");


		Binding binding = bindingList.get(0);
		assertFalse(BindingType.nobject == binding.binding_type);
		assertEquals("host_cxt", binding.binding_name[0].kind);
		NamingContext hostContext = (NamingContext) namingContext.resolve(binding.binding_name);
		bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		binding = bindingList.get(0);
		assertTrue(BindingType.nobject == binding.binding_type);
		org.omg.CORBA.Object resolve = hostContext.resolve(binding.binding_name);
		assertFalse(resolve._is_a(RTObjectHelper.id()));
		assertTrue(resolve._is_a(ManagerHelper.id()));

		verifyComponent(bindingList.get(1), hostContext);
		verifyComponent(bindingList.get(2), hostContext);
		verifyComponent(bindingList.get(3), hostContext);
		verifyComponent(bindingList.get(4), hostContext);
		verifyComponent(bindingList.get(5), hostContext);
		verifyComponent(bindingList.get(6), hostContext);

//
//		org.omg.CORBA.Object resolve = namingContext.resolve(binding.binding_name);

//		namingContext.unbind(binding.binding_name);

//		assertFalse(resolve._is_a(RTObjectHelper.id()));
	}
	private void verifyComponent(Binding binding, NamingContext hostContext)
			throws NotFound, CannotProceed, InvalidName {
		org.omg.CORBA.Object resolve;
		assertTrue(BindingType.nobject == binding.binding_type);
		resolve = hostContext.resolve(binding.binding_name);
		assertTrue(resolve._is_a(RTObjectHelper.id()));
	}
//	@Test
	public void examineCommunication198() throws Exception {
		String address = "192.168.1.198:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		assertNotNull(namingContext);
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		assertFalse(bindingList.isEmpty());
		System.out.println("namingContext has " + bindingList.size() + " nodes");
		Binding binding = bindingList.get(0);
		NamingContextExt hostContext = (NamingContextExt) namingContext.resolve(binding.binding_name);
		assertFalse(BindingType.nobject == binding.binding_type);
		assertFalse(hostContext._is_a(RTObjectHelper.id()));
		assertEquals("host_cxt", binding.binding_name[0].kind);

		bindingList = CorbaUtil.getBindingList(hostContext);
		System.out.println("host_cxt has " + bindingList.size() + " nodes");

		binding = bindingList.get(0);
		assertTrue(BindingType.nobject == binding.binding_type);
		org.omg.CORBA.Object resolve = hostContext.resolve(binding.binding_name);
		assertTrue(resolve._is_a(RTObjectHelper.id())); // 反応が返ってこない
	}
//	@Test
	// HostNamingContextImplがゾンビだと出てこない問題は不明だが、ほっておく
	// STATUS がUNKNOWNになるのは、IDLの問題か。activateもエラーとなるし
	public void examineCommunication162() throws Exception {
		String address = "192.168.1.162:2809";
		NamingContextExt namingContext = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));
		assertNotNull(namingContext);
		List<Binding> bindingList = CorbaUtil.getBindingList(namingContext);
		assertFalse(bindingList.isEmpty());
		System.out.println("namingContext has " + bindingList.size() + " nodes");
		Binding binding = bindingList.get(0);
		org.omg.CORBA.Object resolve = namingContext.resolve(binding.binding_name);
		assertFalse(BindingType.nobject == binding.binding_type);
//		assertFalse(resolve._is_a(RTObjectHelper.id())); // 接続エラー
//		assertFalse(resolve._non_existent());　　　　　　　　　　// 接続エラー
		assertEquals("host_cxt", binding.binding_name[0].kind);
		binding = bindingList.get(1);
		resolve = namingContext.resolve(binding.binding_name);
		assertTrue(BindingType.nobject == binding.binding_type);
		assertFalse(resolve._non_existent());
		assertTrue(resolve._is_a(RTObjectHelper.id()));
		RTObject ro = RTObjectHelper.narrow(resolve);
//		RTC.ExecutionContext[] ec = ro.get_participating_contexts();
		RTC.ExecutionContext[] ec = ro.get_owned_contexts();
		assertNull(ec);
	}

	public static NamingContextNode getNamingContext() throws Exception {
		String address = "192.168.1.164";
		NameServerAccesser instance = NameServerAccesser.getInstance();
//		assertTrue(instance.validateNameServerAddress(address));
		NamingContextExt namingContext = instance.getNameServerRootContext(address);
		assertNotNull(namingContext);

        NameServiceReference nameServiceReference = createNameServerReference(address, namingContext);
        SynchronizationManager synchronizationManager = new SynchronizationManager(setupMappingRule());

        return (NamingContextNode) synchronizationManager.createLocalObject(
                new Object[]{namingContext, nameServiceReference});
	}

	public static MappingRule[] setupMappingRule() throws Exception {
		List<MappingRule> mappingRuleList = new ArrayList<MappingRule>();
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingObjectNodeImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingContextNodeImpl.MAPPING_RULE_NAMESERVER"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl.MAPPING_RULE"));
		mappingRuleList.add(createMappingRule("jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingContextNodeImpl.MAPPING_RULE"));
		return mappingRuleList.toArray(new MappingRule[mappingRuleList.size()]);
	}

	@SuppressWarnings("unchecked")
	private static MappingRule createMappingRule(String mappingRuleValue) throws Exception {
		int lastIndexOf = mappingRuleValue.lastIndexOf(".");

		Class clazz = NameServerAccesserTest.class.getClassLoader().loadClass(
				mappingRuleValue.substring(0, lastIndexOf));
		Field field = clazz.getDeclaredField(mappingRuleValue
				.substring(lastIndexOf + ".".length()));

		return	(MappingRule) field.get(clazz.newInstance());
	}

	private static NameServiceReference createNameServerReference(String address, NamingContextExt namingContext) {
		NameServiceReference nameServiceReference = new NameServiceReferenceImpl();
        nameServiceReference.setNameServerName(address);
        Binding binding = new Binding();
        binding.binding_name = new NameComponent[] {};
        nameServiceReference.setBinding(binding);
        nameServiceReference.setRootNamingContext(namingContext);
		return nameServiceReference;
	}
}
