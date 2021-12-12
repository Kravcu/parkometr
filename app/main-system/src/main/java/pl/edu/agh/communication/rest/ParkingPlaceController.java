package pl.edu.agh.communication.rest;


import pl.edu.agh.dto.ParkingPlaceDetailsDTO;
import pl.edu.agh.repository.impl.ParkingPlaceRepositoryImpl;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/parking-places")
public class ParkingPlaceController {

    @Inject
    private ParkingPlaceRepositoryImpl parkingPlaceRepositoryImpl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParkingPlaceDetailsDTO> getParkingPlaces(@QueryParam("street") String street) {
        return parkingPlaceRepositoryImpl.getParkingPlacesByStreet(street).stream().map(ParkingPlaceDetailsDTO::fromEntity).collect(Collectors.toList());
    }
}
