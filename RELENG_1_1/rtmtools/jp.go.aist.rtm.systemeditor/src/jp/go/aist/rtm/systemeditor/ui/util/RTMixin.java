package jp.go.aist.rtm.systemeditor.ui.util;

/**
 * staticメソッドのみ定義
 */
public class RTMixin {

	public static boolean isBlank(String s) {
		return (s == null || s.isEmpty());
	}

	public static boolean eql(String s1, String s2) {
		return (s1 != null && s1.equals(s2));
	}

}
