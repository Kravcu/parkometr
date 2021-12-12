package pl.edu.agh.repository.impl;

import pl.edu.agh.model.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TicketRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Ticket saveTicket(Ticket ticket) {
        entityManager.persist(ticket);
        return ticket;
    }

}
