package jp.go.aist.rtm.repositoryView.model;

/**
 * ローカルのディレクトリまたはファイルからロードしたRepositoryViewItemの第一エレメント
 * RepositoryViewRootItemと別に定義する意味はあまりない
 *
 */
public class LocalRVRootItem extends RepositoryViewRootItem {

	public LocalRVRootItem() {
		super("");
	}
	public LocalRVRootItem(String name) {
		super(name);
	}
}
