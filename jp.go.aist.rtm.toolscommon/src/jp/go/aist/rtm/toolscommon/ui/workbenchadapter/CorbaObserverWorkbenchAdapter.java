package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;

import org.eclipse.jface.resource.ImageDescriptor;

public class CorbaObserverWorkbenchAdapter extends ModelElementWorkbenchAdapter {
	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return null;
	}

	@Override
	public String getLabel(Object o) {
		if (o instanceof CorbaStatusObserver) {
			return "StatusObserver";
		} else if (o instanceof CorbaLogObserver) {
			return "LogObserver";
		}
		return "Observer";
	}

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		CorbaObserver c = (CorbaObserver) o;
		if (!c.getPropertyKeys().isEmpty()) {
			result.add(c.getPropertyMap());
		}
		return result.toArray();
	}

}
