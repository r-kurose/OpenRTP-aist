package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

/**
 * 多参照のマッピングを定義するためのクラス
 */
public abstract class ManyReferenceMapping extends ReferenceMapping {
	private static class LinkHolder {
		private List deleteRemoteLinkList;
		private List addRemoteLinkList;
	}

	/**
	 * コンストラクタ
	 *
	 * @param localFeature
	 *            ローカルオブジェクトのフィーチャ
	 */
	public ManyReferenceMapping(EReference localFeature) {
		this(localFeature, false);
	}

	/**
	 * コンストラクタ
	 *
	 * @param localFeature
	 *            ローカルオブジェクトのフィーチャ
	 * @param allowZombie
	 *            ゾンビ（リモートオブジェクトが死んだ状態）でも存在させるか
	 */
	public ManyReferenceMapping(EReference localFeature, boolean allowZombie) {
		super(localFeature, allowZombie);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void syncronizeLocal(LocalObject localObject) {
		LinkHolder holder = setupTargetList(localObject, getNewRemoteLinkList(localObject), getOldRemoteLinkList(localObject));

		boolean updated = false;

		for (Object remoteLink : holder.deleteRemoteLinkList) {
			((EList) localObject.eGet(getLocalFeature())).remove(getLocalObjectByRemoteLink(localObject, remoteLink));
			updated = true;
		}

		for (java.lang.Object link : holder.addRemoteLinkList) {
			Object[] remoteObjectByRemoteLink = getRemoteObjectByRemoteLink(localObject, getRemoteObjects(localObject), link);
			if (remoteObjectByRemoteLink != null) {
				if (isAllowZombie() || SynchronizationSupport.ping(remoteObjectByRemoteLink)) {
					LocalObject childNC = loadLocalObjectByRemoteObject(localObject, localObject.getSynchronizationSupport()
							.getSynchronizationManager(), link, remoteObjectByRemoteLink);
					dumpLoadResultForPort(localObject, childNC);
					if (childNC != null) {
						((EList) localObject.eGet(getLocalFeature())).add(childNC);
						updated = true;
					}
				}
			}
		}

		// 変更があった場合の事後処理
		postSynchronizeLocal(localObject, updated);
	}

	@SuppressWarnings("unchecked")
	private LinkHolder setupTargetList(LocalObject localObject,
			List newRemoteLinkList, List oldRemoteLinkList) {
		LinkHolder holder = new LinkHolder();
		holder.deleteRemoteLinkList = new ArrayList(oldRemoteLinkList);
		holder.addRemoteLinkList = new ArrayList(newRemoteLinkList);

		for (int i = 0; i < holder.addRemoteLinkList.size();) {
			int existIndex = findExistLisk(localObject,
					holder.addRemoteLinkList.get(i),
					holder.deleteRemoteLinkList);
			if (existIndex < 0) {
				i++;
			} else {
				holder.addRemoteLinkList.remove(i);
				holder.deleteRemoteLinkList.remove(existIndex);
			}
		}
		return holder;
	}

	private int findExistLisk(LocalObject localObject, Object newLink,
			List deleteRemoteLinkList) {
		for (int i = 0; i < deleteRemoteLinkList.size(); i++) {
			if (isLinkEquals(localObject, newLink, deleteRemoteLinkList.get(i)))
				return i;
		}
		return -1;
	}

	protected void dubugPrint(LocalObject localObject) {
	}

	protected Object[] getRemoteObjects(LocalObject localObject) {
		return localObject.getSynchronizationSupport().getRemoteObjects();
	}

	private void dumpLoadResultForPort(LocalObject localObject,
			LocalObject childNC) {
	}

	/**
	 * 最新のリモートオブジェクトのリンクを返すように、オーバーライドされることが意図される
	 *
	 * @param parentRemoteObjects
	 *            リモートオブジェクト
	 * @return 最新のリモートオブジェクトのリンク
	 */
	protected List getNewRemoteLinkList(Object[] parentRemoteObjects) {
		return null;
	};

	protected List getNewRemoteLinkList(LocalObject localObject) {
		return getNewRemoteLinkList(getRemoteObjects(localObject));
	}

	/**
	 * 現在使用している、リモートオブジェクトのリンクを返す
	 * <p>
	 * 必要に応じて、オーバーライドすること デフォルト実装は、ローカルオブジェクトに対して、リモートオブジェクトが１つである場合の実装。 // *
	 * 関連オブジェクトなどの複数のリモートオブジェクトが存在する場合には、オーバーライドしなければならない。
	 *
	 * @param localObject
	 *            ローカルオブジェクト
	 * @return 現在使用している、リモートオブジェクトのリンク
	 */
	@SuppressWarnings("unchecked")
	public List getOldRemoteLinkList(LocalObject localObject) {
		List result = new ArrayList<Object[]>();
		for (Iterator iter = ((EList) localObject.eGet(getLocalFeature()))
				.iterator(); iter.hasNext();) {
			LocalObject elem = (LocalObject) iter.next();
			try {
				if (getRemoteObjects(elem).length == 1) {
					result.add(getRemoteObjects(elem)[0]);
				}
			} catch (Exception e) {
				iter.remove();
			}
		}

		return result;
	}

	/**
	 * リモートオブジェクトのリンクから、ローカルオブジェクトを取得する
	 *
	 * @param parent
	 *            親のローカルオブジェクト
	 * @param link
	 *            リモートオブジェクトのリンク
	 * @return ローカルオブジェクト
	 */
	public LocalObject getLocalObjectByRemoteLink(LocalObject parent,
			java.lang.Object link) {
		LocalObject result = null;
		java.lang.Object[] links = null;
		if (link instanceof java.lang.Object[]) {
			links = (java.lang.Object[]) link;
		} else {
			links = new java.lang.Object[] { link };
		}
		for (Iterator iter = ((EList) parent.eGet(getLocalFeature()))
				.iterator(); iter.hasNext();) {
			LocalObject elem = (LocalObject) iter.next();
			java.lang.Object[] remotes = getRemoteObjects(elem);
			if (links.length != remotes.length) {
				continue;
			}
			boolean hit = true;
			for (int i = 0; i < links.length; i++) {
				if (!links[i].equals(remotes[i])) {
					hit = false;
					break;
				}
			}
			if (hit) {
				result = elem;
				break;
			}
		}
		return result;
	}

	/**
	 * 同期後の事後処理を定義します。
	 *
	 * @param lo
	 *            同期対象のローカルオブジェクト
	 * @param updated
	 *            更新有無
	 */
	public void postSynchronizeLocal(LocalObject lo, boolean updated) {
		if (updated) {
			postSynchronizeLocal(lo);
		}
	}

	/**
	 * 同期後の事後処理を定義します。
	 *
	 * @param lo
	 *            同期対象のローカルオブジェクト
	 */
	public void postSynchronizeLocal(LocalObject lo) {
	}

}
