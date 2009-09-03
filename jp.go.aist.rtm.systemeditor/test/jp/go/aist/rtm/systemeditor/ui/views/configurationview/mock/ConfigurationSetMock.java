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
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setId(String value) {
		this.id = value;
	}

	public void setSDOConfigurationSet(_SDOPackage.ConfigurationSet value) {
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

	public Object eGet(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object eGet(EStructuralFeature feature, boolean resolve) {
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

	public void eSet(EStructuralFeature feature, Object newValue) {
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
	public Object getAdapter(Class adapter) {
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

}
