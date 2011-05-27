package jp.go.aist.rtm.toolscommon.model.component.util;

import jp.go.aist.rtm.toolscommon.model.component.IPropertyMap;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PropertyMapPropertySource;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ui.views.properties.IPropertySource;

import _SDOPackage.NameValue;

public abstract class CorbaPropertyMap extends EObjectImpl implements
		IPropertyMap {

	public abstract NameValue[] getNameValues();

	public abstract void setNameValues(NameValue[] nvs);

	@Override
	public String getProperty(String key) {
		String result = SDOUtil.findValueAsString(key, getNameValues());
		return result;
	}

	@Override
	public void setProperty(String key, String value) {
		NameValue[] result = SDOUtil
				.storeNameValue(getNameValues(), key, value);
		setNameValues(result);
	}

	@Override
	public String removeProperty(String key) {
		String old = getProperty(key);
		NameValue[] result = SDOUtil.removeNameValue(getNameValues(), key);
		setNameValues(result);
		return old;
	}

	@Override
	public EList<String> getPropertyKeys() {
		EList<String> result = new BasicEList<String>();
		for (NameValue nv : getNameValues()) {
			result.add(nv.name);
		}
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
