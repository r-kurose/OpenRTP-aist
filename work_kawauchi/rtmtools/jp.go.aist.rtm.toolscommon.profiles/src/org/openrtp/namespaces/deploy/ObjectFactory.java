
package org.openrtp.namespaces.deploy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openrtp.namespaces.deploy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeployProfile_QNAME = new QName("http://www.openrtp.org/namespaces/deploy", "DeployProfile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openrtp.namespaces.deploy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link DeployProfile }
     * 
     */
    public DeployProfile createDeployProfile() {
        return new DeployProfile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeployProfile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openrtp.org/namespaces/deploy", name = "DeployProfile")
    public JAXBElement<DeployProfile> createDeployProfile(DeployProfile value) {
        return new JAXBElement<DeployProfile>(_DeployProfile_QNAME, DeployProfile.class, null, value);
    }

}
