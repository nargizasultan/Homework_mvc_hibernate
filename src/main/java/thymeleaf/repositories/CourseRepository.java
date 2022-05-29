package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

@Repository
public class CourseRepository {
    private final EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    public Course findById(UUID courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public List<Course> findAll() {
        return entityManager
                .createQuery("select c from Course c", Course.class)
                .getResultList();
    }

    public void deleteById(UUID courseId) {
        entityManager.remove(entityManager.find(Course.class, courseId));
    }

    public List<Course> findByCompanyId(UUID companyId) {
        return entityManager
                .createQuery("select s from Course s join Company c on  c.id = ?1", Course.class)
                .setParameter(1, companyId)
                .getResultList();
    }

}
