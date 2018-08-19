package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_EXECUTION_CONTEXT_RUNNING;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_EXECUTION_CONTEXT_STOPPED;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_STATE_ACTIVE;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_STATE_CREATED;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_STATE_ERROR;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_STATE_INACTIVE;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_STATE_UNCERTAIN;
import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * システムの色設定から色を取得します。
 */
public class ColorHelper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ColorHelper.class);

	/**
	 * システム設定から ECの枠色を取得します。
	 * 
	 * @param ec
	 * @return
	 */
	public static Color getECBorderColor(ExecutionContext ec) {
		SystemEditorPreferenceManager manager = SystemEditorPreferenceManager
				.getInstance();
		//
		Color ret = manager.getColor(COLOR_RTC_STATE_UNKNOWN);
		if (ec instanceof CorbaExecutionContext) {
			CorbaExecutionContext cec = (CorbaExecutionContext) ec;
			if (cec.getStateL() == ExecutionContext.STATE_RUNNING) {
				ret = manager.getColor(COLOR_RTC_EXECUTION_CONTEXT_RUNNING);
			} else if (cec.getStateL() == ExecutionContext.STATE_STOPPED) {
				ret = manager.getColor(COLOR_RTC_EXECUTION_CONTEXT_STOPPED);
			} else if (cec.getStateL() == ExecutionContext.STATE_UNKNOWN) {
				ret = manager.getColor(COLOR_RTC_STATE_UNKNOWN);
			}
		}
		return ret;
	}

	/**
	 * システム設定から ECの本体色を取得します。
	 * 
	 * @param ec
	 * @return
	 */
	public static Color getECBodyColor(ExecutionContext ec) {
		SystemEditorPreferenceManager manager = SystemEditorPreferenceManager
				.getInstance();
		//
		Color ret = manager.getColor(COLOR_RTC_STATE_UNKNOWN);
		if (ec instanceof CorbaExecutionContext) {
			CorbaExecutionContext cec = (CorbaExecutionContext) ec;
//			LOGGER.info("getECBodyColor: ec={}", ec);
//			LOGGER.info("getECBodyColor: container={}", ec.eContainer());
//			LOGGER.info("getECBodyColor: owner={}", ec.getOwner());
//			LOGGER.info("getECBodyColor: participants={}", ec.getParticipants());
			CorbaComponent comp = (CorbaComponent) ec.eContainer();
			if (comp == null) {
				comp = (CorbaComponent) ec.getOwner();
			}
			if (comp != null) {
				int status = cec.getComponentState(comp);
				if (status == ExecutionContext.RTC_ACTIVE) {
					ret = manager.getColor(COLOR_RTC_STATE_ACTIVE);
				} else if (status == ExecutionContext.RTC_CREATED) {
					ret = manager.getColor(COLOR_RTC_STATE_CREATED);
				} else if (status == ExecutionContext.RTC_ERROR) {
					ret = manager.getColor(COLOR_RTC_STATE_ERROR);
				} else if (status == ExecutionContext.RTC_INACTIVE) {
					ret = manager.getColor(COLOR_RTC_STATE_INACTIVE);
				} else if (status == ExecutionContext.RTC_UNKNOWN) {
					ret = manager.getColor(COLOR_RTC_STATE_UNKNOWN);
				} else if (status == ExecutionContext.RTC_UNCERTAIN) {
					ret = manager.getColor(COLOR_RTC_STATE_UNCERTAIN);
				}
			}
		}
		return ret;
	}

}
