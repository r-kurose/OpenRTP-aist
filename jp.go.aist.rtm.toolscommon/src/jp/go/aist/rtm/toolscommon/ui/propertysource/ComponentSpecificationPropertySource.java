package jp.go.aist.rtm.toolscommon.ui.propertysource;


import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * コンポーネントのIPropertySourceクラス（オフライン）
 */
public class ComponentSpecificationPropertySource extends AbstractPropertySource {

	static final String DISP_PATH_URI = Messages.getString("ComponentPropertySource.disp.path_uri");

	static final String DISP_INSTANCE_NAME = Messages.getString("ComponentSpecificationPropertySource.disp.instance_name");

	static final String DISP_TYPE_NAME = Messages.getString("ComponentSpecificationPropertySource.disp.type_name");

	static final String DISP_DESCRIPTION = Messages.getString("ComponentSpecificationPropertySource.disp.description");

	static final String DISP_VERSION = Messages.getString("ComponentSpecificationPropertySource.disp.version");

	static final String DISP_VENDOR = Messages.getString("ComponentSpecificationPropertySource.disp.vendor");

	static final String DISP_CATEGORY = Messages.getString("ComponentSpecificationPropertySource.disp.category");

	static final String UNKNOWN = "<unknown>";

	private ComponentSpecification component;

	/**
	 * @param component
	 *            モデル
	 */
	public ComponentSpecificationPropertySource(ComponentSpecification component) {
		this.component = component;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		result.add(new TextPropertyDescriptor(Component.PATH_URI, DISP_PATH_URI));
		result.add(new TextPropertyDescriptor(Component.INSTANCE_NAME, DISP_INSTANCE_NAME));
		result.add(new TextPropertyDescriptor(Component.TYPE_NAME, 	DISP_TYPE_NAME));
		result.add(new TextPropertyDescriptor(Component.DESCRIPTION, DISP_DESCRIPTION));
		result.add(new TextPropertyDescriptor(Component.VERSION, DISP_VERSION));
		result.add(new TextPropertyDescriptor(Component.VENDER, DISP_VENDOR));
		result.add(new TextPropertyDescriptor(Component.CATEGORY, DISP_CATEGORY));
		if (component.getRtcType() != null) {
			result.add(new TextPropertyDescriptor("RTC_TYPE", "RTC Type"));
		}
		for (String key : component.getPropertyKeys()) {
			result.add(new TextPropertyDescriptor(new DynamicID("PROPERTIES", key), key));
		}
		return (IPropertyDescriptor[]) result
				.toArray(new IPropertyDescriptor[result.size()]);
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		String result = null;
		try {
			if (Component.PATH_URI.equals(id)) {
				return component.getPathId();
			} else if (Component.VENDER.equals(id)) {
				result = component.getVenderL();
			} else if (Component.INSTANCE_NAME.equals(id)) {
				result = component.getInstanceNameL();
			} else if (Component.DESCRIPTION.equals(id)) {
				result = component.getDescriptionL();
			} else if (Component.CATEGORY.equals(id)) {
				result = component.getCategoryL();
			} else if (Component.TYPE_NAME.equals(id)) {
				result = component.getTypeNameL();
			} else if (Component.VERSION.equals(id)) {
				result = component.getVersionL();
			} else if ("RTC_TYPE".equals(id)) {
				result = component.getRtcType();
			} else if (id instanceof DynamicID) {
				DynamicID dynamicId = (DynamicID) id;
				if ("PROPERTIES".equals(dynamicId.categoryId)) {
					return component.getProperty(dynamicId.subId);
				}
			}
		} catch (Exception e) {
			// void
		}

		if (result == null) {
			result = UNKNOWN;
		}

		return result;
	}

}
