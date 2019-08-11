
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dataport_doc complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="dataport_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}dataport">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_dataport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataport_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    DataportExt.class
})
public class DataportDoc
    extends Dataport
{

    @XmlElement(name = "Doc")
    protected DocDataport doc;

    /**
     * docプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DocDataport }
     *     
     */
    public DocDataport getDoc() {
        return doc;
    }

    /**
     * docプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DocDataport }
     *     
     */
    public void setDoc(DocDataport value) {
        this.doc = value;
    }

}
