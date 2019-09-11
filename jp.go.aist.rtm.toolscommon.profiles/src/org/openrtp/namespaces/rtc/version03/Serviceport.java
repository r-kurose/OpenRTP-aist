
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>serviceport complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
 * 
 * <pre>
 * &lt;complexType name="serviceport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceInterface" type="{http://www.openrtp.org/namespaces/rtc}serviceinterface" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TransMethods" type="{http://www.openrtp.org/namespaces/rtc}transmission_method" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceport", propOrder = {
    "serviceInterface",
    "transMethods"
})
@XmlSeeAlso({
    ServiceportDoc.class
})
public class Serviceport {

    @XmlElement(name = "ServiceInterface")
    protected List<Serviceinterface> serviceInterface;
    @XmlElement(name = "TransMethods")
    protected List<TransmissionMethod> transMethods;
    @XmlAttribute(name = "name", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String name;

    /**
     * Gets the value of the serviceInterface property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceInterface property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceInterface().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Serviceinterface }
     * 
     * 
     */
    public List<Serviceinterface> getServiceInterface() {
        if (serviceInterface == null) {
            serviceInterface = new ArrayList<Serviceinterface>();
        }
        return this.serviceInterface;
    }

    /**
     * Gets the value of the transMethods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transMethods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransMethods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransmissionMethod }
     * 
     * 
     */
    public List<TransmissionMethod> getTransMethods() {
        if (transMethods == null) {
            transMethods = new ArrayList<TransmissionMethod>();
        }
        return this.transMethods;
    }

    /**
     * name�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }
    public void setServiceInterface(List<Serviceinterface> list) {
    }
    public void setTransMethods(List<TransmissionMethod> list) {
    }

}
