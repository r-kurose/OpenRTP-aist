package jp.go.aist.rtm.toolscommon.model.core;

import java.io.Serializable;

/**
 * RtcLinkのPointを表現するクラス
 */
public class Point implements Serializable {
	private static final long serialVersionUID = -6040171233600016868L;

	private int x;

	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
