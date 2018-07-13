package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ExportedInPortFigure;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.InPortFigure;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * InPortのEditPartクラス
 */
public class InPortEditPart extends PortEditPart {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(InPortEditPart.class);

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public InPortEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	public InPort getModel() {
		return (InPort) super.getModel();
	}

	@Override
	public void notifyChanged(Notification notification) {
		LOGGER.trace("notifyChanged: this=<{}> notification=<{}>", getModel()
				.getNameL(), notification);
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (isActive()) {
					refresh();
					refreshVisuals();
					refreshTargetConnections();
				}
			}
		});
	}

	@Override
	protected IFigure createFigure() {
		super.createFigure();

		IFigure result = null;
		if (isExported()) {
			result = new ExportedInPortFigure(getModel()) {
				@Override
				public void setBounds(Rectangle rect) {
					super.setBounds(rect);
					setLabelBounds(getBaseBounds(), rect, getDirection());
				}
			};
		} else {
			result = new InPortFigure(getModel()) {
				@Override
				public void setBounds(Rectangle rect) {
					super.setBounds(rect);
					setLabelBounds(getBaseBounds(), rect, getDirection());
				}
			};
		}
		result.setLocation(new Point(0, 0));

		AutoConnectorCreationTool.fetchTool(this, result);

		return result;
	}

	@Override
	protected void refreshVisuals() {
		Color color = SystemEditorPreferenceManager.getInstance()
				.getColor(SystemEditorPreferenceManager.COLOR_DATAPORT_NO_CONNECT);
		if (isConnected()) {
			color = SystemEditorPreferenceManager.getInstance()
					.getColor(SystemEditorPreferenceManager.COLOR_DATAPORT_CONNECTED);
		}

		getFigure().setBackgroundColor(color);

		getFigure().setToolTip(getDataPortToolTip(getModel()));

		if (getFigure().getParent() != null)
			((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), getFigure().getBounds());
	}

	/**
	 * データポートのツールチップを取得する
	 * 
	 * @param profile
	 *            モデル
	 * @return ツールチップ
	 */
	public static Panel getDataPortToolTip(Port port) {
		Panel tooltip = new Panel();
		tooltip.setLayoutManager(new StackLayout());

		String labelString = "";
		try {
			labelString = labelString + (port.getNameL() == null ? "<unknown>" : port.getNameL()) + "\r\n";
			labelString = labelString + (port.getDataTypes() == null ? "<unknown>" : port.getDataTypes().toString())
					+ "\r\n";
			labelString = labelString + (port.getInterfaceTypes().size() == 0 ? "<unknown>" : port.getInterfaceTypes())
					+ "\r\n";
			labelString = labelString + (port.getDataflowTypes().size() == 0 ? "<unknown>" : port.getDataflowTypes())
					+ "\r\n";
			labelString = labelString
					+ (port.getSubscriptionTypes().size() == 0 ? "<unknown>" : port.getSubscriptionTypes()) + ""; // \r\nは最後はいらない
		} catch (RuntimeException e) {
			// void
		}

		Label label1 = new Label(labelString);
		tooltip.add(label1);
		return tooltip;
	}

}
