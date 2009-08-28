/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getConfigurationSets <em>Configuration Sets</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getActiveConfigurationSet <em>Active Configuration Set</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPorts <em>Ports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInports <em>Inports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getOutports <em>Outports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getServiceports <em>Serviceports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponents <em>Components</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getExecutionContexts <em>Execution Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getChildSystemDiagram <em>Child System Diagram</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVenderL <em>Vender L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getDescriptionL <em>Description L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCategoryL <em>Category L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getTypeNameL <em>Type Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVersionL <em>Version L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPathId <em>Path Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getOutportDirection <em>Outport Direction</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCompositeTypeL <em>Composite Type L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponentId <em>Component Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#isRequired <em>Required</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent()
 * @model abstract="true"
 * @generated
 */
public interface Component extends WrapperObject {
	public static final String CATEGORY = "CATEGORY";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String PATH_URI = "PATH_URI";
	public static final String INSTANCE_NAME = "INSTANCE_NAME";
	public static final String STATE = "STATE";
	public static final String TYPE_NAME = "TYPE_NAME";
	public static final String VENDER = "VENDER";
	public static final String VERSION = "VERSION";
	
	public static final int CHANGE_HORIZON_DIRECTION = 1;
	public static final int CHANGE_VERTICAL_DIRECTION = 2;
	
	public static final String OUTPORT_DIRECTION_RIGHT_LITERAL = "RIGHT";
	public static final String OUTPORT_DIRECTION_LEFT_LITERAL = "LEFT";
	public static final String OUTPORT_DIRECTION_UP_LITERAL = "UP";
	public static final String OUTPORT_DIRECTION_DOWN_LITERAL = "DOWN";

	public static final String COMPOSITETYPE_NONE = "None";
	public static final String COMPOSITETYPE_PERIODIC_EC_SHARED = "PeriodicECShared";
	public static final String COMPOSITETYPE_PERIODIC_STATE_SHARED = "PeriodicStateShared";
	public static final String COMPOSITETYPE_GROUPING = "Grouping";
	
	/**
	 * Returns the value of the '<em><b>Configuration Sets</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ンポーネントが保持するConfigurationSetのリストを取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Sets</em>' containment reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ConfigurationSets()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet" containment="true"
	 * @generated
	 */
	EList getConfigurationSets();

	/**
	 * Returns the value of the '<em><b>Active Configuration Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * アクティブなConfigurationSetを取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Configuration Set</em>' reference.
	 * @see #setActiveConfigurationSet(ConfigurationSet)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ActiveConfigurationSet()
	 * @model
	 * @generated
	 */
	ConfigurationSet getActiveConfigurationSet();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getActiveConfigurationSet <em>Active Configuration Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * アクティブなConfigurationSetを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Configuration Set</em>' reference.
	 * @see #getActiveConfigurationSet()
	 * @generated
	 */
	void setActiveConfigurationSet(ConfigurationSet value);

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.Port}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントが保持するPortのリストを取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' containment reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Ports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.Port" containment="true"
	 * @generated
	 */
	EList getPorts();

	/**
	 * Returns the value of the '<em><b>Inports</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.InPort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントが保持するInPortのリストを取得する。(getPorts()のサブセットが返される)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Inports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.InPort" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getInports();

	/**
	 * Returns the value of the '<em><b>Outports</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.OutPort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントが保持するOutPortのリストを取得する。(getPorts()のサブセットが返される)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Outports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.OutPort" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getOutports();

	/**
	 * Returns the value of the '<em><b>Serviceports</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ServicePort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントが保持するServicePortのリストを取得する。(getPorts()のサブセットが返される)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Serviceports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Serviceports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ServicePort" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getServiceports();

	/**
	 * Returns the value of the '<em><b>Execution Contexts</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Contexts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Contexts</em>' containment reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ExecutionContexts()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ExecutionContext" containment="true"
	 * @generated
	 */
	EList getExecutionContexts();

	/**
	 * Returns the value of the '<em><b>Instance Name L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントのインスタンス名を取得する
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Name L</em>' attribute.
	 * @see #setInstanceNameL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_InstanceNameL()
	 * @model default=""
	 * @generated
	 */
	String getInstanceNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInstanceNameL <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コンポーネントのインスタンス名を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance Name L</em>' attribute.
	 * @see #getInstanceNameL()
	 * @generated
	 */
	void setInstanceNameL(String value);

	/**
	 * Returns the value of the '<em><b>Vender L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントの作成ベンダ名を取得する。ベンダ名はコンポーネントIdの一部として使用される。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vender L</em>' attribute.
	 * @see #setVenderL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_VenderL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getVenderL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVenderL <em>Vender L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コンポーネントの作成ベンダ名を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vender L</em>' attribute.
	 * @see #getVenderL()
	 * @generated
	 */
	void setVenderL(String value);

	/**
	 * Returns the value of the '<em><b>Description L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントの概要情報を取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description L</em>' attribute.
	 * @see #setDescriptionL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_DescriptionL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getDescriptionL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getDescriptionL <em>Description L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コンポーネントの概要情報を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description L</em>' attribute.
	 * @see #getDescriptionL()
	 * @generated
	 */
	void setDescriptionL(String value);

	/**
	 * Returns the value of the '<em><b>Category L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントのカテゴリを取得する。カテゴリはコンポーネントIdの一部として使用される。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category L</em>' attribute.
	 * @see #setCategoryL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_CategoryL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getCategoryL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCategoryL <em>Category L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントのカテゴリを設定する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category L</em>' attribute.
	 * @see #getCategoryL()
	 * @generated
	 */
	void setCategoryL(String value);

	/**
	 * Returns the value of the '<em><b>Type Name L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * RTC仕様の名称を取得する。RTC仕様名称はコンポーネントIdの一部として使用される。
	 * </p>
	 * @return the value of the '<em>Serviceports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Serviceports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ServicePort" transient="true" changeable="false" volatile="true"
	 * @return the value of the '<em>Type Name L</em>' attribute.
	 * @see #setTypeNameL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_TypeNameL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getTypeNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getTypeNameL <em>Type Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * RTC仕様の名称を設定する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name L</em>' attribute.
	 * @see #getTypeNameL()
	 * @generated
	 */
	void setTypeNameL(String value);

	/**
	 * Returns the value of the '<em><b>Version L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * RTC仕様のバージョン番号を取得する。バージョン番号はコンポーネントIdの一部として使用される。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version L</em>' attribute.
	 * @see #setVersionL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_VersionL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getVersionL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVersionL <em>Version L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * RTC仕様のバージョン番号を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version L</em>' attribute.
	 * @see #getVersionL()
	 * @generated
	 */
	void setVersionL(String value);

	/**
	 * Returns the value of the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントのURIを取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Id</em>' attribute.
	 * @see #setPathId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_PathId()
	 * @model
	 * @generated
	 */
	String getPathId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPathId <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コンポーネントのURIを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Id</em>' attribute.
	 * @see #getPathId()
	 * @generated
	 */
	void setPathId(String value);

	/**
	 * Returns the value of the '<em><b>Outport Direction</b></em>' attribute.
	 * The default value is <code>"RIGHT"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントの方向（RIGHT/LEFT/UP/DOWN）を返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Direction</em>' attribute.
	 * @see #setOutportDirection(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_OutportDirection()
	 * @model default="RIGHT"
	 * @generated
	 */
	String getOutportDirection();

	/**
	 * @param value コンポーネントの方向（RIGHT/LEFT/UP/DOWN）
	 */
	void setOutportDirection(String value);

	/**
	 * Returns the value of the '<em><b>Composite Type L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントのコンポジットタイプを取得する。コンポジットタイプはカテゴリから導出される。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Type L</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_CompositeTypeL()
	 * @model transient="true" changeable="false"
	 * @generated
	 */
	String getCompositeTypeL();

	/**
	 * Returns the value of the '<em><b>Components</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.Component}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 子RTCとして含むコンポーネントのリストを返す。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Components()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.Component"
	 * @generated
	 */
	EList getComponents();

	/**
	 * Returns the value of the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントのIDを取得する。コンポーネントIDは
	 * "RTC:" + getVenderL() + "." + getCategoryL () + "."+ getTypeNameL() + ":"+ getVersionL()
	 * で設定される。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Id</em>' attribute.
	 * @see #setComponentId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ComponentId()
	 * @model
	 * @generated
	 */
	String getComponentId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponentId <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コンポーネントのIDを設定する。基本的にはキャッシュの役目しかない。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Id</em>' attribute.
	 * @see #getComponentId()
	 * @generated
	 */
	void setComponentId(String value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コンポーネントが必須であるかを返す。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Required()
	 * @model default="false"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コンポーネントが必須であるかを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Child System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * 複合RTCの内部を表現するダイアグラムを取得する。
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child System Diagram</em>' reference.
	 * @see #setChildSystemDiagram(SystemDiagram)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ChildSystemDiagram()
	 * @model
	 * @generated
	 */
	SystemDiagram getChildSystemDiagram();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getChildSystemDiagram <em>Child System Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * 複合RTCの内部を表現するダイアグラムを設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Child System Diagram</em>' reference.
	 * @see #getChildSystemDiagram()
	 * @generated
	 */
	void setChildSystemDiagram(SystemDiagram value);

	/**
	 * <!-- begin-user-doc -->
	 * コンポーネントのConfigurationSetを更新する。
	 * 必要に応じて、ConfigurationSetの追加／修正／削除とアクティブなConfigurationSetの変更を行う。
	 * <!-- end-user-doc -->
	 * @model listDataType="jp.go.aist.rtm.toolscommon.model.component.List" listMany="false" originallistDataType="jp.go.aist.rtm.toolscommon.model.component.List" originallistMany="false"
	 * @generated
	 */
	boolean updateConfigurationSetListR(List list, ConfigurationSet activeConfigurationSet, List originallist);

	/**
	 * <!-- begin-user-doc -->
	 * 子RTCとして含むコンポーネントを再帰的にすべて取得し、リストとして返す。
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.component.List" many="false"
	 * @generated
	 */
	List getAllComponents();

	/**
	 * <!-- begin-user-doc -->
	 * 当該RTCが複合コンポーネントであるかを返す。
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isCompositeComponent();

	/**
	 * <!-- begin-user-doc -->
	 * 当該RTCが「Grouping」複合RTC（グルーピングだけの複合コンポーネント）であるかを返す。
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isGroupingCompositeComponent();

	/**
	 * <!-- begin-user-doc -->
	 * 当該RTCがオンラインのシステムダイアグラムに含まれるかを返す。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean inOnlineSystemDiagram();

	/**
	 * <!-- begin-user-doc -->
	 * 複合RTCに子RTCを追加する。
	 * <!-- end-user-doc -->
	 * @model componentListDataType="jp.go.aist.rtm.toolscommon.model.component.List" componentListMany="false"
	 * @generated NOT
	 */
	boolean addComponentsR(List<Component> componentList);

	/**
	 * <!-- begin-user-doc -->
	 * 複合RTCから子RTCを削除する
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean removeComponentR(Component component);

	/**
	 * <!-- begin-user-doc -->
	 * 複合RTCが公開しているポートの名称（子RTCのインスタンス名　+　"." + ポート名）のリストを返す。
	 * <!-- end-user-doc -->
	 */
	List<String> getExportedPorts();

	/**
	 * <!-- begin-user-doc -->
	 * 複合RTCが公開しているポートの名称（子RTCのインスタンス名　+　"." + ポート名）のリストを設定する。
	 * <!-- end-user-doc -->
	 * @model valuesType="java.lang.String" valuesMany="true"
	 * @generated
	 */
	boolean setExportedPorts(EList values);

	/**
	 * <!-- begin-user-doc -->
	 * 当該RTCのConfigurationSetを更新する。isActiveがtrueの場合は、アクティベートも行う。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive);

	/**
	 * <!-- begin-user-doc -->
	 * 複合RTCの新規作成時に子RTCのリストを設定する。
	 * <!-- end-user-doc -->
	 * @model componentListDataType="jp.go.aist.rtm.toolscommon.model.component.List" componentListMany="false"
	 * @generated NOT
	 */
	boolean setComponentsR(List<Component> componentList);
	
	/**
	 * <!-- begin-user-doc -->
	 * 新規複合コンポーネント作成ダイアログに表示するパスを返す。
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getPath();

	/**
	 * <!-- begin-user-doc -->
	 * IDに対応付けてExecutionContextを登録します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext setExecutionContext(String id, ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * IDに対応するExecutionContextを取得します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext getExecutionContext(String id);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContextからIDを逆引きして返します。
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getExecutionContextId(ExecutionContext ec);

	/**
	 * 全属性と参照の同期を手動で行う
	 */
	void synchronizeManually();

	/**
	 * 子コンポーネントの同期を手動で行う
	 */
	void synchronizeChildComponents();
	
	/**
	 * 指定した属性（nullの場合は全属性）の同期を実行する
	 * @param reference
	 */
	void synchronizeLocalAttribute(EStructuralFeature reference);

	/**
	 * 参照の同期を実行する
	 */
	void synchronizeLocalReference();

	/**
	 * 子コンポーネントを追加する 
	 */
	void addComponent(Component component);

	/**
	 * コンポーネントのディープコピーを作って返す
	 */
	Component copy();

	/**
	 * pingに応答しない子RTCをcomponentsから取り除く
	 */
	void removeDeadChild();

	/**
	 * @return コンポーネントが生きているか
	 */
	boolean isDead();

} // Component
