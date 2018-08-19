package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.openrtp.namespaces.deploy.Component;
import org.openrtp.namespaces.deploy.DeployProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeployLoadActionDelegate implements IEditorActionDelegate {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeployLoadActionDelegate.class);

	private ISelection selection;
	private AbstractSystemDiagramEditor targetEditor;

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (AbstractSystemDiagramEditor) targetEditor;
	}

	@Override
	public void run(IAction action) {
		FileDialog dialog = new FileDialog(targetEditor.getSite().getShell(),SWT.SAVE);
        dialog.setText("Load Deploy Info.");
		dialog.setFilterNames(new String[] {Messages.getString("DeployActionDelegate.0")}); //$NON-NLS-1$
		dialog.setFilterExtensions(new String[] { "*.xml" });
		String selectedFileName = dialog.open();
		if( selectedFileName==null ) return;
		//
		XmlHandler loader = new XmlHandler();
		DeployProfile profile = null;
		try {
			profile = loader.loadXmlDeploy(selectedFileName);
		} catch (Exception e) {
			LOGGER.error("Fail to load xml and deploy", e);
		}
		//
		SystemDiagram diagram = targetEditor.getSystemDiagram();
		for(Component target : profile.getComponents()) {
			EList<jp.go.aist.rtm.toolscommon.model.component.Component> compList = diagram.getComponents();
			loadDeployInfo(target, compList);
		}
	}

	private void loadDeployInfo(Component target,
			EList<jp.go.aist.rtm.toolscommon.model.component.Component> compList) {
		for(jp.go.aist.rtm.toolscommon.model.component.Component comp : compList) {
			if(target.getId().trim().equals(comp.getComponentId())
					&& target.getInstanceName().trim().equals(comp.getInstanceNameL().trim())) {
				comp.setProperty("DeployType", target.getDeployType());
				comp.setProperty("DeployTarget", target.getTarget());
				comp.setProperty("DeployIOR", target.getIor());
			} else {
				if( comp.isCompositeComponent() ) {
					loadDeployInfo(target, comp.getComponents());
				}
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		action.setEnabled(isEnable());
	}
	
	private boolean isEnable() {
		if (selection instanceof IStructuredSelection) {
			if( ((IStructuredSelection) selection).getFirstElement() instanceof SystemDiagramEditPart) {
				return true;
			}
		}
		return false;
	}
}
