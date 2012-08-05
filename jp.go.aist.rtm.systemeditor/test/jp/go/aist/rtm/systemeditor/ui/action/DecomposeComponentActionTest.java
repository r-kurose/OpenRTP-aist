package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.ui.editor.command.DeleteCommand;
import jp.go.aist.rtm.toolscommon.corba.CorbaObjectMock;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import junit.framework.TestCase;

public class DecomposeComponentActionTest extends TestCase {

	private SystemDiagram diagram;
	private Component component1;
	private Component component2;
	private CorbaComponent component3;
	private StringBuffer buffer;

	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {
		diagram =  ComponentFactory.eINSTANCE.createSystemDiagram();
		component1 = ComponentFactory.eINSTANCE.createCorbaComponent();
		component2 = ComponentFactory.eINSTANCE.createCorbaComponent();
		component3 = ComponentFactory.eINSTANCE.createCorbaComponent();
		diagram.getComponents().add(component1);
		diagram.getComponents().add(component2);
		component3.getComponents().add(component1);
		component3.getComponents().add(component2);
		diagram.getComponents().add(component3);
		buffer = new StringBuffer();
		component3.setCorbaObject(new CorbaObjectMock(buffer));
	}

	public void testDecompose() throws Exception {
		DecomposeComponentAction decomposeAction = new DecomposeComponentAction();
		decomposeAction.setTarget(component3);
		decomposeAction.setParent(diagram);
		decomposeAction.run();
		assertEquals(2, diagram.getComponents().size());
		assertEquals(component1, diagram.getComponents().get(0));
		assertEquals(component2, diagram.getComponents().get(1));
		assertEquals("exit ", buffer.toString());
	}
	
	public void testDelete() throws Exception {
		DeleteCommand command = new DeleteCommand();
		command.setTarget(component3);
		command.setParent(diagram);
		command.execute();
		assertEquals(0, diagram.getComponents().size());
		assertEquals("", buffer.toString());
	}
}
