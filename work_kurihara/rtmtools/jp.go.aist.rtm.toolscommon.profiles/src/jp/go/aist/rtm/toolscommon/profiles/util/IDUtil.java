package jp.go.aist.rtm.toolscommon.profiles.util;

import org.openrtp.namespaces.rtc.version02.BasicInfo;

public class IDUtil {

	/**
	 * RTC IDを表すクラス
	 * 
	 * RTC: [ベンダ名] : [カテゴリ] : [コンポーネント名] : [バージョン番号]
	 */
	public static class RTCId {
		public String vendor;
		public String category;
		public String name;
		public String version;

		public RTCId(String vendor, String category, String name, String version) {
			this.vendor = to_s(vendor);
			this.category = to_s(category);
			this.name = to_s(name);
			this.version = to_s(version);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof RTCId) {
				RTCId id = (RTCId) o;
				if (eql(vendor, id.vendor) && eql(category, id.category)
						&& eql(name, id.name) && eql(version, id.version)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "RTC:" + to_s(vendor) + ":" + to_s(category) + ":"
					+ to_s(name) + ":" + to_s(version);
		}
	}

	/**
	 * RTS IDを表すクラス
	 * 
	 * RTSystem: [ベンダ名] : [システム名] : [バージョン番号]
	 */
	public static class RTSId {
		public String vendor;
		public String name;
		public String version;

		public RTSId(String vendor, String name, String version) {
			this.vendor = to_s(vendor);
			this.name = to_s(name);
			this.version = to_s(version);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof RTSId) {
				RTSId id = (RTSId) o;
				if (eql(vendor, id.vendor) && eql(name, id.name)
						&& eql(version, id.version)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "RTSystem:" + to_s(vendor) + ":" + to_s(name) + ":"
					+ to_s(version);
		}
	}

	/**
	 * IDをパースして、RTC IDオブジェクトを生成します。
	 * 
	 * @param s
	 *            RTC IDの文字列
	 * @return RTC IDオブジェクト
	 */
	public static RTCId parseRTCId(String s) {
		if (s == null) {
			return null;
		}
		if (s.charAt(s.length() - 1) == ':') {
			s += "--";
		}
		String[] ss = s.split(":");
		if (ss.length < 1 || !eql("RTC", ss[0])) {
			return null;
		}
		if (ss.length == 5) {
			RTCId id = new RTCId(ss[1], ss[2], ss[3], ss[4]);
			if ("--".equals(id.version)) {
				id.version = "";
			}
			return id;
		} else if (ss.length == 3) {
			// 旧フォーマット
			// RTC: [ベンダ名] . [カテゴリ名] . [コンポーネント名] : [バージョン番号]
			RTCId id = new RTCId(null, null, null, ss[2]);
			if ("--".equals(id.version)) {
				id.version = "";
			}
			String[] sss = ss[1].split("\\.");
			if (sss.length > 0) {
				id.vendor = sss[0];
			}
			if (sss.length > 1) {
				id.category = sss[1];
			}
			if (sss.length > 2) {
				id.name = sss[2];
			}
			return id;
		}
		return null;
	}

	/**
	 * BasicInfoから RTC IDオブジェクトを生成します。
	 * 
	 * @param bi
	 *            BasicInfoオブジェクト
	 * @return RTC IDオブジェクト
	 */
	public static RTCId createRTCIdBy(BasicInfo bi) {
		if (bi == null) {
			return null;
		}
		RTCId id = new RTCId(bi.getVendor(), bi.getCategory(), bi.getName(), bi
				.getVersion());
		return id;
	}

	/**
	 * IDをパースして、RTS IDオブジェクトを生成します。
	 * 
	 * @param s
	 *            RTS IDの文字列
	 * @return RTS IDオブジェクト
	 */
	public static RTSId parseRTSId(String s) {
		if (s == null) {
			return null;
		}
		if (s.charAt(s.length() - 1) == ':') {
			s += "--";
		}
		String[] ss = s.split(":");
		if (ss.length < 1 || !eql("RTSystem", ss[0])) {
			return null;
		}
		if (ss.length == 4) {
			RTSId id = new RTSId(ss[1], ss[2], ss[3]);
			if ("--".equals(id.version)) {
				id.version = "";
			}
			return id;
		} else if (ss.length == 3) {
			// 旧フォーマット
			// RTSystem : [ベンダ名] . [システム名] : [バージョン番号]
			RTSId id = new RTSId(null, null, ss[2]);
			if ("--".equals(id.version)) {
				id.version = "";
			}
			String[] sss = ss[1].split("\\.");
			if (sss.length > 0) {
				id.vendor = sss[0];
			}
			if (sss.length > 1) {
				id.name = sss[1];
			}
			return id;
		}
		return null;
	}

	public static boolean eql(String s1, String s2) {
		return (s1 != null && s1.equals(s2));
	}

	public static String to_s(String s) {
		return (s == null) ? "" : s;
	}

}
