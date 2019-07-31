package jp.go.aist.rtm.systemeditor.ui.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.RTMixin;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObserverHandler;

/**
 * RTCへの状態通知オブザーバの割当/割当解除を実行するハンドラ
 */
public class AttachStatusObserverCommandHandler extends AbstractHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachStatusObserverCommandHandler.class);

	/** [コマンドID] 状態通知オブザーバ割当 */
	public static final String ATTACH_STATUS_OBSERVER_ID = "rtse.command.observer.status.Attach";
	/** [コマンドID] 状態通知オブザーバ割当解除 */
	public static final String DETACH_STATUS_OBSERVER_ID = "rtse.command.observer.status.Detach";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldValue = HandlerUtil.toggleCommandState(command);
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		List<ComponentEditPart> comps = getSelectedCorbaComponentEditPart(selection);
		if (command == null || comps.isEmpty()) {
			return null;
		}
		boolean doAttach = !oldValue;
		return executeInternal(command.getId(), doAttach, comps.toArray(new ComponentEditPart[0]));
	}

	// 現在の選択から、CorbaComponentをもつ EditPartを抽出
	List<ComponentEditPart> getSelectedCorbaComponentEditPart(ISelection selection) {
		List<ComponentEditPart> ret = new ArrayList<>();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Iterator<?> iterator = structuredSelection.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (!(o instanceof ComponentEditPart)) {
					continue;
				}
				ComponentEditPart part = (ComponentEditPart) o;
				Component comp = part.getModel();
				if (!(comp instanceof CorbaComponent)) {
					continue;
				}
				ret.add(part);
			}
		}
		return ret;
	}

	// コマンド実行本体
	Object executeInternal(String id, boolean doAttach, ComponentEditPart... editParts) {
		LOGGER.debug("executeInternal: command=<{}> doAttach=<{}> comps=<{}>", id, doAttach, editParts);
		for (ComponentEditPart editPart : editParts) {
			if (!(editPart.getModel() instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent comp = (CorbaComponent) editPart.getModel();
			if (doAttach) {
				LOGGER.debug("executeInternal: status observer is attached. comp=<{}>", RTMixin.to_cid(comp));
				CorbaObserverHandler.eINSTANCE.attachStatusObserver(comp);
			} else {
				LOGGER.debug("executeInternal: status observer is detached. comp=<{}>", RTMixin.to_cid(comp));
				CorbaObserverHandler.eINSTANCE.detachStatusObserver(comp);
			}
		}
		return null;
	}

}
