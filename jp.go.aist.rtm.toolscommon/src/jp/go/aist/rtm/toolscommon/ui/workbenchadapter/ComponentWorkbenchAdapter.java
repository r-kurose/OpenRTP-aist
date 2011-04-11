package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * ComponentのWorkbenchAdapter
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

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		Component c = (Component) o;
		if (!c.getExecutionContextHandler().getOwnerContexts().isEmpty()) {
			result.add(c.getExecutionContextHandler());
		}
		if (!c.getParticipationContextHandler().getOwnerContexts().isEmpty()) {
			result.add(c.getParticipationContextHandler());
		}
		result.addAll(c.getPorts());
		result.addAll(c.getComponents());
		if (c instanceof CorbaComponent) {
			CorbaComponent cc = (CorbaComponent) c;
			if (cc.getStatusObserver() != null) {
				result.add(cc.getStatusObserver());
			}
		}
		return result.toArray();
	}
}
