package jp.go.aist.rtm.toolscommon.model.component.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl;

import org.junit.Test;

public class ComponentCommonUtilTest {

	@SuppressWarnings("unchecked")
	@Test
	public void getConnectedPorts_Corba() throws Exception {
		RTC.mock.PortServiceImpl c_in1 = _createRtcPortService("in1");
		RTC.mock.PortServiceImpl c_in2 = _createRtcPortService("in2");
		RTC.mock.PortServiceImpl c_out1 = _createRtcPortService("out1");
		RTC.mock.PortServiceImpl c_out2 = _createRtcPortService("out2");

		Port in1 = _createCorbaPort(c_in1);
		Port in2 = _createCorbaPort(c_in2);
		Port out1 = _createCorbaPort(c_out1);
		Port out2 = _createCorbaPort(c_out2);

		_connectCorbaPorts(out1, in1);
		_connectCorbaPorts(out2, in2);

		List<Port> ports = new ArrayList<Port>();
		ports.add(in1);
		ports.add(in2);
		ports.add(out1);
		// out2はコンポーネント外

		List<Port> result = ComponentCommonUtil.getConnectedPorts(ports);
		assertEquals(1, result.size());
		assertEquals("in2", result.get(0).getNameL());
	}

//	@SuppressWarnings("unchecked")
//	@Test
//	public void getConnectedPorts_Offline() throws Exception {
//		Port in1 = _createPort("in1");
//		Port in2 = _createPort("in2");
//		Port out1 = _createPort("out1");
//		Port out2 = _createPort("out2");
//		PortConnector in1out1 = _createPortConnector(in1, out1);
//		PortConnector in2out2 = _createPortConnector(in2, out2);
//
//		in1.getSourceConnectors().add(in1out1);
//		out1.getTargetConnectors().add(in1out1);
//		in2.getSourceConnectors().add(in2out2);
//		out2.getTargetConnectors().add(in2out2);
//
//		List<Port> ports = new ArrayList<Port>();
//		ports.add(in1);
//		ports.add(in2);
//		ports.add(out1);
//		// out2はコンポーネント外
//
//		List<Port> result = ComponentCommonUtil.getConnectedPorts(ports);
//		assertEquals(1, result.size());
//		assertEquals("in2", result.get(0).getPortProfile().getNameL());
//	}

	@SuppressWarnings("unchecked")
	@Test
	public void getRequiredExportedPortsString_Corba() throws Exception {
		RTC.mock.PortServiceImpl c_in1 = _createRtcPortService("in1");
		RTC.mock.PortServiceImpl c_in2 = _createRtcPortService("in2");
		RTC.mock.PortServiceImpl c_out1 = _createRtcPortService("out1");
		RTC.mock.PortServiceImpl c_out2 = _createRtcPortService("out2");

		Port in1 = _createCorbaPort(c_in1);
		Port in2 = _createCorbaPort(c_in2);
		Port out1 = _createCorbaPort(c_out1);
		Port out2 = _createCorbaPort(c_out2);

		_connectCorbaPorts(out1, in1);
		_connectCorbaPorts(out2, in2);

		Component comp1 = _createComponent("comp1");
		Component comp2 = _createComponent("comp2");
		comp1.getPorts().add(in1);
		comp1.getPorts().add(out1);
		comp2.getPorts().add(in2);

		List<Component> comps = new ArrayList<Component>();
		comps.add(comp1);
		comps.add(comp2);

		List<String> result = ComponentCommonUtil
				.getRequiredExportedPorts(comps);
		assertEquals(1, result.size());
		assertEquals("comp2.in2", result.get(0));
	}

//	@SuppressWarnings("unchecked")
//	@Test
//	public void getRequiredExportedPortsString_Offline() throws Exception {
//		Port in1 = _createPort("in1");
//		Port in2 = _createPort("in2");
//		Port out1 = _createPort("out1");
//		Port out2 = _createPort("out2");
//		PortConnector in1out1 = _createPortConnector(in1, out1);
//		PortConnector in2out2 = _createPortConnector(in2, out2);
//
//		in1.getSourceConnectors().add(in1out1);
//		out1.getTargetConnectors().add(in1out1);
//		in2.getSourceConnectors().add(in2out2);
//		out2.getTargetConnectors().add(in2out2);
//
//		Component comp1 = _createComponent("comp1");
//		Component comp2 = _createComponent("comp2");
//		comp1.getPorts().add(in1);
//		comp1.getPorts().add(out1);
//		comp2.getPorts().add(in2);
//
//		List<Component> comps = new ArrayList<Component>();
//		comps.add(comp1);
//		comps.add(comp2);
//
//		List<String> result = ComponentCommonUtil
//				.getRequiredExportedPorts(comps);
//		assertEquals(1, result.size());
//		assertEquals("comp2.in2", result.get(0));
//	}

	// private

	private Component _createComponent(String name) {
		Component result = ComponentFactory.eINSTANCE.createCorbaComponent();
		result.setInstanceNameL(name);
		return result;
	}

//	private Port _createPort(String name) {
//		Port result = ComponentFactory.eINSTANCE.createPort();
//		PortProfile prof = new PortProfileImpl();
//		result.setNameL(name);
//		result.setPortProfile(prof);
//		return result;
//	}

	private Port _createCorbaPort(RTC.mock.PortServiceImpl port) {
		Port result = ComponentFactory.eINSTANCE.createPort();
		CorbaPortSynchronizer sync = ComponentFactory.eINSTANCE
				.createCorbaPortSynchronizer();
		result.setSynchronizer(sync);
		sync.setCorbaObject(port);
		sync.setRTCPortProfile(new RTC.PortProfile());
		sync.getRTCPortProfile().port_ref = port;
		result.setNameL(port.get_port_profile().name);
		return result;
	}

//	private PortConnector _createPortConnector(Port source, Port target) {
//		PortConnector result = ComponentFactory.eINSTANCE
//				.createCorbaPortConnector();
//		ConnectorProfile prof = new ConnectorProfileImpl();
//		prof.setName(source.getNameL() + "_"
//				+ target.getNameL());
//		result.setSource(source);
//		result.setTarget(target);
//		result.setConnectorProfile(prof);
//		return result;
//	}

	private static int CONNECTOR_PROFILE_SEQ = 1;

	@SuppressWarnings("unchecked")
	private void _connectCorbaPorts(Port source, Port target) {
		RTC.ConnectorProfile conn_prof = new RTC.ConnectorProfile();
		conn_prof.connector_id = "conn-" + CONNECTOR_PROFILE_SEQ++;
		conn_prof.name = source.getNameL() + "_"
				+ target.getNameL();
		CorbaPortSynchronizer ssync = (CorbaPortSynchronizer) source.getSynchronizer();
		CorbaPortSynchronizer tsync = (CorbaPortSynchronizer) target.getSynchronizer();
		RTC.PortService sps = (ssync == null) ? null : ssync.getCorbaObjectInterface();
		RTC.PortService tps = (tsync == null) ? null : tsync.getCorbaObjectInterface();
		conn_prof.ports = new RTC.PortService[] { sps, tps };

		CorbaConnectorProfileImpl connProf = new CorbaConnectorProfileImpl();
		connProf.setRtcConnectorProfile(conn_prof);

		sps.get_port_profile().connector_profiles = new RTC.ConnectorProfile[] { conn_prof };
		tps.get_port_profile().connector_profiles = new RTC.ConnectorProfile[] { conn_prof };
		source.getConnectorProfiles().add(connProf);
		target.getConnectorProfiles().add(connProf);
	}

	private RTC.mock.PortServiceImpl _createRtcPortService(String name) {
		RTC.mock.PortServiceImpl result = new RTC.mock.PortServiceImpl();
		result.get_port_profile().name = name;
		return result;
	}
}
