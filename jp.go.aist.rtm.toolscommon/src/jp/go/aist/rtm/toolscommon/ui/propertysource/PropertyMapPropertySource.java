package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;

public class PropertyMapPropertySource extends AbstractPropertySource {

	static final String UNKNOWN = "<unknown>";

	IPropertyMap propertyMap;

	public PropertyMapPropertySource(IPropertyMap propertyMap) {
		this.propertyMap = propertyMap;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		for (String key : propertyMap.getPropertyKeys()) {
			result.add(new TextPropertyDescriptor(new DynamicID("PROPERTIES",
					key), key));
		}
		return (IPropertyDescriptor[]) result
				.toArray(new IPropertyDescriptor[result.size()]);
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		try {
			if (id instanceof DynamicID) {
				DynamicID dynamicId = (DynamicID) id;
				if ("PROPERTIES".equals(dynamicId.categoryId)) {
					return propertyMap.getProperty(dynamicId.subId);
				}
			}
		} catch (Exception e) {
			return UNKNOWN;
		}
		return UNKNOWN;
	}

}
