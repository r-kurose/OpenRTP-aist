package jp.go.aist.rtm.toolscommon.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

/**
 * 拡張されたモデルの整合性チェックを実行するクラス
 */
public class Validator {
	private static final String EXTENTION_POINT_NAME = "validations";

	private static Map<Class, List<Class>> validationMap;

	public static void validate(Object obj) throws ValidateException {
		if (validationMap == null) {
			buildValidationMap();
		}
		for (Class c : validationMap.keySet()) {
			if (c.isInstance(obj)) {
				List<Class> list = validationMap.get(c);
				for (Class k : list) {
					try {
						AbstractValidator av = (AbstractValidator) k
								.newInstance();
						av.validate(obj);
					} catch (InstantiationException e) {
						ValidateException ve = new ValidateException(
								"Validation failure.", e);
						throw ve;
					} catch (IllegalAccessException e) {
						ValidateException ve = new ValidateException(
								"Validation failure.", e);
						throw ve;
					}
				}
			}
		}
	}

	private static void buildValidationMap() {
		validationMap = new HashMap<Class, List<Class>>();
		String ns = ToolsCommonPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				String id = ce.getAttribute("id");
				String target = ce.getAttribute("target");
				Class idClass;
				Class targetClass;
				try {
					idClass = Platform.getBundle(ex.getNamespaceIdentifier())
							.loadClass(id);
					targetClass = Platform.getBundle(
							ex.getNamespaceIdentifier()).loadClass(target);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				List<Class> list = validationMap.get(targetClass);
				if (list == null) {
					list = new ArrayList<Class>();
					validationMap.put(targetClass, list);
				}
				list.add(idClass);
			}
		}
	}
}
