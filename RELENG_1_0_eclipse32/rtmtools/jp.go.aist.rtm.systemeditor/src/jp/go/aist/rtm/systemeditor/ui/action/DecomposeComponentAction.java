package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.action.Action;

/**
 * 複合コンポーネントを解除するアクション
 *
 */
public class DecomposeComponentAction extends Action {

	private Component target;
	private SystemDiagram parent;

	public void setTarget(Component compositeComponent) {
		target = compositeComponent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		if (!CompositeComponentHelper.openConfirm(target, Messages.getString("DecomposeComponentAction.0"))) //$NON-NLS-1$
			return;
		ComponentUtil.closeCompositeComponent(target);
		
		// 子コンポーネントをダイアグラムに追加する
		for (Object o : target.getComponents()) {
			Component c = (Component)o;
			parent.addComponent(c);
		}
		
		// 複合コンポーネントをダイアグラムから消す
		parent.removeComponent(target);
		
		// 複合コンポーネントにつながっていた接続を消す
		removeConnections();
		
		// ネストしている場合はメンバーの再設定が必要
		if (parent.getCompositeComponent() != null) {
			parent.getCompositeComponent().setComponentsR(parent.getComponents());
		}
		
		// オンラインの複合コンポーネントは、exitする
		if (target instanceof CorbaComponent) {
//			((CorbaComponent) target).exitR();
			int defaultTimeout = ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
					ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
			TimeoutWrapper wrapper = new TimeoutWrapper(defaultTimeout);
			wrapper.setJob(new TimeoutWrappedJob(){
				@Override
				protected Object executeCommand() {
					return ((CorbaComponent) target).exitR();
				}});
			wrapper.start();
		}
	}

	private void removeConnections() {
		for (Object o2 : target.getPorts()) {
			Port p = (Port)o2;
			for (Object o3 :p.getConnectorProfiles()) {
				ConnectorProfile cp = (ConnectorProfile)o3;
				parent.getConnectorMap().remove(cp.getConnectorId());
			}
		}
	}

	public void setParent(SystemDiagram diagram) {
		parent = diagram;
	}

}
