package jp.go.aist.rtm.systemeditor.ui.util;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.profiles.util.IDUtil;

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
		//TODO 09.09.30 pathURI対応
		String basePathId = component.getPathId();
//		int compIndex  = basePathId.lastIndexOf(":");
//		if (compIndex >= 0) basePathId = basePathId.substring(0, compIndex);
		for (Component tempComponent : rootSystemDiagram.getRegisteredComponents()) {
			if (tempComponent.getPathId() != null ) {
				String pathID = tempComponent.getPathId();
//				int index = pathID.lastIndexOf(":");
//				pathID = pathID.substring(0, index);
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
	 * 複合RTC内を表すダイアグラムを設定します。
	 * 
	 * @param diagram
	 *            複合RTC内を表すダイアグラム
	 * @param compositeComponent
	 *            複合RTC
	 * @param parentDiagram
	 *            親のダイアグラム
	 */
	public static void setCompositeComponentDiagram(SystemDiagram diagram,
			Component compositeComponent, SystemDiagram parentDiagram) {
		// システムダイアグラム属性をセットする
		diagram.setParentSystemDiagram(parentDiagram);
		diagram.setKind(parentDiagram.getKind());
		diagram.setCreationDate(parentDiagram.getCreationDate());
		diagram.setUpdateDate(parentDiagram.getUpdateDate());

		// 複合RTC属性をセットする
		diagram.setCompositeComponent(compositeComponent);
		IDUtil.RTSId rtsId = new IDUtil.RTSId(compositeComponent.getVenderL(),
				compositeComponent.getInstanceNameL(), compositeComponent
						.getVersionL());
		diagram.setSystemId(rtsId.toString());
		for (String key : compositeComponent.getPropertyKeys()) {
			diagram.setProperty(key, compositeComponent.getProperty(key));
		}
		// 公開ポート情報をセットする
		String value = "";
		for (String p : compositeComponent.getExportedPorts()) {
			if (!value.isEmpty()) {
				value += ",";
			}
			value += p;
		}
		diagram.setProperty("exported_ports", value);

		// 子ダイアグラムにコンポーネントをセットする
		diagram.clearComponents();
		for (Object o : compositeComponent.getComponents()) {
			Component component = (Component) o;
			diagram.addComponent(component); // ルートダイアグラムのコンポーネントの子供をそのまま使いまわす
		}
	}

}
