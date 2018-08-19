package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.impl.NameServerManagerImpl;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.DeployUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class DeploymentSettingDialog extends Dialog {
	
	final static public String KEY_DEPLOY_TYPE = "DeployType";
	final static public String KEY_DEPLOY_TARGET = "DeployTarget";
	final static public String KEY_DEPLOY_IOR = "DeployIOR";
	
	final static public String KEY_DEPLOY_TYPE_COMPONENT = "Component";
	final static public String KEY_DEPLOY_TYPE_MANAGER = "Manager";
	
	private Component targetComponent;
	private java.util.List<CorbaComponent> componentCandidates;
	private java.util.List<RTCManager> managerCandidates;
	
	private CTabFolder tabFolder;
	private List listComp;
	private List listManager;

	public DeploymentSettingDialog(Shell shell, Component target) {
		super(shell);
		this.setShellStyle(this.getShellStyle() | SWT.RESIZE  );
		this.targetComponent = target;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout(1, false);

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		//
		tabFolder = new CTabFolder(mainComposite,SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.widthHint = 400;
		gd.heightHint = 200;
		tabFolder.setSimple(false);
		tabFolder.setLayoutData(gd);
		componentCandidates = new ArrayList<CorbaComponent>(); 
		if( targetComponent.isCompositeComponent()==false ) {
			CTabItem tabComponent = new CTabItem(tabFolder,SWT.NONE);
			tabComponent.setText("Component");
			//
			NameServerManager ns = NameServerManagerImpl.getInstance();
			EList<?> nscomps = ns.getNodes();
			componentCandidates = DeployUtil.searchComponentList(nscomps, componentCandidates);
			listComp = new List(tabFolder,SWT.SINGLE|SWT.BORDER|SWT.V_SCROLL);
			for(int index=0;index<componentCandidates.size();index++) {
				CorbaComponent comp = componentCandidates.get(index);
				listComp.add(comp.getPathId());
			}
			tabComponent.setControl(listComp);
		}
		CTabItem tabManager = new CTabItem(tabFolder,SWT.NONE);
		tabManager.setText("Manager");
		//
		managerCandidates = DeployUtil.searchManager(targetComponent);
		listManager = new List(tabFolder,SWT.SINGLE|SWT.BORDER|SWT.V_SCROLL);
		for(int index=0;index<managerCandidates.size();index++) {
			RTCManager manager = managerCandidates.get(index);
			listManager.add(manager.getPathId());
		}
		tabManager.setControl(listManager);
		
		load();
		
		return mainComposite;
	}
	
	private void load() {
		String type = targetComponent.getProperty(KEY_DEPLOY_TYPE);
		String target = targetComponent.getProperty(KEY_DEPLOY_TARGET);
		if( type==null || type.length()==0 ) return;
		if( type.equals(KEY_DEPLOY_TYPE_COMPONENT) ) {
			tabFolder.setSelection(0);
			if( target==null || target.length()==0 ) return;
			for(int index=0;index<listComp.getItemCount();index++) {
				if(target.equals(listComp.getItem(index).trim())) {
					listComp.setSelection(index);
					return;
				}
			}
			
		} else if(type.equals(KEY_DEPLOY_TYPE_MANAGER) ) {
			if( targetComponent.isCompositeComponent() ) {
				tabFolder.setSelection(0);
			} else {
				tabFolder.setSelection(1);
			}
			if( target==null || target.length()==0 ) return;
			for(int index=0;index<listManager.getItemCount();index++) {
				if(target.equals(listManager.getItem(index).trim())) {
					listManager.setSelection(index);
					return;
				}
			}
		}
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("DeploymentInformationDialog.0")); //$NON-NLS-1$
	}
	
	@Override
	protected void okPressed() {
		int tabSelection = tabFolder.getSelectionIndex();
		if(tabSelection==0) {
			if( targetComponent.isCompositeComponent() ) {
				targetComponent.setProperty(KEY_DEPLOY_TYPE, KEY_DEPLOY_TYPE_MANAGER);
				int listSelection = listManager.getSelectionIndex();
				targetComponent.setProperty(KEY_DEPLOY_TARGET, managerCandidates.get(listSelection).getPathId());
				targetComponent.setProperty(KEY_DEPLOY_IOR, "");
			} else {
				targetComponent.setProperty(KEY_DEPLOY_TYPE, KEY_DEPLOY_TYPE_COMPONENT);
				int listSelection = listComp.getSelectionIndex();
				targetComponent.setProperty(KEY_DEPLOY_TARGET, componentCandidates.get(listSelection).getPathId());
				targetComponent.setProperty(KEY_DEPLOY_IOR, componentCandidates.get(listSelection).getIor());
				
			}
		} else {
			targetComponent.setProperty(KEY_DEPLOY_TYPE, KEY_DEPLOY_TYPE_MANAGER);
			int listSelection = listManager.getSelectionIndex();
			targetComponent.setProperty(KEY_DEPLOY_TARGET, managerCandidates.get(listSelection).getPathId());
			targetComponent.setProperty(KEY_DEPLOY_IOR, "");
		}
		
		super.okPressed();
	}
}
