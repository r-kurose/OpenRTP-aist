package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Component‚ÌWorkbenchAdapter
 */
public class ComponentWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/Component.png");
	}

	@Override
	public String getLabel(Object o) {
		return ((Component) o).getInstanceNameL();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		result.addAll(((Component) o).getExecutionContexts());
		result.addAll(((Component) o).getPorts());
		result.addAll(((Component) o).getComponents());
		return result.toArray();
	}
}
