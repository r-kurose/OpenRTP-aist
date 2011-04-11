package jp.go.aist.rtm.toolscommon.model.component;

import RTC.PortInterfacePolarity;

/**
 * Corbaコンポーネントのサービスポートインターフェースプロファイル
 * RTC.PortInterfaceProfileに処理を委譲する
 *
 */
public class CorbaPortInterfaceProfile extends PortInterfaceProfile {

	private RTC.PortInterfaceProfile delegate;

	public CorbaPortInterfaceProfile(RTC.PortInterfaceProfile delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public String getInstanceName() {
		return delegate.instance_name;
	}

	@Override
	public void setInstanceName(String instanceName) {
		delegate.instance_name = instanceName;
	}

	@Override
	public String getTypeName() {
		return delegate.type_name;
	}

	@Override
	public void setTypeName(String typeName) {
		delegate.type_name = typeName;
	}

	@Override
	public String getPolarity() {
		int value = delegate.polarity.value();
		if (value == PortInterfacePolarity.PROVIDED.value()) {
			return POLARITY_PROVIDED;
		} else if (value == PortInterfacePolarity.REQUIRED.value()) {
			return POLARITY_REQUIRED;
		}	
		return POLARITY_UNKNOWN;
	}

	@Override
	public void setProvidedPolarity() {
		delegate.polarity = PortInterfacePolarity.PROVIDED;
	}

	@Override
	public void setRequiredPolarity() {
		delegate.polarity = PortInterfacePolarity.REQUIRED;
	}

	
}
