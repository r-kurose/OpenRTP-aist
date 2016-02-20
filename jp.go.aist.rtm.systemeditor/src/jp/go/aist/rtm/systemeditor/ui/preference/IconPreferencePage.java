package jp.go.aist.rtm.systemeditor.ui.preference;

import static jp.go.aist.rtm.systemeditor.nl.Messages.getString;

import java.io.File;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.ComponentIconStore;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.dialog.IconPreferenceDialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IconPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IconPreferencePage.class);

	static final int EXEC_BUTTON_WIDTH = 90;

	static final String ERROR_IMPORT_PROFILE = getString("IconPreferencePage.error.import");
	static final String ERROR_EXPORT_PROFILE = getString("IconPreferencePage.error.export");

	static final String BUTTON_LABEL_ADD = getString("Common.button.add");
	static final String BUTTON_LABEL_EDIT = getString("Common.button.edit");
	static final String BUTTON_LABEL_DELETE = getString("Common.button.delete");
	static final String BUTTON_LABEL_IMPORT = getString("Common.button.import");
	static final String BUTTON_LABEL_EXPORT = getString("Common.button.export");

	static final int PROPERTY_IMAGE = 0;
	static final int PROPERTY_PATTERN = 1;
	static final int PROPERTY_KIND = 2;
	static final int PROPERTY_PATH = 3;

	TableViewer iconTableViewer;

	Button addButton;
	Button editButton;
	Button deleteButton;
	Button importButton;
	Button exportButton;

	SystemEditorPreferenceManager manager;

	List<ComponentIconStore.Entry> entryList;
	ComponentIconStore.Entry selectedEntry;

	@Override
	protected Control createContents(Composite parent) {

		manager = SystemEditorPreferenceManager.getInstance();

		GridLayout gl;
		GridData gd;

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));

		iconTableViewer = new TableViewer(composite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		iconTableViewer.setContentProvider(new ArrayContentProvider());

		Table table = iconTableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		table.setLayoutData(gd);

		createColumn(iconTableViewer, "icon", 64);
		createColumn(iconTableViewer, "pattern", 90);
		createColumn(iconTableViewer, "kind", 70);
		createColumn(iconTableViewer, "path", 200);

		iconTableViewer.setLabelProvider(new IconLabelProvider());
		iconTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) event
								.getSelection();
						selectedEntry = (ComponentIconStore.Entry) selection
								.getFirstElement();
						notifyModified();
					}
				});

		Composite buttonComposite = new Composite(composite, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		buttonComposite.setLayout(gl);
		buttonComposite.setLayoutData(gd);

		addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText(BUTTON_LABEL_ADD);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		addButton.setLayoutData(gd);
		addButton.setEnabled(true);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IconPreferenceDialog dialog = new IconPreferenceDialog(
						getShell());
				if (dialog.open() != IDialogConstants.OK_ID) {
					return;
				}
				entryList.add(dialog.getIconEntry());
				notifyModified();
			}
		});

		editButton = new Button(buttonComposite, SWT.PUSH);
		editButton.setText(BUTTON_LABEL_EDIT);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		editButton.setLayoutData(gd);
		editButton.setEnabled(false);
		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEntry != null) {
					IconPreferenceDialog dialog = new IconPreferenceDialog(
							getShell());
					dialog.setIconEntry(selectedEntry);
					if (dialog.open() != IDialogConstants.OK_ID) {
						return;
					}
				}
				notifyModified();
			}
		});

		deleteButton = new Button(buttonComposite, SWT.PUSH);
		deleteButton.setText(BUTTON_LABEL_DELETE);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		deleteButton.setLayoutData(gd);
		deleteButton.setEnabled(false);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEntry != null) {
					entryList.remove(selectedEntry);
				}
				notifyModified();
			}
		});

		importButton = new Button(buttonComposite, SWT.PUSH);
		importButton.setText(BUTTON_LABEL_IMPORT);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		importButton.setLayoutData(gd);
		importButton.setEnabled(true);
		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setErrorMessage(null);
				FileDialog dialog = new FileDialog(getShell());
				dialog.setFilterExtensions(new String[] { "*.xml" });
				String s = dialog.open();
				if (s == null) {
					return;
				}
				try {
					File file = new File(s);
					ComponentIconStore store = ComponentIconStore
							.loadProfile(file.getAbsolutePath());
					entryList = store.toEntries();
					iconTableViewer.setInput(entryList);
				} catch (Exception e1) {
					setErrorMessage(ERROR_IMPORT_PROFILE);
					LOGGER.error("Fail to load profile for icon store", e1);
				}
			}
		});

		exportButton = new Button(buttonComposite, SWT.PUSH);
		exportButton.setText(BUTTON_LABEL_EXPORT);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		exportButton.setLayoutData(gd);
		exportButton.setEnabled(true);
		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setErrorMessage(null);
				FileDialog dialog = new FileDialog(getShell());
				dialog.setFilterExtensions(new String[] { "*.xml" });
				String s = dialog.open();
				if (s == null) {
					return;
				}
				ComponentIconStore store = ComponentIconStore
						.getByEntries(entryList);
				try {
					File file = new File(s);
					store.saveProfile(file.getAbsolutePath());
				} catch (Exception e1) {
					setErrorMessage(ERROR_EXPORT_PROFILE);
					LOGGER.error("Fail to save profile", e1);
				}
			}
		});

		buildData();

		return composite;
	}

	TableViewerColumn createColumn(TableViewer viewer, String title, int width) {
		TableViewerColumn col;
		col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setText(title);
		col.getColumn().setWidth(width);
		return col;
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	public boolean performOk() {
		ComponentIconStore store = ComponentIconStore.getByEntries(entryList);
		manager.saveComponentIconStore(store);
		buildData();
		return super.performOk();
	}

	@Override
	protected void performDefaults() {
		manager.resetComponentIconStore();
		buildData();
		super.performDefaults();
	}

	void buildData() {
		manager.loadComponentIconStore(ComponentIconStore.eINSTANCE);
		entryList = ComponentIconStore.eINSTANCE.toEntries();
		iconTableViewer.setInput(entryList);
	}

	void notifyModified() {
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		if (selectedEntry != null) {
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
		iconTableViewer.refresh();
	}

	/** アイコン設定表示のLabelProvider */
	public class IconLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			ComponentIconStore.Entry entry = (ComponentIconStore.Entry) element;
			switch (columnIndex) {
			case PROPERTY_IMAGE:
				ImageDescriptor desc = entry.getImageDescriptor();
				if (desc == null) {
					return null;
				}
				return desc.createImage();
			default:
				break;
			}
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ComponentIconStore.Entry entry = (ComponentIconStore.Entry) element;
			switch (columnIndex) {
			case PROPERTY_PATTERN:
				if (entry.isType()) {
					return entry.getType();
				} else {
					return entry.getCategory();
				}
			case PROPERTY_KIND:
				return (entry.isType()) ? ComponentIconStore.Entry.KIND_TYPE
						: ComponentIconStore.Entry.KIND_CATEGORY;
			case PROPERTY_PATH:
				return entry.getPath();
			default:
				break;
			}
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}
	}

}
