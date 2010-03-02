package jp.go.aist.rtm.toolscommon.model.core;

/**
 * Corbaのラッパーオブジェクトを表すクラス
 * 
 * @model
 */
public interface CorbaWrapperObject extends WrapperObject{
	/**
	 * @model
	 * @return ラッピングするCorbaオブジェクト
	 */
	public org.omg.CORBA.Object getCorbaObject();
	
	/**
	 * @return　getCorbaObject()のcorbaオブジェクトをnarrowして返す
	 */
	public org.omg.CORBA.Object getCorbaObjectInterface();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject#getCorbaObject <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Corba Object</em>' attribute.
	 * @see #getCorbaObject()
	 * @generated
	 */
	void setCorbaObject(org.omg.CORBA.Object value);

}
