package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * InPort‚ÌWorkbenchAdapter
 */
public class InPortWorkbenchAdapter extends PortWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/InPort.png");
	}

	@Override
	public String getLabel(Object o) {
		return Messages.getString("InPortWorkbenchAdapter.label");
	}
}
