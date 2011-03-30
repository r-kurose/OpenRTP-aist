package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import org.eclipse.emf.ecore.EObject;

/**
 * コネクタのラッパクラス
 */
public class PortConnectorWrapper {
	private EObject connector;

	/**
	 * コンストラクタ
	 * 
	 * @param component
	 *            ドメインモデル
	 */
	public PortConnectorWrapper(EObject connector) {
		this.connector = connector;
	}

	/**
	 * コネクタを取得する
	 * 
	 * @return コネクタ
	 */
	public EObject getConnector() {
		return connector;
	}

	/**
	 * コネクタを設定する
	 * 
	 * @param component
	 *            コネクタ
	 */
	public void setConnector(EObject connector) {
		this.connector = connector;
	}
}
