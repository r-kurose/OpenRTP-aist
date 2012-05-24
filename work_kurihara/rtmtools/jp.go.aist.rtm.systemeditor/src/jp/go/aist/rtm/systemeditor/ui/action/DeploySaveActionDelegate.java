package jp.go.aist.rtm.systemeditor.ui.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;
import jp.go.aist.rtm.toolscommon.util.DeployProfileHandler;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.openrtp.namespaces.deploy.DeployProfile;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class DeploySaveActionDelegate implements IEditorActionDelegate {
	private ISelection selection;
	private AbstractSystemDiagramEditor targetEditor;

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (AbstractSystemDiagramEditor) targetEditor;
	}

	@Override
	public void run(IAction action) {
		FileDialog dialog = new FileDialog(targetEditor.getSite().getShell(),SWT.SAVE);
        dialog.setText("Save Deploy Info.");
		dialog.setFilterNames(new String[] {Messages.getString("DeployActionDelegate.0")}); //$NON-NLS-1$
		dialog.setFilterExtensions(new String[] { "*.xml" });
		String selectedFileName = dialog.open();
		if( selectedFileName==null ) return;

		SystemDiagram diagram = targetEditor.getSystemDiagram();
		XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl(new GregorianCalendar());
		calendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		diagram.setCreationDate(calendar.toString());
		diagram.setUpdateDate(calendar.toString());
		//
		DeployProfileHandler handler = new DeployProfileHandler();
		DeployProfile profile = handler.save(diagram);
		//
		XmlHandler saver = new XmlHandler();
		try {
			saver.saveXmlDeploy(profile, URLDecoder.decode(selectedFileName,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		action.setEnabled(isEnable());
	}
	
	@SuppressWarnings("unchecked")
	private boolean isEnable() {
		if (selection instanceof IStructuredSelection) {
			if( ((IStructuredSelection) selection).getFirstElement() instanceof SystemDiagramEditPart) {
				return true;
			}
		}
		return false;
	}
}
