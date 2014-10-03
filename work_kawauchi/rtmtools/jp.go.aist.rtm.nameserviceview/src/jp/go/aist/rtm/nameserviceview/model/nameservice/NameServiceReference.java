package jp.go.aist.rtm.nameserviceview.model.nameservice;

import OpenRTMNaming.NamingNotifier;
import OpenRTMNaming.ObserverProfile;
import org.eclipse.emf.ecore.EObject;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NamingContextExt;

/**
 * コンテクストノードのネームサービスルートからの参照を表すオブジェクト（CORBA専用）
 * @model
 */
public interface NameServiceReference extends EObject{

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see #setBinding(Binding)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNameServiceReference_Binding()
	 * @model dataType="jp.go.aist.rtm.nameserviceview.model.nameservice.Binding"
	 * @generated
	 */
	Binding getBinding();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(Binding value);

	/**
	 * Returns the value of the '<em><b>Name Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Server Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Server Name</em>' attribute.
	 * @see #setNameServerName(String)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNameServiceReference_NameServerName()
	 * @model
	 * @generated
	 */
	String getNameServerName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference#getNameServerName <em>Name Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name Server Name</em>' attribute.
	 * @see #getNameServerName()
	 * @generated
	 */
	void setNameServerName(String value);

	/**
	 * Returns the value of the '<em><b>Root Naming Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Naming Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Naming Context</em>' attribute.
	 * @see #setRootNamingContext(NamingContextExt)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNameServiceReference_RootNamingContext()
	 * @model dataType="jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextExt" transient="true"
	 * @generated
	 */
	NamingContextExt getRootNamingContext();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference#getRootNamingContext <em>Root Naming Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Naming Context</em>' attribute.
	 * @see #getRootNamingContext()
	 * @generated
	 */
	void setRootNamingContext(NamingContextExt value);

	/**
	 * Returns the value of the '<em><b>Notifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notifier</em>' attribute.
	 * @see #setNotifier(NamingNotifier)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNameServiceReference_Notifier()
	 * @model dataType="jp.go.aist.rtm.nameserviceview.model.nameservice.NamingNotifier" transient="true"
	 * @generated
	 */
	NamingNotifier getNotifier();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference#getNotifier <em>Notifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notifier</em>' attribute.
	 * @see #getNotifier()
	 * @generated
	 */
	void setNotifier(NamingNotifier value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model childBindingDataType="jp.go.aist.rtm.nameserviceview.model.nameservice.Binding"
	 * @generated
	 */
	NameServiceReference createChildReference(Binding childBinding);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="jp.go.aist.rtm.nameserviceview.model.nameservice.Binding" childBindingDataType="jp.go.aist.rtm.nameserviceview.model.nameservice.Binding"
	 * @generated
	 */
	Binding createChildBinding(Binding childBinding);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.nameserviceview.model.nameservice.Binding"
	 * @generated
	 */
	Binding getBaseBinding();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getPathId();

	/**
	 * Returns the value of the '<em><b>Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updated</em>' attribute.
	 * @see #setUpdated(boolean)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNameServiceReference_Updated()
	 * @model transient="true"
	 * @generated
	 */
	boolean isUpdated();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference#isUpdated <em>Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updated</em>' attribute.
	 * @see #isUpdated()
	 * @generated
	 */
	void setUpdated(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="jp.go.aist.rtm.nameserviceview.model.nameservice.ObserverProfile"
	 * @generated
	 */
	ObserverProfile runObserver();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void stopObserver();

}
