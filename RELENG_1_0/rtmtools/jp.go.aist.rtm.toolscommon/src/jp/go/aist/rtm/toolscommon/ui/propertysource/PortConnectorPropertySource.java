package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile.PROP;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * PortConnector‚ÌIPropertySourceƒNƒ‰ƒX
 */
public class PortConnectorPropertySource extends AbstractPropertySource {

	static final String DISP_ID_NAME = "Name";

	static final String DISP_DATA_TYPE = "Data Type";

	static final String DISP_INTERFACE_TYPE = "Interface Type";

	static final String DISP_DATAFLOW_TYPE = "Dataflow Type";

	static final String DISP_SUBSCRIPTION_TYPE = "Subscription Type";

	static final String DISP_PUSH_RATE = "Push Rate(Hz)";

	static final String DISP_PUSH_POLICY = "Push Policy";

	static final String DISP_SKIP_COUNT = "Skip Count";

	static final String DISP_OUTPORT_BUFF_LENGTH = "Outport Buffer length";

	static final String DISP_OUTPORT_FULL_POLICY = "Outport Buffer full policy";

	static final String DISP_OUTPORT_WRITE_TIMEOUT = "Outport Buffer write timeout";

	static final String DISP_OUTPORT_EMPTY_POLICY = "Outport Buffer empty policy";

	static final String DISP_OUTPORT_READ_TIMEOUT = "Outport Buffer read timeout";

	static final String DISP_INPORT_BUFF_LENGTH = "Inport Buffer length";

	static final String DISP_INPORT_FULL_POLICY = "Inport Buffer full policy";

	static final String DISP_INPORT_WRITE_TIMEOUT = "Inport Buffer write timeout";

	static final String DISP_INPORT_EMPTY_POLICY = "Inport Buffer empty policy";

	static final String DISP_INPORT_READ_TIMEOUT = "Inport Buffer read timeout";

	static final String ID_NAME = "NAME";

	static final String UNKNOWN = "<unknown>";

	private PortConnector portConnector;

	/**
	 * @param PortConnector
	 *            ƒ‚ƒfƒ‹
	 */
	public PortConnectorPropertySource(PortConnector portConnector) {
		this.portConnector = portConnector;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ConnectorProfile profile = portConnector.getConnectorProfile();
		if (profile == null) {
			return new TextPropertyDescriptor[] {};
		}
		List<TextPropertyDescriptor> descriptors = new ArrayList<TextPropertyDescriptor>();
		descriptors.add(new TextPropertyDescriptor(ID_NAME, DISP_ID_NAME));

		if ((portConnector.getSource() instanceof InPort)
				|| (portConnector.getSource() instanceof OutPort)) {
			descriptors.add(new TextPropertyDescriptor(PROP.DATA_TYPE, DISP_DATA_TYPE));
			descriptors.add(new TextPropertyDescriptor(PROP.INTERFACE_TYPE, DISP_INTERFACE_TYPE));
			descriptors.add(new TextPropertyDescriptor(PROP.DATAFLOW_TYPE, DISP_DATAFLOW_TYPE));
			if (profile.isSubscriptionTypeAvailable()) {
				descriptors.add(new TextPropertyDescriptor(PROP.SUBSCRIPTION_TYPE, DISP_SUBSCRIPTION_TYPE));
			}
			if (profile.isPushIntervalAvailable()) {
				descriptors.add(new TextPropertyDescriptor(PROP.PUSH_RATE, DISP_PUSH_RATE));
			}
			if (profile.isPushPolicyAvailable()) {
				descriptors.add(new TextPropertyDescriptor(PROP.PUSH_POLICY, DISP_PUSH_POLICY));
			}
			if (profile.isSkipCountAvailable()) {
				descriptors.add(new TextPropertyDescriptor(PROP.SKIP_COUNT, DISP_SKIP_COUNT));
			}
			//
			if (profile.getOutportBufferFullPolicy() != null
					|| profile.getOutportBufferEmptyPolicy() != null) {
				descriptors.add(new TextPropertyDescriptor(PROP.OUTPORT_BUFF_LENGTH, DISP_OUTPORT_BUFF_LENGTH));
				descriptors.add(new TextPropertyDescriptor(PROP.OUTPORT_FULL_POLICY, DISP_OUTPORT_FULL_POLICY));
				descriptors.add(new TextPropertyDescriptor(PROP.OUTPORT_WRITE_TIMEOUT, DISP_OUTPORT_WRITE_TIMEOUT));
				descriptors.add(new TextPropertyDescriptor(PROP.OUTPORT_EMPTY_POLICY, DISP_OUTPORT_EMPTY_POLICY));
				descriptors.add(new TextPropertyDescriptor(PROP.OUTPORT_READ_TIMEOUT, DISP_OUTPORT_READ_TIMEOUT));
			}
			//
			if (profile.getInportBufferFullPolicy() != null
					|| profile.getInportBufferEmptyPolicy() != null) {
				descriptors.add(new TextPropertyDescriptor(PROP.INPORT_BUFF_LENGTH, DISP_INPORT_BUFF_LENGTH));
				descriptors.add(new TextPropertyDescriptor(PROP.INPORT_FULL_POLICY, DISP_INPORT_FULL_POLICY));
				descriptors.add(new TextPropertyDescriptor(PROP.INPORT_WRITE_TIMEOUT, DISP_INPORT_WRITE_TIMEOUT));
				descriptors.add(new TextPropertyDescriptor(PROP.INPORT_EMPTY_POLICY, DISP_INPORT_EMPTY_POLICY));
				descriptors.add(new TextPropertyDescriptor(PROP.INPORT_READ_TIMEOUT, DISP_INPORT_READ_TIMEOUT));
			}
		} else if (portConnector.getSource() instanceof ServicePort) {
			for (String key : profile.getPropertyKeys()) {
				if (!ConnectorProfile.InterfaceId.isValid(key)) {
					continue;
				}
				descriptors.add(new TextPropertyDescriptor(key, key));
			}
		}

		return descriptors.toArray(new TextPropertyDescriptor []{});
	}

	@Override
	public Object getPropertyValue(Object id) {
		String result = null;
		try {
			ConnectorProfile profile = portConnector.getConnectorProfile();
			if (ID_NAME.equals(id)) {
				result = profile.getName();
			} else if (PROP.DATA_TYPE.equals(id)) {
				result = profile.getDataType();
			} else if (PROP.INTERFACE_TYPE.equals(id)) {
				result = profile.getInterfaceType();
			} else if (PROP.DATAFLOW_TYPE.equals(id)) {
				result = profile.getDataflowType();
			} else if (PROP.SUBSCRIPTION_TYPE.equals(id)) {
				result = profile.getSubscriptionType();
			} else if (PROP.PUSH_RATE.equals(id)) {
				result = profile.getPushRate().toString();
			} else if (PROP.PUSH_POLICY.equals(id)) {
				result = profile.getPushPolicy();
			} else if (PROP.SKIP_COUNT.equals(id)) {
				result = profile.getSkipCount().toString();

			} else if (PROP.OUTPORT_BUFF_LENGTH.equals(id)) {
				result = profile.getOutportBufferLength().toString();
			} else if (PROP.OUTPORT_FULL_POLICY.equals(id)) {
				result = profile.getOutportBufferFullPolicy();
			} else if (PROP.OUTPORT_WRITE_TIMEOUT.equals(id)) {
				result = profile.getOutportBufferWriteTimeout().toString();
			} else if (PROP.OUTPORT_EMPTY_POLICY.equals(id)) {
				result = profile.getOutportBufferEmptyPolicy();
			} else if (PROP.OUTPORT_READ_TIMEOUT.equals(id)) {
				result = profile.getOutportBufferReadTimeout().toString();

			} else if (PROP.INPORT_BUFF_LENGTH.equals(id)) {
				result = profile.getInportBufferLength().toString();
			} else if (PROP.INPORT_FULL_POLICY.equals(id)) {
				result = profile.getInportBufferFullPolicy();
			} else if (PROP.INPORT_WRITE_TIMEOUT.equals(id)) {
				result = profile.getInportBufferWriteTimeout().toString();
			} else if (PROP.INPORT_EMPTY_POLICY.equals(id)) {
				result = profile.getInportBufferEmptyPolicy();
			} else if (PROP.INPORT_READ_TIMEOUT.equals(id)) {
				result = profile.getInportBufferReadTimeout().toString();
			}
			//
			if (id instanceof String
					&& ConnectorProfile.InterfaceId.isValid((String) id)) {
				result = profile.getProperty((String) id);
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
