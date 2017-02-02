package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.rtcbuilder.GuiRtcBuilder;
import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.extension.ImportExtension;
import jp.go.aist.rtm.rtcbuilder.factory.ExportCreator;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.PropertyParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.DocumentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.wizard.RtcExportWizard;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

/**
 * Basic Profile 設定ページ
 */
public class BasicEditorFormPage extends AbstractEditorFormPage {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BasicEditorFormPage.class);

	/**
	 * 生成を行ったCategoryの情報を保存するワークスペース永続文字列へのキー
	 */
	private static final String CATEGORY_INDEX_KEY = BasicEditorFormPage.class.getName() + ".category.name";
	private final String CATEGORY_COMPOSITE =  "composite.";

	private Text nameText;
	private Combo categoryCombo;
	private Text descriptionText;
	private Text versionText;
	private Text venderText;
	private Combo activityTypeCombo;
	private Combo typeCombo;
	private Text maxInstanceText;
	private Combo executionTypeCombo;
	private Text executionRateText;
	private Text abstractText;
	private Button dataFlowBtn;
	private Button fsmBtn;
	private Button multiModeBtn;
	private Text rtcTypeText;

	private Button generateButton;
	private Button packageButton;

	private Button profileLoadButton;
	private Button profileSaveButton;

	private Composite generateSection;
	private Composite outputProjectSection;
	private Composite profileSection;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public BasicEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.BASIC_SECTION);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(final IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm, IMessageConstants.BASIC_SECTION);
		FormToolkit toolkit = managedForm.getToolkit();
		createModuleSection(toolkit, form);
		//
		getSite().setSelectionProvider(new ISelectionProvider() {
			public void addSelectionChangedListener(
				ISelectionChangedListener listener) {
			}
			public ISelection getSelection() {
				return new StructuredSelection(buildview);
			}
			public void removeSelectionChangedListener(
				ISelectionChangedListener listener) {
			}
			public void setSelection(ISelection selection) {
			}
		});
		//
		createHintSection(toolkit, form);
		createGenerateSection(toolkit, form);
		createExportImportSection(toolkit, form);
		//
		// 言語・環境ページより先にこのページが表示された場合、ここで言語を判断する
		editor.setEnabledInfoByLang();
		
		load();
	}

	private String getFileExtension(String filename){
		int index = filename.lastIndexOf(".");
		if( index > -1 ) return filename.substring(index + 1);
		return "";
	}
	
	private void switchPerspective() {

		for(RtcParam rtcParam : editor.getGeneratorParam().getRtcParams() ) {
			//Pluginの存在確認
			LanguageProperty langProp = LanguageProperty.checkPlugin(rtcParam);
			String currentPerspectiveId = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	            							.getActivePage().getPerspective().getId();
			if( langProp != null && !langProp.getPerspectiveId().equals(currentPerspectiveId) ) {
				MessageBox message = new MessageBox(getSite().getShell(), 
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				message.setText(IMessageConstants.BASIC_PERSPECTIVE_TEXT);
				message.setMessage(IMessageConstants.BASIC_PERSPECTIVE_MSG1 + langProp.getPerspectiveName() 
						+ IMessageConstants.BASIC_PERSPECTIVE_MSG2);
				if( message.open() == SWT.YES) {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(
							PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(
									langProp.getPerspectiveId()));
					}
			}
		}
	}

	/**
	 * バリデートを行う。エラーがない場合にはnullを返し、エラーがある場合にはメッセージを返す。
	 * 
	 * @return
	 */
	public String validateParam() {
		String result = null;
		//Module Name
		if (result == null && nameText.getText().length() == 0) {
			result = IMessageConstants.BASIC_VALIDATE_NAME1;
		}
		if( !StringUtil.checkDigitAlphabet(nameText.getText()) ) {
			result = IMessageConstants.BASIC_VALIDATE_NAME2;
		}
		//Module category
		if (result == null && categoryCombo.getText().length() == 0) {
			result = IMessageConstants.BASIC_VALIDATE_CATEGORY;
		}
		//Number of maximum instance
		String maxText = maxInstanceText.getText();
		if( maxText != null ) {
			Integer parseInt = null;
			try {
				parseInt = new Integer(Integer.parseInt(maxInstanceText.getText()));
			} catch (Exception e) {
				// void
			}
			if (parseInt == null) {
				result = IMessageConstants.BASIC_VALIDATE_MAXINST1;
			}
			if (parseInt != null && parseInt.intValue() < 0) {
				result = IMessageConstants.BASIC_VALIDATE_MAXINST2;
			}
		}
		//Execution Rate
		Double parseDbl = null;
		try {
			parseDbl = new Double(Double.parseDouble(executionRateText.getText()));
		} catch (Exception e) {
			// void
		}
		if (parseDbl == null) {
			result = IMessageConstants.BASIC_VALIDATE_ECRATE1;
		}
		if (parseDbl != null && parseDbl.intValue() < 0) {
			result = IMessageConstants.BASIC_VALIDATE_ECRATE2;
		}
		//Component Kind
		if( !dataFlowBtn.getSelection() && 
				!fsmBtn.getSelection() &&
				!multiModeBtn.getSelection() ) {
			result = "Please Select Component Kind.";
		}
		//Composite Component
		if( categoryCombo.getText().startsWith(CATEGORY_COMPOSITE) && 
				(editor.getRtcParam().getInports().size()>0 || editor.getRtcParam().getOutports().size()>0 ||
				 editor.getRtcParam().getServicePorts().size()>0) ) {
			result = "Cannot add any Ports to Composite Component.";
		}

		return result;
	}

	private void createModuleSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_COMPONENT_TITLE, IMessageConstants.BASIC_COMPONENT_EXPL, 2);
		//
		nameText = createLabelAndText(toolkit, composite, 
				IMessageConstants.REQUIRED + IMessageConstants.BASIC_LBL_MODULENAME, SWT.NONE, SWT.COLOR_RED);
		descriptionText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_DESCRIPTION);
		versionText = createLabelAndText(toolkit, composite,
				IMessageConstants.REQUIRED + IMessageConstants.BASIC_LBL_VERSION, SWT.NONE, SWT.COLOR_RED);
		venderText = createLabelAndText(toolkit, composite,
				IMessageConstants.REQUIRED + IMessageConstants.BASIC_LBL_VENDOR, SWT.NONE, SWT.COLOR_RED);
		String[] defaultCategory = {};
		categoryCombo = createEditableCombo(toolkit, composite,
				IMessageConstants.REQUIRED + IMessageConstants.BASIC_LBL_CATEGORY,
				CATEGORY_INDEX_KEY, defaultCategory, SWT.COLOR_RED);
		typeCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.BASIC_LBL_COMPONENT_TYPE,
				IRtcBuilderConstants.COMPONENT_TYPE_ITEMS);
		activityTypeCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.BASIC_LBL_ACTIVITY_TYPE,
				IRtcBuilderConstants.ACTIVITY_TYPE_ITEMS);
		//
		toolkit.createLabel(composite, IMessageConstants.BASIC_LBL_COMPONENT_KIND);
		Group compGroup = new Group(composite, SWT.NONE);
		compGroup.setLayout(new GridLayout(3, false));
		GridData gd = new GridData();
		compGroup.setLayoutData(gd);
		dataFlowBtn = createRadioCheckButton(toolkit, compGroup, "DataFlow", SWT.CHECK);
		fsmBtn = createRadioCheckButton(toolkit, compGroup, "FSM", SWT.CHECK);
		multiModeBtn = createRadioCheckButton(toolkit, compGroup, "MultiMode", SWT.CHECK);
		//
		maxInstanceText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_MAX_INSTANCES);
		executionTypeCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.BASIC_LBL_EXECUTION_TYPE,
				IRtcBuilderConstants.EXECUTIONCONTEXT_TYPE_ITEMS);
		executionRateText = createLabelAndText(toolkit,	composite, IMessageConstants.BASIC_LBL_EXECUTION_RATE);
		abstractText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_ABSTRACT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		gridData.widthHint = 100;
		abstractText.setLayoutData(gridData);
		rtcTypeText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_RTCTYPE);
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 4);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_MODULENAME_TITLE, IMessageConstants.BASIC_HINT_MODULENAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_DESCRIPTION_TITLE, IMessageConstants.BASIC_HINT_DESCRIPTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_VERSION_TITLE, IMessageConstants.BASIC_HINT_VERSION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_VENDOR_TITLE, IMessageConstants.BASIC_HINT_VENDOR_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_CATEGORY_TITLE, IMessageConstants.BASIC_HINT_CATEGORY_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_COMPTYPE_TITLE, IMessageConstants.BASIC_HINT_COMPTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_ACTIVITYTYPE_TITLE, IMessageConstants.BASIC_HINT_ACTIVITYTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_COMPKIND_TITLE, IMessageConstants.BASIC_HINT_COMPKIND_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_MAXINST_TITLE, IMessageConstants.BASIC_HINT_MAXINST_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_EXECUTIONTYPE_TITLE, IMessageConstants.BASIC_HINT_EXECUTIONTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_EXECUTIONRATE_TITLE, IMessageConstants.BASIC_HINT_EXECUTIONRATE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_ABSTRACT_TITLE, IMessageConstants.BASIC_HINT_ABSTRACT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_RTCTYPE_TITLE, IMessageConstants.BASIC_HINT_RTCTYPE_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_GENERATE_TITLE, IMessageConstants.BASIC_HINT_GENERATE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_PACKAGE_TITLE, IMessageConstants.BASIC_HINT_PACKAGE_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_IMPORT_TITLE, IMessageConstants.BASIC_HINT_IMPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_EXPORT_TITLE, IMessageConstants.BASIC_HINT_EXPORT_DESC, toolkit, composite);
	}

	private void createGenerateSection(FormToolkit toolkit, ScrolledForm form) {
//		generateSection = createSectionBaseWithLabel(toolkit, form, 
//				IMessageConstants.BASIC_GENERATE_TITLE, IMessageConstants.BASIC_GENERATE_EXPL, 2);
		generateSection = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_GENERATE_TITLE, IMessageConstants.BASIC_GENERATE_EXPL, 4);
		//
		createGenerateButton(toolkit);
		createPackageButton(toolkit);
	}

	private void createPackageButton(FormToolkit toolkit) {
		packageButton = toolkit.createButton(generateSection, IMessageConstants.BASIC_BTN_PACKAGING, SWT.NONE);
		packageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WizardDialog dialog = new WizardDialog(getSite().getShell(), new RtcExportWizard());
				dialog.open();
			}
		});
	}

	private void createGenerateButton(FormToolkit toolkit) {
		generateButton = toolkit.createButton(generateSection, IMessageConstants.BASIC_BTN_GENERATE, SWT.NONE);
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.allUpdates();
				String validateRtcParam = editor.validateParam();
				if (validateRtcParam != null) {
					MessageDialog.openError(getSite().getShell(), "Error", validateRtcParam);
					return;
				}
				//動的FSMの場合
				boolean isDynamicFSM = false;
				RtcParam rtcParam = editor.getRtcParam();
				PropertyParam fsm = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
				if(fsm!=null) {
					if(Boolean.valueOf(fsm.getValue())) {
						PropertyParam fsmType = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE);
						if(fsmType!=null && fsmType.getValue().equals(IRtcBuilderConstants.FSMTYTPE_DYNAMIC)) {
							isDynamicFSM = true;
						}
					}
				}
				//対象プロジェクトの確認
				IProject project = checkTargetProject(editor.getRtcParam().getOutputProject(), true);
				if( project==null) return;
				// 裏からファイルを削除されている可能性があるため、
				// プロジェクトとファイルシステムの同期を取る
				try {
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException e1) {
					throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
				}
				//
				editor.addDefaultComboValue();
				GuiRtcBuilder rtcBuilder = new GuiRtcBuilder();
				List<GenerateManager> managerList = RtcBuilderPlugin
						.getDefault().getLoader().getManagerList();
				if (managerList != null) {
					for (GenerateManager manager : managerList) {
						rtcBuilder.addGenerateManager(manager);
					}
				}
				GeneratorParam generatorParam = editor.getGeneratorParam();
				//TODO 複数コンポーネント対応版とする場合には複数設定
				generatorParam.getRtcParams().get(0).getServiceClassParams().clear();
				setPrefixSuffix(generatorParam.getRtcParams().get(0));
				if (rtcBuilder.doGenerateWrite(generatorParam, !isDynamicFSM)) {
					LanguageProperty langProp = LanguageProperty.checkPlugin(editor.getRtcParam());
					if(langProp != null) {
						try {
							IProjectDescription description = project.getDescription();
							String[] ids = description.getNatureIds();
							String[] newIds = new String[ids.length + langProp.getNatures().size()];
							System.arraycopy(ids, 0, newIds, 0, ids.length);
							for( int intIdx=0; intIdx<langProp.getNatures().size(); intIdx++ ) {
								newIds[ids.length+intIdx] = langProp.getNatures().get(intIdx);
							}
							description.setNatureIds(newIds);
							project.setDescription(description, null);
						} catch (CoreException e1) {
							LOGGER.error(
									"Fail to get/set description for project",
									e1);
						}
					}
					//
					saveRtcProfile(project);
					switchPerspective();
	        		editor.getRtcParam().resetUpdated();
	        		editor.updateDirty();
				}
				if(isDynamicFSM) {
					generateDynamicFSM();
					return;
				}
			}

			private void generateDynamicFSM() {
				RtcParam rtcParam = editor.getRtcParam();
				StateParam stateParam = rtcParam.getFsmParam();
				
				List<RtcParam> stateList = new ArrayList<RtcParam>();
				
				RtcParam stateRtc = createDefaultRTC(stateParam);
				stateList.add(stateRtc);
				for(StateParam subState : stateParam.getAllStateList()) {
					RtcParam subRtc = createDefaultRTC(subState);
					stateList.add(subRtc);
				}
				//
				editor.addDefaultComboValue();
				GuiRtcBuilder rtcBuilder = new GuiRtcBuilder();
				List<GenerateManager> managerList = RtcBuilderPlugin.getDefault().getLoader().getManagerList();
				if (managerList != null) {
					for (GenerateManager manager : managerList) {
						rtcBuilder.addGenerateManager(manager);
					}
				}
				GeneratorParam generatorParam = editor.getGeneratorParam();
				RtcParam orgRtc = generatorParam.getRtcParams().get(0);
				
				for(RtcParam targetFsm : stateList) {
					IProject project = checkTargetProject(targetFsm.getOutputProject(), false);
					if( project==null) continue;
					try {
						project.refreshLocal(IResource.DEPTH_INFINITE, null);
					} catch (CoreException e1) {
						throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
					}
					//
					targetFsm.getServiceClassParams().clear();
					setPrefixSuffix(targetFsm);
					generatorParam.getRtcParams().set(0, targetFsm);
					//
					if (rtcBuilder.doGenerateWrite(generatorParam, false)) {
						LanguageProperty langProp = LanguageProperty.checkPlugin(editor.getRtcParam());
						if(langProp != null) {
							try {
								IProjectDescription description = project.getDescription();
								String[] ids = description.getNatureIds();
								String[] newIds = new String[ids.length + langProp.getNatures().size()];
								System.arraycopy(ids, 0, newIds, 0, ids.length);
								for( int intIdx=0; intIdx<langProp.getNatures().size(); intIdx++ ) {
									newIds[ids.length+intIdx] = langProp.getNatures().get(intIdx);
								}
								description.setNatureIds(newIds);
								project.setDescription(description, null);
							} catch (CoreException e1) {
								LOGGER.error(
										"Fail to get/set description for project",
										e1);
							}
						}
					}
					saveRtcProfile(project);
				}
        		editor.getRtcParam().resetUpdated();
        		editor.updateDirty();
				generatorParam.getRtcParams().set(0, orgRtc);
				MessageDialog.openInformation(getSite().getShell(), "Information", "Generate success.");
			}

			private RtcParam createDefaultRTC(StateParam stateParam) {
				List<String> langList = new ArrayList<String>();
				List<String> langArgList = new ArrayList<String>();
				String rtmVersion = IRtcBuilderConstants.RTM_VERSION_100;
				langList.add(IRtcBuilderConstants.LANG_CPP);
				langArgList.add(IRtcBuilderConstants.LANG_CPP_ARG);
				
				RtcParam targetRtc = new RtcParam(null, false);
				targetRtc.setName(stateParam.getName());
				targetRtc.setSchemaVersion(IRtcBuilderConstants.SCHEMA_VERSION);
				targetRtc.setDescription(ComponentPreferenceManager.getInstance().getBasic_Description());
				targetRtc.setCategory(ComponentPreferenceManager.getInstance().getBasic_Category());
				targetRtc.setVersion(ComponentPreferenceManager.getInstance().getBasic_Version());
				targetRtc.setVender(ComponentPreferenceManager.getInstance().getBasic_VendorName());
				targetRtc.setComponentType(ComponentPreferenceManager.getInstance().getBasic_ComponentType());
				targetRtc.setActivityType(ComponentPreferenceManager.getInstance().getBasic_ActivityType());
				targetRtc.setComponentKind(ComponentPreferenceManager.getInstance().getBasic_ComponentKind());
				targetRtc.setMaxInstance(ComponentPreferenceManager.getInstance().getBasic_MaxInstances());
				targetRtc.setExecutionType(ComponentPreferenceManager.getInstance().getBasic_ExecutionType());
				targetRtc.setExecutionRate(ComponentPreferenceManager.getInstance().getBasic_ExecutionRate());
				ArrayList<String> docs = DocumentPreferenceManager.getDocumentValue();
				for( int intidx=IRtcBuilderConstants.ACTIVITY_INITIALIZE; intidx<IRtcBuilderConstants.ACTIVITY_DUMMY; intidx++) {
					targetRtc.setActionImplemented(intidx, docs.get(intidx));
				}
				targetRtc.setDocLicense(DocumentPreferenceManager.getLicenseValue());
				targetRtc.setDocCreator(DocumentPreferenceManager.getCreatorValue());
				//
				targetRtc.setOutputProject(stateParam.getName());
				targetRtc.getLangList().addAll(langList);
				targetRtc.getLangArgList().addAll(langArgList);
				targetRtc.setRtmVersion(rtmVersion);
				return targetRtc;
			}

			// Profileを保存
			private void saveRtcProfile(IProject project) {
				ProfileHandler handler = new ProfileHandler();
				try {
					ExportCreator export = new ExportCreator();
					export.preExport(editor);
					//
					List<PropertyParam> properties = editor.getRtcParam().getProperties();
					PropertyParam fsmTarget = null;
					for(PropertyParam param : properties) {
						if( param.getName().equals("FSMPath")) {
							fsmTarget = param;
							break;
						}
					}
					if(fsmTarget!=null) {
						String orgPath = fsmTarget.getValue();
						IFile orgFsmFile  = project.getFile(orgPath);
						String contents = "";
						IFile fsmFile  = null;
						if(orgFsmFile.exists()) {
							contents = FileUtil.readFile(orgFsmFile.getRawLocation().toOSString());
							fsmFile = orgFsmFile;
						} else {
							contents = FileUtil.readFile(orgPath);
							String fileName = new File(orgPath).getName();
							fsmFile  = project.getFile(fileName);
						}
						if(fsmFile.exists()) {
							fsmFile.delete(true, null);
						}
						fsmFile.create(new ByteArrayInputStream(contents.getBytes("UTF-8")), true, null);
						fsmTarget.setValue(fsmFile.getName());
					}
					//
					String strXml = handler.convert2XML(editor.getGeneratorParam());

					IFile orgRtcxml = project.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML);
					if (orgRtcxml.exists()) {
						IFile renameFile = project.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML + DATE_FORMAT.format(new GregorianCalendar().getTime()) );
						orgRtcxml.move(renameFile.getFullPath(), true, null);
						//バックアップ最大数以上のファイルは削除
						FileUtil.removeBackupFiles(project, IRtcBuilderConstants.DEFAULT_RTC_XML);
					}
					//
					IFile saveRtcxml = project.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML);
					saveRtcxml.create(new ByteArrayInputStream(strXml.getBytes("UTF-8")), true, null);
					//
					editor.getRtcParam().resetUpdated();
					editor.updateDirty();
				} catch (Exception e) {
					LOGGER.error("Fail to save rtc-profile", e);
				}
			}

			private IProject checkTargetProject(String targetProject, boolean isConfirmNew) {
				if( targetProject==null || "".equals(targetProject) ){
					MessageDialog.openError(getSite().getShell(), "Error", IRTCBMessageConstants.VALIDATE_ERROR_OUTPUTPROJECT);
					return null;
				}
				IWorkspaceRoot workspaceHandle = ResourcesPlugin.getWorkspace().getRoot();
				IProject project = workspaceHandle.getProject(targetProject);
				if(!project.exists()) {
					if(isConfirmNew) {
						IWorkbench workbench = PlatformUI.getWorkbench();
						IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
						Shell shell = window.getShell();
						MessageBox message = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						message.setText(IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE_TITLE);
						message.setMessage(IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE);
						if( message.open() != SWT.YES) return null;
					}
					try {
						project.create(null);
						project.open(null);
						LanguageProperty langProp = LanguageProperty.checkPlugin(editor.getRtcParam());
						if(langProp != null) {
							IProjectDescription description = project.getDescription();
							String[] ids = description.getNatureIds();
							String[] newIds = new String[ids.length + langProp.getNatures().size()];
							System.arraycopy(ids, 0, newIds, 0, ids.length);
							for( int intIdx=0; intIdx<langProp.getNatures().size(); intIdx++ ) {
								newIds[ids.length+intIdx] = langProp.getNatures().get(intIdx);
							}
							description.setNatureIds(newIds);
							project.setDescription(description, null);
						}
					} catch (CoreException ex) {
						throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
					}
				}
				return project;
			}
		});
	}
	
	private void setPrefixSuffix(RtcParam param) {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		param.setCommonPrefix(ComponentPreferenceManager.getInstance().getBasic_Prefix());
		param.setCommonSuffix(store.getString(ComponentPreferenceManager.Generate_Basic_Suffix));
		param.setConfigurationPrefix(store.getString(ComponentPreferenceManager.Generate_Configuration_Prefix));
		param.setConfigurationSuffix(store.getString(ComponentPreferenceManager.Generate_Configuration_Suffix));
		//
		param.setDataPortPrefix(store.getString(ComponentPreferenceManager.Generate_DataPort_Prefix));
		param.setDataPortSuffix(store.getString(ComponentPreferenceManager.Generate_DataPort_Type));
		param.setServicePortPrefix(store.getString(ComponentPreferenceManager.Generate_ServicePort_Suffix));
		param.setServicePortSuffix(store.getString(ComponentPreferenceManager.Generate_ServicePort_Prefix));
		param.setServiceIFPrefix(store.getString(ComponentPreferenceManager.Generate_ServiceIF_Prefix));
		param.setServiceIFSuffix(store.getString(ComponentPreferenceManager.Generate_ServiceIF_Suffix));
	}

	@SuppressWarnings("unchecked")
	private ImportExtension getTargetImportExtension() {
		List list = RtcBuilderPlugin.getDefault().getImportExtensionLoader().getList();
		if( list != null ) {
			String targetLang = editor.getRtcParam().getLanguage();
			for( Iterator iter = list.iterator(); iter.hasNext(); ) {
				ImportExtension extension = (ImportExtension) iter.next();
				if( extension.getManagerKey().equals(targetLang) ){
					return extension;
				}
			}
		}
		return null;
	}
	
	private void createExportImportSection(FormToolkit toolkit, ScrolledForm form) {
		profileSection = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_EXPORT_IMPORT_TITLE, IMessageConstants.BASIC_EXPORT_IMPORT_EXPL, 2);
		createProfileLoadButton(toolkit);
		createProfileSaveButton(toolkit);
	}

	private void createProfileSaveButton(FormToolkit toolkit) {
		profileSaveButton = toolkit.createButton(profileSection, IMessageConstants.BASIC_BTN_EXPORT, SWT.NONE);
		profileSaveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.allUpdates();
				String validateRtcParam = editor.validateParam();
				if (validateRtcParam != null) {
					MessageDialog.openError(getSite().getShell(), "Error", validateRtcParam);
					return;
				}

				String selectedFileName; 
        		ExportCreator export = new ExportCreator();
        		if(!export.canCreateProfileName(editor)) {
        			FileDialog dialog = new FileDialog(getSite().getShell(),SWT.SAVE);
    		        dialog.setText(IMessageConstants.BASIC_BTN_EXPORT);
    				dialog.setFilterNames(new String[] {IMessageConstants.FILETYPE_XML,IMessageConstants.FILETYPE_YAML});
    				dialog.setFilterExtensions(new String[] { "*.xml","*.yaml" });
    				selectedFileName = dialog.open();
        		} else {
        			selectedFileName = export.createProfileName(editor);
        		}
        		
		        if (selectedFileName != null) {
		        	try {
		        		export.preExport(editor);

		            	if (getFileExtension(selectedFileName).equals(IRtcBuilderConstants.YAML_EXTENSION)) {
		            		ProfileHandler handler = new ProfileHandler();
		            		handler.createYaml(selectedFileName, editor.getGeneratorParam());
		            	} else {
		            		ProfileHandler handler = new ProfileHandler();
		            		try {
		        				handler.validateXml(handler.convert2XML(editor.getGeneratorParam()));
		        			} catch (JAXBException ex) {
		            			if (!MessageDialog.openQuestion(getSite().getShell(),ex.getMessage(),
		            					IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE + System.getProperty("line.separator") + ex.getCause().toString()) )
		            				return ;// 「いいえ」のときは保存しない
		            		}// 通常のExceptionは外側でcatchする
		        			handler.storeToXML(selectedFileName, editor.getGeneratorParam());
		            	}
		            	//FSM
		        		PropertyParam fsm = editor.getRtcParam().getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
		        		if(fsm!=null) {
			        		if(Boolean.valueOf(fsm.getValue())) {
			    				String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
			    				IWorkspace workspace = ResourcesPlugin.getWorkspace();
			    				IWorkspaceRoot root = workspace.getRoot();
			    				IProject project = root.getProject(editor.getRtcParam().getOutputProject());
			    				String fsmFile  = project.getFile(cmpName).getLocation().toOSString();
			        			if(fsmFile!=null) {
			        				String dirName = new File(selectedFileName).getParent();
			        				String targetFile = dirName + File.separator + cmpName;
			        				Path inputPath = FileSystems.getDefault().getPath(fsmFile);
			        				Path outputPath = FileSystems.getDefault().getPath(targetFile);			        				
			        				Files.copy(inputPath, outputPath);
			        			}
			        		}
		        		}
		        		export.postExport(selectedFileName, editor);
		        		editor.getRtcParam().resetUpdated();
		        		editor.updateDirty();
		            	
					} catch (Exception e1) {
						String msg = e1.getMessage();
						if (msg == null || msg.equals("")) {
							msg = IMessageConstants.BASIC_EXPORT_ERROR;
						}
						MessageDialog.openError(getSite().getShell(), "Error", msg);
						return;
					}
					MessageDialog.openInformation(getSite().getShell(), "Finish", IMessageConstants.BASIC_EXPORT_DONE);
		        }
			}
		});
	}

	private void createProfileLoadButton(FormToolkit toolkit) {
		profileLoadButton = toolkit.createButton(profileSection, IMessageConstants.BASIC_BTN_IMPORT, SWT.NONE);
		profileLoadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportExtension extension = getTargetImportExtension();
				FileDialog dialog = new FileDialog(getSite().getShell(),SWT.OPEN);
		        dialog.setText(IMessageConstants.BASIC_BTN_IMPORT);
		        
				String[] names = extension == null ? new String[] { IMessageConstants.FILETYPE_XML,IMessageConstants.FILETYPE_YAML }
				  					: extension.getFileDialogFilterNames();
				String[] exts = extension == null ? new String[] { "*.xml","*.yaml" }
				 					: extension.getFileDialogFilterExtensions();
				dialog.setFilterNames(names);
				dialog.setFilterExtensions(exts);

				String selectedFileName = dialog.open();
		        if (selectedFileName != null) {
		        	if(extension == null) {
			        	try {
			        		String origProject = editor.getRtcParam().getOutputProject();
			        		ProfileHandler handler = new ProfileHandler();
				        	if (getFileExtension(selectedFileName).equals(IRtcBuilderConstants.YAML_EXTENSION)) {
				        		GeneratorParam genParam = handler.readYaml(selectedFileName);
								String xmlFile = handler.convert2XML(genParam);
								editor.setGeneratorParam(genParam);
								editor.getRtcParam().setRtcXml(xmlFile);
				        	} else {
								GeneratorParam genParam = handler.restorefromXMLFile(selectedFileName);
								editor.setGeneratorParam(genParam);
							}
							editor.getRtcParam().setOutputProject(origProject);
						} catch (Exception e1) {
							MessageDialog.openError(getSite().getShell(), "Error", IMessageConstants.BASIC_IMPORT_ERROR);
							return;
						}
		        	} else {
			        	try {
		        			extension.setImportData(selectedFileName,editor);
						} catch (Exception e1) {
							String msg = e1.getMessage();
							if (msg == null || msg.equals("")) {
								msg = IMessageConstants.BASIC_IMPORT_ERROR;
							}
							MessageDialog.openError(getSite().getShell(), "Error", msg);
							return;
						}
		        	}
		        	//FSM
	        		PropertyParam fsm = editor.getRtcParam().getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
	        		if(fsm!=null) {
		        		if(Boolean.valueOf(fsm.getValue())) {
		    				FileDialog dialogFSM = new FileDialog(getSite().getShell(),SWT.OPEN);
		    				dialogFSM.setText("FSM Import");
		    		        
		    				//TODO 多言語化
		    				String[] namesFSM = new String[] { "SCXMLファイル", "XMLファイル" };
		    				String[] extsFSM = new String[] { "*.scxml","*.xml" };
		    				dialogFSM.setFilterNames(namesFSM);
		    				dialogFSM.setFilterExtensions(extsFSM);
		    				String selectedFileNameFSM = dialogFSM.open();
		    				if(selectedFileNameFSM == null) {
		    					fsm.setValue("false");
		    					editor.getRtcParam().deleteFSMPort();
		    					
		    				} else {
			    				String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
			    				IWorkspace workspace = ResourcesPlugin.getWorkspace();
			    				IWorkspaceRoot root = workspace.getRoot();
			    				IProject project = root.getProject(editor.getRtcParam().getOutputProject());
			    				String fsmFile  = project.getLocation().toOSString() + File.separator + cmpName;
		        				try {
			        				Path inputPath = FileSystems.getDefault().getPath(selectedFileNameFSM);
			        				Path outputPath = FileSystems.getDefault().getPath(fsmFile);			        				
									Files.copy(inputPath, outputPath);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
		    				}
		        		}
	        		}
		        	
					MessageDialog.openInformation(getSite().getShell(), "Finish",	IMessageConstants.BASIC_IMPORT_DONE);
					//
					editor.allPagesReLoad();
					editor.updateEMFModuleName(editor.getRtcParam().getName());
					editor.updateEMFDataPorts(
							editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
							editor.getRtcParam().getServicePorts());
					editor.setEnabledInfoByLang();
					extractDataTypes();
					load();
					//
//					editor.getRtcParam().resetUpdated();
					editor.updateDirty();
		        }
			}
		});
	}

	protected void update() {
		RtcParam rtcParam = editor.getRtcParam();

		rtcParam.setName(getText(nameText.getText()));
		rtcParam.setDescription(getText(descriptionText.getText()));
		rtcParam.setVersion(getText(versionText.getText()));
		rtcParam.setVender(getText(venderText.getText()));
		rtcParam.setCategory(getText(categoryCombo.getText()));
		rtcParam.setActivityType(getText(activityTypeCombo.getText()));
		rtcParam.setComponentType(getText(typeCombo.getText()));
		rtcParam.setComponentKind(getSelectedCompKind());
		rtcParam.setAbstract(getText(abstractText.getText()));
		rtcParam.setRtcType(getText(rtcTypeText.getText()));

		try {
			int maxInstance = Integer.parseInt(getText(maxInstanceText
					.getText()));
			rtcParam.setMaxInstance(maxInstance);
		} catch (Exception e) {
			// 例外の場合、画面の値を現在の値に戻す
//			maxInstanceText.setText(String.valueOf(rtcParam.getMaxInstance()));
		}

		rtcParam.setExecutionType(getText(executionTypeCombo.getText()));
		try {
			double exec_rate = Double.parseDouble(getText(executionRateText
					.getText()));
			rtcParam.setExecutionRate(exec_rate);
		} catch (Exception e) {
			// 例外の場合、画面の値を現在の値に戻す
			//executionRateText.setText(String.valueOf(rtcParam.getExecutionRate()));
		}

		editor.updateEMFModuleName(getText(nameText.getText()));
		editor.updateDirty();
	}

	/**
	 * データをロードする
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();

		nameText.setText(getValue(rtcParam.getName()));
		descriptionText.setText(getValue(rtcParam.getDescription()));
		versionText.setText(getValue(rtcParam.getVersion()));
		venderText.setText(getValue(rtcParam.getVender()));
		categoryCombo.setText(getValue(rtcParam.getCategory()));
		loadSelectedCompKind(rtcParam.getComponentKind());
		activityTypeCombo.setText(getValue(rtcParam.getActivityType()));
		typeCombo.setText(getValue(rtcParam.getComponentType()));
		maxInstanceText.setText(getValue(String.valueOf(rtcParam.getMaxInstance())));
		executionTypeCombo.setText(getValue(rtcParam.getExecutionType()));
		executionRateText.setText(getValue(String.valueOf(rtcParam.getExecutionRate())));
		abstractText.setText(getValue(rtcParam.getAbstract()));
		rtcTypeText.setText(getValue(rtcParam.getRtcType()));
		//
		editor.updateEMFModuleName(rtcParam.getName());
	}

	protected void addDefaultComboValue(){
		addDefaultComboValue(categoryCombo, CATEGORY_INDEX_KEY);
	}
	
	private void loadSelectedCompKind(String type) {
		if( type.contains("DataFlow") )           dataFlowBtn.setSelection(true);
		if( type.contains("FiniteStateMachine") ) fsmBtn.setSelection(true);
		if( type.contains("MultiMode") )          multiModeBtn.setSelection(true);
	}
	private String getSelectedCompKind() {
		StringBuffer result = new StringBuffer();
		
		if(dataFlowBtn.getSelection())  result.append("DataFlow");
		if(fsmBtn.getSelection())       result.append("FiniteStateMachine");
		if(multiModeBtn.getSelection()) result.append("MultiMode");
		if( result.length() > 0 ) result.append("Component");
		
		return result.toString();
	}

	@Override
	protected Text createLabelAndText(FormToolkit toolkit, Composite composite, String labelString) {
		Text text = super.createLabelAndText(toolkit, composite, labelString);
		GridData gd = (GridData)text.getLayoutData();
		gd.widthHint = 100;
		return text;
	}

	@Override
	protected Combo createEditableCombo(FormToolkit toolkit, Composite composite, String labelString, String key, String[] defaultValue, int color) {
		Combo combo = super.createEditableCombo(toolkit, composite, labelString, key, defaultValue, color); 
		GridData gd = (GridData)combo.getLayoutData();
		gd.widthHint = 100;
		return combo;
	}

	/**
	 * BasicInfoフォーム内の要素の有効/無効を設定します。
	 * <ul>
	 * <li>basic.info.moduleName : BasicInfoセクションの Module name</li>
	 * <li>basic.info.moduleDescription : BasicInfoセクションの Module description</li>
	 * <li>basic.info.moduleVersion : BasicInfoセクションの Module version</li>
	 * <li>basic.info.moduleVendor : BasicInfoセクションの Module vendor</li>
	 * <li>basic.info.moduleCategory : BasicInfoセクションの Module category</li>
	 * <li>basic.info.componentType : BasicInfoセクションの Component type</li>
	 * <li>basic.info.activityType : BasicInfoセクションの Component's activity type</li>
	 * <li>basic.info.dataFlow : BasicInfoセクションの Component kind の DataFlow</li>
	 * <li>basic.info.fsm : BasicInfoセクションの Component kind の FSM</li>
	 * <li>basic.info.multiMode : BasicInfoセクションの Component kind の MultiMode</li>
	 * <li>basic.info.maxInstances : BasicInfoセクションの maximum instances</li>
	 * <li>basic.info.executionType : BasicInfoセクションの Execution type</li>
	 * <li>basic.info.executionRate : BasicInfoセクションの Execution rate</li>
	 * <li>basic.info.abstract : BasicInfoセクションの Abstract</li>
	 * <li>basic.info.rtcType : BasicInfoセクションの RTC type</li>
	 * <li>basic.outputProject.* : OutputProjectセクション全体</li>
	 * <li>basic.generate.code : Generateセクションのコード生成ボタン</li>
	 * <li>basic.generate.package : Generateセクションのパッケージ化ボタン</li>
	 * <li>basic.profile.import : Profileセクションのインポートボタン</li>
	 * <li>basic.profile.export : Profileセクションのエクスポートボタン</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("info")) {
			if (nameText != null) {
				if (widgetInfo.matchWidget("moduleName"))        setControlEnabled(nameText, enabled);
				if (widgetInfo.matchWidget("moduleDescription")) setControlEnabled(descriptionText, enabled);
				if (widgetInfo.matchWidget("moduleVersion"))     setControlEnabled(versionText, enabled);
				if (widgetInfo.matchWidget("moduleVendor"))      setControlEnabled(venderText, enabled);
				if (widgetInfo.matchWidget("moduleCategory"))    setControlEnabled(categoryCombo, enabled);
				if (widgetInfo.matchWidget("componentType"))     setControlEnabled(typeCombo, enabled);
				if (widgetInfo.matchWidget("activityType"))      setControlEnabled(activityTypeCombo, enabled);
				if (widgetInfo.matchWidget("dataFlow"))          setControlEnabled(dataFlowBtn, enabled);
				if (widgetInfo.matchWidget("fsm"))               setControlEnabled(fsmBtn, enabled);
				if (widgetInfo.matchWidget("multiMode"))         setControlEnabled(multiModeBtn, enabled);
				if (widgetInfo.matchWidget("maxInstances"))      setControlEnabled(maxInstanceText, enabled);
				if (widgetInfo.matchWidget("executionType"))     setControlEnabled(executionTypeCombo, enabled);
				if (widgetInfo.matchWidget("executionRate"))     setControlEnabled(executionRateText, enabled);
				if (widgetInfo.matchWidget("abstract"))          setControlEnabled(abstractText, enabled);
				if (widgetInfo.matchWidget("rtcType"))           setControlEnabled(rtcTypeText, enabled);
			}
		}
		if (widgetInfo.matchSection("outputProject")) {
			if (outputProjectSection != null) {
				outputProjectSection.setEnabled(enabled);
				setEnableBackground(outputProjectSection, getBackgroundByEnabled(enabled));
			}
		}
		if (widgetInfo.matchSection("generate")) {
			if (generateSection != null) {
				if (widgetInfo.matchWidget("code"))    setButtonEnabled(generateButton, enabled);
				if (widgetInfo.matchWidget("package")) setButtonEnabled(packageButton, enabled);
				boolean genEnable = false;
				if (generateButton.getEnabled() || packageButton.getEnabled()) {
					genEnable = true;
				}
				generateSection.setEnabled(genEnable);
				setEnableBackground(generateSection, getBackgroundByEnabled(genEnable));
			}
		}
		if (widgetInfo.matchSection("profile")) {
			if (profileSection != null) {
				if (widgetInfo.matchWidget("import")) setButtonEnabled(profileLoadButton, enabled);
				if (widgetInfo.matchWidget("export")) setButtonEnabled(profileSaveButton, enabled);
				boolean profEnable = false;
				if (profileLoadButton.getEnabled() || profileSaveButton.getEnabled()) {
					profEnable = true;
				}
				profileSection.setEnabled(profEnable);
				setEnableBackground(profileSection,	getBackgroundByEnabled(profEnable));
			}
		}
	}
}
