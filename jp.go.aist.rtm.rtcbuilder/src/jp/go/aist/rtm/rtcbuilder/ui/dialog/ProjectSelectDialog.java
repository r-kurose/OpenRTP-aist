package jp.go.aist.rtm.rtcbuilder.ui.dialog;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class ProjectSelectDialog extends TitleAreaDialog {

    private org.eclipse.swt.widgets.List targetProjectList;
    private String selectedProject;
    
    public String getSelectedProject() {
    	return selectedProject;
    }

    public ProjectSelectDialog(Shell parentShell) {
		super(parentShell);
		selectedProject = null;
	}

	protected Control createDialogArea(Composite parent) {
		setTitle("Select Project");
	    Composite composite = (Composite)super.createDialogArea(parent);
	    //
        targetProjectList = new org.eclipse.swt.widgets.List(composite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        GridData gd = new GridData(GridData.FILL_BOTH);
        targetProjectList.setLayoutData(gd);
        targetProjectList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				selectedProject = targetProjectList.getItem(targetProjectList.getSelectionIndex());
			}
        });
        try {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IResource[] targetProjects = root.members();
			for(int intIdx=0; intIdx<targetProjects.length; intIdx++) {
				targetProjectList.add(targetProjects[intIdx].getName());
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	    return composite;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected void buttonPressed(int buttonId) {
	    if (buttonId == IDialogConstants.OK_ID) {
	      setReturnCode(buttonId);
	    }
	    close();
	    super.buttonPressed(buttonId);
	}
}
