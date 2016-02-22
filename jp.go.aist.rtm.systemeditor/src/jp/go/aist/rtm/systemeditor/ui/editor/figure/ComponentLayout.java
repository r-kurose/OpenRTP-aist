package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * コンポーネントFigureの内部で使用されるレイアウト
 * <p>
 * コンポーネントFigureのデフォルトサイズ（ポートの数から計算）、方向やポートの位置を計算する
 */
public class ComponentLayout extends XYLayout {
	private static final int MIN_WIDTH = 60;

	private static final int MIN_HEIGHT = 25;

	private static final int MIN_Component_INTERBAL = 20;

	@SuppressWarnings("unchecked")
	private static final Class[] OUTPUT_CLASSES = new Class[] {
			OutPortFigure.class, ServicePortFigure.class };

	@SuppressWarnings("unchecked")
	private static final Class[] OUTPUT_180_CLASSES = new Class[] { InPortFigure.class };

	private Component component;

	/**
	 * コンストラクタ
	 * 
	 * @param Component
	 *            モデル
	 */
	public ComponentLayout(Component component) {
		this.component = component;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void layout(IFigure parent) {
		Iterator children = parent.getChildren().iterator();
		IFigure child;
		while (children.hasNext()) {
			child = (IFigure) children.next();
			Rectangle bounds = getLocation(parent, child);
			child.setLocation(new Point(bounds.x, bounds.y));
			child.setBounds(child.getBounds());
			parent.setBounds(parent.getBounds());
		}
	}

	/** ポートが垂直表示の場合はtrue */
	public boolean isVerticalDirection() {
		String direction = this.component.getOutportDirection();
		return direction.equals(Component.OUTPORT_DIRECTION_UP_LITERAL)
				|| direction.equals(Component.OUTPORT_DIRECTION_DOWN_LITERAL);
	}

	@SuppressWarnings("unchecked")
	private Rectangle getLocation(IFigure parent, IFigure child) {
		Rectangle bounds = child.getBounds().getCopy();
		Point offset = getOrigin(parent);

		Rectangle clientArea = parent.getClientArea();
		String direction = getDirection(child.getClass(), component.getOutportDirection());

		Class[] figureClass = null;
		if (isAssignable(child.getClass(), OUTPUT_180_CLASSES)) {
			figureClass = OUTPUT_180_CLASSES;
		} else if (isAssignable(child.getClass(), OUTPUT_CLASSES)) {
			figureClass = OUTPUT_CLASSES;
		}

		// ポートの描画位置へのオフセット 2009.2.2
		int portOffset = ComponentEditPart.PORT_SPACE - 2;
		int portNumber = getTargetOccurenceNumber(parent, child,
				figureClass);

		if (direction.equals(Component.OUTPORT_DIRECTION_LEFT_LITERAL)) {
			bounds.x = clientArea.x + portOffset;
			bounds.y = clientArea.y + ComponentEditPart.NONE_SPACE
					+ (MIN_HEIGHT / 2) + MIN_Component_INTERBAL
					* (portNumber - 1);
			
		} else if(direction.equals(Component.OUTPORT_DIRECTION_RIGHT_LITERAL)) {
			bounds.x = clientArea.x + clientArea.width - portOffset;
			bounds.y = clientArea.y + ComponentEditPart.NONE_SPACE
					+ (MIN_HEIGHT / 2) + MIN_Component_INTERBAL
					* (portNumber - 1);
		} else if (direction.equals(Component.OUTPORT_DIRECTION_UP_LITERAL)) {
			bounds.x = clientArea.x + ComponentEditPart.NONE_SPACE
					+ (MIN_HEIGHT / 2) + MIN_Component_INTERBAL
					* (portNumber - 1);
			bounds.y = clientArea.y + portOffset;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_DOWN_LITERAL)) {
			bounds.x = clientArea.x + ComponentEditPart.NONE_SPACE
			+ (MIN_HEIGHT / 2) + MIN_Component_INTERBAL
			* (portNumber - 1);
			bounds.y = clientArea.y + clientArea.height - portOffset;
		}

		((PortFigure) child).setDirection(direction);
		bounds = bounds.getTranslated(offset);
		return bounds;
	}

	private String getDirection(Class<? extends IFigure> clazz,
			String outportDirection) {
		if (isAssignable(clazz, OUTPUT_CLASSES)) {
			return outportDirection;
		} else if (isAssignable(clazz, OUTPUT_180_CLASSES)) {
			if (Component.OUTPORT_DIRECTION_RIGHT_LITERAL.equals(outportDirection)) {
				return Component.OUTPORT_DIRECTION_LEFT_LITERAL;
			} else if (Component.OUTPORT_DIRECTION_LEFT_LITERAL.equals(outportDirection)) {
				return Component.OUTPORT_DIRECTION_RIGHT_LITERAL;
			} else if (Component.OUTPORT_DIRECTION_UP_LITERAL.equals(outportDirection)) {
				return Component.OUTPORT_DIRECTION_DOWN_LITERAL;
			} else if (Component.OUTPORT_DIRECTION_DOWN_LITERAL.equals(outportDirection)) {
				return Component.OUTPORT_DIRECTION_UP_LITERAL;
			}
		}
		return Component.OUTPORT_DIRECTION_RIGHT_LITERAL;
	}

	@SuppressWarnings("unchecked")
	private int getAllOccurenceCount(IFigure parent, Class[] targetClasses) {
		int count = 0;
		for (Iterator iter = parent.getChildren().iterator(); iter.hasNext();) {
			IFigure child = (IFigure) iter.next();
			if (isAssignable(child.getClass(), targetClasses)) {
				++count;
			}
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	private int getTargetOccurenceNumber(IFigure parent, IFigure target,
			Class[] targetClasses) {
		int count = 0;
		for (Iterator iter = parent.getChildren().iterator(); iter.hasNext();) {
			IFigure child = (IFigure) iter.next();
			if (child == target) {
				++count;
				break;
			}
			if (isAssignable(child.getClass(), targetClasses)) {
				++count;
			}
		}

		return count;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
		int count = Math.max(
				getAllOccurenceCount(container, OUTPUT_180_CLASSES),
				getAllOccurenceCount(container, OUTPUT_CLASSES));

		int height = ComponentEditPart.NONE_SPACE * 2 + MIN_HEIGHT
				+ MIN_Component_INTERBAL * (count - 1);
		int width = 111;

		Dimension result = new Dimension();

		if (isVerticalDirection()) {
			result.height = Math.max(width, MIN_WIDTH);
			result.width = Math.max(height, MIN_HEIGHT);			
		} else {
			result.height = Math.max(height, MIN_HEIGHT);
			result.width = Math.max(width, MIN_WIDTH);			
		}

		return result;
	}

	/**
	 * 対象クラスがclassesに含まれているかどうか
	 * 
	 * @param target
	 * @param classes
	 */
	@SuppressWarnings("unchecked")
	private boolean isAssignable(Class target, Class[] classes) {
		boolean result = false;
		for (Class tmp : classes) {
			if (tmp.isAssignableFrom(target)) {
				result = true;
				break;
			}
		}

		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
		return getMinimumSize(container, wHint, hHint);
	}
}
