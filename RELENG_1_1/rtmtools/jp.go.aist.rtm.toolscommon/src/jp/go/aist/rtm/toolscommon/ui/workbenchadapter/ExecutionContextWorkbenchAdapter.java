package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * ExecutionContextのWorkbenchAdapter
 */
public class ExecutionContextWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/ExecutionContext.gif");
	}

	@Override
	public String getLabel(Object o) {
		return Messages.getString("ExecutionContextWorkbenchAdapter.label");
	}
	
	@Override
	public Object[] getChildren(Object o) {
		return null;
	}
}
