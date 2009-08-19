package jp.go.aist.rtm.nameserviceview.model.nameservice;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;



/**
 * ネーミングオブジェクトを表すクラス（CORBA専用）
 * @model
 */
public interface NamingObjectNode extends CorbaNode {
	/**
	 * @model containment="true"
	 */
	public abstract WrapperObject getEntry();


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

	/**
	 * ゾンビであるかを返す
	 * @model changeable="false" transient="true" volatile="true"
	 */
	public boolean isZombie();

}
