package pl.rwalski.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketCreationDTO {
    private long placeNumber;
    private long sectionNumber;
    private long durationInMinutes;
    protected String registerPlate;
}
