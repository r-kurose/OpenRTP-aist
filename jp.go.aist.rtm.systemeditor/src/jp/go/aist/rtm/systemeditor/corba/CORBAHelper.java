package jp.go.aist.rtm.systemeditor.corba;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CORBA操作に関するヘルパー
 */
public class CORBAHelper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CORBAHelper.class);

	private static NS _ns = new NS();
	private static Factory _factory = new Factory();

	/**
	 * CORBA ネーミングサービスに関するヘルパーを取得します (Singleton)。
	 * 
	 * @return ネーミングサービスに関するヘルパー
	 */
	public static NS ns() {
		return _ns;
	}

	/**
	 * CORBA コンポーネント生成に関するヘルパーを取得します (Singleton)。
	 * 
	 * @return コンポーネント生成に関するヘルパー
	 */
	public static Factory factory() {
		return _factory;
	}

	/**
	 * ネーミングサービスに関するヘルパー
	 */
	public static class NS {

		private NS() {
		}

		/**
		 * パスから CORBA オブジェクトを解決します。
		 * 
		 * @param path
		 *            CORBAオブジェクトのパス
		 * @return CORBAオブジェクト
		 */
		public org.omg.CORBA.Object resolve(String path) {
			org.omg.CORBA.Object result = NameServerAccesser.getInstance()
					.getObjectFromPathId(path);
			return result;
		}

		/**
		 * パスのコンテキスト部に対するマネージャを検索します。
		 * 
		 * @param path
		 *            CORBAオブジェクトのパス
		 * @return マネージャ
		 */
		public RTM.Manager findManager(String path) {
			int index = path.lastIndexOf("/");
			String cid = path.substring(0, index);
			return NameServerAccesser.getInstance()
					.getManagerFromContextId(cid);
		}

	}

	/**
	 * コンポーネント生成に関するヘルパー
	 */
	public static class Factory {

		private Factory() {
		}

		/**
		 * CORBAコンポーネントのリモートオブジェクトが生存しているか判定します。
		 * 
		 * @param comp
		 *            CORBAコンポーネント
		 * @return リモートオブジェクトが生存している場合はtrue
		 */
		public boolean isAvailable(CorbaComponent comp) {
			return (comp.getCorbaObject() != null && SynchronizationSupport
					.ping(comp.getCorbaObject()));
		}

		/**
		 * CORBA コンポーネントに対するリモートオブジェクトを生成します。
		 * 
		 * @param comp
		 *            CORBA コンポーネント
		 * @param diagram
		 *            ダイアグラム
		 * @return リモートオブジェクト
		 * @throws CORBAException
		 */
		public RTC.RTObject createRTObject(CorbaComponent comp,
				SystemDiagram diagram) throws CORBAException {
			LOGGER.trace("createRTObject START comp=<{}> diagram=<{}>", comp,
					diagram);
			RTM.Manager manager = ns().findManager(comp.getPathId());
			if (manager == null) {
				throw new CORBAException(String.format(
						"Fail to find manager: path=<%s>", comp.getPathId()));
			}
			String param = buildCreateComponentParam(comp);
			LOGGER.info("createRTObject: cmd=<{}>", param);
			RTC.RTObject rtobj = manager.create_component(param);
			return rtobj;
		}

		/**
		 * CORBA 複合コンポーネントに対するリモートオブジェクトを生成します。
		 * 
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @param diagram
		 *            ダイアグラム
		 * @return リモートオブジェクト
		 * @throws CORBAException
		 */
		public RTC.RTObject createCompositeRTObject(CorbaComponent comp,
				SystemDiagram diagram) throws CORBAException {
			LOGGER.trace(
					"createCompositeRTObject START comp=<{}> diagram=<{}>",
					comp, diagram);
			RTM.Manager manager = ns().findManager(comp.getPathId());
			if (manager == null) {
				throw new CORBAException(String.format(
						"Fail to find manager: path=<%s>", comp.getPathId()));
			}
			String exportedPorts = findConfiguration("exported_ports", comp,
					diagram);
			String param = buildCreateCompositeComponentParam(comp,
					exportedPorts);
			LOGGER.info("createCompositeRTObject: cmd=<{}>", param);
			RTC.RTObject rtobj = manager.create_component(param);
			return rtobj;
		}

		/**
		 * CORBA 複合コンポーネントのリモートオブジェクトへ、子コンポーネントを割り当てます。<br>
		 * あらかじめ複合コンポーネント自体のリモートオブジェクトを設定しておくこと
		 * 
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @throws CORBAException
		 */
		public void setCompositeMembers(CorbaComponent comp)
				throws CORBAException {
			LOGGER.trace("setCompositeMembers START comp=<{}>", comp);
			RTC.RTObject remote = comp.getCorbaObjectInterface();
			if (remote == null) {
				throw new CORBAException(String.format(
						"Remote object does not loaded: comp=<{}>", comp));
			}
			// 子RTCの CORBAオブジェクトリストを生成
			List<_SDOPackage.SDO> sdolist = new ArrayList<_SDOPackage.SDO>();
			for (Component o : comp.getComponents()) {
				CorbaComponent c = (CorbaComponent) o;
				RTC.RTObject rtobj = c.getCorbaObjectInterface();
				if (rtobj == null) {
					throw new CORBAException(
							String.format(
									"Remote object of child does not loaded: comp=<{}>",
									c));
				}
				sdolist.add(rtobj);
			}
			_SDOPackage.SDO[] sdos = sdolist.toArray(new _SDOPackage.SDO[0]);
			try {
				remote.get_owned_organizations()[0].set_members(sdos);
			} catch (Exception e) {
				remote.exit();
				throw new CORBAException(String.format(
						"Fail to set members: remote=<{}> sdos=<{}>", remote,
						sdos));
			}
		}

		/**
		 * このダイアグラムの RTSProfileから、コンポーネントの ConfigurationSet 設定値を検索します。
		 * 
		 * @param key
		 *            パラメータ名
		 * @param comp
		 *            EMFコンポーネント(CORBA)
		 * @param diagram
		 *            ダイアグラム
		 * @return 設定値、パラメータが存在しなかった場合はブランク("")
		 */
		public String findConfiguration(String key, CorbaComponent comp,
				SystemDiagram diagram) {
			org.openrtp.namespaces.rts.version02.Component orig = RtsProfileHandler
					.findComponent(comp, diagram.getProfile().getComponents());
			String activeId = orig.getActiveConfigurationSet();
			for (org.openrtp.namespaces.rts.version02.ConfigurationSet cs : orig
					.getConfigurationSets()) {
				if (!cs.getId().equals(activeId)) {
					continue;
				}
				for (org.openrtp.namespaces.rts.version02.ConfigurationData cd : cs
						.getConfigurationData()) {
					if (cd.getName().equals(key)) {
						return cd.getData();
					}
				}
			}
			return "";
		}

		/**
		 * コンポーネント生成のパラメータを生成します。<br>
		 * 次の情報が設定されていること (必須)
		 * <ul>
		 * <li>implementation_id : [プロパティ] コンポーネントの型</li>
		 * <li>instance_name : [プロパティ] コンポーネントのインスタンス名</li>
		 * </ul>
		 * 
		 * @param comp
		 *            CORBA コンポーネント
		 * @return コンポーネント生成パラメータ (失敗時はnull)
		 */
		public String buildCreateComponentParam(CorbaComponent comp) {
			String implementationId = comp.getProperty("implementation_id");
			String instanceName = comp.getProperty("instance_name");
			if (implementationId == null || instanceName == null) {
				return null;
			}
			StringBuffer ret = new StringBuffer();
			ret.append(implementationId);
			ret.append("?instance_name=").append(instanceName);
			String pg = comp.getProperty("process_group");
			if (pg != null) {
				ret.append("&process_group=").append(pg);
			}
			return ret.toString();
		}

		/**
		 * 複合コンポーネント生成のパラメータを生成します。<br>
		 * 次の情報が設定されていること (必須)
		 * <ul>
		 * <li>compositeType: [属性] 複合コンポーネントタイプ</li>
		 * <li>instance_name : [プロパティ] コンポーネントのインスタンス名</li>
		 * <li>exportedPorts : [引数] 公開ポート指定</li>
		 * </ul>
		 * 
		 * @param comp
		 *            CORBA 複合コンポーネント
		 * @param exportedPorts
		 *            公開ポート指定
		 * @return コンポーネント生成パラメータ (失敗時はnull)
		 */
		public String buildCreateCompositeComponentParam(CorbaComponent comp,
				String exportedPorts) {
			String compositeType = comp.getCompositeTypeL();
			String instanceName = comp.getProperty("instance_name");
			//
			StringBuffer ret = new StringBuffer();
			ret.append(buildCreateCompositeComponentParam(compositeType,
					instanceName, exportedPorts));
			//
			String pg = comp.getProperty("process_group");
			if (pg != null) {
				ret.append("&process_group=").append(pg);
			}
			return ret.toString();
		}

		/**
		 * 複合コンポーネント生成のパラメータを生成します。
		 * 
		 * @param compositeType
		 *            複合コンポーネントタイプ
		 * @param instanceName
		 *            コンポーネントのインスタンス名
		 * @param exportedPorts
		 *            公開ポート指定
		 * @return コンポーネント生成パラメータ (失敗時はnull)
		 */
		public String buildCreateCompositeComponentParam(String compositeType,
				String instanceName, String exportedPorts) {
			if (compositeType == null || instanceName == null
					|| exportedPorts == null) {
				return null;
			}
			StringBuffer ret = new StringBuffer();
			ret.append(compositeType).append("Composite");
			ret.append("?instance_name=").append(instanceName);
			ret.append("&exported_ports=").append(exportedPorts);
			return ret.toString();
		}

	}

	/**
	 * CORBA 操作に関する例外
	 */
	public static class CORBAException extends Exception {

		private static final long serialVersionUID = 1L;

		public CORBAException(String msg) {
			super(msg);
		}

		public CORBAException(Throwable cause) {
			super(cause);
		}

		public CORBAException(String msg, Throwable cause) {
			super(msg, cause);
		}

	}

}
