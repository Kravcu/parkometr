package pl.edu.agh.communication.webservices;

import pl.edu.agh.dto.ParkingPlaceDetailsDTO;
import pl.edu.agh.enums.ParkingPlaceState;

import java.util.List;


public interface ParkingPlaceSensor {

    void markParkingPlace(Long sensorId, ParkingPlaceState state);

    List<ParkingPlaceDetailsDTO> getAllParkingPlaces();
}
