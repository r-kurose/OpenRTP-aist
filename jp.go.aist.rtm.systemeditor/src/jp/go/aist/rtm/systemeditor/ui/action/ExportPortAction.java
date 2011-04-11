package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.PortHelper;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.CompositeComponentHelper;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * ポートの公開・非公開を切り替えるアクション
 *
 */
public class ExportPortAction extends Action {

	private static final String ERROR_TITLE = Messages.getString("ExportPortAction.error.title"); // Error

	private static final String ERROR_UPDATE_FAIL = Messages.getString("ExportPortAction.error.update_fail"); // Update failure.

	private Port target;
	private Component parent;
	private boolean isExported;
	private String createNewValue;

	public void setTarget(Port port) {
		target = port;
	}

	public void setParent(Component compositeComponent) {
		parent = compositeComponent;
	}

	@Override
	public void run() {
		isExported = PortHelper.isExported(target);
		boolean result;
		if (parent.inOnlineSystemDiagram()) {
			result = runOnline();
		} else {
			result = runOffline();
		}
		if (!result) {
			MessageDialog.openError(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), ERROR_TITLE,
					ERROR_UPDATE_FAIL);
		}
	}

	private boolean runOffline() {
		try {
			if (!runUpdate()) return false;

			// exported_portsの設定でポートを公開設定
			List<Component> emptyList = Collections.emptyList();
			parent.addComponentsR(emptyList);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean runUpdate() {
		ConfigurationSet cso = parent.getActiveConfigurationSet();
		ConfigurationSet csc = ComponentUtil.cloneConfigurationSet(cso);
		if (csc != null && csc.getConfigurationData() != null) {
			for (Object o : csc.getConfigurationData()) {
				NameValue nv = (NameValue) o;
				if (!nv.getName().equals("exported_ports")) {
					continue;
				}
				createNewValue = createNewValue(nv.getValueAsString());
				if (createNewValue == null)
					return false;
				nv.setValue(createNewValue);
			}
		}
		parent.updateConfigurationSetR(csc, true);
		return true;
	}

	private boolean runOnline() {
		try {
			if (!runUpdate()) return false;
			parent.getSynchronizationSupport().synchronizeLocal();
			CompositeComponentHelper.synchronizeAll(parent);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String createNewValue(String value) {
		// String instanceNameL = ((Component)target.eContainer()).getInstanceNameL();
		// String nameL = target.getNameL();
		// String portName = instanceNameL + "." + nameL;
		String nameL = target.getNameL();
		String portName = nameL;

		String original = value;
		String[] portNames = original.split(",");
		int index = findIndex(portNames, portName);
		
		if (isExported) {
			if (index < 0) return null;
		} else {
			if (index >= 0) return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < portNames.length; i++) {
			if (i == index) continue;
			if (StringUtils.isBlank(portNames[i])) continue;
			if (buffer.length() > 0) buffer.append(",");
			buffer.append(portNames[i].trim());
		}
		if (index < 0) {
			if (buffer.length() > 0) buffer.append(",");
			buffer.append(portName);
		}
		return buffer.toString();
	}

	private int findIndex(String[] portNames, String portName) {
		for (int i = 0; i < portNames.length; i++) {
			if (portNames[i].trim().equals(portName)) return i;
		}
		return -1;
	}

}
