package jp.go.aist.rtm.systemeditor.ui.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;

/**
 * expressions.definitionsで利用するためのプロパティテスタ (ComponentEditPart)
 */
public class ComponentEditPartPropertyTester extends PropertyTester {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentEditPartPropertyTester.class);

	private static final String PROPERTY_HAS_CORBA_COMPONENT = "hasCorbaComponent";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		LOGGER.trace("ComponentEditPartPropertyTester: property=<{}> args=<{}> receiver=<{}>", property, args,
				receiver);
		if (receiver instanceof ComponentEditPart) {
			if (PROPERTY_HAS_CORBA_COMPONENT.equals(property)) {
				return hasCorbaComponent((ComponentEditPart) receiver);
			}
		}
		return false;
	}

	/** Componentのモデルが CorbaComponentの場合にtrue */
	private boolean hasCorbaComponent(ComponentEditPart part) {
		LOGGER.trace("hasCorbaComponent: part=<{}>", part);
		return (part.getModel() instanceof CorbaComponent);
	}

}
