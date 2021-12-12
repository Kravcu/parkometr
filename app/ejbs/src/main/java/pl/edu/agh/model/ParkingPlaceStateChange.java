package pl.edu.agh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.enums.ParkingPlaceState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parking_place_state_change")
public class ParkingPlaceStateChange {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingPlaceState state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_place_id", nullable = false)
    private ParkingPlace parkingPlace;

}
