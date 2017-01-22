package jp.go.aist.rtm.nameserviceview.nl;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 多言語化対応メッセージクラス
 * @author nagata
 *
 */
public class Messages {
	private static final String BUNDLE_NAME = "jp.go.aist.rtm.nameserviceview.nl.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
