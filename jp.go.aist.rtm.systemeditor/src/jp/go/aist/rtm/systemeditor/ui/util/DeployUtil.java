package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import RTC.RTObjectHelper;

public class DeployUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeployUtil.class);

	static public List<CorbaComponent> searchComponentList(EList target, List<CorbaComponent> result) {

		for(int index=0;index<target.size();index++) {
			if( target.get(index) instanceof NamingObjectNode ) {
				NamingObjectNode obj = ((NamingObjectNode)target.get(index));
				try {
					if( obj.getCorbaObject()._is_a(RTObjectHelper.id()) ) {
						CorbaComponent component = (CorbaComponent)(jp.go.aist.rtm.toolscommon.model.component.Component) AdapterUtil.getAdapter(obj,jp.go.aist.rtm.toolscommon.model.component.Component.class);
						obj.getSynchronizationSupport().getSynchronizationManager().assignSynchonizationSupport(component);					
						component.synchronizeManually();
						component.setIor(obj.getCorbaObject().toString());
						result.add(component);
					}
				} catch (Exception e) {
					LOGGER.error("Fail to search component", e);
				}
			} else {
				EList nscomps = ((NamingContextNode)target.get(index)).getNodes();
				searchComponentList(nscomps, result);
			}
		}
		return result;
	}
	
	static public CorbaComponent searchComponent(String id, EList target) {

		CorbaComponent result = null;
		
		for(int index=0;index<target.size();index++) {
			if( target.get(index) instanceof NamingObjectNode ) {
				NamingObjectNode obj = ((NamingObjectNode)target.get(index));
				try {
					if( obj.getCorbaObject()._is_a(RTObjectHelper.id()) ) {
						CorbaComponent component = (CorbaComponent)(jp.go.aist.rtm.toolscommon.model.component.Component) AdapterUtil.getAdapter(obj,jp.go.aist.rtm.toolscommon.model.component.Component.class);
						obj.getSynchronizationSupport().getSynchronizationManager().assignSynchonizationSupport(component);					
						component.synchronizeManually();
					
						if( id.equals(component.getCorbaObject().toString()) ) {
							return component;
						}
					}
				} catch (Exception e) {
					LOGGER.error("Fail to search component", e);
				}
			} else {
				EList nscomps = ((NamingContextNode)target.get(index)).getNodes();
				result = searchComponent(id, nscomps);
			}
		}
		return result;
	}
	
	static public  java.util.List<RTCManager> searchManager(Component targetComponent) {
		java.util.List<RTCManager> result = new ArrayList<RTCManager>();
		java.util.List<RTCManager> mgrList = NameServerManager.eInstance.getRTCManagerList();
		if( targetComponent.getCompositeTypeL()==null || targetComponent.getCompositeTypeL().equals("None") ) {
			String compType = targetComponent.getTypeNameL();
			for(RTCManager manager : mgrList) {
				if( manager.getFactoryProfileTypeNamesR().contains(compType) ) {
					result.add(manager);
				}
			}
		} else {
			result.addAll(mgrList);
		}
		return result;
	}
}
