package jp.go.aist.rtm.systemeditor.ui.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.impl.NameServerManagerImpl;
import jp.go.aist.rtm.systemeditor.factory.Rehabilitation;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.dialog.DeploymentSettingDialog;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.NullEditorInput;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.DeployUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.RtsProfileHandler;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.ExecutionContext;
import org.openrtp.namespaces.rts.version02.ObjectFactory;
import org.openrtp.namespaces.rts.version02.Participants;
import org.openrtp.namespaces.rts.version02.Property;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.openrtp.namespaces.rts.version02.TargetComponent;
import org.openrtp.namespaces.rts.version02.TargetComponentExt;
import org.openrtp.namespaces.rts.version02.TargetPort;
import org.openrtp.namespaces.rts.version02.TargetPortExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class DeployActionDelegate implements IEditorActionDelegate {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeployActionDelegate.class);

	private ISelection selection;
	private AbstractSystemDiagramEditor targetEditor;

	/**
	* アクションのメインの実行メソッド
	* 
	*/
	public void run(final IAction action) {
		//一旦オフラインプロファイルの形式に変換
		SystemDiagram diagram = targetEditor.getSystemDiagram();
		XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl(new GregorianCalendar());
		calendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		diagram.setCreationDate(calendar.toString());
		diagram.setUpdateDate(calendar.toString());
		RtsProfileHandler handler = new RtsProfileHandler();
		RtsProfileExt profile = handler.save(diagram);
		
		//プロファイル情報の書き換え
		ObjectFactory factory = new ObjectFactory();
		//事前処理
		prepareProfile(profile, factory);
		//候補コンポーネントの取得
		NameServerManager ns = NameServerManagerImpl.getInstance();
		EList nscomps = ns.getNodes();
		List<CorbaComponent> componentCandidates = new ArrayList<CorbaComponent>(); 
		componentCandidates = DeployUtil.searchComponentList(nscomps, componentCandidates);
		
		EList<Component> comps = targetEditor.getSystemDiagram().getComponents();
		Map<String, CorbaComponent> replaced = new HashMap<String, CorbaComponent>();
		//プロファイル情報の書き換え(複合RTC以外)
		modifyComponents(profile, ns, componentCandidates, factory, comps, replaced);
		List<org.openrtp.namespaces.rts.version02.Component> removed = new ArrayList<org.openrtp.namespaces.rts.version02.Component>();
		//デプロイ情報の整合性の確認
		if( checkDeployInfo(profile, replaced)==false ) {
			return;
		}
		 
		//プロファイル情報の書き換え
		for(org.openrtp.namespaces.rts.version02.Component target : profile.getComponents()) {
			CorbaComponent source = replaced.get(target.getId().trim() + target.getInstanceName().trim());
			if( source==null ) {
				removed.add(target);
				continue;
			}
			if( target.getCompositeType()==null || target.getCompositeType().equals("None") ) {
				target.setPathUri(source.getPathId());
				target.setInstanceName(source.getInstanceNameL());
				for(ExecutionContext ec : target.getExecutionContexts() ) {
					if( ec.getId().equals("default") ) ec.setId("0");
				}
				Property prop = factory.createProperty();
				prop.setName("IOR");
				prop.setValue(source.getIor());
				((ComponentExt)target).getProperties().add(prop);
				
			} else {
				for(Participants parts : target.getParticipants()) {
					TargetComponent part = parts.getParticipant();
					Component sourcePart = replaced.get(part.getComponentId().trim() + part.getInstanceName().trim());
					part.setInstanceName(sourcePart.getInstanceNameL());
					//
					boolean isHit = false;
					for( Property dpp : ((TargetComponentExt)part).getProperties()) {
						if( dpp.getName().equals("COMPONENT_PATH_ID") ) {
							dpp.setValue(sourcePart.getPathId());
							isHit = true;
							break;
						}
					}
					if( !isHit ) {
						Property path = factory.createProperty();
						path.setName("COMPONENT_PATH_ID");
						path.setValue(sourcePart.getPathId());
						((TargetComponentExt)part).getProperties().add(path);
					}
				}
			}
			for( DataportConnector dpc : profile.getDataPortConnectors() ) {
				modifyTargetComponent(factory, target.getId(), source, dpc.getSourceDataPort());
				modifyTargetComponent(factory, target.getId(), source, dpc.getTargetDataPort());
			}
			for( ServiceportConnector svc : profile.getServicePortConnectors() ) {
				modifyTargetComponent(factory, target.getId(), source, svc.getSourceServicePort());
				modifyTargetComponent(factory, target.getId(), source, svc.getTargetServicePort());
			}
			modifyConfigurationSet(factory, target);
		}
		//デプロイ情報がない要素の削除
		removeImComplete(profile, removed);
		//新規オンラインエディタの生成
		createNewOnlineEditor(profile);
	}

	private void removeImComplete(RtsProfileExt profile,
			List<org.openrtp.namespaces.rts.version02.Component> removed) {
		profile.getComponents().removeAll(removed);
		if( 0<removed.size()) {
			List<ServiceportConnector> removedSrv = new ArrayList<ServiceportConnector>();
			for(ServiceportConnector source : profile.getServicePortConnectors() ) {
				for(org.openrtp.namespaces.rts.version02.Component target : removed) {
					if( source.getSourceServicePort().getComponentId().equals(target.getId())
							&& source.getSourceServicePort().getInstanceName().equals(target.getInstanceName()) ) {
						removedSrv.add(source);
						break;
					}
					if( source.getTargetServicePort().getComponentId().equals(target.getId())
							&& source.getTargetServicePort().getInstanceName().equals(target.getInstanceName()) ) {
						removedSrv.add(source);
						break;
					}
				}
			}
			profile.getServicePortConnectors().removeAll(removedSrv);
			//
			List<DataportConnector> removedData = new ArrayList<DataportConnector>();
			for(DataportConnector source : profile.getDataPortConnectors() ) {
				for(org.openrtp.namespaces.rts.version02.Component target : removed) {
					if( source.getSourceDataPort().getComponentId().equals(target.getId())
							&& source.getSourceDataPort().getInstanceName().equals(target.getInstanceName()) ) {
						removedData.add(source);
						break;
					}
					if( source.getTargetDataPort().getComponentId().equals(target.getId())
							&& source.getTargetDataPort().getInstanceName().equals(target.getInstanceName()) ) {
						removedData.add(source);
						break;
					}
				}
			}
			profile.getDataPortConnectors().removeAll(removedData);
		}
	}

	private boolean checkDeployInfo(RtsProfileExt profile, Map<String, CorbaComponent> replaced) {
		boolean isImcomp = false;
		 for(org.openrtp.namespaces.rts.version02.Component target : profile.getComponents()) {
			 if( replaced.get(target.getId().trim() + target.getInstanceName().trim())==null ) {
				 isImcomp = true;
				break;
			 }
		 }
		 if(isImcomp) {
			 if(MessageDialog.openConfirm(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), "Deploy", Messages.getString("Deployment.0"))==false ) {
				 return false;
			 }
		 }
		 return true;
	}

	private void createNewOnlineEditor(RtsProfileExt profile) {
		//新規オンラインエディタの生成
		IWorkbenchWindow window = targetEditor.getSite().getWorkbenchWindow();
		IEditorPart newWindow = null;
		try {
			newWindow = window.getActivePage().openEditor(new NullEditorInput(),
					SystemDiagramEditor.SYSTEM_DIAGRAM_EDITOR_ID);
		} catch (PartInitException e) {
			LOGGER.error("Fail to open editor", e);
		}
		SystemDiagramEditor editor = (SystemDiagramEditor) newWindow;
		try {
			loadFromOffline(editor, profile);
		} catch (PartInitException e) {
			LOGGER.error("Fail to load from offline", e);
		}
	}

	private void prepareProfile(RtsProfileExt profile, ObjectFactory factory) {
		org.openrtp.namespaces.rts.version02.Component baseComp = null;
		for(org.openrtp.namespaces.rts.version02.Component target : profile.getComponents()) {
			if( target.getCompositeType()==null || target.getCompositeType().equals("None") ) {
				baseComp = target;
				//デフォルトフィギュレーションセットの設定
				if( target.getConfigurationSets().size()==0 ) {
					ConfigurationSet config = factory.createConfigurationSet();
					config.setId("default");
					target.getConfigurationSets().add(config);
					target.setActiveConfigurationSet("default");
				}
			}
		}
		//
		String baseIds[] = baseComp.getId().split(":");
		String baseCategory = baseIds[1];
		for(org.openrtp.namespaces.rts.version02.Component target : profile.getComponents()) {
			if( !(target.getCompositeType()==null || target.getCompositeType().equals("None")) ) {
				//複合RTCのID情報の書き換え
				String strId = target.getId();
				String strIds[] = strId.split(":");
				if(strIds[1]==null || strIds[1].length()<=0) {
					strIds[1] = baseCategory;
				}
				String newId;
				if(strIds.length<5) {
					newId = strIds[0] + ":" + strIds[1] + ":" + strIds[2] + ":" + strIds[3] + ":0.1";
				} else {
					newId = strIds[0] + ":" + strIds[1] + ":" + strIds[2] + ":" + strIds[3] + ":" + strIds[4];
				}
				target.setId(newId);
			}
		}
	}

	private boolean modifyComponents(RtsProfileExt profile,
			NameServerManager ns, List<CorbaComponent> componentCandidates,
			ObjectFactory factory, EList<Component> comps, Map<String, CorbaComponent> replaced) {
		for(Component target : comps) {
			if( target.isCompositeComponent() == false ) {
				String type = target.getProperty(DeploymentSettingDialog.KEY_DEPLOY_TYPE);
				if( type==null || type.length()==0 ) continue;
				//Profile中のコンポーネント情報の探索
				org.openrtp.namespaces.rts.version02.Component propTarget = searchProfileComp(profile, target);
				if(propTarget==null) return false;
				
				//実コンポーネント中から対象コンポーネントの探索
				CorbaComponent actTarget = null;
				if( type.equals(DeploymentSettingDialog.KEY_DEPLOY_TYPE_COMPONENT)) {
					actTarget = searchActiveComp(componentCandidates, target);
				} else if( type.equals(DeploymentSettingDialog.KEY_DEPLOY_TYPE_MANAGER)) {
					actTarget = searchActiveCompByManager(ns, factory, target, propTarget);
				}
				if(actTarget==null) return false;
				replaced.put(propTarget.getId().trim() + propTarget.getInstanceName().trim(), actTarget);
				
			} else {
				org.openrtp.namespaces.rts.version02.ComponentExt propTarget = searchProfileComp(profile, target);
				propTarget.setPathUri(target.getProperty(DeploymentSettingDialog.KEY_DEPLOY_TARGET));
				propTarget.setInstanceName(target.getInstanceNameL());
				EList<Component> subComps = target.getComponents();
				if(modifyComponents(profile, ns, componentCandidates, factory, subComps, replaced)==false) {
					return false;
				}
				//
			}
		}
		return true;
	}

	private CorbaComponent searchActiveCompByManager(NameServerManager ns, ObjectFactory factory,
			Component target, org.openrtp.namespaces.rts.version02.Component propTarget) {
		CorbaComponent result = null;
		EList nscomps;
		//設定したManager情報を検索
		java.util.List<RTCManager> managerCandidates = DeployUtil.searchManager(target);
		String refId = target.getProperty(DeploymentSettingDialog.KEY_DEPLOY_TARGET);
		RTCManager actManager = null;
		for(int index=0;index<managerCandidates.size();index++) {
			RTCManager manager = managerCandidates.get(index);
			if( manager.getPathId().equals(refId) ) {
				actManager = manager;
				break;
			}
		}
		if(propTarget==null ) return result;
		//
		EList createds = actManager.getComponentInstanceNamesR();
		jp.go.aist.rtm.toolscommon.model.component.Component created = null;
		Property prop = factory.createProperty();
		for(int index=0;index<createds.size();index++) {
			if( ((String)createds.get(index)).equals(propTarget.getInstanceName()) ) {
				created = ComponentFactory.eINSTANCE.createCorbaComponent();
				prop.setName("IOR");
				prop.setValue( actManager.getComponentsR().get(index).toString());
				break;
			}
		}
		if( created==null ) {
			created = actManager.createComponentR(createParam(propTarget));
			//パラメータフル指定でRTCを生成できなかった場合には型のみ指定して試す
			if( created==null ) {
				created = actManager.createComponentR(createParamAlt(propTarget));
			}
			prop.setName("IOR");
			prop.setValue(((CorbaComponent)created).getCorbaObject().toString());
		}
		//
		((ComponentExt)propTarget).getProperties().add(prop);
		for(ExecutionContext ec : propTarget.getExecutionContexts() ) {
			if( ec.getId().equals("default") ) ec.setId("0");
		}
		if( propTarget.getConfigurationSets().size()==0 ) {
			ConfigurationSet configSet = factory.createConfigurationSet();
			configSet.setId("default");
			propTarget.getConfigurationSets().add(configSet);
			
		}
		((NameServerManagerImpl)ns).refreshAll();
		nscomps = ns.getNodes();
		for( Property property : ((ComponentExt)propTarget).getProperties() ) {
			if( !property.getName().equals("IOR")) continue;
			CorbaComponent corbaComp = DeployUtil.searchComponent(property.getValue(), nscomps);
			if( corbaComp!=null ) {
				propTarget.setPathUri(corbaComp.getPathId());
				corbaComp.setComponentId(propTarget.getId());
				corbaComp.setIor(prop.getValue());
				result = corbaComp;
				break;
			}
		}
		return result;
	}

	private CorbaComponent searchActiveComp(List<CorbaComponent> componentCandidates, Component target) {
		String ior = target.getProperty(DeploymentSettingDialog.KEY_DEPLOY_IOR);
		String refId = target.getProperty(DeploymentSettingDialog.KEY_DEPLOY_TARGET);
		CorbaComponent actTarget = null;
		for(Component actComp : componentCandidates) {
			if( ((CorbaComponent)actComp).getIor().equals(ior) ) {
				actTarget = (CorbaComponent)actComp;
				return actTarget;
			}
		}
		//IORでは見つからなかった場合
		for(Component actComp : componentCandidates) {
			if( ((CorbaComponent)actComp).getPathId().equals(refId) ) {
				actTarget = (CorbaComponent)actComp;
				return actTarget;
			}
		}
		return null;
	}

	private org.openrtp.namespaces.rts.version02.ComponentExt searchProfileComp(RtsProfileExt profile, Component target) {
		org.openrtp.namespaces.rts.version02.ComponentExt propTarget = null;
		for(org.openrtp.namespaces.rts.version02.Component proComp: profile.getComponents() ) {
			if(proComp.getPathUri().equals(target.getPathId()) && proComp.getInstanceName().equals(target.getInstanceNameL())) {
				propTarget = (org.openrtp.namespaces.rts.version02.ComponentExt)proComp;
				break;
			}
		}
		return propTarget;
	}

	protected void loadFromOffline(final SystemDiagramEditor editor, final RtsProfileExt profile) throws PartInitException {
		try {
			if (editor.getSystemDiagram() != null) {
				editor.getSystemDiagram().setSynchronizeInterval(0);
			}

			ProgressMonitorDialog dialog = new ProgressMonitorDialog(editor.getEditorSite().getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException,
						InterruptedException {

					monitor.beginTask(Messages.getString("SystemDiagramEditor.3"), 100); //$NON-NLS-1$
					monitor.subTask(Messages.getString("SystemDiagramEditor.4")); //$NON-NLS-1$
					monitor.internalWorked(20);

					try {
						SystemDiagram diagram = (SystemDiagram)loadContentFromResource(profile);
						editor.setSystemDiagram(diagram);
					} catch (Exception e) {	 
						monitor.done();
						throw new InvocationTargetException(e,
								Messages.getString("SystemDiagramEditor.5")  + "\r\n" + e.getMessage()); //$NON-NLS-1$
					}
					monitor.internalWorked(35);

					monitor.subTask(Messages.getString("SystemDiagramEditor.7")); //$NON-NLS-1$
					try{
						RtsProfileHandler handler = new RtsProfileHandler();
						handler.restoreConnection(editor.getSystemDiagram());
						handler.restoreConfigSet(editor.getSystemDiagram());
						handler.restoreExecutionContext(editor.getSystemDiagram());
						editor.doReplace(editor.getSystemDiagram(), editor.getEditorSite());
					} catch (Exception e) {
						LOGGER.error("Fail to replace in editor", e);
						throw new InvocationTargetException(e, Messages.getString("SystemDiagramEditor.8")); //$NON-NLS-1$
					}
					//
					monitor.done();
				}
			};
			dialog.run(false, false, runable);
		} catch (Exception e) {
			throw new PartInitException(Messages.getString("SystemDiagramEditor.9"), e); //$NON-NLS-1$
		}

		editor.getSystemDiagram()
				.setSynchronizeInterval(SystemEditorPreferenceManager.getInstance().getInterval(SystemEditorPreferenceManager.SYNC_SYSTEMEDITOR_INTERVAL));

		editor.postLoad();
		editor.setDirty();
	}
	
	private EObject loadContentFromResource(RtsProfileExt profile) throws Exception {
		RtsProfileHandler handler = new RtsProfileHandler();
		SystemDiagram diagram = handler.load(profile, SystemDiagramKind.ONLINE_LITERAL);
		SystemEditorWrapperFactory.getInstance().getSynchronizationManager()
								.assignSynchonizationSupportToDiagram(diagram);
		Rehabilitation.rehabilitation(diagram);

		// 読み込み時に明示的に状態の同期を実行
		List<Component> eComps = new ArrayList<Component>(diagram.getComponents());
		diagram.getComponents().clear();
		for (Component c : eComps) {
			c.synchronizeManually();
			diagram.addComponent(c);
		}
		handler.restoreCompositeComponentPort(diagram);
		
		return SystemEditorWrapperFactory.getInstance().postLoad(handler, diagram);
	}
	
	private void modifyTargetComponent(ObjectFactory factory, String id, CorbaComponent result, TargetPort target) {
		if( id.equals(target.getComponentId() )) {
			target.setInstanceName(result.getInstanceNameL());
			boolean isHit = false;
			for( Property dpp : ((TargetPortExt)target).getProperties()) {
				if( dpp.getName().equals("COMPONENT_PATH_ID") ) {
					dpp.setValue(result.getPathId());
					isHit = true;
					break;
				}
			}
			if( !isHit ) {
				Property path = factory.createProperty();
				path.setName("COMPONENT_PATH_ID");
				path.setValue(result.getPathId());
				((TargetPortExt)target).getProperties().add(path);
			}
		}
	}
	
	private void modifyConfigurationSet(ObjectFactory factory, org.openrtp.namespaces.rts.version02.Component comp) {
		if(comp.getConfigurationSets().size() == 0 ) {
			ConfigurationSet config = factory.createConfigurationSet();
			config.setId("default");
			comp.getConfigurationSets().add(config);
		}
	}
	
	private String createParam(org.openrtp.namespaces.rts.version02.Component comp) {
		StringBuffer buf = new StringBuffer();

		String[] params = comp.getId().split(":");

		if( params.length < 5 ) {

			
			if( params.length < 2) return "";
			String[] items = params[1].split("\\.");
			StringBuffer vendor = new StringBuffer();
			for(int index=0;index<items.length-2;index++) {
				vendor.append(items[index]);
			}
			StringBuffer newId = new StringBuffer();
			newId.append(params[0]);
			newId.append(":");
			newId.append(vendor.toString());
			newId.append(":");
			newId.append(items[items.length-2]);
			newId.append(":");
			newId.append(items[items.length-1]);
			newId.append(":");
			newId.append(params[2]);
			          
			buf.append(newId.toString());
			buf.append("?instance_name=");
			buf.append(comp.getInstanceName());
			buf.append("&vendor=");
			buf.append(vendor);
			buf.append("&version=");
			buf.append(params[2]);
			buf.append("&category=");
			buf.append(items[items.length-2]);
			
		} else {
			buf.append(comp.getId());
			buf.append("?instance_name=");
			buf.append(comp.getInstanceName());
			buf.append("&vendor=");
			buf.append(params[1]);
			buf.append("&version=");
			buf.append(params[params.length-1]);
			buf.append("&category=");
			buf.append(params[2]);
		}
		
		return buf.toString();
	}

	private String createParamAlt(org.openrtp.namespaces.rts.version02.Component comp) {
		StringBuffer buf = new StringBuffer();

		String[] params = comp.getId().split(":");

		if( params.length < 5 ) {
			if( params.length < 2) return "";
			String[] items = params[1].split("\\.");
			StringBuffer vendor = new StringBuffer();
			for(int index=0;index<items.length-2;index++) {
				vendor.append(items[index]);
			}
			buf.append(items[items.length-1]);
			buf.append("?instance_name=");
			buf.append(comp.getInstanceName());
			buf.append("&vendor=");
			buf.append(vendor);
			buf.append("&version=");
			buf.append(params[2]);
			buf.append("&category=");
			buf.append(items[items.length-2]);
		} else {
			buf.append(params[3]);
			buf.append("?instance_name=");
			buf.append(comp.getInstanceName());
			buf.append("&vendor=");
			buf.append(params[1]);
			buf.append("&version=");
			buf.append(params[params.length-1]);
			buf.append("&category=");
			buf.append(params[2]);
		}
		
		return buf.toString();
	}
	
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (AbstractSystemDiagramEditor) targetEditor;
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
