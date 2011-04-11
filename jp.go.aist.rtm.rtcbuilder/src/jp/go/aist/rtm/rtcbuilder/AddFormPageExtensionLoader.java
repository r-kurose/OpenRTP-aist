package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.extension.AddFormPageExtension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;


public class AddFormPageExtensionLoader extends AbstractExtensionLoader {

	@Override
	String getExtensionName() { return "addFormPageExtension"; }

	@Override
	String getPointId() { return "jp.go.aist.rtm.rtcbuilder.addFormPageExtension"; }

	@SuppressWarnings("unchecked")
	@Override
	void addExtension(IConfigurationElement element) throws CoreException {
		Object obj = element.createExecutableExtension( "extensionclass" );
		if ( obj instanceof AddFormPageExtension ) {
			getList().add((AddFormPageExtension)obj);
		}
		
	}

}
