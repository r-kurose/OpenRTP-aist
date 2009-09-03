package jp.go.aist.rtm.repositoryView.adapterfactory;

import jp.go.aist.rtm.repositoryView.model.RTCRVLeafItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Component Specification用のアダプタファクトリ
 */
public class AdapterFactory implements IAdapterFactory {
	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptable, Class adapterType) {
		if( adaptable instanceof RTCRVLeafItem ) {
			if( adapterType == Component.class ) {
				return ((RepositoryViewLeafItem)adaptable).getComponent();
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
