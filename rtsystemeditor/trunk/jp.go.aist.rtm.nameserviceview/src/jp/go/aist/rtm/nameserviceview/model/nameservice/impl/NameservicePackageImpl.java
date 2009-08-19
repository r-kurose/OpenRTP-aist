/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameservicePackageImpl.java,v 1.6 2008/03/27 06:58:52 yamashita Exp $
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import jp.go.aist.rtm.nameserviceview.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.manager.impl.ManagerPackageImpl;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameserviceFactory;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorePackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NameservicePackageImpl extends EPackageImpl implements NameservicePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namingContextNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namingObjectNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namingContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameServiceReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType namingContextExtEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bindingEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType notFoundEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType cannotProceedEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType invalidNameEDataType = null;

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
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NameservicePackageImpl() {
		super(eNS_URI, NameserviceFactory.eINSTANCE);
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
	public static NameservicePackage init() {
		if (isInited) return (NameservicePackage)EPackage.Registry.INSTANCE.getEPackage(NameservicePackage.eNS_URI);

		// Obtain or create and register package
		NameservicePackageImpl theNameservicePackage = (NameservicePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof NameservicePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new NameservicePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		ManagerPackageImpl theManagerPackage = (ManagerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI) instanceof ManagerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI) : ManagerPackage.eINSTANCE);

		// Create package meta-data objects
		theNameservicePackage.createPackageContents();
		theCorePackage.createPackageContents();
		theManagerPackage.createPackageContents();

		// Initialize created meta-data
		theNameservicePackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theManagerPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNameservicePackage.freeze();

		return theNameservicePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamingContextNode() {
		return namingContextNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamingContextNode_Zombie() {
		return (EAttribute)namingContextNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamingContextNode_Kind() {
		return (EAttribute)namingContextNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamingObjectNode() {
		return namingObjectNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamingObjectNode_Entry() {
		return (EReference)namingObjectNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaNode() {
		return corbaNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCorbaNode_NameServiceReference() {
		return (EReference)corbaNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamingContext() {
		return namingContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNameServiceReference() {
		return nameServiceReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameServiceReference_Binding() {
		return (EAttribute)nameServiceReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameServiceReference_NameServerName() {
		return (EAttribute)nameServiceReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameServiceReference_RootNamingContext() {
		return (EAttribute)nameServiceReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNamingContextExt() {
		return namingContextExtEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBinding() {
		return bindingEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNotFound() {
		return notFoundEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCannotProceed() {
		return cannotProceedEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInvalidName() {
		return invalidNameEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameserviceFactory getNameserviceFactory() {
		return (NameserviceFactory)getEFactoryInstance();
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
		namingContextNodeEClass = createEClass(NAMING_CONTEXT_NODE);
		createEAttribute(namingContextNodeEClass, NAMING_CONTEXT_NODE__ZOMBIE);
		createEAttribute(namingContextNodeEClass, NAMING_CONTEXT_NODE__KIND);

		namingObjectNodeEClass = createEClass(NAMING_OBJECT_NODE);
		createEReference(namingObjectNodeEClass, NAMING_OBJECT_NODE__ENTRY);

		corbaNodeEClass = createEClass(CORBA_NODE);
		createEReference(corbaNodeEClass, CORBA_NODE__NAME_SERVICE_REFERENCE);

		namingContextEClass = createEClass(NAMING_CONTEXT);

		nameServiceReferenceEClass = createEClass(NAME_SERVICE_REFERENCE);
		createEAttribute(nameServiceReferenceEClass, NAME_SERVICE_REFERENCE__BINDING);
		createEAttribute(nameServiceReferenceEClass, NAME_SERVICE_REFERENCE__NAME_SERVER_NAME);
		createEAttribute(nameServiceReferenceEClass, NAME_SERVICE_REFERENCE__ROOT_NAMING_CONTEXT);

		// Create data types
		namingContextExtEDataType = createEDataType(NAMING_CONTEXT_EXT);
		bindingEDataType = createEDataType(BINDING);
		notFoundEDataType = createEDataType(NOT_FOUND);
		cannotProceedEDataType = createEDataType(CANNOT_PROCEED);
		invalidNameEDataType = createEDataType(INVALID_NAME);
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
		ManagerPackage theManagerPackage = (ManagerPackage)EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Add supertypes to classes
		namingContextNodeEClass.getESuperTypes().add(this.getCorbaNode());
		namingContextNodeEClass.getESuperTypes().add(theManagerPackage.getNameServerContext());
		namingObjectNodeEClass.getESuperTypes().add(this.getCorbaNode());
		corbaNodeEClass.getESuperTypes().add(theCorePackage.getCorbaWrapperObject());
		corbaNodeEClass.getESuperTypes().add(theManagerPackage.getNode());

		// Initialize classes and features; add operations and parameters
		initEClass(namingContextNodeEClass, NamingContextNode.class, "NamingContextNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamingContextNode_Zombie(), ecorePackage.getEBoolean(), "zombie", null, 0, 1, NamingContextNode.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamingContextNode_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, NamingContextNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namingObjectNodeEClass, NamingObjectNode.class, "NamingObjectNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNamingObjectNode_Entry(), theCorePackage.getWrapperObject(), null, "entry", null, 0, 1, NamingObjectNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(corbaNodeEClass, CorbaNode.class, "CorbaNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCorbaNode_NameServiceReference(), this.getNameServiceReference(), null, "nameServiceReference", null, 0, 1, CorbaNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(corbaNodeEClass, null, "deleteR");
		addEException(op, this.getNotFound());
		addEException(op, this.getCannotProceed());
		addEException(op, this.getInvalidName());

		initEClass(namingContextEClass, NamingContext.class, "NamingContext", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(nameServiceReferenceEClass, NameServiceReference.class, "NameServiceReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNameServiceReference_Binding(), this.getBinding(), "binding", null, 0, 1, NameServiceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNameServiceReference_NameServerName(), ecorePackage.getEString(), "nameServerName", null, 0, 1, NameServiceReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNameServiceReference_RootNamingContext(), this.getNamingContextExt(), "rootNamingContext", null, 0, 1, NameServiceReference.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(nameServiceReferenceEClass, this.getNameServiceReference(), "createMergedNameServiceReference", 0, 1);
		addEParameter(op, this.getBinding(), "childBinding", 0, 1);

		// Initialize data types
		initEDataType(namingContextExtEDataType, NamingContextExt.class, "NamingContextExt", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(bindingEDataType, Binding.class, "Binding", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(notFoundEDataType, NotFound.class, "NotFound", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(cannotProceedEDataType, CannotProceed.class, "CannotProceed", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(invalidNameEDataType, InvalidName.class, "InvalidName", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //NameservicePackageImpl
