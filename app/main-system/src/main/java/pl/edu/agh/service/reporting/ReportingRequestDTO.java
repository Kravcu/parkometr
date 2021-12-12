package pl.edu.agh.service.reporting;


import lombok.Data;
import pl.edu.agh.exceptions.InvalidReportingRequestException;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class ReportingRequestDTO {
    private LocalDateTime from;
    private LocalDateTime to;
    private Long sectionNumber;

    public ReportingRequestDTO(String from, String to, Long sectionNumber) {
        if (from == null || to==null || sectionNumber == null) {
            throw new InvalidReportingRequestException();
        }
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
        this.sectionNumber = sectionNumber;
    }

}
