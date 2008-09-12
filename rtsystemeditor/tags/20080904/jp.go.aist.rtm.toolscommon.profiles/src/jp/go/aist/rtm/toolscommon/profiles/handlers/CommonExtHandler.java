package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class CommonExtHandler extends CommonDocHandler {

	public CommonExtHandler(Class type) {
		super(type);
	}

	public Collection keys() {
    	Collection result = null;
    	result = super.keys();
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		Object target = ((List)result).get(intIdx);
    		if( ((String)target).equals("position") ) {
    			((List)result).set(intIdx, "rtcExt::position");
    		}
    	}
    	return result;
    }
	
	public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::position")) {
    		name = "position";
        	return super.getProperty(obj, name).toString();
    	}
    	return super.getProperty(obj, name);
    }
}
