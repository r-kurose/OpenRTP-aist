/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.manager;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.nameserviceview.model.manager.ManagerPackage
 * @generated
 */
public interface ManagerFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ManagerFactory eINSTANCE = jp.go.aist.rtm.nameserviceview.model.manager.impl.ManagerFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Name Server Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Server Manager</em>'.
	 * @generated
	 */
	NameServerManager createNameServerManager();

	/**
	 * Returns a new object of class '<em>Name Server Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Server Context</em>'.
	 * @generated
	 */
	NameServerContext createNameServerContext();

	/**
	 * Returns a new object of class '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node</em>'.
	 * @generated
	 */
	Node createNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ManagerPackage getManagerPackage();

} //ManagerFactory
