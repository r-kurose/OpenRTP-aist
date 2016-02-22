package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import RTC.PortProfile;

public class PortMock implements Port {

	private PortProfile profile;

	public String getOriginalPortString() {
		return null;
	}

	public PortProfile getPortProfile() {
		return this.profile;
	}

	public void setOriginalPortString(String value) {
	}

	public void setPortProfile(PortProfile value) {
		this.profile = value;
	}

	public void accept(Visiter visiter) {
	}

	public Rectangle getConstraint() {
		return null;
	}

	public void setConstraint(Rectangle rectangle) {
	}

	@SuppressWarnings("unchecked")
	public TreeIterator eAllContents() {
		return null;
	}

	public EClass eClass() {
		return null;
	}

	public EObject eContainer() {
		return null;
	}

	public EStructuralFeature eContainingFeature() {
		return null;
	}

	public EReference eContainmentFeature() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eContents() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eCrossReferences() {
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature) {
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature, boolean resolve) {
		return null;
	}

	public boolean eIsProxy() {
		return false;
	}

	public boolean eIsSet(EStructuralFeature feature) {
		return false;
	}

	public Resource eResource() {
		return null;
	}

	public void eSet(EStructuralFeature feature, java.lang.Object newValue) {
	}

	public void eUnset(EStructuralFeature feature) {
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments)
			throws InvocationTargetException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eAdapters() {
		return null;
	}

	public boolean eDeliver() {
		return false;
	}

	public void eNotify(Notification notification) {
	}

	public void eSetDeliver(boolean deliver) {
	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
	}

//	@Override
	public PortSynchronizer getSynchronizer() {
		return null;
	}

//	@Override
	public void setSynchronizer(PortSynchronizer value) {
	}

//	@Override
	public void disconnectAll() {
	}

//	@Override
	public String getNameL() {
		return null;
	}

//	@Override
	public void setNameL(String value) {
	}

//	@Override
	public List<String> getDataTypes() {
		return null;
	}

//	@Override
	public List<String> getDataflowTypes() {
		return null;
	}

//	@Override
	public List<String> getInterfaceTypes() {
		return null;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getProperties() {
		return null;
	}

//	@Override
	public List<String> getSubscriptionTypes() {
		return null;
	}

//	@Override
	public boolean isAllowAnyDataType() {
		return false;
	}

//	@Override
	public boolean isAllowAnyDataflowType() {
		return false;
	}

//	@Override
	public boolean isAllowAnyInterfaceType() {
		return false;
	}

//	@Override
	public boolean isAllowAnySubscriptionType() {
		return false;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getConnectorProfiles() {
		return null;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getInterfaces() {
		return null;
	}

//	@Override
	public boolean validateSourceConnector(Port source) {
		return false;
	}

//	@Override
	public boolean validateTargetConnector(Port target) {
		return false;
	}

//	@Override
	public String getDataType() {
		return null;
	}

//	@Override
	public String getDataflowType() {
		return null;
	}

//	@Override
	public String getInterfaceType() {
		return null;
	}

//	@Override
	public String getSubscriptionType() {
		return null;
	}

//	@Override
	public void setDataType(String type) {
	}

//	@Override
	public void setDataflowType(String value) {
	}

//	@Override
	public void setInterfaceType(String value) {
	}

//	@Override
	public void setSubscriptionType(String value) {
	}

//	@Override
	public Port proxy() {
		return null;
	}

//	@Override
	public Port findPort(SystemDiagram diagram, String originalPortString) {
		return null;
	}

//	@Override
	public String getProperty(String name) {
		return null;
	}

}
