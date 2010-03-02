package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.jface.resource.ImageDescriptor;

public class RTCManagerWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/RTCManager.png");
	}

	@Override
	public String getLabel(Object o) {
		return ((RTCManager) o).getInstanceNameL();
	}

	@Override
	public Object[] getChildren(Object o) {
		return new Object[0];
	}
}
