package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class CommonExtHandler extends CommonDocHandler {

	public CommonExtHandler(Class<?> type) {
		super(type);
	}

	@SuppressWarnings("unchecked")
	public Collection<?> keys() {
    	Collection<?> result = super.keys();
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		String target = ((List<String>)result).get(intIdx);
    		if( target.equals("position") ) {
    			((List<String>)result).set(intIdx, "rtcExt::position");
    		} else if( target.equals("properties") ) {
    			((List<String>)result).set(intIdx, "rtcExt::properties");
    		} else if( target.equals("comment") ) {
    			((List<String>)result).set(intIdx, "rtcExt::comment");
    		}
    	}
    	return result;
    }
	
	public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::position")) {
    		name = "position";
        	return super.getProperty(obj, name).toString();
    	} else 	if(name.equals("rtcExt::properties")) {
    		name = "properties";
        	return super.getProperty(obj, name);
    	} else if(name.equals("rtcExt::comment")) {
    		name = "comment";
    	}
    	return super.getProperty(obj, name);
    }
}
