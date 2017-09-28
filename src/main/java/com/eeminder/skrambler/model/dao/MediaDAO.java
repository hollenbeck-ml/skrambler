package com.eeminder.skrambler.model.dao;

import com.eeminder.skrambler.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class MediaDAO {
    @Autowired
    EntityManager entityManager;

    public List<Media> getAll() {
        return entityManager.createQuery("from Media",Media.class).getResultList();
    }
    public Media get(int id) {
        return entityManager.find(Media.class,id);
    }
    public void save(Media media) {
        media.setId(null);
        entityManager.persist(media);
        entityManager.flush();
        System.out.println("wat te fux"+media.getId());
    }
}
