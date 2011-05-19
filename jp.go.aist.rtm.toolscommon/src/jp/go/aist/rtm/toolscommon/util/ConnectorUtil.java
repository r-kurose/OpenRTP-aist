package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl;

/**
 * ポート間で接続可能なプロパティを管理するユーティリティ
 * 
 */
public class ConnectorUtil {

	/**
	 * 両端のポートがともにAnyのデータ型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyDataType(OutPort source, InPort target) {
		return source.isAllowAnyDataType() && target.isAllowAnyDataType();
	}

	/**
	 * 両端のポートがともにAnyのインターフェース型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyInterfaceType(OutPort source, InPort target) {
		return source.isAllowAnyInterfaceType()
				&& target.isAllowAnyInterfaceType();
	}

	/**
	 * 両端のポートがともにAnyのデータフロー型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyDataflowType(OutPort source, InPort target) {
		return source.isAllowAnyDataflowType()
				&& target.isAllowAnyDataflowType();
	}

	/**
	 * 両端のポートがともにAnyのサブスクリプション型を許すかを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnySubscriptionType(OutPort source,
			InPort target) {
		return source.isAllowAnySubscriptionType()
				&& target.isAllowAnySubscriptionType();
	}

	/**
	 * 使用可能なデータ型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowDataTypes(OutPort source, InPort target) {
		List<String> sourceTypes = source.getDataTypes();
		List<String> targetTypes = target.getDataTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				dataTypeComparer);
		result = sortTypes(result);
		return result;
	}

	/**
	 * 使用可能なインターフェース型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowInterfaceTypes(OutPort source,
			InPort target) {
		List<String> sourceTypes = source.getInterfaceTypes();
		List<String> targetTypes = target.getInterfaceTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				ignoreCaseComparer);
		result = sortTypes(result);
		return result;
	}

	/**
	 * 使用可能なデータフロー型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowDataflowTypes(OutPort source,
			InPort target) {
		List<String> sourceTypes = source.getDataflowTypes();
		List<String> targetTypes = target.getDataflowTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				ignoreCaseComparer);
		return result;
	}

	/**
	 * 使用可能なサブスクリプション型のリストを返す
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowSubscriptionTypes(OutPort source,
			InPort target) {
		List<String> sourceTypes = source.getSubscriptionTypes();
		List<String> targetTypes = target.getSubscriptionTypes();
		//
		List<String> result = getAllowList(sourceTypes, targetTypes,
				ignoreCaseComparer);
		return result;
	}

	/**
	 * 2つの文字列のリストを受け取り、両方に存在する文字列だけのリストを作成する。 「Any」が含まれる場合には、相手先すべての文字列を許す。
	 * 返り値のリストに「Any」自体は含まれないことに注意すること。
	 * <p>
	 * 文字列はCaseを無視して比較が行われる。<br>
	 * Caseが違う文字列の場合、結果のリストに含まれるのは1番目の引数の文字列とおなじCaseとなる。<br>
	 * 順番は、oneの出現順の後に、twoの出現順（oneがanyの場合のみ）で表示される。
	 * 
	 * @param one
	 * @param two
	 * @param comparer
	 * @return
	 */
	public static List<String> getAllowList(List<String> one, List<String> two,
			TypeComparer comparer) {
		boolean isAllowAny_One = PortImpl.isExistAny(one);
		boolean isAllowAny_Two = PortImpl.isExistAny(two);

		List<String> result = new ArrayList<String>();
		for (String type1 : one) {
			if (PortImpl.isAnyString(type1)) {
				continue;
			}
			if (isAllowAny_Two) {
				result.add(type1);
			} else {
				String match = null;
				for (String type2 : two) {
					match = comparer.match(type1, type2);
					if (match != null) {
						break;
					}
				}
				if (match != null) {
					result.add(match);
				}
			}
		}
		if (isAllowAny_One) {
			for (String type1 : two) {
				if (PortImpl.isAnyString(type1)) {
					continue;
				}
				String match = null;
				for (String type2 : result) {
					match = comparer.match(type1, type2);
					if (match != null) {
						break;
					}
				}
				if (match == null) {
					result.add(type1);
				}
			}
		}
		for (String type : new ArrayList<String>(result)) {
			if (PortImpl.isAnyString(type)) {
				result.remove(type);
			}
		}
		return result;
	}

	/** 型比較インターフェース */
	public static interface TypeComparer {
		String match(String type1, String type2);
	}

	/** デフォルト型比較(IgnoreCase) */
	static TypeComparer ignoreCaseComparer = new TypeComparer() {
		@Override
		public String match(String type1, String type2) {
			if (type1 != null && type1.equalsIgnoreCase(type2)) {
				return type1;
			}
			return null;
		}
	};

	/** データ型比較 */
	static TypeComparer dataTypeComparer = new TypeComparer() {
		@Override
		public String match(String type1, String type2) {
			boolean isIFR1 = isIFR(type1);
			boolean isIFR2 = isIFR(type2);
			// IFR形式同士(1.1)、単純形式同士(1.0)の場合はデフォルト型比較
			if (isIFR1 == isIFR2) {
				return ignoreCaseComparer.match(type1, type2);
			}
			// 1.1/1.0混在時は後方一致によるあいまい比較
			String ifrType = null;
			String oldType = null;
			if (isIFR1) {
				ifrType = type1;
				oldType = type2;
			} else if (isIFR2) {
				ifrType = type2;
				oldType = type1;
			}
			if (ifrType == null) {
				return null;
			}
			String ifr[] = ifrType.split(":");
			String ifrSeg[] = ifr[1].split("/");
			String oldSeg[] = oldType.split("::");
			if (oldSeg.length > ifrSeg.length) {
				return null;
			}
			for (int i = 1; i <= oldSeg.length; i++) {
				String s1 = oldSeg[oldSeg.length - i];
				String s2 = ifrSeg[ifrSeg.length - i];
				if (!s1.equalsIgnoreCase(s2)) {
					return null;
				}
			}
			// 1.1/1.0混在時のConnectorProfileにはIFR形式を使用
			// return oldType;
			return ifrType;
		}
	};

	/** IFR形式の場合はtrue (ex. IDL:RTC/TimedLong:1.0) */
	static boolean isIFR(String type) {
		String ifr[] = (type == null ? "" : type).split(":");
		if (ifr.length == 3 && ifr[0].equals("IDL")) {
			return true;
		}
		return false;
	}

	public static List<String> sortTypes(List<String> list) {
		return sortTypes(list, false);
	}

	public static List<String> sortTypes(List<String> list,
			final boolean reverse) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b) * (reverse ? -1 : 1);
			}
		});
		return list;
	}

	public static String join(List<String> list, String d) {
		String result = "";
		for (String s : list) {
			result += (result.isEmpty() ? "" : d) + s;
		}
		return result;
	}

}
