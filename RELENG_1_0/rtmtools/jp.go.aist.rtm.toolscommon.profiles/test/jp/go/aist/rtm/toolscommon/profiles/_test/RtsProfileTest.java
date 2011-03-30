package jp.go.aist.rtm.toolscommon.profiles._test;

import java.math.BigInteger;
import java.util.List;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.Dataport;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.ExecutionContext;
import org.openrtp.namespaces.rts.version02.Location;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.TargetPort;

public class RtsProfileTest extends TestBase {

//	public void testRtsXmlExport() {
////		String resourceDir = rootPath +  "\\resource\\RTC\\RtcSampleVer02.xml";
////		String expected = readFile(resourceDir,"\n");
////		
////		SampleProfileGenerator handle = new SampleProfileGenerator();
//		String result = null;
//		try {
//			ObjectFactory factory = new ObjectFactory();
//			RtsProfileExt profile =  factory.createRtsProfileExt();
//			profile.setId("RTSystem:ta.ta-sample:1.0");
//			profile.setVersion("0.2");
//			profile.setCreationDate(XMLGregorianCalendarImpl.createDateTime(2008, 12, 19, 19, 44, 06));
//			//
////			RtsProfileExt profile = handle.generateProfile();
//			XmlHandler handler = new XmlHandler();
//			result = handler.convertToXmlRts(profile);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
////		assertEquals(expected, result);
//	}
	
	public void testRtsXmlImportVer01() throws Exception{
		String resourceDir = rootPath +  "\\resource\\RTS\\RtsSampleV01.xml";
		String expected = readFile(resourceDir,"\n");
		XmlHandler handler = new XmlHandler();
		RtsProfileExt profile = null;
		try {
			profile = handler.restoreFromXmlRts(expected);
		} catch(Exception ex) {
			fail(ex.getCause().getMessage());
		}
			
		assertEquals("RTSystem:ta:ta-sample:1.0",profile.getId());
		assertEquals("0.2", profile.getVersion());
//		assertEquals("2008-12-19T19:44:06.450+09:00", profile.getUpdateDate());
//		assertEquals("2008-12-19T19:44:06.450+09:00", profile.getCreationDate());
		//
		List<Component> comps = profile.getComponents();
		Component comp = comps.get(0);
		assertTrue(comp.isIsRequired());
		assertEquals("None", comp.getCompositeType());
		assertEquals("ConsoleIn0", comp.getInstanceName());
		assertEquals("127.0.0.1/sirius.host_cxt/ConsoleIn0.rtc", comp.getPathUri());
		assertEquals("RTC:Noriaki Ando, AIST.example.ConsoleIn:1.0", comp.getId());
		//
		List<ExecutionContext> ecs = comp.getExecutionContexts();
		ExecutionContext ec = ecs.get(0);
		assertEquals("PERIODIC", ec.getKind());
		assertEquals(Double.valueOf(1000.0d), ec.getRate());
		//
		List<Dataport> ports = comp.getDataPorts();
		Dataport port = ports.get(0);
		assertEquals("out", port.getName());
		//
		ComponentExt compExt = (ComponentExt)comp;
		Location location = compExt.getLocation();
		assertEquals("right", location.getDirection());
		assertEquals(BigInteger.valueOf(-1), location.getWidth());
		assertEquals(BigInteger.valueOf(-1), location.getHeight());
		assertEquals(BigInteger.valueOf(39), location.getX());
		assertEquals(BigInteger.valueOf(38), location.getY());
		//
		Component comp1 = comps.get(1);
		assertTrue(comp1.isIsRequired());
		assertEquals("PeriodicStateShared", comp1.getCompositeType());
		assertEquals("ConsoleOut1", comp1.getInstanceName());
		assertEquals("127.0.0.1/sirius.host_cxt/ConsoleOut1.rtc", comp1.getPathUri());
		assertEquals("RTC:Noriaki Ando, AIST.example.ConsoleOut:1.0", comp1.getId());
		//
		Component comp2 = comps.get(2);
		assertEquals("PeriodicECShared", comp2.getCompositeType());
		//
		Component comp3 = comps.get(3);
		assertEquals("Grouping", comp3.getCompositeType());
		//
		Component comp4 = comps.get(4);
		assertEquals("None", comp4.getCompositeType());
		//
		List<ExecutionContext> ecs1 = comp1.getExecutionContexts();
		ExecutionContext ec1 = ecs1.get(0);
		assertEquals("PERIODIC", ec1.getKind());
		assertEquals(Double.valueOf(1000.0d), ec1.getRate());
		//
		ComponentExt compExt1 = (ComponentExt)comp1;
		Location location1 = compExt1.getLocation();
		assertEquals("right", location1.getDirection());
		assertEquals(BigInteger.valueOf(-1), location1.getWidth());
		assertEquals(BigInteger.valueOf(-1), location1.getHeight());
		assertEquals(BigInteger.valueOf(157), location1.getX());
		assertEquals(BigInteger.valueOf(38), location1.getY());
		//
		List<DataportConnector> conns = profile.getDataPortConnectors();
		DataportConnector conn = conns.get(0);
		assertEquals("Flush", conn.getSubscriptionType());
		assertEquals("Push", conn.getDataflowType());
		assertEquals("CORBA_Any", conn.getInterfaceType());
		assertEquals("TimedLong", conn.getDataType());
		assertEquals("out_in", conn.getName());
		assertEquals("fd439a4c-cdb2-21dd-015a-005056c00008", conn.getConnectorId());
		//
		TargetPort targets = conn.getSourceDataPort();
		assertEquals("RTC:Noriaki Ando, AIST.example.ConsoleIn:1.0", targets.getComponentId());
		assertEquals("ConsoleIn0", targets.getInstanceName());
		assertEquals("out", targets.getPortName());
		//
		TargetPort targett = conn.getTargetDataPort();
		assertEquals("RTC:Noriaki Ando, AIST.example.ConsoleOut:1.0", targett.getComponentId());
		assertEquals("ConsoleOut0", targett.getInstanceName());
		assertEquals("in", targett.getPortName());
	}
}
