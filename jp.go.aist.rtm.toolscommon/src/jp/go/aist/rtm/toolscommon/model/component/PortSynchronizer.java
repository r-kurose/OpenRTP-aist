/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * 、リモートにあるRTC上のポートが持つ情報との同期を取るためのインターフェース
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer#getOriginalPortString <em>Original Port String</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortSynchronizer()
 * @model
 * @generated
 */
public interface PortSynchronizer extends EObject {

	/**
	 * Returns the value of the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ポートを識別するための文字列を返す。
	 * Corbaコンポーネントの場合は、ポートのCorbaインターフェースが使用される。
	 * オフラインコンポーネントの場合は、複合RTCでないコンポーネントのID、インスタンス名、ポート名の組み合わせが使用される。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Port String</em>' attribute.
	 * @see #setOriginalPortString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortSynchronizer_OriginalPortString()
	 * @model
	 * @generated
	 */
	String getOriginalPortString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer#getOriginalPortString <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * ポートを識別するための文字列を設定する。現在はオフラインのときのみ使用。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Port String</em>' attribute.
	 * @see #getOriginalPortString()
	 * @generated
	 */
	void setOriginalPortString(String value);

	/**
	 * <!-- begin-user-doc -->
	 * 当該ポート上の接続をすべて切断する。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void disconnectAll();

	String getDataflowType();

	String getDataType();

	String getInterfaceType();

	String getSubscriptionType();

	List<NameValue> getProperties();

	String getProperty(String name);


} // PortSynchronizer
