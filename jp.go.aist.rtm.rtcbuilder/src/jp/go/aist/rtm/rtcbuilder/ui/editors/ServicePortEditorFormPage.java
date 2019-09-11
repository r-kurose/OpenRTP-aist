package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.ManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.ParseException;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlPathParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.RTCBuilderPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;
import jp.go.aist.rtm.rtcbuilder.util.ValidationUtil;

/**
 * ServicePortページ
 */
public class ServicePortEditorFormPage extends AbstractEditorFormPage {

	private static final String IDL_EXTENTION = "idl";

	private Section servicePortMasterBlockSection;
	private TreeViewer servicePortViewer;
	private Button addButton;
	private Button addinterfaceButton;
	private Button deleteButton;

	//
	private Text nameText;
	private Combo positionCombo;
	//
	private Text descriptionText;
	private Text ifoverviewText;
	//
	private Text interfaceNameText;
	private Combo directionCombo;
	private Text instanceNameText;
	private Text varNameText;
	private Text idlFileText;
	private Combo interfaceTypeCombo;
	private Text idlPathText;
	//
	private Text ifdetailText;
	private Text ifargumentText;
	private Text ifreturnText;
	private Text ifexceptionText;
	private Text ifpreconditionText;
	private Text ifpostconditionText;
	//
	private String defaultPortName;
	//
	private String defaultIFName;
	private String defaultIFInstanceName;
	private String defaultIFVarName;

    private List<ServiceClassParam> defaultIFList = new ArrayList<ServiceClassParam>();
    private List<ServiceClassParam> currentIFList = new ArrayList<ServiceClassParam>();

	/**
	 * コンストラクタ
	 *
	 * @param editor
	 *            親のエディタ
	 */
	public ServicePortEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.SERVICEPORT_SECTION);
		updateDefaultValue();
	}

	private void updateDefaultValue() {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		defaultPortName = ComponentPreferenceManager.getInstance().getServicePort_Name();

		defaultIFName = ComponentPreferenceManager.getInstance().getServiceIF_Name();
		defaultIFInstanceName = store.getString(ComponentPreferenceManager.Generate_ServiceIF_InstanceName);
		defaultIFVarName = store.getString(ComponentPreferenceManager.Generate_ServiceIF_VarName);

        extractServiceInterface();
    }

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();

		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		managedForm.getForm().getBody().setLayout(gl);
		managedForm.getForm().setShowFocusedControl(true);

		Composite composite = toolkit.createComposite(managedForm.getForm().getBody(), SWT.NULL);
		gl = new GridLayout(2, true);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		//
		ScrolledForm form = toolkit.createScrolledForm(composite);
		gd = new GridData(GridData.FILL_BOTH);
		form.setLayoutData(gd);
		ManagedForm mform = new ManagedForm(toolkit, form);

		createServiceBase(mform);
		ServicePortMasterBlock block = new ServicePortMasterBlock();
		block.createContent(mform);

		Composite compositeHint = toolkit.createComposite(composite, SWT.NULL);
		gl = new GridLayout();
		gl.numColumns = 1;
		compositeHint.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		compositeHint.setLayoutData(gd);

		createHintSectionPort(toolkit, compositeHint);
		createHintSectionInterface(toolkit, compositeHint);

		// 言語・環境ページより先にこのページが表示された場合、ここで言語を判断する
		editor.setEnabledInfoByLang();

		load();

		// 初期状態に応じて、ボタンの活性状態を決定する
		// この処理は、RTC.xml読み込みに依存するため、load()より後である必要がある。
		setButtonEnabled(servicePortViewer.getSelection());
	}

	private void createServiceBase(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		Composite composite = toolkit.createComposite(managedForm.getForm().getBody(), SWT.NULL);
		GridLayout gl = new GridLayout(2, true);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(gd);

		Label title = toolkit.createLabel(composite, IMessageConstants.SERVICEPORT_SECTION);
		if( titleFont==null ) {
			titleFont = new Font(managedForm.getForm().getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		title.setFont(titleFont);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		title.setLayoutData(gd);
	}

	private void createHintSectionPort(FormToolkit toolkit, Composite parent) {
		Composite composite = createHintSectionComp(toolkit, parent, IMessageConstants.SERVIVEPORT_HINT_PORT_TITLE);
		//
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_PORTNAME, IMessageConstants.SERVIVEPORT_HINT_PORT_NAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_POSITION, IMessageConstants.SERVIVEPORT_HINT_PORT_POSITION_DESC, toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_DESCRIPTION, IMessageConstants.SERVIVEPORT_HINT_PORT_DESCRIPTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFDESCRIPTION, IMessageConstants.SERVIVEPORT_HINT_PORT_IFDESCRIPTION_DESC, toolkit, composite);
	}

	private void createHintSectionInterface(FormToolkit toolkit, Composite parent) {
		Composite composite = createHintSectionComp(toolkit, parent, IMessageConstants.SERVIVEPORT_HINT_INTERFACE_TITLE);
		//
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFNAME, IMessageConstants.SERVIVEPORT_HINT_IF_NAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFDIRECTION, IMessageConstants.SERVIVEPORT_HINT_IF_DIRECTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFINSTNAME, IMessageConstants.SERVIVEPORT_HINT_IF_INSTANCE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFVARNAME, IMessageConstants.SERVIVEPORT_HINT_IF_VARNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IDLFILE, IMessageConstants.SERVIVEPORT_HINT_IDLFILE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFTYPE, IMessageConstants.SERVIVEPORT_HINT_IFTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IDLPATH, IMessageConstants.SERVIVEPORT_HINT_ILDPATH_DESC, toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_IFDESCRIPTION, IMessageConstants.SERVIVEPORT_HINT_IFDESC_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_ARGUMENT, IMessageConstants.SERVIVEPORT_HINT_ARGUMENT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_RETURN, IMessageConstants.SERVIVEPORT_HINT_RETURN_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_EXCEPTION, IMessageConstants.SERVIVEPORT_HINT_EXCEPTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_PRE_CONDITION, IMessageConstants.SERVIVEPORT_HINT_PRE_CONDITION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.SERVICEPORT_LBL_POST_CONDITION, IMessageConstants.SERVIVEPORT_HINT_POST_CONDITION_DESC, toolkit, composite);
	}

	private Composite createHintSectionComp(FormToolkit toolkit, Composite form, String title) {
		Section sctHint = toolkit.createSection(form, Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		sctHint.setText(title);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		sctHint.setLayoutData(gd);
		//
		Composite composite = toolkit.createComposite(sctHint, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		GridLayout gl = new GridLayout(2, false);
		composite.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		sctHint.setClient(composite);
		return composite;
	}

	private void setButtonEnabled(ISelection selection){
		if( selection!=null && !selection.isEmpty() ){
			if( addinterfaceButton!=null ) addinterfaceButton.setEnabled(true);
			if( deleteButton!=null ) deleteButton.setEnabled(true);
		}else{
			if( addinterfaceButton!=null ) addinterfaceButton.setEnabled(false);
			if( deleteButton!=null ) deleteButton.setEnabled(false);
		}
	}


	public void update() {
        updateIDLFile();

		if(servicePortViewer != null ) {
			servicePortViewer.getTree().setRedraw(false);
			TreeItem[] selections = servicePortViewer.getTree().getSelection();
			if( selections.length > 0 ) {
				TreeItem selection = selections[0];
				if( selection.getData() instanceof ServicePortParam ) {
					if( nameText.getText()==null || nameText.getText().length()==0 ) {
						((ServicePortParam)selection.getData()).setName(" ");
					} else {
						((ServicePortParam)selection.getData()).setName(getText(nameText.getText()));
					}
					((ServicePortParam)selection.getData()).setPositionByIndex(positionCombo.getSelectionIndex());
					//
					((ServicePortParam)selection.getData()).setDocDescription(StringUtil.getDocText(descriptionText.getText()));
					((ServicePortParam)selection.getData()).setDocIfDescription(StringUtil.getDocText(ifoverviewText.getText()));

				} else if( selection.getData() instanceof ServicePortInterfaceParam ) {
					if( !((ServicePortInterfaceParam)selection.getData()).getIdlFile().equals(
							idlFileText.getText()) ) {
						if(idlFileText.getText()!=null && idlFileText.getText().length()>0) {
							try {
								String targetContent = PreProcessor.parseAlltoSpace(FileUtil.readFile(idlFileText.getText()));
								IDLParser parser = new IDLParser(new StringReader(targetContent));
								List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(parser.specification(), "");
                                if( serviceClassParams!=null && serviceClassParams.size()>0 ) {
                                    int selected = interfaceTypeCombo.getSelectionIndex();
                                    interfaceTypeCombo.removeAll();
                                    currentIFList.clear();
                                    for(ServiceClassParam target : defaultIFList) {
                                        interfaceTypeCombo.add(target.getName());
                                        currentIFList.add(target);
                                    }
                                    for(ServiceClassParam target : serviceClassParams) {
                                        interfaceTypeCombo.add(target.getName());
                                        target.setIdlFile(idlFileText.getText());
                                        currentIFList.add(target);
                                    }
                                    if(0<=selected) {
                                        interfaceTypeCombo.select(selected);
                                    }
                                }
                            } catch (Exception e) {
								MessageDialog.openError(getSite().getShell(), "Error",
										IMessageConstants.PREF_IDLPARSE_ERROR + System.getProperty( "line.separator" ) + System.getProperty( "line.separator" ) +
										e.getMessage() );
								String selected = interfaceTypeCombo.getText();
								interfaceTypeCombo.removeAll();
								interfaceTypeCombo.setText(selected);
								servicePortViewer.getTree().setRedraw(true);
								return;
							}
						}
					}
					((ServicePortInterfaceParam)selection.getData()).setName(interfaceNameText.getText());
					((ServicePortInterfaceParam)selection.getData()).setIndex(directionCombo.getSelectionIndex());
					((ServicePortInterfaceParam)selection.getData()).setInstanceName(instanceNameText.getText());
					((ServicePortInterfaceParam)selection.getData()).setVarName(varNameText.getText());
                    int selected = interfaceTypeCombo.getSelectionIndex();
                    if(0<=selected) {
                        ServiceClassParam selectedIF = currentIFList.get(selected);
                        ((ServicePortInterfaceParam)selection.getData()).setIdlFile(selectedIF.getIdlFile());
                    } else {
                        ((ServicePortInterfaceParam)selection.getData()).setIdlFile(idlFileText.getText());
                    }
					((ServicePortInterfaceParam)selection.getData()).setInterfaceType(interfaceTypeCombo.getText());
					((ServicePortInterfaceParam)selection.getData()).setIdlSearchPath(idlPathText.getText());
					//
					((ServicePortInterfaceParam)selection.getData()).setDocDescription(StringUtil.getDocText(ifdetailText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocArgument(StringUtil.getDocText(ifargumentText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocReturn(StringUtil.getDocText(ifreturnText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocException(StringUtil.getDocText(ifexceptionText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocPreCondition(StringUtil.getDocText(ifpreconditionText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocPostCondition(StringUtil.getDocText(ifpostconditionText.getText()));
					//
				}
			}
			Object[] expanded = servicePortViewer.getExpandedElements();
			servicePortViewer.setInput(editor.getRtcParam().getServicePorts());
			servicePortViewer.getTree().setRedraw(true);
			servicePortViewer.setExpandedElements(expanded);
			//
			editor.updateEMFDataPorts(
					editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
					editor.getRtcParam().getEventports(), editor.getRtcParam().getServicePorts());
			editor.updateDirty();
		}
	}

    private void updateIDLFile() {
        if(idlFileText !=null ) {
            String localIDL = idlFileText.getText();
            if(localIDL!=null && localIDL.isEmpty()==false) {
                String FS = System.getProperty("file.separator");
                RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(RTCBuilderPreferenceManager.HOME_DIRECTORY, "");
                String userHome = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(RTCBuilderPreferenceManager.HOME_DIRECTORY);
                String userDir = userHome + FS + "idl";

                Path sourcePath = Paths.get(localIDL);
                File targetFile = new File(userDir + FS + sourcePath.getFileName());
                boolean isCopy = true;
                if(targetFile.exists()) {
                	if(FileUtil.fileCompare(localIDL, targetFile)) {
                		isCopy = false;
                	} else {
						File renameFile = new File(targetFile.getAbsolutePath() + DATE_FORMAT.format(new GregorianCalendar().getTime()));
						targetFile.renameTo(renameFile);
						FileUtil.removeBackupFiles(targetFile.getParent(), targetFile.getName());
                	}
                }

                if(isCopy) {
                    Path destinationPath = Paths.get(userDir + FS + sourcePath.getFileName());
                    try {
                        Files.copy(sourcePath,destinationPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
	 * データをロードする
	 */
	public void load() {
		GeneratorParam generator = editor.getGeneratorParam();
		if( generator.getRtcParam()!=null) {
			RtcParam rtcParam = generator.getRtcParam();
			if( servicePortViewer != null )
				servicePortViewer.setInput(rtcParam.getServicePorts());
			editor.updateEMFDataPorts(
					editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
					editor.getRtcParam().getEventports(), editor.getRtcParam().getServicePorts());
		}
	}

	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		Set<String> checkSet = new HashSet<String>();
		Set<String> checkVarSet = new HashSet<String>();

		for(ServicePortParam serviceport : rtcParam.getServicePorts()) {
			result = ValidationUtil.validateServicePort(serviceport);
			if( result!=null ) return result;
			//重複
			if( checkSet.contains(serviceport.getName()) ) {
				result = IMessageConstants.SERVICEPORT_VALIDATE_DUPLICATE;
				return result;
			}
			checkSet.add(serviceport.getName());
			for(ServicePortInterfaceParam ifparam : serviceport.getServicePortInterfaces()) {
				result = ValidationUtil.validateServiceInterface(ifparam);
				if( result!=null ) return result;
				//
				if( checkVarSet.contains(ifparam.getTmplVarName()) ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_VAR_DUPLICATE;
					return result;
				}
				checkVarSet.add(ifparam.getTmplVarName());
			}
		}
		return null;
	}

	//Master Block クラス
	private class ServicePortMasterBlock extends MasterDetailsBlock {

		@Override
		protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
			sashForm.setOrientation(SWT.VERTICAL);

			FormToolkit toolkit = managedForm.getToolkit();
			servicePortMasterBlockSection = toolkit.createSection(parent, Section.TITLE_BAR);
			servicePortMasterBlockSection.setText(IMessageConstants.SERVICEPORT_MAIN_TITLE);
			Composite client = toolkit.createComposite(servicePortMasterBlockSection);
			client.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			client.setLayout(new GridLayout(2, false));
			//
			//////
			Tree tree = toolkit.createTree(client, SWT.BORDER);
			servicePortViewer = new TreeViewer(tree);
			servicePortViewer.setContentProvider(new ServiceParamContentProvider());
			servicePortViewer.setLabelProvider(new ServiceParamLabelProvider());
			servicePortViewer.setInput(editor.getRtcParam().getServicePorts());
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.grabExcessHorizontalSpace = true;
			gridData.verticalSpan  = 4;
			tree.setLayoutData(gridData);
			//
			createPortAddButton(managedForm, client);
			createIFAddButton(managedForm, client);
			createDeleteButton(managedForm, client);
			//
			final SectionPart sectionPart = new SectionPart(servicePortMasterBlockSection);
			managedForm.addPart(sectionPart);
			servicePortViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					managedForm.fireSelectionChanged(sectionPart, event.getSelection());
					setButtonEnabled(event.getSelection());
				}
			});
			servicePortMasterBlockSection.setClient(client);
		}

		private void createDeleteButton(final IManagedForm managedForm,
				Composite client) {
			GridData gridData;
			deleteButton = managedForm.getToolkit().createButton(client, IMessageConstants.SERVICEPORT_BTN_DELETE, SWT.PUSH);
			deleteButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TreeItem[] selections = servicePortViewer.getTree().getSelection();
					if( selections==null || selections.length==0 )
						return;// 何も選択されていないときは何もしない
					TreeItem selection = selections[0];
					if( selection.getData() instanceof ServicePortParam ) {
						((List) servicePortViewer.getInput()).remove(selection.getData());
						update();
					} else	if( selection.getData() instanceof ServicePortInterfaceParam ) {
						((ServicePortInterfaceParam)selection.getData()).getParent().getServicePortInterfaces()
							.remove(selection.getData());
						update();
					}
				}
			});
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			deleteButton.setLayoutData(gridData);
		}

		private void createIFAddButton(final IManagedForm managedForm,
				Composite client) {
			GridData gridData;
			addinterfaceButton = managedForm.getToolkit().createButton(client, IMessageConstants.SERVICEPORT_BTN_ADDIF, SWT.PUSH);
			addinterfaceButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateDefaultValue();
					TreeItem[] selections = servicePortViewer.getTree().getSelection();
					if( selections==null || selections.length==0 )
						return;// 何も選択されていないときは何もしない
					TreeItem selection = selections[0];
					if( selection.getData() instanceof ServicePortParam ) {
						servicePortViewer.getTree().setRedraw(false);
						ServicePortInterfaceParam selectParam = new ServicePortInterfaceParam((ServicePortParam)selection.getData() ,
								defaultIFName, defaultIFInstanceName, defaultIFVarName, "", "", "", 0);
						((ServicePortParam)selection.getData()).getServicePortInterfaces().add(selectParam);
						Object[] expanded = servicePortViewer.getExpandedElements();
						List<Object> expanding = new ArrayList<Object>();
						Collections.addAll(expanding, expanded);
						if( !expanding.contains(selection.getData()) ) {
							expanding.add(selection.getData());
							servicePortViewer.setExpandedElements(expanding.toArray());
						}
						update();
						servicePortViewer.getTree().setRedraw(true);
					}
				}
			});
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			addinterfaceButton.setLayoutData(gridData);
		}

		private void createPortAddButton(final IManagedForm managedForm,
				Composite client) {
			GridData gridData;
			addButton = managedForm.getToolkit().createButton(client, IMessageConstants.SERVICEPORT_BTN_ADDPORT, SWT.PUSH);
			addButton.addSelectionListener(new SelectionAdapter() {
				@SuppressWarnings("unchecked")
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateDefaultValue();
					ServicePortParam selectParam = new ServicePortParam(defaultPortName, 0);
					((List) servicePortViewer.getInput()).add(selectParam);
					update();
				}
			});
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			addButton.setLayoutData(gridData);
		}

		@Override
		protected void createToolBarActions(IManagedForm managedForm) {
		}

		@Override
		protected void registerPages(DetailsPart detailsPart) {
			detailsPart.registerPage(ServicePortParam.class, new ServicePortDetailsPage());
			detailsPart.registerPage(ServicePortInterfaceParam.class, new ServicePortInterfaceDetailsPage());
			sashForm.setWeights(new int[] { 1, 3});
		}
	}

	//ServicePort Detail Block クラス
	private class ServicePortDetailsPage implements IDetailsPage {
		private IManagedForm form;

		public void createContents(Composite parent) {
			parent.setLayout(new FillLayout());
			FormToolkit toolkit = form.getToolkit();
			Section section = toolkit.createSection(parent, Section.TITLE_BAR);
			section.setText(IMessageConstants.SERVICEPORT_PORT_TITLE);
			Composite client = toolkit.createComposite(section, SWT.NULL);
			client.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			form.getToolkit().paintBordersFor(client);
			client.setLayout(new GridLayout(2, false));
			Label exp = toolkit.createLabel(client, IMessageConstants.SERVICEPORT_PORT_EXPL);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			exp.setLayoutData(gd);
			//
			nameText = createLabelAndText(toolkit, client,
					IMessageConstants.REQUIRED + IMessageConstants.SERVICEPORT_LBL_PORTNAME, SWT.NONE, SWT.COLOR_RED);
			positionCombo = createLabelAndCombo(toolkit, client,
					IMessageConstants.SERVICEPORT_LBL_POSITION, DataPortParam.COMBO_ITEM);

			createSrvPortDocumentSection(form, client);
			section.setClient(client);
		}

		private void createSrvPortDocumentSection(IManagedForm managedForm, Composite parent) {

			Section section = managedForm.getToolkit().createSection(
					parent,
					Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
			section.setText(IMessageConstants.SERVICEPORT_DOCUMENT_TITLE);
			GridData gridData = new GridData();
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = GridData.FILL;
			gridData.horizontalSpan = 2;
			section.setLayoutData(gridData);
			Composite composite = managedForm.getToolkit().createComposite(section,
					SWT.NULL);
			composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			managedForm.getToolkit().paintBordersFor(composite);
			GridLayout gl = new GridLayout(2, false);
			composite.setLayout(gl);
			section.setClient(composite);

			descriptionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 70;
			descriptionText.setLayoutData(gridData);
			ifoverviewText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_IFDESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifoverviewText.setLayoutData(gridData);
		}

		public void commit(boolean onSave) {
		}

		public void dispose() {
		}

		public void initialize(IManagedForm form) {
			this.form = form;
		}

		public boolean isDirty() {
			return false;
		}

		public boolean isStale() {
			return false;
		}

		public void refresh() {
		}

		public void setFocus() {
		}

		public boolean setFormInput(Object input) {
			return false;
		}

		public void selectionChanged(IFormPart part, ISelection selection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			ServicePortParam servicePort = (ServicePortParam)structuredSelection.getFirstElement();
			nameText.setText(servicePort.getName());
			positionCombo.select(servicePort.getPositionByIndex());
			//
			descriptionText.setText(StringUtil.getDisplayDocText(servicePort.getDocDescription()));
			ifoverviewText.setText(StringUtil.getDisplayDocText(servicePort.getDocIfDescription()));
		}
	}

	//ServicePortInterface Detail Block クラス
	private class ServicePortInterfaceDetailsPage implements IDetailsPage {
		private IManagedForm form;

		public void createContents(Composite parent) {
			parent.setLayout(new FillLayout());
			FormToolkit toolkit = form.getToolkit();
			Section section = toolkit.createSection(parent, Section.TITLE_BAR);
			section.setText(IMessageConstants.SERVICEPORT_IF_TITLE);
			Composite client = toolkit.createComposite(section, SWT.NULL);
			client.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			form.getToolkit().paintBordersFor(client);
			client.setLayout(new GridLayout(3, false));
			//
			Label exp = toolkit.createLabel(client, IMessageConstants.SERVICEPORT_IF_EXPL);
			GridData gd = new GridData();
			gd.horizontalSpan = 3;
			exp.setLayoutData(gd);
			//
			interfaceNameText = createLabelAndText(toolkit, client,
					IMessageConstants.REQUIRED + IMessageConstants.SERVICEPORT_LBL_IFNAME, SWT.NONE, SWT.COLOR_RED);
			toolkit.createLabel(client, "");
			directionCombo = createLabelAndCombo(toolkit, client,
					IMessageConstants.SERVICEPORT_LBL_IFDIRECTION, ServicePortInterfaceParam.COMBO_ITEM);
			toolkit.createLabel(client, "");
			instanceNameText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFINSTNAME);
			toolkit.createLabel(client, "");
			varNameText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFVARNAME);
			toolkit.createLabel(client, "");
			idlFileText = createLabelAndFile(toolkit, client, IDL_EXTENTION,
                    IMessageConstants.SERVICEPORT_LBL_IDLFILE, SWT.COLOR_BLACK, SWT.NONE);

            List<String> ifTypes = new ArrayList<String>();
            for(ServiceClassParam target : defaultIFList) {
                ifTypes.add(target.getName());
            }
            String[] defaultVal = new String[ifTypes.size()];
            defaultVal = ifTypes.toArray(defaultVal);
            interfaceTypeCombo = createLabelAndCombo(toolkit, client,
                    IMessageConstants.REQUIRED + IMessageConstants.SERVICEPORT_LBL_IFTYPE, defaultVal, SWT.COLOR_RED);
            interfaceTypeCombo.addSelectionListener(new SelectionListener() {
  			  public void widgetDefaultSelected(SelectionEvent e){}
  			  public void widgetSelected(SelectionEvent e){
  				int selected = interfaceTypeCombo.getSelectionIndex();
  				ServiceClassParam selectedCalsss = currentIFList.get(selected);
  				idlFileText.setText(selectedCalsss.getIdlFile());
			  }
  			});

			toolkit.createLabel(client, "");
			idlPathText = createLabelAndDirectory(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IDLPATH);

			createSrvPortIfDocumentSection(form, client);
			section.setClient(client);
		}

		private void createSrvPortIfDocumentSection(IManagedForm managedForm, Composite parent) {
			Section section = managedForm.getToolkit().createSection(
					parent,
					Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
			section.setText("Documentation");
			GridData gridData = new GridData();
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = GridData.FILL;
			gridData.horizontalSpan = 2;
			section.setLayoutData(gridData);
			Composite composite = managedForm.getToolkit().createComposite(section,
					SWT.NULL);
			composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			managedForm.getToolkit().paintBordersFor(composite);
			GridLayout gl = new GridLayout(2, false);
			composite.setLayout(gl);
			section.setClient(composite);

			ifdetailText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 70;
			ifdetailText.setLayoutData(gridData);
			ifargumentText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_ARGUMENT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifargumentText.setLayoutData(gridData);
			ifreturnText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_RETURN, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifreturnText.setLayoutData(gridData);
			ifexceptionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_EXCEPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifexceptionText.setLayoutData(gridData);
			ifpreconditionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_PRE_CONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifpreconditionText.setLayoutData(gridData);
			ifpostconditionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_POST_CONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifpostconditionText.setLayoutData(gridData);
		}

		public void commit(boolean onSave) {
		}

		public void dispose() {
		}

		public void initialize(IManagedForm form) {
			this.form = form;
		}

		public boolean isDirty() {
			return false;
		}

		public boolean isStale() {
			return false;
		}

		public void refresh() {
		}

		public void setFocus() {
		}

		public boolean setFormInput(Object input) {
			return false;
		}

		public void selectionChanged(IFormPart part, ISelection selection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			ServicePortInterfaceParam serviceInterface = (ServicePortInterfaceParam)structuredSelection.getFirstElement();
			interfaceNameText.setText(serviceInterface.getName());
			directionCombo.select(serviceInterface.getIndex());
			instanceNameText.setText(serviceInterface.getInstanceName());
			varNameText.setText(serviceInterface.getVarName());
			idlFileText.setText(serviceInterface.getIdlFile());
			interfaceTypeCombo.setText(serviceInterface.getInterfaceType());
			idlPathText.setText(serviceInterface.getIdlSearchPath());
			//
			ifdetailText.setText(StringUtil.getDisplayDocText(serviceInterface.getDocDescription()));
			ifargumentText.setText(StringUtil.getDisplayDocText(serviceInterface.getDocArgument()));
			ifreturnText.setText(StringUtil.getDisplayDocText(serviceInterface.getDocReturn()));
			ifexceptionText.setText(StringUtil.getDisplayDocText(serviceInterface.getDocException()));
			ifpreconditionText.setText(StringUtil.getDisplayDocText(serviceInterface.getDocPreCondition()));
			ifpostconditionText.setText(StringUtil.getDisplayDocText(serviceInterface.getDocPostCondition()));
		}

		private Text createLabelAndDirectory(FormToolkit toolkit, Composite composite, String labelString) {
			GridData gd;

			if(!labelString.equals("")) {
				toolkit.createLabel(composite, labelString);
			}
			final Text text = toolkit.createText(composite, "");
			text.addKeyListener(new KeyListener() {
				public void keyReleased(KeyEvent e) { update(); }
				public void keyPressed(KeyEvent e) {}
			});

			gd = new GridData(GridData.FILL_HORIZONTAL);

			text.setLayoutData(gd);

			Button checkButton = toolkit.createButton(composite, "Browse...", SWT.PUSH);
			checkButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					DirectoryDialog dialog = new DirectoryDialog(getEditorSite().getShell());
					if (text.getText().length() > 0)
						dialog.setFilterPath(text.getText());
					String newPath = dialog.open();
					if (newPath != null) {
						text.setText(newPath);
						update();
					}
				}
			});

			return text;
		}
	}

	private class ServiceParamContentProvider implements ITreeContentProvider {
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof ServicePortParam) {
				ServicePortParam servicePort = (ServicePortParam)parentElement;
				return servicePort.getServicePortInterfaces().toArray();
			} else if(parentElement instanceof Collection) {
				Collection collection = (Collection)parentElement;
				return collection.toArray();
			}
			return null;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return !(element instanceof ServicePortInterfaceParam);
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	private class ServiceParamLabelProvider extends LabelProvider {
		Image ImagePort, ImageReqIF, ImageProIF;

		public ServiceParamLabelProvider() {
			ImageDescriptor descPort;
			ImageDescriptor descReqIF;
			ImageDescriptor descProIF;
			try {
				URL url = RtcBuilderPlugin.getDefault().getBundle().getEntry("/");
				descPort = ImageDescriptor.createFromURL(new URL(url ,"icons/SrvPort.png"));
				descReqIF = ImageDescriptor.createFromURL(new URL(url ,"icons/ReqIF.png"));
				descProIF = ImageDescriptor.createFromURL(new URL(url ,"icons/ProIF.png"));
				ImageProIF = descProIF.createImage();
				ImagePort = descPort.createImage();
				ImageReqIF = descReqIF.createImage();
			} catch(MalformedURLException e) {
				descPort = ImageDescriptor.getMissingImageDescriptor();
			}
		}

		public String getText(Object element) {
			if( element instanceof ServicePortParam ) {
				ServicePortParam servicePort = (ServicePortParam)element;
				return servicePort.getName();
			} else if( element instanceof ServicePortInterfaceParam ) {
				ServicePortInterfaceParam serviceInterface = (ServicePortInterfaceParam)element;
				return serviceInterface.getName();
			}
			return super.getText(element);
		}

		public Image getImage(Object element) {
			if(element instanceof ServicePortParam) {
				return ImagePort;
			} else if(element instanceof ServicePortInterfaceParam) {
				ServicePortInterfaceParam serviceInterface = (ServicePortInterfaceParam)element;
				if(serviceInterface.getDirection().equals(ServicePortInterfaceParam.INTERFACE_DIRECTION_PROVIDED)) {
					return ImageProIF;
				}
				return ImageReqIF;
			}
			return super.getImage(element);
		}

		@Override
		public void dispose() {
			if( !servicePortViewer.getControl().isDisposed() ) {
				ImagePort.dispose();
				ImageReqIF.dispose();
				ImageProIF.dispose();
			}
			super.dispose();
		}
	}

	/**
	 * ServicePortフォーム内の要素の有効/無効を設定します。
	 * <ul>
	 * <li>serviceport.servicePort.table : ServicePortセクションのテーブル</li>
	 * <li>serviceport.servicePort.addButton : ServicePortセクションの Addボタン</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("servicePort")) {
			if (servicePortViewer != null) {
				if (widgetInfo.matchWidget("table"))     setViewerEnabled(servicePortViewer, enabled);
				if (widgetInfo.matchWidget("addButton")) setButtonEnabled(addButton, enabled);
			}
		}
	}

    private void extractServiceInterface() {
		List<IdlPathParam> sources = RTCUtil.getIDLPathes(editor.getRtcParam());
        String FS = System.getProperty("file.separator");
        defaultIFList.clear();

        List<String> exclusionList = Arrays.asList(
        		"basicdatatype.idl", "componentobserver.idl", "dataport.idl",
        		"extendeddatatypes.idl", "interfacedatatypes.idl", "manager.idl",
        		"openrtm.idl", "rtc.idl", "sdopackage.idl",
        		"sharedmemory.idl");

        for(IdlPathParam source : sources) {
	        try {
	            File idlDir = new File(source.getPath());
	            String[] list = idlDir.list();
	            if (list == null) return;
	            List<String> idlNames = new ArrayList<String>();
	            for (String name : list) {
	                if (name.toLowerCase().endsWith(".idl")) {
	                	if(source.isDefault() && exclusionList.contains(name.toLowerCase()) ) continue;
	                    idlNames.add(name);
	                }
	            }
	            Collections.sort(idlNames, new Comparator<String>() {
	                public int compare(String a, String b) {
	                    return a.compareTo(b);
	                }
	            });
	            for (String idlName : idlNames) {
	                String targetFile = source.getPath() + FS + idlName;
	                String idlContent = FileUtil.readFile(targetFile);
	                String targetContent = PreProcessor.parseAlltoSpace(idlContent);
	                IDLParser parser = new IDLParser(new StringReader(targetContent));
	                List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(parser.specification(), "");
	                for(ServiceClassParam param : serviceClassParams) {
	                    param.setIdlFile(targetFile);
	                    defaultIFList.add(param);
	                    currentIFList.add(param);
	                }
	            }
	        } catch (IOException e) {
	            LOGGER.error("Fail to read idl file", e);
	        } catch (ParseException e) {
	            LOGGER.error("Fail to parse idl file", e);
	        }
        }
    }
}
