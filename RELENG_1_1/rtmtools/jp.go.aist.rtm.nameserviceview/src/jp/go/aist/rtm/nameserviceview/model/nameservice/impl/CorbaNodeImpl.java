/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.model.manager.Node;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorbaWrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.CorbaNodeImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.CorbaNodeImpl#getNameServiceReference <em>Name Service Reference</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.CorbaNodeImpl#isZombie <em>Zombie</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorbaNodeImpl extends CorbaWrapperObjectImpl implements CorbaNode {
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> nodes;

	/**
	 * The cached value of the '{@link #getNameServiceReference() <em>Name Service Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameServiceReference()
	 * @generated
	 * @ordered
	 */
	protected NameServiceReference nameServiceReference;

	/**
	 * The default value of the '{@link #isZombie() <em>Zombie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isZombie()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ZOMBIE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CorbaNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NameservicePackage.Literals.CORBA_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Node> getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList<Node>(Node.class, this, NameservicePackage.CORBA_NODE__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NameServiceReference getNameServiceReference() {
		if (nameServiceReference != null && nameServiceReference.eIsProxy()) {
			InternalEObject oldNameServiceReference = (InternalEObject)nameServiceReference;
			nameServiceReference = (NameServiceReference)eResolveProxy(oldNameServiceReference);
			if (nameServiceReference != oldNameServiceReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE, oldNameServiceReference, nameServiceReference));
			}
		}
		return nameServiceReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameServiceReference basicGetNameServiceReference() {
		return nameServiceReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNameServiceReference(NameServiceReference newNameServiceReference) {
		NameServiceReference oldNameServiceReference = nameServiceReference;
		nameServiceReference = newNameServiceReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE, oldNameServiceReference, nameServiceReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isZombie() {
		// TODO: implement this method to return the 'Zombie' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> 
	 * 当該ノードをネームサーバから削除する（CORBA専用）
	 * <!-- end-user-doc -->
	 */
	@Override
	public void deleteR() throws NotFound, CannotProceed, InvalidName {
		NameComponent[] nameComponents = getNameServiceReference().getBinding().binding_name;
		if (eContainer() instanceof NamingContextNode) {
			((NamingContextNode)eContainer())
			.getCorbaObjectInterface()
			.unbind(
					new NameComponent[] { nameComponents[nameComponents.length - 1] });
			
		}
		// ノードを消すのは同期スレッドに任せる 2009.4.2
//		EcoreUtil.remove(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				return getNodes();
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				if (resolve) return getNameServiceReference();
				return basicGetNameServiceReference();
			case NameservicePackage.CORBA_NODE__ZOMBIE:
				return isZombie() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends Node>)newValue);
				return;
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				setNameServiceReference((NameServiceReference)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				getNodes().clear();
				return;
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				setNameServiceReference((NameServiceReference)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				return nodes != null && !nodes.isEmpty();
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				return nameServiceReference != null;
			case NameservicePackage.CORBA_NODE__ZOMBIE:
				return isZombie() != ZOMBIE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Node.class) {
			switch (derivedFeatureID) {
				case NameservicePackage.CORBA_NODE__NODES: return ManagerPackage.NODE__NODES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Node.class) {
			switch (baseFeatureID) {
				case ManagerPackage.NODE__NODES: return NameservicePackage.CORBA_NODE__NODES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	
	@Override
	public org.omg.CORBA.Object getCorbaObjectInterface() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Bindingの比較を行う
	 */
	public static Comparator<org.omg.CosNaming.Binding> COMARATOR = new Comparator<org.omg.CosNaming.Binding>() {
		@Override
		public int compare(org.omg.CosNaming.Binding o1,
				org.omg.CosNaming.Binding o2) {
			for (int i = 0; i < o1.binding_name.length
					&& i < o2.binding_name.length; i++) {
				int compareId = o1.binding_name[i].id
						.compareTo(o2.binding_name[i].id);
				if (compareId != 0) {
					return compareId;
				}

				int compareKind = o1.binding_name[i].kind
						.compareTo(o2.binding_name[i].kind);
				if (compareKind != 0) {
					return compareKind;
				}
			}

			return o1.binding_name.length - o2.binding_name.length;
		}
	};
	
	/**
	 * ネームサービスに登録されているRTCManagerのリストを返します。
	 * NodeImplと同じ実装ではあるが、しょうがない
	 */
	@Override
	public List<RTCManager> getRTCManagerList() {
		List<RTCManager> list = new ArrayList<RTCManager>();
		for (Node nc : getNodes()) {
			list.addAll(nc.getRTCManagerList());
		}
		return list;
	}

} //CorbaNodeImpl
