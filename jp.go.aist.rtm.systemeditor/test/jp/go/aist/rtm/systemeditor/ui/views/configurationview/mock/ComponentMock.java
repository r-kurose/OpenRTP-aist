package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.omg.CORBA.Object;

import RTC.ComponentProfile;
import RTC.RTObject;
import _SDOPackage.Configuration;

public class ComponentMock implements Component {

	public static ComponentMock mock1;

	public static ComponentMock compMock1;

	static {
		// mock1
		createMock1();
		// compMock1
		createCompMock1();
	}

	@SuppressWarnings("unchecked")
	static void createMock1() {
		mock1 = new ComponentMock();
		mock1.instanceName = "component1";
		// mock1.default
		ConfigurationSetMock cs = new ConfigurationSetMock();
		cs.setId("default");
		NameValue nv = createNameValue("int_param1", "1");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "0.11");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "test1");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param2", "case1");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "0.01, 0.11, 0.22");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1.config1
		cs = new ConfigurationSetMock();
		cs.setId("config1");
		nv = createNameValue("int_param1", "2");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "0.22");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "test2");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param2", "case2");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "5.5, 10.5, 15.5");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1.config2
		cs = new ConfigurationSetMock();
		cs.setId("config2");
		nv = createNameValue("int_param1", "3");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "0.33");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "test3");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param2", "case3");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "0.05, 0.15, 0.25");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1.__widget__
		cs = new ConfigurationSetMock();
		cs.setId("__widget__");
		nv = createNameValue("int_param1", "slider");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "slider");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "radio");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param2", "hoge"); // 不明なwidget種別
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "spin");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1.__constraints__
		cs = new ConfigurationSetMock();
		cs.setId("__constraints__");
		nv = createNameValue("int_param1", "0<x<10");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "0<x<1");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "(test1,test2,test3,test4,test5)");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "0.0<x<1.0, 1.0<x<2.0, 2.0<x<3.0");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1._config1
		cs = new ConfigurationSetMock();
		cs.setId("_config1");
		nv = createNameValue("int_param1", "0<x<5");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "0<x<2");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "(test1,test2,test3)");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "5.0<x<10.0, 10.0<x<15.0, 15.0<x<20.0");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1._config2 なし
	}

	static void createCompMock1() {
		ComponentMock mock = new ComponentMock();
		mock.instanceName = "compComponent1";
		mock.category = "composite.PeriodicECShared";
		// mock1.default
		ConfigurationSetMock cs = new ConfigurationSetMock();
		cs.setId("default");
		NameValue nv = createNameValue("exported_ports", "comp1.in,comp2.out");
		cs.configurationData.add(nv);
		mock.configurationSets.add(cs);
		mock.activeConfigurationSet = cs;

		// child1
		ComponentMock child1 = new ComponentMock();
		child1.instanceName = "comp1";
		// port in
		PortMock port = new PortMock();
		port.setNameL("in");
		child1.ports.add(port);
		// port out
		port = new PortMock();
		port.setNameL("out");
		child1.ports.add(port);
		mock.components.add(child1);

		// child2
		ComponentMock child2 = new ComponentMock();
		child2.instanceName = "comp2";
		// port in
		port = new PortMock();
		port.setNameL("in");
		child2.ports.add(port);
		// port out
		port = new PortMock();
		port.setNameL("out");
		child2.ports.add(port);
		mock.components.add(child2);

		compMock1 = mock;
	}

	public static NameValue createNameValue(String key, String value) {
		NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
		nv.setName(key);
		nv.setValue(value);
		return nv;
	}

	public String instanceName;

	public String category;

	public ConfigurationSetMock activeConfigurationSet;

	public BasicEList configurationSets = new BasicEList();

	public BasicEList components = new BasicEList();

	public BasicEList ports = new BasicEList();


	public ConfigurationSet getActiveConfigurationSet() {
		return this.activeConfigurationSet;
	}

	public EList getAllComponents() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getAllInPorts() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getAllOutPorts() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getAllServiceports() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getCategoryL() {
		return this.category;
	}

	public String getComponentId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getComponents() {
		return this.components;
	}

	public Component getCompositeComponent() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getCompsiteTypeStr() {
		return null;
	}

	public EList getConfigurationSets() {
		return this.configurationSets;
	}

	public RTObject getCorbaObjectInterface() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getDescriptionL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getInports() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getInstanceNameL() {
		return this.instanceName;
	}

	public Action getOpenCompositeComponentAction() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getOutportDirection() {
		// TODO 自動生成されたメソッド・スタブ
		return "RIGHT";
	}

	public String getOutportDirectionStr() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getOutports() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getPathId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getPorts() {
		return this.ports;
	}

	public EList getProperties() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public ComponentProfile getRTCComponentProfile() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Configuration getSDOConfiguration() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getServiceports() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getTypeNameL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getVenderL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getVersionL() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean isCompositeComponent() {
		if (this.getCategoryL() != null
				&& this.getCategoryL().startsWith("composite.")) {
			return true;
		}
		return false;
	}

	public void setActiveConfigurationSet(ConfigurationSet value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setComponentId(String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setCompositeComponent(Component value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setCompsiteTypeStr(String type) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setInstanceNameL(String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setOpenCompositeComponentAction(Action value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setOutportDirection(int value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setOutportDirection(String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setPathId(String value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setRTCComponentProfile(ComponentProfile value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setSDOConfiguration(Configuration value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public boolean updateConfigurationSetListR(List list,
			ConfigurationSet activeConfigurationSet, List originallist) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public Object getCorbaBaseObject() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getCorbaObject() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean ping() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void setCorbaObject(Object value) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void accept(Visiter visiter) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public Rectangle getConstraint() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setConstraint(Rectangle rectangle) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public TreeIterator eAllContents() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EClass eClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EObject eContainer() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EStructuralFeature eContainingFeature() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EReference eContainmentFeature() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eContents() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eCrossReferences() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature, boolean resolve) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean eIsProxy() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean eIsSet(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public Resource eResource() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void eSet(EStructuralFeature feature, java.lang.Object newValue) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void eUnset(EStructuralFeature feature) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public EList eAdapters() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean eDeliver() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void eNotify(Notification notification) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void eSetDeliver(boolean deliver) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public boolean addComponentsR(List<Component> componentList) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean removeComponentR(Component component) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public String getCompositeTypeL() {
		if (this.isCompositeComponent()) {
			return this.getCategoryL().split("\\.")[1];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList getOrganizationProperties() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public boolean isRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setRequired(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public EList getExportedPorts() {
		EList result = new BasicEList();
		NameValue nv = this._findExportedPortsNameValue();
		if (nv == null) {
			return result;
		}
		String[] ss = nv.getValueAsString().split(",");
		for (String s : ss) {
			result.add(s);
		}
		return result;
	}

	public boolean isGroupingCompositeComponent() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean setExportedPorts(EList value) {
		NameValue nv = this._findExportedPortsNameValue();
		if (nv == null) {
			return false;
		}
		String result = "";
		for (int i = 0; i < value.size(); i++) {
			if (result.length() > 0) {
				result += ",";
			}
			result += (String) value.get(i);
		}
		nv.setValue(result);
		return true;
	}

	@SuppressWarnings("unchecked")
	NameValue _findExportedPortsNameValue() {
		NameValue result = null;
		ConfigurationSetMock cs = this.activeConfigurationSet;
		if (cs == null) {
			return result;
		}
		EList data = cs.getConfigurationData();
		for (int i = 0; i < data.size(); i++) {
			NameValue nv = (NameValue) data.get(i);
			if (!nv.getName().equals("exported_ports")) {
				continue;
			}
			result = nv;
		}
		return result;
	}
//	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

//	@Override
	public void setCategoryL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setDescriptionL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setTypeNameL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setVenderL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setVersionL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public SystemDiagram getChildSystemDiagram() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setChildSystemDiagram(SystemDiagram value) {
		// TODO Auto-generated method stub
		
	}

	public boolean inOnlineSystemDiagram() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

//	@Override
	public boolean setComponentsR(List<Component> componentList) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public void synchronizeManually() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void synchronizeChildComponents() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void synchronizeLocalAttribute(EStructuralFeature reference) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void synchronizeLocalReference() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void addComponent(Component component) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public Component copy() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public void removeDeadChild() {
		// TODO Auto-generated method stub
		
	}

	public ExecutionContext getExecutionContext(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getExecutionContextId(ExecutionContext ec) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EList getExecutionContexts() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public ExecutionContext setExecutionContext(String id, ExecutionContext ec) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
