package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.extension.ImportExtension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class ImportExtensionLoader extends AbstractExtensionLoader {

	@Override
	String getExtensionName() { return "importExtension"; }

	@Override
	String getPointId() { return "jp.go.aist.rtm.rtcbuilder.importExtension"; }

	@SuppressWarnings("unchecked")
	@Override
	void addExtension(IConfigurationElement element) throws CoreException {
		Object obj = element.createExecutableExtension( "extensionclass" );
		if ( obj instanceof ImportExtension ) {
			getList().add((ImportExtension)obj);
		}		
	}

}
