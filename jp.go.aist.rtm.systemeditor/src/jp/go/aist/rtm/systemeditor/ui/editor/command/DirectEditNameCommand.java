package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.gef.commands.Command;

import static jp.go.aist.rtm.toolscommon.util.ComponentUtil.*;

public class DirectEditNameCommand extends Command {

	Object model;

	String text, oldText;

	@Override
	public void execute() {
		if (model instanceof Component) {
			Component c = (Component) model;
			oldText = c.getInstanceNameL();
			c.setInstanceNameL(text);
			// 設定されたインスタンス名を元にポート名を正規化
			trimPortNames(c);
			// ポート名の変更をコネクタプロファイルへ反映
			trimConnectorProfiles(c);
		}
	}

	@Override
	public void undo() {
		if (model instanceof Component) {
			Component c = (Component) model;
			c.setInstanceNameL(oldText);
			// 設定されたインスタンス名を元にポート名を正規化
			trimPortNames(c);
			// ポート名の変更をコネクタプロファイルへ反映
			trimConnectorProfiles(c);
		}
		oldText = null;
	}

	@Override
	public boolean canExecute() {
		if (model instanceof Component) {
			Component comp = (Component) model;
			if (!(comp.eContainer() instanceof SystemDiagram)) {
				return false;
			}
			SystemDiagram sd = ((SystemDiagram) comp.eContainer())
					.getRootDiagram();
			Component c = find(text, sd.getRegisteredComponents());
			if (c == null) {
				return true;
			}
		}
		return false;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public Object getModel() {
		return model;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static void trimConnectorProfiles(Component comp) {
		SystemDiagram sd = (SystemDiagram) comp.eContainer();
		if (sd == null) {
			return;
		}
		for (PortConnector pc : sd.getConnectorMap().values()) {
			ConnectorProfile cp = pc.getConnectorProfile();
			Port source = pc.getSource();
			Port target = pc.getTarget();
			if (source == null || target == null) {
				continue;
			}
			for (Port p : comp.getPorts()) {
				if (!source.equals(p) && !target.equals(p)) {
					continue;
				}
				cp.setSourceString(source.getOriginalPortString());
				cp.setTargetString(target.getOriginalPortString());
				cp.setName(source.getNameL() + "_" + target.getNameL());
			}
		}
	}

}
