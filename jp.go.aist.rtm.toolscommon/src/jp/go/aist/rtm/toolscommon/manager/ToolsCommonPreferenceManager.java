package jp.go.aist.rtm.toolscommon.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.preference.IPreferenceStore;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;

/**
 * è¨­å®šã‚’ç®¡ç†ã™ã‚‹ãƒãƒãƒ¼ã‚¸ãƒ£
 * <p>
 * è¨­å®šæƒ…å ±ã«ã‚¢ã‚¯ã‚»ã‚¹ã™ã‚‹ã«ã¯ã“ã®ã‚¤ãƒ³ã‚¹ã‚¿ãƒ³ã‚¹ã‚’ä½¿ç”¨ã™ã‚‹
 */
public class ToolsCommonPreferenceManager {
	private static ToolsCommonPreferenceManager __instance = new ToolsCommonPreferenceManager();

	/**
	 * ã‚¿ã‚¤ãƒ ã‚¢ã‚¦ãƒˆåˆ¤å®šæ™‚é–“
	 */
	public static final String DEFAULT_TIMEOUT_PERIOD = ToolsCommonPreferenceManager.class
			.getName()
			+ "DEFAULT_TIMEOUT_PERIOD";

	/** ó‘Ô’Ê’mƒIƒuƒU[ƒo‚Ì—LŒø/–³Œøİ’è */
	public static final String KEY_STATUS_OBSERVER_HB_ENABLE = ToolsCommonPreferenceManager.class
			.getName()
			+ ".STATUS_OBSERVER_HB_ENABLE";

	/** ó‘Ô’Ê’mƒIƒuƒU[ƒo‚Ì H.BóMŠÔŠu [sec] */
	public static final String KEY_STATUS_OBSERVER_HB_INTERVAL = ToolsCommonPreferenceManager.class
			.getName()
			+ ".STATUS_OBSERVER_HB_INTERVAL";
	/** ó‘Ô’Ê’mƒIƒuƒU[ƒo‚Ìƒ^ƒCƒ€ƒAƒEƒg”»’è‰ñ” */
	public static final String KEY_STATUS_OBSERVER_HB_TRYCOUNT = ToolsCommonPreferenceManager.class
			.getName()
			+ ".STATUS_OBSERVER_HB_TRYCOUNT";

	/**
	 * ã‚³ãƒ³ã‚¹ãƒˆãƒ©ã‚¯ã‚¿
	 * 
	 * @return ã‚·ãƒ³ã‚°ãƒ«ãƒˆãƒ³
	 */
	public static ToolsCommonPreferenceManager getInstance() {
		return __instance;
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	IPreferenceStore store;

	public ToolsCommonPreferenceManager() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.store = ToolsCommonPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * ãƒ‡ãƒ•ã‚©ãƒ«ãƒˆã‚¿ã‚¤ãƒ ã‚¢ã‚¦ãƒˆåˆ¤å®šæ™‚é–“
	 */
	public static final int defaultTimeoutPeriod = 1000;

	/** ó‘Ô’Ê’mƒIƒuƒU[ƒo (ƒfƒtƒHƒ‹ƒg —LŒø) */
	public static final Boolean DEFAULT_STATUS_OBSERVER_HB_ENABLE = true;

	/** ó‘Ô’Ê’mƒIƒuƒU[ƒo‚Ì H.BóMŠÔŠu (ƒfƒtƒHƒ‹ƒg 1.0[sec]) */
	public static final Double DEFAULT_STATUS_OBSERVER_HB_INTERVAL = 1.0;

	/** ó‘Ô’Ê’mƒIƒuƒU[ƒo‚Ìƒ^ƒCƒ€ƒAƒEƒg”»’è‰ñ” (ƒfƒtƒHƒ‹ƒg 3‰ñ) */
	public static final Integer DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT = 3;

	/**
	 * ãƒ‡ãƒ•ã‚©ãƒ«ãƒˆã‚¿ã‚¤ãƒ ã‚¢ã‚¦ãƒˆåˆ¤å®šæ™‚é–“ã‚’å–å¾—ã™ã‚‹
	 * 
	 * @param key
	 *            ã‚­ãƒ¼
	 * @return ãƒ‡ãƒ•ã‚©ãƒ«ãƒˆãƒãƒ¼ãƒˆ
	 */
	public int getDefaultTimeout(String key) {
		// ã‚³ãƒ³ã‚½ãƒ¼ãƒ«ã‹ã‚‰èµ·å‹•ã•ã‚ŒãŸå ´åˆã«ã¯ã€pluginã¯å­˜åœ¨ã—ãªã„ç‚ºã€nullã¨ãªã‚‹ã€‚
		if (ToolsCommonPlugin.getDefault() == null){
			return defaultTimeoutPeriod;
		}
		ToolsCommonPlugin.getDefault().getPreferenceStore().setDefault(key, -1);
		int result = ToolsCommonPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == -1) { // defaultvalue
			result = defaultTimeoutPeriod;
		}
		return result;
	}

	/**
	 * ãƒ‡ãƒ•ã‚©ãƒ«ãƒˆã‚¿ã‚¤ãƒ ã‚¢ã‚¦ãƒˆåˆ¤å®šæ™‚é–“ã‚’è¨­å®šã™ã‚‹
	 * 
	 * @param key
	 *            ã‚­ãƒ¼
	 * @param interval
	 *            é–“éš”
	 */
	public void setDefaultTimeout(String key, int defaultTimeout) {
		int oldDefaultTimeout = getDefaultTimeout(key);
		ToolsCommonPlugin.getDefault().getPreferenceStore().setValue(key,
				defaultTimeout);
		propertyChangeSupport.firePropertyChange(key, oldDefaultTimeout,
				defaultTimeout);
	}

	// ó‘Ô’Ê’mƒIƒuƒU[ƒo—LŒø/–³Œø (STATUS_OBSERVER_HB_ENABLE)

	/** STATUS_OBSERVER_HB_ENABLE ‚Ìæ“¾ */
	public final Boolean isSTATUS_OBSERVER_HB_ENABLE() {
		String key = KEY_STATUS_OBSERVER_HB_ENABLE;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_ENABLE);
		return store.getBoolean(key);
	}

	/** STATUS_OBSERVER_HB_ENABLE ‚Ìİ’è */
	public void setSTATUS_OBSERVER_HB_ENABLE(Boolean value) {
		String key = KEY_STATUS_OBSERVER_HB_ENABLE;
		Boolean oldValue = isSTATUS_OBSERVER_HB_ENABLE();
		store.setValue(key, (value == null) ? false : value);
		Boolean newValue = isSTATUS_OBSERVER_HB_ENABLE();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_ENABLE ‚Ì•œŒ³ */
	public void resetSTATUS_OBSERVER_HB_ENABLE() {
		setSTATUS_OBSERVER_HB_ENABLE(DEFAULT_STATUS_OBSERVER_HB_ENABLE);
	}

	// ó‘Ô’Ê’mƒIƒuƒU[ƒoóMŠÔŠu (STATUS_OBSERVER_HB_INTERVAL)

	/** STATUS_OBSERVER_HB_INTERVAL ‚Ìæ“¾ */
	public final Double getSTATUS_OBSERVER_HB_INTERVAL() {
		String key = KEY_STATUS_OBSERVER_HB_INTERVAL;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_INTERVAL);
		return store.getDouble(key);
	}

	/** STATUS_OBSERVER_HB_INTERVAL ‚Ìİ’è */
	public void setSTATUS_OBSERVER_HB_INTERVAL(Double value) {
		String key = KEY_STATUS_OBSERVER_HB_INTERVAL;
		Double oldValue = getSTATUS_OBSERVER_HB_INTERVAL();
		store.setValue(key,
				(value == null) ? DEFAULT_STATUS_OBSERVER_HB_INTERVAL : value);
		Double newValue = getSTATUS_OBSERVER_HB_INTERVAL();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_INTERVAL ‚Ì•œŒ³ */
	public void resetSTATUS_OBSERVER_HB_INTERVAL() {
		setSTATUS_OBSERVER_HB_INTERVAL(DEFAULT_STATUS_OBSERVER_HB_INTERVAL);
	}

	// ó‘Ô’Ê’mƒIƒuƒU[ƒoóM‰ñ” (STATUS_OBSERVER_HB_TRYCOUNT)

	/** STATUS_OBSERVER_HB_TRYCOUNT ‚Ìæ“¾ */
	public final Integer getSTATUS_OBSERVER_HB_TRYCOUNT() {
		String key = KEY_STATUS_OBSERVER_HB_TRYCOUNT;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT);
		return store.getInt(key);
	}

	/** STATUS_OBSERVER_HB_TRYCOUNT ‚Ìİ’è */
	public void setSTATUS_OBSERVER_HB_TRYCOUNT(Integer value) {
		String key = KEY_STATUS_OBSERVER_HB_TRYCOUNT;
		Integer oldValue = getSTATUS_OBSERVER_HB_TRYCOUNT();
		store.setValue(key,
				(value == null) ? DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT : value);
		Integer newValue = getSTATUS_OBSERVER_HB_TRYCOUNT();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_TRYCOUNT ‚Ì•œŒ³ */
	public void resetSTATUS_OBSERVER_HB_TRYCOUNT() {
		setSTATUS_OBSERVER_HB_TRYCOUNT(DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT);
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
