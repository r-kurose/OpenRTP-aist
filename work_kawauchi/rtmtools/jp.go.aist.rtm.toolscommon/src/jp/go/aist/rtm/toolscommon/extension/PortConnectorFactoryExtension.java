package jp.go.aist.rtm.toolscommon.extension;

import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

public abstract class PortConnectorFactoryExtension {

	protected Port source;
	protected Port target;

	public abstract boolean canCreate();

	public abstract PortConnector createPortConnector();

	public Port getSource() {
		return source;
	}

	public void setSource(Port source) {
		this.source = source;
	}

	public Port getTarget() {
		return target;
	}

	public void setTarget(Port target) {
		this.target = target;
	}

}
