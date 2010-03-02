package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

import org.eclipse.emf.common.util.EList;

/**
 * ConfigurationSetを表現するクラス
 * <p>
 * 
 * @model このオブジェクトは、バリューオブジェクトであることに注意すること。<br>
 *        このオブジェクト自体は同期が行われないため、このオブジェクトの参照を保持し続けることは、危険である。<br>
 *        事情が許す限り、参照元のオブジェクトを参照して、必要になるたびにそこから手に入れること。
 */
public interface ConfigurationSet extends WrapperObject{
	/**
	 * @model
	 * @return コンフィグセットのID
	 */
	public String getId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * @model containment="true"
	 *        type="jp.go.aist.rtm.toolscommon.model.component.NameValue"
	 * @return　コンフィグデータを格納するNameValueのリスト
	 */
	public EList getConfigurationData();

}
