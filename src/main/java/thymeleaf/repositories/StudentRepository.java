package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    @Transactional
    public void save(Student student) {

        entityManager.persist(student);

    }

    @Transactional
    public Student findById(Long studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Transactional
    public List<Student> findAll() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }
    @Transactional
    public void removeById(Long studentId) {
        entityManager.remove(entityManager.find(Student.class, studentId));
    }

    @Transactional
    public List<Student> findByGroupId(Long groupId) {
        return entityManager.createQuery("select s from Student s where s.group.id=?1", Student.class).setParameter(1, groupId).getResultList();
    }
    @Transactional
    public void update(Long studentId, Student student) {
        Student student1 = findById(studentId);
        student1.setFirstName(student.getFirstName());
       student1.setEmail(student.getEmail());
       student1.setLastName(student.getLastName());
       student1.setStudyFormat(student.getStudyFormat());
    }
}
