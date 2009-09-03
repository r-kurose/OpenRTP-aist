package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

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

	/**
	 * {@inheritDoc}
	 */
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
			} else if (newInput instanceof RTCManagerWrapper) {
				RTCManagerWrapper w = (RTCManagerWrapper) newInput;
				w.getManager().eAdapters().add(provider);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		addListener(viewer.getInput(), null);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object child) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parent) {
		if (parent instanceof PropertyDescriptorWithSource) {
			return new Object[0];
		} else if (parent instanceof ComponentWrapper) {
			return new Object[] { ((ComponentWrapper) parent).getComponent() };
		} else if (parent instanceof PortConnectorWrapper) {
			return new Object[] { ((PortConnectorWrapper) parent).getConnector() };
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

			IPropertySource source = (IPropertySource) AdapterUtil.getAdapter(
					parent, IPropertySource.class);
			result.addAll(this.createProperDescriptorWithSourceList(source));

			IWorkbenchAdapter workbenchAdapter = (IWorkbenchAdapter) AdapterUtil
					.getAdapter(parent, IWorkbenchAdapter.class);
			if (workbenchAdapter != null) {
				Object[] children = workbenchAdapter.getChildren(parent);
				if (children != null) {
					result.addAll(Arrays.asList(children));
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

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object parent) {
		return getChildren(parent).length > 0;
	}

	private Notification notifyMessage = null;

	/**
	 * {@inheritDoc}
	 */
	public void notifyChanged(final Notification msg) {
		if (msg.getOldValue() != this && msg.getNewValue() != this) { // 自分をリスナに追加することによる、変更通知を無視するため
			if (!CorePackage.eINSTANCE.getModelElement_Constraint().equals(
					msg.getFeature())) {
				if (notifyMessage == null
						|| !notifyMessage.getNotifier().equals(
								msg.getNotifier())) {
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
						if (Arrays.asList(Notification.SET, Notification.ADD,
								Notification.REMOVE).contains(
								notifyMessage.getEventType())) {
							viewer.refresh();
							((TreeViewer) viewer).expandAll();
						}
						notifyMessage = null;
					}
				});
			}
		}
	}
}
