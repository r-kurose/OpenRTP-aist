package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

public class PortConnectorWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		PortConnector c = (PortConnector) o;
		if (c.getConnectorProfile() != null) {
			if (!c.getConnectorProfile().getPropertyKeys().isEmpty()) {
				result.add(c.getConnectorProfile().getPropertyMap());
			}
		}
		result.add(c.getSource());
		result.add(c.getTarget());
		return result.toArray();
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
