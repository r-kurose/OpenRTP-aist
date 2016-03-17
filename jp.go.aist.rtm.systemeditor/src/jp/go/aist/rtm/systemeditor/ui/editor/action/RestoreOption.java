package jp.go.aist.rtm.systemeditor.ui.editor.action;

/**
 * オンラインシステムダイアグラムにRTSプロファイルをロードするときの復元オプション
 */
public enum RestoreOption {

	NONE(), NORMAL(false, true, false), QUICK(true, true, false), CREATE(false, true, true);

	private final boolean doQuick;
	private final boolean doRelace;
	private final boolean doCreate;

	RestoreOption() {
		this(true, false, false);
	}

	RestoreOption(boolean doQuick, boolean doReplace, boolean doCreate) {
		this.doQuick = doQuick;
		this.doRelace = doReplace;
		this.doCreate = doCreate;
	}

	public boolean doQuick() {
		return this.doQuick;
	}

	public boolean doReplace() {
		return this.doRelace;
	}

	public boolean doCreate() {
		return this.doCreate;
	}

}
