package jp.go.aist.rtm.rtcbuilder.util;

import java.util.ArrayList;
import java.util.List;

public class RTCUtil {

	public static String form(String fm, String[] args) {
		String result = fm;
		for (int index = 0; index < args.length; index++) {
			String p = "{" + index + "}";
			int j = result.indexOf(p);
			if (j == -1) {
				continue;
			}
			String head = result.substring(0, j);
			String tail = result.substring(j + p.length());
			result = head + args[index] + tail;
		}
		return result;
	}

	public static boolean isBlank(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}
		return false;
	}

	public static List<String> split(String list, String delimit) {
		List<String> result = new ArrayList<String>();
		for (String s : list.split(delimit)) {
			result.add(s);
		}
		return result;
	}

	public static String join(List<String> list, String delimit) {
		String result = "";
		for (String s : list) {
			if (!result.isEmpty()) {
				result += delimit;
			}
			result += s;
		}
		return result;
	}

}
