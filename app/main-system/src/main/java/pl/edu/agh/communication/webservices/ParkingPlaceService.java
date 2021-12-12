package pl.edu.agh.communication.webservices;


import pl.edu.agh.dto.ParkingPlaceDetailsDTO;
import pl.edu.agh.exceptions.StreetNotFoundException;

import java.util.List;

public interface ParkingPlaceService {
    List<ParkingPlaceDetailsDTO> getParkingPlaces(String street) throws StreetNotFoundException;
}
