/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameserviceFactory.java,v 1.2 2008/01/18 06:39:22 yamashita Exp $
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice;

import org.eclipse.emf.ecore.EFactory;



/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage
 * @generated
 */
public interface NameserviceFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NameserviceFactory eINSTANCE = jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameserviceFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Naming Context Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Naming Context Node</em>'.
	 * @generated
	 */
	NamingContextNode createNamingContextNode();

	/**
	 * Returns a new object of class '<em>Naming Object Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Naming Object Node</em>'.
	 * @generated
	 */
	NamingObjectNode createNamingObjectNode();

	/**
	 * Returns a new object of class '<em>Corba Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Corba Node</em>'.
	 * @generated
	 */
	CorbaNode createCorbaNode();

	/**
	 * Returns a new object of class '<em>Name Service Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Service Reference</em>'.
	 * @generated
	 */
	NameServiceReference createNameServiceReference();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	NameservicePackage getNameservicePackage();

} //NameserviceFactory
