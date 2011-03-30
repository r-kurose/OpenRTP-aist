package jp.go.aist.rtm.toolscommon.ui.propertysource;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class SystemDiagramPropertySource extends AbstractPropertySource {

	static final String SYSTEM_ID = "SYSTEM_ID";
	static final String KIND = "KIND";
	static final String CREATE_DATE = "CREATE_DATE";
	static final String UPDATE_DATE = "UPDATE_DATE";
	static final String COMPOSITE = "COMPOSITE";

	static final String DISP_SYSTEM_ID = Messages
			.getString("SystemDiagramPropertySource.disp.system_id");
	static final String DISP_KIND = Messages
			.getString("SystemDiagramPropertySource.disp.kind");
	static final String DISP_CREATE_DATE = Messages
			.getString("SystemDiagramPropertySource.disp.create_date");
	static final String DISP_UPDATE_DATE = Messages
			.getString("SystemDiagramPropertySource.disp.update_date");
	static final String DISP_COMPOSITE = Messages
			.getString("SystemDiagramPropertySource.disp.composite");

	static final String UNKNOWN = "<unknown>";

	SystemDiagram diagram;

	public SystemDiagramPropertySource(SystemDiagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
		result.add(new TextPropertyDescriptor(SYSTEM_ID, DISP_SYSTEM_ID));
		result.add(new TextPropertyDescriptor(KIND, DISP_KIND));
		result.add(new TextPropertyDescriptor(CREATE_DATE, DISP_CREATE_DATE));
		result.add(new TextPropertyDescriptor(UPDATE_DATE, DISP_UPDATE_DATE));
		result.add(new TextPropertyDescriptor(COMPOSITE, DISP_COMPOSITE));
		for (String key : diagram.getPropertyKeys()) {
			result.add(new TextPropertyDescriptor(new DynamicID("PROPERTIES",
					key), key));
		}
		return (IPropertyDescriptor[]) result
				.toArray(new IPropertyDescriptor[result.size()]);
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		String result = UNKNOWN;
		try {
			if (SYSTEM_ID.equals(id)) {
				result = diagram.getSystemId();
			} else if (KIND.equals(id)) {
				result = diagram.getKind().toString();
			} else if (CREATE_DATE.equals(id)) {
				result = diagram.getCreationDate();
			} else if (UPDATE_DATE.equals(id)) {
				result = diagram.getUpdateDate();
			} else if (COMPOSITE.equals(id)) {
				Component comp = diagram.getCompositeComponent();
				if (comp == null) {
					result = "None";
				} else {
					result = comp.getInstanceNameL();
				}
			} else if (id instanceof DynamicID) {
				DynamicID dynamicId = (DynamicID) id;
				if ("PROPERTIES".equals(dynamicId.categoryId)) {
					return diagram.getProperty(dynamicId.subId);
				}
			}
		} catch (Exception e) {
			// void
		}

		return result;
	}

}
