package jp.go.aist.rtm.toolscommon.model.component.util;

import org.eclipse.emf.common.util.EList;

public interface IPropertyMapUtil {

	String getProperty(String key);

	void setProperty(String key, String value);

	String removeProperty(String key);

	EList<String> getPropertyKeys();

}
