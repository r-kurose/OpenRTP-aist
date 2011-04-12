package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
		return getAllowList(source.getDataTypes(), target.getDataTypes());
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
		return getAllowList(source.getInterfaceTypes(), target
				.getInterfaceTypes());
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
		return getAllowList(source.getDataflowTypes(), target
				.getDataflowTypes(), false);
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
		return getAllowList(source.getSubscriptionTypes(), target
				.getSubscriptionTypes());
	}

	private static List<String> getAllowList(List<String> one, List<String> two) {
		return getAllowList(one, two, true);
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
	 * @return
	 */
	private static List<String> getAllowList(List<String> one, List<String> two, boolean sorting) {
		boolean isAllowAny_One = PortImpl.isExistAny(one);
		boolean isAllowAny_Two = PortImpl.isExistAny(two);

		List<String> result = new ArrayList<String>();
		for (String elem1 : one) {
			if (PortImpl.isAnyString(elem1) == false) {
				boolean isEqualsIgnoreCase = false;
				for (String elem2 : two) {
					if (isAllowAny_Two || elem1.equalsIgnoreCase(elem2)) {
						isEqualsIgnoreCase = true;
						break;
					}
				}

				if (isEqualsIgnoreCase) {
					result.add(elem1);
				}
			}
		}
		if (isAllowAny_One) {
			for (String elem1 : two) {
				if (PortImpl.isAnyString(elem1) == false) {
					boolean isEqualsIgnoreCase = false;
					for (String elem2 : result) {
						if (elem1.equalsIgnoreCase(elem2)) {
							isEqualsIgnoreCase = true;
							break;
						}
					}

					if (isEqualsIgnoreCase == false) {
						result.add(elem1);
					}
				}
			}
		}
		for (Iterator<String> iter = result.iterator(); iter.hasNext();) {
			String elem = iter.next();
			if (PortImpl.isAnyString(elem)) {
				iter.remove();
			}
		}

		if(sorting) {
			// リストを文字列順でソート
			result = sortTypes(result);
		}

		return result;
	}

	public static List<String> sortTypes(List<String> list) {
		return sortTypes(list, false);
	}

	public static List<String> sortTypes(List<String> list,
			final boolean reverse) {
		Collections.sort(list, new Comparator<String>() {
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
