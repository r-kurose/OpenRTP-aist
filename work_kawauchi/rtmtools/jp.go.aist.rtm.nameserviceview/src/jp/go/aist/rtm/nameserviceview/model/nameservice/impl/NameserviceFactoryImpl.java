/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameserviceFactoryImpl.java,v 1.5 2008/03/27 06:58:52 yamashita Exp $
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import OpenRTMNaming.NamingNotifier;
import OpenRTMNaming.ObserverProfile;
import OpenRTMNaming.NamingObserver;
import jp.go.aist.rtm.nameserviceview.model.nameservice.*;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameserviceFactory;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NameserviceFactoryImpl extends EFactoryImpl implements NameserviceFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NameserviceFactory init() {
		try {
			NameserviceFactory theNameserviceFactory = (NameserviceFactory)EPackage.Registry.INSTANCE.getEFactory("http:///jp/go/aist/rtm/nameserviceview/model/nameserviceview.ecore"); 
			if (theNameserviceFactory != null) {
				return theNameserviceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NameserviceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NameserviceFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case NameservicePackage.NAMING_CONTEXT_NODE: return createNamingContextNode();
			case NameservicePackage.NAMING_OBJECT_NODE: return createNamingObjectNode();
			case NameservicePackage.CORBA_NODE: return createCorbaNode();
			case NameservicePackage.NAME_SERVICE_REFERENCE: return createNameServiceReference();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case NameservicePackage.NAMING_CONTEXT_EXT:
				return createNamingContextExtFromString(eDataType, initialValue);
			case NameservicePackage.BINDING:
				return createBindingFromString(eDataType, initialValue);
			case NameservicePackage.NOT_FOUND:
				return createNotFoundFromString(eDataType, initialValue);
			case NameservicePackage.CANNOT_PROCEED:
				return createCannotProceedFromString(eDataType, initialValue);
			case NameservicePackage.INVALID_NAME:
				return createInvalidNameFromString(eDataType, initialValue);
			case NameservicePackage.NAMING_NOTIFIER:
				return createNamingNotifierFromString(eDataType, initialValue);
			case NameservicePackage.OBSERVER_PROFILE:
				return createObserverProfileFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case NameservicePackage.NAMING_CONTEXT_EXT:
				return convertNamingContextExtToString(eDataType, instanceValue);
			case NameservicePackage.BINDING:
				return convertBindingToString(eDataType, instanceValue);
			case NameservicePackage.NOT_FOUND:
				return convertNotFoundToString(eDataType, instanceValue);
			case NameservicePackage.CANNOT_PROCEED:
				return convertCannotProceedToString(eDataType, instanceValue);
			case NameservicePackage.INVALID_NAME:
				return convertInvalidNameToString(eDataType, instanceValue);
			case NameservicePackage.NAMING_NOTIFIER:
				return convertNamingNotifierToString(eDataType, instanceValue);
			case NameservicePackage.OBSERVER_PROFILE:
				return convertObserverProfileToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamingContextNode createNamingContextNode() {
		NamingContextNodeImpl namingContextNode = new NamingContextNodeImpl();
		return namingContextNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamingObjectNode createNamingObjectNode() {
		NamingObjectNodeImpl namingObjectNode = new NamingObjectNodeImpl();
		return namingObjectNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaNode createCorbaNode() {
		CorbaNodeImpl corbaNode = new CorbaNodeImpl();
		return corbaNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameServiceReference createNameServiceReference() {
		NameServiceReferenceImpl nameServiceReference = new NameServiceReferenceImpl();
		return nameServiceReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamingContextExt createNamingContextExtFromString(EDataType eDataType, String initialValue) {
		return (NamingContextExt)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNamingContextExtToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Binding createBindingFromString(EDataType eDataType, String initialValue) {
		return (Binding)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBindingToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotFound createNotFoundFromString(EDataType eDataType, String initialValue) {
		return (NotFound)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNotFoundToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CannotProceed createCannotProceedFromString(EDataType eDataType, String initialValue) {
		return (CannotProceed)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCannotProceedToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidName createInvalidNameFromString(EDataType eDataType, String initialValue) {
		return (InvalidName)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInvalidNameToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamingNotifier createNamingNotifierFromString(EDataType eDataType, String initialValue) {
		return (NamingNotifier)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNamingNotifierToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObserverProfile createObserverProfileFromString(EDataType eDataType, String initialValue) {
		return (ObserverProfile)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertObserverProfileToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameservicePackage getNameservicePackage() {
		return (NameservicePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NameservicePackage getPackage() {
		return NameservicePackage.eINSTANCE;
	}

} //NameserviceFactoryImpl
