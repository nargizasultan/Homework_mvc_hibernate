package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class TeacherRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    @Transactional
    public void save(Teacher teacher) {

        entityManager.persist(teacher);

    }

    @Transactional
    public Teacher findById(UUID teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    @Transactional
    public List<Teacher> findAll() {
        return entityManager.createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }
    @Transactional
    public void removeById(UUID teacherId) {
        entityManager.remove(entityManager.find(Teacher.class, teacherId));
    }
    @Transactional

    public List<Teacher> findByCourseId(UUID courseId) {
        return entityManager.createQuery("select t from Teacher t join Course c on c.id=?1", Teacher.class).setParameter(1, courseId).getResultList();
    }
    @Transactional
    public void update(UUID teacherId, Teacher teacher) {
        Teacher teacher1 = findById(teacherId);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setLastName(teacher.getLastName());
        entityManager.persist(teacher1);
    }
}
