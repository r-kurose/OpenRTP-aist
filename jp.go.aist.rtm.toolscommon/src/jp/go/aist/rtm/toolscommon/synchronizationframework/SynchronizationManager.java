package jp.go.aist.rtm.toolscommon.synchronizationframework;

import java.util.Iterator;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同期マネージャ
 * <p>
 * マッピングルールのリストを保持する（同期機能のコンテクストともいえる）
 * 
 */
public class SynchronizationManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SynchronizationManager.class);

	private MappingRule[] mappingRules;

	/**
	 * コンストラクタ
	 * 
	 * @param mappingRules
	 *            マッピングルールのリスト
	 */
	public SynchronizationManager(MappingRule[] mappingRules) {
		this.mappingRules = mappingRules;
	}

	/**
	 * リモートオブジェクトツリーから、ローカルオブジェクトツリーを作成する。
	 * 
	 * @param remoteObject
	 *            リモートオブジェクトルート
	 * @return 作成したローカルオブジェクト
	 */
	public LocalObject createLocalObject(Object[] remoteObjects) {
		return createLocalObject(null, remoteObjects, null, true);
	}

	/**
	 * 親のローカルオブジェクト、リモートオブジェクトルートおよびリモートオブジェクトのリンクから、ローカルオブジェクトツリーを作成する
	 * 
	 * @param parent
	 *            親のローカルオブジェクト
	 * @param remoteObject
	 *            リモートオブジェクトルート
	 * @param link
	 *            リモートオブジェクトのリンク
	 * @param needSynchronize 同期が必要か          
	 * @return 作成したローカルオブジェクト
	 */
	public LocalObject createLocalObject(LocalObject parent,
			Object[] remoteObjects, java.lang.Object link, boolean needSynchronize) {

		MappingRule rule = getMappingRule(parent, remoteObjects, link);

		LocalObject result = null;
		if (rule != null) {
			try {
				Object[] narrowedRemoteObjects = rule.getClassMapping().narrow(
						remoteObjects);
				result = rule.getClassMapping().createLocalObject(parent,
						narrowedRemoteObjects, link);
				assignSynchonizationSupport(result);
			} catch (Exception e) {
				LOGGER.error("Fail to assign synchronization support", e);
			}
		}
		//
		if (needSynchronize && result != null) {
			result.getSynchronizationSupport().synchronizeLocal();
		}

		return result;
	}

	// 使用すべきマッピングルールを返す
	private MappingRule getMappingRule(LocalObject parent,
			Object[] remoteObjects, java.lang.Object link) {
		boolean ping = true;
		for (MappingRule temp : mappingRules) {
			try {
				if (temp.getClassMapping().getConstructorParamMappings().length == remoteObjects.length 
						&& canTarget(ping, temp.getClassMapping().needsPing())
						&& temp.getClassMapping().isTarget(parent,remoteObjects, link)) {
					if (temp.getClassMapping().allowZombie()) return temp;
					if (ping) ping = SynchronizationSupport.ping(remoteObjects);
					if (ping) return temp;
				}
			} catch (Exception e) {
//				System.out.println("Exception Catched" + e);
				ping = false;
			}
		}
		return null;
	}

	private boolean canTarget(boolean ping, boolean needsPing) {
		if (!needsPing) return true;
		return ping;
	}

	/**
	 * 同期サポートを作成する
	 * 
	 * @param localObject
	 *            ローカルオブジェクト
	 * @param remoteObject
	 *            リモートオブジェクト
	 * @param rule
	 *            マッピングルール
	 * @return
	 */
	private SynchronizationSupport createSynchronizeSupport(
			LocalObject localObject, MappingRule rule) {
		return new SynchronizationSupport(localObject, rule, this);
	}

	/**
	 * 包含参照をたどり、すべてのローカルオブジェクトに対して、同期サポートを復元する
	 * 
	 * @param eobj
	 *            EObject
	 */
	@SuppressWarnings("unchecked")
	public void assignSynchonizationSupport(EObject eobj) {
		if (eobj instanceof LocalObject) {
			LocalObject localObject = (LocalObject) eobj;
			MappingRule rule = getMappingRule(localObject);

			if (rule != null) {
				localObject.setSynchronizationSupport(createSynchronizeSupport(
						localObject, rule));
			}
		}

		for (Iterator iter = eobj.eContents().iterator(); iter.hasNext();) {
			EObject obj = (EObject) iter.next();
			assignSynchonizationSupport((obj));
		}
	}

	/**
	 * システムダイアグラムに含まれるコンポーネントに対し、同期サポートを復元する
	 * @param diagram
	 */
	public void assignSynchonizationSupportToDiagram(SystemDiagram diagram) {
		for (Component c : diagram.getRegisteredComponents()) {
			assignSynchonizationSupport(c);
		}
	}

	private MappingRule getMappingRule(LocalObject localObject) {
		for (MappingRule temp : mappingRules) {
			if (temp.getClassMapping().getLocalClass().isAssignableFrom(localObject.getClass())
					&& temp.isTarget(localObject)) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * マッピングルールを取得する
	 * @return
	 */
	public MappingRule[] getMappingRules() {
		return mappingRules;
	}

	/**
	 * マッピングルールを設定する
	 * @param mappingRules
	 */
	public void setMappingRules(MappingRule[] mappingRules) {
		this.mappingRules = mappingRules;
	}

}
