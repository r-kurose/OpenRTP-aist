/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.manager;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.manager.Node#getNodes <em>Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.nameserviceview.manager.ManagerPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends WrapperObject {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.nameserviceview.manager.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see jp.go.aist.rtm.nameserviceview.manager.ManagerPackage#getNode_Nodes()
	 * @model type="jp.go.aist.rtm.nameserviceview.manager.Node" containment="true"
	 * @generated
	 */
	EList getNodes();

	/**
	 * @return ノードとして含むRTCManagerのリスト
	 */
	List<RTCManager> getRTCManagerList();

} // Node
