
package com.edu.agh.sei;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.edu.agh.sei package. 
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

    private final static QName _MarkParkingPlace_QNAME = new QName("http://impl.webservices.communication.agh.edu.pl/", "markParkingPlace");
    private final static QName _GetAllParkingPlaces_QNAME = new QName("http://impl.webservices.communication.agh.edu.pl/", "getAllParkingPlaces");
    private final static QName _GetAllParkingPlacesResponse_QNAME = new QName("http://impl.webservices.communication.agh.edu.pl/", "getAllParkingPlacesResponse");
    private final static QName _MarkParkingPlaceResponse_QNAME = new QName("http://impl.webservices.communication.agh.edu.pl/", "markParkingPlaceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.edu.agh.sei
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MarkParkingPlaceResponse }
     * 
     */
    public MarkParkingPlaceResponse createMarkParkingPlaceResponse() {
        return new MarkParkingPlaceResponse();
    }

    /**
     * Create an instance of {@link GetAllParkingPlaces }
     * 
     */
    public GetAllParkingPlaces createGetAllParkingPlaces() {
        return new GetAllParkingPlaces();
    }

    /**
     * Create an instance of {@link MarkParkingPlace }
     * 
     */
    public MarkParkingPlace createMarkParkingPlace() {
        return new MarkParkingPlace();
    }

    /**
     * Create an instance of {@link ParkingPlaceDetailsDTO }
     * 
     */
    public ParkingPlaceDetailsDTO createParkingPlaceDetailsDTO() {
        return new ParkingPlaceDetailsDTO();
    }

    /**
     * Create an instance of {@link GetAllParkingPlacesResponse }
     * 
     */
    public GetAllParkingPlacesResponse createGetAllParkingPlacesResponse() {
        return new GetAllParkingPlacesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarkParkingPlace }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservices.communication.agh.edu.pl/", name = "markParkingPlace")
    public JAXBElement<MarkParkingPlace> createMarkParkingPlace(MarkParkingPlace value) {
        return new JAXBElement<MarkParkingPlace>(_MarkParkingPlace_QNAME, MarkParkingPlace.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllParkingPlaces }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservices.communication.agh.edu.pl/", name = "getAllParkingPlaces")
    public JAXBElement<GetAllParkingPlaces> createGetAllParkingPlaces(GetAllParkingPlaces value) {
        return new JAXBElement<GetAllParkingPlaces>(_GetAllParkingPlaces_QNAME, GetAllParkingPlaces.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllParkingPlacesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservices.communication.agh.edu.pl/", name = "getAllParkingPlacesResponse")
    public JAXBElement<GetAllParkingPlacesResponse> createGetAllParkingPlacesResponse(GetAllParkingPlacesResponse value) {
        return new JAXBElement<GetAllParkingPlacesResponse>(_GetAllParkingPlacesResponse_QNAME, GetAllParkingPlacesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MarkParkingPlaceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservices.communication.agh.edu.pl/", name = "markParkingPlaceResponse")
    public JAXBElement<MarkParkingPlaceResponse> createMarkParkingPlaceResponse(MarkParkingPlaceResponse value) {
        return new JAXBElement<MarkParkingPlaceResponse>(_MarkParkingPlaceResponse_QNAME, MarkParkingPlaceResponse.class, null, value);
    }

}
