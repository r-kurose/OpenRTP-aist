package jp.go.aist.rtm.systemeditor.ui.editor.dnd;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.gef.requests.CreationFactory;

/**
 * ドラッグ＆ドロップ時、コンポーネントを作成するファクトリ
 */
public class ComponentFactory implements CreationFactory {
	private Component component;

	/**
	 * {@inheritDoc}
	 */
	public Object getObjectType() {
		return component.getClass();
	}

	/**
	 * コンポーネントのリモートオブジェクトを設定する
	 * 
	 * @param remoteObject
	 *            コンポーネントのリモートオブジェクト
	 */
	public void setComponent(Component component) {
		this.component = component;
	}
	
	/**
	 * コンポーネントのリモートオブジェクトを設定する
	 * 
	 * @param remoteObject
	 *            コンポーネントのリモートオブジェクト
	 */
	protected Component getComponent() {
		return this.component;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object getNewObject() {
		if (getComponent() != null) return getComponent().copy();
		return null;
	}
}
