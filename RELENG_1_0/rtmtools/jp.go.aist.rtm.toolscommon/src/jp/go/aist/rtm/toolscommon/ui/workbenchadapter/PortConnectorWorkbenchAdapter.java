package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

public class PortConnectorWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public Object[] getChildren(Object o) {
		PortConnector c = (PortConnector) o;
		return new Object[] { c.getSource(), c.getTarget() };
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/PortConnector.png");
	}

	@Override
	public String getLabel(Object o) {
		return Messages.getString("PortConnectorWorkbenchAdapter.label");
	}
}
