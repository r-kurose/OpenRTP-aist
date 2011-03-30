package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

public class SystemDiagramWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public Object[] getChildren(Object o) {
		return null;
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/RT.png");
	}

	@Override
	public String getLabel(Object o) {
		return Messages.getString("SystemDiagramWorkbenchAdapter.label");
	}

}
