package jp.go.aist.rtm.rtcbuilder.safety;

import java.util.logging.Logger;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RtcBuilderSafetyPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.go.aist.rtm.rtcbuilder.safety";

	// The shared instance
	private static RtcBuilderSafetyPlugin plugin;
	
	/**
	 * The constructor
	 */
	public RtcBuilderSafetyPlugin() {
		System.out.println("RtcBuilderSafetyPlugin entry");
		plugin = this;
		getLogger();
		System.out.println("RtcBuilderSafetyPlugin return");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("start entry");
		RtcBuilderPlugin.addLogger(getLogger());
		//
		System.out.println("start 050");
		System.out.println(">>"+context);
		super.start(context);
		System.out.println("start return");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("stop entry");
		RtcBuilderPlugin.removeLogger(getLogger());
		//
		plugin = null;
		super.stop(context);
		System.out.println("stop return");
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RtcBuilderSafetyPlugin getDefault() {
		System.out.println("getDefault entry");
		return plugin;
	}

	static Logger log;

	public static Logger getLogger() {
		System.out.println("getLogger entry");
		if (log == null) {
			log = Logger.getLogger(PLUGIN_ID);
		}
		System.out.println("getLogger retrun");
		return log;
	}

}
