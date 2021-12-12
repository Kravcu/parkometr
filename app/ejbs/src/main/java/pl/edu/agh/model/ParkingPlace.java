package pl.edu.agh.model;

import lombok.Data;
import pl.edu.agh.enums.ParkingPlaceState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "parking_place")
@NamedQueries({
        @NamedQuery(name = "parkingPlace.findAll", query = "SELECT pp FROM ParkingPlace pp"),
        @NamedQuery(name = "parkingPlace.findByPlaceAndSection", query = "SELECT pp FROM ParkingPlace pp WHERE pp.sectionNumber = :sectionNumber AND pp.placeNumber = :placeNumber ORDER BY pp.sectionNumber, pp.placeNumber ASC"),
        @NamedQuery(name = "parkingPlace.findOnlyOccupied", query = "SELECT pp FROM ParkingPlace pp WHERE pp.currentState='OCCUPIED'"),
        @NamedQuery(name = "parkingPlace.findByStreet", query = "SELECT pp FROM ParkingPlace pp WHERE pp.street=:street"),
        @NamedQuery(name = "parkingPlace.findBySectionNumber", query = "SELECT pp FROM ParkingPlace pp WHERE pp.sectionNumber = :sectionNumber")
})
public class ParkingPlace implements Serializable {

    @Id
    private Long id;

    @Column(name = "section_number", nullable = false)
    private Long sectionNumber;

    @Column(name = "place_number", nullable = false)
    private Long placeNumber;

    @Column(name = "current_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingPlaceState currentState;

    @Column(name="street", nullable = false)
    private String street;

    @OneToMany(mappedBy = "parkingPlace", fetch = FetchType.EAGER)
    @OrderBy("date DESC")
    private List<ParkingPlaceStateChange> stateChanges;

    @OneToMany(mappedBy = "parkingPlace", fetch = FetchType.LAZY)
    @OrderBy("purchase DESC")
    private List<Ticket> tickets;

}
