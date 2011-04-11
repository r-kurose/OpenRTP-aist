package jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview;

import jp.go.aist.rtm.nameserviceview.model.manager.Node;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * NameServiceViewのContentProviderクラス
 */
public class NameServiceContentProvider extends AdapterImpl implements
		IStructuredContentProvider, ITreeContentProvider {

	private Viewer viewer;

	/**
	 * {@inheritDoc}
	 */
	public void inputChanged(final Viewer viewer, Object oldInput,
			Object newInput) {
		this.viewer = viewer;

		addListener(oldInput, newInput);
	}

	private void addListener(Object oldInput, Object newInput) {
		final NameServiceContentProvider provider = this;

		if (oldInput instanceof ModelElement) {
			((ModelElement) oldInput).accept(new Visiter() {
				public void visit(ModelElement element) {
					element.eAdapters().remove(provider);
				}
			});
		}

		if (newInput instanceof ModelElement) {
			((ModelElement) newInput).accept(new Visiter() {
				public void visit(ModelElement element) {
					if (element.eAdapters().contains(provider) == false) {
						element.eAdapters().add(provider);
					}
				}
			});
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object parent) {
		if (parent instanceof Node) {
			return ((Node) parent).getNodes().toArray();
		}

		return new Object[] {};
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object child) {
		return ((ModelElement) child).eContainer();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parent) {
		return getElements(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object parent) {
		return getChildren(parent).length > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyChanged(Notification msg) {
		if (msg.getNotifier() != null) {
			addListener(null, msg.getNotifier());
		}
		if (viewer.getControl().isDisposed()) return;
		viewer.getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (viewer.getControl().isDisposed() == false) {
					viewer.refresh();
				}
			}
		});
	}
}
