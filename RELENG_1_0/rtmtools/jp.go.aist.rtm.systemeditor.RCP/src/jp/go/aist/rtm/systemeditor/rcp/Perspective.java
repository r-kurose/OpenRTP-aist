package jp.go.aist.rtm.systemeditor.rcp;


import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editor = layout.getEditorArea();
		
		IFolderLayout left = layout.createFolder("nameService", IPageLayout.LEFT, 0.25f, editor);
		left.addView("jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView");
		left.addView("jp.go.aist.rtm.repositoryView.view");
		
		IFolderLayout right = layout.createFolder("property", IPageLayout.RIGHT, 0.7f, editor);
		right.addView(IPageLayout.ID_PROP_SHEET);
		
		IFolderLayout bottom = layout.createFolder("rtcView", IPageLayout.BOTTOM, 0.7f, editor);
		bottom.addView("jp.go.aist.rtm.systemeditor.ui.views.configurationview.ConfigurationView");
		bottom.addView("jp.go.aist.rtm.systemeditor.ui.views.managercontrolview.ManagerControlView");
		bottom.addView("jp.go.aist.rtm.systemeditor.ui.views.compositecomponentview.CompositeComponentView");
		
		layout.addActionSet("jp.go.aist.rtm.systemeditor.ui.actionSet");
		
		layout.getViewLayout("jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview.NameServiceView").setCloseable(false);
		layout.getViewLayout("jp.go.aist.rtm.repositoryView.view").setCloseable(false);
		layout.getViewLayout("org.eclipse.ui.views.PropertySheet").setCloseable(false);
		layout.getViewLayout("jp.go.aist.rtm.systemeditor.ui.views.configurationview.ConfigurationView").setCloseable(false);
		layout.getViewLayout("jp.go.aist.rtm.systemeditor.ui.views.managercontrolview.ManagerControlView").setCloseable(false);
		layout.getViewLayout("jp.go.aist.rtm.systemeditor.ui.views.compositecomponentview.CompositeComponentView").setCloseable(false);
	}

}
