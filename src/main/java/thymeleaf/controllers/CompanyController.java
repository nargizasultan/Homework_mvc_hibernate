package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Company;
import thymeleaf.services.CompanyService;

import java.util.List;

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
        return "allCompanies";
    }
    @GetMapping("/save")
    public String saveCompany(Model model){
        model.addAttribute("emptyCompany", new Company());
        return "save-new-company";
    }
    @PostMapping("/save")
    public String save(Company company){
        companyService.save(company);
        return "redirect:/api/companies";
    }


}
