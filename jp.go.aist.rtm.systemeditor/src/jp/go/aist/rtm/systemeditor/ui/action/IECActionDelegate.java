package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentActionDelegate;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ECに対するアクション (TODO menu化)
 */
public class IECActionDelegate implements IObjectActionDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(IECActionDelegate.class);

	/**
	 * Startに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String START_ACTION_ID = IECActionDelegate.class.getName() + ".executioncontext.Start"; //$NON-NLS-1$

	/**
	 * Stopに使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String STOP_ACTION_ID = IECActionDelegate.class.getName() + ".executioncontext.Stop"; //$NON-NLS-1$

	/**
	 * ActivateRTCs に使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String ACTIVATE_RTCS_ACTION_ID = IECActionDelegate.class.getName() + ".executioncontext.ActivateRTCs";

	/**
	 * DeactivateRTCs に使用されるID。この値が、Plugin.xmlに指定されなければならない。
	 */
	public static final String DEACTIVATE_RTCS_ACTION_ID = IECActionDelegate.class.getName() + ".executioncontext.DeactivateRTCs";

	static final String TITLE_CONFIRM_DIALOG = Messages.getString("Common.dialog.confirm_title");

	static final String MSG_CONFIRM_START = Messages.getString("IECActionDelegate.confirm.start");
	static final String MSG_CONFIRM_STOP = Messages.getString("IECActionDelegate.confirm.stop");
	static final String MSG_CONFIRM_ACTIVATE_RTCS = Messages.getString("IECActionDelegate.confirm.activate_rtcs");
	static final String MSG_CONFIRM_DEACTIVATE_RTCS = Messages.getString("IECActionDelegate.confirm.deactivate_rtcs");

	static final String ERROR_UNKNOWN_COMMAND = Messages.getString("IComponentActionDelegate.14");

	private ISelection selection;

	private IWorkbenchPart targetPart;

	ComponentActionDelegate actionDelegate;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
		actionDelegate = new ComponentActionDelegate();
		actionDelegate.setActivePart(null, this.targetPart);
	}

	/** コンポーネントアクションのコマンド */
	static abstract class ComponentCommand extends ComponentActionDelegate.Command {
		protected CorbaComponent comp;

		public ComponentCommand(CorbaComponent comp) {
			this.comp = comp;
		}
	}

	public void run(final IAction action) {

		for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter.hasNext();) {

			ComponentEditPart part = (ComponentEditPart) iter.next();
			if (!(part.getModel() instanceof CorbaComponent)) {
				continue;
			}
			final CorbaComponent component = (CorbaComponent) part.getModel();

			ComponentCommand command = null;
			if ((START_ACTION_ID).equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_START;
					}

					@Override
					public int run() {
						return comp.startR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (STOP_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_STOP;
					}

					@Override
					public int run() {
						return component.stopR();
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (ACTIVATE_RTCS_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_ACTIVATE_RTCS;
					}

					@Override
					public int run() {
						CorbaExecutionContext pec = (CorbaExecutionContext) component.getPrimaryExecutionContext();
						if (pec == null) {
							LOGGER.warn("Primary EC is null or is not CorbaExecutionContext. pec={}", pec);
							return -1;
						}
						int ret = 0;
						List<CorbaComponent> comps = findComponents(pec);
						for (CorbaComponent comp : comps) {
							int r = pec.activateR(comp);
							if (r != 0) {
								ret = r;
							}
						}
						return ret;
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else if (DEACTIVATE_RTCS_ACTION_ID.equals(action.getId())) {
				command = new ComponentCommand(component) {
					@Override
					public String getConfirmMessage() {
						return MSG_CONFIRM_DEACTIVATE_RTCS;
					}

					@Override
					public int run() {
						CorbaExecutionContext pec = (CorbaExecutionContext) component.getPrimaryExecutionContext();
						if (pec == null) {
							LOGGER.warn("Primary EC is null or is not CorbaExecutionContext. pec={}", pec);
							return -1;
						}
						int ret = 0;
						List<CorbaComponent> comps = findComponents(pec);
						for (CorbaComponent comp : comps) {
							int r = pec.deactivateR(comp);
							if (r != 0) {
								ret = r;
							}
						}
						return ret;
					}

					@Override
					public void done() {
						comp.synchronizeManually();
					}
				};
			} else {
				throw new RuntimeException(ERROR_UNKNOWN_COMMAND);
			}

			if (SystemEditorPreferenceManager.getInstance().isConfirmComponentAction()) {
				// アクションの実行確認が有効な場合
				boolean isOK = MessageDialog.openConfirm(targetPart.getSite().getShell(), TITLE_CONFIRM_DIALOG,
						command.getConfirmMessage());
				if (!isOK) {
					return;
				}
			}

			actionDelegate.run(command);
		}
	}

	// EC に紐づくコンポーネントのリストを取得します(重複除外)
	private List<CorbaComponent> findComponents(CorbaExecutionContext ec) {
		List<CorbaComponent> ret = new ArrayList<>();
		List<Object> remotes = new ArrayList<>();
		//
		if (ec.getOwner() instanceof CorbaComponent) {
			ret.add((CorbaComponent) ec.getOwner());
			remotes.add(((CorbaComponent) ec.getOwner()).getCorbaObjectInterface());
		}
		for (Component c : ec.getParticipants()) {
			if (!(c instanceof CorbaComponent)) {
				continue;
			}
			CorbaComponent cc = (CorbaComponent) c;
			if (remotes.contains(cc.getCorbaObjectInterface())) {
				continue;
			}
			ret.add(cc);
			remotes.add(cc.getCorbaObjectInterface());
		}
		LOGGER.trace("findComponents: comps={}", ret);
		return ret;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		//
		boolean enabled = false;
		if (selection instanceof IStructuredSelection) {
			Object part = ((IStructuredSelection) selection).getFirstElement();
			if (part instanceof ComponentEditPart) {
				String ecType = ((ComponentEditPart) part).getPrimaryExecutionContextType();
				enabled = "owned".equals(ecType);
			}
		}
		action.setEnabled(enabled);
	}

}
