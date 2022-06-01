package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Company;
import thymeleaf.repositories.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Transactional
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public Company findById(UUID companyId) {
        return companyRepository.findById(companyId);
    }
    @Transactional

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void removeById(UUID companyId) {
        companyRepository.removeById(companyId);
    }
    @Transactional
    public void update(UUID companyId, Company company) {
        companyRepository.update(companyId, company);
    }
}
