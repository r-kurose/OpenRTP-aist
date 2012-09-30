package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.io.File;
import java.net.URL;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.ComponentIconStore;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import static jp.go.aist.rtm.systemeditor.nl.Messages.*;

public class IconPreferenceDialog extends TitleAreaDialog {

	static final int EXEC_BUTTON_WIDTH = 70;

	static final String DIALOG_TITLE = getString("IconPreferenceDialog.title");

	static final String LABEL_PATTERN = getString("IconPreferenceDialog.label.pattern");
	static final String LABEL_ICON_PATH = getString("IconPreferenceDialog.label.path");

	static final String BUTTON_LABEL_BROWSE = getString("Common.button.browse");

	static final String ICON_EXTENSION = "*.ico;*.bmp;*.png;*.gif;*.jpg";
	static final String FILTER_EXTENSIONS[] = new String[] { ICON_EXTENSION };
	static final String FILTER_NAMES[] = new String[] { getString("IconPreferenceDialog.filter.name")
			+ " (" + ICON_EXTENSION + ")" };

	Combo kindCombo;
	Text patternText;
	Text pathText;
	Button browseButton;
	Label imageLabel;

	boolean isType = true;
	String pattern = "";
	String path = "";
	ImageDescriptor desc;
	ComponentIconStore.Entry iconEntry;

	public IconPreferenceDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	public void setIconEntry(ComponentIconStore.Entry iconEntry) {
		this.iconEntry = iconEntry;
	}

	public ComponentIconStore.Entry getIconEntry() {
		return iconEntry;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		GridData gd;

		Composite mainComposite = new Composite((Composite) super
				.createDialogArea(parent), SWT.NONE);
		gl = new GridLayout(4, false);
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);
		mainComposite.setFont(parent.getFont());

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(LABEL_PATTERN);
		gd = new GridData();
		label.setLayoutData(gd);

		kindCombo = new Combo(mainComposite, SWT.READ_ONLY);
		gd = new GridData();
		kindCombo.setLayoutData(gd);
		List<String> kinds = ComponentIconStore.Entry.KINDS;
		kindCombo.setItems(kinds.toArray(new String[0]));
		kindCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				isType = ComponentIconStore.Entry.KIND_TYPE.equals(kindCombo
						.getText());
				notifyModified();
			}
		});

		patternText = new Text(mainComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		patternText.setLayoutData(gd);
		patternText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				pattern = (patternText.getText() == null) ? "" : patternText
						.getText();
				notifyModified();
			}
		});

		label = new Label(mainComposite, SWT.NONE);
		label.setText(LABEL_ICON_PATH);
		gd = new GridData();
		label.setLayoutData(gd);

		pathText = new Text(mainComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pathText.setLayoutData(gd);
		pathText.setEditable(false);
		pathText.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				updateImage();
			}
		});

		browseButton = new Button(mainComposite, SWT.NONE);
		browseButton.setText(BUTTON_LABEL_BROWSE);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		browseButton.setLayoutData(gd);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell());
				dialog.setFilterExtensions(FILTER_EXTENSIONS);
				dialog.setFilterNames(FILTER_NAMES);
				if (path != null && !path.isEmpty()) {
					dialog.setFileName(path);
				}
				String s = dialog.open();
				if (s != null) {
					pathText.setFocus();
					pathText.setText(s);
					updateImage();
					browseButton.setFocus();
				}
			}
		});

		label = new Label(mainComposite, SWT.NONE);

		imageLabel = new Label(mainComposite, SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 3;
		gd.heightHint = 64;
		gd.widthHint = 64;
		imageLabel.setLayoutData(gd);

		buildData();

		return mainComposite;
	}

	private void updateImage() {
		imageLabel.setBackgroundImage(null);
		if (pathText.getText() == null) {
			path = "";
			desc = null;
			notifyModified();
			return;
		}
		try {
			path = pathText.getText();
			File file = new File(path);
			path = file.getAbsolutePath();
			URL url = file.toURI().toURL();
			desc = ImageDescriptor.createFromURL(url);
			if (desc != null) {
				imageLabel.setImage(desc.createImage());
			}
		} catch (Exception exp) {
			path = "";
			desc = null;
		}
		notifyModified();
	}
	
	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		notifyModified();
		return composite;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(DIALOG_TITLE);
	}

	/** 表示内容を構築 */
	void buildData() {
		List<String> kinds = ComponentIconStore.Entry.KINDS;
		if (iconEntry != null) {
			int index = kinds.indexOf(iconEntry.getKind());
			if (index != -1) {
				kindCombo.select(index);
			}
			if (iconEntry.isType() && iconEntry.getType() != null) {
				patternText.setText(iconEntry.getType());
			} else if (iconEntry.isCategory()
					&& iconEntry.getCategory() != null) {
				patternText.setText(iconEntry.getCategory());
			}
			if (iconEntry.getPath() != null) {
				path = iconEntry.getPath();
				pathText.setText(path);
			}
			if (iconEntry.getImageDescriptor() != null) {
				desc = iconEntry.getImageDescriptor();
				imageLabel.setImage(desc.createImage());
			}
		}
	}

	/** 変更を通知します */
	void notifyModified() {
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null) {
			okButton.setEnabled(false);
			if (kindCombo.getText() != null && !kindCombo.getText().isEmpty()
					&& !pattern.isEmpty() && !path.isEmpty() && desc != null) {
				okButton.setEnabled(true);
			}
		}
	}

	@Override
	protected void okPressed() {
		if (iconEntry != null) {
			if (isType) {
				iconEntry.setType(pattern);
			} else {
				iconEntry.setCategory(pattern);
			}
			iconEntry.setPath(path);
			iconEntry.setImageDescriptor(desc);
		} else {
			if (isType) {
				iconEntry = ComponentIconStore.Entry.createType(pattern, path,
						desc);
			} else {
				iconEntry = ComponentIconStore.Entry.createCategory(pattern,
						path, desc);
			}
		}
		super.okPressed();
	}

}
