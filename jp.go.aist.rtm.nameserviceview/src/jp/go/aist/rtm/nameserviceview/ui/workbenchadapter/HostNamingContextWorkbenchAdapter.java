package jp.go.aist.rtm.nameserviceview.ui.workbenchadapter;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * HostNamingContextのWorkbenchAdapter
 */
public class HostNamingContextWorkbenchAdapter extends
		NamingContextNodeWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return NameServiceViewPlugin.getImageDescriptor("icons/Server.png");
	}
}
