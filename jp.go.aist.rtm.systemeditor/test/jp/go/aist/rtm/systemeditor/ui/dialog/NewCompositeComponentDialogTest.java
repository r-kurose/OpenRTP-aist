package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.factory.CompositeComponentCreator;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.impl.ComponentFactoryImpl;
import junit.framework.TestCase;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class NewCompositeComponentDialogTest extends TestCase {
	public void testGetPathComboItems() throws Exception {
	    List<Component> selectedComponents = setupSelectedComponents();
	    String[] items = NewCompositeComponentDialogData.getPathComboItems(selectedComponents);
	    assertEquals(2, items.length);
	    assertEquals("component1", items[0]);
	    assertEquals("component2", items[1]);
	}
	
	public void testGetPorts() throws Exception {
	    List<Component> selectedComponents = setupSelectedComponents();
	    List<String> ports = NewCompositeComponentDialogData.getPorts(selectedComponents);
	    assertEquals(2, ports.size());
		assertEquals("abc.in", ports.get(0));
		assertEquals("def.out", ports.get(1));
	}
	
//	public void testCreateCompositeComponentSpecification() throws Exception {
//		NewCompositeComponentDialog dialog = new NewCompositeComponentDialog(null, true, null, null){
//			@Override
//			public String getInstanceName() {
//				return "composite1";
//			}
//			@Override
//			public String getCompositeType() {
//				return Component.COMPOSITETYPE_GROUPING;
//			}
//			@Override
//			public String getPathId() {
//				return "pathId1";
//			}
//			@Override
//			public String getExportedPortString() {
//				return "child.port1,child.port2";
//			}			
//		};
//		Component compositeComponent = NewCompositeComponentDialogData.createCompositeComponentSpecification(dialog);
//		assertEquals("child.port1,child.port2", getExportedPorts(compositeComponent));
//		assertEquals("default", compositeComponent.getActiveConfigurationSet().getId());
//	}
//	private String getExportedPorts(Component compositeComponent) {
//		ConfigurationSet activeConfigurationSet = compositeComponent.getActiveConfigurationSet();
//		for (Object  element : activeConfigurationSet.getConfigurationData()) {
//			NameValue value = (NameValue) element;
//			if (value.getName().equals("exported_ports")) {
//				return value.getValueAsString();
//			}
//		}
//		return null;
//	}

	protected NameValue createNameValue(String name, String value) {
		NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
		nv.setName(name);
		nv.setValue(value);
		return nv;
	}

	/**
	 * 新規複合コンポーネント作成ダイアログのテスト用メソッド
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
	    shell.pack();
	    shell.open();
	    List<Component> list = setupSelectedComponents();
	    CompositeComponentCreator creator = new CompositeComponentCreator();
	    Dialog dialog = new NewCompositeComponentDialog(shell, creator, list, null);
		dialog.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	static List<Component> setupSelectedComponents() {
		List<Component> list = new ArrayList<Component>();
		list.add(createComponent("component1/2", "abc", "in"));
		list.add(createComponent("component2/3", "def", "out"));
		return list;
	}
	private static Component createComponent(String pathId, String name, String portName) {
		Component component = ComponentFactory.eINSTANCE.createCorbaComponent();
		component.setPathId(pathId);
		component.setInstanceNameL(name);
		component.getPorts().add(createPort(portName));
		component.setRequired(name.equals("abc"));
		return component;
	}

	private static Port createPort(String portName) {
		Port port = new ComponentFactoryImpl().createPort();
		port.setSynchronizer(ComponentFactory.eINSTANCE.createPortSynchronizer());
		port.setNameL(portName);
//		port.setOriginalPortString(portName);
		return port;
	}


}
