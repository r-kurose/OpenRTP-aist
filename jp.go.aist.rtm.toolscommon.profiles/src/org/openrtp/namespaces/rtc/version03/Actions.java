
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>actions complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="actions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OnInitialize" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnFinalize" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnStartup" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnShutdown" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnActivated" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnDeactivated" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnAborting" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnError" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnReset" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnExecute" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnStateUpdate" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnRateChanged" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnAction" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnModeChanged" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actions", propOrder = {
    "onInitialize",
    "onFinalize",
    "onStartup",
    "onShutdown",
    "onActivated",
    "onDeactivated",
    "onAborting",
    "onError",
    "onReset",
    "onExecute",
    "onStateUpdate",
    "onRateChanged",
    "onAction",
    "onModeChanged"
})
public class Actions {

    @XmlElement(name = "OnInitialize")
    protected ActionStatus onInitialize;
    @XmlElement(name = "OnFinalize")
    protected ActionStatus onFinalize;
    @XmlElement(name = "OnStartup")
    protected ActionStatus onStartup;
    @XmlElement(name = "OnShutdown")
    protected ActionStatus onShutdown;
    @XmlElement(name = "OnActivated")
    protected ActionStatus onActivated;
    @XmlElement(name = "OnDeactivated")
    protected ActionStatus onDeactivated;
    @XmlElement(name = "OnAborting")
    protected ActionStatus onAborting;
    @XmlElement(name = "OnError")
    protected ActionStatus onError;
    @XmlElement(name = "OnReset")
    protected ActionStatus onReset;
    @XmlElement(name = "OnExecute")
    protected ActionStatus onExecute;
    @XmlElement(name = "OnStateUpdate")
    protected ActionStatus onStateUpdate;
    @XmlElement(name = "OnRateChanged")
    protected ActionStatus onRateChanged;
    @XmlElement(name = "OnAction")
    protected ActionStatus onAction;
    @XmlElement(name = "OnModeChanged")
    protected ActionStatus onModeChanged;

    /**
     * onInitializeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnInitialize() {
        return onInitialize;
    }

    /**
     * onInitializeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnInitialize(ActionStatus value) {
        this.onInitialize = value;
    }

    /**
     * onFinalizeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnFinalize() {
        return onFinalize;
    }

    /**
     * onFinalizeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnFinalize(ActionStatus value) {
        this.onFinalize = value;
    }

    /**
     * onStartupプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnStartup() {
        return onStartup;
    }

    /**
     * onStartupプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnStartup(ActionStatus value) {
        this.onStartup = value;
    }

    /**
     * onShutdownプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnShutdown() {
        return onShutdown;
    }

    /**
     * onShutdownプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnShutdown(ActionStatus value) {
        this.onShutdown = value;
    }

    /**
     * onActivatedプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnActivated() {
        return onActivated;
    }

    /**
     * onActivatedプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnActivated(ActionStatus value) {
        this.onActivated = value;
    }

    /**
     * onDeactivatedプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnDeactivated() {
        return onDeactivated;
    }

    /**
     * onDeactivatedプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnDeactivated(ActionStatus value) {
        this.onDeactivated = value;
    }

    /**
     * onAbortingプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnAborting() {
        return onAborting;
    }

    /**
     * onAbortingプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnAborting(ActionStatus value) {
        this.onAborting = value;
    }

    /**
     * onErrorプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnError() {
        return onError;
    }

    /**
     * onErrorプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnError(ActionStatus value) {
        this.onError = value;
    }

    /**
     * onResetプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnReset() {
        return onReset;
    }

    /**
     * onResetプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnReset(ActionStatus value) {
        this.onReset = value;
    }

    /**
     * onExecuteプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnExecute() {
        return onExecute;
    }

    /**
     * onExecuteプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnExecute(ActionStatus value) {
        this.onExecute = value;
    }

    /**
     * onStateUpdateプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnStateUpdate() {
        return onStateUpdate;
    }

    /**
     * onStateUpdateプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnStateUpdate(ActionStatus value) {
        this.onStateUpdate = value;
    }

    /**
     * onRateChangedプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnRateChanged() {
        return onRateChanged;
    }

    /**
     * onRateChangedプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnRateChanged(ActionStatus value) {
        this.onRateChanged = value;
    }

    /**
     * onActionプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnAction() {
        return onAction;
    }

    /**
     * onActionプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnAction(ActionStatus value) {
        this.onAction = value;
    }

    /**
     * onModeChangedプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnModeChanged() {
        return onModeChanged;
    }

    /**
     * onModeChangedプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnModeChanged(ActionStatus value) {
        this.onModeChanged = value;
    }

}
