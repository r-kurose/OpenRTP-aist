package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import java.util.List;

import org.eclipse.emf.common.util.EList;


/**
 * Portを表現するクラス
 * @model
 */
public interface Port extends WrapperObject {

	/**
	 * Returns the value of the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ポートを識別するための文字列を返す。
	 * 実際の処理はPortSynchronizerに委譲される
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Port String</em>' attribute.
	 * @see #setOriginalPortString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_OriginalPortString()
	 * @model
	 * @generated
	 */
	String getOriginalPortString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getOriginalPortString <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * ポートを識別するための文字列を設定する。
	 * 実際の処理はPortSynchronizerに委譲される
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Port String</em>' attribute.
	 * @see #getOriginalPortString()
	 * @generated
	 */
	void setOriginalPortString(String value);

	/**
	 * Returns the value of the '<em><b>Synchronizer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 同期処理用のPortSynchronizerを取得する
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchronizer</em>' containment reference.
	 * @see #setSynchronizer(PortSynchronizer)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_Synchronizer()
	 * @model containment="true"
	 * @generated
	 */
	PortSynchronizer getSynchronizer();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getSynchronizer <em>Synchronizer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * 同期処理用のPortSynchronizerを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronizer</em>' containment reference.
	 * @see #getSynchronizer()
	 * @generated
	 */
	void setSynchronizer(PortSynchronizer value);

	/**
	 * Returns the value of the '<em><b>Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ポートの名称を返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name L</em>' attribute.
	 * @see #setNameL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_NameL()
	 * @model
	 * @generated
	 */
	String getNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getNameL <em>Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * ポートの名称を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name L</em>' attribute.
	 * @see #getNameL()
	 * @generated
	 */
	void setNameL(String value);

	/**
	 * Returns the value of the '<em><b>Allow Any Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * カンマ区切りで指定されるポートのデータ型の中にAnyが存在するかを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Data Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnyDataType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnyDataType();

	/**
	 * Returns the value of the '<em><b>Allow Any Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * カンマ区切りで指定されるポートのインターフェース型の中にAnyが存在するかを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Interface Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnyInterfaceType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnyInterfaceType();

	/**
	 * Returns the value of the '<em><b>Allow Any Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * カンマ区切りで指定されるポートのデータフロー型の中にAnyが存在するかを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Dataflow Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnyDataflowType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnyDataflowType();

	/**
	 * Returns the value of the '<em><b>Allow Any Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * カンマ区切りで指定されるポートのサブスクリプション型の中にAnyが存在するかを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Subscription Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnySubscriptionType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnySubscriptionType();

	/**
	 * Returns the value of the '<em><b>Connector Profiles</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ポートが保持するコネクタプロファイルのリストを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Profiles</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_ConnectorProfiles()
	 * @model transient="true"
	 * @generated
	 */
	EList<ConnectorProfile> getConnectorProfiles();

	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' attribute list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 *  サービスポートが保持するインターフェースのリストを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_Interfaces()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile"
	 * @generated
	 */
	EList<PortInterfaceProfile> getInterfaces();

	/**
	 * カンマ区切りで指定されるポートのデータフロー型をデータフロー型のリストにして返す
	 */
	public List<String> getDataflowTypes();

	String getDataflowType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getDataflowType <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dataflow Type</em>' attribute.
	 * @see #getDataflowType()
	 * @generated
	 */
	void setDataflowType(String value);

	/**
	 *  カンマ区切りで指定されるポートのデータ型をデータ型のリストにして返す
	 */
	public List<String> getDataTypes();

	String getDataType();

	void setDataType(String type);

	/**
	 * カンマ区切りで指定されるポートのインターフェース型をインターフェース型のリストにして返す
	 */
	public List<String> getInterfaceTypes();

	String getInterfaceType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getInterfaceType <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Type</em>' attribute.
	 * @see #getInterfaceType()
	 * @generated
	 */
	void setInterfaceType(String value);

	/**
	 * カンマ区切りで指定されるポートのサブスクリプション型をサブスクリプション型のリストにして返す
	 */
	public List<String> getSubscriptionTypes();

	String getSubscriptionType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getSubscriptionType <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subscription Type</em>' attribute.
	 * @see #getSubscriptionType()
	 * @generated
	 */
	void setSubscriptionType(String value);

	/**
	 * <!-- begin-user-doc -->
	 * 当該ポート上の接続をすべて切断する。
	 * 実際の処理はPortSynchronizerに委譲される
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void disconnectAll();

	/**
	 * <!-- begin-user-doc -->
	 * 当該ポートが属するルートのシステムダイアグラムからポート識別用の文字列（OriginalPortString)と一致する
	 * ポートを返す。このとき、複合RTCではないコンポーネントのポートが返される。
	 * <!-- end-user-doc -->
	 * @model originalPortStringUnique="false"
	 * @generated
	 */
	Port findPort(SystemDiagram diagram, String originalPortString);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validateTargetConnector(Port target);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validateSourceConnector(Port source);

	/**
	 * @return	プロキシとなるポート
	 */
	Port proxy();

	/**
	 * @return DataType/InterfaceType/DataflowType/SUBSCRIPTION_TYPE以外のプロパティ
	 */
	List<NameValue> getProperties();

	/**
	 * @param name	プロパティ名
	 * @return		プロパティの値
	 */
	String getProperty(String name);
}
