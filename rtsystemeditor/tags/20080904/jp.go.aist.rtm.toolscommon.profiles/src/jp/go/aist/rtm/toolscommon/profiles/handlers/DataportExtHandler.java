package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class DataportExtHandler extends CommonExtHandler {

	public DataportExtHandler(Class type) {
		super(type);
	}

	public Collection keys() {
    	Collection result = null;
    	result = super.keys();
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		Object target = ((List)result).get(intIdx);
    		if( ((String)target).equals("varname") ) {
    			((List)result).set(intIdx, "rtcExt::varname");
    		}
    	}
    	return result;
    }
	
    public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::varname")) {
    		name = "varname";
    	}
    	return super.getProperty(obj, name);
    }
}
