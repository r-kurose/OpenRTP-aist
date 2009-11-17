package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.extension.ExportExtension;
import jp.go.aist.rtm.rtcbuilder.extension.ImportExtension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class ExportExtensionLoader extends AbstractExtensionLoader {

	@Override
	String getExtensionName() { return "exportExtension"; }

	@Override
	String getPointId() { return "jp.go.aist.rtm.rtcbuilder.exportExtension"; }

	@Override
	void addExtension(IConfigurationElement element) throws CoreException {
		Object obj = element.createExecutableExtension( "extensionclass" );
		if ( obj instanceof ExportExtension ) {
			getList().add((ExportExtension)obj);
		}		
	}

}
