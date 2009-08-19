package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ConfigurationWidgetTest extends TestCase {
	public void testParseArrayWidget() throws Exception {
		ConfigurationCondition cc = ConfigurationCondition.parse("0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0");
		List<ConfigurationWidget> result = ConfigurationWidget.parseArrayWidget("slider.10, spin, slider, spin", cc);
		assertEquals(5, result.size());
		verifyWidget(result.get(0), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(10, result.get(0).getSliderMaxStep());
		assertEquals(10, result.get(0).clone().getSliderMaxStep());
		verifyWidget(result.get(1), ConfigurationWidget.SPIN, "0.0<x<10.0");
		verifyWidget(result.get(2), ConfigurationWidget.SLIDER, "0.0<x<10.0");
		assertEquals(100, result.get(2).getSliderMaxStep());
		verifyWidget(result.get(3), ConfigurationWidget.SPIN, "0.0<x<10.0");
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
}
