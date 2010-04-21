package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.emf.ecore.EObject;

/**
 * コンポーネントのラッパクラス
 */
public class ComponentWrapper {
	private EObject component;

	/**
	 * コンストラクタ
	 * 
	 * @param component
	 *            ドメインモデル
	 */
	public ComponentWrapper(EObject component) {
		this.component = component;
	}

	/**
	 * コンポーネントを取得する
	 * 
	 * @return コンポーネント
	 */
	public EObject getComponent() {
		return component;
	}

	/**
	 * コンポーネントを設定する
	 * 
	 * @param component
	 *            コンポーネント
	 */
	public void setComponent(EObject component) {
		this.component = component;
	}

	/**
	 * containment=falseの要素を取得する (eAllContents()でたどれないため)
	 * 
	 * @return containment=falseの要素リスト
	 */
	public List<EObject> getSubContents() {
		List<EObject> result = new ArrayList<EObject>();
		if (component instanceof Component) {
			Component c = (Component) component;
			for (EObject eo : c.getParticipationContexts()) {
				result.add(eo);
			}
		}
		return result;
	}

}
