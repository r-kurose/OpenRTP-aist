package jp.go.aist.rtm.rtcbuilder.generator.param;

public interface UpdateRecordable {

	/**
	 * @return 更新されている場合はtrue
	 */
	boolean isUpdated();

	/**
	 * 更新状態を初期化します。
	 */
	void resetUpdated();

}
