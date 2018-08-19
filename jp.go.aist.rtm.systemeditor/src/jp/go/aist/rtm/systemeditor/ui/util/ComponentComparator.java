package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.Comparator;

import jp.go.aist.rtm.systemeditor.ui.views.actionorderview.ActionOrderView.ActionName;
import jp.go.aist.rtm.toolscommon.model.component.Component;

public class ComponentComparator  implements Comparator<Component> {
	private ActionName type;
	
	public ComponentComparator(ActionName type) {
		this.type = type;
	}
	@Override
	public int compare(Component o1, Component o2) {
		switch (type) {
		case ACTION_START_UP:
            return compare(o1.getStartUp(), o2.getStartUp());
		case ACTION_SHUT_DOWN:
            return compare(o1.getShutDown(), o2.getShutDown());
		case ACTION_ACTIVATION:
            return compare(o1.getActivation(), o2.getActivation());
		case ACTION_DEACTIVATION:
            return compare(o1.getDeActivation(), o2.getDeActivation());
		case ACTION_RESETTING:
            return compare(o1.getResetting(), o2.getResetting());
		case ACTION_INITIALIZE:
            return compare(o1.getInitialize(), o2.getInitialize());
        default:
            return compare(o1.getFinalize(), o2.getFinalize());
		}
	}
	
	private int compare(String str1, String str2) {
		if(str1 == null) return 1;
		if(str2 == null) return -1;
        return str1.compareTo(str2);
	}
}
