package pl.edu.agh.communication.rest.exception.mappers;

import pl.edu.agh.exceptions.InvalidReportingRequestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidReportingRequestExceptionMapper implements ExceptionMapper<InvalidReportingRequestException>{
    @Override
    public Response toResponse(InvalidReportingRequestException exception) {
        return  Response.status(400).build();
    }
}
