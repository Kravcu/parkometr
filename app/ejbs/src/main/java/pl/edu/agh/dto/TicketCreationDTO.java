package pl.edu.agh.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class TicketCreationDTO implements Serializable {
    private long placeNumber;
    private long sectionNumber;
    private long durationInMinutes;
    private String registerPlate;
}
