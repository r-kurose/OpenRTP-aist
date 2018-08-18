package jp.go.aist.rtm.toolscommon.profiles.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	public static <T> T parse(Class<T> clazz, String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (T) mapper.readValue(json, clazz);
		} catch (IOException e) {
			return null;
		}
	}

	public static String toJSON(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			return null;
		}
	}

}
