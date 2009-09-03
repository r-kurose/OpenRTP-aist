package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.extension.EditorExtension;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class EditorExtensionLoader extends AbstractExtensionLoader {

	@Override
	String getExtensionName() { return "editorExtension"; }

	@Override
	String getPointId() { return "jp.go.aist.rtm.rtcbuilder.editorExtension"; }

	@Override
	void addExtension(IConfigurationElement element) throws CoreException {
		Object obj = element.createExecutableExtension( "extensionclass" );
		if ( obj instanceof EditorExtension ) {
			getList().add((EditorExtension)obj);
		}
		
	}


}
