package com.example.EstatesFlow.Controllers.Company;

import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.Services.Company.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        return companyService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestParam long pageNumber){
        return companyService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.addCompany(companyDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyDTO companyDTO){
        return companyService.updateCompany(companyDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        companyService.deleteById(id);
    }

}

