package jp.go.aist.rtm.rtcbuilder.rcp;


import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RtcBuilderPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
//		layout.addActionSet("jp.go.aist.rtm.rtcbuilder.ui.actionSet");
		String editor = layout.getEditorArea();
		
		IFolderLayout left = layout.createFolder("packageExplorer", IPageLayout.LEFT, 0.25f, editor);
		left.addView(JavaUI.ID_PACKAGES);
		
		IFolderLayout bottom = layout.createFolder("rtcView", IPageLayout.BOTTOM, 0.7f, editor);
		bottom.addView("jp.go.aist.rtm.rtcbuilder.buildeview");
		
		layout.addActionSet("jp.go.aist.rtm.rtcbuilder.ui.actionSet");
		
		layout.getViewLayout(JavaUI.ID_PACKAGES).setCloseable(false);
		layout.getViewLayout("jp.go.aist.rtm.rtcbuilder.buildeview").setCloseable(false);
	}
}
