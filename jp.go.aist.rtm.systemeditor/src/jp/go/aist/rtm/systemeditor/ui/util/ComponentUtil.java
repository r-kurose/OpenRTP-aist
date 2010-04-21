package jp.go.aist.rtm.systemeditor.ui.util;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * コンポーネント処理に関するユーティリティ
 */
public class ComponentUtil {

	/**
	 * ダイアグラムを表示しているエディタを探し出して返す。
	 * 見つからなければ、nullを返す。
	 * @param diagram	処理対象のシステムダイアグラム
	 * @return
	 */
	public static AbstractSystemDiagramEditor findEditor(
			SystemDiagram diagram) {
		if (diagram == null) return null;
		if (PlatformUI.getWorkbench() == null) return null;
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) return null;
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (activePage == null) return null;
		IEditorReference[] references = activePage.getEditorReferences();
		for (int i = 0; i < references.length; i++) {
			IEditorPart editor = references[i].getEditor(false);
			if (!(editor instanceof AbstractSystemDiagramEditor)) continue;
			AbstractSystemDiagramEditor systemEditor = (AbstractSystemDiagramEditor) editor;
			if (systemEditor.getSystemDiagram() == diagram) return systemEditor;
		}
		return null;
	}

	/**
	 * 開いた子ウィンドウを閉じる
	 * @param target	複合コンポーネント
	 */
	public static void closeCompositeComponent(Component target) {
		final AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(target.getChildSystemDiagram());
		if (editor != null) {
				PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					try {
						PlatformUI
								.getWorkbench()
								.getActiveWorkbenchWindow()
								.getActivePage()
								.closeEditor(
										editor,
										false);

					} catch (Exception e) {
						e.printStackTrace();
						// void
					}
				}
			});
		}
	}

	/**
	 * コンポーネント数を検索するメソッド(オフラインエディタ用)
	 * @param component	コンポーネント仕様
	 * @param rootSystemDiagram　ルートのシステムダイアグラム
	 * @return
	 */
	public static int getComponentNumberByPathId(
			ComponentSpecification component, SystemDiagram rootSystemDiagram) {
		int result = 0;
		if (component == null || rootSystemDiagram == null) {
			return result;
		}
		String basePathId = component.getPathId();
		for (Component tempComponent : rootSystemDiagram.getRegisteredComponents()) {
			if (tempComponent.getPathId() != null ) {
				String pathID = tempComponent.getPathId();
				if( pathID.equals(basePathId)) {
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * @param rectangle	基準となるコンポーネントの位置
	 * @param count　　　　新規に登録するコンポーネントの番号
	 * @return　　　　　　　　新規に登録するコンポーネントの位置
	 */
	public static Rectangle getNewComponentConstraint(Rectangle rectangle,
			int count) {
		int x = rectangle.getX() + count * 100;
		int y = rectangle.getY();
		Rectangle result = new Rectangle();
		result.setX(x);
		result.setY(y);
		result.setHeight(rectangle.getHeight());
		result.setWidth(rectangle.getWidth());
		return result;
	}

	/**
	 * @param cs	複写元のConfigurationSet
	 * @return　　　　　複写したConfigurationSet
	 */
	public static ConfigurationSet cloneConfigurationSet(ConfigurationSet cs) {
		ConfigurationSet result = (ConfigurationSet) EcoreUtil.copy(cs);
		if (cs.getSynchronizationSupport() != null) {
			result.setSynchronizationSupport(cs.getSynchronizationSupport());
		}
		return result;
	}

	/**
	 * ポート名を正規化("RTC名"."ポート名" 形式)します。(オフライン)
	 */
	public static String createPortName(String insName, String portName) {
		String result = (portName == null) ? "" : portName;
		if (!result.startsWith(insName)) {
			String[] ns = result.split("\\.");
			result = insName + "." + ns[ns.length - 1];
		}
		return result;
	}

}
