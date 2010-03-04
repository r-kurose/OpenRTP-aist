/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

import org.eclipse.emf.common.util.EList;

import RTC.ComponentProfile;
import RTC.ExecutionContext;
import RTC.RTObject;
import RTC.ReturnCode_t;
import _SDOPackage.Configuration;
import _SDOPackage.Organization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Corba Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getExecutionContextState <em>Execution Context State</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getComponentState <em>Component State</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOConfiguration <em>SDO Configuration</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCComponentProfile <em>RTC Component Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCExecutionContext <em>RTC Execution Context</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOOrganization <em>SDO Organization</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getIor <em>Ior</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent()
 * @model
 * @generated
 */
public interface CorbaComponent extends Component, CorbaWrapperObject {
	public static final int STATE_ALIVE = 2;
	public static final int STATE_CREATED = 1;
	public static final int STATE_UNKNOWN = 0;
	
	public static final int RETURNCODE_OK = ReturnCode_t.RTC_OK.value();
	public static final int RETURNCODE_ERROR = ReturnCode_t.RTC_ERROR.value();
	public static final int RETURNCODE_BAD_PARAMETER = ReturnCode_t.BAD_PARAMETER.value();
	public static final int RETURNCODE_UNSUPPORTED = ReturnCode_t.UNSUPPORTED.value();
	public static final int RETURNCODE_OUT_OF_RESOURCES = ReturnCode_t.OUT_OF_RESOURCES.value();
	public static final int RETURNCODE_PRECONDITION_NOT_MET = ReturnCode_t.PRECONDITION_NOT_MET.value();
	
	/**
	 * Returns the value of the '<em><b>Execution Context State</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Context State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Context State</em>' attribute.
	 * @see #setExecutionContextState(int)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_ExecutionContextState()
	 * @model default="0" transient="true"
	 * @generated
	 */
	int getExecutionContextState();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getExecutionContextState <em>Execution Context State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Context State</em>' attribute.
	 * @see #getExecutionContextState()
	 * @generated
	 */
	void setExecutionContextState(int value);

	/**
	 * Returns the value of the '<em><b>Component State</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component State</em>' attribute.
	 * @see #setComponentState(int)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_ComponentState()
	 * @model default="1" transient="true"
	 * @generated
	 */
	int getComponentState();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getComponentState <em>Component State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component State</em>' attribute.
	 * @see #getComponentState()
	 * @generated
	 */
	void setComponentState(int value);

	/**
	 * Returns the value of the '<em><b>SDO Configuration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SDO Configuration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SDO Configuration</em>' attribute.
	 * @see #setSDOConfiguration(Configuration)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_SDOConfiguration()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.SDOConfiguration"
	 * @generated
	 */
	Configuration getSDOConfiguration();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOConfiguration <em>SDO Configuration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SDO Configuration</em>' attribute.
	 * @see #getSDOConfiguration()
	 * @generated
	 */
	void setSDOConfiguration(Configuration value);

	/**
	 * Returns the value of the '<em><b>RTC Component Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RTC Component Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RTC Component Profile</em>' attribute.
	 * @see #setRTCComponentProfile(ComponentProfile)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_RTCComponentProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.RTCComponentProfile" transient="true"
	 * @generated
	 */
	ComponentProfile getRTCComponentProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getRTCComponentProfile <em>RTC Component Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RTC Component Profile</em>' attribute.
	 * @see #getRTCComponentProfile()
	 * @generated
	 */
	void setRTCComponentProfile(ComponentProfile value);

	/**
	 * Returns the value of the '<em><b>RTC Execution Context</b></em>' attribute list.
	 * The list contents are of type {@link RTC.ExecutionContext}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RTC Execution Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RTC Execution Context</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_RTCExecutionContext()
	 * @model default="" dataType="jp.go.aist.rtm.toolscommon.model.component.RTCExecutionContext" transient="true"
	 * @generated
	 */
	EList<ExecutionContext> getRTCExecutionContext();

	/**
	 * Returns the value of the '<em><b>SDO Organization</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SDO Organization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SDO Organization</em>' attribute.
	 * @see #setSDOOrganization(Organization)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_SDOOrganization()
	 * @model default="" dataType="jp.go.aist.rtm.toolscommon.model.component.SDOOrganization"
	 * @generated
	 */
	Organization getSDOOrganization();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getSDOOrganization <em>SDO Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SDO Organization</em>' attribute.
	 * @see #getSDOOrganization()
	 * @generated
	 */
	void setSDOOrganization(Organization value);

	/**
	 * Returns the value of the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ior</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ior</em>' attribute.
	 * @see #setIor(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaComponent_Ior()
	 * @model
	 * @generated
	 */
	String getIor();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent#getIor <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ior</em>' attribute.
	 * @see #getIor()
	 * @generated
	 */
	void setIor(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int startR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int stopR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int activateR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int deactivateR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int resetR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int finalizeR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int exitR();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.component.RTCRTObject"
	 * @generated
	 */
	RTObject getCorbaObjectInterface();

	RTC.ExecutionContext getPrimaryRTCExecutionContext();

	void activateAll();
	void deactivateAll();
	void startAll();
	void stopAll();

} // CorbaComponent
