package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

public class ConfigurationSetMock implements ConfigurationSet {

	public String id;
	public BasicEList configurationData = new BasicEList();

	public EList getConfigurationData() {
		return this.configurationData;
	}

	public String getId() {
		return this.id;
	}

	public _SDOPackage.ConfigurationSet getSDOConfigurationSet() {
		return null;
	}

	public void setId(String value) {
		this.id = value;
	}

	public void setSDOConfigurationSet(_SDOPackage.ConfigurationSet value) {
	}

	public void accept(Visiter visiter) {
	}

	public void dispose() {
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

	public Object eGet(EStructuralFeature feature) {
		return null;
	}

	public Object eGet(EStructuralFeature feature, boolean resolve) {
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

	public void eSet(EStructuralFeature feature, Object newValue) {
	}

	public void eUnset(EStructuralFeature feature) {
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
	public Object getAdapter(Class adapter) {
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
	}
}
