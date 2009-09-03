package jp.go.aist.rtm.rtcbuilder;

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
	
	//ägí£ÉçÅ[É_
	private ExtensionLoader loader;
	private EditorExtensionLoader editorExtensionLoader;
	private AddFormPageExtensionLoader addFormPageExtensionLoader;
	private ImportExtensionLoader importExtensionLoader;
	private ExportExtensionLoader exportExtensionLoader;
	
	/**
	 * The constructor
	 */
	public RtcBuilderPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
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
}
