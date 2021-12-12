package pl.edu.agh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.dto.adapters.LocalDateAdapter;
import pl.edu.agh.model.Ticket;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDetailsDTO implements Serializable {

    private Long id;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime purchase;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime expiration;

    private String registerPlate;

    public static TicketDetailsDTO fromEntity(Ticket ticket) {
        return TicketDetailsDTO.builder()
                .id(ticket.getId())
                .purchase(ticket.getPurchase())
                .expiration(ticket.getExpiration())
                .registerPlate(ticket.getRegisterPlate())
                .build();
    }
}
