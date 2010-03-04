package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

public interface IRTCBMessageConstants {
	public static final String VALIDATE_ERROR_OUTPUTPROJECT = Messages.getString("IRTCBMessageConstants.VALIDATE_ERROR_OUTPUTPROJECT"); //$NON-NLS-1$
	public static final String VALIDATE_ERROR_COMPONENTNAME = Messages.getString("IRTCBMessageConstants.VALIDATE_ERROR_COMPONENTNAME"); //$NON-NLS-1$
	public static final String VALIDATE_ERROR_PORTSAMENAME = Messages.getString("IRTCBMessageConstants.VALIDATE_ERROR_PORTSAMENAME"); //$NON-NLS-1$
	public static final String VALIDATE_ERROR_INTERFACESAMENAME = Messages.getString("IRTCBMessageConstants.VALIDATE_ERROR_INTERFACESAMENAME"); //$NON-NLS-1$

	public static final String ERROR_IDL_DIRECTORY_NOT_FOUND = Messages.getString("IRTCBMessageConstants.ERROR_IDL_DIRECTORY_NOT_FOUND"); //$NON-NLS-1$
	public static final String ERROR_GENERATE_FAILED = Messages.getString("IRTCBMessageConstants.ERROR_GENERATE_FAILED"); //$NON-NLS-1$

	public static final String ERROR_PREPROCESSOR_P1 = Messages.getString("IRTCBMessageConstants.ERROR_PREPROCESSOR_P1"); //$NON-NLS-1$
	public static final String ERROR_PREPROCESSOR_P2 = Messages.getString("IRTCBMessageConstants.ERROR_PREPROCESSOR_P2"); //$NON-NLS-1$
	public static final String ERROR_PREPROCESSOR = StringUtil.connectMessageWithSepalator( new String[]{ERROR_PREPROCESSOR_P1, ERROR_PREPROCESSOR_P2});
	public static final String ERROR_IDLPARSE = " " + Messages.getString("IRTCBMessageConstants.ERROR_IDLPARSE"); //$NON-NLS-1$
	public static final String ERROR_IDLTYPEDUPLICAT = Messages.getString("IRTCBMessageConstants.ERROR_IDLTYPEDUPLICAT"); //$NON-NLS-1$

	public static final String ERROR_PROFILE_RESTORE_P1 = Messages.getString("IRTCBMessageConstants.ERROR_PROFILE_RESTORE_P1"); //$NON-NLS-1$
	public static final String ERROR_PROFILE_RESTORE_P2 = Messages.getString("IRTCBMessageConstants.ERROR_PROFILE_RESTORE_P2"); //$NON-NLS-1$
	public static final String ERROR_PROFILE_RESTORE = StringUtil.connectMessageWithSepalator( new String[]{ERROR_PROFILE_RESTORE_P1, ERROR_PROFILE_RESTORE_P2});


	public static final String CONFIRM_PROJECT_GENERATE_TITLE = Messages.getString("IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE_TITLE"); //$NON-NLS-1$
	public static final String CONFIRM_PROJECT_GENERATE_P1 = Messages.getString("IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE_P1"); //$NON-NLS-1$
	public static final String CONFIRM_PROJECT_GENERATE_P2 = Messages.getString("IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE_P2"); //$NON-NLS-1$
	public static final String CONFIRM_PROJECT_GENERATE = StringUtil.connectMessageWithSepalator( new String[]{CONFIRM_PROJECT_GENERATE_P1, CONFIRM_PROJECT_GENERATE_P2});

}
