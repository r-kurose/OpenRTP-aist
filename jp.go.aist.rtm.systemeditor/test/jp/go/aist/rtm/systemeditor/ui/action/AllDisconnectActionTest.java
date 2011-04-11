package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.toolscommon.corba.CorbaObjectMock;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;
import junit.framework.TestCase;
import RTC.PortProfile;

public class AllDisconnectActionTest extends TestCase {
	private Port port1;
	private Port port2;
	private Port port3;
	private StringBuffer buffer;
	private AllDisconnectAction action;
	
	private SystemDiagram diagram;
	private Component component1;
	private Component component2;
	private Component component3;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		port1 = createPort();
		port1.setOriginalPortString("1");
		port2 = createPort();
		port2.setOriginalPortString("2");
		port3 = createPort();
		port3.setOriginalPortString("2");

		buffer = new StringBuffer();
		
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		component1 = ComponentFactory.eINSTANCE.createComponentSpecification();
		component2 = ComponentFactory.eINSTANCE.createComponentSpecification();
		component3 = ComponentFactory.eINSTANCE.createComponentSpecification();
		diagram.getComponents().add(component1);
		diagram.getComponents().add(component2);
		diagram.getComponents().add(component3);
		component1.getPorts().add(port1);
		component2.getPorts().add(port2);
		component3.getPorts().add(port3);
		
		action = new AllDisconnectAction();
		action.setTarget(port1);
		action.setParent(diagram);
	}

	private Port createPort() {
		Port port = ComponentFactory.eINSTANCE.createPort();
		port.setSynchronizer(ComponentFactory.eINSTANCE.createCorbaPortSynchronizer());
		return port;
	}

	private void setupOnline(){
		SystemEditorPreferenceManager.setInstance(new SystemEditorPreferenceManager(){
			@Override
			public int getInterval(String key) {
				return 1000;
			}});
		setCorbaObject(port1, new CorbaObjectMock(buffer));
	}

	private void setCorbaObject(Port port, CorbaObjectMock corbaObjectMock) {
		CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port.getSynchronizer();
		synchronizer.setCorbaObject(corbaObjectMock);
		synchronizer.setRTCPortProfile(new PortProfile());
		synchronizer.getRTCPortProfile().port_ref = corbaObjectMock;
	}

	private void setupOffline() {
		port1.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port2.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port3.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		
		setupConnector("1", port1, port2);
		verifyConnectCount(1, 1, 0);
		setupConnector("2", port2, port1);
		verifyConnectCount(2, 1, 1);
	}

	private void setupConnector(String connectorId, Port source, Port target) {
		PortConnector connector = PortConnectorFactory.createPortConnectorSpecification();	
		connector.setSource(source);
		connector.setTarget(target);
		
		ConnectorProfile conn = ComponentFactory.eINSTANCE.createConnectorProfile();
		conn.setConnectorId(connectorId);
		connector.setConnectorProfile(conn);
		
		connector.createConnectorR();
	}

	public void testOnline() throws Exception {
		setupOnline();
		action.run();
		assertEquals("disconnect_all ", buffer.toString());
	}
	
	public void testOffline() {
		setupOffline();
		action.run();
		verifyConnectCount(0, 0, 0);
	}
	
	private void verifyConnectCount(int count, int source, int target) {
		assertEquals(count, port1.getConnectorProfiles().size());
		assertEquals(count, port2.getConnectorProfiles().size());
		assertEquals(count, port3.getConnectorProfiles().size());
	}
	
	// TODO: オフライン時の複合コンポーネントのdisconnect allに関しては、connectorIdからPortConnectorSpecificationを探し出す処理がおそらく必要
	// TODO: 複合コンポーネントを開いたウィンドウに対する手動同期が不完全。（接続関連だけか）
}
