package pl.edu.agh.repository.impl;


import pl.edu.agh.model.ParkingPlace;
import pl.edu.agh.model.ParkingPlaceStateChange;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ParkingPlaceRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public void saveStateChange(ParkingPlaceStateChange stateChange) {
        entityManager.persist(stateChange);
    }

    public void saveParkingPlace(ParkingPlace parkingPlace) {
        entityManager.persist(parkingPlace);
    }

    public ParkingPlace getParkingPlace(Long parkingPlaceId) {
        return entityManager.find(ParkingPlace.class, parkingPlaceId);
    }

    public ParkingPlace getParkingPlace(Long sectionId, Long numberId) {
        try {
            return (ParkingPlace) entityManager
                    .createNamedQuery("parkingPlace.findByPlaceAndSection")
                    .setParameter("sectionNumber", sectionId)
                    .setParameter("placeNumber", numberId).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }

    }

    public void updateParkingPlace(ParkingPlace parkingPlace) {
        entityManager.merge(parkingPlace);
    }

    public List<ParkingPlace> getAllParkingPlaces() {
        return entityManager.createNamedQuery("parkingPlace.findAll", ParkingPlace.class).getResultList();
    }

    public List<ParkingPlace> getOccupiedParkingPlaces() {
        return entityManager.createNamedQuery("parkingPlace.findOnlyOccupied", ParkingPlace.class).getResultList();
    }

    public List<ParkingPlace> getParkingPlacesByStreet(String street) {
        return entityManager.createNamedQuery("parkingPlace.findByStreet", ParkingPlace.class)
                .setParameter("street", street)
                .getResultList();
    }

    public List<ParkingPlace> getParkingPlacesBySectionNumber(Long sectionNumber) {
        return entityManager.createNamedQuery("parkingPlace.findBySectionNumber", ParkingPlace.class)
                .setParameter("sectionNumber", sectionNumber)
                .getResultList();
    }
}
