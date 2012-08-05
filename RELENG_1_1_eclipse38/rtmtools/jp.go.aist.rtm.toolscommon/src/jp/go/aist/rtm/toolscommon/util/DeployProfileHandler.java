package jp.go.aist.rtm.toolscommon.util;

import javax.xml.datatype.DatatypeFactory;

import org.openrtp.namespaces.deploy.Component;
import org.openrtp.namespaces.deploy.DeployProfile;
import org.openrtp.namespaces.deploy.ObjectFactory;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

public class DeployProfileHandler extends ProfileHandlerBase {
	
	public DeployProfile save(
			jp.go.aist.rtm.toolscommon.model.component.SystemDiagram eDiagram) {
		
		ObjectFactory factory = new ObjectFactory();
		DeployProfile profile = factory.createDeployProfile();
		profile.setId(eDiagram.getSystemId());
		DatatypeFactory dateFactory = new DatatypeFactoryImpl();
		profile.setCreationDate(dateFactory.newXMLGregorianCalendar(eDiagram.getCreationDate()));
		profile.setUpdateDate(dateFactory.newXMLGregorianCalendar(eDiagram.getUpdateDate()));
		profile.setVersion("0.1");
		//
		for (jp.go.aist.rtm.toolscommon.model.component.Component eComp:
				eDiagram.getRegisteredComponents()) {
			String type = eComp.getProperty(KEY_DEPLOY_TYPE);
			Component target = factory.createComponent();
			target.setId(eComp.getComponentId());
			target.setInstanceName(eComp.getInstanceNameL());
			if( type==null || type.length()==0 ) {
				target.setDeployType("None");
				profile.getComponents().add(target);
				continue;
				
			} else 	if( type.equals("Component")) {
				target.setDeployType("Component");
				
			} else if( type.equals("Manager")) {
				target.setDeployType("Manager");
				
			}
			target.setTarget(eComp.getProperty(KEY_DEPLOY_TARGET));
			target.setIor(eComp.getProperty(KEY_DEPLOY_IOR));
			profile.getComponents().add(target);
		}
		
		return profile;
	}

}
