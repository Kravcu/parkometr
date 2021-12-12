package pl.edu.agh.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.dto.adapters.LocalDateAdapter;
import pl.edu.agh.enums.ParkingPlaceState;
import pl.edu.agh.model.ParkingPlaceStateChange;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingPlaceStateChangeDTO implements Serializable{

    private Long id;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime date;

    private ParkingPlaceState state;

    public static ParkingPlaceStateChangeDTO fromEntity(ParkingPlaceStateChange stateChange) {
        return ParkingPlaceStateChangeDTO.builder()
                .id(stateChange.getId())
                .date(stateChange.getDate())
                .state(stateChange.getState())
                .build();
    }
}
