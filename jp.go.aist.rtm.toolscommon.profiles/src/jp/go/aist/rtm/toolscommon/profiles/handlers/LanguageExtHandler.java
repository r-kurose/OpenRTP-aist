package jp.go.aist.rtm.toolscommon.profiles.handlers;

import java.util.Collection;
import java.util.List;

public class LanguageExtHandler extends CommonExtHandler {

	public LanguageExtHandler(Class<?> type) {
		super(type);
	}

	@SuppressWarnings("unchecked")
	public Collection<?> keys() {
    	Collection<?> result = super.keys();
    	if( result==null ) return null;
    	
    	for( int intIdx=0; intIdx<result.size(); intIdx++) {
    		String target = ((List<String>)result).get(intIdx);
    		if( target.equals("targets") ) {
    			((List<String>)result).set(intIdx, "rtcExt::targets");
    		}
    	}
    	return result;
    }
	
    public Object getProperty(Object obj, String name) {
    	if(name.equals("rtcExt::targets")) {
    		name = "targets";
    	}
    	return super.getProperty(obj, name);
    }
}
