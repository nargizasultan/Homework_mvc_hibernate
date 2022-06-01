package thymeleaf.repositories;

import org.springframework.stereotype.Repository;

import thymeleaf.models.Group;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

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

    public List<Group> findByCourseId(UUID courseId) {
        return entityManager.createQuery("select g from Group g where (select c from Course c where c.id = ?1) member of g.courses", Group.class).setParameter(1, courseId).getResultList();
    }

    public void update(UUID groupId, Group group) {
        Group group1 = findById(groupId);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        entityManager.persist(group1);
    }
}
