package jp.go.aist.rtm.toolscommon.ui.propertysource;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public abstract class AbstractPropertySource implements IPropertySource {

	protected Object parent;

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public Object getParent() {
		return parent;
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[0];
	}

	@Override
	public Object getPropertyValue(Object id) {
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
	}

}
