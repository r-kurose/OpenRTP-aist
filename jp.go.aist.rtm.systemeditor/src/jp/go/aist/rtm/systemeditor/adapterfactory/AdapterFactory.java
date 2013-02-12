package jp.go.aist.rtm.systemeditor.adapterfactory;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.core.runtime.IAdapterFactory;

/**
 * RTCLinkのアダプタファクトリ
 */
public class AdapterFactory implements IAdapterFactory {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptable, Class adapterType) {
		if (adaptable instanceof ComponentEditPart) {
			if (adapterType == Component.class) {
				Object entry = AdapterUtil.getAdapter(
						(ComponentEditPart) adaptable, adapterType);

				if (entry instanceof Component) {
					return entry;
				}
			}
		} 

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Class[] getAdapterList() {
		return new Class[] { Component.class };
	}
}
