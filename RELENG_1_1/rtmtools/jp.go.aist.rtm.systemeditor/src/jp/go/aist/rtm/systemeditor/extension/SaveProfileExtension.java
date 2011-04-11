package jp.go.aist.rtm.systemeditor.extension;

import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.events.SelectionAdapter;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

public abstract class SaveProfileExtension {

	/**
	 * プロファイル情報ダイアログへの追加ボタン名を返します。
	 */
	public abstract String getName();

	/**
	 * プロファイル情報ダイアログへの追加ボタンのリスナを返します。
	 * 
	 * @param sd
	 *            ダイアグラム
	 * @return リスナ
	 */
	public abstract SelectionAdapter getListener(SystemDiagram sd);

	/**
	 * プロファイル情報ダイアログの入力完了のタイミングで行うバリデーションを定義します。
	 * 
	 * @param sd
	 *            ダイアグラム
	 * @return エラー情報。正常終了時はnull
	 */
	public abstract ErrorInfo validate(SystemDiagram sd);

	/**
	 * プロファイル情報ダイアログの入力完了のタイミングで行う処理を定義します。
	 * 
	 * @param sd
	 *            ダイアグラム
	 * @param vendor
	 *            ベンダ名
	 * @param name
	 *            システム名
	 * @param version
	 *            バージョン
	 * @param path
	 *            RTSプロファイル保存ファイルパス
	 * @return エラー情報。正常終了時はnull
	 */
	public abstract ErrorInfo prepareSave(SystemDiagram sd, String vendor,
			String name, String version, String path);

	/**
	 * ダイアグラムからRTSプロファイル情報生成後のタイミングで行う処理を定義します。
	 * 
	 * @param sd
	 *            ダイアグラム
	 * @param profile
	 *            RTSプロファイル
	 * @return エラー情報。正常終了時はnull
	 */
	public abstract ErrorInfo preSave(SystemDiagram sd, RtsProfileExt profile);

	/**
	 * RTSプロファイルをファイルへ保存後のタイミングで行う処理を定義します。
	 * 
	 * @param sd
	 *            ダイアグラム
	 * @param file
	 *            RTSプロファイル保存ファイル
	 * @return エラー情報。正常終了時はnull
	 */
	public abstract ErrorInfo postSave(SystemDiagram sd, IFile file);

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
