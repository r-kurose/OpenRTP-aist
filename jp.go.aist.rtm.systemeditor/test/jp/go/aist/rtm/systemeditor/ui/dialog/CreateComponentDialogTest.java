package jp.go.aist.rtm.systemeditor.ui.dialog;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import junit.framework.TestCase;

public class CreateComponentDialogTest extends TestCase {

	/**
	 * マネージャでのコンポーネント作成ダイアログのテスト用メソッド
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
	    shell.pack();
	    shell.open();
	    CreateComponentDialog dialog = new CreateComponentDialog(shell);
//	    dialog.setTypeList(typeList);
		dialog.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

//	private static EList<String> setupTypeList() {
//		BasicEList<String> result = new BasicEList<String>();
//		result.add("PeriodicECSharedComposite");
//		result.add("ConsoleIn");
//		result.add("ConsoleOut");
//		result.add("SequenceInComponent");
//		result.add("SequenceOutComponent");
//		return result;
//	}

}
