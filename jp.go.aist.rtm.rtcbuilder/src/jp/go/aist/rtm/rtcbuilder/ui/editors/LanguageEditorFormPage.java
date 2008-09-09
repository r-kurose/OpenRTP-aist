package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Languageページ
 */
public class LanguageEditorFormPage extends AbstractEditorFormPage {

	private static final String ACHTECTURE_INDEX_KEY = LanguageEditorFormPage.class.getName() + ".architecture.kind";
	
	private Section CXXSection;
	private Section CsharpSection;
	private Section RubySection;
	private List<Section> sectionList = new ArrayList<Section>();
	private List<LanguageEditorSection> editorList = new ArrayList<LanguageEditorSection>();
	//
	private Button windowsRadio;
	private Button etcRadio;
	private Combo CXXArchCombo;
	//
	private TableViewer cppLibraryPathViewer;
    private ArrayList<SingleLabelItem> libraryPathes = new ArrayList<SingleLabelItem>();

	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public LanguageEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", "言語・環境");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		GridLayout gl;
		gl = new GridLayout();
		gl.numColumns = 1;

		managedForm.getForm().getBody().setLayout(gl);

		ScrolledForm form = managedForm.getToolkit().createScrolledForm(
				managedForm.getForm().getBody());
		gl = new GridLayout(1, false);
		form.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		form.setLayoutData(gd);

		form.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		managedForm.getToolkit().paintBordersFor(form.getBody());

		form.getBody().setLayout(gl);

		CXXSection = createCXXSection(managedForm, form);
		sectionList.add(CXXSection);
		//
		List<GenerateManager> managerList = RtcBuilderPlugin.getDefault().getLoader().getManagerList();
		if( managerList != null ) {
			for( Iterator<GenerateManager> iter = managerList.iterator(); iter.hasNext(); ) {
				GenerateManager manager = iter.next();
				LanguageEditorSection langsec = manager.getLanguageEditorSection();
				langsec.setEditor(editor);
				langsec.setSectionList(sectionList);
				sectionList.add(langsec.createSection(managedForm, form));
				editorList.add(langsec);
			}
		}
		CsharpSection = createCsharpSection(managedForm, form);
		RubySection = createRubySection(managedForm, form);
		sectionList.add(CsharpSection);
		sectionList.add(RubySection);
		//

		load();
	}

	private Section createCXXSection(IManagedForm managedForm, ScrolledForm form) {
		Section result = null;
		GridLayout gl;

		FormToolkit toolkit = managedForm.getToolkit();
		result = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		result.setText("C++");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		result.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(result, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		gl = new GridLayout(3, false);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);

		result.setClient(composite);
		result.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if( e.getState() ) {
					closeSection("C++");
				}
				update();
			}
		});

		Label label = toolkit.createLabel(composite, "OS");
		//
		final Group osgroup = new Group(composite, SWT.NONE);
		osgroup.setLayout(new GridLayout(2, false));
		gd = new GridData();
		gd.horizontalSpan = 2;
		osgroup.setLayoutData(gd);
		//
		windowsRadio = createRadioButton(toolkit, osgroup, "Windows");
		windowsRadio.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				update();
			}
		});

		etcRadio = createRadioButton(toolkit, osgroup, "その他");
		etcRadio.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				update();
			}
		});
		//
		CXXArchCombo = createEditableCombo(managedForm.getToolkit(), composite,
				"Architecture :", ACHTECTURE_INDEX_KEY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		CXXArchCombo.setLayoutData(gd);
		//
		//
		toolkit.createLabel(composite, "Dependency:");
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		label.setLayoutData(gridData);

		cppLibraryPathViewer = createTableViewer(composite);
		result.setExpanded(false);
		return result;
	}

	private Section createCsharpSection(IManagedForm managedForm, ScrolledForm form) {
		Section result = null;
		GridLayout gl;

		FormToolkit toolkit = managedForm.getToolkit();
		result = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		result.setText("C#");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		result.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(result, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		gl = new GridLayout(2, false);
		composite.setLayout(gl);
		result.setClient(composite);
		result.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if( e.getState() ) {
					closeSection("C#");
				}
				update();
			}
		});
		//
		toolkit.createLabel(composite, "C#");
		//
		result.setEnabled(false);
		result.setExpanded(false);
		return result;
	}

	private Section createRubySection(IManagedForm managedForm, ScrolledForm form) {
		Section result = null;
		GridLayout gl;

		FormToolkit toolkit = managedForm.getToolkit();
		result = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		result.setText("Ruby");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		result.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(result, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		gl = new GridLayout(2, false);
		composite.setLayout(gl);
		result.setClient(composite);
		result.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if( e.getState() ) {
					closeSection("Ruby");
				}
				update();
			}
		});
		//
		toolkit.createLabel(composite, "Ruby");
		//
		result.setEnabled(false);
		result.setExpanded(false);
		return result;
	}
	
	protected void closeSection(String target) {
		for(Iterator<Section> iter = sectionList.iterator(); iter.hasNext(); ) {
			Section section = iter.next();
			if( !section.getText().equals(target)) {
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

	private Button createRadioButton(FormToolkit toolkit,
			Composite composite, String labelString) {
		Button radio = toolkit.createButton(composite, "", SWT.RADIO);
		radio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				update();
			}
		});
		radio.setText(labelString);
		return radio;
	}

	public void update() {
		if( CXXSection != null ) {
			RtcParam rtcParam = editor.getRtcParam();
	
			if( rtcParam.getLangList() != null ) rtcParam.getLangList().clear();
	
			if (CXXSection.isExpanded()) {
//				if( windowsRadio.getSelection() ) {
//					rtcParam.getLangList().add(IRtcBuilderConstants.LANG_CPPWIN);
//				} else {
//					rtcParam.getLangList().add(IRtcBuilderConstants.LANG_CPP);
//				}
				rtcParam.getLangList().add(IRtcBuilderConstants.LANG_CPP);
				rtcParam.getLangArgList().add(IRtcBuilderConstants.LANG_CPP_ARG);
				rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_042);
				rtcParam.setArchitecture(CXXArchCombo.getText());
				SingleLabelUtil.convertSingleItems2Strings(libraryPathes, editor.getRtcParam().getCxxLibraryPathes());
			}
			if (CsharpSection.isExpanded()) {
				rtcParam.getLangList().add(IRtcBuilderConstants.LANG_CSHARP);
				rtcParam.getLangArgList().add(IRtcBuilderConstants.LANG_CSHARP);
			}
			if (RubySection.isExpanded()) {
				rtcParam.getLangList().add(IRtcBuilderConstants.LANG_RUBY);
				rtcParam.getLangArgList().add(IRtcBuilderConstants.LANG_RUBY);
			}
			if( editorList != null ) {
				for( Iterator<LanguageEditorSection> iter = editorList.iterator(); iter.hasNext(); ) {
					LanguageEditorSection langsec = iter.next();
					langsec.update();
				}
			}
	
			editor.updateDirty();
		}
	}

	/**
	 * データをロードする
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();
		closeSection("");
		
		if( cppLibraryPathViewer != null ) {
			SingleLabelUtil.convertStrings2SingleItems(rtcParam.getCxxLibraryPathes(), libraryPathes);
			cppLibraryPathViewer.setInput(libraryPathes);
		}
		if( rtcParam.getLangList().contains(IRtcBuilderConstants.LANG_CPP)) {
			if( CXXSection != null ) {
				CXXSection.setExpanded(true);
				etcRadio.setSelection(true);
				CXXArchCombo.setText(rtcParam.getArchitecture());
			}
		} else if( rtcParam.getLangList().contains(IRtcBuilderConstants.LANG_CPPWIN)) {
			if( CXXSection != null ) {
				CXXSection.setExpanded(true);
				windowsRadio.setSelection(true);
				CXXArchCombo.setText(rtcParam.getArchitecture());
			}
		} else if( rtcParam.getLangList().contains(IRtcBuilderConstants.LANG_CSHARP)) {
			if( CsharpSection != null ) {
				CsharpSection.setExpanded(true);
			}
		} else if( rtcParam.getLangList().contains(	IRtcBuilderConstants.LANG_RUBY)) {
			if( RubySection != null ) {
				RubySection.setExpanded(true);
			}
		}
		if( editorList != null ) {
			for( Iterator<LanguageEditorSection> iter = editorList.iterator(); iter.hasNext(); ) {
				LanguageEditorSection langsec = iter.next();
				langsec.load();
			}
		}
	}
	
	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		
		if( rtcParam.getLangList()==null || rtcParam.getLangList().size()==0 ) {
			result = "生成対象言語を選択してください。";
			return result;
		}
		return null;
	}
	
	public void addDefaultComboValue() {
		if( CXXArchCombo!=null )
			addDefaultComboValue(CXXArchCombo, ACHTECTURE_INDEX_KEY);
	}

	private class FileSelectCellEditor extends DialogCellEditor {

	    public FileSelectCellEditor(Composite parent) {
	        super(parent, SWT.NONE);
	    }

	    @Override
		protected Object openDialogBox(Control cellEditorWindow) {
			FileDialog dialog = new FileDialog(getEditorSite().getShell());
			if( ((String)super.doGetValue()).length() > 0)
				dialog.setFileName((String)super.doGetValue());
			String newPath = dialog.open();
			update();
			return newPath;
		}
		
	}
}
