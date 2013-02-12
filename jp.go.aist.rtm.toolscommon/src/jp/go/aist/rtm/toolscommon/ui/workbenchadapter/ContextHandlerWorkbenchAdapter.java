package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * ContextHandlerのWorkbenchAdapter
 */
public class ContextHandlerWorkbenchAdapter extends
		ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return null;
	}

	@Override
	public String getLabel(Object o) {
		return ((ContextHandler) o).getType();
	}

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		ContextHandler c = (ContextHandler) o;
		result.addAll(c.getOwnerContexts());
		return result.toArray();
	}
}
