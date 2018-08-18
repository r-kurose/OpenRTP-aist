package jp.go.aist.rtm.nameserviceview;

import jp.go.aist.rtm.nameserviceview.util.ShutdownListener;
import jp.go.aist.rtm.toolscommon.profiles.util.LoggerUtil;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class NameServiceViewPlugin extends AbstractUIPlugin {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NameServiceViewPlugin.class);

	// The plug-in ID
	private static final String PLUGIN_ID = "jp.go.aist.rtm.nameserviceview";

	// The shared instance
	private static NameServiceViewPlugin plugin;

	/**
	 * 接続が成功したことのあるアドレスの一覧を保存する、ワークスペース永続文字列へのキー
	 */
	public static final String COMBO_ITEMS_KEY = NameServiceViewPlugin.class
			.getName()
			+ ".sever.items"; //$NON-NLS-1$

	/**
	 * The constructor
	 */
	public NameServiceViewPlugin() {
		LoggerUtil.setup();
		LOGGER.trace("NameServiceViewPlugin: START");

		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		PlatformUI.getWorkbench().addWorkbenchListener(new ShutdownListener());
		//
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
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
	public static NameServiceViewPlugin getDefault() {
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

}
