package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.extension.LoadProfileExtension;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

public class ProfileLoader {

	static private final String EXTENTION_POINT_NAME = "loadprofile";
	static private List<LoadProfileExtension> extensionList;

	public ProfileLoader() {
		if (extensionList == null) {
			buildCreater();
		}
	}

	private static void buildCreater() {
		extensionList = new ArrayList<LoadProfileExtension>();
		//
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof LoadProfileExtension) {
						extensionList.add((LoadProfileExtension) obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public List<LoadProfileExtension.ErrorInfo> preLoad(RtsProfileExt profile,
			String path) {
		List<LoadProfileExtension.ErrorInfo> result = new ArrayList<LoadProfileExtension.ErrorInfo>();
		for (LoadProfileExtension extension : extensionList) {
			LoadProfileExtension.ErrorInfo info = extension.preLoad(profile,
					path);
			if (info != null) {
				result.add(info);
			}
		}
		return result;
	}

	public List<LoadProfileExtension.ErrorInfo> postLoad(SystemDiagram diagram,
			RtsProfileExt profile, SystemDiagram oldSd) {
		List<LoadProfileExtension.ErrorInfo> result = new ArrayList<LoadProfileExtension.ErrorInfo>();
		for (LoadProfileExtension extension : extensionList) {
			LoadProfileExtension.ErrorInfo info = extension.postLoad(diagram,
					profile, oldSd);
			if (info != null) {
				result.add(info);
			}
		}
		return result;
	}

}
