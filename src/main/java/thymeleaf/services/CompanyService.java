package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Company;
import thymeleaf.repositories.CompanyRepository;

import java.util.List;
import java.util.UUID;
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public void save(Company company) {
        companyRepository.save(company);
    }


    public Company findById(UUID companyId) {
        return companyRepository.findById(companyId);
    }


    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void removeById(UUID companyId) {
        companyRepository.removeById(companyId);
    }

    public void update(UUID companyId, Company company) {
        companyRepository.update(companyId, company);
    }
}
