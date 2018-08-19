package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import org.eclipse.draw2d.ConnectionAnchor;

/**
 * 方向性のあるコネクションのアンカーを表します。
 */
public interface DirectionableConnectionAnchor extends ConnectionAnchor {

	/**
	 * 方向を取得します。
	 * 
	 * @return　"RIGHT", "LEFT", "UP", "DOWN"
	 */
	String getDirection();

}
