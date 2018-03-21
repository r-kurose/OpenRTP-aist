package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.SelectionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ECSelectionEditPolicy extends SelectionEditPolicy {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECSelectionEditPolicy.class);

	private Request selectionRequest = null;

	@Override
	protected void hideSelection() {
		LOGGER.debug("hideSelection: host={} model={}", getHost(), getHost().getModel());
	}

	@Override
	protected void showSelection() {
		LOGGER.debug("showSelection: host={} model={}", getHost(), getHost().getModel());

		if (this.selectionRequest != null) {
			this.selectionRequest = null;
			return;
		}

		ExecutionContext ec = (ExecutionContext) getHost().getModel();
		Component comp = (Component) ec.eContainer();

		if (comp != null) {
			comp.setPrimaryExecutionContext(ec);
		}
	}

	@Override
	public EditPart getTargetEditPart(Request request) {
		// セレクションのリクエストを保持する
		// 直接選択時は SelectionRequest、範囲選択時は MARQUEE_REQUESTが指定が発行されるので、
		// これを保持し、直接選択時のみ showSelectionを実行する
		if (!(request instanceof SelectionRequest)) {
			this.selectionRequest = request;
		} else {
			this.selectionRequest = null;
		}
		return super.getTargetEditPart(request);
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		super.eraseTargetFeedback(request);
	}

}
