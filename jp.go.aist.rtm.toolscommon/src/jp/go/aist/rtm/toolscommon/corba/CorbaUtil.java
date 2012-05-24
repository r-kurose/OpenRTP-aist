package jp.go.aist.rtm.toolscommon.corba;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NamingContext;

/**
 * CORBAに関するユーティリティ
 */
public class CorbaUtil {

	/**
	 * 対象のNamingContextExtから子供のBindingをListとして返す
	 * 
	 * @param target
	 *            対象のNamingContextExt
	 * @return 子供のBindingのList
	 */
	public static List<Binding> getBindingList(NamingContext target) {
		BindingListHolder bindingListHolder = new BindingListHolder();
		BindingIteratorHolder bindingIteratorHolder = new BindingIteratorHolder();

		try {
			target.list(9999, bindingListHolder, bindingIteratorHolder);
		} catch (Exception e) {
			// void 
		}

		List<Binding> result = new ArrayList<Binding>();
		for (int i = 0; i < bindingListHolder.value.length; i++) {
			Binding binding = bindingListHolder.value[i];

			result.add(binding);
		}

		return result;
	}

	/**
	 * ORBオブジェクト
	 */
//	private static ORB orb = ORB.init(new String[] {
//			"-ORBTCPReadTimeouts",	"1:60000:300:1" }
//		, null);
	
	// omniORBのオプションだろう. JDKのCORBA実装にはない
//	"-ORBclientCallTimeOutPeriod", 
//	String.valueOf(ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
//		ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD)
	private static ORB orb = ORB.init(new String[] {}, createProps());
	static {
		try {
			Method declaredMethod = orb.getClass().getMethod("getLogger", String.class);
			Logger logger = (Logger) declaredMethod.invoke(orb, "");
			logger.setLevel(Level.SEVERE);
		} catch (Exception e) {
			e.printStackTrace(); // system error
		}
	}

	/**
	 * ORBオブジェクトを取得する
	 * 
	 * @return ORB
	 */
	public static ORB getOrb() {
		return orb;
	}

	private static Properties createProps() {
		setConnectionTimeout();
		
		ToolsCommonPreferenceManager.getInstance().addPropertyChangeListener(createListner());
		
		Properties props = new Properties();
		props.put("com.sun.CORBA.transport.ORBSocketFactoryClass"
				, "jp.go.aist.rtm.toolscommon.corba.TimeoutCorbaORBSocketFactory");
		return props;
	}

	private static void setConnectionTimeout() {
		int defaultTimeout = ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
				ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
		TimeoutCorbaORBSocketFactory.setConnectionTimeout(defaultTimeout);
	}

	private static PropertyChangeListener createListner() {
		return new PropertyChangeListener(){
//			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setConnectionTimeout();
			}};
	}

	/**
	 * CORBAオブジェクト（プロキシ）をシリアライズした文字列から、CORBAオブジェクトへ変換する
	 * 
	 * @param str
	 *            CORBAオブジェクト（プロキシ）をシリアライズした文字列
	 * @return CORBAオブジェクト
	 */
	public static org.omg.CORBA.Object stringToObject(String str) {
		return orb.string_to_object(str);
	}
}
