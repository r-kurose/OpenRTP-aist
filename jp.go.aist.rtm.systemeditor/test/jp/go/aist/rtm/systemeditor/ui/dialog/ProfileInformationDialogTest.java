package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ProfileInformationDialogTest {

	/**
	 * RTS保存ダイアログ用のテスト
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
	    shell.pack();
	    shell.open();
//	    List<AbstractComponent> list = setupSelectedComponents();
	    ProfileInformationDialog dialog = new ProfileInformationDialog(shell);
	    List<Component> setupSelectedComponents = NewCompositeComponentDialogTest.setupSelectedComponents();
		SystemDiagram systemDiagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		systemDiagram.addComponents(setupSelectedComponents);
		dialog.setSystemDiagram(systemDiagram);

	    if( dialog.open() == IDialogConstants.OK_ID ) {
	    	for (Component component : setupSelectedComponents) {
				System.out.println(component.getInstanceNameL() + ":" + component.isRequired());
			}
	    }
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
