package pl.edu.agh.communication.rest.exception.mappers;


import pl.edu.agh.exceptions.ParkingPlaceNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ParkingPlaceNotFoundExceptionMapper implements ExceptionMapper<ParkingPlaceNotFoundException>{

    @Override
    public Response toResponse(ParkingPlaceNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
