package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class IComponentActionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = HandlerUtil.getActivePartChecked(event);
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);

		List<CorbaComponent> components = new ArrayList<CorbaComponent>();
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> iter = ((IStructuredSelection) selection).iterator(); iter
					.hasNext();) {
				Object o = AdapterUtil.getAdapter(iter.next(), Component.class);
				if (!(o instanceof CorbaComponent)) {
					continue;
				}
				components.add((CorbaComponent) o);
			}
		}
		if (components.isEmpty()) {
			return null;
		}

		IStructuredSelection ss = new StructuredSelection(components);
		IAction action = getAction(event);

		IComponentActionDelegate delegate = new IComponentActionDelegate();
		delegate.setActivePart(action, part);
		delegate.selectionChanged(action, ss);
		delegate.run(action);

		return null;
	}

	IAction getAction(final ExecutionEvent event) {
		return new IAction() {

			Command command = event.getCommand();

			@Override
			public void addPropertyChangeListener(
					IPropertyChangeListener listener) {
			}

			@Override
			public int getAccelerator() {
				return 0;
			}

			@Override
			public String getActionDefinitionId() {
				return null;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public ImageDescriptor getDisabledImageDescriptor() {
				return null;
			}

			@Override
			public HelpListener getHelpListener() {
				return null;
			}

			@Override
			public ImageDescriptor getHoverImageDescriptor() {
				return null;
			}

			@Override
			public String getId() {
				return command.getId();
			}

			@Override
			public ImageDescriptor getImageDescriptor() {
				return null;
			}

			@Override
			public IMenuCreator getMenuCreator() {
				return null;
			}

			@Override
			public int getStyle() {
				return 0;
			}

			@Override
			public String getText() {
				return null;
			}

			@Override
			public String getToolTipText() {
				return null;
			}

			@Override
			public boolean isChecked() {
				return false;
			}

			@Override
			public boolean isEnabled() {
				return false;
			}

			@Override
			public boolean isHandled() {
				return false;
			}

			@Override
			public void removePropertyChangeListener(
					IPropertyChangeListener listener) {
			}

			@Override
			public void run() {
			}

			@Override
			public void runWithEvent(Event event) {
			}

			@Override
			public void setAccelerator(int keycode) {
			}

			@Override
			public void setActionDefinitionId(String id) {
			}

			@Override
			public void setChecked(boolean checked) {
			}

			@Override
			public void setDescription(String text) {
			}

			@Override
			public void setDisabledImageDescriptor(ImageDescriptor newImage) {
			}

			@Override
			public void setEnabled(boolean enabled) {
			}

			@Override
			public void setHelpListener(HelpListener listener) {
			}

			@Override
			public void setHoverImageDescriptor(ImageDescriptor newImage) {
			}

			@Override
			public void setId(String id) {
			}

			@Override
			public void setImageDescriptor(ImageDescriptor newImage) {
			}

			@Override
			public void setMenuCreator(IMenuCreator creator) {
			}

			@Override
			public void setText(String text) {
			}

			@Override
			public void setToolTipText(String text) {
			}
		};
	}

}
