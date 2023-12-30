package com.example.EstatesFlow.Services;

import com.example.EstatesFlow.DAO.Entities.Company;
import com.example.EstatesFlow.DAO.Entities.Project;
import com.example.EstatesFlow.DAO.Repositories.CompanyRepository;
import com.example.EstatesFlow.DAO.Repositories.ProjectRepository;
import com.example.EstatesFlow.DTO.CompanyDTO;
import com.example.EstatesFlow.DTO.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Vector;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDTO getById(@PathVariable Long id){
        return new CompanyDTO( companyRepository.getById(id));
    }

    public Vector<CompanyDTO> getAll(){
        Vector<CompanyDTO> companyDTOS = new Vector<>();
        List<Company> companies=  companyRepository.findAll();
        for (Company company : companies){
            companyDTOS.add(new CompanyDTO(company));
        }
        return companyDTOS;
    }

    public Company addCompany(@RequestBody Company company){
        return companyRepository.save(company);
    }

    public Company updateCompany(@PathVariable long id, @RequestBody Company company){
        company.setId(id);
        return companyRepository.save(company);
    }

    public void deleteById(@PathVariable long id) {
        companyRepository.deleteById(id);
    }

}
