package jp.go.aist.rtm.systemeditor.ui.views.managercontrolview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.CreateComponentDialog;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.ui.views.propertysheetview.RtcPropertySheetPage;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * マネージャ管理ビュー
 *
 */
public class ManagerControlView extends ViewPart {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ManagerControlView.class);

	private static final String LABEL_LOADABLE_MODULE_BUTTON = Messages
			.getString("ManagerControlView.0");
	private static final String LABEL_LOADED_MODULE_BUTTON = Messages
			.getString("ManagerControlView.2");
	private static final String LABEL_ACTIVE_COMPONENT_BUTTON = Messages
			.getString("ManagerControlView.4");
	private static final String LABEL_CREATE_BUTTON = Messages
			.getString("ManagerControlView.6");
	private static final String LABEL_FORK_BUTTON = Messages
			.getString("ManagerControlView.7");
	private static final String LABEL_RESTART_BUTTON = Messages
			.getString("ManagerControlView.restart");
	private static final String LABEL_SHUTDOWN_BUTTON = Messages
			.getString("ManagerControlView.8");

	private static final String LABEL_LOAD_BUTTON = Messages
			.getString("ManagerControlView.11");
	private static final String LABEL_UPLOAD_BUTTON = Messages
			.getString("ManagerControlView.13");
	private static final String LABEL_DELETE_COMPONENT_BUTTON = Messages
			.getString("ManagerControlView.delete_component");

	private static final String LABEL_MODULE_COLUMN = Messages
			.getString("ManagerControlView.1");
	private static final String LABEL_COMPONENT_COLUMN = Messages
			.getString("ManagerControlView.5");
	private static final String LABEL_PROCESS_GROUP_COLUMN = Messages
			.getString("ManagerControlView.process_group");

	private static final String LABEL_URL_TEXT = Messages
			.getString("ManagerControlView.10");

	private static final int MENU_BUTTON_WIDTH = 160;
	private static final int EXEC_BUTTON_WIDTH = 160;

	private Composite composite = null;
	private Button loadedModuleButton;
	private Button loadableModuleButton;
	private Button activeComponentButton;
	private Button createButton;
	private Button forkButton;
	private Button restartButton;
	private Button shutdownButton;
	private Table modulesTable;
	private TableViewer modulesTableViewer;
	private TableColumn moduleColumn1;
	private TableColumn moduleColumn2;
	private Button loadButton;
	private Button unloadButton;
	private Button deleteComponentButton;
	private Text urlText;

	private boolean isSelectedLoadableModules;
	private boolean isSelectedLoadedModules;
	private boolean isSelectedActiveComponents;

	private RTCManager targetManager;
	private List<String[]> moduleList;

	public ManagerControlView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gl;
		GridData gd;

		gl = new GridLayout();
		gl.numColumns = 3;
		this.composite = new Composite(parent, SWT.FILL);
		this.composite.setLayout(gl);

		final Composite menuButtonComposite = new Composite(this.composite,
				SWT.NONE);
		gl = new GridLayout();
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		menuButtonComposite.setLayout(gl);
		menuButtonComposite.setLayoutData(gd);

		this.loadableModuleButton = new Button(menuButtonComposite, SWT.TOP);
		this.loadableModuleButton.setText(LABEL_LOADABLE_MODULE_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.loadableModuleButton.setLayoutData(gd);
		this.loadableModuleButton.setEnabled(false);
		this.loadableModuleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isSelectedLoadableModules = false;
				isSelectedLoadedModules = false;
				isSelectedActiveComponents = false;
				if (targetManager != null) {
					moduleColumn1.setText(LABEL_MODULE_COLUMN);
					moduleColumn2.setText("");
					isSelectedLoadableModules = true;
					// キャッシュ更新
					targetManager.getLoadableModuleProfilesR();
				}
				refreshModuleListData();
			}
		});

		this.loadedModuleButton = new Button(menuButtonComposite, SWT.TOP);
		this.loadedModuleButton.setText(LABEL_LOADED_MODULE_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.loadedModuleButton.setLayoutData(gd);
		this.loadedModuleButton.setEnabled(false);
		this.loadedModuleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isSelectedLoadableModules = false;
				isSelectedLoadedModules = false;
				isSelectedActiveComponents = false;
				if (targetManager != null) {
					moduleColumn1.setText(LABEL_MODULE_COLUMN);
					moduleColumn2.setText("");
					isSelectedLoadedModules = true;
					// キャッシュ更新
					targetManager.getLoadedModuleProfilesR();
				}
				refreshModuleListData();
			}
		});

		this.activeComponentButton = new Button(menuButtonComposite, SWT.TOP);
		this.activeComponentButton.setText(LABEL_ACTIVE_COMPONENT_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.activeComponentButton.setLayoutData(gd);
		this.activeComponentButton.setEnabled(false);
		this.activeComponentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isSelectedLoadableModules = false;
				isSelectedLoadedModules = false;
				isSelectedActiveComponents = false;
				if (targetManager != null) {
					moduleColumn1.setText(LABEL_COMPONENT_COLUMN);
					moduleColumn2.setText(LABEL_PROCESS_GROUP_COLUMN);
					isSelectedActiveComponents = true;
					// キャッシュ更新
					targetManager.getComponentProfilesR();
				}
				refreshModuleListData();
			}
		});

		this.createButton = new Button(menuButtonComposite, SWT.TOP);
		this.createButton.setText(LABEL_CREATE_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.createButton.setLayoutData(gd);
		this.createButton.setEnabled(false);
		this.createButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager == null) {
					return;
				}
				CreateComponentDialog dialog = new CreateComponentDialog(
						getSite().getShell());
				dialog.setTypeList(targetManager.getFactoryProfileTypeNamesR());
				dialog.setProcessGroupList(targetManager.getSlaveManagerNames());
				if (dialog.open() == IDialogConstants.OK_ID) {
					String cmd = dialog.getParameter();
					LOGGER.info("create command: <{}>", cmd);
					targetManager.createComponentR(cmd);
				}
			}
		});

		this.forkButton = new Button(menuButtonComposite, SWT.TOP);
		this.forkButton.setText(LABEL_FORK_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.forkButton.setLayoutData(gd);
		this.forkButton.setEnabled(false);
		this.forkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					targetManager.forkR();
				}
				buildData();
			}
		});

		this.restartButton = new Button(menuButtonComposite, SWT.TOP);
		this.restartButton.setText(LABEL_RESTART_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.restartButton.setLayoutData(gd);
		this.restartButton.setEnabled(false);
		this.restartButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					targetManager.restartR();
					targetManager = null;
				}
				buildData();
			}
		});

		this.shutdownButton = new Button(menuButtonComposite, SWT.TOP);
		this.shutdownButton.setText(LABEL_SHUTDOWN_BUTTON);
		gd = new GridData();
		gd.widthHint = MENU_BUTTON_WIDTH;
		this.shutdownButton.setLayoutData(gd);
		this.shutdownButton.setEnabled(false);
		this.shutdownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					targetManager.shutdownR();
					targetManager = null;
				}
				buildData();
			}
		});

		final Composite listComposite = new Composite(this.composite, SWT.FILL);
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

		this.modulesTableViewer = new TableViewer(listComposite,
				SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		this.modulesTableViewer.setContentProvider(new ArrayContentProvider());

		this.modulesTable = this.modulesTableViewer.getTable();
		gl = new GridLayout(1, false);
		gl.numColumns = 1;
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		this.modulesTable.setLayout(gl);
		this.modulesTable.setLayoutData(gd);
		this.modulesTable.setLinesVisible(true);
		this.modulesTable.setHeaderVisible(true);
		this.modulesTable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateEnableLoadButton();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateEnableLoadButton();
			}
		});

		this.moduleColumn1 = new TableColumn(this.modulesTable, SWT.NONE);
		this.moduleColumn1.setText(LABEL_MODULE_COLUMN);
		this.moduleColumn1.setWidth(300);
		this.moduleColumn2 = new TableColumn(this.modulesTable, SWT.NONE);
		this.moduleColumn2.setText("");
		this.moduleColumn2.setWidth(300);

		this.modulesTableViewer.setLabelProvider(new ModuleLabelProvider());

		Label urlLabel = new Label(listComposite, SWT.NONE);
		gd = new GridData();
		urlLabel.setLayoutData(gd);
		urlLabel.setText(LABEL_URL_TEXT);

		this.urlText = new Text(listComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.urlText.setLayoutData(gd);
		this.urlText.setTextLimit(255);
		this.urlText.setEnabled(false);
		this.urlText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateEnableLoadButton();
			}
		});

		final Composite execButtonComposite = new Composite(this.composite,
				SWT.NONE);
		gl = new GridLayout();
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		execButtonComposite.setLayout(gl);
		execButtonComposite.setLayoutData(gd);

		this.loadButton = new Button(execButtonComposite, SWT.TOP);
		this.loadButton.setText(LABEL_LOAD_BUTTON);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.loadButton.setLayoutData(gd);
		this.loadButton.setEnabled(false);
		this.loadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					String module = null;
					if (modulesTable.getSelectionIndex() != -1) {
						if (isSelectedLoadableModules) {
							String[] m = moduleList.get(modulesTable
									.getSelectionIndex());
							module = m[0];
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

		this.unloadButton = new Button(execButtonComposite, SWT.TOP);
		this.unloadButton.setText(LABEL_UPLOAD_BUTTON);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.unloadButton.setLayoutData(gd);
		this.unloadButton.setEnabled(false);
		this.unloadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					if (isSelectedLoadedModules) {
						if (modulesTable.getSelectionIndex() != -1) {
							String[] m = moduleList.get(modulesTable
									.getSelectionIndex());
							String module = m[0];
							targetManager.unloadModuleR(module);
						}
					}
				}
				refreshModuleListData();
			}
		});

		this.deleteComponentButton = new Button(execButtonComposite, SWT.TOP);
		this.deleteComponentButton.setText(LABEL_DELETE_COMPONENT_BUTTON);
		gd = new GridData();
		gd.widthHint = EXEC_BUTTON_WIDTH;
		this.deleteComponentButton.setLayoutData(gd);
		this.deleteComponentButton.setEnabled(false);
		this.deleteComponentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (targetManager != null) {
					if (isSelectedActiveComponents) {
						if (modulesTable.getSelectionIndex() != -1) {
							String[] m = moduleList.get(modulesTable
									.getSelectionIndex());
							String comp = m[0];
							targetManager.deleteComponentR(comp);
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
		this.restartButton.setEnabled(false);
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
			this.restartButton.setEnabled(true);
			this.shutdownButton.setEnabled(true);
		}
		refreshModuleListData();
	}

	private void refreshModuleListData() {
		this.modulesTableViewer.setInput(Collections.EMPTY_LIST);
		if (this.moduleList == null) {
			this.moduleList = new ArrayList<String[]>();
		}
		this.moduleList.clear();
		this.urlText.setText("");
		this.urlText.setEnabled(false);
		this.loadButton.setEnabled(false);
		this.unloadButton.setEnabled(false);
		this.deleteComponentButton.setEnabled(false);
		if (this.targetManager != null) {
			if (this.isSelectedLoadableModules) {
				for (String m : this.targetManager.getLoadableModuleFileNames()) {
					this.moduleList.add(new String[] { m });
				}
				this.modulesTableViewer.setInput(this.moduleList);
				this.urlText.setEnabled(true);
			} else if (this.isSelectedLoadedModules) {
				for (String m : this.targetManager.getLoadedModuleFileNames()) {
					this.moduleList.add(new String[] { m });
				}
				this.modulesTableViewer.setInput(this.moduleList);
			} else if (this.isSelectedActiveComponents) {
				for (RTC.ComponentProfile prof : this.targetManager
						.getComponentProfiles()) {
					String name = prof.instance_name;
					String pg = SDOUtil.findValueAsString("process_group",
							prof.properties);
					pg = (pg == null) ? "" : pg;
					this.moduleList.add(new String[] { name, pg });
				}
				this.modulesTableViewer.setInput(this.moduleList);
			}
		}
		updateEnableLoadButton();
	}

	private void updateEnableLoadButton() {
		this.loadButton.setEnabled(false);
		this.unloadButton.setEnabled(false);
		this.deleteComponentButton.setEnabled(false);
		if (this.modulesTable.getSelectionIndex() != -1) {
			if (this.isSelectedLoadableModules) {
				this.loadButton.setEnabled(true);
			} else if (this.isSelectedLoadedModules) {
				this.unloadButton.setEnabled(true);
			} else if (this.isSelectedActiveComponents) {
				this.deleteComponentButton.setEnabled(true);
			}
		} else if (this.urlText.getText().length() > 0) {
			// URL指定の場合
			this.loadButton.setEnabled(true);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.equals(IPropertySheetPage.class)) {
			return new RtcPropertySheetPage();
		}
		return super.getAdapter(adapter);
	}

	/** モジュール/コンポーネント一覧表示のLabelProvider */
	public class ModuleLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			String[] entry = (String[]) element;
			return (columnIndex < entry.length) ? entry[columnIndex] : "";
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

	private ISelectionListener selectionListener = new ISelectionListener() {
		@Override
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

		this.selectionListener.selectionChanged(null, getSite()
				.getWorkbenchWindow().getSelectionService().getSelection());

		// NameServiceViewの選択監視リスナを登録
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this.selectionListener);

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
