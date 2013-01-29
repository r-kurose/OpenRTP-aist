package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

public class SystemDiagramWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		SystemDiagram c = (SystemDiagram) o;
		if (!c.getPropertyKeys().isEmpty()) {
			result.add(c.getPropertyMap());
		}
		return result.toArray();
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
