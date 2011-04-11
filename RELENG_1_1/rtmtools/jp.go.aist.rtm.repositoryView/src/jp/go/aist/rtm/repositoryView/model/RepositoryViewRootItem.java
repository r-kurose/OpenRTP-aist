package jp.go.aist.rtm.repositoryView.model;

import jp.go.aist.rtm.repositoryView.nl.Messages;


/**
 * ルートとなるRepositoryViewItem
 * RepositoryViewItemのサブクラスとして定義する意味はあまりない
 *
 */
public class RepositoryViewRootItem extends RepositoryViewItem {

	public static final String LOCAL_ROOT = Messages.getString("RepositoryViewRootItem.0");
	public static final String SERVER_ROOT = Messages.getString("RepositoryViewRootItem.1");

	public RepositoryViewRootItem(String name) {
		super(name, RepositoryViewItem.ROOT_ITEM);
	}
}
