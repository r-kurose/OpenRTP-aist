package jp.go.aist.rtm.repositoryView.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.repositoryView.RepositoryViewPlugin;

/**
 * レポジトリビューの各種設定を管理するクラス
 *
 */
public class RepositoryViewPreferenceManager {
	private static RepositoryViewPreferenceManager __instance = new RepositoryViewPreferenceManager();

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static RepositoryViewPreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * 警告数のキー
	 */
	public static final String CAUTION_COUNT = RepositoryViewPreferenceManager.class.getName()
			+ "CAUTION_COUNT";
	/**
	 * プロパティファイル位置のキー
	 */
	public static final String PROPERTY_FILE_LOCATION = RepositoryViewPreferenceManager.class.getName()
			+ "PROPERTY_FILE_LOCATION";

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * デフォルトの警告数
	 */
	public static final int defaultCautionCount = 500;
	/**
	 * デフォルトのプロパティファイル位置
	 */
	public static final String defaultPropertyFileLocation = "";

	/**
	 * 警告数を設定する
	 * 
	 * @param key キー
	 * @param defaulCount 設定値
	 */
	public void setCaution_Count(int defaulCount) {
		int oldCautionCount = defaultCautionCount;

		RepositoryViewPlugin.getDefault().getPreferenceStore().setValue(CAUTION_COUNT, defaulCount);

		propertyChangeSupport.firePropertyChange(CAUTION_COUNT, oldCautionCount, defaulCount);
	}
	
	/**
	 * 警告数を取得する
	 * 
	 * @param key キー
	 * @return cautionCount 設定値
	 */
	public int getCaution_Count() {
		RepositoryViewPlugin.getDefault().getPreferenceStore().setDefault(CAUTION_COUNT, "");

		int resultTemp = RepositoryViewPlugin.getDefault().getPreferenceStore().getInt(CAUTION_COUNT);
		int result;
		if (resultTemp == 0 ) { // defaultvalue
			result = defaultCautionCount;
		} else {
			result = resultTemp;
		}
		return result;
	}

	/**
	 * Property File 位置を設定する
	 * 
	 * @param key キー
	 * @param defaulLocation ファイル位置
	 */
	public void setPropertyFile_Location(String defaulLocation) {
		String oldPropertyLocation = defaultPropertyFileLocation;

		RepositoryViewPlugin.getDefault().getPreferenceStore().setValue(PROPERTY_FILE_LOCATION, defaulLocation);

		propertyChangeSupport.firePropertyChange(PROPERTY_FILE_LOCATION, oldPropertyLocation, defaulLocation);
	}
	
	/**
	 * Property File 位置を取得する
	 * 
	 * @param key キー
	 * @return propertyLocation プロパティファイル位置
	 */
	public String getPropertyFile_Location() {
		RepositoryViewPlugin.getDefault().getPreferenceStore().setDefault(PROPERTY_FILE_LOCATION, "");

		String resultTemp = RepositoryViewPlugin.getDefault().getPreferenceStore().getString(PROPERTY_FILE_LOCATION);
		String result;
		if( resultTemp.equals("") ) { // defaultvalue
			result = defaultPropertyFileLocation;
		} else {
			result = resultTemp;
		}
		return result;
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
