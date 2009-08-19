package jp.go.aist.rtm.repositoryView.model;

/**
 * レポジトリらロードしたRepositoryViewRootItem
 * RepositoryViewRootItemと別に定義する意味はあまりない
 *
 */

public class ServerRVRootItem extends RepositoryViewRootItem {

	public ServerRVRootItem() {
		super("");
	}
	public ServerRVRootItem(String name) {
		super(name);
	}
}
