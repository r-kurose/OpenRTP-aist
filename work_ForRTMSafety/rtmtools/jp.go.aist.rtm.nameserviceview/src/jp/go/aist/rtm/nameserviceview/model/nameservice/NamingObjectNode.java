package jp.go.aist.rtm.nameserviceview.model.nameservice;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

/**
 * ネーミングオブジェクトを表すクラス（CORBA専用）
 * @model
 */
public interface NamingObjectNode extends CorbaNode {

	/**
	 * Returns the value of the '<em><b>Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry</em>' reference.
	 * @see #setEntry(WrapperObject)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNamingObjectNode_Entry()
	 * @model resolveProxies="false"
	 * @generated
	 */
	WrapperObject getEntry();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode#getEntry <em>Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * ネーミングオブジェクトをRTコンポーネントまたはRTマネージャとして保持する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry</em>' reference.
	 * @see #getEntry()
	 * @generated
	 */
	void setEntry(WrapperObject value);

}
