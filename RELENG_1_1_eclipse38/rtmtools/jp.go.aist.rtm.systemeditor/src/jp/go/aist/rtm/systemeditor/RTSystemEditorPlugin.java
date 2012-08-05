package jp.go.aist.rtm.systemeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RTSystemEditorPlugin extends AbstractUIPlugin {

	// The plug-in ID
	private static final String PLUGIN_ID = "jp.go.aist.rtm.systemeditor";

	// The shared instance
	private static RTSystemEditorPlugin plugin;

	RTSELogHandler logHandler;

	/**
	 * The constructor
	 */
	public RTSystemEditorPlugin() {
		plugin = this;
		//
		getLogger();
		try {
			logHandler = new RTSELogHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		logHandler.start();
		//
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		logHandler.stop();
		//
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RTSystemEditorPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * ImageRegistryにキャッシュしたイメージを返す
	 * @param path プラグインの相対パス
	 * @return　イメージ
	 */
	public static Image getCachedImage(String path) {
		return getCachedImage(getImageDescriptor(path));
	}

	/**
	 * ImageRegistryにキャッシュしたイメージを返す
	 * @param descriptor イメージディスクリプタ
	 * @return　イメージ
	 */
	public static Image getCachedImage(ImageDescriptor descriptor) {
		if (descriptor == null) return null;
		Image result = getDefault().getImageRegistry().get(descriptor.toString());
		if (result == null) {
			result = descriptor.createImage();
			getDefault().getImageRegistry().put(
					descriptor.toString(), result);
		}
		return result;
	}

	static LogManager logManager;
	static Logger log;

	public static Logger getLogger() {
		if (logManager == null) {
			try {
				InputStream ins = new FileInputStream(new File(
						"systemeditor.logging.properties"));
				logManager = LogManager.getLogManager();
				logManager.readConfiguration(ins);
			} catch (IOException e) {
				// void
			}
		}
		//
		if (log == null) {
			log = Logger.getLogger(PLUGIN_ID);
		}
		return log;
	}

	public static void addLogger(Logger logger) {
		if (plugin != null) {
			plugin.logHandler.addLogger(logger);
		}
	}

	public static void removeLogger(Logger logger) {
		if (plugin != null) {
			plugin.logHandler.removeLogger(logger);
		}
	}

}
