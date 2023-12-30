package com.example.EstatesFlow.Controllers;

import com.example.EstatesFlow.DAO.Entities.Company;
import com.example.EstatesFlow.DTO.CompanyDTO;
import com.example.EstatesFlow.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getById/{id}")
    public CompanyDTO getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @GetMapping("/getAll")
    public Vector<CompanyDTO> getAll(){
        return companyService.getAll();
    }

    @PostMapping("/add")
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping("/update/{id}")
    public Company updateCompany(@PathVariable long id, @RequestBody Company company){
        company.setId(id);
        return companyService.updateCompany(id ,company);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        companyService.deleteById(id);
    }

}

