package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import static jp.go.aist.rtm.systemeditor.ui.util.RTMixin.to_cid;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.EditableManhattanConnectorRouter;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ECConnectionEditPart extends AbstractConnectionEditPart {

	private static final Logger LOGGER = LoggerFactory.getLogger(ECConnectionEditPart.class);

	public ECConnectionEditPart(ActionRegistry actionRegistry) {
		super();
		LOGGER.trace("new: actionRegistry=<{}>", to_cid(actionRegistry));
	}

	@Override
	protected void createEditPolicies() {
		LOGGER.trace("createEditPolicies");
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	protected IFigure createFigure() {
		LOGGER.trace("createFigure");
		PolylineConnection result = new PolylineConnection();
		result.setLineWidth(1);
		// result.setLineDash(new float[] { 0.5f, 0.3f });
		result.setConnectionRouter(new EditableManhattanConnectorRouter());
		return result;
	}

	@Override
	public void activate() {
		LOGGER.trace("activate:");
		super.activate();
	}

	@Override
	public void deactivate() {
		LOGGER.trace("deactivate:");
		super.deactivate();
	}

	@Override
	public ECConnection getModel() {
		return (ECConnection) super.getModel();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		// refreshBendPoint();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		Object result = AdapterUtil.getAdapter(getModel(), key);
		if (result == null) {
			result = super.getAdapter(key);
		}
		return result;
	}

	/**
	 * 
	 */
	public static class ECConnection {

		private ECEditPart.OwnEC source;
		private ECEditPart.PartEC target;

		public ECConnection(ECEditPart.OwnEC source, ECEditPart.PartEC target) {
			this.source = source;
			this.target = target;
		}

		public ECEditPart.OwnEC getSource() {
			return this.source;
		}

		public void setSource(ECEditPart.OwnEC source) {
			this.source = source;
		}

		public ECEditPart.PartEC getTarget() {
			return this.target;
		}

		public void setTarget(ECEditPart.PartEC target) {
			this.target = target;
		}
	}

}
