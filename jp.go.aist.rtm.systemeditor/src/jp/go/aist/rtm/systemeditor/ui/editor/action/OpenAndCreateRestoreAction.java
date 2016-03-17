package jp.go.aist.rtm.systemeditor.ui.editor.action;

import org.eclipse.gef.ui.actions.EditorPartAction;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

/**
 * RTSプロファイルを読み込んで、コンポーネント生成まで行うアクション
 */
public class OpenAndCreateRestoreAction extends EditorPartAction {

	public static final String ID = OpenAndCreateRestoreAction.class.getName();

	public OpenAndCreateRestoreAction(AbstractSystemDiagramEditor editor) {
		super(editor);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(Messages.getString("OpenAndCreateRestoreAction.0"));
		setToolTipText(Messages.getString("OpenAndCreateRestoreAction.1"));
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		((AbstractSystemDiagramEditor) getEditorPart()).open(RestoreOption.CREATE);
	}

}
