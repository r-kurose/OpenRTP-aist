package jp.go.aist.rtm.systemeditor.extension;

import java.util.ArrayList;
import java.util.List;

import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialogData;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;

public abstract class RehabilitateComponentExtension {

	protected Component component;
	protected SystemDiagram diagram;

	public void setComponent(Component component) {
		this.component = component;
	}

	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	/**
	 * @return 復元可能な場合は true
	 */
	public abstract boolean canRehabilitate();

	/**
	 * @return 復元済みの EMFコンポーネント
	 */
	public abstract Component rehabilitate();

	/**
	 * CORBA用ユーティリティ
	 */
	public static class CORBA {
		/**
		 * CORBAコンポーネントに対する CORBAオブジェクトを解決します
		 * 
		 * @param comp
		 *            EMFコンポーネント(CORBA)
		 * @param diagram
		 *            ダイアグラム
		 * @return 解決済みの CORBAオブジェクトをセットした EMFコンポーネント(CORBA)
		 */
		public static CorbaComponent rehabilitate(CorbaComponent comp,
				SystemDiagram diagram) {
			if (comp.getCorbaObject() != null
					&& SynchronizationSupport.ping(comp.getCorbaObject())) {
				return comp;
			}
			org.omg.CORBA.Object remote = resolveCorbaName(comp.getPathId());
			RTC.RTObject narrow = RTC.RTObjectHelper.narrow(remote);
			comp.setCorbaObject(narrow);
			return comp;
		}

		/**
		 * CORBAコンポーネントに対する CORBAオブジェクトを生成します
		 * 
		 * @param comp
		 *            EMFコンポーネント(CORBA)
		 * @param diagram
		 *            ダイアグラム
		 * @return 生成した CORBAオブジェクトをセットした EMFコンポーネント(CORBA)
		 */
		public static CorbaComponent createComponent(CorbaComponent comp,
				SystemDiagram diagram) {
			RTM.Manager manager = findManager(comp.getPathId());
			if (manager == null) {
				return comp;
			}
			String param = NewCompositeComponentDialogData.getParam(comp
					.getCompositeTypeL(), comp.getInstanceNameL(),
					getExportedPortString(comp, diagram));
			RTC.RTObject remote = manager.create_component(param);
			comp.setCorbaObject(remote);
			return comp;
		}

		/**
		 * CORBAコンポーネントが複合RTCの場合に、子RTCを設定します
		 * 
		 * @param comp
		 *            EMFコンポーネント(CORBA)
		 * @param diagram
		 *            ダイアグラム
		 * @return 子RTCを設定した EMFコンポーネント(CORBA)
		 */
		public static CorbaComponent setCompositeMembers(CorbaComponent comp,
				SystemDiagram diagram) {
			if (comp.getCorbaObject() == null) {
				return comp;
			}
			RTC.RTObject remote = comp.getCorbaObjectInterface();
			// 子RTCの CORBAオブジェクトリストを生成
			List<_SDOPackage.SDO> sdolist = new ArrayList<_SDOPackage.SDO>();
			for (Component o : comp.getComponents()) {
				CorbaComponent c = (CorbaComponent) o;
				rehabilitate(c, diagram);
				sdolist.add(c.getCorbaObjectInterface());
			}
			sdolist.toArray(new _SDOPackage.SDO[0]);
			//
			try {
				remote.get_owned_organizations()[0].set_members(sdolist
						.toArray(new _SDOPackage.SDO[0]));
			} catch (Exception e) {
				remote.exit();
				comp.setCorbaObject(null);
			}
			return comp;
		}

		/**
		 * CORBAコンポーネントが複合RTCの場合の公開ポート情報(exported_ports)を取得します
		 * 
		 * @param comp
		 *            EMFコンポーネント(CORBA)
		 * @param diagram
		 *            ダイアグラム
		 * @return 公開ポート情報(exported_ports)
		 */
		public static String getExportedPortString(CorbaComponent comp,
				SystemDiagram diagram) {
			org.openrtp.namespaces.rts.version02.Component orig = RtsProfileHandler
					.findComponent(comp, diagram.getProfile().getComponents());
			String activeId = orig.getActiveConfigurationSet();
			for (ConfigurationSet cs : orig.getConfigurationSets()) {
				if (!cs.getId().equals(activeId)) {
					continue;
				}
				for (ConfigurationData cd : cs.getConfigurationData()) {
					if (cd.getName().equals("exported_ports")) {
						return cd.getData();
					}
				}
			}
			return "";
		}

		/** パスIDを元に CORBAの名前解決を行います */
		public static org.omg.CORBA.Object resolveCorbaName(String path) {
			org.omg.CORBA.Object result = NameServerAccesser.getInstance()
					.getObjectFromPathId(path);
			return result;
		}

		/** パスIDを元に RTM.Managerを検索します */
		public static RTM.Manager findManager(String path) {
			int index = path.lastIndexOf("/");
			String cid = path.substring(0, index);
			return NameServerAccesser.getInstance()
					.getManagerFromContextId(cid);
		}
	}

}
