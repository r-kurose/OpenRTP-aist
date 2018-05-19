package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import static jp.go.aist.rtm.toolscommon.model.component.Component.OUTPORT_DIRECTION_DOWN_LITERAL;
import static jp.go.aist.rtm.toolscommon.model.component.Component.OUTPORT_DIRECTION_LEFT_LITERAL;
import static jp.go.aist.rtm.toolscommon.model.component.Component.OUTPORT_DIRECTION_RIGHT_LITERAL;
import static jp.go.aist.rtm.toolscommon.model.component.Component.OUTPORT_DIRECTION_UP_LITERAL;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * コンポーネントFigureの内部で使用されるレイアウト
 * <p>
 * コンポーネントFigureのデフォルトサイズ（ポートの数から計算）、方向やポートの位置を計算する
 */
public class ComponentLayout extends XYLayout {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentLayout.class);

	/** コンポーネントの周りとコンポーネントのボディまでのスペース(ポート側) */
	public static final int PORT_SPACE = 32;
	/** コンポーネントの周りとコンポーネントのボディまでのスペース(EC側) */
	public static final int EC_SPACE = 16;

	/** ポートの描画間隔 */
	public static final int PORT_INTERVAL = 22;
	/** ECの描画間隔 */
	public static final int EC_INTERVAL = 13;

	private static final int MIN_WIDTH = 25;
	private static final int MIN_HEIGHT = 25;

	private OutPortLayouter outportLayouter;
	private InPortLayouter inportLayouter;
	private OwnECLayouter ownEcLayouter;
	private PartECLayouter partEcLayouter;

	private Component component;

	/**
	 * コンストラクタ
	 * 
	 * @param Component
	 *            モデル
	 */
	public ComponentLayout(Component component) {
		this.component = component;
		//
		this.outportLayouter = new OutPortLayouter(this);
		this.inportLayouter = new InPortLayouter(this);
		this.ownEcLayouter = new OwnECLayouter(this);
		this.partEcLayouter = new PartECLayouter(this);
	}

	public Component getComponent() {
		return this.component;
	}

	public ChildLayouter getChildLayouter(IFigure child) {
		ChildLayouter layouter = null;
		if (this.outportLayouter.isAssignable(child.getClass())) {
			layouter = this.outportLayouter;
		} else if (this.inportLayouter.isAssignable(child.getClass())) {
			layouter = this.inportLayouter;
		} else if (this.ownEcLayouter.isAssignable(child.getClass())) {
			layouter = this.ownEcLayouter;
		} else if (this.partEcLayouter.isAssignable(child.getClass())) {
			layouter = this.partEcLayouter;
		}
		return layouter;
	}

	@Override
	public void layout(IFigure parent) {
		for (Object o : parent.getChildren()) {
			IFigure child = (IFigure) o;

			ChildLayouter layouter = getChildLayouter(child);
			Rectangle bounds = layouter.getLocation(parent, child);

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

	/**
	 * 子要素のレイアウト処理を表します。
	 */
	public static abstract class ChildLayouter {

		protected ComponentLayout layout;

		public ChildLayouter(ComponentLayout layout) {
			this.layout = layout;
		}

		/**
		 * 対象の子要素のクラスを取得します。
		 * 
		 * @return
		 */
		public abstract Class<?>[] getTargetClasses();

		/**
		 * 子要素の向きを取得します。
		 * 
		 * @return
		 */
		public abstract String getChildDirection();

		/**
		 * 子要素の配置スペックを取得します。
		 * 
		 * @return
		 */
		public abstract Spec getSpec();

		/**
		 * ベースとなる親要素の向きを表します。
		 * 
		 * @return
		 */
		public String getParentDirection() {
			return this.layout.getComponent().getOutportDirection();
		}

		/**
		 * 子要素の順番を取得します。
		 */
		public int getChildIndex(IFigure parent, IFigure child) {
			int count = 0;
			for (Object o : parent.getChildren()) {
				IFigure fig = (IFigure) o;
				if (child == fig) {
					++count;
					break;
				}
				if (isAssignable(fig.getClass())) {
					++count;
				}
			}
			return count;
		}

		/**
		 * 子要素の数を取得します。
		 * 
		 * @param parent
		 * @return
		 */
		public int getChildCount(IFigure parent) {
			int count = 0;
			for (Object o : parent.getChildren()) {
				IFigure child = (IFigure) o;
				if (isAssignable(child.getClass())) {
					++count;
				}
			}
			return count;
		}

		/**
		 * 子要素の配置を算出します。
		 * 
		 * @param parent
		 * @param child
		 * @return
		 */
		public Rectangle getLocation(IFigure parent, IFigure child) {
			// LOGGER.trace("getLocation: parent={} child={}", parent.getClass(), child.getClass());

			Rectangle bounds = child.getBounds().getCopy();
			Point offset = this.layout.getOrigin(parent);

			Rectangle clientArea = parent.getClientArea();
			String direction = getChildDirection();

			int childIndex = getChildIndex(parent, child);

			Spec spec = getSpec();
			// LOGGER.trace("getLocation: bounds={} offset={} client={} childDirection={} h_spase={} v_space={}", bounds, offset,
			// clientArea, direction, spec.h_space, spec.v_space);

			if (direction.equals(OUTPORT_DIRECTION_LEFT_LITERAL)) {
				// 子要素:左向き
				bounds.x = clientArea.x + spec.h_space;
				bounds.y = clientArea.y + spec.v_space + spec.offset + spec.interval * (childIndex - 1);
			} else if (direction.equals(OUTPORT_DIRECTION_RIGHT_LITERAL)) {
				// 子要素:右向き
				bounds.x = clientArea.x + clientArea.width - spec.h_space;
				bounds.y = clientArea.y + spec.v_space + spec.offset + spec.interval * (childIndex - 1);
			} else if (direction.equals(OUTPORT_DIRECTION_UP_LITERAL)) {
				// 子要素:上向き
				bounds.x = clientArea.x + spec.v_space + spec.offset + spec.interval * (childIndex - 1);
				bounds.y = clientArea.y + spec.h_space;
			} else if (direction.equals(OUTPORT_DIRECTION_DOWN_LITERAL)) {
				// 子要素:下向き
				bounds.x = clientArea.x + spec.v_space + spec.offset + spec.interval * (childIndex - 1);
				bounds.y = clientArea.y + clientArea.height - spec.h_space;
			}

			((ComponentChildFigure) child).setDirection(direction);
			bounds = bounds.getTranslated(offset);
			return bounds;
		}

		/**
		 * 対象の子要素かを判定します。
		 * 
		 * @param target
		 * @return
		 */
		public boolean isAssignable(Class<?> target) {
			for (Class<?> tmp : getTargetClasses()) {
				if (tmp.isAssignableFrom(target)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * 配置のスペックを表します。
		 */
		public static class Spec {
			int h_space;
			int v_space;
			int offset;
			int interval;
		}

	}

	/**
	 * 出力ポートの配置を表します。
	 */
	public static class OutPortLayouter extends ChildLayouter {

		public static final Class<?>[] CLASSES = new Class[] { OutPortFigure.class, ServicePortFigure.class };

		public OutPortLayouter(ComponentLayout layout) {
			super(layout);
		}

		@Override
		public Class<?>[] getTargetClasses() {
			return CLASSES;
		}

		@Override
		public String getChildDirection() {
			return getParentDirection();
		}

		@Override
		public Spec getSpec() {
			Spec ret = new Spec();
			ret.h_space = PORT_SPACE - 2;
			ret.v_space = EC_SPACE;
			ret.offset = MIN_HEIGHT / 2;
			ret.interval = PORT_INTERVAL;
			return ret;
		}

	}

	/**
	 * 入力ポートの配置を表します。(サービスポート含む)
	 */
	public static class InPortLayouter extends ChildLayouter {

		public static final Class<?>[] CLASSES = new Class[] { InPortFigure.class };

		public InPortLayouter(ComponentLayout layout) {
			super(layout);
		}

		@Override
		public Class<?>[] getTargetClasses() {
			return CLASSES;
		}

		@Override
		public String getChildDirection() {
			String parentDirection = getParentDirection();
			if (OUTPORT_DIRECTION_RIGHT_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_LEFT_LITERAL;
			} else if (OUTPORT_DIRECTION_LEFT_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_RIGHT_LITERAL;
			} else if (OUTPORT_DIRECTION_UP_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_DOWN_LITERAL;
			} else if (OUTPORT_DIRECTION_DOWN_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_UP_LITERAL;
			}
			return OUTPORT_DIRECTION_LEFT_LITERAL;
		}

		@Override
		public Spec getSpec() {
			Spec ret = new Spec();
			ret.h_space = PORT_SPACE - 2;
			ret.v_space = EC_SPACE;
			ret.offset = MIN_HEIGHT / 2;
			ret.interval = PORT_INTERVAL;
			return ret;
		}

	}

	/**
	 * Owned ECの配置を表します。
	 */
	public static class OwnECLayouter extends ChildLayouter {

		public static final Class<?>[] CLASSES = new Class[] { ECFigure.OwnECFigure.class };

		public OwnECLayouter(ComponentLayout layout) {
			super(layout);
		}

		@Override
		public Class<?>[] getTargetClasses() {
			return CLASSES;
		}

		@Override
		public String getChildDirection() {
			String parentDirection = getParentDirection();
			if (OUTPORT_DIRECTION_RIGHT_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_DOWN_LITERAL;
			} else if (OUTPORT_DIRECTION_LEFT_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_UP_LITERAL;
			} else if (OUTPORT_DIRECTION_UP_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_RIGHT_LITERAL;
			} else if (OUTPORT_DIRECTION_DOWN_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_LEFT_LITERAL;
			}
			return OUTPORT_DIRECTION_DOWN_LITERAL;
		}

		@Override
		public Spec getSpec() {
			Spec ret = new Spec();
			ret.h_space = EC_SPACE;
			ret.v_space = PORT_SPACE;
			ret.offset = EC_INTERVAL / 2;
			ret.interval = EC_INTERVAL;
			return ret;
		}

	}

	/**
	 * Participant ECの配置を表します。
	 */
	public static class PartECLayouter extends ChildLayouter {

		public static final Class<?>[] CLASSES = new Class[] { ECFigure.PartECFigure.class };

		public PartECLayouter(ComponentLayout layout) {
			super(layout);
		}

		@Override
		public Class<?>[] getTargetClasses() {
			return CLASSES;
		}

		@Override
		public String getChildDirection() {
			String parentDirection = getParentDirection();
			if (OUTPORT_DIRECTION_RIGHT_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_UP_LITERAL;
			} else if (OUTPORT_DIRECTION_LEFT_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_DOWN_LITERAL;
			} else if (OUTPORT_DIRECTION_UP_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_LEFT_LITERAL;
			} else if (OUTPORT_DIRECTION_DOWN_LITERAL.equals(parentDirection)) {
				return OUTPORT_DIRECTION_RIGHT_LITERAL;
			}
			return OUTPORT_DIRECTION_UP_LITERAL;
		}

		@Override
		public Spec getSpec() {
			Spec ret = new Spec();
			ret.h_space = EC_SPACE;
			ret.v_space = PORT_SPACE;
			ret.offset = EC_INTERVAL / 2;
			ret.interval = EC_INTERVAL;
			return ret;
		}

	}

	@Override
	public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
		int portCount = Math.max(this.outportLayouter.getChildCount(container), this.inportLayouter.getChildCount(container));
		int ecCount = Math.max(this.ownEcLayouter.getChildCount(container), this.partEcLayouter.getChildCount(container));
		portCount = Math.max(portCount, 1);
		ecCount = Math.max(ecCount, 1);

		int height = EC_SPACE * 2 + MIN_HEIGHT + PORT_INTERVAL * (portCount - 1);
		int width = PORT_SPACE * 2 + MIN_WIDTH + EC_INTERVAL * (ecCount - 1);

		width = Math.max(width, 111);

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
	 * {@inheritDoc}
	 */
	@Override
	public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
		return getMinimumSize(container, wHint, hHint);
	}

}
