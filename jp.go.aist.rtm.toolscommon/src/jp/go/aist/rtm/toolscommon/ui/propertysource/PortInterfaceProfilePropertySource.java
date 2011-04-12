package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * PortInterfaceProfileのPropertySourceクラス
 */
public class PortInterfaceProfilePropertySource extends AbstractPropertySource {

	static final String DISP_INSTANCE_NAME = Messages.getString("PortInterfaceProfilePropertySource.disp.instance_name");

	static final String DISP_TYPE_NAME = Messages.getString("PortInterfaceProfilePropertySource.disp.type_name");

	static final String DISP_PORT_INTERFACE_POLARITY = Messages.getString("PortInterfaceProfilePropertySource.disp.port_interface_polarity");

	static final String INSTANCE_NAME = "INSTANCE_NAME";

	static final String TYPE_NAME = "TYPE_NAME";

	static final String PORT_INTERFACE_POLARITY = "PORT_INTERFACE_POLARITY";

	static final String UNKNOWN = Messages.getString("PortInterfaceProfilePropertySource.unknown");

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

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor(INSTANCE_NAME, DISP_INSTANCE_NAME),
				new TextPropertyDescriptor(TYPE_NAME, DISP_TYPE_NAME),
				new TextPropertyDescriptor(PORT_INTERFACE_POLARITY,
						DISP_PORT_INTERFACE_POLARITY) };
	}

	@Override
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

}
