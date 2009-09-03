package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.List;

import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;

public class CreateCompositeComponentJob2 extends TimeoutWrappedJob {

	private Component compositeComponent;
	private List<Component> selectedComponents;

	@Override
	protected Object executeCommand() {
		return createCompositeComponent();
	}

	private Component createCompositeComponent() {
		SystemEditorWrapperFactory.getInstance()
			.getSynchronizationManager().assignSynchonizationSupport(
				compositeComponent);
		
		if (compositeComponent instanceof CorbaComponent) {
			// ìØä˙(SDOOrganization)
			compositeComponent
					.synchronizeLocalAttribute(ComponentPackage.eINSTANCE
							.getCorbaComponent_SDOOrganization());
		}
		
		compositeComponent.setComponentsR(selectedComponents);

		// ìØä˙
		compositeComponent.synchronizeLocalAttribute(null);
		compositeComponent.synchronizeLocalReference();

		// ConstraintÇê›íËÇ∑ÇÈ
		if (compositeComponent.getConstraint() == null) {
			Rectangle rectangle = new Rectangle();
			Component ac = selectedComponents.get(0);
			rectangle.setX(ac.getConstraint().getX());
			rectangle.setY(ac.getConstraint().getY());
			rectangle.setHeight(ac.getConstraint().getHeight());
			rectangle.setWidth(ac.getConstraint().getWidth());
			compositeComponent.setConstraint(rectangle);
		}
		return compositeComponent;
	}

	public void setSelectedComponents(List<Component> selectedComponents) {
		this.selectedComponents = selectedComponents;
	}

	public void setCompositeComponent(Component compositeComponent) {
		this.compositeComponent = compositeComponent;
	}

}
