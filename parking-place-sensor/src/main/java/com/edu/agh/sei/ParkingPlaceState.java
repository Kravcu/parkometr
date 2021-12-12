
package com.edu.agh.sei;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parkingPlaceState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="parkingPlaceState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FREE"/>
 *     &lt;enumeration value="OCCUPIED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "parkingPlaceState")
@XmlEnum
public enum ParkingPlaceState {

    FREE,
    OCCUPIED;

    public String value() {
        return name();
    }

    public static ParkingPlaceState fromValue(String v) {
        return valueOf(v);
    }

}
