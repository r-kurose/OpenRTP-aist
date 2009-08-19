package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.nl.Messages;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PortInterfaceProfilePropertySource;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * PortInterfaceProfile‚ð•\Œ»‚·‚éƒNƒ‰ƒX 
 */
public class PortInterfaceProfile implements IAdaptable {
	protected static final String POLARITY_PROVIDED = Messages.getString("PortInterfaceProfilePropertySource.polarity.provided");
	protected static final String POLARITY_REQUIRED = Messages.getString("PortInterfaceProfilePropertySource.polarity.required");
	protected static final String POLARITY_UNKNOWN = Messages.getString("PortInterfaceProfilePropertySource.unknown");

	private String instanceName;
	private String typeName;
	private String polarity;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PortInterfaceProfile == false) {
			return false;
		}

		PortInterfaceProfile p = (PortInterfaceProfile) obj;

		return new EqualsBuilder().append(getInstanceName(),
				p.getInstanceName()).append(getTypeName(),
				p.getTypeName()).append(getPolarity(),
				p.getPolarity()).isEquals();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		if (IPropertySource.class.equals(adapter)) {
			return new PortInterfaceProfilePropertySource(this);
		}
		
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPolarity() {
		return polarity;
	}

	public void setProvidedPolarity() {
		polarity = POLARITY_PROVIDED;
	}

	public void setRequiredPolarity() {
		polarity = POLARITY_REQUIRED;
	}

	public boolean isProvidedPolarity() {
		return getPolarity().equals(POLARITY_PROVIDED);
	}

	public boolean isRequiredPolarity() {
		return getPolarity().equals(POLARITY_REQUIRED);
	}
}