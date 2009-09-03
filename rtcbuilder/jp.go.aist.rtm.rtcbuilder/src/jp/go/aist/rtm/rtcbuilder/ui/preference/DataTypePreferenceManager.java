package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class DataTypePreferenceManager {
	private static DataTypePreferenceManager __instance = new DataTypePreferenceManager();

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static DataTypePreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * IDL Fileのキー
	 */
	public static final String IDLFILE_DIRECTORIES = DataTypePreferenceManager.class.getName()
			+ "IDLFILE_DIRECTORIES";
	
	public static List<String> defaultIdlFileDirectories = new ArrayList<String>();
	
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * DataType 用IDLファイル格納ディレクトリの デフォルト値を取得する
	 * 
	 * @param key キー
	 * @return IDLFile Directories デフォルト値
	 */
	public List<String> getIdlFileDirectories() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(IDLFILE_DIRECTORIES, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(IDLFILE_DIRECTORIES);
		List<String> result = Arrays.asList(resultTemp.split(","));
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultIdlFileDirectories;
		}
		return result;
	}
	/**
	 * DataType 用IDLファイル格納ディレクトリの デフォルト値を設定する
	 * 
	 * @param key キー
	 * @param IDLFile Directories デフォルト値
	 */
	public void setIdlFileDirectories(List<String> defaultFileName) {
		String oldIdlFileDirectories = Array2String(getIdlFileDirectories());
		String newIdlFileDirectories = Array2String(defaultFileName);

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(IDLFILE_DIRECTORIES, newIdlFileDirectories);

		propertyChangeSupport.firePropertyChange(IDLFILE_DIRECTORIES, oldIdlFileDirectories, newIdlFileDirectories);
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
	private String Array2String(List<String> source) {
		StringBuffer buffer = new StringBuffer();
		String result = null;
		
		for( Iterator<String> iter = source.iterator(); iter.hasNext(); ) {
			String idlFile = iter.next();
			buffer.append(idlFile + ",");
		}
		result = buffer.toString();
		if( result!=null && result.length()>0 ) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
}
