package jp.go.aist.rtm.toolscommon.util;

/**
 * staticメソッドのみ定義
 */
public class RTMixin {

	public static boolean isBlank(String s) {
		return (s == null || s.isEmpty());
	}

	public static boolean eql(String s1, String s2) {
		return (s1 != null && s1.equals(s2));
	}

	public static boolean eql(RTC.PortProfile o1, RTC.PortProfile o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		if (o2 == null) {
			return false;
		}
		if (!eql(o1.port_ref, o2.port_ref)) {
			return false;
		}
		if (!eql(o1.connector_profiles, o2.connector_profiles)) {
			return false;
		}
		return true;
	}

	public static boolean eql(RTC.PortProfile[] o1, RTC.PortProfile[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(RTC.ConnectorProfile o1, RTC.ConnectorProfile o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		if (o2 == null) {
			return false;
		}
		if (!eql(o1.connector_id, o2.connector_id)) {
			return false;
		}
		return true;
	}

	public static boolean eql(RTC.ConnectorProfile[] o1,
			RTC.ConnectorProfile[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(RTC.ExecutionContext o1, RTC.ExecutionContext o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		return o1.equals(o2);
	}

	public static boolean eql(RTC.ExecutionContext[] o1,
			RTC.ExecutionContext[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(RTC.ExecutionContextProfile o1,
			RTC.ExecutionContextProfile o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		if (o2 == null) {
			return false;
		}
		if ((o1.kind == o2.kind) && (o1.rate == o2.rate)
				&& eql(o1.owner, o2.owner)
				&& eql(o1.participants, o2.participants)
				&& eql(o1.properties, o2.properties)) {
			return true;
		}
		return false;
	}

	public static boolean eql(RTC.RTObject o1, RTC.RTObject o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		return o1.equals(o2);
	}

	public static boolean eql(RTC.RTObject[] o1, RTC.RTObject[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(RTC.ComponentProfile o1, RTC.ComponentProfile o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		if (o2 == null) {
			return false;
		}
		if (eql(o1.category, o2.category)
				&& eql(o1.description, o2.description)
				&& eql(o1.instance_name, o2.instance_name)
				&& eql(o1.type_name, o2.type_name) && eql(o1.vendor, o2.vendor)
				&& eql(o1.version, o2.version)
				&& eql(o1.port_profiles, o2.port_profiles)) {
			return true;
		}
		return false;
	}

	public static boolean eql(RTC.ComponentProfile[] o1,
			RTC.ComponentProfile[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(_SDOPackage.ConfigurationSet o1,
			_SDOPackage.ConfigurationSet o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		if (o2 == null) {
			return false;
		}
		if (eql(o1.id, o2.id)
				&& eql(o1.configuration_data, o2.configuration_data)) {
			return true;
		}
		return false;
	}

	public static boolean eql(_SDOPackage.ConfigurationSet[] o1,
			_SDOPackage.ConfigurationSet[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean eql(_SDOPackage.NameValue o1, _SDOPackage.NameValue o2) {
		if (o1 == null) {
			return (o2 == null);
		}
		if (o2 == null) {
			return false;
		}
		if (o1.name == null || o1.value == null) {
			return false;
		}
		if (!o1.name.equals(o2.name) || !o1.value.equal(o2.value)) {
			return false;
		}
		return true;
	}

	public static boolean eql(_SDOPackage.NameValue[] o1,
			_SDOPackage.NameValue[] o2) {
		if (!eql_array(o1, o2)) {
			return false;
		}
		for (int i = 0; i < o1.length; i++) {
			if (!eql(o1[i], o2[i])) {
				return false;
			}
		}
		return true;
	}

	static <T> boolean eql_array(T[] o1, T[] o2) {
		if (o1 == null) {
			return o2 == null;
		}
		if (o2 == null) {
			return false;
		}
		if (o1.length != o2.length) {
			return false;
		}
		return true;
	}

	public static boolean eql(Object o1, Object o2) {
		if (o1 == null) {
			return o2 == null;
		} else {
			return o1.equals(o2);
		}
	}

}
