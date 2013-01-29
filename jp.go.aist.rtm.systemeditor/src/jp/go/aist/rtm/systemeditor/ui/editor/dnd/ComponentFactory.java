package jp.go.aist.rtm.systemeditor.ui.editor.dnd;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.gef.requests.CreationFactory;

/**
 * ドラッグ＆ドロップ時、コンポーネントを作成するファクトリ
 */
public class ComponentFactory implements CreationFactory {

	List<Component> components;

	public ComponentFactory() {
		components = new ArrayList<Component>();
	}

	public void addComponent(Component component) {
		if (!components.contains(component)) {
			components.add(component);
		}
	}

	@Override
	public Object getObjectType() {
		return components.getClass();
	}

	@Override
	public Object getNewObject() {
		List<Component> result = new ArrayList<Component>();
		for (Component c : components) {
			result.add(c.copy());
		}
		return (result.isEmpty()) ? null : result;
	}

}
