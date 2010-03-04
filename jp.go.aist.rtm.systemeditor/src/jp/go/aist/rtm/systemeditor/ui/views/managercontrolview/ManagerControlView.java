package jp.go.aist.rtm.systemeditor.ui.views.managercontrolview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.CreateComponentDialog;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.ui.views.propertysheetview.RtcPropertySheetPage;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * マネージャ管理ビュー
 *
 */
public class ManagerControlView extends ViewPart {

	private static final int MENU_BUTTON_WIDTH = 120;

	private static final int EXEC_BUTTON_WIDTH = 70;

	private Composite composite = null;

	private RTCManager targetManager;

	private Button loadedModuleButton;

	private Button loadableModuleButton;

	private Button activeComponentButton;

	private Button createButton;

	private Button forkButton;

	private Button shutdownButton;

	private Table modulesTable;

	private TableViewer modulesTableViewer;

	private TableColumn moduleColumn;

	private Button loadButton;

	private Button unloadButton;

	private Text urlText;

	private boolean isSelectedLoadableModules;

	private boolean isSelectedLoadedModules;

	private boolean isSelectedActiveComponents;

	private List<String> moduleList;

	public ManagerControlView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl;
		GridData gd;

		gl = new GridLayout();
		gl.numColumns = 3;
		composite = new Composite(parent, SWT.FILL);
		composite.setLayout(gl);

		final Composite menuButtonComposite = new Composite(composite, SWT.NONE);
		gl = new GridLayout();
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		menuButtonComposite.setLayout(gl);
		menuButtonComposite.setLayoutData(gd);

		loadableModuleButton = new Button(menuButtonComposite, SWT.TOP);
		loadableModuleButton.setText(Messages.getString("ManagerControlView.0")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		loadableModuleButton.setLayoutData(gd);
		loadableModuleButton.setEnabled(false);
		loadableModuleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isSelectedLoadableModules = false;
				isSelectedLoadedModules = false;
				isSelectedActiveComponents = false;
				if (targetManager != null) {
					moduleColumn.setText(Messages.getString("ManagerControlView.1")); //$NON-NLS-1$
					isSelectedLoadableModules = true;
					// キャッシュ更新
					targetManager.getLoadableModuleProfilesR();
				}
				refreshModuleListData();
			}
		});

		loadedModuleButton = new Button(menuButtonComposite, SWT.TOP);
		loadedModuleButton.setText(Messages.getString("ManagerControlView.2")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		loadedModuleButton.setLayoutData(gd);
		loadedModuleButton.setEnabled(false);
		loadedModuleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isSelectedLoadableModules = false;
				isSelectedLoadedModules = false;
				isSelectedActiveComponents = false;
				if (targetManager != null) {
					moduleColumn.setText(Messages.getString("ManagerControlView.3")); //$NON-NLS-1$
					isSelectedLoadedModules = true;
					// キャッシュ更新
					targetManager.getLoadedModuleProfilesR();
				}
				refreshModuleListData();
			}
		});

		activeComponentButton = new Button(menuButtonComposite, SWT.TOP);
		activeComponentButton.setText(Messages.getString("ManagerControlView.4")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		activeComponentButton.setLayoutData(gd);
		activeComponentButton.setEnabled(false);
		activeComponentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isSelectedLoadableModules = false;
				isSelectedLoadedModules = false;
				isSelectedActiveComponents = false;
				if (targetManager != null) {
					moduleColumn.setText(Messages.getString("ManagerControlView.5")); //$NON-NLS-1$
					isSelectedActiveComponents = true;
					// キャッシュ更新
					targetManager.getComponentProfilesR();
				}
				refreshModuleListData();
			}
		});

		createButton = new Button(menuButtonComposite, SWT.TOP);
		createButton.setText(Messages.getString("ManagerControlView.6")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		createButton.setLayoutData(gd);
		createButton.setEnabled(false);
		createButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager == null) {
					return;
				}
				CreateComponentDialog dialog = new CreateComponentDialog(
						getSite().getShell());
				dialog.setTypeList(targetManager.getFactoryProfileTypeNamesR());
				if (dialog.open() == IDialogConstants.OK_ID) {
					targetManager.createComponentR(dialog.getParameter());
				}
			}
		});

		forkButton = new Button(menuButtonComposite, SWT.TOP);
		forkButton.setText(Messages.getString("ManagerControlView.7")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		forkButton.setLayoutData(gd);
		forkButton.setEnabled(false);
		forkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					targetManager.forkR();
				}
				buildData();
			}
		});

		shutdownButton = new Button(menuButtonComposite, SWT.TOP);
		shutdownButton.setText(Messages.getString("ManagerControlView.8")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		shutdownButton.setLayoutData(gd);
		shutdownButton.setEnabled(false);
		shutdownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					targetManager.shutdownR();
					targetManager = null;
				}
				buildData();
			}
		});

		final Composite listComposite = new Composite(composite, SWT.FILL);
		gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.numColumns = 2;
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		listComposite.setLayout(gl);
		listComposite.setLayoutData(gd);

		modulesTableViewer = new TableViewer(listComposite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		modulesTableViewer.setContentProvider(new ArrayContentProvider());

		modulesTable = modulesTableViewer.getTable();
		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		modulesTable.setLayout(gl);
		modulesTable.setLayoutData(gd);
		modulesTable.setLinesVisible(true);
		modulesTable.setHeaderVisible(true);
		modulesTable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateEnableLoadButton();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateEnableLoadButton();
			}
		});

		moduleColumn = new TableColumn(modulesTable, SWT.NONE);
		moduleColumn.setText(Messages.getString("ManagerControlView.9")); //$NON-NLS-1$
		moduleColumn.setWidth(300);

		Label urlLabel = new Label(listComposite, SWT.NONE);
		gd = new GridData();
		urlLabel.setLayoutData(gd);
		urlLabel.setText(Messages.getString("ManagerControlView.10")); //$NON-NLS-1$

		urlText = new Text(listComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		urlText.setLayoutData(gd);
		urlText.setTextLimit(255);
		urlText.setEnabled(false);
		urlText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateEnableLoadButton();
			}
		});

		final Composite execButtonComposite = new Composite(composite, SWT.NONE);
		gl = new GridLayout();
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		execButtonComposite.setLayout(gl);
		execButtonComposite.setLayoutData(gd);

		loadButton = new Button(execButtonComposite, SWT.TOP);
		loadButton.setText(Messages.getString("ManagerControlView.11")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		loadButton.setLayoutData(gd);
		loadButton.setEnabled(false);
		loadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					String module = null;
					if (modulesTable.getSelectionIndex() != -1) {
						if (isSelectedLoadableModules) {
							module = moduleList.get(modulesTable
									.getSelectionIndex());
						}
					} else if (urlText.getText().length() > 0) {
						module = urlText.getText();
					}
					if (module != null) {
						// TODO initfuncはどこで指定？
						targetManager.loadModuleR(module, ""); //$NON-NLS-1$
					}
				}
				refreshModuleListData();
			}
		});

		unloadButton = new Button(execButtonComposite, SWT.TOP);
		unloadButton.setText(Messages.getString("ManagerControlView.13")); //$NON-NLS-1$
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		unloadButton.setLayoutData(gd);
		unloadButton.setEnabled(false);
		unloadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					if (isSelectedLoadedModules) {
						if (modulesTable.getSelectionIndex() != -1) {
							String mn = moduleList.get(modulesTable
									.getSelectionIndex());
							targetManager.unloadModuleR(mn);
						}
					}
				}
				refreshModuleListData();
			}
		});

		setSiteSelection();
	}

	@Override
	public void setFocus() {
		// TODO 自動生成されたメソッド・スタブ

	}

	private void buildData() {
		this.isSelectedLoadableModules = false;
		this.isSelectedLoadedModules = false;
		this.isSelectedActiveComponents = false;
		refreshData();
	}

	private void refreshData() {
		this.loadableModuleButton.setEnabled(false);
		this.loadedModuleButton.setEnabled(false);
		this.activeComponentButton.setEnabled(false);
		this.createButton.setEnabled(false);
		this.forkButton.setEnabled(false);
		this.shutdownButton.setEnabled(false);
		this.loadButton.setEnabled(false);
		this.unloadButton.setEnabled(false);
		this.urlText.setEnabled(false);

		if (this.targetManager != null) {
			this.loadableModuleButton.setEnabled(true);
			this.loadedModuleButton.setEnabled(true);
			this.activeComponentButton.setEnabled(true);
			this.createButton.setEnabled(true);
			this.forkButton.setEnabled(true);
			this.shutdownButton.setEnabled(true);
		}
		refreshModuleListData();
	}

	private void refreshModuleListData() {
		modulesTableViewer.setInput(Collections.EMPTY_LIST);
		if (moduleList == null) {
			moduleList = new ArrayList<String>();
		}
		moduleList.clear();
		urlText.setText(""); //$NON-NLS-1$
		urlText.setEnabled(false);
		loadButton.setEnabled(false);
		unloadButton.setEnabled(false);
		if (targetManager != null) {
			if (isSelectedLoadableModules) {
				for (Object o : targetManager.getLoadableModuleFileNames()) {
					moduleList.add((String) o);
				}
				modulesTableViewer.setInput(moduleList);
				urlText.setEnabled(true);
			} else if (isSelectedLoadedModules) {
				for (Object o : targetManager.getLoadedModuleFileNames()) {
					moduleList.add((String) o);
				}
				modulesTableViewer.setInput(moduleList);
			} else if (isSelectedActiveComponents) {
				for (Object o : targetManager.getComponentInstanceNames()) {
					moduleList.add((String) o);
				}
				modulesTableViewer.setInput(moduleList);
			}
		}
		updateEnableLoadButton();
	}

	private void updateEnableLoadButton() {
		loadButton.setEnabled(false);
		unloadButton.setEnabled(false);
		if (modulesTable.getSelectionIndex() != -1) {
			if (isSelectedLoadableModules) {
				loadButton.setEnabled(true);
			} else if (isSelectedLoadedModules) {
				unloadButton.setEnabled(true);
			}
		} else if (urlText.getText().length() > 0) {
			// URL指定の場合
			loadButton.setEnabled(true);
		}
	}

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			return new RtcPropertySheetPage();
		}
		return super.getAdapter(adapter);
	}

	private ISelectionListener selectionListener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			
			targetManager = null;
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				Object firstElement = ss.getFirstElement();
				Object adapter = AdapterUtil.getAdapter(firstElement,
						RTCManager.class);
				if (adapter != null) {
					targetManager = (RTCManager) adapter;
					targetManager.synchronizeManually();
				}
			}
			buildData();
		}
	};

	private void setSiteSelection() {
		if (getSite() == null) {
			return;
		}

		selectionListener.selectionChanged(null, getSite().getWorkbenchWindow().getSelectionService().getSelection());
		
		// NameServiceViewの選択監視リスナを登録
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(selectionListener);

		// SelectionProviderを登録(プロパティ・ビュー連携)
		getSite().setSelectionProvider(new ISelectionProvider() {
			public void addSelectionChangedListener(
					ISelectionChangedListener listener) {
			}

			public ISelection getSelection() {
				StructuredSelection result = null;
				if (targetManager != null) {
					result = new StructuredSelection(targetManager);
				}
				return result;
			}

			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
			}

			public void setSelection(ISelection selection) {
			}
		});
	}

}
