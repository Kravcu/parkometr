package pl.rwalski.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rwalski.configuration.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDetailsDTO {

    private Long id;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime purchase;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime expiration;

    private String registerPlate;
}
