package com.example.EstatesFlow.Services.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Repositories.Company.CompanyRepository;
import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Vector;


public interface CompanyService {
    public ResponseEntity<Object> getById(@PathVariable Long id);

    public ResponseEntity<Object> getAll();

    public ResponseEntity<Object> addCompany(@RequestBody Company company);

    public ResponseEntity<Object> updateCompany(@PathVariable long id, @RequestBody Company company);
    public ResponseEntity<Object> deleteById(@PathVariable long id);

}
