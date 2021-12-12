package pl.edu.agh.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.enums.ParkingPlaceState;
import pl.edu.agh.model.ParkingPlace;
import pl.edu.agh.model.ParkingPlaceStateChange;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingPlaceInfoDTO implements Serializable {
    private Long id;

    private Long sectionNumber;

    private Long placeNumber;

    private ParkingPlaceState currentState;

    private TicketDetailsDTO lastBoughtTicket;

    private List<ParkingPlaceStateChangeDTO> lastStateChanges;


    public static ParkingPlaceInfoDTO fromEntity(ParkingPlace parkingPlace, Integer stateChangesAmount) {


        List<ParkingPlaceStateChange> stateChanges =
                parkingPlace.getStateChanges().size() > stateChangesAmount ?
                        parkingPlace.getStateChanges().subList(0, stateChangesAmount) :
                        parkingPlace.getStateChanges();

        TicketDetailsDTO ticket = parkingPlace.getTickets().isEmpty() ? null : TicketDetailsDTO.fromEntity(parkingPlace.getTickets().get(0));


        return ParkingPlaceInfoDTO.builder()
                .id(parkingPlace.getId())
                .sectionNumber(parkingPlace.getSectionNumber())
                .placeNumber(parkingPlace.getPlaceNumber())
                .currentState(parkingPlace.getCurrentState())
                .lastBoughtTicket(ticket)
                .lastStateChanges(stateChanges.stream().map(ParkingPlaceStateChangeDTO::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
