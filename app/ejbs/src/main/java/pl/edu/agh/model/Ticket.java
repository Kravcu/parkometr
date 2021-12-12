package pl.edu.agh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parking_place_id")
    private ParkingPlace parkingPlace;

    private LocalDateTime purchase;

    private LocalDateTime expiration;

    private String registerPlate;
}
