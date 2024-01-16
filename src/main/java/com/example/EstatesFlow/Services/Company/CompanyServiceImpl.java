package com.example.EstatesFlow.Services.Company;

import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.DTO.Company.CompanyDTOMapper;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Exceptions.UnauthorizedActionException;
import com.example.EstatesFlow.Repositories.Company.CompanyRepository;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final CompanyDTOMapper companyDTOMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyDTOMapper companyDTOMapper) {
        this.companyRepository = companyRepository;
        this.companyDTOMapper = companyDTOMapper;
    }

    @Override
    public ResponseEntity<Object> getById(long id) {
        Company company = companyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This company doesn't exist !!"));
        return ResponseHandler.generateResponse(companyDTOMapper.apply(company), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object>getAll(long pageNumber) {
        Pageable pageable = (Pageable) PageRequest.of((int) pageNumber,4);
        List<Company> companies = companyRepository.getAllPaged(pageable);
        if (companies.isEmpty() && pageNumber > 1){
            return getById(1);
        }
        return ResponseHandler.generateResponse(companies.stream().map(companyDTOMapper).toList(), HttpStatus.OK, companies.size(), companyRepository.getCountPaged());
    }

    @Override
    public ResponseEntity<Object> addCompany(CompanyDTO companyDTO) {
        if (companyRepository.findByDTO(companyDTO.coName(),companyDTO.coDescription()).isEmpty()){
            companyRepository.save(new Company(
                    companyDTO.coName(),
                    companyDTO.coDescription(),
                    companyDTO.imageURL()
                    )
            );
            String successMessage = "Company Saved Successfully !!";
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new UnauthorizedActionException("Company already Exists");
        }
    }

    @Override
    public ResponseEntity<Object> updateCompany(CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyDTO.id()).orElseThrow(()-> new ResourceNotFoundException("Company doesn't exsit !!"));
        company.setCoDescription(companyDTO.coDescription());
        company.setCoName(companyDTO.coName());
        company.setImageURL(companyDTO.imageURL());
        companyRepository.save(company);
        String successMessage = String.format("Company Updated Successfully !!");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        if (!companyRepository.findById(id).isEmpty()) {
            companyRepository.deleteById(id);
            String successMessage = String.format("Company deleted successfully !!");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Company doesn't exist !!");
        }
    }

}
