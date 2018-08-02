package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.Set;

import jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock.ComponentMock;
import junit.framework.TestCase;

public class ComponentConfigurationWrapperTest extends TestCase {

	public void testCreate() {
		ComponentConfigurationWrapper config = ComponentConfigurationWrapper
				.create(ComponentMock.mock1, true);

		for (ConfigurationSetConfigurationWrapper cs : config
				.getConfigurationSetList()) {
			String csId = cs.getId();
			if ("__widget__".equals(csId) || "__constraints__".equals(csId) || "__config1".equals(csId)) {
				continue;
			}
			if (csId == "default") {
				for (NamedValueConfigurationWrapper nv : cs.getNamedValueList()) {
					if (nv.getKey() == "int_param1") {
						assertEquals("1", this.getNamedValueString(nv));
						assertEquals("slider", nv.widget().getType());
						assertEquals("0<x<10", nv.widget().getCondition()
								.toString());
					} else if (nv.getKey() == "double_param1") {
						assertEquals("0.11", this.getNamedValueString(nv));
						assertEquals("slider", nv.widget().getType());
						assertEquals("0<x<1", nv.widget().getCondition()
								.toString());
					} else if (nv.getKey() == "str_param1") {
						assertEquals("test1", this.getNamedValueString(nv));
						assertEquals("radio", nv.widget().getType());
						assertEquals("(test1,test2,test3,test4,test5)", nv
								.widget().getCondition().toString());
					} else if (nv.getKey() == "str_param2") {
						assertEquals("top", this.getNamedValueString(nv));
						assertEquals("checkbox", nv.widget().getType());
						assertEquals("(top,left,bottom,right)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param3") {
						assertEquals("bottom", this.getNamedValueString(nv));
						assertEquals("ordered_list", nv.widget().getType());
						assertEquals("(top,left,bottom,right)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param4") {
						assertEquals("case1", this.getNamedValueString(nv));
						assertEquals("text", nv.widget().getType());
						assertEquals("null", nv.widget().getCondition().toString());
					} else if (nv.getKey() == "vector_param1") {
						assertEquals("0.01, 0.11, 0.22", this
								.getNamedValueString(nv));
						assertEquals(3, nv.widgetSize());
						assertEquals("spin", nv.widget(0).getType());
						assertEquals("0.0<x<1.0", nv.widget(0).getCondition()
								.toString());
						assertEquals("spin", nv.widget(1).getType());
						assertEquals("1.0<x<2.0", nv.widget(1).getCondition()
								.toString());
						assertEquals("spin", nv.widget(2).getType());
						assertEquals("2.0<x<3.0", nv.widget(2).getCondition()
								.toString());
					} else {
						fail("不明なパラメータ名: " + csId + "." + nv.getKey());
					}
				}
			} else if (csId == "config1") {
				for (NamedValueConfigurationWrapper nv : cs.getNamedValueList()) {
					if (nv.getKey() == "int_param1") {
						assertEquals("2", this.getNamedValueString(nv));
						assertEquals("slider", nv.widget().getType());
						assertEquals("0<x<5", nv.widget().getCondition()
								.toString());
					} else if (nv.getKey() == "double_param1") {
						assertEquals("0.22", this.getNamedValueString(nv));
						assertEquals("slider", nv.widget().getType());
						assertEquals("0<x<2", nv.widget().getCondition()
								.toString());
					} else if (nv.getKey() == "str_param1") {
						assertEquals("test2", this.getNamedValueString(nv));
						assertEquals("radio", nv.widget().getType());
						assertEquals("(test1,test2,test3)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param2") {
						assertEquals("right", this.getNamedValueString(nv));
						assertEquals("checkbox", nv.widget().getType());
						assertEquals("(top,left,bottom,right)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param3") {
						assertEquals("top,left", this.getNamedValueString(nv));
						assertEquals("ordered_list", nv.widget().getType());
						assertEquals("(top,left,bottom,right)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param4") {
						assertEquals("case2", this.getNamedValueString(nv));
						assertEquals("text", nv.widget().getType());
						assertEquals("null", nv.widget().getCondition().toString());
					} else if (nv.getKey() == "vector_param1") {
						assertEquals("5.5, 10.5, 15.5", this
								.getNamedValueString(nv));
						assertEquals(3, nv.widgetSize());
						assertEquals("spin", nv.widget(0).getType());
						assertEquals("5.0<x<10.0", nv.widget(0).getCondition()
								.toString());
						assertEquals("spin", nv.widget(1).getType());
						assertEquals("10.0<x<15.0", nv.widget(1).getCondition()
								.toString());
						assertEquals("spin", nv.widget(2).getType());
						assertEquals("15.0<x<20.0", nv.widget(2).getCondition()
								.toString());
					} else {
						fail("不明なパラメータ名: " + csId + "." + nv.getKey());
					}
				}
			} else if (csId == "config2") {
				for (NamedValueConfigurationWrapper nv : cs.getNamedValueList()) {
					if (nv.getKey() == "int_param1") {
						assertEquals("3", this.getNamedValueString(nv));
						assertEquals("slider", nv.widget().getType());
						assertEquals("0<x<10", nv.widget().getCondition()
								.toString());
					} else if (nv.getKey() == "double_param1") {
						assertEquals("0.33", this.getNamedValueString(nv));
						assertEquals("slider", nv.widget().getType());
						assertEquals("0<x<1", nv.widget().getCondition()
								.toString());
					} else if (nv.getKey() == "str_param1") {
						assertEquals("test3", this.getNamedValueString(nv));
						assertEquals("radio", nv.widget().getType());
						assertEquals("(test1,test2,test3,test4,test5)", nv
								.widget().getCondition().toString());
					} else if (nv.getKey() == "str_param2") {
						assertEquals("left", this.getNamedValueString(nv));
						assertEquals("checkbox", nv.widget().getType());
						assertEquals("(top,left,bottom,right)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param3") {
						assertEquals("bottom,right,right", this.getNamedValueString(nv));
						assertEquals("ordered_list", nv.widget().getType());
						assertEquals("(top,left,bottom,right)", nv.widget()
								.getCondition().toString());
					} else if (nv.getKey() == "str_param4") {
						assertEquals("case3", this.getNamedValueString(nv));
						assertEquals("text", nv.widget().getType());
						assertEquals("null", nv.widget().getCondition().toString());
					} else if (nv.getKey() == "vector_param1") {
						assertEquals("0.05, 0.15, 0.25", this
								.getNamedValueString(nv));
						assertEquals(3, nv.widgetSize());
						assertEquals("spin", nv.widget(0).getType());
						assertEquals("0.0<x<1.0", nv.widget(0).getCondition()
								.toString());
						assertEquals("spin", nv.widget(1).getType());
						assertEquals("1.0<x<2.0", nv.widget(1).getCondition()
								.toString());
						assertEquals("spin", nv.widget(2).getType());
						assertEquals("2.0<x<3.0", nv.widget(2).getCondition()
								.toString());
					} else {
						fail("不明なパラメータ名: " + csId + "." + nv.getKey());
					}
				}
			} else {
				fail("不明なコンフィグセット名: " + csId);
			}
		}
	}

	public void testGetDefaultNameSet() throws Exception {
		ComponentConfigurationWrapper config = ComponentConfigurationWrapper
				.create(ComponentMock.mock1, true);
		Set<String> names = config.getDefaultNameSet();
		assertEquals(true, names.contains("int_param1"));
		assertEquals(true, names.contains("double_param1"));
		assertEquals(true, names.contains("str_param1"));
		assertEquals(true, names.contains("str_param2"));
		assertEquals(true, names.contains("str_param3"));
		assertEquals(true, names.contains("str_param4"));
		assertEquals(true, names.contains("vector_param1"));
		assertEquals(false, names.contains("unknown_param1"));
	}

	/** NamedValueConfigurationWrapperのvalueをStringで参照 */
	public String getNamedValueString(NamedValueConfigurationWrapper nv) {
		return nv.getValueAsString();
	}
}
