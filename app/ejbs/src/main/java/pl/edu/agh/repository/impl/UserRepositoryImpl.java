package pl.edu.agh.repository.impl;

import pl.edu.agh.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByLogin(String login) {
        return entityManager.createNamedQuery("user.findByLogin", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    public void updatePassword(String name, String encodedPassword) {
        entityManager.createNamedQuery("user.updatePassword")
                .setParameter("password", encodedPassword)
                .setParameter("login", name)
                .executeUpdate();
    }
}
