package jp.go.aist.rtm.systemeditor.ui.util;

/**
 * staticメソッドのみ定義
 */
public class RTMixin {

	public static String form(String fm, String... args) {
		String result = fm;
		for (int i = 0; i < args.length; i++) {
			String p = "{" + i + "}";
			int j = result.indexOf(p);
			if (j == -1) {
				continue;
			}
			String head = result.substring(0, j);
			String tail = result.substring(j + p.length());
			result = head + args[i] + tail;
		}
		return result;
	}

	public static boolean isBlank(String s) {
		return (s == null || s.isEmpty());
	}

	public static boolean eql(String s1, String s2) {
		return (s1 != null && s1.equals(s2));
	}

	public static String to_cid(Object o) {
		if (o == null) {
			return "null";
		}
		return o.getClass().getSimpleName() + "@" + Integer.toHexString(o.hashCode());
	}

}
