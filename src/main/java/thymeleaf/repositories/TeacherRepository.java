package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

@Repository
public class TeacherRepository {
    private final EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public void save(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }


    public Teacher findById(UUID teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }


    public List<Teacher> findAll() {
        return entityManager.createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }

    public void removeById(UUID teacherId) {
        entityManager.createQuery("delete from Teacher t where t.id = ?1")
                .setParameter(1, teacherId)
                .executeUpdate();
    }
}
