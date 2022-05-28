package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Company;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

@Repository

public class CompanyRepository {
    private final EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public void save(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }


    public Company findById(UUID companyId) {
        return entityManager.find(Company.class, companyId);
    }


    public List<Company> findAll() {
        return entityManager.createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    public void removeById(UUID companyId) {
        entityManager.createQuery("delete from Company c where c.id = ?1")
                .setParameter(1, companyId)
                .executeUpdate();
    }
}
