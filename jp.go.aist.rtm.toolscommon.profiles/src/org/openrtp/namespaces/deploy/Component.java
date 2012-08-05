
package org.openrtp.namespaces.deploy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for component complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="component">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="instanceName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="deployType">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="None"/>
 *             &lt;enumeration value="Component"/>
 *             &lt;enumeration value="Manager"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "component")
public class Component {

    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/deploy", required = true)
    protected String id;
    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/deploy", required = true)
    protected String instanceName;
    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/deploy")
    protected String deployType;
    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/deploy")
    protected String target;
    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/deploy")
    protected String ior;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the instanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Sets the value of the instanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceName(String value) {
        this.instanceName = value;
    }

    /**
     * Gets the value of the deployType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployType() {
        return deployType;
    }

    /**
     * Sets the value of the deployType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployType(String value) {
        this.deployType = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the ior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIor() {
        return ior;
    }

    /**
     * Sets the value of the ior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIor(String value) {
        this.ior = value;
    }

}
