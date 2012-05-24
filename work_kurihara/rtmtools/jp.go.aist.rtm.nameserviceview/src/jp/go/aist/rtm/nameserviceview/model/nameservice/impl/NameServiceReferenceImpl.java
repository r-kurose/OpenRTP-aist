/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameServiceReferenceImpl.java,v 1.3 2008/01/21 01:50:04 yamashita Exp $
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import OpenRTMNaming.NamingNotifier;
import OpenRTMNaming.ObserverProfile;
import java.util.Collections;
import java.util.UUID;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Name Service Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl#getNameServerName <em>Name Server Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl#getRootNamingContext <em>Root Naming Context</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl#getNotifier <em>Notifier</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl#isUpdated <em>Updated</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NameServiceReferenceImpl extends EObjectImpl implements
		NameServiceReference {
	/**
	 * The default value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected static final Binding BINDING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected Binding binding = BINDING_EDEFAULT;

	/**
	 * The default value of the '{@link #getNameServerName() <em>Name Server Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNameServerName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_SERVER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNameServerName() <em>Name Server Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNameServerName()
	 * @generated
	 * @ordered
	 */
	protected String nameServerName = NAME_SERVER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRootNamingContext() <em>Root Naming Context</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRootNamingContext()
	 * @generated
	 * @ordered
	 */
	protected static final NamingContextExt ROOT_NAMING_CONTEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRootNamingContext() <em>Root Naming Context</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRootNamingContext()
	 * @generated
	 * @ordered
	 */
	protected NamingContextExt rootNamingContext = ROOT_NAMING_CONTEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNotifier() <em>Notifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotifier()
	 * @generated
	 * @ordered
	 */
	protected static final NamingNotifier NOTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotifier() <em>Notifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotifier()
	 * @generated
	 * @ordered
	 */
	protected NamingNotifier notifier = NOTIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #isUpdated() <em>Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpdated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPDATED_EDEFAULT = false;

	public NameServiceReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NameservicePackage.Literals.NAME_SERVICE_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Binding getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBinding(Binding newBinding) {
		Binding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAME_SERVICE_REFERENCE__BINDING, oldBinding, binding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNameServerName() {
		return nameServerName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNameServerName(String newNameServerName) {
		String oldNameServerName = nameServerName;
		nameServerName = newNameServerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAME_SERVICE_REFERENCE__NAME_SERVER_NAME, oldNameServerName, nameServerName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamingContextExt getRootNamingContext() {
		return rootNamingContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRootNamingContext(NamingContextExt newRootNamingContext) {
		NamingContextExt oldRootNamingContext = rootNamingContext;
		rootNamingContext = newRootNamingContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAME_SERVICE_REFERENCE__ROOT_NAMING_CONTEXT, oldRootNamingContext, rootNamingContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamingNotifier getNotifier() {
		return notifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNotifier(NamingNotifier newNotifier) {
		NamingNotifier oldNotifier = notifier;
		notifier = newNotifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAME_SERVICE_REFERENCE__NOTIFIER, oldNotifier, notifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public NameServiceReference createChildReference(Binding childBinding) {
		NameServiceReference result = new NameServiceReferenceImpl();
		result.setRootNamingContext(this.getRootNamingContext());
		result.setNameServerName(this.getNameServerName());
		Binding binding = createChildBinding(childBinding);
		result.setBinding(binding);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Binding createChildBinding(Binding childBinding) {
		EList<NameComponent> name = new BasicEList<NameComponent>();
		Collections.addAll(name, this.getBinding().binding_name);
		Collections.addAll(name, childBinding.binding_name);
		Binding result = new Binding();
		result.binding_name = name.toArray(new NameComponent[name.size()]);
		result.binding_type = childBinding.binding_type;
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Binding getBaseBinding() {
		Binding result = new Binding();
		result.binding_type = binding.binding_type;
		result.binding_name = new NameComponent[] { getBinding().binding_name[getBinding().binding_name.length - 1] };
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getPathId() {
		StringBuffer result = new StringBuffer(getNameServerName());
		for (NameComponent name : getBinding().binding_name) {
			result.append("/" + name.id + "." + name.kind);
		}
		return result.toString();
	}

	OpenRTMNaming.ObserverProfile observerProfile;
	boolean updated = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpdated(boolean newUpdated) {
		boolean oldUpdated = updated;
		updated = newUpdated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAME_SERVICE_REFERENCE__UPDATED, oldUpdated, updated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ObserverProfile runObserver() {
		if (notifier == null) {
			return null;
		}
		if (observerProfile == null) {
			observerProfile = new OpenRTMNaming.ObserverProfile();
		}
		NamingObserverPOAImpl observer = new NamingObserverPOAImpl(this);
		org.omg.CORBA.Object o = NameServerAccesser.getInstance()
				.activateServant(observer);
		OpenRTMNaming.NamingObserver obs = OpenRTMNaming.NamingObserverHelper
				.narrow(o);
		observerProfile.id = UUID.randomUUID().toString();
		observerProfile.interface_type = OpenRTMNaming.NamingObserverHelper
				.id();
		observerProfile.observer = obs;
		this.setUpdated(true);
		notifier.subscribe(observerProfile);
		return observerProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void stopObserver() {
		if (notifier == null) {
			return;
		}
		if (observerProfile == null) {
			return;
		}
		try {
			NameServerAccesser.getInstance().deactivateServant(
					observerProfile.observer);
			notifier.unsubscribe(observerProfile.id);
		} catch (RuntimeException e) {

		}
		observerProfile = null;
	}

	static class NamingObserverPOAImpl extends OpenRTMNaming.NamingObserverPOA {
		NameServiceReferenceImpl ref;

		public NamingObserverPOAImpl(NameServiceReferenceImpl ref) {
			this.ref = ref;
		}

		@Override
		public void update() {
			ref.setUpdated(true);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (binding: ");
		result.append(binding);
		result.append(", nameServerName: ");
		result.append(nameServerName);
		result.append(", rootNamingContext: ");
		result.append(rootNamingContext);
		result.append(", notifier: ");
		result.append(notifier);
		result.append(", updated: ");
		result.append(updated);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NameservicePackage.NAME_SERVICE_REFERENCE__BINDING:
				return getBinding();
			case NameservicePackage.NAME_SERVICE_REFERENCE__NAME_SERVER_NAME:
				return getNameServerName();
			case NameservicePackage.NAME_SERVICE_REFERENCE__ROOT_NAMING_CONTEXT:
				return getRootNamingContext();
			case NameservicePackage.NAME_SERVICE_REFERENCE__NOTIFIER:
				return getNotifier();
			case NameservicePackage.NAME_SERVICE_REFERENCE__UPDATED:
				return isUpdated() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NameservicePackage.NAME_SERVICE_REFERENCE__BINDING:
				setBinding((Binding)newValue);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__NAME_SERVER_NAME:
				setNameServerName((String)newValue);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__ROOT_NAMING_CONTEXT:
				setRootNamingContext((NamingContextExt)newValue);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__NOTIFIER:
				setNotifier((NamingNotifier)newValue);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__UPDATED:
				setUpdated(((Boolean)newValue).booleanValue());
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
			case NameservicePackage.NAME_SERVICE_REFERENCE__BINDING:
				setBinding(BINDING_EDEFAULT);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__NAME_SERVER_NAME:
				setNameServerName(NAME_SERVER_NAME_EDEFAULT);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__ROOT_NAMING_CONTEXT:
				setRootNamingContext(ROOT_NAMING_CONTEXT_EDEFAULT);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__NOTIFIER:
				setNotifier(NOTIFIER_EDEFAULT);
				return;
			case NameservicePackage.NAME_SERVICE_REFERENCE__UPDATED:
				setUpdated(UPDATED_EDEFAULT);
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
			case NameservicePackage.NAME_SERVICE_REFERENCE__BINDING:
				return BINDING_EDEFAULT == null ? binding != null : !BINDING_EDEFAULT.equals(binding);
			case NameservicePackage.NAME_SERVICE_REFERENCE__NAME_SERVER_NAME:
				return NAME_SERVER_NAME_EDEFAULT == null ? nameServerName != null : !NAME_SERVER_NAME_EDEFAULT.equals(nameServerName);
			case NameservicePackage.NAME_SERVICE_REFERENCE__ROOT_NAMING_CONTEXT:
				return ROOT_NAMING_CONTEXT_EDEFAULT == null ? rootNamingContext != null : !ROOT_NAMING_CONTEXT_EDEFAULT.equals(rootNamingContext);
			case NameservicePackage.NAME_SERVICE_REFERENCE__NOTIFIER:
				return NOTIFIER_EDEFAULT == null ? notifier != null : !NOTIFIER_EDEFAULT.equals(notifier);
			case NameservicePackage.NAME_SERVICE_REFERENCE__UPDATED:
				return updated != UPDATED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} // NameServiceReferenceImpl
