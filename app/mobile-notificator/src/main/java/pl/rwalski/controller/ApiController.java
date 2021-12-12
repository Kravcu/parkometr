package pl.rwalski.controller;

import pl.edu.agh.dto.NotificationDTO;
import pl.edu.agh.repository.NotificationRepository;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
@Path("/notifications")
public class ApiController {


    @EJB(mappedName = "ejb:/ejbs/NotificationRepositoryImpl!pl.edu.agh.repository.NotificationRepository")
    private NotificationRepository notificationRepository;

    private static final Logger logger = Logger.getLogger(ApiController.class.toString());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NotificationDTO> getNotifications(
            @QueryParam("from") String from,
            @QueryParam("section") Long sectionNumber
    ) {

        logger.info(" " + from + "---" +  sectionNumber);
        return notificationRepository.get(LocalDateTime.parse(from), sectionNumber)
                .stream()
                .map(NotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
