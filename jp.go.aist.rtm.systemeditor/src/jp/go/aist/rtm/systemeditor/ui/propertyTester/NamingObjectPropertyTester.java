package jp.go.aist.rtm.systemeditor.ui.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

/**
 * expressions.definitionsで利用するためのプロパティテスタ (NamingObjectNode)
 */
public class NamingObjectPropertyTester extends PropertyTester {

	private static final Logger LOGGER = LoggerFactory.getLogger(NamingObjectPropertyTester.class);

	private static final String PROPERTY_HAS_CORBA_COMPONENT = "hasCorbaComponent";
	private static final String PROPERTY_HAS_MANAGER = "hasManager";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		LOGGER.trace("NamingObjectPropertyTester: property=<{}> args=<{}> receiver=<{}>", property, args, receiver);
		if (receiver instanceof NamingObjectNode) {
			if (PROPERTY_HAS_CORBA_COMPONENT.equals(property)) {
				return hasCorbaComponent((NamingObjectNode) receiver);
			} else if (PROPERTY_HAS_MANAGER.equals(property)) {
				return hasManager((NamingObjectNode) receiver);
			}
		}
		return false;
	}

	/** NamingObjectのモデルが CorbaComponentの場合にtrue */
	private boolean hasCorbaComponent(NamingObjectNode part) {
		LOGGER.trace("hasCorbaComponent: part=<{}>", part);
		return (part.getEntry() instanceof CorbaComponent);
	}

	/** NamingObjectのモデルが RTCManagerの場合にtrue */
	private boolean hasManager(NamingObjectNode part) {
		LOGGER.trace("hasManager: part=<{}>", part);
		return (part.getEntry() instanceof RTCManager);
	}

}
