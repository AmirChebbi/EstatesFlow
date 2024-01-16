package com.example.EstatesFlow.Controllers.Company;

import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.Services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getAll(long pageNumber){
        return companyService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@RequestBody CompanyDTO company){
        return companyService.addCompany(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCompany(@PathVariable long id, @RequestBody CompanyDTO company){
        return companyService.updateCompany(id ,company);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        companyService.deleteById(id);
    }

}

