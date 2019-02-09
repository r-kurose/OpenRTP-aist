package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * プロシキとなるポートを返すクラス
 *
 */
public abstract class PortProxy extends EObjectImpl implements Port {

	public static Port proxy(Port substance) {
		if (substance instanceof InPort) return new InPortProxy(substance);
		if (substance instanceof OutPort) return new OutPortProxy(substance);
		if (substance instanceof ServicePort) return new ServicePortProxy(substance);
		return null;
	}

	private static class InPortProxy extends PortProxy implements InPort {
		private InPortProxy(Port substance){
			super(substance);
		}
	}

	private static class OutPortProxy extends PortProxy implements OutPort {
		private OutPortProxy(Port substance){
			super(substance);
		}
	}

	private static class ServicePortProxy extends PortProxy implements ServicePort {
		private ServicePortProxy(Port substance){
			super(substance);
		}
	}

	private Port substance;

	private PortProxy(Port substance) {
		this.substance = substance;
	}
//	@Override
	public void disconnectAll() {
		substance.getSynchronizer().setCurrentDiagram((SystemDiagram) eContainer().eContainer());
		substance.disconnectAll();
	}

//	@Override
	public Port findPort(SystemDiagram diagram, String originalPortString) {
		return substance.findPort(diagram, originalPortString);
	}

	@SuppressWarnings("unchecked")
//	@Override
	public EList getConnectorProfiles() {
		return substance.getConnectorProfiles();
	}

//	@Override
	public String getDataType() {
		return substance.getDataType();
	}

//	@Override
	public List<String> getDataTypes() {
		return substance.getDataTypes();
	}

//	@Override
	public String getDataflowType() {
		return substance.getDataflowType();
	}

//	@Override
	public List<String> getDataflowTypes() {
		return substance.getDataflowTypes();
	}

//	@Override
	public String getInterfaceType() {
		return substance.getInterfaceType();
	}

//	@Override
	public List<String> getInterfaceTypes() {
		return substance.getInterfaceTypes();
	}

	@SuppressWarnings("unchecked")
//	@Override
	public EList getInterfaces() {
		return substance.getInterfaces();
	}

//	@Override
	public String getNameL() {
		return substance.getNameL();
	}

//	@Override
	public String getOriginalPortString() {
		return substance.getOriginalPortString();
	}

//	@Override
	public String getSubscriptionType() {
		return substance.getSubscriptionType();
	}

//	@Override
	public List<String> getSubscriptionTypes() {
		return substance.getSubscriptionTypes();
	}

//	@Override
	public PortSynchronizer getSynchronizer() {
		return substance.getSynchronizer();
	}

//	@Override
	public boolean isAllowAnyDataType() {
		return substance.isAllowAnyDataType();
	}

//	@Override
	public boolean isAllowAnyDataflowType() {
		return substance.isAllowAnyDataflowType();
	}

//	@Override
	public boolean isAllowAnyInterfaceType() {
		return substance.isAllowAnyInterfaceType();
	}

//	@Override
	public boolean isAllowAnySubscriptionType() {
		return substance.isAllowAnySubscriptionType();
	}

//	@Override
	public Port proxy() {
		return substance.proxy();
	}

//	@Override
	public void setDataType(String type) {
		substance.setDataType(type);
	}

//	@Override
	public void setDataflowType(String value) {
		substance.setDataflowType(value);
	}

//	@Override
	public void setInterfaceType(String value) {
		substance.setInterfaceType(value);
	}

//	@Override
	public void setNameL(String value) {
		substance.setNameL(value);
	}

//	@Override
	public void setOriginalPortString(String value) {
		substance.setOriginalPortString(value);
	}

//	@Override
	public void setSubscriptionType(String value) {
		substance.setSubscriptionType(value);
	}

//	@Override
	public void setSynchronizer(PortSynchronizer value) {
		substance.setSynchronizer(value);
	}

//	@Override
	public boolean validateSourceConnector(Port source) {
		return substance.validateSourceConnector(source);
	}

//	@Override
	public boolean validateTargetConnector(Port target) {
		return substance.validateTargetConnector(target);
	}

//	@Override
	public void accept(Visiter visiter) {
		substance.accept(visiter);
	}

//	@Override
	public Rectangle getConstraint() {
		return substance.getConstraint();
	}

//	@Override
	public void setConstraint(Rectangle rectangle) {
		substance.setConstraint(rectangle);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeIterator eAllContents() {
		return substance.eAllContents();
	}

	@Override
	public EClass eClass() {
		return substance.eClass();
	}

	@Override
	public EObject eContainer() {
		return super.eContainer();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		return substance.eContainingFeature();
	}

	@Override
	public EReference eContainmentFeature() {
		return substance.eContainmentFeature();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList eContents() {
		return substance.eContents();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList eCrossReferences() {
		return substance.eCrossReferences();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		return substance.eGet(feature);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return substance.eGet(feature, resolve);
	}

	@Override
	public boolean eIsProxy() {
		return substance.eIsProxy();
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		return substance.eIsSet(feature);
	}

	@Override
	public Resource eResource() {
		return substance.eResource();
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		substance.eSet(feature, newValue);
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		substance.eUnset(feature);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EList eAdapters() {
		return substance.eAdapters();
	}

	@Override
	public boolean eDeliver() {
		return substance.eDeliver();
	}

	@Override
	public void eNotify(Notification notification) {
		substance.eNotify(notification);
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		substance.eSetDeliver(deliver);
	}

	@SuppressWarnings("unchecked")
//	@Override
	public Object getAdapter(Class adapter) {
		return substance.getAdapter(adapter);
	}

//	@Override
	public SynchronizationSupport getSynchronizationSupport() {
		return substance.getSynchronizationSupport();
	}

//	@Override
	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
		substance.setSynchronizationSupport(synchronizationSupport);
	}

	public List<NameValue> getProperties() {
		return substance.getProperties();
	}

	public String getProperty(String name) {
		return substance.getProperty(name);
	}
}
