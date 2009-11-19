/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ManyReferenceMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.ui.propertysource.ComponentPropertySource;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;

import RTC.ComponentProfile;
import RTC.ConnectorProfile;
import RTC.PortProfile;
import RTC.RTObject;
import RTC.RTObjectHelper;
import _SDOPackage.Configuration;
import _SDOPackage.InternalError;
import _SDOPackage.InvalidParameter;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.SDO;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getCorbaObject <em>Corba Object</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getExecutionContextState <em>Execution Context State</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getComponentState <em>Component State</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getSDOConfiguration <em>SDO Configuration</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCComponentProfile <em>RTC Component Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCExecutionContext <em>RTC Execution Context</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getSDOOrganization <em>SDO Organization</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getIor <em>Ior</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorbaComponentImpl extends ComponentImpl implements CorbaComponent {
	/**
	 * The default value of the '{@link #getCorbaObject() <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorbaObject()
	 * @generated
	 * @ordered
	 */
	protected static final org.omg.CORBA.Object CORBA_OBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCorbaObject() <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorbaObject()
	 * @generated
	 * @ordered
	 */
	protected org.omg.CORBA.Object corbaObject = CORBA_OBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutionContextState() <em>Execution Context State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContextState()
	 * @generated
	 * @ordered
	 */
	protected static final int EXECUTION_CONTEXT_STATE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExecutionContextState() <em>Execution Context State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContextState()
	 * @generated
	 * @ordered
	 */
	protected int executionContextState = EXECUTION_CONTEXT_STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentState() <em>Component State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentState()
	 * @generated
	 * @ordered
	 */
	protected static final int COMPONENT_STATE_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getComponentState() <em>Component State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentState()
	 * @generated
	 * @ordered
	 */
	protected int componentState = COMPONENT_STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSDOConfiguration() <em>SDO Configuration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOConfiguration()
	 * @generated
	 * @ordered
	 */
	protected static final Configuration SDO_CONFIGURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSDOConfiguration() <em>SDO Configuration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOConfiguration()
	 * @generated
	 * @ordered
	 */
	protected Configuration sDOConfiguration = SDO_CONFIGURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRTCComponentProfile() <em>RTC Component Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCComponentProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ComponentProfile RTC_COMPONENT_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRTCComponentProfile() <em>RTC Component Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCComponentProfile()
	 * @generated
	 * @ordered
	 */
	protected ComponentProfile rTCComponentProfile = RTC_COMPONENT_PROFILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRTCExecutionContext() <em>RTC Execution Context</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCExecutionContext()
	 * @generated
	 * @ordered
	 */
	protected EList rTCExecutionContext= null;

	/**
	 * The default value of the '{@link #getSDOOrganization() <em>SDO Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOOrganization()
	 * @generated NOT
	 * @ordered
	 */
	protected static final Organization SDO_ORGANIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSDOOrganization() <em>SDO Organization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSDOOrganization()
	 * @generated
	 * @ordered
	 */
	protected Organization sDOOrganization = SDO_ORGANIZATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getIor() <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIor()
	 * @generated
	 * @ordered
	 */
	protected static final String IOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIor() <em>Ior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIor()
	 * @generated
	 * @ordered
	 */
	protected String ior = IOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.omg.CORBA.Object getCorbaObject() {
		return corbaObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorbaObject(org.omg.CORBA.Object newCorbaObject) {
		org.omg.CORBA.Object oldCorbaObject = corbaObject;
		corbaObject = newCorbaObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT, oldCorbaObject, corbaObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExecutionContextState() {
		return executionContextState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionContextState(int newExecutionContextState) {
		int oldExecutionContextState = executionContextState;
		executionContextState = newExecutionContextState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE, oldExecutionContextState, executionContextState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getComponentState() {
		return componentState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentState(int newComponentState) {
		int oldComponentState = componentState;
		componentState = newComponentState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE, oldComponentState, componentState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration getSDOConfiguration() {
		return sDOConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSDOConfiguration(Configuration newSDOConfiguration) {
		Configuration oldSDOConfiguration = sDOConfiguration;
		sDOConfiguration = newSDOConfiguration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION, oldSDOConfiguration, sDOConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentProfile getRTCComponentProfile() {
		return rTCComponentProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRTCComponentProfile(ComponentProfile newRTCComponentProfile) {
		ComponentProfile oldRTCComponentProfile = rTCComponentProfile;
		rTCComponentProfile = newRTCComponentProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE, oldRTCComponentProfile, rTCComponentProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public EList getRTCExecutionContext() {
		if (rTCExecutionContext == null) {
			rTCExecutionContext = new EDataTypeUniqueEList(RTC.ExecutionContext.class, this, ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXT);
		}
		return rTCExecutionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Organization getSDOOrganization() {
		return sDOOrganization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSDOOrganization(Organization newSDOOrganization) {
		Organization oldSDOOrganization = sDOOrganization;
		sDOOrganization = newSDOOrganization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION, oldSDOOrganization, sDOOrganization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIor() {
		return ior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIor(String newIor) {
		String oldIor = ior;
		ior = newIor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__IOR, oldIor, ior));
	}

	public RTObject getCorbaObjectInterface() {
		return RTObjectHelper.narrow(getCorbaObject());
	}

	public RTC.ExecutionContext getPrimaryRTCExecutionContext() {
		if (getRTCExecutionContext().size() > 0) 
			return (RTC.ExecutionContext) getRTCExecutionContext().get(0);
		return null;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int startR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext()
					.start()
					.value();
		}
		return RETURNCODE_ERROR;
	}

	public void startAll() {
		for (Object obj : getRTCExecutionContext()) {
			((RTC.ExecutionContext)obj).start();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int stopR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext().stop()
					.value();
		}
		return RETURNCODE_ERROR;
	}

	public void stopAll() {
		for (Object obj : getRTCExecutionContext()) {
			((RTC.ExecutionContext)obj).stop();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int activateR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext()
				.activate_component(getCorbaObjectInterface()).value();
		}
		return RETURNCODE_ERROR;
	}
	
	public void activateAll() {
		for (Object obj : getRTCExecutionContext()) {
			((RTC.ExecutionContext)obj).activate_component(getCorbaObjectInterface());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int deactivateR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext()
				.deactivate_component(getCorbaObjectInterface()).value();
		}
		return RETURNCODE_ERROR;
	}

	public void deactivateAll() {
		for (Object obj : getRTCExecutionContext()) {
			((RTC.ExecutionContext)obj).deactivate_component(getCorbaObjectInterface());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int resetR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext()
				.reset_component(getCorbaObjectInterface()).value();
		}
		return RETURNCODE_ERROR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int finalizeR() {
		return getCorbaObjectInterface()._finalize().value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int exitR() {
		return getCorbaObjectInterface().exit().value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				return getCorbaObject();
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				return new Integer(getExecutionContextState());
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				return new Integer(getComponentState());
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				return getSDOConfiguration();
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				return getRTCComponentProfile();
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXT:
				return getRTCExecutionContext();
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				return getSDOOrganization();
			case ComponentPackage.CORBA_COMPONENT__IOR:
				return getIor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				setCorbaObject((org.omg.CORBA.Object)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				setExecutionContextState(((Integer)newValue).intValue());
				return;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				setComponentState(((Integer)newValue).intValue());
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				setSDOConfiguration((Configuration)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				setRTCComponentProfile((ComponentProfile)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXT:
				getRTCExecutionContext().clear();
				getRTCExecutionContext().addAll((Collection)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				setSDOOrganization((Organization)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				setIor((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				setCorbaObject(CORBA_OBJECT_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				setExecutionContextState(EXECUTION_CONTEXT_STATE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				setComponentState(COMPONENT_STATE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				setSDOConfiguration(SDO_CONFIGURATION_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				setRTCComponentProfile(RTC_COMPONENT_PROFILE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXT:
				getRTCExecutionContext().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				setSDOOrganization(SDO_ORGANIZATION_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				setIor(IOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				return CORBA_OBJECT_EDEFAULT == null ? corbaObject != null : !CORBA_OBJECT_EDEFAULT.equals(corbaObject);
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				return executionContextState != EXECUTION_CONTEXT_STATE_EDEFAULT;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				return componentState != COMPONENT_STATE_EDEFAULT;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				return SDO_CONFIGURATION_EDEFAULT == null ? sDOConfiguration != null : !SDO_CONFIGURATION_EDEFAULT.equals(sDOConfiguration);
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				return RTC_COMPONENT_PROFILE_EDEFAULT == null ? rTCComponentProfile != null : !RTC_COMPONENT_PROFILE_EDEFAULT.equals(rTCComponentProfile);
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXT:
				return rTCExecutionContext != null && !rTCExecutionContext.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				return SDO_ORGANIZATION_EDEFAULT == null ? sDOOrganization != null : !SDO_ORGANIZATION_EDEFAULT.equals(sDOOrganization);
			case ComponentPackage.CORBA_COMPONENT__IOR:
				return IOR_EDEFAULT == null ? ior != null : !IOR_EDEFAULT.equals(ior);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == CorbaWrapperObject.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT: return CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == CorbaWrapperObject.class) {
			switch (baseFeatureID) {
				case CorePackage.CORBA_WRAPPER_OBJECT__CORBA_OBJECT: return ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (corbaObject: ");
		result.append(corbaObject);
		result.append(", executionContextState: ");
		result.append(executionContextState);
		result.append(", componentState: ");
		result.append(componentState);
		result.append(", sDOConfiguration: ");
		result.append(sDOConfiguration);
		result.append(", rTCComponentProfile: ");
		result.append(rTCComponentProfile);
		result.append(", rTCExecutionContext: ");
		result.append(rTCExecutionContext);
		result.append(", sDOOrganization: ");
		result.append(sDOOrganization);
		result.append(", ior: ");
		result.append(ior);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public boolean updateConfigurationSetListR(List localConfigurationSets,
			ConfigurationSet localActiveConfigurationSet,
			List originalConfigurationSets) {

		try {
			Configuration configuration = getCorbaObjectInterface()
					.get_configuration();
			
			List<_SDOPackage.ConfigurationSet> delectedConfigs = new ArrayList<_SDOPackage.ConfigurationSet>();
			_SDOPackage.ConfigurationSet activeConfig = configuration.get_active_configuration_set();
			for (Object original : originalConfigurationSets) {
				ConfigurationSet configurationSet = (ConfigurationSet) original;
				boolean isFind = false;
				final String id = configurationSet.getId();
				for (Iterator iter = localConfigurationSets.iterator(); iter
						.hasNext();) {
					ConfigurationSet element = (ConfigurationSet) iter.next();
					if (element.getId().equals(id)) {
						isFind = true;
						break;
					}
				}

				if (isFind == false) {
					_SDOPackage.ConfigurationSet deletedConfig = configuration.get_configuration_set(id);
					boolean result = configuration.remove_configuration_set(id);
					if (!result) {
						rollbackDelete(configuration, delectedConfigs, activeConfig);
						return false;
					}
					delectedConfigs.add(deletedConfig);
				}
			}

			for (Iterator iter = localConfigurationSets.iterator(); iter
					.hasNext();) {
				ConfigurationSet local = (ConfigurationSet) iter.next();

				boolean isFind = false;
				boolean isModified = false;
				for (Object original : originalConfigurationSets) {
					ConfigurationSet originalConfig = (ConfigurationSet) original;
					if (local.getId().equals(originalConfig.getId())) {
						isFind = true;
						isModified = checkConfigurationSet(local,
								originalConfig);
						break;
					}
				}
				if (isFind) {
					if (isModified) {
						boolean result = configuration
								.set_configuration_set_values(SDOUtil
										.createSdoConfigurationSet(local));
						if (!result) return false;
					}
				} else {
					boolean result = configuration.add_configuration_set(SDOUtil
							.createSdoConfigurationSet(local));
					if (!result) return false;
				}
			}

			if (localActiveConfigurationSet != null) {
				boolean result = configuration
						.activate_configuration_set(localActiveConfigurationSet
								.getId());
				if (!result) return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private void rollbackDelete(Configuration configuration,
			List<_SDOPackage.ConfigurationSet> delectedConfigs,
			_SDOPackage.ConfigurationSet activeConfig) {
		try {
			for (_SDOPackage.ConfigurationSet configurationSet : delectedConfigs) {
					configuration.add_configuration_set(configurationSet);				
			}
			if (activeConfig != null){
				configuration.activate_configuration_set(activeConfig.id);
			}
		} catch (Exception e) {
			// ignore
		}
		
	}

	private boolean checkConfigurationSet(ConfigurationSet local,
			ConfigurationSet original) {

		if (local.getConfigurationData().size() != original
				.getConfigurationData().size())
			return true;

		for (int index = 0; index < local.getConfigurationData().size(); index++) {
			NameValue localNV = (NameValue) local.getConfigurationData().get(
					index);
			NameValue originalNV = (NameValue) original.getConfigurationData()
					.get(index);
			if (!localNV.getName().equals(originalNV.getName()))
				return true;
			if (!localNV.getValueAsString().equals(
					originalNV.getValueAsString()))
				return true;
		}

		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean setComponentsR(List<Component> componentList) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) return false;
			
			List<SDO> list = new ArrayList<SDO>();
			for (Object obj : componentList) {
				if (!(obj instanceof CorbaComponent)) {
					continue;
				}
				CorbaComponent comp = (CorbaComponent) obj;
				list.add(comp.getCorbaObjectInterface());
				if (!getComponents().contains(comp)) addComponent(comp);
			}
			return org.set_members(list.toArray(new SDO[0]));
		} catch (NotAvailable e) {
			e.printStackTrace();
		} catch (InternalError e) {
			e.printStackTrace();
		} catch (InvalidParameter e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean addComponentsR(List<Component> componentList) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) return false;

			List<SDO> list = new ArrayList<SDO>();
			EList components = getComponents();
			for (Object obj : componentList) {
				if (!(obj instanceof CorbaComponent)) {
					continue;
				}
				CorbaComponent comp = (CorbaComponent) obj;
				// リモートオブジェクトが一致するコンポーネントがなければ、
				// 自身を追加する(同期処理で子コンポーネントを複製防止)
				if (this._findChildComponentByRemoteObject(comp
						.getCorbaObject()) == null) {
					list.add(comp.getCorbaObjectInterface());
					components.add(comp);
				}
			}
			return org.add_members(list.toArray(new SDO[0]));
		} catch (NotAvailable e) {
			e.printStackTrace();
		} catch (InternalError e) {
			e.printStackTrace();
		} catch (InvalidParameter e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * this.componentsからremoteとリモートオブジェクトが一致するコンポーネントを検索します
	 */
	@SuppressWarnings("unchecked")
	CorbaComponent _findChildComponentByRemoteObject(Object remote) {
		EList components = getComponents();
		for (Object c : components) {
			if (!(c instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent local = (CorbaComponent) c;
			if (local.getCorbaObject() != null
					&& local.getCorbaObject().equals(remote)) {
				return local;
			}
		}
		return null;
	}

	@Override
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeComponentR(Component component) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) return false;
			CorbaComponent corbaComponent = (CorbaComponent) component;
			return org.remove_member(corbaComponent.getCorbaObjectInterface()
					.get_sdo_id());
		} catch (NotAvailable e) {
			e.printStackTrace();
		} catch (InternalError e) {
			e.printStackTrace();
		} catch (InvalidParameter e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ComponentPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}

		return result;
	}
	
	@Override
	public boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive) {
		boolean result = false;
		try {
			boolean exist = false;
			for (Object o : this.getConfigurationSets()) {
				ConfigurationSet cs = (ConfigurationSet) o;
				if (cs.getId().equals(configSet.getId())) {
					exist = true;
					break;
				}
			}
			Configuration configuration = getSDOConfiguration();
			_SDOPackage.ConfigurationSet sdoConfigurationSet = 
				SDOUtil.createSdoConfigurationSet(configSet);
			if (!exist) {
				configuration.add_configuration_set(sdoConfigurationSet);
			} else {
				configuration.set_configuration_set_values(sdoConfigurationSet);
			}
			if (isActive){
				configuration.activate_configuration_set(configSet.getId());
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public org.omg.CORBA.Object getCorbaBaseObject() {
		return corbaObject;
	}

	@Override
	public String getComponentId() {
		if (componentId != null) return componentId;
		return "RTC:" + getVenderL() + ":" 
		+ getCategoryL() + ":"
		+ getTypeNameL() + ":"
		+ getVersionL();
	}

	@Override
	public String getCategoryL() {
		return getRTCComponentProfile() == null ? null
				: getRTCComponentProfile().category;
	}

	@Override
	public String getDescriptionL() {
		return getRTCComponentProfile() == null ? null
				: getRTCComponentProfile().description;
	}

	@Override
	public String getInstanceNameL() {
		return getRTCComponentProfile() == null ? null
				: getRTCComponentProfile().instance_name;
	}

	@Override
	public String getTypeNameL() {
		return getRTCComponentProfile() == null ? null
				: getRTCComponentProfile().type_name;
	}

	@Override
	public String getVenderL() {
		return getRTCComponentProfile() == null ? null
				: getRTCComponentProfile().vendor;
	}

	@Override
	public String getVersionL() {
		return getRTCComponentProfile() == null ? null
				: getRTCComponentProfile().version;
	}

	@Override
	public void setCategoryL(String newCategoryL) {
		if (getRTCComponentProfile() == null) setRTCComponentProfile(new ComponentProfile());
		getRTCComponentProfile().category = newCategoryL;
	}

	@Override
	public void setDescriptionL(String newDescriptionL) {
		if (getRTCComponentProfile() == null) setRTCComponentProfile(new ComponentProfile());
		getRTCComponentProfile().description = newDescriptionL;
	}

	@Override
	public void setInstanceNameL(String newInstanceNameL) {
		if (getRTCComponentProfile() == null) setRTCComponentProfile(new ComponentProfile());
		getRTCComponentProfile().instance_name = newInstanceNameL;
	}

	@Override
	public void setTypeNameL(String newTypeNameL) {
		if (getRTCComponentProfile() == null) setRTCComponentProfile(new ComponentProfile());
		getRTCComponentProfile().type_name = newTypeNameL;
	}

	@Override
	public void setVenderL(String newVenderL) {
		if (getRTCComponentProfile() == null) setRTCComponentProfile(new ComponentProfile());
		getRTCComponentProfile().vendor = newVenderL;
	}

	@Override
	public void setVersionL(String newVersionL) {
		if (getRTCComponentProfile() == null) setRTCComponentProfile(new ComponentProfile());
		getRTCComponentProfile().version = newVersionL;
	}


	// Mapping Rule	
	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					CorbaComponentImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) }) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					return remoteObjects[0] instanceof org.omg.CORBA.Object &&
						 ((org.omg.CORBA.Object) remoteObjects[0])
								._is_a(RTObjectHelper.id());
				}

				@Override
				public boolean needsPing() {
					return true;
				}

				@Override
				public Object[] narrow(Object[] remoteObjects) {
					return new Object[] { RTObjectHelper
							.narrow(((org.omg.CORBA.Object) remoteObjects[0])) };
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {}	);

	@SuppressWarnings("unchecked")
	public void synchronizeLocalAttribute(EStructuralFeature reference) {
		
		for (AttributeMapping attibuteMapping : getAttributeMappings()) {
			if (reference != null) {
				if (reference.equals(attibuteMapping.getLocalFeature())) {
					try {
						attibuteMapping.syncronizeLocal(this);
						break;
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
			}else{
				try {
					attibuteMapping.syncronizeLocal(this);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	private static AttributeMapping[] getAttributeMappings() {

		return new AttributeMapping[] {
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getCorbaComponent_RTCComponentProfile(), false) {
					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						try {
							return((CorbaComponent) localObject)
									.getCorbaObjectInterface()
									.get_component_profile();
						} catch (Exception e) {
							return null;
						}
					}
					@Override
					public boolean isEquals(Object value1, Object value2) {
						if (value1 == null) {
							return value2 == null;
						}
						if (!(value1 instanceof ComponentProfile)) {
							return false;
						}
						if (!(value2 instanceof ComponentProfile)) {
							return false;
						}
						ComponentProfile cp1 = (ComponentProfile) value1;
						ComponentProfile cp2 = (ComponentProfile) value2;
						if (eq(cp1.category, cp2.category)
								&& eq(cp1.description, cp2.description)
								&& eq(cp1.instance_name, cp2.instance_name)
								&& eq(cp1.type_name, cp2.type_name)
								&& eq(cp1.vendor, cp2.vendor)
								&& eq(cp1.version, cp2.version) 
								&& eqPorts(cp1.port_profiles, cp2.port_profiles)) {
							return true;
						}
						return false;
					}
					private boolean eqPorts(PortProfile[] p1,
							PortProfile[] p2) {
						if (p1 == null) return p2 == null;
						if (p2 == null) return false;
						if (p1.length != p2.length) return false;
						for (int i = 0; i < p1.length; i++) {
							if (!eq(p1[i].port_ref, p2[i].port_ref)) return false;
							if (!eqConnectors(p1[i].connector_profiles, p2[i].connector_profiles)) return false;
						}
						return true;
					}
					private boolean eqConnectors(
							ConnectorProfile[] p1,
							ConnectorProfile[] p2) {
						if (p1 == null) return p2 == null;
						if (p2 == null) return false;
						if (p1.length != p2.length) return false;
						for (int i = 0; i < p1.length; i++) {
							if (!eq(p1[i].connector_id, p2[i].connector_id)) return false;
						}
						return true;
					}
					private boolean eq(Object s1, Object s2) {
						if (s1 == null) {
							return s2 == null;
						} else {
							return s1.equals(s2);
						}
					}
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getCorbaComponent_SDOConfiguration(), true) {
					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						Object result = null;
						try {
							result = RTObjectHelper.narrow(
									(org.omg.CORBA.Object) remoteObjects[0])
									.get_configuration();
						} catch (Exception e) {
							// void
						}

						return result;
					}
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getCorbaComponent_RTCExecutionContext(), false) {
							@Override
							public Object getRemoteAttributeValue(
									LocalObject localObject,
									Object[] remoteObjects) {
								try {
									RTObject ro = RTObjectHelper.narrow((org.omg.CORBA.Object) remoteObjects[0]);
									RTC.ExecutionContext[] ec = ro.get_participating_contexts();
									if (ec != null && ec.length > 0) return Arrays.asList(ec);
									ec = ro.get_owned_contexts();
									if (ec != null && ec.length > 0) return Arrays.asList(ec);
									return Collections.EMPTY_LIST;
								} catch (Exception e) {
									return Collections.EMPTY_LIST;
								}
							}
					
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getCorbaComponent_ComponentState()) {

					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						CorbaComponent component = (CorbaComponent) localObject;
						if (component.getPrimaryRTCExecutionContext() == null) return ExecutionContext.RTC_CREATED;
						try {
							int state = component.getPrimaryRTCExecutionContext().get_component_state(component.getCorbaObjectInterface()).value();
							if (state == RTC.LifeCycleState.ACTIVE_STATE.value() ||
									state == RTC.LifeCycleState.ERROR_STATE.value() ||
									state == RTC.LifeCycleState.INACTIVE_STATE.value())
								return state;
							else
								return ExecutionContext.RTC_UNKNOWN;
						} catch (Exception e) {
							return ExecutionContext.RTC_UNKNOWN;
						}
					}
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getCorbaComponent_ExecutionContextState()) {
					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						CorbaComponent component = (CorbaComponent) localObject;
						RTC.ExecutionContext ec = component
								.getPrimaryRTCExecutionContext();
						if (ec == null) {
							return ExecutionContext.STATE_UNKNOWN;
						}
						try {
							if (ec.is_running()) {
								return ExecutionContext.STATE_RUNNING;
							} else {
								return ExecutionContext.STATE_STOPPED;
							}
						} catch (Exception e) {
							return ExecutionContext.STATE_UNKNOWN;
						}
					}
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getComponent_ConfigurationSets()) {
					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						List<WrapperObject> result = new ArrayList<WrapperObject>();
						try {
							_SDOPackage.ConfigurationSet[] configs = ((CorbaComponent) localObject)
									.getSDOConfiguration()
									.get_configuration_sets();
							for (_SDOPackage.ConfigurationSet config : configs) {
								result.add(CorbaWrapperFactory.getInstance()
										.createWrapperObject(config));
							}
						} catch (Exception e) {
							// void
						}

						return result;
					}
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getComponent_ActiveConfigurationSet()) {
					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						String result = null;
						try {
							result = ((CorbaComponent) localObject)
									.getSDOConfiguration()
									.get_active_configuration_set().id;
						} catch (Exception e) {
							// void
						}

						return result;
					}

					@SuppressWarnings("unchecked")
					@Override
					public Object convert2LocalValue(LocalObject localObject,
							Object remoteAttributeValue) {
						ConfigurationSet result = null;
						for (Iterator iter = ((CorbaComponent) localObject)
								.getConfigurationSets().iterator(); iter
								.hasNext();) {
							ConfigurationSet element = (ConfigurationSet) iter
									.next();
							if (remoteAttributeValue != null) {
								if (remoteAttributeValue
										.equals(element.getId())) {
									result = element;
									break;
								}
							}
						}

						return result;
					}

					@Override
					public boolean isEquals(Object value1, Object value2) {
						return value1 == value2;
					}
				},
				new AttributeMapping(ComponentPackage.eINSTANCE
						.getCorbaComponent_SDOOrganization(), true) {
					@Override
					public Object getRemoteAttributeValue(
							LocalObject localObject, Object[] remoteObjects) {
						try {
							RTObject ro = RTObjectHelper
									.narrow((org.omg.CORBA.Object) remoteObjects[0]);
							Organization[] orgs = ro.get_owned_organizations();
							if (orgs == null || orgs.length == 0) {
								return new NullSDOOrganization();
							}
							return orgs[0]; // １つ目固定
						} catch (Exception e) {
							return new NullSDOOrganization();
						}
					}
				},
		};
	}
	
	@SuppressWarnings("unchecked")
	public void synchronizeLocalReference() {
		for (ReferenceMapping referenceMapping : getReferenceMappings()) {
			try {
				referenceMapping.syncronizeLocal(this);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}

	public static ReferenceMapping[] getReferenceMappings() {
		return new ReferenceMapping[] {
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_Ports()) {
					
					@SuppressWarnings("unchecked")
					@Override
					protected List getNewRemoteLinkList(
							LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						ComponentProfile componentProfile = component.getRTCComponentProfile();
						if (componentProfile == null) return Collections.EMPTY_LIST;
						RTC.PortProfile[] ports = componentProfile.port_profiles;
						if (ports == null) return Collections.EMPTY_LIST;
						List result = new ArrayList<RTC.PortService>();
						for (RTC.PortProfile portProfile : ports) {
							result.add(portProfile);
						}
						return result;
					}
					@SuppressWarnings("unchecked")
					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List result = new ArrayList<Object[]>();
						Component component = (Component) localObject;
						for (Object object : component.getPorts()) {
							Port port = (Port) object;
							Object[] remoteObjects = getRemoteObjects(port); 
							if (remoteObjects.length == 0) continue;
							result.add(remoteObjects[0]);
						}
						return result;
					}
					@Override
					public boolean isLinkEquals(Object link1, Object link2) {
						if (!(link1 instanceof RTC.PortProfile) ) return false;
						if (!(link2 instanceof RTC.PortProfile) ) return false;
						RTC.PortProfile port1 = (RTC.PortProfile)link1;
						RTC.PortProfile port2 = (RTC.PortProfile)link2;
						boolean result = port1.port_ref.equals(port2.port_ref);
						if (result) {
							// コネクタ情報をここで更新する
							// link1が新しく、link2が古い
							port2.connector_profiles = port1.connector_profiles;
						}
						return result;
					}
					@Override
					protected Object[] getRemoteObjects(LocalObject localObject) {
						if (!(localObject instanceof Port)) return new Object[0];
						Port port = (Port) localObject;
						if (!(port.getSynchronizer() instanceof CorbaPortSynchronizer)) return new Object[0];
						CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port.getSynchronizer();
						return new Object[]{synchronizer.getRTCPortProfile()};
					}					
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_ExecutionContexts()) {
					@SuppressWarnings("unchecked")
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCExecutionContext();
					}

					@SuppressWarnings("unchecked")
					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List<org.omg.CORBA.Object> result = new ArrayList<org.omg.CORBA.Object>();
						for (Object obj : ((CorbaComponent) localObject)
								.getExecutionContexts()) {
							CorbaExecutionContext ec = (CorbaExecutionContext) obj;
							if (ec != null) {
								result.add(ec.getCorbaObject());
							}
						}
						return result;
					}

					@Override
					public void postSynchronizeLocal(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						RTObject ro = component.getCorbaObjectInterface();
						for (Object obj : (EList) component
								.eGet(getLocalFeature())) {
							CorbaExecutionContext cec = (CorbaExecutionContext) obj;
							if (cec == null) {
								continue;
							}
							RTC.ExecutionContext ec = cec
									.getCorbaObjectInterface();
							int handle = ro.get_context_handle(ec);
							// CORBAの場合はcontext_handleをExecutionContext IDとして扱う
							component.setExecutionContext(Integer
									.toString(handle), cec);
						}
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_Components()) {
					@SuppressWarnings("unchecked")
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						List<RTObject> result = new ArrayList<RTObject> ();
						Organization org = component.getSDOOrganization();
						if (org == null) return result;

						try {
							_SDOPackage.SDO[] sdo_list = org.get_members();
							if (sdo_list == null) return result;
							for (SDO sdo : sdo_list) {
								RTObject ro = RTObjectHelper.narrow(sdo);
								if (ro != null) {
									result.add(ro);
								}
							}
						} catch (NotAvailable e) {
							e.printStackTrace();
						} catch (InternalError e) {
							e.printStackTrace();
						}
						
						return result;
					}

					@Override
					public LocalObject loadLocalObjectByRemoteObject(
							LocalObject localObject,
							SynchronizationManager synchronizationManager,
							Object link, Object[] remoteObject) {
						if (localObject.eContainer() instanceof SystemDiagram) {
							// 親がSystemDiagramの場合は、Diagramからローカルを検索
							LocalObject lo = SynchronizationSupport
									.findLocalObjectByRemoteObject(
											remoteObject,
											(SystemDiagram) localObject
													.eContainer());
							if (lo != null) {
								return lo;
							}
						}
						return super.loadLocalObjectByRemoteObject(localObject,
								synchronizationManager, link, remoteObject);
					}
				},
			};
	}

	public String getPath() {
		if (getPathId() == null) return null;
		int index = getPathId().indexOf("/");
		if (index < 0) return getPathId();
		return getPathId().substring(0, index);
	}

	private long lastExecutedTime;
	private static long SYNC_MANUAL_INTERVAL = 1000L;
	
	public void synchronizeManually() {
		if(eContainer() instanceof SystemDiagram) return;
		
		if (System.currentTimeMillis() - lastExecutedTime < SYNC_MANUAL_INTERVAL) {
			return;
		}
		synchronizeLocalAttribute(null);
		synchronizeLocalReference();
		synchronizeChildComponents();
		lastExecutedTime = System.currentTimeMillis();
	}

	@SuppressWarnings("unchecked")
//	@Override
	public Component copy() {
		Component copy = (Component) EcoreUtil.copy(this);
		// ExecutionContextの同期をさせるためクリア(同期時にEC-ID対応付けを設定)
		copy.getExecutionContexts().clear();
		getSynchronizationSupport().getSynchronizationManager().assignSynchonizationSupport(copy);
		copy.synchronizeManually();
		adjustPathId(copy.getAllComponents());
		return copy;
	}

//	@Override
	public boolean isDead() {
		return !SynchronizationSupport.ping(getCorbaObject());
	}



} //CorbaComponentImpl
