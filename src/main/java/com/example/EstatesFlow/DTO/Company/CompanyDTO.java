package com.example.EstatesFlow.DTO.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Services.Project.ProjectService;
import java.util.List;

public class CompanyDTO {
    private long id;

    private String coName;

    private String imageURL;

    private String coDescription;

    private List<String> projects ;

    public CompanyDTO(Company company){
        this.id=company.getId();
        this.coName=company.getCoName();
        this.imageURL=company.getImageURL();
        this.coDescription=company.getCoDescription();
        this.projects= ProjectService.mapProjectsToNames(company.getProjects());
    }
}
