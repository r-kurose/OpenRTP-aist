package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

import org.slf4j.Logger;

import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

/**
 * staticメソッドのみ定義
 */
public class RTMixin {

	public static String form(String fm, String... args) {
		String result = fm;
		for (int i = 0; i < args.length; i++) {
			String p = "{" + i + "}";
			int j = result.indexOf(p);
			if (j == -1) {
				continue;
			}
			String head = result.substring(0, j);
			String tail = result.substring(j + p.length());
			result = head + args[i] + tail;
		}
		return result;
	}

	public static boolean isBlank(String s) {
		return (s == null || s.isEmpty());
	}

	public static boolean eql(String s1, String s2) {
		return (s1 != null && s1.equals(s2));
	}

	public static String to_cid(Object o) {
		if (o == null) {
			return "null";
		}
		return o.getClass().getSimpleName() + "@" + Integer.toHexString(o.hashCode());
	}

	public static <T> List<String> to_dump_list(List<T> objs, Function<T, String> func) {
		List<String> ret = new ArrayList<>();
		for (T o : objs) {
			ret.add(func.apply(o));
		}
		return ret;
	}

	public static <T> T nvl(T t, T defaultValue) {
		return (t != null) ? t : defaultValue;
	}

	/** CORBAリクエストの呼び出しをラップしてログ出力 */
	public static <T> T LOG_R(Logger log, String method, Object target, Callable<T> call) {
		log.info("RTP->RTM : {}", method);
		log.info("note right: {}", LOG_TID(target));
		try {
			T ret = call.call();
			log.info("RTP<-RTM : {}", ret);
			return ret;
		} catch (Exception e) {
			log.info("RTP<-RTM : <<exception>> {}", e.getMessage());
			log.error("ERROR", e);
			return null;
		}
	}

	/** ログ用にターゲットを文字列化 */
	public static String LOG_TID(Object o) {
		if (o instanceof CorbaComponent) {
			CorbaComponent c = (CorbaComponent) o;
			return String.format("RTC(name=%s):%s", c.getInstanceNameL(), c.getCorbaObjectInterface());
		} else if (o instanceof CorbaExecutionContext) {
			CorbaExecutionContext c = (CorbaExecutionContext) o;
			String owner = (c.getOwner() != null) ? c.getOwner().getInstanceNameL() : "unknown";
			return String.format("EC(owner=%s):%s", owner, c.getCorbaObjectInterface());
		} else if (o instanceof RTCManager) {
			RTCManager c = (RTCManager) o;
			return String.format("Manager(name=%s):%s", c.getInstanceNameL(), c.getCorbaObjectInterface());
		} else if (o instanceof CorbaWrapperObject) {
			CorbaWrapperObject c = (CorbaWrapperObject) o;
			return String.format("CORBA.Object:%s", c.getCorbaObjectInterface());
		} else {
			return to_cid(o);
		}
	}

}
