package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortGraphicalNodeEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.PortAnchor;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.PortFigure;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;
import jp.go.aist.rtm.toolscommon.model.component.AbstractPortConnector;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnectorSpecification;
import jp.go.aist.rtm.toolscommon.model.component.PortProfile;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.ui.PlatformUI;

/**
 * ポートのEditPartクラス
 */
public abstract class PortEditPart extends AbstractEditPart implements
		NodeEditPart {

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public PortEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new PortGraphicalNodeEditPolicy());
	}

	/**
	 * {@inheritDoc}
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new PortAnchor(getFigure());
	}

	/**
	 * {@inheritDoc}
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new PortAnchor(getFigure());
	}

	/**
	 * {@inheritDoc}
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new PortAnchor(getFigure());
	}

	/**
	 * {@inheritDoc}
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new PortAnchor(getFigure());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public PortFigure getFigure() {
		return (PortFigure) super.getFigure();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected List getModelTargetConnections() {
		List list = new ArrayList();
		list.addAll(getModel().getTargetConnectors());
		removeCompositeConnector(list);
		return list;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected List getModelSourceConnections() {
		List list = new ArrayList();
		list.addAll(getModel().getSourceConnectors());
		removeCompositeConnector(list);
		return list;
	}

	@Override
	public Port getModel() {
		return (Port) super.getModel();
	}

	/**
	 * 設定マネージャを監視するリスナ
	 */
	PropertyChangeListener preferenceChangeListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			refreshVisuals();
		}
	};

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void activate() {
		super.activate();

		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceChangeListener);
		if (getModel().eContainer() instanceof ComponentSpecification) {
			getModel().getPortProfile().eAdapters().add(new Adapter());
		}
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		super.deactivate();

		SystemEditorPreferenceManager.getInstance()
				.removePropertyChangeListener(preferenceChangeListener);
	}
	
	protected boolean isConnected() {
		Port port = getModel();
		if (port.getPortProfile() == null) {
			return false;
		}
		if (port.getPortProfile().getConnectorProfiles().isEmpty()) {
			return false;
		}

		AbstractComponent comp = (AbstractComponent) port.eContainer();
		if (comp.getCompositeComponent() == null) {
			return true;
		}

		AbstractComponent root = comp;
		for (; root.getCompositeComponent() != null;) {
			root = root.getCompositeComponent();
		}
		SystemDiagram sd = (SystemDiagram) comp.eContainer();
		SystemDiagram osd = ComponentUtil.getRootSystemDiagram(sd);
		AbstractComponent ocomp = ComponentUtil.findComponentByPathId(comp, osd);
		AbstractComponent oroot = ComponentUtil.findComponentByPathId(root, osd);

		Port oport = getCopyOf(port, ocomp);

		PortProfile opp = oport.getPortProfile();
		if (opp == null) {
			return false;
		}
		for (Object o : opp.getConnectorProfiles()) {
			ConnectorProfile e = (ConnectorProfile) o;
			AbstractComponent other = findConnectableLeefComponentByConnectorId(ocomp, e, osd);
			if (other == null) {
				continue;
			}
			if (oroot.getAllComponents().contains(other) == false) {
				return true;
			}
		}
		return false;
	}

	private Port getCopyOf(Port port, AbstractComponent comp) {

		PortProfile pp = port.getPortProfile();

		for (Object o : comp.getPorts()) {
			Port e = (Port) o;

			PortProfile epp = e.getPortProfile();
			for (Object oo : epp.getConnectorProfiles()) {
				ConnectorProfile ee = (ConnectorProfile) oo;
				String id = ee.getConnectorId();
				if (id == null) {
					continue;
				}
				for (Object ooo : pp.getConnectorProfiles()) {
					ConnectorProfile eee = (ConnectorProfile) ooo;
					if (id.equals(eee.getConnectorId())) {
						return e;
					}
				}
			}

		}
		return null;
	}

	private AbstractComponent findConnectableLeefComponentByConnectorId(AbstractComponent comp, ConnectorProfile profile, SystemDiagram sd) {

		String id = profile.getConnectorId();

		for (Object o : sd.getComponents()) {
			AbstractComponent e = (AbstractComponent) o;
			if (e == comp) {
				continue;
			}
			if (e.getComponents().isEmpty() == false) {
				continue;
			}
			for (Object oo : e.getPorts()) {
				Port ee = (Port) oo;
				PortProfile eepp = ee.getPortProfile();
				for (Object ooo : eepp.getConnectorProfiles()) {
					ConnectorProfile eee = (ConnectorProfile) ooo;
					if (id.equals(eee.getConnectorId())) {
						return e;
					}
				}
			}
		}
		return null;
	}

	private class Adapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (ComponentPackage.eINSTANCE.getPortProfile_ConnectorProfiles()
					.equals(msg.getFeature())
					&& (msg.getEventType() == Notification.ADD || msg
							.getEventType() == Notification.REMOVE)) {
				if (getModel().eContainer().eContainer() instanceof SystemDiagram) {
					SystemDiagram systemDiagram = (SystemDiagram) getModel()
							.eContainer().eContainer();
					SystemDiagram rootDiagram = ComponentUtil.getRootSystemDiagram(systemDiagram);
					List<SystemDiagram> systemDiagrams = new ArrayList<SystemDiagram>();
					if (rootDiagram.isConnectorProcessing()) {
						//void
					}else{
						rootDiagram.setConnectorProcessing(true);
						systemDiagrams.add(rootDiagram);
						for (Object object : rootDiagram.getComponents()) {
							OpenCompositeComponentAction action = (OpenCompositeComponentAction) ((ComponentSpecification) object)
									.getOpenCompositeComponentAction();
							if (action != null
									&& action
									.getCompositeComponentEditor()
									.getSystemDiagram() != systemDiagram) {
								systemDiagrams.add(action
										.getCompositeComponentEditor()
										.getSystemDiagram());
							}

						}
						for (SystemDiagram diagram : systemDiagrams) {
							AbstractComponent specification = ComponentUtil
									.findComponentByPathId(
											(AbstractComponent) getModel()
													.eContainer(), diagram);
							if (specification != null){
								for (Object port : specification.getPorts()) {
									if (((Port) port).getPortProfile().getNameL()
											.equals(
													getModel().getPortProfile()
															.getNameL())) {
										if (msg.getEventType() == Notification.ADD) {
											boolean exist = false;
											for (Object obj : ((Port) port)
													.getPortProfile()
													.getConnectorProfiles()) {
												if (((ConnectorProfile) obj)
														.getConnectorId()
														.equals(
																((ConnectorProfile) msg
																		.getNewValue())
																		.getConnectorId())) {
													exist = true;
													break;
												}
											}
											if (!exist) {
												ConnectorProfile copy = (ConnectorProfile) EcoreUtil
														.copy((EObject) msg
																.getNewValue());
												if (((ConnectorProfile) msg
														.getNewValue()).eContainer() instanceof PortConnectorSpecification) {
													PortConnectorSpecification connectorSpecification = (PortConnectorSpecification) EcoreUtil
															.copy(((ConnectorProfile) msg
																	.getNewValue())
																	.eContainer());
													connectorSpecification
															.setConnectorProfile(copy);
												}

												((Port) port).getPortProfile()
														.getConnectorProfiles().add(
																copy);
											}
										} else {
											List<PortConnectorSpecification> list = new ArrayList<PortConnectorSpecification>();
											for (Iterator iterator = ((Port) port)
													.getPortProfile()
													.getConnectorProfiles().iterator(); iterator
													.hasNext();) {
												ConnectorProfile connectorProfile = (ConnectorProfile) iterator
														.next();
												if (connectorProfile
														.getConnectorId()
														.equals(
																((ConnectorProfile) msg
																		.getOldValue())
																		.getConnectorId())) {
													if (connectorProfile.eContainer() instanceof PortConnectorSpecification) {
														list.add((PortConnectorSpecification) connectorProfile
																.eContainer());
													}
												}
											}
											for (PortConnectorSpecification connector :list) {
												connector.deleteConnectorR();
												EcoreUtil.remove(connector);
											}
										}
									}
								}
							}
							ComponentUtil.createSpecificationConnector(diagram, true);
						}
//										} else {
//											for (Iterator iterator = ((Port) port)
//													.getPortProfile()
//													.getConnectorProfiles().iterator(); iterator
//													.hasNext();) {
//												ConnectorProfile connectorProfile = (ConnectorProfile) iterator
//														.next();
//												if (connectorProfile
//														.getConnectorId()
//														.equals(
//																((ConnectorProfile) msg
//																		.getOldValue())
//																		.getConnectorId())) {
//													iterator.remove();
//												}
//											}
//
//										}
//
//									}
//								}	
//							}
//						}
					}
					rootDiagram.setConnectorProcessing(false);
				}
			}
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (isActive()) {
						refresh();
						refreshVisuals();
						refreshTargetConnections();
					}
				}
			});
		}
	}
	
	private void removeCompositeConnector(List list) {
		for (Iterator iterator = list.iterator();iterator.hasNext();) {
			AbstractPortConnector connector = (AbstractPortConnector) iterator.next();
			if (connector.getSource() == null
					&& connector.getTarget().getSynchronizationSupport() != null) {
				connector.getTarget().getSynchronizationSupport().synchronizeLocal();
			}
			if (connector.getTarget() == null
					&& connector.getSource().getSynchronizationSupport() != null) {
				connector.getSource().getSynchronizationSupport().synchronizeLocal();
			}
			if (connector.getSource() == null
					|| connector.getTarget() == null) {
				continue;
			}
			Port[] link = new Port[]{
					(Port)connector.getSource(),
					(Port)connector.getTarget()};
			boolean rmove = false;
			AbstractComponent compositeComponent = null;
			List<AbstractComponent> components = new ArrayList<AbstractComponent>();
			for (int i = 0; i < link.length && !rmove; i++) {
				AbstractComponent component = null;
				if (link[i].eContainer() instanceof AbstractComponent) {
					component = (AbstractComponent) link[i].eContainer();
					if (component.isCompositeComponent()) {
						compositeComponent = component;
					} else {
						compositeComponent = component.getCompositeComponent();
					}
					while (compositeComponent != null) {
						if (components.contains(compositeComponent)) {
							iterator.remove();
							break;
						}
						components.add(compositeComponent);
						compositeComponent = compositeComponent
								.getCompositeComponent();
					}
				}
			}
		}
	}
}
