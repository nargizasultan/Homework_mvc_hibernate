package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.services.CompanyService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @ModelAttribute("companyList")
    public List<Company>findAllCompanies(){
        return companyService.findAll();
    }
    @GetMapping
    public String findAll(){
        return "companies/all-companies";
    }
    @GetMapping("/save")
    public String saveCompany(Model model){
        model.addAttribute("emptyCompany", new Company());
        return "companies/save-new-company";
    }
    @PostMapping("/save")
    public String save(Company company){
        companyService.save(company);
        return "redirect:/api/companies";
    }

    @GetMapping("/update/{companyId}")
    public String updateCompany(Model model, @PathVariable ("companyId")UUID companyId){

        model.addAttribute("updateCompany", companyService.findById(companyId));
        return "companies/update-company";
    }
    @PostMapping ("/update/{companyId}")
    public String update(Company company,
                             @PathVariable ("companyId")UUID companyId){
        companyService.update(companyId, company);
        return "redirect:/api/companies";
    }

    @GetMapping("/delete/{companyId}")
    public String delete(@PathVariable UUID companyId){
        Company company = companyService.findById(companyId);
        Course course = company.getCourses().get(0);
        companyService.removeById(companyId);

        return "redirect:/api/companies";
    }


}
