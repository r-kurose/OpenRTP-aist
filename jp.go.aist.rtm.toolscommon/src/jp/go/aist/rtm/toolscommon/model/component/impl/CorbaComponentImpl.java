/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.CorbaLogObserver;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.CorbaStatusObserver;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverStore;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaPropertyMap;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
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
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;

import RTC.ComponentProfile;
import RTC.RTObject;
import _SDOPackage.Configuration;
import _SDOPackage.InternalError;
import _SDOPackage.InvalidParameter;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.SDO;

import static jp.go.aist.rtm.toolscommon.model.component.impl.CorbaExecutionContextImpl.RTC_STATUS;
import static jp.go.aist.rtm.toolscommon.util.RTMixin.*;

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
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCComponentProfile <em>RTC Component Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCExecutionContexts <em>RTC Execution Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCParticipationContexts <em>RTC Participation Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getSDOConfiguration <em>SDO Configuration</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getSDOOrganization <em>SDO Organization</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getRTCRTObjects <em>RTCRT Objects</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getIor <em>Ior</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getStatusObserver <em>Status Observer</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl#getLogObserver <em>Log Observer</em>}</li>
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
	 * The cached value of the '{@link #getRTCExecutionContexts() <em>RTC Execution Contexts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCExecutionContexts()
	 * @generated NOT
	 * @ordered
	 */
	protected EList<RTC.ExecutionContext> rTCExecutionContexts;

	/**
	 * The cached value of the '{@link #getRTCParticipationContexts() <em>RTC Participation Contexts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCParticipationContexts()
	 * @generated NOT
	 * @ordered
	 */
	protected EList<RTC.ExecutionContext> rTCParticipationContexts;

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
	 * The cached value of the '{@link #getRTCRTObjects() <em>RTCRT Objects</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRTCRTObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<RTObject> rTCRTObjects;

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
	 * The cached value of the '{@link #getStatusObserver() <em>Status Observer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatusObserver()
	 * @generated
	 * @ordered
	 */
	protected CorbaStatusObserver statusObserver;

	/**
	 * The cached value of the '{@link #getLogObserver() <em>Log Observer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogObserver()
	 * @generated
	 * @ordered
	 */
	protected CorbaLogObserver logObserver;

	protected IPropertyMap properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaComponentImpl() {
		super();
		this.properties = new CorbaPropertyMap() {
			@Override
			public _SDOPackage.NameValue[] getNameValues() {
				if (getRTCComponentProfile().properties == null) {
					return new _SDOPackage.NameValue[0];
				}
				return getRTCComponentProfile().properties;
			}

			@Override
			public void setNameValues(_SDOPackage.NameValue[] nvs) {
				getRTCComponentProfile().properties = nvs;
			}
		};
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.omg.CORBA.Object getCorbaObject() {
		return corbaObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public int getExecutionContextState() {
		return executionContextState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public int getComponentState() {
		return componentState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComponentState(int newComponentState) {
		int oldComponentState = componentState;
		componentState = newComponentState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE, oldComponentState, componentState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Configuration getSDOConfiguration() {
		try {
			RTC.RTObject ro = getCorbaObjectInterface();
			_SDOPackage.Configuration conf = ro.get_configuration();
			setSDOConfiguration(conf);
		} catch (Exception e) {
			// void
		}
		return sDOConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public ComponentProfile getRTCComponentProfile() {
		return rTCComponentProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public EList<RTC.ExecutionContext> getRTCExecutionContexts() {
		if (rTCExecutionContexts == null) {
			rTCExecutionContexts = new EDataTypeUniqueEList<RTC.ExecutionContext>(RTC.ExecutionContext.class, this, ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS);
		}
		return rTCExecutionContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<RTC.ExecutionContext> getRTCParticipationContexts() {
		if (rTCParticipationContexts == null) {
			rTCParticipationContexts = new EDataTypeEList<RTC.ExecutionContext>(RTC.ExecutionContext.class, this, ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS);
		}
		return rTCParticipationContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Organization getSDOOrganization() {
		try {
			RTC.RTObject ro = getCorbaObjectInterface();
			Organization[] orgs = ro.get_owned_organizations();
			if (orgs == null || orgs.length == 0) {
				setSDOOrganization(new NullSDOOrganization());
			} else {
				setSDOOrganization(orgs[0]); // １つ目固定
			}
		} catch (Exception e) {
			setSDOOrganization(new NullSDOOrganization());
		}
		return sDOOrganization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public EList<RTObject> getRTCRTObjects() {
		if (rTCRTObjects == null) {
			rTCRTObjects = new EDataTypeEList<RTObject>(RTObject.class, this, ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS);
		}
		return rTCRTObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIor() {
		return ior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIor(String newIor) {
		String oldIor = ior;
		ior = newIor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__IOR, oldIor, ior));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public CorbaStatusObserver getStatusObserver() {
		return CorbaObserverStore.eINSTANCE
				.findStatusObserver(getCorbaObjectInterface());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaStatusObserver basicGetStatusObserver() {
		return statusObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setStatusObserver(CorbaStatusObserver newStatusObserver) {
		CorbaObserverStore.eINSTANCE.registStatusObserver(
				getCorbaObjectInterface(), newStatusObserver);
		//
		CorbaStatusObserver oldStatusObserver = statusObserver;
		statusObserver = newStatusObserver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER, oldStatusObserver, statusObserver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CorbaLogObserver getLogObserver() {
		return CorbaObserverStore.eINSTANCE
				.findLogObserver(getCorbaObjectInterface());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaLogObserver basicGetLogObserver() {
		return logObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setLogObserver(CorbaLogObserver newLogObserver) {
		CorbaObserverStore.eINSTANCE.registLogObserver(
				getCorbaObjectInterface(), newLogObserver);
		//
		CorbaLogObserver oldLogObserver = logObserver;
		logObserver = newLogObserver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER, oldLogObserver, logObserver));
	}

	@Override
	public RTC.RTObject getCorbaObjectInterface() {
		return RTC.RTObjectHelper.narrow(getCorbaObject());
	}

	@Override
	public boolean hasComponentAction() {
		return true;
	}

	@Override
	public RTC.ExecutionContext getPrimaryRTCExecutionContext() {
		if (getRTCExecutionContexts().size() > 0)
			return getRTCExecutionContexts().get(0);
		return null;
	}

	@Override
	public ContextHandler getExecutionContextHandler() {
		if (executionContextHandler == null) {
			setExecutionContextHandler(new CorbaContextHandlerImpl());
		}
		return executionContextHandler;
	}

	@Override
	public ContextHandler getParticipationContextHandler() {
		if (participationContextHandler == null) {
			setParticipationContextHandler(new CorbaContextHandlerImpl());
		}
		return participationContextHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int startR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext().start().value();
		}
		return RETURNCODE_ERROR;
	}

	@Override
	public void startAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			ec.start();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int stopR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext().stop().value();
		}
		return RETURNCODE_ERROR;
	}

	@Override
	public void stopAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			ec.stop();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int activateR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext().activate_component(
					getCorbaObjectInterface()).value();
		}
		return RETURNCODE_ERROR;
	}

	@Override
	public void activateAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			ec.activate_component(getCorbaObjectInterface());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int deactivateR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext().deactivate_component(
					getCorbaObjectInterface()).value();
		}
		return RETURNCODE_ERROR;
	}

	@Override
	public void deactivateAll() {
		for (RTC.ExecutionContext ec : getRTCExecutionContexts()) {
			ec.deactivate_component(getCorbaObjectInterface());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int resetR() {
		if (getPrimaryRTCExecutionContext() != null) {
			return getPrimaryRTCExecutionContext().reset_component(
					getCorbaObjectInterface()).value();
		}
		return RETURNCODE_ERROR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int finalizeR() {
		return getCorbaObjectInterface()._finalize().value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int exitR() {
		return getCorbaObjectInterface().exit().value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				return getCorbaObject();
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				return new Integer(getExecutionContextState());
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				return new Integer(getComponentState());
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				return getRTCComponentProfile();
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				return getRTCExecutionContexts();
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				return getRTCParticipationContexts();
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				return getSDOConfiguration();
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				return getSDOOrganization();
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				return getRTCRTObjects();
			case ComponentPackage.CORBA_COMPONENT__IOR:
				return getIor();
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				if (resolve) return getStatusObserver();
				return basicGetStatusObserver();
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				if (resolve) return getLogObserver();
				return basicGetLogObserver();
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
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				setCorbaObject((org.omg.CORBA.Object)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				setExecutionContextState(((Integer)newValue).intValue());
				return;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				setComponentState(((Integer)newValue).intValue());
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				setRTCComponentProfile((ComponentProfile)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				getRTCExecutionContexts().clear();
				getRTCExecutionContexts().addAll((Collection<? extends RTC.ExecutionContext>)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				getRTCParticipationContexts().clear();
				getRTCParticipationContexts().addAll((Collection<? extends RTC.ExecutionContext>)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				setSDOConfiguration((Configuration)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				setSDOOrganization((Organization)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				getRTCRTObjects().clear();
				getRTCRTObjects().addAll((Collection<? extends RTObject>)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				setIor((String)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				setStatusObserver((CorbaStatusObserver)newValue);
				return;
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				setLogObserver((CorbaLogObserver)newValue);
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
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				setCorbaObject(CORBA_OBJECT_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				setExecutionContextState(EXECUTION_CONTEXT_STATE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				setComponentState(COMPONENT_STATE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				setRTCComponentProfile(RTC_COMPONENT_PROFILE_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				getRTCExecutionContexts().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				getRTCParticipationContexts().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				setSDOConfiguration(SDO_CONFIGURATION_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				setSDOOrganization(SDO_ORGANIZATION_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				getRTCRTObjects().clear();
				return;
			case ComponentPackage.CORBA_COMPONENT__IOR:
				setIor(IOR_EDEFAULT);
				return;
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				setStatusObserver((CorbaStatusObserver)null);
				return;
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				setLogObserver((CorbaLogObserver)null);
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
			case ComponentPackage.CORBA_COMPONENT__CORBA_OBJECT:
				return CORBA_OBJECT_EDEFAULT == null ? corbaObject != null : !CORBA_OBJECT_EDEFAULT.equals(corbaObject);
			case ComponentPackage.CORBA_COMPONENT__EXECUTION_CONTEXT_STATE:
				return executionContextState != EXECUTION_CONTEXT_STATE_EDEFAULT;
			case ComponentPackage.CORBA_COMPONENT__COMPONENT_STATE:
				return componentState != COMPONENT_STATE_EDEFAULT;
			case ComponentPackage.CORBA_COMPONENT__RTC_COMPONENT_PROFILE:
				return RTC_COMPONENT_PROFILE_EDEFAULT == null ? rTCComponentProfile != null : !RTC_COMPONENT_PROFILE_EDEFAULT.equals(rTCComponentProfile);
			case ComponentPackage.CORBA_COMPONENT__RTC_EXECUTION_CONTEXTS:
				return rTCExecutionContexts != null && !rTCExecutionContexts.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__RTC_PARTICIPATION_CONTEXTS:
				return rTCParticipationContexts != null && !rTCParticipationContexts.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__SDO_CONFIGURATION:
				return SDO_CONFIGURATION_EDEFAULT == null ? sDOConfiguration != null : !SDO_CONFIGURATION_EDEFAULT.equals(sDOConfiguration);
			case ComponentPackage.CORBA_COMPONENT__SDO_ORGANIZATION:
				return SDO_ORGANIZATION_EDEFAULT == null ? sDOOrganization != null : !SDO_ORGANIZATION_EDEFAULT.equals(sDOOrganization);
			case ComponentPackage.CORBA_COMPONENT__RTCRT_OBJECTS:
				return rTCRTObjects != null && !rTCRTObjects.isEmpty();
			case ComponentPackage.CORBA_COMPONENT__IOR:
				return IOR_EDEFAULT == null ? ior != null : !IOR_EDEFAULT.equals(ior);
			case ComponentPackage.CORBA_COMPONENT__STATUS_OBSERVER:
				return statusObserver != null;
			case ComponentPackage.CORBA_COMPONENT__LOG_OBSERVER:
				return logObserver != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
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
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
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
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (corbaObject: ");
		result.append(corbaObject);
		result.append(", executionContextState: ");
		result.append(executionContextState);
		result.append(", componentState: ");
		result.append(componentState);
		result.append(", rTCComponentProfile: ");
		result.append(rTCComponentProfile);
		result.append(", rTCExecutionContexts: ");
		result.append(rTCExecutionContexts);
		result.append(", rTCParticipationContexts: ");
		result.append(rTCParticipationContexts);
		result.append(", sDOConfiguration: ");
		result.append(sDOConfiguration);
		result.append(", sDOOrganization: ");
		result.append(sDOOrganization);
		result.append(", rTCRTObjects: ");
		result.append(rTCRTObjects);
		result.append(", ior: ");
		result.append(ior);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateConfigurationSetListR(List localConfigurationSets,
			ConfigurationSet localActiveConfigurationSet,
			List originalConfigurationSets) {

		try {
			_SDOPackage.Configuration configuration = getCorbaObjectInterface()
					.get_configuration();
			for (Object o : localConfigurationSets) {
				ConfigurationSet local = (ConfigurationSet) o;
				ConfigurationSet updated = null;
				for (Object o2 : originalConfigurationSets) {
					ConfigurationSet original = (ConfigurationSet) o2;
					if (local.getId().equals(original.getId())) {
						updated = ComponentFactory.eINSTANCE
								.createConfigurationSet();
						updated.setId(local.getId());
						for (int i = 0; i < local.getConfigurationData().size(); i++) {
							NameValue lnv = (NameValue) local
									.getConfigurationData().get(i);
							NameValue onv = (NameValue) original
									.getConfigurationData().get(i);
							if (!lnv.getName().equals(onv.getName())) {
								updated.getConfigurationData().add(lnv);
							} else if (!lnv.getValueAsString().equals(
									onv.getValueAsString())) {
								updated.getConfigurationData().add(lnv);
							}
						}
						break;
					}
				}
				if (updated != null
						&& !updated.getConfigurationData().isEmpty()) {
					boolean result = configuration
							.set_configuration_set_values(SDOUtil
									.createSdoConfigurationSet(updated));
					if (!result) {
						return false;
					}
				}
			}
			if (localActiveConfigurationSet != null) {
				boolean result = configuration
						.activate_configuration_set(localActiveConfigurationSet
								.getId());
				if (!result) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean addComponentsR(List<Component> componentList) {
		try {
			Organization org = getSDOOrganization();
			if (org == null) return false;

			List<SDO> list = new ArrayList<SDO>();
			EList<Component> components = getComponents();
			for (Component c : componentList) {
				if (!(c instanceof CorbaComponent)) {
					continue;
				}
				CorbaComponent comp = (CorbaComponent) c;
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
	CorbaComponent _findChildComponentByRemoteObject(Object remote) {
		EList<Component> components = getComponents();
		for (Component c : components) {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
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
	@Override
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

	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	@Override
	public String removeProperty(String key) {
		return properties.removeProperty(key);
	}

	@Override
	public EList<String> getPropertyKeys() {
		return properties.getPropertyKeys();
	}

	@Override
	public IPropertyMap getPropertyMap() {
		return properties;
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
					return remoteObjects[0] instanceof org.omg.CORBA.Object
							&& ((org.omg.CORBA.Object) remoteObjects[0])
									._is_a(RTC.RTObjectHelper.id());
				}

				@Override
				public boolean needsPing() {
					return true;
				}

				@Override
				public Object[] narrow(Object[] remoteObjects) {
					return new Object[] { RTC.RTObjectHelper
							.narrow(((org.omg.CORBA.Object) remoteObjects[0])) };
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});

	@Override
	public void synchronizeRemoteAttribute(EStructuralFeature reference) {
		ComponentPackage pkg = ComponentPackage.eINSTANCE;
		RTC.RTObject ro = getCorbaObjectInterface();
		//
		if (pkg.getCorbaComponent_RTCComponentProfile().equals(reference)
				|| reference == null) {
			synchronizeRemote_RTCComponentProfile();
		}
		//
		if (pkg.getCorbaComponent_RTCExecutionContexts().equals(reference)
				|| reference == null) {
			synchronizeRemote_RTCExecutionContexts();
			// owned context
			RTC.ExecutionContext[] oec = CorbaObjectStore.eINSTANCE
					.findOwnedContexts(ro);
			if (oec != null) {
				for (RTC.ExecutionContext ec : oec) {
					// ec profile
					synchronizeRemote_EC_ECProfile(ec);
					// ec state
					synchronizeRemote_EC_ECState(ec);
					// component state
					synchronizeRemote_EC_ComponentState(ec);
				}
			}
			// participating context
			RTC.ExecutionContext[] pec = CorbaObjectStore.eINSTANCE
					.findParticipatingContexts(ro);
			if (pec != null) {
				for (RTC.ExecutionContext ec : pec) {
					// component state
					synchronizeRemote_EC_ComponentState(ec);
				}
			}
		}
		//
		if (pkg.getComponent_ConfigurationSets().equals(reference)
				|| reference == null) {
			synchronizeRemote_ConfigurationSets();
		}
		//
		if (pkg.getComponent_ActiveConfigurationSet().equals(reference)
				|| reference == null) {
			synchronizeRemote_ActiveConfigurationSet();
		}
		//
		if (pkg.getCorbaComponent_RTCRTObjects().equals(reference)
				|| reference == null) {
			synchronizeRemote_RTCRTObjects();
		}
	}

	@Override
	public void synchronizeLocalAttribute(EStructuralFeature reference) {
		ComponentPackage pkg = ComponentPackage.eINSTANCE;
		if (pkg.getCorbaComponent_RTCComponentProfile().equals(reference)
				|| reference == null) {
			synchronizeLocal_RTCComponentProfile();
		}
		//
		if (pkg.getCorbaComponent_RTCExecutionContexts().equals(reference)
				|| reference == null) {
			synchronizeLocal_RTCExecutionContexts();
		}
		//
		if (pkg.getComponent_ConfigurationSets().equals(reference)
				|| reference == null) {
			synchronizeLocal_ConfigurationSets();
		}
		//
		if (pkg.getComponent_ActiveConfigurationSet().equals(reference)
				|| reference == null) {
			synchronizeLocal_ActiveConfigurationSet();
		}
		//
		if (pkg.getCorbaComponent_RTCRTObjects().equals(reference)
				|| reference == null) {
			synchronizeLocal_RTCRTObjects();
		}
	}

	/** RTC.ComponentProfileの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCComponentProfile() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCComponentProfile(ro);
	}

	public static void synchronizeRemote_RTCComponentProfile(RTC.RTObject ro) {
		try {
			RTC.ComponentProfile prof = ro.get_component_profile();
			CorbaObjectStore.eINSTANCE.registRTCProfile(ro, prof);
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeRTCProfile(ro);
		}
	}

	/** RTC.PortProfileの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCPortProfile(String name) {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCPortProfile(ro, name);
	}

	public static void synchronizeRemote_RTCPortProfile(RTC.RTObject ro,
			String name) {
		RTC.PortProfile prof = CorbaObjectStore.eINSTANCE.findRTCPortProfile(
				ro, name);
		if (prof != null) {
			try {
				RTC.PortProfile pprof = prof.port_ref.get_port_profile();
				CorbaObjectStore.eINSTANCE
						.registRTCPortProfile(ro, name, pprof);
			} catch (Exception e) {
				// void
			}
		}
	}

	/** RTC.ComponentProfileの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_RTCComponentProfile() {
		RTC.RTObject ro = getCorbaObjectInterface();
		RTC.ComponentProfile prof = CorbaObjectStore.eINSTANCE
				.findRTCProfile(ro);
		if (!eql(getRTCComponentProfile(), prof)) {
			setRTCComponentProfile(prof);
		}
	}

	/** RTC.ExecutionContextの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCExecutionContexts() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCExecutionContexts(ro);
	}

	public static void synchronizeRemote_RTCExecutionContexts(RTC.RTObject ro) {
		boolean update = false;
		try {
			// owned context
			RTC.ExecutionContext[] oec = ro.get_owned_contexts();
			RTC.ExecutionContext[] oecOld = CorbaObjectStore.eINSTANCE
					.findOwnedContexts(ro);
			if (!eql(oec, oecOld)) {
				CorbaObjectStore.eINSTANCE.registOwnedContexts(ro, oec);
				update = true;
			}
			// participating context
			RTC.ExecutionContext[] pec = ro.get_participating_contexts();
			RTC.ExecutionContext[] pecOld = CorbaObjectStore.eINSTANCE
					.findParticipatingContexts(ro);
			if (!eql(pec, pecOld)) {
				CorbaObjectStore.eINSTANCE.registParticipatingContexts(ro, pec);
				update = true;
			}
			//
			if (update) {
				CorbaObjectStore.eINSTANCE.clearContext(ro);
				for (RTC.ExecutionContext ec : oec) {
					int handle = ro.get_context_handle(ec);
					CorbaObjectStore.eINSTANCE.registContext(ro, Integer
							.toString(handle), ec);
				}
				for (RTC.ExecutionContext ec : pec) {
					int handle = ro.get_context_handle(ec);
					CorbaObjectStore.eINSTANCE.registContext(ro, Integer
							.toString(handle), ec);
				}
			}
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeOwnedContexts(ro);
			CorbaObjectStore.eINSTANCE.removeParticipatingContexts(ro);
			CorbaObjectStore.eINSTANCE.clearContext(ro);
		}
	}

	/** RTC.ExecutionContextの同期(comp_state) (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_EC_ComponentState(RTC.ExecutionContext ec) {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_EC_ComponentState(ro, ec);
	}

	public static void synchronizeRemote_EC_ComponentState(RTC.RTObject ro,
			RTC.ExecutionContext ec) {
		try {
			RTC.LifeCycleState state = ec.get_component_state(ro);
			int stateValue = RTC_STATUS(state);
			CorbaObjectStore.eINSTANCE.registComponentState(ec, ro, stateValue);
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeComponentStateMap(ec);
		}
	}

	/** RTC.ExecutionContextの同期(ec_state) (CORBA=>オブジェクトDB) */
	public static void synchronizeRemote_EC_ECState(RTC.ExecutionContext ec) {
		try {
			int ecStateValue = ExecutionContext.STATE_UNKNOWN;
			if (ec.is_running()) {
				ecStateValue = ExecutionContext.STATE_RUNNING;
			} else {
				ecStateValue = ExecutionContext.STATE_STOPPED;
			}
			CorbaObjectStore.eINSTANCE.registECState(ec, ecStateValue);
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeECState(ec);
		}
	}

	/** RTC.ExecutionContextの同期(ec_profile) (CORBA=>オブジェクトDB) */
	public static void synchronizeRemote_EC_ECProfile(RTC.ExecutionContext ec) {
		try {
			RTC.ExecutionContextProfile prof;
			if (ec._is_a(RTC.ExecutionContextServiceHelper.id())) {
				RTC.ExecutionContextService ecs = RTC.ExecutionContextServiceHelper
						.narrow(ec);
				prof = ecs.get_profile();
			} else {
				prof = new RTC.ExecutionContextProfile();
				prof.rate = ec.get_rate();
				prof.kind = ec.get_kind();
			}
			CorbaObjectStore.eINSTANCE.registECProfile(ec, prof);
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeECProfile(ec);
		}
	}

	/** RTC.ExecutionContextの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_RTCExecutionContexts() {
		RTC.RTObject ro = getCorbaObjectInterface();
		// owned context
		RTC.ExecutionContext[] oec = CorbaObjectStore.eINSTANCE
				.findOwnedContexts(ro);
		if (oec == null) {
			getRTCExecutionContexts().clear();
		} else {
			if (!eql(getRTCExecutionContexts().toArray(
					new RTC.ExecutionContext[0]), oec)) {
				getRTCExecutionContexts().clear();
				for (RTC.ExecutionContext ec : oec) {
					getRTCExecutionContexts().add(ec);
				}
			}
			for (RTC.ExecutionContext ec : oec) {
				if (!eql(ec, getPrimaryRTCExecutionContext())) {
					continue;
				}
				Integer stateValue = CorbaObjectStore.eINSTANCE
						.findComponentState(ec, ro);
				Integer ecStateValue = CorbaObjectStore.eINSTANCE
						.findECState(ec);
				if (stateValue != null && stateValue != getComponentState()) {
					setComponentState(stateValue);
				}
				if (ecStateValue != null
						&& ecStateValue != getExecutionContextState()) {
					setExecutionContextState(ecStateValue);
				}
			}
		}
		// participating context
		RTC.ExecutionContext[] pec = CorbaObjectStore.eINSTANCE
				.findParticipatingContexts(ro);
		if (pec == null) {
			getRTCParticipationContexts().clear();
		} else {
			if (!eql(getRTCParticipationContexts().toArray(
					new RTC.ExecutionContext[0]), pec)) {
				getRTCParticipationContexts().clear();
				for (RTC.ExecutionContext ec : pec) {
					getRTCParticipationContexts().add(ec);
				}
			}
		}
	}

	/** SDO.ConfigurationSetの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_ConfigurationSets() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_ConfigurationSets(ro);
	}

	public static void synchronizeRemote_ConfigurationSets(RTC.RTObject ro) {
		try {
			_SDOPackage.Configuration conf = ro.get_configuration();
			_SDOPackage.ConfigurationSet[] cs = conf.get_configuration_sets();
			CorbaObjectStore.eINSTANCE.registConfigSet(ro, cs);
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeConfigSet(ro);
		}
	}

	/** SDO.ConfigurationSetの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_ConfigurationSets() {
		RTC.RTObject ro = getCorbaObjectInterface();
		boolean update = false;
		_SDOPackage.ConfigurationSet[] cs = CorbaObjectStore.eINSTANCE
				.findConfigSet(ro);
		if (cs == null) {
			getConfigurationSets().clear();
			return;
		}
		if (cs.length != getConfigurationSets().size()) {
			update = true;
		} else {
			for (int i = 0; i < cs.length; i++) {
				if (!(getConfigurationSets().get(i) instanceof CorbaConfigurationSet)) {
					update = true;
					break;
				}
				CorbaConfigurationSet ccs = (CorbaConfigurationSet) getConfigurationSets()
						.get(i);
				if (!eql(cs[i], ccs.getSDOConfigurationSet())) {
					update = true;
					break;
				}
			}
		}
		if (update) {
			getConfigurationSets().clear();
			for (_SDOPackage.ConfigurationSet config : cs) {
				CorbaConfigurationSet ccs = (CorbaConfigurationSet) CorbaWrapperFactory
						.getInstance().createWrapperObject(config);
				getConfigurationSets().add(ccs);
			}
		}
	}

	/** SDO.ConfigurationSet(active)の同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_ActiveConfigurationSet() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_ActiveConfigurationSet(ro);
	}

	public static void synchronizeRemote_ActiveConfigurationSet(RTC.RTObject ro) {
		try {
			_SDOPackage.Configuration conf = ro.get_configuration();
			_SDOPackage.ConfigurationSet cs = conf
					.get_active_configuration_set();
			CorbaObjectStore.eINSTANCE.registActiveConfigSet(ro, cs);
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeActiveConfigSet(ro);
		}
	}

	/** SDO.ConfigurationSet(active)の同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_ActiveConfigurationSet() {
		RTC.RTObject ro = getCorbaObjectInterface();
		boolean update = false;
		_SDOPackage.ConfigurationSet cs = CorbaObjectStore.eINSTANCE
				.findActiveConfigSet(ro);
		if (cs == null) {
			setActiveConfigurationSet(null);
			return;
		}
		if (!(getActiveConfigurationSet() instanceof CorbaConfigurationSet)) {
			update = true;
		} else {
			CorbaConfigurationSet ccs = (CorbaConfigurationSet) getActiveConfigurationSet();
			if (ccs == null || !eql(ccs.getSDOConfigurationSet(), cs)) {
				update = true;
			}
		}
		if (update) {
			boolean found = false;
			for (ConfigurationSet c : getConfigurationSets()) {
				if (eql(c.getId(), cs.id)) {
					setActiveConfigurationSet(c);
					found = true;
					break;
				}
			}
			if (!found) {
				setActiveConfigurationSet(null);
			}
		}
	}

	/** RTC.RTObjectメンバの同期 (CORBA=>オブジェクトDB) */
	public void synchronizeRemote_RTCRTObjects() {
		RTC.RTObject ro = getCorbaObjectInterface();
		synchronizeRemote_RTCRTObjects(ro);
	}

	public static void synchronizeRemote_RTCRTObjects(RTC.RTObject ro) {
		List<RTC.RTObject> list = CorbaObjectStore.eINSTANCE
				.getCompositeMemberList(ro);
		try {
			Organization[] orgs = ro.get_owned_organizations();
			if (orgs.length == 0) {
				return;
			}
			_SDOPackage.SDO[] sdo_list = orgs[0].get_members();
			if (sdo_list == null) {
				return;
			}
			list.clear();
			for (SDO sdo : sdo_list) {
				RTC.RTObject r = RTC.RTObjectHelper.narrow(sdo);
				if (r != null) {
					list.add(r);
				}
			}
		} catch (Exception e) {
			CorbaObjectStore.eINSTANCE.removeCompositeMemberList(ro);
		}
	}

	/** RTC.RTObjectメンバの同期 (オブジェクトDB=>モデル) */
	public void synchronizeLocal_RTCRTObjects() {
		RTC.RTObject ro = getCorbaObjectInterface();
		List<RTC.RTObject> list = CorbaObjectStore.eINSTANCE
				.getCompositeMemberList(ro);
		boolean update = false;
		List<RTC.RTObject> curr = getRTCRTObjects();
		if (curr.size() != list.size()) {
			update = true;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (!eql(list.get(i), curr.get(i))) {
					update = true;
					break;
				}
			}
		}
		if (update) {
			getRTCRTObjects().clear();
			getRTCRTObjects().addAll(list);
		}
	}

	@Override
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
					protected List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						ComponentProfile componentProfile = component
								.getRTCComponentProfile();
						if (componentProfile == null)
							return Collections.EMPTY_LIST;
						RTC.PortProfile[] ports = componentProfile.port_profiles;
						if (ports == null)
							return Collections.EMPTY_LIST;
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
						for (Port port : component.getPorts()) {
							Object[] remoteObjects = getRemoteObjects(port);
							if (remoteObjects.length == 0)
								continue;
							result.add(remoteObjects[0]);
						}
						return result;
					}

					@Override
					public boolean isLinkEquals(Object link1, Object link2) {
						if (!(link1 instanceof RTC.PortProfile))
							return false;
						if (!(link2 instanceof RTC.PortProfile))
							return false;
						RTC.PortProfile port1 = (RTC.PortProfile) link1;
						RTC.PortProfile port2 = (RTC.PortProfile) link2;
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
						if (!(localObject instanceof Port))
							return new Object[0];
						Port port = (Port) localObject;
						if (!(port.getSynchronizer() instanceof CorbaPortSynchronizer))
							return new Object[0];
						CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port
								.getSynchronizer();
						return new Object[] { synchronizer.getRTCPortProfile() };
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_ExecutionContexts()) {
					@SuppressWarnings("unchecked")
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCExecutionContexts();
					}

					@SuppressWarnings("unchecked")
					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List<org.omg.CORBA.Object> result = new ArrayList<org.omg.CORBA.Object>();
						CorbaComponent component = (CorbaComponent) localObject;
						for (Object obj : component.getExecutionContexts()) {
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
						component.getExecutionContextHandler().sync();
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_ParticipationContexts()) {
					@SuppressWarnings("unchecked")
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCParticipationContexts();
					}

					@SuppressWarnings("unchecked")
					@Override
					public List getOldRemoteLinkList(LocalObject localObject) {
						List<org.omg.CORBA.Object> result = new ArrayList<org.omg.CORBA.Object>();
						CorbaComponent component = (CorbaComponent) localObject;
						for (Object obj : component.getParticipationContexts()) {
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
						component.getParticipationContextHandler().sync();
					}
				},
				new ManyReferenceMapping(ComponentPackage.eINSTANCE
						.getComponent_Components()) {
					@SuppressWarnings("unchecked")
					@Override
					public List getNewRemoteLinkList(LocalObject localObject) {
						CorbaComponent component = (CorbaComponent) localObject;
						return component.getRTCRTObjects();
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
				}, };
	}

	@Override
	public String getPath() {
		if (getPathId() == null)
			return null;
		int index = getPathId().indexOf("/");
		if (index < 0)
			return getPathId();
		return getPathId().substring(0, index);
	}

	private long lastExecutedTime;
	private static long SYNC_MANUAL_INTERVAL = 1000L;

	@Override
	public void synchronizeManually() {
		if (eContainer() instanceof SystemDiagram)
			return;
		if (System.currentTimeMillis() - lastExecutedTime < SYNC_MANUAL_INTERVAL) {
			return;
		}
		//
		synchronizeRemoteAttribute(null);
		synchronizeRemoteChildComponents();
		//
		synchronizeLocalAttribute(null);
		synchronizeLocalReference();
		synchronizeChildComponents();
		lastExecutedTime = System.currentTimeMillis();
	}

	static _SDOPackage.ServiceProfile dummyServiceProfile;
	Boolean supportCorbaObserver = null;

	@Override
	public boolean supportedCorbaObserver() {
		// OpenRTM-aistバージョンチェック
		if (dummyServiceProfile == null) {
			dummyServiceProfile = new _SDOPackage.ServiceProfile();
			dummyServiceProfile.id = "";
			dummyServiceProfile.interface_type = "";
			dummyServiceProfile.properties = new _SDOPackage.NameValue[0];
		}
		if (supportCorbaObserver == null) {
			boolean result = true;
			try {
				result = !getSDOConfiguration().add_service_profile(
						dummyServiceProfile);
			} catch (Exception e) {
				result = false;
			}
			supportCorbaObserver = Boolean.valueOf(result);
		}
		return supportCorbaObserver.booleanValue();
	}

	@Override
	public Component copy() {
		Component copy = (Component) EcoreUtil.copy(this);
		getSynchronizationSupport().getSynchronizationManager()
				.assignSynchonizationSupport(copy);
		copy.synchronizeManually();
		adjustPathId(copy.getAllComponents());
		return copy;
	}

	@Override
	public boolean isDead() {
		return !SynchronizationSupport.ping(getCorbaObject());
	}

} // CorbaComponentImpl
