package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;

import org.eclipse.gef.ui.actions.EditorPartAction;

/**
 * RTSプロファイルを読み込んで、パスIDに基づく復元を行うアクション
 *
 */
public class OpenAndRestoreAction extends EditorPartAction {
	public static final String ID = OpenAndRestoreAction.class.getName();

	public OpenAndRestoreAction(AbstractSystemDiagramEditor editor) {
		super(editor);
	}

	@Override
	protected void init() {
		setId(ID);
		setText(Messages.getString("OpenAndRestoreAction.0")); //$NON-NLS-1$
		setToolTipText(Messages.getString("OpenAndRestoreAction.1")); //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		((AbstractSystemDiagramEditor) getEditorPart()).open(RestoreOption.NORMAL);
	}
}
