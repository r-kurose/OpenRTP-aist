package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * ユーティリティクラス
 */
public class Draw2dUtil {
	/**
	 * RtcLinkモデルのRectangleを、Draw2DのRectangleに変換する
	 * 
	 * @param constraint
	 *            RtcLinkモデルのRectangle
	 * @return Draw2DのRectangle
	 */
	public static Rectangle toDraw2dRectangle(
			jp.go.aist.rtm.toolscommon.model.core.Rectangle constraint) {
		return new Rectangle(constraint.getX(), constraint.getY(), constraint
				.getWidth(), constraint.getHeight());
	}

	/**
	 * Draw2DのRectangleを、RtcLinkモデルのRectangleに変換する
	 * 
	 * @param constraint
	 *            Draw2DのRectangle
	 * @return RtcLinkモデルのRectangle
	 */
	public static jp.go.aist.rtm.toolscommon.model.core.Rectangle toRtcLinkRectangle(
			Rectangle constraint) {
		jp.go.aist.rtm.toolscommon.model.core.Rectangle result = new jp.go.aist.rtm.toolscommon.model.core.Rectangle();
		result.setX(constraint.x);
		result.setY(constraint.y);
		result.setWidth(constraint.width);
		result.setHeight(constraint.height);

		return result;
	}

	/**
	 * Draw2DのPointマップを、RtcLinkモデルのPointマップに変換する
	 */
	public static Map<Integer,jp.go.aist.rtm.toolscommon.model.core.Point> 
			toRtcLinkPointMap(Map<Integer,Point> draw2dPointMap) {
		Map<Integer,jp.go.aist.rtm.toolscommon.model.core.Point> result 
			= new HashMap<Integer,jp.go.aist.rtm.toolscommon.model.core.Point>();
		for (Map.Entry<Integer,Point> entry : draw2dPointMap.entrySet()) {
			result.put(entry.getKey(), Draw2dUtil.toRtcLinkPoint(entry.getValue()));
		}

		return result;
	}

	/**
	 * RtcLinkモデルのPointのマップを、Draw2DのPointのマップに変換する
	 * 
	 * @param rtcLinkPointMap
	 *            RtcLinkモデルのRectangle
	 * @return Draw2DのRectangle
	 */
	public static Map<Integer,Point> toDraw2dPointMap(
			Map<Integer,jp.go.aist.rtm.toolscommon.model.core.Point> rtcLinkPointMap) {
		Map<Integer,Point> result = new TreeMap<Integer,Point>();
		for (Map.Entry<Integer,jp.go.aist.rtm.toolscommon.model.core.Point> entry : rtcLinkPointMap.entrySet()) {
			result.put(entry.getKey(),
							Draw2dUtil.toDraw2dPoint((entry.getValue())));
		}

		return result;
	}

	/**
	 * Draw2DのPointを、RtcLinkモデルのPointに変換する
	 * 
	 * @param constraint
	 *            Draw2DのPoint
	 * @return RtcLinkモデルのPoint
	 */
	public static jp.go.aist.rtm.toolscommon.model.core.Point toRtcLinkPoint(
			Point draw2dPoint) {
		jp.go.aist.rtm.toolscommon.model.core.Point result = null;
		if (draw2dPoint != null) {
			result = new jp.go.aist.rtm.toolscommon.model.core.Point();
			result.setX(draw2dPoint.x);
			result.setY(draw2dPoint.y);
		}

		return result;
	}

	/**
	 * RtcLinkモデルのPointを、Draw2DのPointに変換する
	 * 
	 * @param constraint
	 *            RtcLinkモデルのPoint
	 * @return Draw2DのPoint
	 */
	public static Point toDraw2dPoint(
			jp.go.aist.rtm.toolscommon.model.core.Point rtcLinkPoint) {
		Point result = null;
		if (rtcLinkPoint != null) {
			result = new Point(rtcLinkPoint.getX(), rtcLinkPoint.getY());
		}

		return result;
	}
}
