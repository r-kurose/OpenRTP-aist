package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Port;

/**
 * PortのWorkbenchAdapter
 */
public abstract class PortWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		Port c = (Port) o;
		if (c.getSynchronizer() != null) {
			if (!c.getSynchronizer().getPropertyKeys().isEmpty()) {
				result.add(c.getSynchronizer().getPropertyMap());
			}
		}
		result.addAll(c.getInterfaces());
		return result.toArray();
	}

}
