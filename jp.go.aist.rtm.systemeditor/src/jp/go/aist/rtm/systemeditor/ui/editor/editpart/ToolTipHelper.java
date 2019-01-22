package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.StackLayout;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

/**
 * ツールチップ作成ヘルパー
 */
public class ToolTipHelper {

	private static final String CRLF = "\r\n";

	/**
	 * ECのツールチップを取得します。
	 *
	 * @param ec
	 * @return
	 */
	public static Panel getECToolTip(ExecutionContext ec) {
		StringBuilder sb = new StringBuilder();
		sb.append("kind: ").append(nv(ec.getKindName())).append(CRLF);
		sb.append("rate: ").append(nv(ec.getRateL())).append(CRLF);
		sb.append("state: ").append(nv(ec.getStateName()));
		if (ec instanceof CorbaExecutionContext) {
			CorbaExecutionContext cec = (CorbaExecutionContext) ec;
			CorbaComponent comp = (CorbaComponent) ec.eContainer();
			if (comp != null) {
				sb.append(CRLF);
				sb.append("component state: ").append(nv(cec.getComponentStateName(comp)));
			}
			String type = cec.getProperty("type");
			if (type != null && !type.isEmpty()) {
				sb.append(CRLF);
				sb.append("type: ").append(type);
			}
		}
		Component owner = ec.getOwner();
		sb.append(CRLF);
		sb.append("owner: ").append((owner == null) ? null : owner.getInstanceNameL());
		return buildPanel(sb.toString());
	}

	private static Panel buildPanel(String label) {
		Panel ret = new Panel();
		ret.setLayoutManager(new StackLayout());
		Label label1 = new Label(label);
		ret.add(label1);
		return ret;
	}

	private static String nv(Object s) {
		return s == null ? "<unknown>" : s.toString();
	}

}
