package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

import org.eclipse.gef.ui.actions.EditorPartAction;

/**
 * RTSプロファイルを読み込み、コンポーネントの割当・生成方法を対話的に指定するアクション
 */
public class OpenWithMappingRestoreAction extends EditorPartAction {

	public static final String ID = OpenWithMappingRestoreAction.class
			.getName();

	public OpenWithMappingRestoreAction(AbstractSystemDiagramEditor editor) {
		super(editor);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(Messages.getString("OpenWithMappingRestoreAction.0"));
		setToolTipText(Messages.getString("OpenWithMappingRestoreAction.1"));
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		((AbstractSystemDiagramEditor) getEditorPart())
				.open(RestoreOption.MAPPING);
	}

}
