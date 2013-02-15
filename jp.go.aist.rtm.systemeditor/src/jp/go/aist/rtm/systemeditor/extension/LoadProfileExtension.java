package jp.go.aist.rtm.systemeditor.extension;

import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.openrtp.namespaces.rts.version02.RtsProfileExt;

public abstract class LoadProfileExtension {

	/**
	 * RTSプロファイル情報からダイアグラム生成前のタイミングで行う処理を定義します。
	 * 
	 * @param profile
	 *            RTSプロファイル
	 * @param path
	 *            RTSプロファイルパス
	 * @return エラー情報。正常終了時はnull
	 */
	public abstract ErrorInfo preLoad(RtsProfileExt profile, String path);

	/**
	 * RTSプロファイルからダイアグラム生成後のタイミングで行う処理を定義します。
	 * 
	 * @param sd
	 *            ダイアグラム
	 * @param profile
	 *            RTSプロファイル
	 * @param oldSd
	 *            以前のダイアグラム
	 * @return エラー情報。正常終了時はnull
	 */
	public abstract ErrorInfo postLoad(SystemDiagram sd, RtsProfileExt profile,
			SystemDiagram oldSd);

	/**
	 * エラーを表すクラス
	 */
	public static class ErrorInfo {
		boolean isError;
		String message;

		public ErrorInfo(boolean isError, String message) {
			this.isError = isError;
			this.message = message;
		}

		public boolean isError() {
			return isError;
		}

		public String getMessage() {
			return message;
		}
	}

}
