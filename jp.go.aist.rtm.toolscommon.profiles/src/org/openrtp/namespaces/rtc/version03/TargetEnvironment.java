
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>target_environment complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
 * 
 * <pre>
 * &lt;complexType name="target_environment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="osVersions" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cpus" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="libraries" type="{http://www.openrtp.org/namespaces/rtc_ext}library" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="langVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="os" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="other" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cpuOther" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "target_environment", namespace = "http://www.openrtp.org/namespaces/rtc_ext", propOrder = {
    "osVersions",
    "cpus",
    "libraries"
})
public class TargetEnvironment {

    protected List<String> osVersions;
    protected List<String> cpus;
    protected List<Library> libraries;
    @XmlAttribute(name = "langVersion", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
    protected String langVersion;
    @XmlAttribute(name = "os", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
    protected String os;
    @XmlAttribute(name = "other", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
    protected String other;
    @XmlAttribute(name = "cpuOther", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
    protected String cpuOther;

    /**
     * Gets the value of the osVersions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the osVersions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOsVersions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOsVersions() {
        if (osVersions == null) {
            osVersions = new ArrayList<String>();
        }
        return this.osVersions;
    }

    /**
     * Gets the value of the cpus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cpus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCpus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCpus() {
        if (cpus == null) {
            cpus = new ArrayList<String>();
        }
        return this.cpus;
    }

    /**
     * Gets the value of the libraries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libraries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibraries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Library }
     * 
     * 
     */
    public List<Library> getLibraries() {
        if (libraries == null) {
            libraries = new ArrayList<Library>();
        }
        return this.libraries;
    }

    /**
     * langVersion�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangVersion() {
        return langVersion;
    }

    /**
     * langVersion�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangVersion(String value) {
        this.langVersion = value;
    }

    /**
     * os�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOs() {
        return os;
    }

    /**
     * os�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOs(String value) {
        this.os = value;
    }

    /**
     * other�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOther() {
        return other;
    }

    /**
     * other�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOther(String value) {
        this.other = value;
    }

    /**
     * cpuOther�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuOther() {
        return cpuOther;
    }

    /**
     * cpuOther�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuOther(String value) {
        this.cpuOther = value;
    }
    public void setLibraries(List<Library> list) {
    }
    public void setCpus(List<String> ist) {
    }
    public void setOsVersions(List<String> list) {
    }

}
