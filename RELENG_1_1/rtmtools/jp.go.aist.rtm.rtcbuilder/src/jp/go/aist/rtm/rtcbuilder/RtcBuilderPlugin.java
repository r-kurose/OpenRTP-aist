package jp.go.aist.rtm.rtcbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import jp.go.aist.rtm.rtcbuilder.util.ShutdownListener;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RtcBuilderPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.go.aist.rtm.rtcbuilder";

	// The shared instance
	private static RtcBuilderPlugin plugin;

	RTCBLogHandler logHandler;

	// 拡張ローダ
	private ExtensionLoader loader;
	private EditorExtensionLoader editorExtensionLoader;
	private AddFormPageExtensionLoader addFormPageExtensionLoader;
	private ImportExtensionLoader importExtensionLoader;
	private ExportExtensionLoader exportExtensionLoader;
	
	//終了時フラグ
	private boolean canExit;
	
	/**
	 * The constructor
	 */
	public RtcBuilderPlugin() {
		plugin = this;
		//
		getLogger();
		try {
			logHandler = new RTCBLogHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		canExit = true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		logHandler.start();
		PlatformUI.getWorkbench().addWorkbenchListener(new ShutdownListener());
		//
		super.start(context);
		loader = new ExtensionLoader();
		loader.loadExtensions();

		editorExtensionLoader = new EditorExtensionLoader();
		editorExtensionLoader.loadExtensions();

		addFormPageExtensionLoader = new AddFormPageExtensionLoader();
		addFormPageExtensionLoader.loadExtensions();

		importExtensionLoader = new ImportExtensionLoader();
		importExtensionLoader.loadExtensions();

		exportExtensionLoader = new ExportExtensionLoader();
		exportExtensionLoader.loadExtensions();
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
	public static RtcBuilderPlugin getDefault() {
		return plugin;
	}

	public ExtensionLoader getLoader() {
		return loader;
	}

	public EditorExtensionLoader getEditorExtensionLoader() {
		return this.editorExtensionLoader;
	}

	public AddFormPageExtensionLoader getAddFormPageExtensionLoader() {
		return this.addFormPageExtensionLoader;
	}

	public ImportExtensionLoader getImportExtensionLoader() {
		return this.importExtensionLoader;
	}

	public ExportExtensionLoader getExportExtensionLoader() {
		return this.exportExtensionLoader;
	}

	static LogManager logManager;
	static Logger log;

	public static Logger getLogger() {
		if (logManager == null) {
			try {
				InputStream ins = new FileInputStream(new File(
						"rtcbuilder.logging.properties"));
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

	public boolean isCanExit() {
		return canExit;
	}
	public void setCanExit(boolean canExit) {
		this.canExit = canExit;
	}
}
