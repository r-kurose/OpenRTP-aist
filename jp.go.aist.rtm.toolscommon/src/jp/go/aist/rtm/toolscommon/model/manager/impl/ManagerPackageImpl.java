/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.manager.impl;

import RTM.ManagerProfile;
import RTM.ModuleProfile;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;

import jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl;

import jp.go.aist.rtm.toolscommon.model.core.CorePackage;

import jp.go.aist.rtm.toolscommon.model.core.impl.CorePackageImpl;

import jp.go.aist.rtm.toolscommon.model.manager.ManagerFactory;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ManagerPackageImpl extends EPackageImpl implements ManagerPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rtcManagerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtmManagerProfileEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtmModuleProfileEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ManagerPackageImpl() {
		super(eNS_URI, ManagerFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ManagerPackage init() {
		if (isInited) return (ManagerPackage)EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI);

		// Obtain or create and register package
		ManagerPackageImpl theManagerPackage = (ManagerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ManagerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ManagerPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ComponentPackageImpl theComponentPackage = (ComponentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) instanceof ComponentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) : ComponentPackage.eINSTANCE);

		// Create package meta-data objects
		theManagerPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theComponentPackage.createPackageContents();

		// Initialize created meta-data
		theManagerPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theComponentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theManagerPackage.freeze();

		return theManagerPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRTCManager() {
		return rtcManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_ManagerProfile() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_InstanceNameL() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_PathId() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_ComponentProfiles() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_LoadableModuleProfiles() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_LoadedModuleProfiles() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRTCManager_FactoryModuleProfiles() {
		return (EAttribute)rtcManagerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTMManagerProfile() {
		return rtmManagerProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRTMModuleProfile() {
		return rtmModuleProfileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagerFactory getManagerFactory() {
		return (ManagerFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		rtcManagerEClass = createEClass(RTC_MANAGER);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__MANAGER_PROFILE);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__INSTANCE_NAME_L);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__PATH_ID);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__COMPONENT_PROFILES);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__LOADABLE_MODULE_PROFILES);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__LOADED_MODULE_PROFILES);
		createEAttribute(rtcManagerEClass, RTC_MANAGER__FACTORY_MODULE_PROFILES);

		// Create data types
		rtmManagerProfileEDataType = createEDataType(RTM_MANAGER_PROFILE);
		rtmModuleProfileEDataType = createEDataType(RTM_MODULE_PROFILE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		ComponentPackage theComponentPackage = (ComponentPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		rtcManagerEClass.getESuperTypes().add(theCorePackage.getCorbaWrapperObject());

		// Initialize classes and features; add operations and parameters
		initEClass(rtcManagerEClass, RTCManager.class, "RTCManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRTCManager_ManagerProfile(), this.getRTMManagerProfile(), "managerProfile", null, 0, 1, RTCManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCManager_InstanceNameL(), ecorePackage.getEString(), "instanceNameL", null, 0, 1, RTCManager.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCManager_PathId(), ecorePackage.getEString(), "pathId", null, 0, 1, RTCManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCManager_ComponentProfiles(), theComponentPackage.getRTCComponentProfile(), "componentProfiles", null, 0, -1, RTCManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCManager_LoadableModuleProfiles(), this.getRTMModuleProfile(), "loadableModuleProfiles", null, 0, -1, RTCManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCManager_LoadedModuleProfiles(), this.getRTMModuleProfile(), "loadedModuleProfiles", null, 0, -1, RTCManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRTCManager_FactoryModuleProfiles(), this.getRTMModuleProfile(), "factoryModuleProfiles", null, 0, -1, RTCManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rtcManagerEClass, this.getRTMManagerProfile(), "getProfileR", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(rtcManagerEClass, theComponentPackage.getComponent(), "createComponentR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "compName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(rtcManagerEClass, ecorePackage.getEInt(), "deleteComponentR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "instanceName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, theComponentPackage.getRTCRTObject(), "getComponentsR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, theComponentPackage.getRTCComponentProfile(), "getComponentProfilesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(rtcManagerEClass, ecorePackage.getEInt(), "loadModuleR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "pathname", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "initfunc", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(rtcManagerEClass, ecorePackage.getEInt(), "unloadModuleR", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "pathname", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, this.getRTMModuleProfile(), "getLoadableModuleProfilesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, this.getRTMModuleProfile(), "getLoadedModuleProfilesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, this.getRTMModuleProfile(), "getFactoryModuleProfilesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEInt(), "forkR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEInt(), "shutdownR", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getComponentInstanceNamesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getLoadableModuleFileNamesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getLoadedModuleFileNamesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getFactoryProfileTypeNamesR", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getComponentInstanceNames", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getLoadableModuleFileNames", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getLoadedModuleFileNames", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rtcManagerEClass, ecorePackage.getEString(), "getFactoryTypeNames", 0, -1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(rtmManagerProfileEDataType, ManagerProfile.class, "RTMManagerProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(rtmModuleProfileEDataType, ModuleProfile.class, "RTMModuleProfile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ManagerPackageImpl
