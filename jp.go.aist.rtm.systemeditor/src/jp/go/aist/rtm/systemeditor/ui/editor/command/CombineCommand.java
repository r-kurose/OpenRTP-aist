package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;

/**
 * システムダイアグラムに複合Rtcを追加するコマンド
 */
public class CombineCommand extends Command {
	private SystemDiagram parent;

	// 複合RTC
	private Component target;

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	private void removeConnections(EList components) {
		for (Object o : components) {
			Component c = (Component)o;
			for (Object o2 :c.getPorts()) {
				Port p = (Port)o2;
				for (Object o3 :p.getConnectorProfiles()) {
					ConnectorProfile cp = (ConnectorProfile)o3;
					parent.getConnectorMap().remove(cp.getConnectorId());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void adjustParentDiagram(List<Component> selectedComponents) {
		Component parentCompositeComponent = parent.getCompositeComponent();
		if (parentCompositeComponent == null) return;
		
		// サーバに対して、set_membersを呼び出す
		parentCompositeComponent.setComponentsR(getTargets());
	}

	private List<Component> getTargets() {
		List<Component> targets = new ArrayList<Component>();
		for (Object o : parent.getComponents()) {
			if (target.getComponents().contains(o)) continue;
			targets.add((Component)o);
		}
		targets.add(target);
		return targets;
	}

	/**
	 * 親となるシステムダイアグラムを設定する
	 * 
	 * @param parent
	 *            親となるシステムダイアグラム
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * 複合コンポーネントを設定する
	 * 
	 * @param target
	 *            複合コンポーネント
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
