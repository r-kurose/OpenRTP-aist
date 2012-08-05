package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * PortInterfaceProfileのWorkbenchAdapter
 */
public class PortInterfaceProfileWorkbenchAdapter implements IWorkbenchAdapter {

	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin
				.getImageDescriptor("icons/PortInterfaceProfile.png");
	}

	public String getLabel(Object o) {
		return Messages.getString("PortInterfaceProfileWorkbenchAdapter.label");
	}

	public Object getParent(Object o) {
		return null;
	}

	public Object[] getChildren(Object o) {
		return null;
	}
}
