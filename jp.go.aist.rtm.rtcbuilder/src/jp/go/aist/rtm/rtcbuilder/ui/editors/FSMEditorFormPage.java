package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.fsm.ScXMLHandler;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.PropertyParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.toolscommon.fsm.editor.SCXMLGraphEditor;
import jp.go.aist.rtm.toolscommon.fsm.editor.SCXMLNotifier;

/**
 * FSMページ
 */
public class FSMEditorFormPage extends AbstractEditorFormPage {

	private Button fsmBtn;
	private Button staticBtn;
	private Button dynamicBtn;
	private Button importBtn;
	private Button newBtn;
	private Button editBtn;
	
	private SCXMLGraphEditor scxmlEditor;
	private SCXMLReceiver observer;
	//
	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public FSMEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", "FSM");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm, "Finite State Machine(FSM)");
		FormToolkit toolkit = managedForm.getToolkit();
		//
		createFSMSection(toolkit, form);
		createHintSection(toolkit, form);

		load();
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 3);
		createHintLabel("FSM:", IMessageConstants.FSM_HINT_DESC, toolkit, composite);
		createHintLabel("Static:", IMessageConstants.FSM_STATIC_HINT_DESC, toolkit, composite);
		createHintLabel("Dynamic:", IMessageConstants.FSM_DYNAMIC_HINT_DESC, toolkit, composite);
		createHintSpace(toolkit, composite);
		createHintLabel("SCXML:", IMessageConstants.FSM_SCXML_HINT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.FSM_SCXML_NEW + ":", IMessageConstants.FSM_SCXML_NEW_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.FSM_SCXML_EDIT + ":", IMessageConstants.FSM_SCXML_EDIT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.FSM_SCXML_IMPORT + ":", IMessageConstants.FSM_SCXML_IMPORT_DESC, toolkit, composite);
	}
	
	private void createFSMSection(FormToolkit toolkit, ScrolledForm form) {
		Section sctOverView = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		sctOverView.setText("FSM Component Profile");
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.BEGINNING;
		sctOverView.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(sctOverView, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		GridLayout gl = new GridLayout(4, false);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gd);
		sctOverView.setClient(composite);
		//
		fsmBtn = createRadioCheckButton(toolkit, composite, "FSM", SWT.CHECK);
		gd = new GridData();
		gd.verticalAlignment = SWT.CENTER;
		fsmBtn.setLayoutData(gd);
		fsmBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				staticBtn.setEnabled(fsmBtn.getSelection());
				dynamicBtn.setEnabled(fsmBtn.getSelection());
				newBtn.setEnabled(fsmBtn.getSelection());
				editBtn.setEnabled(fsmBtn.getSelection() && editor.getRtcParam().getFsmParam()!=null);
				importBtn.setEnabled(fsmBtn.getSelection());
				if(fsmBtn.getSelection()) {
					editor.getRtcParam().addFSMPort();
				} else {
					editor.getRtcParam().deleteFSMPort();
				}
				editor.updateEMFDataPorts(
						editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
						editor.getRtcParam().getServicePorts());
				update();
			}
		});
		
		Group compGroup = new Group(composite, SWT.NONE);
		compGroup.setLayout(new GridLayout(3, false));
		gd = new GridData();
		gd.horizontalSpan = 3;
		compGroup.setLayoutData(gd);
		
		staticBtn = createRadioCheckButton(toolkit, compGroup, IRtcBuilderConstants.FSMTYTPE_STATIC, SWT.RADIO);
		dynamicBtn = createRadioCheckButton(toolkit, compGroup, IRtcBuilderConstants.FSMTYTPE_DYNAMIC, SWT.RADIO);
		staticBtn.setSelection(true);
		dynamicBtn.setSelection(false);
		
		toolkit.createLabel(composite, "SCXML");
		
		newBtn = toolkit.createButton(composite, IMessageConstants.FSM_SCXML_NEW, SWT.PUSH);
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = GridData.BEGINNING;
		newBtn.setLayoutData(gd);
		newBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					if(scxmlEditor==null) {
						String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
						if(observer==null) {
							observer = new SCXMLReceiver(editor.getRtcParam());
						}
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IWorkspaceRoot root = workspace.getRoot();
						IProject project = root.getProject(editor.getRtcParam().getOutputProject());
						IFile fsmFile  = project.getFile(cmpName);
						if(fsmFile.exists()) {
							boolean confirm = MessageDialog.openConfirm(getSite().getShell(), "FSM Editor",
												IMessageConstants.FSM_OVERWRITE);
							if (!confirm) return;
						}
						scxmlEditor = SCXMLGraphEditor.openEditor(null, observer, false);
					} else {
						JFrame frame = (JFrame) SwingUtilities.windowForComponent(scxmlEditor);
						frame.setAlwaysOnTop(true);
						frame.setAlwaysOnTop(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		editBtn = toolkit.createButton(composite, IMessageConstants.FSM_SCXML_EDIT, SWT.PUSH);
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = GridData.END;
		editBtn.setLayoutData(gd);
		editBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					if(scxmlEditor==null) {
						String fsmName = editor.getRtcParam().getName() + "FSM.scxml";
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IWorkspaceRoot root = workspace.getRoot();
						IProject project = root.getProject(editor.getRtcParam().getOutputProject());
						IFile fsmFile  = project.getFile(fsmName);
						String targetFile = "";
						if(fsmFile.exists()) {
							targetFile = fsmFile.getLocation().toOSString();
						} else {
							MessageDialog.openWarning(getSite().getShell(), "FSM Editor",
											IMessageConstants.FSM_NO_EXIST);
							return;
						}
						if(observer==null) {
							observer = new SCXMLReceiver(editor.getRtcParam());
						}
						//
						String dummyName = ".Dummy.scxml";
						IFile dummyFile  = project.getFile(dummyName);
						if(dummyFile.exists()==false) {
							try {
								dummyFile.create(null, true, null);
							} catch (CoreException ex) {
								ex.printStackTrace();
							}
						}
						String strPath = dummyFile.getLocation().toOSString();
						String xmlSplit[] = editor.getRtcParam().getFsmContents().split(System.lineSeparator());
						try {
							BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(strPath), "UTF-8"));
							for (String s : xmlSplit) {
								if(s.length()==0) continue;
								writer.write(s);
								writer.newLine();
							}
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							project.refreshLocal(IResource.DEPTH_INFINITE, null);
						} catch (CoreException e1) {
							throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
						}
						
						scxmlEditor = SCXMLGraphEditor.openEditor(dummyFile.getLocation().toOSString(), observer, false);
					} else {
						JFrame frame = (JFrame) SwingUtilities.windowForComponent(scxmlEditor);
						frame.setAlwaysOnTop(true);
						frame.setAlwaysOnTop(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		editBtn.setEnabled(editor.getRtcParam().getFsmParam()!=null);
		/////
		importBtn = toolkit.createButton(composite, IMessageConstants.FSM_SCXML_IMPORT, SWT.PUSH);
		gd = new GridData();
		gd.widthHint = 100;
		gd.horizontalAlignment = GridData.BEGINNING;
		importBtn.setLayoutData(gd);
		importBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				IProject project = root.getProject(editor.getRtcParam().getOutputProject());
				IFile fsmFile  = project.getFile(cmpName);
				if(fsmFile.exists()) {
					boolean confirm = MessageDialog.openConfirm(getSite().getShell(), "FSM Editor",
											IMessageConstants.FSM_OVERWRITE);
					if (!confirm) return;
				}
				/////
				FileDialog dialog = new FileDialog(getEditorSite().getShell(), SWT.OPEN);
				dialog.setFilterNames(new String[]{"SCXML File", "XML File"});
				dialog.setFilterExtensions(new String[]{"*.scxml", "*.xml"});
				String newFile = dialog.open();
				if (newFile == null) return;
				/////
				String strPath = fsmFile.getLocation().toOSString();
				try {
					fsmFile.delete(true, null);
    				Path inputPath = FileSystems.getDefault().getPath(newFile);
    				Path outputPath = FileSystems.getDefault().getPath(strPath);			        				
					Files.copy(inputPath, outputPath);
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
					//インポートしたファイルの読み込み
					ScXMLHandler scHandler = new ScXMLHandler();
					StringBuffer buffer = new StringBuffer();
					StateParam rootState = scHandler.parseSCXML(strPath, buffer);
					if(rootState!=null) {
						editor.getRtcParam().setFsmParam(rootState);
						editor.getRtcParam().setFsmContents(buffer.toString());
					}
					
					MessageDialog.openInformation(getSite().getShell(), IMessageConstants.FSM_SCXML_IMPORT, IMessageConstants.FSM_IMPORT_OK);
				} catch (IOException e1) {
					MessageDialog.openWarning(getSite().getShell(), IMessageConstants.FSM_SCXML_IMPORT, IMessageConstants.FSM_IMPORT_NG);
					e1.printStackTrace();
				} catch (CoreException e1) {
				}
			}
		});
		staticBtn.setEnabled(false);
		dynamicBtn.setEnabled(false);
		newBtn.setEnabled(false);
		editBtn.setEnabled(false);
		importBtn.setEnabled(false);
		/////
//		Button updateButton = toolkit.createButton(composite, "Update", SWT.PUSH);
//		updateButton.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				scxmlEditor.updateEditor("Off");
//				scxmlEditor.updateEditor("Idle");
//				scxmlEditor.updateEditor("Configuring");
//			}
//		});
//		Button parseButton = toolkit.createButton(composite, "Parse", SWT.PUSH);
//		parseButton.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				String cmpName = editor.getRtcParam().getName() + "FSM.scxml";
//				IWorkspace workspace = ResourcesPlugin.getWorkspace();
//				IWorkspaceRoot root = workspace.getRoot();
//				IProject project = root.getProject(editor.getRtcParam().getOutputProject());
//				IFile fsmFile  = project.getFile(cmpName);
//				
//				ScXMLHandler handler = new ScXMLHandler();
//				handler.parseSCXML(fsmFile.getLocation().toOSString());
//			}
//		});
	}

	public void update() {
		RtcParam rtcParam = editor.getRtcParam();

		if( fsmBtn != null ) {
//			String targetFile = editor.getRtcParam().getName() + "FSM.scxml";
//			if(targetFile!=null && targetFile.length()!=0) {
//				IWorkspace workspace = ResourcesPlugin.getWorkspace();
//				IWorkspaceRoot root = workspace.getRoot();
//				IProject project = root.getProject(editor.getRtcParam().getOutputProject());
//				IFile fsmFile  = project.getFile(targetFile);
//				if(fsmFile.exists()) {
//					targetFile = fsmFile.getLocation().toOSString();
//				}
//				
//				ScXMLHandler handler = new ScXMLHandler();
//				StringBuffer buffer = new StringBuffer();
//				StateParam rootState = handler.parseSCXML(targetFile, buffer);
//				if(rootState!=null) {
//					rtcParam.setFsmParam(rootState);
//					rtcParam.setFsmContents(buffer.toString());
//				}
//			}
			
			rtcParam.setProperty(IRtcBuilderConstants.PROP_TYPE_FSM, Boolean.valueOf(fsmBtn.getSelection()).toString());
			//
			String fsmCompType = "";
			if(dynamicBtn.getSelection()) {
				fsmCompType = IRtcBuilderConstants.FSMTYTPE_DYNAMIC;
			} else if(staticBtn.getSelection()) {
				fsmCompType = IRtcBuilderConstants.FSMTYTPE_STATIC;
			}
			rtcParam.setProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE, fsmCompType);
		}

		editor.updateDirty();
	}

	/**
	 * データをロードする
	 */
	public void load() {
		if(fsmBtn==null) return;
		RtcParam rtcParam = editor.getRtcParam();

		PropertyParam target = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
		if(target!=null) {
			fsmBtn.setSelection(Boolean.valueOf(target.getValue()));
			staticBtn.setEnabled(fsmBtn.getSelection());
			dynamicBtn.setEnabled(fsmBtn.getSelection());
			newBtn.setEnabled(fsmBtn.getSelection());
			editBtn.setEnabled(fsmBtn.getSelection() && editor.getRtcParam().getFsmParam()!=null);
			importBtn.setEnabled(fsmBtn.getSelection());
		}
		//
		PropertyParam fsmType = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE);
		if(fsmType!=null) {
			String fsmCompType = fsmType.getValue();
			if(fsmCompType.equals(IRtcBuilderConstants.FSMTYTPE_DYNAMIC) ) {
				dynamicBtn.setSelection(true);
				staticBtn.setSelection(false);
			} else if(fsmCompType.equals(IRtcBuilderConstants.FSMTYTPE_STATIC)) {
				dynamicBtn.setSelection(false);
				staticBtn.setSelection(true);
			}
		}
		//
		String targetFile = editor.getRtcParam().getName() + "FSM.scxml";
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(editor.getRtcParam().getOutputProject());
		IFile fsmFile  = project.getFile(targetFile);
		if(fsmFile.exists()) {
			targetFile = fsmFile.getLocation().toOSString();
			ScXMLHandler handler = new ScXMLHandler();
			StringBuffer buffer = new StringBuffer();
			StateParam rootState = handler.parseSCXML(targetFile, buffer);
			if(rootState!=null) {
				rtcParam.setFsmParam(rootState);
				rtcParam.setFsmContents(buffer.toString());
			}
		}
	}

	public String validateParam() {
		String result = null;
		RtcParam rtcParam = editor.getRtcParam();
		if(rtcParam==null) return result;
		PropertyParam fsm = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
		if(fsm==null) return result;
		
		if(Boolean.valueOf(fsm.getValue())) {
			PropertyParam fsmType = rtcParam.getProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE);
			if(fsmType==null) {
				result = IMessageConstants.FSM_NOT_SELECTED;
			} else {
				String strType = fsmType.getValue();
				if(!(strType.equals(IRtcBuilderConstants.FSMTYTPE_STATIC) || strType.equals(IRtcBuilderConstants.FSMTYTPE_DYNAMIC))) {
					result = IMessageConstants.FSM_TYPE_INVALID;
				}
			}
			
			StateParam fsmParam = rtcParam.getFsmParam();
			if(fsmParam==null) {
				result = IMessageConstants.FSM_NO_SM;
			} else {
				List<String> stateList = new ArrayList<String>();
				stateList.add(fsmParam.getName());
				for(StateParam param : fsmParam.getAllStateList() ) {
					if(stateList.contains(param.getName())) {
						result = IMessageConstants.FSM_STATE_DUPL1 + param.getName() + IMessageConstants.FSM_STATE_DUPL2;
						break;
					} else {
						stateList.add(param.getName());
					}
				}
			}
		}
		return result;
	}
	
	class SCXMLReceiver implements SCXMLNotifier {
		private RtcParam rtcParam;
		
		public SCXMLReceiver(RtcParam param) {
			this.rtcParam = param;
		}
		
		@Override
		public void notifyContents(String contents) {
    		editor.updateDirty();
			ScXMLHandler handler = new ScXMLHandler();
			StateParam rootState = handler.parseSCXMLStr(contents);
			if(rootState!=null) {
				rtcParam.setFsmParam(rootState);
				rtcParam.setFsmContents(contents);
				editor.updateDirty();
			}
			scxmlEditor = null;
		}
	}
}
