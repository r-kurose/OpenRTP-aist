/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getContextHandler()
 * @model
 * @generated
 */
public interface ContextHandler extends EObject, IAdaptable {
	/**
	 * <!-- begin-user-doc -->
	 * IDに対応付けてExecutionContextを登録します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext setContext(String id, ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * IDに対応するExecutionContextを取得します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext getContext(String id);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContextからIDを逆引きして返します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getId(ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * IDに対応するExecutionContextを削除します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext removeContext(String id);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContextに関連するIDを削除します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String removeId(ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void sync();

	/**
	 * <!-- begin-user-doc -->
	 * ハンドラに登録されているExecutionContextのリストを返します。
	 * <!-- end-user-doc -->
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.List" many="false"
	 * @generated NOT
	 */
	List<ExecutionContext> values();

	/**
	 * <!-- begin-user-doc -->
	 * ハンドラに登録されているExecutionContextのIDリストを返します。
	 * <!-- end-user-doc -->
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.List" many="false"
	 * @generated NOT
	 */
	List<String> keys();

	/**
	 * <!-- begin-user-doc -->
	 * ハンドラに登録されたExecutionContextをクリアします。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void clear();

	/** ハンドラの種別を返します (owned/participate) */
	String getType();

	/** ハンドラを所有するRTCからECのリストを取得します。 */
	List<ExecutionContext> getOwnerContexts();

} // ContextHandler
