package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * ポートに関するユーティリティクラス
 */
public class PortHelper {

	/**
	 * 指定したポートにコネクタが存在するかを返す
	 * 
	 * @param port
	 * @return
	 */
	public static boolean isConnected(Port port) {
		if (port == null)
			return false;
		return !port.getConnectorProfiles().isEmpty();
	}

	/**
	 * 選択されているポートを返す
	 * 
	 * @param selection
	 * @return
	 */
	public static Port getPort(ISelection selection) {
		PortEditPart part = getPortPart(selection);
		if (part == null)
			return null;
		return part.getModel();
	}

	/**
	 * ポートが公開されているかを返す
	 * 
	 * @param port
	 * @return
	 */
	public static boolean isExported(Port port) {
		if (port == null)
			return false;
		if (port.eContainer() == null)
			return false;
		if (!(port.eContainer().eContainer() instanceof SystemDiagram))
			return false;
		SystemDiagram diagram = (SystemDiagram) port.eContainer().eContainer();
		Component component = diagram.getCompositeComponent();
		if (component == null)
			return false;
		for (Object element : component.getPorts()) {
			Port temp = (Port) element;
			if (temp.getOriginalPortString().equals(port.getOriginalPortString()))
				return true;
		}
		return false;
	}

	/**
	 * 選択されているポートのEditPartを返す
	 * 
	 * @param selection
	 * @return
	 */
	public static PortEditPart getPortPart(ISelection selection) {
		if (!(selection instanceof IStructuredSelection))
			return null;
		Object part = ((IStructuredSelection) selection).getFirstElement();
		if (!(part instanceof PortEditPart))
			return null;
		return (PortEditPart) part;
	}

	/**
	 * ポートの接続可否の描画を切り替えます。
	 * 
	 * @param port
	 *            対象ポート
	 * @param connectable
	 *            接続可能な場合はtrue
	 */
	public static void refreshViewPortAsConnectable(PortEditPart port, boolean connectable) {
		if (connectable) {
			port.getFigure().setLineWidth(2);
			port.getFigure().setScale(1.2, 1.2);
		} else {
			port.getFigure().setLineWidth(1);
			port.getFigure().setScale(1.0, 1.0);
		}
	}

}
