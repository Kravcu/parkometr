package pl.edu.agh.communication.webservices.impl;

import pl.edu.agh.communication.webservices.ParkingPlaceService;
import pl.edu.agh.dto.ParkingPlaceDetailsDTO;
import pl.edu.agh.exceptions.StreetNotFoundException;
import pl.edu.agh.repository.impl.ParkingPlaceRepositoryImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService(name="ParkingPlaceService")
public class ParkingPlaceServiceImpl implements ParkingPlaceService {

    @Inject
    private ParkingPlaceRepositoryImpl parkingPlaceRepositoryImpl;

    @Override
    public List<ParkingPlaceDetailsDTO> getParkingPlaces(String street) throws StreetNotFoundException {
        return parkingPlaceRepositoryImpl.getParkingPlacesByStreet(street).stream().map(ParkingPlaceDetailsDTO::fromEntity).collect(Collectors.toList());
    }
}
