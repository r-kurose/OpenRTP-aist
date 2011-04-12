package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;

import org.eclipse.jface.action.Action;

/**
 * ポートの接続をすべて削除するアクション
 *
 */
public class AllDisconnectAction extends Action {

	private Port target;
	private SystemDiagram parent;

	public void setTarget(Port port) {
		target = port;
	}

	@Override
	public void run() {
		if (target == null) return;
		target.disconnectAll();
		if (parent.getKind() == SystemDiagramKind.ONLINE_LITERAL) {
			CompositeComponentHelper.synchronizeAll(parent);
		}
	}

	public void setParent(SystemDiagram diagram) {
		parent = diagram;
	}

}
