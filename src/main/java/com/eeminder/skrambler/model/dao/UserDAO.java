package com.eeminder.skrambler.model.dao;

import com.eeminder.skrambler.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAll() {
        return entityManager.createQuery("from User",User.class).getResultList();
    }
    public void create(User user) {
        entityManager.persist(user);
    }
}
