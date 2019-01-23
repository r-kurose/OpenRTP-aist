package jp.go.aist.rtm.systemeditor.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.extension.CreateComponentCommandExtension;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import static jp.go.aist.rtm.toolscommon.util.ComponentUtil.*;

public class ComponentCommandCreator {

	static final String EXTENTION_POINT_NAME = "createcomponentcommand";
	static List<CreateComponentCommandExtension> creators;

	public static class CommandPair {
		public Command command;
		public Component component;
	}

	String message = "";

	public ComponentCommandCreator() {
		if (creators == null) {
			buildCreator();
		}
	}

	public String getMessage() {
		return this.message;
	}

	public MultiCreateCommand getCreateCommand(CreateRequest request,
			SystemDiagram diagram) {
		MultiCreateCommand result = new MultiCreateCommand();

		if (!List.class.isAssignableFrom((Class<?>) request.getNewObjectType())) {
			this.message = "Target RTC is not correctly specified.";
			return result;
		}

		List<?> components = (List<?>) request.getNewObject();
		if (components == null) {
			// Skip when RTC in the list disappear during DnD because of refreshing on NS.
			this.message = "No target RTC exist.";
			return result;
		}
		List<Component> childComponents = new ArrayList<Component>();
		for (Object o : components) {
			Component c = (Component) o;
			if (c.isCompositeComponent()) {
				childComponents.addAll(c.getAllComponents());
			}
		}

		for (Object o : components) {
			final Component c = (Component) o;
			if (find(c, childComponents) != null) {
				continue;
			}
			CreateRequest req = new CreateRequest() {
				Component component = c;

				@Override
				public Object getNewObject() {
					return component;
				}

				@Override
				public Object getNewObjectType() {
					return component.getClass();
				}
			};
			CommandPair pair = createCommandPair(req, diagram);
			if (pair != null) {
				result.getCommandPairs().add(pair);
			}
		}
		return result;
	}
	
	public CommandPair createCommandPair(CreateRequest request,
			SystemDiagram diagram) {
		CommandPair result = new CommandPair();
		for (CreateComponentCommandExtension ext : creators) {
			ext.setRequest(request);
			ext.setDiagram(diagram);
			if (!ext.canCreate()) {
				continue;
			}
			Command command = ext.getCreateCommand();
			if (command == null) {
				message = ext.getMessage();
				break;
			}
			result.command = command;
			result.component = ext.getComponent();
			return result;
		}
		return null;
	}

	static void buildCreator() {
		creators = new ArrayList<CreateComponentCommandExtension>();
		//
		String ns = RTSystemEditorPlugin.class.getPackage().getName();
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint(ns, EXTENTION_POINT_NAME).getExtensions();
		for (IExtension ex : extensions) {
			for (IConfigurationElement ce : ex.getConfigurationElements()) {
				Object obj;
				try {
					obj = ce.createExecutableExtension("extensionclass");
					if (obj instanceof CreateComponentCommandExtension) {
						creators.add((CreateComponentCommandExtension) obj);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		// デフォルトのファクトリ
		CreateComponentCommandExtension ext = new CreateComponentCommandExtension() {
			final String MSG_ALREADY_EXIST_1 = Messages
					.getString("ComponentCommandCreator.1");
			final String MSG_ALREADY_EXIST_2 = Messages
					.getString("ComponentCommandCreator.2");

			@Override
			public boolean canCreate() {
				return true;
			}

			@Override
			public Command getCreateCommand() {
				if (request == null || diagram == null) {
					return null;
				}
				component = null;

				Component comp = getNewObject();
				if (comp == null) {
					return null;
				}

				SystemDiagram sd = diagram.getRootDiagram();

				if (comp instanceof ComponentSpecification) {
					String name = createUniqueInstanceName(
							(ComponentSpecification) comp, sd);
					setNewInstanceName(comp, name);
				} else {
					Component lc = findInDiagram(comp, sd);
					if (lc != null) {
						message = MSG_ALREADY_EXIST_1 + lc.getInstanceNameL()
								+ MSG_ALREADY_EXIST_2;
						return null;
					}
				}

				component = comp;
				Command command = createCreateCommand(comp, diagram);
				return command;
			}
		};
		creators.add(ext);
	}

	public static class MultiCreateCommand extends Command {
		List<CommandPair> commandPairs;

		public MultiCreateCommand() {
			commandPairs = new ArrayList<CommandPair>();
		}

		public List<CommandPair> getCommandPairs() {
			return commandPairs;
		}

		@Override
		public boolean canExecute() {
			for (CommandPair cp : commandPairs) {
				if (!cp.command.canExecute()) {
					return false;
				}
			}
			return true;
		}

		@Override
		public void execute() {
			for (CommandPair cp : commandPairs) {
				cp.command.execute();
			}
		}
	}
	
}
