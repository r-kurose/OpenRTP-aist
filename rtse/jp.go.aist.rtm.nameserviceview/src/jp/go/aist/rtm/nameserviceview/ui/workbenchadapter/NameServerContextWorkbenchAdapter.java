package jp.go.aist.rtm.nameserviceview.ui.workbenchadapter;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.manager.NameServerContext;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * NameServderNamingContext‚ÌWorkbenchAdapter
 */
public class NameServerContextWorkbenchAdapter extends
		NamingContextNodeWorkbenchAdapter {
	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return NameServiceViewPlugin.getImageDescriptor("icons/RT.png");
	}

	@Override
	public String getLabel(Object o) {
		return ((NameServerContext) o).getNameServerName();
	}
}
