package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class BasicInfoExtHandler extends CommonExtHandler {
	
	public BasicInfoExtHandler(Class<?> type) {
		super(type);
	}

	@SuppressWarnings("unchecked")
	public Collection<?> keys() {
    	Collection<?> result = super.keys();
    	if( result==null ) return null;
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		String target = ((List<String>)result).get(intIdx);
    		if( target.equals("versionUpLogs") ) {
    			((List<String>)result).set(intIdx, "rtcExt::versionUpLogs");
    		} else if( target.equals("saveProject") ) {
    			((List<String>)result).set(intIdx, "rtcExt::saveProject");
    		}
    	}
    	return result;
    }
	
    public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::versionUpLogs")) {
    		name = "versionUpLogs";
    	} else if(name.equals("rtcExt::saveProject")) {
    		name = "saveProject";
    	}
    	return super.getProperty(obj, name);
    }
}
