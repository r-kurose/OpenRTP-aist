/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.manager.impl;

import jp.go.aist.rtm.nameserviceview.manager.ManagerFactory;
import jp.go.aist.rtm.nameserviceview.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.manager.Node;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ManagerFactoryImpl extends EFactoryImpl implements ManagerFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ManagerFactory init() {
		try {
			ManagerFactory theManagerFactory = (ManagerFactory)EPackage.Registry.INSTANCE.getEFactory("http:///jp/go/aist/rtm/nameserviceview/manager.ecore"); 
			if (theManagerFactory != null) {
				return theManagerFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ManagerFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagerFactoryImpl() {
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
			case ManagerPackage.NAME_SERVER_MANAGER: return createNameServerManager();
			case ManagerPackage.NAME_SERVER_CONTEXT: return createNameServerContext();
			case ManagerPackage.NODE: return createNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameServerManager createNameServerManager() {
		NameServerManagerImpl nameServerManager = new NameServerManagerImpl();
		return nameServerManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameServerContext createNameServerContext() {
		NameServerContextImpl nameServerContext = new NameServerContextImpl();
		return nameServerContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagerPackage getManagerPackage() {
		return (ManagerPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ManagerPackage getPackage() {
		return ManagerPackage.eINSTANCE;
	}

} //ManagerFactoryImpl
