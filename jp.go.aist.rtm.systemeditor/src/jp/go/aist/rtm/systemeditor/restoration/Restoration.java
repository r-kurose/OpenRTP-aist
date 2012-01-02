package jp.go.aist.rtm.systemeditor.restoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import _SDOPackage.Configuration;

/**
 * ロード時に復元を行うクラス
 */
public class Restoration {
	/**
	 * 実行メイン
	 * 
	 * @param systemDiagram
	 *            システムダイアグラム
	 * @param result
	 *            結果格納
	 */
	public static void execute(SystemDiagram systemDiagram, Result result) {
		result.setSuccess(true);

		processAllPing(result, systemDiagram);

		processAllRestoreConfigurationSet(result, systemDiagram);

//		processAllConnect(result, systemDiagram);

		//processAllStart(result, systemDiagram);
	}

	/**
	 * RtcLinkのXMLに含まれるすべてのコンフィグレーションを復元する。
	 * 現状はCorbaコンポーネントのみに対応
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	public static void processAllRestoreConfigurationSet(Result result,
			SystemDiagram systemDiagram) {

		for (Component c : systemDiagram.getRegisteredComponents()) {
			List<CorbaConfigurationSet> remoteConfigurationSets = new ArrayList<CorbaConfigurationSet>();
			if (!(c instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent component = (CorbaComponent) c;
			if (component.getCorbaObjectInterface() == null) {
				continue;
			}
			boolean isOk = false;
			try {
				Configuration configuration = component
						.getCorbaObjectInterface().get_configuration();
				_SDOPackage.ConfigurationSet[] remoteConfigurationSet = configuration
						.get_configuration_sets();
				for (_SDOPackage.ConfigurationSet remote : remoteConfigurationSet) {
					CorbaConfigurationSet configSet = (CorbaConfigurationSet) CorbaWrapperFactory
							.getInstance().createWrapperObject(remote);
					remoteConfigurationSets.add(configSet);
				}
				isOk = component.updateConfigurationSetListR(component
						.getConfigurationSets(), component
						.getActiveConfigurationSet(), remoteConfigurationSets);
			} catch (Exception e) {
				e.printStackTrace();
				// void
			}
			if (isOk == false) {
				result.putResult(Messages.getString("Restoration.0") //$NON-NLS-1$
						+ c.getInstanceNameL() + " : " //$NON-NLS-1$
						+ c.getPathId() + "]"); //$NON-NLS-1$
				result.setSuccess(false);
			}
		}
	}

	/**
	 * RtcLinkのXMLに含まれるすべてのRTCにアクセス可能であるか確認する。
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	@SuppressWarnings("unchecked")
	private static void processAllPing(Result result,
			SystemDiagram systemDiagram) {
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof CorbaComponent) {
				CorbaComponent c = (CorbaComponent) obj;
				boolean isOk = SynchronizationSupport.ping(c.getSynchronizationSupport()
						.getRemoteObjects());;
				if (isOk == false) {
					result.putResult(Messages.getString("Restoration.7") //$NON-NLS-1$
							+ ((Component) obj).getInstanceNameL() + " : " //$NON-NLS-1$
							+ ((Component) obj).getPathId() + "]"); //$NON-NLS-1$
					result.setSuccess(false);
				}
			}
		}
	}
}
