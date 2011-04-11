package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * ポートに関するユーティリティクラス
 *
 */
public class PortHelper {

	/**
	 * 指定したポートにコネクタが存在するかを返す
	 * @param port
	 * @return
	 */
	public static boolean isConnected(Port port) {
		if (port == null) return false;
		return !port.getConnectorProfiles().isEmpty();
	}

	/**
	 * 選択されているポートを返す
	 * @param selection
	 * @return
	 */
	public static Port getPort(ISelection selection) {
		PortEditPart part = getPortPart(selection);
		if (part == null) return null;
		return part.getModel();
	}

	/**
	 * ポートが公開されているかを返す
	 * @param port
	 * @return
	 */
	public static boolean isExported(Port port) {
		if (port == null) return false;
		if (port.eContainer() == null) return false;
		if (!(port.eContainer().eContainer() instanceof SystemDiagram)) return false;
		SystemDiagram diagram = (SystemDiagram) port.eContainer().eContainer();
		Component component = diagram.getCompositeComponent();
		if (component == null) return false;
		for (Object element : component.getPorts()) {
			Port temp = (Port) element;
			if (temp.getOriginalPortString().equals(port.getOriginalPortString()))
				return true;
		}
		return false;
	}

	/**
	 * 選択されているポートのEditPartを返す
	 * @param selection
	 * @return
	 */
	public static PortEditPart getPortPart(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		Object part = ((IStructuredSelection) selection).getFirstElement();
		if (!(part instanceof PortEditPart)) return null;
		return (PortEditPart) part;
	}

}
