package pl.edu.agh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Event {
    TICKET_NOT_BOUGHT("Possibly ticket was not bought"),
    TICKET_EXPIRED("Possible ticket expired"),
    PARKING_PLACE_OCCUPIED("Parking place has just been taken"),
    PARKING_PLACE_RELEASED("Parking place has just been released"),
    TICKET_JUST_BOUGHT("Ticket has just been bought");

    private String message;
}
