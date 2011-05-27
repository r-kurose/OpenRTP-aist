package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.extension.CreateCompositeComponentExtension;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialogData;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.profiles.util.IDUtil;

public class CompositeComponentCreator {

	static final String EXTENTION_POINT_NAME = "createcompositecomponent";
	static List<CreateCompositeComponentExtension> creators;

	static List<String> ONLINE_COMPOSITE_TYPES;
	static List<String> OFFLINE_COMPOSITE_TYPES;

	static Map<String, CreateCompositeComponentExtension> ONLINE_CREATORS;
	static Map<String, CreateCompositeComponentExtension> OFFLINE_CREATORS;

	CreateCompositeComponentExtension.Base base;

	boolean containGroupingCompositeComponent;

	String message = "";

	public CompositeComponentCreator() {
		if (creators == null) {
			buildCreator();
		}
		this.base = new CreateCompositeComponentExtension.Base();
	}

	public boolean isOnline() {
		return (base.editor != null && base.editor.isOnline());
	}

	public void setTargetEditor(AbstractSystemDiagramEditor editor) {
		this.base.editor = editor;
	}

	public void setManager(RTCManager manager) {
		this.base.manager = manager;
	}

	public void setCompositeType(String type) {
		this.base.compositeType = type;
	}

	public void setInstanceName(String name) {
		this.base.instanceName = name;
	}

	public void setComponents(List<Component> comps) {
		this.base.components = comps;
		this.containGroupingCompositeComponent = false;
		for (Component c : this.base.components) {
			if (c.isGroupingCompositeComponent()) {
				this.containGroupingCompositeComponent = true;
				break;
			}
		}
	}

	public void setExportedPorts(String exports) {
		this.base.exportedPorts = exports;
	}

	public void setPathId(String path) {
		this.base.pathId = path;
	}

	public List<String> getCompositeTypes() {
		if (base.editor.isOnline()) {
			return ONLINE_COMPOSITE_TYPES;
		} else {
			return OFFLINE_COMPOSITE_TYPES;
		}
	}

	public String getMessage() {
		return this.message;
	}

	/**
	 * 複合RTC生成のポップアップメニューが有効かを判定します。(拡張可)
	 * 
	 * @return 複合RTC生成のポップアップが有効な場合は true
	 */
	public boolean isActionEnabled() {
		for (CreateCompositeComponentExtension ext : creators) {
			if (!ext.isActionEnabled(base)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 設定ダイアログを開く前に、複合RTCが生成可能かを判定します。(拡張可)
	 * 
	 * @return 複合RTCが生成可能な場合は true
	 */
	public boolean canCreate() {
		for (CreateCompositeComponentExtension ext : creators) {
			if (!ext.canCreate(base)) {
				message = ext.getMessage();
				return false;
			}
		}
		return true;
	}

	/**
	 * 複合RTC生成のための設定が有効か判定します。(拡張可)
	 * 
	 * @return 複合RTCが生成可能な場合はtrue
	 */
	public boolean isValid() {
		message = "";
		if (base.compositeType == null || base.compositeType.isEmpty()) {
			return false;
		}
		if (containGroupingCompositeComponent
				&& !base.compositeType.equals(Component.COMPOSITETYPE_GROUPING)) {
			message = NewCompositeComponentDialog.MESSAGE_CONTAIN_GROUPING_FAIL;
			return false;
		}
		for (CreateCompositeComponentExtension ext : creators) {
			if (!ext.isValid(base)) {
				message = ext.getMessage();
				return false;
			}
		}
		return true;
	}

	/**
	 * 複合RTCを生成します。(拡張可)
	 * 
	 * 複合RTCの生成は親RTCの生成、子RTCの追加の２つのジョブとして実行され、タイムアウト制御を行います。
	 * 
	 * @return 生成された複合RTCのオブジェクト
	 */
	public Component create() {
		int defaultTimeout = ToolsCommonPreferenceManager.getInstance()
				.getDefaultTimeout(
						ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
		TimeoutWrapper wrapper = new TimeoutWrapper(defaultTimeout);
		//
		final CreateCompositeComponentExtension creator = findCreator();
		if (creator == null) {
			return null;
		}
		//
		wrapper.setJob(new TimeoutWrappedJob() {
			@Override
			protected Object executeCommand() {
				return creator.createComponent(base);
			}
		});
		final Component comp = (Component) wrapper.start();
		if (comp == null) {
			return null;
		}
		//
		wrapper.setJob(new TimeoutWrappedJob() {
			@Override
			protected Object executeCommand() {
				return creator.setCompositeMembers(base, comp);
			}
		});
		if (wrapper.start() == null && comp instanceof CorbaComponent) {
			final CorbaComponent corbaComp = (CorbaComponent) comp;
			wrapper.setJob(new TimeoutWrappedJob() {
				@Override
				protected Object executeCommand() {
					return corbaComp.exitR();
				}
			});
			wrapper.start();
		}
		return comp;
	}

	CreateCompositeComponentExtension findCreator() {
		if (base.editor.isOnline()) {
			return ONLINE_CREATORS.get(base.compositeType);
		} else {
			return OFFLINE_CREATORS.get(base.compositeType);
		}
	}

	static void buildCreator() {
		creators = new ArrayList<CreateCompositeComponentExtension>();
		//
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof CreateCompositeComponentExtension) {
						creators.add((CreateCompositeComponentExtension) obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		// デフォルトのファクトリ
		CreateCompositeComponentExtension ext = new CreateCompositeComponentExtension() {
			final String[] COMPOSITE_TYPES = {
					Component.COMPOSITETYPE_PERIODIC_EC_SHARED,
					Component.COMPOSITETYPE_PERIODIC_STATE_SHARED,
					Component.COMPOSITETYPE_GROUPING };

			@Override
			public List<String> getOfflineCompositeTypes() {
				return Arrays.asList(COMPOSITE_TYPES);
			}

			@Override
			public List<String> getOnlineCompositeTypes() {
				return Arrays.asList(COMPOSITE_TYPES);
			}

			@Override
			public boolean isValid(Base base) {
				if (!super.isValid(base)) {
					return false;
				}
				if (base.editor != null && base.editor.isOnline()
						&& base.manager == null) {
					return false;
				}
				return true;
			}

			@Override
			public Component createComponent(Base base) {
				if (!base.editor.isOnline()
						|| Component.COMPOSITETYPE_GROUPING
								.equals(base.compositeType)) {
					Component composite = ComponentFactory.eINSTANCE
							.createComponentSpecification();
					composite.setInstanceNameL(base.instanceName);
					composite.setVenderL("");
					composite.setCategoryL("composite." + base.compositeType);
					composite.setTypeNameL(base.compositeType + "Composite");
					composite.setVersionL("");
					IDUtil.RTCId compId = new IDUtil.RTCId(composite
							.getVenderL(), composite.getCategoryL(), composite
							.getTypeNameL(), composite.getVersionL());
					composite.setComponentId(compId.toString());
					composite.setPathId(base.pathId);
					// オフラインでGrouping複合RTC以外の場合はECを追加
					if (!Component.COMPOSITETYPE_GROUPING
							.equals(base.compositeType)) {
						ExecutionContext ec = ComponentFactory.eINSTANCE
								.createExecutionContext();
						ec.setKindL(ExecutionContext.KIND_PERIODIC);
						ec.setRateL(1000.0);
						ec.setOwner(composite);
						composite.getExecutionContexts().add(ec);
						composite.getExecutionContextHandler().sync();
					}
					// 空のConfigurationSet設定
					ConfigurationSet configSet = ComponentFactory.eINSTANCE
							.createConfigurationSet();
					composite.getConfigurationSets().add(configSet);
					composite.setActiveConfigurationSet(configSet);
					configSet.setId("default");

					NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
					nv.setName("exported_ports");
					nv.setValue(base.exportedPorts);
					configSet.getConfigurationData().add(nv);

					return composite;
				} else {
					String param = NewCompositeComponentDialogData.getParam(
							base.compositeType, base.instanceName,
							base.exportedPorts);
					Component composite = base.manager.createComponentR(param);
					String childPathId = base.components.get(0).getPathId();
					composite.setPathId(childPathId.substring(0, childPathId
							.lastIndexOf("/") + 1)
							+ base.instanceName + ".rtc");
					return composite;
				}
			}

			@Override
			public Component setCompositeMembers(Base base, Component comp) {
				SystemEditorWrapperFactory.getInstance()
						.getSynchronizationManager()
						.assignSynchonizationSupport(comp);
				comp.setComponentsR(base.components);
				// 同期
				comp.synchronizeRemoteAttribute(null);
				comp.synchronizeLocalAttribute(null);
				comp.synchronizeLocalReference();
				// Constraintを設定する
				if (comp.getConstraint() == null) {
					Rectangle rectangle = new Rectangle();
					Component ac = base.components.get(0);
					rectangle.setX(ac.getConstraint().getX());
					rectangle.setY(ac.getConstraint().getY());
					rectangle.setHeight(ac.getConstraint().getHeight());
					rectangle.setWidth(ac.getConstraint().getWidth());
					comp.setConstraint(rectangle);
				}
				return comp;
			}
		};
		creators.add(0, ext);
		//
		ONLINE_COMPOSITE_TYPES = new ArrayList<String>();
		OFFLINE_COMPOSITE_TYPES = new ArrayList<String>();
		ONLINE_CREATORS = new HashMap<String, CreateCompositeComponentExtension>();
		OFFLINE_CREATORS = new HashMap<String, CreateCompositeComponentExtension>();
		for (CreateCompositeComponentExtension ce : creators) {
			for (String s : ce.getOnlineCompositeTypes()) {
				if (!ONLINE_COMPOSITE_TYPES.contains(s)) {
					ONLINE_COMPOSITE_TYPES.add(s);
				}
				ONLINE_CREATORS.put(s, ce);
			}
			for (String s : ce.getOfflineCompositeTypes()) {
				if (!OFFLINE_COMPOSITE_TYPES.contains(s)) {
					OFFLINE_COMPOSITE_TYPES.add(s);
				}
				OFFLINE_CREATORS.put(s, ce);
			}
		}
	}

}
