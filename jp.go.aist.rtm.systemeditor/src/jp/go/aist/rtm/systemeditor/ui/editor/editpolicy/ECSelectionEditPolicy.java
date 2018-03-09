package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ECSelectionEditPolicy extends SelectionEditPolicy {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ECSelectionEditPolicy.class);

	@Override
	protected void hideSelection() {
		LOGGER.info("hideSelection: host={} model={}", getHost(), getHost().getModel());

	}

	@Override
	protected void showSelection() {
		LOGGER.info("showSelection: host={} model={}", getHost(), getHost().getModel());

		ExecutionContext ec = (ExecutionContext) getHost().getModel();
		Component comp = (Component) ec.eContainer();

		if (comp != null) {
			comp.setPrimaryExecutionContext(ec);
		}
	}

}
