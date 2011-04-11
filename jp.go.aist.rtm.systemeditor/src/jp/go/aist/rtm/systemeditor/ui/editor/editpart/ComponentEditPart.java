package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.action.ChangeComponentDirectionAction;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.direct.NameCellEditorLocator;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.direct.NameDirectEditManager;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ChangeDirectionEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ComponentComponentEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.EditPolicyConstraint;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.NameDirectEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ComponentLayout;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * コンポーネントのEditPart
 * <p>
 * GEFの仕様では子供のEditPartは親のEditPartに含まれなければならないが、ポートをコンポーネントからはみ出して表示しなければならない。
 * これを満たしながら、一見コンポーネントの外にポートが出ているように見せるために、コンポーネントのボディのドローイングの範囲を狭めることで実現しているため、特殊な実装になっている。
 */
public class ComponentEditPart extends AbstractEditPart {

	/** コンポーネントの周りとコンポーネントのボディまでのスペース(ポートあり) */
	public static final int PORT_SPACE = 23;

	/** コンポーネントの周りとコンポーネントのボディまでのスペース(ポートなし) */
	public static final int NONE_SPACE = 7;

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	private ComponentFloatingLabel componentLabel;

	NameDirectEditManager directManager = null;

	/**
	 * コンストラクタ
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public ComponentEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		Figure result = new Panel() {

			@Override
			/**
			 * {@inheritDoc}
			 */
			protected boolean useLocalCoordinates() {
				return true;
			}

			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * コンポーネントの外にポートが出ているように見せるために、コンポーネントのボディのドローイングの範囲を狭めている
			 */
			protected void paintFigure(Graphics graphics) {
				if (isOpaque()) {
					ComponentLayout cl = (ComponentLayout)this.getLayoutManager();
					Rectangle bound = new Rectangle(getBounds());
					// ポートのある側のスペースを広くとる 2009.2.2
					if (cl.isVerticalDirection()) {
						graphics.fillRectangle(bound.expand(-NONE_SPACE, -PORT_SPACE));
					} else {
						graphics.fillRectangle(bound.expand(-PORT_SPACE, -NONE_SPACE));
					}

					Color saveForegroundColor = graphics.getForegroundColor();
					graphics.drawRectangle(bound);
					graphics.setForegroundColor(saveForegroundColor);
				}
			}

			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * コンポーネントの外にポートが出ているように見せるため、空実装
			 */
			protected void paintBorder(Graphics graphics) {
				// void
			}

			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * コンポーネントの制約が変更されるたびに、ラベルも移動させる。
			 * （責務の分離からすればあまりよくないが、ファイル内に閉じているのでここに実装にした）
			 */
			public void setBounds(Rectangle rect) {
				super.setBounds(rect);

				Rectangle newComponentLabelRectangle = componentLabel
						.getTextBounds().getCopy();
				newComponentLabelRectangle.x = rect.getCenter().x
						- componentLabel.getTextBounds().width / 2;
				newComponentLabelRectangle.y = rect.getBottom().y;

				componentLabel.setBounds(newComponentLabelRectangle);

				propertyChangeSupport.firePropertyChange("Bounds", null, rect);
			}

		};

		result.addMouseListener(new MouseListener.Stub() {
			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * コンポーネントを右クリック（+Shift）して、方向を変換する機能の実装
			 */
			public void mousePressed(MouseEvent me) {
				if (me.button == 3) { // right click
					IAction action = null;
					if (me.getState() == MouseEvent.SHIFT) {
						action = getActionRegistry()
								.getAction(
										ChangeComponentDirectionAction.VERTICAL_DIRECTION_ACTION_ID);
					} else if (me.getState() == MouseEvent.CONTROL){
						action = getActionRegistry()
								.getAction(
										ChangeComponentDirectionAction.HORIZON_DIRECTION_ACTION_ID);
					}

					if (action != null) action.run();
				}
			}
		});

		ComponentLayout layout = new ComponentLayout(getModel());
		result.setLayoutManager(layout);

		result.setBackgroundColor(ColorConstants.orange);

		// 注意：ComponentLabelの親はSystemDiagram
		componentLabel = new ComponentFloatingLabel(
				((AbstractGraphicalEditPart) getParent()).getFigure());
		componentLabel.setText(getModel().getInstanceNameL());
		componentLabel.setSize(30, 10);

		getRoot().refresh();
		
		return result;
	}

	/**
	 * 設定マネージャを監視するリスナ
	 */
	PropertyChangeListener preferenceChangeListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			refreshComponent();
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void activate() {
		super.activate();
		if (getModel().isCompositeComponent()) {
			for (Iterator iterator = getModel().getAllComponents().iterator(); iterator
					.hasNext();) {
				Component component = (Component) iterator.next();
				component.eAdapters().add(this);
			}
		}
		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceChangeListener);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		componentLabel.deactivate();
		super.deactivate();
		if (getModel().isCompositeComponent()) {
			for (Iterator iterator = getModel().getAllComponents().iterator(); iterator
					.hasNext();) {
				Component component = (Component) iterator.next();
				component.eAdapters().remove(this);
			}
		}
		SystemEditorPreferenceManager.getInstance()
				.removePropertyChangeListener(preferenceChangeListener);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new ComponentComponentEditPolicy());
		installEditPolicy(EditPolicyConstraint.CHANGE_DIRECTION_ROLE,
				new ChangeDirectionEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new NameDirectEditPolicy());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		getFigure().setBackgroundColor(getNewBodyColor());

		getFigure().setForegroundColor(getNewBorderColor());

		jp.go.aist.rtm.toolscommon.model.core.Rectangle constraint = getModel()
				.getConstraint();
		if (constraint == null) return;
		
		Rectangle modelRec = Draw2dUtil.toDraw2dRectangle(constraint);

		Rectangle newRectangle = modelRec.getCopy();

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), newRectangle);
	}

	/**
	 * 最新のボーダー部の色を取得する
	 * 
	 * @return
	 */
	public Color getNewBorderColor() {
		Color exexucitonContextColor = SystemEditorPreferenceManager
				.getInstance().getColor(
						SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
		if (getModel() instanceof CorbaComponent) {
			CorbaComponent component = (CorbaComponent) getModel();
			if (component.getExecutionContextState() == ExecutionContext.STATE_RUNNING) {
				exexucitonContextColor = SystemEditorPreferenceManager
						.getInstance()
						.getColor(
								SystemEditorPreferenceManager.COLOR_RTC_EXECUTION_CONTEXT_RUNNING);
			} else if (component.getExecutionContextState() == ExecutionContext.STATE_STOPPED) {
				exexucitonContextColor = SystemEditorPreferenceManager
						.getInstance()
						.getColor(
								SystemEditorPreferenceManager.COLOR_RTC_EXECUTION_CONTEXT_STOPPED);
			} else if (component.getExecutionContextState() == ExecutionContext.STATE_UNKNOWN) {
				exexucitonContextColor = SystemEditorPreferenceManager
						.getInstance()
						.getColor(
								SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
			}
		}
		

		return exexucitonContextColor;
	}

	/**
	 * 最新のボディ部の色を取得する
	 * 
	 * @return
	 */
	public Color getNewBodyColor() {
		Color stateColor = SystemEditorPreferenceManager
				.getInstance()
				.getColor(SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
		if (getModel() instanceof CorbaComponent) {
			CorbaComponent component = (CorbaComponent) getModel();

			stateColor = this.getStateColor(component.getComponentState());

		} else if (getModel() instanceof ComponentSpecification){
			ComponentSpecification component = (ComponentSpecification) getModel();

			if (component.inOnlineSystemDiagram()
					&& component.isGroupingCompositeComponent()) {
				// オンラインGrouping複合RTCの場合は、子RTCの状態から色を設定
				Set<Integer> sts = new HashSet<Integer>();
				for (Object o : component.getComponents()) {
					if (o instanceof CorbaComponent) {
						CorbaComponent c = (CorbaComponent) o;
						sts.add(new Integer(c.getComponentState()));
					}
				}
				int state = ExecutionContext.RTC_UNKNOWN;
				if (sts.size() == 1) {
					Integer st = (Integer) sts.iterator().next();
					state = st.intValue();
				} else {
					if (sts.contains(new Integer(ExecutionContext.RTC_ERROR))) {
						state = ExecutionContext.RTC_ERROR;
					} else {
						state = ExecutionContext.RTC_UNCERTAIN;
					}
				}
				stateColor = this.getStateColor(state);

			} else {
				stateColor = SystemEditorPreferenceManager.getInstance().getColor(
						SystemEditorPreferenceManager.COLOR_RTC_STATE_INACTIVE);
			}
		}

		return stateColor;
	}

	Color getStateColor(int status) {
		// コンポーネントの状態色を判定
		if (status == ExecutionContext.RTC_ACTIVE) {
			return SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_ACTIVE);
		} else if (status == ExecutionContext.RTC_CREATED) {
			return SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_CREATED);
		} else if (status == ExecutionContext.RTC_ERROR) {
			return SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_ERROR);
		} else if (status == ExecutionContext.RTC_INACTIVE) {
			return SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_INACTIVE);
		} else if (status == ExecutionContext.RTC_UNKNOWN) {
			return SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
		} else if (status == ExecutionContext.RTC_UNCERTAIN) {
			return SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_UNCERTAIN);
		}
		return SystemEditorPreferenceManager.getInstance().getColor(
				SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Component getModel() {
		return (Component) super.getModel();
	}

	/**
	 * {@inheritDoc}component.eAdapters().add(this);
	 */
	@SuppressWarnings("unchecked")
	public void notifyChanged(Notification notification) {
		if (ComponentPackage.eINSTANCE.getComponent_Components().equals(
						notification.getFeature())) {
			if (notification.getEventType() == Notification.ADD) {
				Component component = (Component)notification.getNewValue();
				component.eAdapters().add(this);
				if (component.isCompositeComponent()) {
					for (Iterator iterator = component.getAllComponents()
							.iterator(); iterator.hasNext();) {
						((Component) iterator.next()).eAdapters().add(this);
					}
				}
			}else if (notification.getEventType() == Notification.REMOVE) {
				((Component)notification.getOldValue()).eAdapters().remove(this);
			}
			refreshComponent();
			((SystemDiagramEditPart)getParent()).refreshSystemDiagram();
		} else if (CorePackage.eINSTANCE.getModelElement_Constraint().equals(
						notification.getFeature())
				|| ComponentPackage.eINSTANCE.getCorbaComponent_ComponentState()
						.equals(notification.getFeature())
				|| ComponentPackage.eINSTANCE
						.getCorbaComponent_ExecutionContextState().equals(
								notification.getFeature())
				|| ComponentPackage.eINSTANCE
						.getComponent_OutportDirection().equals(
								notification.getFeature())){
			refreshComponent();			
		} else if (ComponentPackage.eINSTANCE.getComponent_Ports()
						.equals(notification.getFeature())) {
			refreshComponent2();
		} else if (ComponentPackage.eINSTANCE.getComponent_InstanceNameL()
				.equals(notification.getFeature())) {
			if (notification.getEventType() == Notification.SET) {
				componentLabel.setText(notification.getNewStringValue());
			}
		}else if (getModel() instanceof ComponentSpecification) {
			refreshComponent();
		}
	}

	private void refreshComponent() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (isActive()) {
					refresh();
					refreshChildren();
					getFigure().invalidate();
				}
			}
		});
	}

	private void refreshComponent2() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (isActive()) {
					refresh();
					refreshChildren2();
					refreshChildDiagram();
					getFigure().invalidate();
				}
			}

			private void refreshChildren2() {
				int i;
				EditPart editPart;
				Object model;

				Map modelToEditPart = new HashMap();
				List children = getChildren();

				for (i = 0; i < children.size(); i++) {
					editPart = (EditPart)children.get(i);
					modelToEditPart.put(editPart.getModel(), editPart);
				}

				List modelObjects = getModelChildren();

				for (i = 0; i < modelObjects.size(); i++) {
					model = modelObjects.get(i);
					EditPart ep = (EditPart) children.get(i);
					//Do a quick check to see if editPart[i] == model[i]
					if (i < children.size()
						&& ep.getModel() == model) {
						ep.refresh();
						continue;						
					}


					//Look to see if the EditPart is already around but in the wrong location
					editPart = (EditPart)modelToEditPart.get(model);

					if (editPart != null)
						reorderChild (editPart, i);
					else {
						//An editpart for this model doesn't exist yet.  Create and insert one.
						editPart = createChild(model);
						addChild(editPart, i);
					}
				}
				List trash = new ArrayList();
				for (; i < children.size(); i++)
					trash.add(children.get(i));
				for (i = 0; i < trash.size(); i++) {
					EditPart ep = (EditPart)trash.get(i);
					removeChild(ep);
				}
			}
			

			private void refreshChildDiagram() {
				// 複合RTCエディタ内の子RTCのポート再描画
				SystemDiagram diagram = getModel().getChildSystemDiagram();
				if (diagram != null) {
					AbstractSystemDiagramEditor editor = ComponentUtil
							.findEditor(diagram);
					if (editor == null) return;
					for (Object o : editor.getSystemDiagram().getComponents()) {
						if (!(o instanceof Component))
							continue;
						Component ac = (Component) o;
						EditPart ep = editor.findEditPart(ac);
						if (!(ep instanceof ComponentEditPart))
							continue;
						ComponentEditPart cep = (ComponentEditPart) ep;
						List childList = new ArrayList(cep.getChildren());
						for (Object o2 : childList) {
							if (!(o2 instanceof PortEditPart))
								continue;
							cep.refreshPortEditPart((PortEditPart) o2);
						}
					}
					editor.refresh();
				}
			}

		});
	}

	/** ポート公開/非公開時にPortEditPartを更新します */
	@SuppressWarnings("unchecked")
	public void refreshPortEditPart(final PortEditPart portPart) {
		Object model = portPart.getModel();
		List children = getModelChildren();
		int index = children.indexOf(model);

		setFocus(false);
		removeChild(portPart);
		if (index != -1) {
			EditPart editPart = createChild(model);
			addChild(editPart, index);
			setFocus(true);
		}
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				setFocus(false);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected List getModelChildren() {
		List result = new ArrayList();
		// 複合コンポーネントに直接属するポートだけを表示させる 2008.11.26
		result.addAll(getModel().getInports());
		result.addAll(getModel().getOutports());
		result.addAll(getModel().getServiceports());

		return result;
	}

	/**
	 * コンポーネントFigureの変更の通知を行うリスナを登録する
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * コンポーネントFigureの変更の通知を行うリスナを削除する
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * システムダイアグラムのコンポーネントに表示されるラベル
	 */
	public class ComponentFloatingLabel extends Label {

		/**
		 * コンストラクタ
		 * 
		 * @param parentFigure
		 *            親フィギュア
		 */
		public ComponentFloatingLabel(IFigure parentFigure) {
			setParent(parentFigure);
			parentFigure.add(this);
		}

		/**
		 * 削除する場合に呼び出されることを意図する
		 */
		public void deactivate() {
			getParent().remove(this);
		}

		@Override
		/**
		 * {@inheritDoc}
		 */
		public boolean isFocusTraversable() {
			return false;
		}

		@Override
		/**
		 * {@inheritDoc}
		 */
		public boolean isRequestFocusEnabled() {
			return false;
		}

		@Override
		/**
		 * {@inheritDoc}
		 */
		protected boolean isMouseEventTarget() {
			return false;
		}
	}

	@Override
	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			if (getModel().isCompositeComponent()) {
				IAction action = getActionRegistry().getAction(
						OpenCompositeComponentAction.ACTION_ID);
				OpenCompositeComponentAction openAction = new OpenCompositeComponentAction(
							((OpenCompositeComponentAction) action)
									.getParentSystemDiagramEditor());
				openAction.setCompositeComponent(getModel());
				openAction.run();
			}
		}
		// オフラインエディタの場合はインスタンス名の直接編集可
		boolean offline = false;
		if (getParent() != null
				&& getParent().getModel() instanceof SystemDiagram) {
			SystemDiagram sd = (SystemDiagram) getParent().getModel();
			offline = SystemDiagramKind.OFFLINE_LITERAL.equals(sd.getKind());
		}
		if (offline && req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
			if (directManager == null) {
				directManager = new NameDirectEditManager(this,
						TextCellEditor.class, new NameCellEditorLocator(
								getFigure()));
			}
			directManager.show();
			return;
		}
		super.performRequest(req);
	}

}
