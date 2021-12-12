package pl.edu.agh.events;

import pl.edu.agh.dto.NotificationDTO;
import pl.edu.agh.enums.Event;
import pl.edu.agh.model.ParkingPlace;
import pl.edu.agh.model.Ticket;
import pl.edu.agh.repository.impl.ParkingPlaceRepositoryImpl;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Singleton
@Startup
public class EventService {

    @Inject
    private ParkingPlaceRepositoryImpl parkingPlaceRepositoryImpl;

    @Inject
    private NotificationPublisher notificationPublisher;

    private static final Logger logger = Logger.getLogger(EventService.class.toString());

    private final Integer OVERTIME_LIMIT_MINUTES = 10;

    @Transactional
    @Schedule(hour = "*", minute = "*/5", info = "Check expired tickets every 5 minutes")
    public void detectExpiredOrNotBoughtTicket() {
        //order in those free is mantained
        final List<ParkingPlace> occupiedParkingPlaces = parkingPlaceRepositoryImpl.getOccupiedParkingPlaces();
        final List<LocalDateTime> occupationDates = occupiedParkingPlaces.stream().map(pp -> pp.getStateChanges().get(0).getDate()).collect(Collectors.toList());
        final List<Ticket> tickets = occupiedParkingPlaces.stream().map(pp -> pp.getTickets().get(0)).collect(Collectors.toList());

        final List<NotificationDTO> notifications = new LinkedList<>();
        IntStream.range(0, occupiedParkingPlaces.size()).forEach(i -> {
            if (!isTicketBought(tickets.get(i), occupationDates.get(i))) {
                notifications.add(new NotificationDTO(occupiedParkingPlaces.get(0), Event.TICKET_NOT_BOUGHT));
            } else if (isTicketExpired(tickets.get(i))) {
                notifications.add(new NotificationDTO(occupiedParkingPlaces.get(0), Event.TICKET_EXPIRED));
            }

        });

        notificationPublisher.publish(notifications);

    }

    private boolean isTicketExpired(Ticket ticket) {
        return ticket.getExpiration().plus(OVERTIME_LIMIT_MINUTES, ChronoUnit.MINUTES).isAfter(LocalDateTime.now());
    }

    private boolean isTicketBought(Ticket ticket, LocalDateTime occupationDate) {
        return ticket.getPurchase().isAfter(occupationDate) || occupationDate.plus(OVERTIME_LIMIT_MINUTES, ChronoUnit.MINUTES).isAfter(LocalDateTime.now());
    }
}
