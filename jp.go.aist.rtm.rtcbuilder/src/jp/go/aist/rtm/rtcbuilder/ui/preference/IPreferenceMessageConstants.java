package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

public interface IPreferenceMessageConstants {
	public static final String LBL_IDL_SEARCH_DIRS = Messages.getString("IPreferenceMessageConstants.DATA_TYPE_LBL_DIRS"); //$NON-NLS-1$
	public static final String LBL_HOME_DIR = Messages.getString("IPreferenceMessageConstants.HOME_DIR"); //$NON-NLS-1$
	public static final String DATA_TYPE_BTN_ADD = Messages.getString("IPreferenceMessageConstants.DATA_TYPE_BTN_ADD"); //$NON-NLS-1$
	public static final String DATA_TYPE_BTN_DELETE = Messages.getString("IPreferenceMessageConstants.DATA_TYPE_BTN_DELETE"); //$NON-NLS-1$

	public static final String CODE_GEN_TITLE_BASIC = Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_BASIC"); //$NON-NLS-1$
	public static final String CODE_GEN_TITLE_DOCUMENT = Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_DOCUMENT"); //$NON-NLS-1$
	public static final String CODE_GEN_TITLE_CONFIG = Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_CONFIG"); //$NON-NLS-1$
	public static final String CODE_GEN_TITLE_PRESUFFIX = Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_PRESUFFIX"); //$NON-NLS-1$
	public static final String CODE_GEN_TITLE_BACKUP = Messages.getString("IPreferenceMessageConstants.CODE_GEN_TITLE_BACKUP"); //$NON-NLS-1$

	public static final String CONFIG_CLMN_CONFIGURATION = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_CONFIGURATION"); //$NON-NLS-1$
	public static final String CONFIG_CLMN_DEFAUT_VALUE = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_DEFAUT_VALUE"); //$NON-NLS-1$
	public static final String CONFIG_BTN_ADD = Messages.getString("IPreferenceMessageConstants.CONFIG_BTN_ADD"); //$NON-NLS-1$
	public static final String CONFIG_BTN_DELETE = Messages.getString("IPreferenceMessageConstants.CONFIG_BTN_DELETE"); //$NON-NLS-1$$
	public static final String CONFIG_CLMN_COMMON_PREFIX = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_COMMON_PREFIX"); //$NON-NLS-1$
	public static final String CONFIG_CLMN_COMMON_SUFFIX = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_COMMON_SUFFIX"); //$NON-NLS-1$
	public static final String CONFIG_CLMN_DATAPORT_PREFIX = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_DATAPORT_PREFIX"); //$NON-NLS-1$
	public static final String CONFIG_CLMN_DATAPORT_SUFFIX = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_DATAPORT_SUFFIX"); //$NON-NLS-1$
	public static final String CONFIG_CLMN_CONFIG_PREFIX = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_CONFIG_PREFIX"); //$NON-NLS-1$
	public static final String CONFIG_CLMN_CONFIG_SUFFIX = Messages.getString("IPreferenceMessageConstants.CONFIG_CLMN_CONFIG_SUFFIX"); //$NON-NLS-1$

	public static final String DOCUMENT_LBL_AUTHOR = Messages.getString("IPreferenceMessageConstants.DOCUMENT_LBL_AUTHOR"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE_P1 = Messages.getString("IPreferenceMessageConstants.DOCUMENT_LBL_LICENSE_P1"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE_P2 = Messages.getString("IPreferenceMessageConstants.DOCUMENT_LBL_LICENSE_P2"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE = StringUtil.connectMessageWithSepalator( new String[]{DOCUMENT_LBL_LICENSE_P1, DOCUMENT_LBL_LICENSE_P2});

	public static final String PORT_TITLE_DATA_PORT = Messages.getString("IPreferenceMessageConstants.PORT_TITLE_DATA_PORT"); //$NON-NLS-1$
	public static final String PORT_TITLE_SERVICE_PORT = Messages.getString("IPreferenceMessageConstants.PORT_TITLE_SERVICE_PORT"); //$NON-NLS-1$
	public static final String PORT_TITLE_SERVICE_INTERFACE = Messages.getString("IPreferenceMessageConstants.PORT_TITLE_SERVICE_INTERFACE"); //$NON-NLS-1$
	public static final String PORT_TITLE_EVENT_PORT = Messages.getString("IPreferenceMessageConstants.PORT_TITLE_EVENT_PORT"); //$NON-NLS-1$
	//
	public static final String PORT_LBL_PREFIX = Messages.getString("IPreferenceMessageConstants.PREFIX"); //$NON-NLS-1$
	public static final String PORT_LBL_SUFFIX = Messages.getString("IPreferenceMessageConstants.SUFFIX"); //$NON-NLS-1$



}
