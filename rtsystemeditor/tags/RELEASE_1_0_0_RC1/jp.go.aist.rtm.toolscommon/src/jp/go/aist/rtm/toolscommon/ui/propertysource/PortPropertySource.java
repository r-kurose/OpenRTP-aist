package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * ポートのPropertySourceクラス
 */
public class PortPropertySource implements IPropertySource {
	private Port port;

	private static final String PROPERTIES_DYNAMICID_CATEGORY = "PROPERTIES";

	private static final String PORT_NAME = "PORT_NAME";
	private static final String PORT_DATAFLOW_TYPE = "PORT_DATAFLOW_TYPE";
	private static final String PORT_DATA_TYPE = "PORT_DATA_TYPE";
	private static final String PORT_INTERFACE_TYPE = "PORT_INTERFACE_TYPE";
	private static final String PORT_SUBSCRIPTION_TYPE = "PORT_SUBSCRIPTION_TYPE";

	/**
	 * コンストラクタ
	 * 
	 * @param port
	 *            モデル
	 */
	public PortPropertySource(Port inport) {
		this.port = inport;
	}

	/**
	 * {@inheritDoc}
	 */
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		try {
			if (PORT_NAME.equals(id)) {
				return port.getNameL();
			} else if (PORT_DATAFLOW_TYPE.equals(id)) {
				return port.getDataflowType();
			} else if (PORT_DATA_TYPE.equals(id)) {
				return port.getDataType();
			} else if (PORT_INTERFACE_TYPE.equals(id)) {
				return port.getInterfaceType();
			} else if (PORT_SUBSCRIPTION_TYPE.equals(id)) {
				return port.getSubscriptionType();
			} else if (id instanceof DynamicID) {
				DynamicID dynamicId = (DynamicID) id;
				if (PROPERTIES_DYNAMICID_CATEGORY.equals(dynamicId.categoryId)) {
					return port.getProperty(dynamicId.subId);
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

	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		result.add(new TextPropertyDescriptor(PORT_NAME, "Name"));
		
		addPropertyDescriptor(result, port.getDataType()
				, new TextPropertyDescriptor(PORT_DATA_TYPE, "Data Type"));
		addPropertyDescriptor(result, port.getInterfaceType()
				, new TextPropertyDescriptor(PORT_INTERFACE_TYPE, "Interface Type"));
		addPropertyDescriptor(result, port.getDataflowType()
				, new TextPropertyDescriptor(PORT_DATAFLOW_TYPE, "Dataflow Type"));
		addPropertyDescriptor(result, port.getSubscriptionType()
				, new TextPropertyDescriptor(PORT_SUBSCRIPTION_TYPE, "Subscription Type"));
		for (NameValue entry : port.getProperties()) {
			result.add(new TextPropertyDescriptor(new DynamicID(
					PROPERTIES_DYNAMICID_CATEGORY, entry.getName()), entry
					.getName()));
		}

		return (IPropertyDescriptor[]) result
				.toArray(new IPropertyDescriptor[result.size()]);
	}

	private void addPropertyDescriptor(List<IPropertyDescriptor> result, String value,
			TextPropertyDescriptor textPropertyDescriptor) {
		if (StringUtils.isBlank(value)) return;
		result.add(textPropertyDescriptor);
	}
}
