package jp.go.aist.rtm.repositoryView.ui.preference;

import jp.go.aist.rtm.repositoryView.nl.Messages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * レポジトリビューの設定画面
 *
 */
public class RepositoryViewPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Text cautionCountText;
	private Text propertyFileLocationText;

	public RepositoryViewPreferencePage() {
	}

	public RepositoryViewPreferencePage(String title) {
		super(title);
	}

	public RepositoryViewPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridData gd;
		GridLayout gridLayout;

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);

		//Property File Location
		Group propertyFileLocationGroup = new Group(composite, SWT.NONE);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		propertyFileLocationGroup.setLayout(gridLayout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		propertyFileLocationGroup.setLayoutData(gd);

		propertyFileLocationGroup.setText(Messages.getString("RepositoryViewPreferencePage.0")); //$NON-NLS-1$

		propertyFileLocationText = new Text(propertyFileLocationGroup, SWT.SINGLE | SWT.BORDER
				| SWT.LEFT);
		propertyFileLocationText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus();
			}
		});
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		propertyFileLocationText.setLayoutData(gd);
		propertyFileLocationText.setText(String.valueOf(RepositoryViewPreferenceManager.getInstance()
				.getPropertyFile_Location()));
		//
		Button selectPropertyFileLocationButton = new Button(propertyFileLocationGroup, SWT.PUSH);
		selectPropertyFileLocationButton.setText(Messages.getString("RepositoryViewPreferencePage.1")); //$NON-NLS-1$
		selectPropertyFileLocationButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell());
				if (propertyFileLocationText.getText().length() > 0)
					dialog.setFilterPath(propertyFileLocationText.getText());
				String newPath = dialog.open();
				if (newPath != null) {
					propertyFileLocationText.setText(newPath);
				}
			}
		});
		//Caution Count
		Group cautionCountGroup = new Group(composite, SWT.NONE);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		cautionCountGroup.setLayout(gridLayout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cautionCountGroup.setLayoutData(gd);

		cautionCountGroup.setText(Messages.getString("RepositoryViewPreferencePage.2")); //$NON-NLS-1$
		
		Label label = new Label(cautionCountGroup, SWT.NULL);
		label.setText(Messages.getString("RepositoryViewPreferencePage.3")); //$NON-NLS-1$
		cautionCountText = new Text(cautionCountGroup, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		cautionCountText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus();
			}
		});
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		cautionCountText.setLayoutData(gd);
		
		cautionCountText.setText(String.valueOf(
				RepositoryViewPreferenceManager.getInstance().getCaution_Count()));
		return null;
	}

	@Override
	protected void performDefaults() {
		setDefault();

		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		RepositoryViewPreferenceManager.getInstance().setPropertyFile_Location(propertyFileLocationText.getText());
		RepositoryViewPreferenceManager.getInstance().setCaution_Count(Integer.valueOf(cautionCountText.getText()).intValue());
		return super.performOk();
	}

	private void setDefault() {
		propertyFileLocationText.setText(RepositoryViewPreferenceManager.defaultPropertyFileLocation);
		cautionCountText.setText(String.valueOf(RepositoryViewPreferenceManager.defaultCautionCount));
	}

	private void updateStatus() {
		setValid(validate());
	}
	
	private boolean validate() {
		try {
			int parse = Integer.parseInt(cautionCountText.getText());
			if(parse<0) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void init(IWorkbench workbench) {
	}
}
