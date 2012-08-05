package jp.go.aist.rtm.repositoryView;

import java.util.List;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.repositoryView.ui.views.RepositoryView;

/**
 * リポジトリへのアクセサ
 */
public class RepositoryAccessor {

	public static RepositoryAccessor eINSTANCE = new RepositoryAccessor();

	RepositoryView view;

	public RepositoryAccessor() {
	}

	/**
	 * リポジトリからコンポーネントを検索します。
	 * 
	 * @param id
	 *            コンポーネントID
	 * @param pathId
	 *            パスID
	 * @return 検索に一致するコンポーネント。一致しない場合は null
	 */
	@SuppressWarnings("unchecked")
	public jp.go.aist.rtm.toolscommon.model.component.Component findComponent(
			String id, String pathId) {
		if (getView() == null) {
			return null;
		}
		// TODO 現状はViewerのInputから検索
		List<RepositoryViewItem> models = (List<RepositoryViewItem>) getView()
				.getModel();
		return findComponent(id, pathId, models);
	}

	public jp.go.aist.rtm.toolscommon.model.component.Component findComponent(
			String id, String pathId, List<RepositoryViewItem> models) {
		if (models == null) {
			return null;
		}
		for (RepositoryViewItem item : models) {
			if (item instanceof RepositoryViewLeafItem) {
				jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification target = ((RepositoryViewLeafItem) item)
						.getComponent();
				if (target == null) {
					return null;
				}
				if (target.getComponentId().equals(id)
						&& target.getPathId().equals(pathId)) {
					return target;
				}
			} else {
				jp.go.aist.rtm.toolscommon.model.component.Component result = findComponent(
						id, pathId, item.getChildren());
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	public RepositoryView getView() {
		if (view == null) {
			view = (RepositoryView) RepositoryViewPlugin.getDefault()
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.findView(RepositoryViewPlugin.PLUGIN_ID + ".view");
		}
		return view;
	}

}
