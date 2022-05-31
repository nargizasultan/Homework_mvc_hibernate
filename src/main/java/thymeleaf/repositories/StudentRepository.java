package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {
    private final EntityManager entityManager;


    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public void save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }


    public Student findById(UUID studentId) {
        return entityManager.find(Student.class, studentId);
    }


    public List<Student> findAll() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    public void removeById(UUID studentId) {
        entityManager.createQuery("delete from Student s where s.id = ?1")
                .setParameter(1, studentId)
                .executeUpdate();
    }


    public List<Student> findByGroupId(UUID groupId) {
        return entityManager.createQuery("select s from Student s join Group g on g.id=?1", Student.class).setParameter(1, groupId).getResultList();
    }
}
