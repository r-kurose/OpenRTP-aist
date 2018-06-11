package jp.go.aist.rtm.toolscommon.profiles.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class JSONUtilTest {

	@Test
	public void testClazz() {
		String json = null;
		{
			MockUser obj = new MockUser();
			obj.name = "Taro";
			obj.email = "taro@example.com";
			json = JSONUtil.toJSON(obj);
			assertEquals("{\r\n" //
					+ "  \"name\" : \"Taro\",\r\n" //
					+ "  \"email\" : \"taro@example.com\"\r\n" //
					+ "}", json);
		}
		{
			MockUser obj = JSONUtil.parse(MockUser.class, json);
			assertEquals("Taro", obj.name);
			assertEquals("taro@example.com", obj.email);
		}
	}

	@Test
	public void testMapList() {
		String json = null;
		{
			List<Map<String, String>> list = new ArrayList<>();
			{
				Map<String, String> map = new HashMap<>();
				map.put("name", "Taro");
				map.put("email", "taro@example.com");
				list.add(map);
			}
			{
				Map<String, String> map = new HashMap<>();
				map.put("name", "Hanako");
				map.put("email", "hanako@example.com");
				list.add(map);
			}
			json = JSONUtil.toJSON(list);
			assertEquals("[ {\r\n" //
					+ "  \"name\" : \"Taro\",\r\n" //
					+ "  \"email\" : \"taro@example.com\"\r\n" //
					+ "}, {\r\n" //
					+ "  \"name\" : \"Hanako\",\r\n" //
					+ "  \"email\" : \"hanako@example.com\"\r\n" //
					+ "} ]" //
			, json);
		}
		{
			@SuppressWarnings("unchecked")
			List<Map<String, String>> obj = JSONUtil.parse(List.class, json);
			assertEquals(2, obj.size());
			{
				Map<String, String> map = obj.get(0);
				assertEquals("Taro", map.get("name"));
				assertEquals("taro@example.com", map.get("email"));
			}
			{
				Map<String, String> map = obj.get(1);
				assertEquals("Hanako", map.get("name"));
				assertEquals("hanako@example.com", map.get("email"));
			}
		}
	}

	public static class MockUser {
		public String name;
		public String email;
	}

}
