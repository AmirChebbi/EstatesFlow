package com.example.EstatesFlow.Controllers.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.Services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        return companyService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCompany(@PathVariable long id, @RequestBody Company company){
        company.setId(id);
        return companyService.updateCompany(id ,company);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        companyService.deleteById(id);
    }

}

