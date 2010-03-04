import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import jp.go.aist.rtm.nameserviceview.manager.impl.NameServerManagerImpl;
import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import _SDOPackage.InternalError;
import _SDOPackage.NotAvailable;

public class CorbaTest {
	private static MappingRule[] mappingRules = new MappingRule[] {
			// toolscommon
//			jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl.MAPPING_RULE,
			jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConnectorProfileImpl.MAPPING_RULE,
			jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl.MAPPING_RULE,
			jp.go.aist.rtm.toolscommon.model.component.impl.CorbaConfigurationSetImpl.MAPPING_RULE,
			// nameserviceview
			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingObjectNodeImpl.MAPPING_RULE,
//			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.HostNamingContextImpl.MAPPING_RULE,
//			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.ManagerNamingContextImpl.MAPPING_RULE,
//			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.ModuleNamingContextImpl.MAPPING_RULE,
//			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.CategoryNamingContextImpl.MAPPING_RULE,
			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingContextNodeImpl.MAPPING_RULE_NAMESERVER,
			jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingContextNodeImpl.MAPPING_RULE,
			// toolscommon
			jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl.MAPPING_RULE,
//			jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortConnectorImpl.MAPPING_RULE,
			jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl.MAPPING_RULE,
			jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl.MAPPING_RULE, };

	static SynchronizationManager manager;

	static NamingContextExt nc;

	static String nameServerAddress = "localhost";
//	static String nameServerAddress = "192.168.3.100";

	public static void main(String[] args) {
		manager = new SynchronizationManager(mappingRules);

		MappingRuleFactory.setMappingRule(mappingRules);

		nc = jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser
				.getInstance().getNameServerRootContext(nameServerAddress);
		System.out.println("name_service_root_context=" + nc);

		// RTCManager一覧表示
		jp.go.aist.rtm.nameserviceview.manager.NameServerManager nsmgr = NameServerManagerImpl
				.getInstance();
		nsmgr.addNameServer(nameServerAddress);
//		nsmgr.addNameServer("localhost");
//		nsmgr.synchronizeAll();
		for (RTCManager e : nsmgr
				.getRTCManagerList()) {
			System.out.println("  path=" + e.getPathId() + " manager=" + e);
		}

		CorbaTest test = new CorbaTest();

		while (true) {
			System.out.print("オブジェクト名[quitで終了]> ");
			String name = test.readLine();
			if (name.equals("quit")) {
				break;
			}
			LocalObject local = getLocalObject(name);
		
			if (local == null) {
				System.out.println("オブジェクトが見つかりません");
				continue;
			}
			System.out.println(local.toString());

			if (local instanceof RTCManager) {
				RTCManager m = (RTCManager) local;
				test.actionManager(m);
			} else if (local instanceof CorbaComponent) {
				CorbaComponent c = (CorbaComponent) local;
				test.actionComponent(c);
			}
		}
	}

	/** ネームサーバからnameに対応するオブジェクトを検索 */
	public static LocalObject getLocalObject(String name) {
		try {
			org.omg.CORBA.Object remote = nc.resolve_str(name);
			LocalObject local = manager
					.createLocalObject(new Object[] { remote });
			return local;
		} catch (NotFound e) {
			e.printStackTrace();
		} catch (CannotProceed e) {
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			e.printStackTrace();
		}
		return null;
	}

	/** マネージャアクション */
	void actionManager(RTCManager m) {
		m.synchronizeManually();
		String result = m.getInstanceNameL() + "\n" + "  components="
				+ m.getComponentInstanceNamesR() + "\n" + "  loadable_modules="
				+ m.getLoadableModuleFileNamesR() + "\n" + "  loaded_modules="
				+ m.getLoadedModuleFileNamesR() + "\n";
		System.out.println(result);
		while (true) {
			String in = selectMenu(new String[] { "1", "コンポーネント一覧", "2",
					"ロード可能モジュール一覧", "3", "ロード済みモジュール一覧", "4", "コンポーネント作成", "5",
					"マネージャ複製", "6", "マネージャ終了" });
			if (in.equals("q")) {
				break;
			}
			if (in.equals("1")) {
				System.out.println(m.getComponentInstanceNamesR());
			} else if (in.equals("2")) {
				System.out.println(m.getLoadableModuleFileNamesR());
			} else if (in.equals("3")) {
				System.out.println(m.getLoadedModuleFileNamesR());
			} else if (in.equals("4")) {
				System.out.print("生成パラメータ> ");
				String p = readLine();
				Component comp = m.createComponentR(p);
				System.out.println(comp);
			} else if (in.equals("5")) {
				m.forkR();
			} else if (in.equals("6")) {
				m.shutdownR();
			}
		}
	}

	/** コンポーネントアクション */
	@SuppressWarnings("unchecked")
	void actionComponent(CorbaComponent c) {
		c.synchronizeLocalAttribute(null);
		c.synchronizeLocalReference();

		String result = "  instance_name=" + c.getInstanceNameL() + "\n"
				+ "  category=" + c.getCategoryL() + "\n"
				+ "  components=" + c.getComponents() + "\n";
		System.out.println(result);
		while (true) {
			String in = selectMenu(new String[] { "0", "exit", "1", "メンバ一覧",
					"2", "メンバ追加", "3", "メンバ削除", "4", "OrganizationProperty一覧",
					"5", "OrganizationProperty更新", "s", "同期" });
			if (in.equals("q")) {
				break;
			}
			if (in.equals("0")) {
				c.exitR();
			} else if (in.equals("1")) {
				try {
					System.out.println(c.getCorbaObjectInterface()
							.get_owned_organizations()[0].get_members());
					System.out.println("local:");
					System.out.println("  composite: type="
							+ c.getCompositeTypeL() + " is_composite="
							+ c.isCompositeComponent());
					System.out.println("  components:");
					for (Object o : c.getComponents()) {
						Component ac = (Component) o;
						System.out.println("    instance_name="
								+ ac.getInstanceNameL());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (in.equals("2")) {
				System.out.print("追加するメンバ名[,区切り]> ");
				String p = readLine();
				String[] ms = p.split(",");
				List<LocalObject> list = new ArrayList<LocalObject>();
				for (int i = 0; i < ms.length; i++) {
					LocalObject lo = getLocalObject(ms[i]);
					System.out.println("  " + ms[i] + " -> " + lo);
					list.add(lo);
				}
//				c.addComponentsR(list);
				c.synchronizeLocalReference();
				System.out.println("  components=" + c.getComponents());

			} else if (in.equals("3")) {
				System.out.println("現在のメンバ: " + c.getComponents());
				System.out.print("削除するメンバ名> ");
				String p = readLine();
				LocalObject lo = getLocalObject(p);
				System.out.println("  " + p + " -> " + lo);
				c.removeComponentR((Component) lo);
				c.synchronizeLocalReference();
				System.out.println("  components=" + c.getComponents());

			} else if (in.equals("4")) {
				try {
					_SDOPackage.OrganizationProperty op = c
							.getCorbaObjectInterface()
							.get_owned_organizations()[0]
							.get_organization_property();
					for (_SDOPackage.NameValue nv : op.properties) {
						System.out.println("remote: name=" + nv.name + " value=" + nv.value);
					}
					System.out.println();
//					for (Object o : c.getOrganizationProperties()) {
//						NameValue nv = (NameValue) o;
//						System.out.println("local: name=" + nv.getName()
//								+ " value=" + nv.getValueAsString());
//					}
				} catch (NotAvailable e) {
					e.printStackTrace();
				} catch (InternalError e) {
					e.printStackTrace();
				}
			} else if (in.equals("5")) {
				System.out.print("設定パラメータ[name=value,...]> ");
				String p = readLine();
				String[] nvs = p.split(",");
				List nvlist = new ArrayList();
				for (int i = 0; i < nvs.length; i++) {
					String nva[] = nvs[i].split("=");
					if (nva.length < 2) {
						continue;
					}
					NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
					nv.setName(nva[0].trim());
					nv.setValue(nva[1].trim());
					nvlist.add(nv);
				}
//				c.updatePortDisplayPropertiesR(nvlist);
				c.synchronizeLocalAttribute(null);

			} else if (in.equals("s")) {
				c.synchronizeLocalAttribute(null);
				c.synchronizeLocalReference();
			}
		}
	}

	/** メニュー選択 */
	String selectMenu(String[] menus) {
		Map<String, String> menuMap = new HashMap<String, String>();
		for (int i = 0; i < menus.length; i += 2) {
			if (i + 1 >= menus.length) {
				break;
			}
			menuMap.put(menus[i], menus[i + 1]);
		}
		System.out.println("");
		for (String key : new TreeSet<String>(menuMap.keySet())) {
			System.out.println(key + " : " + menuMap.get(key));
		}
		System.out.println("q : quit");
		while (true) {
			System.out.print("select menu> ");
			String input = readLine();
			if (input.equals("q") || menuMap.keySet().contains(input)) {
				return input;
			}
		}
	}

	/** 入力受付 */
	String readLine() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String input = null;
			while ((input = br.readLine()) != null) {
				return input;
			}
		} catch (IOException err) {
			// void
		}
		return "";
	}
}
