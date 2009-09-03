package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * PortInterfaceProfileのPropertySourceクラス
 */
public class PortInterfaceProfilePropertySource implements IPropertySource {

	private static final String DISP_INSTANCE_NAME = Messages.getString("PortInterfaceProfilePropertySource.disp.instance_name");

	private static final String DISP_TYPE_NAME = Messages.getString("PortInterfaceProfilePropertySource.disp.type_name");

	private static final String DISP_PORT_INTERFACE_POLARITY = Messages.getString("PortInterfaceProfilePropertySource.disp.port_interface_polarity");

	private static final String INSTANCE_NAME = "INSTANCE_NAME";

	private static final String TYPE_NAME = "TYPE_NAME";

	private static final String PORT_INTERFACE_POLARITY = "PORT_INTERFACE_POLARITY";

	private static final String UNKNOWN = Messages.getString("PortInterfaceProfilePropertySource.unknown");


	private static final IPropertyDescriptor[] PROPERTY_DESCRIPTORS = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(INSTANCE_NAME, DISP_INSTANCE_NAME),
			new TextPropertyDescriptor(TYPE_NAME, DISP_TYPE_NAME),
			new TextPropertyDescriptor(PORT_INTERFACE_POLARITY, DISP_PORT_INTERFACE_POLARITY) };

	private PortInterfaceProfile portInterfaceProfile;

	/**
	 * コンストラクタ
	 * 
	 * @param portInterfaceProfile
	 *            モデル
	 */
	public PortInterfaceProfilePropertySource(
			PortInterfaceProfile portInterfaceProfile) {
		this.portInterfaceProfile = portInterfaceProfile;
	}

	/**
	 * {@inheritDoc}
	 */
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		try {
			if (INSTANCE_NAME.equals(id)) {
				return portInterfaceProfile.getInstanceName();
			} else if (TYPE_NAME.equals(id)) {
				return portInterfaceProfile.getTypeName();
			} else if (PORT_INTERFACE_POLARITY.equals(id)) {
				return portInterfaceProfile.getPolarity();
			}
		} catch (Exception e) {
			// void
		}

		return UNKNOWN;
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

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return PROPERTY_DESCRIPTORS;
	}
}
