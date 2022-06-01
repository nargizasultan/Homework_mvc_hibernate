package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public class CourseRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public void save(Course course) {

        entityManager.persist(course);

    }
    @Transactional
    public Course findById(UUID courseId) {
        return entityManager.find(Course.class, courseId);
    }
    @Transactional
    public List<Course> findAll() {
        return entityManager
                .createQuery("select c from Course c", Course.class)
                .getResultList();
    }
    @Transactional
    public void deleteById(UUID courseId) {
        entityManager.remove(entityManager.find(Course.class, courseId));
    }
    @Transactional
    public List<Course> findByCompanyId(UUID companyId) {
        return entityManager
                .createQuery("select s from Course s where s.company.id = ?1", Course.class)
                .setParameter(1, companyId)
                .getResultList();
    }

    @Transactional
    public void update(UUID courseID, Course newCourse) {
        Course course1 = findById(courseID);

        course1.setCourseName(newCourse.getCourseName());

        course1.setDuration(newCourse.getDuration());

        entityManager.persist(course1);

    }
}
