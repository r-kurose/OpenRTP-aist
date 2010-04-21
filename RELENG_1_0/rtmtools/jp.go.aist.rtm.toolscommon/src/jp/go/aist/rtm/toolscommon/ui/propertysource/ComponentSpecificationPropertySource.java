package jp.go.aist.rtm.toolscommon.ui.propertysource;


import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
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
		return new PropertyDescriptor[] {
				new TextPropertyDescriptor(Component.PATH_URI, DISP_PATH_URI),
				new TextPropertyDescriptor(Component.INSTANCE_NAME,
						DISP_INSTANCE_NAME),
				new TextPropertyDescriptor(Component.TYPE_NAME, DISP_TYPE_NAME),
				new TextPropertyDescriptor(Component.DESCRIPTION,
						DISP_DESCRIPTION),
				new TextPropertyDescriptor(Component.VERSION, DISP_VERSION),
				new TextPropertyDescriptor(Component.VENDER, DISP_VENDOR),
				new TextPropertyDescriptor(Component.CATEGORY, DISP_CATEGORY), };
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
