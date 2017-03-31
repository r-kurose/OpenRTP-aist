/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.manager;

import jp.go.aist.rtm.toolscommon.model.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerFactory
 * @model kind="package"
 * @generated
 */
public interface ManagerPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "manager";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///jp/go/aist/rtm/toolscommon/model/manager.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jp.go.aist.rtm.toolscommon.model.manager";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ManagerPackage eINSTANCE = jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl.init();

	/**
	 * The meta object id for the '{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl <em>RTC Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl
	 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl#getRTCManager()
	 * @generated
	 */
	int RTC_MANAGER = 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__CONSTRAINT = CorePackage.CORBA_WRAPPER_OBJECT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Corba Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__CORBA_OBJECT = CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT;

	/**
	 * The feature id for the '<em><b>Manager Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__MANAGER_PROFILE = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__INSTANCE_NAME_L = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__PATH_ID = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Component Profiles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__COMPONENT_PROFILES = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Loadable Module Profiles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__LOADABLE_MODULE_PROFILES = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Loaded Module Profiles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__LOADED_MODULE_PROFILES = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Factory Module Profiles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER__FACTORY_MODULE_PROFILES = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>RTC Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RTC_MANAGER_FEATURE_COUNT = CorePackage.CORBA_WRAPPER_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '<em>RTM Manager Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTM.ManagerProfile
	 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl#getRTMManagerProfile()
	 * @generated
	 */
	int RTM_MANAGER_PROFILE = 1;

	/**
	 * The meta object id for the '<em>RTM Module Profile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see RTM.ModuleProfile
	 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl#getRTMModuleProfile()
	 * @generated
	 */
	int RTM_MODULE_PROFILE = 2;


	/**
	 * Returns the meta object for class '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager <em>RTC Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RTC Manager</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager
	 * @generated
	 */
	EClass getRTCManager();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getManagerProfile <em>Manager Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manager Profile</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getManagerProfile()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_ManagerProfile();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getInstanceNameL <em>Instance Name L</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Name L</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getInstanceNameL()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_InstanceNameL();

	/**
	 * Returns the meta object for the attribute '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getPathId <em>Path Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path Id</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getPathId()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_PathId();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getComponentProfiles <em>Component Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Component Profiles</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getComponentProfiles()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_ComponentProfiles();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getLoadableModuleProfiles <em>Loadable Module Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Loadable Module Profiles</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getLoadableModuleProfiles()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_LoadableModuleProfiles();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getLoadedModuleProfiles <em>Loaded Module Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Loaded Module Profiles</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getLoadedModuleProfiles()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_LoadedModuleProfiles();

	/**
	 * Returns the meta object for the attribute list '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getFactoryModuleProfiles <em>Factory Module Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Factory Module Profiles</em>'.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getFactoryModuleProfiles()
	 * @see #getRTCManager()
	 * @generated
	 */
	EAttribute getRTCManager_FactoryModuleProfiles();

	/**
	 * Returns the meta object for data type '{@link RTM.ManagerProfile <em>RTM Manager Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTM Manager Profile</em>'.
	 * @see RTM.ManagerProfile
	 * @model instanceClass="RTM.ManagerProfile"
	 * @generated
	 */
	EDataType getRTMManagerProfile();

	/**
	 * Returns the meta object for data type '{@link RTM.ModuleProfile <em>RTM Module Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RTM Module Profile</em>'.
	 * @see RTM.ModuleProfile
	 * @model instanceClass="RTM.ModuleProfile"
	 * @generated
	 */
	EDataType getRTMModuleProfile();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ManagerFactory getManagerFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl <em>RTC Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl
		 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl#getRTCManager()
		 * @generated
		 */
		EClass RTC_MANAGER = eINSTANCE.getRTCManager();

		/**
		 * The meta object literal for the '<em><b>Manager Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__MANAGER_PROFILE = eINSTANCE.getRTCManager_ManagerProfile();

		/**
		 * The meta object literal for the '<em><b>Instance Name L</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__INSTANCE_NAME_L = eINSTANCE.getRTCManager_InstanceNameL();

		/**
		 * The meta object literal for the '<em><b>Path Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__PATH_ID = eINSTANCE.getRTCManager_PathId();

		/**
		 * The meta object literal for the '<em><b>Component Profiles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__COMPONENT_PROFILES = eINSTANCE.getRTCManager_ComponentProfiles();

		/**
		 * The meta object literal for the '<em><b>Loadable Module Profiles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__LOADABLE_MODULE_PROFILES = eINSTANCE.getRTCManager_LoadableModuleProfiles();

		/**
		 * The meta object literal for the '<em><b>Loaded Module Profiles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__LOADED_MODULE_PROFILES = eINSTANCE.getRTCManager_LoadedModuleProfiles();

		/**
		 * The meta object literal for the '<em><b>Factory Module Profiles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RTC_MANAGER__FACTORY_MODULE_PROFILES = eINSTANCE.getRTCManager_FactoryModuleProfiles();

		/**
		 * The meta object literal for the '<em>RTM Manager Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTM.ManagerProfile
		 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl#getRTMManagerProfile()
		 * @generated
		 */
		EDataType RTM_MANAGER_PROFILE = eINSTANCE.getRTMManagerProfile();

		/**
		 * The meta object literal for the '<em>RTM Module Profile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see RTM.ModuleProfile
		 * @see jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl#getRTMModuleProfile()
		 * @generated
		 */
		EDataType RTM_MODULE_PROFILE = eINSTANCE.getRTMModuleProfile();

	}

} //ManagerPackage
