package jp.go.aist.rtm.nameserviceview.corba;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.manager.NameServiceViewPreferenceManager;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;

import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import RTM.ManagerHelper;

/**
 * ネームサーバにアクセスするユーティリティ
 * CORBA専用のクラスである
 */
public class NameServerAccesser {
	/**
	 * シングルトンインスタンス
	 */
	private static NameServerAccesser __instance = new NameServerAccesser();

	/**
	 * シングルトンへのアクセサ
	 * 
	 * @return シングルトン
	 */
	public static NameServerAccesser getInstance() {
		return __instance;
	}

	/**
	 * アドレスを引数に取り、ネームサーバのルートのNamingContextExtを返す
	 * <p>
	 * 形式は、「address:port」となる。ポートが指定されていない場合には、ユーザ設定ポートを使用する
	 * 
	 * @param address
	 *            ネームサーバのアドレス
	 * @return ネームサーバのルートのNamingContextExt
	 */
	public NamingContextExt getNameServerRootContext(String address) {
		if ("".equals(address)) {
			return null;
		}

		if (address.indexOf(":") == -1) {
			// address = address + ":2809";
			address = address
					+ ":"
					+ NameServiceViewPreferenceManager.getInstance().getDefaultPort(
							NameServiceViewPreferenceManager.DEFAULT_CONNECTION_PORT);
		}
		
		String version = "1.0";
		
		return NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:" + version + "@" + address + "/NameService"));
	}

	/**
	 * PathIdからObjectを取得する
	 * 
	 * @param pathId
	 * @return
	 */
	public org.omg.CORBA.Object getObjectFromPathId(String pathId) {
		try {
			NamingContextExt namingContext = getNameServerRootContext(getNameServerNameFromPathId(pathId));
			return namingContext.resolve(getNameComponentsFromPathId(pathId));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * PathIdからNameServerNameを取得する
	 * 
	 * @param pathId
	 * @return
	 */
	public String getNameServerNameFromPathId(String pathId) {
		return pathId.split("/")[0];
	}

	/**
	 * PathIdからNameComponentを取得する
	 * <p>
	 * ネームサーバ名は除く
	 * 
	 * @param pathId
	 * @return
	 */
	public NameComponent[] getNameComponentsFromPathId(String pathId) {
		List<NameComponent> result = new ArrayList<NameComponent>();
		String[] split = pathId.split("/");
		for (int i = 0; i < split.length; ++i) {
			if (i > 0) {
				int index = split[i].lastIndexOf(".");
				result.add(new NameComponent(split[i].substring(0, index),
						split[i].substring(index + ".".length())));
			}
		}

		return result.toArray(new NameComponent[result.size()]);
	}

	/**
	 * contextからRTM.Managerを取得する
	 * @param context
	 * @return
	 */
	public RTM.Manager findManager(NamingContext context) {
		List<Binding> bindingList = CorbaUtil.getBindingList(context);
		for (Binding b : bindingList) {
			try {
				org.omg.CORBA.Object resolve = context.resolve(b.binding_name);
				if (resolve._is_a(ManagerHelper.id()))return ManagerHelper.narrow(resolve);
				if (resolve instanceof NamingContext) {
					RTM.Manager temp = findManager((NamingContext)resolve);
					if (temp != null) return temp;
				}
			} catch (Exception e) {
				// continue
			}
		}
		return null;
	}

	/**
	 * contextIdからRTM.Managerを取得する
	 * @param contextId
	 * @return
	 */
	public RTM.Manager getManagerFromContextId(String contextId) {
		int index = contextId.lastIndexOf("/");
		if (index < 0) {
			return findManager(getNameServerRootContext(getNameServerNameFromPathId(contextId)));
		} else {
			return findManager((NamingContext) getObjectFromPathId(contextId));
		}
	}

}
