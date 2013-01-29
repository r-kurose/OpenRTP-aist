package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import org.eclipse.jface.resource.ImageDescriptor;

public class PropertyMapWorkbenchAdapter extends ModelElementWorkbenchAdapter {
	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return null;
	}

	@Override
	public String getLabel(Object o) {
		return "properties";
	}

	@Override
	public Object[] getChildren(Object o) {
		return null;
	}

}
