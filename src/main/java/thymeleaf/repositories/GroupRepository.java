package thymeleaf.repositories;

import org.springframework.stereotype.Repository;

import thymeleaf.models.Group;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;
import java.util.UUID;

@Repository
public class GroupRepository {
    private final EntityManager entityManager;

    public GroupRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public void save(Group group) {
        entityManager.getTransaction().begin();
        entityManager.persist(group);
    entityManager.getTransaction().commit();
    }


    public Group findById(UUID groupID) {
        return entityManager.find(Group.class, groupID);
    }


    public List<Group> findAll() {
        return entityManager.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    public void removeById(UUID groupId) {
        entityManager.createQuery("delete from Group g where g.id = ?1")
                .setParameter(1, groupId)
                .executeUpdate();
    }
}
