
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.openrtp.namespaces.rtc.version02.ConstraintType;


/**
 * <p>dataport complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
 * 
 * <pre>
 * &lt;complexType name="dataport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Constraint" type="{http://www.openrtp.org/namespaces/rtc}constraint_type" minOccurs="0"/>
 *         &lt;element name="Event" type="{http://www.openrtp.org/namespaces/rtc}event" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="portType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="DataInPort"/>
 *             &lt;enumeration value="DataOutPort"/>
 *             &lt;enumeration value="EventPort"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idlFile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="interfaceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dataflowType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="subscriptionType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataport", propOrder = {
    "constraint",
    "event"
})
@XmlSeeAlso({
    DataportDoc.class
})
public class Dataport {

    @XmlElement(name = "Constraint")
    protected ConstraintType constraint;
    @XmlElement(name = "Event")
    protected List<Event> event;
    @XmlAttribute(name = "portType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String portType;
    @XmlAttribute(name = "name", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String name;
    @XmlAttribute(name = "type", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String type;
    @XmlAttribute(name = "idlFile", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String idlFile;
    @XmlAttribute(name = "interfaceType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String interfaceType;
    @XmlAttribute(name = "dataflowType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String dataflowType;
    @XmlAttribute(name = "subscriptionType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String subscriptionType;
    @XmlAttribute(name = "unit", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String unit;

    /**
     * constraint�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ConstraintType }
     *     
     */
    public ConstraintType getConstraint() {
        return constraint;
    }

    /**
     * constraint�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ConstraintType }
     *     
     */
    public void setConstraint(ConstraintType value) {
        this.constraint = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the event property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Event }
     * 
     * 
     */
    public List<Event> getEvent() {
        if (event == null) {
            event = new ArrayList<Event>();
        }
        return this.event;
    }

    /**
     * portType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortType() {
        return portType;
    }

    /**
     * portType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortType(String value) {
        this.portType = value;
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

    /**
     * type�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * type�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * idlFile�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdlFile() {
        return idlFile;
    }

    /**
     * idlFile�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdlFile(String value) {
        this.idlFile = value;
    }

    /**
     * interfaceType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterfaceType() {
        return interfaceType;
    }

    /**
     * interfaceType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterfaceType(String value) {
        this.interfaceType = value;
    }

    /**
     * dataflowType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataflowType() {
        return dataflowType;
    }

    /**
     * dataflowType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataflowType(String value) {
        this.dataflowType = value;
    }

    /**
     * subscriptionType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * subscriptionType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionType(String value) {
        this.subscriptionType = value;
    }

    /**
     * unit�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * unit�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}
