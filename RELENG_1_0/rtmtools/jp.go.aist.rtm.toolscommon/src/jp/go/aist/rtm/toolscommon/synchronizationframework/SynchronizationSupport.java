package jp.go.aist.rtm.toolscommon.synchronizationframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * ローカルオブジェクトに対して、同期機能を提供するクラス
 */
public class SynchronizationSupport {
	private LocalObject localObject;

	private MappingRule mappingRule;

	private SynchronizationManager synchronizationManager;

	/**
	 * コンストラクタ
	 * 
	 * @param localObject
	 *            ローカルオブジェクト
	 * @param mappingRule
	 *            マッピングルール
	 * @param synchronizationManager
	 *            同期マネージャ
	 */
	public SynchronizationSupport(LocalObject localObject,
			MappingRule mappingRule,
			SynchronizationManager synchronizationManager) {
		this.localObject = localObject;
		this.mappingRule = mappingRule;
		this.synchronizationManager = synchronizationManager;
	}

	/**
	 * 同期マネージャを取得する
	 * 
	 * @return 同期マネージャ
	 */
	public SynchronizationManager getSynchronizationManager() {
		return synchronizationManager;
	}

	/**
	 * ローカルオブジェクトを同期する。
	 * <p>
	 * 包含参照をたどり、すべてのローカルオブジェクトを同期する
	 */
	public synchronized void synchronizeLocal() {
		Object[] remoteObjects = null;
		try {
			remoteObjects = getRemoteObjects(); // 例外が発生することがある
		} catch (Exception e) {
			// void
		}

		if (!mappingRule.getClassMapping().allowZombie()
				&& (remoteObjects == null || !ping(remoteObjects))) {
			remove();
			return;
		}
		synchronizeLocalAttribute();
		synchronizeLocalReference();
		if (localObject.eContainer() instanceof SystemDiagram) {
			if (localObject instanceof Component) {
				((Component) localObject).synchronizeChildComponents();
			}
			return;
		}
		for (Object content : localObject.eContents()) {
			if (content instanceof LocalObject) {
				LocalObject lo = (LocalObject) content;
				if (lo.getSynchronizationSupport() != null) {
					lo.getSynchronizationSupport().synchronizeLocal();
				}
			}
		}
	}

	private void remove() {
		EObject container = localObject.eContainer();
		if (container == null) {
			EcoreUtil.remove(localObject);
			return;
		}
		synchronized (container) {
			EcoreUtil.remove(localObject);
		}
	}

	private void synchronizeLocalAttribute() {
		if (localObject.eContainer() instanceof SystemDiagram) {
			if (localObject instanceof Component) {
				((Component) localObject).synchronizeLocalAttribute(null);
			} 
		}
		for (AttributeMapping attibuteMapping : mappingRule
				.getAllAttributeMappings()) {
			try {
				attibuteMapping.syncronizeLocal(localObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void synchronizeLocalReference() {
		if (localObject.eContainer() instanceof SystemDiagram) {
			if (localObject instanceof Component) {
				((Component) localObject)
						.synchronizeLocalReference();
			} 
		}
		for (ReferenceMapping referenceMapping : mappingRule
				.getAllReferenceMappings()) {
			try {
				referenceMapping.syncronizeLocal(localObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * オブジェクトに対して接続できるか確認する
	 * 
	 * @param remoteObject
	 * @return 接続できるかどうか
	 */
	public static boolean ping(Object[] remoteObject) {
		if (remoteObject != null) {
			for (Object object : remoteObject) {
				if (ping(object) == false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * オブジェクトに対して接続できるか確認する
	 * 
	 * @param remoteObject
	 * @return 接続できるかどうか
	 */
	public static boolean ping(Object remoteObject) {
		try {
			if (remoteObject instanceof org.omg.CORBA.Object) {
				return((org.omg.CORBA.Object) remoteObject)._non_existent() == false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * オブジェクトグラフ内から、ローカルをリモートオブジェクトによって検索します。
	 * 
	 * @param remoteObjects
	 * @param graphPart
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static LocalObject findLocalObjectByRemoteObject(
			Object[] remoteObjects, EObject graphPart) {
		EObject graphRoot = EcoreUtil.getRootContainer(graphPart);

		for (Iterator iter = graphRoot.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			LocalObject result = findLocalObject(obj, remoteObjects);
			if (result != null) return result;
		}

		return null;
	}
	
	private static LocalObject findLocalObject(Object obj, Object[] remoteObjects){
		if (!(obj instanceof LocalObject)) return null;
		LocalObject result = (LocalObject) obj;
		Object[] tmp;
		if (obj instanceof Port) {
			tmp = new Object[]{CorbaPortSynchronizerImpl.getRemoteObjectsForSync(result)};
		} else {
			if (result.getSynchronizationSupport() == null) return null;
			tmp = result.getSynchronizationSupport().getRemoteObjects();
		}
		if (remoteObjects.length != tmp.length) return null;
		for (int i = 0; i < remoteObjects.length; ++i) {
			if (!remoteObjects[i].equals(tmp[i])) return null;
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static Collection<LocalObject> findLocalObjectByRemoteObjects(
			Object[] remoteObjects, LocalObject graphPart) {
		Collection<LocalObject> result = new ArrayList<LocalObject>();
		EObject graphRoot = EcoreUtil.getRootContainer(graphPart);

		for (Iterator iter = graphRoot.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			LocalObject temp = findLocalObject(obj, remoteObjects);
			if (temp != null) result.add(temp);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void poulateLocalObjectByRemoteObjects(EList list,
			Object[] remoteObjects, LocalObject graphPart) {
		EObject graphRoot = EcoreUtil.getRootContainer(graphPart);

		for (Iterator iter = graphRoot.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			LocalObject temp = findLocalObject(obj, remoteObjects);
			if (temp != null) list.add(temp);
		}
	}

	/**
	 * リモートオブジェクトを取得する
	 * 
	 * @return リモートオブジェクト
	 */
	public Object[] getRemoteObjects() {
		List<Object> result = new ArrayList<Object>();
		for (ConstructorParamMapping mapping : mappingRule.getClassMapping()
				.getConstructorParamMappings()) {
			result.add(localObject.eGet(mapping.getFeature()));
		}

		return result.toArray();
	}

}
