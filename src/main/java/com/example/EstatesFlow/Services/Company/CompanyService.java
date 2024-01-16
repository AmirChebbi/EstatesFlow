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
    public ResponseEntity<Object> getById( long id);

    public ResponseEntity<Object> getAll(long pageNumber);

    public ResponseEntity<Object> addCompany(CompanyDTO companyDTO);

    public ResponseEntity<Object> updateCompany(CompanyDTO companyDTO);
    public ResponseEntity<Object> deleteById(long id);


}
