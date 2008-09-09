package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

import org.ho.yaml.wrapper.DefaultBeanWrapper;

public class CommonDocHandler extends DefaultBeanWrapper {

	public CommonDocHandler(Class type) {
		super(type);
	}

	public Collection keys() {
    	Collection result = null;
    	result = super.keys();
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		Object target = ((List)result).get(intIdx);
    		if( ((String)target).equals("doc") ) {
    			((List)result).set(intIdx, "rtcDoc::doc");
    		}
    	}
    	return result;
    }
	
    public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcDoc::doc")) {
    		name = "doc";
    	}
    	return super.getProperty(obj, name);
    }
}
