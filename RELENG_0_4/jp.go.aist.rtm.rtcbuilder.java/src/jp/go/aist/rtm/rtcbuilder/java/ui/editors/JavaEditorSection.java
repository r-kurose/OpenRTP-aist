package jp.go.aist.rtm.rtcbuilder.java.ui.editors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.ui.editors.LanguageEditorSection;
import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelCellModifier;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelItem;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelProvider;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelUtil;

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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public class JavaEditorSection implements LanguageEditorSection {

	private Section JavaSection;
	private List<Section> sectionList;
	private RtcBuilderEditor editor;

	private TableViewer javaClassPathViewer;
    private ArrayList<SingleLabelItem> classPathes = new ArrayList<SingleLabelItem>();
    
    public JavaEditorSection(){
    }

	public Section createSection(IManagedForm managedForm, ScrolledForm form) {
		GridLayout gl;

		FormToolkit toolkit = managedForm.getToolkit();
		JavaSection = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		JavaSection.setText("Java");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		JavaSection.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(JavaSection, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		gl = new GridLayout(3, false);
		composite.setLayout(gl);
		JavaSection.setClient(composite);
		JavaSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if( e.getState() ) {
					closeSection();
				}
				update();
			}
		});
		//
		Label label = toolkit.createLabel(composite, "Jar File:");
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		label.setLayoutData(gridData);

		javaClassPathViewer = createTableViewer(composite);
		JavaSection.setExpanded(false);
		return JavaSection;
	}

	public void update() {
		RtcParam rtcParam = editor.getRtcParam();
		if (JavaSection.isExpanded()) {
			rtcParam.getLangList().add(IRtcBuilderConstantsJava.LANG_JAVA);
			rtcParam.getLangArgList().add(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
			SingleLabelUtil.convertSingleItems2Strings(classPathes, editor.getRtcParam().getJavaClassPathes());
		}
	}
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();
		if( javaClassPathViewer != null ) {
			SingleLabelUtil.convertStrings2SingleItems(rtcParam.getJavaClassPathes(), classPathes);
			javaClassPathViewer.setInput(classPathes);
		}
		if( rtcParam.getLangList().contains(	IRtcBuilderConstantsJava.LANG_JAVA)) {
			if( JavaSection != null ) {
				closeSection();
				JavaSection.setExpanded(true);
			}
		}
	}

	private void closeSection() {
		for(Iterator<Section> iter = sectionList.iterator(); iter.hasNext(); ) {
			Section section = iter.next();
			if( !section.getText().equals("Java")) {
				section.setExpanded(false);
			}
		}
	}

	private TableViewer createTableViewer(Composite parent) {
		final TableViewer libraryTableViewer = new TableViewer(parent, SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		libraryTableViewer.getTable().setLayoutData(gd);

		libraryTableViewer.getTable().setHeaderVisible(false);
		libraryTableViewer.getTable().setLinesVisible(true);

		libraryTableViewer.setContentProvider(new ArrayContentProvider());

		libraryTableViewer.setLabelProvider(new SingleLabelProvider());

		TableColumn nameColumn = new TableColumn(libraryTableViewer
				.getTable(), SWT.NONE);
		nameColumn.setText("");
		nameColumn.setWidth(400);

		libraryTableViewer.setColumnProperties(new String[] {"library"});

		CellEditor[] editors = new CellEditor[] {
				new FileSelectCellEditor(libraryTableViewer.getTable()) };

		libraryTableViewer.setCellEditors(editors);
		libraryTableViewer.setCellModifier(new SingleLabelCellModifier(
				libraryTableViewer));

		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginRight = 0;
		buttonComposite.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalAlignment = SWT.BEGINNING;
		buttonComposite.setLayoutData(gd);

		Button addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((List) libraryTableViewer.getInput()).add(new SingleLabelItem("path"));
				libraryTableViewer.refresh();
				update();
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		addButton.setLayoutData(gd);
		Button deleteButton = new Button(buttonComposite, SWT.PUSH);
		deleteButton.setText("Delete");
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = libraryTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) libraryTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) libraryTableViewer.getInput())
							.remove(selectionIndex);
					libraryTableViewer.refresh();
					update();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		//
		return libraryTableViewer;
	}

	private class FileSelectCellEditor extends DialogCellEditor {

	    public FileSelectCellEditor(Composite parent) {
	        super(parent, SWT.NONE);
	    }

	    @Override
		protected Object openDialogBox(Control cellEditorWindow) {
			FileDialog dialog = new FileDialog(editor.getEditorSite().getShell());
			if( ((String)super.doGetValue()).length() > 0)
				dialog.setFileName((String)super.doGetValue());
			String newPath = dialog.open();
			update();
			return newPath;
		}
		
	}

	public void setEditor(RtcBuilderEditor editor) {
		this.editor = editor;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
}
