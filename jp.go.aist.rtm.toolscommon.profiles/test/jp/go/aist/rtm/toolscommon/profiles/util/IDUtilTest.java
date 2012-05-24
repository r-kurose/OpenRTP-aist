package jp.go.aist.rtm.toolscommon.profiles.util;

import org.openrtp.namespaces.rtc.version02.BasicInfo;

import junit.framework.TestCase;

import static jp.go.aist.rtm.toolscommon.profiles.util.IDUtil.*;

public class IDUtilTest extends TestCase {

	public void testRTCId() throws Exception {
		RTCId id = new RTCId(null, null, null, null);
		assertEquals("RTC::::", id.toString());

		id = new RTCId("vendor1", null, null, null);
		assertEquals("RTC:vendor1:::", id.toString());

		id = new RTCId("vendor1", "category1", null, null);
		assertEquals("RTC:vendor1:category1::", id.toString());

		id = new RTCId("vendor1", "category1", "SampleComponent", null);
		assertEquals("RTC:vendor1:category1:SampleComponent:", id.toString());

		id = new RTCId("vendor1", "category1", "SampleComponent", "1.0");
		assertEquals("RTC:vendor1:category1:SampleComponent:1.0", id.toString());
	}

	public void testRTSId() throws Exception {
		RTSId id = new RTSId(null, null, null);
		assertEquals("RTSystem:::", id.toString());

		id = new RTSId("vendor1", null, null);
		assertEquals("RTSystem:vendor1::", id.toString());

		id = new RTSId("vendor1", "sys1", null);
		assertEquals("RTSystem:vendor1:sys1:", id.toString());

		id = new RTSId("vendor1", "sys1", "1.0");
		assertEquals("RTSystem:vendor1:sys1:1.0", id.toString());
	}

	public void testParseRTCId() throws Exception {
		RTCId id = parseRTCId("RTC:vendor1:category1:SampleComponent:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("category1", id.category);
		assertEquals("SampleComponent", id.name);
		assertEquals("1.0", id.version);

		id = parseRTCId("RTC:vendor1:composite.ECShared:SampleComponent:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("composite.ECShared", id.category);
		assertEquals("SampleComponent", id.name);
		assertEquals("1.0", id.version);

		id = parseRTCId("RTC:vendor1:category1:SampleComponent:");
		assertEquals("vendor1", id.vendor);
		assertEquals("category1", id.category);
		assertEquals("SampleComponent", id.name);
		assertEquals("", id.version);

		// 旧フォーマット
		id = parseRTCId("RTC:vendor1.category1.SampleComponent:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("category1", id.category);
		assertEquals("SampleComponent", id.name);
		assertEquals("1.0", id.version);
	}

	public void testParseRTCIdError() throws Exception {
		RTCId id = parseRTCId(null);
		assertNull(id);

		id = parseRTCId("RTC:vendor1:SampleComponent:1.0");
		assertNull(id);

		// 旧フォーマット
		id = parseRTCId("RTC:vendor1:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("", id.category);
		assertEquals("", id.name);
		assertEquals("1.0", id.version);

		id = parseRTCId("RTC:vendor1.category1:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("category1", id.category);
		assertEquals("", id.name);
		assertEquals("1.0", id.version);
	}

	public void testCreateRTCIdBy() throws Exception {
		BasicInfo bi = new BasicInfo();
		bi.setVendor("vendor1");
		bi.setCategory("category1");
		bi.setName("comp1");
		bi.setVersion("1.0");
		RTCId id = createRTCIdBy(bi);
		assertEquals("RTC:vendor1:category1:comp1:1.0", id.toString());
	}

	public void testParseRTSId() throws Exception {
		RTSId id = parseRTSId("RTSystem:vendor1:sys1:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("sys1", id.name);
		assertEquals("1.0", id.version);

		id = parseRTSId("RTSystem:vendor1:test.sys:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("test.sys", id.name);
		assertEquals("1.0", id.version);

		id = parseRTSId("RTSystem:vendor1:sys1:");
		assertEquals("vendor1", id.vendor);
		assertEquals("sys1", id.name);
		assertEquals("", id.version);

		// 旧フォーマット
		id = parseRTSId("RTSystem:vendor1.sys1:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("sys1", id.name);
		assertEquals("1.0", id.version);
	}

	public void testParseRTSIdError() throws Exception {
		RTSId id = parseRTSId(null);
		assertNull(id);

		id = parseRTSId("RTSystem:1.0");
		assertNull(id);

		// 旧フォーマット
		id = parseRTSId("RTSystem:vendor1:1.0");
		assertEquals("vendor1", id.vendor);
		assertEquals("", id.name);
		assertEquals("1.0", id.version);
	}

}
