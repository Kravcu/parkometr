package pl.edu.agh.repository;


import pl.edu.agh.model.Notification;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository {
    List<Notification> get(LocalDateTime date, Long sectionNumber);
    void save(Notification notification);
    List<Notification> get(LocalDateTime from, LocalDateTime to, Long sectionNumber);
}
