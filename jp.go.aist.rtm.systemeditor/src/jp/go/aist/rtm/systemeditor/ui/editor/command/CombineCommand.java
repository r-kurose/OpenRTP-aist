package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * システムダイアグラムに複合Rtcを追加するコマンド
 */
public class CombineCommand extends Command {

	private SystemDiagram parent;

	// 複合RTC
	private Component target;

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		// 複合コンポーネントの子ウィンドウにて複合コンポーネントを作成したときの処理
		adjustParentDiagram(target.getComponents());

		// 子RTCをダイアグラムから消す
		parent.removeComponents(target.getComponents());

		// 子RTCのポートにつながっていた接続を消す
		removeConnections(target.getComponents());

		// 複合コンポーネントをダイアグラムに追加する
		parent.addComponent(target);
	}

	private void removeConnections(EList<Component> components) {
		for (Component c : components) {
			for (Port p : c.getPorts()) {
				for (ConnectorProfile cp : p.getConnectorProfiles()) {
					parent.getConnectorMap().remove(cp.getConnectorId());
				}
			}
		}
	}

	private void adjustParentDiagram(List<Component> selectedComponents) {
		Component parentCompositeComponent = parent.getCompositeComponent();
		if (parentCompositeComponent == null) {
			return;
		}
		// サーバに対して、set_membersを呼び出す
		parentCompositeComponent.setComponentsR(getTargets());
	}

	private List<Component> getTargets() {
		List<Component> targets = new ArrayList<Component>();
		for (Component c : parent.getComponents()) {
			if (target.getComponents().contains(c)) {
				continue;
			}
			targets.add(c);
		}
		targets.add(target);
		return targets;
	}

	/**
	 * 親となるシステムダイアグラムを設定する
	 * 
	 * @param parent 親となるシステムダイアグラム
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * 複合コンポーネントを設定する
	 * 
	 * @param target 複合コンポーネント
	 */
	public void setTarget(Component target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
	}

}
