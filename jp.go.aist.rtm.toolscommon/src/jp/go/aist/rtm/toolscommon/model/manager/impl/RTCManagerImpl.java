/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.manager.impl;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorbaWrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import RTM.ManagerHelper;
import RTM.ManagerProfile;
import RTM.ModuleProfile;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>RTC Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getManagerProfile <em>Manager Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.impl.RTCManagerImpl#getPathId <em>Path Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RTCManagerImpl extends CorbaWrapperObjectImpl implements
		RTCManager {
	/**
	 * The default value of the '{@link #getManagerProfile() <em>Manager Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagerProfile()
	 * @generated
	 * @ordered
	 */
	protected static final ManagerProfile MANAGER_PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getManagerProfile() <em>Manager Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagerProfile()
	 * @generated
	 * @ordered
	 */
	protected ManagerProfile managerProfile = MANAGER_PROFILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstanceNameL() <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceNameL()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_NAME_L_EDEFAULT = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RTCManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ManagerPackage.Literals.RTC_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagerProfile getManagerProfile() {
		return managerProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagerProfile(ManagerProfile newManagerProfile) {
		ManagerProfile oldManagerProfile = managerProfile;
		managerProfile = newManagerProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ManagerPackage.RTC_MANAGER__MANAGER_PROFILE, oldManagerProfile, managerProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getInstanceNameL() {
		String name = null;
		if (this.managerProfile != null) {
			name = SDOUtil.getStringValue(this.managerProfile.properties,
					"instance_name");
		}
		return name;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ManagerPackage.RTC_MANAGER__PATH_ID, oldPathId, pathId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Component createComponentR(String compName) {
		RTC.RTObject remote = this.getCorbaObjectInterface().create_component(
				compName);
		SynchronizationManager manager = new SynchronizationManager(
				new MappingRule[] { CorbaComponentImpl.MAPPING_RULE });
		LocalObject local = manager.createLocalObject(new Object[] { remote });
		if (local == null || !(local instanceof Component)) {
			return null;
		}
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return (Component) local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int deleteComponentR(String instanceName) {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().delete_component(
				instanceName);
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getComponentsR() {
		BasicEList result = new BasicEList();
		RTC.RTObject[] rtobjs = this.getCorbaObjectInterface().get_components();
		for (int i = 0; i < rtobjs.length; i++) {
			result.add(rtobjs[i]);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getComponentProfilesR() {
		BasicEList result = new BasicEList();
		RTC.ComponentProfile[] profs = this.getCorbaObjectInterface().get_component_profiles();
		for (int i = 0; i < profs.length; i++) {
			result.add(profs[i]);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int loadModuleR(String pathname, String initfunc) {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().load_module(pathname,
				initfunc);
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int unloadModuleR(String pathname) {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface()
				.unload_module(pathname);
		// プロパティ更新通知
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ManagerPackage.RTM_MODULE_PROFILE, false, true));
		return rc.value();
	}

	// 簡易キャッシュ(loadable module)
	BasicEList loadableModules = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getLoadableModuleProfilesR() {
		// 簡易キャッシュ
		if (loadableModules == null) {
			loadableModules = new BasicEList();
		}
		ModuleProfile[] profs = this.getCorbaObjectInterface()
				.get_loadable_modules();
		loadableModules.clear();
		for (int i = 0; i < profs.length; i++) {
			loadableModules.add(profs[i]);
		}
		return loadableModules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getLoadedModuleProfilesR() {
		BasicEList result = new BasicEList();
		ModuleProfile[] profs = this.getCorbaObjectInterface()
				.get_loaded_modules();
		for (int i = 0; i < profs.length; i++) {
			result.add(profs[i]);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int forkR() {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().fork();
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int shutdownR() {
		RTC.ReturnCode_t rc = this.getCorbaObjectInterface().shutdown();
		return rc.value();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getComponentInstanceNamesR() {
		BasicEList result = new BasicEList();
		RTC.ComponentProfile[] profs = this.getCorbaObjectInterface()
				.get_component_profiles();
		for (int i = 0; i < profs.length; i++) {
			result.add(profs[i].instance_name);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getLoadableModuleFileNameR() {
		if (loadableModules == null) {
			getLoadableModuleProfilesR();
		}
		BasicEList result = new BasicEList();
		for (Object o : loadableModules) {
			ModuleProfile prof = (ModuleProfile) o;
			String file = SDOUtil.getStringValue(prof.properties,
					"module_file_path");
			if (file != null) {
				result.add(file);
			}
		}
		return result;
//		BasicEList result = new BasicEList();
//		ModuleProfile[] profs = this.getCorbaObjectInterface()
//				.get_loadable_modules();
//		for (int i = 0; i < profs.length; i++) {
//			String file = SDOUtil.getStringValue(profs[i].properties,
//					"module_file_path");
//			if (file != null) {
//				result.add(file);
//			}
//		}
//		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getLoadedModuleFileNamesR() {
		BasicEList result = new BasicEList();
		ModuleProfile[] profs = this.getCorbaObjectInterface()
				.get_loaded_modules();
		for (int i = 0; i < profs.length; i++) {
			String file = SDOUtil.getStringValue(profs[i].properties,
					"file_path");
			if (file != null) {
				result.add(file);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getFactoryProfileTypeNamesR() {
		BasicEList result = new BasicEList();
		for (Object o : loadableModules) {
			ModuleProfile prof = (ModuleProfile) o;
			String file = SDOUtil.getStringValue(prof.properties, "type_name");
			if (file != null) {
				result.add(file);
			}
		}
//		ModuleProfile[] profs = this.getCorbaObjectInterface()
//				.get_factory_profiles();
//		for (int i = 0; i < profs.length; i++) {
//			String type = SDOUtil.getStringValue(profs[i].properties,
//					"type_name");
//			if (type != null) {
//				result.add(type);
//			}
//		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				return getManagerProfile();
			case ManagerPackage.RTC_MANAGER__INSTANCE_NAME_L:
				return getInstanceNameL();
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				return getPathId();
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
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				setManagerProfile((ManagerProfile)newValue);
				return;
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				setPathId((String)newValue);
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
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				setManagerProfile(MANAGER_PROFILE_EDEFAULT);
				return;
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				setPathId(PATH_ID_EDEFAULT);
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
			case ManagerPackage.RTC_MANAGER__MANAGER_PROFILE:
				return MANAGER_PROFILE_EDEFAULT == null ? managerProfile != null : !MANAGER_PROFILE_EDEFAULT.equals(managerProfile);
			case ManagerPackage.RTC_MANAGER__INSTANCE_NAME_L:
				return INSTANCE_NAME_L_EDEFAULT == null ? instanceNameL != null : !INSTANCE_NAME_L_EDEFAULT.equals(instanceNameL);
			case ManagerPackage.RTC_MANAGER__PATH_ID:
				return PATH_ID_EDEFAULT == null ? pathId != null : !PATH_ID_EDEFAULT.equals(pathId);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (managerProfile: ");
		result.append(managerProfile);
		result.append(", instanceNameL: ");
		result.append(instanceNameL);
		result.append(", pathId: ");
		result.append(pathId);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ManagerProfile getProfileR() {
		this.managerProfile = this.getCorbaObjectInterface().get_profile();
		return this.managerProfile;
	}


//	@Override
	public RTM.Manager getCorbaObjectInterface() {
		return ManagerHelper.narrow(super.getCorbaObject());
	}

	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					RTCManagerImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) }) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					boolean result = false;
					if (remoteObjects[0] instanceof org.omg.CORBA.Object) {
						result = ((org.omg.CORBA.Object) remoteObjects[0])
								._is_a(ManagerHelper.id());
					}
					return result;
				}

				@Override
				public boolean needsPing() {
					return true;
				}

				@Override
				public Object[] narrow(Object[] remoteObjects) {
					return new Object[] { ManagerHelper
							.narrow(((org.omg.CORBA.Object) remoteObjects[0])) };
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});

	private static AttributeMapping[] getAttributeMappings() {
		return new AttributeMapping[] { new AttributeMapping(
				ManagerPackage.eINSTANCE.getRTCManager_ManagerProfile(), true) {
			@Override
			public Object getRemoteAttributeValue(LocalObject localObject,
					Object[] remoteObjects) {
				Object result = null;
				result = ((RTCManager) localObject).getProfileR();
				return result;
			}
		}
		};
	}

	private static ReferenceMapping[] getReferenceMappings() {
		return new ReferenceMapping[] {};
	}

	private  void synchronizeLocalAttribute(EReference reference) {
		for (AttributeMapping attibuteMapping : getAttributeMappings()) {
			if (reference != null) {
				if (reference.equals(attibuteMapping.getLocalFeature())) {
					try {
						attibuteMapping.syncronizeLocal(this);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
			} else {
				try {
					attibuteMapping.syncronizeLocal(this);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	private void synchronizeLocalReference() {
		for (ReferenceMapping referenceMapping : RTCManagerImpl
				.getReferenceMappings()) {
			try {
				referenceMapping.syncronizeLocal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private long lastExecutedTime;
	private static long SYNC_MANUAL_INTERVAL = 1000L;

//	@Override
	public void synchronizeManually() {
		if (System.currentTimeMillis() - lastExecutedTime < SYNC_MANUAL_INTERVAL) {
			return;
		}

		synchronizeLocalAttribute(null);
		synchronizeLocalReference();
		
		lastExecutedTime = System.currentTimeMillis();
	}

} // RTCManagerImpl
