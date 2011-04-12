package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * ContextHandler‚ÌIPropertySourceƒNƒ‰ƒX
 */
public class ContextHandlerPropertySource extends AbstractPropertySource {

	/**
	 * @param handler
	 *            ƒ‚ƒfƒ‹
	 */
	public ContextHandlerPropertySource(ContextHandler handler) {
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new PropertyDescriptor[] {};
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		return null;
	}

}
