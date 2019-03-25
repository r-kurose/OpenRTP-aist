package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramStore;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ECConnectionEditPart;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * EC関連のラインを移動するコマンド
 */
public class ECMoveLineCommand extends Command {

	private Integer index;
	private Point point;
	private Point oldpoint;

	private ECConnectionEditPart.ECConnection model;
	private SystemDiagram diagram;

	public void setModel(ECConnectionEditPart.ECConnection model) {
		this.model = model;
	}

	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	public void setIndex(int index) {
		this.index = new Integer(index);
	}

	public void setLocation(Point point) {
		this.point = point;
	}

	@Override
	public void execute() {
		this.oldpoint = getPoint();
		setPoint(this.point);
	}

	@Override
	public void undo() {
		setPoint(this.oldpoint);
		this.oldpoint = null;
	}

	private void setPoint(Point p) {
		SystemDiagramStore store = SystemDiagramStore.instance(this.diagram);
		@SuppressWarnings("unchecked")
		Map<Integer, Point> points = (Map<Integer, Point>) store.getTarget("ECConnection", this.model.getId()).getResource(
				SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP);
		if (p == null) {
			points.remove(this.index);
		} else {
			points.put(this.index, p);
			SystemDiagramStore.Target target = store.getTarget("ECConnection", this.model.getId());
			target.get(SystemDiagramStore.ID_BENDPOINT_EC_CONN);
		}
	}

	private Point getPoint() {
		Point result = null;
		SystemDiagramStore store = SystemDiagramStore.instance(this.diagram);
		@SuppressWarnings("unchecked")
		Map<Integer, Point> points = (Map<Integer, Point>) store.getTarget("ECConnection", this.model.getId()).getResource(
				SystemDiagramStore.KEY_EC_CONN_BENDPOINT_MAP);
		result = points.get(this.index);
		return result;
	}

}
