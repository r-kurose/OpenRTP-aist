package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECConnectionEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.gef.commands.Command;

/**
 * ラインの制約をクリアするコマンド
 */
public class ClearLineConstraintCommand extends Command {
	private ModelElement model;

	private Map<PortConnector, Map> oldRoutingConstraint = new IdentityHashMap<PortConnector, Map>();

	/**
	 * 変更対象のモデルを設定する
	 * 
	 * @param model
	 *            変更対象のモデル
	 */
	public void setModel(ModelElement model) {
		this.model = model;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		model.accept(new Visiter() {
			@SuppressWarnings("unchecked")
			public void visit(ModelElement element) {
				List<PortConnector> connnectionList = new ArrayList<PortConnector>();
				if (element instanceof PortConnector) {
					connnectionList.add((PortConnector) element);
				}

				// TODO:SystemDiagramからPortConnector情報を得る

				for (PortConnector connection : connnectionList) {
					Object routingConstraint = connection
							.getRoutingConstraint();
					if (routingConstraint instanceof EMap) {
						oldRoutingConstraint.put(connection, new TreeMap(
								((EMap) routingConstraint).map()));

						connection.getRoutingConstraint().clear();
					}
				}
			}
		});

		Component comp = (Component) this.model;
		SystemDiagram diagram = (SystemDiagram) comp.eContainer();
		if (diagram != null) {
			AbstractSystemDiagramEditor editor = ComponentUtil.findEditor(diagram);
			ComponentEditPart c = (ComponentEditPart) editor.findEditPart(comp);
			List<ECConnectionEditPart> conns = new ArrayList<>();
			for (Object o1 : c.getChildren()) {
				if (o1 instanceof ECEditPart.OwnECEditPart) {
					ECEditPart.OwnECEditPart part = (ECEditPart.OwnECEditPart) o1;
					for (Object o2 : part.getSourceConnections()) {
						conns.add((ECConnectionEditPart) o2);
					}
				} else if (o1 instanceof ECEditPart.PartECEditPart) {
					ECEditPart.PartECEditPart part = (ECEditPart.PartECEditPart) o1;
					for (Object o2 : part.getTargetConnections()) {
						conns.add((ECConnectionEditPart) o2);
					}
				}
			}
			SystemDiagramStore store = SystemDiagramStore.instance(diagram);
			for (ECConnectionEditPart conn : conns) {
				@SuppressWarnings("unchecked")
				Map<Integer, Point> routingConstraint = (Map<Integer, Point>) store.getTarget("ECConnection",
						conn.getModel().getId()).getResource(SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP);
				routingConstraint.clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		for (PortConnector connection : oldRoutingConstraint.keySet()) {
			Map constraint = oldRoutingConstraint.get(connection);
			connection.getRoutingConstraint().clear();
			connection.getRoutingConstraint().putAll(constraint);
		}

		oldRoutingConstraint.clear();
	}
}
