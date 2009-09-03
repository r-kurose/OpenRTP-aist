/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice;

import jp.go.aist.rtm.nameserviceview.manager.Node;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Corba Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode#getNameServiceReference <em>Name Service Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getCorbaNode()
 * @model
 * @generated
 */
public interface CorbaNode extends CorbaWrapperObject, Node {
	/**
	 * Returns the value of the '<em><b>Name Service Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Service Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Service Reference</em>' reference.
	 * @see #setNameServiceReference(NameServiceReference)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getCorbaNode_NameServiceReference()
	 * @model
	 * @generated
	 */
	NameServiceReference getNameServiceReference();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode#getNameServiceReference <em>Name Service Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name Service Reference</em>' reference.
	 * @see #getNameServiceReference()
	 * @generated
	 */
	void setNameServiceReference(NameServiceReference value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="jp.go.aist.rtm.nameserviceview.model.nameservice.NotFound jp.go.aist.rtm.nameserviceview.model.nameservice.CannotProceed jp.go.aist.rtm.nameserviceview.model.nameservice.InvalidName"
	 * @generated
	 */
	void deleteR() throws NotFound, CannotProceed, InvalidName;

} // CorbaNode
