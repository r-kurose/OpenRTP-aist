/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.manager;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Name Server Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.manager.NameServerContext#getNameServerName <em>Name Server Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.nameserviceview.manager.ManagerPackage#getNameServerContext()
 * @model
 * @generated
 */
public interface NameServerContext extends Node {
	/**
	 * Returns the value of the '<em><b>Name Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * NameServerManagerのaddNameServerメソッドで追加されたNameServerContextの接続文字列を返す。
	 * 現在は、NameServerManagerのisExistとrefreshAllの中で使用されている。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Server Name</em>' attribute.
	 * @see #setNameServerName(String)
	 * @see jp.go.aist.rtm.nameserviceview.manager.ManagerPackage#getNameServerContext_NameServerName()
	 * @model
	 * @generated
	 */
	String getNameServerName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.manager.NameServerContext#getNameServerName <em>Name Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 接続文字列をNameServerContextに設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name Server Name</em>' attribute.
	 * @see #getNameServerName()
	 * @generated
	 */
	void setNameServerName(String value);

} // NameServerContext
