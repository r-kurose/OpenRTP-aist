package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * CorbaStatusObserverのIPropertySourceクラス
 */
public class CorbaStatusObserverPropertySource extends AbstractPropertySource {

	static final String ID = "ID";
	static final String INTERFACE_TYPE = "INTERFACE_TYPE";
	static final String SERVICE = "SERVICE";

	static final String DISP_ID = "ID";
	static final String DISP_INTERFACE_TYPE = Messages
			.getString("CorbaStatusObserverPropertySource.disp.interface_type");
	static final String DISP_SERVICE = Messages
			.getString("CorbaStatusObserverPropertySource.disp.service");

	static final String UNKNOWN = Messages
			.getString("CorbaStatusObserverPropertySource.unknown");

	CorbaStatusObserver observer;

	public CorbaStatusObserverPropertySource(CorbaStatusObserver observer) {
		this.observer = observer;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		result.add(new TextPropertyDescriptor(ID, DISP_ID));
		result.add(new TextPropertyDescriptor(INTERFACE_TYPE,
				DISP_INTERFACE_TYPE));
		result.add(new TextPropertyDescriptor(SERVICE, DISP_SERVICE));
		for (String key : observer.getPropertyKeys()) {
			result.add(new TextPropertyDescriptor(new DynamicID("PROPERTIES",
					key), key));
		}
		return (IPropertyDescriptor[]) result
				.toArray(new IPropertyDescriptor[result.size()]);
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		String result = UNKNOWN;
		if (ID.equals(id)) {
			result = observer.getServiceProfile().id;
		} else if (INTERFACE_TYPE.equals(id)) {
			result = observer.getServiceProfile().interface_type;
		} else if (SERVICE.equals(id)) {
			result = observer.getServiceProfile().service.toString();
		} else if (id instanceof DynamicID) {
			DynamicID dynamicId = (DynamicID) id;
			if ("PROPERTIES".equals(dynamicId.categoryId)) {
				return observer.getProperty(dynamicId.subId);
			}
		}
		return result;
	}

}
