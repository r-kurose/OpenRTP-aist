
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>basic_info_doc complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="basic_info_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}basic_info">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_basic" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basic_info_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    BasicInfoExt.class
})
public class BasicInfoDoc
    extends BasicInfo
{

    @XmlElement(name = "Doc")
    protected DocBasic doc;

    /**
     * docプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DocBasic }
     *     
     */
    public DocBasic getDoc() {
        return doc;
    }

    /**
     * docプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DocBasic }
     *     
     */
    public void setDoc(DocBasic value) {
        this.doc = value;
    }

}
