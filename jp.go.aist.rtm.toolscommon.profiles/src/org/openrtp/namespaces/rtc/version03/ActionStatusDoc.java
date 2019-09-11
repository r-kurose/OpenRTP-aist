
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>action_status_doc complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="action_status_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}action_status">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_action" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action_status_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
public class ActionStatusDoc
    extends ActionStatus
{

    @XmlElement(name = "Doc")
    protected DocAction doc;

    /**
     * docプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DocAction }
     *     
     */
    public DocAction getDoc() {
        return doc;
    }

    /**
     * docプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DocAction }
     *     
     */
    public void setDoc(DocAction value) {
        this.doc = value;
    }

}
