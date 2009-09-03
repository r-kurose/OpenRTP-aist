/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.manager;

import jp.go.aist.rtm.toolscommon.model.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.nameserviceview.manager.ManagerFactory
 * @model kind="package"
 * @generated
 */
public interface ManagerPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "manager";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///jp/go/aist/rtm/nameserviceview/manager.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jp.go.aist.rtm.nameserviceview.manager";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ManagerPackage eINSTANCE = jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl.init();

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.nameserviceview.manager.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.nameserviceview.manager.impl.NodeImpl
	 * @see jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 2;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__CONSTRAINT = CorePackage.WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NODES = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = CorePackage.WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.nameserviceview.manager.impl.NameServerManagerImpl <em>Name Server Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.nameserviceview.manager.impl.NameServerManagerImpl
	 * @see jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl#getNameServerManager()
	 * @generated
	 */
	int NAME_SERVER_MANAGER = 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_MANAGER__CONSTRAINT = NODE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_MANAGER__NODES = NODE__NODES;

	/**
	 * The number of structural features of the '<em>Name Server Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_MANAGER_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.nameserviceview.manager.impl.NameServerContextImpl <em>Name Server Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.nameserviceview.manager.impl.NameServerContextImpl
	 * @see jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl#getNameServerContext()
	 * @generated
	 */
	int NAME_SERVER_CONTEXT = 1;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_CONTEXT__CONSTRAINT = NODE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_CONTEXT__NODES = NODE__NODES;

	/**
	 * The feature id for the '<em><b>Name Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_CONTEXT__NAME_SERVER_NAME = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Name Server Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_SERVER_CONTEXT_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.nameserviceview.manager.NameServerManager <em>Name Server Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name Server Manager</em>'.
	 * @see jp.go.aist.rtm.nameserviceview.manager.NameServerManager
	 * @generated
	 */
	EClass getNameServerManager();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.nameserviceview.manager.NameServerContext <em>Name Server Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name Server Context</em>'.
	 * @see jp.go.aist.rtm.nameserviceview.manager.NameServerContext
	 * @generated
	 */
	EClass getNameServerContext();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.nameserviceview.manager.NameServerContext#getNameServerName <em>Name Server Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name Server Name</em>'.
	 * @see jp.go.aist.rtm.nameserviceview.manager.NameServerContext#getNameServerName()
	 * @see #getNameServerContext()
	 * @generated
	 */
	EAttribute getNameServerContext_NameServerName();

	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.nameserviceview.manager.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see jp.go.aist.rtm.nameserviceview.manager.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference list '{@link jp.go.aist.rtm.nameserviceview.manager.Node#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see jp.go.aist.rtm.nameserviceview.manager.Node#getNodes()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Nodes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ManagerFactory getManagerFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.nameserviceview.manager.impl.NameServerManagerImpl <em>Name Server Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.nameserviceview.manager.impl.NameServerManagerImpl
		 * @see jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl#getNameServerManager()
		 * @generated
		 */
		EClass NAME_SERVER_MANAGER = eINSTANCE.getNameServerManager();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.nameserviceview.manager.impl.NameServerContextImpl <em>Name Server Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.nameserviceview.manager.impl.NameServerContextImpl
		 * @see jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl#getNameServerContext()
		 * @generated
		 */
		EClass NAME_SERVER_CONTEXT = eINSTANCE.getNameServerContext();

		/**
		 * The meta object literal for the '<em><b>Name Server Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME_SERVER_CONTEXT__NAME_SERVER_NAME = eINSTANCE.getNameServerContext_NameServerName();

		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.nameserviceview.manager.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.nameserviceview.manager.impl.NodeImpl
		 * @see jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__NODES = eINSTANCE.getNode_Nodes();

	}

} //ManagerPackage
