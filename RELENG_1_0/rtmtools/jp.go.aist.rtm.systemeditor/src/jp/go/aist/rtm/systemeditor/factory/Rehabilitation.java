package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialogData;
import jp.go.aist.rtm.systemeditor.ui.util.RtsProfileHandler;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;

import RTC.RTObject;
import RTC.RTObjectHelper;
import _SDOPackage.SDO;

/**
 * アクセスできないオブジェクトに対して、PathIdや名前から修復を行うクラス
 * <p>
 * セーブ後のロード中の修復を想定している。セーブ内容がここでは確実に手に入ると考えてよい。
 * 修復をかけるのは、Componentの情報。
 * 現在はCORBA専用
 */
public class Rehabilitation {
	@SuppressWarnings("unchecked")
	public static void rehabilitation(SystemDiagram diagram) {
		for (Component c : diagram.getRegisteredComponents()) {
			if (!(c instanceof CorbaComponent)) continue;
			
			CorbaComponent component = (CorbaComponent)c;
			
			rehabilitation(component, diagram);
		}
	}

	private static RTObject rehabilitation(CorbaComponent component,
			SystemDiagram diagram) {
		if( component.getCorbaObject() != null &&
				SynchronizationSupport.ping(component.getCorbaObject()) ) 
			return (RTObject) component.getCorbaObject();

		RTObject narrow = null;
		try {
			org.omg.CORBA.Object remote = NameServerAccesser.getInstance()
						.getObjectFromPathId(component.getPathId());
			narrow = RTObjectHelper.narrow(remote);
		} catch (Exception e) {
			if (!component.isCompositeComponent())
				throw new RuntimeException("cannot access:" + component.getPathId() + "\n" + e.getMessage());
		}
		if (narrow == null && component.isCompositeComponent()) 
			narrow = createCompositeComponent(component, diagram);
		if (narrow == null) 
			throw new RuntimeException("cannot access:" + component.getPathId());
		component.setCorbaObject(narrow);
		return narrow;
	}

	private static RTObject createCompositeComponent(CorbaComponent component,
			SystemDiagram diagram) {
		RTM.Manager manager = getManager(component.getPathId());
		if (manager == null) return null;
		
		String param = NewCompositeComponentDialogData.getParam(
				component.getCompositeTypeL()
				, component.getInstanceNameL()
				, getExportedPortString(component, diagram));
		
		RTC.RTObject remote = manager.create_component(param);
		
		try {
			remote.get_owned_organizations()[0].set_members(getSdos(component,diagram));
		} catch (Exception e) {
			remote.exit();
			return null;
		}
		return remote;
	}

	private static SDO[] getSdos(CorbaComponent component, SystemDiagram diagram) {
		List<SDO> result = new ArrayList<SDO>();
		for (Object o : component.getComponents()) {
			CorbaComponent c = (CorbaComponent) o;
			rehabilitation(c, diagram);
			result.add(c.getCorbaObjectInterface());
		}
		return result.toArray(new SDO[0]);
	}

	private static String getExportedPortString(CorbaComponent component,
			SystemDiagram diagram) {
		org.openrtp.namespaces.rts.version02.Component originalComponent 
			= RtsProfileHandler.findComponent(component, diagram.getProfile().getComponents());
		String activeId = originalComponent.getActiveConfigurationSet();
		for (ConfigurationSet configSet : originalComponent.getConfigurationSets()) {
			if (!configSet.getId().equals(activeId)) continue;
			for (ConfigurationData configData : configSet.getConfigurationData()) {
				if (configData.getName().equals("exported_ports")) return configData.getData();
			}
		}
		return "";
	}

	private static RTM.Manager getManager(String pathId) {
		int index = pathId.lastIndexOf("/");
		String contextId = pathId.substring(0, index);
		return NameServerAccesser.getInstance().getManagerFromContextId(contextId);
	}
}
