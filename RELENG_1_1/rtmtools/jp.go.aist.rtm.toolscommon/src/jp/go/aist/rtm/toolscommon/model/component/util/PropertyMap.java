package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PropertyMapPropertySource;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ui.views.properties.IPropertySource;

public class PropertyMap extends EObjectImpl implements IPropertyMap {

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
		Collections.sort(result);
		return result;
	}

	@Override
	public IPropertyMap getPropertyMap() {
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new PropertyMapPropertySource(this);
		}
		return result;
	}

}
