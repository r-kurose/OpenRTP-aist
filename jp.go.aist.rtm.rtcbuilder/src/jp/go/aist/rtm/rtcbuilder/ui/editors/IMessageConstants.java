package jp.go.aist.rtm.rtcbuilder.ui.editors;

import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;

public interface IMessageConstants {
	public static final String HINT_TITLE = Messages.getString("IMessageConstants.HINT_TITLE"); //$NON-NLS-1$

	public static final String BASIC_SECTION = Messages.getString("IMessageConstants.BASIC_SECTION"); //$NON-NLS-1$
	public static final String BASIC_COMPONENT_TITLE = Messages.getString("IMessageConstants.BASIC_COMPONENT_TITLE"); //$NON-NLS-1$
	public static final String BASIC_PROJECT_TITLE = Messages.getString("IMessageConstants.BASIC_PROJECT_TITLE"); //$NON-NLS-1$
	public static final String BASIC_GENERATE_TITLE = Messages.getString("IMessageConstants.BASIC_GENERATE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_EXPORT_IMPORT_TITLE = Messages.getString("IMessageConstants.BASIC_EXPORT_IMPORT_TITLE"); //$NON-NLS-1$

	public static final String BASIC_COMPONENT_EXPL = Messages.getString("IMessageConstants.BASIC_COMPONENT_EXPL"); //$NON-NLS-1$
	public static final String BASIC_PROJECT_EXPL = Messages.getString("IMessageConstants.BASIC_PROJECT_EXPL"); //$NON-NLS-1$
	public static final String BASIC_GENERATE_EXPL = Messages.getString("IMessageConstants.BASIC_GENERATE_EXPL"); //$NON-NLS-1$
	public static final String BASIC_EXPORT_IMPORT_EXPL = Messages.getString("IMessageConstants.BASIC_EXPORT_IMPORT_EXPL"); //$NON-NLS-1$

	public static final String BASIC_HINT_MODULENAME_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_MODULENAME_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_MODULENAME_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_MODULENAME_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_MODULENAME_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_MODULENAME_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_MODULENAME_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_MODULENAME_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_MODULENAME_DESC_P4 = Messages.getString("IMessageConstants.BASIC_HINT_MODULENAME_DESC_P4"); //$NON-NLS-1$
	public static final String BASIC_HINT_MODULENAME_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_MODULENAME_DESC_P1, BASIC_HINT_MODULENAME_DESC_P2, BASIC_HINT_MODULENAME_DESC_P3, BASIC_HINT_MODULENAME_DESC_P4});
	public static final String BASIC_HINT_DESCRIPTION_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_DESCRIPTION_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_DESCRIPTION_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_DESCRIPTION_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_DESCRIPTION_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_DESCRIPTION_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_DESCRIPTION_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_DESCRIPTION_DESC_P1, BASIC_HINT_DESCRIPTION_DESC_P2});
	public static final String BASIC_HINT_VERSION_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_VERSION_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_VERSION_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_VERSION_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_VERSION_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_VERSION_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_VERSION_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_VERSION_DESC_P1, BASIC_HINT_VERSION_DESC_P2});
	public static final String BASIC_HINT_VENDOR_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_VENDOR_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_VENDOR_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_VENDOR_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_VENDOR_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_VENDOR_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_VENDOR_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_VENDOR_DESC_P1, BASIC_HINT_VENDOR_DESC_P2});
	public static final String BASIC_HINT_CATEGORY_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_CATEGORY_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_CATEGORY_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_CATEGORY_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_CATEGORY_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_CATEGORY_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_CATEGORY_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_CATEGORY_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_CATEGORY_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_CATEGORY_DESC_P1, BASIC_HINT_CATEGORY_DESC_P2, BASIC_HINT_CATEGORY_DESC_P3});
	public static final String BASIC_HINT_COMPTYPE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_COMPTYPE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPTYPE_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_COMPTYPE_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPTYPE_DESC_P2 = " " + Messages.getString("IMessageConstants.BASIC_HINT_COMPTYPE_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPTYPE_DESC_P3 = " " + Messages.getString("IMessageConstants.BASIC_HINT_COMPTYPE_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPTYPE_DESC_P4 = " " + Messages.getString("IMessageConstants.BASIC_HINT_COMPTYPE_DESC_P4"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPTYPE_DESC = StringUtil.connectMessageWithSepalator( new String[]{BASIC_HINT_COMPTYPE_DESC_P1, BASIC_HINT_COMPTYPE_DESC_P2, BASIC_HINT_COMPTYPE_DESC_P3, BASIC_HINT_COMPTYPE_DESC_P4});
	public static final String BASIC_HINT_ACTIVITYTYPE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_ACTIVITYTYPE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_ACTIVITYTYPE_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_ACTIVITYTYPE_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_ACTIVITYTYPE_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_ACTIVITYTYPE_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_ACTIVITYTYPE_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_ACTIVITYTYPE_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_ACTIVITYTYPE_DESC_P4 = Messages.getString("IMessageConstants.BASIC_HINT_ACTIVITYTYPE_DESC_P4"); //$NON-NLS-1$
	public static final String BASIC_HINT_ACTIVITYTYPE_DESC = StringUtil.connectMessageWithSepalator( new String[]{BASIC_HINT_ACTIVITYTYPE_DESC_P1, BASIC_HINT_ACTIVITYTYPE_DESC_P2, BASIC_HINT_ACTIVITYTYPE_DESC_P3, BASIC_HINT_ACTIVITYTYPE_DESC_P4});
	public static final String BASIC_HINT_COMPKIND_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_COMPKIND_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPKIND_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_COMPKIND_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPKIND_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_COMPKIND_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPKIND_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_COMPKIND_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPKIND_DESC_P4 = Messages.getString("IMessageConstants.BASIC_HINT_COMPKIND_DESC_P4"); //$NON-NLS-1$
	public static final String BASIC_HINT_COMPKIND_DESC = StringUtil.connectMessageWithSepalator( new String[]{BASIC_HINT_COMPKIND_DESC_P1, BASIC_HINT_COMPKIND_DESC_P2, BASIC_HINT_COMPKIND_DESC_P3, BASIC_HINT_COMPKIND_DESC_P4});
	public static final String BASIC_HINT_MAXINST_TITLE_P1 = Messages.getString("IMessageConstants.BASIC_HINT_MAXINST_TITLE_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_MAXINST_TITLE_P2 = Messages.getString("IMessageConstants.BASIC_HINT_MAXINST_TITLE_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_MAXINST_TITLE = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_MAXINST_TITLE_P1, BASIC_HINT_MAXINST_TITLE_P2});
	public static final String BASIC_HINT_MAXINST_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_MAXINST_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_MAXINST_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_MAXINST_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_MAXINST_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_MAXINST_DESC_P1, BASIC_HINT_MAXINST_DESC_P2});
	public static final String BASIC_HINT_EXECUTIONTYPE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_EXECUTIONTYPE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXECUTIONTYPE_DESC = Messages.getString("IMessageConstants.BASIC_HINT_EXECUTIONTYPE_DESC"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXECUTIONRATE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_EXECUTIONRATE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXECUTIONRATE_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_EXECUTIONRATE_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXECUTIONRATE_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_EXECUTIONRATE_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXECUTIONRATE_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_EXECUTIONRATE_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXECUTIONRATE_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_EXECUTIONRATE_DESC_P1, BASIC_HINT_EXECUTIONRATE_DESC_P2, BASIC_HINT_EXECUTIONRATE_DESC_P3});
	public static final String BASIC_HINT_ABSTRACT_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_ABSTRACT_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_ABSTRACT_DESC = Messages.getString("IMessageConstants.BASIC_HINT_ABSTRACT_DESC"); //$NON-NLS-1$
	public static final String BASIC_HINT_RTCTYPE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_RTCTYPE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_RTCTYPE_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_RTCTYPE_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_RTCTYPE_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_RTCTYPE_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_RTCTYPE_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_RTCTYPE_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_RTCTYPE_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_RTCTYPE_DESC_P1, BASIC_HINT_RTCTYPE_DESC_P2, BASIC_HINT_RTCTYPE_DESC_P3});
	public static final String BASIC_HINT_PROJECT_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_PROJECT_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_PROJECT_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_PROJECT_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_PROJECT_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_PROJECT_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_PROJECT_DESC_P3 = Messages.getString("IMessageConstants.BASIC_HINT_PROJECT_DESC_P3"); //$NON-NLS-1$
	public static final String BASIC_HINT_PROJECT_DESC_P4 = Messages.getString("IMessageConstants.BASIC_HINT_PROJECT_DESC_P4"); //$NON-NLS-1$
	public static final String BASIC_HINT_PROJECT_DESC_P5 = Messages.getString("IMessageConstants.BASIC_HINT_PROJECT_DESC_P5"); //$NON-NLS-1$
	public static final String BASIC_HINT_PROJECT_DESC = StringUtil.connectMessageWithSepalator( new String[]{BASIC_HINT_PROJECT_DESC_P1, BASIC_HINT_PROJECT_DESC_P2, BASIC_HINT_PROJECT_DESC_P3, BASIC_HINT_PROJECT_DESC_P4, BASIC_HINT_PROJECT_DESC_P5});
	public static final String BASIC_HINT_GENERATE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_GENERATE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_GENERATE_DESC = Messages.getString("IMessageConstants.BASIC_HINT_GENERATE_DESC"); //$NON-NLS-1$
	public static final String BASIC_HINT_PACKAGE_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_PACKAGE_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_PACKAGE_DESC_P1 = Messages.getString("IMessageConstants.BASIC_HINT_PACKAGE_DESC_P1"); //$NON-NLS-1$
	public static final String BASIC_HINT_PACKAGE_DESC_P2 = Messages.getString("IMessageConstants.BASIC_HINT_PACKAGE_DESC_P2"); //$NON-NLS-1$
	public static final String BASIC_HINT_PACKAGE_DESC = StringUtil.connectMessageFlat( new String[]{BASIC_HINT_PACKAGE_DESC_P1, BASIC_HINT_PACKAGE_DESC_P2});
	public static final String BASIC_HINT_IMPORT_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_IMPORT_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_IMPORT_DESC = Messages.getString("IMessageConstants.BASIC_HINT_IMPORT_DESC"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXPORT_TITLE = Messages.getString("IMessageConstants.BASIC_HINT_EXPORT_TITLE"); //$NON-NLS-1$
	public static final String BASIC_HINT_EXPORT_DESC = Messages.getString("IMessageConstants.BASIC_HINT_EXPORT_DESC"); //$NON-NLS-1$

	public static final String BASIC_LBL_MODULENAME = Messages.getString("IMessageConstants.BASIC_LBL_MODULENAME"); //$NON-NLS-1$
	public static final String BASIC_LBL_DESCRIPTION = Messages.getString("IMessageConstants.BASIC_LBL_DESCRIPTION"); //$NON-NLS-1$
	public static final String BASIC_LBL_VERSION = Messages.getString("IMessageConstants.BASIC_LBL_VERSION"); //$NON-NLS-1$
	public static final String BASIC_LBL_VENDOR = Messages.getString("IMessageConstants.BASIC_LBL_VENDOR"); //$NON-NLS-1$
	public static final String BASIC_LBL_CATEGORY = Messages.getString("IMessageConstants.BASIC_LBL_CATEGORY"); //$NON-NLS-1$
	public static final String BASIC_LBL_COMPONENT_TYPE = Messages.getString("IMessageConstants.BASIC_LBL_COMPONENT_TYPE"); //$NON-NLS-1$
	public static final String BASIC_LBL_ACTIVITY_TYPE = Messages.getString("IMessageConstants.BASIC_LBL_ACTIVITY_TYPE"); //$NON-NLS-1$
	public static final String BASIC_LBL_COMPONENT_KIND = Messages.getString("IMessageConstants.BASIC_LBL_COMPONENT_KIND"); //$NON-NLS-1$
	public static final String BASIC_LBL_MAX_INSTANCES = Messages.getString("IMessageConstants.BASIC_LBL_MAX_INSTANCES"); //$NON-NLS-1$
	public static final String BASIC_LBL_EXECUTION_TYPE = Messages.getString("IMessageConstants.BASIC_LBL_EXECUTION_TYPE"); //$NON-NLS-1$
	public static final String BASIC_LBL_EXECUTION_RATE = Messages.getString("IMessageConstants.BASIC_LBL_EXECUTION_RATE"); //$NON-NLS-1$
	public static final String BASIC_LBL_ABSTRACT = Messages.getString("IMessageConstants.BASIC_LBL_ABSTRACT"); //$NON-NLS-1$
	public static final String BASIC_LBL_RTCTYPE = Messages.getString("IMessageConstants.BASIC_LBL_RTCTYPE"); //$NON-NLS-1$

	public static final String BASIC_BTN_REF = Messages.getString("IMessageConstants.BASIC_BTN_REF"); //$NON-NLS-1$
	public static final String BASIC_BTN_GENERATE = Messages.getString("IMessageConstants.BASIC_BTN_GENERATE"); //$NON-NLS-1$
	public static final String BASIC_BTN_PACKAGING = Messages.getString("IMessageConstants.BASIC_BTN_PACKAGING"); //$NON-NLS-1$
	public static final String BASIC_BTN_IMPORT = Messages.getString("IMessageConstants.BASIC_BTN_IMPORT"); //$NON-NLS-1$
	public static final String BASIC_BTN_EXPORT = Messages.getString("IMessageConstants.BASIC_BTN_EXPORT"); //$NON-NLS-1$

	public static final String BASIC_IMPORT_ERROR = Messages.getString("IMessageConstants.BASIC_IMPORT_ERROR"); //$NON-NLS-1$
	public static final String BASIC_IMPORT_DONE = Messages.getString("IMessageConstants.BASIC_IMPORT_DONE"); //$NON-NLS-1$
	public static final String BASIC_EXPORT_ERROR = Messages.getString("IMessageConstants.BASIC_EXPORT_ERROR"); //$NON-NLS-1$
	public static final String BASIC_EXPORT_DONE = Messages.getString("IMessageConstants.BASIC_EXPORT_DONE"); //$NON-NLS-1$
	public static final String BASIC_PERSPECTIVE_TEXT = Messages.getString("IMessageConstants.BASIC_PERSPECTIVE_TEXT"); //$NON-NLS-1$
	public static final String BASIC_PERSPECTIVE_MSG1 = Messages.getString("IMessageConstants.BASIC_PERSPECTIVE_MSG1"); //$NON-NLS-1$
	public static final String BASIC_PERSPECTIVE_MSG2 = " " + Messages.getString("IMessageConstants.BASIC_PERSPECTIVE_MSG2"); //$NON-NLS-1$

	public static final String BASIC_VALIDATE_NAME1 = Messages.getString("IMessageConstants.BASIC_VALIDATE_NAME1"); //$NON-NLS-1$
	public static final String BASIC_VALIDATE_NAME2 = Messages.getString("IMessageConstants.BASIC_VALIDATE_NAME2"); //$NON-NLS-1$
	public static final String BASIC_VALIDATE_CATEGORY = Messages.getString("IMessageConstants.BASIC_VALIDATE_CATEGORY"); //$NON-NLS-1$
	public static final String BASIC_VALIDATE_MAXINST1 = Messages.getString("IMessageConstants.BASIC_VALIDATE_MAXINST1"); //$NON-NLS-1$
	public static final String BASIC_VALIDATE_MAXINST2 = Messages.getString("IMessageConstants.BASIC_VALIDATE_MAXINST2"); //$NON-NLS-1$
	public static final String BASIC_VALIDATE_ECRATE1 = Messages.getString("IMessageConstants.BASIC_VALIDATE_ECRATE1"); //$NON-NLS-1$
	public static final String BASIC_VALIDATE_ECRATE2 = Messages.getString("IMessageConstants.BASIC_VALIDATE_ECRATE2"); //$NON-NLS-1$


	public static final String DATAPORT_SECTION = Messages.getString("IMessageConstants.DATAPORT_SECTION"); //$NON-NLS-1$
	public static final String DATAPORT_INPORT_TITLE = Messages.getString("IMessageConstants.DATAPORT_INPORT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_OUTPORT_TITLE = Messages.getString("IMessageConstants.DATAPORT_OUTPORT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_INPORT_EXPL_P1 = Messages.getString("IMessageConstants.DATAPORT_INPORT_EXPL_P1"); //$NON-NLS-1$
	public static final String DATAPORT_INPORT_EXPL_P2 = Messages.getString("IMessageConstants.DATAPORT_INPORT_EXPL_P2"); //$NON-NLS-1$
	public static final String DATAPORT_INPORT_EXPL = StringUtil.connectMessageFlat( new String[]{DATAPORT_INPORT_EXPL_P1, DATAPORT_INPORT_EXPL_P2});
	public static final String DATAPORT_OUTPORT_EXPL_P1 = Messages.getString("IMessageConstants.DATAPORT_OUTPORT_EXPL_P1"); //$NON-NLS-1$
	public static final String DATAPORT_OUTPORT_EXPL_P2 = Messages.getString("IMessageConstants.DATAPORT_OUTPORT_EXPL_P2"); //$NON-NLS-1$
	public static final String DATAPORT_OUTPORT_EXPL = StringUtil.connectMessageFlat( new String[]{DATAPORT_OUTPORT_EXPL_P1, DATAPORT_OUTPORT_EXPL_P2});
	public static final String DATAPORT_DOCUMENT_EXPL_P1 = Messages.getString("IMessageConstants.DATAPORT_DOCUMENT_EXPL_P1"); //$NON-NLS-1$
	public static final String DATAPORT_DOCUMENT_EXPL_P2 = Messages.getString("IMessageConstants.DATAPORT_DOCUMENT_EXPL_P2"); //$NON-NLS-1$
	public static final String DATAPORT_DOCUMENT_EXPL = StringUtil.connectMessageFlat( new String[]{DATAPORT_DOCUMENT_EXPL_P1, DATAPORT_DOCUMENT_EXPL_P2});

	public static final String DATAPORT_HINT_DATAPORT_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_DATAPORT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATAPORT_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATAPORT_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATAPORT_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATAPORT_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATAPORT_DESC_P3 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATAPORT_DESC_P3"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATAPORT_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_DATAPORT_DESC_P1, DATAPORT_HINT_DATAPORT_DESC_P2, DATAPORT_HINT_DATAPORT_DESC_P3});
	public static final String DATAPORT_HINT_INPORT_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_INPORT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_INPORT_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_INPORT_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_INPORT_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_INPORT_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_INPORT_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_INPORT_DESC_P1, DATAPORT_HINT_INPORT_DESC_P2});
	public static final String DATAPORT_HINT_OUTPORT_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_OUTPORT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_OUTPORT_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_OUTPORT_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_OUTPORT_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_OUTPORT_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_OUTPORT_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_OUTPORT_DESC_P1, DATAPORT_HINT_OUTPORT_DESC_P2});
	public static final String DATAPORT_HINT_PORTNAME_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_PORTNAME_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_PORTNAME_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_PORTNAME_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_PORTNAME_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_PORTNAME_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_PORTNAME_DESC_P3 = Messages.getString("IMessageConstants.DATAPORT_HINT_PORTNAME_DESC_P3"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_PORTNAME_DESC_P4 = Messages.getString("IMessageConstants.DATAPORT_HINT_PORTNAME_DESC_P4"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_PORTNAME_DESC_P5 = Messages.getString("IMessageConstants.DATAPORT_HINT_PORTNAME_DESC_P5"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_PORTNAME_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_PORTNAME_DESC_P1, DATAPORT_HINT_PORTNAME_DESC_P2, DATAPORT_HINT_PORTNAME_DESC_P3, DATAPORT_HINT_PORTNAME_DESC_P4, DATAPORT_HINT_PORTNAME_DESC_P5});
	public static final String DATAPORT_HINT_DATATYPE_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_DATATYPE_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATATYPE_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATATYPE_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATATYPE_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATATYPE_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATATYPE_DESC_P3 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATATYPE_DESC_P3"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATATYPE_DESC_P4 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATATYPE_DESC_P4"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATATYPE_DESC_P5 = Messages.getString("IMessageConstants.DATAPORT_HINT_DATATYPE_DESC_P5"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DATATYPE_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_DATATYPE_DESC_P1, DATAPORT_HINT_DATATYPE_DESC_P2, DATAPORT_HINT_DATATYPE_DESC_P3, DATAPORT_HINT_DATATYPE_DESC_P4, DATAPORT_HINT_DATATYPE_DESC_P5});
	public static final String DATAPORT_HINT_VARNAME_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_VARNAME_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_VARNAME_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_VARNAME_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_VARNAME_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_VARNAME_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_VARNAME_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_VARNAME_DESC_P1, DATAPORT_HINT_VARNAME_DESC_P2});
	public static final String DATAPORT_HINT_POSITION_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_POSITION_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_POSITION_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_POSITION_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_POSITION_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_POSITION_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_POSITION_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_POSITION_DESC_P1, DATAPORT_HINT_POSITION_DESC_P2});
	public static final String DATAPORT_HINT_CONSTRAINT_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P3 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P3"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P4 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P4"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P5 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P5"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P6 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P6"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC_P7 = Messages.getString("IMessageConstants.DATAPORT_HINT_CONSTRAINT_DESC_P7"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_CONSTRAINT_DESC = StringUtil.connectMessageWithSepalator( new String[]{DATAPORT_HINT_CONSTRAINT_DESC_P1, DATAPORT_HINT_CONSTRAINT_DESC_P2, DATAPORT_HINT_CONSTRAINT_DESC_P3, DATAPORT_HINT_CONSTRAINT_DESC_P4, DATAPORT_HINT_CONSTRAINT_DESC_P5, DATAPORT_HINT_CONSTRAINT_DESC_P6, DATAPORT_HINT_CONSTRAINT_DESC_P7});
	public static final String DATAPORT_HINT_UNIT_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_UNIT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_UNIT_DESC = Messages.getString("IMessageConstants.DATAPORT_HINT_UNIT_DESC"); //$NON-NLS-1$

	public static final String DATAPORT_HINT_DOCUMENT_TITLE = Messages.getString("IMessageConstants.DATAPORT_HINT_DOCUMENT_TITLE"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DOCUMENT_DESC_P1 = Messages.getString("IMessageConstants.DATAPORT_HINT_DOCUMENT_DESC_P1"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DOCUMENT_DESC_P2 = Messages.getString("IMessageConstants.DATAPORT_HINT_DOCUMENT_DESC_P2"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DOCUMENT_DESC_P3 = Messages.getString("IMessageConstants.DATAPORT_HINT_DOCUMENT_DESC_P3"); //$NON-NLS-1$
	public static final String DATAPORT_HINT_DOCUMENT_DESC = StringUtil.connectMessageFlat( new String[]{DATAPORT_HINT_DOCUMENT_DESC_P1, DATAPORT_HINT_DOCUMENT_DESC_P2, DATAPORT_HINT_DOCUMENT_DESC_P3});

	public static final String DATAPORT_LBL_PORTNAME = Messages.getString("IMessageConstants.DATAPORT_LBL_PORTNAME"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_DESCRIPTION = Messages.getString("IMessageConstants.DATAPORT_LBL_DESCRIPTION"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_PORTTYPE = Messages.getString("IMessageConstants.DATAPORT_LBL_PORTTYPE"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_DATANUM = Messages.getString("IMessageConstants.DATAPORT_LBL_DATANUM"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_SEMANTICS = Messages.getString("IMessageConstants.DATAPORT_LBL_SEMANTICS"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_UNIT = Messages.getString("IMessageConstants.DATAPORT_LBL_UNIT"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_OCCUR = Messages.getString("IMessageConstants.DATAPORT_LBL_OCCUR"); //$NON-NLS-1$
	public static final String DATAPORT_LBL_OPERAT = Messages.getString("IMessageConstants.DATAPORT_LBL_OPERAT"); //$NON-NLS-1$

	public static final String DATAPORT_TBLLBL_PORTNAME = Messages.getString("IMessageConstants.DATAPORT_TBLLBL_PORTNAME"); //$NON-NLS-1$
	public static final String DATAPORT_TBLLBL_DATATYPE = Messages.getString("IMessageConstants.DATAPORT_TBLLBL_DATATYPE"); //$NON-NLS-1$
	public static final String DATAPORT_TBLLBL_VARNAME = Messages.getString("IMessageConstants.DATAPORT_TBLLBL_VARNAME"); //$NON-NLS-1$
	public static final String DATAPORT_TBLLBL_POSITION = Messages.getString("IMessageConstants.DATAPORT_TBLLBL_POSITION"); //$NON-NLS-1$
	public static final String DATAPORT_TBLLBL_CONSTRAINT = Messages.getString("IMessageConstants.DATAPORT_TBLLBL_CONSTRAINT"); //$NON-NLS-1$
	public static final String DATAPORT_TBLLBL_UNIT = Messages.getString("IMessageConstants.DATAPORT_TBLLBL_UNIT"); //$NON-NLS-1$

	public static final String DATAPORT_VALIDATE_PORTNAME1 = Messages.getString("IMessageConstants.DATAPORT_VALIDATE_PORTNAME1"); //$NON-NLS-1$
	public static final String DATAPORT_VALIDATE_PORTNAME2 = Messages.getString("IMessageConstants.DATAPORT_VALIDATE_PORTNAME2"); //$NON-NLS-1$
	public static final String DATAPORT_VALIDATE_PORTTYPE = Messages.getString("IMessageConstants.DATAPORT_VALIDATE_PORTTYPE"); //$NON-NLS-1$
	public static final String DATAPORT_VALIDATE_DUPLICATE = Messages.getString("IMessageConstants.DATAPORT_VALIDATE_DUPLICATE"); //$NON-NLS-1$


	public static final String SERVICEPORT_SECTION = Messages.getString("IMessageConstants.SERVICEPORT_SECTION"); //$NON-NLS-1$
	public static final String SERVICEPORT_MAIN_TITLE = Messages.getString("IMessageConstants.SERVICEPORT_MAIN_TITLE"); //$NON-NLS-1$
	public static final String SERVICEPORT_PORT_TITLE = Messages.getString("IMessageConstants.SERVICEPORT_PORT_TITLE"); //$NON-NLS-1$
	public static final String SERVICEPORT_IF_TITLE = Messages.getString("IMessageConstants.SERVICEPORT_IF_TITLE"); //$NON-NLS-1$
	public static final String SERVICEPORT_DOCUMENT_TITLE = Messages.getString("IMessageConstants.SERVICEPORT_DOCUMENT_TITLE"); //$NON-NLS-1$
	public static final String SERVICEPORT_PORT_EXPL = Messages.getString("IMessageConstants.SERVICEPORT_PORT_EXPL"); //$NON-NLS-1$
	public static final String SERVICEPORT_IF_EXPL = Messages.getString("IMessageConstants.SERVICEPORT_IF_EXPL"); //$NON-NLS-1$

	public static final String SERVICEPORT_LBL_PORTNAME = Messages.getString("IMessageConstants.SERVICEPORT_LBL_PORTNAME"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_POSITION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_POSITION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_DESCRIPTION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_DESCRIPTION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_ARGUMENT = Messages.getString("IMessageConstants.SERVICEPORT_LBL_ARGUMENT"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_RETURN = Messages.getString("IMessageConstants.SERVICEPORT_LBL_RETURN"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_EXCEPTION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_EXCEPTION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_PRE_CONDITION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_PRE_CONDITION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_POST_CONDITION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_POST_CONDITION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IFDESCRIPTION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IFDESCRIPTION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IFNAME = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IFNAME"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IFDIRECTION = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IFDIRECTION"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IFINSTNAME = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IFINSTNAME"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IFVARNAME = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IFVARNAME"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IDLFILE = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IDLFILE"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IFTYPE = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IFTYPE"); //$NON-NLS-1$
	public static final String SERVICEPORT_LBL_IDLPATH = Messages.getString("IMessageConstants.SERVICEPORT_LBL_IDLPATH"); //$NON-NLS-1$

	public static final String SERVICEPORT_BTN_ADDPORT = "  " + Messages.getString("IMessageConstants.SERVICEPORT_BTN_ADDPORT"); //$NON-NLS-1$
	public static final String SERVICEPORT_BTN_ADDIF = "  " + Messages.getString("IMessageConstants.SERVICEPORT_BTN_ADDIF"); //$NON-NLS-1$
	public static final String SERVICEPORT_BTN_DELETE = "   " + Messages.getString("IMessageConstants.SERVICEPORT_BTN_DELETE"); //$NON-NLS-1$

	public static final String SERVICEPORT_VALIDATE_PORTNAME1 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_PORTNAME1"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_PORTNAME2 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_PORTNAME2"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_DUPLICATE = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_DUPLICATE"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_IFNAME1 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_IFNAME1"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_IFNAME2 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_IFNAME2"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_INSTNAME1 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_INSTNAME1"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_INSTNAME2 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_INSTNAME2"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_IFTYPE1 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_IFTYPE1"); //$NON-NLS-1$
	public static final String SERVICEPORT_VALIDATE_IFTYPE2 = Messages.getString("IMessageConstants.SERVICEPORT_VALIDATE_IFTYPE2"); //$NON-NLS-1$


	public static final String CONFIGURATION_SECTION = Messages.getString("IMessageConstants.CONFIGURATION_SECTION"); //$NON-NLS-1$
	public static final String CONFIGURATION_SECTION_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_SECTION_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_SET_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_SET_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_PARAMETER_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_PARAMETER_TITLE"); //$NON-NLS-1$

	public static final String CONFIGURATION_SET_EXPL = Messages.getString("IMessageConstants.CONFIGURATION_SET_EXPL"); //$NON-NLS-1$
	public static final String CONFIGURATION_PARAMETER_EXPL = Messages.getString("IMessageConstants.CONFIGURATION_PARAMETER_EXPL"); //$NON-NLS-1$
	public static final String CONFIGURATION_DETAIL_EXPL = Messages.getString("IMessageConstants.CONFIGURATION_DETAIL_EXPL"); //$NON-NLS-1$
	public static final String CONFIGURATION_DOCUMENT_EXPL_P1 = Messages.getString("IMessageConstants.CONFIGURATION_DOCUMENT_EXPL_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_DOCUMENT_EXPL_P2 = Messages.getString("IMessageConstants.CONFIGURATION_DOCUMENT_EXPL_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_DOCUMENT_EXPL_P3 = Messages.getString("IMessageConstants.CONFIGURATION_DOCUMENT_EXPL_P3"); //$NON-NLS-1$
	public static final String CONFIGURATION_DOCUMENT_EXPL = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_DOCUMENT_EXPL_P1, CONFIGURATION_DOCUMENT_EXPL_P2, CONFIGURATION_DOCUMENT_EXPL_P3});

	public static final String CONFIGURATION_HINT_COFIGPARAM_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC_P3 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC_P3"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC_P4 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC_P4"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC_P5 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC_P5"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC_P6 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_COFIGPARAM_DESC_P6"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_COFIGPARAM_DESC = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_HINT_COFIGPARAM_DESC_P1, CONFIGURATION_HINT_COFIGPARAM_DESC_P2, CONFIGURATION_HINT_COFIGPARAM_DESC_P3, CONFIGURATION_HINT_COFIGPARAM_DESC_P4, CONFIGURATION_HINT_COFIGPARAM_DESC_P5, CONFIGURATION_HINT_COFIGPARAM_DESC_P6});
	public static final String CONFIGURATION_HINT_PARAMNAME_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMNAME_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMNAME_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMNAME_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMNAME_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMNAME_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMNAME_DESC_P3 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMNAME_DESC_P3"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMNAME_DESC = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_HINT_PARAMNAME_DESC_P1, CONFIGURATION_HINT_PARAMNAME_DESC_P2, CONFIGURATION_HINT_PARAMNAME_DESC_P3});
	public static final String CONFIGURATION_HINT_PARAMTYPE_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMTYPE_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMTYPE_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMTYPE_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMTYPE_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_PARAMTYPE_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_PARAMTYPE_DESC = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_HINT_PARAMTYPE_DESC_P1, CONFIGURATION_HINT_PARAMTYPE_DESC_P2});
	public static final String CONFIGURATION_HINT_VARNAME_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_VARNAME_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_VARNAME_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_VARNAME_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_VARNAME_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_VARNAME_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_VARNAME_DESC = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_HINT_VARNAME_DESC_P1, CONFIGURATION_HINT_VARNAME_DESC_P2});
	public static final String CONFIGURATION_HINT_DEFAULT_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_DEFAULT_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_DEFAULT_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_DEFAULT_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_DEFAULT_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_DEFAULT_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_DEFAULT_DESC_P3 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_DEFAULT_DESC_P3"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_DEFAULT_DESC = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_HINT_DEFAULT_DESC_P1, CONFIGURATION_HINT_DEFAULT_DESC_P2, CONFIGURATION_HINT_DEFAULT_DESC_P3});
	public static final String CONFIGURATION_HINT_CONSTRAINT_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P3 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P3"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P4 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P4"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P5 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P5"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P6 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P6"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC_P7 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_CONSTRAINT_DESC_P7"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_CONSTRAINT_DESC = StringUtil.connectMessageWithSepalator( new String[]{CONFIGURATION_HINT_CONSTRAINT_DESC_P1, CONFIGURATION_HINT_CONSTRAINT_DESC_P2, CONFIGURATION_HINT_CONSTRAINT_DESC_P3, CONFIGURATION_HINT_CONSTRAINT_DESC_P4, CONFIGURATION_HINT_CONSTRAINT_DESC_P5, CONFIGURATION_HINT_CONSTRAINT_DESC_P6, CONFIGURATION_HINT_CONSTRAINT_DESC_P7});
	public static final String CONFIGURATION_HINT_UNIT_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_UNIT_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_UNIT_DESC = Messages.getString("IMessageConstants.CONFIGURATION_HINT_UNIT_DESC"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_WIDGET_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_WIDGET_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_WIDGET_DESC = Messages.getString("IMessageConstants.CONFIGURATION_HINT_WIDGET_DESC"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_STEP_TITLE = Messages.getString("IMessageConstants.CONFIGURATION_HINT_STEP_TITLE"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_STEP_DESC_P1 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_STEP_DESC_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_STEP_DESC_P2 = Messages.getString("IMessageConstants.CONFIGURATION_HINT_STEP_DESC_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_HINT_STEP_DESC = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_HINT_STEP_DESC_P1, CONFIGURATION_HINT_STEP_DESC_P2});

	public static final String CONFIGURATION_LBL_VARNAME = Messages.getString("IMessageConstants.CONFIGURATION_LBL_VARNAME"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_PARAMNAME = Messages.getString("IMessageConstants.CONFIGURATION_LBL_PARAMNAME"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_DATANAME = Messages.getString("IMessageConstants.CONFIGURATION_LBL_DATANAME"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_DEFAULT = Messages.getString("IMessageConstants.CONFIGURATION_LBL_DEFAULT"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_DESCRIPTION = Messages.getString("IMessageConstants.CONFIGURATION_LBL_DESCRIPTION"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_UNIT = Messages.getString("IMessageConstants.CONFIGURATION_LBL_UNIT"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_RANGE = Messages.getString("IMessageConstants.CONFIGURATION_LBL_RANGE"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_CONSTRAINT = Messages.getString("IMessageConstants.CONFIGURATION_LBL_CONSTRAINT"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_WIDGET = Messages.getString("IMessageConstants.CONFIGURATION_LBL_WIDGET"); //$NON-NLS-1$
	public static final String CONFIGURATION_LBL_STEP = Messages.getString("IMessageConstants.CONFIGURATION_LBL_STEP"); //$NON-NLS-1$

	public static final String CONFIGURATION_TBLLBL_NAME = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_NAME"); //$NON-NLS-1$
	public static final String CONFIGURATION_TBLLBL_TYPE = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_TYPE"); //$NON-NLS-1$
	public static final String CONFIGURATION_TBLLBL_VARNAME = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_VARNAME"); //$NON-NLS-1$
	public static final String CONFIGURATION_TBLLBL_DEFAULTVAL = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_DEFAULTVAL"); //$NON-NLS-1$
	public static final String CONFIGURATION_TBLLBL_CONSTRAINT = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_CONSTRAINT"); //$NON-NLS-1$
	public static final String CONFIGURATION_TBLLBL_UNIT = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_UNIT"); //$NON-NLS-1$
	public static final String CONFIGURATION_TBLLBL_CONFIGURATION = Messages.getString("IMessageConstants.CONFIGURATION_TBLLBL_CONFIGURATION"); //$NON-NLS-1$

	public static final String CONFIGURATION_VALIDATE_NAME1 = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_NAME1"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_NAME2 = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_NAME2"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_TYPE = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_TYPE"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_DEFVALUE = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_DEFVALUE"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_VARIABLE_P1 = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_VARIABLE_P1"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_VARIABLE_P2 = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_VARIABLE_P2"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_VARIABLE = StringUtil.connectMessageFlat( new String[]{CONFIGURATION_VALIDATE_VARIABLE_P1, CONFIGURATION_VALIDATE_VARIABLE_P2});
	public static final String CONFIGURATION_VALIDATE_DUPLICATE = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_DUPLICATE"); //$NON-NLS-1$

	public static final String CONFIGURATION_VALIDATE_RADIO = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_RADIO"); //$NON-NLS-1$
	public static final String CONFIGURATION_VALIDATE_SPIN = Messages.getString("IMessageConstants.CONFIGURATION_VALIDATE_SPIN"); //$NON-NLS-1$


	public static final String DOCUMENT_HINT_COMPONENT_TITLE = Messages.getString("IMessageConstants.DOCUMENT_HINT_COMPONENT_TITLE"); //$NON-NLS-1$
	public static final String DOCUMENT_HINT_COMPONENT_DESC = Messages.getString("IMessageConstants.DOCUMENT_HINT_COMPONENT_DESC"); //$NON-NLS-1$
	public static final String DOCUMENT_HINT_ETC_TITLE = Messages.getString("IMessageConstants.DOCUMENT_HINT_ETC_TITLE"); //$NON-NLS-1$
	public static final String DOCUMENT_HINT_ETC_DESC = Messages.getString("IMessageConstants.DOCUMENT_HINT_ETC_DESC"); //$NON-NLS-1$


	public static final String LANGUAGE_SECTION = Messages.getString("IMessageConstants.LANGUAGE_SECTION"); //$NON-NLS-1$
	public static final String LANGUAGE_LANG_TITLE = Messages.getString("IMessageConstants.LANGUAGE_LANG_TITLE"); //$NON-NLS-1$
	public static final String LANGUAGE_ENV_TITLE = Messages.getString("IMessageConstants.LANGUAGE_ENV_TITLE"); //$NON-NLS-1$
	public static final String LANGUAGE_LANG_EXPL = Messages.getString("IMessageConstants.LANGUAGE_LANG_EXPL"); //$NON-NLS-1$
	public static final String LANGUAGE_ENV_EXPL = Messages.getString("IMessageConstants.LANGUAGE_ENV_EXPL"); //$NON-NLS-1$

	public static final String LANGUAGE_HINT_LANG_TITLE = Messages.getString("IMessageConstants.LANGUAGE_HINT_LANG_TITLE"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_LANG_DESC = Messages.getString("IMessageConstants.LANGUAGE_HINT_LANG_DESC"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_ENV_TITLE = Messages.getString("IMessageConstants.LANGUAGE_HINT_ENV_TITLE"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_ENV_DESC_P1 = Messages.getString("IMessageConstants.LANGUAGE_HINT_ENV_DESC_P1"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_ENV_DESC_P2 = Messages.getString("IMessageConstants.LANGUAGE_HINT_ENV_DESC_P2"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_ENV_DESC_P3 = Messages.getString("IMessageConstants.LANGUAGE_HINT_ENV_DESC_P3"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_ENV_DESC_P4 = Messages.getString("IMessageConstants.LANGUAGE_HINT_ENV_DESC_P4"); //$NON-NLS-1$
	public static final String LANGUAGE_HINT_ENV_DESC = StringUtil.connectMessageFlat( new String[]{LANGUAGE_HINT_ENV_DESC_P1, LANGUAGE_HINT_ENV_DESC_P2, LANGUAGE_HINT_ENV_DESC_P3, LANGUAGE_HINT_ENV_DESC_P4});

	public static final String LANGUAGE_LBL_DETAIL = Messages.getString("IMessageConstants.LANGUAGE_LBL_DETAIL"); //$NON-NLS-1$
	public static final String LANGUAGE_LBL_OSETC = Messages.getString("IMessageConstants.LANGUAGE_LBL_OSETC"); //$NON-NLS-1$
	public static final String LANGUAGE_LBL_CPUETC = Messages.getString("IMessageConstants.LANGUAGE_LBL_CPUETC"); //$NON-NLS-1$
	public static final String LANGUAGE_LBL_LIBRARY = Messages.getString("IMessageConstants.LANGUAGE_LBL_LIBRARY"); //$NON-NLS-1$

	public static final String LANGUAGE_SELECTION_CAUTION = Messages.getString("IMessageConstants.LANGUAGE_SELECTION_CAUTION"); //$NON-NLS-1$


	public static final String RTCXML_SECTION = Messages.getString("IMessageConstants.RTCXML_SECTION"); //$NON-NLS-1$
	public static final String RTCXML_CAUTION_P1 = Messages.getString("IMessageConstants.RTCXML_CAUTION_P1"); //$NON-NLS-1$
	public static final String RTCXML_CAUTION_P2 = Messages.getString("IMessageConstants.RTCXML_CAUTION_P2"); //$NON-NLS-1$
	public static final String RTCXML_CAUTION = StringUtil.connectMessageFlat( new String[]{RTCXML_CAUTION_P1, RTCXML_CAUTION_P2});

	public static final String RTCXML_MSG_VALIDATE_ERROR = Messages.getString("IMessageConstants.RTCXML_MSG_VALIDATE_ERROR"); //$NON-NLS-1$
	public static final String RTCXML_MSG_UPDATE_ERROR = Messages.getString("IMessageConstants.RTCXML_MSG_UPDATE_ERROR"); //$NON-NLS-1$
	public static final String RTCXML_MSG_UPDATE = Messages.getString("IMessageConstants.RTCXML_MSG_UPDATE"); //$NON-NLS-1$
	public static final String RTCXML_MSG_NOUPDATE = Messages.getString("IMessageConstants.RTCXML_MSG_NOUPDATE"); //$NON-NLS-1$
	public static final String RTCXML_MSG_DONEUPDATE = Messages.getString("IMessageConstants.RTCXML_MSG_DONEUPDATE"); //$NON-NLS-1$
	public static final String RTCXML_MSG_COMPARE = Messages.getString("IMessageConstants.RTCXML_MSG_COMPARE"); //$NON-NLS-1$


	public static final String DOCUMENT_SECTION = Messages.getString("IMessageConstants.DOCUMENT_SECTION"); //$NON-NLS-1$
	public static final String DOCUMENT_OVERVIEW_TITLE = Messages.getString("IMessageConstants.DOCUMENT_OVERVIEW_TITLE"); //$NON-NLS-1$
	public static final String DOCUMENT_ETC_TITLE = Messages.getString("IMessageConstants.DOCUMENT_ETC_TITLE"); //$NON-NLS-1$
	public static final String DOCUMENT_VERSIONUP_LOGS = Messages.getString("IMessageConstants.DOCUMENT_VERSIONUP_LOGS"); //$NON-NLS-1$

	public static final String DOCUMENT_LBL_DESCRIPTION = Messages.getString("IMessageConstants.DOCUMENT_LBL_DESCRIPTION"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_INOUT = Messages.getString("IMessageConstants.DOCUMENT_LBL_INOUT"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_ALGORITHM = Messages.getString("IMessageConstants.DOCUMENT_LBL_ALGORITHM"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_CREATOR = Messages.getString("IMessageConstants.DOCUMENT_LBL_CREATOR"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE = Messages.getString("IMessageConstants.DOCUMENT_LBL_LICENSE"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_REFERENCE = Messages.getString("IMessageConstants.DOCUMENT_LBL_REFERENCE"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_VERSIONUPLOG = Messages.getString("IMessageConstants.DOCUMENT_LBL_VERSIONUPLOG"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_VUHISTORY = Messages.getString("IMessageConstants.DOCUMENT_LBL_VUHISTORY"); //$NON-NLS-1$


	public static final String ACTIVITY_SECTION = Messages.getString("IMessageConstants.ACTIVITY_SECTION"); //$NON-NLS-1$
	public static final String ACTIVITY_ACTIVITY_TITLE = Messages.getString("IMessageConstants.ACTIVITY_ACTIVITY_TITLE"); //$NON-NLS-1$

	public static final String ACTIVITY_ACTIVITY_EXPL = Messages.getString("IMessageConstants.ACTIVITY_ACTIVITY_EXPL"); //$NON-NLS-1$
	public static final String ACTIVITY_DOCUMENT_EXPL_P1 = Messages.getString("IMessageConstants.ACTIVITY_DOCUMENT_EXPL_P1"); //$NON-NLS-1$
	public static final String ACTIVITY_DOCUMENT_EXPL_P2 = Messages.getString("IMessageConstants.ACTIVITY_DOCUMENT_EXPL_P2"); //$NON-NLS-1$
	public static final String ACTIVITY_DOCUMENT_EXPL = StringUtil.connectMessageFlat( new String[]{ACTIVITY_DOCUMENT_EXPL_P1, ACTIVITY_DOCUMENT_EXPL_P2});

	public static final String ACTIVITY_LBL_INIT_FINAL = Messages.getString("IMessageConstants.ACTIVITY_LBL_INIT_FINAL"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_START_END = Messages.getString("IMessageConstants.ACTIVITY_LBL_START_END"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_ALIVE = Messages.getString("IMessageConstants.ACTIVITY_LBL_ALIVE"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_DATAFLOW = Messages.getString("IMessageConstants.ACTIVITY_LBL_DATAFLOW"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_FSM = Messages.getString("IMessageConstants.ACTIVITY_LBL_FSM"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_MODE = Messages.getString("IMessageConstants.ACTIVITY_LBL_MODE"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_ACTIVITYNAME = Messages.getString("IMessageConstants.ACTIVITY_LBL_ACTIVITYNAME"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_DESCRIPTION = Messages.getString("IMessageConstants.ACTIVITY_LBL_DESCRIPTION"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_PRECONDITION = Messages.getString("IMessageConstants.ACTIVITY_LBL_PRECONDITION"); //$NON-NLS-1$
	public static final String ACTIVITY_LBL_POSTCONDITION = Messages.getString("IMessageConstants.ACTIVITY_LBL_POSTCONDITION"); //$NON-NLS-1$

	public static final String ACTIVITY_HINT_ONINITIALIZE_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONINITIALIZE_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONINITIALIZE_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONINITIALIZE_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONFINALIZE_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONFINALIZE_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONFINALIZE_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONFINALIZE_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONSTARTUP_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONSTARTUP_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONSTARTUP_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONSTARTUP_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONSHUTDOWN_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONSHUTDOWN_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONSHUTDOWN_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONSHUTDOWN_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONACTIVATED_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONACTIVATED_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONACTIVATED_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONACTIVATED_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONDEACTIVATED_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONDEACTIVATED_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONDEACTIVATED_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONDEACTIVATED_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONABORTING_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONABORTING_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONABORTING_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONABORTING_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONERROR_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONERROR_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONERROR_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONERROR_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONRESET_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONRESET_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONRESET_DESC_P1 = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONRESET_DESC_P1"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONRESET_DESC_P2 = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONRESET_DESC_P2"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONRESET_DESC = StringUtil.connectMessageFlat( new String[]{ACTIVITY_HINT_ONRESET_DESC_P1, ACTIVITY_HINT_ONRESET_DESC_P2});
	public static final String ACTIVITY_HINT_ONEXECUTE_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONEXECUTE_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONEXECUTE_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONEXECUTE_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONSTATEUPDATE_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONSTATEUPDATE_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONSTATEUPDATE_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONSTATEUPDATE_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONRATECHANGED_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONRATECHANGED_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONRATECHANGED_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONRATECHANGED_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONACTION_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONACTION_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONACTION_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONACTION_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONMODECHANGED_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONMODECHANGED_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_ONMODECHANGED_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_ONMODECHANGED_DESC"); //$NON-NLS-1$

	public static final String ACTIVITY_HINT_DESCRIPTION_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_DESCRIPTION_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_DESCRIPTION_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_DESCRIPTION_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_PRECONDITION_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_PRECONDITION_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_PRECONDITION_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_PRECONDITION_DESC"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_POSTCONDITION_TITLE = Messages.getString("IMessageConstants.ACTIVITY_HINT_POSTCONDITION_TITLE"); //$NON-NLS-1$
	public static final String ACTIVITY_HINT_POSTCONDITION_DESC = Messages.getString("IMessageConstants.ACTIVITY_HINT_POSTCONDITION_DESC"); //$NON-NLS-1$


	public static final String PREF_IDL_SELECTION = Messages.getString("IMessageConstants.PREF_IDL_SELECTION"); //$NON-NLS-1$
	public static final String PREF_IDLPARSE_NOFILE = Messages.getString("IMessageConstants.PREF_IDLPARSE_NOFILE"); //$NON-NLS-1$
	public static final String PREF_IDLPARSE_ERROR_P1 = Messages.getString("IMessageConstants.PREF_IDLPARSE_ERROR_P1"); //$NON-NLS-1$
	public static final String PREF_IDLPARSE_ERROR_P2 = Messages.getString("IMessageConstants.PREF_IDLPARSE_ERROR_P2"); //$NON-NLS-1$
	public static final String PREF_IDLPARSE_ERROR = StringUtil.connectMessageWithSepalator( new String[]{PREF_IDLPARSE_ERROR_P1, PREF_IDLPARSE_ERROR_P2});
	public static final String FILETYPE_XML = Messages.getString("IMessageConstants.FILETYPE_XML"); //$NON-NLS-1$
	public static final String PROFILE_COMPARE_MESSAGE_P1 = Messages.getString("IMessageConstants.PROFILE_COMPARE_MESSAGE_P1"); //$NON-NLS-1$
	public static final String PROFILE_COMPARE_MESSAGE_P2 = Messages.getString("IMessageConstants.PROFILE_COMPARE_MESSAGE_P2"); //$NON-NLS-1$
	public static final String PROFILE_COMPARE_MESSAGE = StringUtil.connectMessageWithSepalator( new String[]{PROFILE_COMPARE_MESSAGE_P1, PROFILE_COMPARE_MESSAGE_P2});

	public static final String CREATE_FILE_ERROR = Messages.getString("IMessageConstants.CREATE_FILE_ERROR"); //$NON-NLS-1$


	public static final String FILETYPE_YAML = Messages.getString("IMessageConstants.FILETYPE_YAML"); //$NON-NLS-1$


	public static final String COMPARE_TITLE = Messages.getString("IMessageConstants.COMPARE_TITLE"); //$NON-NLS-1$


	public static final String COMMON_LABEL_UPDATE = Messages.getString("IMessageConstants.COMMON_LABEL_UPDATE"); //$NON-NLS-1$


	public static final String TITLE_FONT = Messages.getString("IMessageConstants.TITLE_FONT"); //$NON-NLS-1$

	public static final String PROFILE_VALIDATE_ERROR_MESSAGE = Messages.getString("IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE"); //$NON-NLS-1$

	public static final String SELECT_DIRECTORY = Messages.getString("IMessageConstants.SELECT_DIRECTORY"); //$NON-NLS-1$
}
