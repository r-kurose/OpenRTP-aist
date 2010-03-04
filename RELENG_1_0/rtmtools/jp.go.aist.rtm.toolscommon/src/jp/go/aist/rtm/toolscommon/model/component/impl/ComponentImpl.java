/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getConfigurationSets <em>Configuration Sets</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getActiveConfigurationSet <em>Active Configuration Set</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getInports <em>Inports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getOutports <em>Outports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getServiceports <em>Serviceports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getExecutionContexts <em>Execution Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getChildSystemDiagram <em>Child System Diagram</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getVenderL <em>Vender L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getDescriptionL <em>Description L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getCategoryL <em>Category L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getTypeNameL <em>Type Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getVersionL <em>Version L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getPathId <em>Path Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getOutportDirection <em>Outport Direction</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getCompositeTypeL <em>Composite Type L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#getComponentId <em>Component Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl#isRequired <em>Required</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ComponentImpl extends WrapperObjectImpl implements Component {
	/**
	 * The cached value of the '{@link #getConfigurationSets() <em>Configuration Sets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationSets()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfigurationSet> configurationSets;

	/**
	 * The cached value of the '{@link #getActiveConfigurationSet() <em>Active Configuration Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveConfigurationSet()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationSet activeConfigurationSet;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<Port> ports;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> components;

	/**
	 * The cached value of the '{@link #getExecutionContexts() <em>Execution Contexts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContexts()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContext> executionContexts;

	/**
	 * The cached value of the '{@link #getChildSystemDiagram() <em>Child System Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildSystemDiagram()
	 * @generated
	 * @ordered
	 */
	protected SystemDiagram childSystemDiagram;

	/**
	 * The default value of the '{@link #getInstanceNameL() <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceNameL()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_NAME_L_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getInstanceNameL() <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceNameL()
	 * @generated
	 * @ordered
	 */
	protected String instanceNameL = INSTANCE_NAME_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getVenderL() <em>Vender L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVenderL()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDER_L_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVenderL() <em>Vender L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVenderL()
	 * @generated
	 * @ordered
	 */
	protected String venderL = VENDER_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescriptionL() <em>Description L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptionL()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_L_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescriptionL() <em>Description L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptionL()
	 * @generated
	 * @ordered
	 */
	protected String descriptionL = DESCRIPTION_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getCategoryL() <em>Category L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoryL()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_L_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getCategoryL() <em>Category L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoryL()
	 * @generated
	 * @ordered
	 */
	protected String categoryL = CATEGORY_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeNameL() <em>Type Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeNameL()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_NAME_L_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTypeNameL() <em>Type Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeNameL()
	 * @generated
	 * @ordered
	 */
	protected String typeNameL = TYPE_NAME_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionL() <em>Version L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersionL()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_L_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVersionL() <em>Version L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersionL()
	 * @generated
	 * @ordered
	 */
	protected String versionL = VERSION_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getPathId() <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathId()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPathId() <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathId()
	 * @generated
	 * @ordered
	 */
	protected String pathId = PATH_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutportDirection() <em>Outport Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportDirection()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPORT_DIRECTION_EDEFAULT = "RIGHT";

	/**
	 * The cached value of the '{@link #getOutportDirection() <em>Outport Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutportDirection()
	 * @generated
	 * @ordered
	 */
	protected String outportDirection = OUTPORT_DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompositeTypeL() <em>Composite Type L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeTypeL()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPOSITE_TYPE_L_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompositeTypeL() <em>Composite Type L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeTypeL()
	 * @generated
	 * @ordered
	 */
	protected String compositeTypeL = COMPOSITE_TYPE_L_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentId() <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentId()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentId() <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentId()
	 * @generated
	 * @ordered
	 */
	protected String componentId = COMPONENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected boolean required = REQUIRED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConfigurationSet> getConfigurationSets() {
		if (configurationSets == null) {
			configurationSets = new EObjectContainmentEList<ConfigurationSet>(ConfigurationSet.class, this, ComponentPackage.COMPONENT__CONFIGURATION_SETS);
		}
		return configurationSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationSet getActiveConfigurationSet() {
		if (activeConfigurationSet != null && activeConfigurationSet.eIsProxy()) {
			InternalEObject oldActiveConfigurationSet = (InternalEObject)activeConfigurationSet;
			activeConfigurationSet = (ConfigurationSet)eResolveProxy(oldActiveConfigurationSet);
			if (activeConfigurationSet != oldActiveConfigurationSet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.COMPONENT__ACTIVE_CONFIGURATION_SET, oldActiveConfigurationSet, activeConfigurationSet));
			}
		}
		return activeConfigurationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationSet basicGetActiveConfigurationSet() {
		return activeConfigurationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveConfigurationSet(ConfigurationSet newActiveConfigurationSet) {
		ConfigurationSet oldActiveConfigurationSet = activeConfigurationSet;
		activeConfigurationSet = newActiveConfigurationSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__ACTIVE_CONFIGURATION_SET, oldActiveConfigurationSet, activeConfigurationSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Port> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentEList<Port>(Port.class, this, ComponentPackage.COMPONENT__PORTS);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList getInports() {
		return selectPorts(InPort.class);
	}

	@SuppressWarnings("unchecked")
	private EList selectPorts(Class<?> cls) {
		try {
			EList result = new BasicEList();
			for (Object element: getPorts()) {
				if (cls.isAssignableFrom(element.getClass())) {
					result.add(element);
				}
			}
			return result;
		} catch (ConcurrentModificationException ex) {
			ex.printStackTrace();
			// 別スレッドで更新がかかった時は、とりあえず空のリストを返しておく
			return ECollections.EMPTY_ELIST;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList getOutports() {
		return selectPorts(OutPort.class);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList getServiceports() {
		return selectPorts(ServicePort.class);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionContext> getExecutionContexts() {
		if (executionContexts == null) {
			executionContexts = new EObjectContainmentEList<ExecutionContext>(ExecutionContext.class, this, ComponentPackage.COMPONENT__EXECUTION_CONTEXTS);
		}
		return executionContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstanceNameL() {
		return instanceNameL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstanceNameL(String newInstanceNameL) {
		String oldInstanceNameL = instanceNameL;
		instanceNameL = newInstanceNameL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__INSTANCE_NAME_L, oldInstanceNameL, instanceNameL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVenderL() {
		return venderL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVenderL(String newVenderL) {
		String oldVenderL = venderL;
		venderL = newVenderL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__VENDER_L, oldVenderL, venderL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptionL() {
		return descriptionL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescriptionL(String newDescriptionL) {
		String oldDescriptionL = descriptionL;
		descriptionL = newDescriptionL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__DESCRIPTION_L, oldDescriptionL, descriptionL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCategoryL() {
		return categoryL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategoryL(String newCategoryL) {
		String oldCategoryL = categoryL;
		categoryL = newCategoryL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__CATEGORY_L, oldCategoryL, categoryL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeNameL() {
		return typeNameL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeNameL(String newTypeNameL) {
		String oldTypeNameL = typeNameL;
		typeNameL = newTypeNameL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__TYPE_NAME_L, oldTypeNameL, typeNameL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersionL() {
		return versionL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersionL(String newVersionL) {
		String oldVersionL = versionL;
		versionL = newVersionL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__VERSION_L, oldVersionL, versionL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPathId() {
		return pathId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathId(String newPathId) {
		String oldPathId = pathId;
		pathId = newPathId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__PATH_ID, oldPathId, pathId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutportDirection() {
		return outportDirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutportDirection(String newOutportDirection) {
		String oldOutportDirection = outportDirection;
		outportDirection = newOutportDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__OUTPORT_DIRECTION, oldOutportDirection, outportDirection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getCompositeTypeL() {
		if (this.compositeTypeL != null) {
			return this.compositeTypeL;
		}
		String category = this.getCategoryL();
		if (this.isCompositeComponent()) {
			this.compositeTypeL = category.substring("composite.".length());
		} else {
			this.compositeTypeL = "None";
		}
		return this.compositeTypeL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Component> getComponents() {
		if (components == null) {
			components = new EObjectResolvingEList<Component>(Component.class, this, ComponentPackage.COMPONENT__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComponentId() {
		return componentId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentId(String newComponentId) {
		String oldComponentId = componentId;
		componentId = newComponentId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__COMPONENT_ID, oldComponentId, componentId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequired(boolean newRequired) {
		boolean oldRequired = required;
		required = newRequired;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__REQUIRED, oldRequired, required));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemDiagram getChildSystemDiagram() {
		if (childSystemDiagram != null && childSystemDiagram.eIsProxy()) {
			InternalEObject oldChildSystemDiagram = (InternalEObject)childSystemDiagram;
			childSystemDiagram = (SystemDiagram)eResolveProxy(oldChildSystemDiagram);
			if (childSystemDiagram != oldChildSystemDiagram) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.COMPONENT__CHILD_SYSTEM_DIAGRAM, oldChildSystemDiagram, childSystemDiagram));
			}
		}
		return childSystemDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemDiagram basicGetChildSystemDiagram() {
		return childSystemDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChildSystemDiagram(SystemDiagram newChildSystemDiagram) {
		SystemDiagram oldChildSystemDiagram = childSystemDiagram;
		childSystemDiagram = newChildSystemDiagram;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__CHILD_SYSTEM_DIAGRAM, oldChildSystemDiagram, childSystemDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean updateConfigurationSetListR(List list, ConfigurationSet activeConfigurationSet, List originallist) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public List getAllComponents() {
		EList result = new BasicEList();
		result.addAll(getComponents());
		for (Iterator iterator = getComponents().iterator(); iterator.hasNext(); ) {
			Component component = (Component) iterator.next();
			if (!component.equals(this)) {
				result.addAll(component.getAllComponents());
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isCompositeComponent() {
		String category = this.getCategoryL();
		if (category != null && category.startsWith("composite.")) {
			return true;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isGroupingCompositeComponent() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean inOnlineSystemDiagram() {
		if (eContainer() == null) {
			return false;
		}
		if (eContainer() instanceof SystemDiagram) {
			SystemDiagram diagram = (SystemDiagram) eContainer();
			return SystemDiagramKind.ONLINE_LITERAL.equals(diagram.getKind());
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean addComponentsR(List<Component> componentList) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean removeComponentR(Component component) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * 公開されているポートの名称をリストにして返す
	 */
	public List<String> getExportedPorts() {
		List<String> result = new ArrayList<String>();
		ConfigurationSet cs = this.getActiveConfigurationSet();
		if (cs != null && cs.getConfigurationData() != null) {
			for (Object o : cs.getConfigurationData()) {
				NameValue nv = (NameValue) o;
				if (!nv.getName().equals("exported_ports")) {
					continue;
				}
				String value = nv.getValueAsString();
				String[] values = value.split(",");
				for (int i = 0; i < values.length; i++) {
					result.add(values[i].trim());
				}
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public boolean setExportedPorts(EList values) {
		StringBuffer value = new StringBuffer();
		for (Object o : values) {
			if (o instanceof String) {
				String s = (String) o;
				if (value.length() > 0) {
					value.append(",");
				}
				value.append(s);
			}
		}
		ConfigurationSet cs = this.getActiveConfigurationSet();
		if (cs != null && cs.getConfigurationData() != null) {
			for (Object o : cs.getConfigurationData()) {
				NameValue nv = (NameValue) o;
				if (!nv.getName().equals("exported_ports")) {
					continue;
				}
				nv.setValue(value.toString());
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean setComponentsR(List<Component> componentList) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/** IDとExecutionContextを対応付けて格納 */
	protected Map<String, ExecutionContext> executionContextMap = new HashMap<String, ExecutionContext>();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public ExecutionContext setExecutionContext(String id, ExecutionContext ec) {
		if (!getExecutionContexts().contains(ec)) {
			getExecutionContexts().add(ec);
		}
		executionContextMap.put(id, ec);
		return ec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ExecutionContext getExecutionContext(String id) {
		return executionContextMap.get(id);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getExecutionContextId(ExecutionContext ec) {
		for (String id : executionContextMap.keySet()) {
			ExecutionContext e = executionContextMap.get(id);
			if (e != null && e.equals(ec)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentPackage.COMPONENT__CONFIGURATION_SETS:
				return ((InternalEList<?>)getConfigurationSets()).basicRemove(otherEnd, msgs);
			case ComponentPackage.COMPONENT__PORTS:
				return ((InternalEList<?>)getPorts()).basicRemove(otherEnd, msgs);
			case ComponentPackage.COMPONENT__EXECUTION_CONTEXTS:
				return ((InternalEList<?>)getExecutionContexts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.COMPONENT__CONFIGURATION_SETS:
				return getConfigurationSets();
			case ComponentPackage.COMPONENT__ACTIVE_CONFIGURATION_SET:
				if (resolve) return getActiveConfigurationSet();
				return basicGetActiveConfigurationSet();
			case ComponentPackage.COMPONENT__PORTS:
				return getPorts();
			case ComponentPackage.COMPONENT__INPORTS:
				return getInports();
			case ComponentPackage.COMPONENT__OUTPORTS:
				return getOutports();
			case ComponentPackage.COMPONENT__SERVICEPORTS:
				return getServiceports();
			case ComponentPackage.COMPONENT__COMPONENTS:
				return getComponents();
			case ComponentPackage.COMPONENT__EXECUTION_CONTEXTS:
				return getExecutionContexts();
			case ComponentPackage.COMPONENT__CHILD_SYSTEM_DIAGRAM:
				if (resolve) return getChildSystemDiagram();
				return basicGetChildSystemDiagram();
			case ComponentPackage.COMPONENT__INSTANCE_NAME_L:
				return getInstanceNameL();
			case ComponentPackage.COMPONENT__VENDER_L:
				return getVenderL();
			case ComponentPackage.COMPONENT__DESCRIPTION_L:
				return getDescriptionL();
			case ComponentPackage.COMPONENT__CATEGORY_L:
				return getCategoryL();
			case ComponentPackage.COMPONENT__TYPE_NAME_L:
				return getTypeNameL();
			case ComponentPackage.COMPONENT__VERSION_L:
				return getVersionL();
			case ComponentPackage.COMPONENT__PATH_ID:
				return getPathId();
			case ComponentPackage.COMPONENT__OUTPORT_DIRECTION:
				return getOutportDirection();
			case ComponentPackage.COMPONENT__COMPOSITE_TYPE_L:
				return getCompositeTypeL();
			case ComponentPackage.COMPONENT__COMPONENT_ID:
				return getComponentId();
			case ComponentPackage.COMPONENT__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.COMPONENT__CONFIGURATION_SETS:
				getConfigurationSets().clear();
				getConfigurationSets().addAll((Collection<? extends ConfigurationSet>)newValue);
				return;
			case ComponentPackage.COMPONENT__ACTIVE_CONFIGURATION_SET:
				setActiveConfigurationSet((ConfigurationSet)newValue);
				return;
			case ComponentPackage.COMPONENT__PORTS:
				getPorts().clear();
				getPorts().addAll((Collection<? extends Port>)newValue);
				return;
			case ComponentPackage.COMPONENT__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends Component>)newValue);
				return;
			case ComponentPackage.COMPONENT__EXECUTION_CONTEXTS:
				getExecutionContexts().clear();
				getExecutionContexts().addAll((Collection<? extends ExecutionContext>)newValue);
				return;
			case ComponentPackage.COMPONENT__CHILD_SYSTEM_DIAGRAM:
				setChildSystemDiagram((SystemDiagram)newValue);
				return;
			case ComponentPackage.COMPONENT__INSTANCE_NAME_L:
				setInstanceNameL((String)newValue);
				return;
			case ComponentPackage.COMPONENT__VENDER_L:
				setVenderL((String)newValue);
				return;
			case ComponentPackage.COMPONENT__DESCRIPTION_L:
				setDescriptionL((String)newValue);
				return;
			case ComponentPackage.COMPONENT__CATEGORY_L:
				setCategoryL((String)newValue);
				return;
			case ComponentPackage.COMPONENT__TYPE_NAME_L:
				setTypeNameL((String)newValue);
				return;
			case ComponentPackage.COMPONENT__VERSION_L:
				setVersionL((String)newValue);
				return;
			case ComponentPackage.COMPONENT__PATH_ID:
				setPathId((String)newValue);
				return;
			case ComponentPackage.COMPONENT__OUTPORT_DIRECTION:
				setOutportDirection((String)newValue);
				return;
			case ComponentPackage.COMPONENT__COMPONENT_ID:
				setComponentId((String)newValue);
				return;
			case ComponentPackage.COMPONENT__REQUIRED:
				setRequired(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentPackage.COMPONENT__CONFIGURATION_SETS:
				getConfigurationSets().clear();
				return;
			case ComponentPackage.COMPONENT__ACTIVE_CONFIGURATION_SET:
				setActiveConfigurationSet((ConfigurationSet)null);
				return;
			case ComponentPackage.COMPONENT__PORTS:
				getPorts().clear();
				return;
			case ComponentPackage.COMPONENT__COMPONENTS:
				getComponents().clear();
				return;
			case ComponentPackage.COMPONENT__EXECUTION_CONTEXTS:
				getExecutionContexts().clear();
				return;
			case ComponentPackage.COMPONENT__CHILD_SYSTEM_DIAGRAM:
				setChildSystemDiagram((SystemDiagram)null);
				return;
			case ComponentPackage.COMPONENT__INSTANCE_NAME_L:
				setInstanceNameL(INSTANCE_NAME_L_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__VENDER_L:
				setVenderL(VENDER_L_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__DESCRIPTION_L:
				setDescriptionL(DESCRIPTION_L_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__CATEGORY_L:
				setCategoryL(CATEGORY_L_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__TYPE_NAME_L:
				setTypeNameL(TYPE_NAME_L_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__VERSION_L:
				setVersionL(VERSION_L_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__PATH_ID:
				setPathId(PATH_ID_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__OUTPORT_DIRECTION:
				setOutportDirection(OUTPORT_DIRECTION_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__COMPONENT_ID:
				setComponentId(COMPONENT_ID_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT__REQUIRED:
				setRequired(REQUIRED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.COMPONENT__CONFIGURATION_SETS:
				return configurationSets != null && !configurationSets.isEmpty();
			case ComponentPackage.COMPONENT__ACTIVE_CONFIGURATION_SET:
				return activeConfigurationSet != null;
			case ComponentPackage.COMPONENT__PORTS:
				return ports != null && !ports.isEmpty();
			case ComponentPackage.COMPONENT__INPORTS:
				return !getInports().isEmpty();
			case ComponentPackage.COMPONENT__OUTPORTS:
				return !getOutports().isEmpty();
			case ComponentPackage.COMPONENT__SERVICEPORTS:
				return !getServiceports().isEmpty();
			case ComponentPackage.COMPONENT__COMPONENTS:
				return components != null && !components.isEmpty();
			case ComponentPackage.COMPONENT__EXECUTION_CONTEXTS:
				return executionContexts != null && !executionContexts.isEmpty();
			case ComponentPackage.COMPONENT__CHILD_SYSTEM_DIAGRAM:
				return childSystemDiagram != null;
			case ComponentPackage.COMPONENT__INSTANCE_NAME_L:
				return INSTANCE_NAME_L_EDEFAULT == null ? instanceNameL != null : !INSTANCE_NAME_L_EDEFAULT.equals(instanceNameL);
			case ComponentPackage.COMPONENT__VENDER_L:
				return VENDER_L_EDEFAULT == null ? venderL != null : !VENDER_L_EDEFAULT.equals(venderL);
			case ComponentPackage.COMPONENT__DESCRIPTION_L:
				return DESCRIPTION_L_EDEFAULT == null ? descriptionL != null : !DESCRIPTION_L_EDEFAULT.equals(descriptionL);
			case ComponentPackage.COMPONENT__CATEGORY_L:
				return CATEGORY_L_EDEFAULT == null ? categoryL != null : !CATEGORY_L_EDEFAULT.equals(categoryL);
			case ComponentPackage.COMPONENT__TYPE_NAME_L:
				return TYPE_NAME_L_EDEFAULT == null ? typeNameL != null : !TYPE_NAME_L_EDEFAULT.equals(typeNameL);
			case ComponentPackage.COMPONENT__VERSION_L:
				return VERSION_L_EDEFAULT == null ? versionL != null : !VERSION_L_EDEFAULT.equals(versionL);
			case ComponentPackage.COMPONENT__PATH_ID:
				return PATH_ID_EDEFAULT == null ? pathId != null : !PATH_ID_EDEFAULT.equals(pathId);
			case ComponentPackage.COMPONENT__OUTPORT_DIRECTION:
				return OUTPORT_DIRECTION_EDEFAULT == null ? outportDirection != null : !OUTPORT_DIRECTION_EDEFAULT.equals(outportDirection);
			case ComponentPackage.COMPONENT__COMPOSITE_TYPE_L:
				return COMPOSITE_TYPE_L_EDEFAULT == null ? compositeTypeL != null : !COMPOSITE_TYPE_L_EDEFAULT.equals(compositeTypeL);
			case ComponentPackage.COMPONENT__COMPONENT_ID:
				return COMPONENT_ID_EDEFAULT == null ? componentId != null : !COMPONENT_ID_EDEFAULT.equals(componentId);
			case ComponentPackage.COMPONENT__REQUIRED:
				return required != REQUIRED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (instanceNameL: ");
		result.append(instanceNameL);
		result.append(", venderL: ");
		result.append(venderL);
		result.append(", descriptionL: ");
		result.append(descriptionL);
		result.append(", categoryL: ");
		result.append(categoryL);
		result.append(", typeNameL: ");
		result.append(typeNameL);
		result.append(", versionL: ");
		result.append(versionL);
		result.append(", pathId: ");
		result.append(pathId);
		result.append(", outportDirection: ");
		result.append(outportDirection);
		result.append(", compositeTypeL: ");
		result.append(compositeTypeL);
		result.append(", componentId: ");
		result.append(componentId);
		result.append(", required: ");
		result.append(required);
		result.append(')');
		return result.toString();
	}

	// 同一コンポーネントであるかのチェックをcomponentId + pathIdを用いて行う。
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Component))
			return false;
		//TODO 09.09.30 instanceName 追加(pathURI対応)
		if (getComponentId() == null || getPathId() == null || getInstanceNameL() == null)
			return super.equals(obj);
		Component other = (Component) obj;
		return getComponentId().equals(other.getComponentId())
				&& getPathId().equals(other.getPathId())
				&& getInstanceNameL().equals(other.getInstanceNameL());
	}

	@Override
	public int hashCode() {
		if (getComponentId() == null || getPathId() == null)
			return super.hashCode();
		return getComponentId().hashCode() * 3 + getPathId().hashCode() + 5;
	}

	public void synchronizeChildComponents() {
		for (Object content : eContents()) {
			if (content instanceof LocalObject) {
				LocalObject lo = (LocalObject) content;
				if (lo.getSynchronizationSupport() != null) {
					lo.getSynchronizationSupport().synchronizeLocal();
				}
			}
		}
		
		if (getComponents() == null) {
			return;
		}
		for (Object obj : getComponents()) {
			if (obj instanceof CorbaComponent) {
				CorbaComponent c = (CorbaComponent) obj;
				c.synchronizeLocalAttribute(null);
				c.synchronizeLocalReference();
				c.synchronizeChildComponents();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public synchronized void addComponent(Component component) {
		getComponents().add(component);
	}

	protected void adjustPathId(List<Component> components) {
		String basePathId = getPathId();
		for (Component c: components) {
			if (c.getPathId() == null) {
				c.setPathId(basePathId.substring(0, basePathId.lastIndexOf("/") + 1)
								+  c.getInstanceNameL() + ".rtc");
			}
		}
	}
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@SuppressWarnings("unchecked")
	public void accept(Visiter visiter) {
		super.accept(visiter);
		for (Object obj : getComponents()) {
			ModelElement element = (ModelElement) obj;
			element.accept(visiter);
		}
	}

	@SuppressWarnings("unchecked")
//	@Override
	public void removeDeadChild() {
		for (Iterator iterate = getComponents().iterator();iterate.hasNext();){
			Component c = (Component)iterate.next();
			if (c.isDead()) {
				iterate.remove();
			} else {
				c.removeDeadChild();
			}
		}
		
	}
	
	

} //ComponentImpl
