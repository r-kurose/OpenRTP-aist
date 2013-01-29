package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.Map;

import junit.framework.TestCase;

public class NamedValueConfigurationWrapperTest extends TestCase {

	public void testSetWidgetAndCondition() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key");
		// 制約条件なし
		nv.setWidgetAndCondition("text", null);
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// sliderは最小、最大設定が必要
		nv.setWidgetAndCondition("slider", "0<x<100");
		assertEquals(true, nv.widget().isSlider());
		assertEquals("0", nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("slider", "x<100");
		assertEquals(false, nv.widget().isSlider());
		assertEquals(true, nv.widget().isText());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("slider", "");
		assertEquals(false, nv.widget().isSlider());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// spinnerは最大、最小設定が必要
		nv.setWidgetAndCondition("spin", "0<x<100");
		assertEquals(true, nv.widget().isSpinner());
		assertEquals("0", nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("spin", "x<100");
		assertEquals(false, nv.widget().isSpinner());
		assertEquals(true, nv.widget().isText());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("spin", "");
		assertEquals(false, nv.widget().isSpinner());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// radioは列挙設定が必要
		nv.setWidgetAndCondition("radio", "(100,200,300)");
		assertEquals(true, nv.widget().isRadio());
		assertEquals(true, nv.widget().getCondition().hasEnumList());
		assertEquals(3, nv.widget().getCondition().getEnumList().size());

		nv.setWidgetAndCondition("radio", "x<100");
		assertEquals(false, nv.widget().isRadio());
		assertEquals(true, nv.widget().isText());
		assertEquals(false, nv.widget().getCondition().hasEnumList());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("radio", "");
		assertEquals(false, nv.widget().isRadio());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// checkboxは列挙設定が必要
		nv.setWidgetAndCondition("checkbox", "(top,left,bottom,right)");
		assertEquals(true, nv.widget().isCheckbox());
		assertEquals(true, nv.widget().getCondition().hasEnumList());
		assertEquals(4, nv.widget().getCondition().getEnumList().size());

		nv.setWidgetAndCondition("checkbox", "x<100");
		assertEquals(false, nv.widget().isCheckbox());
		assertEquals(true, nv.widget().isText());
		assertEquals(false, nv.widget().getCondition().hasEnumList());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("checkbox", "");
		assertEquals(false, nv.widget().isCheckbox());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// ordered_listは列挙設定が必要
		nv.setWidgetAndCondition("ordered_list", "(top,left,bottom,right)");
		assertEquals(true, nv.widget().isOrderedList());
		assertEquals(true, nv.widget().getCondition().hasEnumList());
		assertEquals(4, nv.widget().getCondition().getEnumList().size());

		nv.setWidgetAndCondition("ordered_list", "x<100");
		assertEquals(false, nv.widget().isOrderedList());
		assertEquals(true, nv.widget().isText());
		assertEquals(false, nv.widget().getCondition().hasEnumList());
		assertEquals(null, nv.widget().getCondition().getMin());
		assertEquals("100", nv.widget().getCondition().getMax());

		nv.setWidgetAndCondition("ordered_list", "");
		assertEquals(false, nv.widget().isOrderedList());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		// 制約条件エラーの場合はtext
		nv.setWidgetAndCondition("radio", "(100,200"); // 列挙閉じ括弧なし
		assertEquals(false, nv.widget().isRadio());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());

		nv.setWidgetAndCondition("slider", "0<x<100a"); // 数値誤り
		assertEquals(false, nv.widget().isSlider());
		assertEquals(true, nv.widget().isText());
		assertEquals(true, nv.widget().getCondition().isNull());


		// TODO widgetの配列表記

		// 配列表記
		nv.setWidgetAndCondition("slider", "0<x<1, 1<x<2");
		assertEquals(2, nv.widgetSize());
		assertEquals(true, nv.widget(0).isSlider());
		assertEquals("0", nv.widget(0).getCondition().getMin());
		assertEquals("1", nv.widget(0).getCondition().getMax());
		assertEquals(true, nv.widget(1).isSlider());
		assertEquals("1", nv.widget(1).getCondition().getMin());
		assertEquals("2", nv.widget(1).getCondition().getMax());

		// 配列表記(条件エラーの要素はtext)
		nv.setWidgetAndCondition("slider", "0<x<1, 1<x<2a");
		assertEquals(2, nv.widgetSize());
		assertEquals(true, nv.widget(0).isSlider());
		assertEquals("0", nv.widget(0).getCondition().getMin());
		assertEquals("1", nv.widget(0).getCondition().getMax());
		assertEquals(false, nv.widget(1).isSlider());
		assertEquals(true, nv.widget(1).isText());
		assertEquals(true, nv.widget(1).getCondition().isNull());

		// ハッシュ表記
		nv.setWidgetAndCondition("slider", "{key0:0<x<1, key1: 1<x<2 }");
		assertEquals(2, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isSlider());
		assertEquals("0", nv.widget("key0").getCondition().getMin());
		assertEquals("1", nv.widget("key0").getCondition().getMax());
		assertEquals(true, nv.widget("key1").isSlider());
		assertEquals("1", nv.widget("key1").getCondition().getMin());
		assertEquals("2", nv.widget("key1").getCondition().getMax());

		// ハッシュ表記(条件エラーの要素はtext)
		nv.setWidgetAndCondition("slider", "{ key0:0<x<1, key1: 1<x< }");
		assertEquals(2, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isSlider());
		assertEquals("0", nv.widget("key0").getCondition().getMin());
		assertEquals("1", nv.widget("key0").getCondition().getMax());
		assertEquals(false, nv.widget("key1").isSlider());
		assertEquals(true, nv.widget("key1").isText());
		assertEquals(true, nv.widget("key1").getCondition().isNull());
	}

	public void testLoadWidgetValue() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", null);
		nv.setValue("1, 2,3");
		// 制約条件なし
		nv.setWidgetAndCondition("text", null);
		nv.loadWidgetValue();
		assertEquals(true, nv.widget().isText());
		assertEquals(false, nv.widget().isValueModified());
		assertEquals("1, 2,3", nv.widget().getValue());
		nv.widget().setValue("4");
		assertEquals(true, nv.widget().isValueModified());
		// 編集中状態チェック
		assertEquals(true, nv.isLoadedWidgetValue());
		nv.saveWidgetValue();
		assertEquals(false, nv.isLoadedWidgetValue());

		// 配列の場合
		nv.setValue("1, 2,3");

		// 値が配列、widgetが単体
		nv.setWidgetAndCondition("slider", "0<x<100");
		nv.loadWidgetValue();
		assertEquals(true, nv.widget().isSlider());
		assertEquals(false, nv.widget().isValueModified());
		assertEquals("1, 2,3", nv.widget().getValue());
		nv.widget().setValue("4");
		assertEquals(true, nv.widget().isValueModified());

		// 配列 (valueがwidgetより多い)
		nv.setWidgetAndCondition("text", "0<x<1, 1<x<2");
		nv.loadWidgetValue();
		assertEquals(2, nv.widgetSize());
		assertEquals(true, nv.widget(0).isText());
		assertEquals(false, nv.widget(0).isValueModified());
		assertEquals("1", nv.widget(0).getValue());
		assertEquals(true, nv.widget(1).isText());
		assertEquals(false, nv.widget(1).isValueModified());
		assertEquals("2", nv.widget(1).getValue());
		nv.widget(0).setValue("4");
		assertEquals(true, nv.widget(0).isValueModified());
		nv.widget(1).setValue("5");
		assertEquals(true, nv.widget(0).isValueModified());

		// 配列 (valueがwidgetより少ない)
		nv.setWidgetAndCondition("text", "0<x<1, 1<x<2, 2<x<3, 3<x<4");
		nv.loadWidgetValue();
		assertEquals(4, nv.widgetSize());
		assertEquals(true, nv.widget(0).isText());
		assertEquals("1", nv.widget(0).getValue());
		assertEquals(true, nv.widget(1).isText());
		assertEquals("2", nv.widget(1).getValue());
		assertEquals(true, nv.widget(2).isText());
		assertEquals("3", nv.widget(2).getValue());
		assertEquals(true, nv.widget(3).isText());
		assertEquals("", nv.widget(3).getValue());

		// ハッシュの場合
		nv.setValue("{key0:1, key1: 2,key2:3 }");

		// 値がハッシュ、widgetが単体
		nv.setWidgetAndCondition("slider", "0<x<100");
		nv.loadWidgetValue();
		assertEquals(true, nv.widget().isSlider());
		assertEquals(false, nv.widget().isValueModified());
		assertEquals("{key0:1, key1: 2,key2:3 }", nv.widget().getValue());
		nv.widget().setValue("4");
		assertEquals(true, nv.widget().isValueModified());

		// ハッシュ (valueがwidgetより多い)
		nv.setWidgetAndCondition("text", "{key0: 0<x<1, key1:1<x<2 }");
		nv.loadWidgetValue();
		assertEquals(2, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isText());
		assertEquals(false, nv.widget("key0").isValueModified());
		assertEquals("1", nv.widget("key0").getValue());
		assertEquals(true, nv.widget("key1").isText());
		assertEquals(false, nv.widget("key1").isValueModified());
		assertEquals("2", nv.widget("key1").getValue());
		nv.widget("key0").setValue("4");
		assertEquals(true, nv.widget("key0").isValueModified());
		nv.widget("key1").setValue("5");
		assertEquals(true, nv.widget("key1").isValueModified());

		// ハッシュ (valueがwidgetより少ない)
		nv.setWidgetAndCondition("text", "{ key0: 0<x<1, key1:1<x<2, key2: 2<x<3,key3: 3<x<4}");
		nv.loadWidgetValue();
		assertEquals(4, nv.widgetKeySet().size());
		assertEquals(true, nv.widget("key0").isText());
		assertEquals("1", nv.widget("key0").getValue());
		assertEquals(true, nv.widget("key1").isText());
		assertEquals("2", nv.widget("key1").getValue());
		assertEquals(true, nv.widget("key2").isText());
		assertEquals("3", nv.widget("key2").getValue());
		assertEquals(true, nv.widget("key3").isText());
		assertEquals("", nv.widget("key3").getValue());
	}

	public void testSaveWidgetValue() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", null);
		// 制約条件なし
		nv.setWidgetAndCondition("text", null);
		nv.widget().setValue("1");
		nv.saveWidgetValue();
		assertEquals("1", nv.getValueAsString());
		assertEquals(false, nv.widget().isValueModified());
		nv.widget().setValue("11");
		assertEquals(true, nv.widget().isValueModified());

		// spinner
		nv.setWidgetAndCondition("spinner", "0<x<100");
		nv.widget().setValue("10");
		nv.saveWidgetValue();
		assertEquals("10", nv.getValueAsString());
		assertEquals(false, nv.widget().isValueModified());
		nv.widget().setValue("11");
		assertEquals(true, nv.widget().isValueModified());

		// 配列
		nv.setWidgetAndCondition("text", "0<x<1, 1<x<2");
		nv.widget(0).setValue("0.1");
		nv.widget(1).setValue("1.1");
		nv.saveWidgetValue();
		assertEquals("0.1, 1.1", nv.getValueAsString());
		assertEquals(false, nv.widget(0).isValueModified());
		nv.widget(0).setValue("11");
		assertEquals(true, nv.widget(0).isValueModified());
		assertEquals(false, nv.widget(1).isValueModified());
		nv.widget(1).setValue("11");
		assertEquals(true, nv.widget(1).isValueModified());

		// ハッシュ
		nv.setWidgetAndCondition("text", "{key0:0<x<1, key1:1<x<2 }");
		nv.widget("key0").setValue("0.1");
		nv.widget("key1").setValue("1.1");
		nv.saveWidgetValue();
		assertEquals("{key0: 0.1, key1: 1.1}", nv.getValueAsString());
		assertEquals(false, nv.widget("key0").isValueModified());
		nv.widget("key0").setValue("11");
		assertEquals(true, nv.widget("key0").isValueModified());
		assertEquals(false, nv.widget("key1").isValueModified());
		nv.widget("key1").setValue("11");
		assertEquals(true, nv.widget("key1").isValueModified());
	}

	public void testParseMap() throws Exception {
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", null);
		Map<String, String> map;

		// {key0:1, key1: 2,key2:3 }
		map = nv.parseMap("{key0:1, key1: 2,key2:3 }");
		assertEquals(3, map.keySet().size());
		assertEquals("1", map.get("key0"));
		assertEquals("2", map.get("key1"));
		assertEquals("3", map.get("key2"));

		// {key0:, key1: 2 } // 値なし
		map = nv.parseMap("{key0:, key1: 2 }");
		assertEquals(2, map.keySet().size());
		assertEquals("", map.get("key0"));
		assertEquals("2", map.get("key1"));

		// {:1, key1: 2 } // キーなし
		map = nv.parseMap("{:1, key1: 2 }");
		assertEquals(1, map.keySet().size());
		assertEquals("2", map.get("key1"));
	}

	public void testClone() throws Exception {
		String value = "hoge";
		NamedValueConfigurationWrapper nv = new NamedValueConfigurationWrapper("key", value);
		nv.setWidgetAndCondition("slider", "0<x<100");
		NamedValueConfigurationWrapper clone = nv.clone();
		assertEquals(nv.getKey(), clone.getKey());
		assertEquals(nv.getValueAsString(), clone.getValueAsString());
		assertEquals(nv.isKeyModified(), clone.isKeyModified());
		assertEquals(nv.isValueModified(), clone.isValueModified());
		assertEquals(nv.widgetSize(), clone.widgetSize());
		assertEquals(nv.widgetKeySet(), clone.widgetKeySet());
	}
}
