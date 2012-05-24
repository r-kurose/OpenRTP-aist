package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.extension.RehabilitateComponentExtension;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

/**
 * アクセスできないオブジェクトに対して、PathIdや名前から修復を行うクラス
 * <p>
 * セーブ後のロード中の修復を想定している。セーブ内容がここでは確実に手に入ると考えてよい。
 * <p>
 * 修復をかけるのは、Componentの情報。
 */
public class Rehabilitation {

	static final String EXTENTION_POINT_NAME = "rehabilitatecomponent";
	static List<RehabilitateComponentExtension> rehabilitaters;

	public static void rehabilitation(SystemDiagram diagram) {
		if (rehabilitaters == null) {
			buildRehabilitaters();
		}
		for (Component c : diagram.getRegisteredComponents()) {
			RehabilitateComponentExtension rehabilitater = null;
			for (RehabilitateComponentExtension ext : rehabilitaters) {
				ext.setComponent(c);
				ext.setDiagram(diagram);
				if (!ext.canRehabilitate()) {
					continue;
				}
				rehabilitater = ext;
				break;
			}
			rehabilitater.rehabilitate();
		}
	}

	static void buildRehabilitaters() {
		rehabilitaters = new ArrayList<RehabilitateComponentExtension>();
		//
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof RehabilitateComponentExtension) {
						rehabilitaters
								.add((RehabilitateComponentExtension) obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		// デフォルトのファクトリ
		RehabilitateComponentExtension ext = new RehabilitateComponentExtension() {
			@Override
			public boolean canRehabilitate() {
				if (component instanceof CorbaComponent) {
					return true;
				}
				return false;
			}

			@Override
			public Component rehabilitate() {
				CorbaComponent comp = (CorbaComponent) component;
				try {
					comp = CORBA.rehabilitate(comp, diagram);
				} catch (Exception e) {
					if (!comp.isCompositeComponent()) {
						throw new RuntimeException("cannot access: "
								+ comp.getPathId() + "\n" + e.getMessage());
					}
				}
				if (comp.getCorbaObject() == null
						&& comp.isCompositeComponent()) {
					comp = CORBA.createComponent(comp, diagram);
					comp = CORBA.setCompositeMembers(comp, diagram);
				}
				if (comp.getCorbaObject() == null) {
					throw new RuntimeException("cannot access: "
							+ comp.getPathId());
				}
				return comp;
			}
		};
		rehabilitaters.add(ext);
	}

}
