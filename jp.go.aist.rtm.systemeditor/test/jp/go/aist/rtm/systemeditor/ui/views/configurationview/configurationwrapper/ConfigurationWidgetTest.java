package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ConfigurationWidgetTest extends TestCase {
	public void testParseArrayWidget() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0");
		List<ConfigurationWidget> result = ConfigurationWidget.parseArrayWidget("slider.1, spin.10, slider, spin", cc);
		assertEquals(5, result.size());
		verifyWidget(result.get(0), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(10, result.get(0).getSliderMaxStep());
		assertEquals(10, result.get(0).clone().getSliderMaxStep());
		verifyWidget(result.get(1), ConfigurationWidget.SPIN, "0.0<x<10.0");
		assertEquals(100, result.get(1).getSpinIncrement());
		assertEquals(100, result.get(1).clone().getSpinIncrement());
		verifyWidget(result.get(2), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(10, result.get(2).getSliderMaxStep());
		verifyWidget(result.get(3), ConfigurationWidget.SPIN, "0.0<x<10.0");
		assertEquals(1, result.get(3).getSpinIncrement());
		verifyWidget(result.get(4), ConfigurationWidget.SPIN, "0.0<x<10.0");
	}
	
	public void testParseArrayWidget2() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("0.0<x<10.0, 0.1<x<10.0, 0.2<x<10.0, 0.3<x<10.0, 0.4<x<10.0");
		List<ConfigurationWidget> result = ConfigurationWidget.parseArrayWidget("slider", cc);
		assertEquals(5, result.size());
		verifyWidget(result.get(0), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		verifyWidget(result.get(1), ConfigurationWidget.SLIDER, "0.1<x<10.0");
		verifyWidget(result.get(2), ConfigurationWidget.SLIDER, "0.2<x<10.0");
		verifyWidget(result.get(3), ConfigurationWidget.SLIDER, "0.3<x<10.0");
		verifyWidget(result.get(4), ConfigurationWidget.SLIDER, "0.4<x<10.0");
	}
	
	public void testParseHashWidget() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("{key0:(ichi,one), key1:0.0<x<10.0 }");
		Map<String, ConfigurationWidget> result = ConfigurationWidget.parseHashWidget("{key0:radio, key1:spin}", cc);
		verifyWidget(result.get("key0"), ConfigurationWidget.RADIO, "(ichi,one)");
		verifyWidget(result.get("key1"), ConfigurationWidget.SPIN, "0.0<x<10.0");
	}

	public void testParseHashWidget2() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("{key0:0.0<x<10.0, key1:0.1<x<10.0}");
		Map<String, ConfigurationWidget> result = ConfigurationWidget.parseHashWidget("slider", cc);
		verifyWidget(result.get("key0"), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		verifyWidget(result.get("key1"), ConfigurationWidget.SLIDER, "0.1<x<10.0");
	}

	public void testParseHashWidget3() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("{key0:(top,left,bottom,right), key1:0.0<x<10.0 }");
		Map<String, ConfigurationWidget> result = ConfigurationWidget.parseHashWidget("{key0:checkbox, key1:spin}", cc);
		verifyWidget(result.get("key0"), ConfigurationWidget.CHECKBOX, "(top,left,bottom,right)");
		verifyWidget(result.get("key1"), ConfigurationWidget.SPIN, "0.0<x<10.0");
	}

	public void testParseHashWidget4() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("{key0:(top,left,bottom,right), key1:0.0<x<10.0 }");
		Map<String, ConfigurationWidget> result = ConfigurationWidget.parseHashWidget("{key0:ordered_list, key1:spin}", cc);
		verifyWidget(result.get("key0"), ConfigurationWidget.ORDERED_LIST, "(top,left,bottom,right)");
		verifyWidget(result.get("key1"), ConfigurationWidget.SPIN, "0.0<x<10.0");
	}

	public void testSimpleWidget() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse(" 0.0<x<10.0 ");
		List<ConfigurationWidget> result = ConfigurationWidget.parseSimpleWidget(" slider ", cc);
		assertEquals(1, result.size());
		verifyWidget(result.get(0), ConfigurationWidget.SLIDER, "0.0<x<10.0");		
	}
	private void verifyWidget(ConfigurationWidget widget, String type, String condition) {
		assertEquals(type, widget.getType());
		assertEquals(condition, widget.getCondition().toString());
	}

	public void testParseSliderMaxStep() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition
				.parse("0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0");
		List<ConfigurationWidget> result = ConfigurationWidget
				.parseArrayWidget(
						"slider, slider.10, slider.0.2, slider.0, slider.a", cc);
		assertEquals(5, result.size());
		verifyWidget(result.get(0), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(10, result.get(0).getSliderMaxStep());
		verifyWidget(result.get(1), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(1, result.get(1).getSliderMaxStep());
		verifyWidget(result.get(2), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(50, result.get(2).getSliderMaxStep());
		verifyWidget(result.get(3), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(10, result.get(3).getSliderMaxStep());
		verifyWidget(result.get(4), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(10, result.get(4).getSliderMaxStep());
	}

	public void testParseSpinIncrement() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition
				.parse("0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.00, 0.0<x<10.0, 0.0<x<10.0");
		List<ConfigurationWidget> result = ConfigurationWidget
				.parseArrayWidget("spin, spin.10, spin.0.2, spin.0, spin.a", cc);
		assertEquals(5, result.size());
		verifyWidget(result.get(0), ConfigurationWidget.SPIN, "0.0<x<10.0");
		assertEquals(1, result.get(0).getSpinIncrement());
		verifyWidget(result.get(1), ConfigurationWidget.SPIN, "0.0<x<10.0");
		assertEquals(100, result.get(1).getSpinIncrement());
		verifyWidget(result.get(2), ConfigurationWidget.SPIN, "0.0<x<10.00");
		assertEquals(20, result.get(2).getSpinIncrement());
		verifyWidget(result.get(3), ConfigurationWidget.SPIN, "0.0<x<10.0");
		assertEquals(1, result.get(3).getSpinIncrement());
		verifyWidget(result.get(4), ConfigurationWidget.SPIN, "0.0<x<10.0");
		assertEquals(1, result.get(4).getSpinIncrement());
	}

}
