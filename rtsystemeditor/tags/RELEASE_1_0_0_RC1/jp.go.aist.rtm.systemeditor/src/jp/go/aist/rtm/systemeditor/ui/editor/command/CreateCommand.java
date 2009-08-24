package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.gef.commands.Command;

/**
 * システムダイアグラムにRtcを追加するコマンド
 */
public class CreateCommand extends Command {
	private SystemDiagram parent;

	private Component target;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		parent.addComponent(target);
		setComponentsConstraint(target);
		ComponentUtil.findEditor(parent).refresh();
		Component compositeComponent = parent.getCompositeComponent();
		if (compositeComponent != null) {
			// 複合RTC内のエディタの場合
			// 複合コンポーネントに子を追加
			List<Component> list = new ArrayList<Component>();
			list.add(target);
			compositeComponent.addComponentsR(list);
		}
	}

	@SuppressWarnings("unchecked")
	private void setComponentsConstraint(Component component) {
		List<Integer> counts = new ArrayList<Integer>();
		int count = 0;
		counts.add(count);
		Integer temp = counts.get(counts.size() - 1);
		for (Iterator iterator = component.getAllComponents().iterator(); iterator
				.hasNext();) {
			Component tempComponent = (Component) iterator.next();
			if (tempComponent.getConstraint() == null) {
				tempComponent.setConstraint(ComponentUtil
						.getNewComponentConstraint(component.getConstraint(), temp));
				temp++;
				counts.set(counts.size() - 1, temp);
			}
		}
		counts.remove(counts.size() - 1);
		if (counts.size() > 1) {
			temp = counts.get(counts.size() - 1);
		}		
	}

	/**
	 * 親となるシステムダイアグラムを設定する
	 * 
	 * @param parent
	 *            親となるシステムダイアグラム
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * 作成対象のRtcを設定する
	 * 
	 * @param target
	 *            作成対象のRtc
	 */
	public void setTarget(Component target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
	}
}
