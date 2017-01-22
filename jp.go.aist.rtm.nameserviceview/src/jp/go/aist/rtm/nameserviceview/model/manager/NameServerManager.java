/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.manager;

import jp.go.aist.rtm.nameserviceview.model.manager.impl.NameServerManagerImpl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Name Server Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see jp.go.aist.rtm.nameserviceview.model.manager.ManagerPackage#getNameServerManager()
 * @model
 * @generated
 */
public interface NameServerManager extends Node {
	NameServerManager eInstance = NameServerManagerImpl.getInstance();

	/**
	 * <!-- begin-user-doc -->
	 * 含んでいるすべてのノードをいったん切断し、再度接続する。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void refreshAll();

	/**
	 * <!-- begin-user-doc -->
	 * 指定した接続文字列のノードを含んでいるかを返す。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean isExist(String nameServer);

	/**
	 * <!-- begin-user-doc -->
	 * 指定した接続文字列のノードを追加する。追加したNameServerContextを返す。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	NameServerContext addNameServer(String nameServer);

	/**
	 * 指定されたネームサーバを破棄する
	 * @param nameServerNamingContext
	 */
	void removeNode(NameServerContext nameServerNamingContext);
	
	/**
	 * <!-- begin-user-doc -->
	 * 何ミリ秒おきに含んでいるノードを更新するかを設定する。
	 * システムエディタの起動時、停止時、ネームサービスビューの設定画面で接続周期を変更したときに呼び出される。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setSynchronizeInterval(long milliSecond);

} // NameServerManager
