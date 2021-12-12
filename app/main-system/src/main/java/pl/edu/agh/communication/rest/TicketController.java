package pl.edu.agh.communication.rest;

import pl.edu.agh.dto.NotificationDTO;
import pl.edu.agh.dto.TicketCreationDTO;
import pl.edu.agh.dto.TicketDetailsDTO;
import pl.edu.agh.enums.Event;
import pl.edu.agh.events.NotificationPublisher;
import pl.edu.agh.exceptions.ParkingPlaceNotFoundException;
import pl.edu.agh.model.ParkingPlace;
import pl.edu.agh.model.Ticket;
import pl.edu.agh.repository.impl.ParkingPlaceRepositoryImpl;
import pl.edu.agh.repository.impl.TicketRepositoryImpl;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Singleton
@Path("/tickets")
public class TicketController {

    @Inject
    private TicketRepositoryImpl ticketRepositoryImpl;

    @Inject
    private ParkingPlaceRepositoryImpl parkingPlaceRepositoryImpl;


    @Inject
    private NotificationPublisher notificationPublisher;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TicketDetailsDTO createTicket(TicketCreationDTO ticketDTO) {
        LocalDateTime purchase = LocalDateTime.now();

        ParkingPlace parkingPlace = parkingPlaceRepositoryImpl.getParkingPlace(ticketDTO.getSectionNumber(), ticketDTO.getPlaceNumber());
        if (parkingPlace == null) {
            throw new ParkingPlaceNotFoundException();
        }
        Ticket entity = Ticket.builder()
                .parkingPlace(parkingPlace)
                .purchase(purchase)
                .expiration(purchase.plus(ticketDTO.getDurationInMinutes(), ChronoUnit.MINUTES))
                .registerPlate(ticketDTO.getRegisterPlate())
                .build();

        Ticket ticket = ticketRepositoryImpl.saveTicket(entity);

        notificationPublisher.publish(new NotificationDTO(parkingPlace, Event.TICKET_JUST_BOUGHT));

        return TicketDetailsDTO.builder()
                .id(ticket.getId())
                .expiration(ticket.getExpiration())
                .purchase(ticket.getPurchase())
                .registerPlate(ticket.getRegisterPlate())
                .build();
    }
}
