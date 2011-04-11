package jp.go.aist.rtm.toolscommon.model.component;

import RTC.ExecutionContextProfile;

import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

public interface CorbaExecutionContext extends ExecutionContext, CorbaWrapperObject {

	/**
	 * Returns the value of the '<em><b>Rtc Execution Context Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rtc Execution Context Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rtc Execution Context Profile</em>' attribute.
	 * @see #setRtcExecutionContextProfile(ExecutionContextProfile)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaExecutionContext_RtcExecutionContextProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.RTCExecutionContextProfile"
	 * @generated
	 */
	ExecutionContextProfile getRtcExecutionContextProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext#getRtcExecutionContextProfile <em>Rtc Execution Context Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rtc Execution Context Profile</em>' attribute.
	 * @see #getRtcExecutionContextProfile()
	 * @generated
	 */
	void setRtcExecutionContextProfile(ExecutionContextProfile value);

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
	int activateR(Component comp);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int deactivateR(Component comp);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int resetR(Component comp);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int getComponentStateR(Component comp);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int getComponentState(Component comp);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setComponentState(Component comp, int state);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getComponentStateName(Component comp);

	RTC.ExecutionContext getCorbaObjectInterface();
}
