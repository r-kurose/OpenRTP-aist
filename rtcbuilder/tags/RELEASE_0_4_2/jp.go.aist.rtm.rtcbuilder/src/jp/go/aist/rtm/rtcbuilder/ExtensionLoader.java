package jp.go.aist.rtm.rtcbuilder;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class ExtensionLoader {
	// 拡張ポイントID
	public static final String EXTENSION_POINT_ID = "jp.go.aist.rtm.rtcbuilder.generateManager";
	// Manager
	private List<GenerateManager> managerList = new ArrayList<GenerateManager>();

	/**
	 * 拡張オブジェクトをロードしてリストに格納する。
	 * @throws CoreException 
	 */
	public void loadExtensions() throws CoreException {
		// 拡張ポイントの取得
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint( EXTENSION_POINT_ID );		
		if( point == null ) return;		
		
		// 拡張宣言のロード
		IExtension[] extensions = point.getExtensions();
		for( int index = 0; index < extensions.length; index++ ) {
			// 拡張宣言（extensionタグ）ごとに、下位のタグを処理する
			IConfigurationElement[] cfgElems = extensions[index].getConfigurationElements();
			for(int intext = 0; intext < cfgElems.length; intext++) {
				IConfigurationElement cfgElem = cfgElems[intext];
				
				if ( "manager".equals( cfgElem.getName() ) ) {
					processManager( cfgElem );
				}
			}
		}
	}

	protected void processManager(IConfigurationElement cfgElem) throws CoreException {
		try {
			if ( cfgElem.isValid() ) {
				Object obj = cfgElem.createExecutableExtension( "managerclass" );
				if ( obj instanceof GenerateManager ) {
					GenerateManager manager = (GenerateManager)obj;
					managerList.add(manager);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<GenerateManager> getManagerList() {
		return managerList;
	}
}
