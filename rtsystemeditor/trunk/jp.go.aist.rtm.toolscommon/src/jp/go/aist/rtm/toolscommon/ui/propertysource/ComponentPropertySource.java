package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * コンポーネントのIPropertySourceクラス（オンライン）
 */
public class ComponentPropertySource implements IPropertySource {
	private static final String DISP_INSTANCE_NAME = Messages.getString("ComponentPropertySource.disp.instance_name");

	private static final String DISP_TYPE_NAME = Messages.getString("ComponentPropertySource.disp.type_name");

	private static final String DISP_DESCRIPTION = Messages.getString("ComponentPropertySource.disp.description");

	private static final String DISP_VERSION = Messages.getString("ComponentPropertySource.disp.version");

	private static final String DISP_VENDOR = Messages.getString("ComponentPropertySource.disp.vendor");

	private static final String DISP_CATEGORY = Messages.getString("ComponentPropertySource.disp.category");

	private static final String DISP_STATE = Messages	.getString("ComponentPropertySource.disp.state");

//	private static final String RTC_UNKNOWN_VIEWSTRING = Messages	.getString("ComponentPropertySource.unknown");

	private static final String STATE_UNKNOWN_VIEWSTRING = Messages.getString("ComponentPropertySource.state.unknown");

	private static final String STATE_CREATED_VIEWSTRING = Messages.getString("ComponentPropertySource.state.created");

	private static final String STATE_INACTIVE_VIEWSTRING = Messages.getString("ComponentPropertySource.state.inactive");

	private static final String STATE_ACTIVE_VIEWSTRING = Messages.getString("ComponentPropertySource.state.active");

//	private static final String STATE_ALIVE_VIEWSTRING = Messages.getString("ComponentPropertySource.state.alive");

	private static final String STATE_ERROR_VIEWSTRING = Messages.getString("ComponentPropertySource.state.error");

	private static final PropertyDescriptor[] componentPropertyDescriptor = new PropertyDescriptor[] {
			new TextPropertyDescriptor(Component.INSTANCE_NAME, DISP_INSTANCE_NAME),
			new TextPropertyDescriptor(Component.TYPE_NAME, DISP_TYPE_NAME),
			new TextPropertyDescriptor(Component.DESCRIPTION, DISP_DESCRIPTION),
			new TextPropertyDescriptor(Component.VERSION, DISP_VERSION),
			new TextPropertyDescriptor(Component.VENDER, DISP_VENDOR),
			new TextPropertyDescriptor(Component.CATEGORY, DISP_CATEGORY),
			new TextPropertyDescriptor(Component.STATE, DISP_STATE), };

	private Component component;

	/**
	 * {@inheritDoc}
	 * 
	 * @param component
	 *            モデル
	 */
	public ComponentPropertySource(Component component) {
		this.component = component;
	}

	/**
	 * {@inheritDoc}
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return componentPropertyDescriptor;
	}

	/**
	 * {@inheritDoc}
	 */
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		try {
			if (Component.INSTANCE_NAME.equals(id)) {
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
					if (c.getComponentState() == ExecutionContext.RTC_UNKNOWN) {
						return STATE_UNKNOWN_VIEWSTRING;
					} else if (c.getComponentState() == ExecutionContext.RTC_CREATED) {
						return STATE_CREATED_VIEWSTRING;
					} else if (c.getComponentState() == ExecutionContext.RTC_INACTIVE) {
						return STATE_INACTIVE_VIEWSTRING;
					} else if (c.getComponentState() == ExecutionContext.RTC_ACTIVE) {
						return STATE_ACTIVE_VIEWSTRING;
					} else if (c.getComponentState() == ExecutionContext.RTC_ERROR) {
						return STATE_ERROR_VIEWSTRING;
					}
				}
				
			}
		} catch (Exception e) {
			return "<unknown>";
		}

		return "<unknown>";
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isPropertySet(java.lang.Object id) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPropertyValue(java.lang.Object id) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPropertyValue(java.lang.Object id, java.lang.Object value) {
	}

	/**
	 * {@inheritDoc}
	 */
	public java.lang.Object getEditableValue() {
		return null;
	}

}
