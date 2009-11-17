package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.param.LibraryParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.TargetEnvParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelCellModifier;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelItem;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelProvider;
import jp.go.aist.rtm.rtcbuilder.ui.parts.SingleLabelUtil;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * Languageページ
 */
public class LanguageEditorFormPage extends AbstractEditorFormPage {
	
	private static final String LANGUAGE_PROPERTY_VERSION = "LANGUAGE_PROPERTY_VERSION";
	private static final String LANGUAGE_PROPERTY_OS = "LANGUAGE_PROPERTY_OS";
	//
	private static final String LANGUAGE_PROPERTY_LIBRARY_NAME = "LANGUAGE_PROPERTY_LIBRARY_NAME";
	private static final String LANGUAGE_PROPERTY_LIBRARY_VERSION = "LANGUAGE_PROPERTY_LIBRARY_VERSION";
	private static final String LANGUAGE_PROPERTY_LIBRARY_OTHER = "LANGUAGE_PROPERTY_LIBRARY_OTHER";
	
	private List<GenerateManager> managerList = null;
	private Group LangGroup;
	private Button cppRadio;
	private Button rubyRadio;
	private List<Button> buttonList = new ArrayList<Button>();
	//
	private Composite envSection;
	private TableViewer langVersionViewer;
	private TableViewer osVersionViewer;
	private Text osOther;
	private TableViewer cpuTypesViewer;
	private Text cpuOther;
	//
	private TableViewer libraryViewer;

	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public LanguageEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.LANGUAGE_SECTION);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm);
		FormToolkit toolkit = managedForm.getToolkit();

		Label label = toolkit.createLabel(form.getBody(), IMessageConstants.LANGUAGE_SECTION);
		if( titleFont==null ) {
			titleFont = new Font(form.getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		label.setFont(titleFont);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		//
		createLanguageSection(toolkit, form);
		createHintSection(toolkit, form);
		creatEnvSection(toolkit, form);
		//
		managerList = RtcBuilderPlugin.getDefault().getLoader().getManagerList();
		if( managerList != null ) {
			for( Iterator<GenerateManager> iter = managerList.iterator(); iter.hasNext(); ) {
				GenerateManager manager = iter.next();
				Button extRadio = createRadioCheckButton(toolkit, LangGroup, manager.getManagerKey(), SWT.RADIO);
				extRadio.addSelectionListener(createLanguageRadioListner());
				buttonList.add(extRadio);
			}
		}
		rubyRadio = createRadioCheckButton(toolkit, LangGroup, "Ruby", SWT.RADIO);
		rubyRadio.setEnabled(false);
		
		load();
	}

	private void creatEnvSection(FormToolkit toolkit, ScrolledForm form) {
		envSection = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.LANGUAGE_ENV_TITLE, IMessageConstants.LANGUAGE_ENV_EXPL, 2);
		//
		langVersionViewer = createVersionTableViewer(envSection);
		//
		Group detailGroup = new Group(envSection, SWT.SHADOW_IN);
		detailGroup.setText(IMessageConstants.LANGUAGE_LBL_DETAIL);
		GridLayout gl = new GridLayout(1, false);
		detailGroup.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		detailGroup.setLayoutData(gd);
		//
		Composite detailBase = toolkit.createComposite(detailGroup, SWT.NULL);
		gl = new GridLayout(4, false);
		detailBase.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		detailBase.setLayoutData(gd);
		//
		osVersionViewer = createSingleTableViewer(detailBase, "OS Version", "OS Version");
		cpuTypesViewer = createSingleTableViewer(detailBase, "CPU", "CPU");
		//
		Composite textBase = toolkit.createComposite(detailGroup, SWT.NULL);
		toolkit.paintBordersFor(textBase);
		gl = new GridLayout(2, false);
		textBase.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		textBase.setLayoutData(gd);
		//
		osOther = createLabelAndText(toolkit, textBase, IMessageConstants.LANGUAGE_LBL_OSETC, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		osOther.setLayoutData(gridData);
		//
		cpuOther = createLabelAndText(toolkit, textBase, IMessageConstants.LANGUAGE_LBL_CPUETC, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		cpuOther.setLayoutData(gridData);
		//
		Group libraryGroup = new Group(textBase, SWT.SHADOW_IN);
		libraryGroup.setText(IMessageConstants.LANGUAGE_LBL_LIBRARY);
		gl = new GridLayout(2, false);
		libraryGroup.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		libraryGroup.setLayoutData(gd);
		toolkit.paintBordersFor(libraryGroup);
		
//		Composite libraryBase = toolkit.createComposite(detailGroup, SWT.NULL);
//		gl = new GridLayout(2, false);
//		libraryBase.setLayout(gl);
//		gd = new GridData(GridData.FILL_BOTH);
//		libraryBase.setLayoutData(gd);
		//
		libraryViewer = createLibrariesTableViewer(libraryGroup);
	}

	private void createLanguageSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.LANGUAGE_LANG_TITLE, IMessageConstants.LANGUAGE_LANG_EXPL, 1);
		//
		LangGroup = new Group(composite, SWT.NONE);
		LangGroup.setLayout(new GridLayout(1, false));
		GridData gd = new GridData();
		LangGroup.setLayoutData(gd);
		//
		cppRadio = createRadioCheckButton(toolkit, LangGroup, "C++", SWT.RADIO);
		cppRadio.addSelectionListener(createLanguageRadioListner());
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 2);
		//
		createHintLabel(IMessageConstants.LANGUAGE_HINT_LANG_TITLE, IMessageConstants.LANGUAGE_HINT_LANG_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.LANGUAGE_HINT_ENV_TITLE, IMessageConstants.LANGUAGE_HINT_ENV_DESC, toolkit, composite);
	}

	private TableViewer createLibrariesTableViewer(Composite parent) {
		//
		final TableViewer targetTableViewer = new TableViewer(parent, SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		targetTableViewer.getTable().setLayoutData(gd);

		targetTableViewer.getTable().setHeaderVisible(true);
		targetTableViewer.getTable().setLinesVisible(true);

		targetTableViewer.setContentProvider(new ArrayContentProvider());

		targetTableViewer.setLabelProvider(new LibraryLabelProvider());

		createColumnToTableViewer(targetTableViewer,"Name", 130);
		createColumnToTableViewer(targetTableViewer,"Version", 130);
		createColumnToTableViewer(targetTableViewer,"Info.", 130);


		targetTableViewer.setColumnProperties(new String[] {
				LANGUAGE_PROPERTY_LIBRARY_NAME, LANGUAGE_PROPERTY_LIBRARY_VERSION,
				LANGUAGE_PROPERTY_LIBRARY_OTHER});

		CellEditor[] editors = new CellEditor[] {
				new TextCellEditor(targetTableViewer.getTable()),
				new TextCellEditor(targetTableViewer.getTable()),
				new TextCellEditor(targetTableViewer.getTable())
			};
		
		targetTableViewer.setCellEditors(editors);
		targetTableViewer.setCellModifier(new LibraryCellModifier(targetTableViewer));

		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginRight = 0;
		buttonComposite.setLayout(gl);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalAlignment = SWT.BEGINNING;
		buttonComposite.setLayoutData(gd);

		Button addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( targetTableViewer.getInput()==null ) return;
				LibraryParam selectParam = new LibraryParam("Name", "Version", "Info.");
				((List) targetTableViewer.getInput()).add(selectParam);
				targetTableViewer.refresh();
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
				int selectionIndex = targetTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) targetTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) targetTableViewer.getInput())
							.remove(selectionIndex);
					targetTableViewer.refresh();
					update();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		return targetTableViewer;
	}

	private TableViewer createSingleTableViewer(Composite parent, String title, final String newItem) {
		//
		final TableViewer targetTableViewer = new TableViewer(parent, SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		targetTableViewer.getTable().setLayoutData(gd);
		targetTableViewer.getTable().setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);

		targetTableViewer.getTable().setHeaderVisible(true);
		targetTableViewer.getTable().setLinesVisible(true);

		targetTableViewer.setContentProvider(new ArrayContentProvider());

		targetTableViewer.setLabelProvider(new SingleLabelProvider());

		TableColumn versionColumn = new TableColumn(targetTableViewer.getTable(), SWT.NONE);
		versionColumn.setText(title);
		versionColumn.setWidth(160);


		targetTableViewer.setColumnProperties(new String[] {"single"});

		CellEditor[] editors = new CellEditor[] {
				new TextCellEditor(targetTableViewer.getTable())
			};
		
		targetTableViewer.setCellEditors(editors);
		targetTableViewer.setCellModifier(new LangCellModifier(targetTableViewer));

		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginRight = 0;
		buttonComposite.setLayout(gl);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalAlignment = SWT.BEGINNING;
		buttonComposite.setLayoutData(gd);

		Button addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( targetTableViewer.getInput()==null ) return;
				((List) targetTableViewer.getInput()).add(new SingleLabelItem(newItem));
				targetTableViewer.refresh();
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
				int selectionIndex = targetTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) targetTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) targetTableViewer.getInput())
							.remove(selectionIndex);
					targetTableViewer.refresh();
					update();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		//
		return targetTableViewer;
	}

	private TableViewer createVersionTableViewer(Composite parent) {
		//
		final TableViewer targetTableViewer = new TableViewer(parent, SWT.SINGLE | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.grabExcessHorizontalSpace = true;
		targetTableViewer.getTable().setLayoutData(gd);

		targetTableViewer.getTable().setHeaderVisible(true);
		targetTableViewer.getTable().setLinesVisible(true);

		targetTableViewer.setContentProvider(new ArrayContentProvider());

		targetTableViewer.setLabelProvider(new VersionLabelProvider());

		createColumnToTableViewer(targetTableViewer,"Version", 200);
		createColumnToTableViewer(targetTableViewer,"OS", 200);

		targetTableViewer.setColumnProperties(new String[] {
				LANGUAGE_PROPERTY_VERSION, LANGUAGE_PROPERTY_OS});

		CellEditor[] editors = new CellEditor[] {
				new TextCellEditor(targetTableViewer.getTable()),
				new TextCellEditor(targetTableViewer.getTable())
			};
		
		targetTableViewer.setCellEditors(editors);
		targetTableViewer.setCellModifier(new VersionCellModifier(targetTableViewer));

		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginRight = 0;
		buttonComposite.setLayout(gl);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalAlignment = SWT.BEGINNING;
		buttonComposite.setLayoutData(gd);

		Button addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( targetTableViewer.getInput()==null ) return;
				TargetEnvParam selectParam = new TargetEnvParam("New Version", "Target OS", "", "");
				((List) targetTableViewer.getInput()).add(selectParam);
				targetTableViewer.refresh();
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
				int selectionIndex = targetTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) targetTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) targetTableViewer.getInput())
							.remove(selectionIndex);
					targetTableViewer.refresh();
					
					osVersionViewer.setInput(null);
					cpuTypesViewer.setInput(null);
					osOther.setText("");
					cpuOther.setText("");
					libraryViewer.setInput(null);
					//
					setEnvSectionEnabled(true);
					//
					update();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		//
		targetTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				TargetEnvParam selectParam = (TargetEnvParam)selection.getFirstElement();
				if( selectParam != null ) {
					ArrayList<SingleLabelItem> targets = new ArrayList<SingleLabelItem>();
					SingleLabelUtil.convertStrings2SingleItems(selectParam.getOsVersions(), targets);
					osVersionViewer.setInput(targets);
					//
					targets = new ArrayList<SingleLabelItem>();
					SingleLabelUtil.convertStrings2SingleItems(selectParam.getCpus(), targets);
					cpuTypesViewer.setInput(targets);
					//
					osOther.setText(getValue(selectParam.getOther()));
					cpuOther.setText(getValue(selectParam.getCpuOther()));
					//
					libraryViewer.setInput(selectParam.getLibraries());
					//
					setEnvSectionEnabled(true);
				}
			}
		});
		return targetTableViewer;
	}

	public void update() {
		if (cppRadio != null) {
			// 以下、cppRadioが有効な場合のみ実行する
			// →この画面が表示される前にこの処理が呼ばれた場合は、なにもしない
			// ∵rtcParamが画面に反映される前にクリアしてしまうとまずいため
			RtcParam rtcParam = editor.getRtcParam();

			List<String> langList = new ArrayList<String>();
			List<String> langArgList = new ArrayList<String>();
			String rtmVersion = null;

			if (cppRadio.getSelection()) {
				langList.add(IRtcBuilderConstants.LANG_CPP);
				langArgList.add(IRtcBuilderConstants.LANG_CPP_ARG);
				rtmVersion = IRtcBuilderConstants.RTM_VERSION_100;
			}
			if (buttonList != null) {
				for (Button extButton : buttonList) {
					if (!extButton.getSelection()) {
						continue;
					}
					for (GenerateManager manager : managerList) {
						if (!extButton.getText().trim().equals(
								manager.getManagerKey())) {
							continue;
						}
						langList.add(manager.getManagerKey());
						langArgList.add(manager.getManagerKey());
						rtmVersion = manager.getTargetVersion();
						break;
					}
					break;
				}
			}
			if (!rtcParam.getLangList().equals(langList)) {
				rtcParam.getLangList().clear();
				rtcParam.getLangList().addAll(langList);
				rtcParam.getLangArgList().clear();
				rtcParam.getLangArgList().addAll(langArgList);
				rtcParam.setRtmVersion(rtmVersion);
			}
			//
			StructuredSelection selection = (StructuredSelection) langVersionViewer.getSelection();
			TargetEnvParam selectParam = (TargetEnvParam) selection.getFirstElement();
			if (selectParam != null) {
				ArrayList<String> targets = new ArrayList<String>();
				SingleLabelUtil.convertSingleItems2Strings((ArrayList<SingleLabelItem>) osVersionViewer.getInput(), targets);
				if (!selectParam.getOsVersions().equals(targets)) {
					selectParam.getOsVersions().clear();
					selectParam.getOsVersions().addAll(targets);
				}
				//
				targets = new ArrayList<String>();
				SingleLabelUtil.convertSingleItems2Strings((ArrayList<SingleLabelItem>) cpuTypesViewer.getInput(), targets);
				if (!selectParam.getCpus().equals(targets)) {
					selectParam.getCpus().clear();
					selectParam.getCpus().addAll(targets);
				}
				//
				selectParam.setOther(getText(osOther.getText()));
				selectParam.setCpuOther(getText(cpuOther.getText()));
			}
			//
			editor.updateDirty();
		}
	}

	/**
	 * データをロードする
	 */
	public void load() {
		if (cppRadio == null) {
			return;
		}
		//
		RtcParam rtcParam = editor.getRtcParam();
		if (rtcParam.getLangList().contains(IRtcBuilderConstants.LANG_CPP)
				|| rtcParam.getLangList().contains(
						IRtcBuilderConstants.LANG_CPPWIN)) {
			cppRadio.setSelection(true);
		} else {
			// rtcParam.getLangList()に含まれない場合は選択解除
			cppRadio.setSelection(false);
		}
		if (buttonList != null) {
			for (Button chkButton : buttonList) {
				if (rtcParam.getLangList().contains(chkButton.getText().trim())) {
					chkButton.setSelection(true);
				} else {
					// rtcParam.getLangList()に含まれない場合は選択解除
					chkButton.setSelection(false);
				}
			}
		}
		//
		langVersionViewer.setInput(rtcParam.getTargetEnvs());
		//
		StructuredSelection selection = (StructuredSelection) langVersionViewer
				.getSelection();
		TargetEnvParam selectParam = (TargetEnvParam) selection
				.getFirstElement();
		if (selectParam == null) {
			osVersionViewer.setInput(null);
			cpuTypesViewer.setInput(null);
			osOther.setText("");
			cpuOther.setText("");
			libraryViewer.setInput(null);
		}
	}

	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		
		if( rtcParam.getLangList()==null || rtcParam.getLangList().size()==0 ) {
			result = IMessageConstants.LANGUAGE_SELECTION_CAUTION;
			return result;
		}
		return null;
	}

	@Override
	public void pageSelected() {
		Control [] btns = LangGroup.getChildren();
		boolean selected = false;// Radioが一つでも選択されているとtrue
		for(int i=0; i<btns.length; i++){
			if( ((Button)btns[i]).getSelection() ){
				selected = true;
				break;
			}
		}
		setEnvSectionEnabled(selected);
	}

	private void setEnvSectionEnabled(boolean value) {
		Color enableColor = getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
		Color disnableColor = getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
		//
		StructuredSelection selection = (StructuredSelection) langVersionViewer
				.getSelection();
		TargetEnvParam selectParam = (TargetEnvParam) selection
				.getFirstElement();
		//
		envSection.setEnabled(value);
		//
		if (value) {
			langVersionViewer.getControl().setBackground(enableColor);
		} else {
			langVersionViewer.getControl().setBackground(disnableColor);
		}
		//
		if (value && selectParam != null) {
			osVersionViewer.getControl().setBackground(enableColor);
			cpuTypesViewer.getControl().setBackground(enableColor);
			osOther.setEnabled(true);
			cpuOther.setEnabled(true);
			osOther.setBackground(enableColor);
			cpuOther.setBackground(enableColor);
			libraryViewer.getControl().setBackground(enableColor);
		} else {
			osVersionViewer.getControl().setBackground(disnableColor);
			cpuTypesViewer.getControl().setBackground(disnableColor);
			osOther.setEnabled(false);
			cpuOther.setEnabled(false);
			osOther.setBackground(disnableColor);
			cpuOther.setBackground(disnableColor);
			libraryViewer.getControl().setBackground(disnableColor);
		}
	}

	private class LangCellModifier extends SingleLabelCellModifier {

		public LangCellModifier(StructuredViewer viewer) {
			super(viewer);
		}

		@Override
		public void modify(Object element, String property, Object value) {
			super.modify(element, property, value);
			update();
		}
		
	}

	private class VersionLabelProvider extends LabelProvider implements	ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof TargetEnvParam == false) {
				return null;
			}
		
			TargetEnvParam targetEnvParam = (TargetEnvParam) element;
		
			String result = null;
			if (columnIndex == 0) {
				result = targetEnvParam.getLangVersion();
			} else if (columnIndex == 1) {
				result = targetEnvParam.getOs();
			}
		
			return result;
		}
	}

	private class VersionCellModifier implements ICellModifier {
	
		private StructuredViewer viewer;
		
		public VersionCellModifier(StructuredViewer viewer) {
			this.viewer = viewer;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public Object getValue(Object element, String property) {
			if (element instanceof TargetEnvParam == false) {
				return null;
			}
		
			TargetEnvParam targetParam = (TargetEnvParam) element;
		
			String result = null;
			if (LANGUAGE_PROPERTY_VERSION.equals(property)) {
				result = targetParam.getLangVersion();
			} else if (LANGUAGE_PROPERTY_OS.equals(property)) {
				result = targetParam.getOs();
			}
		
			return result;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public void modify(Object element, String property, Object value) {
			if (element instanceof TableItem == false) {
				return;
			}
		
			TargetEnvParam targetParam = (TargetEnvParam) ((TableItem) element).getData();
		
			if (LANGUAGE_PROPERTY_VERSION.equals(property)) {
				targetParam.setLangVersion((String) value);
			} else if (LANGUAGE_PROPERTY_OS.equals(property)) {
				targetParam.setOs((String) value);
			}
		
			viewer.refresh();
			update();
		}
	}
	
	private class LibraryLabelProvider extends LabelProvider implements	ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof LibraryParam == false) {
				return null;
			}
		
			LibraryParam libraryParam = (LibraryParam) element;
		
			String result = null;
			if (columnIndex == 0) {
				result = libraryParam.getName();
			} else if (columnIndex == 1) {
				result = libraryParam.getVersion();
			} else if (columnIndex == 2) {
				result = libraryParam.getOther();
			}
		
			return result;
		}
	}

	private class LibraryCellModifier implements ICellModifier {
	
		private StructuredViewer viewer;
		
		public LibraryCellModifier(StructuredViewer viewer) {
			this.viewer = viewer;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public Object getValue(Object element, String property) {
			if (element instanceof LibraryParam == false) {
				return null;
			}
		
			LibraryParam libraryParam = (LibraryParam) element;
		
			String result = null;
			if (LANGUAGE_PROPERTY_LIBRARY_NAME.equals(property)) {
				result = libraryParam.getName();
			} else if (LANGUAGE_PROPERTY_LIBRARY_VERSION.equals(property)) {
				result = libraryParam.getVersion();
			} else if (LANGUAGE_PROPERTY_LIBRARY_OTHER.equals(property)) {
				result = libraryParam.getOther();
			}
		
			return result;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public void modify(Object element, String property, Object value) {
			if (element instanceof TableItem == false) {
				return;
			}
		
			LibraryParam libraryParam = (LibraryParam) ((TableItem) element).getData();
		
			if (LANGUAGE_PROPERTY_LIBRARY_NAME.equals(property)) {
				libraryParam.setName((String) value);
			} else if (LANGUAGE_PROPERTY_LIBRARY_VERSION.equals(property)) {
				libraryParam.setVersion((String) value);
			} else if (LANGUAGE_PROPERTY_LIBRARY_OTHER.equals(property)) {
				libraryParam.setOther((String) value);
			}
		
			viewer.refresh();
			update();
		}
	}
	
	private org.eclipse.swt.events.SelectionAdapter createLanguageRadioListner(){
		return new org.eclipse.swt.events.SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// eventからボタン名称を取得
				String btnName = ((Button)e.widget).getText();
				// 選択言語による活性状態の制御
				editor.setEnabledInfoByLang(btnName);
				// 自ページ内の環境セクションの活性状態の制御
				setEnvSectionEnabled(true);
			}
		};
	}
	
}
