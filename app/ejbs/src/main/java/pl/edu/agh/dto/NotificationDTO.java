package pl.edu.agh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.enums.Event;
import pl.edu.agh.model.Notification;
import pl.edu.agh.model.ParkingPlace;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO implements Serializable {
    private Long placeId;
    private Long placeNumber;
    private Long sectionNumber;
    private Event event;
    private LocalDateTime generated;

    public NotificationDTO(ParkingPlace parkingPlace, Event event) {
        this.event = event;
        this.placeId = parkingPlace.getId();
        this.placeNumber = parkingPlace.getPlaceNumber();
        this.sectionNumber = parkingPlace.getSectionNumber();
        this.generated = LocalDateTime.now();
    }

    public Notification toEntity() {
        return Notification.builder()
                .event(this.event)
                .generated(this.generated)
                .sectionNumber(this.sectionNumber)
                .placeNumber(this.placeNumber)
                .build();
    }


    public static NotificationDTO fromEntity(Notification notification) {
        return NotificationDTO.builder()
                .placeNumber(notification.getPlaceNumber())
                .sectionNumber(notification.getSectionNumber())
                .event(notification.getEvent())
                .generated(notification.getGenerated())
                .build();
    }

}
