package pl.rwalski.managed.beans;

import pl.edu.agh.dto.ParkingPlaceInfoDTO;
import pl.edu.agh.dto.ParkingPlaceStateChangeDTO;
import pl.edu.agh.dto.TicketDetailsDTO;
import pl.edu.agh.service.DashboardService;
import pl.edu.agh.enums.ParkingPlaceState;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@ManagedBean
@RequestScoped
public class Index {

    @EJB(mappedName = "ejb:/ejbs/DashboardServiceImpl!pl.edu.agh.service.DashboardService")
    private DashboardService dashboardService;

    @Inject
    private SessionsContainer sessionsContainer;

    private static final Logger logger = Logger.getLogger(Index.class.toString());

    private List<ParkingPlaceInfoDTO> parkingPlaces;

    @PostConstruct
    private void init() {
        parkingPlaces = dashboardService.getParkingPlacesInfo(3);
    }

    public Map<Long, List<ParkingPlaceInfoDTO>> getParkingPlacesMap() {
        Map<Long, List<ParkingPlaceInfoDTO>> map = parkingPlaces.stream().collect(Collectors.groupingBy(ParkingPlaceInfoDTO::getSectionNumber));
        map.values().forEach(v -> v.sort(Comparator.comparingLong(ParkingPlaceInfoDTO::getPlaceNumber)));
        return map;
    }

    public String getStatus(ParkingPlaceInfoDTO parkingPlaceInfoDTO) {
        TicketDetailsDTO ticket = parkingPlaceInfoDTO.getLastBoughtTicket();

        if (parkingPlaceInfoDTO.getLastStateChanges().isEmpty()) {
            return "ERROR OCCURRED";
        }
        ParkingPlaceStateChangeDTO lastStateChange = parkingPlaceInfoDTO.getLastStateChanges().get(0);

        if (lastStateChange.getState() == ParkingPlaceState.FREE) {
            return "PLACE IS FREE";
        } else if (lastStateChange.getState() == ParkingPlaceState.OCCUPIED) {
            if (ticket == null) {
                return "PLACE OCCUPIED AND NO TICKET BOUGHT";
            } else {
                LocalDateTime now = now();
                long between = Math.abs(ChronoUnit.MINUTES.between(ticket.getExpiration(), now));
                if (ticket.getExpiration().isAfter(now)) {
                    return "TICKET IS VALID " + between + " MORE MINUTES";
                } else {
                    return "TICKET EXPIRED " + between + " MINUTES AGO";

                }
            }
        } else {
            return "ERROR OCCURRED";
        }


    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        logger.info("User is logging out " + externalContext.getRemoteUser() + " " + sessionsContainer.contains(externalContext.getRemoteUser()) + " " + sessionsContainer.size());
        sessionsContainer.delete(externalContext.getRemoteUser());
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/restricted/index.xhtml?faces-redirect=true");
    }
}
