package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.extension.RehabilitateComponentExtension;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

/**
 * アクセスできないオブジェクトに対して、PathIdや名前から修復を行うクラス
 * <p>
 * セーブ後のロード中の修復を想定している。セーブ内容がここでは確実に手に入ると考えてよい。
 * <p>
 * 修復をかけるのは、Componentの情報。
 */
public class Rehabilitation {

	private static final Logger LOGGER = LoggerFactory.getLogger(Rehabilitation.class);

	static final String EXTENTION_POINT_NAME = "rehabilitatecomponent";
	static List<RehabilitateComponentExtension> rehabilitaters = null;

	public static void rehabilitation(SystemDiagram diagram) {
		rehabilitation(diagram, false);
	}

	public static void rehabilitation(SystemDiagram diagram, boolean doCreate) {
		if (rehabilitaters == null) {
			buildRehabilitaters();
		}
		for (Component c : diagram.getRegisteredComponents()) {
			rehabilitateComponent(c, diagram, doCreate);
		}
		for (Component c : diagram.getRegisteredComponents()) {
			rehabilitateStructure(c, diagram);
		}
		if (doCreate) {
			// コンポーネントを生成した場合、生成後のRTCProfileの変更が反映されないので強制的に更新 (OpenRTMの不具合)
			LOGGER.info("Re-rehabilitating the component structure on doCreate mode.");
			for (Component c : diagram.getRegisteredComponents()) {
				rehabilitateStructure(c, diagram);
			}
		}
	}

	public static void rehabilitateComponent(Component comp, SystemDiagram diagram, boolean doCreate) {
		if (rehabilitaters == null) {
			buildRehabilitaters();
		}
		RehabilitateComponentExtension rehabilitater = findExtension(comp, diagram);
		if (rehabilitater == null) {
			LOGGER.info("Rehabilitate extension does not defined. comp=<{}> diagram=<{}>", comp, diagram);
			return;
		}
		rehabilitater.rehabilitateComponent(doCreate);
	}

	public static void rehabilitateStructure(Component comp, SystemDiagram diagram) {
		if (rehabilitaters == null) {
			buildRehabilitaters();
		}
		RehabilitateComponentExtension rehabilitater = findExtension(comp, diagram);
		if (rehabilitater == null) {
			LOGGER.info("Rehabilitate extension does not defined. comp=<{}> diagram=<{}>", comp, diagram);
			return;
		}
		rehabilitater.rehabilitateStructure();
	}

	public static RehabilitateComponentExtension findExtension(Component comp, SystemDiagram diagram) {
		RehabilitateComponentExtension ret = null;
		for (RehabilitateComponentExtension ext : rehabilitaters) {
			ext.setComponent(comp);
			ext.setDiagram(diagram);
			if (!ext.canRehabilitate()) {
				continue;
			}
			ret = ext;
			break;
		}
		return ret;
	}

	static void buildRehabilitaters() {
		List<RehabilitateComponentExtension> exts = new ArrayList<RehabilitateComponentExtension>();
		//
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(ns, EXTENTION_POINT_NAME)
				.getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof RehabilitateComponentExtension) {
						exts.add((RehabilitateComponentExtension) obj);
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
			public Component rehabilitateComponent(boolean doCreate) {
				CorbaComponent comp = (CorbaComponent) component;
				try {
					comp = CORBA.rehabilitate(comp, diagram);
				} catch (Exception e) {
					LOGGER.warn("Fail to resolve component: comp=<{}> cause=<{}>", comp, e.getMessage());
					comp.setCorbaObject(null);
				}
				if (comp.getCorbaObject() != null) {
					return comp;
				}
				if (doCreate) {
					try {
						comp = CORBA.createComponent(comp, diagram);
					} catch (Exception e) {
						throw new RuntimeException(String.format("Fail to create component: path=<%s>\n<%s>",
								comp.getPathId(), e.getMessage()), e);
					}
				}
				return comp;
			}

			@Override
			public Component rehabilitateStructure() {
				CorbaComponent comp = (CorbaComponent) this.component;
				if (comp.getCorbaObject() != null) {
					if (comp.isCompositeComponent()) {
						comp = CORBA.setCompositeMembers(comp, diagram);
					}
				}
				if (comp.getCorbaObject() == null) {
					throw new RuntimeException(String.format("Fail to set composite: path=<%s>", comp.getPathId()));
				}
				return comp;
			}
		};
		exts.add(ext);

		rehabilitaters = exts;
	}

}
