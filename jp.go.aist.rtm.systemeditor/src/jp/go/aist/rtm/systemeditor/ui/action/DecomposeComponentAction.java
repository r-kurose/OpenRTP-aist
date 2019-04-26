package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.jface.action.Action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.CompositeComponentHelper;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * 複合コンポーネントを解除するアクション
 */
public class DecomposeComponentAction extends Action {

	private Component target;
	private SystemDiagram parent;

	public void setTarget(Component compositeComponent) {
		target = compositeComponent;
	}

	@Override
	public void run() {
		if (!CompositeComponentHelper.openConfirm(target, Messages.getString("DecomposeComponentAction.0"))) {
			return;
		}
		ComponentUtil.closeCompositeComponent(target);

		// 子コンポーネントの複合化を解除する
		List<Component> children = new ArrayList<>();
		children.addAll(target.getComponents());
		for (Component c : children) {
			target.removeComponentR(c);
		}

		// 複合コンポーネントをダイアグラムから消す
		parent.removeComponent(target);
		// 子コンポーネントをダイアグラムに追加する
		for (Component c : children) {
			parent.addComponent(c);
		}

		// 複合コンポーネントにつながっていた接続を消す
		removeConnections();

		// ネストしている場合はメンバーの再設定が必要
		if (parent.getCompositeComponent() != null) {
			parent.getCompositeComponent().setComponentsR(parent.getComponents());
		}

		// オンラインの複合コンポーネントは、exitする
		if (target instanceof CorbaComponent) {
			TimeoutWrapper wrapper = TimeoutWrapper.asDefault();
			wrapper.start(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return ((CorbaComponent) target).exitR();
				}
			});
		}
	}

	private void removeConnections() {
		for (Port p : target.getPorts()) {
			for (ConnectorProfile cp : p.getConnectorProfiles()) {
				parent.getConnectorMap().remove(cp.getConnectorId());
			}
		}
	}

	public void setParent(SystemDiagram diagram) {
		parent = diagram;
	}

}
