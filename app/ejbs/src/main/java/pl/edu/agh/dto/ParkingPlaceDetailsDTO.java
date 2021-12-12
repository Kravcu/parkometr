package pl.edu.agh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.enums.ParkingPlaceState;
import pl.edu.agh.model.ParkingPlace;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingPlaceDetailsDTO implements Serializable{
    private Long id;

    private Long sectionNumber;

    private Long placeNumber;

    private String street;

    private ParkingPlaceState currentState;


    public static ParkingPlaceDetailsDTO fromEntity(ParkingPlace parkingPlace) {
        return ParkingPlaceDetailsDTO.builder()
                .id(parkingPlace.getId())
                .currentState(parkingPlace.getCurrentState())
                .placeNumber(parkingPlace.getPlaceNumber())
                .sectionNumber(parkingPlace.getSectionNumber())
                .street(parkingPlace.getStreet())
                .build();
    }
}
