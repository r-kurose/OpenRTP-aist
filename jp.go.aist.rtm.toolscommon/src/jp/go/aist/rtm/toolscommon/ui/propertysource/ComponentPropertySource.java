package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * コンポーネントのIPropertySourceクラス（オンライン）
 */
public class ComponentPropertySource extends AbstractPropertySource {

	static final String DISP_PATH_URI = Messages.getString("ComponentPropertySource.disp.path_uri");
	static final String DISP_INSTANCE_NAME = Messages.getString("ComponentPropertySource.disp.instance_name");
	static final String DISP_TYPE_NAME = Messages.getString("ComponentPropertySource.disp.type_name");
	static final String DISP_DESCRIPTION = Messages.getString("ComponentPropertySource.disp.description");
	static final String DISP_VERSION = Messages.getString("ComponentPropertySource.disp.version");
	static final String DISP_VENDOR = Messages.getString("ComponentPropertySource.disp.vendor");
	static final String DISP_CATEGORY = Messages.getString("ComponentPropertySource.disp.category");
	static final String DISP_STATE = Messages.getString("ComponentPropertySource.disp.state");

	static final String STATE_UNKNOWN_VIEWSTRING = Messages.getString("ComponentPropertySource.state.unknown");
	static final String STATE_CREATED_VIEWSTRING = Messages.getString("ComponentPropertySource.state.created");
	static final String STATE_INACTIVE_VIEWSTRING = Messages.getString("ComponentPropertySource.state.inactive");
	static final String STATE_ACTIVE_VIEWSTRING = Messages.getString("ComponentPropertySource.state.active");
	// static final String STATE_ALIVE_VIEWSTRING = Messages.getString("ComponentPropertySource.state.alive");
	static final String STATE_ERROR_VIEWSTRING = Messages.getString("ComponentPropertySource.state.error");

	static final String UNKNOWN = "<unknown>";

	private Component component;

	/**
	 * @param component
	 *            モデル
	 */
	public ComponentPropertySource(Component component) {
		this.component = component;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		result.add(new TextPropertyDescriptor(Component.PATH_URI, DISP_PATH_URI));
		result.add(new TextPropertyDescriptor(Component.INSTANCE_NAME, DISP_INSTANCE_NAME));
		result.add(new TextPropertyDescriptor(Component.TYPE_NAME, DISP_TYPE_NAME));
		result.add(new TextPropertyDescriptor(Component.DESCRIPTION, DISP_DESCRIPTION));
		result.add(new TextPropertyDescriptor(Component.VERSION, DISP_VERSION));
		result.add(new TextPropertyDescriptor(Component.VENDER, DISP_VENDOR));
		result.add(new TextPropertyDescriptor(Component.CATEGORY, DISP_CATEGORY));
		result.add(new TextPropertyDescriptor(Component.STATE, DISP_STATE));
		return (IPropertyDescriptor[]) result.toArray(new IPropertyDescriptor[result.size()]);
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		try {
			if (Component.PATH_URI.equals(id)) {
				return component.getPathId();
			} else if (Component.INSTANCE_NAME.equals(id)) {
				return component.getInstanceNameL();
			} else if (Component.VENDER.equals(id)) {
				return component.getVenderL();
			} else if (Component.DESCRIPTION.equals(id)) {
				return component.getDescriptionL();
			} else if (Component.CATEGORY.equals(id)) {
				return component.getCategoryL();
			} else if (Component.TYPE_NAME.equals(id)) {
				return component.getTypeNameL();
			} else if (Component.VERSION.equals(id)) {
				return component.getVersionL();
			} else if (Component.STATE.equals(id)) {
				if (component instanceof CorbaComponent) {
					CorbaComponent c = (CorbaComponent) component;
					return c.getComponentStateName();
				}
			}
		} catch (Exception e) {
			return UNKNOWN;
		}
		return UNKNOWN;
	}

}
