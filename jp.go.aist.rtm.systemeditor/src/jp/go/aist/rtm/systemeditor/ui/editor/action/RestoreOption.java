package jp.go.aist.rtm.systemeditor.ui.editor.action;

/**
 * オンラインシステムダイアグラムにRTSプロファイルをロードするときの復元オプション
 *
 */
public enum RestoreOption {
	NONE(), NORMAL(false, true), QUICK(true, true);

	private final boolean doQuick;
	private final boolean doRelace;
	
	RestoreOption(){
		this(true, false);
	}
	RestoreOption(boolean doQuick, boolean doReplace) {
		this.doQuick = doQuick;
		this.doRelace = doReplace;
	}
	
	public boolean doQuick() {
		return doQuick;
	}

	public boolean doReplace() {
		return doRelace;
	}
}
