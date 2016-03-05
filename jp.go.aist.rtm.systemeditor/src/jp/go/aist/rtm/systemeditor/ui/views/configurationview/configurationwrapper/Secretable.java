package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

/**
 * 隠しパラメータを判定するインターフェース
 */
public interface Secretable {

	/**
	 * 隠しパラメータか判定します。
	 * 
	 * @return 隠しパラメータの場合はtrue
	 */
	boolean isSecret();

}
