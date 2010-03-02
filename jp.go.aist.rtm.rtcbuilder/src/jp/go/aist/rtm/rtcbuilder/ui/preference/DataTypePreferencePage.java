package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.ui.editors.IMessageConstants;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelCellModifier;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelItem;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelProvider;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelUtil;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DataTypePreferencePage extends AbstractPreferencePage implements
		IWorkbenchPreferencePage {

    private ArrayList<SingleLabelItem> idlFileDirectories = new ArrayList<SingleLabelItem>();
	private TableViewer directoriesTableViewer;

	public DataTypePreferencePage() {
	}

	public DataTypePreferencePage(String title) {
		super(title);
	}

	public DataTypePreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout(2,false));

		directoriesTableViewer = new TableViewer(composite, SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalSpan = 2;
		directoriesTableViewer.getTable().setLayoutData(gd);

		directoriesTableViewer.getTable().setHeaderVisible(true);
		directoriesTableViewer.getTable().setLinesVisible(true);

		directoriesTableViewer.setContentProvider(new ArrayContentProvider());

		directoriesTableViewer.setLabelProvider(new SingleLabelProvider());

		TableColumn nameColumn = new TableColumn(directoriesTableViewer
				.getTable(), SWT.NONE);
		nameColumn.setText(IPreferenceMessageConstants.DATA_TYPE_LBL_DIRS);
		nameColumn.setWidth(400);

		directoriesTableViewer.setColumnProperties(new String[] {"idlFileDir"});

		CellEditor[] editors = new CellEditor[] {
				new DirectorySelectCellEditor(directoriesTableViewer.getTable()) };

		directoriesTableViewer.setCellEditors(editors);
		directoriesTableViewer.setCellModifier(new SingleLabelCellModifier(
				directoriesTableViewer));

		Composite buttonComposite = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout();
		buttonComposite.setLayout(gl);
		gd = new GridData();
		gd.widthHint = 80;
		buttonComposite.setLayoutData(gd);

		Button addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((List) directoriesTableViewer.getInput()).add(new SingleLabelItem("idlFileDir"));
				directoriesTableViewer.refresh();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		addButton.setLayoutData(gd);
		Button deleteButton = new Button(buttonComposite, SWT.PUSH);
		deleteButton.setText("Delete");
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = directoriesTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) directoriesTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) directoriesTableViewer.getInput())
							.remove(selectionIndex);
					directoriesTableViewer.refresh();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		//
		SingleLabelUtil.convertStrings2SingleItems(DataTypePreferenceManager.getInstance().getIdlFileDirectories(), idlFileDirectories);
		directoriesTableViewer.setInput(idlFileDirectories);
		
		return composite;
	}

	public void init(IWorkbench workbench) {
	}

	@Override
	protected boolean validate() {
		List<String> sources = new ArrayList<String>();
		try{
			for( Iterator<SingleLabelItem> iter = idlFileDirectories.iterator(); iter.hasNext(); ) {
				SingleLabelItem targetIdl = iter.next();
				File idlDir = new File(targetIdl.getLabeltext());
				String[] idlNames = idlDir.list();
				for( int intidx=0; intidx<idlNames.length; intidx++ ) {
					if(idlNames[intidx].toLowerCase().endsWith(".idl") ) {
						String idlContent = FileUtil.readFile(
								targetIdl.getLabeltext() + System.getProperty( "file.separator" ) + idlNames[intidx]);
						sources.add(idlContent);
					}
				}
			}
			if( idlFileDirectories.size()>0 && sources.size()==0 ) {
				MessageDialog.openError(getShell(), "Error", IMessageConstants.PREF_IDLPARSE_NOFILE);
				return false;
			}
			IDLParamConverter.extractTypeDef(sources);
		} catch (Exception ex) {
			MessageDialog.openError(getShell(), "Error", 
					IMessageConstants.PREF_IDLPARSE_ERROR + System.getProperty( "line.separator" ) + System.getProperty( "line.separator" ) + 
					ex.getMessage() );
			return false;
		}
		return true;
	}

	@Override
	protected void performDefaults() {
		idlFileDirectories.clear();
		directoriesTableViewer.refresh();
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		if( !validate() ) return false;
		ArrayList<String> target = new ArrayList<String>();
		SingleLabelUtil.convertSingleItems2Strings(idlFileDirectories, target);
		DataTypePreferenceManager.getInstance().setIdlFileDirectories(target);
		return super.performOk();
	}

	@Override
	protected void performApply() {
		if( !validate() ) return;
		ArrayList<String> target = new ArrayList<String>();
		SingleLabelUtil.convertSingleItems2Strings(idlFileDirectories, target);
		DataTypePreferenceManager.getInstance().setIdlFileDirectories(target);
		super.performApply();
	}

	private class DirectorySelectCellEditor extends DialogCellEditor {

	    public DirectorySelectCellEditor(Composite parent) {
	        super(parent, SWT.NONE);
	    }

	    @Override
		protected Object openDialogBox(Control cellEditorWindow) {
			DirectoryDialog dialog = new DirectoryDialog(getShell());
			if( ((String)super.doGetValue()).length() > 0) {
				dialog.setFilterPath((String)super.doGetValue());
			}
	        dialog.setText(IMessageConstants.PREF_IDL_SELECTION);
			String newPath = dialog.open();
			return newPath;
		}
		
	}
}
