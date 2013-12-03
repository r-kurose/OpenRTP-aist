package jp.go.aist.rtm.rtcbuilder.template;

import junit.framework.TestCase;

public class TemplateHelperTest extends TestCase {

	TemplateHelper helper;

	@Override
	protected void setUp() throws Exception {
		helper = new TemplateHelper();
	}

	@SuppressWarnings("static-access")
	public void testVersion() throws Exception {
		String ver = null;
		assertEquals("", helper.getVerMajor(ver));
		assertEquals("", helper.getVerMinor(ver));
		assertEquals("", helper.getVerPatch(ver));
		//
		ver = "";
		assertEquals("", helper.getVerMajor(ver));
		assertEquals("", helper.getVerMinor(ver));
		assertEquals("", helper.getVerPatch(ver));
		ver = "1";
		assertEquals("1", helper.getVerMajor(ver));
		assertEquals("0", helper.getVerMinor(ver));
		assertEquals("0", helper.getVerPatch(ver));
		ver = "1.1";
		assertEquals("1", helper.getVerMajor(ver));
		assertEquals("1", helper.getVerMinor(ver));
		assertEquals("0", helper.getVerPatch(ver));
		ver = "1.1.1";
		assertEquals("1", helper.getVerMajor(ver));
		assertEquals("1", helper.getVerMinor(ver));
		assertEquals("1", helper.getVerPatch(ver));
	}

}
