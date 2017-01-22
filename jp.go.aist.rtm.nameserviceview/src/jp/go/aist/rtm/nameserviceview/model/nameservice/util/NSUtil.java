package jp.go.aist.rtm.nameserviceview.model.nameservice.util;

import org.omg.CosNaming.NameComponent;

public class NSUtil {

	public static boolean eql(NameComponent[] o1, NameComponent[] o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.length != o2.length) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(NameComponent o1, NameComponent o2) {
		if (o1 == null) {
			return false;
		}
		if (o1.id == null || !o1.id.equals(o2.id)) {
			return false;
		}
		if (o1.kind == null || !o1.kind.equals(o2.kind)) {
			return false;
		}
		return true;
	}

}
