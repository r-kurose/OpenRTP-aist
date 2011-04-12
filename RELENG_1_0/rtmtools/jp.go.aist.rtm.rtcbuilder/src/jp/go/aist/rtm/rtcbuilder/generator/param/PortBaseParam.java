package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;

/**
 * ポート情報のベースクラス
 */
public class PortBaseParam extends AbstractRecordedParam implements
		Serializable {

	private static final long serialVersionUID = 5671331983795384692L;

	public static final String[] COMBO_ITEM = IRtcBuilderConstants.DIRECTION_ITEMS;
	protected String position;
	protected int selection = 0;

	public String getPosition() {
		return position;
	}

	public int getPositionByIndex() {
		return selection;
	}

	public void setPositionByIndex(int index) {
		if (this.selection != index) {
			setUpdated(true);
		}
		this.selection = index;
		this.position = COMBO_ITEM[index];
	}

	public void setPosition(String position) {
		int index = -1;
		if (position.equals(COMBO_ITEM[1])) {
			index = 1;
		} else if (position.equals(COMBO_ITEM[2])) {
			index = 2;
		} else if (position.equals(COMBO_ITEM[3])) {
			index = 3;
		} else {
			index = 0;
		}
		setPositionByIndex(index);
	}

}
