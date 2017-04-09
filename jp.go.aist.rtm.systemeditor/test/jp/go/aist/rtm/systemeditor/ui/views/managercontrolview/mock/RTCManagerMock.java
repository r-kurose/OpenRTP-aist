package jp.go.aist.rtm.systemeditor.ui.views.managercontrolview.mock;

import java.lang.reflect.InvocationTargetException;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.TCKind;

import RTC.ComponentProfile;
import RTC.RTObject;
import RTM.ManagerProfile;
import RTM.ModuleProfile;
import _SDOPackage.NameValue;

public class RTCManagerMock implements RTCManager {

	static ORB orb;

	public static RTCManagerMock mock1;
	static {
		orb = ORB.init();

		// mock1
		mock1 = new RTCManagerMock();

		ModuleProfile mp;
		mp = new ModuleProfile();
		NameValue nv = newNV("file_path", "lib/module1.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module2.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module3.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module4.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module5.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);

		ComponentProfile cp;
		cp = new ComponentProfile();
		cp.instance_name = "component_instance1";
		mock1.componentProfileList.add(cp);
		cp = new ComponentProfile();
		cp.instance_name = "component_instance2";
		mock1.componentProfileList.add(cp);
		cp = new ComponentProfile();
		cp.instance_name = "component_instance3";
		mock1.componentProfileList.add(cp);
	};

	public ManagerProfile managerProfile;

	public BasicEList<ModuleProfile> loadableModuleList = new BasicEList<ModuleProfile>();

	public BasicEList<ModuleProfile> loadedModuleList = new BasicEList<ModuleProfile>();

	public BasicEList<ComponentProfile> componentProfileList = new BasicEList<ComponentProfile>();

	public static NameValue newNV(String name, String value) {
		NameValue nv = new NameValue();
		nv.name = name;
		nv.value = newAny(value);
		return nv;
	}

	public static Any newAny(String value) {
		Any any = orb.create_any();
		if (StringUtils.isAsciiPrintable(value)) {
			any.insert_string(value);
		} else {
			any.insert_wstring(value);
		}
		return any;
	}

	private static Any findValue(NameValue[] nameValue, String name) {
		Any result = null;
		if (nameValue != null) {
			for (NameValue elem : nameValue) {
				if (elem != null && name.equals(elem.name)) {
					result = elem.value;
					break;
				}
			}
		}
		return result;
	}

	public static String findStringValue(NameValue[] nameValue, String name) {
		Any any = findValue(nameValue, name);
		String result = null;
		if (any != null) {
			if (any.type().kind() == TCKind.tk_wstring) {
				result = any.extract_wstring();
			} else {
				result = any.extract_string();
			}
		}
		return result;
	}

	public Component createComponentR(String compName) {
		return null;
	}

	public int forkR() {
		return 0;
	}

	public int shutdownR() {
		return 0;
	}

	public int deleteComponentR(String instanceName) {
		return 0;
	}

	public EList<RTObject> getComponentsR() {
		return null;
	}

	public ManagerProfile getProfileR() {
		return null;
	}

	public Object getCorbaBaseObject() {
		return null;
	}

	public Object getCorbaObject() {
		return null;
	}

	public org.omg.CORBA.Object getCorbaObjectInterface() {
		return null;
	}

	public boolean ping() {
		return false;
	}

	public void setCorbaObject(Object value) {
	}

	public void accept(Visiter visiter) {
	}

	public void dispose() {
	}

	public Rectangle getConstraint() {
		return null;
	}

	public void setConstraint(Rectangle rectangle) {
	}

	@SuppressWarnings("rawtypes")
	public TreeIterator eAllContents() {
		return null;
	}

	public EClass eClass() {
		return null;
	}

	public EObject eContainer() {
		return null;
	}

	public EStructuralFeature eContainingFeature() {
		return null;
	}

	public EReference eContainmentFeature() {
		return null;
	}

	public EList<EObject> eContents() {
		return null;
	}

	public EList<EObject> eCrossReferences() {
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature) {
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature, boolean resolve) {
		return null;
	}

	public boolean eIsProxy() {
		return false;
	}

	public boolean eIsSet(EStructuralFeature feature) {
		return false;
	}

	public Resource eResource() {
		return null;
	}

	public void eSet(EStructuralFeature feature, java.lang.Object newValue) {
	}

	public void eUnset(EStructuralFeature feature) {
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments)
			throws InvocationTargetException {
		return null;
	}

	public EList<Adapter> eAdapters() {
		return null;
	}

	public boolean eDeliver() {
		return false;
	}

	public void eNotify(Notification notification) {
	}

	public void eSetDeliver(boolean deliver) {
	}

	@SuppressWarnings("rawtypes")
	public java.lang.Object getAdapter(Class adapter) {
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
	}

	public EList<String> getComponentInstanceNamesR() {
		return null;
	}

	public EList<ComponentProfile> getComponentProfilesR() {
		return null;
	}

	public EList<String> getLoadableModuleFileNamesR() {
		BasicEList<String> result = new BasicEList<String>();
		for (java.lang.Object o : this.loadableModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value != null) {
					result.add(value);
				}
			}
		}
		return result;
	}

	public EList<ModuleProfile> getLoadableModuleProfilesR() {
		BasicEList<ModuleProfile> result = new BasicEList<ModuleProfile>();
		for (java.lang.Object o : this.loadableModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				result.add(mp);
			}
		}
		return result;
	}

	public EList<String> getLoadedModuleFileNamesR() {
		BasicEList<String> result = new BasicEList<String>();
		for (java.lang.Object o : this.loadedModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value != null) {
					result.add(value);
				}
			}
		}
		return result;
	}

	public EList<ModuleProfile> getLoadedModuleProfilesR() {
		BasicEList<ModuleProfile> result = new BasicEList<ModuleProfile>();
		for (java.lang.Object o : this.loadedModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				result.add(mp);
			}
		}
		return result;
	}

	public int loadModuleR(String pathname, String initfunc) {
		for (java.lang.Object o : this.loadableModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value.equals(pathname)) {
					this.loadedModuleList.add(mp);
					this.loadableModuleList.remove(mp);
					return 1;
				}
			}
		}
		return 0;
	}

	public int unloadModuleR(String pathname) {
		for (java.lang.Object o : this.loadedModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value.equals(pathname)) {
					this.loadableModuleList.add(mp);
					this.loadedModuleList.remove(mp);
					return 1;
				}
			}
		}
		return 0;
	}

	public String getInstanceNameL() {
		return null;
	}

	public ManagerProfile getManagerProfile() {
		return null;
	}

	public void setManagerProfile(ManagerProfile value) {
	}

	public EList<String> getFactoryProfileTypeNamesR() {
		return null;
	}

	public String getPathId() {
		return null;
	}

	@Override
	public void setPathId(String value) {
	}

	public void synchronizeLocalAttribute(EReference reference) {
	}

	public void synchronizeLocalReference() {
	}

	@Override
	public void synchronizeManually() {
	}

	@Override
	public EList<String> getComponentInstanceNames() {
		return null;
	}

	@Override
	public EList<ComponentProfile> getComponentProfiles() {
		return null;
	}

	@Override
	public EList<ModuleProfile> getFactoryModuleProfiles() {
		return null;
	}

	@Override
	public EList<ModuleProfile> getFactoryModuleProfilesR() {
		return null;
	}

	@Override
	public EList<String> getFactoryTypeNames() {
		return null;
	}

	@Override
	public EList<String> getLoadableModuleFileNames() {
		return null;
	}

	@Override
	public EList<ModuleProfile> getLoadableModuleProfiles() {
		return null;
	}

	@Override
	public EList<String> getLoadedModuleFileNames() {
		return null;
	}

	@Override
	public EList<ModuleProfile> getLoadedModuleProfiles() {
		return null;
	}

	@Override
	public boolean isMaster() {
		return false;
	}

	@Override
	public void setMaster(boolean value) {
	}

	@Override
	public EList<RTCManager> getMasterManagers() {
		return null;
	}

	@Override
	public EList<RTCManager> getSlaveManagers() {
		return null;
	}

	@Override
	public EList<jp.go.aist.rtm.toolscommon.model.component.NameValue> getConfiguratoins() {
		return null;
	}

	@Override
	public int restartR() {
		return 0;
	}

	@Override
	public boolean isMasterR() {
		return false;
	}

	@Override
	public EList<RTCManager> getMasterManagersR() {
		return null;
	}

	@Override
	public int addMasterManagerR(RTCManager mgr) {
		return 0;
	}

	@Override
	public int removeMasterManagerR(RTCManager mgr) {
		return 0;
	}

	@Override
	public EList<RTCManager> getSlaveManagersR() {
		return null;
	}

	@Override
	public int addSlaveManagerR(RTCManager mgr) {
		return 0;
	}

	@Override
	public int removeSlaveManagerR(RTCManager mgr) {
		return 0;
	}

	@Override
	public EList<jp.go.aist.rtm.toolscommon.model.component.NameValue> getConfigurationR() {
		return null;
	}

	@Override
	public int setConfigurationR(String name, String value) {
		return 0;
	}

	@Override
	public EList<String> getSlaveManagerNames() {
		return null;
	}

}
