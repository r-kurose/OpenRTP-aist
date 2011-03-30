package jp.go.aist.rtm.toolscommon.model.component.util;

import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import _SDOPackage.NameValue;

public abstract class CorbaPropertyMap implements IPropertyMapUtil {

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

}
