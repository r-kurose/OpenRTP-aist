
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>configuration_doc complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="configuration_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}configuration">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_configuration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuration_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
@XmlSeeAlso({
    ConfigurationExt.class
})
public class ConfigurationDoc
    extends Configuration
{

    @XmlElement(name = "Doc")
    protected DocConfiguration doc;

    /**
     * docプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DocConfiguration }
     *     
     */
    public DocConfiguration getDoc() {
        return doc;
    }

    /**
     * docプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DocConfiguration }
     *     
     */
    public void setDoc(DocConfiguration value) {
        this.doc = value;
    }

}
