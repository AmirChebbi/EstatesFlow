package com.example.EstatesFlow.Services.Company;

import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Override
    public ResponseEntity<Object> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object>getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> addCompany(Company company) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateCompany(long id, Company company) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        return null;
    }
}
