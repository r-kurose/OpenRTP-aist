package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialogData;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.toolscommon.model.component.Component;

public class CreateCompositeComponentJob1 extends TimeoutWrappedJob {
	private NewCompositeComponentDialog dialog;
	private AbstractSystemDiagramEditor targetEditor;

	@Override
	protected Object executeCommand() {
		return 	createCompositeComponent();
	}

	private Object createCompositeComponent() {
		if (!targetEditor.isOnline()) {
			// オフラインの場合
			return NewCompositeComponentDialogData
					.createCompositeComponentSpecification(dialog);
		} else if (dialog.getCompositeType().equals(
				Component.COMPOSITETYPE_GROUPING)) {
			// コンポジット種別がGroupingの場合 2009.01.06
			return NewCompositeComponentDialogData
					.createCompositeComponentSpecification(dialog);
		} else {
			// オンラインの場合
			// マネージャでリモートオブジェクト生成（現在はCORBAにだけ対応）
			return dialog.createComponentR();
		}
	}

	public void setDialog(NewCompositeComponentDialog dialog) {
		this.dialog = dialog;
	}

	public void setTargetEditor(AbstractSystemDiagramEditor targetEditor) {
		this.targetEditor = targetEditor;
	}

}
