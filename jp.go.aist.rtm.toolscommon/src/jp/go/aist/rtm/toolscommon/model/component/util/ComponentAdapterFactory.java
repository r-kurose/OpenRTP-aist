/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentAdapterFactory.java,v 1.9 2008/03/06 04:01:49 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.*;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage
 * @generated
 */
public class ComponentAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ComponentPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentSwitch<Adapter> modelSwitch =
		new ComponentSwitch<Adapter>() {
			@Override
			public Adapter caseSystemDiagram(SystemDiagram object) {
				return createSystemDiagramAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter caseComponentSpecification(ComponentSpecification object) {
				return createComponentSpecificationAdapter();
			}
			@Override
			public Adapter caseExecutionContext(ExecutionContext object) {
				return createExecutionContextAdapter();
			}
			@Override
			public Adapter caseContextHandler(ContextHandler object) {
				return createContextHandlerAdapter();
			}
			@Override
			public Adapter caseConfigurationSet(ConfigurationSet object) {
				return createConfigurationSetAdapter();
			}
			@Override
			public Adapter caseNameValue(NameValue object) {
				return createNameValueAdapter();
			}
			@Override
			public Adapter casePort(Port object) {
				return createPortAdapter();
			}
			@Override
			public Adapter caseInPort(InPort object) {
				return createInPortAdapter();
			}
			@Override
			public Adapter caseOutPort(OutPort object) {
				return createOutPortAdapter();
			}
			@Override
			public Adapter caseServicePort(ServicePort object) {
				return createServicePortAdapter();
			}
			@Override
			public Adapter casePortSynchronizer(PortSynchronizer object) {
				return createPortSynchronizerAdapter();
			}
			@Override
			public Adapter casePortConnector(PortConnector object) {
				return createPortConnectorAdapter();
			}
			@Override
			public Adapter caseConnectorProfile(ConnectorProfile object) {
				return createConnectorProfileAdapter();
			}
			@Override
			public Adapter caseEIntegerObjectToPointMapEntry(Map.Entry<Integer, Point> object) {
				return createEIntegerObjectToPointMapEntryAdapter();
			}
			@Override
			public Adapter caseCorbaComponent(CorbaComponent object) {
				return createCorbaComponentAdapter();
			}
			@Override
			public Adapter caseCorbaPortSynchronizer(CorbaPortSynchronizer object) {
				return createCorbaPortSynchronizerAdapter();
			}
			@Override
			public Adapter caseCorbaConnectorProfile(CorbaConnectorProfile object) {
				return createCorbaConnectorProfileAdapter();
			}
			@Override
			public Adapter caseCorbaConfigurationSet(CorbaConfigurationSet object) {
				return createCorbaConfigurationSetAdapter();
			}
			@Override
			public Adapter caseCorbaExecutionContext(CorbaExecutionContext object) {
				return createCorbaExecutionContextAdapter();
			}
			@Override
			public Adapter caseCorbaContextHandler(CorbaContextHandler object) {
				return createCorbaContextHandlerAdapter();
			}
			@Override
			public Adapter caseIAdaptable(IAdaptable object) {
				return createIAdaptableAdapter();
			}
			@Override
			public Adapter caseModelElement(ModelElement object) {
				return createModelElementAdapter();
			}
			@Override
			public Adapter caseLocalObject(LocalObject object) {
				return createLocalObjectAdapter();
			}
			@Override
			public Adapter caseWrapperObject(WrapperObject object) {
				return createWrapperObjectAdapter();
			}
			@Override
			public Adapter caseCorbaWrapperObject(CorbaWrapperObject object) {
				return createCorbaWrapperObjectAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram <em>System Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagram
	 * @generated
	 */
	public Adapter createSystemDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.InPort <em>In Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.InPort
	 * @generated
	 */
	public Adapter createInPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.OutPort <em>Out Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.OutPort
	 * @generated
	 */
	public Adapter createOutPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Component
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaComponent <em>Corba Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaComponent
	 * @generated
	 */
	public Adapter createCorbaComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile <em>Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile
	 * @generated
	 */
	public Adapter createConnectorProfileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet <em>Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet
	 * @generated
	 */
	public Adapter createConfigurationSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EInteger Object To Point Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEIntegerObjectToPointMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer <em>Port Synchronizer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer
	 * @generated
	 */
	public Adapter createPortSynchronizerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer <em>Corba Port Synchronizer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer
	 * @generated
	 */
	public Adapter createCorbaPortSynchronizerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile <em>Corba Connector Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile
	 * @generated
	 */
	public Adapter createCorbaConnectorProfileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet <em>Corba Configuration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet
	 * @generated
	 */
	public Adapter createCorbaConfigurationSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext <em>Corba Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext
	 * @generated
	 */
	public Adapter createCorbaExecutionContextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler <em>Corba Context Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler
	 * @generated
	 */
	public Adapter createCorbaContextHandlerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification
	 * @generated
	 */
	public Adapter createComponentSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.Port
	 * @generated
	 */
	public Adapter createPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector <em>Port Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.PortConnector
	 * @generated
	 */
	public Adapter createPortConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.ServicePort <em>Service Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ServicePort
	 * @generated
	 */
	public Adapter createServicePortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ExecutionContext
	 * @generated
	 */
	public Adapter createExecutionContextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.ContextHandler <em>Context Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ContextHandler
	 * @generated
	 */
	public Adapter createContextHandlerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue <em>Name Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.component.NameValue
	 * @generated
	 */
	public Adapter createNameValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.IAdaptable
	 * @generated
	 */
	public Adapter createIAdaptableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.core.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.core.ModelElement
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject <em>Local Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject
	 * @generated
	 */
	public Adapter createLocalObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.core.WrapperObject <em>Wrapper Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.core.WrapperObject
	 * @generated
	 */
	public Adapter createWrapperObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject <em>Corba Wrapper Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject
	 * @generated
	 */
	public Adapter createCorbaWrapperObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ComponentAdapterFactory
