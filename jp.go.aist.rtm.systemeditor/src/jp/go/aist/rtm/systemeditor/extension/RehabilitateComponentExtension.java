package jp.go.aist.rtm.systemeditor.extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.corba.CORBAHelper;
import jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CORBAException;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

public abstract class RehabilitateComponentExtension {

	private static final Logger LOGGER = LoggerFactory.getLogger(RehabilitateComponentExtension.class);

	protected Component component;
	protected SystemDiagram diagram;

	public void setComponent(Component component) {
		this.component = component;
	}

	public void setDiagram(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	/**
	 * この拡張が対象の EMFコンポーネントを復元可能か判定します。
	 * 
	 * @return 復元可能な場合は true
	 */
	public abstract boolean canRehabilitate();

	/**
	 * EMFコンポーネントに対するリモートオブジェクトを復元します。
	 * 
	 * @param doCreate
	 *            リモートオブジェクトが存在しないときに生成する場合はtrue
	 * @return 復元された EMFコンポーネント
	 */
	public abstract Component rehabilitateComponent(boolean doCreate);

	/**
	 * EMFコンポーネントの構造を復元します (複合コンポーネント)。
	 * 
	 * @return 復元された EMFコンポーネント
	 */
	public abstract Component rehabilitateStructure();

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
		public static CorbaComponent rehabilitate(CorbaComponent comp, SystemDiagram diagram) {
			if (CORBAHelper.factory().isAvailable(comp)) {
				return comp;
			}
			org.omg.CORBA.Object remote = CORBAHelper.ns().resolve(comp.getPathId());
			if (remote != null) {
				RTC.RTObject narrow = RTC.RTObjectHelper.narrow(remote);
				comp.setCorbaObject(narrow);
			}
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
		public static CorbaComponent createComponent(CorbaComponent comp, SystemDiagram diagram) {
			try {
				RTC.RTObject rtobj = null;
				if (comp.isCompositeComponent()) {
					rtobj = CORBAHelper.factory().createCompositeRTObject(comp, diagram);
				} else {
					rtobj = CORBAHelper.factory().createRTObject(comp, diagram);
				}
				comp.setCorbaObject(rtobj);
			} catch (CORBAException e) {
				LOGGER.error("Fail to create component: composite=<{}> comp=<{}>", comp.isCompositeComponent(), comp);
				LOGGER.error("Fail to create component:", e);
			}
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
		public static CorbaComponent setCompositeMembers(CorbaComponent comp, SystemDiagram diagram) {
			try {
				CORBAHelper.factory().setCompositeMembers(comp);
			} catch (CORBAException e) {
				LOGGER.error("Fail to set composite members: comp=<{}>", comp);
				LOGGER.error("Fail to set composite members:", e);
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
		@Deprecated
		public static String getExportedPortString(CorbaComponent comp, SystemDiagram diagram) {
			return CORBAHelper.factory().findConfiguration("exported_ports", comp, diagram);
		}

		/** パスIDを元に CORBAの名前解決を行います */
		@Deprecated
		public static org.omg.CORBA.Object resolveCorbaName(String path) {
			return CORBAHelper.ns().resolve(path);
		}

		/** パスIDを元に RTM.Managerを検索します */
		@Deprecated
		public static RTM.Manager findManager(String path) {
			return CORBAHelper.ns().findManager(path);
		}

	}

}
