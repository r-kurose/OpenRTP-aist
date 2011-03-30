package jp.go.aist.rtm.repositoryView.ui;


import java.util.List;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * レポジトリビューのコンテンツプロバイダ
 *
 */
public class ArrayContentProvider implements ITreeContentProvider {

	@SuppressWarnings("unchecked")
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof List) {
			List list = (List)parentElement;
			return list.toArray();
		}
		return ((RepositoryViewItem)parentElement).getChildren().toArray();
	}

	public Object getParent(Object element) {
		if (element instanceof List) return null;

		return ((RepositoryViewItem)element).getParent();
	}
	
	public boolean hasChildren(Object element) {
	    //(自分内にディレクトリがあるかどうか)
	    return getChildren(element).length > 0;
	}

	public Object[] getElements(Object inputElement) {
	    return getChildren(inputElement);
	}

	public void dispose() {
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

}
