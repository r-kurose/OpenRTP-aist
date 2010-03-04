package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.extension.AddFormPageExtension;
import jp.go.aist.rtm.rtcbuilder.extension.EditorExtension;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ParamUtil;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.model.component.BuildView;
import jp.go.aist.rtm.rtcbuilder.model.component.Component;
import jp.go.aist.rtm.rtcbuilder.model.component.ComponentFactory;
import jp.go.aist.rtm.rtcbuilder.model.component.DataInPort;
import jp.go.aist.rtm.rtcbuilder.model.component.DataOutPort;
import jp.go.aist.rtm.rtcbuilder.model.component.InterfaceDirection;
import jp.go.aist.rtm.rtcbuilder.model.component.PortDirection;
import jp.go.aist.rtm.rtcbuilder.model.component.ServiceInterface;
import jp.go.aist.rtm.rtcbuilder.model.component.ServicePort;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.DocumentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.FileEditorInput;
import org.openrtp.namespaces.rtc.version02.RtcProfile;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

/**
 * RtcBuilderエディタ
 */
public class RtcBuilderEditor extends FormEditor implements IActionFilter {
	public static final String RTC_BUILDER_EDITOR_ID = RtcBuilderEditor.class
			.getName();

	public static String RTCBUILDER_NEW_EDITOR_PATH = "RtcBuilder";
	//
	public static String ECLPSE_VERSION_33 = "3.3";
	//
	private boolean isDirty;
	private String title;

	private GeneratorParam generatorParam;
	private BuildView buildview;

	private BasicEditorFormPage basicFormPage;
	private DataPortEditorFormPage dataPortFormPage;
	private ServicePortEditorFormPage servicePortFormPage;
	private ConfigurationEditorFormPage configurationFormPage;
	private LanguageEditorFormPage languageFormPage;
	private RtcXmlEditorFormPage rtcXmlFormPage;
	private DocumentEditorFormPage documentFormPage;
	private ActivityEditorFormPage activityFormPage;
	
	private Map<Integer, AbstractCustomFormPage> customFormPages;
	
	//
	private List<GenerateManager> managerList = null;

	private IPageChangedListener pageChangedListener = new IPageChangedListener(){
		public void pageChanged(PageChangedEvent event) {
			if( event!=null ){
				if( event.getSelectedPage() instanceof AbstractEditorFormPage ){
					((AbstractEditorFormPage)event.getSelectedPage()).pageSelected();
				}
			}
		}
	};
	
	public RtcBuilderEditor() {
	}

	private IEditorInput load(IEditorInput input, IEditorSite site)
			throws PartInitException {
		boolean newOpenEditor = input instanceof NullEditorInput;// 新規エディタ

		IEditorInput result = input;
		
		if (newOpenEditor) {
			//新規エディタオープン処理
			createGeneratorParam();
			title = "RtcBuilder";
		} else if (result instanceof FileEditorInput) {
			FileEditorInput fileEditorInput = ((FileEditorInput) result);
			try {
				ProfileHandler handler = new ProfileHandler();
				generatorParam = handler.restorefromXMLFile(fileEditorInput.getPath().toOSString());
				
				if( buildview==null ) buildview = ComponentFactory.eINSTANCE.createBuildView();
				updateEMFModuleName(this.getRtcParam().getName());
				updateEMFDataInPorts(this.getRtcParam().getInports());
				updateEMFDataOutPorts(this.getRtcParam().getOutports());
				updateEMFServiceOutPorts(this.getRtcParam().getServicePorts());
			} catch (Exception e) {
				createGeneratorParam();
			}
			String[] target = ((FileEditorInput) result).getPath().segments();
			if( target.length>1 ) {
				title = target[target.length-2];
			} else {
				title = ((FileEditorInput) result).getPath().lastSegment();
			}
		}

		isDirty = false;
		firePropertyChange(IEditorPart.PROP_TITLE);

		if( basicFormPage != null )	 basicFormPage.load();
		allPagesReLoad();
		this.setInput(result);

		return result;
	}
	
	public void loadNewData(RtcParam param) {
		this.generatorParam.getRtcParams().set(0, param);
		
		title = "RtcBuilder";
		if( buildview==null ) buildview = ComponentFactory.eINSTANCE.createBuildView();
		updateEMFModuleName(this.getRtcParam().getName());
		updateEMFDataInPorts(this.getRtcParam().getInports());
		updateEMFDataOutPorts(this.getRtcParam().getOutports());
		updateEMFServiceOutPorts(this.getRtcParam().getServicePorts());
		//

		if( basicFormPage != null )	 basicFormPage.load();
		allPagesReLoad();
		
		updateDirty();
	}

	private void createGeneratorParam(){
		generatorParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(generatorParam);
		rtcParam.setSchemaVersion(IRtcBuilderConstants.SCHEMA_VERSION);
		//
		rtcParam.setName(ComponentPreferenceManager.getInstance().getBasic_ComponentName());
		rtcParam.setDescription(ComponentPreferenceManager.getInstance().getBasic_Description());
		rtcParam.setCategory(ComponentPreferenceManager.getInstance().getBasic_Category());
		rtcParam.setVersion(ComponentPreferenceManager.getInstance().getBasic_Version());
		rtcParam.setVender(ComponentPreferenceManager.getInstance().getBasic_VendorName());
		rtcParam.setComponentType(ComponentPreferenceManager.getInstance().getBasic_ComponentType());
		rtcParam.setActivityType(ComponentPreferenceManager.getInstance().getBasic_ActivityType());
		rtcParam.setComponentKind(ComponentPreferenceManager.getInstance().getBasic_ComponentKind());
		rtcParam.setMaxInstance(ComponentPreferenceManager.getInstance().getBasic_MaxInstances());
		rtcParam.setExecutionType(ComponentPreferenceManager.getInstance().getBasic_ExecutionType());
		rtcParam.setExecutionRate(ComponentPreferenceManager.getInstance().getBasic_ExecutionRate());
		//
		ArrayList<String> docs = DocumentPreferenceManager.getDocumentValue();
		for( int intidx=IRtcBuilderConstants.ACTIVITY_INITIALIZE; intidx<IRtcBuilderConstants.ACTIVITY_DUMMY; intidx++) {
			rtcParam.setActionImplemented(intidx, docs.get(intidx));
		}
		//
		rtcParam.setDocLicense(DocumentPreferenceManager.getLicenseValue());
		rtcParam.setDocCreator(DocumentPreferenceManager.getCreatorValue());
		//
		rtcParam.resetUpdated();
		generatorParam.getRtcParams().add(rtcParam);
		buildview = ComponentFactory.eINSTANCE.createBuildView();
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		IEditorInput newInput = load(input, site);
		super.init(site, newInput);
		managerList = RtcBuilderPlugin.getDefault().getLoader().getManagerList();
		// ページ切り替え時のイベントを管理
		addPageChangedListener(pageChangedListener);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RtcBuilderEditor.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(getContainer());
		getContainer().setMenu(menu);
		((IEditorSite) getSite()).registerContextMenu(menuMgr,
				new ISelectionProvider() {

					public void addSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					public ISelection getSelection() {
						return new StructuredSelection(RtcBuilderEditor.this);
					}

					public void removeSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					public void setSelection(ISelection selection) {
					}

				}, false);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator());
		// drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	protected void createPages() {
		super.createPages();
		hookContextMenu();
	}

	/**
	 * {@inheritDoc}
	 */
	protected FormToolkit createToolkit(Display display) {
		return new FormToolkit(getSite().getShell().getDisplay());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addPages() {
		try {
			AbstractEditorFormPage[] defaultPages = new AbstractEditorFormPage[8];
			//
			basicFormPage = new BasicEditorFormPage(this);
			defaultPages[0] = basicFormPage;
			activityFormPage = new ActivityEditorFormPage(this);
			defaultPages[1] = activityFormPage;
			dataPortFormPage = new DataPortEditorFormPage(this);
			defaultPages[2] = dataPortFormPage;
			servicePortFormPage = new ServicePortEditorFormPage(this);
			defaultPages[3] = servicePortFormPage;
			configurationFormPage = new ConfigurationEditorFormPage(this);
			defaultPages[4] = configurationFormPage;
			documentFormPage = new DocumentEditorFormPage(this);
			defaultPages[5] = documentFormPage;
			languageFormPage = new LanguageEditorFormPage(this);
			defaultPages[6] = languageFormPage;
			rtcXmlFormPage = new RtcXmlEditorFormPage(this);
			defaultPages[7] = rtcXmlFormPage;
			//
			List<List<AbstractEditorFormPage>> forms = new ArrayList<List<AbstractEditorFormPage>>();
			forms.add(new ArrayList<AbstractEditorFormPage>());
			for (AbstractEditorFormPage p : defaultPages) {
				List<AbstractEditorFormPage> list = new ArrayList<AbstractEditorFormPage>();
				list.add(p);
				forms.add(list);
			}
			List<AbstractEditorFormPage> last = forms.get(forms.size() - 1);
			//
			List extList = RtcBuilderPlugin.getDefault()
					.getAddFormPageExtensionLoader().getList();
			if (extList != null) {
				for (Object o : extList) {
					AddFormPageExtension ext = (AddFormPageExtension) o;
					Map<Integer, AbstractCustomFormPage> map = ext
							.getCustomPages(this);
					for (Integer i : map.keySet()) {
						if (i < 0 || i >= defaultPages.length) {
							last.add(map.get(i));
						} else {
							forms.get(i).add(map.get(i));
						}
					}
				}
			}
			//
			this.customFormPages = new HashMap<Integer, AbstractCustomFormPage>();
			for (List<AbstractEditorFormPage> list : forms) {
				for (AbstractEditorFormPage p : list) {
					int index = addPage(p);
					if (p instanceof AbstractCustomFormPage) {
						this.customFormPages.put(index,
								(AbstractCustomFormPage) p);
					}
				}
			}
			// nullページが挿入されるので削除しておく (Eclipseのバグ？)
			if (this.pages.contains(null)) {
				int nullIndex = -1;
				for (int i = 0; i < this.pages.size(); i++) {
					if (this.pages.get(i) == null) {
						nullIndex = i;
						break;
					}
				}
				if (nullIndex >= 0) {
					this.pages.remove(nullIndex);
				}
			}
			//
			for (AbstractCustomFormPage p : customFormPages.values()) {
				p.setDefaultEnableInfo();
			}
		} catch (PartInitException e) {
			throw new RuntimeException(e); // system error
		}
	}

	private void customPagesOperation(String command) {
		if (customFormPages == null)
			return;

		for (Integer i : customFormPages.keySet()) {
			AbstractCustomFormPage customPage = customFormPages.get(i);
			if (customPage == null) {
				continue;
			}
			String key = customPage.getManagerKey();
			if (command.equals("load")) {
				customPage.load();
			} else if (command.equals("update")) {
				// 言語選択に一致しないページは画面→RtcParamへ反映しない
				if (!getRtcParam().getLangList().contains(key)) {
					continue;
				}
				customPage.update();
			}
		}
	}

	protected void allPagesReLoad(){
		if( dataPortFormPage != null ) dataPortFormPage.load();
		if( servicePortFormPage != null ) servicePortFormPage.load();
		if( configurationFormPage != null ) configurationFormPage.load();
		if( languageFormPage != null ) languageFormPage.load();
		if( rtcXmlFormPage != null ) rtcXmlFormPage.load();
		if( documentFormPage != null ) documentFormPage.load();
		if( activityFormPage != null ) activityFormPage.load();
		//
		customPagesOperation("load");
	}
	
	protected void allUpdates(){
		basicFormPage.update();
		dataPortFormPage.updateForOutput();
		servicePortFormPage.update();
		configurationFormPage.updateForOutput();
		languageFormPage.update();
		documentFormPage.update();
		activityFormPage.update();
		//
		customPagesOperation("update");
	}

	protected void addDefaultComboValue(){
		basicFormPage.addDefaultComboValue();
	}

	public String validateParam() {
		String result = null;
		for (int intIdx = 0; intIdx < this.pages.size(); intIdx++) {
			AbstractEditorFormPage page = (AbstractEditorFormPage) this.pages.get(intIdx);
			if (page == null) continue;
			if (page instanceof AbstractCustomFormPage) {
				String key = ((AbstractCustomFormPage) page).getManagerKey();
				if (!getRtcParam().getLangList().contains(key)) continue;
			}
			result = page.validateParam();
			if (result != null) {
				this.setActivePage(intIdx);
				return result;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public void doSave(IProgressMonitor monitor) {
		boolean isRtcXml = getCurrentPage() == rtcXmlFormPage.getIndex();
		boolean newOpenEditor = getEditorInput() instanceof NullEditorInput;// 新規エディタ
		this.allUpdates();

		if (newOpenEditor) {
			doSaveAs();
			return;
		}

		if (isRtcXml) {
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(this.getRtcParam().getRtcXml()) ) return;
			} catch (Exception e) {
				String errMessage = null;
				if( e.getCause()==null ) {
					errMessage = e.getMessage();
				} else {
					errMessage = e.getCause().toString();
				}
				MessageDialog.openError(getSite().getShell(), "XML Save Error", errMessage);
				return;
			}
		}else{
			// RTC.xmlページではないとき
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(handler.convert2XML(this.getGeneratorParam())) ) return;
			} catch (JAXBException ex) {
				boolean result = MessageDialog.openQuestion(
						getSite().getShell(),
						ex.getMessage(),
    					IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE + System.getProperty("line.separator") + ex.getCause().toString()
    				); 
    			if( !result ) return;// 「いいえ」のときは保存しない
			} catch (Exception e) {
				MessageDialog.openError(getSite().getShell(), "XML Save Error", e.getMessage());
				return;
			}
		}

		IFile file = ((IFileEditorInput) getEditorInput()).getFile();

		try {
			save(file, monitor, isRtcXml);
		} catch (CoreException e) {
			ErrorDialog.openError(getSite().getShell(), "Error During Save",
					"The current model could not be saved.", e.getStatus());
		} catch (Exception e) {
			MessageDialog.openError(getSite().getShell(), "Error During Save",
					"The current model could not be saved.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void doSaveAs() {
		final boolean isRtcXml = getCurrentPage() == rtcXmlFormPage.getIndex();
		boolean newOpenEditor = getEditorInput() instanceof NullEditorInput;// 新規エディタ

		if (isRtcXml) {
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(this.getRtcParam().getRtcXml()) ) return;
			} catch (Exception e) {
				String errMessage = null;
				if( e.getCause()==null ) {
					errMessage = e.getMessage();
				} else {
					errMessage = e.getCause().toString();
				}
				MessageDialog.openError(getSite().getShell(), "XML Save Error", errMessage);
				return;
			}
		}else{
			// RTC.xmlページではないとき
			try {
				ProfileHandler handler = new ProfileHandler();
				if( !handler.validateXml(handler.convert2XML(this.getGeneratorParam())) ) return;
			} catch (JAXBException ex) {
				boolean result = MessageDialog.openQuestion(
						getSite().getShell(),
						ex.getMessage(),
    					IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE + System.getProperty("line.separator") + ex.getCause().toString()
    				); 
    			if( !result ) return;// 「いいえ」のときは保存しない
			} catch (Exception e) {
				MessageDialog.openError(getSite().getShell(), "XML Save Error", e.getMessage());
				return;
			}
		}
		
		IPath oldFile = null;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (newOpenEditor) {
			oldFile = new Path(root.getLocation().toOSString());
			// void
		} else {
			oldFile = ((FileEditorInput) getEditorInput()).getFile().getProject().getLocation();
		}

		final IPath newPath = FileUtil.getDirectoryPathByDialog(oldFile);

		if (newPath == null) return;

		if (newPath.toFile().exists() == false) {
			try {
				newPath.toFile().createNewFile();
			} catch (IOException e) {
				MessageDialog.openError(getSite().getShell(), "Error",
						IMessageConstants.CREATE_FILE_ERROR + newPath.toOSString());
				return;
			}
		}

		final IFile newFile = root.getFileForLocation(newPath);

		ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(
				getSite().getShell());

		try {
			progressMonitorDialog.run(false, false,
					new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							try {
								if (newPath.toFile().exists() == false) {
									try {
										newPath.toFile().createNewFile();
									} catch (IOException e) {
										throw new RuntimeException(e); // SystemError
									}
								}

								save(newFile, monitor, isRtcXml);
								// getMultiPageCommandStackListener().markSaveLocations();
							} catch (CoreException e) {
							} catch (Exception e) {
							}
						}
					});
		} catch (Exception e) {
			throw new RuntimeException(e); // SystemError
		}

	}

	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 */
	private void save(IFile file, IProgressMonitor progressMonitor, boolean blnRtcXml)
			throws Exception {

		if (null == progressMonitor) progressMonitor = new NullProgressMonitor();

		progressMonitor.beginTask("Saving ", 2);
		String xmlFile = "";
		if( blnRtcXml ) {
	        xmlFile = this.getRtcParam().getRtcXml();
		} else {
			DatatypeFactory dateFactory = new DatatypeFactoryImpl();
			String dateTime = dateFactory.newXMLGregorianCalendar(new GregorianCalendar()).toString();
			for(RtcParam rtcParam : generatorParam.getRtcParams() ) {
				rtcParam.setUpdateDate(dateTime);
			}
			ProfileHandler handler = new ProfileHandler();
			xmlFile = handler.convert2XML(generatorParam);
		}
		progressMonitor.worked(15);
		//
		
		IProject projectHandle = file.getProject();
		try {
			IFile rtcxml = projectHandle.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML);
			if( rtcxml.exists()) rtcxml.delete(true, null);
			rtcxml.create(new ByteArrayInputStream(xmlFile.getBytes("UTF-8")), true, null);
			//
			setInput(new FileEditorInput(rtcxml));
			this.getRtcParam().setRtcXml(xmlFile);
			//
			// isDirty = false;
			// firePropertyChange(IEditorPart.PROP_DIRTY);
			getRtcParam().resetUpdated();
			updateDirty();
		} catch (UnsupportedEncodingException e) {
			IStatus status = new Status(IStatus.ERROR, RtcBuilderPlugin
						.getDefault().getClass().getName(), 0,
						"Error writing file.", e);
			progressMonitor.done();
			throw new CoreException(status);
		} catch (NullPointerException ex) {
			MessageDialog.openError(getSite().getShell(), "Error",
					"Error writing file.");
			progressMonitor.done();
			throw new CoreException(null);
		}
		//
		if( blnRtcXml ) {
			updateProfiles(xmlFile);
		}
		//
		if( rtcXmlFormPage != null ) rtcXmlFormPage.load();
		addDefaultComboValue();
		title = projectHandle.getName();
		firePropertyChange(IEditorPart.PROP_TITLE);
		progressMonitor.done();
	}

	protected void updateProfiles(String xmlFile) throws Exception {
		// RTC.xmlの内容を他のページに反映
		ProfileHandler handler = new ProfileHandler();
		RtcProfile module = handler.restorefromXML(xmlFile);
		ParamUtil putil = new ParamUtil();
		getGeneratorParam().getRtcParams().set(0,
				putil.convertFromModule(module, generatorParam, managerList));
		getRtcParam().setRtcXml(xmlFile);
		//
		if (basicFormPage != null) basicFormPage.load();
		if (dataPortFormPage != null) dataPortFormPage.load();
		if (servicePortFormPage != null) servicePortFormPage.load();
		if (configurationFormPage != null) configurationFormPage.load();
		if (languageFormPage != null) languageFormPage.load();
		if (documentFormPage != null) documentFormPage.load();
		if (activityFormPage != null) activityFormPage.load();
		//
		customPagesOperation("load");
		//
		getRtcParam().resetUpdated();
		updateDirty();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isDirty() {
		return isDirty;
	}

	private void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void firePropertyChange(int propertyId) {
		super.firePropertyChange(propertyId);
	}

	/**
	 * エディタをダーティにする。
	 */
	public void updateDirty() {
		setDirty(getRtcParam().isUpdated());
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * GeneratorParamを取得する
	 */
	public GeneratorParam getGeneratorParam() {
		return generatorParam;
	}
	public void setGeneratorParam(GeneratorParam genparam) {
		this.generatorParam = genparam;
	}

	/**
	 * RtcParamを取得する
	 */
	public RtcParam getRtcParam() {
		return generatorParam.getRtcParams().get(0);
	}

	/**
	 * EMF modelを取得する
	 */
	public BuildView getEMFmodel() {
		return buildview;
	}
	
	public void open() {
		boolean save = false;
		if (isDirty()) {
			save = MessageDialog.openQuestion(getSite().getShell(), "",
					"ファイルが保存されていません。保存しますか？");
		}

		if (save) doSave(null);

		final IPath newPath = FileUtil.getFilePathByDialog(null, SWT.OPEN);
		if( newPath==null ) return;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IFile newFile = root.getFileForLocation(newPath);
		if (newFile != null) {
			try {
				load(new FileEditorInput(newFile), getEditorSite());
			} catch (PartInitException e) {
				e.printStackTrace(); // system error
				MessageDialog.openError(getSite().getShell(), "", e.getMessage());
			}
		} else {
			MessageDialog.openError(getSite().getShell(), "File Open Error", "Project内のファイルを選択してください。");
		}
	}
	
	public void updateEMFModuleName(String name) {
		((Component)buildview.getComponents().get(0)).setComponent_Name(name);
	}

	public void updateEMFDataInPorts(List<DataPortParam> dataInPorts) {
		((Component)buildview.getComponents().get(0)).clearDataInports();
		for(int intIdx=0; intIdx<dataInPorts.size();intIdx++ ) {
			DataInPort dataInport= ComponentFactory.eINSTANCE.createDataInPort();
			dataInport.setInPort_Name(dataInPorts.get(intIdx).getName());
			dataInport.setIndex(intIdx);
			dataInport.setDirection(PortDirection.get(dataInPorts.get(intIdx).getPositionByIndex()));
			((Component)buildview.getComponents().get(0)).addDataInport(dataInport);
		}
	}

	public void updateEMFDataOutPorts(List<DataPortParam> dataOutPorts) {
		((Component)buildview.getComponents().get(0)).clearDataOutports();
		for(int intIdx=0; intIdx<dataOutPorts.size();intIdx++ ) {
			DataOutPort dataOutport= ComponentFactory.eINSTANCE.createDataOutPort();
			dataOutport.setOutPort_Name(dataOutPorts.get(intIdx).getName());
			dataOutport.setIndex(intIdx);
			dataOutport.setDirection(PortDirection.get(dataOutPorts.get(intIdx).getPositionByIndex()));
			((Component)buildview.getComponents().get(0)).addDataOutport(dataOutport);
		}
	}

	public void updateEMFServiceOutPorts(List<ServicePortParam> servicePorts) {
		((Component)buildview.getComponents().get(0)).clearServiceports();
		for(int intIdx=0; intIdx<servicePorts.size();intIdx++ ) {
			ServicePortParam srvParam = servicePorts.get(intIdx);
			ServicePort servicePort= ComponentFactory.eINSTANCE.createServicePort();
			servicePort.setServicePort_Name(srvParam.getName());
			servicePort.setIndex(intIdx);
			servicePort.setDirection(PortDirection.get(srvParam.getPositionByIndex()));
			for(int idxIf=0;idxIf<srvParam.getServicePortInterfaces().size();idxIf++) {
				ServicePortInterfaceParam srvIfParam = srvParam.getServicePortInterfaces().get(idxIf);
				ServiceInterface serviceIF = ComponentFactory.eINSTANCE.createServiceInterface();
				serviceIF.setServiceInterface_Name(srvIfParam.getName());
				serviceIF.setDirection(InterfaceDirection.get(srvIfParam.getDirection()));
				serviceIF.setParentDirection(servicePort.getDirection());
				serviceIF.setIndex(idxIf);
				servicePort.addServiceInterface(serviceIF);
			}
			((Component)buildview.getComponents().get(0)).addServiceport(servicePort);
		}
	}

	public boolean testAttribute(Object target, String name, String value) {
		boolean result = false;
		if ("dirty".equals(name)) {
			if (isDirty()) {
				result = "true".equalsIgnoreCase(value);
			} else {
				result = "false".equalsIgnoreCase(value);
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	private String getEclipseVersion() {
		return System.getProperty("osgi.framework.version");
	}

	public void setEnabledInfoByLang() {
		setEnabledInfoByLang(getRtcParam().getLanguage());
	}

	@SuppressWarnings("deprecation")
	public void setEnabledInfoByLang(String langName) {
		EditorExtension ext = RtcBuilderPlugin.getDefault()
				.getEditorExtensionLoader().findByLang(langName);
		if (ext == null) {
			// 全表示(C++含む)
			setEnabledInfo(new ArrayList<String>());
		} else {
			if (!ext.getDisableFormWidgets().isEmpty()) {
				setEnabledInfo(ext.getDisableFormWidgets());
			} else {
				// 廃止予定
				setEnabledInfoObsolete(ext.getInapplicables());
			}
		}
		// 拡張しているFormPage
		for (AbstractCustomFormPage customPage : customFormPages.values()) {
			customPage.setEnableInfo(langName);
		}
	}

	void setEnabledInfo(List<String> infos) {
		AbstractEditorFormPage.WidgetInfo widgetInfo;
		widgetInfo = createWidgetInfo("basic.*.*");
		basicFormPage.setEnabledInfo(widgetInfo, true);
		widgetInfo = createWidgetInfo("activity.*.*");
		activityFormPage.setEnabledInfo(widgetInfo, true);
		widgetInfo = createWidgetInfo("dataport.*.*");
		dataPortFormPage.setEnabledInfo(widgetInfo, true);
		widgetInfo = createWidgetInfo("serviceport.*.*");
		servicePortFormPage.setEnabledInfo(widgetInfo, true);
		widgetInfo = createWidgetInfo("config.*.*");
		configurationFormPage.setEnabledInfo(widgetInfo, true);
		for (String info : infos) {
			widgetInfo = createWidgetInfo(info);
			if (widgetInfo == null) {
				continue;
			}
			if (widgetInfo.matchForm("basic")) {
				basicFormPage.setEnabledInfo(widgetInfo, false);
			} else if (widgetInfo.matchForm("activity")) {
				activityFormPage.setEnabledInfo(widgetInfo, false);
			} else if (widgetInfo.matchForm("dataport")) {
				dataPortFormPage.setEnabledInfo(widgetInfo, false);
			} else if (widgetInfo.matchForm("serviceport")) {
				servicePortFormPage.setEnabledInfo(widgetInfo, false);
			} else if (widgetInfo.matchForm("config")) {
				configurationFormPage.setEnabledInfo(widgetInfo, false);
			}
		}
	}

	/** 古い方式によるフォームの活性化設定 */
	@Deprecated
	void setEnabledInfoObsolete(List<String> infos) {
		AbstractEditorFormPage.WidgetInfo widgetInfo;
		//
		if (infos.contains(EditorExtension.RTC_PROFILE_PARAMETERS_INAPPLICABLE)) {
			widgetInfo = createWidgetInfo("config.configParam.*");
			configurationFormPage.setEnabledInfo(widgetInfo, false);
			// configurationFormPage.setConfigurationParameterSectionCompositeEnabled(false);
		} else {
			widgetInfo = createWidgetInfo("config.*.*");
			configurationFormPage.setEnabledInfo(widgetInfo, true);
			// configurationFormPage.setConfigurationParameterSectionCompositeEnabled(true);
		}
		//
		if (infos
				.contains(EditorExtension.RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE)) {
			widgetInfo = createWidgetInfo("serviceport.servicePort.*");
			servicePortFormPage.setEnabledInfo(widgetInfo, false);
			// servicePortFormPage.setServicePortFormPageEnabled(false);
		} else {
			widgetInfo = createWidgetInfo("serviceport.*.*");
			servicePortFormPage.setEnabledInfo(widgetInfo, true);
			// servicePortFormPage.setServicePortFormPageEnabled(true);
		}
		//
		if (infos.contains(EditorExtension.RTC_PROFILE_DATA_PORTS_INAPPLICABLE)) {
			widgetInfo = createWidgetInfo("dataport.inPort.*");
			dataPortFormPage.setEnabledInfo(widgetInfo, false);
			widgetInfo = createWidgetInfo("dataport.outPort.*");
			dataPortFormPage.setEnabledInfo(widgetInfo, false);
			// dataPortFormPage.setDataPortFormPageEnabled(false);
		} else {
			widgetInfo = createWidgetInfo("dataport.*.*");
			dataPortFormPage.setEnabledInfo(widgetInfo, true);
			// dataPortFormPage.setDataPortFormPageEnabled(true);
		}
		//
		if (infos
				.contains(EditorExtension.GENERATE_BUTTON_SECTION_INAPPLICABLE)) {
			widgetInfo = createWidgetInfo("basic.outputProject.*");
			basicFormPage.setEnabledInfo(widgetInfo, false);
			widgetInfo = createWidgetInfo("basic.generate.*");
			basicFormPage.setEnabledInfo(widgetInfo, false);
			// basicFormPage.setEnableGenerateSection(false);
		} else {
			widgetInfo = createWidgetInfo("basic.*.*");
			basicFormPage.setEnabledInfo(widgetInfo, true);
			// basicFormPage.setEnableGenerateSection(true);
		}
	}

	@Deprecated
	public void setEnabledInfoByLangFromRtcParam() {
		setEnabledInfoByLang(getRtcParam().getLanguage());
		// FormPage
		for (AbstractCustomFormPage customPage : customFormPages.values()) {
			customPage.setEnableInfo(getRtcParam().getLanguage());
		}
	}

	AbstractEditorFormPage.WidgetInfo createWidgetInfo(String widgetName) {
		if (widgetName == null) {
			return null;
		}
		String[] ss = widgetName.split("\\.");
		if (ss.length != 3) {
			return null;
		}
		return new AbstractEditorFormPage.WidgetInfo(ss[0], ss[1], ss[2]);
	}
}
