package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
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

	@Override
	public void execute() {
		boolean can = true;
		Component cc = parent.getCompositeComponent();
		if (cc != null) {
			// 複合RTC内のエディタの場合は複合RTCに子を追加
			List<Component> list = new ArrayList<Component>();
			list.add(target);
			if (!cc.addComponentsR(list)) {
				// 複合RTCへ追加できない場合はダイアグラムｈの追加不可
				can = false;
			}
		}
		if (can) {
			parent.addComponent(target);
			setComponentsConstraint(target);
			AbstractSystemDiagramEditor ae = ComponentUtil.findEditor(parent);
			if (ae != null) {
				ae.refresh();
			}
		}
	}

	private void setComponentsConstraint(Component component) {
		List<Integer> counts = new ArrayList<Integer>();
		int count = 0;
		counts.add(count);
		Integer temp = counts.get(counts.size() - 1);
		for (Iterator<?> iterator = component.getAllComponents().iterator(); iterator
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

	
	public Component getTarget() {
		return target;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
	}
}
