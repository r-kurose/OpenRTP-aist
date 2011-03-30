package jp.go.aist.rtm.repositoryView;

import java.util.logging.Logger;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RepositoryViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.go.aist.rtm.repositoryView";

	// The shared instance
	private static RepositoryViewPlugin plugin;
	
	/**
	 * The constructor
	 */
	public RepositoryViewPlugin() {
		plugin = this;
		getLogger();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RepositoryViewPlugin getDefault() {
		return plugin;
	}

	static Logger log;

	public static Logger getLogger() {
		if (log == null) {
			log = Logger.getLogger(PLUGIN_ID);
		}
		return log;
	}

}
