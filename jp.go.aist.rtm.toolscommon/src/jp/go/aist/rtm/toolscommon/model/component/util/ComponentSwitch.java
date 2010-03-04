/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentSwitch.java,v 1.9 2008/03/06 04:01:49 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.List;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage
 * @generated
 */
public class ComponentSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentSwitch() {
		if (modelPackage == null) {
			modelPackage = ComponentPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ComponentPackage.SYSTEM_DIAGRAM: {
				SystemDiagram systemDiagram = (SystemDiagram)theEObject;
				T result = caseSystemDiagram(systemDiagram);
				if (result == null) result = caseModelElement(systemDiagram);
				if (result == null) result = caseIAdaptable(systemDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.COMPONENT: {
				Component component = (Component)theEObject;
				T result = caseComponent(component);
				if (result == null) result = caseWrapperObject(component);
				if (result == null) result = caseModelElement(component);
				if (result == null) result = caseLocalObject(component);
				if (result == null) result = caseIAdaptable(component);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.COMPONENT_SPECIFICATION: {
				ComponentSpecification componentSpecification = (ComponentSpecification)theEObject;
				T result = caseComponentSpecification(componentSpecification);
				if (result == null) result = caseComponent(componentSpecification);
				if (result == null) result = caseWrapperObject(componentSpecification);
				if (result == null) result = caseModelElement(componentSpecification);
				if (result == null) result = caseLocalObject(componentSpecification);
				if (result == null) result = caseIAdaptable(componentSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.EXECUTION_CONTEXT: {
				ExecutionContext executionContext = (ExecutionContext)theEObject;
				T result = caseExecutionContext(executionContext);
				if (result == null) result = caseWrapperObject(executionContext);
				if (result == null) result = caseModelElement(executionContext);
				if (result == null) result = caseLocalObject(executionContext);
				if (result == null) result = caseIAdaptable(executionContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CONFIGURATION_SET: {
				ConfigurationSet configurationSet = (ConfigurationSet)theEObject;
				T result = caseConfigurationSet(configurationSet);
				if (result == null) result = caseWrapperObject(configurationSet);
				if (result == null) result = caseModelElement(configurationSet);
				if (result == null) result = caseLocalObject(configurationSet);
				if (result == null) result = caseIAdaptable(configurationSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.NAME_VALUE: {
				NameValue nameValue = (NameValue)theEObject;
				T result = caseNameValue(nameValue);
				if (result == null) result = caseWrapperObject(nameValue);
				if (result == null) result = caseModelElement(nameValue);
				if (result == null) result = caseLocalObject(nameValue);
				if (result == null) result = caseIAdaptable(nameValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.PORT: {
				Port port = (Port)theEObject;
				T result = casePort(port);
				if (result == null) result = caseWrapperObject(port);
				if (result == null) result = caseModelElement(port);
				if (result == null) result = caseLocalObject(port);
				if (result == null) result = caseIAdaptable(port);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.IN_PORT: {
				InPort inPort = (InPort)theEObject;
				T result = caseInPort(inPort);
				if (result == null) result = casePort(inPort);
				if (result == null) result = caseWrapperObject(inPort);
				if (result == null) result = caseModelElement(inPort);
				if (result == null) result = caseLocalObject(inPort);
				if (result == null) result = caseIAdaptable(inPort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.OUT_PORT: {
				OutPort outPort = (OutPort)theEObject;
				T result = caseOutPort(outPort);
				if (result == null) result = casePort(outPort);
				if (result == null) result = caseWrapperObject(outPort);
				if (result == null) result = caseModelElement(outPort);
				if (result == null) result = caseLocalObject(outPort);
				if (result == null) result = caseIAdaptable(outPort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.SERVICE_PORT: {
				ServicePort servicePort = (ServicePort)theEObject;
				T result = caseServicePort(servicePort);
				if (result == null) result = casePort(servicePort);
				if (result == null) result = caseWrapperObject(servicePort);
				if (result == null) result = caseModelElement(servicePort);
				if (result == null) result = caseLocalObject(servicePort);
				if (result == null) result = caseIAdaptable(servicePort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.PORT_SYNCHRONIZER: {
				PortSynchronizer portSynchronizer = (PortSynchronizer)theEObject;
				T result = casePortSynchronizer(portSynchronizer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.PORT_CONNECTOR: {
				PortConnector portConnector = (PortConnector)theEObject;
				T result = casePortConnector(portConnector);
				if (result == null) result = caseWrapperObject(portConnector);
				if (result == null) result = caseModelElement(portConnector);
				if (result == null) result = caseLocalObject(portConnector);
				if (result == null) result = caseIAdaptable(portConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CONNECTOR_PROFILE: {
				ConnectorProfile connectorProfile = (ConnectorProfile)theEObject;
				T result = caseConnectorProfile(connectorProfile);
				if (result == null) result = caseWrapperObject(connectorProfile);
				if (result == null) result = caseModelElement(connectorProfile);
				if (result == null) result = caseLocalObject(connectorProfile);
				if (result == null) result = caseIAdaptable(connectorProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.EINTEGER_OBJECT_TO_POINT_MAP_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<Integer, Point> eIntegerObjectToPointMapEntry = (Map.Entry<Integer, Point>)theEObject;
				T result = caseEIntegerObjectToPointMapEntry(eIntegerObjectToPointMapEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CORBA_COMPONENT: {
				CorbaComponent corbaComponent = (CorbaComponent)theEObject;
				T result = caseCorbaComponent(corbaComponent);
				if (result == null) result = caseComponent(corbaComponent);
				if (result == null) result = caseCorbaWrapperObject(corbaComponent);
				if (result == null) result = caseWrapperObject(corbaComponent);
				if (result == null) result = caseModelElement(corbaComponent);
				if (result == null) result = caseLocalObject(corbaComponent);
				if (result == null) result = caseIAdaptable(corbaComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CORBA_PORT_SYNCHRONIZER: {
				CorbaPortSynchronizer corbaPortSynchronizer = (CorbaPortSynchronizer)theEObject;
				T result = caseCorbaPortSynchronizer(corbaPortSynchronizer);
				if (result == null) result = caseCorbaWrapperObject(corbaPortSynchronizer);
				if (result == null) result = casePortSynchronizer(corbaPortSynchronizer);
				if (result == null) result = caseWrapperObject(corbaPortSynchronizer);
				if (result == null) result = caseModelElement(corbaPortSynchronizer);
				if (result == null) result = caseLocalObject(corbaPortSynchronizer);
				if (result == null) result = caseIAdaptable(corbaPortSynchronizer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CORBA_CONNECTOR_PROFILE: {
				CorbaConnectorProfile corbaConnectorProfile = (CorbaConnectorProfile)theEObject;
				T result = caseCorbaConnectorProfile(corbaConnectorProfile);
				if (result == null) result = caseConnectorProfile(corbaConnectorProfile);
				if (result == null) result = caseWrapperObject(corbaConnectorProfile);
				if (result == null) result = caseModelElement(corbaConnectorProfile);
				if (result == null) result = caseLocalObject(corbaConnectorProfile);
				if (result == null) result = caseIAdaptable(corbaConnectorProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CORBA_CONFIGURATION_SET: {
				CorbaConfigurationSet corbaConfigurationSet = (CorbaConfigurationSet)theEObject;
				T result = caseCorbaConfigurationSet(corbaConfigurationSet);
				if (result == null) result = caseConfigurationSet(corbaConfigurationSet);
				if (result == null) result = caseWrapperObject(corbaConfigurationSet);
				if (result == null) result = caseModelElement(corbaConfigurationSet);
				if (result == null) result = caseLocalObject(corbaConfigurationSet);
				if (result == null) result = caseIAdaptable(corbaConfigurationSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentPackage.CORBA_EXECUTION_CONTEXT: {
				CorbaExecutionContext corbaExecutionContext = (CorbaExecutionContext)theEObject;
				T result = caseCorbaExecutionContext(corbaExecutionContext);
				if (result == null) result = caseExecutionContext(corbaExecutionContext);
				if (result == null) result = caseCorbaWrapperObject(corbaExecutionContext);
				if (result == null) result = caseWrapperObject(corbaExecutionContext);
				if (result == null) result = caseModelElement(corbaExecutionContext);
				if (result == null) result = caseLocalObject(corbaExecutionContext);
				if (result == null) result = caseIAdaptable(corbaExecutionContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemDiagram(SystemDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>In Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>In Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInPort(InPort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Out Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Out Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutPort(OutPort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponent(Component object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaComponent(CorbaComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectorProfile(ConnectorProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationSet(ConfigurationSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EInteger Object To Point Map Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EInteger Object To Point Map Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEIntegerObjectToPointMapEntry(Map.Entry<Integer, Point> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Synchronizer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Synchronizer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortSynchronizer(PortSynchronizer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Port Synchronizer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Port Synchronizer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaPortSynchronizer(CorbaPortSynchronizer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Connector Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Connector Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaConnectorProfile(CorbaConnectorProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Configuration Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Configuration Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaConfigurationSet(CorbaConfigurationSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Execution Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Execution Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaExecutionContext(CorbaExecutionContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentSpecification(ComponentSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePort(Port object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortConnector(PortConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServicePort(ServicePort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionContext(ExecutionContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Name Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Name Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameValue(NameValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAdaptable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAdaptable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIAdaptable(IAdaptable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Local Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Local Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocalObject(LocalObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrapper Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrapper Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapperObject(WrapperObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Wrapper Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Wrapper Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorbaWrapperObject(CorbaWrapperObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ComponentSwitch
