package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
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

public class ComponentMock implements Component {

	public static ComponentMock mock1;

	public static ComponentMock compMock1;

	static {
		// mock1
		createMock1();
		// compMock1
		createCompMock1();
	}

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
		nv = createNameValue("str_param2", "top");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param3", "bottom");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param4", "case1");
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
		nv = createNameValue("str_param2", "right");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param3", "top,left");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param4", "case2");
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
		nv = createNameValue("str_param2", "left");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param3", "bottom,right,right");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param4", "case3");
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
		nv = createNameValue("str_param2", "checkbox");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param3", "ordered_list");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param4", "hoge"); // 不明なwidget種別
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "spin"); // TODO 配列表記
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
		nv = createNameValue("str_param2", "(top,left,bottom,right)");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param3", "(top,left,bottom,right)");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "0.0<x<1.0, 1.0<x<2.0, 2.0<x<3.0");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1.__config1
		cs = new ConfigurationSetMock();
		cs.setId("__config1");
		nv = createNameValue("int_param1", "0<x<5");
		cs.configurationData.add(nv);
		nv = createNameValue("double_param1", "0<x<2");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param1", "(test1,test2,test3)");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param2", "(top,left,bottom,right)");
		cs.configurationData.add(nv);
		nv = createNameValue("str_param3", "(top,left,bottom,right)");
		cs.configurationData.add(nv);
		nv = createNameValue("vector_param1", "5.0<x<10.0, 10.0<x<15.0, 15.0<x<20.0");
		cs.configurationData.add(nv);
		mock1.configurationSets.add(cs);
		// mock1.__config2 なし
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

	public BasicEList<ConfigurationSet> configurationSets = new BasicEList<ConfigurationSet>();

	public BasicEList<Component> components = new BasicEList<Component>();

	public BasicEList<Port> ports = new BasicEList<Port>();


	public ConfigurationSet getActiveConfigurationSet() {
		return this.activeConfigurationSet;
	}

	public EList<Component> getAllComponents() {
		return null;
	}

	public String getCategoryL() {
		return this.category;
	}

	public String getComponentId() {
		return null;
	}

	public EList<Component> getComponents() {
		return this.components;
	}

	public EList<ConfigurationSet> getConfigurationSets() {
		return this.configurationSets;
	}

	public String getDescriptionL() {
		return null;
	}

	public EList<InPort> getInports() {
		return null;
	}

	public String getInstanceNameL() {
		return this.instanceName;
	}

	public String getOutportDirection() {
		return "RIGHT";
	}

	public EList<OutPort> getOutports() {
		return null;
	}

	public String getPathId() {
		return null;
	}

	public EList<Port> getPorts() {
		return this.ports;
	}

	public EList<ServicePort> getServiceports() {
		return null;
	}

	public String getTypeNameL() {
		return null;
	}

	public String getVenderL() {
		return null;
	}

	public String getVersionL() {
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
	}

	public void setComponentId(String value) {
	}

	public void setInstanceNameL(String value) {
	}

	public void setOutportDirection(String value) {
	}

	public void setPathId(String value) {
	}

	@SuppressWarnings("unchecked")
	public boolean updateConfigurationSetListR(List list,
			ConfigurationSet activeConfigurationSet, List originallist) {
		return false;
	}

	public void accept(Visiter visiter) {
	}

	public Rectangle getConstraint() {
		return null;
	}

	public void setConstraint(Rectangle rectangle) {
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public EList eContents() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eCrossReferences() {
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

	@SuppressWarnings("unchecked")
	public EList eAdapters() {
		return null;
	}

	public boolean eDeliver() {
		return false;
	}

	public void eNotify(Notification notification) {
	}

	public void eSetDeliver(boolean deliver) {
	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
	}

	public boolean addComponentsR(List<Component> componentList) {
		return false;
	}

	public boolean removeComponentR(Component component) {
		return false;
	}

	public String getCompositeTypeL() {
		if (this.isCompositeComponent()) {
			return this.getCategoryL().split("\\.")[1];
		}
		return null;
	}

//	@SuppressWarnings("unchecked")
//	public EList getOrganizationProperties() {
//		return null;
//	}

	public boolean isRequired() {
		return false;
	}

	public void setRequired(boolean value) {
	}

	public EList<String> getExportedPorts() {
		EList<String> result = new BasicEList<String>();
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
		return false;
	}

	@Override
	public boolean setExportedPorts(EList<String> values) {
		NameValue nv = this._findExportedPortsNameValue();
		if (nv == null) {
			return false;
		}
		String result = "";
		for (int i = 0; i < values.size(); i++) {
			if (result.length() > 0) {
				result += ",";
			}
			result += (String) values.get(i);
		}
		nv.setValue(result);
		return true;
	}

	NameValue _findExportedPortsNameValue() {
		NameValue result = null;
		ConfigurationSetMock cs = this.activeConfigurationSet;
		if (cs == null) {
			return result;
		}
		EList<NameValue> data = cs.getConfigurationData();
		for (int i = 0; i < data.size(); i++) {
			NameValue nv = data.get(i);
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
		return false;
	}

//	@Override
	public void setCategoryL(String value) {
	}

//	@Override
	public void setDescriptionL(String value) {
	}

//	@Override
	public void setTypeNameL(String value) {
	}

//	@Override
	public void setVenderL(String value) {
	}

//	@Override
	public void setVersionL(String value) {
	}

//	@Override
	public SystemDiagram getChildSystemDiagram() {
		return null;
	}

//	@Override
	public void setChildSystemDiagram(SystemDiagram value) {
	}

	public boolean inOnlineSystemDiagram() {
		return false;
	}

//	@Override
	public boolean setComponentsR(List<Component> componentList) {
		return false;
	}

//	@Override
	public void synchronizeManually() {
	}

//	@Override
	public void synchronizeChildComponents() {
	}

//	@Override
	public void synchronizeLocalAttribute(EStructuralFeature reference) {
	}

//	@Override
	public void synchronizeLocalReference() {
	}

//	@Override
	public void addComponent(Component component) {
	}

//	@Override
	public Component copy() {
		return null;
	}

//	@Override
	public boolean isDead() {
		return false;
	}

//	@Override
	public void removeDeadChild() {
	}

	public EList<ExecutionContext> getExecutionContexts() {
		return null;
	}

	@Override
	public EList<ExecutionContext> getParticipationContexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasComponentAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ContextHandler getExecutionContextHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContextHandler getParticipationContextHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExecutionContextHandler(ContextHandler value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setParticipationContextHandler(ContextHandler value) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getPropertyKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String key, String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public IPropertyMap getPropertyMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronizeRemoteAttribute(EStructuralFeature reference) {
		// TODO Auto-generated method stub
	}

	@Override
	public void synchronizeRemoteChildComponents() {
		// TODO Auto-generated method stub
	}

}
