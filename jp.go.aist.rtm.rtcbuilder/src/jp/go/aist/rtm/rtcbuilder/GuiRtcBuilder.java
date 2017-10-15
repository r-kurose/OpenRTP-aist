package jp.go.aist.rtm.rtcbuilder;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator.MergeHandler;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.HeaderException;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.parser.MergeBlockParser;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.compare.CompareResultDialog;
import jp.go.aist.rtm.rtcbuilder.ui.compare.CompareTarget;
import jp.go.aist.rtm.rtcbuilder.ui.editors.IMessageConstants;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * GUIのRtcBuilderを実行する際のメインとなるクラス
 */
public class GuiRtcBuilder {
	private final int RETURN_YES = 0;
	private final int RETURN_NO = 1;

	Generator generator = new Generator();
	/**
	 * ジェネレート・マネージャを追加する
	 * 
	 * @param genManager　生成対象のジェネレート・マネージャ
	 */
	public void addGenerateManager(GenerateManager genManager) {
		generator.addGenerateManager(genManager);
	}
	/**
	 * ジェネレート・マネージャをクリアする
	 */
	public void clearGenerateManager() {
		generator.clearGenerateManager();
	}
	/**
	 * ジェネレートを行い、ファイル出力を行う
	 * 
	 * @param generatorParam
	 *            パラメータ
	 */
	@Deprecated
	public boolean doGenerateWrite(GeneratorParam generatorParam) {
		return this.doGenerateWrite(generatorParam, null, true);
	}
	/**
	 * ジェネレートを行い、ファイル出力を行う
	 * 
	 * @param generatorParam   パラメータ
	 * @param isShowDialog     完了時にダイアログを表示するか
	 */
	public boolean doGenerateWrite(GeneratorParam generatorParam, List<String> idlDirs, boolean isShowDialog) {

		try {
			//設定されたパラメータのチェック
			generator.validate(generatorParam.getRtcParam());
			//IDLファイルのチェック
			try {
				generator.validateIDLDef(generatorParam, idlDirs);
			} catch (HeaderException ex1) {
				String[] buttons = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL };
				MessageDialog dialog = new MessageDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
						"IDL Error", null, "Warning: Included IDL [" + ex1.getMessage() + "] not found. Generated code might be incomplete. Continue?",
						MessageDialog.QUESTION, buttons, 0);
					if(dialog.open() == RETURN_NO) return false;
			}
			//
			generator.doGenerateWrite(generatorParam, idlDirs, new MergeHandler() {
				public int getSelectedProcess(GeneratedResult generatedResult,
						String originalFileContents) {
					return compareByDialog(generatedResult,
							originalFileContents);
				}
			});

			if( isShowDialog ) {
				if(0<generator.getWarningMessage().length()) {
					MessageDialog.openWarning(PlatformUI.getWorkbench()
							.getDisplay().getActiveShell(), "Warning",
							generator.getWarningMessage());
				} else {
					MessageDialog.openInformation(PlatformUI.getWorkbench()
							.getDisplay().getActiveShell(), "Information",
							"Generate success.");
				}
			}
			return true;
		} catch (Exception e) {
			MessageDialog.openError(PlatformUI.getWorkbench().getDisplay()
					.getActiveShell(), "Error", e.getMessage());
			return false;
		}
	}

	private int compareByDialog(GeneratedResult generatedResult,
			String originalFileContents) {
		CompareTarget target = new CompareTarget();
		target.setTargetName(generatedResult.getName());
		target.setOriginalSrc(originalFileContents);
		target.setGenerateSrc(generatedResult.getCode());
		target.setCanMerge(MergeBlockParser.parse(generatedResult.getCode())
				.equals(MergeBlockParser.parse(originalFileContents)) == false);

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		return new CompareResultDialog(shell, target).open();
	}

}
