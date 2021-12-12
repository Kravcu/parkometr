package pl.rwalski.managed.beans;

import lombok.Data;
import pl.rwalski.dto.TicketCreationDTO;
import pl.rwalski.dto.TicketDetailsDTO;
import pl.rwalski.managed.beans.rest.RestClient;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
@Data
public class Index {

    private static final Logger logger = Logger.getLogger(Index.class.toString());

    @Inject
    private RestClient restClient;


    private Long sectionNumber;
    private Long placeNumber;
    private Long minutes;
    private String registerPlate;

    private String message = null;

    public String getTicket() {
        TicketCreationDTO ticketCreationDTO = TicketCreationDTO.builder()
                .durationInMinutes(minutes)
                .placeNumber(placeNumber)
                .sectionNumber(sectionNumber)
                .registerPlate(registerPlate)
                .build();

        TicketDetailsDTO ticketDetailsDTO = null;
        try {
            ticketDetailsDTO = restClient.buyTicketAndReturnDetails(ticketCreationDTO);
            message = "Successfull - you bought ticket with id " + ticketDetailsDTO.getId() + " and it expires " + ticketDetailsDTO.getExpiration().toString();
            logger.info(ticketDetailsDTO.toString());
        } catch (WebApplicationException e) {
            message = "Error occurred with status " + e.getResponse().getStatus();
        }


        return "index";
    }
}
