package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.ui.propertysource.AbstractPropertySource;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * RtcのPropertySheetのContentProviderクラス
 */
public class PropertySheetContentProvider extends AdapterImpl implements
		IStructuredContentProvider, ITreeContentProvider {

	private Viewer viewer;

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;

		addListener(oldInput, newInput);
	}

	@SuppressWarnings("unchecked")
	private void addListener(Object oldInput, Object newInput) {
		final PropertySheetContentProvider provider = this;

		if (oldInput != null) {
			if (oldInput instanceof ComponentWrapper) {
				ComponentWrapper w = (ComponentWrapper) oldInput;
				w.getComponent().eAdapters().remove(provider);
				for (Iterator iter = w.getComponent().eAllContents(); iter
						.hasNext();) {
					EObject element = (EObject) iter.next();
					element.eAdapters().remove(provider);
				}
				for (EObject eo : w.getSubContents()) {
					eo.eAdapters().remove(provider);
				}
			} else if (oldInput instanceof RTCManagerWrapper) {
				RTCManagerWrapper w = (RTCManagerWrapper) oldInput;
				w.getManager().eAdapters().remove(provider);
			}
		}

		if (newInput != null) {
			if (newInput instanceof ComponentWrapper) {
				ComponentWrapper w = (ComponentWrapper) newInput;
				w.getComponent().eAdapters().add(provider);
				for (Iterator iter = w.getComponent().eAllContents(); iter
						.hasNext();) {
					EObject element = (EObject) iter.next();
					element.eAdapters().add(provider);
				}
				for (EObject eo : w.getSubContents()) {
					eo.eAdapters().add(provider);
				}
			} else if (newInput instanceof RTCManagerWrapper) {
				RTCManagerWrapper w = (RTCManagerWrapper) newInput;
				w.getManager().eAdapters().add(provider);
			}
		}
	}

	public void dispose() {
		addListener(viewer.getInput(), null);
	}

	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}

	public Object getParent(Object child) {
		return null;
	}

	public Object[] getChildren(Object parent) {
		if (parent instanceof PropertyDescriptorWithSource) {
			return new Object[0];
		} else if (parent instanceof ComponentWrapper) {
			return new Object[] { ((ComponentWrapper) parent).getComponent() };
		} else if (parent instanceof PortConnectorWrapper) {
			return new Object[] { ((PortConnectorWrapper) parent)
					.getConnector() };
		} else if (parent instanceof RTCManagerWrapper) {
			return new Object[] { ((RTCManagerWrapper) parent).getManager() };
		} else if (parent instanceof RTCManager) {
			List<Object> result = new ArrayList<Object>();

			RTCManagerWrapper w = new RTCManagerWrapper((RTCManager) parent);
			result.add(w.getComponentsChild());
			result.add(w.getLoadableModulesChild());
			result.add(w.getLoadedModulesChild());

			return result.toArray();

		} else if (parent instanceof RTCManagerWrapper.Child) {
			List<Object> result = new ArrayList<Object>();

			RTCManagerWrapper.Child c = (RTCManagerWrapper.Child) parent;
			IPropertySource source = c.getPropertySource();
			result.addAll(this.createProperDescriptorWithSourceList(source));

			return result.toArray();

		} else {
			List<Object> result = new ArrayList<Object>();

			Object obj = parent;
			IPropertySource source;
			source = (IPropertySource) AdapterUtil.getAdapter(parent,
					IPropertySource.class);

			if (parent instanceof ChildWithParent) {
				ChildWithParent cp = (ChildWithParent) parent;
				obj = cp.getChild();
				if (source instanceof AbstractPropertySource) {
					((AbstractPropertySource) source).setParent(cp.getParent());
				}
			}
			result.addAll(this.createProperDescriptorWithSourceList(source));

			IWorkbenchAdapter workbenchAdapter = (IWorkbenchAdapter) AdapterUtil
					.getAdapter(parent, IWorkbenchAdapter.class);
			if (workbenchAdapter != null) {
				Object[] children = workbenchAdapter.getChildren(obj);
				if (children != null) {
					for (Object o : children) {
						ChildWithParent cp = new ChildWithParent(o, obj);
						result.add(cp);
					}
				}
			}

			return result.toArray(new Object[result.size()]);
		}
	}

	List<PropertyDescriptorWithSource> createProperDescriptorWithSourceList(
			IPropertySource source) {
		List<PropertyDescriptorWithSource> result = new ArrayList<PropertyDescriptorWithSource>();
		if (source == null) {
			return result;
		}
		for (IPropertyDescriptor descriptor : source.getPropertyDescriptors()) {
			result.add(new PropertyDescriptorWithSource(descriptor, source));
		}
		return result;
	}

	/** 親/子要素をまとめてラップするクラス */
	public static class ChildWithParent implements IAdaptable {
		Object child;
		Object parent;

		public ChildWithParent(Object child, Object parent) {
			this.child = child;
			this.parent = parent;
		}

		public Object getChild() {
			return child;
		}

		public Object getParent() {
			return parent;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object getAdapter(Class adapter) {
			Object result = null;
			if (getChild() instanceof IAdaptable) {
				result = ((IAdaptable) getChild()).getAdapter(adapter);
			}
			if (result == null) {
				result = Platform.getAdapterManager().getAdapter(getChild(),
						adapter);
			}
			return result;
		}
	}

	public boolean hasChildren(Object parent) {
		return getChildren(parent).length > 0;
	}

	private Notification notifyMessage = null;

	@Override
	public void notifyChanged(final Notification msg) {
		if (msg.getOldValue() == this || msg.getNewValue() == this) { // 自分をリスナに追加することによる、変更通知を無視するため
			return;
		}
		if (CorePackage.eINSTANCE.getModelElement_Constraint().equals(
				msg.getFeature())) {
			return;
		}
		final List<Integer> refreshTypes = Arrays.asList(Notification.SET,
				Notification.ADD, Notification.REMOVE);
		if ((notifyMessage == null || !notifyMessage.getNotifier().equals(
				msg.getNotifier()))
				&& refreshTypes.contains(msg.getEventType())) {
			notifyMessage = msg;
		}
		viewer.getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (viewer.getControl().isDisposed()) {
					return;
				}
				if (notifyMessage == null) {
					return;
				}
				if (refreshTypes.contains(notifyMessage.getEventType())) {
					viewer.refresh();
					((TreeViewer) viewer).expandAll();
				}
				notifyMessage = null;
			}
		});
	}

}
