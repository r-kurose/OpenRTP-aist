package jp.go.aist.rtm.systemeditor.ui.preference;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;

/**
 * オンラインエディタの設定ページ
 */
public class OnlineEditorPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	static final String LABEL_COMPONENT_ACTION = Messages
			.getString("OnlineEditorPreferencePage.1"); //$NON-NLS-1$

	static final String MSG_CONFIRM_ACTION = Messages
			.getString("OnlineEditorPreferencePage.2"); //$NON-NLS-1$

	SystemEditorPreferenceManager manager;

	Button confirmActionCheckbox;

	public OnlineEditorPreferencePage() {
	}

	public OnlineEditorPreferencePage(String title) {
		super(title);
	}

	public OnlineEditorPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridData gd;

		manager = SystemEditorPreferenceManager.getInstance();

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);

		// Component Actions
		Group componentActionGroup = createGroup(composite,
				LABEL_COMPONENT_ACTION);
		confirmActionCheckbox = new Button(componentActionGroup, SWT.CHECK);
		confirmActionCheckbox.setText(MSG_CONFIRM_ACTION);

		buildData();
		return composite;
	}

	public void init(IWorkbench workbench) {
	}

	@Override
	protected void performDefaults() {
		manager.resetConfirmComponentAction();
		buildData();
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		boolean b = confirmActionCheckbox.getSelection();
		manager.setConfirmComponentAction(b);
		return super.performOk();
	}

	void buildData() {
		if (manager.isConfirmComponentAction()) {
			confirmActionCheckbox.setSelection(true);
		} else {
			confirmActionCheckbox.setSelection(false);
		}
	}

	Group createGroup(Composite parent, String title) {
		Group targetGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		targetGroup.setLayout(gridLayout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		targetGroup.setLayoutData(gd);
		targetGroup.setText(title);
		return targetGroup;
	}

}
