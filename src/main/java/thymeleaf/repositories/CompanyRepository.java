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

    public void update(UUID companyId, Company newCompany) {
//        entityManager.createQuery
//                ("update Company c set c.companyName=:companyName, " +
//                        "c.locatedCountry=:locatedCountry  where c.id=:id", Company.class).
//                setParameter("companyName", newCompany.getCompanyName()).
//                setParameter("locatedCountry", newCompany.getLocatedCountry()).
//                setParameter("id", companyId).executeUpdate();

        Company company = findById(companyId);
        company.setCompanyName(newCompany.getCompanyName());
        company.setLocatedCountry(newCompany.getLocatedCountry());
        entityManager.persist(company);
    }
}
