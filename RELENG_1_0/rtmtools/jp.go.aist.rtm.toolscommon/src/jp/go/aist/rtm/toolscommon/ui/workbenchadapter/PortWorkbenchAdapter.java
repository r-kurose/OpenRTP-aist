package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.model.component.Port;

/**
 * Port‚ÌWorkbenchAdapter
 */
public abstract class PortWorkbenchAdapter extends ModelElementWorkbenchAdapter {
	@Override
	public Object[] getChildren(Object o) {
		return ((Port) o).getInterfaces().toArray();
	}
}
