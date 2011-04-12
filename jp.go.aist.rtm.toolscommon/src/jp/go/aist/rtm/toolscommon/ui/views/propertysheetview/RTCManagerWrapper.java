package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * マネージャのラッパクラス
 */
public class RTCManagerWrapper {
	public static final String DISP_COMPONENTS = Messages.getString("RTCManagerWrapper.disp.components");

	public static final String DISP_LOADABLE_MODULES = Messages.getString("RTCManagerWrapper.disp.loadable_modules");

	public static final String DISP_LOADED_MODULES = Messages.getString("RTCManagerWrapper.disp.loaded_modules");

	private EObject manager;

	/**
	 * コンストラクタ
	 * 
	 * @param manager
	 *            ドメインモデル
	 */
	public RTCManagerWrapper(EObject manager) {
		this.manager = manager;
	}

	/**
	 * マネージャを取得する
	 * 
	 * @return マネージャのオブジェクト
	 */
	public EObject getManager() {
		return manager;
	}

	/**
	 * マネージャを設定する
	 * 
	 * @param manager
	 *            マネージャのオブジェクト
	 */
	public void setManager(EObject manager) {
		this.manager = manager;
	}

	/**
	 * 起動中コンポーネント名一覧の子オブジェクトを取得します。
	 * 
	 * @return コンポーネント名一覧の子オブジェクト
	 */
	public Child getComponentsChild() {
		return new Child((RTCManager) this.manager, Child.COMPONENTS);
	}

	/**
	 * ロード可能モジュール名一覧の子オブジェクトを取得します。
	 * 
	 * @return ロード可能モジュール名一覧の子オブジェクト
	 */
	public Child getLoadableModulesChild() {
		return new Child((RTCManager) this.manager, Child.LOADABLE_MODULES);
	}

	/**
	 * ロード済みモジュール名一覧の子オブジェクトを取得します。
	 * 
	 * @return ロード済みモジュール名一覧の子オブジェクト
	 */
	public Child getLoadedModulesChild() {
		return new Child((RTCManager) this.manager, Child.LOADED_MODULES);
	}

	/**
	 * マネージャの子オブジェクト用のプロパティ・ソース
	 */
	public class PropertySource implements IPropertySource {

		private List<String> idList = new ArrayList<String>();

		private Map<String, String> nameMap = new HashMap<String, String>();

		private Map<String, String> valueMap = new HashMap<String, String>();

		/**
		 * プロパティを追加します。
		 * 
		 * @param id
		 *            プロパティID
		 * @param name
		 *            プロパティ表示名
		 * @param value
		 *            プロパティ値
		 */
		public void addProperty(String id, String name, String value) {
			this.idList.add(id);
			if (name != null) {
				this.nameMap.put(id, name);
			}
			if (value != null) {
				this.valueMap.put(id, value);
			}
		}

		public Object getEditableValue() {
			return null;
		}

		public IPropertyDescriptor[] getPropertyDescriptors() {
			List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
			for (String id : this.idList) {
				IPropertyDescriptor d = new TextPropertyDescriptor(id,
						this.nameMap.get(id));
				result.add(d);
			}
			return result.toArray(new IPropertyDescriptor[0]);
		}

		public Object getPropertyValue(Object id) {
			for (String i : this.idList) {
				if (i.equals(id)) {
					return this.valueMap.get(id);
				}
			}
			return null;
		}

		public boolean isPropertySet(Object id) {
			return false;
		}

		public void resetPropertyValue(Object id) {
		}

		public void setPropertyValue(Object id, Object value) {
		}
	}

	/**
	 * マネージャの子オブジェクト用のワークベンチ・アダプタ
	 */
	public class WorkbenchAdapter implements IWorkbenchAdapter {
		public Object[] getChildren(Object o) {
			return null;
		}

		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}

		public String getLabel(Object o) {
			if (o instanceof Child) {
				return ((Child) o).getLabel();
			}
			return null;
		}

		public Object getParent(Object o) {
			return null;
		}
	};

	/**
	 * マネージャの子オブジェクトを表します。
	 */
	public class Child implements IAdaptable {
		public static final String COMPONENTS = "COMPONENTS";

		public static final String LOADABLE_MODULES = "LOADABLE_MODULES";

		public static final String LOADED_MODULES = "LOADED_MODULES";

		private RTCManager manager;

		/** 子オブジェクトのキー(COMPONENTS/LOADABLE_MODULES/LOADED_MODULES) */
		private String key;

		PropertySource source;

		public Child(RTCManager manager, String key) {
			this.manager = manager;
			this.key = key;
			this.source = null;
		}

		/**
		 * この子オブジェクトの表示名を返します。
		 * 
		 * @return 子オブジェクトの表示名(Components/Loadable Modules/Loaded Modules)
		 */
		public String getLabel() {
			if (this.key.equals(COMPONENTS)) {
				return DISP_COMPONENTS;
			} else if (this.key.equals(LOADABLE_MODULES)) {
				return DISP_LOADABLE_MODULES;
			} else if (this.key.equals(LOADED_MODULES)) {
				return DISP_LOADED_MODULES;
			}
			return "";
		}

		/**
		 * この子オブジェクトのプロパティ・ソースを返します。
		 * 
		 * @return プロパティ・ソース
		 */
		@SuppressWarnings("unchecked")
		public IPropertySource getPropertySource() {
			if (this.source != null) {
				return this.source;
			}
			this.source = new PropertySource();
			EList list = null;
			if (this.key.equals(COMPONENTS)) {
				list = this.manager.getComponentInstanceNames();
			} else if (this.key.equals(LOADABLE_MODULES)) {
				list = this.manager.getLoadableModuleFileNames();
			} else if (this.key.equals(LOADED_MODULES)) {
				list = this.manager.getLoadedModuleFileNames();
			}
			if (list == null) {
				return this.source;
			}
			for (Object o : list) {
				if (o instanceof String) {
					String s = (String) o;
					this.source.addProperty(this.key + "." + s, "", s);
				}
			}
			return this.source;
		}

		@SuppressWarnings("unchecked")
		public Object getAdapter(Class adapter) {
			if (IWorkbenchAdapter.class.equals(adapter)) {
				return new WorkbenchAdapter();
			}
			return null;
		}
	}
}
