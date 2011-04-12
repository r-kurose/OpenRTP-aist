package jp.go.aist.rtm.rtcbuilder.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.nl.Messages;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * ユーティリティクラス
 */
public class FileUtil {

	/**
	 * ダイアログによって、ディレクトリのパスを取得する
	 * 
	 * @param defaultValue
	 *            デフォルト値
	 * @return ディレクトリのパス
	 */
	public static IPath getDirectoryPathByDialog(IPath defaultPath) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();
		DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
		
		if (defaultPath != null) {
			dialog.setFilterPath(defaultPath.toOSString());
		}
		String pathString = dialog.open();
		if (pathString == null) return null;
		
		IPath result = new Path(pathString + File.separator + IRtcBuilderConstants.DEFAULT_RTC_XML);
		
		return result;
	}

	/**
	 * ダイアログによって、IDLファイルのパスを取得する
	 * 
	 * @param defaultValue
	 *            デフォルト値
	 * @return IDLファイルのパス
	 */
	public static IPath getFilePathByDialog(IFile defaultFile, int style) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();
		FileDialog dialog = new FileDialog(shell, style);

		if (defaultFile != null) {
			dialog.setFileName(defaultFile.toString());
			dialog.setFilterNames(new String[] { "RTCビルダ設定ファイル" });
			dialog.setFilterExtensions(new String[] { "*.xml" });
		}

		String pathString = dialog.open();

		if (pathString == null) {
			return null;
		}

		IPath result = new Path(pathString);

		return result;
	}

	/**
	 * ファイルを読み込み、内容を返す
	 * 
	 * @param path
	 *            パス
	 * @return ファイル内容
	 * @throws IOException 
	 */
	public static String readFile(String path) throws IOException {
		StringBuffer result = null;
		try {
			FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr); 

			result = new StringBuffer();
            String ch;
            String sep = System.getProperty("line.separator");
            while ((ch = br.readLine() ) != null) {
            	result.append(ch + sep);
            }
            br.close();
            isr.close();
            fis.close();
		} catch (IOException e) {
			throw new IOException(Messages.getString("IRTCBMessageConstants.ERROR_PROFILE_RESTORE_P1") + " path:" + path);  //$NON-NLS-1$
		}

		return result == null ? null : result.toString();
	}
}
