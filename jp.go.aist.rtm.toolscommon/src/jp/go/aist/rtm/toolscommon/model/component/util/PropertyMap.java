package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public class PropertyMap implements IPropertyMapUtil {

	Map<String, String> properties;

	public PropertyMap() {
		this.properties = new HashMap<String, String>();
	}

	@Override
	public String getProperty(String key) {
		String result = properties.get(key);
		return result;
	}

	@Override
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	@Override
	public String removeProperty(String key) {
		String old = properties.get(key);
		properties.remove(key);
		return old;
	}

	@Override
	public EList<String> getPropertyKeys() {
		EList<String> result = new BasicEList<String>();
		for (String key : properties.keySet()) {
			result.add(key);
		}
		return result;
	}

}
