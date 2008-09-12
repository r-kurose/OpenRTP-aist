package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class BasicInfoExtHandler extends CommonDocHandler {
	
	public BasicInfoExtHandler(Class type) {
		super(type);
	}

	public Collection keys() {
    	Collection result = null;
    	result = super.keys();
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		Object target = ((List)result).get(intIdx);
    		if( ((String)target).equals("versionUpLog") ) {
    			((List)result).set(intIdx, "rtcExt::versionUpLog");
    		}
    	}
    	return result;
    }
	
    public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::versionUpLog")) {
    		name = "versionUpLog";
    	}
    	return super.getProperty(obj, name);
    }
}
