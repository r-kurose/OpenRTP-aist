package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.omg.CORBA.Object;

import RTC.PortProfile;
import RTC.PortService;

public class PortMock implements Port {

	private PortProfile profile;

	public PortService getCorbaObjectInterface() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getOriginalPortString() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public PortProfile getPortProfile() {
		return this.profile;
	}

	public void setOriginalPortString(String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setOriginalPortString(String componentId, String instanceName,
			String portName) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setPortProfile(PortProfile value) {
		this.profile = value;
	}

	public EList getSourceConnectors() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getCorbaBaseObject() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getCorbaObject() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean ping() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void setCorbaObject(Object value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void accept(Visiter visiter) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public Rectangle getConstraint() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setConstraint(Rectangle rectangle) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public TreeIterator eAllContents() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EClass eClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EObject eContainer() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EStructuralFeature eContainingFeature() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EReference eContainmentFeature() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eContents() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eCrossReferences() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature, boolean resolve) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean eIsProxy() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean eIsSet(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public Resource eResource() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void eSet(EStructuralFeature feature, java.lang.Object newValue) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void eUnset(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public EList eAdapters() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean eDeliver() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void eNotify(Notification notification) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void eSetDeliver(boolean deliver) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
		// TODO 自動生成されたメソッド・スタブ

	}

//	@Override
	public PortSynchronizer getSynchronizer() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setSynchronizer(PortSynchronizer value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void disconnectAll() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList findAllEquivalentPorts() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Port findPort(String originalPortString) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getNameL() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setNameL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public List<String> getDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public List<String> getDataflowTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public List<String> getInterfaceTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public List<String> getSubscriptionTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public boolean isAllowAnyDataType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean isAllowAnyDataflowType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean isAllowAnyInterfaceType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean isAllowAnySubscriptionType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getConnectorProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public boolean validateSourceConnector(Port source) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean validateTargetConnector(Port target) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public String getDataType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getDataflowType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getInterfaceType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getSubscriptionType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setDataType(String type) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setDataflowType(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setInterfaceType(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setSubscriptionType(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void synchronizeLocalReference() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public Port proxy() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Port findPort(SystemDiagram diagram, String originalPortString) {
		// TODO Auto-generated method stub
		return null;
	}

//@Override
public String getProperty(String name) {
	// TODO Auto-generated method stub
	return null;
}

}
