package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.ui.editors.IMessageConstants;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RtcBuilderPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	public RtcBuilderPreferencePage(){
		super(GRID);
		setPreferenceStore(RtcBuilderPlugin.getDefault().getPreferenceStore());
	}
	
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		Composite composite = new Composite(getFieldEditorParent(), SWT.NONE);
		composite.setLayout(new GridLayout(1,false));
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		
		PathEditor editor = new PathEditor(DataTypePreferenceManager.IDLFILE_DIRECTORIES,
				IPreferenceMessageConstants.LBL_IDL_SEARCH_DIRS,	"",	composite);
		editor.getListControl(composite).setLayoutData(gd);
		addField(editor);
	}
}
