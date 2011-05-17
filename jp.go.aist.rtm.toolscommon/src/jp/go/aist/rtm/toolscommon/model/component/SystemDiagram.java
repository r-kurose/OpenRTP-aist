package jp.go.aist.rtm.toolscommon.model.component;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.core.ModelElement;

import org.eclipse.emf.common.util.EList;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

/**
 * システムダイアグラム（エディタ）を表現するクラス
 * @model
 */
public interface SystemDiagram extends ModelElement, IPropertyMap {
	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link jp.go.aist.rtm.toolscommon.model.component.Component}. <!--
	 * begin-user-doc -->
	 * <p>
	 * ダイアグラムに含まれるコンポーネントのリストを返す。
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Components</em>' containment reference
	 *         list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_Components()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.Component"
	 *        containment="true" resolveProxies="false"
	 * @generated
	 */
	EList<Component> getComponents();

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ダイアグラムの種類を返す。オンラインか、オフラインのいずれかである。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
	 * @see #setKind(SystemDiagramKind)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_Kind()
	 * @model
	 * @generated
	 */
	SystemDiagramKind getKind();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * ダイアグラムの種類を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(SystemDiagramKind value);

	/**
	 * <!-- begin-user-doc -->
	 * 何ミリ秒おきに含んでいるコンポーネントを更新するかを設定する。
	 * オンラインシステムエディタの作成時、破棄時、システムエディタの設定画面で接続周期を変更したときに呼び出される。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setSynchronizeInterval(long milliSecond);

	/**
	 * コンポーネンツ変更の通知を行うリスナを登録する
	 * 現在は、複合RTCの削除時に、複合RTCの内部表示ウィンドウを閉じる際に使用されている。
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * コンポーネンツ変更の通知を行うリスナを削除する
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * @return コネクタ描画処理中であるか
	 */
	public boolean isConnectorProcessing();
	
	/**
	 * @param connectorProcessing コネクタ描画処理中であるか
	 */
	public void setConnectorProcessing(boolean connectorProcessing);
	/**
	 * Returns the value of the '<em><b>System Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * システムIDを取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Id</em>' attribute.
	 * @see #setSystemId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_SystemId()
	 * @model
	 * @generated
	 */
	String getSystemId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getSystemId <em>System Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * システムIDを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Id</em>' attribute.
	 * @see #getSystemId()
	 * @generated
	 */
	void setSystemId(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 作成日時を取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_CreationDate()
	 * @model
	 * @generated
	 */
	String getCreationDate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 作成日時を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(String value);

	/**
	 * Returns the value of the '<em><b>Update Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 最終更新日時を取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Update Date</em>' attribute.
	 * @see #setUpdateDate(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_UpdateDate()
	 * @model
	 * @generated
	 */
	String getUpdateDate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getUpdateDate <em>Update Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 最終更新日時を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Date</em>' attribute.
	 * @see #getUpdateDate()
	 * @generated
	 */
	void setUpdateDate(String value);

	/**
	 * Returns the value of the '<em><b>Parent System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 複合RTCの内部を表現するダイアグラムの場合、その複合RTCが含まれている元のダイアグラムを返す。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent System Diagram</em>' reference.
	 * @see #setParentSystemDiagram(SystemDiagram)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_ParentSystemDiagram()
	 * @model
	 * @generated
	 */
	SystemDiagram getParentSystemDiagram();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getParentSystemDiagram <em>Parent System Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * 複合RTCの内部を表現するダイアグラムに対し、複合RTCが含まれている元のダイアグラムを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent System Diagram</em>' reference.
	 * @see #getParentSystemDiagram()
	 * @generated
	 */
	void setParentSystemDiagram(SystemDiagram value);

	/**
	 * Returns the value of the '<em><b>Composite Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 複合RTCの内部を表現するダイアグラムの場合、その複合RTCを返す。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Component</em>' reference.
	 * @see #setCompositeComponent(Component)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_CompositeComponent()
	 * @model
	 * @generated
	 */
	Component getCompositeComponent();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCompositeComponent <em>Composite Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * 複合RTCの内部を表現するダイアグラムに対し、複合RTCを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composite Component</em>' reference.
	 * @see #getCompositeComponent()
	 * @generated
	 */
	void setCompositeComponent(Component value);

	/**
	 * @param profile 読み込んだRtsProfileExt
	 */
	void setProfile(RtsProfileExt profile);

	/**
	 * @return 読み込んだRtsProfileExt
	 */
	RtsProfileExt getProfile();

	/**
	 * @return 複合RTCの内部を表現するダイアグラムでない、RTシステムそのものを表現するダイアグラム
	 */
	SystemDiagram getRootDiagram();

	/**
	 * @return ダイアグラム内に含まれるコネクタをコネクタIDとConnectorのマップで返す。
	 */
	Map<String, PortConnector> getConnectorMap();

	/**
	 * @param component	削除するコンポーネント
	 */
	void removeComponent(Component component);

	/**
	 * @param components　削除するコンポーネント
	 */
	void removeComponents(List<Component> components);

	/**
	 * @param component	追加するコンポーネント
	 */
	void addComponent(Component component);

	/**
	 * @param pos		追加する位置
	 * @param component	追加するコンポーネント
	 */
	void addComponent(int pos, Component component);

	/**
	 * @param ｃomponents	追加するコンポーネント
	 */
	void addComponents(List<Component> components);

	/**
	 * 子コンポーネントをクリアする
	 */
	void clearComponents();

	/**
	 * システムダイアグラム内の全コンポーネントを手動で更新する
	 */
	boolean synchronizeManually();

	/**
	 * ルートのシステムダイアグラムに含まれる全コンポーネントをリストにして返す
	 */
	List<Component> getRegisteredComponents();

}
