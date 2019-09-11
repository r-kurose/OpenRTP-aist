
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>serviceport_doc complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="serviceport_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}serviceport">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_serviceport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceport_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    ServiceportExt.class
})
public class ServiceportDoc
    extends Serviceport
{

    @XmlElement(name = "Doc")
    protected DocServiceport doc;

    /**
     * docプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DocServiceport }
     *     
     */
    public DocServiceport getDoc() {
        return doc;
    }

    /**
     * docプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DocServiceport }
     *     
     */
    public void setDoc(DocServiceport value) {
        this.doc = value;
    }

}
