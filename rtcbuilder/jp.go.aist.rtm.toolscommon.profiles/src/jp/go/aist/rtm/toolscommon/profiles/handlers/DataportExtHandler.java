package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class DataportExtHandler extends CommonExtHandler {

	public DataportExtHandler(Class<?> type) {
		super(type);
	}

	@SuppressWarnings("unchecked")
	public Collection<?> keys() {
    	Collection<?> result = super.keys();
    	if( result==null ) return null;
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		String target = ((List<String>)result).get(intIdx);
    		if( target.equals("variableName") ) {
    			((List<String>)result).set(intIdx, "rtcExt::variableName");
    		}
    	}
    	return result;
    }
	
    public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::variableName")) {
    		name = "variableName";
    	}
    	return super.getProperty(obj, name);
    }
}
