package jp.go.aist.rtm.systemeditor.extension;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import jp.go.aist.rtm.systemeditor.ui.editor.command.CreateCommand;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import static jp.go.aist.rtm.toolscommon.util.ComponentUtil.*;

public abstract class CreateComponentCommandExtension {

	protected String message = "";
	protected CreateRequest request;
	protected SystemDiagram diagram;
	protected Component component;

	public String getMessage() {
		return this.message;
	}

	public void setRequest(CreateRequest request) {
		this.request = request;
	}

	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	public Component getComponent() {
		return component;
	}

	/**
	 * @return コマンド生成可能な場合はtrue
	 */
	public abstract boolean canCreate();

	/**
	 * @return エディタへコンポーネントを生成するコマンド
	 */
	public abstract Command getCreateCommand();

	/**
	 * 生成要求から新しいコンポーネントを生成します。
	 * 
	 * @return 生成要求から生成された新規コンポーネント
	 */
	public Component getNewObject() {
		return (Component) request.getNewObject();
	}

	/**
	 * 同一パスIDのコンポーネントの一意なインスタンス名を作成します。(オフライン)
	 * 
	 * @param comp
	 *            対象コンポーネント
	 * @param sd
	 *            コンポーネントを追加するダイアグラム
	 * @return コンポーネントの一意なインスタンス名
	 */
	public String createUniqueInstanceName(ComponentSpecification comp,
			SystemDiagram sd) {
		int count = ComponentUtil.getComponentNumberByPathId(comp, sd);
		String result = comp.getInstanceNameL()
				+ Integer.valueOf(count).toString();
		while (find(result, sd.getRegisteredComponents()) != null) {
			result += "~";
		}
		return result;
	}

	/**
	 * コンポーネントのインスタンス名を設定します。(ポート名の正規化含む)
	 * 
	 * @param comp
	 *            対象コンポーネント
	 * @param name
	 *            インスタンス名
	 */
	public void setNewInstanceName(Component comp, String name) {
		comp.setInstanceNameL(name);
		// 設定されたインスタンス名を元にポート名を正規化
		trimPortNames(comp);
	}

	/**
	 * ダイアグラム中のコンポーネントを検索します。(オンライン)
	 * 
	 * @param comp
	 *            対象コンポーネント
	 * @param sd
	 *            コンポーネントを追加するダイアグラム
	 * @return ダイアグラム中に一致したコンポーネント。一致するコンポーネントがない場合はnull
	 */
	public Component findInDiagram(Component comp, SystemDiagram sd) {
		Component result = find(comp, sd.getRegisteredComponents());
		if (result == null) {
			if (comp.isCompositeComponent()
					&& !comp.getAllComponents().isEmpty()) {
				for (Component c : comp.getAllComponents()) {
					result = find(c, sd.getRegisteredComponents());
					if (result != null) {
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * コンポーネント生成コマンドを作成します。
	 * 
	 * @param comp
	 *            対象コンポーネント
	 * @param sd
	 *            コンポーネントを追加するダイアグラム
	 * @return エディタへコンポーネントを追加する生成コマンド
	 */
	public Command createCreateCommand(Component comp, SystemDiagram sd) {
		CreateCommand command = new CreateCommand();
		command.setParent(sd);
		command.setTarget(comp);
		return command;
	}

}
