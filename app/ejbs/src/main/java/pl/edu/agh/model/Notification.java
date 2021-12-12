package pl.edu.agh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.enums.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@NamedQueries({
        @NamedQuery(name = "Notification.findByDateAndSectionNumber",
                query = "SELECT n FROM Notification n WHERE  n.generated > :date AND n.sectionNumber = :sectionNumber"),
        @NamedQuery(name = "Notification.findByDatesAndSectionNumber",
                query = "SELECT n FROM Notification n WHERE  n.generated > :from AND n.generated < :to AND n.sectionNumber = :sectionNumber ORDER BY n.generated ASC")
})
public class Notification implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long placeNumber;

    private Long sectionNumber;

    @Enumerated(EnumType.STRING)
    private Event event;

    private LocalDateTime generated;
}
