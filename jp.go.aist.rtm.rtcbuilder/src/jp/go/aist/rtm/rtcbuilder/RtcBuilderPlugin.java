package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.util.ShutdownListener;
import jp.go.aist.rtm.toolscommon.profiles.util.LoggerUtil;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class RtcBuilderPlugin extends AbstractUIPlugin {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RtcBuilderPlugin.class);

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.go.aist.rtm.rtcbuilder";

	// The shared instance
	private static RtcBuilderPlugin plugin;

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
		LoggerUtil.setup();
		LOGGER.trace("RtcBuilderPlugin: START");

		plugin = this;
		canExit = true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
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

	public boolean isCanExit() {
		return canExit;
	}

	public void setCanExit(boolean canExit) {
		this.canExit = canExit;
	}

}
