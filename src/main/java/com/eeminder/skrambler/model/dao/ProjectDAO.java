package com.eeminder.skrambler.model.dao;

import com.eeminder.skrambler.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ProjectDAO {

    @Autowired
    EntityManager entityManager;

    public List<Project> getAll() {
        return entityManager.createQuery("from Project",Project.class).getResultList();
    }

    public Project get(int id) {
        return entityManager.find(Project.class,id);
    }

    public void update(Project p) {
        entityManager.refresh(p);
    }
    public void create(Project p) {
        entityManager.persist(p);
    }
}
