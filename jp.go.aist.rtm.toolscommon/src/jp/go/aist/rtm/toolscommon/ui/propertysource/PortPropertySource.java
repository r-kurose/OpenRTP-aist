package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * ポートのPropertySourceクラス
 */
public class PortPropertySource extends AbstractPropertySource {

	static final String DISP_PORT_NAME = "Name";

	static final String DISP_PORT_DATA_TYPE = "Data Type";

	static final String DISP_PORT_INTERFACE_TYPE = "Interface Type";

	static final String DISP_PORT_DATAFLOW_TYPE = "Dataflow Type";

	static final String DISP_PORT_SUBSCRIPTION_TYPE = "Subscription Type";

	static final String PROPERTIES_DYNAMICID_CATEGORY = "PROPERTIES";

	static final String PORT_NAME = "PORT_NAME";

	static final String PORT_DATAFLOW_TYPE = "PORT_DATAFLOW_TYPE";

	static final String PORT_DATA_TYPE = "PORT_DATA_TYPE";

	static final String PORT_INTERFACE_TYPE = "PORT_INTERFACE_TYPE";

	static final String PORT_SUBSCRIPTION_TYPE = "PORT_SUBSCRIPTION_TYPE";

	static final String UNKNOWN = "<unknown>";

	private Port port;

	/**
	 * コンストラクタ
	 * 
	 * @param port
	 *            モデル
	 */
	public PortPropertySource(Port inport) {
		this.port = inport;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		result.add(new TextPropertyDescriptor(PORT_NAME, DISP_PORT_NAME));

		addPropertyDescriptor(result, port.getDataType(),
				new TextPropertyDescriptor(PORT_DATA_TYPE, DISP_PORT_DATA_TYPE));
		addPropertyDescriptor(result, port.getInterfaceType(),
				new TextPropertyDescriptor(PORT_INTERFACE_TYPE,
						DISP_PORT_INTERFACE_TYPE));
		addPropertyDescriptor(result, port.getDataflowType(),
				new TextPropertyDescriptor(PORT_DATAFLOW_TYPE,
						DISP_PORT_DATAFLOW_TYPE));
		addPropertyDescriptor(result, port.getSubscriptionType(),
				new TextPropertyDescriptor(PORT_SUBSCRIPTION_TYPE,
						DISP_PORT_SUBSCRIPTION_TYPE));
		for (NameValue entry : port.getProperties()) {
			result.add(new TextPropertyDescriptor(new DynamicID(
					PROPERTIES_DYNAMICID_CATEGORY, entry.getName()), entry
					.getName()));
		}

		return (IPropertyDescriptor[]) result
				.toArray(new IPropertyDescriptor[result.size()]);
	}

	private void addPropertyDescriptor(List<IPropertyDescriptor> result,
			String value, TextPropertyDescriptor textPropertyDescriptor) {
		if (StringUtils.isBlank(value))
			return;
		result.add(textPropertyDescriptor);
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		try {
			if (PORT_NAME.equals(id)) {
				return port.getNameL();
			} else if (PORT_DATAFLOW_TYPE.equals(id)) {
				return sortValues(port.getDataflowTypes());
			} else if (PORT_DATA_TYPE.equals(id)) {
				return sortValues(port.getDataTypes());
			} else if (PORT_INTERFACE_TYPE.equals(id)) {
				return sortValues(port.getInterfaceTypes());
			} else if (PORT_SUBSCRIPTION_TYPE.equals(id)) {
				return sortValues(port.getSubscriptionTypes());
			} else if (id instanceof DynamicID) {
				DynamicID dynamicId = (DynamicID) id;
				if (PROPERTIES_DYNAMICID_CATEGORY.equals(dynamicId.categoryId)) {
					return port.getProperty(dynamicId.subId);
				}
			}
		} catch (Exception e) {
			return UNKNOWN;
		}

		return UNKNOWN;
	}

	String sortValues(List<String> list) {
		list = ConnectorUtil.sortTypes(list);
		String result = ConnectorUtil.join(list, ",");
		return result;
	}

}
