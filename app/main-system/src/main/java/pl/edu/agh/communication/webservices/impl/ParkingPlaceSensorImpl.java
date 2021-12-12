package pl.edu.agh.communication.webservices.impl;

import pl.edu.agh.dto.NotificationDTO;
import pl.edu.agh.dto.ParkingPlaceDetailsDTO;
import pl.edu.agh.enums.Event;
import pl.edu.agh.enums.ParkingPlaceState;
import pl.edu.agh.events.NotificationPublisher;
import pl.edu.agh.model.ParkingPlace;
import pl.edu.agh.model.ParkingPlaceStateChange;
import pl.edu.agh.repository.impl.ParkingPlaceRepositoryImpl;
import pl.edu.agh.communication.webservices.ParkingPlaceSensor;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebService(name = "ParkingPlaceSensor")
public class ParkingPlaceSensorImpl implements ParkingPlaceSensor {

    private static final Logger logger = Logger.getLogger(ParkingPlaceSensorImpl.class.toString());

    @Inject
    private ParkingPlaceRepositoryImpl repository;

    @Inject
    private NotificationPublisher notificationPublisher;


    @Override
    @WebMethod
    @Transactional
    public void markParkingPlace(Long parkingPlaceId, ParkingPlaceState state) {

        ParkingPlace parkingPlace = repository.getParkingPlace(parkingPlaceId);
        ParkingPlaceStateChange stateChange = ParkingPlaceStateChange.builder()
                .date(LocalDateTime.now())
                .parkingPlace(parkingPlace)
                .state(state)
                .build();

        parkingPlace.setCurrentState(state);
        repository.updateParkingPlace(parkingPlace);
        repository.saveStateChange(stateChange);

        if (ParkingPlaceState.FREE == state) {
            notificationPublisher.publish(new NotificationDTO(parkingPlace, Event.PARKING_PLACE_RELEASED));
        } else if (ParkingPlaceState.OCCUPIED == state) {
            notificationPublisher.publish(new NotificationDTO(parkingPlace, Event.PARKING_PLACE_OCCUPIED));
        }
    }

    @Override
    @WebMethod
    @Transactional
    public List<ParkingPlaceDetailsDTO> getAllParkingPlaces() {
        return repository.getAllParkingPlaces()
                .stream()
                .map(ParkingPlaceDetailsDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
