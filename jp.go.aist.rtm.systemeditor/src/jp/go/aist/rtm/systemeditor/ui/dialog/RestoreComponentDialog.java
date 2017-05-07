package jp.go.aist.rtm.systemeditor.ui.dialog;

import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.COLOR_WHITE;
import static jp.go.aist.rtm.systemeditor.ui.util.UIUtil.getColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.corba.CORBAHelper;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerFactory;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RTシステム復元設定を行うダイアログ
 */
public class RestoreComponentDialog extends TitleAreaDialog {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestoreComponentDialog.class);

	static final String LABEL_MAPPING_MESSAGE = Messages
			.getString("RestoreComponentDialog.head_message");

	static final String MSG_STATUS_NO_EP_NAME = Messages
			.getString("RestoreComponentDialog.msg_no_ep_name");
	static final String MSG_STATUS_EP_UNREACHABLE = Messages
			.getString("RestoreComponentDialog.msg_ep_unreach");
	static final String MSG_STATUS_NEED_CREATE = Messages
			.getString("RestoreComponentDialog.msg_need_create");

	private static final String LABEL_COMPONENT = "component:";
	private static final String LABEL_MANAGER = "manager:";
	private static final String LABEL_TARGET = "target:";
	private static final String LABEL_STATUS = "status:";

	private static final String COL_COMPONENT = "component";
	private static final String COL_TYPE = "type";
	private static final String COL_MANAGER = "manager";
	private static final String COL_ENDPOINT = "endpoint";
	private static final String COL_TARGET = "target";
	private static final String COL_STATUS = "status";

	static final String PROP_IMPLEMENTATION_ID = "implementation_id";
	static final String PROP_CORBA_ENDPOINTS = "corba.endpoints";
	static final String PROP_MANAGER_NAME = "manager_name";

	private TableViewer tableViewer;
	private Table table;

	private Label componentNameLabel;
	private Label typeNameLabel;
	private Text managerNameText;
	private Text endpointNameText;
	private Combo targetCombo;
	private Label statusLabel;

	private List<TargetInfo> targetList = new ArrayList<>();
	private TargetInfo selectedTarget;

	private EndpointCache endpoints = new EndpointCache();

	public RestoreComponentDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * マッピング対象となる　CORBAコンポーネントの一覧を設定します。
	 */
	public void setCorbaComponents(List<CorbaComponent> list) {
		if (list == null) {
			return;
		}
		for (CorbaComponent comp : list) {
			TargetInfo target = new TargetInfo(comp);
			target.verifyStatus();
			this.targetList.add(target);
			String epName = target.getEndpointName();
			if (epName == null) {
				continue;
			}
			Endpoint ep = this.endpoints.get(epName);
			RTC.RTObject rtc = ep.getComponent(target.getName());
			if (rtc != null) {
				target.setTarget(rtc);
			}
		}
	}

	/**
	 * マッピングの設定結果一覧を取得します。
	 */
	public List<MappingResult> getMappingResultList() {
		List<MappingResult> ret = new ArrayList<>();
		for (TargetInfo target : this.targetList) {
			ret.add(target.getMappingResult());
		}
		return ret;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		GridLayout gl;
		GridData gd;

		Composite mainComposite = new Composite(dialogArea, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);
		mainComposite.setFont(parent.getFont());

		Composite detailComposite = new Composite(dialogArea, SWT.NONE);
		gl = new GridLayout(1, false);
		gd = new GridData(GridData.FILL_BOTH);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setFont(parent.getFont());

		{
			Label label = new Label(mainComposite, SWT.NONE);
			label.setText(LABEL_MAPPING_MESSAGE);

			this.tableViewer = new TableViewer(mainComposite,
					SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
			this.tableViewer.setContentProvider(new ArrayContentProvider());
			this.tableViewer
					.setColumnProperties(new String[] { COL_COMPONENT,
							COL_TYPE, COL_MANAGER, COL_ENDPOINT, COL_TARGET,
							COL_STATUS });
			this.tableViewer.setLabelProvider(new TargetLabelProvider());
			this.tableViewer
					.addSelectionChangedListener(new ISelectionChangedListener() {
						@Override
						public void selectionChanged(SelectionChangedEvent event) {
							StructuredSelection selection = (StructuredSelection) event
									.getSelection();
							LOGGER.trace(
									"Restore: tableViewer.selectionChanged: selectoin=<{}>",
									selection);
							selectedTarget = (TargetInfo) selection
									.getFirstElement();
							refreshData();
						}
					});

			this.table = this.tableViewer.getTable();
			gl = new GridLayout(1, false);
			gd = new GridData();
			gd.verticalAlignment = SWT.FILL;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessVerticalSpace = true;
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			gd.heightHint = 250;
			this.table.setLayout(gl);
			this.table.setLayoutData(gd);
			this.table.setLinesVisible(true);
			this.table.setHeaderVisible(true);

			TableColumn col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_COMPONENT);
			col.setWidth(150);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_TYPE);
			col.setWidth(150);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_MANAGER);
			col.setWidth(100);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_ENDPOINT);
			col.setWidth(120);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_TARGET);
			col.setWidth(150);
			col = new TableColumn(this.table, SWT.NONE);
			col.setText(COL_STATUS);
			col.setWidth(200);
		}
		{
			gl = new GridLayout();
			gl.numColumns = 3;
			detailComposite.setLayout(gl);

			Label compLabel = new Label(detailComposite, SWT.NONE);
			compLabel.setText(LABEL_COMPONENT);

			this.componentNameLabel = new Label(detailComposite, SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.componentNameLabel.setLayoutData(gd);
			this.componentNameLabel.setBackground(getColor(COLOR_WHITE));

			this.typeNameLabel = new Label(detailComposite, SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.typeNameLabel.setLayoutData(gd);
			this.typeNameLabel.setBackground(getColor(COLOR_WHITE));

			Label mgrLabel = new Label(detailComposite, SWT.NONE);
			mgrLabel.setText(LABEL_MANAGER);

			this.managerNameText = new Text(detailComposite, SWT.SINGLE
					| SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.managerNameText.setLayoutData(gd);
			this.managerNameText.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					LOGGER.trace("Restore: managerName.focusLost: event=<{}>",
							e);
					notifyModified();
				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});

			this.endpointNameText = new Text(detailComposite, SWT.SINGLE
					| SWT.BORDER);
			gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.endpointNameText.setLayoutData(gd);
			this.endpointNameText.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					LOGGER.trace("Restore: endpointName.focusLost: event=<{}>",
							e);
					String epName = endpointNameText.getText();
					Endpoint ep = endpoints.get(epName);
					targetCombo.removeAll();
					for (String comp : ep.getComponentNames()) {
						targetCombo.add(comp);
					}
					notifyModified();
				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});

			Label tgtLabel = new Label(detailComposite, SWT.NONE);
			tgtLabel.setText(LABEL_TARGET);

			this.targetCombo = new Combo(detailComposite, SWT.DROP_DOWN);
			gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.targetCombo.setLayoutData(gd);

			this.targetCombo.select(0);
			this.targetCombo.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					LOGGER.trace("Restore: targetCombo.focusLost: event=<{}>",
							e);
					notifyModified();
				}

				@Override
				public void focusGained(FocusEvent e) {
				}
			});

			Label stLabel = new Label(detailComposite, SWT.NONE);
			stLabel.setText(LABEL_STATUS);

			this.statusLabel = new Label(detailComposite, SWT.BORDER);
			gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = SWT.FILL;
			gd.grabExcessHorizontalSpace = true;
			this.statusLabel.setLayoutData(gd);
			this.statusLabel.setBackground(getColor(COLOR_WHITE));
		}

		buildData();

		return dialogArea;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		return composite;
	}

	/** テーブルの表示内容を構築 */
	void buildData() {
		this.tableViewer.setInput(this.targetList);
	}

	/** 詳細項目の変更を通知します */
	void notifyModified() {
		if (this.selectedTarget == null) {
			return;
		}

		String managerName = this.managerNameText.getText();
		String epName = this.endpointNameText.getText();
		String targetName = this.targetCombo.getText();
		Endpoint ep = this.endpoints.get(epName);
		RTC.RTObject rtc = ep.getComponent(targetName);

		this.selectedTarget.setManagerName(managerName);
		this.selectedTarget.setEndpointName(epName);
		this.selectedTarget.setTargetName(targetName);
		this.selectedTarget.setEndpoint(ep);
		this.selectedTarget.setTarget(rtc);

		this.selectedTarget.verifyStatus();
		if (this.selectedTarget.getStatus() != null) {
			this.statusLabel.setText(this.selectedTarget.getStatus());
		}

		this.tableViewer.refresh();
	}

	/** テーブル上の行選択を詳細項目へ反映します */
	void refreshData() {
		if (this.selectedTarget == null) {
			this.componentNameLabel.setText("");
			this.typeNameLabel.setText("");
			this.managerNameText.setText("");
			this.endpointNameText.setText("");
			this.targetCombo.select(0);
			this.statusLabel.setText("");
		} else {
			if (this.selectedTarget.getName() != null) {
				this.componentNameLabel.setText(this.selectedTarget.getName());
			}
			if (this.selectedTarget.getType() != null) {
				this.typeNameLabel.setText(this.selectedTarget.getType());
			}
			if (this.selectedTarget.getManagerName() != null) {
				this.managerNameText.setText(this.selectedTarget
						.getManagerName());
			}
			if (this.selectedTarget.getEndpointName() != null) {
				this.endpointNameText.setText(this.selectedTarget
						.getEndpointName());
			}
			if (this.selectedTarget.getTargetName() != null) {
				this.targetCombo.setText(this.selectedTarget.getTargetName());
			}
			if (this.selectedTarget.getStatus() != null) {
				this.statusLabel.setText(this.selectedTarget.getStatus());
			}
		}
	}

	//

	/**
	 * リストア対象情報
	 */
	public static class TargetInfo {

		private String name;
		private String type;
		private String managerName;
		private String endpointName;
		private String targetName;
		private String status;

		private Endpoint endpoint;
		private CorbaComponent component;

		public TargetInfo(CorbaComponent component) {
			this.component = component;
			this.name = component.getInstanceNameL();
			this.type = component.getProperty(PROP_IMPLEMENTATION_ID);
			this.managerName = component.getProperty(PROP_MANAGER_NAME);
			this.endpointName = component.getProperty(PROP_CORBA_ENDPOINTS);
			//
			if (this.endpointName == null) {
				// エンドポイントのプロパティ設定がない場合は、パスURIから取得(既存互換)
				String path = component.getPathId();
				if (path != null && !path.isEmpty()) {
					path = path.substring(0, path.indexOf("/"));
					this.endpointName = path;
				}
			}
		}

		public String getName() {
			return this.name;
		}

		public String getType() {
			return this.type;
		}

		public String getManagerName() {
			return this.managerName;
		}

		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}

		public String getEndpointName() {
			return this.endpointName;
		}

		public void setEndpointName(String endpointName) {
			this.endpointName = endpointName;
		}

		public void setEndpoint(Endpoint endpoint) {
			this.endpoint = endpoint;
		}

		public String getTargetName() {
			return this.targetName;
		}

		public void setTargetName(String targetName) {
			this.targetName = targetName;
		}

		public void setTarget(RTC.RTObject rtc) {
			if (this.component != null) {
				this.component.setCorbaObject(rtc);
			}
		}

		public MappingResult getMappingResult() {
			if (this.component != null) {
				this.component.setProperty(PROP_CORBA_ENDPOINTS,
						this.endpointName);
				this.component.setProperty(PROP_MANAGER_NAME, this.managerName);
			}
			RTCManager manager = null;
			if (this.endpoint != null) {
				manager = this.endpoint.getManager();
			}
			MappingResult ret = new MappingResult(this.component, manager);
			return ret;
		}

		public String getStatus() {
			return this.status;
		}

		public void verifyStatus() {
			this.status = "";
			if (this.endpointName == null || this.endpointName.isEmpty()) {
				// エンドポイント名が未設定
				this.status = MSG_STATUS_NO_EP_NAME;
				return;
			}
			if (this.endpoint == null
					|| EndpointCache.NULL_ENDPOINT.equals(this.endpoint)) {
				// エンドポイントへアクセス不可
				this.status = MSG_STATUS_EP_UNREACHABLE;
				return;
			}
			if (this.targetName == null || this.targetName.isEmpty()) {
				// コンポーネント生成が必要
				this.status = MSG_STATUS_NEED_CREATE;
				return;
			}
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "<" + this.name + "|"
					+ this.type + "|" + this.managerName + "|"
					+ this.endpointName + "|" + this.targetName + "|"
					+ this.status + ">";
		}

	}

	/**
	 * コンポーネントのマッピング結果を表します。
	 */
	public static class MappingResult {

		private CorbaComponent component;
		private RTCManager manager;

		public MappingResult(CorbaComponent component, RTCManager manager) {
			this.component = component;
			this.manager = manager;
		}

		public CorbaComponent getComponent() {
			return this.component;
		}

		public RTCManager getManager() {
			return this.manager;
		}

		public boolean isMapped() {
			return (this.component != null && this.component
					.getCorbaObjectInterface() != null);
		}

		public boolean hasManager() {
			return (this.manager != null);
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "<" + this.component + "|"
					+ this.manager + ">";
		}

	}

	/**
	 * エンドポイントの検索・キャッシュを表します。
	 */
	public static class EndpointCache {

		/** エンドポイントの NullObject */
		public static Endpoint NULL_ENDPOINT = new Endpoint(null);

		private Map<String, Endpoint> cache = new HashMap<>();

		public Endpoint get(String endpoint) {
			Endpoint ret = this.cache.get(endpoint);
			if (ret != null) {
				return ret;
			}
			RTM.Manager remote = null;
			if (endpoint != null) {
				try {
					remote = CORBAHelper.ns().findManager(endpoint);
				} catch (RuntimeException e) {
					remote = null;
				}
			}
			if (remote == null) {
				this.cache.put(endpoint, NULL_ENDPOINT);
				return NULL_ENDPOINT;
			}
			RTCManager manager = ManagerFactory.eINSTANCE.createRTCManager();
			manager.setCorbaObject(remote);
			ret = new Endpoint(manager);
			this.cache.put(endpoint, ret);
			return ret;
		}

	}

	/**
	 * エンドポイント(マネージャ)を表します。
	 */
	public static class Endpoint {

		private RTCManager remote;
		private List<String> managers = null;
		private List<String> components = null;
		private Map<String, RTC.RTObject> objects = null;

		Endpoint(RTCManager remote) {
			this.remote = remote;
		}

		public RTCManager getManager() {
			return this.remote;
		}

		/**
		 * エンドポイントに属する起動中のコンポーネント名一覧を取得します。
		 */
		public List<String> getComponentNames() {
			if (this.components != null) {
				return this.components;
			}
			this.components = new ArrayList<>();
			this.objects = new HashMap<>();
			if (this.remote == null) {
				return this.components;
			}
			for (RTC.RTObject rtc : this.remote.getComponentsR()) {
				RTC.ComponentProfile prof = rtc.get_component_profile();
				this.components.add(prof.instance_name);
				this.objects.put(prof.instance_name, rtc);
			}
			return this.components;
		}

		/**
		 * コンポーネント名に対する CORBAオブジェクトを取得します。
		 */
		public RTC.RTObject getComponent(String name) {
			if (this.objects != null) {
				return this.objects.get(name);
			}
			return null;
		}

		/**
		 * エンドポイントのマネージャに属するスレーブマネージャ名一覧を取得します。
		 */
		public List<String> getManagerNames() {
			if (this.managers != null) {
				return this.managers;
			}
			this.managers = new ArrayList<>();
			if (this.remote == null) {
				return this.managers;
			}
			this.remote.getSlaveManagersR();
			for (String name : this.remote.getSlaveManagerNames()) {
				this.managers.add(name);
			}
			return this.managers;
		}

	}

	public class TargetLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			TargetInfo entry = (TargetInfo) element;
			if (columnIndex == 0) {
				return entry.getName();
			} else if (columnIndex == 1) {
				return entry.getType();
			} else if (columnIndex == 2) {
				return entry.getManagerName();
			} else if (columnIndex == 3) {
				return entry.getEndpointName();
			} else if (columnIndex == 4) {
				return entry.getTargetName();
			} else if (columnIndex == 5) {
				return entry.getStatus();
			}
			return "";
		}

	}

}
