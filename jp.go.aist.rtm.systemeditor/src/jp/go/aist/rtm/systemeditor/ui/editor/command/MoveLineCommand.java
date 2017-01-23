package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.Map;

import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コネクタのラインを移動するコマンド
 */
public class MoveLineCommand extends Command {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MoveLineCommand.class);

	private Integer index;
	private Point point;
	private Point oldpoint;

	private PortConnector model;
	private SystemDiagram diagram;
	private String connectorId;

	/**
	 * 編集対象のコネクタのモデルを設定します。
	 * 
	 * @param model
	 *            編集対象のコネクタのモデル
	 */
	public void setModel(PortConnector model) {
		this.model = model;
		this.connectorId = model.getConnectorProfile().getConnectorId();
	}

	/**
	 * 編集対象のダイアグラムを設定します。
	 * 
	 * @param diagram
	 *            編集対象のダイアグラム
	 */
	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	/**
	 * インデックスを設定する
	 * 
	 * @param index
	 *            インデックス
	 */
	public void setIndex(int index) {
		this.index = new Integer(index);
	}

	/**
	 * ロケーションを設定する
	 * 
	 * @param point
	 *            ロケーション
	 */
	public void setLocation(Point point) {
		this.point = point;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		LOGGER.trace("execute: this=<{}> point=<{}>", this.connectorId,
				this.point);
		this.oldpoint = getPoint();
		setPoint(this.point);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		LOGGER.trace("undo: this=<{}> oldpoint=<{}>", this.connectorId,
				this.oldpoint);
		setPoint(this.oldpoint);
		this.oldpoint = null;
	}

	private void setPoint(Point p) {
		Map<Integer, jp.go.aist.rtm.toolscommon.model.core.Point> constraint = this.diagram
				.getPortConnectorRoutingConstraint(this.connectorId);
		EMap<Integer, jp.go.aist.rtm.toolscommon.model.core.Point> routingConstraint = this.model
				.getRoutingConstraint();
		if (p == null) {
			constraint.remove(this.index);
			routingConstraint.remove(this.index);
		} else {
			jp.go.aist.rtm.toolscommon.model.core.Point ePoint = Draw2dUtil
					.toRtcLinkPoint(p);
			constraint.put(this.index, ePoint);
			// EMap にはすでに存在するキーに対する put() で変更通知されないバグがあるため、delete-set する。
			routingConstraint.remove(this.index);
			routingConstraint.put(this.index, ePoint);
		}
	}

	private Point getPoint() {
		Point result = null;
		Map<Integer, jp.go.aist.rtm.toolscommon.model.core.Point> constraint = this.diagram
				.getPortConnectorRoutingConstraint(this.connectorId);
		result = Draw2dUtil.toDraw2dPoint(constraint.get(this.index));
		return result;
	}

}
