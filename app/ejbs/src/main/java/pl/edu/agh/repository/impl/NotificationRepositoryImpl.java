package pl.edu.agh.repository.impl;


import pl.edu.agh.model.Notification;
import pl.edu.agh.repository.NotificationRepository;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Stateless(mappedName = "NotificationRepository")
@Remote(NotificationRepository.class)
public class NotificationRepositoryImpl implements NotificationRepository {

    @PersistenceContext
    private EntityManager entityManager;


    private static final Logger logger = Logger.getLogger(NotificationRepositoryImpl.class.getName());

    @Override
    public void save(Notification notification) {
        entityManager.persist(notification);
    }

    @Override
    @Transactional
    public List<Notification> get(LocalDateTime date, Long sectionNumber) {
        List<Notification> resultList = entityManager.createNamedQuery("Notification.findByDateAndSectionNumber", Notification.class)
                .setParameter("date", date)
                .setParameter("sectionNumber", sectionNumber)
                .getResultList();

        logger.info("" + resultList.size());
        return resultList;
    }

    @Override
    @Transactional
    public List<Notification> get(LocalDateTime from, LocalDateTime to, Long sectionNumber) {
        return entityManager.createNamedQuery("Notification.findByDatesAndSectionNumber", Notification.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("sectionNumber", sectionNumber)
                .getResultList();
    }
}
