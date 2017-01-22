package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.extension.SaveProfileExtension;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.events.SelectionListener;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

public class ProfileSaver {

	static private final String EXTENTION_POINT_NAME = "saveprofile";
	static private List<SaveProfileExtension> extensionList;

	public ProfileSaver() {
		if (extensionList == null) {
			buildCreater();
		}
	}

	private static void buildCreater() {
		extensionList = new ArrayList<SaveProfileExtension>();
		//
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof SaveProfileExtension) {
						extensionList.add((SaveProfileExtension) obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public List<SaveProfileExtension.ErrorInfo> validate(SystemDiagram diagram) {
		List<SaveProfileExtension.ErrorInfo> result = new ArrayList<SaveProfileExtension.ErrorInfo>();
		for (SaveProfileExtension extension : extensionList) {
			SaveProfileExtension.ErrorInfo info = extension.validate(diagram);
			if (info != null) {
				result.add(info);
			}
		}
		return result;
	}

	public List<SaveProfileExtension.ErrorInfo> prepareSave(
			SystemDiagram diagram, String vendor, String name, String version,
			String path) {
		List<SaveProfileExtension.ErrorInfo> result = new ArrayList<SaveProfileExtension.ErrorInfo>();
		for (SaveProfileExtension extension : extensionList) {
			SaveProfileExtension.ErrorInfo info = extension.prepareSave(
					diagram, vendor, name, version, path);
			if (info != null) {
				result.add(info);
			}
		}
		return result;
	}

	public List<SaveProfileExtension.ErrorInfo> preSave(SystemDiagram diagram,
			RtsProfileExt profile) {
		List<SaveProfileExtension.ErrorInfo> result = new ArrayList<SaveProfileExtension.ErrorInfo>();
		for (SaveProfileExtension extension : extensionList) {
			SaveProfileExtension.ErrorInfo info = extension.preSave(diagram,
					profile);
			if (info != null) {
				result.add(info);
			}
		}
		return result;
	}

	public List<SaveProfileExtension.ErrorInfo> postSave(SystemDiagram diagram,
			IFile file) {
		List<SaveProfileExtension.ErrorInfo> result = new ArrayList<SaveProfileExtension.ErrorInfo>();
		for (SaveProfileExtension extension : extensionList) {
			SaveProfileExtension.ErrorInfo info = extension.postSave(diagram,
					file);
			if (info != null) {
				result.add(info);
			}
		}
		return result;
	}

	public List<ExtentionButton> loadExtentionButtons(SystemDiagram diagram) {
		List<ExtentionButton> result = new ArrayList<ExtentionButton>();
		for (SaveProfileExtension extension : extensionList) {
			ExtentionButton eb = new ExtentionButton(extension.getName(),
					extension.getListener(diagram));
			result.add(eb);
		}
		return result;
	}

	public static class ExtentionButton {
		String label;
		SelectionListener listener;

		public ExtentionButton(String label, SelectionListener listener) {
			this.label = label;
			this.listener = listener;
		}

		public String getLabel() {
			return label;
		}

		public SelectionListener getListener() {
			return listener;
		}
	}

}
