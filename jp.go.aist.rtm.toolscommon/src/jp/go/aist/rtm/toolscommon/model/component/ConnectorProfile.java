package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

/**
 * ConnectorProfileを表現するクラス
 * <p>
 * 
 * このオブジェクトは、バリューオブジェクトであることに注意すること。<br>
 * このオブジェクト自体は同期が行われないため、このオブジェクトの参照を保持し続けることは、危険である。<br>
 * 事情が許す限り、参照元のオブジェクトを参照して、必要になるたびにそこから手に入れること。
 * 
 * @model
 */
public interface ConnectorProfile extends WrapperObject{

	public static final String ANY = "Any";

	public static final String PERIODIC = "Periodic";

	public static final String PUSH = "Push";

	/**
	 * 当該コネクタで使用されるデータフロー型を返す。
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getDataflowType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataflowType <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるデータフロー型を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dataflow Type</em>' attribute.
	 * @see #getDataflowType()
	 * @generated
	 */
	void setDataflowType(String value);

	/**
	 * 当該コネクタで使用されるサブスクリプション型を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getSubscriptionType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSubscriptionType <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるサブスクリプション型を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subscription Type</em>' attribute.
	 * @see #getSubscriptionType()
	 * @generated
	 */
	void setSubscriptionType(String value);

	/**
	 * サブスクリプション型が使用可能か（データフロー型がPUSHであるか）を返す
	 * @model changeable="false" transient="true" volatile="true"
	 * @return
	 */
	public boolean isSubscriptionTypeAvailable();

	/**
	 * PUSH間隔が使用可能か（サブスクリプション型が使用可能でサブスクリプション型がPERIODIC）を返す
	 * @model changeable="false" transient="true" volatile="true"
	 * @return
	 */
	public boolean isPushIntervalAvailable();

	/**
	 * OutportからInportに流れるデータの型を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getDataType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * OutportからInportに流れるデータの型を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 *  当該コネクタで使用されるインターフェース型を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getInterfaceType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInterfaceType <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるインターフェース型を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Type</em>' attribute.
	 * @see #getInterfaceType()
	 * @generated
	 */
	void setInterfaceType(String value);

	/**
	 * 当該コネクタで使用されるデータ送信周期を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public Double getPushRate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushRate <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるデータ送信周期を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Push Rate</em>' attribute.
	 * @see #getPushRate()
	 * @generated
	 */
	void setPushRate(Double value);

	/**
	 * Returns the value of the '<em><b>Source String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタの接続元であるポートの識別文字列を返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source String</em>' attribute.
	 * @see #setSourceString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_SourceString()
	 * @model
	 * @generated
	 */
	String getSourceString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSourceString <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コネクタの接続元であるポートの識別文字列を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source String</em>' attribute.
	 * @see #getSourceString()
	 * @generated
	 */
	void setSourceString(String value);

	/**
	 * Returns the value of the '<em><b>Target String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタの接続先であるポートの識別文字列を返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target String</em>' attribute.
	 * @see #setTargetString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_TargetString()
	 * @model
	 * @generated
	 */
	String getTargetString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTargetString <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コネクタの接続先であるポートの識別文字列を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target String</em>' attribute.
	 * @see #getTargetString()
	 * @generated
	 */
	void setTargetString(String value);

	/**
	 * @model
	 * @return	接続情報の名称
	 */
	public String getName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 接続情報の名称を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model
	 * @return　接続情報の一意識別子
	 */
	public String getConnectorId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getConnectorId <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 接続情報の一意識別子を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Id</em>' attribute.
	 * @see #getConnectorId()
	 * @generated
	 */
	void setConnectorId(String value);

}
