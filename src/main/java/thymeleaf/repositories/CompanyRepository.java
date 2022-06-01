package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.models.Company;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository

public class CompanyRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public void save(Company company) {

        entityManager.persist(company);

    }

    @Transactional
    public Company findById(Long companyId) {
        return entityManager.find(Company.class, companyId);
    }

    @Transactional
    public List<Company> findAll() {
        return entityManager.createQuery("select c from Company c", Company.class)
                .getResultList();
    }
    @Transactional
    public void removeById(Long companyId) {
        entityManager.remove(entityManager.find(Company.class, companyId));
    }
    @Transactional
    public void update(Long companyId, Company newCompany) {

        Company company = findById(companyId);
        company.setCompanyName(newCompany.getCompanyName());
        company.setLocatedCountry(newCompany.getLocatedCountry());
        entityManager.persist(company);
    }
}
